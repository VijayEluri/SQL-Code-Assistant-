package com.deepsky.lang.common;

import com.intellij.lang.Language;

public class PlSqlLanguage extends Language {
    public static final String ID = "PlSql";

    public PlSqlLanguage() {
        super(ID, "text/plsql", "application/plsql");
    }
}
