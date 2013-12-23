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

package com.deepsky.lang.parser.plsql;

import com.deepsky.generated.plsql.PLSqlTokenTypes;
import com.deepsky.integration.plsql.PlSqlElementType;
import com.intellij.psi.tree.TokenSet;


public interface PlSqlElementTypes extends PLSqlTypesAdopted {

    TokenSet PLSQL_STATEMENTS = TokenSet.create(
            RAISE_STATEMENT, LOOP_STATEMENT, GOTO_STATEMENT, EXIT_STATEMENT,
            IF_STATEMENT, RETURN_STATEMENT, LOCK_TABLE_STATEMENT, OPEN_STATEMENT,
            SET_TRN_STATEMENT, CLOSE_STATEMENT,
            CASE_STATEMENT, ASSIGNMENT_STATEMENT, COMMIT_STATEMENT, NULL_STATEMENT,
            FETCH_STATEMENT, COLLECTION_METHOD_CALL2, PROCEDURE_CALL, IMMEDIATE_COMMAND
    );


    TokenSet DB_OBJECTS_MAYBE_NESTED = TokenSet.create(
            PlSqlElementTypes.FUNCTION_BODY,
            PlSqlElementTypes.FUNCTION_SPEC,
            PlSqlElementTypes.PROCEDURE_BODY,
            PlSqlElementTypes.PROCEDURE_SPEC
    );

    TokenSet PLSQL_BLOCK_PARENTS = TokenSet.create(
            PlSqlElementTypes.FUNCTION_BODY,
            PlSqlElementTypes.FUNCTION_SPEC,
            PlSqlElementTypes.PROCEDURE_BODY,
            PlSqlElementTypes.PROCEDURE_SPEC,
            PlSqlElementTypes.CREATE_TRIGGER,
            PlSqlElementTypes.PACKAGE_BODY,
            PlSqlElementTypes.PACKAGE_SPEC
    );

    TokenSet BLOCKS = TokenSet.create(
            PLSQL_BLOCK
    );

    TokenSet SELECT_FROM_CLAUSE = TokenSet.create(
            TABLE_ALIAS, FROM_SUBQUERY, TABLE_FUNCTION
    );

    TokenSet LOOP_SPEC_TYPES = TokenSet.create(
            NUMERIC_LOOP_SPEC, CURSOR_REF_LOOP_SPEC, CURSOR_LOOP_SPEC, FORALL_LOOP_SPEC
    );

    TokenSet TABLE_LEVEL_CONSTRAINTS = TokenSet.create(
            PK_SPEC, FK_SPEC, UNIQUE_CONSTRAINT, CHECK_CONSTRAINT
    );

    TokenSet COLUMN_LEVEL_CONSTRAINTS = TokenSet.create(
            COLUMN_NOT_NULL_CONSTRAINT, COLUMN_FK_SPEC, COLUMN_PK_SPEC, COLUMN_CHECK_CONSTRAINT
    );

    TokenSet TABLE_ORGANIZATION = TokenSet.create(
            EXTERNAL_TYPE, IOT_TYPE, HEAP_ORGINIZED
    );
    // Type Extension Section
/*
    PlSqlElementType MINUS_OP = new PlSqlElementType("MINUS_OP");
    PlSqlElementType PLUS_OP = new PlSqlElementType("PLUS_OP");
    PlSqlElementType MULTIPLICATION_OP = new PlSqlElementType("MULTIPLICATION_OP");
    PlSqlElementType DIVIDE_OP = new PlSqlElementType("DIVIDE_OP");

    TokenSet ARITHMETIC_OP = TokenSet.create(
            MINUS_OP, PLUS_OP, MULTIPLICATION_OP, DIVIDE_OP
    );
*/


    TokenSet DISPLAYED_COLUMN = TokenSet.create(
            ASTERISK_COLUMN, IDENT_ASTERISK_COLUMN, EXPR_COLUMN
    );

    TokenSet ORDER_BY_EXPR = TokenSet.create(
            ARITHMETIC_EXPR, VAR_REF, ROWCOUNT_EXRESSION, EXTRACT_DATE_FUNC, ROWNUM,
            FUNCTION_CALL, COUNT_FUNC, TRIM_FUNC, SYSTIMESTAMP_CONST, SYSDATE_CONST, DBTIMEZONE,
            CASE_EXPRESSION_SMPL, CASE_EXPRESSION_SRCH
    );

