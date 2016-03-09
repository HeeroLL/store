package com.isell.app.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;

import com.isell.app.dao.entity.CenterOrder;
import com.isell.app.dao.entity.CenterOrderItem;
import com.isell.app.dao.entity.CenterOrderParam;
import com.isell.app.dao.entity.CollParam;
import com.isell.app.dao.entity.CollectInfo;
import com.isell.app.dao.entity.CoolMember;
import com.isell.app.dao.entity.HelpList;
import com.isell.app.dao.entity.HelpType;
import com.isell.app.dao.entity.HomePageImage;
import com.isell.app.dao.entity.Hotword;
import com.isell.app.dao.entity.LoginParam;
import com.isell.app.dao.entity.MemberAddress;
import com.isell.app.dao.entity.MemberCommunity;
import com.isell.app.dao.entity.Notice;
import com.isell.app.dao.entity.OrderCount;
import com.isell.app.dao.entity.OrderDetail;
import com.isell.app.dao.entity.OrderGoods;
import com.isell.app.dao.entity.OrderParam;
import com.isell.app.dao.entity.OrderRecv;
import com.isell.app.dao.entity.Product;
import com.isell.app.dao.entity.ProductGg;
import com.isell.app.dao.entity.ProductImg;
import com.isell.app.dao.entity.ProductRec;
import com.isell.app.dao.entity.SearchParam;
import com.isell.app.dao.entity.SearchProduct;
import com.isell.app.dao.entity.SearchShop;
import com.isell.app.dao.entity.SearchType;
import com.isell.app.dao.entity.SearchTypelist;
import com.isell.app.dao.entity.Theme;
import com.isell.core.mybatis.Mapper;
import com.isell.service.member.vo.CoolMemberFavorites;
import com.isell.service.member.vo.CoolUser;
import com.isell.service.member.vo.UserInfo;

@Mapper
public interface AppUserMapper {
	 
	/**
	 * 首页轮播图
	 * @return
	 */
	List<HomePageImage>queryHomePageImages(String imgdomain);
	/**
	 * 首页公告
	 * @return
	 */
	List<Notice>queryHomePageNotices();
	/**
	 * 公告详情
	 * @param notice
	 * @return
	 */
	Notice queryHpnoticeinfo(Notice notice);
	/**
	 * 根据用户id查询用户详细信息
	 * @param user
	 * @return
	 */
	CoolMember queryUserDetailById(CoolUser user);
	
	
	/**
	 * 首页模块
	 * @return
	 */
	List<Theme>queryThemelist();
	/**
	 * 根据id查询商品信息
	 * @param goodsid
	 * @return
	 */
	Product queryProductinfoById(Map map);
	
	String checkProIsSuccessSale(Map map);
	/**
	 * 查询店铺id，code 
	 * @param sid
	 * @return
	 */
	String queryShopCodeBySid(String sid);
	
	String queryMobileByShopid(String sid);
	
	int checkShopIsHasGoods(Product product);
	
	/**
	 * 
	 * @param map
	 * @return
	 */
	String queryShopIdByRandom(Map map);
	/**
	 * 验证用户是否已经收藏该商品
	 * @param coolMemberFavorites
	 * @return
	 */
	int checkUserIsRecGoods(CoolMemberFavorites coolMemberFavorites);
	/**
	 *  验证商品库存是否足量
	 * @param orderParam
	 * @return
	 */
	int checkGoodsStockIsCanPay(OrderParam orderParam);
	/**
	 * 保存用户收藏
	 * @param coolMemberFavorites
	 * @return
	 */
	int saveUserRecGoods(CoolMemberFavorites coolMemberFavorites);
	/**
	 * 查询搜索热词
	 * @return
	 */
	List<Hotword>queryHotwordlist();
	/**
	 *  商品搜索总的条数 
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
	 * 关键字搜索
	 * @param searchParam
	 * @return
	 */
	List<SearchProduct>querySearchGoods(SearchParam searchParam);
	 /**
	  * 搜索宝贝 -关键字搜索 已关注
	  * @param searchParam
	  * @return
	  */
	 List<SearchProduct>querySearchFavShopGoods(RowBounds rowBounds,SearchParam searchParam);
	 
