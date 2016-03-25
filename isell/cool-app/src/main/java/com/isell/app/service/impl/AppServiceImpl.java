package com.isell.app.service.impl;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.isell.app.dao.AppUserMapper;
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
import com.isell.app.dao.entity.OrderCount;
import com.isell.app.dao.entity.OrderDetail;
import com.isell.app.dao.entity.OrderParam;
import com.isell.app.dao.entity.OrderRecv;
import com.isell.app.dao.entity.Product;
import com.isell.app.dao.entity.ProductRec;
import com.isell.app.dao.entity.SearchParam;
import com.isell.app.dao.entity.SearchProduct;
import com.isell.app.dao.entity.SearchShop;
import com.isell.app.dao.entity.SearchType;
import com.isell.app.dao.entity.Theme;
import com.isell.app.service.AppService;
import com.isell.core.config.BisConfig;
import com.isell.core.util.CommonUtils;
import com.isell.core.util.DateUtil;
import com.isell.core.util.Record;
import com.isell.service.member.dao.CoolMemberMapper;
import com.isell.service.member.dao.CoolMemberReceiverMapper;
import com.isell.service.member.dao.CoolUserMapper;
import com.isell.service.member.vo.CoolMemberFavorites;
import com.isell.service.member.vo.CoolMemberReceiver;
import com.isell.service.member.vo.CoolUser;
import com.isell.service.member.vo.UserInfo;
import com.isell.service.order.dao.CoolOrderItemMapper;
import com.isell.service.order.dao.CoolOrderMapper;
import com.isell.service.order.vo.CoolOrder;
import com.isell.service.order.vo.CoolOrderItem;
import com.isell.service.product.dao.CoolProductGgMapper;
import com.isell.service.product.dao.CoolProductMapper;
import com.isell.service.product.vo.CoolProduct;
import com.isell.service.product.vo.CoolProductGg;



@Service("appService")
public class AppServiceImpl implements AppService{

	@Resource
	private AppUserMapper appUserMapper;
	 /**
     * 会员收货信息mapper
     */
    @Resource
    private CoolMemberReceiverMapper coolMemberReceiverMapper;
    @Resource
    private CoolProductGgMapper coolProductGgMapper;
    @Resource
    private CoolOrderItemMapper coolOrderItemMapper;
    @Resource
    private JdbcTemplate jdbcTemplate;
    @Resource
    private CoolOrderMapper coolOrderMapper;
    
    @Resource 
    private CoolUserMapper coolUserMapper;
    
    @Resource
    private CoolMemberMapper coolMemberMapper;
    
	 /**
     * 配置信息
     */
    @Resource
    private BisConfig config;
    
    @Resource
    private CoolProductMapper coolProductMapper;
    /**
     * 首页主题
     */
    public Record queryThemelist(CoolMember coolMember)
    {
    	Record record = new Record(); 
		boolean success = false;
		int checkIsRec=0;
		String s_id=""; 
		if(coolMember.getId()>0)
		{
			SearchParam searchParam=new SearchParam();
			searchParam.setmId(coolMember.getId());
			s_id=this.appUserMapper.checkUserIsRecShop(searchParam);
			if(s_id!=null&&!"".equals(s_id))
			{
				checkIsRec=1;
			}
		}
		 
		List<Theme>records=this.appUserMapper.queryThemelist();
    	for(Theme info:records)
    	{
    		List<Product>goodslist=new ArrayList<Product>();
    		String goodsid=info.getGoodsid();
    		//String shopid=info.getShopid();
    		String imgs=info.getImgs();
    		String []goodids=goodsid.split(",");
    		//String [] shopids=shopid.split(",");
    		String []img_paths=imgs.split(",");
    		for(int i=0;i<goodids.length;i++)
    		{
    			if(StringUtils.isNotBlank(goodids[i]))
    			{
    				Product pro=new Product();
    				Map<String, String> map=new HashMap<String, String>();
    				map.put("goodsid", goodids[i]);
    				map.put("checkIsRec", checkIsRec + "");
    				map.put("s_id", s_id);
    				pro=this.appUserMapper.queryProductinfoById(map);
    				if(pro!=null)
    				{
    					//pro.setImg(config.getImgDomain()+pro.getImg());
    					pro.setImg(config.getImgDomain()+img_paths[i]);
    					if(pro.getIsrec()==3)
    					{
    						//pro.setShopid(Integer.parseInt(shopids[i]));
    						String sale_id=this.appUserMapper.checkProIsSuccessSale(map);
    						if(sale_id!=null &&!"".equals(sale_id))
    						{
    							 pro.setShopcode(Integer.parseInt(appUserMapper.queryShopCodeBySid(sale_id)));
    							 pro.setShopid(sale_id);
    							 pro.setMobile(this.appUserMapper.queryMobileByShopid(sale_id));
    						}else
    						{
    							//随机取一家店铺的 id ，code 
    							 String id=this.appUserMapper.queryShopIdByRandom(map);
    							 if(id!=null&&!"".equals(id))
    							 {
    								 pro.setShopcode(Integer.parseInt(appUserMapper.queryShopCodeBySid(id)));
        							 pro.setShopid(id);
        							 pro.setMobile(this.appUserMapper.queryMobileByShopid(id));
    							 }
    							
    						}
    					}
    					pro.setIshas(this.appUserMapper.checkShopIsHasGoods(pro));
    					goodslist.add(pro);
    				}
    			}
    				
    		}
    		info.setProductlist(goodslist);
    	}
    	
    	
    	if(CollectionUtils.isNotEmpty(records)){
    		record.set("themelist", records);
			success = true;
    	}else{
			record.set("msg", "无数据");
		}
		record.set("success", success);
		
		return record;
    	
    }
    
