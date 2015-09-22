package com.zebone.dip.ws.resource.service.impl;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.jws.WebService;
import javax.servlet.http.HttpServletRequest;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.cxf.transport.http.AbstractHTTPDestination;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.xml.sax.SAXException;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.zebone.check.CheckConfig;
import com.zebone.check.CheckFlag;
import com.zebone.check.CheckResult;
import com.zebone.check.component.SchemaCheck;
import com.zebone.dip.ws.resource.check.ResourceVcheck;
import com.zebone.dip.ws.resource.model.PResourceLog;
import com.zebone.dip.ws.resource.pojo.FamilyRequest;
import com.zebone.dip.ws.resource.pojo.FamilyResponse;
import com.zebone.dip.ws.resource.pojo.OfficeRequest;
import com.zebone.dip.ws.resource.pojo.OfficeResponse;
import com.zebone.dip.ws.resource.pojo.Opt;
import com.zebone.dip.ws.resource.pojo.ResourceCode;
import com.zebone.dip.ws.resource.pojo.ResponseHead;
import com.zebone.dip.ws.resource.pojo.UserRequest;
import com.zebone.dip.ws.resource.pojo.UserResponse;
import com.zebone.dip.ws.resource.service.ResourceDataService;
import com.zebone.dip.ws.resource.service.ResourceDeptService;
import com.zebone.dip.ws.resource.service.ResourceFamilyService;
import com.zebone.dip.ws.resource.service.ResourceLogService;
import com.zebone.dip.ws.resource.service.ResourcesUserService;
import com.zebone.log.ErrorType;
import com.zebone.log.FileStructureError;
import com.zebone.log.RangeError;
import com.zebone.util.DateUtil;
import com.zebone.util.UUIDUtil;

@WebService(endpointInterface = "com.zebone.dip.ws.resource.service.ResourceDataService")
@Service("resourceDataService")
public class ResourceDataServiceImpl implements ResourceDataService {
    
	
	private static final Log log = LogFactory.getLog(ResourceDataServiceImpl.class);
		
	private static final String BASE_URI = "com/zebone/dip/ws/resource/xsd/";
	
	private static final Map<String,String> schemaUriMap= new HashMap<String, String>();
	
	private static final Map<String,String> checkConfigUriMap = new HashMap<String, String>();
	
	private XStream xs;
	
	@Resource
	private ResourceDeptService resourceDeptService;
	
	@Resource
	private ResourcesUserService resourcesUserService;
	
	@Resource
	private ResourceFamilyService resourceFamilyService;
	
	@Resource
	private ResourceLogService resourceLogService;
	
	
	private WebServiceContext context = new org.apache.cxf.jaxws.context.WebServiceContextImpl();

	
	
	static{
		schemaUriMap.put(ResourceCode.KSZY.name()+"_"+ Opt.ADD.getCode(),"resourceDept.xsd");
		schemaUriMap.put(ResourceCode.KSZY.name()+"_"+ Opt.UPDATE.getCode(),"resourceDept.xsd");
		schemaUriMap.put(ResourceCode.KSZY.name()+"_"+ Opt.DEL.getCode(),"resourceDeptDel.xsd");
		schemaUriMap.put(ResourceCode.KSZY.name()+"_"+ Opt.QUERY.getCode(),"resourceDeptQuery.xsd");	
		
		schemaUriMap.put(ResourceCode.WSRY.name()+"_"+ Opt.ADD.getCode(),"resourceUser.xsd");
		schemaUriMap.put(ResourceCode.WSRY.name()+"_"+ Opt.UPDATE.getCode(),"resourceUser.xsd");
		schemaUriMap.put(ResourceCode.WSRY.name()+"_"+ Opt.DEL.getCode(),"resourceUserDel.xsd");
		schemaUriMap.put(ResourceCode.WSRY.name()+"_"+ Opt.QUERY.getCode(),"resourceUserQuery.xsd");
		
		schemaUriMap.put(ResourceCode.JTDA.name()+"_"+ Opt.ADD.getCode(),"resourceFamily.xsd");
		schemaUriMap.put(ResourceCode.JTDA.name()+"_"+ Opt.UPDATE.getCode(),"resourceFamily.xsd");
		schemaUriMap.put(ResourceCode.JTDA.name()+"_"+ Opt.DEL.getCode(),"resourceFamilyDel.xsd");
		schemaUriMap.put(ResourceCode.JTDA.name()+"_"+ Opt.QUERY.getCode(),"resourceFamilyQuery.xsd");
		
		checkConfigUriMap.put(ResourceCode.KSZY.name(),"resourceDeptCheck.xml");
		checkConfigUriMap.put(ResourceCode.WSRY.name(),"resourceUserCheck.xml");
		checkConfigUriMap.put(ResourceCode.JTDA.name(),"resourceFamilyCheck.xml");
	}
	
   
	
