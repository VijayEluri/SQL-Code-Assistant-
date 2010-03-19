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

package com.deepsky.lang.plsql.psi.resolve.impl;

import com.deepsky.lang.plsql.psi.resolve.ResolveContext777;
import com.deepsky.lang.plsql.psi.resolve.VariantsProcessor777;
import com.deepsky.lang.plsql.psi.resolve.NameNotResolvedException;
import com.deepsky.lang.plsql.struct.Type;
import com.deepsky.lang.plsql.struct.TableDescriptor;
import com.deepsky.lang.plsql.NotSupportedException;
import com.deepsky.database.SqlScriptManager;
import com.intellij.psi.PsiElement;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;

public class TableContext implements ResolveContext777  {

    Project project;
    TableDescriptor tdesc;
    public TableContext(Project project, TableDescriptor tdesc){
        this.project = project;
        this.tdesc = tdesc;
    }

    @NotNull
    public VariantsProcessor777 create(int narrow_type /* constants from ObjectCache */) {
        return null;
    }

    public PsiElement getDeclaration() {
        return SqlScriptManager.mapToPsiTree(project, tdesc);
    }

    public ResolveContext777 resolve(PsiElement elem) throws NameNotResolvedException {
        Type type = tdesc.getColumnType(elem.getText());
        if (type == null) {
            throw new NameNotResolvedException("Column " + elem.getText() + " not found in the table definition");
        }

        return new PlainTableColumnContext(project, tdesc, elem.getText());
    }

    public Type getType() throws NameNotResolvedException {
        throw new NotSupportedException();
    }
}