	/**
	 * 首页轮播图
	 */
	public Record queryHomePageImages()
	{
		Record record = new Record(); 
		boolean success = false;	
		List<HomePageImage>records=this.appUserMapper.queryHomePageImages(config.getImgDomain());
		
		if(CollectionUtils.isNotEmpty(records)){
			record.set("hpImagelist", records);
			success = true;
		}else{
			record.set("msg", "无数据");
		}
		record.set("success", success);
		
		return record;
	}
	/**
	 * 查询公告列表
	 */
	public Record queryHomePageNotices()
	{
		Record record = new Record(); 
		boolean success = false;	
		List<Notice>records=this.appUserMapper.queryHomePageNotices();
		if(CollectionUtils.isNotEmpty(records)){
			record.set("noticelist", records);
			success = true;
		}else{
			record.set("msg", "无数据");
		}
		record.set("success", success);
		
		return record;
		
	}
	/**
	 * 公告详情
	 */
	public Record queryHpnoticeinfo(Notice notice)
	{
		Record record = new Record(); 
		boolean success = false;	
		Notice noticeinfo=this.appUserMapper.queryHpnoticeinfo(notice);
		if(noticeinfo!=null)
		{
			success = true;
			record.set("noticeinfo", noticeinfo);
		}else
		{
			record.set("msg", "无数据");
		}
		record.set("success", success);
		
		return record;
	}
	/**
	 * 根据用户id查询用户详细信息
	 */
	public Record queryUserDetailById(CoolUser user)
	{
		Record record = new Record(); 
		boolean success = false;	
		CoolMember userinfo=this.appUserMapper.queryUserDetailById(user);
		
		if(userinfo!=null)
		{
			success = true;
			if(!"".equals(userinfo.getLogo()))
			{
				userinfo.setLogo(config.getImgDomain()+userinfo.getLogo());
			}
			record.set("userinfo", userinfo);
		}else
		{
			record.set("msg", "无数据");
		}
		record.set("success", success);
		return record;
	}
	/**
	 *  验证商品库存是否足量
	 */
	public int checkGoodsStockIsCanPay(OrderParam orderParam)
	{
		return this.appUserMapper.checkGoodsStockIsCanPay(orderParam);
	}
	
