package com.zebone.dip.release;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

import com.zebone.dip.release.jdbc.DictSyncOperation;
import com.zebone.dip.release.jdbc.JdbcOperation;

/**
 * 数据库操作测试
 * @author YinCM
 *
 */
public class JdbcTest {
	
	@Test
	public void testUpdateTable(){
		System.out.println("aaaa");
		Connection sourceConn=null;
		try {
			sourceConn = JdbcOperation.getConnection("oracle.jdbc.driver.OracleDriver", "jdbc:oracle:thin:@192.168.90.6:1521:ZB", "dsp", "dsp");
		} catch (Exception e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		Connection targetConn=null;
		try {
			targetConn = JdbcOperation.getConnection("oracle.jdbc.driver.OracleDriver", "jdbc:oracle:thin:@192.168.90.6:1521:ZB", "empi", "empi");
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String[] str = new String[]{"P_DICTIONARY_TYPE"};
		try {
			JdbcOperation.updateTargetDBTablesCommon(sourceConn, targetConn, str,"25-6-2010 15:57:30");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testUpdateDictByType(){
		Connection sourceConn=null;
		try {
			sourceConn = JdbcOperation.getConnection("oracle.jdbc.driver.OracleDriver", "jdbc:oracle:thin:@192.168.90.6:1521:ZB", "dsp", "dsp");
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Connection targetConn=null;
		try {
			targetConn = JdbcOperation.getConnection("oracle.jdbc.driver.OracleDriver", "jdbc:oracle:thin:@192.168.90.6:1521:ZB", "empi", "empi");
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String[] str = new String[]{"1bbec33c553c49aea38de399c1076a77"};
		try {
			DictSyncOperation.updateTargetDBTablesCommon(sourceConn, targetConn, str,"2013-9-25 5:57:30");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testDateTime(){
		Date date = new Date();
		String nowString = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(date);
		System.out.println(nowString);
	}
}
