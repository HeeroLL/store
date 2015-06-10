package com.zebone.empi.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.zebone.empi.check.CheckConfig;
import com.zebone.empi.check.ValidateResult;
import com.zebone.empi.check.component.SchemaCheck;
import com.zebone.empi.service.ValueCheckService;
import com.zebone.empi.util.VCheck;
import com.zebone.empi.vo.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import com.zebone.empi.dao.ResidentCardMapper;
import com.zebone.empi.dao.ResidentInfoMapper;
import com.zebone.empi.service.BizLogService;
import com.zebone.empi.service.CheckerService;
import com.zebone.empi.util.CheckCard;
import com.zebone.empi.util.ObjectReflect;

/**
 * 验证实现类
 * @author YinCm
 * @version 2013-7-31 下午10:10:20
 */
@Service
public class CheckerServiceImpl implements CheckerService {

    private XStream xs;
    private static final String BASE_URI = "com/zebone/empi/xsd/";

	@Resource
	private ResidentCardMapper residentCardMapper;
	
	@Resource
	private ResidentInfoMapper residentInfoMapper;
	
	@Resource
	private BizLogService bizLogService;

    @Resource
    private ValueCheckService valueCheckService;

    //主索引注册时二级标识是否是必须的
    @Value("${secondCardMandatory}")
    private String secondCardMandatory;
	
	
	@Override
	public CheckResult doCheck(ResidentInfo residentInfo, EmpiLog empiLog, String xmlString) throws Exception{
		
		CheckResult checkResult = new CheckResult();
		//TODO validate the residentInfo fields
	/**	//字段格式验证，不通过记录日志并返回，通过则继续
		boolean validatePassed = validateFieldValues(residentInfo, empiLog);
     */
        //xsd校验，不通过记录日志并返回，通过则继续
        boolean validatePassed = xsdCheck(xmlString,empiLog);
		boolean idCardPassed = CheckCard.CheckIDCard(residentInfo,empiLog);
		
		if(!validatePassed || !idCardPassed){
			checkResult.setResultCode(0);
			return checkResult;
		}
		
		if(empiLog.getErrorPosition()!=null && !empiLog.getErrorPosition().trim().isEmpty()){
			checkResult.setResultCode(0);
			return checkResult;
		}
		//数据有效性校验
		CheckResult validateResult = validateDataConsistency(residentInfo, empiLog);
		
		return validateResult;
	}

	 
	
