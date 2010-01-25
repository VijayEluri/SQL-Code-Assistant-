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
 *     3. The name of the author may not be used to endorse or promote
 *       products derived from this software without specific prior written
 *       permission from the author.
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

import com.deepsky.lang.plsql.psi.*;
import com.deepsky.lang.plsql.SyntaxTreeCorruptedException;
import com.deepsky.lang.parser.plsql.PlSqlElementTypes;
import com.deepsky.lang.validation.ValidationException;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElementVisitor;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.NotNull;

public class SelectFieldExprImpl extends PlSqlElementBase implements SelectFieldExpr {

    public SelectFieldExprImpl(ASTNode astNode) {
        super(astNode);
    }

    public String getAlias() {
        ASTNode alias = getNode().findChildByType(PlSqlElementTypes.ALIAS_NAME);
        if(alias != null){
            return alias.findChildByType(PlSqlElementTypes.ALIAS_IDENT).getText();
        } else {
            return null;
        }
    }

    public Expression getExpression() {
        ASTNode expr = getNode().findChildByType(PlSqlElementTypes.EXPR_TYPES);
        if(expr != null){
            return (Expression) expr.getPsi();
        } else {
            throw new SyntaxTreeCorruptedException();
        }
    }

    @Nullable
    public String getQuickNavigateInfo(){

//        if(findParent(Subquery.class) != null){
//            Expression expr = getExpression();
//            String _expr = expr.getText();
//            if(_expr.length() > 14){
//                _expr = _expr.substring(0, 14) + " ...";
//            }
//
//            String alias = getAlias();
//            if(alias != null ){
//                _expr += "  AS " + alias.toUpperCase();
//            }
//            try {
//                return _expr + " [Type] " + expr.getExpressionType().typeName().toLowerCase();
//            }catch (ValidationException e){
//                return _expr;
//            }
//        } else if(findParent(SelectStatement.class) != null){
            Expression expr = getExpression();
            String _expr = expr.getText();
            if(_expr.length() > 14){
                _expr = _expr.substring(0, 14) + " ...";
            }

            String alias = getAlias();
            if(alias != null ){
                _expr += "  AS " + alias.toUpperCase();
            }
            try {
                return _expr + " [Type] " + expr.getExpressionType().typeName().toLowerCase();
            }catch (ValidationException e){
                return _expr;
            }
//        }
//        return null;
    }

    public void accept(@NotNull PsiElementVisitor visitor) {
      if (visitor instanceof PlSqlElementVisitor) {
        ((PlSqlElementVisitor)visitor).visitSelectFieldExpr(this);
      }
      else {
        super.accept(visitor);
      }
    }

}
