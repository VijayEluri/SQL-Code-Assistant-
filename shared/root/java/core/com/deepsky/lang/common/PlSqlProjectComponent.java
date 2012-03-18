/*
 * Copyright (c) 2009,2010 Serhiy Kulyk
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 *     1. Redistributions of source code must retain the above copyright
 *       notice, this list of conditions and the following disclaimer.
 *     2. Redistributions in binary form must reproduce the above copyright
 *       notice, this list of conditions and the following disclaimer in the
 *       documentation and/or other materials provided with the distribution.
 *
 * SQL CODE ASSISTANT PLUG-IN FOR INTELLIJ IDEA IS PROVIDED BY SERHIY KULYK
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL SERHIY KULYK BE LIABLE FOR ANY
 * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package com.deepsky.lang.common;

import com.deepsky.database.CacheManagerListener;
import com.deepsky.database.ConnectionInfo;
import com.deepsky.database.ConnectionManagerListener;
import com.deepsky.database.MyProgressIndicator;
import com.deepsky.database.impl.ConnectionManagerImpl;
import com.deepsky.database.ora.DbUrl;
import com.deepsky.database.ora.DbUrlSID;
import com.deepsky.database.ora2.DbSchemaIndexer;
import com.deepsky.lang.integration.CodeChangeEventAggregator;
import com.deepsky.lang.integration.LocalFSChangeTracker;
import com.deepsky.lang.integration.PlSqlFileChangeTracker;
import com.deepsky.lang.plsql.ConfigurationException;
import com.deepsky.lang.plsql.indexMan.FSIndexer;
import com.deepsky.lang.plsql.indexMan.IndexManagerImpl;
import com.deepsky.lang.plsql.sqlIndex.IndexManager;
import com.deepsky.navigation.DbObjectContributor;
import com.deepsky.navigation.NameLookupService;
import com.deepsky.navigation.impl.DbObjectContribNewImpl;
import com.deepsky.navigation.impl.NameLookupServiceImpl;
import com.deepsky.settings.SqlCodeAssistantSettings;
import com.deepsky.view.query_pane.QueryResultWindow;
import com.deepsky.view.query_pane.converters.TIMESTAMPLTZ_Convertor;
import com.deepsky.view.query_pane.converters.TIMESTAMPTZ_Convertor;
import com.deepsky.view.query_pane.converters.TIMESTAMP_Convertor;
import com.deepsky.view.query_pane.util.DateTimeParser;
import com.deepsky.view.utils.ProgressIndicatorHelper;
import com.intellij.codeInsight.daemon.DaemonCodeAnalyzer;
import com.intellij.ide.plugins.IdeaPluginDescriptor;
import com.intellij.ide.plugins.PluginManager;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.components.ProjectComponent;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.extensions.Extensions;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.openapi.fileTypes.FileTypeFactory;
import com.intellij.openapi.fileTypes.FileTypeManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;


public class PlSqlProjectComponent implements ProjectComponent {

    private final Logger log = Logger.getInstance("#PlSqlProjectComponent");

    private static final String componentName = "PlSqlProjectComponent";

    private Project project;

    private ConnectionManagerListener connectionListener;
    private CacheManagerListener cacheListener;

    private ConnectionManagerImpl connectionManager;
    private QueryResultWindow qrWindow;

    private DbSchemaIndexer dbIndexer;
    private FSIndexer fsIndexer;
    private IndexManager indexManager;

    private DbUrl dbUrl;
    private NameLookupService nameLookupService;

    public PlSqlProjectComponent(Project project) {
        this.project = project;
    }

    public void projectOpened() {
        dbUrl = new DbUrlSID(IndexManager.FS_URL.getFullUrl()) {
            public String getAlias() {
                return project.getName();
            }
        };
        PluginKeys.LOCAL_FS_URL.putData(dbUrl, project);

        final SqlCodeAssistantSettings settings = SqlCodeAssistantSettings.getInstance(project);
        PluginKeys.PLUGIN_SETTINGS.putData(settings, project);

        DateTimeParser parser = DateTimeParser.getInstance(project);

        PluginKeys.TS_CONVERTOR.putData(new TIMESTAMP_Convertor(parser, settings), project);
        PluginKeys.TSTZ_CONVERTOR.putData(new TIMESTAMPTZ_Convertor(parser, settings), project);
        PluginKeys.TSLTZ_CONVERTOR.putData(new TIMESTAMPLTZ_Convertor(parser, settings), project);

        connectionListener = new StateListenerImpl();
        cacheListener = new CacheManagerListenerImpl();

        indexManager = new IndexManagerImpl(project);
        PluginKeys.SQL_INDEX_MAN.putData(indexManager, project);
        indexManager.start();


        // create USER schema database index
        dbIndexer = new DbSchemaIndexer(project, indexManager);
        PluginKeys.DB_NAMES_INDEXER.putData(dbIndexer, project);
        dbIndexer.addListener(cacheListener);

        // create SYS schema database index
        // todo -- implement

        connectionManager = new ConnectionManagerImpl(project, dbIndexer);
        PluginKeys.CONNECTION_MANAGER.putData(connectionManager, project);
        connectionManager.addStateListener(connectionListener);
        connectionManager.addStateListener(indexManager);

        // activate connection if it is elegable for logging on start
        final String session = settings.getConnection();
        if (session != null) {
            // run some later to avoid collisions with setting of Project ref in the context
            ApplicationManager.getApplication().invokeLater(new Runnable() {
                public void run() {
                    try {
                        DbUrl dbUrl = DbUrl.parse(session);
                        MyProgressIndicator indicator = connectionManager.activateSessionOnStart(dbUrl);
                        if (indicator != null) {
                            String url = indicator.targetDatabase();
                            new ProgressIndicatorHelper(project, "Connecting to " + url).runBackgrounableWithProgressInd(indicator, true);
                        }

                    } catch (ConfigurationException e) {
                        // incorrect URL ?
                        settings.setConnection(null);
                    }
                }
            });
        }

        long ms = System.currentTimeMillis();
        // preload indexes on start
        int nbr = 0;

        boolean flag = settings.isAccessOfflineEnabled();
        ConnectionInfo[] infos = connectionManager.getSessionList();
        DbUrl[] urls = new DbUrl[infos.length];
        for (int i = 0; i < infos.length; i++) {
            urls[i] = infos[i].getUrl();
            nbr++;
        }
        indexManager.enableOfflineCache(urls, flag);

        ms = System.currentTimeMillis() - ms;
        log.info("Nbr preloaded indexes: " + nbr + ", time spent: " + ms);

        // Create the Request Result Pane
        qrWindow = new QueryResultWindow(project, connectionManager);
        PluginKeys.QR_WINDOW.putData(qrWindow, project);

        // Create DB Browser window
//        dbBrowser = new DBBrowserWindow(project);

        DbObjectContributor dbObjContrib = new DbObjectContribNewImpl(); //utorImpl();
        PluginKeys.DB_OBJECT_CONTR.putData(dbObjContrib, project);

        // create SQL file indexer
        fsIndexer = new FSIndexer(project, indexManager.getFSIndex());
        fsIndexer.start();
//        PluginKeys.FS_SQL_NAMES_INDEXER.putData(fsIndexManager, project);

        LocalFSChangeTracker localFSAggr = new LocalFSChangeTracker(project, fsIndexer);
        localFSAggr.start();

        PlSqlFileChangeTracker tracker = new PlSqlFileChangeTracker();
        PluginKeys.PLSQLFILE_CHANGE_TRACKER.putData(tracker, project);

        nameLookupService = new NameLookupServiceImpl(project);
        PluginKeys.NAME_LOOKUP.putData(nameLookupService, project);

        // Make sure there are no plugins with SQL like extensions
        ApplicationManager.getApplication().runWriteAction(new Runnable (){
            public void run() {
//                IdeaPluginDescriptor[] desc = PluginManager.getPlugins();
                FileType sql = FileTypeManager.getInstance().getFileTypeByFileName("test.sql");
                FileType pkb = FileTypeManager.getInstance().getFileTypeByFileName("test.pkb");
                FileType pks = FileTypeManager.getInstance().getFileTypeByFileName("test.pks");

                List<FileType> strangers = new ArrayList<FileType>();
                if( !(sql instanceof PlSqlFileType) ){
                    // Ext 'sql' by someone else, report and disable plugin
                    //PluginManager.getPlugins()
                    //FileTypeManager.getInstance().
                    strangers.add(sql);
                    log.warn("sql ext is used by someone else.");
                }

                if(!( pkb instanceof PlSqlFileType)){
                    // Ext 'pkb' by someone else, report and disable plugin
                    log.warn("pkb ext is used by someone else.");
                    strangers.add(pkb);
                }

                if(!( pks instanceof PlSqlFileType)){
                    // Ext 'pks' by someone else, report and disable plugin
                    log.warn("pks ext is used by someone else.");
                    strangers.add(pks);
                }

                if(strangers.size() > 0){
                    // Format warning messages
                    StringBuilder b1 = new StringBuilder();
                    StringBuilder b2 = new StringBuilder();
                    for(FileType ft: strangers){
                        String ext = ft.getDefaultExtension();
                        if(b1.length() > 0){
                            b1.append(",");
                            b2.append(",");
                        }
                        b1.append(ext);
                        b2.append("*.").append(ext);
                    }

                    String message =
                            "To identify SQL files, SQL Code Assistant plugin uses the following file extensions: sql, pks and pkb.\n" +
                            "But it seems another plugin has already registered these file types. To let SQL Code Assistant plugin work properly " +
                            "you may need to associate " + b1.toString() + " file extension(s) with the plugin.\"" +
                            "Go to Settings -> File Types and add registered patterns " + b2.toString() + " to SQL (PL/SQL) files.";
                    Messages.showWarningDialog(project,
                            message,
                            "Conflict in file extensions detected");
                }
                //PluginManager.getPlugins()[2].setEnabled(false);
                //log.warn("Plugin was disabled!");
                int hh =0;
/*
                final FileTypeFactory[] fileTypeFactories = Extensions.getExtensions(FileTypeFactory.FILE_TYPE_FACTORY_EP);
                for (final FileTypeFactory factory : fileTypeFactories) {
                    try {
                        //initFactory(consumer, factory);
                        factory.
                    } catch (final Error ex) {
                        //PluginManager.disableIncompatiblePlugin(factory, ex);
                    }
                }
*/
            }
        });

    }


    public void projectClosed() {
        connectionManager.removeStateListener(connectionListener);
        connectionManager.close();

        qrWindow.close();

        fsIndexer.stop();
        indexManager.stop();
    }

    @NotNull
    public String getComponentName() {
        return componentName;
    }

    public void initComponent() {
        CodeChangeEventAggregator.getInstance(project).start();
    }

    public void disposeComponent() {
        CodeChangeEventAggregator.getInstance(project).stop();
    }

    private class CacheManagerListenerImpl implements CacheManagerListener {
        public void handleUpdate(int state) {
            if (state == CacheManagerListener.CACHE_UPDATED) {
                runCodeAnalyzer();
            }
        }
    }

    private class StateListenerImpl implements ConnectionManagerListener {
        public void handleUpdate(StateEvent state) {
            if (state.getStatus() == ConnectionManagerListener.DISCONNECTED) {
                PluginKeys.PLUGIN_SETTINGS.getData(project).setConnection(null);
                runCodeAnalyzer();
            } else if (state.getStatus() == ConnectionManagerListener.CONNECTED) {
                PluginKeys.PLUGIN_SETTINGS.getData(project).setConnection(
                        connectionManager.getDbUrl().getFullUrl()
                );
            }
        }
    }

    private void runCodeAnalyzer() {
        ApplicationManager.getApplication().invokeLater(new Runnable() {
            public void run() {
                if (!project.isDisposed()) {
                    DaemonCodeAnalyzer.getInstance(project).restart();
                    log.info("#RESTART code analyzer");
                }
            }
        });
    }
}

