package com.zebone.pubsub.server.service;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;
import javax.jms.Connection;
import javax.jms.JMSException;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import com.zebone.mq.activemq.ActiveMqMsgUtil;
import com.zebone.mq.activemq.GenActiveMqConPool;
import com.zebone.pubsub.server.pojo.DcDataJobParameter;
import com.zebone.webservice.cxf.log.DocQuery;

@Service("dcPubSubService")
public class DcPubSubServiceImpl implements PubSubService<DcDataJobParameter> {
	
	private static final Log log = LogFactory.getLog(DcPubSubServiceImpl.class);
	
	@Resource
	private DocQuery docQuery;
	
	
	@Resource(name="jdbcTemplateWRC")
	private JdbcTemplate jdbcTemplateWRC;
	
	@Resource(name="jdbcTemplateDIP")
	private JdbcTemplate jdbcTemplateDIP;

	@Resource
	private GenActiveMqConPool genActiveMqConPool;

	@Override
	public void doPub(DcDataJobParameter ddjp) {
		// TODO Auto-generated method stub
		try {
			/** 历史数据 **/
			String sql ="SELECT DISTINCT(EMPI_ID) FROM R_REGISTER_MAIN WHERE DOC_ORG = ?";
			String orgCode = ddjp.getOrgCode();
			String beginDate = ddjp.getBeginDate();
			String endDate = ddjp.getEndDate();
			String docTypeCode = ddjp.getDocTypeCode();
			List<String> empiIdList = jdbcTemplateWRC.queryForList(sql,new Object[]{orgCode}, String.class);
			if (empiIdList != null && empiIdList.size() > 0) {
				for (int i = 0; i < empiIdList.size(); i++) {
					String empi = empiIdList.get(i);
					if(StringUtils.isNotEmpty(empi)){
						sql = "SELECT A.EMPI_ID,A.DOC_NO FROM R_REGISTER_MAIN A LEFT JOIN R_EMPI_INFO B ON A.ID_=B.MAIN_ID WHERE A.EMPI_ID= ? AND A.DOC_TYPE_CODE=? AND A.DOC_ORG != ? AND B.ACTIVE_TIME >= ? AND B.ACTIVE_TIME <= ?";
						log.info(sql + " " + empi +" "+docTypeCode +" "+orgCode +" "+beginDate +" "+endDate);
						List<GetDocPara> getDocParaList = jdbcTemplateWRC.query(sql, new GetDocParaMapper(), new Object[] {empi,docTypeCode,orgCode,beginDate,endDate});
						if (getDocParaList != null && getDocParaList.size() > 0) {
							for (GetDocPara docPara : getDocParaList) {
								StringBuilder queryDocParaBuilder = new StringBuilder("<param><flag>1</flag><code>");
								queryDocParaBuilder.append(docPara.getDocNo());
								queryDocParaBuilder.append("</code><empiId>");
								queryDocParaBuilder.append(docPara.getEmpiId());
								queryDocParaBuilder.append("</empiId></param>");
								String doc = docQuery.DocumentRepository_RetrieveDocumentSet(queryDocParaBuilder.toString());
								if (StringUtils.isNotBlank(doc)) {
									sendMsg(orgCode, doc);
								}
							}
						}
					}		
				}
			}
			jdbcTemplateDIP.update("UPDATE P_SUBSCRIBE_SERVICE SET IS_HISTORY_RUN = 2 WHERE ID_ = ?", ddjp.getPubSubserviceId());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error(e.getMessage(), e);
		}

	}

    private void sendMsg(String orgCode,String doc){
         try {
			Connection con = genActiveMqConPool.getCon();
			ActiveMqMsgUtil.sendMsg(con, "pubsub."+orgCode, doc);
			genActiveMqConPool.releaseCon(con);
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			log.error(e.getMessage(),e);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error(e.getMessage(),e);
		}
    }
    
    
	public static class  GetDocPara{
		
		private String empiId;
		
		private String docNo;

		public String getEmpiId() {
			return empiId;
		}

		public void setEmpiId(String empiId) {
			this.empiId = empiId;
		}

		public String getDocNo() {
			return docNo;
		}

		public void setDocNo(String docNo) {
			this.docNo = docNo;
		}
		
	}
	
	private  static final class GetDocParaMapper implements RowMapper<GetDocPara>{

		@Override
		public GetDocPara mapRow(ResultSet rs, int rowNum) throws SQLException {
			// TODO Auto-generated method stub
			GetDocPara gdp = new GetDocPara();
			gdp.setDocNo(rs.getString("DOC_NO"));
			gdp.setEmpiId(rs.getString("EMPI_ID"));
			return gdp;
		}
		
	}

	

}
