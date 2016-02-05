package com.isell.bis.user.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.isell.cache.service.JVMCacheService;
import com.isell.core.util.JsonData;
import com.isell.core.util.JsonUtil;
import com.isell.service.member.service.MemberService;
import com.isell.service.member.vo.CoolIdentityAuth;
import com.isell.service.member.vo.CoolMember;
import com.isell.service.member.vo.CoolMemberFavorites;
import com.isell.service.member.vo.CoolMemberReceiver;
import com.isell.service.member.vo.CoolUser;
import com.isell.service.member.vo.CoonShopFocus;

/**
 * 用户接口controller
 * 
 * @author lilin
 * @version [版本号, 2015年11月5日]
 */
@Controller
@RequestMapping("user")
public class UserController {

	/**
	 * 会员接口
	 */
	@Resource
	private MemberService memberService;

	/**
	 * JVM缓存服务接口
	 */
	@Resource
	protected JVMCacheService jvmCacheService;

	/**
	 * 获取用户信息
	 * 
	 * @param jsonObj 参数
	 * @return 返回用户信息
	 */
	@RequestMapping("getUserInfo")
	@ResponseBody
	public JsonData getUserInfo(String jsonObj, String accessCode) {
		CoolUser user = JsonUtil.readValue(jsonObj, CoolUser.class);
		JsonData jsonData = new JsonData();
		String result = jvmCacheService.get("user_" + accessCode);
		if (result != null) {
			// jsonData.setData(result);
			Map<String, Object> resultMap = memberService.login(user).getColumns();
			jsonData.setSuccess(resultMap.get("success") == null ? false : (Boolean) resultMap.get("success"));
			resultMap.remove("success");
			if (resultMap.get("msg") != null) {
				jsonData.setMsg((String) resultMap.get("msg"));
				resultMap.remove("msg");
			}
			if (resultMap.get("shop") != null) {
				resultMap.remove("shop");
			}
			jsonData.setData(resultMap);
		} else {
			jsonData.setSuccess(false);
			throw new RuntimeException("exception.access.service.user-null");
		}

		return jsonData;
	}

	/**
	 * 注册用户接口
	 * 
	 * @param jsonObj
	 *            参数
	 * @return 注册是否成功
	 */
	@RequestMapping("register")
	@ResponseBody
	public JsonData register(String jsonObj, String accessCode) {
		CoolUser user = JsonUtil.readValue(jsonObj, CoolUser.class);
		String sms = user.getSms();
		JsonData jsonData = new JsonData();
		Map<String, Object> resultMap = new HashMap<String, Object>();
		if (StringUtils.isNotEmpty(sms)) {
			if (sms.equals(jvmCacheService.get("sms_" + accessCode + "_" + sms))) {
				resultMap = memberService.saveRegisterMember(user).getColumns();
				jsonData.setSuccess(resultMap.get("success") == null ? false
						: (Boolean) resultMap.get("success"));
				resultMap.remove("success");
				if (resultMap.get("msg") != null) {
					jsonData.setMsg((String) resultMap.get("msg"));
					resultMap.remove("msg");
				}
				jvmCacheService.del("sms_" + accessCode + "_" + sms);
			} else {
				jsonData.setSuccess(false);
				jsonData.setMsg("验证码错误");
			}
		} else {
			jsonData.setSuccess(false);
			jsonData.setMsg("验证码不能为空");
		}
		jsonData.setData(resultMap);
		return jsonData;
	}

	/**
	 * 校验手机接口
	 * 
	 * @param jsonObj 参数
	 * @return 手机号是否注册
	 */
	@RequestMapping("checkMobile")
	@ResponseBody
	public JsonData checkMobile(String jsonObj) {
		CoolUser user = JsonUtil.readValue(jsonObj, CoolUser.class);
		JsonData jsonData = new JsonData();
		jsonData.setData(memberService.checkMobile(user).getColumns());

		return jsonData;
	}

	/**
	 * 发送信息
	 * 
	 * @param jsonObj
	 *            参数
	 * @return 手机号是否存在
	 */
	@RequestMapping("sendMessage")
	@ResponseBody
	public JsonData sendMessage(String jsonObj, String accessCode) {
		CoolUser user = JsonUtil.readValue(jsonObj, CoolUser.class);
		JsonData jsonData = new JsonData();
		Map<String, Object> resultMap = memberService.sendMessage(user)
				.getColumns();
		jsonData.setSuccess(resultMap.get("success") == null ? false
				: (Boolean) resultMap.get("success"));
		resultMap.remove("success");
		if (resultMap.get("msg") != null) {
			jsonData.setMsg((String) resultMap.get("msg"));
			resultMap.remove("msg");
		}
		if (resultMap.get("securityCode") != null) {
			jvmCacheService.set(
					"sms_" + accessCode + "_"
							+ resultMap.get("securityCode").toString(),
					resultMap.get("securityCode").toString());
			jsonData.setData(resultMap);
		}
		return jsonData;
	}

