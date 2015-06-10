/**
 * ==================   =============   ============   =================================
 * Author               OperateType     Date           Description
 * ==================   =============   ============   =================================
 * 蔡祥龙              New             Aug 8, 2013      文档注册服务的实现类
 */
package com.zebone.register;

import java.util.Map;

import javax.annotation.Resource;

import com.zebone.register.dao.RegisterTempMapper;
import com.zebone.register.vo.*;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.springframework.beans.factory.annotation.Value;

import com.zebone.btp.core.util.DateUtil;
import com.zebone.btp.core.util.Identities;
import com.zebone.dip.log.service.DocExtInfoService;
import com.zebone.dip.log.service.LogServiceClient;
import com.zebone.dip.log.vo.Error;
import com.zebone.dip.log.vo.DocExtendInfo;
import com.zebone.dip.log.vo.Syslog;
import com.zebone.empi.service.EmpiService;
import com.zebone.register.dao.EmpiInfoMapper;
import com.zebone.register.dao.RegisterLogMapper;
import com.zebone.register.dao.RegisterMainMapper;
import com.zebone.register.util.XmlParser;
import org.springframework.stereotype.Service;

@Service("DocRegisterImpl")
public class DocRegisterImpl implements DocRegister {

	private static final Log log = LogFactory.getLog(DocRegisterImpl.class);
	
	@Resource
	private RegisterMainMapper registerMainMapper;
	@Resource
	private RegisterLogMapper registerLogMapper;
	@Resource
	private EmpiInfoMapper empiInfoMapper;
    @Resource
    private RegisterTempMapper registerTempMapper;

	@Value("${url.registerOrUpdate}")
	private String url;
	@Resource
	private LogServiceClient logServiceClient;

    //文档头中患者信息
    @Value("${patient_name}")
    private   String PATIENT_NAME ;
    @Value("${card_type}")
    private   String CARD_TYPE ;
    @Value("${card_no}")
    private   String CARD_NO ;
    @Value("${card_org}")
    private   String CARD_ORG ;

    //文档头中文档活动类信息
    @Value("${doc_no}")
    private   String DOC_NO ;
    @Value("${doc_type}")
    private   String DOC_TYPE ;
    @Value("${doc_version}")
    private   String DOC_VERSION ;
    @Value("${doc_title}")
    private   String DOC_TITLE ;

    //文档作者信息
    @Value("${doctor_code}")
    private   String DOCTOR_CODE ;
    @Value("${dept_code}")
    private   String DEPT_CODE ;

    //生成文档信息
    @Value("${doc_org}")
    private   String DOC_ORG;
    @Value("${operate_type}")
    private   String OPERATE_TYPE ;
    @Value("${sys_type}")
    private   String SYS_TYPE ;

    //文档管理信息
    @Value("${manage_org}")
    private   String MANAGE_ORG;

    //服务活动
    @Value("${active_time}")
    private   String ACTIVE_TIME;
    @Value("${health_code}")
    private   String HEALTH_CODE ;
    @Value("${icd_code}")
    private   String ICD_CODE ;

    //关联活动
    @Value("${parent_doc_no}")
    private   String PARENT_DOC_NO;

