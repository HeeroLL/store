package com.isell.service.shop.service;

import java.util.List;

import com.isell.core.util.Record;
import com.isell.service.account.po.CoonRunAccountParam;
import com.isell.service.account.vo.CoonRunAccount;
import com.isell.service.order.po.CoolOrderParam;
import com.isell.service.order.vo.CoolDistributionCar;
import com.isell.service.shop.po.CoonShopBusinessParam;
import com.isell.service.shop.po.CoonShopFavInfo;
import com.isell.service.shop.po.CoonShopProductParam;
import com.isell.service.shop.po.CoonShopShareParam;
import com.isell.service.shop.vo.CoonBanner;
import com.isell.service.shop.vo.CoonShop;
import com.isell.service.shop.vo.CoonShopApply;
import com.isell.service.shop.vo.CoonShopBanner;
import com.isell.service.shop.vo.CoonShopClick;
import com.isell.service.shop.vo.CoonShopFav;
import com.isell.service.shop.vo.CoonShopNotice;
import com.isell.service.shop.vo.CoonShopShare;
import com.isell.service.shop.vo.CoonShopShareExperience;
import com.isell.service.shop.vo.CoonThirdParty;

/**
 * 酷店业务层接口
 * 
 * @author lilin
 * @version [版本号, 2015年9月25日]
 */
public interface CoonShopService {
    /**
     * 根据主键查询酷店信息
     * 
     * @param id 主键
     * @return 酷店信息
     */
    CoonShop getCoonShopById(String id);
    
    /**
     * 获取酷店各类收入
     * 
     * @param shop 酷店信息
     * @return 酷店各类收入
     */
    Record getShopAmount(CoonShop shop);
    
    /**
     * 获取酷店账单列表
     * 
     * @param account 账单信息
     * @return 酷店账单列表
     */
    Record getAccountListPage(CoonRunAccount account);
    
    /**
     * 保存提现
     * 
     * @param account 账单信息
     * @return 是否保存成功
     */
    Record saveWithdraw(CoonRunAccount account);
    
    /**
     * 根据主键删除账单流水（只是不显示，跟其他所有业务操作无关）
     * 
     * @param account 账单信息
     * @return 是否删除成功
     */
    Record deleteAccount(CoonRunAccount account);
    
    /**
     * 保存酷店申请
     * 
     * @param applay 申请信息
     * @return 是否保存成功
     */
    Record saveApplyShop(CoonShopApply applay);
    
    /**
     * 获取酷店信息
     * 
     * @param shop 酷店查询信息
     * @return 酷店信息
     */
    Record getShopIndex(CoonShop shop);
    
    /**
     * 更新酷店信息
     * 
     * @param shop 酷店查询信息
     * @return 是否更新成功
     */
    Record updateShop(CoonShop shop);
    
    /**
     * 新建酷店分享信息
     * 
     * @param share 酷店分享信息
     * @return 是否保存成功
     */
    Record saveShopShare(CoonShopShare share);
    
    /**
     * 删除酷店分享信息
     * 
     * @param share 酷店分享信息
     * @return 是否删除成功
     */
    Record deleteShopShare(CoonShopShare share);
    
    /**
     * 获取酷店分享信息
     * 
     * @param share 酷店分享信息
     * @return 酷店分享信息
     */
    Record getShopShare(CoonShopShare share);
    
    /**
     * 获取不属于该店铺海报列表信息
     * 
     * @param coonShopBanner 海报信息
     * @return 海报列表信息
     */
    Record getBannerListPage(CoonShopBanner coonShopBanner);
    
    /**
     * 获取该店铺海报列表信息
     * 
     * @param coonShopBanner 海报信息
     * @return 海报列表信息
     */
    Record getShopBannerListPage(CoonShopBanner coonShopBanner);
    
    /**
     * 保存店铺海报
     * 
     * @param coonShopBanner 酷店海报信息
     * @return 是否保存成功
     */
    Record saveShopBanner(CoonShopBanner coonShopBanner);
    
    /**
     * 删除店铺海报
     * 
     * @param coonShopBanner 海报信息
     * @return 是否删除成功
     */
    Record deleteShopBanner(CoonShopBanner coonShopBanner);
    
    /**
     * 分页获取酷店粉丝列表接口
     * 
     * @param coonShopFav 粉丝信息
     * @return 粉丝列表信息
     */
    Record getShopFavListPage(CoonShopFav coonShopFav);
    
    /**
     * 申请一件代发
     * 
     * @param coonThirdParty 一件代发申请信息
     * @return 是否保存申请成功
     */
    Record saveShopDistApply(CoonThirdParty coonThirdParty);
    
    /**
     * 判断是否有一件代发权限
     * 
     * @param coonThirdParty 一件代发申请信息
     * @return  一件代发权限
     */
    Record getShopDist(CoonShop shop);
    
