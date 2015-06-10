package com.zebone.empi.service;

import com.zebone.empi.vo.CheckResult;
import com.zebone.empi.vo.EmpiLog;
import com.zebone.empi.vo.ResidentInfo;



/**
 * 验证
 * @author YinCm
 * @version 2013-7-31 下午10:10:20
 */
public interface CheckerService {
	
	/**
	 * 实现对EMPI注册文档进行校验。
	 * @param 个人基本信息(详细参见EMPI注册文档定义)
	 * @return CheckResult  resultCode: 1 校验通过，需要注册；2校验通过，需要更新；0 校验没有通过。
	 */
	CheckResult doCheck(ResidentInfo residentInfo, EmpiLog empiLog, String xmlString) throws Exception;
	
	
	/**
	 * 按照一级标识和姓名来查找是否存在
	 * @param cardNo
	 * @param name
	 * @return
	 */
	public boolean seachByCardNoAndName(String cardNo, String name);
	
	/**
	 * 字段格式验证
	 * @param residentInfo
	 * @param empiLog
	 * @return validate passed:true; validate wrong:false
	 */
	public boolean validateFieldValues(ResidentInfo residentInfo, EmpiLog empiLog);

    /**
     * 申请参数xsd校验
     * @param xmlString
     */
    public boolean xsdCheckForApply(String xmlString, EmpiLog empiLog, String methodName) throws Exception;
}