	/**
	 * 文档注册
	 * 
	 * @param xml
	 *            需要注册的文档
	 * @param docRegisterState
	 *            文档注册状态（1插入 2更新）
	 * @return String 返回注册标识和主索引标识
	 * @author caixl
	 * @since ug 8, 2013
	 */
	@Override
	public String DocumentRegistry_RegistryDocumentSet_b(String xml,
			String docRegisterState) {
		StringBuffer sf = new StringBuffer("<result><flag>0</flag><empiId></empiId></result>");
		
		//日志对象
		Syslog syslog = new Syslog();
		syslog.setCategory("1");
		syslog.setType("5");
		
		DocExtendInfo ro = DocExtInfoService.getExtInfo(xml);
		if(ro!=null){
			syslog.setLogID(ro.getUuid());
		}
		
		// 解析xml流
		ParamInfo paramInfo = getEmpiInfoByXML(xml);
		if (paramInfo != null && paramInfo.getEmpiInfo() != null){
			RegisterMain registerMain = paramInfo.getRegisterMain();
			registerMain.setDocState(docRegisterState);
			EmpiInfo empiInfo = paramInfo.getEmpiInfo();

			RegisterLog registerLog = new RegisterLog();
			registerLog.setDocDataSource(registerMain.getDocDataSource());
			registerLog.setDocNo(registerMain.getDocNo());
			registerLog.setDocOperState(registerMain.getDocOperState());
			registerLog.setDocOrg(registerMain.getDocOrg());
			registerLog.setDocState(registerMain.getDocState());
			registerLog.setDocTypeCode(registerMain.getDocTypeCode());
			registerLog.setDocWebUrl(registerMain.getDocWebUrl());
			registerLog.setEmpiId(registerMain.getEmpiId());
			registerLog.setDocVersion(registerMain.getDocVersion());
			registerLog.setId(Identities.uuid());
			registerLog.setRegisterTime(DateUtil.getCurrentDate("yyyyMMddHHmmss"));

			int result = 0;
			try{
				if ("1".equals(registerMain.getDocOperState())) {// 插入注册文档
					result = registerMainMapper.getRegisterCountByDocNo(registerMain.getDocNo());
					if (result > 0) {
						syslog.setStatus("0");
						Error error = new Error();
						error.setCode("R_002");
						error.setDesc("文档编号["+registerMain.getDocNo()+"]已经注册，不能再次注册");
						syslog.setError(error);
						syslog.setDocNo(registerMain.getDocNo());
						logServiceClient.saveLog(syslog);
						
						result = 0;
					} else {
						registerMain.setId(Identities.uuid());
						registerMain.setRegisterTime(DateUtil.getCurrentDate("yyyyMMddHHmmss"));
						result = registerMainMapper.insert(registerMain);
						if (result > 0) {
							empiInfo.setMainId(registerMain.getId());
							empiInfo.setId(Identities.uuid());
							result = empiInfoMapper.insert(empiInfo);
							if (result > 0) {
								registerLog.setMainId(registerMain.getId());
								result = registerLogMapper.insert(registerLog);
								if (result > 0) {
									sf.delete(0, sf.length());
									sf.append("<result><flag>1</flag><empiId>")
											.append(registerMain.getEmpiId())
											.append("</empiId></result>");
									
									syslog.setStatus("1");
									logServiceClient.saveLog(syslog);
								}
							}
						}
					}
				} else {// 更新注册文档
					String mainId = registerMainMapper.getMainIdByDocNo(registerMain.getDocNo());
					if (!StringUtils.isEmpty(mainId)) {
						registerMain.setId(mainId);
						result = registerMainMapper.update(registerMain);
						if (result > 0) {
							empiInfo.setMainId(mainId);
							result = empiInfoMapper.update(empiInfo);
							if (result > 0) {
								registerLog.setMainId(mainId);
								result = registerLogMapper.insert(registerLog);
								if (result > 0) {
									sf.delete(0, sf.length());
									sf.append("<result><flag>1</flag><empiId>")
											.append(registerMain.getEmpiId())
											.append("</empiId></result>");
									
									syslog.setStatus("1");
									logServiceClient.saveLog(syslog);
								}
							}
						}
					}else{
						syslog.setStatus("0");
						Error error = new Error();
						error.setCode("R_003");
						error.setDesc("文档编号["+registerMain.getDocNo()+"]不存在，不能更新注册");
						syslog.setError(error);
						syslog.setDocNo(registerMain.getDocNo());
						logServiceClient.saveLog(syslog);
					}
				}
			}catch(Exception e){
				
				log.error("注册异常", e);
				
				syslog.setStatus("0");
				Error error = new Error();
				error.setCode("R_999");
				error.setDesc("文档编号["+registerMain.getDocNo()+"]注册异常");
				syslog.setError(error);
				syslog.setDocNo(registerMain.getDocNo());
				logServiceClient.saveLog(syslog);
			}
		}else if (paramInfo != null && paramInfo.getRegisterTemp() != null){
            RegisterTemp registerTemp = paramInfo.getRegisterTemp();
            registerTemp.setId(Identities.uuid());
            registerTempMapper.insert(registerTemp);
        }
		return sf.toString();
	}