    /**
     * 保存进货单
     * 
     * @param coolDistributionCarList进货单信息
     * @return  是否保存成功
     */
    Record saveShopDistributionCar(List<CoolDistributionCar> coolDistributionCarList);
    
    /**
     * 获取进货单分页信息
     * 
     * @param coolDistributionCar进货单信息
     * @return  进货单分页信息
     */
    Record getShopDistributionCarListPage(CoolDistributionCar coolDistributionCar);
    
    /**
     *  删除进货单
     * 
     * @param coolDistributionCar进货单信息
     * @return  是否删除成功
     */
    Record deleteShopDistributionCar(CoolDistributionCar coolDistributionCar);
    
    /**
     *  保存推荐开店申请
     * 
     * @param coonShop 申请信息
     * @return  是否保存成功
     */
    Record saveRecommendApply(CoonShop coonShop);
    
    /**
     *  获取合伙人奖励列表
     * 
     * @param coolOrderParam  参数
     * @return  合伙人列表
     */
    Record getShopPartner(CoolOrderParam coolOrderParam);
    
    /**
     *  获取合伙人奖励明细列表
     * 
     * @param coolOrderParam  参数
     * @return  合伙人奖励明细列表
     */
    Record getShopPartnerDetailList(CoolOrderParam coolOrderParam);
    
    /**
     *  获取生意参谋各统计图表
     * 
     * @param coonShopBusinessParam  查询参数
     * @return  统计列表
     */
    Record getBusinessList(CoonShopBusinessParam coonShopBusinessParam);
    
    /**
     *  保存收藏店铺
     * 
     * @param coonShopFav  参数
     * @return  是否保存成功
     */
    Record saveShopFav(CoonShopFav coonShopFav);
    
    /**
     *  取消收藏店铺
     * 
     * @param coonShopFavInfo  参数
     * @return  是否取消成功
     */
    Record deleteShopFav(CoonShopFavInfo coonShopFavInfo );
    
    /**
     *  获取店铺及推荐商品列表
     * 
     * @param coonShop  参数
     * @return  coonShop
     */
    Record getShopListForUser(CoonShop coonShop);
    
    /**
     *  获取店铺热销商品列表
     * 
     * @param coonShopProductParam  参数
     * @return  coonShop
     */
    Record getHotSellProductList(CoonShopProductParam coonShopProductParam);
    
    /**
     * 获取体验店列表
     *
     * @param coonShop  参数
     * @return 体验店列表
     */
    Record getExperienceShopList(CoonShop coonShop);
    
    /**
     *  保存酷店点击信息
     * 
     * @param coonShopClick  参数
     * @return  是否保存成功
     */
    Record saveCoonShopClick(CoonShopClick coonShopClick);
    
    /**
     * 获取分享信息
     * 
     * @param CoonShopProductParam 分页信息
     * @return 酷店分享信息
     */
    Record getShareList(CoonShopProductParam coonShopProductParam);
    
    /**
     *  保存分享经验心得
     * 
     * @param CoonShopShareExperience coonShopShareExperience  参数
     * @return  是否保存成功
     */
    Record saveShopShareExperience(CoonShopShareExperience coonShopShareExperience);
    
    /**
     *  删除分享经验心得
     * 
     * @param CoonShopShareExperience coonShopShareExperience  参数
     * @return  是否删除成功
     */
    Record deleteShopShareExperience(CoonShopShareExperience coonShopShareExperience);
    
    /**
     *  获取分享经验心得
     * 
     * @param coonShopShareParam  参数
     * @return  分享经验心得列表
     */
    Record getShopShareExperiencePage(CoonShopShareParam coonShopShareParam);
    
    /**
     * 获取卖家版首页店铺信息
     *
     * @param coonShop  参数
     * @return 店铺信息
     */
    Record getHomepageShopInfo(CoonShop coonShop);
    
    /**
     * 获取酷店各类金额列表
     * 
     * @param account 账单信息
     * @return 酷店账单列表
     */
    Record getIncomePage(CoonRunAccountParam coonRunAccountParam);
    
    /**
     * 修改酷店信息
     * 
     * @param coonShop 酷店信息
     * @return 是否修改成功
     */
    Record updateShopInfo(CoonShop coonShop);
    
    /**
     * 获取酷店公告列表
     * 
     * @param coonShop 酷店信息
     * @return 酷店公告列表
     */
    Record getShopNoticePage(CoonShop coonShop);
    
    /**
     * 保存/修改酷店公告
     * 
     * @param coonShopNotice 酷店公告信息
     * @return 是否保存成功
     */
    Record saveShopNotice(CoonShopNotice coonShopNotice);
    
    /**
     * 保存酷店海报
     * 
     * @param bannerList 海报列表
     * @return 是否保存成功
     */
    Record saveShopBannerBatch(List<CoonBanner> bannerList);
    
    /**
     * 删除酷店海报
     * 
     * @param coonShopBanner 酷店海报信息
     * @return 是否删除成功
     */
    Record delShopBanner(CoonShopBanner coonShopBanner);
}
