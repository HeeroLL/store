package com.isell.app;

import java.math.BigDecimal;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.isell.app.dao.entity.BillReqParam;
import com.isell.app.dao.entity.GoodsReqParam;
import com.isell.app.dao.entity.OrderCount;
import com.isell.app.dao.entity.OrderDetail;
import com.isell.app.dao.entity.OrderGoods;
import com.isell.app.dao.entity.OrderParam;
import com.isell.app.dao.entity.OrderSumInfo;
import com.isell.app.dao.entity.Product;
import com.isell.app.dao.entity.PulldownEntity;
import com.isell.app.dao.entity.SearchParam;
import com.isell.app.dao.entity.SearchType;
import com.isell.app.service.AppService;
import com.isell.app.service.BmsManageService;
import com.isell.core.config.BisConfig;
import com.isell.core.util.JsonData;
import com.isell.core.util.JsonUtil;

@Controller
@RequestMapping("bmsService")
public class BmsServiceController {
	 @Resource
    private AppService appService;
    /**
     * 配置信息
     */
    @Resource
    private BisConfig config;
    
    @Resource
    private BmsManageService bmsManageService;
    /**
     * 注册同步用户
     * @param jsonObj
     * @return
     */
    @RequestMapping("regShopUser")
    @ResponseBody
    public JsonData regShopUser(String jsonObj)
    {
    	JsonData vo=new JsonData();
    	SearchParam searchParam=JsonUtil.readValue(jsonObj, SearchParam.class);
    	int result=this.appService.regShopUser(searchParam);
    	if(result>0)
    	{
    		vo.setSuccess(true);
    	}else
    	{
    		vo.setSuccess(false);
    		vo.setMsg("注册失败");
    	}
    	return vo;
    }
    /**
     * 
     * @param jsonObj
     * @return
     */
    @RequestMapping("updateShopname")
    @ResponseBody
    public JsonData updateShopname(String jsonObj)
    {
    	JsonData vo=new JsonData();
    	SearchParam searchParam=JsonUtil.readValue(jsonObj, SearchParam.class);
    	 int result=this.appService.updateShopname(searchParam);
    	if(result>0)
    	{
    		vo.setSuccess(true);
    	}else
    	{
    		vo.setSuccess(false);
    		vo.setMsg("更新失败");
    	}
    	
    	return vo;
    }
    
    
    /**
     * 
     * @param jsonObj
     * @return
     */
    @RequestMapping("queryAllGoods")
    @ResponseBody
    public JsonData queryAllGoods(String jsonObj)
    {
    	 
    	JsonData vo=new JsonData();
    	SearchParam searchParam=JsonUtil.readValue(jsonObj, SearchParam.class);
    	searchParam.setImg_domain(config.getImgDomain());
    	List<Product>list=this.appService.queryBmsProductList(searchParam);
    	if(list.size()>0)
    	{
    		vo.setSuccess(true);
    		vo.setData(list);
    		vo.setTotal(this.appService.queryBmsProductAllNum(searchParam));
    	}else
    	{
    		vo.setSuccess(false);
    		vo.setMsg("无数据");
    	}
    	return vo;
    }
   
    
    
