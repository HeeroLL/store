package com.zebone.dnode.engine.analyze.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Node;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.zebone.dip.log.service.DocExtInfoService;
import com.zebone.dip.log.service.LogServiceClient;
import com.zebone.dip.log.vo.DocExtendInfo;
import com.zebone.dip.log.vo.Error;
import com.zebone.dip.log.vo.Syslog;
import com.zebone.dnode.engine.analyze.AnalyzeException;
import com.zebone.dnode.engine.analyze.dao.DocAnalyzeLogMapper;
import com.zebone.dnode.engine.analyze.dao.DocAnalyzedMapper;
import com.zebone.dnode.engine.analyze.dao.MappingTableMapper;
import com.zebone.dnode.engine.analyze.vo.AnalyzeDataInfo;
import com.zebone.dnode.engine.analyze.vo.DocAnalyzeLog;
import com.zebone.dnode.engine.analyze.vo.DocumentInfo;
import com.zebone.dnode.engine.analyze.vo.MappingInfo;
import com.zebone.dnode.engine.analyze.vo.ParesTableLog;
import com.zebone.util.DateUtil;
import com.zebone.util.UUIDUtil;

/**
 * 文档解析服务的实现类
 * 
 * @author songjunjie
 * @version 2013-8-8 下午03:29:45
 */
@Service
public class DocumentAnalyzeServiceImpl implements DocumentAnalyzeService {
	private Log log = LogFactory.getLog(DocumentAnalyzeServiceImpl.class);

	/**
	 * 文档唯一标识的xpath路径。
	 */
	@Value("${doc_no_xpath}")
	private   String DOC_NO_XPATH ;

	/**
	 * 文档类型的xpath路径
	 */
	@Value("${doc_type_code_xpath}")
	private   String DOC_TYPE_CODE_XPATH ;
	
	/**
	 * 文档中的文档机构编码的xpath路径
	 */
	@Value("${doc_organ_xpath}")
	private   String DOC_ORGAN_XPATH ;
	
	//业务库中文档编号字段的名字
	@Value("${doc_no_column}")
	private String DOC_NO_COLUMN;
    //业务库中文档来源机构字段的名字
    @Value("${source_org_column}")
    private String SOURCE_ORG_COLUMN;
	//EMPI编号字段的名字
	@Value("${empi_no_column}")
	private String EMPI_NO_COLUMN;
	//创建时间字段的名字
	@Value("${create_time_column}")
	private String CREATE_TIME_COLUMN;
	//文档版本的xpath路径
	@Value("${doc_version_code_xpath}")
	private String DOC_VERSION_CODE_XPATH;
	//一次处理的条数
	@Value("${analyze_batch_size}")
	private String ANALYZE_BATCH_SIZE;
	
	@Resource
	private MappingTableMapper mappingTableMapper;
	@Resource
	private DocAnalyzedMapper docAnalyzedMapper;
	@Resource
	private DocAnalyzeLogMapper docAnalyzeLogMapper;
	@Resource
	private BizDataStorageService bizDataStorageService;
	@Resource(name="logServiceClient")
	private LogServiceClient logServiceClient;
	
