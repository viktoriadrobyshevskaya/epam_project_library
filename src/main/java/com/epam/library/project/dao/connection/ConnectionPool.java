package com.epam.library.project.dao.connection;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;

public final class ConnectionPool {
	private static final ConnectionPool instance;
	private static final String DB_CONN_PROP_FILE = "db_conn.properties";
	private static final Logger logger = LogManager.getLogger(ConnectionPool.class);
	
	static {
		ConnectionPool tmp = null;
		try {
			tmp = new ConnectionPool();
		} catch (ConnectionPoolException e) {
			logger.error("Can't initialize connection pool.", e);
		}
		instance = tmp;
	}
	   
	private BlockingQueue<Connection> connectionQueue;
	private BlockingQueue<Connection> givenConnectionQueue;
	
	private String driverName;
	private String url;
	private String user;
	private String password;
	private int poolSize;
	
	private ConnectionPool() throws ConnectionPoolException {

		driverName = getProperties().getProperty(DBParameter.DB_DRIVER);
		url = getProperties().getProperty(DBParameter.DB_URL);
		user = getProperties().getProperty(DBParameter.DB_USER);
		password = getProperties().getProperty(DBParameter.DB_PASSWORD);
	
		try {
			poolSize = Integer.parseInt(getProperties().getProperty(DBParameter.DB_POOL_SIZE));
		} catch (NumberFormatException e) {
			poolSize = 5;
			logger.warn("Can't read data from properties file. Connections count set to " + poolSize + ".", e);
		}
		
		initPoolData();

	}
	
	public static ConnectionPool getInstance() {
		return instance;
	}
	
	public Properties getProperties() throws ConnectionPoolException {
        Properties properties = new Properties();
        String propFileName = DB_CONN_PROP_FILE;
        
        InputStream io = getClass().getClassLoader().getResourceAsStream(propFileName);
        
        try {
			properties.load(io);
			io.close();
		} catch (IOException e) {
			throw new ConnectionPoolException(e);
		}
		return properties;
	}

	public void initPoolData() throws ConnectionPoolException {
		
		try {
			Class.forName(driverName);
			
			givenConnectionQueue = new ArrayBlockingQueue<Connection>(poolSize);
			connectionQueue = new ArrayBlockingQueue<Connection>(poolSize);
			
			for(int i = 0; i < poolSize; i++) {
				Connection connection = DriverManager.getConnection(url, user, password);
				PooledConnection poledConnection = new PooledConnection(connection);
				connectionQueue.add(poledConnection);
			}
		} catch (SQLException e) {
			throw new ConnectionPoolException("SQLException in connection pool.", e);
		} catch (ClassNotFoundException e) {
			throw new ConnectionPoolException("Can't find database driver class.", e);
		}
	}
	
	public void dispose() {
		clearConnectionQueue();
	}
	
	public void clearConnectionQueue() {
		try {
			closeConnectionQueue(givenConnectionQueue);
			closeConnectionQueue(connectionQueue);
		} catch (SQLException e) {
			logger.error("Error closing the connection.", e);
		}
	}
	
	public void closeConnectionQueue(Connection con, Statement st, ResultSet rs) {
		try {
			if (con != null) {
				con.close();
			}
		} catch (SQLException e) {
			logger.error("Connection isn't return to the pool.", e);
		}
		try {
			if (rs != null) {
				rs.close();
			}
		} catch (SQLException e) {
			logger.error("Result set isn't closed.", e);
		}
		try {
			if (st != null) {
				st.close();
			}
		} catch (SQLException e) {
			logger.error("Statement isn't closed.", e);
		}
	}
	
	public void closeConnectionQueue(Connection con, Statement st) {
		try {
			if (con != null) {
				con.close();
			}
		} catch (SQLException e) {
			logger.error("Connection isn't return to the pool.", e);
		}
		try {
			if (st != null) {
				st.close();
			}
		} catch (SQLException e) {
			logger.error("Statement isn't closed.", e);
		}
	}
	
	public void closeConnectionQueue(Connection con) {
		try {
			if (con != null) {
				con.close();
			}
		} catch (SQLException e) {
			logger.error("Connection isn't return to the pool.", e);
		}
	}
	
