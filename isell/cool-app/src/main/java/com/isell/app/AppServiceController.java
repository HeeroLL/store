package com.isell.app;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.jstl.core.Config;
import javax.xml.bind.annotation.XmlAccessOrder;

import org.apache.axis.utils.XMLUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.lang3.StringUtils;
import org.codehaus.jackson.annotate.JsonUnwrapped;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import util.FileUtil;

import com.isell.app.dao.entity.CenterOrder;
import com.isell.app.dao.entity.CenterOrderParam;
import com.isell.app.dao.entity.CollParam;
import com.isell.app.dao.entity.CollectInfo;
import com.isell.app.dao.entity.CoolMember;
import com.isell.app.dao.entity.HelpType;
import com.isell.app.dao.entity.HomePageImage;
import com.isell.app.dao.entity.Hotword;
import com.isell.app.dao.entity.LoginParam;
import com.isell.app.dao.entity.MemberAddress;
import com.isell.app.dao.entity.MemberCommunity;
import com.isell.app.dao.entity.Notice;
import com.isell.app.dao.entity.OrderDetail;
import com.isell.app.dao.entity.OrderParam;
import com.isell.app.dao.entity.OrderRecv;
import com.isell.app.dao.entity.OrderReturn;
import com.isell.app.dao.entity.Product;
import com.isell.app.dao.entity.ProductImg;
import com.isell.app.dao.entity.SearchData;
import com.isell.app.dao.entity.SearchParam;
import com.isell.app.dao.entity.SearchProduct;
import com.isell.app.dao.entity.SearchShop;
import com.isell.app.dao.entity.SearchType;
import com.isell.app.service.AppService;
import com.isell.cache.service.JVMCacheService;
import com.isell.core.config.BisConfig;
import com.isell.core.util.FileUploadUtil;
import com.isell.core.util.JsonData;
import com.isell.core.util.JsonUtil;
import com.isell.core.util.Record;
import com.isell.ei.logistics.kuaidi100.service.KuaidiService;
import com.isell.service.member.vo.CoolMemberFavorites;
import com.isell.service.member.vo.CoolUser;
import com.isell.service.member.vo.UserInfo;
import com.isell.service.order.service.OrderService;
import com.isell.service.order.vo.CoolOrder;
/**
 * 
 * @author ycl
 *2015年12月30
 */
@Controller
@RequestMapping("appservice")
public class AppServiceController {
	
    @Resource
    private AppService appService;
    
    /**
     * 订单接口
     */
    @Resource
    private OrderService orderService;
    /**
     * 快递100查询接口
     */
    @Resource
    private KuaidiService KuaidiService;
    
    /**
     * 配置信息
     */
    @Resource
    private BisConfig config;
    
    /**
     * 服务接口
     */
    @Value("${order_limit}")
    private String order_limit;
    
    /**
     * JVM缓存服务接口
     */
    @Resource
    protected JVMCacheService jvmCacheService;
    
    private static final String partner="2088021829532240";
    
    private static final String alipay_key="utjn3sfouen3ynpesrv227xx91yllha8";
    
    //app首页轮播图接口
    @RequestMapping("homepageimage")
    @ResponseBody
    public JsonData homepageimage(String jsonObj)
    {
    	JsonData jsonData = new JsonData();
    	Map<String, Object> resultMap=appService.queryHomePageImages().getColumns();
    	jsonData.setSuccess(resultMap.get("success") == null ? false : (Boolean)resultMap.get("success"));
    	 resultMap.remove("success");
         if (resultMap.get("msg") != null) {
             jsonData.setMsg((String)resultMap.get("msg"));
             resultMap.remove("msg");
         } 
          
         jsonData.setData(resultMap.get("hpImagelist"));
    	
    	return jsonData;
    }
    
    
    
    
    //app首页 公告
    @RequestMapping("homepagenotice")
    @ResponseBody
    public JsonData homepagenotice(String jsonObj)
    {
    	JsonData jsonData = new JsonData();
    	Map<String,Object>resultMap=appService.queryHomePageNotices().getColumns();
    	jsonData.setSuccess(resultMap.get("success") == null ? false : (Boolean)resultMap.get("success"));
    	resultMap.remove("success");
    	if (resultMap.get("msg") != null) {
            jsonData.setMsg((String)resultMap.get("msg"));
            resultMap.remove("msg");
        }
    	 
         jsonData.setData(resultMap.get("noticelist"));
    	
    	
    	return jsonData;
    }
    /**
     * 公告详情
     * @param jsonObj
     * @return
     */
    @RequestMapping("hpnoticeinfo")
    @ResponseBody 
    public  JsonData hpnoticeinfo(String jsonObj)
    {
    	JsonData jsonData = new JsonData();
    	Notice notice=JsonUtil.readValue(jsonObj, Notice.class); 
    	
    	Map<String,Object>resultMap=appService.queryHpnoticeinfo(notice).getColumns();
    	jsonData.setSuccess(resultMap.get("success") == null ? false : (Boolean)resultMap.get("success"));
        resultMap.remove("success");
        if (resultMap.get("msg") != null) {
             jsonData.setMsg((String)resultMap.get("msg"));
             resultMap.remove("msg");
        }
        
        jsonData.setData(resultMap.get("noticeinfo"));
    	return jsonData;
    }
    /**
     * 根据用户id查询用户详细信息
     * @param jsonObj
     * @return
     */
    @RequestMapping("userDetail_wap")
    @ResponseBody
    public JsonData userDetailWap(String jsonObj,String accessCode)
    {
    	JsonData jsonData = new JsonData();
    	/*String result = jvmCacheService.get("user_" + accessCode);
    	 if( result != null){*/
    		 
    	    	CoolUser user=JsonUtil.readValue(jsonObj, CoolUser.class);
    	    	Map<String,Object>resultMap=appService.queryUserDetailById(user).getColumns();
    	    	jsonData.setSuccess(resultMap.get("success") == null ? false : (Boolean)resultMap.get("success"));
    	    	 resultMap.remove("success");
    	         if (resultMap.get("msg") != null) {
    	              jsonData.setMsg((String)resultMap.get("msg"));
    	              resultMap.remove("msg");
    	         }
    	         jsonData.setData(resultMap.get("userinfo"));
    	/* }else
    	 {
    		 jsonData.setSuccess(false);
         	throw new RuntimeException("exception.access.service.user-null");
    	 }*/
    	
     	return jsonData;
    	
    }
    /**
     * 根据用户id查询用户详细信息
     * @param jsonObj
     * @return
     */
    @RequestMapping("userDetail")
    @ResponseBody
    public JsonData userDetail(String jsonObj,String accessCode)
    {
    	JsonData jsonData = new JsonData();
    	String result = jvmCacheService.get("user_" + accessCode);
    	 if( result != null){
    		 
    	    	CoolUser user=JsonUtil.readValue(jsonObj, CoolUser.class);
    	    	Map<String,Object>resultMap=appService.queryUserDetailById(user).getColumns();
    	    	jsonData.setSuccess(resultMap.get("success") == null ? false : (Boolean)resultMap.get("success"));
    	    	 resultMap.remove("success");
    	         if (resultMap.get("msg") != null) {
    	              jsonData.setMsg((String)resultMap.get("msg"));
    	              resultMap.remove("msg");
    	         }
    	         jsonData.setData(resultMap.get("userinfo"));
    	 }else
    	 {
    		 jsonData.setSuccess(false);
         	throw new RuntimeException("exception.access.service.user-null");
    	 }
    	
     	return jsonData;
    	
    }
   
