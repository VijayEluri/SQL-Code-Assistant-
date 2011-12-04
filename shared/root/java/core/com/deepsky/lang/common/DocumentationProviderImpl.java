package com.deepsky.lang.common;

import com.deepsky.lang.plsql.psi.ExecNameRef;
import com.deepsky.lang.plsql.psi.PlSqlElement;
import com.deepsky.lang.plsql.psi.utils.Formatter;
import com.deepsky.lang.plsql.resolver.ResolveDescriptor;
import com.deepsky.lang.plsql.resolver.helpers.SysFuncResolveHelper;
import com.deepsky.lang.plsql.resolver.utils.ArgumentSpec;
import com.deepsky.lang.plsql.struct.Type;
import com.intellij.lang.documentation.DocumentationProvider;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiManager;
import org.jetbrains.annotations.Nullable;

public class DocumentationProviderImpl implements DocumentationProvider {

    @Nullable
    public String getQuickNavigateInfo(PsiElement psiElement) {
        String out = null;
        if (psiElement instanceof PlSqlElement) {
            try {
                PlSqlElement plsql_el = (PlSqlElement) psiElement;
                out = plsql_el.getQuickNavigateInfo();
            } catch (Throwable e) {
                // ignore error    
            }
        }
        return out;
    }

    public String getQuickNavigateInfo(PsiElement element, PsiElement originalElement) {
        // Build QuickNavigationInfo for System Functions only
        if(originalElement instanceof ExecNameRef){
            ResolveDescriptor rdesc = ((ExecNameRef) originalElement).resolveLight();
            if(rdesc instanceof SysFuncResolveHelper){
                ArgumentSpec[] args = ((SysFuncResolveHelper) rdesc).getArgumentSpecification();
                String name = ((SysFuncResolveHelper) rdesc).getName();
                Type t = ((SysFuncResolveHelper) rdesc).getType();
                return Formatter.formatFunctionSignature(name, args, t, 100);
            }
        }
        return null;
    }

    @Nullable
    public java.util.List<java.lang.String> getUrlFor(PsiElement psiElement, PsiElement psiElement1) {
        return null;
    }

    @Nullable
    public String generateDoc(PsiElement psiElement, PsiElement psiElement1) {
        return null;
    }

    @Nullable
    public PsiElement getDocumentationElementForLookupItem(PsiManager psiManager, Object o, PsiElement psiElement) {
        return null;
    }

    @Nullable
    public PsiElement getDocumentationElementForLink(PsiManager psiManager, String s, PsiElement psiElement) {
        return null;
    }
}