	/**
	 * 保存用户收藏
	 */
	public Record saveMemberRecGoods(CoolMemberFavorites coolMemberFavorites)
	{
		Record record = new Record(); 
		boolean success = false;
		
		int result=0;
		result=this.appUserMapper.checkUserIsRecGoods(coolMemberFavorites);
		if(result>0)
		{
			success=true;
		}else
		{
			success=this.appUserMapper.saveUserRecGoods(coolMemberFavorites)>0?true:false;
		}
		record.set("success", success);
		return record;
	}
	/**
	 * 
	 */
	public List<Hotword>queryHotwordlist()
	{
		return this.appUserMapper.queryHotwordlist();
	}
	/**
	 * 商品搜索总的条数 
	 */
	public int querySearchGoodsAllNum(SearchParam searchParam)
	{
		return this.appUserMapper.querySearchGoodsAllNum(searchParam);
	}
	public int querySearchUnorderNum(SearchParam searchParam)
	{
		return this.appUserMapper.querySearchUnorderNum(searchParam);
	}
	/**
	 * 分类搜索
	 */
	public List<SearchType>typeSearch(SearchParam searchParam)
	{
		List<SearchType>list=this.appUserMapper.typeSearch(searchParam);
		for(SearchType info:list)
		{
			searchParam.setTypeid(info.getId());
			info.setSearchTypelist(this.appUserMapper.querySearchTpyeList(searchParam));
		}
		return list;
	}
	/**
	 * 搜索宝贝 -关键字搜索
	 */
	public List<SearchProduct>querySearchGoods(SearchParam searchParam)
	{
		List<SearchProduct>list=this.appUserMapper.querySearchGoods(searchParam);
			 //查询推荐店铺
		 if(list.size()>0)
		 {
			 for(SearchProduct info:list)
				{
					searchParam.setGoodid(info.getGoodid());
					searchParam.setUncode(info.getCode());
					info.setShoplist(this.appUserMapper.querySearchShop(searchParam));
				}
		 }
		return list;
	}
	/**
	 * 删除社区消息
	 */
	public  int deleteMemberCommunity(MemberCommunity memberCommunity)
	{
		return this.appUserMapper.deleteMemberCommunity(memberCommunity);
	}
	/**
	 * 
	 * @param searchParam
	 * @return
	 */
	public List<HelpType>queryHelptypeList(SearchParam searchParam)
	{
		List<HelpType>list=this.appUserMapper.queryHelptypeList(searchParam);
		for(HelpType info:list)
		{
			info.setHelplist(this.appUserMapper.queryHelplist(info));
		}
		return list;
	}
	/**
	 * 登录验证
	 */
	public UserInfo queryLoginCoolUser(LoginParam longinParam)
	{
		return this.appUserMapper.queryLoginCoolUser(longinParam);
	}
	
	/**
	 * 查询社区 发布的消息
	 */
	public List<MemberCommunity>queryAllMemberCommunity(SearchParam searchParam)
	{
		return this.appUserMapper.queryAllMemberCommunity(searchParam);
	}
	
	/**
	 * 
	 */
	public List<SearchProduct>querySearchGoodsUnOrder(SearchParam searchParam)
	{
		List<SearchProduct>list=this.appUserMapper.querySearchGoodsUnOrder(searchParam);
		if(list.size()>0)
		{
			for(SearchProduct info:list)
			{
				searchParam.setGoodid(info.getGoodid());
				searchParam.setUncode(info.getCode());
				System.out.println("code"+info.getCode());
				info.setShoplist(this.appUserMapper.querySearchShopUnOrder(searchParam));
			}
		}
		return list;
	}
	/**
	 * 
	 */
	public List<CollectInfo>queryUserCollect(SearchParam searchParam)
	{
		List<CollectInfo>list=this.appUserMapper.queryUserCollect(searchParam);
		return list;
	}
	/**
	 * 取消订单
	 */
	public int cancleOrderByOrderno(OrderParam orderParam)
	{
		int result=0;
		int checkresult=this.appUserMapper.queryOrderStateByOrderno(orderParam);
		if(checkresult>0)
		{
			result=this.appUserMapper.cancleOrderByOrderno(orderParam);
		}
		return result;
	}
	/**
	 * 查询用户收藏总的数量
	 */
	public int queryUserCollectTotalNum(SearchParam searchParam)
	{
		return this.appUserMapper.queryUserCollectTotalNum(searchParam);
	}
	/**
	 * 查询店铺详情
	 */
	public SearchShop queryShopInfo(SearchShop searchShop)
	{
		SearchParam param=new SearchParam();
		param.setShopcode(searchShop.getShopcode());
		searchShop.setShopid(this.appUserMapper.queryShopIdByShopCode(param));
		return this.appUserMapper.queryShopInfo(searchShop);
	}
	/**
	 * 查询体验店 列表
	 */
	public List<SearchShop>queryShpolist(SearchParam searchParam)
	{
		return this.appUserMapper.queryShpolist(searchParam);
	}
	