	/**
	 * 解析文档.
	 * @see com.zebone.dnode.analyze.service.DocumentAnalyzeService#analyze(java.lang.String, java.lang.String)
	 */
	@Override
	public void analyze(String analyzeThreads,String threadNo) {
		int size=1;
        int threads = Integer.parseInt(analyzeThreads);
        int no = Integer.parseInt(threadNo);
		try {
			if(ANALYZE_BATCH_SIZE!=null){
				ANALYZE_BATCH_SIZE = ANALYZE_BATCH_SIZE.trim();
			}
			size = Integer.parseInt(ANALYZE_BATCH_SIZE);
		} catch (NumberFormatException e1) {
			log.error("系统配置文件中analyze_batch_size值配置有错误！",e1);
			return;
		}
		List<DocumentInfo> docList = docAnalyzedMapper.getNoAnalyzedDoc(size,threads,no);
		for (DocumentInfo docInfo : docList) {
			String docxml = docInfo.getDocxml();
			String docId = docInfo.getId();
			String empiId = docInfo.getEmpiId();
			String parseState = docInfo.getParseState();			
			try {
				this.analyze(docId, empiId,parseState, docxml);
			} catch (Exception e) {
				log.error("",e);
			}
		}
	}
	
	
	@Override
	public void analyze(String docOrgCode) {
		// TODO Auto-generated method stub
		int size=1;
		try {
			if(ANALYZE_BATCH_SIZE!=null){
				ANALYZE_BATCH_SIZE = ANALYZE_BATCH_SIZE.trim();
			}
			size = Integer.parseInt(ANALYZE_BATCH_SIZE);
		} catch (NumberFormatException e1) {
			log.error("系统配置文件中analyze_batch_size值配置有错误！",e1);
			return;
		}
		List<DocumentInfo> docList = docAnalyzedMapper.getNoAnalyzedDocByDocOrgCode(docOrgCode,size);
		for (DocumentInfo docInfo : docList) {
			String docxml = docInfo.getDocxml();
			String docId = docInfo.getId();
			String empiId = docInfo.getEmpiId();
			String parseState = docInfo.getParseState();			
			try {
				this.analyze(docId, empiId,parseState, docxml);
			} catch (Exception e) {
				log.error("",e);
			}
		}
	}
	
