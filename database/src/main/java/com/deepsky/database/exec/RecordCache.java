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

package com.deepsky.database.exec;

import com.deepsky.database.DBException;

import java.sql.Connection;

public interface RecordCache {

    Connection getConnection();
    int getColumnCount();

    // columnIndex starts with 1, 2, ...
    Class getColumnClass(int columnIndex);
    boolean isColumnEditable(int columnIndex);
    boolean isColumnNotNull(int columnIndex);
    String getColumnName(int columnIndex);
    String getColumnSpecification(int columnIndex);

    int getFetchedRowCount();

    /**
     * How much time was taken on last fetch
     * @return - time in milliseconds
     */
    long timeSpentOnFetch();

    boolean allRowsFetched();

    // columnIndex starts with 1, 2, ...
    Object getValueAt(int rowIndex, int columnIndex) throws DBException;
    void setValueAt(int rowIndex, int columnIndex, Object value) throws DBException;

    void sortByColumn(int columnId, int direction, boolean stickyTag) throws DBException;

    /**
     * Refresh result set with current order settings
     * @throws com.deepsky.database.DBException - unable to execute SQL query
     */
    void refresh() throws DBException;

    /**
     * Close record set and dispose listeners
     */
    void close();

    /**
     * Cancel last request
     */
    void cancel();

    void addListener(TableResizeListener listener);
    void removeListener(TableResizeListener listener);

}

