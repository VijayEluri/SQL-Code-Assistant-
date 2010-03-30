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

package com.deepsky.actions;

import com.deepsky.database.DBException;
import com.deepsky.lang.common.PlSqlFile;
import com.deepsky.lang.parser.plsql.PlSqlElementTypes;
import com.deepsky.lang.plsql.NotSupportedException;
import com.deepsky.lang.plsql.psi.PlSqlElement;
import com.deepsky.lang.plsql.psi.Statement;
import com.deepsky.lang.plsql.psi.ddl.SqlDDLStatement;
import com.deepsky.lang.plsql.struct.parser.PlSqlASTParser;
import com.deepsky.lang.plsql.structure_view.PlSqlStructureViewElement;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.LangDataKeys;
import com.intellij.openapi.actionSystem.Presentation;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
import com.intellij.pom.Navigatable;
import com.intellij.psi.PsiFile;
import com.intellij.psi.tree.IElementType;

public class ExecSQLStmtStructurePopupAction extends ExecuteSQLStatementAction {

    public ExecSQLStmtStructurePopupAction(){
    }

    public void actionPerformed(AnActionEvent event) {
        PsiFile psi = event.getData(LangDataKeys.PSI_FILE);
        if (psi instanceof PlSqlFile) {
            Navigatable elem = event.getData(LangDataKeys.NAVIGATABLE);
            Project project = event.getData(LangDataKeys.PROJECT);

            if(elem instanceof PlSqlStructureViewElement){
                PlSqlStructureViewElement structure = (PlSqlStructureViewElement) elem;
                PlSqlElement plSqlElement = structure.getValue();

                String text = "";
                int _st = 0;
                int _end = 0;

                if (plSqlElement instanceof Statement) {
                    Statement stmt = (Statement) plSqlElement;
                    text = stmt.getText();
                    _st = stmt.getTextRange().getStartOffset();
                    _end = stmt.getTextRange().getEndOffset();
                } else if (plSqlElement instanceof SqlDDLStatement) {
                    SqlDDLStatement stmt = (SqlDDLStatement) plSqlElement;
                    text = stmt.getText();
                    _st = stmt.getTextRange().getStartOffset();
                    _end = stmt.getTextRange().getEndOffset();
                } else {
                    // do not know how to process
                    return;
                }

                try {
                    PlSqlASTParser parser = new PlSqlASTParser();
                    PlSqlElement[] elems = parser.parse(text);
                    if(elems.length == 1 &&
                        PlSqlElementTypes.EXECUTABLE_STATEMENTS.contains(elems[0].getNode().getElementType())){
                        IElementType itype = elems[0].getNode().getElementType();
                        executeStatement(project, text, itype, (PlSqlFile) psi, _st, _end);
                    }

                } catch (NotSupportedException e1) {
                    // todo --
                } catch (DBException e1) {
                    Messages.showErrorDialog(e1.getMessage(), "SQL query error");
                } catch (Throwable e1) {
                    // todo --
                    // error
                }
            }
        }
    }

    public void update(AnActionEvent event) {

        PsiFile psi = event.getData(LangDataKeys.PSI_FILE);
        if (psi instanceof PlSqlFile) {
            Presentation presentation = event.getPresentation();
            presentation.setVisible(true);

            Navigatable elem = event.getData(LangDataKeys.NAVIGATABLE);
            if(elem instanceof PlSqlStructureViewElement){
                PlSqlStructureViewElement structure = (PlSqlStructureViewElement) elem;
                PlSqlElement plSqlElement = structure.getValue();
                if (plSqlElement instanceof Statement) {
                    presentation.setEnabled(true);
                } else if (plSqlElement instanceof SqlDDLStatement) {
                    presentation.setEnabled(true);
                } else {
                    presentation.setEnabled(false);
                }
            }

        } else {
            Presentation presentation = event.getPresentation();
            presentation.setVisible(false);
        }
    }
}
