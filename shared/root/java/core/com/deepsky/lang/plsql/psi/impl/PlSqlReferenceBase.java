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

import com.intellij.lang.ASTNode;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiReference;
import com.intellij.util.IncorrectOperationException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public abstract class PlSqlReferenceBase extends PlSqlElementBase implements PsiReference {

    PsiElement cachedReference = null;
    long modifStamp = -1;
    PsiFile psiFile;


    public PlSqlReferenceBase(ASTNode astNode) {
        super(astNode);
        psiFile = this.getContainingFile();
        assert psiFile != null;
    }

    public PsiElement getElement() {
        return this;
    }

    public TextRange getRangeInElement() {
        return TextRange.from(0, getTextLength());
    }

    @Nullable
    public PsiElement resolve() {
        __ensure_resolver_available__();
        try {
            return resolveInternal();
        }catch(Throwable e){
            return null;
        }

/*
        if (modifStamp != psiFile.getModificationStamp()) {
            __ensure_resolver_available__();
            try {
                cachedReference = resolveInternal();
            }catch(Throwable e){
                int hh = 0;
            }
            modifStamp = psiFile.getModificationStamp();
        }
        return cachedReference;
*/
    }

    protected abstract PsiElement resolveInternal();

    public String getCanonicalText() {
        return null;
    }

    public PsiElement handleElementRename(String s) throws IncorrectOperationException {
        return null;
    }

    public PsiElement bindToElement(@NotNull PsiElement psiElement) throws IncorrectOperationException {
        return null;
    }

    public boolean isReferenceTo(PsiElement psiElement) {
        return false;
    }


    // todo -- subject to remove
    @NotNull
    public Object[] getVariants() {
//        String columnPrefix = stripText(this.getText()).trim();
        return new Object[0]; //getVariants(columnPrefix);
    }


    @NotNull
    public abstract Object[] getVariants(String text);

    public boolean isSoft() {
        return false;
    }

    public PsiReference getReference() {
        return this;
    }


//    static final String IntellijIdeaRulezzz = "IntellijIdeaRulezzz";
//    public static String stripText(String text) {
//        int idx = text.indexOf(Constants.COMPL_IDENTIFIER); //IntellijIdeaRulezzz);
//        if (idx >= 0) {
//            if (idx == 0) {
//                return "";
//            } else {
//                return text.substring(0, idx);
//            }
//
//        } else {
//            return text;
//        }
//    }


}
