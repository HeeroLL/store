package com.zebone.dnode.engine.clean;

import java.beans.PropertyVetoException;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.zebone.dnode.engine.clean.pojo.DsObj;


/**
 * 注册数据源
 * @author cz
 *
 */
public class DSRegister {

	private static Map<String, DataSource> dsMap = new HashMap<String, DataSource>();
	
	public DataSource register(DsObj dsObj) throws PropertyVetoException{
		String userName = dsObj.getUserName();
		String passWord = dsObj.getPassword();
		String driverClass = dsObj.getDriver();
		String jdbcUrl = dsObj.getUrl();
		
		String mapKey = jdbcUrl + "_" + userName + "_" + passWord;
		
		DataSource ds = null;
		if(dsMap.containsKey(mapKey)){
			ds = getDataSource(mapKey);
		}else{
			ComboPooledDataSource cpds = new ComboPooledDataSource();
			cpds.setDriverClass(driverClass);
			cpds.setJdbcUrl(jdbcUrl);
			cpds.setUser(userName);
			cpds.setPassword(passWord);
			dsMap.put(mapKey, cpds);
			ds = cpds;
		}
		return ds;
	}
	
	private DataSource getDataSource(String mapKey){
		return dsMap.get(mapKey);
	}
	

}
