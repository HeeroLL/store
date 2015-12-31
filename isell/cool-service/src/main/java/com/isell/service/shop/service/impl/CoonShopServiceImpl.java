package com.isell.service.shop.service.impl;

import java.io.File;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.isell.core.config.BisConfig;
import com.isell.core.util.CommonUtils;
import com.isell.core.util.DateUtil;
import com.isell.core.util.GenerateQRCode;
import com.isell.core.util.Record;
import com.isell.service.account.dao.CoonRunAccountMapper;
import com.isell.service.account.vo.CoonRunAccount;
import com.isell.service.code.dao.CoolConfigMapper;
import com.isell.service.code.vo.CoolConfig;
import com.isell.service.common.GeneralDef;
import com.isell.service.member.dao.CoolMemberMapper;
import com.isell.service.member.dao.CoolUserMapper;
import com.isell.service.member.vo.CoolMember;
import com.isell.service.member.vo.CoolUser;
import com.isell.service.order.dao.CoolDistributionCarMapper;
import com.isell.service.order.dao.CoolOrderMapper;
import com.isell.service.order.po.CoolDistributionCarInfo;
import com.isell.service.order.po.CoolOrderParam;
import com.isell.service.order.vo.CoolDistributionCar;
import com.isell.service.product.dao.CoolProductGgMapper;
import com.isell.service.product.dao.CoolProductMapper;
import com.isell.service.product.dao.CoolProductReviewMapper;
import com.isell.service.product.vo.CoolProduct;
import com.isell.service.product.vo.CoolProductGg;
import com.isell.service.product.vo.CoolProductReview;
import com.isell.service.shop.dao.CoonBannerMapper;
import com.isell.service.shop.dao.CoonShopApplyMapper;
import com.isell.service.shop.dao.CoonShopBannerMapper;
import com.isell.service.shop.dao.CoonShopClickMapper;
import com.isell.service.shop.dao.CoonShopExperienceMapper;
import com.isell.service.shop.dao.CoonShopFavMapper;
import com.isell.service.shop.dao.CoonShopMapper;
import com.isell.service.shop.dao.CoonShopProductMapper;
import com.isell.service.shop.dao.CoonShopShareMapper;
import com.isell.service.shop.dao.CoonThirdPartyMapper;
import com.isell.service.shop.po.CoonShopBusinessInfo;
import com.isell.service.shop.po.CoonShopBusinessParam;
import com.isell.service.shop.po.CoonShopPartnerInfo;
import com.isell.service.shop.po.CoonShopProductInfo;
import com.isell.service.shop.po.CoonShopProductParam;
import com.isell.service.shop.service.CoonShopService;
import com.isell.service.shop.vo.CoonBanner;
import com.isell.service.shop.vo.CoonShop;
import com.isell.service.shop.vo.CoonShopApply;
import com.isell.service.shop.vo.CoonShopBanner;
import com.isell.service.shop.vo.CoonShopClick;
import com.isell.service.shop.vo.CoonShopFav;
import com.isell.service.shop.vo.CoonShopShare;
import com.isell.service.shop.vo.CoonThirdParty;

/**
 * 酷店业务层接口实现类
 * 
 * @author lilin
 * @version [版本号, 2015年9月25日]
 */
@Service("coonShopService")
public class CoonShopServiceImpl implements CoonShopService {
	
	private static final Logger log = Logger.getLogger(CoonShopServiceImpl.class);
    
    /**
     * 酷店表Mapper
     */
    @Resource
    private CoonShopMapper coonShopMapper;
    
    /**
     * 用户表mapper
     */
    @Resource
    private CoolUserMapper coolUserMapper;
    
    /**
     * 会员表mapper
     */
    @Resource
    private CoolMemberMapper coolMemberMapper;
    
    /**
     * 账单流水记录表mapper
     */
    @Resource
    private CoonRunAccountMapper coonRunAccountMapper;
    
    /**
     * 酷店申请表mapper
     */
    @Resource
    private CoonShopApplyMapper coonShopApplyMapper;
    
    /**
     * 酷店分享表mapper
     */
    @Resource
    private CoonShopShareMapper coonShopShareMapper;
    
    /**
     * 系统海报表mapper
     */
    @Resource
    private CoonBannerMapper coonBannerMapper;
    
    /**
     * 酷店海报表mapper
     */
    @Resource
    private  CoonShopBannerMapper coonShopBannerMapper;
    
    /**
     * 酷店收藏表mapper
     */
    @Resource
    private CoonShopFavMapper coonShopFavMapper;
    
    /**
     * 一件代发表mapper
     */
    @Resource
    private  CoonThirdPartyMapper coonThirdPartyMapper;
    
    /**
     * 商品表mapper
     */
    @Resource
    private  CoolProductMapper coolProductMapper;
    
    /**
     * 商品规格表mapper
     */
    @Resource
    private  CoolProductGgMapper coolProductGgMapper;
    
    /**
     * 进货单表mapper
     */
    @Resource
    private CoolDistributionCarMapper coolDistributionCarMapper;
    
    /**
     * 酷店商品表mapper
     */
    @Resource
    private CoonShopProductMapper coonShopProductMapper;
    
    /**
     * 商品评价表mapper
     */
    @Resource
    private CoolProductReviewMapper coolProductReviewMapper;
    
    /**
     * 体验店表mapper
     */
    @Resource
    private CoonShopExperienceMapper coonShopExperienceMapper;
    
    /**
     * 配置表mapper
     */
    @Resource
    private CoolConfigMapper coolConfigMapper;
    
    /**
     * 订单表mapper
     */
    @Resource
    private CoolOrderMapper coolOrderMapper;
    
    /**
     * 酷店访问表mapper
     */
    @Resource
    private CoonShopClickMapper coonShopClickMapper;
    
    /**
     * coolJdbcTemplate
     */
    @Resource
    private JdbcTemplate jdbcTemplate;
    
    /**
     * 配置信息
     */
    @Resource
    private BisConfig config;
    
    @Override
    public CoonShop getCoonShopById(String id) {
        return coonShopMapper.getCoonShopById(id);
    }

    /**
     * 获取酷店各类收入
     * 
     * @param id 主键
     * @return 酷店各类收入
     */
	@Override
	public Record getShopAmount(CoonShop shop) {
		Record record = new Record(); 
		boolean success = false;
		String shopId = shop.getId();
		if(StringUtils.isNotEmpty(shopId)){
			CoonShop coonShop = coonShopMapper.getCoonShopById(shopId);
			if(coonShop != null){
				record.set("nwdAmount", coonShop.getNwdAmount());
				record.set("wdAmount", coonShop.getWdAmount());
				record.set("allAmount", coonShop.getAllAmount());
				record.set("tbdAmount", coonShop.getTbdAmount());
				record.set("jjAmount", coonShop.getJjAmount());
				success = true;
			}else{
				record.set("msg", "参数不正确");
			}
		}else{
			record.set("msg", "参数不能为空");
		}
		
		record.set("success", success);
		return record;
	}

	 /**
     * 获取酷店账单列表
     * 
     * @param account 账单信息
     * @return 酷店账单列表
     */
	@Override
	public Record getAccountListPage(CoonRunAccount account) {
		Record record = new Record(); 
		boolean success = false;
		String userId = account.getUserId();
		if(StringUtils.isNotEmpty(userId)){
			CoolUser user = coolUserMapper.getCoolUserById(Integer.valueOf(userId));
			if(user != null){
				String state = account.getState();  //1：可提现 2：即将可用  对应 isFreeze 0未冻结1冻结 空未冻结
				if(StringUtils.isNotEmpty(state)){	
					if("1".equals(state)){
						account.setIsFreeze(CoonRunAccount.IS_FREEZE_0);
					}else{
						account.setIsFreeze(CoonRunAccount.IS_FREEZE_1);
					}
				}
				List<CoonRunAccount> accountList = coonRunAccountMapper.findCoonRunAccountListPage(account.getRowBounds(), account);
				if(CollectionUtils.isNotEmpty(accountList)){
					record.set("accountList", accountList);
				}
				success = true;
			}else{
				record.set("msg", "用户不存在");
			}
		}else{
			record.set("msg", "用户主键不能为空");
		}		
		record.set("success", success);
		return record;
	}

