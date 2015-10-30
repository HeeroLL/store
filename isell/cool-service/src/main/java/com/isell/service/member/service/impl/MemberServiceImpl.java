package com.isell.service.member.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.isell.core.util.DateUtil;
import com.isell.core.util.Record;
import com.isell.service.member.dao.CoolMemberMapper;
import com.isell.service.member.dao.CoolMemberReceiverMapper;
import com.isell.service.member.dao.CoolUserMapper;
import com.isell.service.member.service.MemberService;
import com.isell.service.member.vo.CoolMember;
import com.isell.service.member.vo.CoolMemberReceiver;
import com.isell.service.member.vo.CoolUser;

/**
 * 会员接口实现层
 * 
 * @author wangpeng
 * @version [版本号, 2015-10-14]
 */
@Service("memberService")
public class MemberServiceImpl implements MemberService {
	
	/**
	 * 用户mapper
	 */
	@Resource
	private CoolUserMapper coolUserMapper;
	
	/**
	 * 会员mapper
	 */
	@Resource
	private CoolMemberMapper coolMemberMapper;
	
	/**
	 * 会员地址mapper
	 */
	@Resource
	private CoolMemberReceiverMapper coolMemberReceiverMapper;

	/**
	 * 保存会员注册信息
	 */
	@Override
	public Record saveCoolMember(Map<String, Object> param) {
		Record record = new Record();
		
		String username = param.get("username").toString();
        String password = param.get("password").toString();
        boolean success = false;
        int result = 0;
        if (param.get("sms").equals(param.get("sms_" + username))) {
        	int count = coolUserMapper.getRegisterNumberByUserName(username);
        	if(count > 0){
        		record.set("msg", "手机号码已被注册");
        	}else{
        		// 保存用户注册信息
                password = DigestUtils.md5Hex(password);
                CoolUser user = new CoolUser();
                user.setUsername(username);
                user.setPassword(password);
                user.setState(1);
                user.setMobile(username);
                coolUserMapper.saveCoolUser(user);
                
                String maxMember = coolMemberMapper.getMaxCoolMemberNo();
                String no = null;
                if (StringUtils.isNotEmpty(maxMember)) {
                    no = "10000000";
                } else {
                    no = (Integer.parseInt(maxMember) + 1) + "";
                }
                CoolMember member = new CoolMember();
                member.setUserId(user.getId());
                member.setMobile(username);
                member.setNo(no);
                result = coolMemberMapper.saveCoolMember(member);
                success = result>0?true:false;
                
                if(!success){
                	record.set("mag", "用户注册失败");
                }
        	}
        }
        
        record.set("success", success);
        
        return record;
	}

	/**
	 * 验证email
	 */
	@Override
	public Record updateEmailVerify(Map<String, Object> param) {
		Record record = new Record();
		
		String verifyCode = param.get("key").toString();
		CoolMember member = new CoolMember();
		member.setVerifyCode(verifyCode);
		List<CoolMember> mList = coolMemberMapper.findCoolMember(member);
		if(CollectionUtils.isNotEmpty(mList)){
			member = mList.get(0);
		}
		if(member != null){
			// 修改邮箱
            String email = member.getVerifyEmail();
            member.setEmail(email);
            member.setVerifyCode("");
            member.setVerifyEmail("");
            coolMemberMapper.updateCoolMember(member);
            
            CoolUser user = coolUserMapper.getCoolUserById(member.getUserId());
            if(user != null){
            	user.setEmail(email);
            	coolUserMapper.updateCoolUser(user);
            }
		}else{
			record.set("msg", "该验证码不存在！");
		}
		
		return record;
	}

