package com.isell.service.member.service;

import java.util.Map;

import com.isell.core.util.Record;

/**
 * 会员接口
 * 
 * @author wangpeng
 * @version [版本号, 2015-10-14]
 */
public interface MemberService {
	
	/**
     * 保存会员注册信息
     * 
     * @param param
     * @return Record
     */
    Record saveCoolMember(Map<String,Object> param);
    
    /**
     * 验证email
     * 
     * @param param
     * @return Record
     */
    Record updateEmailVerify(Map<String,Object> param);
    
    /**
     * 修改会员信息
     * 
     * @param param
     * @return Record
     */
    Record updateCoolUser(Map<String,Object> param);
    
    /**
     * 修改个人信息
     * 
     * @param param
     * @return Record
     */
    Record updateCoolMember(Map<String,Object> param);
    
    /**
     * 根据主键获取
     * 
     * @param param
     * @return Record
     */
    Record getCoolMemberById(Map<String,Object> param);
    
    /**
     * 根据用户id获取
     * 
     * @param param
     * @return Record
     */
    Record getCoolMemberByUserId(Map<String,Object> param);
    
    /**
     * 修改CoolMember（通用）
     * 
     * @param param
     * @return Record
     */
    Record updateCoolMemberCommen(Map<String,Object> param);

    /**
     * 修改CoolUser（通用）
     * 
     * @param param
     * @return Record
     */
    Record updateCoolUserCommen(Map<String,Object> param);
    
    /**
     * 平台帐户检查
     * 
     * @param param
     * @return Record
     */
    Record getNumberForCheckUser(Map<String,Object> param);
    
    /**
     * 检查身份证是否正确
     * 
     * @param param
     * @return Record
     */
    Record getNumberForCheckIdCard(Map<String,Object> param);
    
    /**
     * 获取收货地址
     * 
     * @param param
     * @return Record
     */
    Record getcoolMemberReceiverList(Map<String,Object> param);
    
    /**
     * 获取收货地址
     * 
     * @param param
     * @return Record
     */
    Record deleteCoolMemberReceiver(Map<String,Object> param);
    
    /**
     * 获取收货地址
     * 
     * @param param
     * @return Record
     */
    Record saveCoolMemberReceiver(Map<String,Object> param);

}
