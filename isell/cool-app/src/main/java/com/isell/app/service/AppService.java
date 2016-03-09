package com.isell.app.service;

import java.util.List;

import com.isell.app.dao.entity.CenterOrder;
import com.isell.app.dao.entity.CenterOrderParam;
import com.isell.app.dao.entity.CollParam;
import com.isell.app.dao.entity.CollectInfo;
import com.isell.app.dao.entity.CoolMember;
import com.isell.app.dao.entity.HelpType;
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
import com.isell.core.util.Record;
import com.isell.service.member.vo.CoolMemberFavorites;
import com.isell.service.member.vo.CoolUser;
import com.isell.service.member.vo.UserInfo;

public interface AppService {
	
	 Record  queryHomePageImages();//首页轮播图
	 
	 Record queryHomePageNotices();//首页公告
	 
	 Record queryHpnoticeinfo(Notice notice);//根据id查询公告详情
	 
	 Record queryUserDetailById(CoolUser user);//根据id查询用户详细信息
	 /**
	  * 查询首页分类
	  * @return
	  */
	 Record queryThemelist(CoolMember coolMember);
	 /**
	  * 保存用户收藏商品
	  * @param coolMemberFavorites
	  * @return
	  */
	 Record saveMemberRecGoods(CoolMemberFavorites coolMemberFavorites);
	 /**
	  * 验证商品库存是否足量
	  * @param orderParam
	  * @return
	  */
	 int checkGoodsStockIsCanPay(OrderParam orderParam);
	 /**
	  * 查询热词
	  * @return
	  */
	 List<Hotword>queryHotwordlist(); 
	 /**
	  * 买家删除订单  已取消、已完成和已退款
	  * @param orderParam
	  * @return
	  */
	 int mDdelOrderByOrderNo(OrderParam orderParam);
	 /**
	  * 保存订单评价
	  * @param orderrec
	  * @return
	  */
	 int saveUserRecOrder(OrderRecv orderrec);
	 /**
	  * 搜索宝贝 -关键字搜索
	  * @param searchParam
	  * @return
	  */
	 List<SearchProduct>querySearchGoods(SearchParam searchParam);
	 /**
	  * 搜索宝贝 -关键字搜索 已关注
	  * @param searchParam
	  * @return
	  */
	 List<SearchProduct>querySearchFavShopGoods(SearchParam searchParam);
	 /**
	  * 搜索宝贝 --已关注 
	  * @param searchParam
	  * @return
	  */
	 int querySearchFavShopGoodsNum(SearchParam searchParam);
	 /**
	  * 分类搜索
	  * @param searchParam
	  * @return
	  */
	 List<SearchType>typeSearch(SearchParam searchParam);
	 /**
	  * 商品搜索 总的条数
	  * @param searchParam
	  * @return
	  */
	 int querySearchGoodsAllNum(SearchParam searchParam);
	 /**
	  * 
	  * @param searchParam
	  * @return
	  */
	 int querySearchUnorderNum(SearchParam searchParam);
	 /**
	  * 
	  * @param searchParam
	  * @return
	  */
	 List<SearchProduct>querySearchGoodsUnOrder(SearchParam searchParam);
	 /**
	  * 保存app客户端 订单数据
	  * @param orderParam
	  * @return
	  */
	 int savePreOrder(OrderParam orderParam);
	 /**
	  * 
	  * @param orderParam
	  * @return
	  */
	 List<HelpType>queryHelptypeList(SearchParam searchParam);
	 /**
	  * 
	  * @param loginParam
	  * @return
	  */
	 UserInfo queryLoginCoolUser(LoginParam loginParam);
	 /**
	  * 删除社区消息
	  * @param memberCommunity
	  * @return
	  */
	 int deleteMemberCommunity(MemberCommunity memberCommunity);
	 
	 /**
	  * 查询社区 发布的消息
	  * @param searchParam
	  * @return
	  */
	 List<MemberCommunity>queryAllMemberCommunity(SearchParam searchParam);
	 
	 /**
	  * 
	  * @param orderseq
	  * @return
	  */
	 int checkOrderIsPaySuccess(String orderseq);
	 /**
	  * 查询我的中心订单
	  * @param centerOrderParam
	  * @return
	  */
	 List<CenterOrder>queryMyOrderPage(CenterOrderParam centerOrderParam);
	 /**
	  * 查询我的订单--所以记录
	  * @param centerOrderParam
	  * @return
	  */
	 int queryMyOrderTotalNum(CenterOrderParam centerOrderParam);
	 /**
	  * 查询订单详情
	  * @param orderParam
	  * @return
	  */
	 OrderDetail queryOrderDetail(OrderParam orderParam);
	 /**
	  *我的收藏 店铺 、商品
	  * @param searchParam
	  * @return
	  */
	 List<CollectInfo>queryUserCollect(SearchParam searchParam);
	 /**
	  * 取消订单
	  * @param orderParam
	  * @return
	  */
	 int cancleOrderByOrderno(OrderParam orderParam);
	 /**
	  * 查询 用户收藏总数
	  * @param searchParam
	  * @return
	  */
	 int queryUserCollectTotalNum(SearchParam searchParam);
	 /**
	  * 查询体验店 列表
	  * @param searchParam
	  * @return
	  */
	 List<SearchShop>queryShpolist(SearchParam searchParam);
	 /**
	  * 查询店铺详情
	  * @param searchShop
	  * @return
	  */
	 SearchShop queryShopInfo(SearchShop searchShop);
	 
