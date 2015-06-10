package com.zebone.register.analyze;

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

import com.zebone.register.analyze.vo.EmpiRegLog;
import com.zebone.auditlog.service.AuditlogCacheService;
import com.zebone.auditlog.vo.AuditLog;
import com.zebone.empi.service.EmpiService;
import com.zebone.empi.util.UUIDUtil;
import com.zebone.register.analyze.dao.EmpiRegLogMapper;
import com.zebone.register.analyze.dao.MappingTableMapper;
import com.zebone.register.analyze.service.BizDataStorageService;
import com.zebone.register.analyze.vo.AnalyzeDataInfo;
import com.zebone.register.analyze.vo.MappingInfo;
import com.zebone.register.validation.enu.OpeType;
import com.zebone.register.validation.enu.ResultType;
import com.zebone.util.DateUtil;

/**
  * 项目名称：empi
  * 类名称：    DocumentAnalyze 
  * 类描述：   文档解析
  * 创建人：     LinBin 
  * 创建时间：2015年5月15日 上午11:05:21
  * 修改人：     LinBin 
  * 修改时间：2015年5月15日 上午11:05:21
  * 修改备注： 
  * @version
 */
@Service("documentAnalyze")
public class DocumentAnalyze {
	
	private static final Log log = LogFactory.getLog(DocumentAnalyze.class);
	
	@Resource
	private MappingTableMapper mappingTableMapper;
	@Resource
	private BizDataStorageService bizDataStorageService;
	@Resource
	private AuditlogCacheService auditlogService;
	@Resource
	private EmpiService empiService;
	@Resource
	private EmpiRegLogMapper empiRegLogMapper;
	
	
	/**
	 * 文档唯一标识的xpath路径。
	 */
	@Value("${doc_no_xpath}")
	private   String DOC_NO_XPATH ;
	/**
	 * 文档业务系统编码的xpath路径
	 */
	@Value("${doc_sys_code_xpath}")
	private String DOC_SYS_CODE_XPATH;
	
	/**
	 * 文档类型的xpath路径
	 */
	@Value("${doc_type_code_xpath}")
	private   String DOC_TYPE_CODE_XPATH ;
	
	/**
	 * 文档操作的xpath路径
	 */
	@Value("${doc_ope_code_xpath}")
	private   String DOC_OPE_CODE_XPATH ;
	
	/**
	 * 文档机构编码的xpath路径
	 */
	@Value("${doc_organ_xpath}")
	private   String DOC_ORGAN_XPATH ;
	
	/**
	 * 人员编码的xpath路径
	 */
	@Value("${doc_input_user_xpath}")
	private   String DOC_INPUT_USER_XPATH ;
	
	/**
	 * 文档版本的xpath路径
	 */
	@Value("${doc_version_code_xpath}")
	private String DOC_VERSION_CODE_XPATH;
	
	//业务库中文档编号字段的名字
	@Value("${doc_no_column}")
	private String DOC_NO_COLUMN;
    //业务库中文档来源机构字段的名字
    @Value("${source_org_column}")
    private String SOURCE_ORG_COLUMN;
	//创建时间字段的名字
	@Value("${create_time_column}")
	private String CREATE_TIME_COLUMN;
	