	/**
	 * 将xml格式的文档解析到业务库
	 * @param docId 文档存储表中的此文档对应的ID(主键)
	 * @param empiId empi标识号
	 * @param parseState 文档解析状态
	 * @param docxml
	 */
	@SuppressWarnings("unchecked")
	public void analyze(String docId,String empiId,String parseState, String docxml){
		
		long start = System.currentTimeMillis();
		
		Document doc = null;
		
		try {
			doc = DocumentHelper.parseText(docxml);
		} catch (DocumentException e) {
			// 文档进行了校验和注册后才进行解析，应该不会出现这个错误。
			String errDesc = "文档数据不能够解析成DOM";
			log.error(errDesc, e);
			String docTypeCode = this.getDocTypeCode(doc);
			this.saveLog("", "", docTypeCode, "", errDesc);
			long end = System.currentTimeMillis();
			savaSysLog(docxml, "", "A_002", Syslog.STATUS_FAILURE, errDesc, end-start);
			try {
				parseState = "-1";//表示解析失败
				//更新业务库的文档解析状态
				docAnalyzedMapper.updateParseState(docId, parseState);
			} catch (Exception e1) {
				log.error("更新业务库的文档解析状态出错",e1);
			}
			return;
		}
		String docType = this.getDocTypeCode(doc);
		String docNo = this.getDocNo(doc);
		String docVersion =  this.getDocVersion(doc);
		String docOrgan = this.getDocOrgan(doc);
		boolean flag = true;
		StringBuilder sb = new StringBuilder("文档头信息错误:");
		if(docType == null){
			flag = false;
			sb.append("不能得到文档类型代码;");
		}
		if(docVersion == null){
			flag = false;
			sb.append("不能得到文档版本代码;");
		}
		if(docNo == null){
			flag = false;
			sb.append("不能得到文档编码;");
		}
		if(docOrgan  == null){
			flag = false;
			sb.append("不能得到文档机构代码;");
		}
		if(!flag){
			String docTypeCode = this.getDocTypeCode(doc);
			this.saveLog(docNo, docOrgan, docTypeCode, "", sb.toString());
			try {
				parseState = "-1";//表示解析失败
				//更新业务库的文档解析状态
				docAnalyzedMapper.updateParseState(docId, parseState);
			} catch (Exception e) {
				log.error("更新业务库的文档解析状态出错",e);
			}
			return;
		}
		//log.info("解析文档编号:"+docNo);
		// 得到所有文档对应的业务表
		List<Map<String, String>> tableList = mappingTableMapper.getMappingTableByDocTypeCode(docType);
		//解析数据信息列表
		List<AnalyzeDataInfo> analyzeDataList = new ArrayList<AnalyzeDataInfo>();
		for (Map<String, String> tableInfo : tableList) {
			//调试使用的映射关系字符串
			StringBuilder debugStr = new StringBuilder();
			String tableId = tableInfo.get("TABLEID");
			String tableName = tableInfo.get("TABLENAME");
			if (log.isDebugEnabled()) {
				debugStr.append("\n\n---------------------------------------------------\n");
				debugStr.append("表名：" + tableName+"\n");				
			}
			// 得到一个表的所有字段以及对应的xpath
			List<MappingInfo> mappingInfoList = mappingTableMapper.getMappingInfo(tableId,docType,docVersion);
			if(mappingInfoList==null || mappingInfoList.size()==0){
				String errDesc = "不能找到映射关系!文档类型编号:"+docType+" 文档编号："+docNo;
				log.error(errDesc);
				String docTypeCode = this.getDocTypeCode(doc);
				this.saveLog(docNo, docOrgan, docTypeCode, "", errDesc);
				try {
					parseState = "-1";//表示解析失败
					//更新业务库的文档解析状态
					docAnalyzedMapper.updateParseState(docId, parseState);
				} catch (Exception e) {
					log.error("更新业务库的文档解析状态出错",e);
				}
				return ;
			}
			List<String> columnList = new ArrayList<String>();
			//参数列表
			List<List<String>> paramList = new ArrayList<List<String>>();
			int valueParamNum = 0;
			int groupCount = 0;//可能有重复的父节点，记录有多个少
			//一个表的映射信息
			for (MappingInfo mi : mappingInfoList) {
				String column = mi.getFieldName();
				String isfk = mi.getIsfk();
				String xpath = mi.getXpath() + "/value";
				List<Element> valueElementList = doc.selectNodes(xpath);
				//xpath找不到的情况。文档都会经过校验的，一般不会出现这种情况。但是为了
				//保险还是加上此日志
				if(valueElementList==null || valueElementList.size()==0){
					/**
					String docTypeCode = this.getDocTypeCode(doc);
					String errDesc = "映射配置中的xpath在文档中不能够找到.xpath:"+xpath;
					this.saveLog(docNo, docOrgan, docTypeCode, "", errDesc);
					log.error(errDesc);
					savaSysLog(docxml, docNo, "A_002", Syslog.STATUS_FAILURE, errDesc);
					try {
						parseState = "-1";//表示解析失败
						//更新业务库的文档解析状态
						docAnalyzedMapper.updateParseState(docId, parseState);
					} catch (Exception e) {
						log.error("更新业务库的文档解析状态出错",e);
					}
					return;
					**/
					continue;
				}
				if(groupCount< valueElementList.size()){
					groupCount = valueElementList.size();
				}
				//某个字段的所有值
				List<String> cellList = new ArrayList<String>();
				//表中的外键不会xml中重复出现。
				if(isfk.equals("1")){//是映射关系中的外键
					Element elem = valueElementList.get(0);
					String value = elem.attributeValue("code");
					for(int k=0;k<groupCount;k++){
						cellList.add(value);
					}
				}else{
					for(Element elem : valueElementList){
						String value = elem.attributeValue("code");
						cellList.add(value);
						valueParamNum++;
					}
				}
				
				paramList.add(cellList);
				columnList.add(column);
				
				if (log.isDebugEnabled()) {
					debugStr.append("\t字段：").append(column).append("\n");
					debugStr.append("\tXPATH：").append(xpath).append("\n");
					String value = "";
					for(String cell : cellList){
						value += cell+"  ";
					}
					debugStr.append("\t数据：").append(value).append("\n\n");
				}
			}
			/** 其它字段无值就不插入  **/
			if(valueParamNum == 0){
				continue;
			}
			log.debug(debugStr);
			//文档编号字段与文档来源机构在每个业务表中都应该定义，而且名字都是一样的变量“DOC_NO_COLUMN"中规定了名字
			columnList.add(DOC_NO_COLUMN);
            columnList.add(SOURCE_ORG_COLUMN);
			columnList.add(EMPI_NO_COLUMN);
			columnList.add(CREATE_TIME_COLUMN);
			
			//每一条数据都要加上文档编号参数、文档来源机构、empi编号、创建时间
			List<String> docNoList = new ArrayList<String>();
            List<String> sourceOrgList = new ArrayList<String>();
			List<String> empiNoList = new ArrayList<String>();
			List<String> createTimeList = new ArrayList<String>();
			String curTime = DateUtil.getCurrentDate("yyyyMMddHHmmss");
			for(int i=0;i<groupCount;i++){
				docNoList.add(docNo);
                sourceOrgList.add(docOrgan);
				empiNoList.add(empiId);
				createTimeList.add(curTime);
			}
			//注意参数值信息的加入顺序，需要与前面的columnList列表中的字段加入顺序一致。
			paramList.add(docNoList);
            paramList.add(sourceOrgList);
			paramList.add(empiNoList);
			paramList.add(createTimeList);
			AnalyzeDataInfo analyzeDataInfo = new AnalyzeDataInfo();
			analyzeDataInfo.setDocNo(docNo);
            analyzeDataInfo.setSourceOrg(docOrgan);
			analyzeDataInfo.setEmpiId(empiId);
			analyzeDataInfo.setTableName(tableName);
			analyzeDataInfo.setColumnList(columnList);
			analyzeDataInfo.setParamList(paramList);
			analyzeDataList.add(analyzeDataInfo);
		}
		try {
			//将文档所对应的业务表的数据保存到数据库中
			bizDataStorageService.saveToDataBase(analyzeDataList);
			long end = System.currentTimeMillis();
			savaSysLog(docxml, docNo, "", Syslog.STATUS_SUCCESS, "", end-start);
		} catch (AnalyzeException e) {
			log.error("", e);
			String docTypeCode = this.getDocTypeCode(doc);
			String errorEx = e.getMessage();
			String errDesc = e.getErrorDesc();
			if(errorEx.length()>1000){
				errorEx = errorEx.substring(0,1000);
			}
			if(errDesc.length()>1000){
				errDesc = errDesc.substring(0,1000);
			}
			this.saveLog(docNo, docOrgan, docTypeCode, errorEx, errDesc);
			//更新新解析状态为“解析失败”，避免下一次在查询出，会造成一致解析这个错误的数据
			parseState = "-1";//表示解析失败
			long end = System.currentTimeMillis();
			savaSysLog(docxml, docNo, "A_002", Syslog.STATUS_FAILURE, errDesc, end-start);
			
		}catch(Exception e){
			log.error("", e);			
			String docTypeCode = this.getDocTypeCode(doc);
			String errorEx = e.getMessage();
			this.saveLog(docNo, docOrgan, docTypeCode, errorEx, "");
			parseState = "-1";//表示解析失败
			long end = System.currentTimeMillis();
			savaSysLog(docxml, docNo, "A_999", Syslog.STATUS_FAILURE, errorEx, end-start);
		}
		try {
			if (!parseState.equals("-1")) {//如果解析没有出错。那么记录业务表变化日志
				saveChangeLog(tableList,docNo,empiId);
			}
		} catch (Exception e) {
			log.error("保存业务表变化日志出现错误",e);
		}
		if (parseState.equals("0")) {// 已经状态是0，要转换成1
			parseState = "1";
		} else if(parseState.equals("2")){// parseState是2，需要更新成3，表示重新解析
			parseState = "3";
		}
		try {
			//更新业务库的文档解析状态
			docAnalyzedMapper.updateParseState(docId, parseState);
		} catch (Exception e) {
			log.error("更新业务库的文档解析状态出错",e);
		}
	}
	