	/**
	 * 查询订单详情
	 */
	public OrderDetail queryOrderDetail(OrderParam orderParam)
	{
		OrderDetail orderDetail=this.appUserMapper.queryOrderDetail(orderParam);
		orderDetail.setOrderGoods(this.appUserMapper.queryOrderGoods(orderParam));
		//CenterOrder info=this.appUserMapper.queryOrderDetail(orderParam);
		/*if(orderDetail!=null)
		{
			//查询物流信息
			 Map<String, String> paramMap = new HashMap<String, String>();
			 paramMap.put("com", "");
	         paramMap.put("nu", orderDetail.getPscode());
	         
	         //String result = HttpUtils.httpPost("http://m.i-coolshop.com/logistics/kuaidi/webService", paramMap);
	        // System.out.println(result);
		}*/
		return orderDetail;
	}
	
	/**
	 * 验证订单号是否已经成功
	 */
	public int checkOrderIsPaySuccess(String orderseq)
	{
		return this.appUserMapper.checkOrderIsPaySuccess(orderseq);
	}
	
	public int queryMyOrderTotalNum(CenterOrderParam centerOrderParam)
	{
		return this.appUserMapper.queryMyOrderTotalNum(centerOrderParam);
	}
	/**
	 *  查询我的中心订单
	 */
	public List<CenterOrder>queryMyOrderPage(CenterOrderParam centerOrderParam)
	{
		
		List<CenterOrder>list=this.appUserMapper.queryMyOrderPage(centerOrderParam);
		 
		for(CenterOrder info:list)
		{
			info.setImgdomain(centerOrderParam.getImgdomain());
			info.setOrderItemlist(this.appUserMapper.queryOrderItem(info));
		}
		return list;
	}
	/**
	 * 保存客户端下单数据
	 */
	@Transactional
	public int savePreOrder(OrderParam orderParam)
	{
		int result=0;
		Date date = new Date();
		BigDecimal total = new BigDecimal(0);
		BigDecimal taxfee=new BigDecimal(0);
		
		CoolOrder order = new CoolOrder();
		order.setCreatetime(new Timestamp(date.getTime()));
		order.setmId(orderParam.getMemberid());
		order.setIsBatch(new Byte("0"));
		
		 // 收货地址
        Integer recId = orderParam.getReceiveid();
        if (recId != null) {
            CoolMemberReceiver recRec = coolMemberReceiverMapper.getCoolMemberReceiverById(recId);
            order.setLocationP(recRec.getLocationP());
            order.setLocationC(recRec.getLocationC());
            order.setLocationA(recRec.getLocationA());
            order.setAddress(recRec.getAddress());
            order.setLinkman(recRec.getName());
            order.setMobile(recRec.getMobile());
            order.setTel(recRec.getTel());
            order.setZipcode(recRec.getZipcode());
        } else {
            order.setLocationP(orderParam.getProvince());
            order.setLocationC(orderParam.getCity());
            order.setLocationA(orderParam.getRegion());
            order.setAddress(orderParam.getAddress());
            order.setLinkman(orderParam.getLinkname());
            order.setMobile(orderParam.getPhone());
            order.setTel("");
            order.setZipcode(orderParam.getPostcode());
            order.setIdcard(orderParam.getIdcard());
            order.setOrderType(new Byte("0"));
        }
        order.setShareUser("");
        order.setShareAdded(false);
        //配送方式 
        order.setPsfs("");
     // 支付方式
        order.setZffs(orderParam.getPaytype());
        String orderNo =
                "CO" + DateUtil.dateToStr(DateUtil.yyyyMMddHHmmss, new Date()) + CommonUtils.randomFour();
       order.setOrderNo(orderNo);
       
       //商品信息  商品规格信息
       
       CoolProduct product;
       CoolProductGg productGg;
       int checkRest=this.appUserMapper.checkGoodsStockIsCanPay(orderParam);
       
       if(checkRest>0)
       {
    	   int pid=orderParam.getGoodid();
           product = coolProductMapper.getCoolProductById(pid);
           if (product == null) {
               //record.set("msg", "参数错误，根据商品主键" + pId + "无法获取商品信息");
               //break;
               result=2;
               return result;
           }     
           productGg = coolProductGgMapper.getCoolProductGgById(orderParam.getGgid());
           if (productGg == null) {
        	   result=3;
        	   return result;
           }
           BigDecimal divide = product.getDivide();
           int count = orderParam.getQuality();
           CoolOrderItem item=new CoolOrderItem();
           item.setgId(pid);
           
           item.setName(product.getName());
           item.setLogo(product.getLogo());
           item.setGg(productGg.getGg());
           item.setCount(count);
           item.setOrderNo(orderNo);
           item.setbId(product.getbId());
           item.setGid(productGg.getId());
           
           if (productGg.getCxjg().compareTo(new BigDecimal(0)) == 0) {
               total = total.add(productGg.getJg().multiply(new BigDecimal(count)));
               item.setPrice(productGg.getJg());
           } else {
               total = total.add(productGg.getCxjg().multiply(new BigDecimal(count)));
               item.setPrice(productGg.getCxjg());
           }
           
           if(total.compareTo(new BigDecimal(orderParam.getOrder_limit()))>0)
           {
        	   if(total.multiply(product.getTax()).compareTo(new BigDecimal("50"))>0)
        	   {
        		   taxfee=taxfee.add(total.multiply(product.getTax()));
        		   total.add(taxfee);
        	   }
           }
           // 添加佣金
           BigDecimal profit =
               item.getPrice().multiply(new BigDecimal(item.getCount())).multiply(divide);
           item.setProfit(profit);
           
           coolOrderItemMapper.saveCoolOrderItem(item);
           
           // 处理商品库存和销量
           jdbcTemplate.update("update cool_product_gg set stock=stock-" + count + ",sales=sales+"
               + count + " where id=?", item.getGid());
           jdbcTemplate.update("update cool_product set sales=sales+" + count + " where id=?",
               item.getgId());
           
           order.setbId(product.getbId());
           order.setPsPrice(null);
           order.setTotal(total);
           order.setSupplier(this.appUserMapper.queryShopidByNO(orderParam.getShopCode()));
           order.setoType(new Byte("0"));
           order.setmId(orderParam.getMemberid());
           order.setComments(orderParam.getRemark());
           order.setTaxPrice(taxfee);
           result = coolOrderMapper.saveCoolOrder(order);
           if(result==1)
           {
        	   orderParam.setTotal(total);
               orderParam.setGoodname(product.getName());
               orderParam.setOrderseq(orderNo);
               orderParam.setComment(product.getRemark());
           }
           return result;
       }else
       {
    	   return 5;
       }
	}
	/**
	 *  保存用户收藏商品
	 */
	public int saveMemberFavourte(CollParam collParam)
	{
		int result=0;
		result=this.appUserMapper.checkMemberIsFaved(collParam);
		if(result>0)
		{
			return 1;
		}else
		{
			SearchParam param=new SearchParam();
			param.setShopcode(collParam.getShopcode());
			collParam.setIds(this.appUserMapper.queryShopIdByShopCode(param));
			return this.appUserMapper.saveMemberFavourte(collParam);
		}
	}
	/**
	 * 保存用户评论订单
	 */
	public int saveUserRecOrder(OrderRecv rec)
	{
		int result=0;
		result=this.appUserMapper.checkUserIsSaveedOrderRec(rec);
		if(result>0)
		{
			result=2;
		}else
		{
			result=this.appUserMapper.saveUserRecOrder(rec);
			this.appUserMapper.updateOrderEndByOrderno(rec);
			
		}
		return result;
	}
	/**
	 * 买家删除订单  已取消、已完成和已退款
	 */
	public int mDdelOrderByOrderNo(OrderParam orderParam)
	{
		int result=0;
		result=this.appUserMapper.checkOrderIsCanMDel(orderParam);
		if(result>0)
		{
			result=this.appUserMapper.mDdelOrderByOrderNo(orderParam);
		}
		return result;
	}
	/**
	 * 
	 */
	public List<SearchProduct>querySearchFavShopGoods(SearchParam searchParam)
	{
		List<SearchProduct>list=this.appUserMapper.querySearchFavShopGoods(searchParam.getRowBounds(),searchParam);
		return list;
	}
	/**
	 * 
	 */
	public String queryShopIdByShopCode(SearchParam searchParam)
	{
		return this.appUserMapper.queryShopIdByShopCode(searchParam);
	}
	/**
	 * 查询搜索 已关注 记录数量
	 */
	public int querySearchFavShopGoodsNum(SearchParam searchParam)
	{
		return this.appUserMapper.querySearchFavShopGoodsNum(searchParam);
	}
	/**
	 * 删除用户收藏信息
	 */
	public int deleteMemberFavGoods(CollParam collParam)
	{
		int result=0;
		if(collParam.getType()==1)
		{
			return this.appUserMapper.deleteMemberFavGoods(collParam);
		}else
		
			if(collParam.getType()==2)
			{
				String [] recids_arr= collParam.getRecids();
				for(int i=0;i<recids_arr.length;i++)
				{
					collParam.setCid(Integer.parseInt(recids_arr[i]));
					result=this.appUserMapper.deleteMemberFavGoods(collParam);
				}
			}else if(collParam.getType()==3)
			{
				String ids=collParam.getIds();
			
				String [] recids_arr=ids.split(",");
				for(int i=0;i<recids_arr.length;i++)
				{
					collParam.setCid(Integer.parseInt(recids_arr[i]));
					result=this.appUserMapper.deleteMemberFavGoods(collParam);
				}
			}
		return result;
	}
	/**
	 * 删除收货地址
	 */
	public int deleteMemRecAddress(CollParam collParam)
	{
		return this.appUserMapper.deleteMemRecAddress(collParam);
	}
	/**
	 * 验证用户有没有关注店铺
	 */
	public String checkUserIsRecShop(SearchParam searchParam)
	{
		return this.appUserMapper.checkUserIsRecShop(searchParam);
	}
	/**
	 * app用户注册 +推荐店铺code
	 */
	public int appUserRegister(CoolUser coolUser)
	{
		int result=0;
		String username = coolUser.getUsername();
		String password = coolUser.getPassword();
		if(StringUtils.isEmpty(username) ){
			throw new RuntimeException("exception.access.param-invalidate");
		}else{
			int number = this.coolUserMapper.getRegisterNumberByUserName(username);
			if(number > 0){
				result=2;//手机号已注册
			}else{
				 
				CoolUser cUser = new CoolUser();
				cUser.setUsername(username);
				cUser.setPassword(password);
				cUser.setState(CoolUser.USER_STATE_1);
				cUser.setPetname(username);
				result = coolUserMapper.saveCoolUser(cUser);
				if(result>0)
				{
					CoolMember cMember = new CoolMember();
					String maxMember = coolMemberMapper.getMaxCoolMemberNo();
					 String no = null;
	                    if (maxMember == null) {
	                        no = "10000000";
	                    } else {
	                        no = (Integer.parseInt(maxMember) + 1) + "";
	                    }
	                    CoolUser u = coolUserMapper.getCoolUserByUserName(username);
	                    cMember.setUserId(u.getId());
	                    cMember.setMobile(username);
	                    cMember.setNo(no);
	                    cMember.setPetname(username);
	                    result = this.appUserMapper.saveCoolMember(cMember);
	                    if(result > 0){
	                    	//
	                    	if(this.appUserMapper.checkShopCodeisExists(coolUser)>0)
	                    	{
	                    		//保存关注
	                    		coolUser.setSms(CommonUtils.uuid());
	                    		result=this.appUserMapper.saveNewMemberFavShop(coolUser);
	                    	}
	                    }else
	                    {
	                    	result=3;//用户注册失败
	                    }
				}else
				{
					result=3;//用户注册失败
				}
			}
		}
		return result;
	}
	/**
	 * 保存用户关注店铺信息
	 */
	@Transactional
	public int saveUserFavShop(SearchParam searchParam)
	{
		int result=0;
		this.appUserMapper.deleteUserOldFavShop(searchParam);
		CollParam collParam=new CollParam();
		collParam.setmId(searchParam.getmId());
		collParam.setType(1);
		searchParam.setSid(this.appUserMapper.queryShopIdByShopCode(searchParam));
		this.appUserMapper.updateUserOldFavGoodsShopCode(searchParam);
		//this.appUserMapper.deleteMemberFavGoods(collParam);
		searchParam.setUncode(CommonUtils.uuid());
		result=this.appUserMapper.saveUserFavShop(searchParam);
		return result;
	}
	/**
	 * 用户是否关注指定店铺
	 */
	public int checkUserIsFavShop(SearchParam searchParam)
	{
		searchParam.setSid(this.appUserMapper.queryShopIdByShopCode(searchParam));
		return this.appUserMapper.checkUserIsFavShop(searchParam);
	}
	
