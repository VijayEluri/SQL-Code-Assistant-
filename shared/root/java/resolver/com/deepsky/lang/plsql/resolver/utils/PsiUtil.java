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

package com.deepsky.lang.plsql.resolver.utils;

import com.deepsky.lang.common.PlSqlTokenTypes;
import com.deepsky.lang.plsql.resolver.ContextPathProvider;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.TokenSet;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class PsiUtil {

    public static ASTNode findTreePrevForTypes(@NotNull ASTNode node, @Nullable TokenSet tokens) {
        if (tokens == null) {
            // Pick up any previous node
            return node.getTreePrev();
        } else {
            ASTNode prev = node.getTreePrev();
            if (prev != null) {
                if (tokens.contains(prev.getElementType())) {
                    return prev;
                } else {
                    return findTreePrevForTypes(prev, tokens);
                }
            } else {
                return null;
            }
        }
    }

    public static ASTNode findTreePrevForType(@NotNull ASTNode node, @NotNull IElementType type) {
        ASTNode prev = node.getTreePrev();
        if (prev != null) {
            if (type == prev.getElementType()) {
                return prev;
            } else {
                return findTreePrevForType(prev, type);
            }
        } else {
            return null;
        }
    }

    public static String[] findCtxPathAmongChilds(ASTNode root, int ctxType, String name) {
        List<String> out = new ArrayList<String>();
        for (ASTNode node : root.getChildren(null)) {
            if (node instanceof ContextPathProvider) {
                String ctxPath = ((ContextPathProvider) node).getCtxPath1().getPath();
                ContextPathUtil.CtxPathParser parser = new ContextPathUtil.CtxPathParser(ctxPath);
                if (parser.lastCtxName().equalsIgnoreCase(name) && parser.extractLastCtxType() == ctxType) {
                    out.add(ctxPath);
                }
            }
        }
        return out.toArray(new String[out.size()]);
    }

    /**
     * Iterate over siblings except WS and comments
     *
     * @param e
     * @param visitor
     */
    public static void iterateOverSiblings(PsiElement e, SiblingVisitor visitor) {
        if (e != null) {
            PsiElement prev = e.getPrevSibling();
            while (prev != null) {
                if (PlSqlTokenTypes.WS_TOKENS.contains(prev.getNode().getElementType())) {
                    // ignore WS, LF, comments
                } else if (!visitor.visit(prev)) {
                    break;
                }
                prev = prev.getPrevSibling();
            }
        }
    }

    public static interface SiblingVisitor {
        boolean visit(PsiElement e);
    }
}
