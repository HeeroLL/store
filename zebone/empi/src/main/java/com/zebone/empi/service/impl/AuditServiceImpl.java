package com.zebone.empi.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.jws.WebService;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.naming.NoNameCoder;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.zebone.empi.dao.DcOpMapper;
import com.zebone.empi.dao.DocStorageLogMapper;
import com.zebone.empi.dao.DocStorageMapper;
import com.zebone.empi.dao.EmpiUnbindApplyMapper;
import com.zebone.empi.dao.EmpiUnbindLogMapper;
import com.zebone.empi.dao.ParseTableLogMapper;
import com.zebone.empi.dao.RegisterLogMapper;
import com.zebone.empi.dao.RegisterMainMapper;
import com.zebone.empi.dao.ResidentCardMapper;
import com.zebone.empi.dao.ResidentInfoMapper;
import com.zebone.empi.dao.ResidentUpdateApplyMapper;
import com.zebone.empi.service.AuditService;
import com.zebone.empi.service.BizLogService;
import com.zebone.empi.service.CheckerService;
import com.zebone.empi.service.ResidentInfoOperationState;
import com.zebone.empi.util.IDCardValidate;
import com.zebone.empi.util.UUIDUtil;
import com.zebone.empi.vo.AuditForUpdateRequest;
import com.zebone.empi.vo.AuditForUpdateResult;
import com.zebone.empi.vo.AuditForunBindResult;
import com.zebone.empi.vo.DocStorage;
import com.zebone.empi.vo.DocStorageExample;
import com.zebone.empi.vo.DocStorageLog;
import com.zebone.empi.vo.DocStorageLogExample;
import com.zebone.empi.vo.EmpiLog;
import com.zebone.empi.vo.EmpiUnbindApply;
import com.zebone.empi.vo.EmpiUnbindLog;
import com.zebone.empi.vo.EmpiUnbindRequest;
import com.zebone.empi.vo.ParseTableLog;
import com.zebone.empi.vo.ParseTableLogExample;
import com.zebone.empi.vo.RegisterLog;
import com.zebone.empi.vo.RegisterLogExample;
import com.zebone.empi.vo.RegisterMain;
import com.zebone.empi.vo.RegisterMainExample;
import com.zebone.empi.vo.RegisterMainExample.Criteria;
import com.zebone.empi.vo.ResidentCard;
import com.zebone.empi.vo.ResidentInfo;
import com.zebone.empi.vo.ResidentUpdateApply;
import com.zebone.empi.vo.UnbindException;

/**
 * 主索引审核服务接口实现类
 *
 * @author 杨英
 * @version 2014-8-4 上午9:08
 */
@Service("AuditServiceImpl")
@WebService
public class AuditServiceImpl implements AuditService{

    private static final Log log = LogFactory.getLog(AuditServiceImpl.class);
    
    @Resource
    private BizLogService bizLogService;

    @Resource
    private CheckerService checkerService;

    @Resource
    private ResidentInfoMapper residentInfoMapper;

    @Resource
    private ResidentCardMapper residentCardMapper;

    @Resource
    private ResidentUpdateApplyMapper updateApplyMapper;
      
	@Resource
	private EmpiUnbindLogMapper empiUnbindLogMapper;
	
	@Resource
	private RegisterLogMapper registerLogMapper;
	
	@Resource
	private RegisterMainMapper registerMainMapper;
	
	@Resource
	private DocStorageMapper docStorageMapper;	
	
	@Resource
	private DocStorageLogMapper docStorageLogMapper;
	
	@Resource
	private ParseTableLogMapper parseTableLogMapper;
	
	@Resource
	private DcOpMapper dcOpMapper;
	
	@Resource
	private EmpiUnbindApplyMapper empiUnbindApplyMapper;
	