	/**
     * 保存提现
     * 
     * @param account 账单信息
     * @return 是否保存成功
     */
	@Override
	public Record saveWithdraw(CoonRunAccount account) {
		Record record = new Record(); 
		boolean success = false;
		String shopId = account.getShopId();
		if(StringUtils.isNotEmpty(shopId)){
			CoonShop shop = coonShopMapper.getCoonShopById(shopId);
			if(shop != null){
				String userId = shop.getUserId();
				CoolMember member = coolMemberMapper.getCoolMemberByUserId(Integer.valueOf(userId));
				String shopname = shop.getName();
				if(StringUtils.isEmpty(account.getWithdrawNum())){
					record.set("msg", "支付账号不能为空");
				}else{
					BigDecimal amount = account.getAmount();
					if(amount == null){
						record.set("msg", "提现金额不能为空");
					}else{
				        BigDecimal nwdAmount = shop.getNwdAmount();
				        BigDecimal fee = new BigDecimal(0);
				        BigDecimal maxMoney = GeneralDef.MAX_WITHDRAW_MONEY;
				        BigDecimal total = new BigDecimal(0);
				        CoonRunAccount select = new CoonRunAccount();
				        select.setUserId(userId);
				        select.setCreateTime(new Date());
				        List<CoonRunAccount> runAccounts = coonRunAccountMapper.findCoonRunAccountList(select);
				        // 计算今日提现金额
				        for (CoonRunAccount rAccount : runAccounts) {
				            total = total.add(rAccount.getAmount());
				        }
				        // 判断今日提现金额是否大于10000
				        if (amount.add(total).compareTo(maxMoney) != 1) {
				            // 判断提现金额是否大于0
				            if (amount.compareTo(new BigDecimal(0)) == 1) {
				                // 如果提现金额小于200收取1元手续费
				                if (amount.compareTo(new BigDecimal(200)) == -1) {
				                    fee = new BigDecimal(1);
				                }
				                // 判断提取金额(加手续费)是否大于余额
				                int result = amount.add(fee).compareTo(nwdAmount);
				                if (result == -1) {			
				                	int res = 0;
				                	res = jdbcTemplate.update("update coon_shop set nwd_amount=nwd_amount - ?-?,tbd_amount=tbd_amount + ? where user_id = ?",amount,fee,amount,userId);
				                	if(res > 0){
					                    String sNumber = DateUtil.dateToStr(DateUtil.yyyyMMddHHmmss, new Date()) + CommonUtils.randomFour();				                    
					                    account.setId(CommonUtils.uuid());
					                    account.setUserId(userId);
										account.setShopName(shopname);
										account.setSerialNumber(sNumber);
										account.setType(CoonRunAccount.TYPE_1);
										account.setWithdrawName(member.getRealname());
										account.setFee(fee);
										account.setWithdrawState(CoonRunAccount.WITHDRAW_STATE_0);
										account.setWithdrawType(CoonRunAccount.WITHDRAW_TYPE_0);
										res = coonRunAccountMapper.saveCoonRunAccount(account);
										if(res > 0){
											member.setPayNum(account.getWithdrawNum());
											res = coolMemberMapper.updateCoolMember(member);
											if(res > 0){
												success = true;
											}else{
												record.set("msg", "更新会员支付账号失败");
											}
										}else{
											record.set("msg", "保存提现信息失败");
										}
				                	}else{
				                		record.set("msg", "更新酷店收益失败");
				                	}
				                }else{
				                	record.set("msg", "提现金额不能大于可提现余额");
				                }
				            }else{
				            	record.set("msg", "提现金额不能小于0");
				            }
				        }else{
				        	record.set("msg", "提现金额超出单日可提金额上限");
				        }
					}
				}							
			}else{
				record.set("msg", "店铺主键不正确");
			}
		}else{
			record.set("msg", "店铺主键不能为空");
		}
		record.set("success", success);
		return record;
	}

	/**
     * 根据主键删除账单流水（只是不显示，跟其他所有业务操作无关）
     * 
     * @param account 账单信息
     * @return 是否删除成功
     */
	@Override
	public Record deleteAccount(CoonRunAccount account) {
		Record record = new Record(); 
		boolean success = false;
		String id = account.getId();
		if(StringUtils.isNotEmpty(id)){
			account.setIsDel(GeneralDef.BYTE_1);
			CoonRunAccount coonRunAccount = coonRunAccountMapper.getCoonRunAccountById(id);
			if(coonRunAccount != null){
				int result = coonRunAccountMapper.updateCoonRunAccount(account);
				if(result > 0){
					success = true;
				}else{
					record.set("msg", "删除账单失败");
				}
			}else{
				record.set("msg", "参数错误，该账单流水记录不存在");
			}			
		}else{
			record.set("msg", "参数不能为空");
		}
		record.set("success", success);
		return record;
	}

	/**
     * 保存酷店申请
     * 
     * @param applay 申请信息
     * @return 是否保存成功
     */
	@Override
	public Record saveApplyShop(CoonShopApply applay) {
		Record record = new Record(); 
		boolean success = false;
		String userId = applay.getUserId();
		if(StringUtils.isNotEmpty(userId)){
			String realname = applay.getName();
			if(StringUtils.isNotEmpty(realname)){
				String mobile = applay.getMobile();
				if(StringUtils.isNotEmpty(mobile)){
					CoolUser user = coolUserMapper.getCoolUserById(Integer.valueOf(userId));
					if(user != null){
						CoonShop shop = coonShopMapper.getCoonShopByUserId(userId);
						if(shop == null){							
							applay.setId(CommonUtils.uuid());
							applay.setState(GeneralDef.BYTE_1);
							applay.setAudit("0");
							applay.setAuditTime(new Date());							
							int result = coonShopApplyMapper.saveCoonShopApply(applay);
							if(result > 0){
								//直接生成酷店信息
								shop = new CoonShop();
								String no = "";
								String maxNo = coolMemberMapper.getMaxCoolMemberNo();
								if(StringUtils.isNotEmpty(maxNo)){
									no = (Integer.parseInt(maxNo) + 1) + "";
								}else{
									no = "10000000";
								}
								String uuid = CommonUtils.uuid();
								shop.setId(uuid);
								shop.setCode(no);
								shop.setUserId(userId.toString());
								shop.setName(realname);								
								String dirPath = "/shop/" + uuid + "/";
								 // 生成酷店二维码和网址
								String webSite = "http://" + no + ".m." + config.getBaseDomain();
								String dir = config.getImgLocal();
								String qrCode = dirPath + "qr_" + no  + ".png";
								new File(dir + dirPath).mkdirs();
								File file = new File(dir + qrCode);
								if(file.exists()){
									FileUtils.deleteQuietly(file);
								}
								//CommonUtils.createQRCode(webSite, dir + qrCode, "/wap/images/xiaocoon1.png", 10);
							    try {
									 GenerateQRCode.generate("qr_" + no  + ".png", webSite, dir+ dirPath, 200, 200);
								 } catch (Exception e) {
									 log.info("酷店编码为" + no + "的店铺生成二维码失败");
								 }
								shop.setShowWay(CoonShop.SHOW_WAY_1);
								shop.setQrCode(qrCode);
								result = coonShopMapper.saveCoonShop(shop);
								if(result > 0){
									success = true;
								}else{
									record.set("msg", "酷店注册保存失败");
								}
							}else{
								record.set("msg", "酷店申请失败");
							}
						}else{
							record.set("msg", "该用户已注册过店铺");
						}
					}else{
						record.set("msg", "参数错误，无法获得用户信息");
					}
				}else{
					record.set("msg", "手机号不能为空");
				}
			}else{
				record.set("msg", "姓名不能为空");
			}
		}else{
			record.set("msg", "用户主键不能为空");
		}
		record.set("success", success);
		return record;
	}

	/**
     * 获取酷店首页信息
     * 
     * @param shop 酷店查询信息
     * @return 酷店信息
     */
	@Override
	public Record getShopIndex(CoonShop shop) {
		Record record = new Record(); 
		boolean success = false;
		String shopId = shop.getId();
		if(StringUtils.isNotEmpty(shopId)){
			CoonShop coonShop = coonShopMapper.getCoonShopById(shopId);
			if(coonShop != null){
				CoolMember member = coolMemberMapper.getCoolMemberByUserId(Integer.valueOf(coonShop.getUserId()));
				if(member != null){
					record.set("mobile",member.getMobile());
					record.set("qq",member.getQq());
				}
				record.set("coonshop",coonShop);
				success = true;
			}else{
				record.set("msg", "参数错误，酷店信息不存在");
			}
		}else{
			record.set("msg", "酷店主键不能为空");
		}
		record.set("success", success);
		return record;
	}

	/**
     * 更新酷店信息
     * 
     * @param shop 酷店查询信息
     * @return 是否更新成功
     */
	@Override
	public Record updateShop(CoonShop shop) {
		Record record = new Record(); 
		boolean success = false;
		String shopId = shop.getId();
		if(StringUtils.isNotEmpty(shopId)){
			CoonShop coonShop = coonShopMapper.getCoonShopById(shopId);
			if(coonShop != null){
				//通用更新，可扩展
				boolean flag = false;
				String annInfo = shop.getAnnInfo(); //酷店公告
				if(StringUtils.isNotEmpty(annInfo)){
					coonShop.setAnnInfo(annInfo);
					flag = true;
				}
				if(shop.getShowWay() != null){ //显示方式
					coonShop.setShowWay(shop.getShowWay());
					flag = true;
				}
				if(shop.getShowModel() != null){ //酷店模板
					coonShop.setShowModel(shop.getShowModel());
					flag = true;
				}
				
				if(flag){
					int result = coonShopMapper.updateCoonShop(coonShop);
					if(result > 0){
						success = true;
					}else{
						record.set("msg", "酷店更新失败");
					}
				}else{
					record.set("msg", "需要修改的信息不能为空");
				}
			}else{
				record.set("msg", "参数错误，酷店信息不存在");
			}			
		}else{
			record.set("msg", "酷店主键不能为空");
		}
		record.set("success", success);
		return record;
	}

