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

package com.deepsky.lang.plsql.psi.resolve.psibased;

import com.deepsky.lang.plsql.psi.resolve.ResolveContext777;
import com.deepsky.lang.plsql.psi.resolve.VariantsProcessor777;
import com.deepsky.lang.plsql.psi.resolve.NameNotResolvedException;
import com.deepsky.lang.plsql.psi.resolve.ResolveHelper3;
import com.deepsky.lang.plsql.psi.resolve.impl.UserDefinedTypeHelper;
import com.deepsky.lang.plsql.psi.*;
import com.deepsky.lang.plsql.struct.ExecutableDescriptor;
import com.deepsky.lang.plsql.struct.Type;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.ArrayList;

public class PsiPackageBodyContext  implements ResolveContext777 {

    PackageBody body;
    public PsiPackageBodyContext(PackageBody body) {
        this.body = body;
    }

    @NotNull
    public VariantsProcessor777 create(int narrow_type /* constants from ObjectCache */) {
        return null;
    }

    public PsiElement getDeclaration() {
        return body;
    }

    /**
     * Resolve name in the package scope
     * @param elem
     * @return
     * @throws com.deepsky.lang.plsql.psi.resolve.NameNotResolvedException
     */
    public ResolveContext777 resolve(PsiElement elem) throws NameNotResolvedException {
        String text = elem.getText();

        PlSqlElement[] objects = body.findObjectByName(text);

        List<ResolveContext777> out = new ArrayList<ResolveContext777>();
        for (PlSqlElement e : objects) {
            if(e instanceof TypeDeclaration){
                out.add(UserDefinedTypeHelper.createResolveContext((TypeDeclaration)e));
//            }
//            if (e instanceof RecordTypeDecl) {
//                out.add(new PsiRecordTypeContext((RecordTypeDecl) e));
//            } else if (e instanceof TableCollectionDecl) {
//                out.add(new PsiTableCollectionTypeContext((TableCollectionDecl) e));
            } else if (e instanceof VariableDecl) {
                out.add(new PsiVariableContext((VariableDecl) e));
            } else if (e instanceof ExecutableSpec) {
                // validate argument list
                ExecutableSpec execSpec = (ExecutableSpec) e;

                if (elem.getContext() instanceof CallableCompositeName) {
                    // executable with arguments
                    Callable call = (Callable) ((CallableCompositeName) elem.getContext()).getContext();
                    ExecutableDescriptor exec = execSpec.describe();
                    List<String> errors = new ArrayList<String>();
                    if (ResolveHelper3.validateCallArgumentList(exec, call.getCallArgumentList(), errors)) {
                        out.add(new PsiExecutableContext(execSpec));
                    }

                } else if (elem.getContext() instanceof ObjectReference) {
                    // executable without arguments
                    out.add(new PsiExecutableContext(execSpec));
                } else {
                    throw new NameNotResolvedException("Type " + execSpec.getClass() + " not supported");
                }
            }
        }

        if (out.size() == 1) {
            return out.get(0);
        } else {
            throw new NameNotResolvedException("");
        }
    }

    public Type getType() throws NameNotResolvedException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}

