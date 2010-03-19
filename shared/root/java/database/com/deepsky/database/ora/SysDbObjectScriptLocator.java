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

package com.deepsky.database.ora;

import com.deepsky.database.CacheManager;
import com.deepsky.database.fs.SysObjectSqlFile;
import com.deepsky.lang.common.PluginKeys;
import com.deepsky.lang.plsql.struct.SqlScriptLocator;
import com.deepsky.lang.common.PlSqlSupportLoader;
import com.deepsky.database.ConnectionManagerImpl;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.testFramework.LightVirtualFile;

import org.jetbrains.annotations.NotNull;

public class SysDbObjectScriptLocator implements SqlScriptLocator {

    static final long serialVersionUID = -3267944036751679496L;
    
    public String type;
    public String name;

    public SysDbObjectScriptLocator(String type, String name){
        this.type = type;
        this.name = name;
    }

    @NotNull
    public String getPresentableUrl() {
        // todo -- should be revised!!!!
/*
        DbUrl url = PluginKeys.CONNECTION_MANAGER.getData().getDbUrl();
//        DbUrl url = ConnectionManagerImpl.getInstance().getDbUrl();
        String s = "";
        if(url != null){
            s = name + " [SYS" + "@" + url.getHostPortServiceName() + "]";
        } else {
            s = name;
        }
        return s.toLowerCase();
*/
        String s = name + " [SYS]";
        return s.toLowerCase();
    }

    @NotNull
    public String getScriptName() {
        return name;
    }

    @NotNull
    public VirtualFile getScript() {
        // todo -- moved to SqlScriptManager

/*
        CacheManager cacheManager = PluginKeys.CACHE_MANAGER.getData();
        if(cacheManager != null){
//            String content = CacheManager3.getInstance().getSysDbObjectSource(type, name);
            String content = cacheManager.getSysDbObjectSource(type, name);
            if(content != null){
                return new SysObjectSqlFile(content, this);
            }
        }
//        String content = CacheManager3.getInstance().getSysDbObjectSource(type, name);
//        if(content != null){
//            return new SysObjectSqlFile(content, this);
//        }
        return new LightVirtualFile(name, PlSqlSupportLoader.PLSQL, "");
*/
        return null;
    }

}