	/**
	 * 检验数据一致性
	 * @param residentInfo
	 * @return 0:未通过，1:通过且不存在, 2:通过且已经存在
	 */
	private CheckResult validateDataConsistency(ResidentInfo residentInfo, EmpiLog empiLog){
		CheckResult checkResult = new CheckResult();
		List<ResidentCard> updateCardList = new ArrayList<ResidentCard>();
		List<ResidentCard> insertCardList = new ArrayList<ResidentCard>();
		checkResult.setResidentCardListInsert(insertCardList);
		checkResult.setResidentCardListUpdate(updateCardList);
		
		//寻找一级标识
		List<ResidentCard> rcList = residentInfo.getCardList();  //从上传文档中获取标识信息列表
		ResidentCard rcFirst = null;
        List<ResidentCard> rcFirstLic = new ArrayList<ResidentCard>();
        ResidentCard rcOther = null;
		for(ResidentCard rc : rcList){
			if(rc.getCardType().trim().equals("1")||rc.getCardType().trim().equals("2")||rc.getCardType().trim().equals("3")){
				rc.setCardLevel("1");
				rc.setCardOrg("A1");
				rcFirst = rc;
                rcFirstLic.add(rc);
			}else{
                rcOther = rc;
            }
		}
		if(rcFirst==null){
			/**
			 * 一级标识不存在
			 */
			empiLog.setErrorPosition("一级标识不存在！");
			checkResult.setResultCode(0);
			return checkResult;
		}
		//1.查询一级标识，如果不存在继续执行1.2；如果存在，继续执行1.1
        List<ResidentCard> firstRcList = new ArrayList<ResidentCard>();
        for(ResidentCard rcFirst1 : rcFirstLic){
            firstRcList = residentCardMapper.selectByCodeAndTypeAndDept(rcFirst1);
            if(firstRcList.size()>0){
                break;
            }
        }
		if(firstRcList.size()==0){//一级标识存在不存在
			//1.2.1 遍历每个二级card，用其cardcode和codetype获取card
			//1.2.2 如果获取的card集合不为空，则返回0。否则返回1
			//TODO
			//如果类型为8，需要比较姓名

            //此为empi注册分支，注册的时候文档中必须要包含二级标识
            if (rcOther == null && "true".equals(secondCardMandatory)) {
                empiLog.setErrorPosition("二级标识不存在！");
                checkResult.setResultCode(0);
                return checkResult;
            }

			for(ResidentCard rcTemp : rcList){
				List<ResidentCard> secondLevelRc=null;
				if(rcTemp.getCardLevel()!=null && rcTemp.getCardLevel().equals("8")){
					secondLevelRc = residentCardMapper.selectByCodeAndTypeAndDeptAndName(rcTemp);
				}else if(rcTemp.getCardLevel()==null || !rcTemp.getCardLevel().equals("1")){
				    secondLevelRc = residentCardMapper.selectByCodeAndTypeAndDept(rcTemp);
				}
				if(secondLevelRc!=null && secondLevelRc.size()>0){
					//0:未通过
					empiLog.setErrorPosition(rcTemp.getCardNo());
					empiLog.setEventTime(new Date());
					checkResult.setResultCode(0);
					return checkResult;
				}
			}
			//1:通过且不存在
			checkResult.setResultCode(1);
			return checkResult;
		}else{//一级标识存在
			ResidentCard firstRc = firstRcList.get(0);
			//1.1.1.通过一级标识得到empi
			 String empi = firstRc.getEmpi();
			//1.1.2.通过empi得到name
			 ResidentInfo residentInfoTemp = residentInfoMapper.selectResidentInfoByEmpi(empi);
			 String empiName = residentInfoTemp.getName().trim();
			//1.1.3.对比name,相同继续执行; 不同返回0，记录日志.
			 if(!empiName.equals(residentInfo.getName().trim())){
				 //0:未通过
				 empiLog.setErrorPosition(residentInfo.getName()+"：该用户卡号已经存在，但是姓名与数据库中不同！");
				 empiLog.setEventTime(new Date());
				 empiLog.setCardNo(rcFirst.getCardNo());
				 empiLog.setCardType(rcFirst.getCardType());
				 checkResult.setResultCode(0);
				 return checkResult;
			 }
			//1.1.4.通过empi得到数据库中card集合
			List<ResidentCard> dbCardList = residentCardMapper.selectByEmpi(empi);
			//1.1.5.二级card如果存在数据库，则查看是否是同一个，如果是则加入更新，如果不是返回冲突,其余都忽略；如果不存在，则插入//通过比对同类型下cardcode，如果有冲突，返回0；否则返回2
			for(ResidentCard rcw : residentInfo.getCardList()){
				List<ResidentCard> secondLevelRc=null;
				if(rcw.getCardLevel()!=null && rcw.getCardLevel().equals("8")){
					secondLevelRc = residentCardMapper.selectByCodeAndTypeAndDeptAndName(rcw);
				}else{
					secondLevelRc = residentCardMapper.selectByCodeAndTypeAndDept(rcw);
					//原需求，根据原有card是否一致
					/*String rcwCardType = rcw.getCardType().trim();
					String rcwCardNo = rcw.getCardNo().trim();
					//将数据库中的比对
					for(ResidentCard rcd : dbCardList){
						//类型相同，但是值不同即为冲突（是否考虑，不同地域的同一类型卡，卡号可能冲突）
						if(rcd.getCardType().trim().equals(rcwCardType) && !rcd.getCardNo().trim().equals(rcwCardNo)){
							 //0:未通过
						 	 empiLog.setErrorPosition(rcw.getCardNo());
							 empiLog.setEventTime(new Date());
							 empiLog.setCardNo(rcw.getCardNo());
							 empiLog.setCardType(rcw.getCardType());
							 checkResult.setResultCode(0);
							 return checkResult;
						}
					}*/
				}
				rcw.setEmpi(empi);
				rcw.setCreateDate(new Date());
				if(secondLevelRc!=null && secondLevelRc.size()>0){
					ResidentCard rcd = secondLevelRc.get(0);
					rcw.setId(rcd.getId());
					//空值更新
					ObjectReflect.updateEmpty(rcd, rcw);
					if(rcd.getEmpi().equals(empi)){
						checkResult.getResidentCardListUpdate().add(rcd);
					}else{
						checkResult.setResultCode(0);
                        empiLog.setErrorPosition(rcd.getCardNo()+": 该卡已被注册。");
						return checkResult;
					}
				}else{
                    rcw.setDeptCode(residentInfo.getDeptCode());
                    rcw.setOperState("2"); //empi更新操作
					checkResult.getResidentCardListInsert().add(rcw);
				}
			}
			//2:通过且已经存在
			empiLog.setEmpi(empi);
			empiLog.setEventTime(new Date());
			checkResult.setResultCode(2);
			return checkResult;
		}
	}
	