	/**
	 * 登录
	 * 
	 * @param jsonObj
	 *            参数
	 * @param accessCode
	 *            接入编码
	 * @return 缓存key
	 */
	@RequestMapping("login")
	@ResponseBody
	public JsonData login(String jsonObj, String accessCode) {
		CoolUser user = JsonUtil.readValue(jsonObj, CoolUser.class);
		JsonData jsonData = new JsonData();
		Map<String, Object> resultMap = memberService.login(user).getColumns();
		jsonData.setSuccess(resultMap.get("success") == null ? false
				: (Boolean) resultMap.get("success"));
		resultMap.remove("success");
		if (resultMap.get("msg") != null) {
			jsonData.setMsg((String) resultMap.get("msg"));
			resultMap.remove("msg");
		}
		if (resultMap.get("user") != null) {
			jvmCacheService.set("user_" + accessCode,
					JsonUtil.writeValueAsString(resultMap.get("user")));
			// resultMap.remove("user");
		}
		if (resultMap.get("shop") != null) {
			jvmCacheService.set("shop_" + accessCode,
					JsonUtil.writeValueAsString(resultMap.get("shop")));
			resultMap.remove("shop");
		}
		jsonData.setData(resultMap);
		return jsonData;
	}

	/**
	 * 注销
	 * 
	 * @param jsonObj
	 *            参数
	 * @return
	 */
	@RequestMapping("loginOut")
	@ResponseBody
	public JsonData loginOut(String jsonObj, String accessCode) {
		// CoolUser user = JsonUtil.readValue(jsonObj, CoolUser.class);
		JsonData jsonData = new JsonData();
		jvmCacheService.del("user_" + accessCode, "shop_" + accessCode);
		jsonData.setSuccess(true);
		return jsonData;
	}

	/**
	 * 找回密码接口
	 * 
	 * @param jsonObj
	 *            参数
	 * @return 密码是否设置成功
	 */
	@RequestMapping("getPassword")
	@ResponseBody
	public JsonData getPassword(String jsonObj, String accessCode) {
		CoolUser user = JsonUtil.readValue(jsonObj, CoolUser.class);
		String sms = user.getSms();
		JsonData jsonData = new JsonData();
		Map<String, Object> resultMap = new HashMap<String, Object>();
		if (StringUtils.isNotEmpty(sms)) {
			if (sms.equals(jvmCacheService.get("sms_" + accessCode + "_" + sms))) {
				resultMap = memberService.updatePassword(user).getColumns();
				jsonData.setSuccess(resultMap.get("success") == null ? false
						: (Boolean) resultMap.get("success"));
				resultMap.remove("success");
				if (resultMap.get("msg") != null) {
					jsonData.setMsg((String) resultMap.get("msg"));
					resultMap.remove("msg");
				}
				jvmCacheService.del("sms_" + accessCode + "_" + sms);
			} else {
				jsonData.setSuccess(false);
				jsonData.setMsg("验证码错误");
			}
		} else {
			jsonData.setSuccess(false);
			jsonData.setMsg("验证码不能为空");
		}
		jsonData.setData(resultMap);
		return jsonData;
	}

	/**
	 * 修改密码接口
	 * 
	 * @param jsonObj
	 *            参数
	 * @return 密码是否修改成功
	 */
	@RequestMapping("updatePassword")
	@ResponseBody
	public JsonData updatePassword(String jsonObj) {
		CoolUser user = JsonUtil.readValue(jsonObj, CoolUser.class);
		JsonData jsonData = new JsonData();
		Map<String, Object> resultMap = memberService.updatePassword(user)
				.getColumns();
		jsonData.setSuccess(resultMap.get("success") == null ? false
				: (Boolean) resultMap.get("success"));
		resultMap.remove("success");
		if (resultMap.get("msg") != null) {
			jsonData.setMsg((String) resultMap.get("msg"));
			resultMap.remove("msg");
		}
		jsonData.setData(resultMap);
		return jsonData;
	}