	public ParamInfo getEmpiInfoByXML(String xml) {
		StringBuilder empiInfoTemp = new StringBuilder();
		empiInfoTemp.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
		empiInfoTemp.append("<empi_info>");
        empiInfoTemp.append("<name>#name</name>");
        empiInfoTemp.append("<system_code>DRC</system_code>");  //"DRC"代表是注册中心
		empiInfoTemp.append("<card_list>");
		empiInfoTemp.append("<card>");
		empiInfoTemp.append("<no>#no</no>");
		empiInfoTemp.append("<type>#type</type>");
		empiInfoTemp.append("<org>#org</org>");				
		empiInfoTemp.append("</card>");
		empiInfoTemp.append("</card_list>");
		empiInfoTemp.append("</empi_info>");
		
		XmlParser xp = new XmlParser();
		Map<String, String> map = xp.getParamInfo(xml);
		ParamInfo paramInfo = new ParamInfo();
		
		//日志对象
		Syslog syslog = new Syslog();
		syslog.setCategory("1");
		syslog.setType(Syslog.TYPE_REGISTER);
		
		DocExtendInfo ro = DocExtInfoService.getExtInfo(xml);
		if(ro!=null){
			syslog.setLogID(ro.getUuid());
		}
		
		if (map!=null && !map.isEmpty()) {
			String empiInfoStr = empiInfoTemp.toString();
            if (map.get(PATIENT_NAME) != null && !"".equals(map.get(PATIENT_NAME))) {
                empiInfoStr = empiInfoStr.replaceAll("#name", map.get(PATIENT_NAME));
            }
            empiInfoStr = empiInfoStr.replaceAll("#no", map.get(CARD_NO));
			empiInfoStr = empiInfoStr.replaceAll("#type", map.get(CARD_TYPE));
			empiInfoStr = empiInfoStr.replaceAll("#org", map.get(CARD_ORG));
			
			JaxWsProxyFactoryBean jw = new JaxWsProxyFactoryBean();
			jw.setAddress(url);
			jw.setServiceClass(EmpiService.class);
			EmpiService empiService = (EmpiService) jw.create();

			String empiId = null;
			try {
				empiId = empiService.searchEmpiId(empiInfoStr);
			} catch (Exception e) {
                e.printStackTrace();
                log.error("获取empi标识失败", e);
                syslog.setStatus("0");
                Error error = new Error();
                error.setCode("R_001");
                error.setDesc("没有获取到empi标识");
                syslog.setError(error);
                syslog.setDocNo(map.get(DOC_NO));
                logServiceClient.saveLog(syslog);
                return paramInfo;
			}
			if (!StringUtils.isEmpty(empiId)) {
                if ("01|未注册".equals(empiId) || "02|未匹配".equals(empiId)
                        || "03|参数错误".equals(empiId) || "04|未知错误".equals(empiId)) {
                    syslog.setStatus("0");
                    Error error = new Error();
                    error.setCode("R_001");
                    error.setDesc("没有获取到empi标识");
                    syslog.setError(error);
                    syslog.setDocNo(map.get(DOC_NO));
                    logServiceClient.saveLog(syslog);
                    if ("01|未注册".equals(empiId) || "02|未匹配".equals(empiId)) {
                        RegisterTemp registerTemp = new RegisterTemp();
                        registerTemp.setDocNo(map.get(DOC_NO));
                        registerTemp.setRegisterTime(DateUtil.getCurrentDate("yyyyMMddHHmmss"));
                        registerTemp.setCardNo(map.get(CARD_NO));
                        registerTemp.setCardType(map.get(CARD_TYPE));
                        registerTemp.setCardOrg(map.get(CARD_ORG));
                        registerTemp.setName(map.get(PATIENT_NAME));
                        registerTemp.setSystemCode("A"); //A为自定义编码,代表存储中心A
                        paramInfo.setRegisterTemp(registerTemp);
                    }
                }else{
                    RegisterMain registerMain = new RegisterMain();
                    registerMain.setEmpiId(empiId);
                    registerMain.setDocDataSource(map.get(SYS_TYPE));
                    registerMain.setDocOrg(map.get(DOC_ORG));
                    registerMain.setDocTypeCode(map.get(DOC_TYPE));
                    registerMain.setDocNo(map.get(DOC_NO));
                    registerMain.setDocOperState(map.get(OPERATE_TYPE));
                    registerMain.setDocVersion(map.get(DOC_VERSION));
                    registerMain.setDocManageOrg(map.get(MANAGE_ORG));
                    registerMain.setDocTitle(map.get(DOC_TITLE));
//                    registerMain.setDocSerialNo(map.get("EVENT-EX00.00.001.81"));
                    registerMain.setParentDocNo(map.get(PARENT_DOC_NO));
                    paramInfo.setRegisterMain(registerMain);

                    EmpiInfo empiInfo = new EmpiInfo();
                    empiInfo.setDocDeptCode(map.get(DEPT_CODE));
                    empiInfo.setDocOrg(map.get(DOC_ORG));
                    empiInfo.setSystemType(map.get(SYS_TYPE));
                    empiInfo.setCardType(map.get(CARD_TYPE));
                    empiInfo.setCardNo(map.get(CARD_NO));
                    empiInfo.setName(map.get(PATIENT_NAME));
                    empiInfo.setDoctorCode(map.get(DOCTOR_CODE));
                    empiInfo.setIcdCode(map.get(ICD_CODE));
                    empiInfo.setHealthCode(map.get(HEALTH_CODE));
                    empiInfo.setActiveTime(map.get(ACTIVE_TIME));
                    empiInfo.setCardOrg(map.get(CARD_ORG));
                    paramInfo.setEmpiInfo(empiInfo);
                }
            }else{
				syslog.setStatus("0");
				Error error = new Error();
				error.setCode("R_001");
				error.setDesc("该empi标识并不存在");
				syslog.setError(error);
				syslog.setDocNo(map.get(DOC_NO));
				logServiceClient.saveLog(syslog);
			}
		}else{
			syslog.setStatus("0");
			Error error = new Error();
			error.setCode("R_999");
			error.setDesc("解析xml文档有误");
			syslog.setError(error);
			
			logServiceClient.saveLog(syslog);
		}
		return paramInfo;
	}
}