	/**
	 * 字段格式验证
	 * @param residentInfo
	 */
	@Override
	public boolean validateFieldValues(ResidentInfo residentInfo, EmpiLog empiLog) {
		 
		boolean isPassed = true;
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
	    Validator validator = factory.getValidator();
	    Set<ConstraintViolation<ResidentInfo>> violations = validator.validate(residentInfo);
	    for(Iterator<ConstraintViolation<ResidentInfo>> it = violations.iterator();it.hasNext();){
	    	ConstraintViolation<ResidentInfo> cvrc = it.next();
	    	isPassed = false;
	    	if(empiLog!=null){
		    	String errorString =empiLog.getErrorPosition()==null?cvrc.getMessage():(empiLog.getErrorPosition().isEmpty()?cvrc.getMessage():(empiLog.getErrorPosition()+","+cvrc.getMessage()));
		    	empiLog.setErrorPosition(errorString);
	    	}
	    }
	    for(ResidentCard rc : residentInfo.getCardList()){
	    	 Validator cardValidator = factory.getValidator();
			 Set<ConstraintViolation<ResidentCard>> cardViolations = cardValidator.validate(rc);
			 for(Iterator<ConstraintViolation<ResidentCard>> it = cardViolations.iterator();it.hasNext();){
			    	ConstraintViolation<ResidentCard> cvrc = it.next();
			    	isPassed = false;
			    	if(empiLog!=null){
				    	String errorString = empiLog.getErrorPosition()==null ?cvrc.getMessage():(empiLog.getErrorPosition().isEmpty()?cvrc.getMessage():(empiLog.getErrorPosition()+","+cvrc.getMessage()));
				    	empiLog.setErrorPosition(errorString);
			    	}
			 }
			 
	    }
	    return isPassed;
	}

	
	/**
	 * 通过cardNo和居民姓名查询记录是否存在
	 * @param name Resident Name
	 * @return 是否存在
	 */
	@Override
	public boolean seachByCardNoAndName(String cardNo, String name){
		if(cardNo==null || name==null){
			return false;
		}
		
		String empiId = residentCardMapper.selectByFirstLevelCardId(cardNo);
		if(empiId==null || empiId.trim().equals("")){
			return false;
		}
		ResidentInfo ri = residentInfoMapper.selectResidentInfoByEmpi(empiId);
		String rsName = ri.getName();
		if(name.trim().equals(rsName.trim())){
			return true;
		}else{
			return false;
		}
		
	}

