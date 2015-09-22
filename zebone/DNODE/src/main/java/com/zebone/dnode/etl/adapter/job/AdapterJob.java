package com.zebone.dnode.etl.adapter.job;

import java.sql.Clob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.zebone.dnode.etl.adapter.service.AdapterService;
import com.zebone.dnode.etl.adapter.vo.DocConf;
import com.zebone.taskscheduling.quartz.SequenceJob;

/**
 * 
 * @author 陈阵 
 * @date 2014-2-26 下午3:35:26
 */
public class AdapterJob extends SequenceJob implements Job {
	
    private static final Log log = LogFactory.getLog(AdapterJob.class);
 
	
	private AdapterService adapterService;
	
	
    private String orgCode;
    
	public void setAdapterService(AdapterService adapterService) {
		this.adapterService = adapterService;
	}


    
	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}



	@Override
	public void execute(JobExecutionContext jec) throws JobExecutionException {
		// TODO Auto-generated method stub
		log.info("############ 适配任务开始 ##################");
		Connection conn = null;
		DocConf docConf = null;
		try {
			Map<String,String> para = new HashMap<String, String>();
			para.put("orgCode", orgCode);
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@192.168.1.6:1521:ZB", "DIP_LR", "DIP_LR");
			String SQL = "select t.id_,t.doc_xml,t.doc_version,t.doc_type_code,t.doc_name from p_doc_conf t where t.id_ = '95358514a56b477e93b6bfd33f6fb3a1'";
			PreparedStatement ps = conn.prepareStatement(SQL);
			ResultSet rs = ps.executeQuery();
			docConf = null;
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
			adapterService.tableToDocByDocXml(docConf,para);
			log.info("############ 适配任务结束  ##################");
			execteChained(jec);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