	/**
	 * 实名认证接口
	 * 
	 * @param jsonObj
	 *            参数
	 * @return 实名认证是否保持保存成功
	 */
	@RequestMapping("idcardAuth")
	@ResponseBody
	public JsonData idcardAuth(String jsonObj, String accessCode) {
		CoolIdentityAuth auth = JsonUtil.readValue(jsonObj,
				CoolIdentityAuth.class);
		JsonData jsonData = new JsonData();
		String sms = auth.getSms();
		if (StringUtils.isNotEmpty(sms)) {
			if (sms.equals(jvmCacheService.get("sms_" + accessCode + "_" + sms))) {
				Map<String, Object> resultMap = memberService.saveIdcardAuth(
						auth).getColumns();
				jsonData.setSuccess(resultMap.get("success") == null ? false
						: (Boolean) resultMap.get("success"));
				resultMap.remove("success");
				if (resultMap.get("msg") != null) {
					jsonData.setMsg((String) resultMap.get("msg"));
					resultMap.remove("msg");
				}
				jsonData.setData(resultMap);
				jvmCacheService.del("sms_" + accessCode + "_" + sms);
			} else {
				jsonData.setSuccess(false);
				jsonData.setMsg("验证码错误");
			}
		} else {
			jsonData.setSuccess(false);
			jsonData.setMsg("验证码不能为空");
		}

		return jsonData;
	}

	/**
	 * 获取实名认证状态接口
	 * 
	 * @param jsonObj
	 *            参数
	 * @return 实名认证信息
	 */
	@RequestMapping("getIdcardAuth")
	@ResponseBody
	public JsonData getIdcardAuth(String jsonObj) {
		CoolIdentityAuth auth = JsonUtil.readValue(jsonObj,
				CoolIdentityAuth.class);
		JsonData jsonData = new JsonData();
		Map<String, Object> resultMap = memberService.getIdcardAuth(auth)
				.getColumns();
		jsonData.setSuccess(resultMap.get("success") == null ? false
				: (Boolean) resultMap.get("success"));
		resultMap.remove("success");
		if (resultMap.get("msg") != null) {
			jsonData.setMsg((String) resultMap.get("msg"));
			resultMap.remove("msg");
		}
		jsonData.setData(resultMap);
		return jsonData;
	}

	/**
	 * 获取收货地址列表接口
	 * 
	 * @param jsonObj
	 *            参数
	 * @return 会员收货地址列表
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("getReceiverList")
	@ResponseBody
	public JsonData getReceiverList(String jsonObj) {
		CoolMemberReceiver receiver = JsonUtil.readValue(jsonObj,
				CoolMemberReceiver.class);
		JsonData jsonData = new JsonData();
		Map<String, Object> resultMap = memberService.getReceiverList(receiver)
				.getColumns();
		jsonData.setSuccess(resultMap.get("success") == null ? false
				: (Boolean) resultMap.get("success"));
		resultMap.remove("success");
		if (resultMap.get("msg") != null) {
			jsonData.setMsg((String) resultMap.get("msg"));
			resultMap.remove("msg");
		}
		if (resultMap.get("receiverList") != null) {
			jsonData.setRows((List<CoolMemberReceiver>) resultMap
					.get("receiverList"));
			resultMap.remove("receiverList");
		}
		jsonData.setData(resultMap);
		return jsonData;
	}

	/**
	 * 保存收货地址接口
	 * 
	 * @param jsonObj
	 *            参数
	 * @return 是否保存成功
	 */
	@RequestMapping("saveMemberReceiver")
	@ResponseBody
	public JsonData saveMemberReceiver(String jsonObj) {
		CoolMemberReceiver receiver = JsonUtil.readValue(jsonObj,
				CoolMemberReceiver.class);
		JsonData jsonData = new JsonData();
		Map<String, Object> resultMap = memberService.saveMemberReceiver(
				receiver).getColumns();
		jsonData.setSuccess(resultMap.get("success") == null ? false
				: (Boolean) resultMap.get("success"));
		resultMap.remove("success");
		if (resultMap.get("msg") != null) {
			jsonData.setMsg((String) resultMap.get("msg"));
			resultMap.remove("msg");
		}
		jsonData.setData(resultMap);
		return jsonData;
	}

	/**
	 * 更新关注店铺接口
	 * 
	 * @param jsonObj
	 *            参数
	 * @return 是否更新成功
	 */
	@RequestMapping("updateFocusShop")
	@ResponseBody
	public JsonData updateFocusShop(String jsonObj) {
		CoonShopFocus coonShopFocus = JsonUtil.readValue(jsonObj,
				CoonShopFocus.class);
		JsonData jsonData = new JsonData();
		Map<String, Object> resultMap = memberService.updateFocusShop(
				coonShopFocus).getColumns();
		jsonData.setSuccess(resultMap.get("success") == null ? false
				: (Boolean) resultMap.get("success"));
		resultMap.remove("success");
		if (resultMap.get("msg") != null) {
			jsonData.setMsg((String) resultMap.get("msg"));
			resultMap.remove("msg");
		}
		jsonData.setData(resultMap);
		return jsonData;
	}

