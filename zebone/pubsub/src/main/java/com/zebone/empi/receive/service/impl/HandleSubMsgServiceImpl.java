package com.zebone.empi.receive.service.impl;

import java.io.File;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.iq80.leveldb.DB;
import org.iq80.leveldb.impl.Iq80DBFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Service;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.naming.NoNameCoder;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.zebone.empi.receive.pojo.DocSubMsg;
import com.zebone.empi.receive.pojo.DocSubMsgObj;
import com.zebone.empi.receive.service.HandleSubMsgService;
import com.zebone.util.UUIDUtil;

/**
 * 处理文档订阅信息服务实现类
 * 
 * @author 杨英
 * @version 2014-8-13 上午8:41
 */
@Service("handleSubMsgService")
public class HandleSubMsgServiceImpl implements HandleSubMsgService {

	private static final Log log = LogFactory.getLog(HandleSubMsgServiceImpl.class);

	private static DB db = null;

	@Resource(name = "jdbcTemplateCli")
	private JdbcTemplate jdbcTemplate;

	@Override
	public void handleSubMsg(String xmlMsg) {
		try {
			getLevelDb();
			String uuid = getUUID(xmlMsg);
			String hasget = Iq80DBFactory.asString(db.get(Iq80DBFactory.bytes(uuid)));
			//log.info("数据是否推送:"+ hasget == null?"没有推送":"已经推送");
			if (hasget == null) {
				XStream xs = new XStream(new DomDriver("UTF-8",new NoNameCoder()));
				xs.processAnnotations(DocSubMsg.class);
				DocSubMsg docSubMsg = (DocSubMsg) xs.fromXML(xmlMsg);

				if (docSubMsg != null) {
					DocSubMsgObj docSubMsgObj = new DocSubMsgObj();
					BeanUtils.copyProperties(docSubMsgObj, docSubMsg);
					docSubMsgObj.setId(UUIDUtil.getUuid());
					jdbcTemplate.update("INSERT INTO DOC_SUB_MSG (ID_,EMPI,DOC_ORG,DOC_TYPE_CODE,DOC_NO,ACTIVE_TIME,DOC_VERSION,DOC_XML) VALUES(?,?,?,?,?,?,?,?)",
									new DocMsgObjPSS(docSubMsgObj));
					db.put(Iq80DBFactory.bytes(uuid), Iq80DBFactory.bytes("1"));
				}
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}

	}

	private final class DocMsgObjPSS implements PreparedStatementSetter {

		private DocSubMsgObj docSubMsgObj;

		public DocMsgObjPSS(DocSubMsgObj docSubMsgObj) {
			super();
			this.docSubMsgObj = docSubMsgObj;
		}

		@Override
		public void setValues(PreparedStatement paramPreparedStatement)
				throws SQLException {
			paramPreparedStatement.setString(1, docSubMsgObj.getId());
			paramPreparedStatement.setString(2, docSubMsgObj.getEmpi());
			paramPreparedStatement.setString(3, docSubMsgObj.getDocOrg());
			paramPreparedStatement.setString(4, docSubMsgObj.getDocType());
			paramPreparedStatement.setString(5, docSubMsgObj.getDocNo());
			paramPreparedStatement.setString(6, docSubMsgObj.getActiveTime());
			paramPreparedStatement.setString(7, docSubMsgObj.getDocVersion());
			paramPreparedStatement.setString(8, docSubMsgObj.getDocXml());
		}
	}

	private String getUUID(String docXml) {
		String uuid = null;
		Pattern pattern = Pattern
				.compile("\\{&quot;uuid&quot;:&quot;([0-9a-z]{8}[0-9a-z]{4}[0-9a-z]{4}[0-9a-z]{4}[0-9a-z]{12})&quot;\\}");
		Matcher m = pattern.matcher(docXml);
		while (m.find()) {
			uuid = m.group(1);
		}
		return uuid;
	}

	private void getLevelDb() {
		try {
			if (db == null) {
				org.iq80.leveldb.Options options = new org.iq80.leveldb.Options();
				options.createIfMissing(true);
				db = Iq80DBFactory.factory
						.open(new File("C:\\zb\\db"), options);
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
	}

}
