package com.isell.bis.profit.controller;

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
import com.isell.service.account.vo.CoonRunAccount;
import com.isell.service.shop.service.CoonShopService;
import com.isell.service.shop.vo.CoonShop;

/**
 * 收益controller
 * 
 * @author wangpeng
 * @version [版本号, 2015-11-20]
 */
@Controller
@RequestMapping("profit")
public class ProfitController {
	
	/**
	 * 酷店service
	 */
	@Resource
	private CoonShopService coonShopService;
	
	 /**
     * JVM缓存服务接口
     */
    @Resource
    protected JVMCacheService jvmCacheService;
	
	/**
     * 获取各类收入接口
     * 
     * @param jsonObj 参数
     * @return 各类收入
     */
    @RequestMapping("getAmount")
    @ResponseBody
    public JsonData getShopAmount(String jsonObj) {
        CoonShop shop = JsonUtil.readValue(jsonObj, CoonShop.class);
        JsonData jsonData = new JsonData();
        Map<String, Object> resultMap = coonShopService.getShopAmount(shop).getColumns();
        jsonData.setSuccess(resultMap.get("success") == null ? false : (Boolean)resultMap.get("success"));
        resultMap.remove("success");
        if (resultMap.get("msg") != null) {
            jsonData.setMsg((String)resultMap.get("msg"));
            resultMap.remove("msg");
        }        
        jsonData.setData(resultMap);
        return jsonData;
    }
    
    /**
     * 获取账单列表接口
     * 
     * @param jsonObj 参数
     * @return 账单列表
     */
    @SuppressWarnings("unchecked")
	@RequestMapping("getAccountList")
    @ResponseBody
    public JsonData getAccountList(String jsonObj) {
    	CoonRunAccount account = JsonUtil.readValue(jsonObj, CoonRunAccount.class);
        JsonData jsonData = new JsonData();
        Map<String, Object> resultMap = coonShopService.getAccountListPage(account).getColumns();
        jsonData.setSuccess(resultMap.get("success") == null ? false : (Boolean)resultMap.get("success"));
        resultMap.remove("success");
        if (resultMap.get("msg") != null) {
            jsonData.setMsg((String)resultMap.get("msg"));
            resultMap.remove("msg");
        } 
        if (resultMap.get("accountList") != null) {
            jsonData.setRows((List<CoonRunAccount>)resultMap.get("accountList"));
            resultMap.remove("accountList");
        } 
        jsonData.setData(resultMap);
        return jsonData;
    }
    
    /**
     * 保存提现接口
     * 
     * @param jsonObj 参数
     * @return 是否返回成功
     */
	@RequestMapping("saveWithdraw")
    @ResponseBody
    public JsonData saveWithdraw(String jsonObj, String accessCode) {
    	CoonRunAccount account = JsonUtil.readValue(jsonObj, CoonRunAccount.class);
        JsonData jsonData = new JsonData();
        String securityCode = account.getSecurityCode();
        String sCode = jvmCacheService.get("sms_" + accessCode + "_" + securityCode);
        if(StringUtils.isEmpty(securityCode)  || StringUtils.isEmpty(sCode) || !securityCode.equals(sCode)){
        	jsonData.setMsg("验证码不正确");
        	jsonData.setSuccess(false);
        }else{
        	Map<String, Object> resultMap = coonShopService.saveWithdraw(account).getColumns();
            jsonData.setSuccess(resultMap.get("success") == null ? false : (Boolean)resultMap.get("success"));
            resultMap.remove("success");
            if (resultMap.get("msg") != null) {
                jsonData.setMsg((String)resultMap.get("msg"));
                resultMap.remove("msg");
            } 
            jsonData.setData(resultMap);
        }        
        return jsonData;
    }
	
	 /**
     * 删除账单接口（删除后只是不显示，跟其他业务操作无关）
     * 
     * @param jsonObj 参数
     * @return 是否删除成功
     */
	@RequestMapping("deleteAccount")
    @ResponseBody
    public JsonData deleteAccount(String jsonObj) {
    	CoonRunAccount account = JsonUtil.readValue(jsonObj, CoonRunAccount.class);
        JsonData jsonData = new JsonData();
        Map<String, Object> resultMap = coonShopService.deleteAccount(account).getColumns();
        jsonData.setSuccess(resultMap.get("success") == null ? false : (Boolean)resultMap.get("success"));
        resultMap.remove("success");
        if (resultMap.get("msg") != null) {
            jsonData.setMsg((String)resultMap.get("msg"));
            resultMap.remove("msg");
        } 
        jsonData.setData(resultMap);
        return jsonData;
    }
	

}
