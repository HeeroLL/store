package com.zebone.btp.core.log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.AppenderSkeleton;
import org.apache.log4j.pattern.BridgePatternConverter;
import org.apache.log4j.spi.ErrorCode;
import org.apache.log4j.spi.LoggingEvent;

public class DBAppender extends AppenderSkeleton implements org.apache.log4j.Appender {

	private static final int THREAD_COUNT = 10;

	/**
	 * URL of the DB for default connection handling
	 */
	protected String databaseURL = "jdbc:odbc:myDB";

	/**
	 * User to connect as for default connection handling
	 */
	protected String databaseUser = "me";

	/**
	 * User to use for default connection handling
	 */
	protected String databasePassword = "mypassword";

	/**
	 * Connection used by default. The connection is opened the first time it is needed and then held open until the
	 * appender is closed (usually at garbage collection). This behavior is best modified by creating a sub-class and
	 * overriding the <code>getConnection</code> and <code>closeConnection</code> methods.
	 */
	protected Connection connection = null;

	/**
	 * Stores the string given to the pattern layout for conversion into a SQL statement, eg: insert into LogTable
	 * (Thread, Class, Message) values ("%t", "%c", "%m").
	 * 
	 * Be careful of quotes in your messages!
	 * 
	 * Also see PatternLayout.
	 */
	protected String sqlStatement = null;

	/**
	 * size of LoggingEvent buffer before writting to the database. Default is 1.
	 */
	protected int bufferSize = 1;

	/**
	 * ArrayList holding the buffer of Logging Events.
	 */
	protected ArrayList buffer;

	/**
	 * Helper object for clearing out the buffer
	 */
	protected ArrayList removes;

	private boolean locationInfo = false;

	/**
	 * 字段的个数
	 */
	private int fieldCount = 0;

	/**
	 * 参数数组
	 */
	private String[] paramArray;

	/**
	 * 
	 */
	private String tableName = "";
	private String fieldList = "";
	private String paramList = "";
	private String datasource = null;

//	private static ThreadPoolExecutor threadPoolExecutor;

	public DBAppender() {
		super();
		buffer = new ArrayList(bufferSize);
		removes = new ArrayList(bufferSize);
//		threadPoolExecutor = new ThreadPoolExecutor(THREAD_COUNT, Integer.MAX_VALUE, 3L, TimeUnit.MILLISECONDS,
//				new LinkedBlockingQueue<Runnable>());
//		threadPoolExecutor.allowCoreThreadTimeOut(true);
//		ThreadFactory threadFactory = new ThreadFactory(){
//			@Override
//			public Thread newThread(Runnable r) {
//				Thread t = new Thread(r);
//				t.setDaemon(false);
//				return t;
//			}
//			
//		};
//		threadPoolExecutor.setThreadFactory(threadFactory);
	}

	/**
	 * Adds the event to the buffer. When full the buffer is flushed.
	 */
	public void append(LoggingEvent event) {
		event.getNDC();
		event.getThreadName();
		// Get a copy of this thread's MDC.
		event.getMDCCopy();
		if (locationInfo) {
			event.getLocationInformation();
		}
		event.getRenderedMessage();
		event.getThrowableStrRep();
		buffer.add(event);

		if (buffer.size() >= bufferSize)
			flushBuffer();
	}

