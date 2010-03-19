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

package com.deepsky.lang.plsql.psi.impl.ddl;

import com.deepsky.lang.parser.plsql.PlSqlElementTypes;
import com.deepsky.lang.plsql.SyntaxTreeCorruptedException;
import com.deepsky.lang.plsql.psi.Declaration;
import com.deepsky.lang.plsql.psi.DeclarationList;
import com.deepsky.lang.plsql.psi.PlSqlElementVisitor;
import com.deepsky.lang.plsql.psi.ddl.CreateTriggerDML;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElementVisitor;
import org.jetbrains.annotations.NotNull;

public class CreateTriggerDMLImpl extends CreateTriggerGeneric implements CreateTriggerDML {

    public CreateTriggerDMLImpl(ASTNode astNode) {
        super(astNode);
    }

//    @NotNull
//    public String getTriggerName() {
//        try {
//            return getNode().findChildByType(PlSqlElementTypes.TRIGGER_NAME).getText();
//        } catch (NullPointerException e) {
//            throw new SyntaxTreeCorruptedException();
//        }
//    }

    @NotNull
    public String getTableName() {
        try {
            ASTNode node = getNode().findChildByType(PlSqlElementTypes.DML_TRIGGER_CLAUSE);
            return node.findChildByType(PlSqlElementTypes.TABLE_NAME_DDL).getText();
        } catch (NullPointerException e) {
            throw new SyntaxTreeCorruptedException();
        }
    }

    @NotNull
    public Declaration[] getDeclarationList() {
        ASTNode block = getNode().findChildByType(PlSqlElementTypes.PLSQL_BLOCK);
        if(block != null){
            ASTNode decl =block.findChildByType(PlSqlElementTypes.DECLARE_LIST);
            if(decl != null){
                DeclarationList dlist = (DeclarationList) decl.getPsi();
                return dlist.getDeclList();
            }
        }
        return new Declaration[0];
    }

    public void accept(@NotNull PsiElementVisitor visitor) {
        if (visitor instanceof PlSqlElementVisitor) {
            ((PlSqlElementVisitor) visitor).visitCreateTriggerDML(this);
        } else {
            super.accept(visitor);
        }
    }

}