	/**
	 * 根据所给定的子表列表从所有的表中找到主表
	 * 
	 * @param tableList
	 * @param subTableList
	 * @return
	 */
	@SuppressWarnings("unused")
	private List<Map<String, String>> findMainTables(List<Map<String, String>> tableList,
			List<Map<String, String>> subTableList) {
		List<Map<String, String>> mainTableList = new ArrayList<Map<String, String>>();
		for (Map<String, String> table : tableList) {
			boolean isfind = false;
			for (Map<String, String> subTable : subTableList) {
				if (subTable.get("tableName").equals(table.get("tableName"))) {
					isfind = true;
				}
			}
			if (!isfind) {
				mainTableList.add(table);
			}
		}
		return mainTableList;
	}

	/**
	 * 得到文档的唯一标识。文档的第一个solt节点为唯一标识
	 * 
	 * @param doc
	 *            文档对象
	 * @return 文档的唯一标识
	 */
	private String getDocNo(Document doc) {
		Node node = doc.selectSingleNode(DOC_NO_XPATH);
		if(node == null){
			return null;
		}
		String docNo = ((Element) node).attributeValue("code");
		return docNo;
	}
	
	private String getDocVersion(Document doc){
		Node node = doc.selectSingleNode(DOC_VERSION_CODE_XPATH);
		if(node == null){
			return null;
		}
		String docVersion = ((Element) node).attributeValue("code");
		return docVersion;
	}

