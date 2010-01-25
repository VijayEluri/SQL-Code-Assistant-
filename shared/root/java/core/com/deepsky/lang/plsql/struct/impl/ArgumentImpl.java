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

package com.deepsky.lang.plsql.struct.impl;

import com.deepsky.lang.plsql.psi.Argument;
import com.deepsky.lang.plsql.psi.Expression;
import com.deepsky.lang.plsql.psi.types.TypeSpec;
import com.deepsky.lang.plsql.struct.Type;
import org.jetbrains.annotations.NotNull;

public class ArgumentImpl extends PsiElementDumb implements Argument {

    private String name;
    private Type type;
    private Expression expr;

    boolean in;
    boolean out;
    boolean nocopy;
    boolean assigned;

    public String getArgumentName() {
        return name;
    }

    @NotNull
    public Type getType() {
        return type;
    }

    public TypeSpec getTypeSpec() {
        // todo --
        return null;
    }

    public Expression getDefaultExpr() {
        return expr;
    }

    public String getPresentableForm() {
        return null;
    }

    public boolean isIn() {
        return in;
    }

    public boolean isOut() {
        return out;
    }

    public boolean isNocopy() {
        return nocopy;
    }

    public boolean isAssigned() {
        return assigned;
    }

    public void setAssigned(boolean assigned) {
        this.assigned = assigned;
    }

    public void setIn(boolean in){
        this.in = in;
    }

    public void setOut(boolean out){
        this.out = out;
    }

    public void setNocopy(boolean nocopy){
        this.nocopy = nocopy;
    }

    public void setDefaultExpr(Expression expr){
        this.expr = expr;
    }

    public void setArgumentName(String name) {
        this.name = name;
    }

    public void setType(Type type) {
        this.type = type;
    }
}
