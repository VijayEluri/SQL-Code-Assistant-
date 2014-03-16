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

package com.deepsky.lang.plsql.completion.lookups;

import com.intellij.codeInsight.completion.InsertHandler;
import com.intellij.codeInsight.completion.InsertionContext;
import com.intellij.codeInsight.completion.PrioritizedLookupElement;
import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.codeInsight.lookup.LookupElementDecorator;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.Editor;
import com.intellij.psi.PsiDocumentManager;



public class KeywordLookupElement  <T extends LookupElement> extends LookupElementDecorator<T> {
    protected KeywordLookupElement(T delegate) {
        super(delegate);
    }

    public static KeywordLookupElement create(final String name){
        LookupElement e = LookupElementBuilder.create(name)
                .withCaseSensitivity(false)
                .withBoldness(true)
                .withInsertHandler(new InsertHandler<LookupElement>() {
                    @Override
                    public void handleInsert(InsertionContext context, LookupElement item) {
                        final Editor editor = context.getEditor();
                        String prefix = name + " ";
                        editor.getDocument().replaceString(context.getStartOffset(), context.getTailOffset(), prefix);
                        editor.getCaretModel().moveToOffset(context.getTailOffset());
                        final Document document = editor.getDocument();
                        PsiDocumentManager.getInstance(context.getProject()).commitDocument(document);
                        LookupUtils.scheduleAutoPopup(editor, context);
                    }
                });

        return new KeywordLookupElement<LookupElement>(
                PrioritizedLookupElement.withGrouping(e, 3));
    }

    public static KeywordLookupElement create(final String name, boolean isBold){
        LookupElement e = LookupElementBuilder.create(name)
                .withCaseSensitivity(false)
                .withBoldness(isBold)
                .withInsertHandler(new InsertHandler<LookupElement>() {
                    @Override
                    public void handleInsert(InsertionContext context, LookupElement item) {
                        final Editor editor = context.getEditor();
                        String prefix = name + " ";
                        editor.getDocument().replaceString(context.getStartOffset(), context.getTailOffset(), prefix);
                        editor.getCaretModel().moveToOffset(context.getTailOffset());
                        final Document document = editor.getDocument();
                        PsiDocumentManager.getInstance(context.getProject()).commitDocument(document);
                        LookupUtils.scheduleAutoPopup(editor, context);
                    }
                });


        return new KeywordLookupElement<LookupElement>(
                PrioritizedLookupElement.withGrouping(e, 3));
    }

    public static KeywordLookupElement create(final String name, boolean isBold, InsertHandler<LookupElement> insertHandler ){

        LookupElement e;
        if(insertHandler != null){
            e = LookupElementBuilder.create(name)
                    .withCaseSensitivity(false)
                    .withBoldness(isBold)
                    .withInsertHandler(insertHandler);
        } else {
            e = LookupElementBuilder.create(name)
                    .withCaseSensitivity(false)
                    .withBoldness(isBold);
        }

        return new KeywordLookupElement<LookupElement>(
                PrioritizedLookupElement.withGrouping(e, 3));
    }
}
