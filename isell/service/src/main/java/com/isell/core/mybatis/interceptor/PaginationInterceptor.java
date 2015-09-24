package com.isell.core.mybatis.interceptor;

import java.sql.Connection;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.session.RowBounds;

import com.isell.core.mybatis.dialect.Dialect;
import com.isell.core.mybatis.dialect.MySql5Dialect;
import com.isell.core.mybatis.dialect.OracleDialect;



@Intercepts( { @Signature(type = StatementHandler.class, method = "prepare", args = { Connection.class }) })
public class PaginationInterceptor implements Interceptor {
	private Properties properties;
	// 日志对象
	private static Log log = LogFactory.getLog(PaginationInterceptor.class);

	public Object intercept(Invocation invocation) throws Throwable {
		StatementHandler statementHandler = (StatementHandler) invocation.getTarget();
		MetaObject metaStatementHandler = MetaObject.forObject(statementHandler);

		RowBounds rowBounds = (RowBounds) metaStatementHandler.getValue("delegate.rowBounds");
		if (rowBounds == null || rowBounds == RowBounds.DEFAULT) {
			return invocation.proceed();
		}

		String originalSql = (String) metaStatementHandler.getValue("delegate.boundSql.sql");
		Dialect.Type databaseType = null;
		try {
			databaseType = Dialect.Type.valueOf(properties.getProperty("dialect").toUpperCase());
		} catch (Exception e) {
			// ignore
			log.error("在获取数据库方言时出现错误！不能够按照物理分页方式进行查询，mybatis将采用默认的方式进行分页。", e);
			return invocation.proceed();
		}
		if (databaseType == null) {
			// throw new
			// RuntimeException("the value of the dialect property in configuration.xml is not defined : "
			// + configuration.getVariables().getProperty("dialect"));
			log.error("没有定义数据库方言！不能够按照物理分页方式进行查询，mybatis将采用默认的方式进行分页。");
			return invocation.proceed();
		}
		Dialect dialect = null;
		switch (databaseType) {
		case ORACLE:
			dialect = new OracleDialect();
			break;
		case MYSQL:
			dialect = new MySql5Dialect();
			break;

		}
		metaStatementHandler.setValue("delegate.boundSql.sql", dialect.getLimitString(originalSql, rowBounds.getOffset(), rowBounds
				.getLimit()));
		metaStatementHandler.setValue("delegate.rowBounds.offset", RowBounds.NO_ROW_OFFSET);
		metaStatementHandler.setValue("delegate.rowBounds.limit", RowBounds.NO_ROW_LIMIT);
		if (log.isDebugEnabled()) {
			BoundSql boundSql = statementHandler.getBoundSql();
			log.debug("生成分页SQL :\n " + boundSql.getSql());
		}
		return invocation.proceed();
	}

	public Object plugin(Object target) {
		return Plugin.wrap(target, this);
	}

	public void setProperties(Properties properties) {
		this.properties = properties;
	}

}