	/**
	 * 得到文档中的文档机构代码。
	 * 
	 * @param doc
	 *            文档对象
	 * @return 文档机构代码
	 */
	private String getDocOrgan(Document doc) {
		Node node = doc.selectSingleNode(DOC_ORGAN_XPATH);
		if(node == null){
			return null;
		}
		String docOrgan = ((Element) node).attributeValue("code");
		return docOrgan;
	}

	/**
	 * 得到文档中的文档类型代码
	 * 
	 * @param doc
	 *            文档对象
	 * @return
	 */
	private String getDocTypeCode(Document doc) {
		Node node = doc.selectSingleNode(DOC_TYPE_CODE_XPATH);
		if(node == null){
			return null;
		}
		String docTypeCode = ((Element) node).attributeValue("code");
		return docTypeCode;
	}
	
	/**
	 * 保存日志信息
	 * @param docNo 文档编号
	 * @param docOrgan 文档机构
	 * @param docTypeCode 文档类型
	 * @param errorEx  异常的错误信息
	 * @param errorDesc 错误的业务信息
	 */
	private void saveLog(String docNo,String docOrgan,String docTypeCode,String errorEx,String errorDesc){
		DocAnalyzeLog docAnalyzeLog = new DocAnalyzeLog();
		docAnalyzeLog.setCreateTime(new Date());
		docAnalyzeLog.setDocNo(docNo);
		docAnalyzeLog.setDocOrgan(docOrgan);
		docAnalyzeLog.setDocTypeCode(docTypeCode);
		docAnalyzeLog.setErrorDesc(errorDesc);
		docAnalyzeLog.setErrorException(errorEx);
		String uuid = UUIDUtil.getUuid();
		docAnalyzeLog.setId(uuid);
		try {
			docAnalyzeLogMapper.insert(docAnalyzeLog);
		} catch (Exception e) {
			log.error("保存解析日志出现错误",e);
		}
	}
	
	/**
	 * 保存业务表的变化日志
	 * @param tableList
	 */
	private void saveChangeLog(List<Map<String, String>> tableList ,String docNo ,String empiId){
		for (Map<String, String> tableInfo : tableList) {
			String tableName = tableInfo.get("TABLENAME");
			ParesTableLog paresTableLog = new ParesTableLog();
			String uuid = UUIDUtil.getUuid();
			paresTableLog.setId(uuid);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
			String createTime = sdf.format(new Date());
			paresTableLog.setCreateTime(createTime);
			paresTableLog.setDataFlag("0");//表示未加工
			paresTableLog.setDocNo(docNo);
			paresTableLog.setEmpiNo(empiId);
			paresTableLog.setTableName(tableName);
			this.docAnalyzeLogMapper.insertChangeLog(paresTableLog);
		}
	}
	
	/**
	 * 记录系统日志
	 */
	private void savaSysLog(String docxml ,String docNo , String errCode , String status,String errDesc,long execTime){
		try{
			//日志对象
			Syslog syslog = new Syslog();
			syslog.setCategory("1");
			syslog.setType(Syslog.TYPE_ANALYZE);
			DocExtendInfo ro = DocExtInfoService.getExtInfo(docxml);
			syslog.setLogID(ro.getUuid());
			syslog.setStatus(status);
			syslog.setDocNo(docNo);
			syslog.setLogTime(DateUtil.getCurrentDate("yyyy-MM-dd HH:mm:ss"));
			syslog.setExecTime(execTime);
			if(status.equals(Syslog.STATUS_FAILURE)){
				Error error = new Error();
				error.setCode(errCode);
				error.setDesc(errDesc);
				syslog.setError(error);
			}
			logServiceClient.saveLog(syslog);
		}catch(Exception e){
			log.error("",e);
		}
	}


}
