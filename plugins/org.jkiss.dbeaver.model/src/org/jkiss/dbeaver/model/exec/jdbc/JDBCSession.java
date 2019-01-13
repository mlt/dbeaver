/*
 * DBeaver - Universal Database Manager
 * Copyright (C) 2010-2019 Serge Rider (serge@jkiss.org)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jkiss.dbeaver.model.exec.jdbc;

import org.jkiss.code.NotNull;
import org.jkiss.code.Nullable;
import org.jkiss.dbeaver.model.exec.DBCException;
import org.jkiss.dbeaver.model.exec.DBCExecutionContext;
import org.jkiss.dbeaver.model.exec.DBCSession;
import org.jkiss.dbeaver.model.exec.DBCStatementType;
import org.jkiss.dbeaver.model.impl.jdbc.JDBCDataSource;
import org.jkiss.dbeaver.model.impl.jdbc.JDBCExecutionContext;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.concurrent.Executor;

/**
 * JDBC session
 */
public interface JDBCSession extends DBCSession, Connection {

    @NotNull
    JDBCDataSource getDataSource();

    Connection getOriginal()
        throws SQLException;

    JDBCExecutionContext getExecutionContext();

    @Override
    @NotNull
    JDBCStatement prepareStatement(
        @NotNull DBCStatementType type,
        @NotNull String query,
        boolean scrollable,
        boolean updatable,
        boolean returnGeneratedKeys) throws DBCException;

    @Override
    @NotNull
    JDBCDatabaseMetaData getMetaData()
        throws SQLException;

    @Override
    @NotNull
    JDBCStatement createStatement()
        throws SQLException;

    @Override
    @NotNull
    JDBCStatement createStatement(int resultSetType, int resultSetConcurrency, int resultSetHoldability)
        throws SQLException;

    @Override
    @NotNull
    JDBCPreparedStatement prepareStatement(String sql)
        throws SQLException;

    @Override
    @NotNull
    JDBCCallableStatement prepareCall(String sql)
        throws SQLException;

    @Override
    @NotNull
    JDBCPreparedStatement prepareStatement(String sql, int resultSetType, int resultSetConcurrency)
        throws SQLException;

    @Override
    @NotNull
    JDBCCallableStatement prepareCall(String sql, int resultSetType, int resultSetConcurrency)
        throws SQLException;

    @Override
    @NotNull
    JDBCPreparedStatement prepareStatement(String sql, int resultSetType, int resultSetConcurrency, int resultSetHoldability)
        throws SQLException;

    @Override
    @NotNull
    JDBCCallableStatement prepareCall(String sql, int resultSetType, int resultSetConcurrency, int resultSetHoldability)
        throws SQLException;

    @Override
    @NotNull
    JDBCPreparedStatement prepareStatement(String sql, int autoGeneratedKeys)
        throws SQLException;

    @Override
    @NotNull
    JDBCPreparedStatement prepareStatement(String sql, int[] columnIndexes)
        throws SQLException;

    @Override
    @NotNull
    JDBCPreparedStatement prepareStatement(String sql, String[] columnNames)
        throws SQLException;

    @Override
    void close();

    ////////////////////////////////////////////////////////////////////////////
    // JDBC 1.7

    @Nullable
    @Override
    String getSchema() throws SQLException;

    @Override
    void setSchema(String schema) throws SQLException;

    @Override
    void abort(Executor executor) throws SQLException;

    @Override
    void setNetworkTimeout(Executor executor, int milliseconds) throws SQLException;

    @Override
    int getNetworkTimeout() throws SQLException;
}