	/**
	 * 修改会员信息
	 */
	@Override
	public Record updateCoolUser(Map<String, Object> param) {
		Record record = new Record();		
		int result = 0;
		boolean success = false;
		String msg = "";
		
		CoolUser user = coolUserMapper.getCoolUserById(Integer.parseInt(param.get("userId").toString()));
		if(user != null){
			//修改密码
			String oldPassword = DigestUtils.md5Hex(param.get("oldPassword").toString()); // 原密码
	        String newPassword = DigestUtils.md5Hex(param.get("password").toString());// 新密码
	        String password = user.getPassword();
	        if(oldPassword.equals(password)){
	        	user.setPassword(newPassword);	        	
	        	result = coolUserMapper.updateCoolUser(user);
	        	if(result > 0){
	        		success = true;
	        	}else{
	        		msg = "密码修改失败！";
	        	}
	        }else {
	        	msg = "旧密码错误，请重新输入!";
	        }
		}else{
			msg = "该会员不存在!";
		}
		
		record.set("success", success);
		record.set("msg", msg);
		return record;
	}

	/**
	 * 修改个人信息
	 */
	@Override
	public Record updateCoolMember(Map<String, Object> param) {
		Record record = new Record();
		int result = 0;
		boolean success = false;
		String msg = "";
		
		CoolMember member = coolMemberMapper.getCoolMemberById(Integer.parseInt(param.get("id").toString()));
		if(member != null){
			member.setPetname(param.get("petname").toString());
			member.setSex(param.get("sex").toString());
			member.setBirthday(DateUtil.strToDate("yyyyMMdd", param.get("date").toString()));
			member.setSkin(Integer.valueOf(param.get("skin").toString()));
			member.setHair(Integer.valueOf(param.get("hair").toString()));
			member.setCostPerYear(Integer.valueOf(param.get("cost_per_year").toString()));
			member.setDescription(param.get("sex").toString());
			member.setSex(param.get("cardId").toString());
			result = coolMemberMapper.updateCoolMember(member);
			
			CoolUser user = coolUserMapper.getCoolUserById(Integer.parseInt(param.get("id").toString()));
			user.setPetname(param.get("petname").toString());
			result = coolUserMapper.updateCoolUser(user);
			if(result > 0){
	            success = true;
	        } else {
	            msg = "个人资料保存失败";
	        }
		}else{
			msg = "该用户不存在！";
		}
		
		record.set("success", success);
		record.set("msg", msg);
		return record;
	}
	
	/**
	 * 根据主键获取
	 */
	@Override
	public Record getCoolMemberById(Map<String, Object> param) {
		Record record = new Record();
		CoolMember member = coolMemberMapper.getCoolMemberById(Integer.valueOf(param.get("id").toString()));
		record.set("email", member.getEmail());
		record.set("idcard", member.getIdcard());
		record.set("petname", member.getPetname());
		record.set("mobile", member.getMobile());
		record.set("logo", member.getLogo());
		return record;
	}
	
	/**
	 * 根据用户id获取
	 */
	@Override
	public Record getCoolMemberByUserId(Map<String, Object> param) {
		Record record = new Record();
		CoolMember member = coolMemberMapper.getCoolMemberByUserId(Integer.valueOf(param.get("userId").toString()));
		record.set("email", member.getEmail());
		record.set("idcard", member.getIdcard());
		record.set("petname", member.getPetname());
		record.set("mobile", member.getMobile());
		record.set("logo", member.getLogo());
		return record;
	}

	/**
     * 修改CoolMember（通用）
     * 
     */
	@Override
	public Record updateCoolMemberCommen(Map<String, Object> param) {
		CoolMember member = new CoolMember();
		if(param.get("id") != null){
			member = coolMemberMapper.getCoolMemberById(Integer.valueOf(param.get("id").toString()));
		}else{
			member = coolMemberMapper.getCoolMemberByUserId(Integer.valueOf(param.get("userId").toString()));
		}
		
		if(param.get("username") != null){
			member.setMobile(param.get("username").toString());
		}
		if(param.get("verify_code") != null){
			member.setVerifyCode(param.get("verify_code").toString());
		}
		if(param.get("verify_email") != null){
			member.setVerifyEmail(param.get("verify_email").toString());
		}
		
		int result = coolMemberMapper.updateCoolMember(member);
		
		Record record = new Record();
		record.set("success", result>0?true:false);
		return record;
	}

