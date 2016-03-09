package com.isell.app;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.isell.app.dao.entity.CenterOrder;
import com.isell.app.dao.entity.CenterOrderParam;
 
import com.isell.app.dao.entity.Product;
 
import com.isell.app.dao.entity.CoonBannerInfo;
 
import com.isell.app.dao.entity.SearchParam;
import com.isell.app.service.AppSellService;
import com.isell.cache.service.JVMCacheService;
import com.isell.core.config.BisConfig;
import com.isell.core.util.JsonData;
import com.isell.core.util.JsonUtil;
import com.isell.service.account.po.CoonRunAccountParam;
import com.isell.service.member.service.MemberService;
import com.isell.service.member.vo.CoolUser;
import com.isell.service.order.service.OrderService;
import com.isell.service.shop.service.CoonShopService;
import com.isell.service.shop.vo.CoonShop;
import com.isell.service.shop.vo.CoonShopBanner;
import com.isell.service.shop.vo.CoonShopNotice;
import com.isell.service.shop.vo.CoonThirdParty;

/**
 * APP卖家版控制层
 * 
 * @author wangpeng
 * @version [版本号, 2016-03-04]
 */
@Controller
@RequestMapping("appservice")
public class AppSellController {
	@Resource
    private AppSellService appSellService;
	
	/**
	 * 会员接口
	 */
	@Resource
	private MemberService memberService;
	
	/**
	 * 酷店service
	 */
	@Resource
	private CoonShopService coonShopService;
	
	 /**
     * 订单服务接口
     */
    @Resource
    private OrderService orderService;

	/**
	 * JVM缓存服务接口
	 */
	@Resource
	protected JVMCacheService jvmCacheService;
	
	/**
	 * 登录
	 * 
	 * @param jsonObj 参数
	 * @param accessCode  接入编码
	 * @return 缓存key
	 */
	@RequestMapping("loginSell")
	@ResponseBody
	public JsonData login(String jsonObj, String accessCode) {
		CoolUser user = JsonUtil.readValue(jsonObj, CoolUser.class);
		JsonData jsonData = new JsonData();
		Map<String, Object> resultMap = memberService.login(user).getColumns();
		jsonData.setSuccess(resultMap.get("success") == null ? false : (Boolean) resultMap.get("success"));
		resultMap.remove("success");
		if (resultMap.get("msg") != null) {
			jsonData.setMsg((String) resultMap.get("msg"));
			resultMap.remove("msg");
		}
		if (resultMap.get("user") != null) {
			jvmCacheService.set("user_" + accessCode,JsonUtil.writeValueAsString(resultMap.get("user")));
			jsonData.setData(resultMap.get("user"));
		}
		if (resultMap.get("shop") != null) {
			jvmCacheService.set("shop_" + accessCode, JsonUtil.writeValueAsString(resultMap.get("shop")));			
			//jsonData.setData(resultMap.get("shop"));
			resultMap.remove("shop");
		}
		//jsonData.setData(resultMap);
		return jsonData;
	}
	
	/**
	 * 注销
	 * 
	 * @param jsonObj 参数
	 * @param accessCode  接入编码
	 * @return 缓存key
	 */
	@RequestMapping("loginOut")
	@ResponseBody
	public JsonData loginOut(String jsonObj, String accessCode) {
		JsonData jsonData = new JsonData();
		jvmCacheService.del("user_" + accessCode, "shop_" + accessCode);
		jsonData.setSuccess(true);
		return jsonData;
	}
	
