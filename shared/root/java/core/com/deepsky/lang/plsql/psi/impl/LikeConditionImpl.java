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

import com.deepsky.lang.parser.plsql.PlSqlElementTypes;
import com.deepsky.lang.plsql.psi.Expression;
import com.deepsky.lang.plsql.psi.LikeCondition;
import com.deepsky.lang.plsql.struct.Type;
import com.deepsky.lang.plsql.struct.TypeFactory;
import com.deepsky.lang.validation.TypeValidationHelper;
import com.deepsky.lang.validation.ValidationException;
import com.intellij.lang.ASTNode;
import org.jetbrains.annotations.NotNull;

public class LikeConditionImpl extends ConditionBase implements LikeCondition {

    public LikeConditionImpl(ASTNode astNode) {
        super(astNode);
    }

    @NotNull
    public Type getExpressionType() {
        ASTNode[] nodes = getNode().getChildren(PlSqlElementTypes.EXPR_TYPES);
        if (nodes == null || (!(nodes.length == 2 || nodes.length == 3)) ) {
            // todo - handle of error!!!
            int hh = 9;
        } else {
            Type l = ((Expression) nodes[0].getPsi()).getExpressionType();
            Type r = ((Expression) nodes[1].getPsi()).getExpressionType();

            if( !TypeValidationHelper.canBeAssigned(Type.CHAR, l.typeId())){
                throw new ValidationException("Invalid expression type", nodes[0]);
            }

            if(!TypeValidationHelper.canBeAssigned(Type.CHAR, r.typeId())){
                throw new ValidationException("Invalid expression type", nodes[1]);
            }
            return TypeFactory.createTypeById(Type.BOOLEAN);
        }

        throw new ValidationException("Cannot resolve type", this);
    }

    public Expression getLeftExpr() {
        ASTNode[] nodes = getNode().getChildren(PlSqlElementTypes.EXPR_TYPES);
        return (Expression) nodes[0].getPsi();
    }

    public Expression getRightExpr() {
        ASTNode[] nodes = getNode().getChildren(PlSqlElementTypes.EXPR_TYPES);
        return (Expression) nodes[1].getPsi();
    }

    public boolean isNotLike() {
        // todo --
        return false;
    }
}
