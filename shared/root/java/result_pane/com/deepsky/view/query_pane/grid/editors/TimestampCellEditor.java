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

package com.deepsky.view.query_pane.grid.editors;

import com.deepsky.view.query_pane.ConversionException;
import com.deepsky.view.query_pane.DataAccessor;
import com.deepsky.view.query_pane.ValueConvertor;
import com.deepsky.view.query_pane.converters.TIMESTAMP_Convertor;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class TimestampCellEditor extends AbstractCellEditor1 {

    Object value;
    ValueConvertor convertor;

    public TimestampCellEditor(Font font, ValueConvertor convertor) {
        super(font, false);
        this.convertor = convertor;
        setHorizontalAlignment(JLabel.LEFT);
 //       setHorizontalAlignment(JTextField.RIGHT);
    }

    public boolean stopCellEditing() {
        String s = (String) super.getCellEditorValue();
        // Here we are dealing with the case where a user
        // has deleted the string value in a cell, possibly
        // after a failed validation. Return null, so that
        // they have the option to replace the value with
        // null or use escape to restore the original.
        // For Strings, return "" for backward compatibility.
        if ("".equals(s)) {
            value = null;
            return super.stopCellEditing();
        }

        try {
//            Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(s);
//            java.sql.Timestamp sqlDate = new java.sql.Timestamp(date.getTime());
//            value = new TIMESTAMP(sqlDate);
            value = convertor.stringToValue(s);
        } catch (Throwable e) {
            setInputErrored();
            setToolTip("Entered timestamp value is not valid");
            return false;
        }

        return super.stopCellEditing();
    }

    public Component getTableCellEditorComponent(JTable table, Object value,
                                                 boolean isSelected,
                                                 int row, int column) {

        this.value = null;
        String value1 = null;
        try {
            value1 = convertor.valueToString(value);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return super.getTableCellEditorComponent(table, value1, isSelected, row, column);
    }

    @Override
    protected DataAccessor getDataAccessor() {
        String value = super.getTextValue();
        return new DataAccessor(value, convertor) {
            public void loadFromString(String text) throws ConversionException {
                super.loadFromString(text);
                TimestampCellEditor.this.setValue(text);
            }

            public boolean isReadOnly() {
                return false;
            }
        };
    }

    public Object getCellEditorValue() {
        return value;
    }

}
