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

package com.deepsky.lang.plsql.struct.parser;

import antlr.RecognitionException;
import antlr.TokenStreamException;
import antlr.collections.AST;
import com.deepsky.generated.plsql.PLSqlTokenTypes;
import com.deepsky.lang.plsql.ReferencedName;
import com.deepsky.lang.plsql.parser.ParserException;
import com.deepsky.lang.plsql.psi.*;
import com.deepsky.lang.plsql.psi.ddl.CreateTrigger;
import com.deepsky.lang.plsql.psi.ddl.TableDefinition;
import com.deepsky.lang.plsql.psi.ddl.VColumnDefinition;
import com.deepsky.lang.plsql.struct.Type;
import com.deepsky.lang.plsql.struct.impl.*;
import com.deepsky.lang.plsql.struct.types.*;

import java.io.Reader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class PlSqlASTParser0 {
    //static Logger log = Logger.getLogger(PlSqlASTParser0.class);

    protected ContextTracker ctxTracker = new ContextTrackerImpl();

    protected PLSqlFilteredLexer lexer = null;
    private String packageName;

    boolean ignoreSyntaxErrors = false;
    boolean syntaxErrorToStdout = true;
    String[] errors = new String[0];

    public PlSqlASTParser0() {
        this.lexer = new PLSqlFilteredLexerImpl();
    }

    public PlSqlASTParser0(PLSqlFilteredLexer lexer) {
        this.lexer = lexer;
    }

    public void ignoreSyntaxErrors(boolean ignore) {
        ignoreSyntaxErrors = ignore;
    }

    public void syntaxErrorToStdout(boolean out) {
        syntaxErrorToStdout = out;
    }

    public String[] getErrors() {
        return errors;
    }

    public PlSqlElement[] parseStream(Reader in) {

        ctxTracker.__init__();
        packageName = null;
        lexer.reload(in);

        PLSqlParserEx parser = new PLSqlParserEx(lexer);
        parser.setASTNodeClass(ExtAST.class.getName());
        parser.outputErrors(syntaxErrorToStdout);
//        long ms = System.currentTimeMillis();

        try {
            parser.start_rule();
        } catch (RecognitionException e) {
            throw new ParserException("Could not parse sql script", e);
        } catch (TokenStreamException e) {
            throw new ParserException("Could not parse sql script", e);
        }
//        ms = System.currentTimeMillis() - ms;
//        log.info("Time spend for parsing, ms: " + ms);

        if (!ignoreSyntaxErrors && parser.getErrors().size() > 0) {
            throw new SyntaxErrorException("Syntax is not correct.", parser.getErrors());
        }

        if (parser.getErrors().size() > 0) {
            errors = parser.getErrors().toArray(new String[parser.getErrors().size()]);
        }

        AST ast = parser.getAST();
        List<PlSqlElement> out = parserTopAST(ast);

        return out.toArray(new PlSqlElement[out.size()]);
    }


    protected List<PlSqlElement> parserTopAST(AST ast) {
        List<PlSqlElement> out = new ArrayList<PlSqlElement>();
        while (ast != null) {
            switch (ast.getType()) {
                case PLSqlTokenTypes.FUNCTION_BODY: {
                    out.add(parseFunctionBody(ast, false));
                    break;
                }
                case PLSqlTokenTypes.CREATE_FUNCTION: {
                    out.add(parseFunctionBody(ast.getFirstChild(), true));
                    break;
                }
                case PLSqlTokenTypes.PROCEDURE_BODY: {
                    out.add(parseProcedureBody(ast, false));
                    break;
                }
                case PLSqlTokenTypes.CREATE_PROCEDURE: {
                    out.add(parseProcedureBody(ast.getFirstChild(), true));
                    break;
                }
//                case PLSqlTokenTypes.INSERT_COMMAND: {
//                    out.add(parseInsertCommand(ast));
//                    break;
//                }
                case PLSqlTokenTypes.PACKAGE_BODY: {
                    out.add(parseCreatePackageBody(ast));
                    break;
                }
                case PLSqlTokenTypes.PACKAGE_SPEC: {
                    out.add(parsePackageSpec(ast));
                    break;
                }
                case PLSqlTokenTypes.SQLPLUS_COMMAND: {
                    parsePlSqlPlusCommand(ast, out);
                    break;
                }
                case PLSqlTokenTypes.TABLE_DEF: {
                    out.add(parseTableDefintion(ast));
                    break;
                }
                case PLSqlTokenTypes.OBJECT_TYPE_DEF: {
                    out.add(parseObjectType(ast));
                    break;
                }
                case PLSqlTokenTypes.TABLE_COLLECTION: {
                    out.add(parseTableCollection(ast));
                    break;
                }
                case PLSqlTokenTypes.VARRAY_COLLECTION: {
                    out.add(parseVarrayCollection(ast));
                    break;
                }
//                case PLSqlTokenTypes.CREATE_PROCEDURE:
//                    throw new ParserException("Parsed type not supported: " + ast.getText());
//                case PLSqlTokenTypes.CREATE_FUNCTION: {
//                    out.add(parseFunctionBody(ast.getFirstChild(), true));
//                    break;
//                }
                case PLSqlTokenTypes.SELECT_EXPRESSION: { //COMMAND: {
                    SelectStatement select = parseSelectExpression(ast);
                    out.add(select);
                    break;
                }
                case PLSqlTokenTypes.SELECT_EXPRESSION_UNION: {
                    SelectStatement select = parseSelectExpressionUnion(ast);
                    out.add(select);
                    break;
                }
                case PLSqlTokenTypes.CREATE_TEMP_TABLE: {
                    out.add(parseCreateTempTable(ast));
                    break;
                }
                case PLSqlTokenTypes.COMMENT: {
                    out.add(parseComment(ast));
                    break;
                }
                case PLSqlTokenTypes.CREATE_INDEX: {
                    out.add(parseCreateIndex(ast));
                    break;
                }
                case PLSqlTokenTypes.CREATE_VIEW: {
                    out.add(parseCreateView(ast));
                    break;
                }
                case PLSqlTokenTypes.ALTER_TABLE: {
                    out.add(parseAlterTable(ast));
                    break;
                }
                case PLSqlTokenTypes.DELETE_COMMAND: {
                    out.add(parseDeleteStatement(ast));
                    break;
                }
                case PLSqlTokenTypes.UPDATE_COMMAND: {
                    out.add(parseUpdateStatement(ast));
                    break;
                }
                case PLSqlTokenTypes.INSERT_COMMAND: {
                    out.add(parseInsertStatement(ast));
                    break;
                }
                case PLSqlTokenTypes.MERGE_COMMAND: {
                    out.add(parseMergeStatement(ast));
                    break;
                }
                case PLSqlTokenTypes.CREATE_TRIGGER: {
                    out.add(parseCreateTriggerStatement(ast));
                    break;
                }
                case PLSqlTokenTypes.SQLPLUS_PROMPT: {
                    // ignore
                    break;
                }
                case PLSqlTokenTypes.ALTER_TRIGGER: {
                    // todo -- implement me
                    break;
                }
                case PLSqlTokenTypes.COMMIT_STATEMENT:
                    // todo -- implement me
                    break;
                case PLSqlTokenTypes.DROP_SYNONYM:
                case PLSqlTokenTypes.DROP_OPERATOR:
                case PLSqlTokenTypes.DROP_PACKAGE:
                case PLSqlTokenTypes.DROP_TYPE:
                case PLSqlTokenTypes.DROP_ROLE:
                case PLSqlTokenTypes.DROP_USER:
                case PLSqlTokenTypes.DROP_LIBRARY:
                case PLSqlTokenTypes.DROP_DB_LINK:
                case PLSqlTokenTypes.DROP_DIRECTORY:

                case PLSqlTokenTypes.CREATE_DIRECTORY:
                case PLSqlTokenTypes.CREATE_DB_LINK:
                    // todo -- implement me
                    break;
                case PLSqlTokenTypes.CREATE_SEQUENCE:
                    out.add(parseCreateSequence(ast));
                    break;
                default:
                    throw new ParserException("Parsed type not supported: " + ast.getText());
            }
            ast = ast.getNextSibling();
        }

        return out;
    }


    protected PlSqlElement parseCreateSequence(AST ast) {

        String name = null;
        AST ast0 = ast.getFirstChild();
        while(ast0 != null){
            if(ast0.getType() == PLSqlTokenTypes.OBJECT_NAME){
                name = ast0.getFirstChild().getText();
                break;
            }
            ast0 = ast0.getNextSibling();
        }
        return new CreateSequenceImpl(name);
    }

    protected void parsePlSqlPlusCommand(AST ast, List<PlSqlElement> out) {
        AST ast1 = ast.getFirstChild();

        while(ast1 != null){
            if(ast1.getType() == PLSqlTokenTypes.SQLPLUS_EXIT){
                SqlPlusCommandImpl sqlplus = new SqlPlusCommandImpl(ast1.getType());
                out.add(sqlplus);
            } else if(ast1.getType() == PLSqlTokenTypes.SQLPLUS_PROMPT){
                SqlPlusCommandImpl sqlplus = new SqlPlusCommandImpl(ast1.getType());
                out.add(sqlplus);
            } else if(ast1.getType() == PLSqlTokenTypes.SQLPLUS_QUIT){
                SqlPlusCommandImpl sqlplus = new SqlPlusCommandImpl(ast1.getType());
                out.add(sqlplus);
            } else if(ast1.getType() == PLSqlTokenTypes.SQLPLUS_RUNSCRIPT){
                SqlPlusCommandImpl sqlplus = new SqlPlusCommandImpl(ast1.getType());
                out.add(sqlplus);
            } else if(ast1.getType() == PLSqlTokenTypes.SQLPLUS_SET){
                SqlPlusCommandImpl sqlplus = new SqlPlusCommandImpl(ast1.getType());
                out.add(sqlplus);
            } else if(ast1.getType() == PLSqlTokenTypes.SQLPLUS_SHOW){
                SqlPlusCommandImpl sqlplus = new SqlPlusCommandImpl(ast1.getType());
                out.add(sqlplus);

            } else if(ast1.getType() == PLSqlTokenTypes.PLSQL_BLOCK){
                SqlPlusCommandImpl sqlplus = new SqlPlusCommandImpl(ast1.getType());
                sqlplus.setBlock(parsePlSqlBlock(ast1));
                out.add(sqlplus);
            } else {
                out.add(new SqlPlusCommandImpl(-1));
            }
            ast1 = ast1.getNextSibling();
        }
    }

    protected PlSqlElement parseMergeStatement(AST ast) {
        MergeStatementImpl merge = new MergeStatementImpl();

        AST ast1 = ast.getFirstChild();
        if (ast1.getType() == PLSqlTokenTypes.TABLE_ALIAS) {
            PlainTable tab = parseTableAlias(ast1);
            merge.setIntoTable(tab);
            return merge;
        } else {
            // insert into subquery, not supported at the moment
            throw new ParserException("Cannot parse: " + ast.getText());
        }
    }


    private PlSqlElement parseCreateTriggerStatement(AST ast) {
        AST ast0 = ast.getFirstChild();
        String trigger_name = ast0.getFirstChild().getText();

        ctxTracker.__openContext__(ContextPath.CREATE_TRIGGER, trigger_name);

        CreateTrigger trigger = null;
        AST ast1 = ast0.getNextSibling();
        while (ast1 != null) {
            if (ast1.getType() == PLSqlTokenTypes.DB_EVNT_TRIGGER_CLAUSE) {
                String before_after = ast0.getNextSibling().getText();
                throw new ParserException("Could not parser DB Level Trigger");
            } else if (ast1.getType() == PLSqlTokenTypes.DDL_TRIGGER_CLAUSE) {
                String before_after = ast0.getNextSibling().getText();
                throw new ParserException("Could not parser DDL Level Trigger");
            } else if (ast1.getType() == PLSqlTokenTypes.DML_TRIGGER_CLAUSE) {
                String before_after = ast0.getNextSibling().getText();
                String table_name = "";
                AST ast2 = ast1.getFirstChild();
                while (ast2 != null) {
                    if (ast2.getType() == PLSqlTokenTypes.TABLE_NAME_DDL) {
                        table_name = ast2.getFirstChild().getText();
                        break;
                    }
                    ast2 = ast2.getNextSibling();
                }

                trigger = new CreateTriggerDMLImpl(trigger_name, table_name);
            } else if (ast1.getType() == PLSqlTokenTypes.INSTEADOF_TRIGGER) {
                throw new ParserException("Could not parser INSTEAD OF Trigger");
            } else if (ast1.getType() == PLSqlTokenTypes.PLSQL_BLOCK) {
                parsePlSqlBlock(ast1);
//                AST ast2 = ast.getFirstChild();
//                while(ast2 != null){
//                    if(ast2.getType() == PLSqlTokenTypes.DECLARE_LIST){
//                        parseDeclareList(ast2);
//                        // todo --
//                    } else if(ast2.getType() == PLSqlTokenTypes.PLSQL_BLOCK){
//                        parsePlSqlBlock(ast2);
//                        // todo --
//                    }
//                    ast2 = ast2.getNextSibling();
//                }
                ctxTracker.__closeContext__();
                return trigger;
            }
            ast1 = ast1.getNextSibling();
        }

        throw new ParserException("Could not parser Trigger definition");
    }

    private InsertStatement parseInsertStatement(AST ast) {
        AST ast1 = ast.getFirstChild();
        if (ast1.getType() == PLSqlTokenTypes.TABLE_ALIAS) {
            PlainTable tab = parseTableAlias(ast1);
            return new InsertStatementImpl(tab);
        } else {
            // insert into subquery, not supported at the moment
            throw new ParserException("INSERT into SUBQUERY not supported: " + ast.getText());
        }
    }

    private PlSqlElement parseUpdateStatement(AST ast) {
        AST ast1 = ast.getFirstChild();
        PlainTable tab = parseTableAlias(ast1);

        return new UpdateStatementImpl(tab);
    }

    private PlSqlElement parseDeleteStatement(AST ast) {
        AST ast1 = ast.getFirstChild();
        PlainTable tab = parseTableAlias(ast1);

        return new DeleteStatementImpl(tab);
    }

    private PlSqlElement parseCreateView(AST ast) {
        AST ast1 = ast.getFirstChild();
        String tableName = "";
        SelectStatement select = null;
//        ColumnSpecList spec = null;
        CreateViewImpl view = new CreateViewImpl();

        List<VColumnDefinition> columns = new ArrayList<VColumnDefinition>();
        while (ast1 != null) {
            if (ast1.getType() == PLSqlTokenTypes.VIEW_NAME_DDL) {
                tableName = ast1.getFirstChild().getText();
            } else if (ast1.getType() == PLSqlTokenTypes.SELECT_EXPRESSION) {
                select = parseSelectExpression(ast1);
            } else if (ast1.getType() == PLSqlTokenTypes.V_COLUMN_DEF) {
                AST ast2 = ast1.getFirstChild();
                columns.add(new VColumnDefinitionImpl(view, ast2.getText()));
//                spec = parseColumnSpecList(ast1);
            }
            ast1 = ast1.getNextSibling();
        }

        view.setVcolumns(columns.toArray(new VColumnDefinition[columns.size()]));
        view.setName(tableName);
        view.setSelect(select);
        return view; //new CreateViewImpl(tableName, spec, select);
    }

    private static ColumnSpecList parseColumnSpecList(AST ast) {
        AST ast1 = ast.getFirstChild();
        List<ColumnSpec> columns = new ArrayList<ColumnSpec>();
        while (ast1 != null) {
            if (ast1.getType() == PLSqlTokenTypes.COLUMN_SPEC) {
                ColumnSpec column = parseColumnSpec(ast1);
                columns.add(column);
            }
            ast1 = ast1.getNextSibling();
        }

        return new ColumnSpecListImpl(columns.toArray(new ColumnSpec[0]));
    }

    private PlSqlElement parseAlterTable(AST ast) {
        // todo
        return new AlterTableImpl();
    }

    private PlSqlElement parseCreateIndex(AST ast) {
        // todo
        return new IndexImpl();
    }

    private PlSqlElement parseComment(AST ast) {
        // todo
        return new CommentImpl();
    }

    private PlSqlElement parseCreateTempTable(AST ast) {
        AST ast0 = ast.getFirstChild();
        boolean isGlobal = false;
        if ("GLOBAL".equalsIgnoreCase(ast0.getText())) {
            // set GLOBAL
            ast0 = ast0.getNextSibling();
            isGlobal = true;
        }
        String tableName = ast0.getFirstChild().getText();
        TempTableDefinitionImpl tabdef = new TempTableDefinitionImpl(tableName);
        tabdef.setGlobal(isGlobal);

        List<ColumnDefinition> columns = new ArrayList<ColumnDefinition>();
        ast0 = ast0.getNextSibling();
        while (ast0 != null) {
            if (ast0.getType() == PLSqlTokenTypes.COLUMN_DEF) {
                AST ast1 = ast0.getFirstChild();
                String columnName = ast1.getFirstChild().getText();

                ast1 = ast1.getNextSibling();
                // parse type ...
                Type t = parseDatatype(ast1);

                ColumnDefinitionImpl cdef = new ColumnDefinitionImpl(columnName, t, tabdef);
                AST ast2 = ast1.getNextSibling();
                while (ast2 != null) {
                    if (ast2.getType() == PLSqlTokenTypes.COLUMN_PK_SPEC) {
                        cdef.setPrimaryKey(true);
                    } else if (ast2.getType() == PLSqlTokenTypes.COLUMN_FK_SPEC) {
                        AST ast3 = ast2.getFirstChild();
                        String _tableName = parseTableName(ast3);
                        ast3 = ast3.getNextSibling();
                        String _columnName = parseColumnName(ast3);
                        cdef.setForeignKeySpec(_tableName, _columnName);
                    } else if (ast2.getType() == PLSqlTokenTypes.NOT_NULL_STMT) {
                        if ("not".equalsIgnoreCase(ast2.getFirstChild().getText())) {
                            cdef.setNotNull(true);
                        }
                    }
                    ast2 = ast2.getNextSibling();
                }

                columns.add(cdef);
            } else if (ast0.getType() == PLSqlTokenTypes.CONSTRAINT) {
                AST ast2 = ast0.getFirstChild();
                String constraintName = ast2.getFirstChild().getText();
                ast2 = ast2.getNextSibling();
                if (ast2.getType() == PLSqlTokenTypes.FK_SPEC) {
                    AST columnList = ast2.getFirstChild();
                    String[] privateColumns = parseColumnList(columnList);
                    AST refTable = columnList.getNextSibling();
                    AST refColumnList = refTable.getNextSibling();
                    String[] refColumns = parseColumnList(refColumnList);

                    ForeignKeyConstraint constr = new ForeignKeyConstraintImpl(
                            constraintName,
                            refTable.getFirstChild().getText(),
                            privateColumns,
                            refColumns
                    );
                    tabdef.addConstraint(constr);

                } else if (ast2.getType() == PLSqlTokenTypes.PK_SPEC) {
                    AST columnList = ast2.getFirstChild();
                    String[] privateColumns = parseColumnList(columnList);
                    PrimaryKeyConstraint constr = new PrimaryKeyConstraintImpl(
                            constraintName, privateColumns
                    );
                    tabdef.addConstraint(constr);
                }
            }
            ast0 = ast0.getNextSibling();
        }

        tabdef.setColumnDefs(columns.toArray(new ColumnDefinition[0]));
        return tabdef;
    }

    private SelectStatement parseSelectExpressionUnion(AST ast) {
        AST ast1 = ast.getFirstChild();
        SelectStatementUnionImpl selectUnion = new SelectStatementUnionImpl();
        while (ast1 != null) {
            if (ast1.getType() == PLSqlTokenTypes.SELECT_EXPRESSION) {
                SelectStatement select = parseSelectExpression(ast1);

                selectUnion.addSelect(select);
            }
            ast1 = ast1.getNextSibling();
        }

        return selectUnion;
    }


    private SelectStatement parseSelectExpression(AST ast) {
        // select_command:
        //  ( select_expression | subquery )

        AST ast1 = ast.getFirstChild();
        if (ast1.getType() == PLSqlTokenTypes.SUBQUERY) {
            SelectStatement select;
            select = new SelectSubqueryImpl(
                    parseSelectExpression(ast1.getFirstChild())
            );
            return select;
        } else {
            ctxTracker.__openContext__(ContextPath.SELECT_EXPR);

            WhereCondition where = null;
            SelectExpressionImpl select = new SelectExpressionImpl();
            List<SelectFieldCommon> fields = new ArrayList<SelectFieldCommon>();

            while (ast1 != null) {
                if (ast1.getType() == PLSqlTokenTypes.EXPR_COLUMN) {
                    // expr_column:
                    //    plsql_expression ( alias )?
                    AST ast2 = ast1.getFirstChild();

                    Expression expr = parseExpression(ast2);
                    String alias = null;
                    ast2 = ast2.getNextSibling();
                    if (ast2 != null && ast2.getType() == PLSqlTokenTypes.ALIAS_NAME) {

                        alias = ast2.getFirstChild().getFirstChild().getText();
                    }

                    fields.add(new SelectFieldExprImpl(expr, alias));
                } else if (ast1.getType() == PLSqlTokenTypes.ASTERISK_COLUMN) {
                    // parse ASTERISK_COLUMN
                    fields.add(new SelectFieldAsteriskImpl(null));
                } else if (ast1.getType() == PLSqlTokenTypes.IDENT_ASTERISK_COLUMN) {
                    // parse IDENT_ASTERISK_COLUMN
                    AST ast2 = ast1.getFirstChild();
                    fields.add(new SelectFieldAsteriskImpl(ast2.getFirstChild().getText()));
                } else if (ast1.getType() == PLSqlTokenTypes.TABLE_REFERENCE_LIST_FROM) {
                    // parse TABLE_REFERENCE_LIST_FROM
                    select.setFromClause(parseFromClause(ast1));
                } else if (ast1.getType() == PLSqlTokenTypes.WHERE_CONDITION) {
                    // parse WHERE_CONDITION
                    AST ast2 = ast1.getFirstChild();
                    where = new WhereConditionImpl(parseCondition(ast2));
                    select.setWhereCondition(where);
                } else if ("union".equalsIgnoreCase(ast1.getText())) {
                    ast1 = ast1.getNextSibling();
                    if ("all".equalsIgnoreCase(ast1.getText())) {
                        // scroll ALL
                        ast1 = ast1.getNextSibling();
                    }
                    SelectStatement select1 = parseSelectExpression(ast1);
                    select.setFollowingSelectStatement(select1, SelectStatement.UNION_TYPE);
                } else if ("intersect".equalsIgnoreCase(ast1.getText())) {
                    ast1 = ast1.getNextSibling();
                    SelectStatement select1 = parseSelectExpression(ast1);
                    select.setFollowingSelectStatement(select1, SelectStatement.INTERSECT_TYPE);
                } else if ("minus".equalsIgnoreCase(ast1.getText())) {
                    ast1 = ast1.getNextSibling();
                    SelectStatement select1 = parseSelectExpression(ast1);
                    select.setFollowingSelectStatement(select1, SelectStatement.MINUS_TYPE);
                }

                ast1 = ast1.getNextSibling();
            }

            select.setSelectFieldList(fields.toArray(new SelectFieldCommon[0]));
            ctxTracker.__closeContext__();
            return select;
        }
    }


    private FromClause parseFromClause(AST ast) {
        AST ast1 = ast.getFirstChild();
        List<GenericTable> tabs = new ArrayList<GenericTable>();
        while (ast1 != null) {
            if (ast1.getType() == PLSqlTokenTypes.TABLE_ALIAS) {
                tabs.add(parseTableAlias(ast1));
            } else if (ast1.getType() == PLSqlTokenTypes.ANSI_JOIN_TAB_SPEC) {
                return parseAnsiJoinTableSpec(ast1, tabs.get(0));
            } else if (ast1.getType() == PLSqlTokenTypes.VIRTUAL_TABLE) {
                tabs.add(parseVirtualTable(ast1));
            } else if (ast1.getType() == PLSqlTokenTypes.FROM_SUBQUERY) {
                // subquery ( alias )?
                tabs.add(parseFromSubquery(ast1));
            } else {
                throw new ParserException("Not supported at the moment: " + ast1.getText());
            }

            ast1 = ast1.getNextSibling();
        }

        FromClauseImpl from = new FromClauseImpl();
        from.setTableList(tabs.toArray(new GenericTable[0]));
        return from;
    }

    private GenericTable parseVirtualTable(AST ast) {
        AST ast1 = ast.getFirstChild();

        Expression expr = parseExpression(ast1);
        ast1 = ast1.getNextSibling();
        if (ast1 != null) {
            String alias = parseAlias(ast1);
            return new VirtualTableImpl(expr, alias);
        } else {
            return new VirtualTableImpl(expr, null);

        }
    }

    private GenericTable parseFromSubquery(AST ast) {
        // subquery ( alias )?
        AST ast2 = ast.getFirstChild();
        SelectStatement select = parseSelectExpression(ast2.getFirstChild());
        String alias = null;
        if (ast2.getNextSibling() != null) {
            alias = parseAlias(ast2);
        }

        return new SubqueryImpl(select, alias);
    }

    private AnsiFromClause parseAnsiJoinTableSpec(AST ast, GenericTable lead) {
//                ansi_spec :
//                      ( "inner"
//                        | (( "left" | "right") ("outer")? )
//                      )? "join"
//                      selected_table
//                      "on"
//                      condition
//                      { #ansi_spec = #([ANSI_JOIN_TAB_SPEC, "ANSI_JOIN_TAB_SPEC"], #ansi_spec); }
//                      ;

        AnsiFromClauseImpl ansi = new AnsiFromClauseImpl(lead);
        while (ast != null) {
            assert ast.getType() == PLSqlTokenTypes.ANSI_JOIN_TAB_SPEC;

            AST ast1 = ast.getFirstChild();
            List<String> joinSpec = new ArrayList<String>();
            GenericTable table = null;
            Expression cond = null;
            while (ast1 != null) {
                if ("inner".equalsIgnoreCase(ast1.getText())) {
                    joinSpec.add(ast1.getText());
                } else if ("left".equalsIgnoreCase(ast1.getText())) {
                    joinSpec.add(ast1.getText());
                } else if ("right".equalsIgnoreCase(ast1.getText())) {
                    joinSpec.add(ast1.getText());
                } else if ("outer".equalsIgnoreCase(ast1.getText())) {
                    joinSpec.add(ast1.getText());
                } else if ("join".equalsIgnoreCase(ast1.getText())) {
                    joinSpec.add(ast1.getText());
                } else if ("on".equalsIgnoreCase(ast1.getText())) {
                    ast1 = ast1.getNextSibling();
                    cond = parseCondition(ast1);
                } else if (ast1.getType() == PLSqlTokenTypes.FROM_SUBQUERY) {
                    table = parseFromSubquery(ast1);
                } else if (ast1.getType() == PLSqlTokenTypes.TABLE_ALIAS) {
                    table = parseTableAlias(ast1);
                }

                ast1 = ast1.getNextSibling();
            }

            ansi.addJoinPart(joinSpec, table, cond);
            ast = ast.getNextSibling();
        }

        return ansi;
    }

    private static String parseAlias(AST ast2) {
        if (ast2.getType() == PLSqlTokenTypes.ALIAS_NAME) {
            return ast2.getFirstChild().getFirstChild().getText();
        }

        return null;
    }


    protected PlainTable parseTableAlias(AST ast) {
        // table_spec ( alias )?
        AST ast1 = ast.getFirstChild();
        String tableName = null;
        String schema = null;
        String alias = null;

        while (ast1 != null) {
            if (ast1.getType() == PLSqlTokenTypes.TABLE_NAME) {
                tableName = ast1.getFirstChild().getText();
            } else if (ast1.getType() == PLSqlTokenTypes.TABLE_NAME_WITH_LINK) {
                String full = ast1.getFirstChild().getText();
                tableName = full.substring(0, full.indexOf('@'));
            } else if (ast1.getType() == PLSqlTokenTypes.ALIAS_NAME) {
                AST alias_ident = ast1.getFirstChild();
                alias = alias_ident.getFirstChild().getText();
            } else if (ast1.getType() == PLSqlTokenTypes.SCHEMA_NAME) {
                schema = ast1.getFirstChild().getText();
            }
            ast1 = ast1.getNextSibling();
        }
        return new PlainTableImpl(tableName, schema, alias);
    }

    protected Expression parseCondition(AST ast) {
        return new BooleanLiteralImpl("TRUE");
/*  todo -- subject to derive
        Condition c = null;

        if (ast.getType() == PLSqlTokenTypes.RELATION_CONDITION) {
            AST ast2 = ast.getFirstChild();
            // exprssion left side
            Expression l = parseExpression(ast2);

            ast2 = ast2.getNextSibling();
            // relation op
            String op = ast2.getFirstChild().getText();
            // exprssion right side
            ast2 = ast2.getNextSibling();
            Expression r = parseExpression(ast2);

            c = new RelationConditionImpl(l, r, op);
            return c;
        } else if (ast.getType() == PLSqlTokenTypes.LIKE_CONDITION) {
            AST ast1 = ast.getFirstChild();

            Expression l = parseExpression(ast1);
            ast1 = ast1.getNextSibling();

            boolean isNotLike = false;
            // scroll "(not)? like"
            if (ast1.getText().equalsIgnoreCase("not")) {
                ast1 = ast1.getNextSibling();
                ast1 = ast1.getNextSibling();
                isNotLike = true;
            } else if (ast1.getText().equalsIgnoreCase("like")) {
                ast1 = ast1.getNextSibling();
            }

            Expression r = parseExpression(ast1);
            return new LikeConditionImpl(l, r, isNotLike);
        } else if (ast.getType() == PLSqlTokenTypes.PARENTHESIZED_EXPR) {
            return new ParenthesizedExprImpl(parseCondition(ast.getFirstChild()));
        } else if (ast.getType() == PLSqlTokenTypes.IN_CONDITION) {
            return new InConditionImpl();
        } else if (ast.getType() == PLSqlTokenTypes.ISNULL_CONDITION) {

            AST ast1 = ast.getFirstChild();
            Expression expr = parseExpression(ast1);
            boolean isNotNull = false;
            // scroll 'is'
            ast1 = ast1.getNextSibling();
            ast1 = ast1.getNextSibling();
            if ("not".equalsIgnoreCase(ast1.getText())) {
                isNotNull = true;
            }
            return new IsNullConditionImpl(expr, isNotNull);

        } else if (ast.getType() == PLSqlTokenTypes.EXISTS_EXPR) {
            return new ExistsConditionImpl();
        } else if (ast.getType() == PLSqlTokenTypes.VAR_REF) {
            return parseGenericNameRef(ast);
        } else if (ast.getType() == PLSqlTokenTypes.BETWEEN_CONDITION) {
            AST ast1 = ast.getFirstChild();
            Expression expr = parseExpression(ast1);
            boolean isNotBetween = false;
            ast1 = ast1.getNextSibling();
            if ("not".equalsIgnoreCase(ast1.getText())) {
                isNotBetween = true;
                ast1 = ast1.getNextSibling();
            }
            Expression l = parseExpression(ast1);
            ast1 = ast1.getNextSibling();
            Expression r = parseExpression(ast1);

            return new BetweenConditionImpl(expr, l, r, isNotBetween);
        } else if (ast.getType() == PLSqlTokenTypes.LOGICAL_EXPR) {
            AST ast2 = ast.getFirstChild();
            boolean isOr = false;

            List<Expression> exprList = new ArrayList<Expression>();
            while (ast2 != null) {

                if (ast2.getType() == PLSqlTokenTypes.AND_LOGICAL) {
                    isOr = false;
                } else if (ast2.getType() == PLSqlTokenTypes.OR_LOGICAL) {
                    isOr = true;
                } else {
                    Expression l = parseCondition(ast2);
                    exprList.add(l);
                }

                ast2 = ast2.getNextSibling();
            }

            return new LogicalExpressionImpl(exprList, isOr);
        } else if (ast.getType() == PLSqlTokenTypes.BOOLEAN_LITERAL) {
            return new BooleanLiteralImpl(ast.getFirstChild().getText());
//        } else if (ast.getType() == PLSqlTokenTypes.CASE_EXPRESSION_SRCH) {
//            return parseCaseExpressionSearch(ast);
//        } else if (ast.getType() == PLSqlTokenTypes.CASE_EXPRESSION_SMPL) {
//            return parseCaseExpressionSimple(ast);
        } else {
            return parseExpression(ast);
//            throw new ParserException("Not supported at the moment: " + ast.getText());
        }
*/
    }


    protected SchemaTableName parseSchemaTableNames(AST ast, AST[] result) {
        if (ast.getType() == PLSqlTokenTypes.SCHEMA_NAME) {
            String schema = ast.getFirstChild().getText();
            ast = ast.getNextSibling();
            String table = ast.getFirstChild().getText();
            result[0] = ast.getNextSibling();
            return new SchemaTableName(schema, table);
        } else if (ast.getType() == PLSqlTokenTypes.TABLE_NAME_DDL) {
            String table = ast.getFirstChild().getText();
            result[0] = ast.getNextSibling();
            return new SchemaTableName(null, table);
        } else if (ast.getType() == PLSqlTokenTypes.TABLE_NAME) {
            String table = ast.getFirstChild().getText();
            result[0] = ast.getNextSibling();
            return new SchemaTableName(null, table);
        } else {
            throw new ParserException("Could not table name");
        }
    }

    protected ColumnDefinition parseColumnDefinition(AST ast, TableDefinition tabdef){
        AST ast1 = ast.getFirstChild();
        String columnName = ast1.getFirstChild().getText();

        ast1 = ast1.getNextSibling();
        // parse type ...
        Type t = parseDatatype(ast1);

        return new ColumnDefinitionImpl(columnName, t, tabdef);
    }

    private PlSqlElement parseTableDefintion(AST ast) {
        AST ast0 = ast.getFirstChild();
        AST[] result = new AST[1];
        SchemaTableName tt2 = parseSchemaTableNames(ast0, result);
        ast0 = result[0];
        TableDefinitionImpl tabdef = new TableDefinitionImpl(tt2.table);

        ctxTracker.__openContext__(ContextPath.TABLE_DEF, tt2.table);
        List<ColumnDefinition> columns = new ArrayList<ColumnDefinition>();
        while (ast0 != null) {
            if (ast0.getType() == PLSqlTokenTypes.COLUMN_DEF) {
                AST ast1 = ast0.getFirstChild();
/*
                String columnName = ast1.getFirstChild().getText();
                ast1 = ast1.getNextSibling();
                // parse type ...
                Type t = parseDatatype(ast1);
*/

                ColumnDefinitionImpl cdef = (ColumnDefinitionImpl) parseColumnDefinition(ast0, tabdef); //new ColumnDefinitionImpl(columnName, t, tabdef);
                AST ast2 = ast1.getNextSibling();
                while (ast2 != null) {
                    if (ast2.getType() == PLSqlTokenTypes.COLUMN_PK_SPEC) {
                        cdef.setPrimaryKey(true);
                    } else if (ast2.getType() == PLSqlTokenTypes.COLUMN_FK_SPEC) {
                        AST ast3 = ast2.getFirstChild();
                        String _tableName = parseTableName(ast3);
                        ast3 = ast3.getNextSibling();
                        String _columnName = parseColumnName(ast3);
                        cdef.setForeignKeySpec(_tableName, _columnName);
                    } else if (ast2.getType() == PLSqlTokenTypes.NOT_NULL_STMT) {
                        if ("not".equalsIgnoreCase(ast2.getFirstChild().getText())) {
                            cdef.setNotNull(true);
                        }
                    }
                    ast2 = ast2.getNextSibling();
                }

                columns.add(cdef);
            } else if (ast0.getType() == PLSqlTokenTypes.CONSTRAINT) {
                AST ast2 = ast0.getFirstChild();
                String constraintName = ast2.getFirstChild().getText();
                ast2 = ast2.getNextSibling();
                if (ast2.getType() == PLSqlTokenTypes.FK_SPEC) {
                    AST columnList = ast2.getFirstChild();
                    String[] privateColumns = parseColumnList(columnList);
                    AST refTable = columnList.getNextSibling();
                    AST refColumnList = refTable.getNextSibling();
                    String[] refColumns = parseColumnList(refColumnList);

                    ForeignKeyConstraint constr = new ForeignKeyConstraintImpl(
                            constraintName,
                            refTable.getFirstChild().getText(),
                            privateColumns,
                            refColumns
                    );
                    tabdef.addConstraint(constr);

                } else if (ast2.getType() == PLSqlTokenTypes.PK_SPEC) {
                    AST columnList = ast2.getFirstChild();
                    String[] privateColumns = parseColumnList(columnList);
                    PrimaryKeyConstraint constr = new PrimaryKeyConstraintImpl(
                            constraintName, privateColumns
                    );
                    tabdef.addConstraint(constr);
                }
            }
            ast0 = ast0.getNextSibling();
        }

        tabdef.setColumnDefs(columns.toArray(new ColumnDefinition[0]));
        ctxTracker.__closeContext__();
        return tabdef;
    }

    static String[] parseColumnList(AST ast) {
        AST ast0 = ast.getFirstChild();
        List<String> out = new ArrayList<String>();
        while (ast0 != null) {
            String column = ast0.getFirstChild().getText();
            out.add(column);
            ast0 = ast0.getNextSibling();
        }
        return out.toArray(new String[0]);
    }

    static String parseTableName(AST ast) {
        return ast.getFirstChild().getText();
    }

    static String parseColumnName(AST ast) {
        return ast.getFirstChild().getText();
    }

    private PlSqlElement parsePackageSpec(AST ast) {

        AST ast0 = ast.getFirstChild();
        final String[] names = new String[1];
        AST ast1 = parsePackageName(ast0, new PackageNameHandler(){
            public void handle(String schema, String packageName) {
                names[0] = packageName;
            }
        });

        setPackageName(names[0]);
        PackageSpecImpl spec = new PackageSpecImpl(names[0]);
        ctxTracker.__openContext__(ContextPath.PACKAGE_SPEC, names[0]);

        if (ast1 == null) {
            // no specification body?
            return spec;
        }
        if (ast1.getType() == PLSqlTokenTypes.AUTHID) {
            ast1 = ast1.getNextSibling();
            String uid = parseIdent(ast1);
            ast1 = ast1.getNextSibling();
        }

        spec.setSpecList(parsePackageContent(ast1));

        ctxTracker.__closeContext__();
        return spec;
    }

    void parsePackageObjectSpec(AST ast0, List<PlSqlElement> spec) { //PackageSpecImpl spec) {
        if (ast0.getType() == PLSqlTokenTypes.FUNCTION_SPEC) {
            AST ast1 = ast0.getFirstChild();
            String fname = parseObjectName(ast1);
            ctxTracker.__openContext__(ContextPath.FUNCTION_SPEC, fname);

            ArgumentList args = new ArgumentListImpl();
            if (ast1.getNextSibling().getType() == PLSqlTokenTypes.ARGUMENT_LIST) {
                ast1 = ast1.getNextSibling();
                args = parseArgumentList(ast1);
            }

            // parse return type
            ast1 = ast1.getNextSibling().getFirstChild();
            Type returnType = parseTypeSpec(ast1, getPackageName());

            ctxTracker.__closeContext__();
            spec.add( new FunctionSpecImpl(fname, args, returnType));
        } else if (ast0.getType() == PLSqlTokenTypes.PROCEDURE_SPEC) {
            AST ast1 = ast0.getFirstChild();
            String pname = parseObjectName(ast1);
            ctxTracker.__openContext__(ContextPath.PROCEDURE_SPEC, pname);

            ArgumentList args = new ArgumentListImpl();
            if (ast1.getNextSibling() != null) {
                args = parseArgumentList(ast1.getNextSibling());
            }

            ctxTracker.__closeContext__();
            spec.add(new ProcedureSpecImpl(pname, args));
        } else if (ast0.getType() == PLSqlTokenTypes.FUNCTION_BODY) {
            spec.add(parseFunctionBody(ast0, false));
        } else if (ast0.getType() == PLSqlTokenTypes.PROCEDURE_BODY) {
            spec.add(parseProcedureBody(ast0, false));

        } else if (ast0.getType() == PLSqlTokenTypes.VARIABLE_DECLARATION) {
            spec.add(parseVariableDecl(ast0));

/*
    "type"! type_name ("as"!|"is"!)
    (
        ("object"! OPEN_PAREN! record_item (COMMA! record_item )* CLOSE_PAREN!)
            { #type_definition = #([OBJECT_TYPE_DEF, "OBJECT_TYPE_DEF" ], #type_definition);}
        | ("table"! "of"! type_spec ("index"! "by"! type_spec)? ("not" "null")? )
            { #type_definition = #([TABLE_COLLECTION, "TABLE_COLLECTION" ], #type_definition);}
        | ("record" OPEN_PAREN! record_item ( COMMA! record_item )* CLOSE_PAREN!)
            { #type_definition = #([RECORD_TYPE_DECL, "RECORD_TYPE_DECL" ], #type_definition); }
        | ( "ref" "cursor"! ( "return"! record_name (PERCENTAGE "rowtype")? )? )
            { #type_definition = #([REF_CURSOR, "REF_CURSOR" ], #type_definition); }
        | ( "varray"! OPEN_PAREN! plsql_expression CLOSE_PAREN! "of"! type_spec ("not" "null")?)
            { #type_definition = #([VARRAY_COLLECTION, "VARRAY_COLLECTION" ], #type_definition); }
    )

    "type"! rt=type_name "is"!
*/

        } else if (ast0.getType() == PLSqlTokenTypes.RECORD_TYPE_DECL) {
            spec.add(parseRecordTypeDecl(ast0));
        } else if (ast0.getType() == PLSqlTokenTypes.TABLE_COLLECTION) {
            spec.add(parseTableCollection(ast0));
        } else if (ast0.getType() == PLSqlTokenTypes.VARRAY_COLLECTION) {
            spec.add(parseVarrayCollection(ast0));
        } else if (ast0.getType() == PLSqlTokenTypes.OBJECT_TYPE_DEF) {
            spec.add(parseObjectType(ast0));
        } else if (ast0.getType() == PLSqlTokenTypes.REF_CURSOR) {
            spec.add(parserRefCursor(ast0));

        } else if (ast0.getType() == PLSqlTokenTypes.EXCEPTION_DECL) {
            Declaration decl = parseExceptionDecl(ast0);
            spec.add(decl);
        } else if (ast0.getType() == PLSqlTokenTypes.EXCEPTION_PRAGMA) {
            Declaration decl = parseExceptionPragma(ast0);
            spec.add(decl);
        } else if (ast0.getType() == PLSqlTokenTypes.RESTRICT_REF_PRAGMA) {
            // todo -
        } else if (ast0.getType() == PLSqlTokenTypes.INTERFACE_PRAGMA) {
            // todo -
        } else if (ast0.getType() == PLSqlTokenTypes.BUILTIN_PRAGMA) {
            // todo -
        } else if (ast0.getType() == PLSqlTokenTypes.FIPSFLAG_PRAGMA) {
            // todo -
        } else if (ast0.getType() == PLSqlTokenTypes.SUBTYPE_DECL) {
            // todo -
        } else if (ast0.getType() == PLSqlTokenTypes.TIMESTAMPG_PRAGMA) {
            // todo -
        } else if (ast0.getType() == PLSqlTokenTypes.CONDITIONAL_COMPILATION) {
            parseConditionCompilationExpr(ast0, spec);
        } else if (ast0.getType() == PLSqlTokenTypes.COND_COMP_ERROR) {
            // todo -
        } else if (ast0.getType() == PLSqlTokenTypes.SERIALLY_REUSABLE_PRAGMA) {
            // todo -
        } else if (ast0.getType() == PLSqlTokenTypes.PACKAGE_INIT_SECTION) {
            // todo -- ignore for now
        } else if (ast0.getType() == PLSqlTokenTypes.PRAGMA) {
            // todo -- ignore for now
        } else if (ast0.getType() == PLSqlTokenTypes.CURSOR_DECLARATION) {
            // todo -- ignore for now
        } else {
            throw new ParserException("Not supported at the moment: " + ast0.getText());
        }
    }

    protected RefCursorDecl parserRefCursor(AST ast) {
        AST ast0 = ast.getFirstChild();
        String typeName = parseTypeName(ast0);
        return new RefCursorDeclImpl(typeName, null);
    }

//    private Declaration parseObjectTypeDecl(AST ast) {
//        AST ast1 = ast.getFirstChild();
//        String typeName = parseTypeName(ast1);
//        ObjectTypeDeclImpl o = new ObjectTypeDeclImpl();
//        o.setTypeName(typeName);
//
//        ast1 = ast1.getNextSibling();
//        while(ast1 != null){
//
//            ast1 = ast1.getNextSibling();
//        }
//
//        return o;
//    }

    protected VarrayCollectionDecl parseVarrayCollection(AST ast0) {
        AST ast1 = ast0.getFirstChild();
        String typeName = parseTypeName(ast1);

        ast1 = ast1.getNextSibling();
        Expression varraySize = parseExpression(ast1);

        ast1 = ast1.getNextSibling();
        Type basedType = parseTypeSpec(ast1, getPackageName());

        // todo - baseType must be resolved
        return new VarrayCollectionDeclImpl(typeName, basedType, varraySize);
    }

    private void parseConditionCompilationExpr(AST ast0, List<PlSqlElement> spec) { //PackageSpecImpl spec) {
        AST ast1 = ast0.getFirstChild();

        while (ast1 != null) {
            if (ast1.getText().equalsIgnoreCase("$end")) {
                // end of expression
                break;
            } else if (ast1.getText().equalsIgnoreCase("$else")) {
                ast1 = ast1.getNextSibling();
                // todo - parse "else" expression
                AST ast2 = ast1.getFirstChild();
                while (ast2 != null) {
                    parsePackageObjectSpec(ast2, spec);
                    ast2 = ast2.getNextSibling();
                }
            } else if (ast1.getText().equalsIgnoreCase("$if")) {
                // skip $if
                ast1 = ast1.getNextSibling();
                Expression cond = parseCondition(ast1);
            } else if (ast1.getText().equalsIgnoreCase("$then")) {
                // skip $then
                ast1 = ast1.getNextSibling();
                // todo - parse "then" expression
                AST ast2 = ast1.getFirstChild();
                while (ast2 != null) {
                    parsePackageObjectSpec(ast2, spec);
                    ast2 = ast2.getNextSibling();
                }
            }

            ast1 = ast1.getNextSibling();
        }
    }

    protected TableCollectionDecl parseTableCollection(AST ast0) {
        AST ast1 = ast0.getFirstChild();
        String typeName = parseTypeName(ast1);

        ast1 = ast1.getNextSibling();
        Type basedType = parseTypeSpec(ast1, getPackageName());
        return new TableCollectionDeclImpl(typeName, basedType);
    }

    private Declaration parseExceptionPragma(AST ast) {
        // "pragma"! "exception_init"! OPEN_PAREN! complex_name COMMA! oracle_err_number CLOSE_PAREN! SEMI!
        AST ast0 = ast.getFirstChild();
        String name = parseComplexName(ast0);
        ast0 = ast0.getNextSibling();
        Expression expr = parseExpression(ast0);
        return new PragmaExceptionInitImpl(name, expr);
    }

    private static Declaration parseExceptionDecl(AST ast) {
        // exception_name "exception" SEMI!
        AST ast0 = ast.getFirstChild();
        String exceptName = parseIdent(ast0);
        if (ast0.getNextSibling() != null) {
            String name2 = parseIdent(ast0.getNextSibling());
            exceptName += "." + name2;
        }

        return new ExceptionDeclImpl(exceptName);
    }


    private PlSqlElement parseCreatePackageBody(AST ast) {

        AST ast0 = ast.getFirstChild();
        PackageBodyImpl body = new PackageBodyImpl();
        final String[] names = new String[1];
        AST ast1 = parsePackageName(ast0, new PackageNameHandler(){
            public void handle(String schema, String packageName) {
                names[0] = packageName;
            }
        });

        setPackageName(names[0]);
        body.setName(names[0]);
        ctxTracker.__openContext__(ContextPath.PACKAGE_BODY, names[0]);

//        List<PlSqlElement> spec = new ArrayList<PlSqlElement>();
//
//        while (ast1 != null) {
//            parsePackageObjectSpec(ast1, spec);
//            ast1 = ast1.getNextSibling();
//        }
//
//        body.setObjectSpec(spec);

        body.setObjectSpec(parsePackageContent(ast1));
        ctxTracker.__closeContext__();
        return body;
    }

    protected List<PlSqlElement> parsePackageContent(AST ast){
        List<PlSqlElement> spec = new ArrayList<PlSqlElement>();

/*
    NOTE: let derivied classes do real parsing
        while (ast != null) {
            parsePackageObjectSpec(ast, spec);
            ast = ast.getNextSibling();
        }
*/
        return spec;
    }
    
    protected RecordTypeDecl parseRecordTypeDecl(AST ast) {
        AST ast1 = ast.getFirstChild();
        String typeName = parseTypeName(ast1);
        ctxTracker.__openContext__(ContextPath.RECORD_TYPE, typeName);

        ast1 = ast1.getNextSibling();

        RecordTypeDeclImpl decl = new RecordTypeDeclImpl();
        decl.setTypeName(typeName);
        AST ast2 = ast1.getNextSibling();
        while (ast2 != null) {
            if (ast2.getType() == PLSqlTokenTypes.RECORD_ITEM) {
                RecordTypeItem ti = parseRecordItem(ast2);
                decl.addTypeItem(ti);
            }

            ast2 = ast2.getNextSibling();
        }

        ctxTracker.__closeContext__();
        return decl;
    }

    protected ObjectTypeDecl parseObjectType(AST ast) {
        AST ast0 = ast.getFirstChild();
        String typeName = parseTypeName(ast0);
        ctxTracker.__openContext__(ContextPath.OBJECT_TYPE, typeName);

        AST ast2 = ast0.getNextSibling();

        ObjectTypeDeclImpl otype = new ObjectTypeDeclImpl();
        otype.setTypeName(typeName);
        while (ast2 != null) {
            if (ast2.getType() == PLSqlTokenTypes.RECORD_ITEM) {
                RecordTypeItem ti = parseRecordItem(ast2);
                otype.addTypeItem(ti);
            }
            ast2 = ast2.getNextSibling();
        }

        ctxTracker.__closeContext__();
        return otype;
    }

    private RecordTypeItem parseRecordItem(AST ast2) {
        AST ast3 = ast2.getFirstChild();
        String recodItemName = ast3.getFirstChild().getText();

        ctxTracker.__openContext__(ContextPath.RECORD_ITEM, recodItemName);
        //Column column = parseColumnSpec(ast3);
        ast3 = ast3.getNextSibling();
        Type type = parseTypeSpec(ast3, getPackageName());
        ast3 = ast3.getNextSibling();
        Expression expr = null;
        if (ast3 != null) {
            // (default1 |p:ASSIGNMENT_EQ {#p.setType(ASSIGNMENT_OP);})
            if (ast3.getType() == PLSqlTokenTypes.ASSIGNMENT_OP) {
                // todo
            }

            ast3 = ast3.getNextSibling();
            expr = parseExpression(ast3);
        }

        RecordTypeItem ri = new RecordTypeItemImpl(recodItemName, type, expr);
        handleRecordTypeItem(ri);

        ctxTracker.__closeContext__();
        return ri;
    }

    protected void handleRecordTypeItem(RecordTypeItem ri) {
    }

    static ColumnSpec parseColumnSpec(AST ast) {
        AST ast1 = ast.getFirstChild();

        List<String> names = new ArrayList<String>();
        while (ast1 != null) {
            if (ast1.getType() == PLSqlTokenTypes.NAME_FRAGMENT) {
                String name = ast1.getFirstChild().getText();
                names.add(name);
            }
            ast1 = ast1.getNextSibling();
        }

        if (names.size() == 2) {
            return new ColumnSpecImpl(names.get(0) + "." + names.get(1));
        } else {
            return new ColumnSpecImpl(names.get(0));
        }
    }

    private static String parseObjectName(AST ast0) {
        String name = "";
        AST ast1 = ast0.getFirstChild();
        while (ast1 != null) {
            if (name.length() > 0) {
                name += ".";
            }
            name += ast1.getText();
            ast1 = ast1.getNextSibling();
        }
        return name;
    }


//    protected String parsePackageName(AST ast0) {
//        AST ast1 = ast0;
//        while (ast1 != null) {
//            if (ast1.getType() == PLSqlTokenTypes.SCHEMA_NAME) {
//                // todo -- do we need handle schema name?
//            } else if (ast1.getType() == PLSqlTokenTypes.PACKAGE_NAME) {
//                return ast1.getNextSibling().getText();
//            }
//            ast1 = ast1.getNextSibling();
//        }
//        throw new ParserException("Cannot parse package name");
//    }


    private static AST parsePackageName(AST ast0, PackageNameHandler handler) {
        AST ast1 = ast0;
        String schema = null;
        while (ast1 != null) {
            if (ast1.getType() == PLSqlTokenTypes.SCHEMA_NAME) {
                schema = ast1.getFirstChild().getText();
            } else if (ast1.getType() == PLSqlTokenTypes.PACKAGE_NAME) {
                handler.handle(schema, ast1.getFirstChild().getText());
                return ast1.getNextSibling();
            }
            ast1 = ast1.getNextSibling();
        }
        return ast1;
    }


    private interface PackageNameHandler {
        void handle(String schema, String packageName);
    }



    private Procedure parseProcedureBody(AST ast, boolean createOrReplace) {

        AST ast1 = ast.getFirstChild();
        String name = parseObjectName(ast.getFirstChild());

        ctxTracker.__openContext__(ContextPath.PROCEDURE_BODY, name);

        ProcedureImpl proc = new ProcedureImpl();
        proc.setCreateOrReplace(createOrReplace);
        proc.setEName(name);

        AST ast0 = ast1.getNextSibling();
        while (ast0 != null) {
            if (ast0.getType() == PLSqlTokenTypes.ARGUMENT_LIST) {
                proc.setArgumentList(parseArgumentList(ast0));
            } else if (ast0.getType() == PLSqlTokenTypes.DECLARE_LIST) {
                proc.setDeclarationList(parseDeclareList(ast0));
            } else if (ast0.getType() == PLSqlTokenTypes.PLSQL_BLOCK) {
               proc.setBlock(parsePlSqlBlock(ast0));
            } else {
                // ignore it so far
                throw new ParserException("Not supported: " + ast0.getText());
            }

            ast0 = ast0.getNextSibling();
        }

        ctxTracker.__closeContext__();
        return proc;
    }

    private Function parseFunctionBody(AST ast, boolean createOrReplace) {
        FunctionImpl fbody = new FunctionImpl();

        String name = parseObjectName(ast.getFirstChild());

        ctxTracker.__openContext__(ContextPath.FUNCTION_BODY, name);

        fbody.setEName(name);
        fbody.setCreateOrReplace(createOrReplace);

        AST ast0 = ast.getFirstChild().getNextSibling();
        while (ast0 != null) {
            if (ast0.getType() == PLSqlTokenTypes.ARGUMENT_LIST) {
                fbody.setArgumentList(parseArgumentList(ast0));
            } else if ("deterministic".equalsIgnoreCase(ast0.getText())) {
                // ignore so far
            } else if ("parallel_enable".equalsIgnoreCase(ast0.getText())) {
                // ignore so far
            } else if ("pipelined".equalsIgnoreCase(ast0.getText())) {
                // ignore so far
            } else if ("using".equalsIgnoreCase(ast0.getText())) {
                // skip identifier
                ast0 = ast0.getNextSibling();
            } else if (ast0.getType() == PLSqlTokenTypes.CHARACTER_SET) {
                // ignore so far
            } else if (ast0.getType() == PLSqlTokenTypes.RETURN_TYPE) {
                fbody.setReturnType(parseTypeSpec(ast0.getFirstChild(), null));
            } else if (ast0.getType() == PLSqlTokenTypes.DECLARE_LIST) {
                fbody.setDecl(parseDeclareList(ast0));
            } else if (ast0.getType() == PLSqlTokenTypes.PLSQL_BLOCK) {
                fbody.setBlock(parsePlSqlBlock(ast0));
            } else {
                throw new ParserException("Not supported: " + ast0.getText());
            }

            ast0 = ast0.getNextSibling();
        }

        ctxTracker.__closeContext__();
        return fbody;
    }


    protected Declaration[] parseDeclareList(AST ast){
        List<PlSqlElement> out = new ArrayList<PlSqlElement>();
        AST ast0 = ast.getFirstChild();
        while(ast0 != null){
            parsePackageObjectSpec(ast0, out);
            ast0 = ast0.getNextSibling();
        }

        // todo - DIRTY HACK, according to the return type we need Declaration only
        Iterator<PlSqlElement> iterator = out.iterator();
        while(iterator.hasNext()){
            if(!(iterator.next() instanceof Declaration)){
                iterator.remove();
            }
        }

        return out.toArray(new Declaration[0]);
    }

    protected PlSqlBlock parsePlSqlBlock(AST ast) {
/*
        AST ast0 = ast.getFirstChild();

        while(ast0 != null){
            if(ast0.getType() == PLSqlTokenTypes.STATEMENT_LIST){
                parseStatementList(ast0);
            } else if(ast0.getType() == PLSqlTokenTypes.EXCEPTION_SECTION){

            }
            ast0 = ast0.getNextSibling();
        }
*/
        return new PlSqlBlockImpl();
    }

/*
    private void parseStatementList(AST ast) {
        AST ast0 = ast.getFirstChild();
        while(ast0 != null){
            if(ast0.getType() == PLSqlTokenTypes.LOOP_STATEMENT){
                parseLoopStatement(ast0);
            } else if(ast0.getType() == PLSqlTokenTypes.FORALL_STATEMENT){
            } else if(ast0.getType() == PLSqlTokenTypes.RETURN_STATEMENT){
            } else if(ast0.getType() == PLSqlTokenTypes.EXIT_STATEMENT){
            } else if(ast0.getType() == PLSqlTokenTypes.CASE_STATEMENT){
            } else if(ast0.getType() == PLSqlTokenTypes.ASSIGNMENT_STATEMENT){
                AST l = ast0.getFirstChild();
                String var = parsePlSqlVarRef(l);
                AST r = l.getNextSibling();
                Expression expr = parseCondition(r);
            } else if(ast0.getType() == PLSqlTokenTypes.SUBQUERY){
                SelectStatement select = new SelectSubqueryImpl(
                        parseSelectExpression(ast0.getFirstChild())
                );
            } else if(ast0.getType() == PLSqlTokenTypes.SELECT_EXPRESSION){
                SelectStatement select = parseSelectExpression(ast0);
            } else if(ast0.getType() == PLSqlTokenTypes.SELECT_EXPRESSION_UNION){
                SelectStatement select = parseSelectExpressionUnion(ast0);
            } else if(ast0.getType() == PLSqlTokenTypes.INSERT_COMMAND){
                InsertStatement stmt = parseInsertStatement(ast0);
            } else if(ast0.getType() == PLSqlTokenTypes.UPDATE_COMMAND){
//                PlSqlElement stmt = parseUpdateStatement(ast0);
            } else if(ast0.getType() == PLSqlTokenTypes.DELETE_COMMAND){
                PlSqlElement stmt = parseDeleteStatement(ast0);
            } else if(ast0.getType() == PLSqlTokenTypes.INSERT_INTO_SUBQUERY_COMMAND){
            } else if(ast0.getType() == PLSqlTokenTypes.IF_STATEMENT){
                parseIfStatement(ast0);
            } else if(ast0.getType() == PLSqlTokenTypes.PROCEDURE_CALL){
                parseProcedureCall(ast0);
            } else if(ast0.getType() == PLSqlTokenTypes.PLSQL_BLOCK){
                PlSqlBlock blk = parsePlSqlBlock(ast0);
            } else if(ast0.getType() == PLSqlTokenTypes.RAISE_STATEMENT){
            } else {
                int jj = 0;
            }

            ast0 = ast0.getNextSibling();
        }
    }

    private void parseIfStatement(AST ast) {
        AST ast0 = ast.getFirstChild();
        Expression expr = parseCondition(ast0);
        ast0 = ast0.getNextSibling();

        while(ast0 != null){
            if(ast0.getType() == PLSqlTokenTypes.ELSIF_STATEMENT){
                AST ast1 = ast0.getFirstChild();
                Expression expr2 = parseCondition(ast1);
                ast1 = ast1.getNextSibling();
                parseStatementList(ast1);

            } else if(ast0.getType() == PLSqlTokenTypes.ELSE_STATEMENT){
                AST ast1 = ast0.getFirstChild();
                parseStatementList(ast1);

            } else if(ast0.getType() == PLSqlTokenTypes.STATEMENT_LIST){
                parseStatementList(ast0);
            }
            ast0 = ast0.getNextSibling();
        }

    }


    protected void parseLoopStatement(AST ast){
        AST ast0 = ast.getFirstChild();
        String loop_idx = null;
        while(ast0 != null){
            if("while".equalsIgnoreCase(ast0.getText())){
                AST ast1 = ast0.getNextSibling();
                Expression cond = parseCondition(ast1);
                ast0 = ast1;
            } else if("for".equalsIgnoreCase(ast0.getText())){
                AST ast1 = ast0.getNextSibling();
                if(ast1.getType() == PLSqlTokenTypes.NUMERIC_LOOP_SPEC){
                    AST ast2 = ast1.getFirstChild();
                    loop_idx = ast2.getFirstChild().getText();
                    AST ast3 = ast2.getNextSibling();
                    Expression l = parseExpression(ast3);
                    AST ast4 = ast3.getNextSibling();
                    Expression r = parseExpression(ast4);
                } else if(ast1.getType() == PLSqlTokenTypes.CURSOR_REF_LOOP_SPEC){
                    AST ast2 = ast1.getFirstChild();
                    loop_idx = ast2.getFirstChild().getText();
                    AST ast3 = ast2.getNextSibling();
                    String cursorName = ast3.getFirstChild().getText();
                    AST ast4 = ast3.getNextSibling();
                    if(ast4 != null){
                        // EXPR_LIST
                        parseExpessionList(ast4);
                    }

                } else if(ast1.getType() == PLSqlTokenTypes.CURSOR_IMPL_LOOP_SPEC){
                    AST ast2 = ast1.getFirstChild();
                    loop_idx = ast2.getFirstChild().getText();
                    AST ast3 = ast2.getNextSibling();
                    SelectStatement select = parseSelectExpression(ast3);
                }
                ast0 = ast1;

            } else if("loop".equalsIgnoreCase(ast0.getText())){
                AST ast1 = ast0.getNextSibling();
                if(ast1.getType() == PLSqlTokenTypes.STATEMENT_LIST){
                    ctxTracker.__openContext__(ContextPath.LOOP_STMT);
                    handleLoopIndex(loop_idx);
                    parseStatementList(ast0);
                    ctxTracker.__closeContext__();
                }
                ast0 = ast1;
            }
            ast0 = ast0.getNextSibling();
        }
    }

    protected void handleLoopIndex(String loop_idx) {
    }

    private void parseExpessionList(AST ast) {
        while(ast != null){
            parseExpression(ast);
            ast = ast.getNextSibling();
        }
    }
*/

    private ArgumentList parseArgumentList(AST ast) {
        ArgumentListImpl alist = new ArgumentListImpl();

        AST ast0 = ast.getFirstChild();
        while (ast0 != null) {
            if (ast0.getType() == PLSqlTokenTypes.PARAMETER_SPEC) {
                alist.add(parseParameterSpec(ast0));
            } else {
                throw new ParserException("Not supported: " + ast0.getText());
            }

            ast0 = ast0.getNextSibling();
        }

        return alist;
    }

    protected Argument parseParameterSpec(AST ast) {
        ArgumentImpl a = new ArgumentImpl();
        AST ast0 = ast.getFirstChild();

        while (ast0 != null) {
            if (ast0.getText().equalsIgnoreCase("in")) {
                a.setIn(true);
            } else if (ast0.getText().equalsIgnoreCase("out")) {
                a.setOut(true);
            } else if (ast0.getText().equalsIgnoreCase("nocopy")) {
                a.setNocopy(true);
            } else if (ast0.getText().equalsIgnoreCase("ref")) {
                // todo -
            } else if (ast0.getText().equalsIgnoreCase(":=")) {
                ExtAST ext = (ExtAST) ast0;
                int line = ext._token.getLine();
                ast0 = ast0.getNextSibling();
                Expression expr = parseExpression(ast0);
                a.setDefaultExpr(expr);
                a.setAssigned(true);
            } else if (ast0.getType() == PLSqlTokenTypes.PARAMETER_NAME) {
//                a.setArgumentName(ast0.getFirstChild().getFirstChild().getText());
                a.setArgumentName(ast0.getFirstChild().getText());
            } else if (isTypeSpec(ast0.getType())) { // == PLSqlTokenTypes.TYPE_SPEC) {
                a.setType(parseTypeSpec(ast0, getPackageName()));
            } else if (ast0.getType() == PLSqlTokenTypes.DEFAULT) {
                ast0 = ast0.getNextSibling();
                a.setDefaultExpr(parseExpression(ast0));
            } else if (ast0.getType() == PLSqlTokenTypes.CHARACTER_SET) {
                // todo
            } else {
                throw new ParserException("Not supported: " + ast0.getText());
            }

            ast0 = ast0.getNextSibling();
        }
        return a;
    }

    protected ReferencedName parseGenericNameRef(AST ast0) {
        String name = null;
        AST ast1 = ast0.getFirstChild();
        List<String> names = new ArrayList<String>();

        while (ast1 != null) {
            if (ast1.getType() == PLSqlTokenTypes.NAME_FRAGMENT) {
                AST ast2 = ast1.getFirstChild();
                String name1 = parseIdent(ast2);
                names.add(name1);
            }
            ast1 = ast1.getNextSibling();
        }

        Collections.reverse(names);
        switch (names.size()) {
            case 1:
                return new ReferencedNameImpl(null, names.get(0));
            case 2:
                return new ReferencedNameImpl(names.get(1), names.get(0));
            default:
                return new ReferencedNameImpl(names.get(1), names.get(0), names.subList(2, names.size()).toArray(new String[0]));
        }
    }

    protected Expression parseExpression(AST ast) {

        return new NullLiteralImpl();
/*
        AST ast0 = ast;
        if (ast0.getType() == PLSqlTokenTypes.NULL_STATEMENT) {
            return new NullLiteralImpl();
        } else if (ast0.getType() == PLSqlTokenTypes.STRING_LITERAL) {
            return new StringLiteralImpl(ast0.getFirstChild().getText());
        } else if (ast0.getType() == PLSqlTokenTypes.BOOLEAN_LITERAL) {
            return new BooleanLiteralImpl(ast0.getFirstChild().getText());
        } else if (ast0.getType() == PLSqlTokenTypes.NUMERIC_LITERAL) {
            return new NumericLiteralImpl(ast0.getFirstChild().getText());
        } else if (ast0.getType() == PLSqlTokenTypes.ROWNUM) {
            return new RownumLiteral();
        } else if (ast0.getType() == PLSqlTokenTypes.VAR_REF) {
            return parseGenericNameRef(ast0);
        } else if (ast0.getType() == PLSqlTokenTypes.PARENTHESIZED_EXPR) {
            Expression e = parseExpression(ast0.getFirstChild());
            return new ParenthesizedExprImpl(e);
        } else if (ast0.getType() == PLSqlTokenTypes.ARITHMETIC_EXPR) {
            AST ast2 = ast0.getFirstChild();
            List itemList = new ArrayList();
            while (ast2 != null) {

                if (ast2.getType() == PLSqlTokenTypes.PLUS_OP) {
                    //isOr = false;
                    itemList.add("+");
                } else if (ast2.getType() == PLSqlTokenTypes.MINUS_OP) {
                    //isOr = true;
                    itemList.add("-");
                } else if (ast2.getType() == PLSqlTokenTypes.MULTIPLICATION_OP) {
                    //isOr = true;
                    itemList.add("*");
                } else if (ast2.getType() == PLSqlTokenTypes.DIVIDE_OP) {
                    //isOr = true;
                    itemList.add("/");
                } else if (ast2.getType() == PLSqlTokenTypes.CONCAT_OP) {
                    //isOr = true;
                    itemList.add("||");
                } else {
                    Expression l = parseExpression(ast2);
                    itemList.add(l);
                }

                ast2 = ast2.getNextSibling();
            }

            return new ArithmeticExpressionImpl(itemList);
        } else if (ast0.getType() == PLSqlTokenTypes.FUNCTION_CALL) {
            return parseFunctionCall(ast0);
        } else if (ast0.getType() == PLSqlTokenTypes.SYSDATE_CONST) {
            return new SysdateExpressionImpl();
        } else if (ast0.getType() == PLSqlTokenTypes.SYSTIMESTAMP_CONST) {
            return new SystimestampLiteralImpl();
        } else if (ast0.getType() == PLSqlTokenTypes.TIMESTAMP_CONST) {
            AST ast1 = ast0.getFirstChild();
            // todo - validate format timestamp constant
            return new TimestampLiteralImpl(ast1.getFirstChild().getText());
        } else if (ast0.getType() == PLSqlTokenTypes.RELATION_CONDITION) {
            return parseCondition(ast0);
        } else if (ast0.getType() == PLSqlTokenTypes.CASE_EXPRESSION_SRCH) {
            return parseCaseExpressionSearch(ast0);
        } else if (ast0.getType() == PLSqlTokenTypes.CASE_EXPRESSION_SMPL) {
            return parseCaseExpressionSimple(ast0);
        } else if (ast0.getType() == PLSqlTokenTypes.SUBQUERY_EXPR) {
            SelectStatement select = parseSelectExpression(ast0); //.getFirstChild());
            return new SubqueryExpressionImpl(select);
        } else if (ast0.getType() == PLSqlTokenTypes.TRIM_FUNC) {
            return parseTrimFunc(ast0);
        } else if (ast0.getType() == PLSqlTokenTypes.COUNT_FUNC) {
            return parseCountFunc(ast0);
        } else if (ast0.getType() == PLSqlTokenTypes.EXTRACT_DATE_FUNC) {
            return parseExtractFunc(ast0);
//        } else if (ast0.getType() == PLSqlTokenTypes.FROM_TZ_FUNC) {
//            return parseFunctionCall(ast0);
        } else if (ast0.getType() == PLSqlTokenTypes.SEQUENCE_EXPR) {
            AST ast1 = ast0.getFirstChild();
            String sequenceName = ast1.getFirstChild().getText();
            ast1 = ast1.getNextSibling();
//            ast1 = ast1.getNextSibling();
            String method = ast1.getFirstChild().getText();

            return new SequenceExprImpl(sequenceName, method);
        } else if (ast0.getType() == PLSqlTokenTypes.AT_TIME_ZONE_EXPR) {
            AST ast1 = ast0.getFirstChild();
            Expression expr = parseExpression(ast1);
            ast1 = ast1.getNextSibling();
            String tz = parseTimeZoneSpec(ast1);
            return new AtTimeZoneExpressionImpl(expr, tz);
        } else if (ast0.getType() == PLSqlTokenTypes.INTERVAL_CONST) {
            AST ast1 = ast0.getFirstChild();
            String ivalue = ast1.getFirstChild().getText();
            ast1 = ast1.getNextSibling();
            String itype = ast1.getText();

            return new IntervalExpressionImpl(ivalue, itype);
        } else if (ast0.getType() == PLSqlTokenTypes.CAST_EXPR) {
            return parseCastExpression(ast0);
        } else if (ast0.getType() == PLSqlTokenTypes.COLUMN_OUTER_JOIN) {
            // todo
            ColumnSpec spec = parseColumnSpec(ast0.getFirstChild());
            return new ColumnOuterJoinImpl(spec.getColumnNameRef());
        } else if (ast0.getType() == PLSqlTokenTypes.CURRENT_TIMESTAMP_CONST) {
            return new CurrentTimestampLiteral(ast0.getFirstChild().getText());
        } else {
            throw new ParserException("Could not parse expression: " + ast0.getText());
        }
*/  
    }

    private Expression parseCastExpression(AST ast) {
//        cast_proc :
//            "cast"! OPEN_PAREN! plsql_expression "as"! (type_name_ref|datatype) CLOSE_PAREN!
        AST ast1 = ast.getFirstChild();
        Expression expr = parseExpression(ast1);
        String typeName = "";
        ast1 = ast1.getNextSibling();

        if (ast1.getType() == PLSqlTokenTypes.TYPE_NAME_REF) {
            typeName = ast1.getFirstChild().getText();
        } else if (ast1.getType() == PLSqlTokenTypes.DATATYPE) {
            typeName = ast1.getFirstChild().getText();
        } else {
            throw new ParserException("Could not parse tyoe name: " + ast1.getText());
        }

        return new CastExpressionImpl(expr, typeName);
    }

    private static String parseTimeZoneSpec(AST ast) {
//        timezone_spec:
//            ( string_literal
//             | identifier (DOT identifier)*
//             | "sessiontimezone"
//             | "dbtimezone")
        AST ast1 = ast.getFirstChild();
        if (ast1.getType() == PLSqlTokenTypes.STRING_LITERAL) {
            return ast1.getFirstChild().getText();
        } else if (ast1.getType() == PLSqlTokenTypes.COMPLEX_NAME) {
            return ast1.getFirstChild().getText();
        } else {
            return ast1.getText();
        }
    }

    private static Expression parseBaseExpression(AST ast) {
        // todo
        return null;
    }

    private Expression parseCountFunc(AST ast) {
//            count_function:
//                "count"! OPEN_PAREN! ( ASTERISK | ( ("distinct")? plsql_expression )) CLOSE_PAREN!

        AST ast1 = ast.getFirstChild();
        Expression expr = null;
        String asterisk = null;
        if (ast1.getType() == PLSqlTokenTypes.ASTERISK_COLUMN) {
            return new CountFuncImpl(ast1.getFirstChild().getText());
        } else if (ast1.getType() == PLSqlTokenTypes.IDENT_ASTERISK_COLUMN) {
            AST ast2 = ast1.getFirstChild();
            String ref = parseTableRef(ast2);
            return new CountFuncImpl(ref + ".*");
        } else if ("distinct".equalsIgnoreCase(ast1.getText())) {
            ast1 = ast1.getNextSibling();
            expr = parseExpression(ast1);
            return new CountFuncImpl(expr, true);
        } else {
            expr = parseExpression(ast1);
            return new CountFuncImpl(expr);
        }
    }

    private static String parseTableRef(AST ast) {
        return ast.getFirstChild().getText();
    }

    private Expression parseExtractFunc(AST ast0) {
//      "extract"! OPEN_PAREN! extract_consts "from"! plsql_expression CLOSE_PAREN!
//  extract_consts:
//      "second" | "minute" | "hour" | "day" | "month" | "year" | "timezone_hour" | "timezone_minute"
        AST ast1 = ast0.getFirstChild();
        Expression expr = null;
        String literal = null;
        while (ast1 != null) {
            if ("second".equalsIgnoreCase(ast1.getText())) {
                literal = ast1.getText();
            } else if ("minute".equalsIgnoreCase(ast1.getText())) {
                literal = ast1.getText();
            } else if ("hour".equalsIgnoreCase(ast1.getText())) {
                literal = ast1.getText();
            } else if ("day".equalsIgnoreCase(ast1.getText())) {
                literal = ast1.getText();
            } else if ("month".equalsIgnoreCase(ast1.getText())) {
                literal = ast1.getText();
            } else if ("year".equalsIgnoreCase(ast1.getText())) {
                literal = ast1.getText();
            } else if ("timezone_hour".equalsIgnoreCase(ast1.getText())) {
                literal = ast1.getText();
            } else if ("timezone_minute".equalsIgnoreCase(ast1.getText())) {
                literal = ast1.getText();
            } else {
                // plsql_expression
                expr = parseExpression(ast1);
            }

            ast1 = ast1.getNextSibling();
        }
        return new ExtractFunctionImpl(expr, literal);
    }

    private Expression parseTrimFunc(AST ast) {
        AST ast1 = ast.getFirstChild();
//        "trim"! o:OPEN_PAREN! ( "leading" | "trailing" | "both") ?
//        plsql_expression ( f:"from" plsql_expression )? CLOSE_PAREN!
        Expression arg = null;
        while (ast1 != null) {
            if ("leading".equalsIgnoreCase(ast1.getText())) {
                // ...
            } else if ("trailing".equalsIgnoreCase(ast1.getText())) {
                // ...
            } else if ("both".equalsIgnoreCase(ast1.getText())) {
                // ...
            } else if ("from".equalsIgnoreCase(ast1.getText())) {
                ast1 = ast1.getNextSibling();
                Expression e = parseExpression(ast1);
            } else {
                arg = parseExpression(ast1);
            }
            ast1 = ast1.getNextSibling();
        }


        return new TrimFunctionImpl(arg);
    }

    private Expression parseCaseExpressionSearch(AST ast) {
//        case_expression:
//        ("case"! (
//            // searched_case_expression
//            ( "when" condition t:"then" plsql_expression )+
//            // simple_case_expression
//            | smpl:plsql_expression ("when" plsql_expression "then" plsql_expression)+
//         )
//        ( "else" plsql_expression ) ?
//        "end"!)
//        {
//            if(#smpl != null){
//                {#case_expression=#([CASE_EXPRESSION_SMPL, "CASE_EXPRESSION_SMPL"], #case_expression);}
//            } else {
//                {#case_expression=#([CASE_EXPRESSION_SRCH, "CASE_EXPRESSION_SRCH"], #case_expression);}
//            }
//        }

        AST ast0 = ast.getFirstChild();
        Expression _else = null;
        Expression when = null;

        CaseExpressionImpl _case = new CaseExpressionImpl(CaseExpression.SEARCHED);

        while (ast0 != null) {
            if (ast0.getText().equalsIgnoreCase("when")) {
                // get condition
                ast0 = ast0.getNextSibling();
                when = parseCondition(ast0);
            } else if (ast0.getText().equalsIgnoreCase("then")) {
                // get expression
                ast0 = ast0.getNextSibling();
                Expression then = parseExpression(ast0);

                _case.addWhenThenPair(when, then);
            } else if (ast0.getText().equalsIgnoreCase("else")) {
                // get expression
                ast0 = ast0.getNextSibling();
                _else = parseExpression(ast0);
                _case.setElseExpression(_else);
            }

            ast0 = ast0.getNextSibling();
        }

        return _case;
    }

    private Expression parseCaseExpressionSimple(AST ast) {
//        case_expression:
//        ("case"! (
//            // searched_case_expression
//            ( "when" condition t:"then" plsql_expression )+
//            // simple_case_expression
//            | smpl:plsql_expression ("when" plsql_expression "then" plsql_expression)+
//         )
//        ( "else" plsql_expression ) ?
//        "end"!)
//        {
//            if(#smpl != null){
//                {#case_expression=#([CASE_EXPRESSION_SMPL, "CASE_EXPRESSION_SMPL"], #case_expression);}
//            } else {
//                {#case_expression=#([CASE_EXPRESSION_SRCH, "CASE_EXPRESSION_SRCH"], #case_expression);}
//            }
//        }

        AST ast0 = ast.getFirstChild();
        Expression _else = null;
        Expression when = null;

        CaseExpressionImpl _case = new CaseExpressionImpl(CaseExpression.SIMPLE);

        Expression lead = parseExpression(ast0);
        ast0 = ast0.getNextSibling();

        _case.setLeadExpression(lead);
        while (ast0 != null) {
            if (ast0.getText().equalsIgnoreCase("when")) {
                // get condition
                ast0 = ast0.getNextSibling();
                when = parseExpression(ast0);
            } else if (ast0.getText().equalsIgnoreCase("then")) {
                // get expression
                ast0 = ast0.getNextSibling();
                Expression then = parseExpression(ast0);

                _case.addWhenThenPair(when, then);
            } else if (ast0.getText().equalsIgnoreCase("else")) {
                // get expression
                ast0 = ast0.getNextSibling();
                _else = parseExpression(ast0);
                _case.setElseExpression(_else);
            }

            ast0 = ast0.getNextSibling();
        }

        return _case;
    }

    protected ProcedureCall parseProcedureCall(AST ast){
        ProcedureCallImpl call = new ProcedureCallImpl();
        AST ast0 = ast.getFirstChild();
        while (ast0 != null) {
            if (ast0.getType() == PLSqlTokenTypes.CALLABLE_NAME_REF) {
                AST ast1 = ast0.getFirstChild();
                String name = "";
                while (ast1 != null) {
                    if (name.length() != 0) {
                        name += ".";
                    }

                    if (ast1.getType() == PLSqlTokenTypes.TABLE_REF) {
                        name += ast1.getFirstChild().getText();
                    } else if (ast1.getType() == PLSqlTokenTypes.NAME_FRAGMENT) { //EXEC_NAME_REF) {
                        name += ast1.getFirstChild().getText();
                    }

                    ast1 = ast1.getNextSibling();
                }
                call.setFunctionName(name);
            } else if (ast0.getType() == PLSqlTokenTypes.CALL_ARGUMENT_LIST) {
                call.setCallArgumentList(parseCallArgumentList(ast0));
            } else {
                throw new ParserException("Parsed type not supported: " + ast0.getText());
            }

            ast0 = ast0.getNextSibling();
        }

        return call;
    }

    protected FunctionCall parseFunctionCall(AST ast) {
        FunctionCallImpl func = new FunctionCallImpl();
        AST ast0 = ast.getFirstChild();
        while (ast0 != null) {
            if (ast0.getType() == PLSqlTokenTypes.CALLABLE_NAME_REF) {
                AST ast1 = ast0.getFirstChild();
                String name = "";
                while (ast1 != null) {
                    if (name.length() != 0) {
                        name += ".";
                    }

                    if (ast1.getType() == PLSqlTokenTypes.TABLE_REF) {
                        name += ast1.getFirstChild().getText();
                    } else if (ast1.getType() == PLSqlTokenTypes.NAME_FRAGMENT) {
                        name += ast1.getFirstChild().getText();
                    }

                    ast1 = ast1.getNextSibling();
                }
                func.setFunctionName(name);
            } else if (ast0.getType() == PLSqlTokenTypes.CALL_ARGUMENT_LIST) {
                func.setCallArgumentList(parseCallArgumentList(ast0));
            } else {
                throw new ParserException("Parsed type not supported: " + ast0.getText());
            }

            ast0 = ast0.getNextSibling();
        }

        return func;
    }

    private CallArgument[] parseCallArgumentList(AST ast) {
        List<CallArgument> cargs = new ArrayList<CallArgument>();
        AST ast0 = ast.getFirstChild();
        while (ast0 != null) {
            if (ast0.getType() == PLSqlTokenTypes.CALL_ARGUMENT) {
                AST ast1 = ast0.getFirstChild();
                String name = null;
                if (ast1.getType() == PLSqlTokenTypes.PARAMETER_REF) {
                    AST var = ast1;
                    Expression expr = null;
                    // todo --
                    ast1 = ast1.getNextSibling();
                    //cargs.add(new CallArgumentImpl(name, expr));
                } //else if (ast1.getType() == PLSqlTokenTypes.PLSQL_EXPRESSION) {
                Expression expr = parseExpression(ast1);
                cargs.add(new CallArgumentImpl(name, expr));

            } else {
                throw new ParserException("Parsed type not supported: " + ast0.getText());
            }

            ast0 = ast0.getNextSibling();
        }

        return cargs.toArray(new CallArgument[0]);
    }


    private static String parseFunctionName(AST ast) {
        String out = "";
        AST ast1 = ast.getFirstChild();
        while (ast1 != null) {
            String name1 = parseIdent(ast1);
            if (out.length() > 0) {
                out += ".";
            }
            out += name1;
            ast1 = ast1.getNextSibling();
        }

        return out;
    }

    public static Type parseDatatype(AST ast) {
        AST tt = ast.getFirstChild();
        if (tt.getText().equalsIgnoreCase("VARCHAR2")) {
            if (tt.getNextSibling() != null) {
                String tn = tt.getNextSibling().getText();
                return new Varchar2Type(Integer.parseInt(tn));
            } else {
                return new Varchar2Type();
            }
        } else if (tt.getText().equalsIgnoreCase("VARCHAR")) {
            AST ast2 = tt.getNextSibling();
            if (ast2 != null) {
                String tn = ast2.getText();
                return new VarcharType(Integer.parseInt(tn));
            } else {
                return new VarcharType();
            }
        } else if (tt.getText().equalsIgnoreCase("DATE")) {
            return new DateType();
        } else if (tt.getText().equalsIgnoreCase("TIMESTAMP")) {
            return new TimestampType();
        } else if (tt.getText().equalsIgnoreCase("INTEGER")) {
            if (tt.getNextSibling() != null) {
                String tn = tt.getNextSibling().getText();
                return new IntegerType(Integer.parseInt(tn));
            } else {
                return new IntegerType();
            }
        } else if (tt.getText().equalsIgnoreCase("PLS_INTEGER")) {
            if (tt.getNextSibling() != null) {
                String tn = tt.getNextSibling().getText();
                return new PlsIntegerType(Integer.parseInt(tn));
            } else {
                return new PlsIntegerType();
            }
        } else if (tt.getText().equalsIgnoreCase("INT")) {
            if (tt.getNextSibling() != null) {
                String tn = tt.getNextSibling().getText();
                return new IntegerType(Integer.parseInt(tn));
            } else {
                return new IntegerType();
            }
        } else if (tt.getText().equalsIgnoreCase("NUMBER")) {
            /*
                "number"(OPEN_PAREN! (NUMBER|*) (COMMA! NUMBER)? CLOSE_PAREN! )? )
            */
            if (tt.getNextSibling() != null && tt.getNextSibling().getNextSibling() != null) {
                String tn = tt.getNextSibling().getText();
                String part = tt.getNextSibling().getNextSibling().getText();
                if (tn.equals("*")) {
                    return new NumberType();
                } else {
                    return new NumberType(Integer.parseInt(tn), Integer.parseInt(part));
                }
            } else if (tt.getNextSibling() != null) {
                String tn = tt.getNextSibling().getText();
                return new NumberType(Integer.parseInt(tn));
            } else {
                return new NumberType();
            }
        } else if (tt.getText().equalsIgnoreCase("NUMERIC")) {
            /*
                "numeric"(OPEN_PAREN! NUMBER (COMMA! NUMBER)? CLOSE_PAREN! )? )
            */
            if (tt.getNextSibling() != null && tt.getNextSibling().getNextSibling() != null) {
                String tn = tt.getNextSibling().getText();
                String part = tt.getNextSibling().getNextSibling().getText();
                return new NumericType(Integer.parseInt(tn), Integer.parseInt(part));
            } else if (tt.getNextSibling() != null) {
                String tn = tt.getNextSibling().getText();
                return new NumericType(Integer.parseInt(tn));
            } else {
                return new NumericType();
            }
        } else if (tt.getText().equalsIgnoreCase("INTERVAL")) {
            // todo!!!
            /* "interval"
                ( ( "year" (OPEN_PAREN! NUMBER CLOSE_PAREN!)? "to"! "month"!)
                | ("day" (OPEN_PAREN! NUMBER CLOSE_PAREN!)? "to"! "second"! (OPEN_PAREN! NUMBER CLOSE_PAREN!)?)
            */
            String tn = "INTERVAL";
            AST prefix = tt.getNextSibling();
            tn += " " + prefix.getText();
            AST ast1 = prefix.getNextSibling();
            if (ast1.getText().equalsIgnoreCase("to")) {
                // prefix without size
                tn += " TO";
                ast1 = ast1.getNextSibling();
            } else {
                // prefix with size
                // set size
                tn += " (" + ast1.getText() + ") TO";
                ast1 = ast1.getNextSibling();
                ast1 = ast1.getNextSibling();
            }

            tn += " " + ast1.getText();
            ast1 = ast1.getNextSibling();
            if (ast1 != null) {
                tn += " (" + ast1.getText() + ")";
            }

            return new IntervalDayToSecType();
        } else if (tt.getText().equalsIgnoreCase("CHAR")) {
            if (tt.getNextSibling() != null) {
                return new CharType(Integer.parseInt(tt.getNextSibling().getText()));
            } else {
                return new CharType();
            }
        } else if (tt.getText().equalsIgnoreCase("BOOLEAN")) {
            return new BooleanType();
        } else if (tt.getText().equalsIgnoreCase("FLOAT")) {
            return new FloatType();
        } else if (tt.getText().equalsIgnoreCase("binary_integer")) {
            return new BinaryIntegerType();
        } else if (tt.getText().equalsIgnoreCase("RAW")) {
            if (tt.getNextSibling() != null) {
                return new RawType(Integer.parseInt(tt.getNextSibling().getText()));
            } else {
                return new RawType();
            }
        } else if (tt.getText().equalsIgnoreCase("NATURAL")) {
            // actually NATURAL is a subtype of BINARY INTEGER
            return new BinaryIntegerType();
        } else if (tt.getText().equalsIgnoreCase("POSITIVE")) {
            // actually POSITIVE is a subtype of BINARY INTEGER
            return new BinaryIntegerType();
        } else if (tt.getText().equalsIgnoreCase("NVARCHAR2")) {
            return new NVarchar2Type();
        } else if (tt.getText().equalsIgnoreCase("BLOB")) {
            return new BlobType();
        } else if (tt.getText().equalsIgnoreCase("CLOB")) {
            return new ClobType();
        } else if (tt.getText().equalsIgnoreCase("LONG")) {
            return new LongType();
        } else if (tt.getText().equalsIgnoreCase("SMALLINT")) {
            return new SmallIntType();
        } else {
            throw new ParserException("Type not supported: " + ast.getText());
        }
    }

    protected UserDefinedType parseTypeNameRef(AST ast) {
        AST ast0 = ast.getFirstChild();
        List<String> sign = new ArrayList<String>();
        while (ast0 != null) {
            if (ast0.getType() == PLSqlTokenTypes.NAME_FRAGMENT) {
                sign.add(ast0.getFirstChild().getText());
            }
            ast0 = ast0.getNextSibling();
        }

        if (sign.size() == 1) {
            return new UserDefinedType(null, sign.get(0));
        } else {
            return new UserDefinedType(sign.get(0), sign.get(1));
        }
    }

    protected String parsePlSqlVarRef(AST ast) {
        AST ast0 = ast.getFirstChild();
        StringBuilder b = new StringBuilder();
        while (ast0 != null) {
            if (ast0.getType() == PLSqlTokenTypes.NAME_FRAGMENT) {
                String ss = ast0.getFirstChild().getText();
                if(b.length() > 0){
                    b.append(".");
                }
                b.append(ss);
            }
            ast0 = ast0.getNextSibling();
        }

        return b.toString();
    }

    protected String parseTypeName(AST ast) {
        AST ast0 = ast.getFirstChild();
        String text = ast0.getText();
        if (text.charAt(0) == '\"' && text.charAt(text.length() - 1) == '\"') {
            return text.substring(1, text.length() - 1);
        } else {
            return text;
        }
    }


    static boolean isTypeSpec(int type) {
        switch (type) {
            case PLSqlTokenTypes.DATATYPE:
            case PLSqlTokenTypes.TYPE_NAME_REF:
            case PLSqlTokenTypes.COLUMN_TYPE_REF:
            case PLSqlTokenTypes.TABLE_TYPE_REF:
                return true;
        }

        return false;
    }

    public Type parseTypeSpec(AST ast, String packageName) {

        AST ast0 = ast;
        if (ast0.getType() == PLSqlTokenTypes.DATATYPE) {
            return parseDatatype(ast0);
        } else if (ast0.getType() == PLSqlTokenTypes.TYPE_NAME_REF) {
            return parseTypeNameRef(ast0);
        } else if (ast0.getType() == PLSqlTokenTypes.COLUMN_TYPE_REF) {
            //  (variable_name PERCENTAGE "type" ) => variable_name PERCENTAGE! "type"!
            //  | ( table_name DOT column_name PERCENTAGE "type" ) => table_name DOT! column_name PERCENTAGE! "type"!
            AST ast1 = ast0.getFirstChild();
            String table = parseIdent(ast1.getFirstChild());
            AST ast2 = ast1.getNextSibling();
            String column = parseIdent(ast2.getFirstChild());
            return new TableColumnRefType(table, column);
        } else if (ast0.getType() == PLSqlTokenTypes.TABLE_TYPE_REF) {

            AST ast1 = ast0.getFirstChild().getFirstChild();
            String tableName = parseIdent(ast1);
            return new RowtypeType(tableName);
        } else {
            throw new ParserException("Type not supported: " + ast.getText());
        }
//        } else {
//            throw new ParserException("Type not supported: " + ast.getText());
//        }
    }


    protected VariableDecl parseVariableDecl(AST ast) {
        // variable_name ("constant")?  type_spec ("not" "null")? ((ASSIGNMENT_EQ|default1) plsql_expression)? SEMI!
        VariableDeclImpl decl = new VariableDeclImpl();
        AST ast0 = ast.getFirstChild();

        String varname = ast0.getFirstChild().getText();
//        String varname = ast0.getFirstChild().getFirstChild().getText();
        decl.setVariableName(varname);
        ast0 = ast0.getNextSibling();
        if (ast0.getText().equalsIgnoreCase("constant")) {
            // constan
            decl.setConstant(true);
            ast0 = ast0.getNextSibling();
        }

        Type t = parseTypeSpec(ast0, getPackageName());
        decl.setType(t);
        ast0 = ast0.getNextSibling();

        if (ast0 != null) {
            if (ast0.getText().equalsIgnoreCase("not")) {
                decl.setNotNull(true);
                ast0 = ast0.getNextSibling().getNextSibling();
            }
        }

        if (ast0 != null) {
            // todo - process (ASSIGNMENT_EQ|default1)
            if (ast0.getText().equals(":=")) {
                // assignment
                decl.setBeingAssigned(true);
            } else if (ast0.getType() == PLSqlTokenTypes.DEFAULT) {
                // default
                decl.setDefault1(true);
            }
            ast0 = ast0.getNextSibling();
            Expression expr = parseExpression(ast0);
            decl.setExpr(expr);
        }

        return decl;
    }


    private static String parseIdent(AST ast) {
        return ast.getText();
// TODO- IDENT removed       if (ast.getType() == PLSqlTokenTypes.IDENT) {
//            return ast.getFirstChild().getText();
//        } else {
//            throw new ParserException("IDENT node expected but was: " + ast.getText());
//        }
    }


    private static String parseComplexName(AST ast) {
        AST ast1 = ast.getFirstChild();

        String out = "";
        while (ast1 != null) {
            String ident = parseIdent(ast1);
            if (out.length() > 0) {
                out += ".";
            }
            out += ident;
            ast1 = ast1.getNextSibling();
        }
        return out;
    }


    protected String getPackageName() {
        return packageName;
    }

    protected void setPackageName(String packageName) {
        this.packageName = packageName;
    }

}
