package com.isell.bis.member.controller;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.isell.core.util.JsonData;
import com.isell.service.member.service.MemberService;

/**
 * 会员controller
 * 
 * @author wangpeng
 * @version [版本号, 2015-10-14]
 */
@Controller
@RequestMapping("member")
public class MemberController {
	
	/**
	 * 会员service
	 */
	@Resource
	private MemberService memberService;
	
	/**
	 * 保存用户注册信息
	 * 
	 * @param param
	 * @return
	 */
	@RequestMapping("saveCoolMember")
    @ResponseBody
	public JsonData saveCoolMember(@RequestBody Map<String,Object> param) {
        JsonData jsonData = new JsonData();
        jsonData.setData(memberService.saveCoolMember(param).getColumns());
        return jsonData;
    }
	
	/**
	 * 验证email
	 * 
	 * @param param
	 * @return
	 */
	@RequestMapping("emailVerify")
    @ResponseBody
	public JsonData emailVerify(@RequestBody Map<String,Object> param) {
        JsonData jsonData = new JsonData();
        jsonData.setData(memberService.updateEmailVerify(param).getColumns());
        return jsonData;
    }
	
	/**
	 * 修改会员信息
	 * 
	 * @param param
	 * @return
	 */
	@RequestMapping("updateCoolUser")
    @ResponseBody
	public JsonData updateCoolUser(@RequestBody Map<String,Object> param) {
        JsonData jsonData = new JsonData();
        jsonData.setData(memberService.updateCoolUser(param).getColumns());
        return jsonData;
    }
	
	/**
	 * 修改个人资料
	 * 
	 * @param param
	 * @return
	 */
	@RequestMapping("updateCoolMember")
    @ResponseBody
	public JsonData updateCoolMember(@RequestBody Map<String,Object> param) {
        JsonData jsonData = new JsonData();
        jsonData.setData(memberService.updateCoolMember(param).getColumns());
        return jsonData;
    }
	
	/**
	 * 根据主键获取
	 * 
	 * @param param
	 * @return
	 */
	@RequestMapping("getCoolMemberById")
    @ResponseBody
	public JsonData getCoolMemberById(@RequestBody Map<String,Object> param) {
        JsonData jsonData = new JsonData();
        jsonData.setData(memberService.getCoolMemberById(param).getColumns());
        return jsonData;
    }
	
	/**
	 * 根据主键获取
	 * 
	 * @param param
	 * @return
	 */
	@RequestMapping("updateCoolMemberCommen")
    @ResponseBody
	public JsonData updateCoolMemberCommen(@RequestBody Map<String,Object> param) {
        JsonData jsonData = new JsonData();
        jsonData.setData(memberService.updateCoolMemberCommen(param).getColumns());
        return jsonData;
    }
	
	/**
	 * 平台帐户检查
	 * 
	 * @param param
	 * @return
	 */
	@RequestMapping("checkUser")
    @ResponseBody
	public JsonData getNumberForCheckUser(@RequestBody Map<String,Object> param) {
        JsonData jsonData = new JsonData();
        jsonData.setData(memberService.getNumberForCheckUser(param).getColumns());
        return jsonData;
    }
	
	/**
	 * 检查身份证是否正确
	 * 
	 * @param param
	 * @return
	 */
	@RequestMapping("checkIdCard")
    @ResponseBody
	public JsonData getNumberForCheckIdCard(@RequestBody Map<String,Object> param) {
        JsonData jsonData = new JsonData();
        jsonData.setData(memberService.getNumberForCheckIdCard(param).getColumns());
        return jsonData;
    }
	
	/**
	 * 获取收货地址
	 * 
	 * @param param
	 * @return
	 */
	@RequestMapping("getAddress")
    @ResponseBody
	public JsonData getCoolMemberReceiverList(@RequestBody Map<String,Object> param) {
        JsonData jsonData = new JsonData();
        jsonData.setData(memberService.getcoolMemberReceiverList(param).getColumns());
        return jsonData;
    }
	
	/**
	 * 保存收货地址
	 * 
	 * @param param
	 * @return
	 */
	@RequestMapping("saveCoolMemberReceiver")
    @ResponseBody
	public JsonData saveCoolMemberReceiver(@RequestBody Map<String,Object> param) {
        JsonData jsonData = new JsonData();
        jsonData.setData(memberService.saveCoolMemberReceiver(param).getColumns());
        return jsonData;
    }
	
	/**
	 * 删除收货地址
	 * 
	 * @param param
	 * @return
	 */
	@RequestMapping("deleteCoolMemberReceiver")
    @ResponseBody
	public JsonData deleteCoolMemberReceiver(@RequestBody Map<String,Object> param) {
        JsonData jsonData = new JsonData();
        jsonData.setData(memberService.deleteCoolMemberReceiver(param).getColumns());
        return jsonData;
    }

}
