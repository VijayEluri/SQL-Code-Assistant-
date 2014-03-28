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

package com.deepsky.lang.plsql.completion.processors;

import com.deepsky.lang.parser.plsql.PlSqlElementTypes;
import com.deepsky.lang.plsql.completion.SyntaxTreePath;
import com.deepsky.lang.plsql.completion.VariantsProvider;
import com.deepsky.lang.plsql.completion.lookups.GenericLookupElement;
import com.deepsky.lang.plsql.completion.lookups.GroupByLookupElement;
import com.deepsky.lang.plsql.completion.lookups.KeywordLookupElement;
import com.deepsky.lang.plsql.completion.lookups.OrderByLookupElement;
import com.deepsky.lang.plsql.completion.lookups.dml.DeleteLookupElement;
import com.deepsky.lang.plsql.completion.lookups.dml.InsertLookupElement;
import com.deepsky.lang.plsql.completion.lookups.dml.SelectLookupElement;
import com.deepsky.lang.plsql.completion.lookups.dml.UpdateLookupElement;
import com.deepsky.lang.plsql.psi.*;
import com.deepsky.lang.plsql.struct.Type;
import com.deepsky.lang.validation.ValidationException;
import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;

import java.util.*;


@SyntaxTreePath("/..1$SelectStatement")
public class SelectStmtProcessor extends CompletionBase {


    @SyntaxTreePath("/#SELECT ..2#EXPR_COLUMN/#PARENTHESIZED_EXPR/ ..#VAR_REF//#C_MARKER")
    public void process$SelectParenExpr() {
        // TODO - implement me
    }

    // select * from (select a, <caret>)
    @SyntaxTreePath("/..#TABLE_REFERENCE_LIST_FROM/..#FROM_SUBQUERY// ..#ERROR_TOKEN_A/#SELECT ..#EXPR_COLUMN #COMMA #ERROR_TOKEN_A/#C_MARKER")
    public void process$SelectFromSubqueryError() {
        // TODO - implement me
    }

    @SyntaxTreePath("/#SELECT ..2#EXPR_COLUMN/#VAR_REF/..3$NameFragmentRef/#C_MARKER")
    public void process$SelectVarRef(C_Context ctx, SelectStatement select, ASTNode expr, NameFragmentRef nameRef) {
        VariantsProvider provider = ctx.getProvider();
        final NameFragmentRef prev = nameRef.getPrevFragment();
        final String prevText = prev != null ? prev.getText() : null;

        provider.collectColumnVariants(select, prevText);

        final List<LookupElement> variants = new ArrayList<LookupElement>();
        variants.addAll(provider.takeCollectedLookups());

        // Collect Sequence
        variants.addAll(provider.collectSequenceVariants(prevText, ctx.getLookup()));

        for (LookupElement elem : variants) {
            ctx.addElement(elem);
        }
    }


    @SyntaxTreePath("/..1#EXPR_COLUMN/#SUBQUERY_EXPR//..2$SelectStatement/..#EXPR_COLUMN/#FUNCTION_CALL//..#CALL_ARGUMENT/..#VAR_REF/..3$NameFragmentRef/#C_MARKER")
    public void process$SelectSubqueryExpr(C_Context ctx, SelectStatement select0, ASTNode expr, SelectStatement select, NameFragmentRef nameRef) {
        VariantsProvider provider = ctx.getProvider();
        final NameFragmentRef prev = nameRef.getPrevFragment();
        final String prevText = prev != null ? prev.getText() : null;
        provider.collectColumnVariants(select, prevText);

        PsiElement parent = expr.getTreeParent().getPsi();
        if(parent instanceof SelectStatement){
            provider.collectColumnVariants((SelectStatement) parent, prevText);
        }

        final List<LookupElement> variants = new ArrayList<LookupElement>();
        variants.addAll(provider.takeCollectedLookups());

        // Collect Sequence
        variants.addAll(provider.collectSequenceVariants(prevText, ctx.getLookup()));

        for (LookupElement elem : variants) {
            ctx.addElement(elem);
        }
    }

    @SyntaxTreePath("/#SELECT ..2#ERROR_TOKEN_A/#SUBQUERY_EXPR//#OPEN_PAREN #SELECT #C_MARKER")
    public void process$SelectSubquery() {
        // TODO - implement me
    }

    @SyntaxTreePath("/#SELECT ..2#ERROR_TOKEN_A/#SUBQUERY_EXPR//#OPEN_PAREN #SELECT #C_MARKER")
    public void process$SelectSubquery2() {
        // TODO - implement me
    }

    @SyntaxTreePath("/#SELECT ..2#ERROR_TOKEN_A/#SUBQUERY_EXPR//#OPEN_PAREN #SELECT #C_MARKER")
    public void process$SelectFromSubquery() {
        // TODO - implement me
    }

    @SyntaxTreePath("/ ..#EXPR_COLUMN/ ..#ALIAS_NAME/#ALIAS_IDENT/2#C_MARKER")
    public void process$SelectColumnAlias() {
        // TODO - implement me
    }

