package com.isell.bis.shop.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.isell.bis.shop.controller.vo.CoolDistributionCarPo;
import com.isell.cache.service.JVMCacheService;
import com.isell.core.util.JsonData;
import com.isell.core.util.JsonUtil;
import com.isell.service.order.po.CoolDistributionCarInfo;
import com.isell.service.order.po.CoolOrderParam;
import com.isell.service.order.vo.CoolDistributionCar;
import com.isell.service.shop.po.CoonShopFavInfo;
import com.isell.service.shop.po.CoonShopPartnerInfo;
import com.isell.service.shop.po.CoonShopProductInfo;
import com.isell.service.shop.po.CoonShopProductParam;
import com.isell.service.shop.po.CoonShopShareParam;
import com.isell.service.shop.service.CoonShopService;
import com.isell.service.shop.vo.CoonBanner;
import com.isell.service.shop.vo.CoonShop;
import com.isell.service.shop.vo.CoonShopApply;
import com.isell.service.shop.vo.CoonShopBanner;
import com.isell.service.shop.vo.CoonShopFav;
import com.isell.service.shop.vo.CoonShopShare;
import com.isell.service.shop.vo.CoonShopShareExperience;
import com.isell.service.shop.vo.CoonThirdParty;

/**
 * 店铺相关对外接口
 * 
 * @author wangpeng
 * @version [版本号, 2015-11-11]
 */
@Controller
@RequestMapping("shop")
public class ShopController {
	
	//private static final Logger log = Logger.getLogger(ShopController.class);
	
	/**
	 * 酷店接口
	 */
	@Resource
	private CoonShopService coonShopService;
	
	/**
     * JVM缓存服务接口
     */
    @Resource
    protected JVMCacheService jvmCacheService;
	
