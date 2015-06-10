package com.zebone.dnode.etl.adapter.test;

import java.sql.Clob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.zebone.dnode.etl.adapter.service.AdapterService;
import com.zebone.dnode.etl.adapter.service.AdapterServiceImpl;
import com.zebone.dnode.etl.adapter.vo.DocConf;

public class TestTableToDoc {
	
	public static void main(String[] args) throws SQLException {
		ClassPathXmlApplicationContext cx = new ClassPathXmlApplicationContext("classpath:com/zebone/dnode/etl/adapter/config/ApplicationContext.xml");
		String SQL = "select t.id_,t.doc_xml,t.doc_version,t.doc_type_code,t.doc_name from p_doc_conf t where t.id_ = '95358514a56b477e93b6bfd33f6fb3a1' ";
		Connection conn = getConnection();
		PreparedStatement ps = conn.prepareStatement(SQL);
		ResultSet rs = ps.executeQuery();
		DocConf docConf = null;
		if(rs.next()){
			docConf = new DocConf();
			docConf.setDocName(rs.getString("doc_name"));
			docConf.setDocTypeCode(rs.getString("doc_type_code"));
			docConf.setId(rs.getString("id_"));
			docConf.setDocVersion(rs.getString("doc_version"));
			Clob clob = rs.getClob("doc_xml");
			String ss = clob.getSubString((long)1, (int)clob.length());
			docConf.setDocXml(ss);
		}
		AdapterService service = cx.getBean("adapterService", AdapterServiceImpl.class);
		Map<String, String> map = new HashMap<String, String>();
		map.put("orgCode", "100");
		service.tableToDocByDocXml(docConf,map);
	}
	
	static Connection getConnection(){
		Connection conn = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@192.168.1.6:1521:ZB", "DIP_LR", "DIP_LR");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}
}