	public int updateMemberDefAddress(CollParam collParam)
	{
		this.appUserMapper.clearUserAddress(collParam);
		return this.appUserMapper.updateMemberDefAddress(collParam);
	}
	/**
	 * 取消关注
	 */
	public int cancelUserFavShop(SearchParam searchParam)
	{
		searchParam.setSid(this.appUserMapper.queryShopIdByShopCode(searchParam));
		this.appUserMapper.cancelUserFavShop(searchParam);
		//this.appUserMapper.deleteUserFavGoods(searchParam);
		return 1;
	}
	/**
	 * 查询我的关注店铺
	 */
	public CollectInfo queryMyFavShop(SearchParam searchParam)
	{
		return this.appUserMapper.queryMyFavShop(searchParam);
	}
	/**
	 * 
	 */
	public int updateMemberAddress(MemberAddress memberAddress)
	{
		int result=0;
		if(memberAddress.getDef()>0)
		{
			this.appUserMapper.clearUserDefAddress(memberAddress);
		}
		
		if(memberAddress.getId()>0)
		{
			result=this.appUserMapper.updateUserAddress(memberAddress);
		}else
		{
			result=this.appUserMapper.saveNewUserAddress(memberAddress);
		}
		
		return result;
	}
	/**
	 * 保存用户社区留言
	 */
	public int saveNewCommunity(MemberCommunity memberCommunity)
	{
		return this.appUserMapper.saveNewCommunity(memberCommunity);
	}
	/**
	 * 查询商品详情
	 */
	public Product queryProductinfo(Product product)
	{
		Product info= this.appUserMapper.queryProductinfo(product);
		info.setProductGglist(this.appUserMapper.queryProductGgList(product));
		return  info;
	}
	/**
	 * 查询店铺主页
	 */
	public SearchShop queryShopData(SearchShop searchShop)
	{
		SearchShop info=this.appUserMapper.queryShopData(searchShop);
		if(info!=null)
		{
			info.setNoticelist(this.appUserMapper.queryShopNoticelist(info));
			info.setShopimglist(this.appUserMapper.queryShopImagelist(info));
		}
		return info;
	}
	/**
	 * 
	 */
	public int queryGoodsRecTotalNum(SearchParam searchParam)
	{
		return this.appUserMapper.queryGoodsRecTotalNum(searchParam);
	}
	public List<ProductRec>queryGoodsRecPage(SearchParam searchParam)
	{
		return this.appUserMapper.queryGoodsRecPage(searchParam);
	}
	/**
	 * 修改用户留言
	 */
	public int updateUserCommunity(MemberCommunity memberCommunity)
	{
		return this.appUserMapper.updateUserCommunity(memberCommunity);
	}
	/**
	 * 更新头像
	 */
	public int saveUpdateMemberLogo(CoolMember coolMember)
	{
		this.appUserMapper.updateUserLogo(coolMember);
		return this.appUserMapper.saveUpdateMemberLogo(coolMember);
	}
	