	/**
     * 获取各类收入接口
     * 
     * @param jsonObj 参数
     * @return 各类收入
     */
    @RequestMapping("getIncome")
    @ResponseBody
    public JsonData getIncome(String jsonObj) {
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
     * 获取各类收入列表接口
     * 
     * @param jsonObj 参数
     * @return 各类收入列表
     */
    @RequestMapping("getIncomePage")
    @ResponseBody
    public JsonData getIncomePage(String jsonObj) {
    	CoonRunAccountParam param = JsonUtil.readValue(jsonObj, CoonRunAccountParam.class);
        JsonData jsonData = new JsonData();
        Map<String, Object> resultMap = coonShopService.getIncomePage(param).getColumns();
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
     * 获取首页店铺数据接口
     * 
     * @param jsonObj 参数
     * @return 各类收入
     */
    @RequestMapping("getHomepageShopInfo")
    @ResponseBody
    public JsonData getHomepageShopInfo(String jsonObj) {
        CoonShop shop = JsonUtil.readValue(jsonObj, CoonShop.class);
        JsonData jsonData = new JsonData();
        Map<String, Object> resultMap = coonShopService.getHomepageShopInfo(shop).getColumns();
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
     * 获取最近购物人次列表接口
     * 
     * @param jsonObj 参数
     * @return 最近购物人次列表
     */
    @RequestMapping("getBuyCountPage")
    @ResponseBody
    public JsonData getBuyCountPage(String jsonObj) {
    	CoonShop coonShop = JsonUtil.readValue(jsonObj, CoonShop.class);
        JsonData jsonData = new JsonData();
        Map<String, Object> resultMap = orderService.getBuyCountPage(coonShop).getColumns();
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
     * 修改酷店信息接口
     * 
     * @param jsonObj 参数
     * @return 最近购物人次列表
     */
    @RequestMapping("updateShopInfo")
    @ResponseBody
    public JsonData updateShopInfo(String jsonObj) {
    	CoonShop coonShop = JsonUtil.readValue(jsonObj, CoonShop.class);
        JsonData jsonData = new JsonData();
        Map<String, Object> resultMap = coonShopService.updateShopInfo(coonShop).getColumns();
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
     * 获取酷店公告接口
     * 
     * @param jsonObj 参数
     * @return 最近购物人次列表
     */
    @RequestMapping("getShopNoticePage")
    @ResponseBody
    public JsonData getShopNoticePage(String jsonObj) {
    	CoonShop coonShop = JsonUtil.readValue(jsonObj, CoonShop.class);
        JsonData jsonData = new JsonData();
        Map<String, Object> resultMap = coonShopService.getShopNoticePage(coonShop).getColumns();
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
     * 保存/修改酷店公告接口
     * 
     * @param jsonObj 参数
     * @return 是否保存成功
     */
    @RequestMapping("saveShopNotice")
    @ResponseBody
    public JsonData saveShopNotice(String jsonObj) {
    	CoonShopNotice coonShopNotice = JsonUtil.readValue(jsonObj, CoonShopNotice.class);
        JsonData jsonData = new JsonData();
        Map<String, Object> resultMap = coonShopService.saveShopNotice(coonShopNotice).getColumns();
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
     * 获取不属于本店铺的海报列表接口
     * 
     * @param jsonObj 参数
     * @return 不属于本店铺的海报列表
     */
    @RequestMapping("getBannerPage")
    @ResponseBody
    public JsonData getBannerPage(String jsonObj) {
    	CoonShopBanner banner = JsonUtil.readValue(jsonObj, CoonShopBanner.class);
    	JsonData jsonData = new JsonData();
        Map<String, Object> resultMap = coonShopService.getBannerListPage (banner).getColumns();
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
     * 获取本店铺的海报列表接口
     * 
     * @param jsonObj 参数
     * @return 本店铺的海报列表
     */
    @RequestMapping("getShopBannerPage")
    @ResponseBody
    public JsonData getShopBannerPage(String jsonObj) {
    	CoonShopBanner banner = JsonUtil.readValue(jsonObj, CoonShopBanner.class);
    	JsonData jsonData = new JsonData();
        Map<String, Object> resultMap = coonShopService.getShopBannerListPage (banner).getColumns();
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
     * 新增海报接口
     * 
     * @param jsonObj 参数
     * @return 是否保存成功
     */
    @RequestMapping("saveShopBannerBatch")
    @ResponseBody
    public JsonData saveShopBannerBatch(String jsonObj) {
    	CoonBannerInfo info = JsonUtil.readValue(jsonObj, CoonBannerInfo.class);
    	JsonData jsonData = new JsonData();
        Map<String, Object> resultMap = coonShopService.saveShopBannerBatch (info.getBannerList()).getColumns();
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
     * 删除酷店海报接口
     * 
     * @param jsonObj 参数
     * @return 是否删除成功
     */
    @RequestMapping("delShopBanner")
    @ResponseBody
    public JsonData delShopBanner(String jsonObj) {
    	CoonShopBanner banner = JsonUtil.readValue(jsonObj, CoonShopBanner.class);
    	JsonData jsonData = new JsonData();
        Map<String, Object> resultMap = coonShopService.delShopBanner (banner).getColumns();
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
     * 判断一件代发权限接口
     * 
     * @param jsonObj 参数
     * @return 一件代发权限
     */
    @RequestMapping("getShopDist")
    @ResponseBody
    public JsonData getShopDist(String jsonObj) {
    	CoonShop shop = JsonUtil.readValue(jsonObj, CoonShop.class);
    	JsonData jsonData = new JsonData();
        Map<String, Object> resultMap = coonShopService.getShopDist(shop).getColumns();
        jsonData.setSuccess(resultMap.get("success") == null ? false : (Boolean)resultMap.get("success"));
        resultMap.remove("success");
        if (resultMap.get("msg") != null) {
            jsonData.setMsg((String)resultMap.get("msg"));
            resultMap.remove("msg");
        }
        jsonData.setData(resultMap);
        return jsonData;
    }
    
  /*  *//**
     * 申请一件代发接口
     * 
     * @param jsonObj 参数
     * @return 是否申请成功
     *//*
    @RequestMapping("saveShopDistApply")
    @ResponseBody
    public JsonData saveShopDistApply(String jsonObj) {
    	CoonThirdParty coonThirdParty = JsonUtil.readValue(jsonObj, CoonThirdParty.class);
    	JsonData jsonData = new JsonData();
      //  Map<String, Object> resultMap = coonShopService.saveCoonShopDistApply(coonThirdParty).getColumns();
        jsonData.setSuccess(resultMap.get("success") == null ? false : (Boolean)resultMap.get("success"));
        resultMap.remove("success");
        if (resultMap.get("msg") != null) {
            jsonData.setMsg((String)resultMap.get("msg"));
            resultMap.remove("msg");
        }
        jsonData.setData(resultMap);
        return jsonData;
    }
    */
    /***************************ycl sell app service begin************************************/
    /**
     * 配置信息
     */
    @Resource
    private BisConfig config;
    
    
    /**
     * 查询店家订单
     * @param jsonObj
     * @return
     */
    @RequestMapping("querySellMyOrderPage")
    @ResponseBody 
    public JsonData querySellMyOrderPage(String jsonObj)
    {
    	JsonData vo = new JsonData();
    	CenterOrderParam centerOrderParam=JsonUtil.readValue(jsonObj, CenterOrderParam.class);
    	centerOrderParam.setImgdomain(config.getImgDomain());
    	List<CenterOrder>list=this.appSellService.querySellMyOrderPage(centerOrderParam);
    	if(list.size()>0)
    	{
    		vo.setSuccess(true);
    		vo.setData(list);
    	}else
    	{
    		vo.setSuccess(false);
    		vo.setMsg("无数据");
    	}
    	return vo;
    }
    
    
    
    /**
     *  商品批量配置新品（上/下）
     * @param jsonObj
     * @return
     */
    @RequestMapping("batchUpdateGoodsIsNew")
    @ResponseBody
    public JsonData batchUpdateGoodsIsNew(String jsonObj)
    {
    	JsonData vo = new JsonData();
    	SearchParam param=JsonUtil.readValue(jsonObj, SearchParam.class);
    	 
    	int result=this.appSellService.batchUpdateGoodsIsNew(param);
    	if(result>0)
    	{
    		vo.setSuccess(true);
    	}else
    	{
    		vo.setSuccess(false);
    		vo.setMsg("操作失败");
    	}
    	return vo;
    }
    /**
     * 
     * @param jsonObj
     * @return
     */
    @RequestMapping("batchUpdateGoodsIsAdd")
    @ResponseBody
    public JsonData batchUpdateGoodsIsAdd(String jsonObj)
    {
    	JsonData vo = new JsonData();
    	SearchParam param=JsonUtil.readValue(jsonObj, SearchParam.class);
    	int result=this.appSellService.batchUpdateGoodsIsAdd(param);
    	if(result>0)
    	{
    		vo.setSuccess(true);
    	}else
    	{
    		vo.setSuccess(false);
    		vo.setMsg("操作失败");
    	}
    	return vo;
    	
    }
    /**
     * 获取已上架/未上架/新品上线商品（搜索）
     * @param jsonObj
     * @return
     */
    @RequestMapping("querySellGoodsListSearch")
    @ResponseBody
    public JsonData querySellGoodsListSearch(String jsonObj)
    {
    	JsonData vo = new JsonData();
    	SearchParam param=JsonUtil.readValue(jsonObj, SearchParam.class);
    	List<Product>list=this.appSellService.querySellGoodsListSearch(param);
    	if(list.size()>0)
    	{
    		vo.setData(list);
    		vo.setSuccess(true);
    	}else
    	{
    		vo.setSuccess(false);
    		vo.setMsg("无数据");
    	}
    	return vo;
    }
    
    
    /***************************ycl sell app service end************************************/
}