    /**
     * xsd校验
     *
     * @param xmlString empi注册的xml格式字符串
     */
    public boolean xsdCheck(String xmlString, EmpiLog empiLog) throws Exception {
        xs = new XStream(new DomDriver());
        String schemaFile = "EmpiRegister.xsd";
        String checkConfigFile = "EmpiRegisterCheck.xml";
        boolean flag = false;
        try {
            InputStream inputStream = getSchemaInputStream(schemaFile);
            //注册文档结构校验
            ValidateResult validateResult = new SchemaCheck().check(xmlString, inputStream);
            if (validateResult.isSuccess()) { //结构校验成功
                xs.processAnnotations(EmpiRegisterInfo.class);
                EmpiRegisterInfo empiRegisterInfo = (EmpiRegisterInfo) xs.fromXML(xmlString);
                CheckConfig checkConfig = getCheckConfig(checkConfigFile);
                String checkMessage = new VCheck().check(checkConfig, empiRegisterInfo,valueCheckService);
                if (checkMessage != null && checkMessage.length() > 0) {
                    empiLog.setErrorPosition(checkMessage);
                } else {
                    flag = true;
                }
            } else {
                empiLog.setErrorPosition(validateResult.getErrorMessage());
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return flag;
    }

    /**
     * 申请参数xsd校验
     * @param xmlString
     */
    public boolean xsdCheckForApply(String xmlString, EmpiLog empiLog, String methodName) throws Exception {
        xs = new XStream(new DomDriver());
        String schemaFile = "";
        String checkConfigFile = "";
        Object obj = new Object();
        boolean flag = false;
        if ("解绑".equals(methodName)) {
            schemaFile = "UnbindApply.xsd";
            checkConfigFile = "UnbindApplyCheck.xml";
            xs.processAnnotations(ApplyUnbindRequest.class);
            obj = (ApplyUnbindRequest) xs.fromXML(xmlString);
        } else if ("实名变更".equals(methodName)) {
            schemaFile = "UpdateApply.xsd";
            checkConfigFile = "UpdateApplyCheck.xml";
            xs.processAnnotations(ApplyUpdateReq.class);
            obj = (ApplyUpdateReq) xs.fromXML(xmlString);
        } else if("实名变更审核".equals(methodName)){
            schemaFile = "UpdateApply.xsd";
            checkConfigFile = "UpdateApplyCheck.xml";
            xs.processAnnotations(AuditForUpdateRequest.class);
            obj = (AuditForUpdateRequest) xs.fromXML(xmlString);
        }
        try {
            InputStream inputStream = getSchemaInputStream(schemaFile);
            //参数结构校验
            ValidateResult validateResult = new SchemaCheck().check(xmlString, inputStream);
            if (validateResult.isSuccess()) { //结构校验成功
                CheckConfig checkConfig = getCheckConfig(checkConfigFile);
                String checkMessage = new VCheck().check(checkConfig, obj,valueCheckService);
                if (checkMessage != null && checkMessage.length() > 0) {
                    empiLog.setErrorPosition(checkMessage);
                } else {
                    flag = true;
                }
            } else {
                empiLog.setErrorPosition(validateResult.getErrorMessage());
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return flag;
    }

    /**
     * 获取校验配置对象
     *
     * @param checkConfigName
     * @return
     */
    private CheckConfig getCheckConfig(String checkConfigName) throws IOException {
        InputStream checkInputStream = new ClassPathResource(BASE_URI + checkConfigName).getInputStream();
        xs.processAnnotations(CheckConfig.class);
        CheckConfig checkConfig = (CheckConfig) xs.fromXML(checkInputStream);
        return checkConfig;
    }

    /**
     * @param schemaName
     * @return
     * @throws IOException
     */
    private InputStream getSchemaInputStream(String schemaName) throws IOException {
        InputStream xsdInputStream = new ClassPathResource(BASE_URI + schemaName).getInputStream();
        return xsdInputStream;
    }

	public ResidentCardMapper getResidentCardMapper() {
		return residentCardMapper;
	}

	public void setResidentCardMapper(ResidentCardMapper residentCardMapper) {
		this.residentCardMapper = residentCardMapper;
	}

	public ResidentInfoMapper getResidentInfoMapper() {
		return residentInfoMapper;
	}

	public void setResidentInfoMapper(ResidentInfoMapper residentInfoMapper) {
		this.residentInfoMapper = residentInfoMapper;
	}

	public BizLogService getBizLogService() {
		return bizLogService;
	}

	public void setBizLogService(BizLogService bizLogService) {
		this.bizLogService = bizLogService;
	}

	
	 
	
}