	public int saveWapCommunityinfo(MemberCommunity memberCommunity)
	{
		int result=0;
		if("add".equals(memberCommunity.getMname()))
		{
			result=this.appUserMapper.saveNewWapCommunityinfo(memberCommunity);
		}else
		{
			result=this.appUserMapper.saveUpdateWapCommunityinfo(memberCommunity);
		}
		return result;
	}
	/**
	 * 更新用户信息
	 */
	public int updateMemberInfo(CoolMember coolMember)
	{
		this.appUserMapper.updateUserInfo(coolMember);
		return this.appUserMapper.updateMemberInfo(coolMember);
	}
	public List<Product>queryShopNewGoods(SearchParam searchParam)
	{
		return this.appUserMapper.queryShopNewGoods(searchParam);
	}
	/**
	 * 查询店铺分类
	 */
	public List<HelpType>queryShopCatelog(SearchParam searchParam)
	{
		return this.appUserMapper.queryShopCatelog(searchParam);
	}
	
	public List<Product>queryShopGoodsByCatelogId(SearchParam searchParam)
	{
		return this.appUserMapper.queryShopGoodsByCatelogId(searchParam);
	}
	public List<Product>queryBmsProductList(SearchParam searchParam)
	{
		return this.appUserMapper.queryBmsProductList(searchParam);
	}
	public int queryBmsProductAllNum(SearchParam searchParam)
	{
		return this.appUserMapper.queryBmsProductAllNum(searchParam);
	}
	public int queryBMsMyOrderAllNum(OrderParam orderParam)
	{
		return this.appUserMapper.queryBMsMyOrderAllNum(orderParam);
	}
	/**
	 * 查询订单详情
	 */
	public OrderDetail queryOrderDetailForBms(SearchParam searchParam)
	{
		OrderDetail info= this.appUserMapper.queryOrderDetailForBms(searchParam);
		if(info!=null)
		{
			info.setOrderGoods(this.appUserMapper.queryOrderGoodsListForBms(searchParam));
		}
		return info;
	}
	