    @Override
    public String auditForUpdate(String paramXml) throws Exception {
        XStream  xs = new XStream(new DomDriver("UTF-8", new NoNameCoder()));
        String returnInfo;
        String auditResult;
        AuditForUpdateResult auditForUpdateResult = new AuditForUpdateResult();
        EmpiLog empiLog = new EmpiLog();
        empiLog.setInfo_XMLDoc(paramXml);
        boolean isSuccess = true;
        try {
            boolean xsdCheck = checkerService.xsdCheckForApply(paramXml, empiLog,"实名变更审核");
            if (xsdCheck) {  //参数结构校验通过
                AuditForUpdateRequest auditUpdateReq = (AuditForUpdateRequest) getRequestParObj(paramXml,xs);
                String cardNo = auditUpdateReq.getCardNo();
                String strIdValidate = "";
                //身份证有效性的校验
                strIdValidate = IDCardValidate.IDCardValidate(cardNo);
                if (!"".equals(strIdValidate)) { //身份证不合法
                    isSuccess = false;
                    auditForUpdateResult.setErrorCode("04"); //参数值域错误
                    auditForUpdateResult.setErrorMsg(strIdValidate);
                } else {
                    ResidentUpdateApply updateApply = new ResidentUpdateApply();
                    BeanUtils.copyProperties(updateApply, auditUpdateReq);
                    if(auditUpdateReq.getStrPhoto()!=null){
                        updateApply.setPhoto(auditUpdateReq.getStrPhoto().getBytes());
                    }
                    String strBirthday = auditUpdateReq.getStrBirthday();
                    if(strBirthday!=null){
                        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
                        updateApply.setBirthday(df.parse(strBirthday));
                    }
                    auditResult = auditUpdateReq.getAuditResult();
                    String empi = residentCardMapper.selectByFirstLevelCardId(cardNo);
                    updateApply.setEmpi(empi);

                    //填充日志信息
                    empiLog.setCardNo(cardNo);
                    empiLog.setResidentName(updateApply.getName());
                    empiLog.setOrgCode(updateApply.getOrgCode());
                    empiLog.setCardType("1");
                    empiLog.setEmpi(empi);

                    if ("1".equals(auditResult)) { //1 允许更新
                        empiLog.setSource("7"); //允许申请事件源
                        empiLog.setTarget("7");//事件目标 允许申请
                        empiLog.setEventNameCode("14");//允许申请

                        updateApply.setAuditStatus("2"); //"2"表示审核通过
                        ResidentInfo residentInfo = new ResidentInfo();
                        BeanUtils.copyProperties(residentInfo, updateApply);
                        residentInfo.setModifiedTime(new Date());
                        residentInfo.setDeptCode(updateApply.getOrgCode());
                        byte[] photo = residentInfo.getPhoto();
                        if (photo == null || photo.length == 0) {
                            residentInfo.setPhoto(null);
                        }
                        residentInfoMapper.updateResidentInfoByEmpi(residentInfo); //更新实名信息
                        bizLogService.log(residentInfo, ResidentInfoOperationState.Update);    //插入实名信息更新日志记录
                        updateApplyMapper.updateAuditStatus(updateApply);
                    } else if ("2".equals(auditResult)) { //2 拒绝更新
                        empiLog.setSource("8"); //拒绝申请事件源
                        empiLog.setTarget("8");//事件目标 拒绝申请
                        empiLog.setEventNameCode("15");//拒绝申请

                        updateApply.setAuditStatus("3");   //"3"表示审核未通过
                        updateApplyMapper.updateAuditStatus(updateApply);
                    }
                }
            }else{
                isSuccess = false;
                auditForUpdateResult.setErrorCode("03");
                auditForUpdateResult.setErrorMsg("xml参数结构错误");
            }
        } catch (Exception e) {
            isSuccess = false;
            auditForUpdateResult.setErrorCode("05");
            auditForUpdateResult.setErrorMsg("系统出现异常");
            log.error(e.getMessage(), e);
        } finally {
            if (isSuccess) {
                auditForUpdateResult.setSuccess("1");  // 1 表示处理成功
            } else {
                auditForUpdateResult.setSuccess("2");  //2 表示处理失败
            }
            insertAuditLog(isSuccess,empiLog,auditForUpdateResult); //插入日志记录
            xs.processAnnotations(AuditForUpdateResult.class);
            returnInfo = xs.toXML(auditForUpdateResult);
        }
        return returnInfo;
    }
    
    
    

