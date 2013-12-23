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

package com.deepsky.findUsages.windex;

import antlr.TokenStreamException;
import com.deepsky.generated.plsql.PLSqlTokenTypes;
import com.deepsky.lang.plsql.struct.index.BuildIndexException;
import com.deepsky.lang.plsql.struct.parser.PLSqlIndexingLexer;
import com.deepsky.lang.plsql.struct.parser.WordProcessor;

import java.io.Reader;
import java.io.StringReader;
import java.util.HashSet;
import java.util.Set;

public class SqlScriptIndexer {

    public static String buildIndex(String text) throws BuildIndexException {
        try {
            Reader in = new StringReader(text);
            PLSqlIndexingLexer lexer = new PLSqlIndexingLexer(in);

            WordIndexBuilder indexer = new WordIndexBuilder();
            lexer.setWordProcessor(indexer);

            while(lexer.nextToken().getType() != PLSqlTokenTypes.EOF){
                // idle
            }

            return indexer.getIndex();

        } catch (TokenStreamException e) {
            throw new BuildIndexException();
        }
    }


    static class WordIndexBuilder implements WordProcessor {

        Set<String> words = new HashSet<String>();
        int wordsNum = 0;

        public void process(String text) {
            words.add(text.toLowerCase());
            wordsNum++;
        }

        public String getIndex() {
            StringBuilder sb = new StringBuilder();
            for (String s : words) {
                sb.append(s).append(".");
            }

            return "." + sb.toString();
        }
    }
    
}