	/**
     * 新建酷店分享信息
     * 
     * @param share 酷店分享信息
     * @return 是否保存成功
     */
	@Override
	public Record saveShopShare(CoonShopShare share) {
		Record record = new Record(); 
		boolean success = false;
		String shopId = share.getsShop();
		if(StringUtils.isNotEmpty(shopId)){
			CoonShop shop  = coonShopMapper.getCoonShopById(shopId);
			if(shop != null){
				int result = 0;
				String msg = "";
				String id = share.getId();
				if(StringUtils.isEmpty(id)){
					share.setId(CommonUtils.uuid());
					result = coonShopShareMapper.saveCoonShopShare(share);
					msg = "保存";
				}else{
					result = coonShopShareMapper.updateCoonShopShare(share);
					msg = "修改";
				}
				if(result  > 0){
					success = true;
				}else{
					record.set("msg",  msg + "酷店信息失败");
				}
			}else{
				record.set("msg", "参数错误，酷店信息不存在");
			}
		}else{
			record.set("msg", "酷店主键不能为空");
		}
		record.set("success", success);
		return record;
	}

	/**
     * 删除酷店分享信息
     * 
     * @param share 酷店分享信息
     * @return 是否删除成功
     */
	@Override
	public Record deleteShopShare(CoonShopShare share) {
		Record record = new Record(); 
		boolean success = false;
		String id = share.getId();
		if(StringUtils.isNotEmpty(id)){
			int result = coonShopShareMapper.deleteCoonShopShare(id);
			if(result > 0){
				success = true;
			}else{
				record.set("msg", "酷店分享删除失败");
			}
		}else{
			record.set("msg", "参数错误，分享主键不能为空");
		}
		record.set("success", success);
		return record;
	}

	/**
     * 获取酷店分享信息
     * 
     * @param share 酷店分享信息
     * @return 酷店分享信息
     */
	@Override
	public Record getShopShare(CoonShopShare share) {
		Record record = new Record(); 
		boolean success = false;
		String shopId = share.getsShop();
		if(StringUtils.isNotEmpty(shopId)){
			List<CoonShopShare> shareList = coonShopShareMapper.findCoonShopShareList(share);
			if(CollectionUtils.isNotEmpty(shareList)){
				record.set("shareList", shareList);
				success = true;
			}else{
				record.set("msg", "尚未保存酷店分享信息");
			}
		}else{
			record.set("msg", "酷店主键不能为空");
		}
		record.set("success", success);
		return record;
	}

	/**
     * 保存店铺海报
     * 
     * @param coonShopBanner 酷店海报信息
     * @return 是否保存成功
     */
	@Override
	public Record saveShopBanner(CoonShopBanner coonShopBanner) {
		Record record = new Record(); 
		boolean success = false;
		String shopId = coonShopBanner.getsId();
		if(StringUtils.isNotEmpty(shopId)){
			String pId = coonShopBanner.getpId();
			if(StringUtils.isNotEmpty(pId)){
				String img = coonShopBanner.getImg();
				if(StringUtils.isNotEmpty(img)){
					int result = 0;
					if(StringUtils.isEmpty(coonShopBanner.getId())){
						coonShopBanner.setId(CommonUtils.uuid());
						result = coonShopBannerMapper.saveCoonShopBanner(coonShopBanner);
					}else{
						result = coonShopBannerMapper.updateCoonShopBanner(coonShopBanner);
					}
					if(result > 0){
						success = true;
					}else{
						record.set("msg", "、酷店海报保存失败");
					}
				}else{
					record.set("msg", "图片路径不能为空");
				}
			}else{
				record.set("msg", "商品主键不能为空");
			}
		}else{
			record.set("msg", "店铺主键不能为空");
		}		
		record.set("success", success);
		return record;
	}

	/**
     * 获取海报列表信息
     * 
     * @param coonShopBanner 海报信息
     * @return 海报列表信息
     */
	@Override
	public Record getShopBannerListPage(CoonShopBanner coonShopBanner) {
		Record record = new Record(); 
		boolean success = false;
		String shopId = coonShopBanner.getsId();
		if(StringUtils.isNotEmpty(shopId)){
			CoonShop shop = coonShopMapper.getCoonShopById(shopId);
			if(shop != null){
				List<CoonBanner> bannerList = coonBannerMapper.findCoonBannerListPage(coonShopBanner.getRowBounds(), shopId);
				if(CollectionUtils.isNotEmpty(bannerList)){
					record.set("bannerList", bannerList);
				}
				success = true;
			}else{
				record.set("msg", "参数错误，不能获取酷店信息");
			}
		}else{
			record.set("msg", "酷店主键不能为空");
		}
		record.set("success", success);
		return record;
	}

	/**
     * 删除店铺海报
     * 
     * @param coonShopBanner 海报信息
     * @return 是否删除成功
     */
	@Override
	public Record deleteShopBanner(CoonShopBanner coonShopBanner) {
		Record record = new Record(); 
		boolean success = false;
		String ids = coonShopBanner.getIds();
		if(StringUtils.isNotEmpty(ids)){
			String[] idStr = ids.split(",");
			if(idStr != null){
				for(String id : idStr){
					coonShopBannerMapper.deleteCoonShopBanner(id);
				}
				success = true;
			}else{
				record.set("msg", "参数格式不正确");
			}
		}else{
			record.set("msg", "参数不能为空");
		}
		record.set("success", success);
		return record;
	}

	 /**
     * 分页获取酷店粉丝列表接口
     * 
     * @param coonShopFav 粉丝信息
     * @return 粉丝列表信息
     */
	@Override
	public Record getShopFavListPage(CoonShopFav coonShopFav) {
		Record record = new Record(); 
		boolean success = false;
		String sId = coonShopFav.getsId();
		if(StringUtils.isNotEmpty(sId)){
			List<CoonShopFav> favList = coonShopFavMapper.findCoonShopFavListPage(coonShopFav.getRowBounds(),coonShopFav);
			if(CollectionUtils.isNotEmpty(favList)){
				record.set("favList", favList);
			}
			success = true;
		}else{
			record.set("msg", "酷店主键不能为空");
		}
		record.set("success", success);
		return record;
	}

	 /**
     * 申请一件代发
     * 
     * @param coonThirdParty 一件代发申请信息
     * @return 是否保存申请成功
     */
	@Override
	public Record saveShopDistApply(CoonThirdParty coonThirdParty) {
		Record record = new Record(); 
		boolean success = false;
		String userId = coonThirdParty.getUserId();
		if(StringUtils.isNotEmpty(userId)){
			String coolStoreId = coonThirdParty.getCoolStoreId();
			if(StringUtils.isNotEmpty(coolStoreId)){
				CoonThirdParty party = coonThirdPartyMapper.getCoonThirdPartyByShopId(coolStoreId);
				if(party == null){
					coonThirdParty.setId(CommonUtils.uuid());
					int result = coonThirdPartyMapper.saveCoonThirdParty(coonThirdParty);
					if(result > 0){
						success = true;
					}else{
						record.set("msg", "保存一件代发申请失败");
					}
				}else{
					record.set("msg", "已申请过一件代发，请等待审核");
				}
			}else{
				record.set("msg", "酷店主键不能为空");
			}
		}else{
			record.set("msg", "用户主键不能为空");
		}
		record.set("success", success);
		return record;
	}

	/**
     * 判断是否有一件代发权限
     * 
     * @param coonThirdParty 一件代发申请信息
     * @return  一件代发权限
     */
	@Override
	public Record getShopDist(CoonShop shop) {
		Record record = new Record(); 
		boolean success = false;
		String shopId = shop.getId();
		if(StringUtils.isNotEmpty(shopId)){
			CoonThirdParty party = coonThirdPartyMapper.getCoonThirdPartyByShopId(shopId);
			if(party != null){
				if(CoonThirdParty.AUDIT_STATE_0.toString().equals(party.getAuditState().toString())){ 
					record.set("msg", "尚未审核");
				}else if(CoonThirdParty.AUDIT_STATE_1.toString().equals(party.getAuditState().toString())){
					record.set("msg", "审核中");
				}else if(CoonThirdParty.AUDIT_STATE_2.toString().equals(party.getAuditState().toString())){
					record.set("msg", "审核中");
					success = true;
				}else{
					record.set("msg", "未通过");
				}				
			}else{
				record.set("msg", "该酷店尚未申请一件代发");
			}
		}else{
			record.set("msg", "酷店主键不能为空");
		}
		record.set("success", success);
		return record;
	}