    /**
     * 插入审核日志
     *
     * @param
     * @throws Exception
     */
    public void insertAuditLog(boolean flag, EmpiLog empiLog, AuditForUpdateResult auditForUpdateResult) throws Exception {
        empiLog.setEventTime(new Date());
        if (flag) {//处理成功
            empiLog.setResultCode("1");
        } else {//处理失败
            empiLog.setResultCode("2");
            empiLog.setErrorCategory("9"); //审核出现错误
            empiLog.setErrorPosition(auditForUpdateResult.getErrorMsg());
        }
        try {
        	bizLogService.log(empiLog);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new Exception(e);
        }
    }

    /**
     * 获取请求参数参数对象
     *
     * @param documentXML
     * @return
     */
    private Object getRequestParObj(String documentXML,XStream xs) {
        try {
            xs.processAnnotations(AuditForUpdateRequest.class);
            AuditForUpdateRequest auditForUpdateRequest = (AuditForUpdateRequest) xs.fromXML(documentXML);
            return auditForUpdateRequest;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return null;
        }
    }


    
    
    /**
     * EMPI解绑
     */
	@Override
	public String auditForunBind(String paramXml) {
			XStream  xs = new XStream(new DomDriver("UTF-8", new NoNameCoder()));
			AuditForunBindResult result = new AuditForunBindResult();
			EmpiUnbindRequest empiUnbindRequest = null;
			Map<String,String> empiMap = new HashMap<String, String>();
			
			EmpiLog empiLog = new EmpiLog();
			empiLog.setSource("9");
			empiLog.setTarget("9");
			empiLog.setResultCode("1");
            empiLog.setEventNameCode("18");
		    empiLog.setInfo_XMLDoc(paramXml);
			
			/** 解析xml to bean **/
            try{
            	empiUnbindRequest = parseAuditForunBindPara(paramXml,xs);
            }catch(UnbindException e){
            	log.error(e.getMessage(),e);
            	result.setSuccess("2");
            	result.setErrorMsg("系统错误");
            	xs.processAnnotations(AuditForunBindResult.class);
            	return xs.toXML(result);
            }
            
            String auditResult =  empiUnbindRequest.getAuditResult();
            
            if ("1".equals(auditResult)) {
			/** 更新旧卡注册的empi **/
			try {
				empiMap = updateSecCardErrorEMPI(empiUnbindRequest,xs);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				log.error(e.getMessage(), e);
				fullEmpiLog(empiUnbindRequest,empiLog,empiMap);
				empiLog.setResultCode("2");
				empiLog.setErrorPosition(e.getMessage());
				result.setSuccess("2");
				result.setErrorMsg("系统错误");
				xs.processAnnotations(AuditForunBindResult.class);
				return xs.toXML(result);
			}finally{
				bizLogService.log(empiLog);
			}

			
			/** 更新注册相关 **/
			try {
				updateWrc(empiMap,xs);
			} catch (Exception e) {
				log.error(e.getMessage(), e);
				empiLog.setErrorPosition(e.getMessage());
				result.setSuccess("2");
				result.setErrorMsg("系统错误");
				xs.processAnnotations(AuditForunBindResult.class);
				return xs.toXML(result);
			}finally{
				bizLogService.log(empiLog);
			}
			
			/** 更新存储相关 **/

			try {
				updateDcStorage(empiMap,xs);
			} catch (Exception e) {
				log.error(e.getMessage(), e);
				empiLog.setErrorPosition(e.getMessage());

				result.setSuccess("2");
				result.setErrorMsg("系统错误");
				xs.processAnnotations(AuditForunBindResult.class);
				return xs.toXML(result);
			}finally{
				bizLogService.log(empiLog);
			}
			
			try {
				updateDc(empiMap,xs);
			} catch (Exception e) {
				log.error(e.getMessage(), e);
				empiLog.setErrorPosition(e.getMessage());
				
				result.setSuccess("2");
				result.setErrorMsg("系统错误");
				xs.processAnnotations(AuditForunBindResult.class);
				return xs.toXML(result);
			}finally{
				bizLogService.log(empiLog);
			}
			
			try{
				EmpiUnbindApply eua = new EmpiUnbindApply();
				eua.setAuditDesc(empiUnbindRequest.getAuditDesc());
				eua.setAuditStatus("2");
				eua.setSecCardNo(empiUnbindRequest.getSecCardNo());
				eua.setSecCardOrg(empiUnbindRequest.getSecCardOrg());
				eua.setSecCardType(empiUnbindRequest.getSecCardType());
				empiUnbindApplyMapper.updateUnbindAuditStatus(eua);
			}catch(Exception e){
				log.error(e.getMessage(), e);
				empiLog.setErrorPosition(e.getMessage());

				result.setSuccess("2");
				result.setErrorMsg("系统错误");
				xs.processAnnotations(AuditForunBindResult.class);
				return xs.toXML(result);
			}finally{
				bizLogService.log(empiLog);
			}
		}else if("2".equals(auditResult)){
			try{
				EmpiUnbindApply eua = new EmpiUnbindApply();
				eua.setAuditDesc(empiUnbindRequest.getAuditDesc());
				eua.setAuditStatus("3");
				eua.setSecCardNo(empiUnbindRequest.getSecCardNo());
				eua.setSecCardOrg(empiUnbindRequest.getSecCardOrg());
				eua.setSecCardType(empiUnbindRequest.getSecCardType());
				empiUnbindApplyMapper.updateUnbindAuditStatus(eua);
			}catch(Exception e){
				log.error(e.getMessage(), e);
				empiLog.setErrorPosition(e.getMessage());
				
				result.setSuccess("2");
				result.setErrorMsg("系统错误");
				xs.processAnnotations(AuditForunBindResult.class);
				return xs.toXML(result);
			}finally{	
				empiLog.setSource("8");
				empiLog.setTarget("8");
				empiLog.setEventNameCode("17");
				empiLog.setResultCode("2");
				bizLogService.log(empiLog);
			}
		}
        bizLogService.log(empiLog);

		xs.processAnnotations(AuditForunBindResult.class);
		result.setSuccess("1");
		return xs.toXML(result);
	}
    

	
	/**
	 * 
	 * @param paraXml
	 * @return
	 * @throws Exception
	 * @author 陈阵 
	 * @date 2014-8-8 上午9:54:25
	 */
	private EmpiUnbindRequest parseAuditForunBindPara(String paraXml,XStream xs) throws UnbindException{
		EmpiUnbindRequest empiUnbindRequest = null;
		try {
			xs = new XStream(new DomDriver("UTF-8", new NoNameCoder()));
			xs.processAnnotations(EmpiUnbindRequest.class);
			empiUnbindRequest = (EmpiUnbindRequest) xs.fromXML(paraXml);
		}catch(Exception e){
		   throw new UnbindException("prase-xml", e);
		}
		return empiUnbindRequest;
	}
	
	
	/** 
	 * 更新错误二级卡绑定的empi
	 * @param empiUnbindRequest
	 * @author 陈阵 
	 * @throws ParseException 
	 * @date 2014-8-8 上午10:42:24
	 */
	private Map<String, String> updateSecCardErrorEMPI(EmpiUnbindRequest empiUnbindRequest,XStream xs) throws Exception{
		    String empi = null;
		    Map<String,String> para = new HashMap<String, String>();    
		
			String secCardNo = empiUnbindRequest.getSecCardNo();
			String secCardType = empiUnbindRequest.getSecCardType();
			
			ResidentCard residentCardQuery = new ResidentCard();
			residentCardQuery.setCardNo(secCardNo);
			residentCardQuery.setCardType(secCardType);
			
			List<ResidentCard> secResidentCardList = residentCardMapper.selectByCodeAndType(residentCardQuery);
			empi = secResidentCardList.get(0).getEmpi();
			
			xs.processAnnotations(List.class);
			for(ResidentCard residentCard : secResidentCardList){
				residentCardQuery.setCardNo(residentCard.getCardNo());
				residentCardQuery.setCardType(residentCard.getCardType());
				residentCardMapper.delByCodeAndType(residentCardQuery);
			}	
            String opeData = xs.toXML(secResidentCardList);			
			EmpiUnbindLog empiUnbindLog = new EmpiUnbindLog();
			empiUnbindLog.setEmpi(empi);
			empiUnbindLog.setOpeData(opeData);
			empiUnbindLog.setTableName("E_RESIDENT_CARD");
			empiUnbindLog(empiUnbindLog);
			
			
			/**
			residentInfoMapper.deleteResidentInfoByEmpi(empi);
			empiUnbindLog.setOpeData(opeData);
			empiUnbindLog.setTableName("E_RESIDENT_INFO");
			empiUnbindLog(empiUnbindLog);
			**/
              
			para.put("empi", empi);			
			return para;
		
	}
	