	/**
	 * 将xml格式的文档解析到业务库
	 * @param docId 文档存储表中的此文档对应的ID(主键)
	 * @param empiId empi标识号
	 * @param parseState 文档解析状态
	 * @param docxml
	 */
	@SuppressWarnings("unchecked")
	public String analyze(String docxml,String ip){
		AuditLog auditLog = new AuditLog();
		auditLog.setSourceIp(ip);
        auditLog.setCreateTime(new Date());
        auditLog.setCreateDate(DateUtil.getCurrentDate("yyyyMMddHHmmss"));
        
		Document doc = null;
		String result = null;
		try {
			doc = DocumentHelper.parseText(docxml);
		} catch (DocumentException e) {
			// 文档进行了校验和注册后才进行解析，应该不会出现这个错误。
			String errDesc = "文档数据不能够解析成DOM";
			auditLog.setDescription(errDesc);
			auditlogService.saveLogToCache(auditLog);
			result = ResultType.XML_ERROR.getErrorCode()+"|"+ResultType.XML_ERROR.getErrorMsg()+"|"+errDesc;
			return result;
		}
		String sysCode = this.getDocCodeByPath(doc,DOC_SYS_CODE_XPATH);
		String docTypeCode = this.getDocCodeByPath(doc,DOC_TYPE_CODE_XPATH);
		String docOpeCode =  this.getDocCodeByPath(doc,DOC_OPE_CODE_XPATH);
		String docOrgCode = this.getDocCodeByPath(doc,DOC_ORGAN_XPATH);
		String docUserCode = this.getDocCodeByPath(doc,DOC_INPUT_USER_XPATH);
		String docVersion = this.getDocCodeByPath(doc,DOC_VERSION_CODE_XPATH);
		String docNo = this.getDocCodeByPath(doc,DOC_NO_XPATH);
		boolean flag = true;
		StringBuilder sb = new StringBuilder("文档头信息错误:");
		if(sysCode == null){
			flag = false;
			sb.append("不能得到业务系统代码;");
		}
		if(docTypeCode == null){
			flag = false;
			sb.append("不能得到文档类型代码;");
		}
		if(docOpeCode == null){
			flag = false;
			sb.append("不能得到文档操作编码;");
		}
		if(docOrgCode == null){
			flag = false;
			sb.append("不能得到文档机构编码;");
		}
		if(docUserCode  == null){
			flag = false;
			sb.append("不能得到操作人员代码;");
		}
		if(docVersion  == null){
			flag = false;
			sb.append("不能得到版本信息;");
		}
		if(docNo  == null){
			flag = false;
			sb.append("不能得到文档编号信息;");
		}
		if(!flag){
			//TODO 保存日志
			auditLog.setDescription(sb.toString());
			auditlogService.saveLogToCache(auditLog);
			result = ResultType.XML_ERROR.getErrorCode()+"|"+ResultType.XML_ERROR.getErrorMsg()+"|"+sb.toString();
			return result;
		}
		
		auditLog.setOrgCode(docOrgCode);
        auditLog.setPersonAccount(docUserCode);
        //TODO需要修改
        auditLog.setEventType("注册模块");
        //操作类型
        if(docOpeCode.equals(OpeType.DATA_INSERT.getErrorCode())){
        	auditLog.setOptTypeId(OpeType.DATA_INSERT.getErrorCode());
        	auditLog.setOptType(OpeType.DATA_INSERT.getErrorMsg());
        }else if(docOpeCode.equals(OpeType.DATA_UPDATE.getErrorCode())){
        	auditLog.setOptTypeId(OpeType.DATA_UPDATE.getErrorCode());
        	auditLog.setOptType(OpeType.DATA_UPDATE.getErrorMsg());
        }else if(docOpeCode.equals(OpeType.DATA_DELETE.getErrorCode())){
        	auditLog.setOptTypeId(OpeType.DATA_DELETE.getErrorCode());
        	auditLog.setOptType(OpeType.DATA_DELETE.getErrorMsg());
        }
        
        if(docTypeCode.equals("个人注册")&&docOpeCode.equals(OpeType.DATA_QUERY.getErrorCode())){
        	try {
        		//保存记录
        		EmpiRegLog empiRegLog = new EmpiRegLog();
        		empiRegLog.setId(UUIDUtil.getUuid());
        		empiRegLog.setBusinessSysCode(sysCode);
        		empiRegLog.setInputOrgCode(docOrgCode);
        		empiRegLog.setInputTime(new Date());
        		empiRegLog.setInputUserCode(docUserCode);
        		empiRegLog.setRegDocument(docxml);
        		empiRegLogMapper.saveEmpiRegLog(empiRegLog);
				empiService.searchEmpiInfo(docxml);
			} catch (Exception e) {
				e.printStackTrace();
				result = ResultType.SYS_ERROR.getErrorCode()+"|"+ResultType.SYS_ERROR.getErrorMsg()+"|个人注册失败！";
				return result;
			}
        }else if (docTypeCode.equals("个人注册")&&(docOpeCode.equals(OpeType.DATA_INSERT.getErrorCode())||docOpeCode.equals(OpeType.DATA_UPDATE.getErrorCode()))){
        	empiService.registerOrUpdate(docxml);
        }
        
		
		// 得到所有文档对应的业务表
		List<Map<String, String>> tableList = mappingTableMapper.getMappingTableByDocTypeCode(docTypeCode);
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
			List<MappingInfo> mappingInfoList = mappingTableMapper.getMappingInfo(tableId,docTypeCode,docVersion);
			if(mappingInfoList==null || mappingInfoList.size()==0){
				//TODO 保存日志
				String errDesc = "不能找到映射关系!文档类型编号:"+docTypeCode+" 文档编号："+docNo;
				auditLog.setDescription(errDesc);
				auditlogService.saveLogToCache(auditLog);
				log.error(errDesc);
				result = ResultType.SYS_ERROR.getErrorCode()+"|"+ResultType.SYS_ERROR.getErrorMsg()+"|"+errDesc;
				return result;
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
					String errDesc = "映射配置中的xpath在文档中不能够找到.xpath:"+xpath;
					auditLog.setDescription(errDesc);
					auditlogService.saveLogToCache(auditLog);
					log.error(errDesc);
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
//			log.debug(debugStr);
			//文档编号字段与文档来源机构在每个业务表中都应该定义，而且名字都是一样的变量“DOC_NO_COLUMN"中规定了名字
			columnList.add(DOC_NO_COLUMN);
            columnList.add(SOURCE_ORG_COLUMN);
//			columnList.add(EMPI_NO_COLUMN);
			columnList.add(CREATE_TIME_COLUMN);
			
			//每一条数据都要加上文档编号参数、文档来源机构、empi编号、创建时间
			List<String> docNoList = new ArrayList<String>();
            List<String> sourceOrgList = new ArrayList<String>();
			List<String> empiNoList = new ArrayList<String>();
			List<String> createTimeList = new ArrayList<String>();
			String curTime = DateUtil.getCurrentDate("yyyyMMddHHmmss");
			for(int i=0;i<groupCount;i++){
				docNoList.add(docNo);
                sourceOrgList.add(docOrgCode);
//				empiNoList.add(empiId);
				createTimeList.add(curTime);
			}
			//注意参数值信息的加入顺序，需要与前面的columnList列表中的字段加入顺序一致。
			paramList.add(docNoList);
            paramList.add(sourceOrgList);
			paramList.add(empiNoList);
			paramList.add(createTimeList);
			AnalyzeDataInfo analyzeDataInfo = new AnalyzeDataInfo();
			analyzeDataInfo.setDocNo(docNo);
            analyzeDataInfo.setSourceOrg(docOrgCode);
//			analyzeDataInfo.setEmpiId(empiId);
			analyzeDataInfo.setTableName(tableName);
			analyzeDataInfo.setColumnList(columnList);
			analyzeDataInfo.setParamList(paramList);
			analyzeDataList.add(analyzeDataInfo);
		}
		try {
			//将文档所对应的业务表的数据保存到数据库中
			bizDataStorageService.saveToDataBase(analyzeDataList,docOpeCode);
		}catch(Exception e){
			log.error("", e);
			String errorEx = e.getMessage();
			auditLog.setDescription(errorEx);
			auditlogService.saveLogToCache(auditLog);
			result = ResultType.SYS_ERROR.getErrorCode()+"|"+ResultType.SYS_ERROR.getErrorMsg()+"|"+errorEx;
			return result;
		}
		result = ResultType.SUCCESS.getErrorCode()+"|"+ResultType.SUCCESS.getErrorMsg();
		return result;
	}
	
	/**
	 * 得到文档中的特定路径的值
	 * 
	 * @param doc
	 *            文档对象
	 * @param path
	 *            路径
	 * @return
	 */
	private String getDocCodeByPath(Document doc,String path) {
		Node node = doc.selectSingleNode(path);
		if(node == null){
			return null;
		}
		String docCode = ((Element) node).attributeValue("code");
		return docCode;
	}
	
}
