package com.isell.bis.business;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.isell.core.util.JsonData;
import com.isell.core.util.JsonUtil;
import com.isell.service.shop.po.CoonShopBusinessInfo;
import com.isell.service.shop.po.CoonShopBusinessParam;
import com.isell.service.shop.service.CoonShopService;
import com.isell.service.shop.vo.CoonShopClick;

/**
 * 生意参谋controller
 * 
 * @author wangpeng
 * @version [版本号, 2015-11-30]
 */
@Controller
@RequestMapping("business")
public class BusinessController {
	
	/**
	 * 酷店接口
	 */
	@Resource
	private CoonShopService coonShopService;
	
	/**
     * 获取生意参谋各统计图表接口
     *
     * @param jsonObj 参数
     * @return 统计列表
     */
    @SuppressWarnings("unchecked")
	@RequestMapping("getBusinessList")
    @ResponseBody
    public JsonData getBusinessList(String jsonObj) {
    	CoonShopBusinessParam param = JsonUtil.readValue(jsonObj, CoonShopBusinessParam.class);
    	JsonData jsonData = new JsonData();
        Map<String, Object> resultMap = coonShopService.getBusinessList(param).getColumns();
        jsonData.setSuccess(resultMap.get("success") == null ? false : (Boolean)resultMap.get("success"));
        resultMap.remove("success");
        if (resultMap.get("msg") != null) {
            jsonData.setMsg((String)resultMap.get("msg"));
            resultMap.remove("msg");
        } 
        if (resultMap.get("businessList") != null) {
        	jsonData.setRows((List<CoonShopBusinessInfo>)resultMap.get("businessList"));        	
            resultMap.remove("businessList");
        } 
        jsonData.setData(resultMap);
        return jsonData;
    }
    
    /**
     * 保存酷店点击信息
     *
     * @param jsonObj 参数
     * @return 统计列表
     */
	@RequestMapping("saveCoonShopClick")
    @ResponseBody
    public JsonData saveCoonShopClick(String jsonObj) {
		CoonShopClick coonShopClick = JsonUtil.readValue(jsonObj, CoonShopClick.class);
    	JsonData jsonData = new JsonData();
        Map<String, Object> resultMap = coonShopService.saveCoonShopClick(coonShopClick).getColumns();
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
