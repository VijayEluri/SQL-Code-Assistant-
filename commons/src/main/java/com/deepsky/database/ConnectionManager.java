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

package com.deepsky.database;

//import com.deepsky.database.exec.SQLExecutor;
import com.deepsky.database.ora.DbUrl;
import com.intellij.lang.ASTNode;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public interface ConnectionManager {

    // check connection status
    boolean isConnected();

    void setAutoCommit(boolean state) throws DBException;
    boolean getAutoCommit();

    List<String> statementListSinceLastCommit();

    ConnectionStatus checkConnection(DbUrl url);

    /**
     * Get Database Url for established connection
     * @return - url or null if not connected to database
     */
    @Nullable
    DbUrl getDbUrl();

    DbMetaInfo getDbMetaInfo();

    ConnectionInfo[] getSessionList();

    ConnectionInfo createSession(DbUrl url, boolean loginOnStartup, boolean repair, int period) throws DbConfigurationException;
    void updateSession(ConnectionInfo cinfo, DbUrl url, boolean loginOnStartup, boolean repair, int period);

    /**
     * Remove session from the registry,
     * if the session was active at the moment of the request it requests to stop
     * the updater thread and wait until it gets shutdown
     *  
     * @param cinfo
     * @return
     */
    boolean removeSession(ConnectionInfo cinfo);

    void addStateListener(ConnectionManagerListener listener);
    void removeStateListener(ConnectionManagerListener listener);

    void close();

    /**
     * List of DbUrl saved in persistent storage
     * @return
     */
    @NotNull
    DbUrl[] getDbUrlList();

    /**
     * Refresh active session
     */
    void refreshSession();

    void addProcessedStatement(ASTNode node);
}