	private void execute(LoggingEvent event) {
		Connection con = null;
		PreparedStatement stmt = null;
		try {
			String sql = getSql();
			con = getConnection();
			stmt = con.prepareStatement(sql);
			for (int i = 0; i < fieldCount; i++) {
				String param = null;
				if (i > paramArray.length - 1) {
					param = "";
				} else {
					if (paramArray[i].trim().toLowerCase().equals("%uuid")) {
						UUID uuid = UUID.randomUUID();
						param = uuid.toString().replaceAll("-", "");
					} else {
						BridgePatternConverter converter = new BridgePatternConverter(paramArray[i]);
						
						StringBuffer sb = new StringBuffer();
						converter.format(sb, event);
						if (paramArray[i].trim().toLowerCase().equals("%m")) {
							String[] repArray = event.getThrowableStrRep();
							if(repArray!=null){
								sb.append("\n");
								for(String rep : repArray){
									sb.append(rep).append("\n");
								}
							}
						}
						param = sb.toString();
					}
				}
				stmt.setString((i + 1), param);
			}
			stmt.execute();
		} catch (Exception e) {
			errorHandler.error("执行insert语句错误", e, ErrorCode.GENERIC_FAILURE);
		} finally {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					errorHandler.error("关闭PreparedStatement出现错误", e, ErrorCode.GENERIC_FAILURE);
				}
			}
		}
	}

	/**
	 * 
	 * Override this to provide an alertnate method of getting connections (such as caching). One method to fix this is
	 * to open connections at the start of flushBuffer() and close them at the end. I use a connection pool outside of
	 * JDBCAppender which is accessed in an override of this method.
	 * */
	private class ExecuteHandler extends Thread {
		private LoggingEvent event;

		public ExecuteHandler(LoggingEvent event) {
			this.event = event;
		}

		public void run() {
			Connection con = null;
			PreparedStatement stmt = null;
			try {
				String sql = getSql();
				con = getConnection();
				stmt = con.prepareStatement(sql);
				for (int i = 0; i < fieldCount; i++) {
					String param = null;
					if (i > paramArray.length - 1) {
						param = "";
					} else {
						if (paramArray[i].trim().toLowerCase().equals("%uuid")) {
							UUID uuid = UUID.randomUUID();
							param = uuid.toString().replaceAll("-", "");
						} else {
							BridgePatternConverter converter = new BridgePatternConverter(paramArray[i]);
							StringBuffer sb = new StringBuffer();
							converter.format(sb, event);
							param = sb.toString();
						}
					}
					stmt.setString((i + 1), param);
				}
				stmt.execute();
			} catch (Exception e) {
				errorHandler.error("执行insert语句错误", e, ErrorCode.GENERIC_FAILURE);
			} finally {
				if (stmt != null) {
					try {
						stmt.close();
					} catch (SQLException e) {
						errorHandler.error("关闭PreparedStatement出现错误", e, ErrorCode.GENERIC_FAILURE);
					}
				}
			}
		}
	}

	/**
	 * Override this to link with your connection pooling system.
	 * 
	 * By default this creates a single connection which is held open until the object is garbage collected.
	 */
	protected Connection getConnection() throws SQLException {
		if (!DriverManager.getDrivers().hasMoreElements())
			setDriver("sun.jdbc.odbc.JdbcOdbcDriver");
		if (connection == null) {
			connection = DriverManager.getConnection(databaseURL, databaseUser, databasePassword);
		}
		return connection;
	}

	/**
	 * Closes the appender, flushing the buffer first then closing the default connection if it is open.
	 */
	public void close() {
		flushBuffer();
		try {
			if (connection != null && !connection.isClosed())
				connection.close();
		} catch (SQLException e) {
			errorHandler.error("Error closing connection", e, ErrorCode.GENERIC_FAILURE);
		}
		this.closed = true;
	}

	/**
	 * loops through the buffer of LoggingEvents, gets a sql string from getLogStatement() and sends it to execute().
	 * Errors are sent to the errorHandler.
	 * 
	 * If a statement fails the LoggingEvent stays in the buffer!
	 */
	public void flushBuffer() {
		// Do the actual logging
		removes.ensureCapacity(buffer.size());
		for (Iterator i = buffer.iterator(); i.hasNext();) {
			LoggingEvent logEvent = (LoggingEvent) i.next();
			try {
//				 ExecuteHandler executeHandler = new ExecuteHandler(logEvent);
				execute(logEvent);
//				threadPoolExecutor.execute(executeHandler);
			} catch (Exception e) {
				errorHandler.error("Failed to excute sql", e, ErrorCode.FLUSH_FAILURE);
			} finally {
				removes.add(logEvent);
			}
		}

		// remove from the buffer any events that were reported
		buffer.removeAll(removes);

		// clear the buffer of reported events
		removes.clear();
	}

	/** closes the appender before disposal */
	public void finalize() {
		close();
	}

	/**
	 * 返回insert语句
	 * 
	 * @throws Exception
	 */
	public String getSql() throws Exception {
		if (this.sqlStatement != null) {
			return this.sqlStatement;
		}
		if (this.fieldList == null || this.fieldList.length() == 0) {
			Exception e = new Exception("没有指定fieldList值");
			throw e;
		}
		if (this.tableName == null || this.tableName.equals("")) {
			Exception e = new Exception("没有指定tableName值");
			throw e;
		}
		String[] fieldArray = this.fieldList.split(",");
		this.fieldCount = fieldArray.length;
		StringBuilder sb = new StringBuilder();
		// valuse 部分
		StringBuilder sb2 = new StringBuilder();
		sb2.append("(");
		sb.append("INSERT INTO ");
		sb.append(this.tableName);
		sb.append("(");
		for (String field : fieldArray) {
			sb.append(field);
			sb.append(",");
			sb2.append("?,");
		}
		String pre = sb.substring(0, sb.length() - 1);
		String after = sb2.substring(0, sb2.length() - 1);
		pre += ") VALUES";
		after += ")";
		this.sqlStatement = pre + after;
		return sqlStatement;
	}

	public void setUser(String user) {
		databaseUser = user;
	}

	public void setURL(String url) {
		databaseURL = url;
	}

	public void setPassword(String password) {
		databasePassword = password;
	}

	public void setBufferSize(int newBufferSize) {
		bufferSize = newBufferSize;
		buffer.ensureCapacity(bufferSize);
		removes.ensureCapacity(bufferSize);
	}

	public String getUser() {
		return databaseUser;
	}

	public String getURL() {
		return databaseURL;
	}

	public String getPassword() {
		return databasePassword;
	}

	public int getBufferSize() {
		return bufferSize;
	}

	/**
	 * Ensures that the given driver class has been loaded for sql connection creation.
	 */
	public void setDriver(String driverClass) {
		try {
			Class.forName(driverClass);
		} catch (Exception e) {
			errorHandler.error("装载数据库驱动出现错误", e, ErrorCode.GENERIC_FAILURE);
		}
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getFieldList() {
		return fieldList;
	}

	public void setFieldList(String fieldList) {
		this.fieldList = fieldList;
	}

	public String getParamList() {
		return paramList;
	}

	public void setParamList(String paramList) {
		String[] paramArray = paramList.split(",");
		this.paramArray = paramArray;
		this.paramList = paramList;
	}

	public String getDatasource() {
		return datasource;
	}

	public void setDatasource(String datasource) {
		this.datasource = datasource;
	}

	@Override
	public boolean requiresLayout() {
		return false;
	}
}