	 /**
	  * 根据店铺code查询店铺id
	  * @param searchParam
	  * @return
	  */
	 String queryShopIdByShopCode(SearchParam searchParam);
	 /**
	  * 
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
	 * 
	 * @param searchParam
	 * @return
	 */
	List<SearchTypelist>querySearchTpyeList(SearchParam searchParam);
	/**
	 * 
	 * @param searchParam
	 * @return
	 */
	List<SearchProduct>querySearchGoodsUnOrder(SearchParam searchParam);
	/**
	 * 查询推荐店铺
	 * @param searchParam
	 * @return
	 */
	List<SearchShop>querySearchShop(SearchParam searchParam);
	/**
	 *  删除社区消息
	 * @param memberCommunity
	 * @return
	 */
	int deleteMemberCommunity(MemberCommunity memberCommunity);
	/**
	 * 
	 * @param searchParam
	 * @return
	 */
	List<HelpType>queryHelptypeList(SearchParam searchParam);
	/**
	 * 
	 * @param longinParam
	 * @return
	 */
	UserInfo queryLoginCoolUser(LoginParam longinParam);
	/**
	 * 
	 * @param helpType
	 * @return
	 */
	List<HelpList>queryHelplist(HelpType helpType);
	/**
	 * 
	 * @param searchParam
	 * @return
	 */
	List<MemberCommunity>queryAllMemberCommunity(SearchParam searchParam);
	/**
	 * 
	 * @param searchParam
	 * @return
	 */
	List<SearchShop>querySearchShopUnOrder(SearchParam searchParam);
	/**
	 * 验证订单状态是否已经 置为成功
	 * @param orderseq
	 * @return
	 */
	int checkOrderIsPaySuccess(String orderseq);
	
	int queryMyOrderTotalNum(CenterOrderParam centerOrderParam);
	/**
	 * 
	 * @param centerOrderParam
	 * @return
	 */
	List<CenterOrder>queryMyOrderPage(CenterOrderParam centerOrderParam);
	/**
	 * 查询订单
	 * @param centerOrder
	 * @return
	 */
	List<CenterOrderItem>queryOrderItem(CenterOrder centerOrder);
	/**
	 * 
	 * @param code
	 * @return
	 */
	String queryShopidByNO(String code);
	/**
	 * 查询订单详情
	 * @param orderParam
	 * @return
	 */
	OrderDetail queryOrderDetail(OrderParam orderParam);
	/**
	 * 
	 * @param orderParam
	 * @return
	 */
	List<OrderGoods>queryOrderGoods(OrderParam orderParam);
	/**
	 * 
	 * @param searchParam
	 * @return
	 */
	List<Product>queryBmsBindGoodsId(SearchParam searchParam);
	/**
	 * 根据父分类id查询子分类
	 * @param searchParam
	 * @return
	 */
	List<SearchType>queryChildCatelogListByParent(SearchParam searchParam);
	
	int queryBmsBindGoodsAllNum(SearchParam searchParam);
	
	int updateShopname(SearchParam searchParam);
	
	int regShopUser(SearchParam searchParam);
	
	int updateOrderState(SearchParam searchParam);
	
	int updateShopProFlag(SearchParam searchParam);
	
	OrderCount queryBmsMyOrderCount(SearchParam searchParam);
	/**
	 * 
	 * @param searchParam
	 * @return
	 */
	int checkGoodsIsInTable(SearchParam searchParam);
	
	int saveNewShopProduct(SearchParam searchParam);
	
	int deleteBmsShopProduct(SearchParam searchParam);
	
	int updateBmsShopProduct(SearchParam searchParam);
 
	/**
	 * 查询用户收藏数据
	 * @param searchParam
	 * @return
	 */
	List<CollectInfo>queryUserCollect(SearchParam searchParam);
	/**
	 * 查询订单状态
	 * @param orderParam
	 * @return
	 */
	int queryOrderStateByOrderno(OrderParam orderParam);
	/**
	 * 取消订单
	 * @param orderParam
	 * @return
	 */
	int cancleOrderByOrderno(OrderParam orderParam);
	/**
	 * 查询用户收藏 总的数量
	 * @param searchParam
	 * @return
	 */
	int queryUserCollectTotalNum(SearchParam searchParam);
	/**
	 * 查询店铺详情
	 * @param searchParam
	 * @return
	 */
	SearchShop queryShopInfo(SearchShop searchShop);
	/**
	 * 查询体验店 列表
	 * @param searchParam
	 * @return
	 */
	List<SearchShop>queryShpolist(SearchParam searchParam);
	/**
	 * 验证用户是否收藏该商品
	 * @param collParam
	 * @return
	 */
	int checkMemberIsFaved(CollParam collParam);
	/**
	 * 
	 * @param orderParam
	 * @return
	 */
	int checkOrderIsCanMDel(OrderParam orderParam);
	
	int mDdelOrderByOrderNo(OrderParam orderParam);
	/**
	 * 保存用户收藏商品
	 * @param collParam
	 * @return
	 */
	int saveMemberFavourte(CollParam collParam);
	/**
	 * 验证用户有没有评价过这个订单
	 * @param rec
	 * @return
	 */
	int checkUserIsSaveedOrderRec(OrderRecv rec);
	/**
	 * 保存用户订单评论
	 * @param rec
	 * @return
	 */
	int saveUserRecOrder(OrderRecv rec);
	