    TokenSet EXPR_TYPES = TokenSet.create(
            CAST_FUNC, LAG_FUNCTION, LEAD_FUNCTION, COUNT_FUNC, TRIM_FUNC, EXTRACT_DATE_FUNC,
            ARITHMETIC_EXPR, EXISTS_EXPR, INTERVAL_DAY_TO_SEC_EXPR, DECODE_FUNC,
            FUNCTION_CALL, SYSTIMESTAMP_CONST, SYSDATE_CONST, DBTIMEZONE,
            ROWCOUNT_EXRESSION,
            STRING_LITERAL, NUMERIC_LITERAL, BOOLEAN_LITERAL, NULL_STATEMENT,
            PARENTHESIZED_EXPR, COLUMN_OUTER_JOIN,
            VAR_REF, COLUMN_OUTER_JOIN, SUBQUERY_EXPR, SEQUENCE_EXPR,
            CASE_EXPRESSION_SMPL, CASE_EXPRESSION_SRCH, USER_CONST, AT_TIME_ZONE_EXPR,
            LAST_STMT_RESULT_BOOL, LAST_STMT_RESULT_NUM, CURSOR_STATE, CURRENT_OF_CLAUSE,
            SQLCODE_SYSVAR, SQLERRM_SYSVAR, LOGICAL_EXPR, RELATION_CONDITION,
            TRIGGER_COLUMN_REF,
            COLLECTION_METHOD_CALL,
            CURRENT_TIMESTAMP_CONST,
            ROWNUM, ROWID
    );

    // Expression with BOOLEAN type only
    TokenSet CONDITION_EXPR_TYPES = TokenSet.create(
            CAST_FUNC, LAG_FUNCTION, LEAD_FUNCTION, COUNT_FUNC, TRIM_FUNC, EXTRACT_DATE_FUNC,
            ARITHMETIC_EXPR, EXISTS_EXPR, INTERVAL_DAY_TO_SEC_EXPR, DECODE_FUNC,
            FUNCTION_CALL, SYSTIMESTAMP_CONST, SYSDATE_CONST, DBTIMEZONE,
            ROWCOUNT_EXRESSION,
            LOGICAL_EXPR, PARENTHESIZED_EXPR,
            STRING_LITERAL, NUMERIC_LITERAL, BOOLEAN_LITERAL, NULL_STATEMENT,
            VAR_REF, SUBQUERY_EXPR,
            SUBQUERY_CONDITION, EXISTS_EXPR, RELATION_CONDITION, IN_CONDITION, LIKE_CONDITION, BETWEEN_CONDITION, ISNULL_CONDITION,
            CASE_EXPRESSION_SMPL, CASE_EXPRESSION_SRCH,
            LAST_STMT_RESULT_BOOL, CURSOR_STATE, CURRENT_OF_CLAUSE
    );

    TokenSet CONDITION_EXPR = TokenSet.create(
            VAR_REF, SUBQUERY_EXPR,
            ARITHMETIC_EXPR, EXISTS_EXPR, FUNCTION_CALL, PARENTHESIZED_EXPR,
            SUBQUERY_CONDITION, EXISTS_EXPR, RELATION_CONDITION, IN_CONDITION, LIKE_CONDITION, BETWEEN_CONDITION, ISNULL_CONDITION
    );

    TokenSet OBJECT_SPEC = TokenSet.create(
            VARIABLE_DECLARATION,
            PROCEDURE_SPEC, FUNCTION_SPEC,
            RECORD_TYPE_DECL,
            TABLE_COLLECTION, OBJECT_TYPE_DEF, REF_CURSOR, VARRAY_COLLECTION,
            PROCEDURE_BODY, FUNCTION_BODY
    );

    TokenSet DECLARATIONS = TokenSet.create(
            VARIABLE_DECLARATION,
            TABLE_COLLECTION,
            OBJECT_TYPE_DEF,
            RECORD_TYPE_DECL,
            // todo - REF_CURSOR,
            VARRAY_COLLECTION,
            CURSOR_DECLARATION
    );

    TokenSet SQL_STATEMENTS = TokenSet.create(
            SELECT_EXPRESSION,
            INSERT_COMMAND,
            DELETE_COMMAND,
            UPDATE_COMMAND
//            SELECT_SUBSEQ
    );