	/**
     * 保存进货单
     * 
     * @param coolDistributionCarList进货单信息
     * @return  是否保存成功
     */
	@Override
	public Record saveShopDistributionCar(List<CoolDistributionCar> coolDistributionCarList) {
		Record record = new Record(); 
		boolean success = false;
		if(CollectionUtils.isNotEmpty(coolDistributionCarList)){
			int result = 0;
			for(CoolDistributionCar car : coolDistributionCarList){
				Integer userId = car.getUserId();
				if(userId != null){
					CoolUser user = coolUserMapper.getCoolUserById(userId);
					if(user != null){
						Integer pId = car.getpId();
						if(pId != null){
							CoolProduct product = coolProductMapper.getCoolProductById(pId);
							if(product != null){
								Integer gId = car.getgId();
								if(gId != null){
									Integer quantity = car.getQuantity();
									CoolProductGg productGg = coolProductGgMapper.getCoolProductGgById(gId);
									if(productGg != null){
										Float stock = productGg.getStock();
										if(stock > 0){
											if(quantity == null){
												quantity = 1;
											}
											List<CoolDistributionCar> cList = coolDistributionCarMapper.getCoolDistributionCarList(car);
											result = 0;
											success = false;
											if(CollectionUtils.isNotEmpty(cList)){
												CoolDistributionCar c = cList.get(0);
												car.setId(c.getId());
												car.setQuantity(quantity + c.getQuantity());
												result = coolDistributionCarMapper.updateCoolDistributionCar(car);
											}else{
												car.setQuantity(quantity);
												result = coolDistributionCarMapper.saveCoolDistributionCar(car);
											}
											if(result > 0){
												success = true;
											}else{
												record.set("msg", "进货单保存失败");
											}
										}else{
											record.set("msg", "商品规格主键为"+gId+"的商品已售罄");
										}
									}else{
										record.set("msg", "参数错误，商品规格主键为"+gId+"的商品规格不存在");
									}
									
								}else{
									record.set("msg", "商品规格主键不能为空");
								}
							}else{
								record.set("msg", "参数错误，商品主键为"+pId+"的商品不存在");
							}
						}else{
							record.set("msg", "商品主键不能为空");
						}
					}else{
						record.set("msg", "参数错误，用户主键为"+userId+"的用户不存在");
					}
				}else{
					record.set("msg", "用户主键不能为空");
				}
			}
		}else{
			record.set("msg", "参数不正确");
		}
		record.set("success", success);
		return record;
	}

	 /**
     * 获取进货单分页信息
     * 
     * @param coolDistributionCar进货单信息
     * @return  进货单分页信息
     */
	@Override
	public Record getShopDistributionCarListPage(CoolDistributionCar coolDistributionCar) {
		Record record = new Record(); 
		boolean success = false;
		Integer userId = coolDistributionCar.getUserId();
		if(userId != null){
			CoolUser user = coolUserMapper.getCoolUserById(userId);
			if(user != null){
				List<CoolDistributionCarInfo> carList = coolDistributionCarMapper.findCoolDistributionCarInfoListPage(coolDistributionCar.getRowBounds(), coolDistributionCar);
				if(CollectionUtils.isNotEmpty(carList)){
					record.set("carList", carList);
					success = true;
				}else{
					record.set("msg", "无数据");
				}
			}else{
				record.set("msg", "参数错误，用户主键为"+userId+"的用户不存在");
			}
		}else{
			record.set("msg", "参数错误，用户主键不能为空");
		}
		record.set("success", success);
		return record;
	}

	/**
     *  删除进货单
     * 
     * @param coolDistributionCar进货单信息
     * @return  是否删除成功
     */
	@Override
	public Record deleteShopDistributionCar(CoolDistributionCar coolDistributionCar) {
		Record record = new Record(); 
		boolean success = false;
		Integer id = coolDistributionCar.getId();
		if(id != null){
			int result = coolDistributionCarMapper.deleteCoolDistributionCar(id);
			if(result > 0){
				success = true;
			}else{
				record.set("msg", "删除失败");
			}
		}else{
			record.set("msg", "参数错误，进货单主键不能为空");
		}
		record.set("success", success);
		return record;
	}

	 /**
     *  保存推荐开店申请
     * 
     * @param coonShop 申请信息
     * @return  是否保存成功
     */
	@Override
	public Record saveRecommendApply(CoonShop coonShop) {
		Record record = new Record(); 
		boolean success = false;
		String username = coonShop.getUsername();
		String realname = coonShop.getRealname();
		String code = coonShop.getCode();
		if(StringUtils.isNotEmpty(username) || StringUtils.isNotEmpty(realname) || StringUtils.isNotEmpty(code)){
			//会员
			boolean flag = false;
			Integer userId = 0;
			int result = 0;
			String no = "";
			CoolUser user = coolUserMapper.getCoolUserByUserName(username);
			if(user == null){
				user = new CoolUser();
				String password = coonShop.getPassword();
				if(StringUtils.isNotEmpty(password)){
					user.setUsername(username);
					user.setPassword(password);
					user.setState(CoolUser.USER_STATE_1);
					user.setMobile(username);
					user.setCreatetime(new Date());
					user.setPetname(realname);
					result = coolUserMapper.saveCoolUser(user);
					if(result > 0){
						userId = coolUserMapper.getCoolUserByUserName(username).getId();
						if(userId != null){
							CoolMember member = new CoolMember();
							String maxNo = coolMemberMapper.getMaxCoolMemberNo();
							if(StringUtils.isNotEmpty(maxNo)){
								no = (Integer.parseInt(maxNo) + 1) + "";
							}else{
								no = "10000000";
							}
							member.setNo(no);
							member.setUserId(userId);
							member.setMobile(username);
							member.setRealname(realname);
							member.setPetname(realname);
							result = coolMemberMapper.saveCoolMember(member);
							if(result > 0){
								flag = true;
							}else{
								record.set("msg", "会员保存失败");
							}
						}else{
							record.set("msg", "用户主键获取失败");
						}
					}else{
						record.set("msg", "用户保存失败");
					}
				}else{
					record.set("msg", "参数错误，参数不能为空");
				}
			}			
			//店铺
			if(flag){
				CoonShop shop = coonShopMapper.getCoonShopByUserId(userId.toString());
				if(shop == null){
					CoonShopApply apply = coonShopApplyMapper.getCoonShopApplyByUserId(userId.toString());
					if(apply == null){
						result = 0; 						
						apply = new CoonShopApply();
						apply.setId(CommonUtils.uuid());
						apply.setUserId(userId.toString());
						apply.setMobile(username);
						apply.setState(CoonShopApply.STATE_1);
						apply.setCreatetime(new Date());
						apply.setName(realname);
						CoolConfig cfig = coolConfigMapper.getCoolConfigById(1);
						if(!cfig.getStoreAudit()){
							apply.setAudit(CoonShopApply.AUDIT_0);
							apply.setAuditTime(new Date());
							apply.setState(CoonShopApply.STATE_1);
						}
						result = coonShopApplyMapper.saveCoonShopApply(apply);
						if(result > 0){
							shop = new CoonShop();				
							//推荐店铺
							CoonShop cShop =  coonShopMapper.getCoonShopByCode(code);
							if(cShop != null){
								String uuid = CommonUtils.uuid();
								shop.setId(uuid);
								shop.setCode(no);
								shop.setUserId(userId.toString());
								shop.setName(realname+"的酷店");								
								String dirPath = "/shop/" + uuid + "/";
								 // 生成酷店二维码和网址
								String webSite = "http://" + no + ".m." + config.getBaseDomain();
								String dir = config.getImgLocal();
								String qrCode = dirPath + "qr_" + no + ".png";
								new File(dir + dirPath).mkdirs();
								File file = new File(dir + qrCode);
								if(file.exists()){
									FileUtils.deleteQuietly(file);
								}
								//CommonUtils.createQRCode(webSite, dir + qrCode, "/wap/images/xiaocoon1.png", 10);
							    try {
							    	GenerateQRCode.generate("qr_" + no + ".png", webSite, dir+ dirPath, 200, 200);
								 } catch (Exception e) {
									 log.info("酷店编码为" + no + "的店铺生成二维码失败");
								 }
								shop.setShowWay(CoonShop.SHOW_WAY_1);
								shop.setQrCode(qrCode);
								shop.setRecommendId(cShop.getId());
								result = coonShopMapper.saveCoonShop(shop);
								if(result > 0){
									success = true;
								}else{
									record.set("msg", "酷店注册保存失败");
								}
							}else{
								record.set("msg", "参数错误，推荐店铺不存在");
							}
						}else{
							record.set("msg", "酷店申请保存失败");
						}
					}else{
						apply.setState(CoonShopApply.STATE_1);
						coonShopApplyMapper.updateCoonShopApply(apply);
					}
				}else{
					record.set("msg", "参数错误，用户名不能为空");
				}
			}else{
				record.set("msg", "该用户已存在酷店");
			}
		}else{
			record.set("msg", "参数错误，用户名和真实姓名不能为空");
		}
		record.set("success", success);
		return record;
	}