	/**
	 * 收藏商品接口
	 * 
	 * @param jsonObj
	 *            参数
	 * @return 是否更新成功
	 */
	@RequestMapping("saveMemberFavorites")
	@ResponseBody
	public JsonData saveMemberFavorites(String jsonObj) {
		CoolMemberFavorites coolMemberFavorites = JsonUtil.readValue(jsonObj,
				CoolMemberFavorites.class);
		JsonData jsonData = new JsonData();
		Map<String, Object> resultMap = memberService.saveMemberFavorites(
				coolMemberFavorites).getColumns();
		jsonData.setSuccess(resultMap.get("success") == null ? false
				: (Boolean) resultMap.get("success"));
		resultMap.remove("success");
		if (resultMap.get("msg") != null) {
			jsonData.setMsg((String) resultMap.get("msg"));
			resultMap.remove("msg");
		}
		jsonData.setData(resultMap);
		return jsonData;
	}

	/**
	 * 取消收藏商品接口
	 * 
	 * @param jsonObj
	 *            参数
	 * @return 是否更新成功
	 */
	@RequestMapping("deleteMemberFavorites")
	@ResponseBody
	public JsonData deleteMemberFavorites(String jsonObj) {
		CoolMemberFavorites coolMemberFavorites = JsonUtil.readValue(jsonObj,
				CoolMemberFavorites.class);
		JsonData jsonData = new JsonData();
		Map<String, Object> resultMap = memberService.deleteMemberFavorites(
				coolMemberFavorites).getColumns();
		jsonData.setSuccess(resultMap.get("success") == null ? false
				: (Boolean) resultMap.get("success"));
		resultMap.remove("success");
		if (resultMap.get("msg") != null) {
			jsonData.setMsg((String) resultMap.get("msg"));
			resultMap.remove("msg");
		}
		jsonData.setData(resultMap);
		return jsonData;
	}

	/**
	 * 获取收藏商品列表接口
	 * 
	 * @param jsonObj
	 *            参数
	 * @return 是否更新成功
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("getMemberFavoritesList")
	@ResponseBody
	public JsonData getMemberFavoritesList(String jsonObj) {
		CoolMemberFavorites coolMemberFavorites = JsonUtil.readValue(jsonObj,
				CoolMemberFavorites.class);
		JsonData jsonData = new JsonData();
		Map<String, Object> resultMap = memberService.getMemberFavoritesList(
				coolMemberFavorites).getColumns();
		jsonData.setSuccess(resultMap.get("success") == null ? false
				: (Boolean) resultMap.get("success"));
		resultMap.remove("success");
		if (resultMap.get("msg") != null) {
			jsonData.setMsg((String) resultMap.get("msg"));
			resultMap.remove("msg");
		}
		if (resultMap.get("favList") != null) {
			jsonData.setRows((List<CoolMemberFavorites>) resultMap
					.get("favList"));
			resultMap.remove("favList");
		}
		jsonData.setData(resultMap);
		return jsonData;
	}

	/**
	 * 删除收货地址接口
	 * 
	 * @param jsonObj
	 *            参数
	 * @return 是否更新成功
	 */
	@RequestMapping("deleteMemberReceiver")
	@ResponseBody
	public JsonData deleteMemberReceiver(String jsonObj) {
		CoolMemberReceiver coolMemberReceiver = JsonUtil.readValue(jsonObj,
				CoolMemberReceiver.class);
		JsonData jsonData = new JsonData();
		Map<String, Object> resultMap = memberService.deleteMemberReceiver(
				coolMemberReceiver).getColumns();
		jsonData.setSuccess(resultMap.get("success") == null ? false
				: (Boolean) resultMap.get("success"));
		resultMap.remove("success");
		if (resultMap.get("msg") != null) {
			jsonData.setMsg((String) resultMap.get("msg"));
			resultMap.remove("msg");
		}
		jsonData.setData(resultMap);
		return jsonData;
	}

	/**
	 * 修改会员信息接口
	 * 
	 * @param jsonObj
	 *            参数
	 * @return 是否修改成功
	 */
	@RequestMapping("updateCoolMember")
	@ResponseBody
	public JsonData updateCoolMember(String jsonObj) {
		CoolMember coolMember = JsonUtil.readValue(jsonObj, CoolMember.class);
		JsonData jsonData = new JsonData();
		coolMember.setmId(false);
		Map<String, Object> resultMap = memberService.updateCoolMember(
				coolMember).getColumns();
		jsonData.setSuccess(resultMap.get("success") == null ? false
				: (Boolean) resultMap.get("success"));
		resultMap.remove("success");
		if (resultMap.get("msg") != null) {
			jsonData.setMsg((String) resultMap.get("msg"));
			resultMap.remove("msg");
		}
		jsonData.setData(resultMap);
		return jsonData;
	}
}
