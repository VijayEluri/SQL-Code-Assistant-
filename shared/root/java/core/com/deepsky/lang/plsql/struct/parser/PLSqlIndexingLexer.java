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
 *     3. The name of the author may not be used to endorse or promote
 *       products derived from this software without specific prior written
 *       permission from the author.
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

package com.deepsky.lang.plsql.struct.parser;

import antlr.CharBuffer;
import antlr.LexerSharedInputState;
import antlr.Token;
import antlr.TokenStreamException;
import com.deepsky.generated.plsql.PLSqlTokenTypes;
import com.deepsky.integration.PLSqlLexerEx;

import java.io.ByteArrayInputStream;
import java.io.Reader;

public class PLSqlIndexingLexer extends PLSqlLexerEx implements PLSqlFilteredLexer {

    boolean keyword = false;
    WordProcessor processor = null;

    public PLSqlIndexingLexer(){
        super(new ByteArrayInputStream(new byte[0]));
    }

    public PLSqlIndexingLexer(Reader in){
        super(in);
    }

    public void setWordProcessor(WordProcessor processor) {
        this.processor = processor;
    }

    public Token nextToken() throws TokenStreamException {
        Token t = super.nextToken();
        if (t != null) {
            switch(t.getType()){
                case PLSqlTokenTypes.WS:
                case PLSqlTokenTypes.LF:
                case PLSqlTokenTypes.SL_COMMENT:
                case PLSqlTokenTypes.ML_COMMENT:
                case PLSqlTokenTypes.BAD_ML_COMMENT:
                    return nextToken();
                case PLSqlTokenTypes.IDENTIFIER:
                    if(processor != null){
                        processor.process(t.getText());
                    }
                    break;
                default:
                    if(keyword && processor != null){
                        processor.process(t.getText());
                    }
                    break;
            }
        }

        keyword = false;
        return t;
    }

    public void reload(Reader in) {
        inputState = new LexerSharedInputState(new CharBuffer(in));
    }

    public int testLiteralsTable(int ttype) {
        int ret = super.testLiteralsTable(ttype);
        keyword = ret != ttype;
        return ret;
    }

    public int testLiteralsTable(String text, int ttype) {
        int ret = super.testLiteralsTable(text, ttype);
        keyword = ret != ttype;
        return ret;
    }

}