	public void closeConnectionQueue(BlockingQueue<Connection> queue) throws SQLException {
		Connection connection;
		while ((connection = queue.poll()) != null) {
			if (!connection.getAutoCommit()) {
				connection.commit();
			}
			((PooledConnection) connection).reallyClose();
		}
	}
	
	public Connection takeConnection() throws ConnectionPoolException {
		Connection connection = null;
		try {
			connection = connectionQueue.take();
			givenConnectionQueue.add(connection);
		} catch (InterruptedException e) {
			throw new ConnectionPoolException("Error connecting ot the data source.", e);
		}
		return connection;
	}
	
	private class PooledConnection implements Connection {
		private Connection connection;
		
		public PooledConnection(Connection connection) throws SQLException {
			this.connection = connection;
			this.connection.setAutoCommit(true);
		}
		
		public void reallyClose() throws SQLException {
			connection.close();
		}

		@Override
		public <T> T unwrap(Class<T> iface) throws SQLException {
			return connection.unwrap(iface);
		}

		@Override
		public boolean isWrapperFor(Class<?> iface) throws SQLException {
			return connection.isWrapperFor(iface);
		}

		@Override
		public Statement createStatement() throws SQLException {
			return connection.createStatement();
		}

		@Override
		public PreparedStatement prepareStatement(String sql) throws SQLException {
			return connection.prepareStatement(sql);
		}

		@Override
		public CallableStatement prepareCall(String sql) throws SQLException {
			return connection.prepareCall(sql);
		}

		@Override
		public String nativeSQL(String sql) throws SQLException {
			return connection.nativeSQL(sql);
		}

		@Override
		public void setAutoCommit(boolean autoCommit) throws SQLException {
			connection.setAutoCommit(autoCommit);
		}

		@Override
		public boolean getAutoCommit() throws SQLException {
			return connection.getAutoCommit();
		}

		@Override
		public void commit() throws SQLException {
			connection.commit();
			
		}

		@Override
		public void rollback() throws SQLException {
			connection.rollback();
		}

		@Override
		public void close() throws SQLException {
			if (connection.isClosed()) {
				throw new SQLException("Attemping to close closed connection.");
			}
			if (connection.isReadOnly()) {
				connection.setReadOnly(false);
			}
			if (!givenConnectionQueue.remove(this)) {
				throw new SQLException("Error deleting connection from the giwen away connection pool.");
			}
			if (!connectionQueue.offer(this)) {
				throw new SQLException("Error allocating connection in the connection pool.");
			}
		}

		@Override
		public boolean isClosed() throws SQLException {
			return connection.isClosed();
		}

		@Override
		public DatabaseMetaData getMetaData() throws SQLException {
			return connection.getMetaData();
		}

		@Override
		public void setReadOnly(boolean readOnly) throws SQLException {
			connection.setReadOnly(readOnly);
		}

		@Override
		public boolean isReadOnly() throws SQLException {
			return connection.isReadOnly();
		}

		@Override
		public void setCatalog(String catalog) throws SQLException {
			connection.setCatalog(catalog);
		}

		@Override
		public String getCatalog() throws SQLException {
			return connection.getCatalog();
		}

		@Override
		public void setTransactionIsolation(int level) throws SQLException {
			connection.setTransactionIsolation(level);
		}

		@Override
		public int getTransactionIsolation() throws SQLException {
			return connection.getTransactionIsolation();
		}

		@Override
		public SQLWarning getWarnings() throws SQLException {
			return connection.getWarnings();
		}

		@Override
		public void clearWarnings() throws SQLException {
			connection.clearWarnings();
		}

		@Override
		public Statement createStatement(int resultSetType, int resultSetConcurrency) throws SQLException {
			return connection.createStatement(resultSetType, resultSetConcurrency);
		}

		@Override
		public PreparedStatement prepareStatement(String sql, int resultSetType, int resultSetConcurrency)
				throws SQLException {
			return connection.prepareStatement(sql, resultSetType, resultSetConcurrency);
		}

		@Override
		public CallableStatement prepareCall(String sql, int resultSetType, int resultSetConcurrency) throws SQLException {
			return connection.prepareCall(sql, resultSetType, resultSetConcurrency);
		}

