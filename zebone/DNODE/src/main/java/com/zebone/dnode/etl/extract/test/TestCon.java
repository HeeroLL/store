package com.zebone.dnode.etl.extract.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//import org.apache.commons.dbutils.DbUtils;
//import org.apache.commons.dbutils.QueryRunner;

public class TestCon {

	/**
	 * @param args
	 * @author 陈阵 
	 * @date 2013-11-20 下午2:36:31 
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*
		try {
			QueryRunner run = new QueryRunner();
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@192.168.1.15:1521:ZB","DIP_LR","DIP_LR");
			Object[][] pa = new Object[2][1];
			pa[0][0] = "81206704854888804256";
			pa[1][0] ="25256279423839296349";
			System.out.println("#############");
			run.batch(con,"DELETE FROM TBL_PSON_HEALTH_RECORD WHERE HR_NO = ?", pa);
			System.out.println("TTTTTT");
			DbUtils.close(con);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
	}

}
