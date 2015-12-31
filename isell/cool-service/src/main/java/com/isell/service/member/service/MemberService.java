package com.isell.service.member.service;

import java.util.Map;

import com.isell.core.util.Record;
import com.isell.service.member.vo.CoolIdentityAuth;
import com.isell.service.member.vo.CoolMemberReceiver;
import com.isell.service.member.vo.CoolUser;
import com.isell.service.member.vo.CoonShopFocus;

/**
 * 会员接口
 * 
 * @author wangpeng
 * @version [版本号, 2015-10-14]
 */
public interface MemberService {
	
	/**
	 * 登录
	 * @param user
	 * @return
	 */
	Record login(CoolUser user);
	
	/**
	 * 注销
	 * @param user
	 * @return
	 */
	Record loginout(CoolUser user);
	
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
    
    /**
     * 导入用户信息自动注册会员及酷店<br>
     * 导入文件格式具有不确定性，此方法暂不可被其他地方调用
     * 
     * @param param
     * @return Record
     */
    Record saveCoolMemberAndShopForImport(Map<String,Object> param);
    
    /**
     * 校验手机号是否注册
     * 
     * @param user
     * @return 手机号是否注册
     */
    Record checkMobile(CoolUser user);
    
    /**
     * 发送手机验证码
     * 
     * @param user
     * @return 手机验证码
     */
    Record sendMessage(CoolUser user);
    
    /**
     * 注册用户
     * 
     * @param user
     * @return 是否注册成功
     */
    Record saveRegisterMember(CoolUser user);
    
    /**
     * 更新用户密码
     * 
     * @param user
     * @return
     */
    Record updatePassword(CoolUser user);
    
    /**
     * 用户实名认证
     * 
     * @param user
     * @return
     */
    Record saveIdcardAuth(CoolIdentityAuth coolIdentityAuth);
    
    /**
     * 获取收货地址列表
     * 
     * @param coolMemberReceiver 查询参数
     * @return 收货地址列表
     */
    Record getReceiverList(CoolMemberReceiver coolMemberReceiver);
    
    /**
     * 保存收货地址
     * 
     * @param coolMemberReceiver 参数
     * @return 是否保存成功
     */
    Record saveMemberReceiver(CoolMemberReceiver coolMemberReceiver);
    
    /**
     * 更新关注店铺
     * 
     * @param coonShopFocus 参数
     * @return 是否保存成功
     */
    Record updateFocusShop(CoonShopFocus coonShopFocus);
    
}