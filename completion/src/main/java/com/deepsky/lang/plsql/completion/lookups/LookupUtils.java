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

package com.deepsky.lang.plsql.completion.lookups;

import com.intellij.codeInsight.CodeInsightSettings;
import com.intellij.codeInsight.completion.CompletionPhase;
import com.intellij.codeInsight.completion.CompletionProgressIndicator;
import com.intellij.codeInsight.completion.CompletionType;
import com.intellij.codeInsight.completion.InsertionContext;
import com.intellij.codeInsight.completion.impl.CompletionServiceImpl;
import com.intellij.codeInsight.editorActions.CompletionAutoPopupHandler;
import com.intellij.ide.PowerSaveMode;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiDocumentManager;
import com.intellij.psi.PsiFile;

public class LookupUtils {

    public static void scheduleAutoPopup(final Editor editor, final InsertionContext context) {
        if (ApplicationManager.getApplication().isUnitTestMode() && !CompletionAutoPopupHandler.ourTestingAutopopup) {
            return;
        }

        if (!CodeInsightSettings.getInstance().AUTO_POPUP_COMPLETION_LOOKUP) {
            return;
        }
        if (PowerSaveMode.isEnabled()) {
            return;
        }

        if (!CompletionServiceImpl.isPhase(CompletionPhase.CommittingDocuments.class, CompletionPhase.NoCompletion.getClass())) {
            return;
        }

        final CompletionProgressIndicator currentCompletion = CompletionServiceImpl.getCompletionService().getCurrentCompletion();
        if (currentCompletion != null) {
            currentCompletion.closeAndFinish(true);
        }

        final CompletionPhase.CommittingDocuments phase = new CompletionPhase.CommittingDocuments(null, editor);
        CompletionServiceImpl.setCompletionPhase(phase);

        Runnable request = new Runnable() {
            @Override
            public void run() {
                if (context.getProject().isDisposed()) return;
                CompletionAutoPopupHandler.runLaterWithCommitted(context.getProject(), editor.getDocument(), new Runnable() {
                    @Override
                    public void run() {
                        if (phase.checkExpired()) return;

                        PsiFile file = PsiDocumentManager.getInstance(context.getProject()).getPsiFile(editor.getDocument());
                        //if (file != null && condition != null && !condition.value(file)) return;

                        CompletionAutoPopupHandler.invokeCompletion(CompletionType.BASIC, true, context.getProject(), editor, 0, false);
                    }
                });
            }
        };

        context.setLaterRunnable(request);
//        addRequest(request, CodeInsightSettings.getInstance().AUTO_LOOKUP_DELAY);
    }


    public static void scheduleAutoPopup2(final Editor editor, final Project project) {
        if (ApplicationManager.getApplication().isUnitTestMode() && !CompletionAutoPopupHandler.ourTestingAutopopup) {
            return;
        }

        if (!CodeInsightSettings.getInstance().AUTO_POPUP_COMPLETION_LOOKUP) {
            return;
        }
        if (PowerSaveMode.isEnabled()) {
            return;
        }

        if (!CompletionServiceImpl.isPhase(CompletionPhase.CommittingDocuments.class, CompletionPhase.NoCompletion.getClass())) {
            return;
        }

        final CompletionProgressIndicator currentCompletion = CompletionServiceImpl.getCompletionService().getCurrentCompletion();
        if (currentCompletion != null) {
            currentCompletion.closeAndFinish(true);
        }

        final CompletionPhase.CommittingDocuments phase = new CompletionPhase.CommittingDocuments(null, editor);
        CompletionServiceImpl.setCompletionPhase(phase);

        CompletionAutoPopupHandler.runLaterWithCommitted(project, editor.getDocument(), new Runnable() {
            @Override
            public void run() {
                if (phase.checkExpired()) return;
                if (project.isDisposed()) return;

                PsiFile file = PsiDocumentManager.getInstance(project).getPsiFile(editor.getDocument());
                //if (file != null && condition != null && !condition.value(file)) return;

                CompletionAutoPopupHandler.invokeCompletion(CompletionType.BASIC, true, project, editor, 0, false);
            }
        });
    }

    public static String calcLookupPrefix(String lookup, String content) {

        for (int i = lookup.length() - 1; i >= 0; i--) {
            int i2 = i;
            for (int j = content.length() - 1; j >= 0 && i2 >= 0; j--) {
                if (lookup.charAt(i2) == content.charAt(j)) {
                    // Check boundary
                    if (i2 == 0) {
                        return (j == 0 || content.charAt(j - 1) == ' ' || content.charAt(j - 1) == '\t'
                                || content.charAt(j - 1) == '\n') ? lookup.substring(0, i + 1) : "";
                    }

                } else if (content.charAt(j) == ' ' || content.charAt(j) == '\n' || content.charAt(j) == '\t') {
                    // Skip whitespaces
                    continue;
                } else {
                    break;
                }

                i2--;
            }
        }
        return "";
    }

    public static String adoptPrefix(String lookupString, String text, int endOffset, String prefix) {
        int i = endOffset;
        for (int cnt = lookupString.length() + 20; i > 0 && cnt > 0; cnt--, i--) ;

        String _prefix = LookupUtils.calcLookupPrefix(lookupString, text.substring(i, endOffset));
        if (prefix.startsWith(_prefix)) {
            return prefix.substring(_prefix.length());
        }
        return prefix;
    }


    public static int calcPrefixSize(String lookupString, String text, int endOffset, String prefix) {
        int i = endOffset;
        for (int cnt = lookupString.length() + 20; i > 0 && cnt > 0; cnt--, i--) ;

        String _prefix = LookupUtils.calcLookupPrefix(lookupString, text.substring(i, endOffset));
        if (prefix.startsWith(_prefix)) {
            return prefix.length() - _prefix.length();
        }
        return prefix.length();
    }
}