    /**
     * 
     * @param jsonObj
     * @return
     */
    @RequestMapping("updateShopProducts")
    @ResponseBody
    public JsonData updateShopProducts(String jsonObj)
    {
    	JsonData vo=new JsonData();
    	SearchParam searchParam=JsonUtil.readValue(jsonObj, SearchParam.class);
    	int result=this.appService.updateShopProducts(searchParam);
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
    @RequestMapping("queryBmsBindGoodsId")
    @ResponseBody
    public JsonData queryBmsBindGoodsId(String jsonObj)
    {
    	JsonData vo=new JsonData();
    	SearchParam searchParam=JsonUtil.readValue(jsonObj, SearchParam.class);
    	searchParam.setImg_domain(config.getImgDomain());
    	
    	List<Product>list=this.appService.queryBmsBindGoodsId(searchParam);
    	if(list.size()>0)
    	{
    		vo.setSuccess(true);
    		vo.setData(list);
    		vo.setTotal(this.appService.queryBmsBindGoodsAllNum(searchParam));
    	}else
    	{
    		vo.setSuccess(false);
    		vo.setMsg("无数据");
    	}
    	return vo;
    }
    
    /**
     * 根据父分类id查询子分类
     * @param jsonObj
     * @return
     */
    @RequestMapping("queryChildCatelogListByParent")
    @ResponseBody
    public JsonData queryChildCatelogListByParent(String jsonObj)
    {
    	JsonData vo=new JsonData();
    	SearchParam searchParam=JsonUtil.readValue(jsonObj, SearchParam.class);
    	List<SearchType>list=this.appService.queryChildCatelogListByParent(searchParam);
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
    
    
    
    @RequestMapping("updateOrderState")
    @ResponseBody
    public JsonData updateOrderState(String jsonObj)
    {
    	JsonData vo=new JsonData();
    	SearchParam searchParam=JsonUtil.readValue(jsonObj, SearchParam.class);
    	int result=this.appService.updateOrderState(searchParam);
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
    @RequestMapping("updateShopProFlag")
    @ResponseBody
    public JsonData updateShopProFlag(String jsonObj)
    {
    	JsonData vo=new JsonData();
    	SearchParam searchParam=JsonUtil.readValue(jsonObj, SearchParam.class);
    	int result=this.appService.updateShopProFlag(searchParam);
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
    @RequestMapping("queryBmsMyOrderCount")
    @ResponseBody
    public JsonData queryBmsMyOrderCount(String jsonObj)
    {
    	JsonData vo=new JsonData();
    	SearchParam searchParam=JsonUtil.readValue(jsonObj, SearchParam.class);
    	OrderCount orderCount=this.appService.queryBmsMyOrderCount(searchParam);
    	if(orderCount!=null)
    	{
    		vo.setSuccess(true);
    		vo.setData(orderCount);
    	}else
    	{
    		vo.setSuccess(false);
    		vo.setMsg("无数据");
    	}
    	
    	return vo;
    }
    /**
     * 
     * @param jsonObj
     * @return
     */
    @RequestMapping("queryBmsMyOrder")
    @ResponseBody
    public JsonData queryBmsMyOrder(String jsonObj)
    {
    	JsonData vo=new JsonData();
    	OrderParam order=JsonUtil.readValue(jsonObj, OrderParam.class);
    	order.setImgdomain(config.getImgDomain());
    	List<OrderDetail>list=this.appService.queryBmsMyOrder(order);
    	if(list.size()>0)
    	{
    		vo.setSuccess(true);
    		vo.setData(list);
    		vo.setTotal(this.appService.queryBMsMyOrderAllNum(order));
    	}else
    	{
    		vo.setSuccess(false);
    		vo.setMsg("无数据");
    	}
    	return vo;
    }
    /**
     * 查询订单详情
     * @param jsonObj
     * @return
     */
    @RequestMapping("queryOrderDetailForBms")
    @ResponseBody
    public JsonData queryOrderDetailForBms(String jsonObj)
    {
    	JsonData vo=new JsonData();
    	SearchParam searchParam =JsonUtil.readValue(jsonObj, SearchParam.class);
    	searchParam.setImg_domain(config.getImgDomain());
    	OrderDetail info=this.appService.queryOrderDetailForBms(searchParam);
    	if(info!=null)
    	{
    		vo.setSuccess(true);
    		vo.setData(info);
    	}else
    	{
    		vo.setSuccess(false);
    		vo.setMsg("无数据");
    	}
    	return vo;
    }

    /**
     * 查询账单信息列表
     * @param jsonObj
     * @return
     */
    @RequestMapping("queryBmsBillList")
    @ResponseBody
    public JsonData queryBmsBillList(String jsonObj) {
    	JsonData vo=new JsonData();
    	BillReqParam order=JsonUtil.readValue(jsonObj, BillReqParam.class);
    	List<OrderDetail>list=this.bmsManageService.queryBmsMyOrder(order);
    	if(list.size() > 0) {
    		vo.setSuccess(true);
    		vo.setData(list);
    		vo.setTotal(this.bmsManageService.queryBillCount(order));
    	}else
    	{
    		vo.setSuccess(false);
    		vo.setMsg("无数据");
    	}
    	return vo;
    }
    
    /**
     * 统计商品信息
     * @param jsonObj
     * @return
     */
    @RequestMapping("sumGoodsInfoList")
    @ResponseBody
    public JsonData sumGoodsInfoList(String jsonObj) {
    	JsonData vo=new JsonData();
    	GoodsReqParam params = JsonUtil.readValue(jsonObj, GoodsReqParam.class);
    	params.setImgDomain(config.getImgDomain());
    	List<OrderGoods>list = this.bmsManageService.sumGoodsInfoList(params);
    	if(list.size() > 0) {
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
     * 统计商品信息
     * @param jsonObj
     * @return
     */
    @RequestMapping("sumGoodsInfo")
    @ResponseBody
    public JsonData sumGoodsInfo(String jsonObj) {
    	JsonData vo=new JsonData();
    	GoodsReqParam params = JsonUtil.readValue(jsonObj, GoodsReqParam.class);
    	List<OrderGoods>list = this.bmsManageService.sumAllGoodsInfo(params);
    	if(list.size() > 0) {
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
     * 统计订单信息
     * @param jsonObj
     * @return
     */
    @RequestMapping("sumOrderInfo")
    @ResponseBody
    public JsonData sumOrderInfo(String jsonObj) {
    	JsonData vo=new JsonData();
    	OrderParam params = JsonUtil.readValue(jsonObj, OrderParam.class);
    	List<OrderSumInfo>list = this.bmsManageService.sumAllOrderInfo(params);
    	if(list.size() > 0) {
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
     * 获取酷店里面的新品的数量
     * @param jsonObj
     * @return
     */
    @RequestMapping("getCountOfShopNewGoods")
    @ResponseBody
    public JsonData getCountOfShopNewGoods(String jsonObj) {
    	JsonData vo=new JsonData();
    	OrderParam params = JsonUtil.readValue(jsonObj, OrderParam.class);
    	int newGoodsCount = this.bmsManageService.getCountOfShopNewGoods(params);
		vo.setSuccess(true);
		vo.setData(newGoodsCount);
    	return vo;
    }

    /**
     * 获取未结算订单金额
     * @param jsonObj
     * @return
     */
    @RequestMapping("getUnsettleAmount")
    @ResponseBody
    public JsonData getUnsettleAmount(String jsonObj) {
    	JsonData vo=new JsonData();
    	OrderParam params = JsonUtil.readValue(jsonObj, OrderParam.class);
    	BigDecimal unsettleAmount = this.bmsManageService.getUnsettleAmount(params);
		vo.setSuccess(true);
		vo.setData(unsettleAmount);
    	return vo;
    }
    
    /**
     * 获取品牌下拉框
     * @param jsonObj
     * @return
     */
    @RequestMapping("getBrandAsPullDown")
    @ResponseBody
    public JsonData getBrandAsPullDown(String jsonObj) {
    	JsonData vo=new JsonData();
    	List<PulldownEntity>list = this.bmsManageService.getBrandAsPullDown();
    	if(list.size() > 0) {
    		vo.setSuccess(true);
    		vo.setData(list);
    	}else
    	{
    		vo.setSuccess(false);
    		vo.setMsg("无数据");
    	}
    	return vo;
    }
}
