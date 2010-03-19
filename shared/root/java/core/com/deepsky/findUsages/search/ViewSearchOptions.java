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

package com.deepsky.findUsages.search;

import com.deepsky.findUsages.persistence.SqlSearchParameters;
import com.deepsky.lang.plsql.psi.ddl.CreateView;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;

public class ViewSearchOptions  implements GenericSearchOptions {

    CreateView view;
    public ViewSearchOptions(@NotNull CreateView view) {
        this.view = view;
    }

    @NotNull
    public PsiElement getTarget() {
        return view;
    }

    public String getSearchTitle() {
        return "View " + view.getViewName().toUpperCase();
    }

    @NotNull
    public SearchOption[] getSearchOptions() {
        SqlSearchParameters searchParams = SqlSearchParameters.getInstance(view.getProject());
        SearchOption[] out = new SearchOption[2];
        out[0] = new SearchOptionImpl("Usages", searchParams.isViewUsages, 0);
        out[1] = new SearchOptionImpl("Usages of Columns", searchParams.isUsagesOfColumns, 1);
        return out;
    }

    @NotNull
    public String getTargetNode() {
        return "View";
    }

    @NotNull
    public String getTargetPresentableText() {
        return view.getViewName().toUpperCase();
    }

    @NotNull
    public String getPresentableSearchParameters() {
        return "Usages of View " + view.getViewName().toUpperCase();
    }

    class SearchOptionImpl implements SearchOption {
        String opName;
        boolean enabled;
        int tag;

        public SearchOptionImpl(String opName, boolean enabled, int tag){
            this.opName = opName;
            this.enabled = enabled;
            this.tag = tag;
        }

        public boolean isEnabled() {
            return enabled;
        }

        public void setEnabled(boolean value) {
            enabled = value;
            SqlSearchParameters searchParams = SqlSearchParameters.getInstance(view.getProject());
            if(tag == 0){
                searchParams.isViewUsages = value;
            } else if(tag == 1){
                searchParams.isUsagesOfColumns = value;
            }
        }

        public String getOptionName() {
            return opName;
        }
    }

}
