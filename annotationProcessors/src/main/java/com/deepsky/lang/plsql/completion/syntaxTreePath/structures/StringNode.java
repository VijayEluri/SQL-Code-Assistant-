/*
 * Copyright (c) 2009,2014 Serhiy Kulyk
 * All rights reserved.
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *      1. Redistributions of source code must retain the above copyright
 *        notice, this list of conditions and the following disclaimer.
 *      2. Redistributions in binary form must reproduce the above copyright
 *        notice, this list of conditions and the following disclaimer in the
 *        documentation and/or other materials provided with the distribution.
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

package com.deepsky.lang.plsql.completion.syntaxTreePath.structures;

public class StringNode extends TNode {

    private boolean isDollar = true;
    private int pos = -1;
    private int metaInfoRef;
    private boolean negative;

    public StringNode(boolean isDollar, int pos, String name, boolean negative) {
        super(name);
        this.isDollar = isDollar;
        this.pos = pos;
        this.negative = negative;
    }

    public StringNode(String name) {
        super(name);
    }

    @Override
    public void accept(TNodeVisitor visitor) {
        visitor.visitStringNode(this);
    }

    public boolean isDollar() {
        return isDollar;
    }

    public int getPos() {
        return pos;
    }

    public void setMetaInfoRef(int metaInfoRef) {
        this.metaInfoRef = metaInfoRef;
    }

    public int getMetaInfoRef() {
        return metaInfoRef;
    }

    public boolean isNegative() {
        return negative;
    }

    public boolean equals(Object obj) {
        if(obj instanceof StringNode && ((StringNode)obj).getChildren().size() == getChildren().size()){
            StringNode ext = (StringNode) obj;
            if(ext.getName().equals(getName())){
                if((ext.isDollar() && isDollar) || (!ext.isDollar() && !isDollar)){
                    if((ext.isNegative() && negative) || (!ext.isNegative() && !negative)){
                        for(int i = 0; i<getChildren().size(); i++){
                            if(!getChildren().get(i).equals(ext.getChildren().get(i)))
                                return false;
                        }
                        return true;
                    }
                }
            }
            return false;
        } else {
            return super.equals(obj);
        }
    }

}