	@Override
	public String request(String requestParam) {
		String requestResult = null;
		xs =  new XStream(new DomDriver());
		PResourceLog rLog = null;
		try {
			SAXReader reader = new SAXReader();
			rLog = prepareLog();
			rLog.setResourceData(requestParam);
			Document document = reader.read(new ByteArrayInputStream(requestParam.getBytes("utf-8")));
			prepareDocumentLog(document, rLog);
			if(StringUtils.isNotEmpty(rLog.getResourceCode()) && StringUtils.isNotEmpty(rLog.getOperFlag())){
				    String tradeNo = rLog.getResourceCode();
				    String optNo = rLog.getOperFlag();
					if(!ResourceCode.contains(tradeNo)){
						CheckResult checkResult = new CheckResult(
								false,
								ErrorType.VALUE_CHECK.getErrorCode()+"-"+RangeError.TRADE_NO.getErrorCode(), 
								ErrorType.VALUE_CHECK.getErrorDesc()+"--"+RangeError.TRADE_NO.getErrorDesc()
								);
						rLog.setCheckType(ErrorType.FILE_STRUCTURE.getErrorCode());
						return processError(checkResult,rLog);
					}
					if(!Opt.contains(optNo)){
						CheckResult checkResult = new CheckResult(
								false,
								ErrorType.VALUE_CHECK.getErrorCode()+"-"+RangeError.OPT_NO.getErrorCode(), 
								ErrorType.VALUE_CHECK.getErrorDesc()+"--"+RangeError.OPT_NO.getErrorDesc()
								);
						rLog.setCheckType(ErrorType.FILE_STRUCTURE.getErrorCode());
						return processError(checkResult,rLog);
					}
					CheckResult checkResult = new SchemaCheck().check(requestParam, getSchemaInputStream(schemaUriMap.get(tradeNo+"_"+optNo)));		
					if(checkResult.isSuccess()){
						Object requestObj = getRequest(ResourceCode.valueOf(tradeNo),requestParam);			
						String resultString = check(ResourceCode.valueOf(tradeNo),requestObj);
						if(StringUtils.isNotEmpty(resultString)){
							CheckResult icheckResult = new CheckResult(
									false,
									ErrorType.VALUE_CHECK.getErrorCode()+"-"+RangeError.VALUE_NO.getErrorCode(), 
									resultString
									);
							rLog.setCheckType(ErrorType.LOGIC.getErrorCode());
							return processError(icheckResult,rLog);
						}else{
							//校验成功
							requestResult = choiceService(ResourceCode.valueOf(tradeNo),Opt.getOpt(optNo),requestObj);
							SAXReader rReader = new SAXReader();
							Document rdocument = rReader.read(new ByteArrayInputStream(requestResult.getBytes("utf-8")));
							Node sucessNode = rdocument.selectSingleNode("//response/head/sucess");
							if(sucessNode != null && sucessNode.getStringValue() != null){
								String sucess = sucessNode.getStringValue();
								if(CheckFlag.FAIL.getCode().equals(sucess)){
									rLog.setCheckFlag(sucess);
									rLog.setCheckType(ErrorType.LOGIC.getErrorCode());
									rLog.setCheckDesc(rdocument.selectSingleNode("//response/head/errorDesc").getStringValue());
								}
								resourceLogService.saveResourceLog(rLog);
							}
						}
					}else{
						rLog.setCheckType(checkResult.getErrorCode().split("-")[0]);
						return processError(checkResult,rLog);
					}
                
			}else{
				Node tradeNoNode = document.selectSingleNode("//request/head/tradeNo");
				if(tradeNoNode!=null){
					CheckResult checkResult = new CheckResult(
							false,
							ErrorType.VALUE_CHECK.getErrorCode()+"-"+RangeError.VALUE_NO.getErrorCode(), 
							ErrorType.VALUE_CHECK.getErrorDesc()+"--文档头结点tradeNo"+RangeError.VALUE_NO.getErrorDesc()
							);
					rLog.setCheckType(ErrorType.FILE_STRUCTURE.getErrorCode());
					return processError(checkResult,rLog);
				}else{
					CheckResult checkResult = new CheckResult(
							false,
							ErrorType.FILE_STRUCTURE.getErrorCode()+"-"+FileStructureError.TRADENO_NODE_NOT_EXISTS.getErrorCode(), 
							ErrorType.FILE_STRUCTURE.getErrorDesc()+"--"+FileStructureError.TRADENO_NODE_NOT_EXISTS.getErrorDesc()
							);
					rLog.setCheckType(ErrorType.FILE_STRUCTURE.getErrorCode());
					return processError(checkResult,rLog);
				}
			}
		} catch (Exception e) {
			rLog.setCheckType(ErrorType.SYSTEM.getErrorCode());
			requestResult = processError(e,rLog);
			log.error(e.getMessage(), e);
		} 
		return requestResult;
	}
	
	
	private PResourceLog prepareLog(){
		PResourceLog log = new PResourceLog();
		
		MessageContext ctx = context.getMessageContext();
		HttpServletRequest request = (HttpServletRequest) ctx.get(AbstractHTTPDestination.HTTP_REQUEST);
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		log.setIp(ip);
		log.setId(UUIDUtil.getUuid());
		log.setCreateTime(DateUtil.getCurrentDate("yyyyMMddHHmmss"));
		
	    return log;
	}
	