	int updateOrderEndByOrderno(OrderRecv rec);
	/**
	 * 删除用户收藏信息
	 * @param collParam
	 * @return
	 */
	int deleteMemberFavGoods(CollParam collParam);
	/**
	 * 删除用户收藏地址
	 * @param collParam
	 * @return
	 */
	int deleteMemRecAddress(CollParam collParam);
	/**
	 * 
	 * @param searchParam
	 * @return
	 */
	String checkUserIsRecShop(SearchParam searchParam);
	/**
	 * 保存会员信息
	 * @param coolMember
	 * @return
	 */
	int saveCoolMember(CoolMember coolMember);
	/**
	 * 
	 * @param coolUser
	 * @return
	 */
	int checkShopCodeisExists(CoolUser coolUser);
	/**
	 * 
	 * @param coolUser
	 * @return
	 */
	int saveNewMemberFavShop(CoolUser coolUser);
	/**
	 * 删除用户关注信息
	 * @param searchParam
	 * @return
	 */
	int deleteUserOldFavShop(SearchParam searchParam);
	/**
	 * 保存用户关注店铺信息
	 * @param searchParam
	 * @return
	 */
	int saveUserFavShop(SearchParam searchParam);
	/**
	 * 
	 * @param searchParam
	 * @return
	 */
	int updateUserOldFavGoodsShopCode(SearchParam searchParam);
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
	int clearUserAddress(CollParam collParam);
	
	int updateMemberDefAddress(CollParam collParam);
	/**
	 * 
	 * @param searchParam
	 * @return
	 */
	int cancelUserFavShop(SearchParam searchParam);
	/**
	 * 
	 * @param searchParam
	 * @return
	 */
	int deleteUserFavGoods(SearchParam searchParam);
	/**
	 *  查询我的关注店铺
	 * @param searchParam
	 * @return
	 */
	CollectInfo queryMyFavShop(SearchParam searchParam);
	/**
	 * 
	 * @param memberAddress
	 * @return
	 */
	int clearUserDefAddress(MemberAddress memberAddress);
	/**
	 * 
	 * @param memberAddress
	 * @return
	 */
	int updateUserAddress(MemberAddress memberAddress);
	/**
	 * 
	 * @param memberAddress
	 * @return
	 */
	int saveNewUserAddress(MemberAddress memberAddress);
	
	/**
	 * 保存用户社区留言
	 * @param memberCommunity
	 * @return
	 */
	int saveNewCommunity(MemberCommunity memberCommunity);
	/**
	 * 修改用户留言
	 * @param memberCommunity
	 * @return
	 */
	int updateUserCommunity(MemberCommunity memberCommunity);
	/**
	 * 查询商品详情
	 * @param product
	 * @return
	 */
	Product queryProductinfo(Product product);
	
	List<ProductGg>queryProductGgList(Product product);
	/**
	 * 查询店铺主页
	 * @param searchShop
	 * @return
	 */
	SearchShop queryShopData(SearchShop searchShop);
	/**
	 * 查询店铺公共列表
	 * @param searchShop
	 * @return
	 */
	List<Notice>queryShopNoticelist(SearchShop searchShop);
	/**
	 * 
	 * @param searchShop
	 * @return
	 */
	List<ProductImg>queryShopImagelist(SearchShop searchShop);
	/**
	 * 
	 * @param searchParam
	 * @return
	 */
	int queryGoodsRecTotalNum(SearchParam searchParam);
	/**
	 * 
	 * @param searchParam
	 * @return
	 */
	List<ProductRec>queryGoodsRecPage(SearchParam searchParam);
	/**
	 * 
	 * @param coolMember
	 * @return
	 */
	int updateUserLogo(CoolMember coolMember);
	/**
	 * 
	 * @param coolMember
	 * @return
	 */
	int saveUpdateMemberLogo(CoolMember coolMember);
	/**
	 * 
	 * @param memberCommunity
	 * @return
	 */
	int saveNewWapCommunityinfo(MemberCommunity memberCommunity);
	/**
	 * 
	 * @param memberCommunity
	 * @return
	 */
	int saveUpdateWapCommunityinfo(MemberCommunity memberCommunity);
	/**
	 * 更新用户信息
	 * @param coolMember
	 * @return
	 */
	int updateUserInfo(CoolMember coolMember);
	/**更新用户信息
	 * 
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
	 * 查询bms
	 * @return
	 */
	List<Product>queryBmsProductList(SearchParam searchParam);
	
	int queryBmsProductAllNum(SearchParam searchParam);
	
	int queryBMsMyOrderAllNum(OrderParam orderParam);
	/**
	 * Bms查询订单详情
	 * @param searchParam
	 * @return
	 */
	OrderDetail queryOrderDetailForBms(SearchParam searchParam);
	/**
	 * 查询订单商品详情
	 * @param searchParam
	 * @return
	 */
	List<OrderGoods>queryOrderGoodsListForBms(SearchParam searchParam);
	/**
	 * 
	 * @param orderParam
	 * @return
	 */
	List<OrderDetail>queryBmsMyOrder(OrderParam orderParam);
}