    TokenSet SUBQUERY_SELECTS = TokenSet.create(
            SELECT_EXPRESSION,
            SELECT_EXPRESSION_UNION
            //SUBQUERY
    );

    TokenSet QUERIES = TokenSet.create(
            SELECT_EXPRESSION,
            SELECT_EXPRESSION_UNION,
            SUBQUERY
    );

    TokenSet DML_STATEMENTS = TokenSet.create(
//            INSERT_SUBQUERY,
            MERGE_COMMAND,
            INSERT_COMMAND,
            DELETE_COMMAND,
            UPDATE_COMMAND
    );

    TokenSet EXEC_CONTEXT = TokenSet.create(
            PROCEDURE_BODY,
            FUNCTION_BODY
    );

    TokenSet CALLABLE_CONTEXT = TokenSet.create(
            PROCEDURE_CALL,
            FUNCTION_CALL
    );

    TokenSet PKG_BODY_TRIGGER_CONTEXT = TokenSet.create(
            PACKAGE_BODY, PACKAGE_SPEC, CREATE_TRIGGER
    );

    TokenSet TYPES = TokenSet.create(
            TYPE_NAME_REF,
            COLUMN_TYPE_REF,
            TABLE_TYPE_REF,
            DATATYPE
    );


    TokenSet PKG_LEVEL = TokenSet.create(
            SUBTYPE_DECL, VARIABLE_DECLARATION, CURSOR_DECLARATION,
            OBJECT_TYPE_DEF, TABLE_COLLECTION, RECORD_TYPE_DECL, REF_CURSOR, VARRAY_COLLECTION,
            PROCEDURE_BODY, PROCEDURE_SPEC, FUNCTION_BODY, FUNCTION_SPEC,
            RESTRICT_REF_PRAGMA, INTERFACE_PRAGMA, BUILTIN_PRAGMA, FIPSFLAG_PRAGMA,
            TIMESTAMPG_PRAGMA, EXCEPTION_PRAGMA,
            EXCEPTION_DECL
    );


    // Shortened search patterns
    TokenSet TS_COLUMN_DEF = TokenSet.create(
            COLUMN_DEF
    );

    TokenSet TS_EXPR_COLUMN = TokenSet.create(
            EXPR_COLUMN

    );

    TokenSet SQLPLUS_COMMANDS = TokenSet.create(
            SQLPLUS_EXIT, SQLPLUS_SPOOL,
            SQLPLUS_COLUMN, SQLPLUS_DEFINE, SQLPLUS_EXEC,
            SQLPLUS_QUIT, SQLPLUS_REPFOOTER, SQLPLUS_REPHEADER,
            SQLPLUS_RUNSCRIPT, SQLPLUS_SERVEROUTPUT,
            SQLPLUS_SET, SQLPLUS_SHOW, SQLPLUS_START,
            SQLPLUS_VARIABLE

    );


    // Types after resolving
    PlSqlElementType TABLE_REF_NOT_RESOLVED = new PlSqlElementType("TABLE_REF_NOT_RESOLVED", PLSqlTokenTypes.TABLE_REF_NOT_RESOLVED);
    PlSqlElementType VIEW_NAME_REF = new PlSqlElementType("VIEW_NAME_REF", PLSqlTokenTypes.VIEW_NAME_REF);
    PlSqlElementType TABLE_NAME_REF = new PlSqlElementType("TABLE_NAME_REF", PLSqlTokenTypes.TABLE_NAME_REF);
    PlSqlElementType GENERIC_REF = new PlSqlElementType("GENERIC_REF", PLSqlTokenTypes.GENERIC_REF);


    PlSqlElementType CALL_NOT_RESOLVED = new PlSqlElementType("CALL_NOT_RESOLVED", PLSqlTokenTypes.CALL_NOT_RESOLVED);
    PlSqlElementType BUILT_IT_FUNCTION_CALL = new PlSqlElementType("BUILT_IT_FUNCTION_CALL", PLSqlTokenTypes.BUILT_IT_FUNCTION_CALL);
    PlSqlElementType UDF_CALL = new PlSqlElementType("UDF_CALL", PLSqlTokenTypes.UDF_CALL);
    PlSqlElementType UDP_CALL = new PlSqlElementType("UDP_CALL", PLSqlTokenTypes.UDP_CALL);

}