	private void prepareDocumentLog(Document document,PResourceLog log){
		Node requestIdNode = document.selectSingleNode("request/head/requestId");
		if(requestIdNode !=null && requestIdNode.getStringValue() != null){
			log.setRequestId(requestIdNode.getStringValue());
			
		}
		
		Node tradeNoNode = document.selectSingleNode("//request/head/tradeNo");
		if(tradeNoNode != null && tradeNoNode.getStringValue() != null){
		    String tradeNoValue = tradeNoNode.getStringValue();
		    String[] tradeNoValues = tradeNoValue.split("_");
		    if(tradeNoValues.length == 2){
		    	log.setResourceCode(tradeNoValues[0]);
		    	log.setOperFlag(tradeNoValues[1]);
		    }
		}
		
		Node orgCodeNode = document.selectSingleNode("//request/head/orgCode");
		if(orgCodeNode != null && orgCodeNode.getStringValue() != null){
		     log.setOrgCode(orgCodeNode.getStringValue());	   
		}
	}
	
	private String decodeXml(String xml) {
		return xml.replaceAll("&lt;", "<").replaceAll("&gt;", ">")
				.replaceAll("&apos;", "'").replaceAll("&quot;", "\"")
				.replaceAll("&amp;", "&").replaceAll("&#xd;", "");
	}
	/**
	 * 
	 * @param errorMsg
	 * @return
	 * @author 陈阵 
	 * @date 2014-4-2 上午9:22:26
	 */
	private String processError(CheckResult checkResult,PResourceLog rLog){
		ResponseHead responseHead = new ResponseHead();
		responseHead.setErrorCode(checkResult.getErrorCode());
		responseHead.setErrorDesc(checkResult.getErrorMessage());
		xs.processAnnotations(ResponseHead.class);
		String result = decodeXml("<response>"+xs.toXML(responseHead)+"</response>");
		//插入日志
		if(StringUtils.isEmpty(rLog.getId())){
			
		}
		saveResourceData(checkResult,rLog);
		return result;
	}
	
	
	/**
	 * 
	 * @param e
	 * @return
	 * @author 陈阵 
	 * @date 2014-4-2 上午9:22:12
	 */
	private String processError(Exception e,PResourceLog rLog){
		CheckResult checkResult = new CheckResult(false);
		if(e instanceof DocumentException || e instanceof SAXException){
			checkResult.setErrorCode(ErrorType.FILE_STRUCTURE.getErrorCode());
			checkResult.setErrorMessage(ErrorType.FILE_STRUCTURE.getErrorDesc());
		}else{
			checkResult.setErrorCode(ErrorType.SYSTEM.getErrorCode());
			checkResult.setErrorMessage(ErrorType.SYSTEM.getErrorDesc());
		}
		String result = processError(checkResult,rLog);
		return result;
	}
	
	
	private Object getRequest(ResourceCode serviceNo, String documentXML){
		switch (serviceNo) {
		case KSZY:
			xs.processAnnotations(OfficeRequest.class);
			OfficeRequest officeRequest = (OfficeRequest)xs.fromXML(documentXML);
			return officeRequest;
		case WSRY:
			xs.processAnnotations(UserRequest.class);
			UserRequest userRequest = (UserRequest)xs.fromXML(documentXML);
			return userRequest;
		case JTDA:
			xs.processAnnotations(FamilyRequest.class);
			FamilyRequest familyRequest = (FamilyRequest)xs.fromXML(documentXML);
			return familyRequest;
		default:
			break;
		}
		return null;
	}
	