	/**
	 * 更新注册相关
	 * @param empiPara
	 * @throws Exception
	 * @author 陈阵 
	 * @date 2014-8-13 上午9:23:32
	 */
	private void updateWrc(Map<String,String> empiPara,XStream xs) throws Exception{
		String empi = empiPara.get("empi");
		
		RegisterMainExample rme = new RegisterMainExample();
		Criteria query = rme.createCriteria();
		query.andEmpiIdEqualTo(empi);
		List<RegisterMain> rmList = registerMainMapper.selectByExample(rme);
		
		registerMainMapper.deleteByExample(rme);
		if(rmList.size() > 0){
			xs.processAnnotations(RegisterMain.class);
			for(RegisterMain rmt :rmList){
				EmpiUnbindLog empiUnbindLog = new EmpiUnbindLog();
				empiUnbindLog.setEmpi(empi);
				empiUnbindLog.setTableName("R_REGISTER_MAIN");
				empiUnbindLog.setOpeData(xs.toXML(rmt));
				empiUnbindLog(empiUnbindLog);
			}
		}
		
		RegisterLogExample rle = new RegisterLogExample();
		com.zebone.empi.vo.RegisterLogExample.Criteria queryrl = rle.createCriteria();
		queryrl.andEmpiIdEqualTo(empi);
		List<RegisterLog> rlList = registerLogMapper.selectByExample(rle);
		
		registerLogMapper.deleteByExample(rle);
		
		xs.processAnnotations(RegisterLog.class);
		if(rlList.size() > 0){
			for(RegisterLog rlt : rlList){
				EmpiUnbindLog empiUnbindLog = new EmpiUnbindLog();
				empiUnbindLog.setEmpi(empi);
				empiUnbindLog.setOpeData(xs.toXML(rlt));
				empiUnbindLog.setTableName("R_REGISTER_LOG");
				empiUnbindLog(empiUnbindLog);
			}
		}
			
	}
	
