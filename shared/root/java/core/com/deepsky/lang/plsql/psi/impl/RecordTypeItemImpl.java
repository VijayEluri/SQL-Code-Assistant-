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

package com.deepsky.lang.plsql.psi.impl;

import com.deepsky.lang.parser.plsql.PLSqlTypesAdopted;
import com.deepsky.lang.parser.plsql.PlSqlElementTypes;
import com.deepsky.lang.plsql.SyntaxTreeCorruptedException;
import com.deepsky.lang.plsql.psi.*;
import com.deepsky.lang.plsql.psi.names.RecordItemName;
import com.deepsky.lang.plsql.psi.types.TypeSpec;
import com.deepsky.lang.plsql.psi.utils.ASTNodeIterator;
import com.deepsky.lang.plsql.resolver.ContextPath;
import com.deepsky.lang.plsql.resolver.utils.ContextPathUtil;
import com.deepsky.lang.plsql.struct.Type;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import org.jetbrains.annotations.NotNull;

public class RecordTypeItemImpl extends PlSqlElementBase implements RecordTypeItem {

    public RecordTypeItemImpl(ASTNode astNode) {
        super(astNode);
    }

    public String getRecordItemName() {
        ASTNode name = getNode().findChildByType(PLSqlTypesAdopted.RECORD_ITEM_NAME);
        if (name != null) {
            return name.getText();
        } else {
            throw new SyntaxTreeCorruptedException();
        }
    }

    public RecordItemName getPsiRecordItemName() {
        ASTNode name = getNode().findChildByType(PLSqlTypesAdopted.RECORD_ITEM_NAME);
        if (name != null) {
            return (RecordItemName) name.getPsi();
        } else {
            throw new SyntaxTreeCorruptedException();
        }
    }

    public String getQuickNavigateInfo() {
        StringBuilder out = new StringBuilder();
        ASTNode child = getNode().getFirstChildNode();
        ASTNodeIterator iterator = new ASTNodeIterator(child);
        while (iterator.hasNext() && !iterator.peek().getText().equals(";")) {
            if (out.length() > 0) {
                out.append(" ");
            }
            out.append(iterator.next().getText());
        }

        return out.toString();
    }

    public Type getType() {
        ASTNode type = getNode().findChildByType(PlSqlElementTypes.TYPES);
        if (type != null) {
            return ((TypeSpec) type.getPsi()).getType();
        } else {
            throw new SyntaxTreeCorruptedException();
        }
    }

    public Expression getDefaultExpr() {
        return null;
    }

    public TypeDeclaration getRecordType() {
        return (TypeDeclaration) getParent();
    }

    public void accept(@NotNull PsiElementVisitor visitor) {
        if (visitor instanceof PlSqlElementVisitor) {
            ((PlSqlElementVisitor) visitor).visitRecordTypeItem(this);
        } else {
            super.accept(visitor);
        }
    }

}