    @SyntaxTreePath("/ ..#TABLE_REFERENCE_LIST_FROM/..#FROM_SUBQUERY/#SUBQUERY/#OPEN_PAREN #ERROR_TOKEN_A/2#C_MARKER")
    public void process$SelectFromSubquery2() {
        // TODO - implement me
    }

//    @SyntaxTreePath("//..#SUBQUERY_CONDITION/..#SUBQUERY/..2$SelectStatement/..#EXPR_COLUMN//#VAR_REF/..3$NameFragmentRef/#C_MARKER")
//    public void process$SelectWhereSubqueryCondition(C_Context ctx, SelectStatement s0, SelectStatement select, NameFragmentRef ref) {
//        collectColumns(ctx, select, ref);
//    }

/*
    @SyntaxTreePath("/..#WHERE_CONDITION//..2#VAR_REF/..3$NameFragmentRef/#C_MARKER")
    public void process$SelectWhere1(C_Context ctx, SelectStatement select, ASTNode expr, NameFragmentRef nameRef) {
        collectColumns(ctx, select, nameRef);

        if(expr.getChildren(null).length == 1){
            ASTNode parent = expr.getTreeParent();
            if(parent.getElementType() == PlSqlElementTypes.WHERE_CONDITION){
                ctx.addElement(KeywordLookupElement.create("exists"));
            } else if(parent.getElementType() == PlSqlElementTypes.LOGICAL_EXPR){
                ctx.addElement(KeywordLookupElement.create("exists"));
            }
        }

        if(nameRef.getPrevFragment() == null && expr.getTreeParent().getElementType() == PlSqlElementTypes.RELATION_CONDITION){
            // Possible case: column1 < sysdate/systimestamp/dbtimezone/current_timestamp
            ctx.addElement(KeywordLookupElement.create("sysdate"));
            ctx.addElement(KeywordLookupElement.create("systimestamp"));
            ctx.addElement(KeywordLookupElement.create("dbtimezone"));
            ctx.addElement(KeywordLookupElement.create("current_timestamp"));
        }
    }
*/

//    @SyntaxTreePath("/..#WHERE_CONDITION//..#RELATION_CONDITION/..2#VAR_REF/..3$NameFragmentRef/#C_MARKER")
//    public void process$SelectWhere2(C_Context ctx, SelectStatement select, ASTNode expr, NameFragmentRef nameRef) {
//        collectColumns(ctx, select, nameRef);
//    }

//    @SyntaxTreePath("/..#WHERE_CONDITION//..#LIKE_CONDITION/..2#VAR_REF/..3$NameFragmentRef/#C_MARKER")
//    public void process$SelectWhere3(C_Context ctx, SelectStatement select, ASTNode expr, NameFragmentRef nameRef) {
//        collectColumns(ctx, select, nameRef);
//    }


/*
    @SyntaxTreePath("/..#WHERE_CONDITION/..#EXISTS_EXPR//..2$SelectStatement/..#WHERE_CONDITION/..#VAR_REF/..3$NameFragmentRef/#C_MARKER")
    public void process$SelectExistsExpr(C_Context ctx, SelectStatement select, SelectStatement subquery, NameFragmentRef nameRef) {
        VariantsProvider provider = ctx.getProvider();
        final NameFragmentRef prev = nameRef.getPrevFragment();
        final String prevText = prev != null ? prev.getText() : null;

        provider.collectColumnVariants(select, prevText);
        provider.collectColumnVariants(subquery, prevText);

        final List<LookupElement> variants = new ArrayList<LookupElement>();
        variants.addAll(provider.takeCollectedLookups());

        for (LookupElement elem : variants) {
            ctx.addElement(elem);
        }
    }
*/

    @SyntaxTreePath("/..#EXPR_COLUMN/#LAG_FUNCTION/..#SPEC_CALL_ARGUMENT_LIST/..#QUERY_PARTITION_CLAUSE/..#VAR_REF/..2$NameFragmentRef/#C_MARKER")
    public void process$LagFunc(C_Context ctx, SelectStatement select, NameFragmentRef nameRef) {
        collectColumns(ctx, select, nameRef);
    }

    @SyntaxTreePath("/..#EXPR_COLUMN/#LAG_FUNCTION/..#SPEC_CALL_ARGUMENT_LIST/..#CALL_ARGUMENT/..#VAR_REF/..2$NameFragmentRef/#C_MARKER")
    public void process$LagFuncArg(C_Context ctx, SelectStatement select, NameFragmentRef nameRef) {
        collectColumns(ctx, select, nameRef);
    }

    @SyntaxTreePath("/..#EXPR_COLUMN/#LEAD_FUNCTION/..#SPEC_CALL_ARGUMENT_LIST/..#QUERY_PARTITION_CLAUSE/..#VAR_REF/..2$NameFragmentRef/#C_MARKER")
    public void process$LeadFunc(C_Context ctx, SelectStatement select, NameFragmentRef nameRef) {
        collectColumns(ctx, select, nameRef);
    }

    // ANSI FROM STATEMENT
    @SyntaxTreePath("/ ..#TABLE_REFERENCE_LIST_FROM/..#ANSI_JOIN_TAB_SPEC/..#ANSI_JOIN_TAB_CONDITION//..#VAR_REF/..2$NameFragmentRef/#C_MARKER")
    public void process$AnsiFrom(C_Context ctx, SelectStatement select, NameFragmentRef nameRef) {
        collectColumns(ctx, select, nameRef);
    }

}