	 /**
     *  获取合伙人奖励列表
     * 
     * @param coolOrderParam  参数
     * @return  合伙人列表
     */
	@Override
	public Record getShopPartner(CoolOrderParam coolOrderParam) {
		Record record = new Record(); 
		boolean success = false;
		String supplier = coolOrderParam.getSupplier();
		if(StringUtils.isNotEmpty(supplier)){
			Integer level = coolOrderParam.getLevel();
			if(level != null){
				String[] shopIds = null;
				List<CoonShop> shopList;
				boolean flag = false;
				if(2 == level){ //二级分销
					shopList = coonShopMapper.getCoonShopListByRecommendL2(supplier);
					if(CollectionUtils.isNotEmpty(shopList)){
						shopIds = new String[shopList.size()];
						for(CoonShop shop : shopList){
							int i = 0;
							shopIds[i] = shop.getId();
							i++;
						}
						flag = true;
					}else{
						record.set("msg", "二级合伙人不存在");
					}
				}else if(3 == level) { //三级分销
					shopList = coonShopMapper.getCoonShopListByRecommendL3(supplier);
					if(CollectionUtils.isNotEmpty(shopList)){
						shopIds = new String[shopList.size()];
						for(CoonShop shop : shopList){
							int i = 0;
							shopIds[i] = shop.getId();
							i++;
						}
						flag = true;
					}else{
						record.set("msg", "三级合伙人不存在");
					}
				}else{
					record.set("msg", "参数错误，分销等级不正确");
				}
				if(flag){ //合伙人
					List<CoonShopPartnerInfo> partnerList = coonShopMapper.getCoonShopPartnerListByShopIdsAndDevide(shopIds, level.toString(), supplier);
					if(CollectionUtils.isNotEmpty(partnerList)){
						record.set("partnerList", partnerList);
						success = true;
					}else{
						record.set("msg", "合伙人不存在");
					}
				}
			}else{
				record.set("msg", "参数错误，分销等级不能为空");
			}
		}else{
			record.set("msg", "参数错误，酷店主键不能为空"); 
		}
		record.set("success", success);
		return record;
	}

	/**
     *  获取合伙人奖励明细列表
     * 
     * @param coolOrderParam  参数
     * @return  合伙人奖励明细列表
     */
	@Override
	public Record getShopPartnerDetailList(CoolOrderParam coolOrderParam) {
		Record record = new Record(); 
		boolean success = false;
		String supplier = coolOrderParam.getSupplier();
		if(StringUtils.isNotEmpty(supplier)){
			String partnerId = coolOrderParam.getPartnerId();
			if(StringUtils.isNotEmpty(partnerId)){
				Integer level = coolOrderParam.getLevel();
				if(level != null){
					boolean flag = false;
					if(flag){ //合伙人
						List<CoonShopPartnerInfo> partnerList = coonShopMapper.getCoonShopPartnerDetailList(supplier, partnerId, level.toString());
						if(CollectionUtils.isNotEmpty(partnerList)){
							record.set("partnerList", partnerList);
							success = true;
						}else{
							record.set("msg", "无数据"); 
						}
					}
				}else{
					record.set("msg", "参数错误，分销等级不能为空");
				}
			}else{
				record.set("msg", "参数错误，合伙人店铺主键不能为空");
			}			
		}else{
			record.set("msg", "参数错误，酷店主键不能为空"); 
		}
		record.set("success", success);
		return record;
	}