	/**
     * 申请酷店接口
     *
     * @param jsonObj 酷店参数
     * @return 是否申请成功
     */
    @RequestMapping("applyShop")
    @ResponseBody
    public JsonData saveApplyShop(String jsonObj,String accessCode) {
    	CoonShopApply applay = JsonUtil.readValue(jsonObj, CoonShopApply.class);
    	JsonData jsonData = new JsonData();
        Map<String, Object> resultMap = coonShopService.saveApplyShop(applay).getColumns();              
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
     * 登录酷店首页接口
     *
     * @param jsonObj 酷店参数
     * @return 酷店信息
     */
    @RequestMapping("indexShop")
    @ResponseBody
    public JsonData shopIndex(String jsonObj) {
    	CoonShop shop = JsonUtil.readValue(jsonObj, CoonShop.class);
    	JsonData jsonData = new JsonData();
        Map<String, Object> resultMap = coonShopService.getShopIndex(shop).getColumns();
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
     * 获取酷店信息接口
     *
     * @param jsonObj 酷店参数
     * @return 酷店信息
     */
    @RequestMapping("getShopInfo")
    @ResponseBody
    public JsonData getShopInfo(String jsonObj) {
    	CoonShop shop = JsonUtil.readValue(jsonObj, CoonShop.class);
    	JsonData jsonData = new JsonData();
        Map<String, Object> resultMap = coonShopService.getShopIndex(shop).getColumns();
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
     * @param jsonObj 酷店参数
     * @return 是否修改成功
     */
    @RequestMapping("updateShop")
    @ResponseBody
    public JsonData updateShop(String jsonObj) {
    	CoonShop shop = JsonUtil.readValue(jsonObj, CoonShop.class);
    	JsonData jsonData = new JsonData();
        Map<String, Object> resultMap = coonShopService.updateShop(shop).getColumns();
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
     * 新建店铺分享接口
     *
     * @param jsonObj 酷店参数
     * @return 是否保存成功
     */
    @RequestMapping("saveShopShare")
    @ResponseBody
    public JsonData saveShopShare(String jsonObj) {
    	CoonShopShare share = JsonUtil.readValue(jsonObj, CoonShopShare.class);
    	JsonData jsonData = new JsonData();
        Map<String, Object> resultMap = coonShopService.saveShopShare(share).getColumns();
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
     * 删除店铺分享接口
     *
     * @param jsonObj 酷店参数
     * @return 是否删除成功
     */
    @RequestMapping("deleteShopShare")
    @ResponseBody
    public JsonData deleteShopShare(String jsonObj) {
    	CoonShopShare share = JsonUtil.readValue(jsonObj, CoonShopShare.class);
    	JsonData jsonData = new JsonData();
        Map<String, Object> resultMap = coonShopService.deleteShopShare(share).getColumns();
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
     * 查询店铺分享接口
     *
     * @param jsonObj 酷店参数
     * @return 酷店分享列表
     */
    @SuppressWarnings("unchecked")
	@RequestMapping("getShopShare")
    @ResponseBody
    public JsonData getShopShare(String jsonObj) {
    	CoonShopShare share = JsonUtil.readValue(jsonObj, CoonShopShare.class);
    	JsonData jsonData = new JsonData();
        Map<String, Object> resultMap = coonShopService.getShopShare(share).getColumns();
        jsonData.setSuccess(resultMap.get("success") == null ? false : (Boolean)resultMap.get("success"));
        resultMap.remove("success");
        if (resultMap.get("msg") != null) {
            jsonData.setMsg((String)resultMap.get("msg"));
            resultMap.remove("msg");
        } 
        if (resultMap.get("shareList") != null) {
        	jsonData.setRows((List<CoonShopShare>)resultMap.get("shareList"));        	
            resultMap.remove("shareList");
        } 
        jsonData.setData(resultMap);
        return jsonData;
    }
    
    /**
     * 获取不属于该店铺海报接口
     *
     * @param jsonObj 参数
     * @return 海报列表信息
     */
    @SuppressWarnings("unchecked")
	@RequestMapping("getBannerListPage ")
    @ResponseBody
    public JsonData getBannerListPage (String jsonObj) {
    	CoonShopBanner banner = JsonUtil.readValue(jsonObj, CoonShopBanner.class);
    	JsonData jsonData = new JsonData();
        Map<String, Object> resultMap = coonShopService.getBannerListPage (banner).getColumns();
        jsonData.setSuccess(resultMap.get("success") == null ? false : (Boolean)resultMap.get("success"));
        resultMap.remove("success");
        if (resultMap.get("msg") != null) {
            jsonData.setMsg((String)resultMap.get("msg"));
            resultMap.remove("msg");
        }
        if (resultMap.get("bannerList") != null) {
            jsonData.setRows((List<CoonBanner>)resultMap.get("bannerList"));
            resultMap.remove("bannerList");
        }
        jsonData.setData(resultMap);
        return jsonData;
    }
    
    /**
     * 获取属于该店铺海报接口
     *
     * @param jsonObj 参数
     * @return 海报列表信息
     */
    @SuppressWarnings("unchecked")
	@RequestMapping("getShopBannerListPage ")
    @ResponseBody
    public JsonData getShopBannerListPage (String jsonObj) {
    	CoonShopBanner banner = JsonUtil.readValue(jsonObj, CoonShopBanner.class);
    	JsonData jsonData = new JsonData();
        Map<String, Object> resultMap = coonShopService.getShopBannerListPage (banner).getColumns();
        jsonData.setSuccess(resultMap.get("success") == null ? false : (Boolean)resultMap.get("success"));
        resultMap.remove("success");
        if (resultMap.get("msg") != null) {
            jsonData.setMsg((String)resultMap.get("msg"));
            resultMap.remove("msg");
        }
        if (resultMap.get("bannerList") != null) {
            jsonData.setRows((List<CoonBanner>)resultMap.get("bannerList"));
            resultMap.remove("bannerList");
        }
        jsonData.setData(resultMap);
        return jsonData;
    }
    
    /**
     * 新增店铺海报接口
     *
     * @param jsonObj 酷店参数
     * @return 是否保存成功
     */
    @RequestMapping("saveShopBanner")
    @ResponseBody
    public JsonData saveShopBanner(String jsonObj) {
    	CoonShopBanner banner = JsonUtil.readValue(jsonObj, CoonShopBanner.class);
    	JsonData jsonData = new JsonData();
        Map<String, Object> resultMap = coonShopService.saveShopBanner(banner).getColumns();
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
     * 删除店铺海报接口
     *
     * @param jsonObj 参数
     * @return 是否删除成功
     */
    @RequestMapping("deleteShopBanner")
    @ResponseBody
    public JsonData deleteShopBanner(String jsonObj) {
    	CoonShopBanner banner = JsonUtil.readValue(jsonObj, CoonShopBanner.class);
    	JsonData jsonData = new JsonData();
        Map<String, Object> resultMap = coonShopService.deleteShopBanner(banner).getColumns();
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
     * 获取粉丝列表接口
     *
     * @param jsonObj 酷店参数
     * @return 酷店信息
     */
    @SuppressWarnings("unchecked")
	@RequestMapping("getShopFav")
    @ResponseBody
    public JsonData getShopFav(String jsonObj) {
    	CoonShopFav fav = JsonUtil.readValue(jsonObj, CoonShopFav.class);
    	JsonData jsonData = new JsonData();
        Map<String, Object> resultMap = coonShopService.getShopFavListPage(fav).getColumns();
        jsonData.setSuccess(resultMap.get("success") == null ? false : (Boolean)resultMap.get("success"));
        resultMap.remove("success");
        if (resultMap.get("msg") != null) {
            jsonData.setMsg((String)resultMap.get("msg"));
            resultMap.remove("msg");
        }
        if (resultMap.get("favList") != null) {
            jsonData.setRows((List<CoonShopFav>)resultMap.get("favList"));
            resultMap.remove("favList");
        }
        jsonData.setData(resultMap);
        return jsonData;
    }
    
    /**
     * 申请一件代发接口
     *
     * @param jsonObj 参数
     * @return 是否保存成功
     */
    @RequestMapping("saveShopDistApply")
    @ResponseBody
    public JsonData saveShopDistApply(String jsonObj) {
    	CoonThirdParty coonThirdParty = JsonUtil.readValue(jsonObj, CoonThirdParty.class);
    	JsonData jsonData = new JsonData();
        Map<String, Object> resultMap = coonShopService.saveShopDistApply(coonThirdParty).getColumns();
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
     * 判断是否有一件代发权限接口
     *
     * @param jsonObj 参数
     * @return 一件代发信息
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
    
    /**
     *保存进货单接口
     *
     * @param jsonObj 参数
     * @return  是否保存成功
     */
    @RequestMapping("saveShopDistributionCar")
    @ResponseBody
    public JsonData saveShopDistributionCar(String jsonObj) {
    	CoolDistributionCarPo po = JsonUtil.readValue(jsonObj, CoolDistributionCarPo.class);
    	JsonData jsonData = new JsonData();
        Map<String, Object> resultMap = coonShopService.saveShopDistributionCar(po.getList()).getColumns();
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
     * 获取进货单列表接口
     *
     * @param jsonObj 参数
     * @return  进货单列表
     */
    @SuppressWarnings("unchecked")
	@RequestMapping("getShopDistributionCarListPage")
    @ResponseBody
    public JsonData getShopDistributionCarListPage(String jsonObj) {
    	CoolDistributionCar coolDistributionCar = JsonUtil.readValue(jsonObj, CoolDistributionCar.class);
    	JsonData jsonData = new JsonData();
        Map<String, Object> resultMap = coonShopService.getShopDistributionCarListPage(coolDistributionCar).getColumns();
        jsonData.setSuccess(resultMap.get("success") == null ? false : (Boolean)resultMap.get("success"));
        resultMap.remove("success");
        if (resultMap.get("msg") != null) {
            jsonData.setMsg((String)resultMap.get("msg"));
            resultMap.remove("msg");
        }
        if (resultMap.get("carList") != null) {
            jsonData.setRows((List<CoolDistributionCarInfo>)resultMap.get("carList"));
            resultMap.remove("carList");
        }
        jsonData.setData(resultMap);
        return jsonData;
    }
    
    /**
     * 删除进货单
     *
     * @param jsonObj 参数
     * @return  是否删除成功
     */
	@RequestMapping("deleteShopDistributionCar")
    @ResponseBody
    public JsonData deleteShopDistributionCar(String jsonObj) {
    	CoolDistributionCar coolDistributionCar = JsonUtil.readValue(jsonObj, CoolDistributionCar.class);
    	JsonData jsonData = new JsonData();
        Map<String, Object> resultMap = coonShopService.deleteShopDistributionCar(coolDistributionCar).getColumns();
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
     * 推荐开店申请接口
     *
     * @param jsonObj 参数
     * @return  是否保存成功
     */
	@RequestMapping("saveRecommendApply")
    @ResponseBody
    public JsonData saveRecommendApply(String jsonObj) {
		CoonShop coonShop = JsonUtil.readValue(jsonObj, CoonShop.class);
    	JsonData jsonData = new JsonData();
        Map<String, Object> resultMap = coonShopService.saveRecommendApply(coonShop).getColumns();
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
     * 获取合伙人奖励接口
     *
     * @param jsonObj 参数
     * @return  合伙人奖励列表
     */
	@SuppressWarnings("unchecked")
	@RequestMapping("getShopPartner")
    @ResponseBody
    public JsonData getShopPartner(String jsonObj) {
		CoolOrderParam coolOrderParam = JsonUtil.readValue(jsonObj, CoolOrderParam.class);
    	JsonData jsonData = new JsonData();
        Map<String, Object> resultMap = coonShopService.getShopPartner(coolOrderParam).getColumns();
        jsonData.setSuccess(resultMap.get("success") == null ? false : (Boolean)resultMap.get("success"));
        resultMap.remove("success");
        if (resultMap.get("msg") != null) {
            jsonData.setMsg((String)resultMap.get("msg"));
            resultMap.remove("msg");
        }
        if (resultMap.get("partnerList") != null) {
            jsonData.setRows((List<CoonShopPartnerInfo>)resultMap.get("partnerList"));
            resultMap.remove("partnerList");
        }
        jsonData.setData(resultMap);
        return jsonData;
    }
	
	/**
     * 获取合伙人奖励明细列表接口
     *
     * @param jsonObj 参数
     * @return  合伙人奖励列表
     */
	@SuppressWarnings("unchecked")
	@RequestMapping("getShopPartnerDetailList")
    @ResponseBody
    public JsonData getShopPartnerDetailList(String jsonObj) {
		CoolOrderParam coolOrderParam = JsonUtil.readValue(jsonObj, CoolOrderParam.class);
    	JsonData jsonData = new JsonData();
        Map<String, Object> resultMap = coonShopService.getShopPartnerDetailList(coolOrderParam).getColumns();
        jsonData.setSuccess(resultMap.get("success") == null ? false : (Boolean)resultMap.get("success"));
        resultMap.remove("success");
        if (resultMap.get("msg") != null) {
            jsonData.setMsg((String)resultMap.get("msg"));
            resultMap.remove("msg");
        }
        if (resultMap.get("partnerList") != null) {
            jsonData.setRows((List<CoonShopPartnerInfo>)resultMap.get("partnerList"));
            resultMap.remove("partnerList");
        }
        jsonData.setData(resultMap);
        return jsonData;
    }
	
	/**
     * 获取店铺及推荐商品列表接口
     *
     * @param jsonObj 参数
     * @return  店铺及推荐商品列表
     */
	@SuppressWarnings("unchecked")
	@RequestMapping("getShopListForUser")
    @ResponseBody
    public JsonData getShopListForUser(String jsonObj) {
		CoonShop coonShop = JsonUtil.readValue(jsonObj, CoonShop.class);
    	JsonData jsonData = new JsonData();
        Map<String, Object> resultMap = coonShopService.getShopListForUser(coonShop).getColumns();
        jsonData.setSuccess(resultMap.get("success") == null ? false : (Boolean)resultMap.get("success"));
        resultMap.remove("success");
        if (resultMap.get("msg") != null) {
            jsonData.setMsg((String)resultMap.get("msg"));
            resultMap.remove("msg");
        }
        if (resultMap.get("shopList") != null) {
            jsonData.setRows((List<CoonShop>)resultMap.get("shopList"));
            resultMap.remove("shopList");
        }
        jsonData.setData(resultMap);
        return jsonData;
    }
	
	/**
     * 获取店铺热销商品列表接口
     *
     * @param jsonObj 参数
     * @return  店铺热销商品列表
     */
	@SuppressWarnings("unchecked")
	@RequestMapping("getHotSellProductList")
    @ResponseBody
    public JsonData getHotSellProductList(String jsonObj) {
		CoonShopProductParam param = JsonUtil.readValue(jsonObj, CoonShopProductParam.class);
    	JsonData jsonData = new JsonData();
        Map<String, Object> resultMap = coonShopService.getHotSellProductList(param).getColumns();
        jsonData.setSuccess(resultMap.get("success") == null ? false : (Boolean)resultMap.get("success"));
        resultMap.remove("success");
        if (resultMap.get("msg") != null) {
            jsonData.setMsg((String)resultMap.get("msg"));
            resultMap.remove("msg");
        }
        if (resultMap.get("productList") != null) {
            jsonData.setRows((List<CoonShopProductInfo>)resultMap.get("productList"));
            resultMap.remove("productList");
        }
        jsonData.setData(resultMap);
        return jsonData;
    }
	
	/**
     *  保存收藏店铺接口
     *
     * @param jsonObj 参数
     * @return  是否保存成功
     */
	@RequestMapping("saveShopFav")
    @ResponseBody
    public JsonData saveShopFav(String jsonObj) {
		CoonShopFav coonShopFav = JsonUtil.readValue(jsonObj, CoonShopFav.class);
    	JsonData jsonData = new JsonData();
        Map<String, Object> resultMap = coonShopService.saveShopFav(coonShopFav).getColumns();
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
     *  取消收藏店铺接口
     *
     * @param jsonObj 参数
     * @return  是否取消成功
     */
	@RequestMapping("deleteShopFav")
    @ResponseBody
    public JsonData deleteShopFav(String jsonObj) {
		CoonShopFavInfo coonShopFavInfo = JsonUtil.readValue(jsonObj, CoonShopFavInfo.class);
    	JsonData jsonData = new JsonData();
        Map<String, Object> resultMap = coonShopService.deleteShopFav(coonShopFavInfo).getColumns();
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
     * 查询体验店列表接口
     *
     * @param jsonObj 店参数
     * @return 体验店列表
     */
    @SuppressWarnings("unchecked")
	@RequestMapping("getExperienceShopList")
    @ResponseBody
    public JsonData getExperienceShopList(String jsonObj) {
    	CoonShop coonShop = JsonUtil.readValue(jsonObj, CoonShop.class);
    	JsonData jsonData = new JsonData();
        Map<String, Object> resultMap = coonShopService.getExperienceShopList(coonShop).getColumns();
        jsonData.setSuccess(resultMap.get("success") == null ? false : (Boolean)resultMap.get("success"));
        resultMap.remove("success");
        if (resultMap.get("msg") != null) {
            jsonData.setMsg((String)resultMap.get("msg"));
            resultMap.remove("msg");
        } 
        if (resultMap.get("experienceShopList") != null) {
        	jsonData.setRows((List<CoonShop>)resultMap.get("experienceShopList"));        	
            resultMap.remove("experienceShopList");
        } 
        jsonData.setData(resultMap);
        return jsonData;
    }
    
    /**
     * 查询店铺分享接口
     *
     * @param jsonObj 酷店参数
     * @return 酷店分享列表
     */
    @SuppressWarnings("unchecked")
	@RequestMapping("getShareList")
    @ResponseBody
    public JsonData getShareList(String jsonObj) {
    	CoonShopProductParam param = JsonUtil.readValue(jsonObj, CoonShopProductParam.class);
    	JsonData jsonData = new JsonData();
        Map<String, Object> resultMap = coonShopService.getShareList(param).getColumns();
        jsonData.setSuccess(resultMap.get("success") == null ? false : (Boolean)resultMap.get("success"));
        resultMap.remove("success");
        if (resultMap.get("msg") != null) {
            jsonData.setMsg((String)resultMap.get("msg"));
            resultMap.remove("msg");
        } 
        if (resultMap.get("shareList") != null) {
        	jsonData.setRows((List<CoonShopShare>)resultMap.get("shareList"));        	
            resultMap.remove("shareList");
        } 
        jsonData.setData(resultMap);
        return jsonData;
    }
    
    /**
     * 保存分享经验心得接口
     *
     * @param jsonObj 酷店参数
     * @return 是否保存成功
     */
	@RequestMapping("saveShopShareExperience")
    @ResponseBody
    public JsonData saveShopShareExperience(String jsonObj) {
		CoonShopShareExperience coonShopShareExperience = JsonUtil.readValue(jsonObj, CoonShopShareExperience.class);
    	JsonData jsonData = new JsonData();
        Map<String, Object> resultMap = coonShopService.saveShopShareExperience(coonShopShareExperience).getColumns();
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
     * 删除分享经验心得接口
     *
     * @param jsonObj 酷店参数
     * @return 是否删除成功
     */
	@RequestMapping("deleteShopShareExperience")
    @ResponseBody
    public JsonData deleteShopShareExperience(String jsonObj) {
		CoonShopShareExperience coonShopShareExperience = JsonUtil.readValue(jsonObj, CoonShopShareExperience.class);
    	JsonData jsonData = new JsonData();
        Map<String, Object> resultMap = coonShopService.deleteShopShareExperience(coonShopShareExperience).getColumns();
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
     * 获取分享经验心得接口
     *
     * @param jsonObj 酷店参数
     * @return 分享经验心得列表
     */
	@SuppressWarnings("unchecked")
	@RequestMapping("getShopShareExperiencePage")
    @ResponseBody
    public JsonData getShopShareExperiencePage(String jsonObj) {
		CoonShopShareParam coonShopShareParam = JsonUtil.readValue(jsonObj, CoonShopShareParam.class);
    	JsonData jsonData = new JsonData();
        Map<String, Object> resultMap = coonShopService.getShopShareExperiencePage(coonShopShareParam).getColumns();
        jsonData.setSuccess(resultMap.get("success") == null ? false : (Boolean)resultMap.get("success"));
        resultMap.remove("success");
        if (resultMap.get("msg") != null) {
            jsonData.setMsg((String)resultMap.get("msg"));
            resultMap.remove("msg");
        } 
        if (resultMap.get("shareList") != null) {
            jsonData.setRows((List<CoonShopShareExperience>)resultMap.get("shareList"));
            resultMap.remove("shareList");
        } 
        jsonData.setData(resultMap);
        return jsonData;
    }

}