	private String check(ResourceCode serviceNo,Object obj) throws IOException, IllegalAccessException, InvocationTargetException, NoSuchMethodException, InstantiationException, SQLException{
		String checkMessage = null;
		String checkConfigFile = checkConfigUriMap.get(serviceNo.name());
		if(StringUtils.isEmpty(checkConfigFile)){
			return checkMessage;
		}
		CheckConfig checkConfig = getCheckConfig(checkConfigFile);
		switch (serviceNo) {
		case KSZY:
			checkMessage = new ResourceVcheck().check(checkConfig, (OfficeRequest)obj);
			break;
		case WSRY:
			checkMessage = new ResourceVcheck().check(checkConfig, (UserRequest)obj);
			break;
		case JTDA:
			checkMessage = new ResourceVcheck().check(checkConfig, (FamilyRequest)obj);
			break;
		default:
			break;
		}
		return checkMessage;
	}
	
	
	/**
	 * 
	 * @param serviceNo
	 * @param documentXML
	 * @return
	 * @author 陈阵 
	 * @throws IOException 
	 * @throws InstantiationException 
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @date 2014-3-28 下午3:15:29
	 */
	private String choiceService(ResourceCode serviceNo,Opt action,Object obj) throws IOException, IllegalAccessException, InvocationTargetException, NoSuchMethodException, InstantiationException{
		String result = null;
		
		switch (serviceNo) {
		case KSZY:
			result = doKSZY(action,obj);
			break;
		case WSRY:
			result = doWSRY(action,obj);
			break;
		case JTDA:
			result = doJTDA(action,obj);
			break;
		default:
			break;
		}
		return result;
	}
	
	
	private String doKSZY(Opt action,Object obj) {
		OfficeRequest officeRequest = (OfficeRequest)obj;
		OfficeResponse officeResponse = null;
		switch (action) {
		case ADD:
			 officeResponse = resourceDeptService.saveDept(officeRequest);
			break;
		case UPDATE:
			 officeResponse = resourceDeptService.updateDept(officeRequest);
			break;
		case QUERY:
			officeResponse = resourceDeptService.searchDept(officeRequest);
			break;
		case DEL:
			 officeResponse = resourceDeptService.delDept(officeRequest);
			break;
		default:
			break;
		}
		xs.processAnnotations(OfficeResponse.class);
		return xs.toXML(officeResponse);

	}
	
	
	private String doWSRY(Opt action,Object obj){
		UserRequest userRequest = (UserRequest)obj;
		UserResponse userResponse = null;
		switch (action) {
		case ADD:
			userResponse = resourcesUserService.saveUser(userRequest);
			break;
		case UPDATE:
			userResponse = resourcesUserService.updateUser(userRequest);
			break;
		case QUERY:
			userResponse = resourcesUserService.searchUser(userRequest);
			break;
		case DEL:
			userResponse = resourcesUserService.delUser(userRequest);
			break;
		default:
			break;
		}
		xs.processAnnotations(UserResponse.class);
		return xs.toXML(userResponse);
	}
	
	
	private String doJTDA(Opt action,Object obj){
		FamilyRequest familyRequest = (FamilyRequest)obj;
		FamilyResponse familyResponse = null;
		switch (action) {
		case ADD:
			familyResponse = resourceFamilyService.saveFamily(familyRequest);
			break;
		case UPDATE:
			familyResponse = resourceFamilyService.updateFamily(familyRequest);
			break;
		case QUERY:
			familyResponse = resourceFamilyService.searchFamily(familyRequest);
			break;
		case DEL:
			familyResponse = resourceFamilyService.delFamily(familyRequest);
			break;
		default:
			break;
		}
		xs.processAnnotations(FamilyResponse.class);
		return xs.toXML(familyResponse);
	}
	
	/**
	 * 
	 * @param schema
	 * @return
	 * @throws IOException
	 * @author 陈阵 
	 * @date 2014-3-28 下午3:15:37
	 */
	private InputStream getSchemaInputStream(String schemaName) throws IOException{
		 InputStream xsdInputStream =  new ClassPathResource(BASE_URI+schemaName).getInputStream();
		 return xsdInputStream;
	}
	
	
	/**
	 * 
	 * @param checkConfigName
	 * @return
	 * @throws IOException
	 * @author 陈阵 
	 * @date 2014-4-2 上午9:22:19
	 */
	private CheckConfig getCheckConfig(String checkConfigName) throws IOException{
		InputStream checkInputStream =  new ClassPathResource(BASE_URI+checkConfigName).getInputStream();
		xs.processAnnotations(CheckConfig.class);
		CheckConfig checkConfig = (CheckConfig)xs.fromXML(checkInputStream);
		return checkConfig;
	}
	
	/**
	 * 资源上传日志
	 * @param checkFlag
	 * @param requestParam
	 * @param checkResult
	 */
	private void saveResourceData(CheckResult checkResult,PResourceLog log){
		 if(checkResult.isSuccess()){
	          log.setCheckFlag(CheckFlag.SUCCEED.getCode());
		 }else{
			 log.setCheckFlag(CheckFlag.FAIL.getCode());
		 }
         log.setCheckDesc(checkResult.getErrorMessage());
         resourceLogService.saveResourceLog(log);
	}
	
}
