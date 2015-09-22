package com.zebone.mq.activemq;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.commons.pool2.PooledObjectFactory;
import org.apache.commons.pool2.impl.GenericObjectPool;

public class GenActiveMqConPool {

	private String brokerURL;

	private String userName;

	private String password;

	private int minIdle;

	private GenericObjectPool<Connection> pool;

	public void initPool() throws Exception {
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(
				brokerURL);
		PooledObjectFactory<Connection> factory = new ActiveMqConFactory(
				connectionFactory);

		pool = new GenericObjectPool<Connection>(factory);
		pool.setMinIdle(minIdle);
		for (int i = 0; i < minIdle; i++) {
			pool.addObject();
		}
	}

	public Connection getCon() throws Exception {
		Connection con = pool.borrowObject();
		return con;
	}
	
	public void releaseCon(Connection con){
		pool.returnObject(con);
	}

	public String getBrokerURL() {
		return brokerURL;
	}

	public void setBrokerURL(String brokerURL) {
		this.brokerURL = brokerURL;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getMinIdle() {
		return minIdle;
	}

	public void setMinIdle(int minIdle) {
		this.minIdle = minIdle;
	}
    
	
}