	/**
	 * 修改dc存储相关数据
	 * @param empiPara
	 * @throws Exception
	 * @author 陈阵 
	 * @date 2014-8-13 上午11:00:13
	 */
	private void updateDcStorage(Map<String,String> empiPara,XStream xs) throws Exception{
		String empi = empiPara.get("empi");
		
		DocStorageExample rse = new DocStorageExample();
		com.zebone.empi.vo.DocStorageExample.Criteria queryDs = rse.createCriteria();
		queryDs.andEmpiIdEqualTo(empi);
		List<DocStorage> dsList = docStorageMapper.selectByExample(rse);
		
		DocStorage ds = new DocStorage();
		ds.setEmpiId(empi);
		docStorageMapper.deleteByExample(rse);
		
		if(dsList.size() > 0){
			xs.processAnnotations(DocStorage.class);
			for(DocStorage dst :dsList){
				EmpiUnbindLog empiUnbindLog = new EmpiUnbindLog();
				empiUnbindLog.setEmpi(empi);
				empiUnbindLog.setOpeData(xs.toXML(dst));
				empiUnbindLog.setTableName("D_DOC_STORAGE");
				empiUnbindLog(empiUnbindLog);
			}
		}
		
		DocStorageLogExample dsle = new DocStorageLogExample();
		com.zebone.empi.vo.DocStorageLogExample.Criteria queryDsl =  dsle.createCriteria();
		queryDsl.andEmpiIdEqualTo(empi);
		List<DocStorageLog> dslList = docStorageLogMapper.selectByExample(dsle);
		
		docStorageLogMapper.deleteByExample(dsle);
		
		if(dslList.size() > 0){
			xs.processAnnotations(DocStorageLog.class);
			for(DocStorageLog dslt : dslList){
				EmpiUnbindLog empiUnbindLog = new EmpiUnbindLog();
				empiUnbindLog.setEmpi(empi);
				empiUnbindLog.setOpeData(xs.toXML(dslt));
				empiUnbindLog.setTableName("D_DOC_STORAGE_LOG");
				empiUnbindLog(empiUnbindLog);
			}
		}		
	}
	
