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
import com.deepsky.lang.plsql.psi.ColumnDefinition;
import com.deepsky.lang.plsql.psi.ColumnNameDDL;
import com.deepsky.lang.plsql.psi.PlSqlElementVisitor;
import com.deepsky.lang.plsql.psi.ddl.VColumnDefinition;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.tree.TokenSet;
import org.jetbrains.annotations.NotNull;

public class ColumnNameDDLImpl extends PlSqlElementBase implements ColumnNameDDL {

    public ColumnNameDDLImpl(ASTNode astNode) {
        super(astNode);
    }


    public void accept(@NotNull PsiElementVisitor visitor) {
        if (visitor instanceof PlSqlElementVisitor) {
            ((PlSqlElementVisitor) visitor).visitColumnNameDDL(this);
        } else {
            super.accept(visitor);
        }
    }



/*
    static final private TokenSet set1 = TokenSet.create(
            PLSqlTypesAdopted.FK_SPEC, PLSqlTypesAdopted.PK_SPEC, PLSqlTypesAdopted.COLUMN_FK_SPEC,
            PLSqlTypesAdopted.CONSTRAINT, PLSqlTypesAdopted.COLUMN_DEF, PLSqlTypesAdopted.UNIQUE_CONSTRAINT,
            PLSqlTypesAdopted.COMMENT, PLSqlTypesAdopted.COLUMN_DEF
    );

    private TableDescriptor identifyTable(){

        final String[] tableName = {null};

        ASTTreeProcessor processor = new ASTTreeProcessor();
        processor.add(new ASTNodeHandler(){
            @NotNull
            public TokenSet getTokenSet() {
                return set1;
            }

            public boolean handleNode(@NotNull ASTNode node) {
                if(node.getElementType() == PLSqlTypesAdopted.FK_SPEC){
                    ASTNode[] list = node.getChildren(TokenSet.create(PLSqlTypesAdopted.COLUMN_NAME_LIST));
                    // search for the column among referenced columns
                    ASTNode[] columns = list[1].getChildren(TokenSet.create(PLSqlTypesAdopted.COLUMN_NAME_DDL));
                    for(ASTNode n: columns){
                        if(n.getPsi() == ColumnNameDDLImpl.this){
                            // found!
                            tableName[0] = node.findChildByType(PLSqlTypesAdopted.TABLE_NAME_DDL).getText();
                            break;
                        }
                    }

                    if(tableName[0] == null){
                        // search for column among own columns
                        columns = list[0].getChildren(TokenSet.create(PLSqlTypesAdopted.COLUMN_NAME_DDL));
                        for(ASTNode n: columns){
                            if(n.getPsi() == ColumnNameDDLImpl.this){
                                // found!
                                ASTNode tableDef = node.getTreeParent().getTreeParent();
                                if(tableDef != null){
                                    tableName[0] = ((TableDefinition)tableDef.getPsi()).getTableName();
                                }
                                break;
                            }
                        }
                    }


                } else if(node.getElementType() == PLSqlTypesAdopted.COLUMN_DEF){
                    ASTNode parent = node.getTreeParent();
                    if(parent.getElementType() == PLSqlTypesAdopted.TABLE_DEF){
                        tableName[0] = ((TableDefinition)parent.getPsi()).getTableName();
                    } else if(parent.getElementType() == PLSqlTypesAdopted.CREATE_TEMP_TABLE){
                        tableName[0] = ((TableDefinition)parent.getPsi()).getTableName();
                    } 

                } else if(node.getElementType() == PLSqlTypesAdopted.COLUMN_FK_SPEC){
                    tableName[0] = node.findChildByType(PLSqlTypesAdopted.TABLE_NAME_DDL).getText();
                } else if(node.getElementType() == PLSqlTypesAdopted.COMMENT){
                    tableName[0] = node.findChildByType(PLSqlTypesAdopted.TABLE_NAME_DDL).getText();
                } else {
                    // todo --
                }

                return false;
            }
        });

        processor.process(this.getNode());

        if(tableName[0] != null){
            return describeTable(tableName[0]);
        }

        return null;
    }
*/

    public ColumnDefinition getColumnDefinition() {
        return (ColumnDefinition) getParent();
    }
}