		@Override
		public Map<String, Class<?>> getTypeMap() throws SQLException {
			return connection.getTypeMap();
		}

		@Override
		public void setTypeMap(Map<String, Class<?>> map) throws SQLException {
			connection.setTypeMap(map);
		}

		@Override
		public void setHoldability(int holdability) throws SQLException {
			connection.setHoldability(holdability);
		}

		@Override
		public int getHoldability() throws SQLException {
			return connection.getHoldability();
		}

		@Override
		public Savepoint setSavepoint() throws SQLException {
			return connection.setSavepoint();
		}

		@Override
		public Savepoint setSavepoint(String name) throws SQLException {
			return connection.setSavepoint(name);
		}

		@Override
		public void rollback(Savepoint savepoint) throws SQLException {
			connection.rollback(savepoint);
		}

		@Override
		public void releaseSavepoint(Savepoint savepoint) throws SQLException {
			connection.releaseSavepoint(savepoint);
		}

		@Override
		public Statement createStatement(int resultSetType, int resultSetConcurrency, int resultSetHoldability)
				throws SQLException {
			return connection.createStatement(resultSetType, resultSetConcurrency, resultSetHoldability);
		}

		@Override
		public PreparedStatement prepareStatement(String sql, int resultSetType, int resultSetConcurrency,
				int resultSetHoldability) throws SQLException {
			return connection.prepareStatement(sql, resultSetType, resultSetConcurrency, resultSetHoldability);
		}

		@Override
		public CallableStatement prepareCall(String sql, int resultSetType, int resultSetConcurrency,
				int resultSetHoldability) throws SQLException {
			return connection.prepareCall(sql, resultSetType, resultSetConcurrency, resultSetHoldability);
		}

		@Override
		public PreparedStatement prepareStatement(String sql, int autoGeneratedKeys) throws SQLException {
			return connection.prepareStatement(sql, autoGeneratedKeys);
		}

		@Override
		public PreparedStatement prepareStatement(String sql, int[] columnIndexes) throws SQLException {
			return connection.prepareStatement(sql, columnIndexes);
		}

		@Override
		public PreparedStatement prepareStatement(String sql, String[] columnNames) throws SQLException {
			return connection.prepareStatement(sql, columnNames);
		}

		@Override
		public Clob createClob() throws SQLException {
			return connection.createClob();
		}

		@Override
		public Blob createBlob() throws SQLException {
			return connection.createBlob();
		}

		@Override
		public NClob createNClob() throws SQLException {
			return connection.createNClob();
		}

		@Override
		public SQLXML createSQLXML() throws SQLException {
			return connection.createSQLXML();
		}

		@Override
		public boolean isValid(int timeout) throws SQLException {
			return connection.isValid(timeout);
		}

		@Override
		public void setClientInfo(String name, String value) throws SQLClientInfoException {
			connection.setClientInfo(name, value);
		}

		@Override
		public void setClientInfo(Properties properties) throws SQLClientInfoException {
			connection.setClientInfo(properties);
		}

		@Override
		public String getClientInfo(String name) throws SQLException {
			return connection.getClientInfo(name);
		}

		@Override
		public Properties getClientInfo() throws SQLException {
			return connection.getClientInfo();
		}

		@Override
		public Array createArrayOf(String typeName, Object[] elements) throws SQLException {
			return connection.createArrayOf(typeName, elements);
		}

		@Override
		public Struct createStruct(String typeName, Object[] attributes) throws SQLException {
			return connection.createStruct(typeName, attributes);
		}

		@Override
		public void setSchema(String schema) throws SQLException {
			connection.setSchema(schema);
		}

		@Override
		public String getSchema() throws SQLException {
			return connection.getSchema();
		}

		@Override
		public void abort(Executor executor) throws SQLException {
			connection.abort(executor);
		}

		@Override
		public void setNetworkTimeout(Executor executor, int milliseconds) throws SQLException {
			connection.setNetworkTimeout(executor, milliseconds);
		}

		@Override
		public int getNetworkTimeout() throws SQLException {
			return connection.getNetworkTimeout();
		}

	}

}