    /**
     * 首页主题获取
     * @param jsonObj
     * @return
     */
    @RequestMapping("themelist")
    @ResponseBody
    public JsonData themelist(String jsonObj)
    {
    	JsonData jsonData = new JsonData();
    	CoolMember coolMember=JsonUtil.readValue(jsonObj, CoolMember.class);
    	
    	Map<String,Object>resultMap=appService.queryThemelist(coolMember).getColumns();
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
     * 下单前验证商品库存 是否足量
     * @param jsonObj
     * @return
     */
    @RequestMapping("checkGoodsStock")
    @ResponseBody
    public JsonData checkGoodsStock(String jsonObj)
    {
    	JsonData jsonData = new JsonData();
    	OrderParam orderParam=JsonUtil.readValue(jsonObj, OrderParam.class);
    	int result=this.appService.checkGoodsStockIsCanPay(orderParam);
    	if(result>0)
    	{
    		jsonData.setSuccess(true);
    	}else
    	{
    		jsonData.setSuccess(false);
    		jsonData.setMsg("库存不足");
    	}
    	return jsonData;
    }
    
    
    /**
     * 保存用户收藏宝贝
     */
    @RequestMapping("saveMemberRecGoods")
    @ResponseBody
    public JsonData saveMemberRecGoods(String jsonObj)
    {
    	JsonData jsonData = new JsonData();
    	CoolMemberFavorites coolMemberFavorites=JsonUtil.readValue(jsonObj, CoolMemberFavorites.class);
    	Map<String,Object>resultMap=appService.saveMemberRecGoods(coolMemberFavorites).getColumns();
    	jsonData.setSuccess(resultMap.get("success") == null ? false : (Boolean)resultMap.get("success"));
    	resultMap.remove("success");
    	
    	//jsonData.setData(resultMap);
    	return jsonData;
    }
    /**
     * 搜索热词
     * @param jsonObj
     * @return
     */
    @RequestMapping("hotwordlist")
    @ResponseBody
    public JsonData hotwordlist(String jsonObj)
    {
    	JsonData jsonData = new JsonData();
    	List<Hotword>list=this.appService.queryHotwordlist();
    	if(list.size()>0)
    	{
    		jsonData.setSuccess(true);
    		//jsonData.setRows(list);
    		jsonData.setData(list);
    	}else
    	{
    		jsonData.setSuccess(false);
    		jsonData.setMsg("无数据");
    	}
    	return jsonData;
    }
    /**
     * 买家删除订单  已取消、已完成和已退款
     * @param jsonObj
     * @return
     */
    @RequestMapping("mDdelOrderByOrderNo")
    @ResponseBody
    public JsonData mDdelOrderByOrderNo(String jsonObj)
    {
    	JsonData vo = new JsonData();
    	OrderParam orderParam=JsonUtil.readValue(jsonObj, OrderParam.class);
    	int result=this.appService.mDdelOrderByOrderNo(orderParam);
    	if(result>0)
    	{
    		vo.setSuccess(true);
    	}else
    	{
    		vo.setSuccess(false);
    		vo.setMsg("该订单不可被删除");
    	}
    	return vo;
    }
    /**
     * 
     * @param jsonObj
     * @return
     */
    @RequestMapping("receiveOrderForApp")
    @ResponseBody
    public JsonData receiveOrderForApp(String jsonObj)
    {
    	JsonData vo=new JsonData();
    	CoolOrder order=JsonUtil.readValue(jsonObj, CoolOrder.class);
    	order = orderService.getCoolOrderDetailByOrderNo(order.getOrderNo());
    	 Map<String, Object> paramMap = new HashMap<String, Object>();
         paramMap.put("id", order.getId());
         Record record = orderService.updateCoolOrderRec(paramMap);
         vo.setSuccess(record.getBoolean("success"));
         Map map=new HashMap();
         map.put("receiptTime", record.get("receiptTime"));
         vo.setData(map);
         vo.setMsg(record.getStr("msg"));
    	return vo;
    }
    /**
     * 保存用户发布的评价
     * @param jsonObj
     * @return
     */
    @RequestMapping("saveUserRecOrder")
    @ResponseBody
    public JsonData saveUserRecOrder(String jsonObj)
    {
    	JsonData vo=new JsonData();
    	OrderRecv rec=JsonUtil.readValue(jsonObj, OrderRecv.class);
    	 
    	int result=this.appService.saveUserRecOrder(rec);
    	if(result==1)
    	{
    		vo.setSuccess(true);
    	}
    	else if(result==2)
    	{
    		vo.setSuccess(false);
    		vo.setMsg("该订单已评价");
    	}else 
    	{
    		vo.setSuccess(false);
    		vo.setMsg("保存失败");
    	}
    	return vo;
    }
    /**
     * 商品关键字搜索
     * @param jsobObj
     * @return
     */
    @RequestMapping("searchGoods")
    @ResponseBody
    public JsonData searchGoods(String jsonObj)
    {
    	JsonData jsonData = new JsonData();
    	SearchParam searchParam=JsonUtil.readValue(jsonObj, SearchParam.class);
    	searchParam.setImg_domain(config.getImgDomain());
    	//验证用户是否有关注店铺
    	if(StringUtils.isNotBlank(searchParam.getShopcode()))
    	{
    		String s_id=this.appService.queryShopIdByShopCode(searchParam);
    		if(StringUtils.isNotBlank(s_id))
    		{
    			searchParam.setSid(s_id);
        		List<SearchProduct>list=this.appService.querySearchFavShopGoods(searchParam);
        		SearchData data=new SearchData();
        		if(list.size()>0)
        		{
        			jsonData.setSuccess(true);
        			jsonData.setTotal(this.appService.querySearchFavShopGoodsNum(searchParam));
        			data.setRecords(list);
        			jsonData.setData(data);
        		}else
        		{
        			jsonData.setSuccess(false);
            		jsonData.setMsg("无数据");
        		}
    		}
    	}else
    	{
    		String  s_id=this.appService.checkUserIsRecShop(searchParam);
        	if(s_id!=null &&!"".equals(s_id))
        	{
        		searchParam.setSid(s_id);
        		List<SearchProduct>list=this.appService.querySearchFavShopGoods(searchParam);
        		SearchData data=new SearchData();
        		if(list.size()>0)
        		{
        			jsonData.setSuccess(true);
        			jsonData.setTotal(this.appService.querySearchFavShopGoodsNum(searchParam));
        			data.setRecords(list);
        			jsonData.setData(data);
        		}else
        		{
        			jsonData.setSuccess(false);
            		jsonData.setMsg("无数据");
        		}
        	}else
        	{
        		
        		List<SearchProduct>goodslist=this.appService.querySearchGoods(searchParam);
            	SearchData data=new SearchData();
            	if(goodslist.size()>0)
            	{
            		//jsonData.setTotal(this.appService.querySearchGoodsAllNum(searchParam));
            		data.setTotal(this.appService.querySearchGoodsAllNum(searchParam));
            		data.setRecords(goodslist);
            		jsonData.setSuccess(true);
            		jsonData.setData(data);
            	}else
            	{
            		//查询非订单数据
            		int result=this.appService.querySearchUnorderNum(searchParam);
            		if(result>0)
            		{
            			//jsonData.setTotal(result);
            			data.setTotal(result);
            			jsonData.setSuccess(true);
            			//jsonData.setRows(this.appService.querySearchGoodsUnOrder(searchParam));
            			data.setRecords(this.appService.querySearchGoodsUnOrder(searchParam));
            			jsonData.setData(data);
            		}else
            		{
            			jsonData.setSuccess(false);
                		jsonData.setMsg("无数据");
            		}
            		
            	}
        	}
    	}
    	
    	return jsonData;
    }
    /**
     * 分类搜索
     * @param jsonObj
     * @return
     */
    @RequestMapping("typeSearch")
    @ResponseBody
    public JsonData typeSearch(String jsonObj)
    {
    	JsonData jsonData = new JsonData();
    	SearchParam searchParam=JsonUtil.readValue(jsonObj, SearchParam.class);
    	searchParam.setImg_domain(config.getImgDomain());
    	List<SearchType>list=this.appService.typeSearch(searchParam);
    	if(list.size()>0)
    	{
    		jsonData.setSuccess(true);
    		//jsonData.setRows(list);
    		jsonData.setData(list);
    	}else
    	{
    		jsonData.setSuccess(false);
    		jsonData.setMsg("无数据");
    	}
    	return jsonData;
    }
    /**
     * 我的中心 我的订单
     * @param jsonObj
     * @return
     */
    @RequestMapping("queryMyOrderPage")
    @ResponseBody
    public JsonData queryMyOrderPage(String jsonObj)
    {
    	JsonData jsonData = new JsonData();
    	CenterOrderParam centerOrderParam=JsonUtil.readValue(jsonObj, CenterOrderParam.class);
    	centerOrderParam.setImgdomain(config.getImgDomain());
    	List<CenterOrder>list=this.appService.queryMyOrderPage(centerOrderParam);
    	jsonData.setTotal(this.appService.queryMyOrderTotalNum(centerOrderParam));
    	if(list.size()>0)
    	{
    		jsonData.setSuccess(true);
    		jsonData.setData(list);
    	}else
    	{
    		jsonData.setSuccess(false);
    		jsonData.setMsg("无数据");
    	}
    	return jsonData;
    }
    
    
    
    /**
     * 我的中心 我的收藏
     * @param jsonObj
     * @return
     */
    @RequestMapping("userCollect")
    @ResponseBody
    public JsonData userCollect(String jsonObj)
    {
    	JsonData jsonData = new JsonData();
    	SearchParam searchParam=JsonUtil.readValue(jsonObj, SearchParam.class);
    	searchParam.setImg_domain(config.getImgDomain());
    	List<CollectInfo>list=this.appService.queryUserCollect(searchParam);
    	if(list.size()>0)
    	{
    		jsonData.setTotal(this.appService.queryUserCollectTotalNum(searchParam));
    		jsonData.setData(list);
    		jsonData.setSuccess(true);
    	}else
    	{
    		jsonData.setSuccess(false);
    		jsonData.setMsg("无数据");
    	}
    	return jsonData;
    }
    /**
     * 取消订单
     * @param jsonObj
     * @return
     */
    @RequestMapping("cancleOrderByOrderno")
    @ResponseBody
    public JsonData cancleOrderByOrderno(String jsonObj)
    {
    	JsonData vo=new JsonData();
    	OrderParam orderParam=JsonUtil.readValue(jsonObj, OrderParam.class);
    	int result=this.appService.cancleOrderByOrderno(orderParam);
    	if(result>0)
    	{
    		vo.setSuccess(true);
    	}else
    	{
    		vo.setSuccess(false);
    		vo.setMsg("该订单不可被取消");
    	}
    	return vo;
    }
    
    /**
     * 
     * @param jsonObj
     * @return
     */
    @RequestMapping("getposturl")
    @ResponseBody
    public JsonData getposturl(String jsonObj)
    {
    	 JsonData jsonData = new JsonData();
    	 jsonData.setSuccess(false);
    	 CoolOrder order = JsonUtil.readValue(jsonObj, CoolOrder.class);
    	 
    	 order = orderService.getCoolOrderDetailByOrderNo(order.getOrderNo());
         if (order == null) {
             throw new RuntimeException("exception.order.null");
         }
         if(order.getPsfs().length()>0)
         {
        	 jsonData.setSuccess(true);
        	 String url=KuaidiService.WAP_URL+"?type="+KuaidiService.queryPostTypeByName(order.getPsfs())+"&postid="+order.getPsCode();
        	 jsonData.setData(url);
         }
    	 return jsonData;
    }
    
    /**
     * 查询订单详情
     * @param jsonObj
     * @return
     */
    @RequestMapping("queryOrderDetail")
    @ResponseBody
    public JsonData queryOrderDetail(String jsonObj)
    {
    	JsonData jsonData = new JsonData();
    	OrderParam order=JsonUtil.readValue(jsonObj, OrderParam.class);
    	order.setImgdomain(config.getImgDomain());
    	OrderDetail orderDetail=this.appService.queryOrderDetail(order);
    	if(orderDetail!=null)
    	{
    		jsonData.setSuccess(true);
    		jsonData.setData(orderDetail);
    	}else
    	{
    		jsonData.setSuccess(false);
    		jsonData.setMsg("无数据");
    	}
    	return jsonData;
    }
    /**
     * 
     * @param jsonObj
     * @return
     */
    @RequestMapping("queryOrderLimit")
    @ResponseBody
    public JsonData queryOrderLimit(String jsonObj)
    {
    	JsonData jsonData = new JsonData();
    	jsonData.setSuccess(true);
    	jsonData.setData(order_limit);
    	return jsonData;
    }
    
    /**
     * 保存客户端下单数据
     * @param jsonObj
     * @return
     */
    @RequestMapping("savePreOrder")
    @ResponseBody
    public JsonData savePreOrder(String jsonObj)
    {
    	JsonData jsonData = new JsonData();
    	OrderParam orderParam=JsonUtil.readValue(jsonObj, OrderParam.class);
    	orderParam.setOrder_limit(order_limit);
    	int result=this.appService.savePreOrder(orderParam);
    	if(result==1)
    	{
    		jsonData.setSuccess(true);
    		OrderReturn ret=new OrderReturn();
    		ret.setGoodname(orderParam.getGoodname());
    		ret.setOrderno(orderParam.getOrderseq());
    		ret.setRemark(orderParam.getComment());
    		ret.setTotal(orderParam.getTotal());
    		jsonData.setData(ret);
    	}else if(result==2)
    	{
    		jsonData.setMsg("参数错误，根据商品主键" + orderParam.getGoodid() + "无法获取商品信息");
    		jsonData.setSuccess(false);
    	}else if(result==3)
    	{
    		jsonData.setMsg("参数错误，根据商品规格主键" + orderParam.getGgid() + "无法获取商品规格信息");
    		jsonData.setSuccess(false);
    	}else if(result==5)
    	{
    		jsonData.setMsg("商品库存不足");
    		jsonData.setSuccess(false);
    	}
    	return jsonData;
    }
    
    /**
     * 帮助中心
     * @param jsonObj
     * @return
     */
    @RequestMapping("helplist")
    @ResponseBody
    public JsonData helplist(String jsonObj)
    {
    	JsonData jsonData = new JsonData();
    	SearchParam searchParam=new SearchParam();
    	searchParam.setImg_domain(config.getImgDomain());
    	List<HelpType>list=this.appService.queryHelptypeList(searchParam);
    	if(list.size()>0)
    	{
    		jsonData.setSuccess(true);
    		jsonData.setData(list);
    	}else
    	{
    		jsonData.setSuccess(false);
    		jsonData.setMsg("无数据");
    	}
    	return jsonData;
    }
    
    
    /**
     * 手机号码+验证码 登录
     * @param jsonObj
     * @return
     */
    @RequestMapping("loginByValidateCode")
    @ResponseBody
    public JsonData loginByValidateCode(String jsonObj,String accessCode)
    {
    	JsonData jsonData = new JsonData();
    	LoginParam loginParam=JsonUtil.readValue(jsonObj, LoginParam.class);
    	String result = jvmCacheService.get("sms_" + accessCode+"_"+loginParam.getVacode());
    	if(result!=null)
    	{
    		loginParam.setImagedomain(config.getImgDomain());
        	UserInfo user=this.appService.queryLoginCoolUser(loginParam);
        	if(user!=null)
        	{
        		jsonData.setSuccess(true);
        		jsonData.setData(user);
        		jvmCacheService.set("user_" + accessCode, JsonUtil.writeValueAsString(user));
        	}else
        	{
        		jsonData.setSuccess(false);
        		jsonData.setMsg("无数据");
        	}
        	
    	}else
    	{
    		jsonData.setSuccess(false);
    		jsonData.setMsg("验证码错误");
    	}
    	return jsonData;
    }
    
    
    /**
     * 店铺列表
     * @param jsonObj
     * @return
     */
    @RequestMapping("queryShpolist")
    @ResponseBody
    public JsonData queryShpolist(String jsonObj)
    {
    	JsonData jsonData = new JsonData();
    	SearchParam searchParam=JsonUtil.readValue(jsonObj, SearchParam.class);  
    	searchParam.setImg_domain(config.getImgDomain());
    	List<SearchShop>list=this.appService.queryShpolist(searchParam);
    	if(list.size()>0)
    	{
    		jsonData.setSuccess(true);
    		jsonData.setData(list);
    	}else
    	{
    		jsonData.setSuccess(false);
    		jsonData.setMsg("无数据");
    	}
    	
    	return jsonData;
    }
    /**
     * 查询店铺详情
     * @param jsonObj
     * @return
     */
    @RequestMapping("queryShopinfo")
    @ResponseBody
    public JsonData queryShopinfo(String jsonObj)
    {
    	JsonData jsonData = new JsonData();
    	SearchShop shopParam=JsonUtil.readValue(jsonObj, SearchShop.class);
    	SearchShop info=this.appService.queryShopInfo(shopParam);
    	if(info!=null)
    	{
    		jsonData.setSuccess(true);
    		if(!"".equals(info.getShopimg()))
    		{
    			info.setShopimg(config.getImgDomain()+info.getShopimg());
    		}
    		jsonData.setData(info);
    	}else
    	{
    		jsonData.setSuccess(false);
    		jsonData.setMsg("无数据");
    	}
    	return jsonData;
    }
    
    /**	
     * 删除社区消息
     * @param jsonObj
     * @return
     */
    @RequestMapping("deleteMemberCommunity")
    @ResponseBody
    public JsonData deleteMemberCommunity(String jsonObj)
    {
    	JsonData jsonData = new JsonData();
    	MemberCommunity memberCommunity=JsonUtil.readValue(jsonObj, MemberCommunity.class);
    	int result=this.appService.deleteMemberCommunity(memberCommunity);
    	if(result>0)
    	{
    		jsonData.setSuccess(true);
    	}else
    	{
    		jsonData.setSuccess(false);
    	}
    	return jsonData;
    }
    /**
     * 查询社区成员发布的消息
     * @param jsonObj
     * @return
     */
    @RequestMapping("allMemberCommunity")
    @ResponseBody
    public JsonData allMemberCommunity(String jsonObj)
    {
    	JsonData jsonData = new JsonData();
    	SearchParam searchParam=JsonUtil.readValue(jsonObj, SearchParam.class);
    	searchParam.setImg_domain(config.getImgDomain());
    	List<MemberCommunity>list=this.appService.queryAllMemberCommunity(searchParam);
    	if(list.size()>0)
    	{
    		jsonData.setSuccess(true);
    		jsonData.setData(list);
    	}else
    	{
    		jsonData.setSuccess(false);
    		jsonData.setMsg("无数据");
    	}
    	
    	return jsonData;
    }
    
    
    /**
     * 微信支付异步回调
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping("wxpayCallBackUrl")
    public void wxpayCallBackUrl(HttpServletRequest request,HttpServletResponse response) throws IOException
    {
    	Map<String,String> resultMap=null;
    	
    }
    
    
    
    /************************2016年1月20日14:29:21 接口调整 begin******************************************/
    //1 收藏 商品 增加 店铺code
    @RequestMapping("saveMemberFavourte")
    @ResponseBody
    public JsonData saveMemberFavourte(String jsonObj)
    {
    	JsonData jsonData=new JsonData();
    	CollParam collParam=JsonUtil.readValue(jsonObj, CollParam.class);
    	int result=this.appService.saveMemberFavourte(collParam);
    	if(result>0)
    	{
    		jsonData.setSuccess(true);
    	}else
    	{
    		jsonData.setSuccess(false);
    	}
    	return jsonData;
    }
    
    //取消收藏 、单个 全部删除
    @RequestMapping("deleteMemberFavGoods")
    @ResponseBody
    public JsonData deleteMemberFavGoods(String jsonObj)
    {
    	JsonData jsonData=new JsonData();
    	CollParam info= JsonUtil.readValue(jsonObj, CollParam.class);
    	int result=this.appService.deleteMemberFavGoods(info);
    	if(result>0)
    	{
    		jsonData.setSuccess(true);
    	}else
    	{
    		jsonData.setSuccess(false);
    	}
    	
    	return jsonData;
    }
    /**
     * 删除收货地址
     * @param jsonObj
     * @return
     */
    @RequestMapping("deleteMemRecAddress")
    @ResponseBody
    public JsonData deleteMemRecAddress(String jsonObj)
    {
    	JsonData jsonData=new JsonData();
    	CollParam info=JsonUtil.readValue(jsonObj, CollParam.class);
    	int result=this.appService.deleteMemRecAddress(info);
    	if(result>0)
    	{
    		jsonData.setSuccess(true);
    	}else
    	{
    		jsonData.setSuccess(false);
    	}
    	
    	return jsonData;
    }
 
    
    
    
    
    /**
     * app用户注册 +推荐店铺code 
     * @param jsonObj
     * @return
     */
    @RequestMapping("appUserRegister")
    @ResponseBody
    public JsonData appUserRegister(String jsonObj,String accessCode)
    {
    	 CoolUser user = JsonUtil.readValue(jsonObj, CoolUser.class);
         String sms = user.getSms();
         JsonData jsonData = new JsonData();
         Map<String, Object> resultMap = new HashMap<String, Object>();
         if(StringUtils.isNotEmpty(sms)){
        	 if(sms.equals(jvmCacheService.get("sms_" + accessCode + "_" +sms))){
        		 //注册会员
        		 int result=this.appService.appUserRegister(user);
        		 if(result>0)
        		 {
        			 jsonData.setSuccess(true);
        		 }else
        		 {
        			 jsonData.setSuccess(false);
        		 }
        		 
        		 jvmCacheService.del("sms_" + accessCode + "_" +sms);
        	 }else
        	 {
        		 jsonData.setSuccess(false);
             	jsonData.setMsg("验证码错误");
        	 }
         }else{
         	jsonData.setSuccess(false);
         	jsonData.setMsg("验证码不能为空");
         }
          
    	
    	return jsonData;
    }
    /*****************关注接口******************************************************************/
    /**
     * 
     * @param jsonObj
     * @return
     */
    @RequestMapping("saveUserFavShop")
    @ResponseBody
    public JsonData saveUserFavShop(String jsonObj)
    {
    	JsonData jsonData=new JsonData();
    	SearchParam searchParam=JsonUtil.readValue(jsonObj, SearchParam.class);
    	int result=this.appService.saveUserFavShop(searchParam);
    	if(result>0)
    	{
    		jsonData.setSuccess(true);
    	}else
    	{
    		jsonData.setSuccess(false);
    	}
    	return jsonData;
    }
    /**
     * 用户是否关注指定店铺
     * @param jsonObj
     * @return
     */
    @RequestMapping("checkUserIsFavShop")
    @ResponseBody
    public JsonData checkUserIsFavShop(String jsonObj)
    {
    	JsonData jsonData=new JsonData();
    	SearchParam searchParam=JsonUtil.readValue(jsonObj, SearchParam.class);
    	int result=this.appService.checkUserIsFavShop(searchParam);
    	if(result>0)
    	{
    		jsonData.setSuccess(true);
    	}else
    	{
    		jsonData.setSuccess(false);
    	}
    	
    	return jsonData;
    }
    /**
     * 更新用户的默认收货地址
     * @param jsonObj
     * @return
     */
    @RequestMapping("updateMemberDefAddress")
    @ResponseBody
    public JsonData updateMemberDefAddress(String jsonObj)
    {
    	JsonData jsonData=new JsonData();
    	CollParam collParam=JsonUtil.readValue(jsonObj, CollParam.class);
    	int result=this.appService.updateMemberDefAddress(collParam);
    	if(result>0)
    	{
    		jsonData.setSuccess(true);
    	}else
    	{
    		jsonData.setSuccess(false);
    	}
    	return jsonData;
    }
    /**
     * 取消关注
     * @param jsonObj
     * @return
     */
    @RequestMapping("cancelUserFavShop")
    @ResponseBody
    public JsonData cancelUserFavShop(String jsonObj)
    {
    	JsonData jsonData=new JsonData();
    	SearchParam searchParam=JsonUtil.readValue(jsonObj, SearchParam.class);
    	int result=this.appService.cancelUserFavShop(searchParam);
    	if(result>0)
    	{
    		jsonData.setSuccess(true);
    	}else
    	{ 
    		jsonData.setSuccess(false);
    	}
    	
    	return jsonData;
    }
    /**
     * 我的关注
     * @param jsonObj
     * @return
     */
    @RequestMapping("myFavShop")
    @ResponseBody
    public JsonData myFavShop(String jsonObj)
    {
    	JsonData jsonData=new JsonData();
    	SearchParam searchParam =JsonUtil.readValue(jsonObj, SearchParam.class);
    	searchParam.setImg_domain(config.getImgDomain());
    	CollectInfo shopinfo=this.appService.queryMyFavShop(searchParam);
    	if(shopinfo!=null)
    	{
    		jsonData.setSuccess(true);
    		jsonData.setData(shopinfo);
    	}else
    	{
    		jsonData.setSuccess(false);
    		jsonData.setMsg("无数据");
    	}
    	
    	return jsonData;
    }
    /**
     * 
     * @param jsonObj
     * @return
     */
    @RequestMapping("updateMemberAddress")
    @ResponseBody
    public JsonData updateMemberAddress(String jsonObj)
    {
    	JsonData jsonData=new JsonData();
    	MemberAddress memberAddress=JsonUtil.readValue(jsonObj, MemberAddress.class);
    	int result=this.appService.updateMemberAddress(memberAddress);
    	if(result>0)
    	{
    		jsonData.setSuccess(true);
    	}else
    	{
    		jsonData.setSuccess(false);
    	}
    	return jsonData;
    }
    
    /**********************************原生接口部分***************************************************************/
    /**
     * 
     * @param jsonObj
     * @return
     */
    @RequestMapping("queryShopData")
    @ResponseBody
    public JsonData queryShopData(String jsonObj)
    {
    	JsonData vo=new JsonData();
    	SearchShop searchShop=JsonUtil.readValue(jsonObj, SearchShop.class);
    	searchShop.setImage_domain(config.getImgDomain());
    	SearchShop shopinfo=this.appService.queryShopData(searchShop);
    	if(shopinfo!=null)
    	{
    		vo.setSuccess(true);
    		vo.setData(shopinfo);
    	}
    	return vo;
    }
    /**
     * 店铺新品
     * @param jsonObj
     * @return
     */
    @RequestMapping("queryShopNewGoods")
    @ResponseBody
    public JsonData queryShopNewGoods(String jsonObj)
    {
    	JsonData vo=new JsonData();
    	SearchParam searchShop=JsonUtil.readValue(jsonObj, SearchParam.class);
    	//SearchParam searchShop=new SearchParam();
    	//searchShop.setUncode("33013249");searchShop.setStart(0);searchShop.setLimit(10);
    	searchShop.setImg_domain(config.getImgDomain());
    	List<Product>list=this.appService.queryShopNewGoods(searchShop);
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
     * 查询店铺分类
     * @param jsonObj
     * @return
     */
    @RequestMapping("queryShopCatelog")
    @ResponseBody
    public JsonData queryShopCatelog(String jsonObj)
    {
    	JsonData vo=new JsonData();
    	SearchParam searchShop=JsonUtil.readValue(jsonObj, SearchParam.class);
    	List<HelpType>list=this.appService.queryShopCatelog(searchShop);
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
     * 查询店铺分类商品
     * @param jsonObj
     * @return
     */
    @RequestMapping("queryShopGoodsByCatelogId")
    @ResponseBody
    public JsonData queryShopGoodsByCatelogId(String jsonObj)
    {
    	JsonData vo=new JsonData();
    	SearchParam searchParam=JsonUtil.readValue(jsonObj, SearchParam.class);
    	  
    	List<Product>list=this.appService.queryShopGoodsByCatelogId(searchParam);
    	if(list.size()>0)
    	{
    		vo.setSuccess(true);
    		vo.setData(list);
    	}
    	return vo;
    }
    
    
    /**
     * 
     * @param jsonObj
     * @return
     */
    @RequestMapping("queryProductEvalist")
    @ResponseBody
    public JsonData queryProductEvalist(String jsonObj)
    {
    	JsonData vo=new JsonData();
    	SearchParam searchParam=JsonUtil.readValue(jsonObj, SearchParam.class);
    	searchParam.setImg_domain(config.getImgDomain());
    	int totalnum=this.appService.queryGoodsRecTotalNum(searchParam);
    	if(totalnum>0)
    	{
    		vo.setSuccess(true);
    		SearchData sd=new SearchData();
    		sd.setTotal(totalnum);
    		sd.setRecords(this.appService.queryGoodsRecPage(searchParam));
    		vo.setData(sd);
    	}else
    	{
    		vo.setSuccess(false);
    		vo.setMsg("无数据");
    	}
    	return vo;
    }
    
    
    /**
     * 查询商品详情信息
     * @param jsonObj
     * @return
     */
    @RequestMapping("queryProductinfo")
    @ResponseBody
    public JsonData queryProductinfo(String jsonObj)
    {
    	JsonData vo=new JsonData();
    	Product productParam=JsonUtil.readValue(jsonObj, Product.class); 
    	Product info=this.appService.queryProductinfo(productParam);
    	if(info!=null)
    	{
    		if(info.getProductImglist().size()>0)
    		{
    			for(ProductImg img:info.getProductImglist())
    			{
    				if(img.getImg().length()>0)
    				{
    					img.setImg(config.getImgDomain()+img.getImg());
    				}
    			}
    		}
    		List<ProductImg>contimgs=new ArrayList<ProductImg>();
    		//解析content img
    		String content=info.getContent();
    		String [] con_img=content.split("<img");
    		for(int i=0;i<con_img.length;i++)
    		{
    			if(con_img[i].indexOf("src=")>0)
    			{
    				ProductImg o=new ProductImg();
    				String fir=con_img[i].substring(con_img[i].indexOf("\"")+1);
    				o.setImg(fir.substring(0, fir.indexOf("\"")));
    				contimgs.add(o);
    			}
    		}
    		info.setContentimgs(contimgs);
    		vo.setSuccess(true);
    		vo.setData(info);
    	}else
    	{
    		vo.setSuccess(false);
    		vo.setMsg("无数据");
    	}
    	return vo;
    }
    
    
    
    
    /*******************************图文上传 ***************************************************************/
    /**
     * 
     * @param jsonObj
     * @return
     */
    @RequestMapping("updateMemberInfo")
    @ResponseBody
    public JsonData updateMemberInfo(String jsonObj)
    {
    	JsonData jsonData = new JsonData();
    	CoolMember coolMember=JsonUtil.readValue(jsonObj, CoolMember.class);
    	int result=this.appService.updateMemberInfo(coolMember);
    	if(result>0)
    	{
    		jsonData.setSuccess(true);
    	}else
    	{
    		jsonData.setSuccess(false);
    		jsonData.setMsg("操作失败");
    	}
    	return jsonData;
    }
    /**
     * 更新头像
     * @param request
     * @return
     */
    @RequestMapping("uploadUserLogo")
    @ResponseBody
    public JsonData uploadUserLogo(HttpServletRequest request)
    {
    	JsonData jsonData = new JsonData();
    	String path = "";
    	try {
    		File file = new File(config.getImgLocal()+"/temp/");
    		FileUtil.createDirectory(file);
	        ServletFileUpload fu = FileUtil.initFileUpload(file);
	        List<FileItem> fileItems = fu.parseRequest(request);
    		CoolMember coolMember=new CoolMember();
    		Iterator iter = fileItems.iterator();
    		 
    		while (iter.hasNext()) {
	        	FileItem item = (FileItem)iter.next();
	        	if (item.isFormField()) {//判断该表单项是否是普通类型
	        		String key = item.getFieldName();
	        		if("userid".equals(key))
	        		{
	        			coolMember.setUserId(Integer.parseInt(item.getString("utf-8")));
	        		}
	        	}else
	        	{
	        		String name = item.getName();
	                String filename = item.getName();
	                name = name.substring(0, name.lastIndexOf("."));
	                if (("".equalsIgnoreCase(name)) || (item.getSize() <= 100L)){
	                	continue;
	                }else{
	                	 path = System.currentTimeMillis() + (int)(Math.random() * 1000.0D) + FileUtil.getImageSuffix(filename);
	                     File fNew = new File(config.getImgLocal()+"/temp/", path);
	                     item.write(fNew);
	                     
	                     long imgMaxsize = config.getImgMaxSize();
	             		String imgLocal = config.getImgLocal();	
	             		
	                     String filepath="/member/"+coolMember.getUserId()+"/";
	                     String result=FileUploadUtil.uploadImage(fNew, filepath, fNew.getName(), imgMaxsize, imgLocal);
	                     if("true".equals(result)){
	                    	 coolMember.setLogo(filepath+fNew.getName());
	                    	 jsonData.setData(config.getImgDomain()+filepath+fNew.getName());
	                    	 jsonData.setSuccess(true);
	                     }else
	                     {
	                    	 jsonData.setMsg(result);
	                    	 jsonData.setSuccess(false);
	                     }
	        	}
	        }
	        	
    		}
    		//更新用户信息
    		this.appService.saveUpdateMemberLogo(coolMember);
    		
    	}catch (FileUploadException e) {
			jsonData.setMsg("不支持的编码");
		} catch (IOException e) {
			jsonData.setMsg("文件上传异常");
	    } catch (Exception e) {
	    	jsonData.setMsg("文件上传失败");
        } 
    	
    	
    	return jsonData;
    }
    /**
     * 
     * @param jsonObj
     * @return
     */
    @RequestMapping("saveWapCommunityinfo")
    @ResponseBody
    public JsonData saveWapCommunityinfo(String jsonObj)
    {
    	JsonData jsonData = new JsonData();
    	MemberCommunity memberCommunity=JsonUtil.readValue(jsonObj, MemberCommunity.class);
    	int result=this.appService.saveWapCommunityinfo(memberCommunity);
    	if(result>0)
    	{
    		jsonData.setSuccess(true);
    		
    	}else
    	{
    		jsonData.setSuccess(false);
    		jsonData.setMsg("保存失败");
    	}
    	return jsonData;
    }
    
    /**
     * 
     * @param request
     * @return
     */
    @RequestMapping("saveUploadCommImg")
    @ResponseBody
    public JsonData saveUploadCommImg(HttpServletRequest request)
    {
    	JsonData jsonData = new JsonData();
    	jsonData.setSuccess(false);
    	String path = "";
    	try {
    		File file = new File(config.getImgLocal()+"/temp/");
    		FileUtil.createDirectory(file);
	        ServletFileUpload fu = FileUtil.initFileUpload(file);
	        List<FileItem> fileItems = fu.parseRequest(request);
	        Iterator iter = fileItems.iterator();
	        while (iter.hasNext()) {
	        	FileItem item = (FileItem)iter.next();
	        	
	        	if (item.isFormField()) {//判断该表单项是否是普通类型
	        		
	        	}else
	        	{
	        		String name = item.getName();
	                String filename = item.getName();
	                name = name.substring(0, name.lastIndexOf("."));
	                if (("".equalsIgnoreCase(name)) || (item.getSize() <= 100L)){
	                	continue;
	                }else{
	                	 path = System.currentTimeMillis() + (int)(Math.random() * 1000.0D) + FileUtil.getImageSuffix(filename);
	                     File fNew = new File(config.getImgLocal()+"/temp/", path);
	                     item.write(fNew);
	                     
	                     long imgMaxsize = config.getImgMaxSize();
	             		 String imgLocal = config.getImgLocal();	
	             		
	                     String filepath="/community/";
	                     String result=FileUploadUtil.uploadImage(fNew, filepath, fNew.getName(), imgMaxsize, imgLocal);
	                     if("true".equals(result)){
	                    	 jsonData.setSuccess(true);
	                    	 jsonData.setData(filepath+fNew.getName());
	                     }else
	                     {
	                    	 jsonData.setMsg(result);
	                    	 jsonData.setSuccess(false);
	                     }
	                     //删除临时文件
	                     if(fNew.exists()){
	                     	fNew.delete();	   
	                     }
	                }
	        	}
	        }
	        
    	}catch (FileUploadException e) {
			jsonData.setMsg("不支持的编码");
		} catch (IOException e) {
			jsonData.setMsg("文件上传异常");
	    } catch (Exception e) {
	    	jsonData.setMsg("文件上传失败");
        } 
    	return jsonData;
    }
    /**
     * 保存社区新增图文
     * @param jsonObj
     * @return
     */
    @RequestMapping("saveNewCommunity")
    @ResponseBody
    public JsonData saveNewCommunity(HttpServletRequest request)
    {
    	JsonData jsonData = new JsonData();
    	String path = "";
    	String method="";
    	try {
    		File file = new File(config.getImgLocal()+"/temp/");
    		FileUtil.createDirectory(file);
	        ServletFileUpload fu = FileUtil.initFileUpload(file);
	        List<FileItem> fileItems = fu.parseRequest(request);
	        MemberCommunity memberCommunity=new MemberCommunity();
	        Iterator iter = fileItems.iterator();
	        while (iter.hasNext()) {
	        	FileItem item = (FileItem)iter.next();
	        	if (item.isFormField()) {//判断该表单项是否是普通类型
	        		String key = item.getFieldName();
	        		if("userid".equals(key))
	        		{
	        			memberCommunity.setUserid(Integer.parseInt(item.getString("utf-8")));
	        		}
	        		if("content".equals(key))
	        		{
	        			memberCommunity.setContent(item.getString("utf-8"));
	        		}
	        		if("type".equals(key))
	        		{
	        			memberCommunity.setType(Integer.parseInt(item.getString("utf-8")));
	        		}
	        		if("method".equals(key))
	        		{
	        			method=item.getString("utf-8");
	        		}
	        		if("id".equals(key))
	        		{
	        			memberCommunity.setId(Integer.parseInt(item.getString("utf-8")));
	        		}
	        	}else
	        	{
	        		String name = item.getName();
	                String filename = item.getName();
	                name = name.substring(0, name.lastIndexOf("."));
	                if (("".equalsIgnoreCase(name)) || (item.getSize() <= 100L)){
	                	continue;
	                }else{
	                	 path = System.currentTimeMillis() + (int)(Math.random() * 1000.0D) + FileUtil.getImageSuffix(filename);
	                     File fNew = new File(config.getImgLocal()+"/temp/", path);
	                     item.write(fNew);
	                     
	                     long imgMaxsize = config.getImgMaxSize();
	             		 String imgLocal = config.getImgLocal();	
	             		
	                     String filepath="/community/";
	                     String result=FileUploadUtil.uploadImage(fNew, filepath, fNew.getName(), imgMaxsize, imgLocal);
	                     if("true".equals(result)){
	                    	 memberCommunity.setShowimg(filepath+fNew.getName());
	                    	 jsonData.setSuccess(true);
	                     }else
	                     {
	                    	 jsonData.setMsg(result);
	                    	 jsonData.setSuccess(false);
	                     }
	                     //删除临时文件
	                     if(fNew.exists()){
	                     	fNew.delete();	   
	                     }
	                }
	        	}
	        }
	        if("add".equals(method))
        	{
        		this.appService.saveNewCommunity(memberCommunity);
        	}else
        	{
        		this.appService.updateUserCommunity(memberCommunity);
        	}
	       	  
    	}catch (FileUploadException e) {
			jsonData.setMsg("不支持的编码");
		} catch (IOException e) {
			jsonData.setMsg("文件上传异常");
	    } catch (Exception e) {
	    	jsonData.setMsg("文件上传失败");
        } 
    	return jsonData;
    }
}
