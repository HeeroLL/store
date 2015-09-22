package com.zebone.mq.activemq;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;

import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.PooledObjectFactory;
import org.apache.commons.pool2.impl.DefaultPooledObject;


public class ActiveMqConFactory implements PooledObjectFactory<Connection> {
	
    private ConnectionFactory connectionFactory;
    

	public ActiveMqConFactory(ConnectionFactory connectionFactory) {
		super();
		this.connectionFactory = connectionFactory;
	}
    
	@Override
	public void activateObject(PooledObject<Connection> pool) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void destroyObject(PooledObject<Connection> pool) throws Exception {
		// TODO Auto-generated method stub
		Connection con = pool.getObject();
		con.stop();
		con.close();
	}

	@Override
	public PooledObject<Connection> makeObject() throws Exception {
		// TODO Auto-generated method stub
		Connection con = connectionFactory.createConnection();
		con.start();
		return new DefaultPooledObject<Connection>(con);

	}

	@Override
	public void passivateObject(PooledObject<Connection> pool) throws Exception {
		// TODO Auto-generated method stub
	}

	@Override
	public boolean validateObject(PooledObject<Connection> pool) {
		// TODO Auto-generated method stub
		Connection con = pool.getObject();
		if(con == null){
			return false;
		}
		return true;
	}
    
}