	/**
     * 修改CoolUser（通用）
     * 
     */
	@Override
	public Record updateCoolUserCommen(Map<String, Object> param) {
		Record record = new Record();
		CoolUser user = coolUserMapper.getCoolUserById(Integer.parseInt(param.get("id").toString()));
		if(param.get("username") != null){
			user.setUsername(param.get("username").toString());			
		}
		if(param.get("mobile") != null){
			user.setMobile(param.get("mobile").toString());
		}
		if(param.get("logo") != null){
			user.setLogo(param.get("logo").toString());
		}
		
		int result = coolUserMapper.updateCoolUser(user);
		
		record.set("success", result>0?true:false);
		return record;
	}

	/**
     * 平台帐户检查
     * 
     */
	@Override
	public Record getNumberForCheckUser(Map<String, Object> param) {
		Record record = new Record();		
		int count = coolUserMapper.getNumberForCheckUser(Integer.valueOf(param.get("id").toString()), (param.get("email").toString()));
		record.set("count",count);
		return record;
	}

	/**
	 * 检查身份证是否正确
	 * 
	 */
	@Override
	public Record getNumberForCheckIdCard(Map<String, Object> param) {
		Record record = new Record();		
		int count = coolMemberMapper.getNumberForCheckIdCard(Integer.valueOf(param.get("userId").toString()), (param.get("idcard").toString()));
		record.set("count",count);
		return record;
	}

	/**
	 * 获取收货地址
	 */
	@Override
	public Record getcoolMemberReceiverList(Map<String, Object> param) {
		Record record = new Record();
		List<CoolMemberReceiver> list = new ArrayList<CoolMemberReceiver>();
		if(param.get("mId") != null){
			list = coolMemberReceiverMapper.getCoolMemberReceiver(Integer.valueOf(param.get("mId").toString()),0);
		}else if(param.get("id") != null){
			list = coolMemberReceiverMapper.getCoolMemberReceiver(0,Integer.valueOf(param.get("id").toString()));
		}
		
		record.set("receivers", list);
		return record;
	}

	/**
	 * 删除收货地址
	 */
	@Override
	public Record deleteCoolMemberReceiver(Map<String, Object> param) {
		Record record = new Record();
		int count = coolMemberReceiverMapper.deleteCoolMemberReceiver(Integer.valueOf(param.get("id").toString()));
		
		record.set("count", count);
		return record;
	}
	 
	/**
	 * 保存收货地址
	 */
	@Override
	public Record saveCoolMemberReceiver(Map<String, Object> param) {
		Record record = new Record();
		CoolMemberReceiver receiver = coolMemberReceiverMapper.getCoolMemberReceiverById(Integer.valueOf(param.get("id").toString()));
		if(param.get("location_p") != null){
			receiver.setLocationP(param.get("location_p").toString());
		}
		if(param.get("location_c") != null){
			receiver.setLocationC(param.get("location_c").toString());
		}
		if(param.get("location_a") != null){
			receiver.setLocationA(param.get("location_a").toString());
		}
		if(param.get("address") != null){
			receiver.setAddress(param.get("address").toString());
		}
		if(param.get("name") != null){
			receiver.setName(param.get("name").toString());
		}
		if(param.get("mobile") != null){
			receiver.setMobile(param.get("mobile").toString());
		}
		if(param.get("tel") != null){
			receiver.setTel(param.get("tel").toString());
		}
		if(param.get("m_id") != null){
			receiver.setmId(Integer.valueOf(param.get("m_id").toString()));
		}
		if(param.get("def") != null){
			receiver.setDef(Boolean.valueOf(param.get("def").toString()));
		}
		
		int result = coolMemberReceiverMapper.updateCoolMemberReceiver(receiver);
		
		record.set("result", result);
		return record;
	}

}