	/**
     *  获取生意参谋各统计图表
     * 
     * @param param  查询参数
     * @return  统计列表
     */
	@Override
	public Record getBusinessList(CoonShopBusinessParam param) {
		Record record = new Record(); 
		boolean success = false;
		String shopId = param.getShopId();
		if(StringUtils.isNotEmpty(shopId)){
			//TODO 暂时这么处理，后期需要做数据抽取
			String beginTime = "";
			String endTime = "";
			boolean flag = true;
			int index = 0;
			Calendar cal = Calendar.getInstance();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			List<CoonShopBusinessInfo> businessList = new ArrayList<CoonShopBusinessInfo>();
			if(CoonShopBusinessParam.select_Type_1.equals(param.getSelectType())){ //店铺收入
				//账单流水表 shop_id
				if(CoonShopBusinessParam.TIME_TYPE_1.equals(param.getTimeType())){ //本周 周一到周日
					index = 6;					
					for(int i=0;i<=index;i++){
						cal.set(Calendar.DAY_OF_WEEK,Calendar.MONDAY);
						cal.add(Calendar.DATE, i);
						
						beginTime = sdf.format(cal.getTime());
						CoonShopBusinessInfo info = new CoonShopBusinessInfo();
						info.setTimeKey(beginTime);
						info.setValue(coonRunAccountMapper.getSumAmount(beginTime+ " 00:00:00", beginTime + " 23:59:59", shopId));
						businessList.add(info);						
					}
				}else if(CoonShopBusinessParam.TIME_TYPE_2.equals(param.getTimeType())){ //近一周 当天往前的7天
					index = 6;					
					cal.add(Calendar.DATE, -7);
					for(int i=0;i<=index;i++){	
						cal.add(Calendar.DATE, 1);
						beginTime = sdf.format(cal.getTime());
						CoonShopBusinessInfo info = new CoonShopBusinessInfo();
						info.setTimeKey(beginTime);
						info.setValue(coonRunAccountMapper.getSumAmount(beginTime+ " 00:00:00", beginTime + " 23:59:59", shopId));
						businessList.add(info);						
					}
				}else if(CoonShopBusinessParam.TIME_TYPE_3.equals(param.getTimeType())){ //本月 1-7日 8-14日 15-21日 22-28日 28-周末
					index = 4;					
					cal.add(Calendar.MONTH, 0);
			        cal.set(Calendar.DAY_OF_MONTH,1);//设置为1号,当前日期既为本月第一天 
			        cal.add(Calendar.DATE, -1);
					for(int i=0;i<index;i++){	
						cal.add(Calendar.DATE, 1);
						beginTime = sdf.format(cal.getTime());
						if(i != 3){
							cal.add(Calendar.DATE, 6);
							endTime = sdf.format(cal.getTime());
						}else{
							cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH)); 
							endTime = sdf.format(cal.getTime());
						}						
						CoonShopBusinessInfo info = new CoonShopBusinessInfo();
						info.setTimeKey(beginTime);
						info.setValue(coonRunAccountMapper.getSumAmount(beginTime+ " 00:00:00", endTime + " 23:59:59", shopId));
						businessList.add(info);						
					}
				}else if(CoonShopBusinessParam.TIME_TYPE_4.equals(param.getTimeType())){ //近一月　近四周  近三周  近二周  近一周
					index = 4;					 
			        cal.add(Calendar.DATE, -28);
					for(int i=0;i<index;i++){	
						cal.add(Calendar.DATE, 1);
						beginTime = sdf.format(cal.getTime());
						cal.add(Calendar.DATE, 6);
						endTime = sdf.format(cal.getTime());
						CoonShopBusinessInfo info = new CoonShopBusinessInfo();
						info.setTimeKey(beginTime);
						info.setValue(coonRunAccountMapper.getSumAmount(beginTime+ " 00:00:00", endTime + " 23:59:59", shopId));
						businessList.add(info);						
					}
				}else if(CoonShopBusinessParam.TIME_TYPE_5.equals(param.getTimeType())){ //今年　第一季 第二季  第三季  第四季
					index = 4;					 
			        int year = cal.get(Calendar.YEAR);
					for(int i=0;i<index;i++){
						int monthStart = i*3 + 1;
						int monthEnd = i*3+3;
						if(monthStart < 10){
							beginTime = year + "-0" + monthStart + "-01";
						}else{
							beginTime = year + "-" + monthStart + "-01";
						}
						if(monthEnd < 10){
							endTime = year + "-0" + monthEnd + "-31";
						}else{
							endTime = year + "-" + monthEnd + "-31";
						}						
						CoonShopBusinessInfo info = new CoonShopBusinessInfo();
						info.setTimeKey(beginTime);
						info.setValue(coonRunAccountMapper.getSumAmount(beginTime+ " 00:00:00", endTime + " 23:59:59", shopId));
						businessList.add(info);						
					}
				}else if(CoonShopBusinessParam.TIME_TYPE_6.equals(param.getTimeType())){ //近一年
					index = 4;					 
					cal.add(Calendar.MONTH, -12);
					for(int i=0;i<index;i++){
						cal.add(Calendar.MONTH, 1);
						cal.set(Calendar.DAY_OF_MONTH,1);//设置为1号,当前日期既为本月第一天 
						beginTime = sdf.format(cal.getTime());
						cal.add(Calendar.MONTH, 2);
						cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH)); 
						endTime = sdf.format(cal.getTime()); 
						CoonShopBusinessInfo info = new CoonShopBusinessInfo();
						info.setTimeKey(beginTime);
						info.setValue(coonRunAccountMapper.getSumAmount(beginTime+ " 00:00:00", endTime + " 23:59:59", shopId));
						businessList.add(info);						
					}
				}else{
					record.set("msg", "参数错误，时间类型错误");
					flag = false;
				}				
				if(flag){
					record.set("businessList", businessList);
					success = true;
				}
			}else if(CoonShopBusinessParam.select_Type_2.equals(param.getSelectType())){ //店铺订单量
				//订单表 supplier
				if(CoonShopBusinessParam.TIME_TYPE_1.equals(param.getTimeType())){ //本周 周一到周日
					index = 6;					
					for(int i=0;i<=index;i++){
						cal.set(Calendar.DAY_OF_WEEK,Calendar.MONDAY);
						cal.add(Calendar.DATE, i);
						beginTime = sdf.format(cal.getTime());
						CoonShopBusinessInfo info = new CoonShopBusinessInfo();
						info.setTimeKey(beginTime);
						info.setValue(new BigDecimal(coolOrderMapper.getOrderCount(beginTime+ " 00:00:00", endTime + " 23:59:59", shopId)));
						businessList.add(info);						
					}
				}else if(CoonShopBusinessParam.TIME_TYPE_2.equals(param.getTimeType())){ //近一周 当天往前的7天
					index = 6;					
					cal.add(Calendar.DATE, -7);
					for(int i=0;i<=index;i++){	
						cal.add(Calendar.DATE, 1);
						beginTime = sdf.format(cal.getTime());
						CoonShopBusinessInfo info = new CoonShopBusinessInfo();
						info.setTimeKey(beginTime);
						info.setValue(new BigDecimal(coolOrderMapper.getOrderCount(beginTime+ " 00:00:00", endTime + " 23:59:59", shopId)));
						businessList.add(info);						
					}
				}else if(CoonShopBusinessParam.TIME_TYPE_3.equals(param.getTimeType())){ //本月 1-7日 8-14日 15-21日 22-28日 28-周末
					index = 4;					
					cal.add(Calendar.MONTH, 0);
			        cal.set(Calendar.DAY_OF_MONTH,1);//设置为1号,当前日期既为本月第一天 
			        cal.add(Calendar.DATE, -1);
					for(int i=0;i<index;i++){	
						cal.add(Calendar.DATE, 1);
						beginTime = sdf.format(cal.getTime());
						if(i != 3){
							cal.add(Calendar.DATE, 6);
							endTime = sdf.format(cal.getTime());
						}else{
							cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH)); 
							endTime = sdf.format(cal.getTime());
						}						
						CoonShopBusinessInfo info = new CoonShopBusinessInfo();
						info.setTimeKey(beginTime);
						info.setValue(new BigDecimal(coolOrderMapper.getOrderCount(beginTime+ " 00:00:00", endTime + " 23:59:59", shopId)));
						businessList.add(info);						
					}
				}else if(CoonShopBusinessParam.TIME_TYPE_4.equals(param.getTimeType())){ //近一月　近四周  近三周  近二周  近一周
					index = 4;					 
			        cal.add(Calendar.DATE, -28);
					for(int i=0;i<index;i++){	
						cal.add(Calendar.DATE, 1);
						beginTime = sdf.format(cal.getTime());
						cal.add(Calendar.DATE, 6);
						endTime = sdf.format(cal.getTime());
						CoonShopBusinessInfo info = new CoonShopBusinessInfo();
						info.setTimeKey(beginTime);
						info.setValue(new BigDecimal(coolOrderMapper.getOrderCount(beginTime+ " 00:00:00", endTime + " 23:59:59", shopId)));
						businessList.add(info);						
					}
				}else if(CoonShopBusinessParam.TIME_TYPE_5.equals(param.getTimeType())){ //今年　第一季 第二季  第三季  第四季
					index = 4;					 
			        int year = cal.get(Calendar.YEAR);
					for(int i=0;i<index;i++){
						int monthStart = i*3 + 1;
						int monthEnd = i*3+3;
						if(monthStart < 10){
							beginTime = year + "-0" + monthStart + "-01";
						}else{
							beginTime = year + "-" + monthStart + "-01";
						}
						if(monthEnd < 10){
							endTime = year + "-0" + monthEnd + "-31";
						}else{
							endTime = year + "-" + monthEnd + "-31";
						}						
						CoonShopBusinessInfo info = new CoonShopBusinessInfo();
						info.setTimeKey(beginTime);
						info.setValue(new BigDecimal(coolOrderMapper.getOrderCount(beginTime+ " 00:00:00", endTime + " 23:59:59", shopId)));
						businessList.add(info);						
					}
				}else if(CoonShopBusinessParam.TIME_TYPE_6.equals(param.getTimeType())){ //近一年
					index = 4;					 
					cal.add(Calendar.MONTH, -12);
					for(int i=0;i<index;i++){
						cal.add(Calendar.MONTH, 1);
						cal.set(Calendar.DAY_OF_MONTH,1);//设置为1号,当前日期既为本月第一天 
						beginTime = sdf.format(cal.getTime());
						cal.add(Calendar.MONTH, 2);
						cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH)); 
						endTime = sdf.format(cal.getTime()); 
						CoonShopBusinessInfo info = new CoonShopBusinessInfo();
						info.setTimeKey(beginTime);
						info.setValue(new BigDecimal(coolOrderMapper.getOrderCount(beginTime+ " 00:00:00", endTime + " 23:59:59", shopId)));
						businessList.add(info);						
					}
				}else{
					record.set("msg", "参数错误，时间类型错误");
					flag = false;
				}				
				if(flag){
					record.set("businessList", businessList);
					success = true;
				}
			}else if(CoonShopBusinessParam.select_Type_3.equals(param.getSelectType())){ //店铺交易额
				//订单表 supplier
				if(CoonShopBusinessParam.TIME_TYPE_1.equals(param.getTimeType())){ //本周 周一到周日
					index = 6;					
					for(int i=0;i<=index;i++){
						cal.set(Calendar.DAY_OF_WEEK,Calendar.MONDAY);
						cal.add(Calendar.DATE, i);
						beginTime = sdf.format(cal.getTime());
						CoonShopBusinessInfo info = new CoonShopBusinessInfo();
						info.setTimeKey(beginTime);
						info.setValue(coolOrderMapper.getSumAmount(beginTime+ " 00:00:00", beginTime + " 23:59:59", shopId));
						businessList.add(info);						
					}
				}else if(CoonShopBusinessParam.TIME_TYPE_2.equals(param.getTimeType())){ //近一周 当天往前的7天
					index = 6;					
					cal.add(Calendar.DATE, -7);
					for(int i=0;i<=index;i++){	
						cal.add(Calendar.DATE, 1);
						beginTime = sdf.format(cal.getTime());
						CoonShopBusinessInfo info = new CoonShopBusinessInfo();
						info.setTimeKey(beginTime);
						info.setValue(coolOrderMapper.getSumAmount(beginTime+ " 00:00:00", beginTime + " 23:59:59", shopId));
						businessList.add(info);						
					}
				}else if(CoonShopBusinessParam.TIME_TYPE_3.equals(param.getTimeType())){ //本月 1-7日 8-14日 15-21日 22-28日 28-周末
					index = 4;					
					cal.add(Calendar.MONTH, 0);
			        cal.set(Calendar.DAY_OF_MONTH,1);//设置为1号,当前日期既为本月第一天 
			        cal.add(Calendar.DATE, -1);
					for(int i=0;i<index;i++){	
						cal.add(Calendar.DATE, 1);
						beginTime = sdf.format(cal.getTime());
						if(i != 3){
							cal.add(Calendar.DATE, 6);
							endTime = sdf.format(cal.getTime());
						}else{
							cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH)); 
							endTime = sdf.format(cal.getTime());
						}						
						CoonShopBusinessInfo info = new CoonShopBusinessInfo();
						info.setTimeKey(beginTime);
						info.setValue(coolOrderMapper.getSumAmount(beginTime+ " 00:00:00", endTime + " 23:59:59", shopId));
						businessList.add(info);						
					}
				}else if(CoonShopBusinessParam.TIME_TYPE_4.equals(param.getTimeType())){ //近一月　近四周  近三周  近二周  近一周
					index = 4;					 
			        cal.add(Calendar.DATE, -28);
					for(int i=0;i<index;i++){	
						cal.add(Calendar.DATE, 1);
						beginTime = sdf.format(cal.getTime());
						cal.add(Calendar.DATE, 6);
						endTime = sdf.format(cal.getTime());
						CoonShopBusinessInfo info = new CoonShopBusinessInfo();
						info.setTimeKey(beginTime);
						info.setValue(coolOrderMapper.getSumAmount(beginTime+ " 00:00:00", endTime + " 23:59:59", shopId));
						businessList.add(info);						
					}
				}else if(CoonShopBusinessParam.TIME_TYPE_5.equals(param.getTimeType())){ //今年　第一季 第二季  第三季  第四季
					index = 4;					 
			        int year = cal.get(Calendar.YEAR);
					for(int i=0;i<index;i++){
						int monthStart = i*3 + 1;
						int monthEnd = i*3+3;
						if(monthStart < 10){
							beginTime = year + "-0" + monthStart + "-01";
						}else{
							beginTime = year + "-" + monthStart + "-01";
						}
						if(monthEnd < 10){
							endTime = year + "-0" + monthEnd + "-31";
						}else{
							endTime = year + "-" + monthEnd + "-31";
						}						
						CoonShopBusinessInfo info = new CoonShopBusinessInfo();
						info.setTimeKey(beginTime);
						info.setValue(coolOrderMapper.getSumAmount(beginTime+ " 00:00:00", endTime + " 23:59:59", shopId));
						businessList.add(info);						
					}
				}else if(CoonShopBusinessParam.TIME_TYPE_6.equals(param.getTimeType())){ //近一年
					index = 4;					 
					cal.add(Calendar.MONTH, -12);
					for(int i=0;i<index;i++){
						cal.add(Calendar.MONTH, 1);
						cal.set(Calendar.DAY_OF_MONTH,1);//设置为1号,当前日期既为本月第一天 
						beginTime = sdf.format(cal.getTime());
						cal.add(Calendar.MONTH, 2);
						cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH)); 
						endTime = sdf.format(cal.getTime()); 
						CoonShopBusinessInfo info = new CoonShopBusinessInfo();
						info.setTimeKey(beginTime);
						info.setValue(coolOrderMapper.getSumAmount(beginTime+ " 00:00:00", endTime + " 23:59:59", shopId));
						businessList.add(info);						
					}
				}else{
					record.set("msg", "参数错误，时间类型错误");
					flag = false;
				}				
				if(flag){
					record.set("businessList", businessList);
					success = true;
				}
			}else if(CoonShopBusinessParam.select_Type_4.equals(param.getSelectType())){ //店铺访客数
				//coon_shop_click
				if(CoonShopBusinessParam.TIME_TYPE_1.equals(param.getTimeType())){ //本周 周一到周日
					index = 6;					
					for(int i=0;i<=index;i++){
						cal.set(Calendar.DAY_OF_WEEK,Calendar.MONDAY);
						cal.add(Calendar.DATE, i);
						
						beginTime = sdf.format(cal.getTime());
						CoonShopBusinessInfo info = new CoonShopBusinessInfo();
						info.setTimeKey(beginTime);
						info.setValue(new BigDecimal(coonShopClickMapper.getCountUV(beginTime+ " 00:00:00", beginTime + " 23:59:59", shopId)));
						businessList.add(info);						
					}
				}else if(CoonShopBusinessParam.TIME_TYPE_2.equals(param.getTimeType())){ //近一周 当天往前的7天
					index = 6;					
					cal.add(Calendar.DATE, -7);
					for(int i=0;i<=index;i++){	
						cal.add(Calendar.DATE, 1);
						beginTime = sdf.format(cal.getTime());
						CoonShopBusinessInfo info = new CoonShopBusinessInfo();
						info.setTimeKey(beginTime);
						info.setValue(new BigDecimal(coonShopClickMapper.getCountUV(beginTime+ " 00:00:00", beginTime + " 23:59:59", shopId)));
						businessList.add(info);						
					}
				}else if(CoonShopBusinessParam.TIME_TYPE_3.equals(param.getTimeType())){ //本月 1-7日 8-14日 15-21日 22-28日 28-周末
					index = 4;					
					cal.add(Calendar.MONTH, 0);
			        cal.set(Calendar.DAY_OF_MONTH,1);//设置为1号,当前日期既为本月第一天 
			        cal.add(Calendar.DATE, -1);
					for(int i=0;i<index;i++){	
						cal.add(Calendar.DATE, 1);
						beginTime = sdf.format(cal.getTime());
						if(i != 3){
							cal.add(Calendar.DATE, 6);
							endTime = sdf.format(cal.getTime());
						}else{
							cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH)); 
							endTime = sdf.format(cal.getTime());
						}						
						CoonShopBusinessInfo info = new CoonShopBusinessInfo();
						info.setTimeKey(beginTime);
						info.setValue(new BigDecimal(coonShopClickMapper.getCountUV(beginTime+ " 00:00:00", endTime + " 23:59:59", shopId)));
						businessList.add(info);						
					}
				}else if(CoonShopBusinessParam.TIME_TYPE_4.equals(param.getTimeType())){ //近一月　近四周  近三周  近二周  近一周
					index = 4;					 
			        cal.add(Calendar.DATE, -28);
					for(int i=0;i<index;i++){	
						cal.add(Calendar.DATE, 1);
						beginTime = sdf.format(cal.getTime());
						cal.add(Calendar.DATE, 6);
						endTime = sdf.format(cal.getTime());
						CoonShopBusinessInfo info = new CoonShopBusinessInfo();
						info.setTimeKey(beginTime);
						info.setValue(new BigDecimal(coonShopClickMapper.getCountUV(beginTime+ " 00:00:00", endTime + " 23:59:59", shopId)));
						businessList.add(info);						
					}
				}else if(CoonShopBusinessParam.TIME_TYPE_5.equals(param.getTimeType())){ //今年　第一季 第二季  第三季  第四季
					index = 4;					 
			        int year = cal.get(Calendar.YEAR);
					for(int i=0;i<index;i++){
						int monthStart = i*3 + 1;
						int monthEnd = i*3+3;
						if(monthStart < 10){
							beginTime = year + "-0" + monthStart + "-01";
						}else{
							beginTime = year + "-" + monthStart + "-01";
						}
						if(monthEnd < 10){
							endTime = year + "-0" + monthEnd + "-31";
						}else{
							endTime = year + "-" + monthEnd + "-31";
						}						
						CoonShopBusinessInfo info = new CoonShopBusinessInfo();
						info.setTimeKey(beginTime);
						info.setValue(new BigDecimal(coonShopClickMapper.getCountUV(beginTime+ " 00:00:00", endTime + " 23:59:59", shopId)));
						businessList.add(info);						
					}
				}else if(CoonShopBusinessParam.TIME_TYPE_6.equals(param.getTimeType())){ //近一年
					index = 4;					 
					cal.add(Calendar.MONTH, -12);
					for(int i=0;i<index;i++){
						cal.add(Calendar.MONTH, 1);
						cal.set(Calendar.DAY_OF_MONTH,1);//设置为1号,当前日期既为本月第一天 
						beginTime = sdf.format(cal.getTime());
						cal.add(Calendar.MONTH, 2);
						cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH)); 
						endTime = sdf.format(cal.getTime()); 
						CoonShopBusinessInfo info = new CoonShopBusinessInfo();
						info.setTimeKey(beginTime);
						info.setValue(new BigDecimal(coonShopClickMapper.getCountUV(beginTime+ " 00:00:00", endTime + " 23:59:59", shopId)));
						businessList.add(info);						
					}
				}else{
					record.set("msg", "参数错误，时间类型错误");
					flag = false;
				}				
				if(flag){
					record.set("businessList", businessList);
					success = true;
				}
			}else if(CoonShopBusinessParam.select_Type_5.equals(param.getSelectType())){ //店铺浏览量
				//coon_shop_click
				if(CoonShopBusinessParam.TIME_TYPE_1.equals(param.getTimeType())){ //本周 周一到周日
					index = 6;					
					for(int i=0;i<=index;i++){
						cal.set(Calendar.DAY_OF_WEEK,Calendar.MONDAY);
						cal.add(Calendar.DATE, i);
						
						beginTime = sdf.format(cal.getTime());
						CoonShopBusinessInfo info = new CoonShopBusinessInfo();
						info.setTimeKey(beginTime);
						info.setValue(new BigDecimal(coonShopClickMapper.getCountPV(beginTime+ " 00:00:00", beginTime + " 23:59:59", shopId)));
						businessList.add(info);						
					}
				}else if(CoonShopBusinessParam.TIME_TYPE_2.equals(param.getTimeType())){ //近一周 当天往前的7天
					index = 6;					
					cal.add(Calendar.DATE, -7);
					for(int i=0;i<=index;i++){	
						cal.add(Calendar.DATE, 1);
						beginTime = sdf.format(cal.getTime());
						CoonShopBusinessInfo info = new CoonShopBusinessInfo();
						info.setTimeKey(beginTime);
						info.setValue(new BigDecimal(coonShopClickMapper.getCountPV(beginTime+ " 00:00:00", beginTime + " 23:59:59", shopId)));
						businessList.add(info);						
					}
				}else if(CoonShopBusinessParam.TIME_TYPE_3.equals(param.getTimeType())){ //本月 1-7日 8-14日 15-21日 22-28日 28-周末
					index = 4;					
					cal.add(Calendar.MONTH, 0);
			        cal.set(Calendar.DAY_OF_MONTH,1);//设置为1号,当前日期既为本月第一天 
			        cal.add(Calendar.DATE, -1);
					for(int i=0;i<index;i++){	
						cal.add(Calendar.DATE, 1);
						beginTime = sdf.format(cal.getTime());
						if(i != 3){
							cal.add(Calendar.DATE, 6);
							endTime = sdf.format(cal.getTime());
						}else{
							cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH)); 
							endTime = sdf.format(cal.getTime());
						}						
						CoonShopBusinessInfo info = new CoonShopBusinessInfo();
						info.setTimeKey(beginTime);
						info.setValue(new BigDecimal(coonShopClickMapper.getCountPV(beginTime+ " 00:00:00", endTime + " 23:59:59", shopId)));
						businessList.add(info);						
					}
				}else if(CoonShopBusinessParam.TIME_TYPE_4.equals(param.getTimeType())){ //近一月　近四周  近三周  近二周  近一周
					index = 4;					 
			        cal.add(Calendar.DATE, -28);
					for(int i=0;i<index;i++){	
						cal.add(Calendar.DATE, 1);
						beginTime = sdf.format(cal.getTime());
						cal.add(Calendar.DATE, 6);
						endTime = sdf.format(cal.getTime());
						CoonShopBusinessInfo info = new CoonShopBusinessInfo();
						info.setTimeKey(beginTime);
						info.setValue(new BigDecimal(coonShopClickMapper.getCountPV(beginTime+ " 00:00:00", endTime + " 23:59:59", shopId)));
						businessList.add(info);						
					}
				}else if(CoonShopBusinessParam.TIME_TYPE_5.equals(param.getTimeType())){ //今年　第一季 第二季  第三季  第四季
					index = 4;					 
			        int year = cal.get(Calendar.YEAR);
					for(int i=0;i<index;i++){
						int monthStart = i*3 + 1;
						int monthEnd = i*3+3;
						if(monthStart < 10){
							beginTime = year + "-0" + monthStart + "-01";
						}else{
							beginTime = year + "-" + monthStart + "-01";
						}
						if(monthEnd < 10){
							endTime = year + "-0" + monthEnd + "-31";
						}else{
							endTime = year + "-" + monthEnd + "-31";
						}						
						CoonShopBusinessInfo info = new CoonShopBusinessInfo();
						info.setTimeKey(beginTime);
						info.setValue(new BigDecimal(coonShopClickMapper.getCountPV(beginTime+ " 00:00:00", endTime + " 23:59:59", shopId)));
						businessList.add(info);						
					}
				}else if(CoonShopBusinessParam.TIME_TYPE_6.equals(param.getTimeType())){ //近一年
					index = 4;					 
					cal.add(Calendar.MONTH, -12);
					for(int i=0;i<index;i++){
						cal.add(Calendar.MONTH, 1);
						cal.set(Calendar.DAY_OF_MONTH,1);//设置为1号,当前日期既为本月第一天 
						beginTime = sdf.format(cal.getTime());
						cal.add(Calendar.MONTH, 2);
						cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH)); 
						endTime = sdf.format(cal.getTime()); 
						CoonShopBusinessInfo info = new CoonShopBusinessInfo();
						info.setTimeKey(beginTime);
						info.setValue(new BigDecimal(coonShopClickMapper.getCountPV(beginTime+ " 00:00:00", endTime + " 23:59:59", shopId)));
						businessList.add(info);						
					}
				}else{
					record.set("msg", "参数错误，时间类型错误");
					flag = false;
				}				
				if(flag){
					record.set("businessList", businessList);
					success = true;
				}
			}else{
				record.set("msg", "参数错误，查询类型错误");
			}			
		}else{
			record.set("msg", "参数错误，酷店主键不能为空");
		}
		record.set("success", success);
		return record;
	}

	/**
     *  保存收藏店铺
     * 
     * @param coonShopFav  参数
     * @return  是否保存成功
     */
	@Override
	public Record saveShopFav(CoonShopFav coonShopFav) {
		Record record = new Record(); 
		boolean success = false;
		String mId = coonShopFav.getmId();		
		if(StringUtils.isNotEmpty(mId)){
			String sId = coonShopFav.getsId();
			if(StringUtils.isNotEmpty(sId)){
				List<CoonShopFav> list = coonShopFavMapper.findCoonShopFavByIdAndSId(mId, sId);
				if(CollectionUtils.isEmpty(list)){
					coonShopFav.setId(CommonUtils.uuid());
					coonShopFav.setCreatetime(new Date());
					int result = coonShopFavMapper.saveCoonShopFav(coonShopFav);
					if(result > 0){
						success = true;
					}else{
						record.set("msg", "保存酷店收藏失败");
					}
				}else{
					record.set("msg", "该会员已收藏过该酷店");
				}
			}else{
				record.set("msg", "参数错误，酷店	主键不能为空");
			}
		}else{
			record.set("msg", "参数错误，会员主键不能为空");
		}		
		record.set("success", success);
		return record;
	}

	/**
     *  获取店铺及推荐商品列表
     * 
     * @param coonShop  参数
     * @return  coonShop
     */
	@Override
	public Record saveShopFav(CoonShop coonShop) {
		Record record = new Record(); 
		boolean success = false;	
		List<CoonShop> list = coonShopMapper.getCoonShopListPage(coonShop.getRowBounds(), coonShop);
		if(CollectionUtils.isNotEmpty(list)){
			List<CoonShop> shopList = new ArrayList<CoonShop>();
			for(CoonShop shop : list){
				//获取酷店商品表
				CoonShopProductParam coonShopProductParam = new CoonShopProductParam();
				coonShopProductParam.setShowCount(5);		
				coonShopProductParam.setsId(shop.getId());
				List<CoonShopProductInfo> infoList = coonShopProductMapper.getHotSellProductList(coonShopProductParam);
				shop.setProductList(infoList);
				shopList.add(shop);
			}
			record.set("shopList", shopList);
			success = true;
		}else{
			record.set("msg", "无数据");
		}
		record.set("success", success);
		return record;
	}

	/**
     *  获取店铺热销商品列表
     * 
     * @param coonShopProductParam  参数
     * @return  coonShop
     */
	@Override
	public Record getHotSellProductList(CoonShopProductParam coonShopProductParam) {
		Record record = new Record(); 
		boolean success = false;
		String sId = coonShopProductParam.getsId();
		if(StringUtils.isNotEmpty(sId)){
			coonShopProductParam.setShowCount(5);		
			List<CoonShopProductInfo> list = coonShopProductMapper.getHotSellProductList(coonShopProductParam);
			if(CollectionUtils.isNotEmpty(list)){
				List<CoonShopProductInfo> productList = new ArrayList<CoonShopProductInfo>();
				for(CoonShopProductInfo info : list){
					//处理价格、上架酷店数、是否在该店铺上架、评价数
					if(new BigDecimal(0).compareTo(info.getCxjg()) == 0){
						info.setPrice(info.getJg());
					}else{
						info.setPrice(info.getCxjg());
					}
					int count = coonShopProductMapper.getCoonShopProductAddedCountByGoodsId(info.getGoodsId().toString());
					info.setPutSalesNum(count);
					int added = coonShopProductMapper.getCoonShopProductAddedCountByGoodsIdAndShopId(info.getGoodsId().toString(), sId);
					if(added > 0){
						info.setAdded(true);
					}else{
						info.setAdded(false);
					}					
					CoolProductReview coolProductReview = new CoolProductReview();
					coolProductReview.setgId(info.getGoodsId());
					coolProductReview.setGgId(info.getId());
					int countReview = coolProductReviewMapper.getCountReview(coolProductReview);
					info.setReviews(countReview);
					productList.add(info);
				}
				record.set("productList", productList);
				success = true;
			}else{
				record.set("msg", "无数据");
			}
		}else{
			record.set("msg", "参数错误，酷店主键不能为空");
		}
		record.set("success", success);
		return record;
	}

	/**
     * 获取体验店列表
     *
     * @return 体验店列表
     */
	@Override
	public Record getExperienceShopList() {
		Record record = new Record(); 
		boolean success = false;	
		//List<CoonShopExperience> list = coonShopExperienceMapper.findAllCoonShopExperience();
		List<CoonShop> list = coonShopMapper.findAllCoonShopExperience();
		if(CollectionUtils.isNotEmpty(list)){
			record.set("experienceShopList", list);
			success = true;
		}else{
			record.set("msg", "无数据");
		}
		record.set("success", success);
		return record;
	}

	/**
     *  保存酷店点击信息
     * 
     * @param coonShopClick  参数
     * @return  是否保存成功
     */
	@Override
	public Record saveCoonShopClick(CoonShopClick coonShopClick) {
		Record record = new Record(); 
		boolean success = false;	
		String sId = coonShopClick.getSid();
		if(StringUtils.isNotEmpty(sId)){
			String ip = coonShopClick.getIp();
			if(StringUtils.isNotEmpty(ip)){
				String url = coonShopClick.getUrl();
				if(StringUtils.isNotEmpty(url)){
					int result = coonShopClickMapper.saveCoonShopClick(coonShopClick);
					if(result > 0){
						success = true;
					}else{
						record.set("msg", "酷店点击信息保存失败");
					}
				}else{
					record.set("msg", "参数错误，url不能为空");
				}
			}else{
				record.set("msg", "参数错误，ip不能为空");
			}
		}else{
			record.set("msg", "参数错误，酷店主键不能为空");
		}
		record.set("success", success);
		return record;
	}

	 /**
     * 获取分享信息
     * 
     * @param CoonShopProductParam 分页信息
     * @return 酷店分享信息
     */
	@Override
	public Record getShareList(CoonShopProductParam coonShopProductParam) {
		Record record = new Record(); 
		boolean success = false;	
		List<CoonShopShare> shareList = coonShopShareMapper.findCoonShopShareList(coonShopProductParam.getRowBounds(), new CoonShopShare());
		if(CollectionUtils.isNotEmpty(shareList)){
			record.set("shareList", shareList);
			success = true;
		}else{
			record.set("msg", "无数据");
		}
		record.set("success", success);
		return record;
	}
    
}