	 /**
	  * 保存用户收藏商品
	  * @param collParam
	  * @return
	  */
	 int saveMemberFavourte(CollParam collParam);
	 /**
	  * 删除用户收藏信息
	  * @param collectInfo
	  * @return
	  */
	 int deleteMemberFavGoods(CollParam collParam);
	 /**
	  * 删除收货地址
	  * @param collParam
	  * @return
	  */
	 int deleteMemRecAddress(CollParam collParam);
	 /**
	  * 验证用户有没有关注店铺
	  * @param searchParam
	  * @return
	  */
	 String checkUserIsRecShop(SearchParam searchParam);
	 /**
	  * 根据店铺code查询店铺id
	  * @param searchParam
	  * @return
	  */
	 String queryShopIdByShopCode(SearchParam searchParam);
	 /**
	  * app用户注册 +推荐店铺code
	  * @param coolUser
	  * @return
	  */
	 int appUserRegister(CoolUser coolUser);
	 /**
	  * 
	  * @param searchParam
	  * @return
	  */
	 int saveUserFavShop(SearchParam searchParam);
	 /**
	  * 用户是否关注指定店铺
	  * @param searchParam
	  * @return
	  */
	 int checkUserIsFavShop(SearchParam searchParam);
	 /**
	  * 
	  * @param collParam
	  * @return
	  */
	 int updateMemberDefAddress(CollParam collParam);
	 /**
	  * 取消关注
	  * @param searchParam
	  * @return
	  */
	 int cancelUserFavShop(SearchParam searchParam);
	 /**
	  * 我的中心 我的关注
	  * @param searchParam
	  * @return
	  */
	 CollectInfo queryMyFavShop(SearchParam searchParam);
	 /**
	  * 
	  * @param memberAddress
	  * @return
	  */
	 int updateMemberAddress(MemberAddress memberAddress);
	 /**
	  * 查询商品详情
	  * @param product
	  * @return
	  */
	 Product queryProductinfo(Product product);
	 /**
	  * 查询
	  * @param searchParam
	  * @return
	  */
	 int queryGoodsRecTotalNum(SearchParam searchParam);
	 /**
	  * 查询店铺主页信息
	  * @param searchShop
	  * @return
	  */
	 SearchShop queryShopData(SearchShop searchShop);
	 /**
	  * 商品评论
	  * @param searchParam
	  * @return
	  */
	 List<ProductRec>queryGoodsRecPage(SearchParam searchParam);
	 /**
	  * 保存用户新建社区留言
	  * @param memberCommunity
	  * @return
	  */
	 int saveNewCommunity(MemberCommunity memberCommunity);
	 /**
	  * 
	  * @param memberCommunity
	  * @return
	  */
	 int updateUserCommunity(MemberCommunity memberCommunity);
	 /**
	  * 更新头像
	  * @param coolMember
	  * @return
	  */
	 int saveUpdateMemberLogo(CoolMember coolMember);
	 /**
	  * 
	  * @param memberCommunity
	  * @return
	  */
	 int saveWapCommunityinfo(MemberCommunity memberCommunity);
	 /**
	  * 更新会员信息
	  * @param coolMember
	  * @return
	  */
	 int updateMemberInfo(CoolMember coolMember);
	 /**
	  * 
	  * @param searchParam
	  * @return
	  */
	 List<Product>queryShopNewGoods(SearchParam searchParam);
	 /**
	  * 查询店铺分类
	  * @param searchParam
	  * @return
	  */
	 List<HelpType>queryShopCatelog(SearchParam searchParam);
	 /**
	  * 查询分类下面的商品
	  * @param searchParam
	  * @return
	  */
	 List<Product>queryShopGoodsByCatelogId(SearchParam searchParam);
	 /**
	  * 查询bms系统产品数据
	  * @return
	  */
	 List<Product>queryBmsProductList(SearchParam searchParam);
	 
	 
	 int regShopUser(SearchParam searchParam);
	 
	 int updateShopname(SearchParam  searchParam);
	 /**
	  * 
	  * @param searchParam
	  * @return
	  */
	 int queryBmsProductAllNum(SearchParam searchParam);
	 /**
	  * 查询订单
	  * @param orderParam
	  * @return
	  */
	 List<OrderDetail>queryBmsMyOrder(OrderParam orderParam);
	 /**
	  * 
	  * @param orderParam
	  * @return
	  */
	 int queryBMsMyOrderAllNum(OrderParam orderParam);
	 /**
	  * 
	  * @param searchParam
	  * @return
	  */
	 OrderDetail queryOrderDetailForBms(SearchParam searchParam);
	 /**
	  * 
	  * @param searchParam
	  * @return
	  */
	 int updateShopProducts(SearchParam searchParam);
	 /**
	  * 
	  * @param searchParam
	  * @return
	  */
	 List<Product>queryBmsBindGoodsId(SearchParam searchParam);
	 /**
	  * 
	  * @param searchParam
	  * @return
	  */
	 int queryBmsBindGoodsAllNum(SearchParam searchParam);
	 /**
	  * 根据父类查询子类列表
	  * @param searchParam
	  * @return
	  */
	 List<SearchType>queryChildCatelogListByParent(SearchParam searchParam);
	 
	 int updateOrderState(SearchParam searchParam);
	 /**
	  * 
	  * @param searchParam
	  * @return
	  */
	 int updateShopProFlag(SearchParam searchParam);
	 /**
	  * 
	  * @param searchParam
	  * @return
	  */
	 OrderCount queryBmsMyOrderCount(SearchParam searchParam);
	 
	 
}