	/**
	 * 更新业务表的数据
	 * @param empiPara
	 * @throws Exception
	 * @author 陈阵 
	 * @date 2014-8-13 下午1:41:47
	 */
	private void updateDc(Map<String,String> empiPara,XStream xs) throws Exception{
		String empi = empiPara.get("empi");
		
		ParseTableLogExample ptle = new ParseTableLogExample();
		com.zebone.empi.vo.ParseTableLogExample.Criteria queryPtl = ptle.createCriteria();
		queryPtl.andEmpiNoEqualTo(empi);
		List<ParseTableLog> ptlList = parseTableLogMapper.selectByExample(ptle);
				
		if(ptlList.size() > 0){
			xs.processAnnotations(Map.class);
			for(ParseTableLog ptl :ptlList){
			    String tableName = ptl.getTableName();
			    String empiNo = ptl.getEmpiNo();
			    String docNo = ptl.getDocNo();
			    
			    Map<String,String> dataMap = dcOpMapper.getDcData(tableName, empiNo,docNo);			    
			    dcOpMapper.delDcData(tableName, empiNo, docNo);
			    String opeData = xs.toXML(dataMap);
			    EmpiUnbindLog empiUnbindLog = new EmpiUnbindLog();
				empiUnbindLog.setEmpi(empiNo);
				empiUnbindLog.setOpeData(opeData);
				empiUnbindLog.setTableName(tableName);		    
			}
		}
	}
    
    

	/**
	 * 解绑数据记录
	 * @author 陈阵 
	 * @date 2014-10-24 上午9:55:18
	 */
	private void empiUnbindLog(EmpiUnbindLog empiUnbindLog){
		empiUnbindLog.setId(UUIDUtil.getUuid());
		empiUnbindLogMapper.insert(empiUnbindLog);
	}
	
	
	/**
	 * 填充日志
	 * @param er
	 * @param el
	 * @return
	 * @author 陈阵 
	 * @date 2014-10-24 下午2:06:28
	 */
	private void fullEmpiLog(EmpiUnbindRequest er, EmpiLog el, Map<String,String> empiMap){
		el.setCardNo(er.getSecCardNo());
        el.setResidentName(er.getName());
        el.setOrgCode(er.getOrgCode());
        el.setCardType(er.getSecCardType());
        el.setEmpi(empiMap.get("empi"));
        el.setEventTime(new Date());
	}
    
    
}