	public List<OrderDetail>queryBmsMyOrder(OrderParam orderParam)
	{
		List<OrderDetail>list= this.appUserMapper.queryBmsMyOrder(orderParam);
		for(OrderDetail orderDetail:list)
		{
			OrderParam os=new OrderParam();
			os.setOrderseq(orderDetail.getOrderno());
			os.setImgdomain(orderParam.getImgdomain());
			orderDetail.setOrderGoods(this.appUserMapper.queryOrderGoods(os));
		}
		return list;
	}
	public List<Product>queryBmsBindGoodsId(SearchParam searchParam)
	{
		return this.appUserMapper.queryBmsBindGoodsId(searchParam);
	}
	/**
	 * 根据父分类id查询子分类
	 */
	public List<SearchType>queryChildCatelogListByParent(SearchParam param)
	{
		return this.appUserMapper.queryChildCatelogListByParent(param);
	}
	public int queryBmsBindGoodsAllNum(SearchParam searchParam)
	{
		return this.appUserMapper.queryBmsBindGoodsAllNum(searchParam);
	}
	
	public int updateShopname(SearchParam searchParam)
	{
		return this.appUserMapper.updateShopname(searchParam);
	}
	public int regShopUser(SearchParam searchParam)
	{
		searchParam.setUncode(CommonUtils.uuid());
		return this.appUserMapper.regShopUser(searchParam);
	}
	public int updateOrderState(SearchParam searchParam)
	{
		int result=0;
		String sid=searchParam.getSid();
		String [] sids=sid.split(",");
		for(int i=0;i<sids.length;i++)
		{
			searchParam.setUncode(sids[i]);
			result=this.appUserMapper.updateOrderState(searchParam);
		}
		return result;
	}
	public int updateShopProFlag(SearchParam searchParam)
	{
		int result=0;
		String sid=searchParam.getSid();
		String [] sids=sid.split(",");
		for(int i=0;i<sids.length;i++)
		{
			searchParam.setGoodid(Integer.parseInt(sids[i]));
			result=this.appUserMapper.updateShopProFlag(searchParam);
		}
		
		return result;
	}
	/**
	 * 查询订单统计
	 */
	public OrderCount queryBmsMyOrderCount(SearchParam searchParam)
	{
		return this.appUserMapper.queryBmsMyOrderCount(searchParam);
	}
	/* (non-Javadoc)
	 * @see com.isell.app.service.AppService#updateShopProducts(com.isell.app.dao.entity.SearchParam)
	 */
	public int updateShopProducts(SearchParam searchParam)
	{
		int result=0;
		String sid=searchParam.getSid();
		String [] sids=sid.split(",");
		if(searchParam.getType()==1)//新增
		{
			for(int i=0;i<sids.length;i++)
			{
				searchParam.setGoodid(Integer.parseInt(sids[i]));
				searchParam.setTypeid(0);
				if(this.appUserMapper.checkGoodsIsInTable(searchParam)>0)
				{
					//已存在
					result=this.appUserMapper.updateBmsShopProduct(searchParam);
				}else
				{
					searchParam.setUncode(CommonUtils.uuid());
					result=this.appUserMapper.saveNewShopProduct(searchParam);
				}
			}
		}else if(searchParam.getType()==2)//解绑
		{		 
			for(int i=0;i<sids.length;i++)
			{
				searchParam.setGoodid(Integer.parseInt(sids[i]));
				//searchParam.setTypeid(0);
				//result=this.appUserMapper.updateBmsShopProduct(searchParam);
				result=this.appUserMapper.deleteBmsShopProduct(searchParam);
			}
			
		}
		return result;
	}
}
