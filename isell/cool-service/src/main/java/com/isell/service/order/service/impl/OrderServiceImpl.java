package com.isell.service.order.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.isell.core.common.StatisticsPo;
import com.isell.core.config.BisConfig;
import com.isell.core.mybatis.page.PageInfo;
import com.isell.core.pojo.OrderFiling;
import com.isell.core.pojo.OrderFilingDetail;
import com.isell.core.util.Coder;
import com.isell.core.util.CommonUtils;
import com.isell.core.util.DateUtil;
import com.isell.core.util.GetBarCode;
import com.isell.core.util.HttpUtils;
import com.isell.core.util.JaxbUtil;
import com.isell.core.util.JsonData;
import com.isell.core.util.JsonUtil;
import com.isell.core.util.MessageUtil;
import com.isell.core.util.Record;
import com.isell.core.util.SMSUtil;
import com.isell.ei.pay.ehking.bean.EhkingCustomsInfo;
import com.isell.ei.pay.ehking.bean.EhkingCustomsPayer;
import com.isell.ei.pay.ehking.bean.EhkingCustomsRequest;
import com.isell.ei.pay.zjys.service.ZjysService;
import com.isell.ei.pay.zjys.vo.ZjysBuyTradeNoRes;
import com.isell.ei.pay.zjys.vo.ZjysOrderInfo;
import com.isell.log.SendMessageLogUtil;
import com.isell.service.account.dao.CoonRunAccountMapper;
import com.isell.service.account.vo.CoonRunAccount;
import com.isell.service.alipay.dao.CoolAlipayMapper;
import com.isell.service.code.dao.CoolConfigMapper;
import com.isell.service.code.vo.CoolConfig;
import com.isell.service.common.GeneralDef;
import com.isell.service.common.po.AddressInfo;
import com.isell.service.common.po.ItemInfo;
import com.isell.service.common.po.MultipleItems;
import com.isell.service.common.po.MultipleRequest;
import com.isell.service.common.po.OrderRequest;
import com.isell.service.custorms.po.CoolProductCustomsNbyb;
import com.isell.service.custorms.po.CoolProductCustomsZz;
import com.isell.service.custorms.service.CoolProductCustomsNbybService;
import com.isell.service.custorms.service.CoolProductCustomsZzService;
import com.isell.service.fare.dao.CoonFareTempProMapper;
import com.isell.service.fare.vo.CoonFareTempPro;
import com.isell.service.member.dao.CoolMemberMapper;
import com.isell.service.member.dao.CoolMemberReceiverMapper;
import com.isell.service.member.dao.CoolUserMapper;
import com.isell.service.member.vo.CoolMember;
import com.isell.service.member.vo.CoolMemberReceiver;
import com.isell.service.message.dao.CoolMessageMapper;
import com.isell.service.message.vo.CoolMessage;
import com.isell.service.order.dao.CoolDistributionCarMapper;
import com.isell.service.order.dao.CoolOrderItemMapper;
import com.isell.service.order.dao.CoolOrderMapper;
import com.isell.service.order.dao.CoolShopPushMapper;
import com.isell.service.order.dao.CoolStockDepotMapper;
import com.isell.service.order.dao.CoonFareTempProByGetFareMapper;
import com.isell.service.order.dao.CoonShopcartMapper;
import com.isell.service.order.po.CoolDistributionCarInfo;
import com.isell.service.order.po.CoolOrderExternal;
import com.isell.service.order.po.CoolOrderParam;
import com.isell.service.order.po.CoolOrderSelect;
import com.isell.service.order.po.CoolOrderWayBill;
import com.isell.service.order.po.CoolOrderWayBillReturn;
import com.isell.service.order.po.CoolProductSales;
import com.isell.service.order.po.CoonShopCartInfo;
import com.isell.service.order.po.CoonShopCartParam;
import com.isell.service.order.po.MultipleResponse;
import com.isell.service.order.po.SendPersonalResponse;
import com.isell.service.order.po.SendPresonalInfo;
import com.isell.service.order.po.SendPresonalItems;
import com.isell.service.order.service.OrderService;
import com.isell.service.order.util.OrderUtil;
import com.isell.service.order.vo.CoolDistributionCar;
import com.isell.service.order.vo.CoolOrder;
import com.isell.service.order.vo.CoolOrderItem;
import com.isell.service.order.vo.CoolShopPush;
import com.isell.service.order.vo.CoolStockDepot;
import com.isell.service.order.vo.CoonFareTempProByGetFare;
import com.isell.service.order.vo.CoonShopcart;
import com.isell.service.order.vo.HzCoolOrderItem;
import com.isell.service.order.vo.OrderReturn;
import com.isell.service.payinfo.dao.CoolSendPayinfoMapper;
import com.isell.service.payinfo.vo.CoolSendPayinfo;
import com.isell.service.product.dao.CoolProductGgMapper;
import com.isell.service.product.dao.CoolProductGroupMapper;
import com.isell.service.product.dao.CoolProductMapMapper;
import com.isell.service.product.dao.CoolProductMapper;
import com.isell.service.product.dao.CoolProductReviewMapper;
import com.isell.service.product.po.CoolProductInfo;
import com.isell.service.product.vo.CoolProduct;
import com.isell.service.product.vo.CoolProductGg;
import com.isell.service.product.vo.CoolProductGroup;
import com.isell.service.product.vo.CoolProductMap;
import com.isell.service.product.vo.CoolProductReview;
import com.isell.service.shop.dao.CoonShopLevelMapper;
import com.isell.service.shop.dao.CoonShopMapper;
import com.isell.service.shop.dao.CoonShopPartnerMapper;
import com.isell.service.shop.dao.CoonShopProductMapper;
import com.isell.service.shop.vo.CoonShop;
import com.isell.service.shop.vo.CoonShopLevel;
import com.isell.service.shop.vo.CoonShopPartner;
import com.isell.service.shop.vo.CoonShopProduct;
import com.isell.ws.WebServiceClient;
import com.isell.ws.hangzhou.service.impl.CrossBorderServiceImpl;
import com.isell.ws.ningbo.bean.Detail;
import com.isell.ws.ningbo.bean.Order;
import com.isell.ws.ningbo.bean.OrderMsg;
import com.isell.ws.ningbo.bean.OrderSearchResult;
import com.isell.ws.ningbo.bean.UserReg;
import com.isell.ws.ningbo.service.YoubeiService;
import com.isell.ws.ningbo.ws.order.KJB2CWebService;
import com.isell.ws.zhengzhou.bean.Bodydetail;
import com.isell.ws.zhengzhou.bean.Bodymaster;
import com.isell.ws.zhengzhou.bean.Cbecmessage;
import com.isell.ws.zhengzhou.bean.Messagebody;
import com.isell.ws.zhengzhou.bean.Messagehead;
import com.isell.ws.zhengzhou.bean.OrderInfo;

/**
 * 订单服务接口实现类
 * 
 * @author lilin
 * @version [版本号, 2015年9月24日]
 */
@Service("orderService")
public class OrderServiceImpl implements OrderService {
    
    /**
     * 郑州商品海关备案业务层接口
     */
    @Resource
    private CoolProductCustomsZzService coolProductCustomsZzService;
    
    /**
     * 宁波优贝商品海关备案业务层接口
     */
    @Resource
    private CoolProductCustomsNbybService coolProductCustomsNbybService;
    
    /**
     * 订单查询mapper
     */
    @Resource
    private CoolOrderMapper coolOrderMapper;
    
    /**
     * 订单详情Mapper
     */
    @Resource
    private CoolOrderItemMapper coolOrderItemMapper;
    
    /**
     * 商品mapper
     */
    @Resource
    private CoolProductMapper coolProductMapper;
    
    /**
     * 商品规格mapper
     */
    @Resource
    private CoolProductGgMapper coolProductGgMapper;
    
    /**
     * 快递模板明细mapper
     */
    @Resource
    private CoonFareTempProMapper coonFareTempProMapper;
    
    /**
     * 会员收货信息mapper
     */
    @Resource
    private CoolMemberReceiverMapper coolMemberReceiverMapper;
    
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
     * 酷店表mapper
     */
    @Resource
    private CoonShopMapper coonShopMapper;
    
    /**
     * 酷店商品表mapper
     */
    @Resource
    private CoonShopProductMapper coonShopProductMapper;
    
    /**
     * 酷店等级表mapper
     */
    @Resource
    private CoonShopLevelMapper coonShopLevelMapper;
    
    /**
     * 佣金流水记录表mapper
     */
    @Resource
    private CoonRunAccountMapper coonRunAccountMapper;
    
    /**
     * 支付宝信息mapper
     */
    @Resource
    private CoolAlipayMapper coolAlipayMapper;
    
    /**
     * 酷店合伙人佣金记录mapper
     */
    @Resource
    private CoonShopPartnerMapper coonShopPartnerMapper;
    
    @Resource
    private CoolMessageMapper messageMapper;
    
    @Resource
    private CoolConfigMapper coolConfigMapper;
    
    /**
     * 商品映射表Mapper
     */
    @Resource
    private CoolProductMapMapper coolProductMapMapper;
    
    /**
     * 一件代发进货单表
     */
    @Resource
    private CoolDistributionCarMapper coolDistributionCarMapper;
    
    /**
     * 商品评价表mapper
     */
    @Resource
    private CoolProductReviewMapper coolProductReviewMapper;
    
    /**
     * 购物车表mapper
     */
    @Resource
    private CoonShopcartMapper coonShopcartMapper;
    
    /**
     * 商品组mapper
     */
    @Resource
    private CoolProductGroupMapper coolProductGroupMapper;
    
    /**
     * 支付单报关信息Mapper
     */
    @Resource
    private CoolSendPayinfoMapper coolSendPayinfoMapper;
    /**
     * 订单推送
     */
    @Resource 
    private CoolShopPushMapper coolShopPushMapper;
    
    /**
     * 浙江银商支付服务层接口
     */
    @Resource
    private ZjysService zjysService;
    
    /**
     * 配置信息
     */
    @Resource
    private BisConfig config;
    
    /**
     * coolJdbcTemplate
     */
    @Resource
    private JdbcTemplate jdbcTemplate;
    
    /**
     * 获取运费模板
     */
    @Resource
    private CoonFareTempProByGetFareMapper coonFareTempProByGetFareMapper;
    
    /**
     * 获取仓库信息
     */
    @Resource
    private CoolStockDepotMapper coolStockDepotMapper;
    
    @Override
    public CoolOrder getCoolOrderById(Integer id) {
        return coolOrderMapper.getCoolOrderById(id);
    }
    
    @Override
    public CoolOrder getCoolOrderByOrderNo(String orderNo) {
        return coolOrderMapper.getCoolOrderByOrderNo(orderNo);
    }
    
    @Override
    public CoolOrder getCoolOrderDetailById(Integer id) {
        CoolOrder order = getCoolOrderById(id);
        if (order != null) {
            order.setItemList(coolOrderItemMapper.findCoolOrderItemByOrderNo(order.getOrderNo()));
        }
        return order;
    }
    
    @Override
    public CoolOrder getCoolOrderDetailByOrderNo(String orderNo) {
        CoolOrder order = getCoolOrderByOrderNo(orderNo);
        if (order != null) {
            order.setItemList(coolOrderItemMapper.findCoolOrderItemByOrderNo(order.getOrderNo()));
        }
        return order;
    }
    
    @Override
    public CoolOrder getCoolOrderDetailByPsCode(String psCode) {
        CoolOrder order = coolOrderMapper.getCoolOrderByPsCode(psCode);
        if (order != null) {
            order.setItemList(coolOrderItemMapper.findCoolOrderItemByOrderNo(order.getOrderNo()));
        }
        return order;
    }
    
    @Override
    public void cancelOrder(Integer... ids) {
        if (ids == null) {
            throw new RuntimeException("exception.order.null");
        }
        CoolOrder param = new CoolOrder();
        param.setState(CoolOrder.ORDER_STATE_99);
        for (int id : ids) {
            param.setId(id);
            // 更新订单状态
            // coolOrderMapper.updateCoolOrder(param);
            CoolOrder order = coolOrderMapper.getCoolOrderById(id);
            if (order.getIsBatch() != null && order.getIsBatch() == 1) { // 批量操作不设自动取消
            } else {
                coolOrderMapper.updateCoolOrder(param);
            }
            // 减销量加库存
            List<CoolOrderItem> itemList = coolOrderItemMapper.findCoolOrderItemByOrderId(id);
            if (itemList != null) {
                for (CoolOrderItem item : itemList) {
                    if (order.getIsBatch() != null && order.getIsBatch() == 1) { // 批量操作不更新库存
                        jdbcTemplate.update("update cool_product_gg set sales=sales-? where id=?",
                            item.getCount(),
                            item.getGid());
                    } else {
                        jdbcTemplate.update("update cool_product_gg set stock=stock+?,sales=sales-? where id=?",
                            item.getCount(),
                            item.getCount(),
                            item.getGid());
                    }
                    
                    jdbcTemplate.update("update cool_product set sales=sales-? where id=?",
                        item.getCount(),
                        item.getgId());
                }
            }
        }
    }
    
    @Override
    public List<CoolOrder> getCoolOrderList(CoolOrder param) {
        return coolOrderMapper.getCoolOrderList(param);
    }
    
    /**
     * 确认订单（不保存订单）
     */
    @Override
    public Record getCoolOrderReturn(CoolOrderParam param) {
        Record record = new Record();
        boolean success = false;
        
        Double psPrice = 0.0; // 邮费
        BigDecimal total = new BigDecimal(0); // 订单总金额
        Double totalWeight = 0.0; // 订单总重量，单位克
        int count = 0; // 订单商品数量
        double tax = 0.0; // 税
        BigDecimal chaiPrice = new BigDecimal(0); // 一件代发拆弹价格
        List<CoolProduct> products = new ArrayList<CoolProduct>();
        String isProducts = "0";
        List<CoolOrderItem> orderItems = param.getOrderItems();
        if (orderItems != null && !orderItems.isEmpty()) {
            CoolProduct product;
            CoolProductGg productGg;
            for (CoolOrderItem item : orderItems) {
                productGg = coolProductGgMapper.getCoolProductGgById(item.getGid());
                product = new CoolProduct();
                CoolProduct productDb = coolProductMapper.getCoolProductById(productGg.getGoodsId());
                BeanUtils.copyProperties(productDb, product);
                
                Byte orderType = param.getOrderType();
                if (0 == orderType) { // 普通订单
                    if (productGg.getCxjg().compareTo(new BigDecimal(0)) == 0) {
                        product.setPrice(productGg.getJg());
                    } else {
                        product.setPrice(productGg.getCxjg());
                    }
                } else if (1 == orderType) { // 一件代发
                    Integer userId = param.getUserId();
                    // 查询酷店的vip等级，来调取gg里边的vip价格
                    CoonShop coonShop = coonShopMapper.getCoonShopByUserId(String.valueOf(userId));
                    Integer vip = coonShop.getVip();
                    BigDecimal price = new BigDecimal(0);
                    if (vip == 1) {
                        price = productGg.getVipPriceA();
                    } else if (vip == 2) {
                        price = productGg.getVipPriceB();
                    } else if (vip == 3) {
                        price = productGg.getVipPriceC();
                    }
                    // 如果vip等于0 或者有没有设置vip价格的商品就直接去原先的供货价
                    if (price.compareTo(BigDecimal.ZERO) == 0) {
                        BigDecimal rate = coonShopLevelMapper.getLevelPrate(userId.toString());
                        price = productGg.getDrpPrice().multiply(rate);
                    }
                    product.setPrice(price);
                    chaiPrice = price;
                }
                count += item.getCount();
                totalWeight += item.getCount() * (productGg.getWeight() == null ? 0 : productGg.getWeight());
                total = total.add(product.getPrice().multiply(new BigDecimal(item.getCount())));
                product.setStandard(productGg);
                product.setQuantity(item.getCount());
                tax += product.getPrice().multiply(product.getTax()).doubleValue() * count;
                products.add(product);
                isProducts = "1";
            }
            
            // 不包邮的情况下才计算邮费
            if (param.getFreePost() != null && !param.getFreePost() && StringUtils.isNotEmpty(param.getLocationP())) {
                String expressCode = param.getExpressCode();
                psPrice = countPsPrice(param.getLocationP(), totalWeight / 1000, expressCode);
            }
            
            // 是否免税
            if ((param.getFreeTax() != null && !param.getFreeTax()) || param.getFreeTax() == null) {
                if (tax > 50) {
                    total = total.add(new BigDecimal(tax));
                }
            }
            
            record.set("count", count);
            record.set("products", products);
            record.set("total", total.add(new BigDecimal(psPrice)));
            record.set("psPrice", psPrice);
            record.set("taxPrice", tax);
            record.set("isProducts", isProducts);
            record.set("chaiPrice", chaiPrice);// 仅供拆单用
            success = true;
        } else {
            record.set("msg", "参数错误，订单详情不能为空");
        }
        record.set("success", success);
        return record;
    }
    
    /**
     * 计算邮费
     */
    public Double countPsPrice(String province, Double sw, String expressCode) {
        Double sumPrice = 0.0;
        // 获取物流信息
        /**
        if ("北京".equals(province)) {
            province = "北京市";
        }
        */
        List<CoonFareTempPro> proList = coonFareTempProMapper.getCoonFareTempProByPro(province, expressCode);
        if (CollectionUtils.isNotEmpty(proList)) {
            CoonFareTempPro pro = proList.get(0);
            
            if (sw <= pro.getFirstWei().doubleValue()) {
                sumPrice = pro.getFirstPri().doubleValue();
            } else {
                if (sw - pro.getFirstWei().doubleValue() <= pro.getExtentWei().doubleValue()) {
                    sumPrice = pro.getFirstPri().doubleValue() + pro.getExtentPri().doubleValue();
                } else {
                    sumPrice =
                        pro.getFirstPri().doubleValue() + Math.ceil(sw - pro.getFirstWei().doubleValue())
                            / pro.getExtentWei().doubleValue() * pro.getExtentPri().doubleValue();
                }
                
            }
        }
        
        return sumPrice;
    }
    
    /**
     * 保存订单
     */
    @Override
    public Record saveCoolOrder(CoolOrderParam param) {
        Record record = new Record();
        boolean success = false;
        Date date = new Date();
        Integer userId = param.getUserId();
        // APP跟网页版参数不一致暂时采用的解决方法
        String supplier = param.getSupplier();
        if (StringUtils.isNotEmpty(supplier)) {
            CoonShop cShop = coonShopMapper.getCoonShopById(supplier);
            if (cShop != null) {
                userId = Integer.valueOf(cShop.getUserId());
            }
        }
        if (userId != null) { // 酷店的userId
            String mId = param.getmId(); // 购买者会员主键
            if (StringUtils.isNotEmpty(mId)) {
                BigDecimal total = new BigDecimal(0);
                CoolMember member = coolMemberMapper.getCoolMemberById(Integer.valueOf(mId));
                CoolOrder order = new CoolOrder();
                order.setmId(member.getUserId());
                order.setCreatetime(new Timestamp(date.getTime()));
                
                // Boolean flag = false;
                
                if (StringUtils.isNotEmpty(param.getIsBatch()) && "1".equals(param.getIsBatch())) {
                    // flag = true;
                    order.setIsBatch(new Byte("1"));
                } else {
                    order.setIsBatch(new Byte("0"));
                }
                
                // 收货地址
                Integer recId = param.getRecId();
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
                    order.setLocationP(param.getLocationP());
                    order.setLocationC(param.getLocationC());
                    order.setLocationA(param.getLocationA());
                    order.setAddress(param.getAddress());
                    order.setLinkman(param.getLinkman());
                    order.setMobile(param.getMobile());
                    order.setTel(param.getTel());
                    order.setZipcode(param.getZipcode());
                    order.setIdcard(param.getIdcard());
                    order.setOrderType(param.getOrderType());
                }
                
                // 旗舰店分佣
                boolean canSplitBrokerage = (param.getShareUser() != null && !param.getShareUser().equals(userId));
                if (canSplitBrokerage) {
                    order.setShareUser(param.getShareUser());
                    order.setShareAdded(false);
                }
                
                // 配送方式
                order.setPsfs(param.getPsfs());
                // 支付方式
                order.setZffs(param.getZffs());
                
                // 处理商品信息
                List<CoolOrderItem> items = param.getOrderItems();
                if (CollectionUtils.isNotEmpty(items)) {
                    CoolProduct product;
                    CoolProductGg productGg;
                    boolean flag = false;
                    // 生成订单号
                    String orderNo = OrderUtil.generateOrderNo(items.get(0).getGid());
                    order.setOrderNo(orderNo);
                    
                    Double psPrice = 0.0; // 邮费
                    Double totalWeight = 0.0; // 订单总重量，单位克
                    for (CoolOrderItem item : items) {
                        flag = false;
                        Integer pId = item.getgId();
                        if (pId != null) {
                            Integer gid = item.getGid();
                            if (gid != null) {
                                product = coolProductMapper.getCoolProductById(item.getgId());
                                if (product == null) {
                                    record.set("msg", "参数错误，根据商品主键" + pId + "无法获取商品信息");
                                    break;
                                }
                                productGg = coolProductGgMapper.getCoolProductGgById(item.getGid());
                                if (productGg == null) {
                                    record.set("msg", "参数错误，根据商品规格主键" + gid + "无法获取商品规格信息");
                                    break;
                                }
                                BigDecimal divide = product.getDivide();
                                int count = item.getCount();
                                // 保存商品清单
                                item.setName(product.getNameEn());
                                item.setLogo(product.getLogo());
                                item.setGg(productGg.getGg());
                                item.setCount(count);
                                item.setOrderNo(orderNo);
                                item.setbId(product.getbId());
                                item.setGid(productGg.getId());
                                totalWeight +=
                                    item.getCount() * (productGg.getWeight() == null ? 0 : productGg.getWeight());
                                
                                Byte orderType = param.getOrderType();
                                if (0 == orderType) { // 普通订单
                                    if (productGg.getCxjg().compareTo(new BigDecimal(0)) == 0) {
                                        total = total.add(productGg.getJg().multiply(new BigDecimal(count)));
                                        item.setPrice(productGg.getJg());
                                    } else {
                                        total = total.add(productGg.getCxjg().multiply(new BigDecimal(count)));
                                        item.setPrice(productGg.getCxjg());
                                    }
                                    // 添加佣金
                                    BigDecimal profit =
                                        item.getPrice().multiply(new BigDecimal(item.getCount())).multiply(divide);
                                    item.setProfit(profit);
                                } else if (1 == orderType) { // 一件代发
                                    /**
                                     * BigDecimal rate = coonShopLevelMapper.getLevelPrate(userId.toString()); total =
                                     * total.add(productGg.getDrpPrice() .multiply(rate) .multiply(new
                                     * BigDecimal(count))); item.setPrice(productGg.getDrpPrice().multiply(rate));
                                     */
                                    if (productGg.getDrpPrice().compareTo(new BigDecimal(0)) == 0) {
                                        total = total.add(productGg.getCxjg().multiply(new BigDecimal(count)));
                                        item.setPrice(productGg.getCxjg());
                                    } else {
                                        total = total.add(productGg.getDrpPrice().multiply(new BigDecimal(count)));
                                        item.setPrice(productGg.getDrpPrice());
                                    }
                                }
                                
                                // 旗舰店分佣
                                if (canSplitBrokerage && param.getStoreId() != null) {
                                    CoonShopProduct shopProductP = new CoonShopProduct();
                                    shopProductP.setpId(item.getgId().toString());
                                    shopProductP.setsId(param.getStoreId().toString());
                                    CoonShopProduct shopProduct =
                                        coonShopProductMapper.getCoonShopProduct(shopProductP);
                                    BigDecimal brokerage = shopProduct.getBrokerage();
                                    if (brokerage != null) {
                                        // 二次分佣 = 分佣比例 * 商品价格 * 佣金比例 * 酷店等级佣金比例(旗舰店为100%)
                                        item.setBrokerage(brokerage.multiply(item.getPrice())
                                            .multiply(product.getDivide()));
                                    }
                                }
                                coolOrderItemMapper.saveCoolOrderItem(item);
                                
                                // 处理商品库存和销量
                                jdbcTemplate.update("update cool_product_gg set stock=stock-" + count + ",sales=sales+"
                                    + count + " where id=?", item.getGid());
                                jdbcTemplate.update("update cool_product set sales=sales+" + count + " where id=?",
                                    item.getgId());
                                // 删除购物车中对应商品，如果有的话
                                jdbcTemplate.update("delete from coon_shopcart where user_id=? and p_id=? and g_id=?",
                                // userId,
                                    member.getUserId(),
                                    item.getgId(),
                                    item.getGid());
                                // 如果一件代发，删除进货单，如果有的话
                                if (1 == orderType) { // 一件代发
                                    jdbcTemplate.update("delete from cool_distribution_car where user_id=? and p_id=? and g_id=?",
                                        userId,
                                        item.getgId(),
                                        item.getGid());
                                }
                                
                                order.setbId(product.getbId());
                                
                                flag = true;
                            } else {
                                record.set("msg", "参数错误，商品规格主键不能为空");
                                break;
                            }
                        } else {
                            record.set("msg", "参数错误，商品主键不能为空");
                            break;
                        }
                    }
                    if (flag) {
                        if (param.getPsPrice() != null) {
                            order.setPsPrice(param.getPsPrice());
                            total.add(param.getPsPrice());
                        } else {
                            // 根据参数是否包邮处理
                            // 不包邮的情况下才计算邮费
                            if (param.getFreePost() != null && !param.getFreePost()
                                && StringUtils.isNotEmpty(param.getLocationP())) {
                                String expressCode = param.getExpressCode();
                                psPrice = countPsPrice(param.getLocationP(), totalWeight / 1000, expressCode);
                            }
                        }
                        order.setPsPrice(new BigDecimal(psPrice));
                        order.setTotal(total.add(new BigDecimal(psPrice)));
                        order.setSupplier(param.getSupplier());
                        order.setoType(param.getoType());
                        
                        if (param.getStoreId() != null) {
                            CoonShop shop = coonShopMapper.getCoonShopById(param.getStoreId().toString());
                            CoonShopLevel level = coonShopLevelMapper.getCoonShopLevelById(shop.getLevel());
                            
                            // 计算订单佣金
                            BigDecimal shopRate = level.getcRate();
                            BigDecimal profit = coolOrderItemMapper.getMaxProfit(orderNo);// 订单最大佣金
                            profit = profit.multiply(shopRate);
                            order.setSupplierProfit(profit); // 设置订单的酷店分成金额
                        }
                        
                        int result = coolOrderMapper.saveCoolOrder(order);
                        
                        CoolOrder orderR = coolOrderMapper.getCoolOrderByOrderNo(orderNo);
                        record.set("id", orderR.getId());
                        record.set("orderNo", order.getOrderNo());
                        if (result > 0) {
                            success = true;
                            
                            // 我的消息内部发送消息(发送给客户)
                            CoolMessage m1 = new CoolMessage();
                            m1.setUserId(member.getUserId());
                            m1.setType("3");
                            m1.setClick(2);
                            m1.setContent("【小酷儿】订单" + order.getOrderNo() + "已生成了，"
                                + "了解更多请关注公众号艾售国际。客服电话4009693356，感谢您的使用！");
                            m1.setSendtime(new Date());
                            m1.setBelongId(order.getOrderNo());
                            messageMapper.saveCoolMessage(m1);
                            
                            // 发送给酷店
                            CoolMessage m2 = new CoolMessage();
                            m2.setUserId(userId);
                            m2.setType("3");
                            m2.setClick(2);
                            m2.setContent("【小酷儿】订单" + order.getOrderNo() + "已生成了，"
                                + "了解更多请关注公众号艾售国际。客服电话4009693356，感谢您的使用！");
                            m2.setSendtime(new Date());
                            m2.setBelongId(order.getOrderNo());
                            messageMapper.saveCoolMessage(m2);
                        } else {
                            record.set("msg", "订单保存失败");
                        }
                        // record.set("zffs",param.getZffs());
                        // record.set("storeId",param.getStoreId());
                    }
                } else {
                    record.set("msg", "参数错误，订单详情不能为空");
                }
            } else {
                record.set("msg", "参数错误，会员主键不能为空");
            }
        }
        
        record.set("success", success);
        return record;
    }
    
    /**
     * 修改订单（通用）
     */
    @Override
    public Record updateCoolOrderCommon(Map<String, Object> param) {
        Record record = new Record();
        CoolOrder order = coolOrderMapper.getCoolOrderById((Integer)param.get("id"));
        if (param.get("oid_billno") != null) {
            order.setOidBillno(param.get("oid_billno").toString());
        }
        if (param.get("state") != null) {
            order.setState((Byte)param.get("state"));
        }
        if (param.get("pay_time") != null) {
            order.setPayTime(new Timestamp(new Date().getTime()));
        }
        if (param.get("trade_no") != null) {
            order.setTradeNo(param.get("trade_no").toString());
        }
        
        int result = coolOrderMapper.updateCoolOrder(order);
        record.set("success", result > 0 ? true : false);
        return record;
    }
    
    /**
     * 修改订单（支付成功）
     */
    @Override
    public Record updateCoolOrderCheck(Map<String, Object> param) {
        
        Record record = new Record();
        /**
         * CoolOrder order = coolOrderMapper.getCoolOrderById((Integer)param.get("id")); boolean success = false; String
         * msg = "恭喜您，您的订单已提交成功"; if (order.getZffs() == 1) {// 处理连连支付 if ("SUCCESS".equals(param.get(("pay_status"))))
         * {// 支付完成 success = true; // msg = "恭喜您，您的订单已支付成功"; msg = "您的包裹已准备发出"; String oid_billno =
         * param.get("oid_billno").toString();// 支付单号 order.setOidBillno(oid_billno);
         * order.setState(CoolOrder.ORDER_STATE_1); order.setPayTime(new Timestamp(new Date().getTime()));
         * 
         * coolOrderMapper.updateCoolOrder(order);
         * 
         * } else { msg = "订单支付失败"; } } else if (order.getZffs() == 2) {// 处理支付宝
         * 
         * } else if (order.getInt("zffs") == 3) {// 处理微信支付 if ("ok".equals(getPara(1))) {// 支付完成 success = true; msg =
         * "您的包裹已准备发出"; } else { msg = getPara(1) + "，请到<a href='/user/order'>我的订单</a>尝试重新支付"; } } else { success =
         * true; } setAttr("msg", msg); setAttr("success", success); setAttr("orderNo", order.getStr("order_no"));
         * setAttr("order", order); render("check_success.html");
         */
        return record;
    }
    
    /**
     * 修改订单（订单发货）0 自提，1 圆通，2 费舍尔，10 宁波艾购保税仓，11 宁波优贝保税仓，20 郑州海关
     */
    @SuppressWarnings("unchecked")
    @Override
    public Record updateCoolOrderDelivery(Map<String, Object> param) {
        Record record = new Record();
        CoolOrder orderR = getCoolOrderDetailById(Integer.valueOf(param.get("id").toString()));
        
        String send_sms_url = param.get("send_sms_url").toString();
        
        String fhfs = param.get("fhfs").toString();
        int resultUpdate = 0;
        
        if ("0".equals(fhfs)) {
            orderR.setFhfs(Byte.parseByte(fhfs));
            CoolOrder order = new CoolOrder();
            order.setState(CoolOrder.ORDER_STATE_2);
            order.setPsfs("自提");
            order.setFhfs((byte)0);
            order.setUpdatetime(new Timestamp(System.currentTimeMillis()));
            
            order.setId(orderR.getId());
            resultUpdate = coolOrderMapper.updateCoolOrder(order);
            
            // sendMessage(orderR, send_sms_url);
            record.set("success", resultUpdate > 0 ? true : false);
            return record;
        } else if ("1".equals(fhfs)) {
            /**
             * // 发送物流消息给客户 CoolMessage coolMessage = new CoolMessage(); coolMessage.setContent("【小酷儿】订单" +
             * orderR.getOrderNo() + "已发货了，" + "物流消息请点击:" + "<a href='http://m.i-coolshop.com/common/searchWapExpress/"
             * + orderR.getId() + "'>" + "http://m.i-coolshop.com/common/searchWapExpress/" + orderR.getId() + "</a>" +
             * "。客服电话4009693356，感谢您的使用！");
             * 
             * coolMessage.setClick(0); coolMessage.setType("2"); coolMessage.setBelongId(orderR.getOrderNo());
             * coolMessage.setUserId(orderR.getmId()); coolMessage.setSendtime(new Date());
             * coolMessage.setOperatorId(Integer.valueOf(param.get("operatorId").toString()));
             * messageMapper.saveCoolMessage(coolMessage);
             */
            orderR.setFhfs(Byte.parseByte(fhfs));
            String yuantong_surface_url = param.get("yuantong_surface_url").toString();
            CoolOrder order = new CoolOrder();
            OrderRequest request = new OrderRequest();
            // Record record = Db.findById("cool_order", getPara(0));
            request.setTxLogisticID(orderR.getOrderNo());
            
            // 发件人地址
            AddressInfo sender = new AddressInfo();
            sender.setName("艾售国际服务中心");
            sender.setPostCode(213000);
            sender.setMobile("13512345678");
            sender.setProv("江苏省");
            sender.setCity("常州市,新北区");
            sender.setAddress("太湖东路9-1号常高薪大厦A1801");
            // 收货人地址
            AddressInfo receiver = new AddressInfo();
            receiver.setName(orderR.getLinkman());
            // receiver.setPostCode(Integer.parseInt(orderR.getZipcode()));
            receiver.setMobile(orderR.getMobile());
            receiver.setProv(orderR.getLocationP());
            receiver.setCity(orderR.getLocationC() + "," + orderR.getLocationA());
            receiver.setAddress(orderR.getAddress());
            // 地址
            request.setSender(sender);
            request.setReceiver(receiver);
            // 发货商品信息
            List<ItemInfo> list = new ArrayList<ItemInfo>();
            for (CoolOrderItem orderItem : orderR.getItemList()) {
                ItemInfo item = new ItemInfo();
                item.setItemName(orderItem.getName());
                item.setNumber(orderItem.getCount());
                item.setItemValue(orderItem.getPrice().multiply(new BigDecimal(orderItem.getCount())));
                list.add(item);
            }
            request.setItems(list);
            request.setGoodsValue(orderR.getTotal());
            String result = HttpUtils.httpPost(yuantong_surface_url, JsonUtil.writeValueAsString(request));
            JsonData jsonData = JsonUtil.readValue(result, JsonData.class);
            Object jsonObject = jsonData.getDataValue("orderMessage");
            Map<String, Object> orderMessage = (Map<String, Object>)jsonObject;
            String datePath = new SimpleDateFormat("yy-MM-dd").format(new Date());
            String filePath = "/order/" + datePath + "/" + orderR.getOrderNo() + ".png";
            GetBarCode.getBarCode(config.getImgLocal() + "/order/" + datePath + "/",
                orderR.getOrderNo() + ".png",
                (String)orderMessage.get("mailNo"));
            
            order.setUpdatetime(new Timestamp(System.currentTimeMillis()));
            order.setState(CoolOrder.ORDER_STATE_2);
            order.setPsfs("圆通速递");
            order.setFhfs((byte)1);
            order.setBigpen(orderMessage.get("bigPen").toString());
            order.setPsCode(orderMessage.get("mailNo").toString());
            order.setBarcode(filePath);
            order.setId(orderR.getId());
            resultUpdate = coolOrderMapper.updateCoolOrder(order);
            
            sendMessage(orderR, send_sms_url);
            record.set("success", resultUpdate > 0 ? true : false);
            return record;
        } else if ("10".equals(fhfs)) { // 宁波艾购保税仓
            orderR.setFhfs(Byte.parseByte(fhfs));
            CoolOrder order = new CoolOrder();
            order.setState(CoolOrder.ORDER_STATE_11); // 已通知海关发货
            order.setFhfs((byte)10);
            order.setUpdatetime(new Timestamp(System.currentTimeMillis()));
            
            order.setId(orderR.getId());
            // 支付信息报关
            sendPayInfo(order, orderR);
            resultUpdate = coolOrderMapper.updateCoolOrder(order);
            
            // sendMessage(orderR, send_sms_url);
            record.set("success", resultUpdate > 0 ? true : false);
            return record;
        } else if ("11".equals(fhfs)) { // 宁波优贝保税仓
            orderR.setFhfs(Byte.parseByte(fhfs));
            OrderMsg orderMsg = getOrderMsg(orderR);
            String xml = JaxbUtil.convertToXml(orderMsg);
            xml = xml.replace("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>", "");
            
            StringBuilder builder = new StringBuilder();
            // 记录发送日志
            builder.append("parameter: ")
                .append(System.getProperty("line.separator", "\n"))
                .append(xml)
                .append(System.getProperty("line.separator", "\n"));
            
            KJB2CWebService service = new KJB2CWebService();
            String result =
                service.getKJB2CWebServiceSoap12().addOrderKJB2C(YoubeiService.ERPKEY, YoubeiService.ERPSECRET, xml);
            builder.append("response:  ").append(result);
            
            SendMessageLogUtil.info(builder.toString());
            if ("success".equals(result)) {
                CoolOrder order = new CoolOrder();
                order.setState(CoolOrder.ORDER_STATE_11); // 已通知海关发货
                order.setFhfs((byte)11);
                order.setUpdatetime(new Timestamp(System.currentTimeMillis()));
                
                order.setId(orderR.getId());
                // 支付信息报关
                sendPayInfo(order, orderR);
                resultUpdate = coolOrderMapper.updateCoolOrder(order);
                // sendMessage(orderR, send_sms_url);
            }
            
            record.set("success", resultUpdate > 0 ? true : false);
            record.set("msg", result);
            return record;
        } else if ("20".equals(fhfs)) { // 郑州海关
            orderR.setFhfs(Byte.parseByte(fhfs));
            String xml = JaxbUtil.convertToXml(getCbecmessage(orderR));
            // 发消息给海关
            String endpoint = "http://www.haeport.com:8000/com.ygjt.csp.api.WSRecvService?wsdl";
            String methodName = "receive"; // 平台暴露的方法名
            String namespace = "http://api.csp.ygjt.com/"; // 命名空间
            String parametersName = "arg0"; // 参数名
            String result = WebServiceClient.invokeRemoteFuc(endpoint, methodName, xml, namespace, parametersName);
            
            if ("SUCCESS".equals(result)) {
                CoolOrder order = new CoolOrder();
                order.setState(CoolOrder.ORDER_STATE_11); // 已通知海关发货
                order.setFhfs((byte)20);
                order.setUpdatetime(new Timestamp(System.currentTimeMillis()));
                
                order.setId(orderR.getId());
                // 支付信息报关
                // sendPayInfo(order, orderR);
                if (coolSendPayinfoMapper.getCoolSendPayinfoByOrderNo(orderR.getOrderNo()) == null) {
                    CoolSendPayinfo coolSendPayinfo = new CoolSendPayinfo();
                    coolSendPayinfo.setOrderNo(orderR.getOrderNo());
                    coolSendPayinfoMapper.saveCoolSendPayinfo(coolSendPayinfo);
                }
                
                resultUpdate = coolOrderMapper.updateCoolOrder(order);
                // sendMessage(orderR, send_sms_url);
            }
            
            // 内部消息
            if (!"0".equals(fhfs)) {
                CoolMessage coolMessage = new CoolMessage();
                coolMessage.setContent("【小酷儿】订单" + orderR.getOrderNo() + "已发货了，" + "物流消息请点击:"
                    + "<a href='http://m.i-coolshop.com/common/searchWapExpress/" + orderR.getId() + "'>"
                    + "http://m.i-coolshop.com/common/searchWapExpress/" + orderR.getId() + "</a>"
                    + "。客服电话4009693356，感谢您的使用！");
                coolMessage.setClick(0);
                coolMessage.setType("2");
                coolMessage.setBelongId(orderR.getOrderNo());
                coolMessage.setUserId(orderR.getmId());
                coolMessage.setSendtime(new Date());
                coolMessage.setOperatorId(Integer.valueOf(param.get("operatorId").toString()));
                messageMapper.saveCoolMessage(coolMessage);
            }
            
            // 发送消息给物流
            record.set("success", resultUpdate > 0 ? true : false);
            record.set("msg", result);
            return record;
        }
        
        return record;
    }
    
    @Override
    public Record sendPayInfo(String orderNo) {
        CoolOrder orderR = getCoolOrderByOrderNo(orderNo);
        
        Record record = sendPayInfo(orderR, orderR);
        if (record.getBoolean("success")) {
            updateOrder(orderR);
        }
        return record;
    }
    
    /**
     * 发送支付信息给海关
     * 
     * @param order 订单信息
     * @param orderDB 订单数据库信息
     */
    private Record sendPayInfo(CoolOrder order, CoolOrder orderDB) {
        Record record = new Record();
        record.set("success", false);
        if (orderDB.getZffs() == null || orderDB.getFhfs() == null || orderDB.getArrears() == 1) { // 欠费订单不需要报关
            return record;
        }
        String customsPlace = "HENAN"; // 支付宝发送海关编码 新郑综合保税区：HENAN，宁波：NINGBO
        String customs = "9"; // 微信发送海关编码 3宁波 9 郑州（综保区）
        String customsCode = "ZZ_4604"; // 易极付发送海关编码 ZZ_4604:郑州关区 NB_3100:宁波关区
        
        String mchCustomsNo = "3117964017"; // 微信商户备案号 3117964017：艾售-郑州综保，3302461678：宁兴优贝-宁波
        String eshopEntName = "上海艾售电子商务有限公司"; // 电商商户企业名称
        
        if (orderDB.getFhfs() == CoolOrder.FHFS_10 || orderDB.getFhfs() == CoolOrder.FHFS_11) { // 宁波海关
            customsPlace = "NINGBO";
            customs = "3";
            customsCode = "NB_3100";
            mchCustomsNo = "3302461678";
            eshopEntName = "宁波保税区宁兴优贝国际贸易有限公司"; // 电商商户企业名称
        } else if (orderDB.getFhfs() == CoolOrder.FHFS_20) { // 郑州综保区海关
            customsPlace = "HENAN";
            customs = "9";
            customsCode = "ZZ_4604";
            mchCustomsNo = "3117964017";
            eshopEntName = "上海艾售电子商务有限公司"; // 电商商户企业名称
        }
        
        if (orderDB.getZffs() == CoolOrder.ZFFS_2) {
            Map<String, String> map = new HashMap<String, String>();
            // 报关流水号，商户生成的用于唯一标识一次 报关操作的业务编号。 建议生成规则：yyyymmmdd 型 8位日期拼接 4 位序列号
            map.put("out_request_no", "isell" + System.currentTimeMillis());
            map.put("trade_no", orderDB.getTradeNo()); // 支付宝交易号
            map.put("amount", orderDB.getTotal().toString()); // 报关金额
            if (orderDB.getTotal().compareTo(new BigDecimal(100)) > 0) {
                // map.put("amount", new BigDecimal(99).add(orderDB.getPsPrice()).toString()); // 报关金额
            }
            map.put("merchant_customs_code", "3117964017"); // 商户海关备案编号
            map.put("customs_place", customsPlace); // 海关编号 如果您的支付单要报关到新郑综合保税区，请在报关接口的海关字段中填写HENAN。
            map.put("merchant_customs_name", "上海艾售电子商务有限公司"); // 商户海关备案名称 上海艾售电子商务有限公司jwyhanguo_card
            String result =
                HttpUtils.httpPost(config.getServiceDomain() + "/pay/alipay/sendOrder",
                    JsonUtil.writeValueAsString(map));
            JsonData jsonData = JsonUtil.readValue(result, JsonData.class);
            record.set("msg", jsonData.getMsg());
            if (jsonData.getSuccess()) {
                record.set("success", true);
                order.setPayState((byte)1); // 设置支付报关状态
            }
        } else if (orderDB.getZffs() == CoolOrder.ZFFS_3) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("transaction_id", orderDB.getTradeNo()); // 微信支付交易号
            map.put("mch_customs_no", mchCustomsNo); // 商户海关备案号（这个是宁兴优贝的）
            map.put("customs", customs); // 海关编号：3宁波 9 郑州（综保区）
            
            String result =
                HttpUtils.httpPost(config.getServiceDomain() + "/pay/weixin/sendOrder",
                    JsonUtil.writeValueAsString(map));
            JsonData jsonData = JsonUtil.readValue(result, JsonData.class);
            record.set("msg", jsonData.getMsg());
            if (jsonData.getSuccess()) {
                record.set("success", true);
                order.setPayState((byte)1); // 设置支付报关状态
            }
        } else if (orderDB.getZffs() == CoolOrder.ZFFS_4) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("tradeNo", "[\"" + orderDB.getTradeNo() + "\"]"); // 易极付支付交易号
            map.put("outOrderNo", orderDB.getOrderNo()); // 订单号
            map.put("eshopEntCode", mchCustomsNo); // 商户海关备案号
            map.put("eshopEntName", eshopEntName); // 商户海关备案名称
            map.put("customsCode", customsCode); // 易极付发送海关编码 ZZ_4604:郑州关区 NB_3100:宁波关区
            map.put("payerId", orderDB.getIdcard().toUpperCase()); // 支付人证件号码
            map.put("payerName", orderDB.getLinkman()); // 支付人姓名
            map.put("goodsAmount", orderDB.getTotal()); // 支付金额
            if (orderDB.getFhfs() == CoolOrder.FHFS_20) {
                map.put("ieType", "IMPORT"); // 进出口标示
            }
            
            String result =
                HttpUtils.httpPost(config.getServiceDomain() + "/pay/yijifu/singlePaymentUpload",
                    JsonUtil.writeValueAsString(map));
            JsonData jsonData = JsonUtil.readValue(result, JsonData.class);
            record.set("msg", jsonData.getDataValue("resultMessage"));
            if (jsonData.getSuccess()) {
                record.set("success", true);
                order.setPayState((byte)1); // 设置支付报关状态
            }
        }
        return record;
    }
    
    private void sendMessage(CoolOrder orderR, String url) {
        
        CoonShop coonShop = coonShopMapper.getCoonShopById(orderR.getSupplier());
        if (0 == coonShop.getSmsEd()) {
            SMSUtil.sendMsg("15129", orderR.getMobile(), orderR.getOrderNo(), url);
        }
        /*
         * if (!"56510b6f384c4f8e881ee1614913a3ef".equals(orderR.getSupplier()) &&
         * !"b4124c7271094479ae2c20cce611b5f4".equals(orderR.getSupplier()) &&
         * !"dc27cddab07d4d5da74d38d9a9fe77e0".equals(orderR.getSupplier())) {// 可爱淘、KK馆不需要发货短信 SMSUtil.sendMsg("15129",
         * orderR.getMobile(), orderR.getOrderNo(), url); }
         */
    }
    
    /**
     * 宁波优贝订单信息
     * 
     * @param orderDb 订单信息
     * @return 订单信息
     */
    private OrderMsg getOrderMsg(CoolOrder orderDb) {
        OrderMsg orderMsg = new OrderMsg();
        Order order = new Order();
        order.setPayNo(orderDb.getTradeNo());
        order.setTid(orderDb.getOrderNo());
        order.setCreated(orderDb.getCreatetime());
        order.setPayTime(orderDb.getPayTime());
        if (order.getCreated().compareTo(order.getPayTime()) >= 0) { // 创建日期必须小于支付时间
            order.setCreated(new Date(order.getPayTime().getTime() - 60000));
        }
        order.setBuyerNick(orderDb.getLinkman());
        order.setPostFee(orderDb.getPsPrice());
        order.setPayment(orderDb.getTotal());
        order.setTotalRate(orderDb.getPsPrice().doubleValue() > 50 ? orderDb.getPsPrice() : BigDecimal.ZERO);
        order.setReceiverName(orderDb.getLinkman());
        order.setReceiverState(orderDb.getLocationP());
        order.setReceiverCity(orderDb.getLocationC());
        order.setReceiverDistrict(orderDb.getLocationA());
        order.setReceiverAddress(orderDb.getAddress());
        order.setReceiverMobile(orderDb.getMobile());
        if (orderDb.getDiscountPrice().compareTo(BigDecimal.ZERO) > 0) {
            order.setProAmount(orderDb.getDiscountPrice().doubleValue());
        }
        if (orderDb.getZffs() == 2) { // 支付宝支付
            order.setKjPayType("02");
        } else if (orderDb.getZffs() == 3) { // 微信支付
            order.setKjPayType("13");
        } else if (orderDb.getZffs() == 4) { // 易极付支付
            order.setKjPayType("23");
        } else if (orderDb.getZffs() == 6) { // 浙江银商支付
            order.setKjPayType("29");
        }
        // order.setKjPayType("02"); // 微信走不通，直接都写支付宝
        
        orderMsg.setOrder(order);
        orderMsg.setOrderItems(new ArrayList<Detail>(orderDb.getItemList().size()));
        for (CoolOrderItem item : orderDb.getItemList()) {
            Detail detail = new Detail();
            CoolProductCustomsNbyb coolProductCustomsNbyb =
                coolProductCustomsNbybService.getCustomsInfoByGid(item.getGid());
            detail.setProductCode(coolProductCustomsNbyb.getProductCode()); // 这个要填货品在保税仓的商品编号
            detail.setProductName(coolProductCustomsNbyb.getProductName()); // 货品名
            detail.setUnit(coolProductCustomsNbyb.getUnit()); // 单位
            detail.setQuantity(item.getCount());
            detail.setPrice(item.getPrice());
            detail.setTotalPrice(detail.getPrice().multiply(new BigDecimal(detail.getQuantity())));
            
            orderMsg.getOrderItems().add(detail);
        }
        UserReg userReg = new UserReg();
        userReg.setName(orderDb.getLinkman());
        userReg.setPhone(orderDb.getMobile());
        userReg.setIdnum(orderDb.getIdcard());
        userReg.setEmail(orderDb.getMobile() + "@139.com");
        
        orderMsg.setUserReg(userReg);
        return orderMsg;
    }
    
    /**
     * 郑州海关进出口订单信息
     * 
     * @param orderDb 订单信息
     * @return 订单信息
     */
    private Cbecmessage getCbecmessage(CoolOrder orderDb) {
        Cbecmessage cbecmessage = new Cbecmessage();
        Messagehead messagehead = new Messagehead();
        messagehead.setMessageid(orderDb.getOrderNo() + CommonUtils.randomFour());
        messagehead.setSendtime(DateUtil.getCurrentDate(DateUtil.yyyy_MM_dd_HH_mm_ss));
        messagehead.setSeqno(System.currentTimeMillis() + "");
        
        Messagebody messagebody = new Messagebody();
        
        Bodymaster bodymaster = new Bodymaster();
        bodymaster.setCollectionuseraddress(orderDb.getLocationP() + orderDb.getLocationC() + orderDb.getLocationA()
            + orderDb.getAddress());
        bodymaster.setCollectionusername(orderDb.getLinkman());
        bodymaster.setCollectionusertelephone(orderDb.getMobile());
        bodymaster.setGoodsvalue(orderDb.getTotal().doubleValue() + "");
        bodymaster.setOrderid(orderDb.getOrderNo());
        bodymaster.setOrdersum(orderDb.getTotal().doubleValue() + "");
        bodymaster.setFreight(orderDb.getPsPrice().doubleValue() + "");
        bodymaster.setOtherfee("0");
        bodymaster.setTaxfee(orderDb.getTaxPrice().doubleValue() + "");
        bodymaster.setCustomerid(orderDb.getIdcard().toUpperCase());
        bodymaster.setSubmittime(DateUtil.dateToStr(DateUtil.yyyy_MM_dd_HH_mm_ss, orderDb.getCreatetime()));
        bodymaster.setPaynumber(orderDb.getTradeNo());
        if (orderDb.getZffs() == 2) { // 支付宝支付
            bodymaster.setPayenterprisecode("P460400005");
            bodymaster.setPayenterprisename("支付宝(中国)网络技术有限公司");
        } else if (orderDb.getZffs() == 3) { // 微信支付
            bodymaster.setPayenterprisecode("P460400004");
            bodymaster.setPayenterprisename("财付通支付科技有限公司");
        } else if (orderDb.getZffs() == 4) { // 易极付支付
            bodymaster.setPayenterprisecode("P461200007");
            bodymaster.setPayenterprisename("重庆易极付科技有限公司");
        }
        
        Bodydetail bodydetail = new Bodydetail();
        bodydetail.setOrderList(new ArrayList<OrderInfo>(orderDb.getItemList().size()));
        
        for (CoolOrderItem item : orderDb.getItemList()) {
            OrderInfo order = new OrderInfo();
            CoolProductCustomsZz customsInfo = coolProductCustomsZzService.getCustomsInfoByGid(item.getGid());
            
            order.setGno(customsInfo.getGno()); // 关联 H2010 项号
            order.setTaxid(customsInfo.getTaxId()); // 行邮税号
            order.setBarcode(customsInfo.getBarcode()); // HS 编码
            order.setUnit(customsInfo.getUnit()); // 计量单位（海）
            order.setUnitinsp(customsInfo.getUnitinsp()); // 计量单位（检）
            order.setItemno(customsInfo.getItemno()); // 海关备案商品编号
            order.setShelfgoodsname(customsInfo.getShelfgoodsname()); // 商品上架品名
            order.setGoodid(customsInfo.getGoodId()); // 商品货号 (有表格)
            order.setGoodname(item.getName()); // 商品名
            order.setSpecifications(customsInfo.getSpecifications()); // 规格型号
            order.setAmount(item.getCount() + ""); // 申报数量
            order.setGoodprice(item.getPrice() + ""); // 成交单价
            order.setOrdersum(item.getPrice().multiply(new BigDecimal(item.getCount())) + ""); // 成交总价
            order.setOrderid(orderDb.getOrderNo()); // 订单编号
            order.setGoodidinsp(customsInfo.getGoodIdinsp()); // 检验检疫商品备案编号
            
            bodydetail.getOrderList().add(order);
        }
        
        messagebody.setBodymaster(bodymaster);
        messagebody.setBodydetail(bodydetail);
        
        cbecmessage.setMessagehead(messagehead);
        cbecmessage.setMessagebody(messagebody);
        
        return cbecmessage;
    }
    
    /**
     * 修改订单（订单签收）
     */
    @Override
    public Record updateCoolOrderRec(Map<String, Object> param) {
        Record record = new Record();
        boolean success = false;
        CoolOrder order = coolOrderMapper.getCoolOrderById((Integer)param.get("id"));
        if (order.getOrderType() == CoolOrder.ORDER_STATE_3) {
            record.set("msg", "该订单已确认收货，无法再次确认收货");
        } else if (order.getOrderType() == CoolOrder.ORDER_STATE_4) {
            record.set("msg", "该订单已完成，无法再次确认收货");
        } else if (order.getOrderType() == CoolOrder.ORDER_STATE_5) {
            record.set("msg", "该订单已退款，无法再次确认收货");
        } else {
            order.setState(CoolOrder.ORDER_STATE_3);
            order.setFinishTime(new Timestamp(System.currentTimeMillis()));
            
            int result = 0;
            String shopId = order.getSupplier();
            if (StringUtils.isNotEmpty(shopId)) {
                CoonShop shop = coonShopMapper.getCoonShopById(shopId);
                if (shop == null) {
                    return null;
                }
                CoonShopLevel level = coonShopLevelMapper.getCoonShopLevelById(shop.getLevel());
                if (order.getOrderType() == 0) { // 订单类型：0：普通订单/1：一件代发
                    if (order.getSupplierProfit().compareTo(BigDecimal.ZERO) == 0) {
                        // 计算订单佣金
                        BigDecimal shopRate = level.getcRate();
                        BigDecimal profit = coolOrderItemMapper.getMaxProfit(order.getOrderNo()); // 订单最大佣金
                        profit = profit.multiply(shopRate);
                        order.setSupplierProfit(profit);
                        
                    }
                    // 记录佣金
                    CoonRunAccount bill = new CoonRunAccount();
                    bill.setId(CommonUtils.uuid());
                    bill.setUserId(shop.getUserId());
                    bill.setAmount(order.getSupplierProfit());
                    bill.setType(CoonRunAccount.TYPE_0);
                    bill.setCreateTime(new Date());
                    bill.setFinishTime(new Date());
                    bill.setWithdrawState(CoonRunAccount.WITHDRAW_STATE_3);
                    bill.setSerialNumber(DateUtil.dateToStr(DateUtil.yyyyMMddHHmmss, new Date())
                        + CommonUtils.randomFour());
                    bill.setIsFreeze((byte)1);
                    bill.setFreezeTime(new Date());
                    bill.setFinishTime(new Date());
                    bill.setShopId(shopId);
                    bill.setShopName(shop.getName());
                    bill.setOrderNo(order.getOrderNo());
                    coonRunAccountMapper.saveCoonRunAccount(bill);
                    
                    // 更新酷店成交订单数,all_amount=all_amount+?,nwd_amount=nwd_amount+?
                    result =
                        jdbcTemplate.update("update coon_shop set turnover_orders=turnover_orders+1,jj_amount=jj_amount+? where id=?",
                            order.getSupplierProfit(),
                            shopId);
                    
                    CoolMessage message1 = new CoolMessage();
                    message1.setUserId(Integer.valueOf(shop.getUserId()));
                    message1.setType("5");
                    message1.setContent("【小酷儿】订单" + order.getOrderNo() + "佣金【"
                        + order.getSupplierProfit().setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue()
                        + "元】即将在七天之后打入您的账户。" + "了解更多请关注公众号艾售国际。客服电话4009693356，感谢你的使用！");
                    message1.setSendtime(new Date());
                    message1.setClick(0);
                    message1.setRole("b");
                    message1.setBelongId(order.getOrderNo());
                    messageMapper.saveCoolMessage(message1);
                    
                    // 推荐店铺相关处理
                    String recommendId = shop.getRecommendId();
                    if (StringUtils.isNotEmpty(recommendId)) {
                        CoonShopPartner partner = new CoonShopPartner();
                        CoolConfig cc = coolConfigMapper.getPercentageByDevide("2");
                        partner.setId(CommonUtils.uuid());
                        partner.setoId(order.getId());
                        partner.setSupplier(recommendId);
                        partner.setPartnerId(shop.getId());
                        partner.setPartnerName(shop.getName());
                        partner.setPartnerAmount(order.getSupplierProfit().multiply(cc.getRateJpy()));
                        partner.setCreatetime(new Date());
                        partner.setOrderAmount(order.getTotal());
                        coonShopPartnerMapper.saveCoonShopPartner(partner);
                        
                        // 记录佣金
                        CoonRunAccount bill2 = new CoonRunAccount();
                        CoonShop shop2 = coonShopMapper.getCoonShopById(recommendId);
                        bill2.setId(CommonUtils.uuid());
                        bill2.setUserId(shop2.getUserId());
                        bill2.setAmount(order.getSupplierProfit().multiply(cc.getRateJpy()));
                        bill2.setType(CoonRunAccount.TYPE_0);
                        bill2.setCreateTime(new Date());
                        bill2.setFreezeTime(new Date());
                        bill2.setFinishTime(new Date());
                        bill2.setWithdrawState(CoonRunAccount.WITHDRAW_STATE_6);
                        bill2.setSerialNumber(DateUtil.dateToStr(DateUtil.yyyyMMddHHmmss, new Date())
                            + CommonUtils.randomFour());
                        bill2.setOrderNo(order.getOrderNo());
                        bill2.setDevide("2");
                        bill2.setIsFreeze((byte)1);
                        bill2.setShopId(shop2.getId());
                        bill2.setShopName(shop2.getName());
                        coonRunAccountMapper.saveCoonRunAccount(bill2);
                        jdbcTemplate.update("update coon_shop set jj_amount=jj_amount+? where id=?",
                            order.getSupplierProfit(),
                            recommendId);
                        
                        CoolMessage message2 = new CoolMessage();
                        message2.setUserId(Integer.valueOf(shop2.getUserId()));
                        message2.setType("5");
                        message2.setContent("【小酷儿】订单"
                            + order.getOrderNo()
                            + "佣金【"
                            + order.getSupplierProfit()
                                .multiply(cc.getRateJpy())
                                .setScale(2, BigDecimal.ROUND_HALF_UP)
                                .doubleValue() + "元】即将在七天之后打入您的账户。" + "了解更多请关注公众号艾售国际。客服电话4009693356，感谢你的使用！");
                        message2.setSendtime(new Date());
                        message2.setClick(0);
                        message2.setRole("b");
                        message2.setBelongId(order.getOrderNo());
                        messageMapper.saveCoolMessage(message2);
                        
                        /*
                         * jdbcTemplate.update(
                         * "update coon_shop set all_amount=all_amount+?,nwd_amount=nwd_amount+?,recommend_amount=recommend_amount +? where id=?"
                         * , order.getSupplierProfit().multiply(shop2.getsPercentage()),
                         * order.getSupplierProfit().multiply(shop2.getsPercentage()),
                         * order.getSupplierProfit().multiply(shop2.getsPercentage()), recommendId);
                         */
                        
                        String recommendId2 = shop2.getRecommendId();
                        if (StringUtils.isNotEmpty(recommendId2)) {
                            CoonShop shop3 = coonShopMapper.getCoonShopById(recommendId2);
                            CoolConfig cc2 = coolConfigMapper.getPercentageByDevide("3");
                            CoonShopPartner partner2 = new CoonShopPartner();
                            partner2.setId(CommonUtils.uuid());
                            partner2.setoId(order.getId());
                            partner2.setSupplier(recommendId2);
                            partner2.setPartnerId(shop2.getId());
                            partner2.setPartnerName(shop2.getName());
                            partner2.setPartnerAmount(order.getSupplierProfit().multiply(cc2.getRateJpy()));
                            partner2.setCreatetime(new Date());
                            partner2.setOrderAmount(order.getTotal());
                            coonShopPartnerMapper.saveCoonShopPartner(partner2);
                            
                            // 记录佣金
                            CoonRunAccount bill3 = new CoonRunAccount();
                            bill3.setId(CommonUtils.uuid());
                            bill3.setUserId(shop3.getUserId());
                            bill3.setAmount(order.getSupplierProfit().multiply(cc2.getRateJpy()));
                            bill3.setType(CoonRunAccount.TYPE_0);
                            bill3.setCreateTime(new Date());
                            bill3.setFreezeTime(new Date());
                            bill3.setFinishTime(new Date());
                            bill3.setWithdrawState(CoonRunAccount.WITHDRAW_STATE_6);
                            bill3.setSerialNumber(DateUtil.dateToStr(DateUtil.yyyyMMddHHmmss, new Date())
                                + CommonUtils.randomFour());
                            bill3.setOrderNo(order.getOrderNo());
                            bill3.setDevide("3");
                            bill3.setIsFreeze((byte)1);
                            bill3.setShopId(shop3.getId());
                            bill3.setShopName(shop3.getName());
                            coonRunAccountMapper.saveCoonRunAccount(bill3);
                            jdbcTemplate.update("update coon_shop set jj_amount=jj_amount+? where id=?",
                                order.getSupplierProfit(),
                                recommendId2);
                            
                            CoolMessage message3 = new CoolMessage();
                            message3.setUserId(Integer.valueOf(shop3.getUserId()));
                            message3.setType("5");
                            message3.setContent("【小酷儿】订单"
                                + order.getOrderNo()
                                + "佣金【"
                                + order.getSupplierProfit()
                                    .multiply(cc2.getRateJpy())
                                    .setScale(2, BigDecimal.ROUND_HALF_UP)
                                    .doubleValue() + "元】即将在七天之后打入您的账户。" + "了解更多请关注公众号艾售国际。客服电话4009693356，感谢你的使用！");
                            message3.setSendtime(new Date());
                            message3.setClick(0);
                            message3.setRole("b");
                            message3.setBelongId(order.getOrderNo());
                            messageMapper.saveCoolMessage(message3);
                            
                            /*
                             * jdbcTemplate.update(
                             * "update coon_shop set all_amount=all_amount+?,nwd_amount=nwd_amount+?,recommend_amount=recommend_amount +? where id=?"
                             * , order.getSupplierProfit().multiply(shop3.getfPercentage()),
                             * order.getSupplierProfit().multiply(shop3.getfPercentage()),
                             * order.getSupplierProfit().multiply(shop3.getfPercentage()), recommendId2);
                             */
                        }
                    }
                } else if (order.getOrderType() == 1) { // 一件代发无佣金
                    result =
                        jdbcTemplate.update("update coon_shop set turnover_orders=turnover_orders+1 where id=?", shopId);
                }
                
                if (result > 0 && level.getLevel() != 25) {
                    // 判断是否到达下个等级
                    int res = coonShopMapper.getCoonShopNextLevel(shopId);
                    if (res > 0) {
                        // 更新酷店等级
                        result = coonShopMapper.updateCoonShopLevel(shopId);
                    }
                }
            }
            result = coolOrderMapper.updateCoolOrder(order);
            if (result > 0) {
                success = true;
            } else {
                record.set("msg", "订单更新失败");
            }
        }
        // 沃朴 同步收货
        wplwSynOrder(order);
        
        record.set("receiptTime", order.getFinishTime());
        record.set("success", success);
        return record;
    }
    
    public void wplwSynOrder(CoolOrder order) {
        Map<String, String> tmp = new HashMap<String, String>();
        tmp.put("orderNo", order.getOrderNo());
        
        Map<String, String> map = new HashMap<String, String>();
        map.put("service", "order_receiveOrder");
        map.put("accessCode", "ays");// 沃朴 给的 艾售账号
        map.put("jsonObj", JsonUtil.writeValueAsString(tmp));
        map.put("authCode", Coder.encodeMd5(JsonUtil.writeValueAsString(tmp) + "ce0bad8cc59b2044fefc9a2dbec240df"));// private
                                                                                                                    // key
        HttpUtils.httpsPost("http://test.wpwl.org/external/access/do", map);
    }
    
    /**
     * 删除订单
     */
    @Override
    public Record deleteCoolOrder(Map<String, Object> param) {
        Record record = new Record();
        Boolean success = false;
        if (param.get("ids") != null) {
            Integer[] ids = (Integer[])param.get("ids");
            for (int id : ids) {
                CoolOrder order = coolOrderMapper.getCoolOrderById(id);
                if (order != null && param.get("mId") != null) { // 会员删除
                    order.setIsDelM((byte)1);
                }
                if (order != null && param.get("sId") != null) { // 酷店删除
                    order.setIsDel((byte)1);
                }
                coolOrderMapper.updateCoolOrder(order);
                success = true;
            }
        }
        
        record.set("success", success);
        return record;
    }
    
    /**
     * 取消订单
     */
    @Override
    public Record cancelCoolOrder(CoolOrder coolOrder) {
        Record record = new Record();
        Boolean success = false;
        Integer id = coolOrder.getId();
        CoolOrder order = coolOrderMapper.getCoolOrderById(id);
        if (order != null) {
            order.setState(new Byte("99"));
            int result = coolOrderMapper.updateCoolOrder(order);
            // 处理商品库存和销量
            List<CoolOrderItem> itemList = coolOrderItemMapper.findCoolOrderItemByOrderNo(order.getOrderNo());
            for (CoolOrderItem item : itemList) {
                jdbcTemplate.update("update cool_product_gg set stock=stock+" + item.getCount() + ",sales=sales-"
                    + item.getCount() + " where id=?", item.getGid());
                jdbcTemplate.update("update cool_product set sales=sales-" + item.getCount() + " where id=?",
                    item.getgId());
            }
            if (result > 0) {
                success = true;
            } else {
                record.set("msg", "取消订单失败");
            }
        } else {
            record.set("msg", "订单不存在");
        }
        record.set("success", success);
        return record;
    }
    
    /**
     * 分页查询订单列表
     * 
     * @param orderSelect 查询条件
     * @return 分页信息
     */
    @Override
    public PageInfo<CoolOrder> getCoolOrderListPage(CoolOrderSelect orderSelect) {
        PageInfo<CoolOrder> pageInfo = new PageInfo<CoolOrder>();
        
        pageInfo.setRows(coolOrderMapper.getCoolOrderPageList(orderSelect.getRowBounds(), orderSelect));
        pageInfo.setTotal(coolOrderMapper.getCoolOrderPageListCount(orderSelect));
        
        if (orderSelect.isSearchDetail() && CollectionUtils.isNotEmpty(pageInfo.getRows())) {
            for (CoolOrder order : pageInfo.getRows()) {
                List<CoolOrderItem> itemList = coolOrderItemMapper.findCoolOrderItemByOrderNo(order.getOrderNo());
                int count = 0;
                for (CoolOrderItem item : itemList) {
                    count += item.getCount();
                }
                order.setCount(count);
                order.setItemList(itemList);
            }
        }
        return pageInfo;
    }
    
    /**
     * 查询订单流水
     * 
     * @param order 订单信息
     * @param jsonData 返回值
     */
    @Override
    public void getCoolOrderListSerial(CoolOrder order, JsonData jsonData) {
        List<CoolOrder> orderList = new ArrayList<CoolOrder>();
        orderList = coolOrderMapper.getCoolOrderList(order);
        
        jsonData.setRows(orderList);
    }
    
    /**
     * 通用更新
     * 
     * @param param 参数
     */
    public void updateOrder(CoolOrder param) {
        coolOrderMapper.updateCoolOrder(param);
    }
    
    /**
     * 统计商品销量排名
     */
    @Override
    public void getSumCoolProductSales(Map<String, Object> param, JsonData jsonData) {
        CoolOrderSelect c = new CoolOrderSelect();
        if (param.get("supplier") != null) {
            c.setSupplier(param.get("supplier").toString());
        }
        List<CoolProductSales> salesList = coolOrderItemMapper.getSumCoolProductSales(c);
        List<CoolProductSales> list = new ArrayList<CoolProductSales>();
        int count = 0; // 数量
        int countAll = 0; // 全部销数量
        BigDecimal total = new BigDecimal(0); // 金额
        BigDecimal totalAll = new BigDecimal(0); // 全部金额
        int i = 1;
        for (CoolProductSales sale : salesList) {
            if (StringUtils.isNotEmpty(sale.getpName())) {
                count += sale.getSales();
                total = total.add(sale.getTotal());
                sale.setId(i);
                list.add(sale);
                i++;
            } else {
                countAll = sale.getSales();
                totalAll = totalAll.add(sale.getTotal());
            }
        }
        if ((countAll - count) > 0) {
            CoolProductSales sale = new CoolProductSales();
            sale.setId(i);
            sale.setpName("其他");
            sale.setGid("");
            sale.setGg("其他");
            sale.setSales(countAll - count);
            sale.setTotal(totalAll.subtract(total));
            list.add(sale);
        }
        
        jsonData.setRows(list);
    }
    
    /**
     * 统计店铺销量排名
     */
    @Override
    public void getSumCoonShopSales(Map<String, Object> param, JsonData jsonData) {
        // 取截止到昨天
        List<CoolOrder> orderList = coolOrderMapper.getSumCoonShopSales(new CoolOrderSelect());
        List<CoolOrder> list = new ArrayList<CoolOrder>();
        BigDecimal total = new BigDecimal(0); // 销售额
        BigDecimal totalAll = new BigDecimal(0); // 全部销售额
        BigDecimal profit = new BigDecimal(0); // 佣金
        BigDecimal profitAll = new BigDecimal(0); // 全部佣金
        int i = 1;
        for (CoolOrder order : orderList) {
            if (StringUtils.isNotEmpty(order.getSupplier())) {
                total = total.add(order.getTotal());
                profit = profit.add(order.getSupplierProfit());
                order.setId(i); // 统计时订单主键无意义，只做页面展示时的序号
                list.add(order);
                i++;
            } else {
                totalAll = totalAll.add(order.getTotal());
                profitAll = profitAll.add(order.getSupplierProfit());
            }
        }
        if (totalAll.subtract(total).compareTo(new BigDecimal(0)) == 1) {
            CoolOrder orderOther = new CoolOrder();
            orderOther.setId(i);
            orderOther.setSupplier("其他");
            orderOther.setSupName("其他");
            orderOther.setTotal(totalAll.subtract(total));
            orderOther.setSupplierProfit(profitAll.subtract(profit));
            list.add(orderOther);
        }
        Map<String, BigDecimal> map = new HashMap<String, BigDecimal>();
        map.put("totalAll", totalAll);
        map.put("profitAll", profitAll);
        jsonData.setData(map);
        jsonData.setRows(list);
    }
    
    /**
     * 判断是否存在中文
     * 
     * @param str
     * @return
     */
    public static boolean isContainsChinese(String str) {
        String regEx = "[\u4e00-\u9fa5]";
        Pattern pat = Pattern.compile(regEx);
        Matcher matcher = pat.matcher(str);
        boolean flg = false;
        if (matcher.find()) {
            flg = true;
        }
        return flg;
    }
    
    @Override
    public void updateRunAccount(Date date) {
        SimpleDateFormat myFmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date2 = myFmt.format(date);
        List<CoonRunAccount> run = coonRunAccountMapper.findByEndDate(date2);
        for (CoonRunAccount r : run) {
            if (r != null) {
                CoonRunAccount rr = new CoonRunAccount();
                rr.setIsFreeze((byte)0);
                rr.setId(r.getId());
                coonRunAccountMapper.updateCoonRunAccount(rr);
                // CoolConfig cc = coolConfigMapper.getPercentageByDevide(r.getDevide());
                if (0 == r.getType()) {
                    CoonShop cs = coonShopMapper.getCoonShopById(r.getShopId());
                    if (cs != null && cs.getJjAmount().doubleValue() > r.getAmount().doubleValue()) {
                        jdbcTemplate.update("update coon_shop set all_amount=all_amount+?,nwd_amount=nwd_amount+?,recommend_amount=recommend_amount+?,jj_amount=jj_amount-? where id=?",
                            r.getAmount(),
                            r.getAmount(),
                            r.getAmount(),
                            r.getAmount(),
                            r.getShopId());
                    } else {
                        jdbcTemplate.update("update coon_shop set all_amount=all_amount+?,nwd_amount=nwd_amount+?,recommend_amount=recommend_amount+? where id=?",
                            r.getAmount(),
                            r.getAmount(),
                            r.getAmount(),
                            r.getShopId());
                    }
                } else {
                    jdbcTemplate.update("update coon_shop set all_amount=all_amount-?,nwd_amount=nwd_amount-?,recommend_amount=recommend_amount-?,jj_amount=jj_amount-? where id=?",
                        r.getAmount(),
                        r.getAmount(),
                        r.getAmount(),
                        r.getAmount(),
                        r.getShopId());
                }
            }
        }
    }
    
    /**
     * 分页查询订单列表
     * 
     * @param orderSelect 查询条件
     * @return 分页信息
     */
    @Override
    public Record getOrderListPage(CoolOrderSelect orderSelect) {
        Record record = new Record();
        boolean success = false;
        String supplier = orderSelect.getSupplier();
        Integer mId = orderSelect.getmId();
        if (StringUtils.isNotBlank(supplier) || mId != null) {
            // 订单中存的mid其实是userId，需要转换
            if (mId != null) {
                CoolMember member = coolMemberMapper.getCoolMemberById(mId);
                orderSelect.setmId(member.getUserId());
                // orderSelect.setmId(mId);
            }
            List<CoolOrder> list = coolOrderMapper.getCoolOrderPageList(orderSelect.getRowBounds(), orderSelect);
            int count = coolOrderMapper.getCoolOrderPageListCount(orderSelect);
            record.set("count", count);
            if (CollectionUtils.isNotEmpty(list)) {
                List<CoolOrder> orderList = new ArrayList<CoolOrder>();
                for (CoolOrder order : list) {
                    List<CoolOrderItem> itemList = coolOrderItemMapper.findCoolOrderItemByOrderNo(order.getOrderNo());
                    order.setItemList(itemList);
                    orderList.add(order);
                }
                record.set("orderList", orderList);
                success = true;
            } else {
                record.set("msg", "无数据");
            }
        } else {
            record.set("msg", "参数错误，酷店主键或者会员主键不能都为空");
        }
        record.set("success", success);
        return record;
    }
    
    /**
     * 查询订单详情
     * 
     * @param order 查询条件
     * @return 订单详情
     */
    @Override
    public Record getOrderDetail(CoolOrder order) {
        Record record = new Record();
        boolean success = false;
        Integer id = order.getId();
        if (id != null) {
            CoolOrder coolOrder = coolOrderMapper.getCoolOrderById(id);
            if (coolOrder != null) {
                List<CoolOrderItem> itemList = coolOrderItemMapper.findCoolOrderItemByOrderNo(coolOrder.getOrderNo());
                coolOrder.setItemList(itemList);
                record.set("order", coolOrder);
                success = true;
            } else {
                record.set("msg", "参数错误，无法获取订单信息");
            }
        } else {
            record.set("msg", "订单主键不能为空");
        }
        record.set("success", success);
        return record;
    }
    
    /**
     * 删除订单
     * 
     * @param order 查询条件
     * @return 是否删除成功
     */
    @Override
    public Record deleteOrder(CoolOrder order) {
        Record record = new Record();
        boolean success = false;
        Integer id = order.getId();
        if (id != null) {
            CoolOrder coolOrder = coolOrderMapper.getCoolOrderById(id);
            if (coolOrder != null) {
                String supplier = order.getSupplier();
                if (StringUtils.isNotEmpty(supplier)) { // 酷店删除
                    coolOrder.setIsDel(GeneralDef.BYTE_1);
                }
                Integer mId = order.getmId();
                if (mId != null) { // 会员删除
                    coolOrder.setIsDelM(GeneralDef.BYTE_1);
                }
                int result = coolOrderMapper.updateCoolOrder(coolOrder);
                if (result > 0) {
                    success = true;
                } else {
                    record.set("msg", "订单删除失败");
                }
            } else {
                record.set("msg", "参数错误，无法获取订单信息");
            }
        } else {
            record.set("msg", "订单主键不能为空");
        }
        record.set("success", success);
        return record;
    }
    
    /**
     * 分页获取一件代发进货单列表
     * 
     * @param order 查询条件
     * @return 进货单列表信息
     */
    @Override
    public Record getDistributionCartListPage(CoolDistributionCar coolDistributionCar) {
        Record record = new Record();
        boolean success = false;
        Integer userId = coolDistributionCar.getUserId();
        if (userId != null) {
            List<CoolDistributionCarInfo> list =
                coolDistributionCarMapper.findCoolDistributionCarInfoListPage(coolDistributionCar.getRowBounds(),
                    coolDistributionCar);
            if (CollectionUtils.isNotEmpty(list)) {
                List<CoolDistributionCarInfo> cartList = new ArrayList<CoolDistributionCarInfo>();
                for (CoolDistributionCarInfo info : list) {
                    if (info.getCxjg() != null) {
                        if (info.getCxjg().compareTo(new BigDecimal(0)) == 0) {
                            info.setPrice(info.getJg());
                        } else {
                            info.setPrice(info.getCxjg());
                        }
                    } else {
                        info.setPrice(info.getJg());
                    }
                    // 库存小于0 的取0
                    Float stock = info.getStock();
                    if (!(stock != null && stock > 0)) {
                        info.setStock(new Float(0));
                    }
                    cartList.add(info);
                }
                record.set("cartList", cartList);
                success = true;
            } else {
                record.set("msg", "无数据");
            }
        } else {
            record.set("msg", "用户主键不能为空");
        }
        record.set("success", success);
        return record;
    }
    
    /**
     * 分页获取一件代发购买过商品列表
     * 
     * @param coolOrderParam 查询条件
     * @return 商品列表信息
     */
    @Override
    public Record getBuyList(CoolOrderParam coolOrderParam) {
        Record record = new Record();
        boolean success = false;
        String supplier = coolOrderParam.getSupplier();
        if (StringUtils.isNotEmpty(supplier)) {
            List<CoolProductInfo> list =
                coolOrderMapper.getCoolProductInfoList(coolOrderParam.getRowBounds(), coolOrderParam);
            if (CollectionUtils.isNotEmpty(list)) {
                record.set("orderList", list);
                success = true;
            } else {
                record.set("msg", "无数据");
            }
        } else {
            record.set("msg", "参数错误，酷店主键不能为空");
        }
        record.set("success", success);
        return record;
    }
    
    /**
     * 更新订单
     * 
     * @param coolOrder 订单参数
     * @return 是否更新成功
     */
    @Override
    public Record updateOrderReceive(CoolOrder coolOrder) {
        Record record = new Record();
        boolean success = false;
        Integer id = coolOrder.getId();
        if (id != null) {
            coolOrder.setState(CoolOrder.ORDER_STATE_3);
            int result = coolOrderMapper.updateCoolOrder(coolOrder);
            if (result > 0) {
                success = true;
            } else {
                record.set("msg", "更新订单失败");
            }
        } else {
            record.set("msg", "参数错误，订单主键不能为空");
        }
        record.set("success", success);
        return record;
    }
    
    /**
     * 保存评价
     * 
     * @param coolOrderParam 评价参数
     * @return 是否保存成功
     */
    @Override
    public Record saveOrderReview(CoolOrderParam coolOrderParam) {
        Record record = new Record();
        boolean success = false;
        List<CoolProductReview> reviewList = coolOrderParam.getReviewList();
        if (CollectionUtils.isNotEmpty(reviewList)) {
            int result = 0;
            for (CoolProductReview review : reviewList) {
                result = 0;
                success = false;
                Integer mId = review.getmId();
                if (mId != null) {
                    CoolMember member = coolMemberMapper.getCoolMemberById(mId);
                    if (member != null) {
                        Integer gId = review.getgId();
                        if (gId != null) {
                            Integer oId = review.getoId();
                            if (oId != null) {
                                Integer scoreP = review.getScoreP();
                                Integer scoreB = review.getScoreB();
                                Integer scoreD = review.getScoreD();
                                if (scoreP != null && scoreB != null && scoreD != null) {
                                    String petname = member.getPetname();
                                    if (StringUtils.isNotEmpty(petname)) {
                                        review.setmName(CommonUtils.getEncodeName(petname));
                                    } else {
                                        review.setmName(CommonUtils.getEncodeMobile(member.getMobile()));
                                    }
                                    result = coolProductReviewMapper.saveCoolProductReview(review);
                                    if (result > 0) {
                                        // 更新订单状态
                                        CoolOrder order = coolOrderMapper.getCoolOrderById(oId);
                                        order.setState(CoolOrder.ORDER_STATE_4);
                                        coolOrderMapper.updateCoolOrder(order);
                                        success = true;
                                    } else {
                                        record.set("msg", "商品主键为" + gId + "的评价保存失败");
                                        break;
                                    }
                                } else {
                                    record.set("msg", "参数错误，评价不能为空");
                                }
                            } else {
                                record.set("msg", "参数错误，订单主键不能为空");
                            }
                        } else {
                            record.set("msg", "参数错误，商品主键不能为空");
                        }
                    } else {
                        record.set("msg", "参数错误，无法根据会员主键获得会员信息");
                    }
                } else {
                    record.set("msg", "参数错误，会员主键不能为空");
                }
            }
        } else {
            record.set("msg", "参数错误，评价参数错误");
        }
        record.set("success", success);
        return record;
    }
    
    /**
     * 保存购物车
     * 
     * @param coonShopCartParam 购物车参数
     * @return 是否保存成功
     */
    @Override
    public Record saveShopCart(CoonShopCartParam coonShopCartParam) {
        Record record = new Record();
        boolean success = false;
        List<CoonShopcart> cartList = coonShopCartParam.getShopCartList();
        if (CollectionUtils.isNotEmpty(cartList)) {
            int result = 0;
            for (CoonShopcart cart : cartList) {
                result = 0;
                success = false;
                String userId = cart.getUserId();
                if (StringUtils.isNotEmpty(userId)) {
                    String pId = cart.getpId();
                    if (StringUtils.isNotEmpty(pId)) {
                        String gId = cart.getgId();
                        if (StringUtils.isNotEmpty(gId)) {
                            Integer quantity = cart.getQuantity();
                            if (quantity != null) {
                                String id = cart.getId();
                                // 有主键时直接修改数量
                                if (StringUtils.isNotEmpty(id)) {
                                    result = coonShopcartMapper.updateCoonShopcart(cart);
                                    if (result > 0) {
                                        success = true;
                                    } else {
                                        record.set("msg", "修改购物车失败");
                                        break;
                                    }
                                } else {
                                    String sId = cart.getsId();
                                    if (StringUtils.isNotEmpty(sId)) {
                                        // 根据用户、商品、规格获取购物车信息
                                        CoonShopcart sCart = coonShopcartMapper.getCoonShopcart(cart);
                                        if (sCart != null) { // 增加数量
                                            sCart.setQuantity(quantity + sCart.getQuantity());
                                            result = coonShopcartMapper.updateCoonShopcart(sCart);
                                        } else { // 新增购物车
                                            cart.setId(CommonUtils.uuid());
                                            result = coonShopcartMapper.saveCoonShopcart(cart);
                                        }
                                        if (result > 0) {
                                            success = true;
                                        } else {
                                            record.set("msg", "商品主键为" + pId + "，规格主键为" + gId + "的商品添加购物车失败");
                                            break;
                                        }
                                    } else {
                                        record.set("msg", "参数错误， 酷店主键不能为空");
                                    }
                                }
                                
                                /**
                                 * if (StringUtils.isEmpty(id)) { cart.setId(CommonUtils.uuid()); result =
                                 * coonShopcartMapper.saveCoonShopcart(cart); if (result > 0) { success = true; } else {
                                 * record.set("msg", "商品主键为" + pId + "，规格主键为" + gId + "的商品添加购物车失败"); break; } } else {
                                 * result = coonShopcartMapper.updateCoonShopcart(cart); if (result > 0) { success =
                                 * true; } else { record.set("msg", "商品主键为" + pId + "，规格主键为" + gId + "的商品更新购物车失败");
                                 * break; } }
                                 */
                            } else {
                                record.set("msg", "参数错误， 数量不能为空");
                            }
                        } else {
                            record.set("msg", "参数错误， 商品规格主键不能为空");
                        }
                    } else {
                        record.set("msg", "参数错误， 商品主键不能为空");
                    }
                } else {
                    record.set("msg", "参数错误， 用户主键不能为空");
                }
            }
        } else {
            record.set("msg", "参数错误");
        }
        record.set("success", success);
        return record;
    }
    
    /**
     * 获取购物车列表信息
     * 
     * @param coonShopcart 购物车参数
     * @return 购物车列表
     */
    @Override
    public Record getShopCartList(CoonShopcart coonShopcart) {
        Record record = new Record();
        boolean success = false;
        String userId = coonShopcart.getUserId();
        if (StringUtils.isNotEmpty(userId)) {
            String pName = coonShopcart.getpName();
            if (StringUtils.isNotEmpty(pName)) {
                coonShopcart.setpName("%" + pName + "%");
            }
            List<CoonShopCartInfo> shopCartList = coonShopcartMapper.findCoonShopCartInfoList(coonShopcart);
            if (CollectionUtils.isNotEmpty(shopCartList)) {
                List<CoonShopCartInfo> list = new ArrayList<CoonShopCartInfo>();
                // 运费设为0
                for (CoonShopCartInfo info : shopCartList) {
                    info.setPsPrice(new BigDecimal(0.00));
                    
                    // 库存小于0 的取0
                    Integer stock = info.getStock();
                    if (!(stock != null && stock > 0)) {
                        info.setStock(0);
                    }
                    list.add(info);
                }
                record.set("shopCartList", list);
                success = true;
            } else {
                record.set("msg", "无数据");
            }
        } else {
            record.set("msg", "参数错误，用户主键不能为空");
        }
        record.set("success", success);
        return record;
    }
    
    /**
     * 删除购物车
     * 
     * @param coonShopcart 购物车参数
     * @return 是否删除成功
     */
    @Override
    public Record deleteShopCart(CoonShopcart coonShopcart) {
        Record record = new Record();
        boolean success = false;
        String ids = coonShopcart.getIds();
        if (StringUtils.isNotEmpty(ids)) {
            String[] idStr = ids.split(",");
            int result = 0;
            for (int i = 0; i < idStr.length; i++) {
                result = 0;
                success = false;
                result = coonShopcartMapper.deleteCoonShopcart(idStr[i]);
                if (result > 0) {
                    success = true;
                } else {
                    record.set("msg", "主键为 " + idStr[i] + "的购物车商品信息删除失败");
                    break;
                }
            }
        } else {
            record.set("msg", "参数错误，购物车主键不能为空");
        }
        record.set("success", success);
        return record;
    }
    
    @Override
    public Record updateCoolOrderNbyb(String orderNo) {
        // TODO: 临时查询订单的接口，之前的ws接口只会在货物放行时才能获取单号
        String getUrl = "http://kf.celeka.cn/queryoid.php?orderno=";
        String result = HttpUtils.httpGet(getUrl + orderNo);
        
        // LogisticsWebServiceSoap serviceSoap = new LogisticsWebService().getLogisticsWebServiceSoap12();
        // String result =
        // serviceSoap.getKJB2CLogisticsInfo(YoubeiService.ERPKEY,
        // YoubeiService.ERPSECRET,
        // YoubeiService.SHOPID,
        // orderNo);
        // StringBuilder builder = new StringBuilder();
        // // 记录发送日志
        // builder.append("parameter: orderNo=").append(orderNo).append(System.getProperty("line.separator", "\n"));
        // builder.append("response:  ").append(result);
        //
        // SendMessageLogUtil.info(builder.toString());
        OrderSearchResult orderResult = JaxbUtil.converyToJavaBean(result, OrderSearchResult.class);
        
        Record record = new Record();
        record.set("result", orderResult);
        
        if ("T".equals(orderResult.getResult()) && orderResult.getBody() != null
            && orderResult.getBody().getLogNo() != null && orderResult.getBody().getLogName() != null) {
            CoolOrder order = new CoolOrder();
            order.setOrderNo(orderNo);
            order.setState(CoolOrder.ORDER_STATE_2);
            order.setPsCode(orderResult.getBody().getLogNo());
            order.setPsfs(orderResult.getBody().getLogName());
            record.set("success", coolOrderMapper.updateCoolOrder(order) > 0);
        }
        
        return record;
    }
    
    /**
     * 获取订单各状态数量接口
     * 
     * @param param 参数
     * @return 状态数量
     */
    @Override
    public Record getStateCount(CoolOrderParam param) {
        Record record = new Record();
        boolean success = false;
        String mId = param.getmId();
        if (StringUtils.isNotEmpty(mId)) {
            Byte orderType = param.getOrderType();
            if (orderType != null) {
                int dfkCount =
                    coolOrderMapper.getOrderCountBymIdAndState(Integer.valueOf(mId),
                        Byte.toString(CoolOrder.ORDER_STATE_0),
                        orderType.intValue()); // 待付款
                int dfhCount =
                    coolOrderMapper.getOrderCountBymIdAndState(Integer.valueOf(mId),
                        Byte.toString(CoolOrder.ORDER_STATE_1) + "%",
                        orderType.intValue()); // 待发货
                int dshCount =
                    coolOrderMapper.getOrderCountBymIdAndState(Integer.valueOf(mId),
                        Byte.toString(CoolOrder.ORDER_STATE_2),
                        orderType.intValue()); // 待收货
                int tkCount =
                    coolOrderMapper.getOrderCountBymIdAndState(Integer.valueOf(mId),
                        Byte.toString(CoolOrder.ORDER_STATE_5),
                        orderType.intValue()); // 退款维权
                record.set("dfkCount", dfkCount);
                record.set("dfhCount", dfhCount);
                record.set("dshCount", dshCount);
                record.set("tkCount", tkCount);
                success = true;
            } else {
                record.set("msg", "参数错误，订单类型不能为空");
            }
        } else {
            record.set("msg", "参数错误，会员主键不能为空");
        }
        record.set("success", success);
        return record;
    }
    
    @Override
    public Record saveCoolOrder(CoolOrder order) {
        Record record = new Record();
        int state = 0;
        if (order.getItems() != null) {
            BigDecimal totalPrice = order.getTotal(); // 支付总额
            if (order.getPsPrice() != null) {
                totalPrice = totalPrice.subtract(order.getPsPrice()); // 减去运费
            }
            
            for (CoolOrderItem item : order.getItems()) {
                BigDecimal price = totalPrice.divide(new BigDecimal(order.getItems().size()), 2, RoundingMode.HALF_UP);
                // 映射在第三方平台做
                if (item.getGid() != null) {
                    order.setOrderNo(OrderUtil.generateOrderNo(item.getGid()));
                    item.setOrderNo(order.getOrderNo());
                    saveOrderItem(item, price);
                } else if (item.getCode() != null) { // 第三方平台商品编码
                    CoolProductMap productMap = new CoolProductMap();
                    productMap.setWid(item.getCode());// 外部商品编码
                    productMap.setWsid(order.getSupplier());// 酷店id
                    productMap = coolProductMapMapper.getCoolProductMap(productMap);
                    if (productMap.getGroupId() != null) { // 组合商品
                        List<CoolProductGroup> groupList =
                            coolProductGroupMapper.findCoolProductGroupByGroupId(productMap.getGroupId().toString());
                        if (CollectionUtils.isEmpty(groupList)) {
                            throw new RuntimeException("exception.product.null");
                        }
                        int index = 0;
                        BigDecimal sumPrice = BigDecimal.ZERO;
                        order.setOrderNo(OrderUtil.generateOrderNo(groupList.get(0).getgId()));
                        for (CoolProductGroup group : groupList) {
                            index++;
                            BigDecimal singleprice;
                            if (index == groupList.size()) {
                                singleprice = price.subtract(sumPrice);
                            } else {
                                singleprice = price.divide(new BigDecimal(groupList.size()), 0, RoundingMode.DOWN);
                                sumPrice = sumPrice.add(singleprice);
                            }
                            CoolOrderItem groupItem = new CoolOrderItem();
                            groupItem.setOrderNo(order.getOrderNo());
                            groupItem.setCount(group.getCount());
                            groupItem.setGid(group.getgId());
                            saveOrderItem(groupItem, singleprice);
                        }
                    } else { // 非组合商品
                        item.setGid(productMap.getGid());
                        order.setOrderNo(OrderUtil.generateOrderNo(item.getGid()));
                        item.setOrderNo(order.getOrderNo());
                        saveOrderItem(item, price);
                    }
                }
            }
            CoolShopPush csp = new CoolShopPush();
            csp.setCeatetime(new Date());
            csp.setShopId(order.getSupplier());
            csp.setOrderNo(order.getOrderOldno());
            coolShopPushMapper.saveCoolShopPush(csp);
            // 保存订单
            state = coolOrderMapper.saveCoolOrder(order);
        }
        record.set("success", state > 0);
        return record;
    }
    
    private void saveOrderItem(CoolOrderItem item, BigDecimal price) {
        CoolProductGg gg = coolProductGgMapper.getCoolProductGgById(item.getGid());
        CoolProduct product = coolProductMapper.getCoolProductById(gg.getGoodsId());
        
        item.setgId(product.getId());
        
        item.setName(product.getNameEn());
        if (StringUtils.isNotEmpty(gg.getLogo())) {
            item.setLogo(gg.getLogo());
        } else {
            item.setLogo(product.getLogo());
        }
        item.setGg(gg.getGg());
        item.setbId(product.getbId());
        
        item.setPrice(price.divide(new BigDecimal(item.getCount()), 2, RoundingMode.HALF_UP));
        
        // 保存订单明细
        coolOrderItemMapper.saveCoolOrderItem(item);
        // 处理商品库存和销量
        jdbcTemplate.update("update cool_product_gg set stock=stock-" + item.getCount() + ",sales=sales+"
            + item.getCount() + " where id=?",
            item.getGid());
        jdbcTemplate.update("update cool_product set sales=sales+" + item.getCount() + " where id=?", item.getgId());
    }
    
    @Override
    public void validateOrder(CoolOrder order) {
        if (StringUtils.isEmpty(order.getOrderOldno())) {
            throw new RuntimeException(MessageUtil.getMessage("exception.access.param-notnull", "orderOldno"));
        }
        if (StringUtils.isEmpty(order.getIdcard())) {
            throw new RuntimeException(MessageUtil.getMessage("exception.access.param-notnull", "idcard"));
        }
        if (StringUtils.isEmpty(order.getLinkman())) {
            throw new RuntimeException(MessageUtil.getMessage("exception.access.param-notnull", "linkman"));
        }
        if (StringUtils.isEmpty(order.getLocationP())) {
            throw new RuntimeException(MessageUtil.getMessage("exception.access.param-notnull", "locationP"));
        }
        if (StringUtils.isEmpty(order.getLocationC())) {
            throw new RuntimeException(MessageUtil.getMessage("exception.access.param-notnull", "locationC"));
        }
        if (StringUtils.isEmpty(order.getLocationA())) {
            throw new RuntimeException(MessageUtil.getMessage("exception.access.param-notnull", "locationA"));
        }
        if (StringUtils.isEmpty(order.getAddress())) {
            throw new RuntimeException(MessageUtil.getMessage("exception.access.param-notnull", "address"));
        }
        if (StringUtils.isEmpty(order.getMobile())) {
            throw new RuntimeException(MessageUtil.getMessage("exception.access.param-notnull", "mobile"));
        }
        if (order.getTotal() == null) {
            throw new RuntimeException(MessageUtil.getMessage("exception.access.param-notnull", "total"));
        }
        if (CollectionUtils.isEmpty(order.getItems())) {
            throw new RuntimeException(MessageUtil.getMessage("exception.access.param-notnull", "itemList"));
        }
        for (CoolOrderItem item : order.getItems()) {
            if (StringUtils.isEmpty(item.getCode()) && item.getGid() == null) {
                throw new RuntimeException(MessageUtil.getMessage("exception.access.param-notnull", "gid or code"));
            }
            if (item.getCount() == null) {
                throw new RuntimeException(MessageUtil.getMessage("exception.access.param-notnull", "count"));
            }
        }
        // 验证订单是否存在
        List<CoolOrder> oList = coolOrderMapper.getCoolOrderByOrderOldNoList(order.getOrderOldno(), "");
        if (CollectionUtils.isNotEmpty(oList)) { // 重复不导入
            throw new RuntimeException("订单已存在，不要重复导入");
        }
    }
    
    @Override
    public boolean updateOrderPartByOrderNo(CoolOrder order) {
        return 1 == coolOrderMapper.updateOrderPartByOrderNo(order);
    }
    
    @Override
    public List<CoolOrderWayBillReturn> getWayBill(CoolOrderWayBill order) {
        String[] orderOldNo = null;
        if (order.getOrderOldno() != null) {
            orderOldNo = order.getOrderOldno().split(",");
        }
        if (order.getBeginTime() != null) {
            String begin = order.getBeginTime();
            order.setBeginTime(begin.substring(0, 4) + "-" + begin.substring(4, 6) + "-" + begin.substring(6, 8)
                + " 00:00:00");
        }
        if (order.getEndTime() != null) {
            String end = order.getEndTime();
            order.setEndTime(end.substring(0, 4) + "-" + end.substring(4, 6) + "-" + end.substring(6, 8) + " 23:59:59");
        }
        return coolOrderMapper.getWayBill(order, orderOldNo);
    }
    
    @Override
    public List<CoolOrderExternal> getOrderExternalByOrderOldNo(String shopId, String orderNo) {
        return coolOrderMapper.getOrderExternalByOrderOldNo(shopId, orderNo);
    }
    /**
     * 保存post失败记录
     */
    @Override
    public int saveUnSuccessOrderReturn(OrderReturn orderReturn)
    {
        /*int result=0;
    	result=this.coolOrderMapper.checkUnSuccessIsExist(orderReturn);
    	if(result>0)
    	{
    		return 1;
    	}else
    	{
    		
    	}*/
    	return coolOrderMapper.saveUnSuccessOrderReturn(orderReturn);
    }
    @Override
    public boolean confirmOrder(CoolOrder order) {
        List<CoolOrderExternal> orders =
            coolOrderMapper.getOrderExternalByOrderOldNo(order.getSupplier(), order.getOrderOldno());
        boolean flag = false;
        if (CollectionUtils.isEmpty(orders)) {
            throw new RuntimeException(MessageUtil.getMessage("exception.access.param-wrong",
                "accessCode or orderOldNo"));
        } else {
            Byte state = orders.get(0).getState();
            if ((new Byte("2")).equals(state) || (new Byte("3")).equals(state)) {
                order.setState(new Byte("3"));
                flag = (1 == coolOrderMapper.updateStateByOlderOldNo(order));
            }
        }
        return flag;
    }
    
    @Override
    public boolean cancelOrder(CoolOrder order) {
        List<CoolOrderExternal> orders =
            coolOrderMapper.getOrderExternalByOrderOldNo(order.getSupplier(), order.getOrderOldno());
        boolean flag = false;
        if (CollectionUtils.isEmpty(orders)) {
            throw new RuntimeException(MessageUtil.getMessage("exception.access.param-wrong",
                "accessCode or orderOldNo"));
        } else {
            Byte state = orders.get(0).getState();
            if ((new Byte("0")).equals(state) || (new Byte("1")).equals(state) || (new Byte("99")).equals(state)) {
                order.setState(new Byte("99"));
                flag = (1 == coolOrderMapper.updateStateByOlderOldNo(order));
            }
        }
        return flag;
    }
    
    @Override
    public JsonData buyTradeNo(String... orderNos) {
        if (ArrayUtils.isEmpty(orderNos)) {
            throw new RuntimeException("exception.order.null");
        }
        CoolOrder[] orderArray = new CoolOrder[orderNos.length];
        int index = 0;
        for (String orderNo : orderNos) {
            CoolOrder order = coolOrderMapper.getCoolOrderByOrderNo(orderNo);
            if (order == null || order.getLinkman() == null || order.getMobile() == null || order.getIdcard() == null
                || order.getTotal() == null) {
                throw new RuntimeException("exception.order.null");
            }
            orderArray[index++] = order;
        }
        
        ZjysBuyTradeNoRes result = zjysService.buyTradeNo(orderArray);
        JsonData data = new JsonData();
        data.setSuccess(result.getResult());
        data.setMsg(result.getErrorMsg());
        data.setData(result);
        if (result.getContent() != null) {
            for (ZjysOrderInfo zjysOrderInfo : result.getContent()) {
                if ("Success".equals(zjysOrderInfo.getResult())) {
                    CoolOrder order = new CoolOrder();
                    order.setState(CoolOrder.ORDER_STATE_1);
                    order.setZffs(CoolOrder.ZFFS_6);
                    order.setArrears(0); // 不欠费订单
                    order.setPayState((byte)1); // 已报关订单
                    order.setTradeNo(zjysOrderInfo.getWaterNumber()); // 支付流水号
                    order.setOrderNo(zjysOrderInfo.getOutTrandNo()); // 订单号
                    order.setPayTime(new Date()); // 更新支付时间
                    
                    coolOrderMapper.updateCoolOrder(order);
                } else if (result.getContent().size() == 1) {
                    data.setSuccess(false);
                    data.setMsg(zjysOrderInfo.getResult());
                }
            }
        }
        return data;
    }
    
    /**
     * 根据物流单号查询订单编号
     * 
     * @param psCode 物流单号
     * @return 订单编号
     */
    @Override
    public Record getOrderNoByPsCode(String psCode) {
        Record record = new Record();
        boolean success = false;
        List<CoolOrder> list = coolOrderMapper.getOrderByPsCode(psCode);
        if (CollectionUtils.isNotEmpty(list)) {
            CoolOrder order = list.get(0);
            record.set("orderNo", order.getOrderNo());
            success = true;
        } else {
            throw new RuntimeException("exception.order.null");
        }
        record.set("success", success);
        return record;
    }
    
    /**
     * 修改订单（订单发货）0 自提，30杭州海关
     */
    @Override
    public Record updateZrCoolOrderDelivery(Map<String, Object> param) {
        Record record = new Record();
        CoolOrder orderR = getZrCoolOrderDetailById(Integer.valueOf(param.get("id").toString()));
        
        String fhfs = param.get("fhfs").toString();
        int resultUpdate = 0;
        
        if ("0".equals(fhfs)) {
            orderR.setFhfs(Byte.parseByte(fhfs));
            CoolOrder order = new CoolOrder();
            order.setState(CoolOrder.ORDER_STATE_2);
            order.setPsfs("自提");
            order.setFhfs((byte)0);
            order.setUpdatetime(new Timestamp(System.currentTimeMillis()));
            
            order.setId(orderR.getId());
            resultUpdate = coolOrderMapper.updateCoolOrder(order);
            record.set("success", resultUpdate > 0 ? true : false);
            return record;
        } else if ("30".equals(fhfs)) { // 杭州海关
            if("000".equals(sendToDtw(orderR))) {// 通知大田物流，综合订单
             if("000".equals(sendPersonalToDtw(orderR))) {//给大田物流发送个人申报
                if(sendToYhg(orderR)) {// 易慧金报关
                	 sendToHzhg(orderR);// 发送给海关
                	  CoolOrder order = new CoolOrder();
                      order.setState(CoolOrder.ORDER_STATE_11);
                      order.setPsfs("杭州海关");
                      order.setFhfs((byte)11);
                      order.setUpdatetime(new Timestamp(System.currentTimeMillis()));
                      order.setId(orderR.getId());
                      resultUpdate = coolOrderMapper.updateCoolOrder(order);
                      record.set("success", resultUpdate > 0 ? true : false);
                	}
                } else {
                	record.set("success",false);
                }
            }
          
            return record;
        }
        
        return record;
    }
    
    private CoolOrder getZrCoolOrderDetailById(Integer id) {
    	CoolOrder coolOrder = coolOrderMapper.getCoolOrderById(id);
    	if(coolOrder!=null) {
    		coolOrder.setHzItem(coolOrderItemMapper.getHzItemByOrderNo(coolOrder.getOrderNo()));
    	}
		return coolOrder;
	}

	private String  sendPersonalToDtw(CoolOrder orderR) {
    	SendPresonalInfo s = new SendPresonalInfo();
		s.setPassKey("6442305C-5A31-43BB-B36D-C73FB1EE14EC");
		s.setMsgid(orderR.getOrderNo());
		s.setConsignee(orderR.getLinkman());
		s.setCurrCode("142");/**成交币值 1**/
		s.seteCommerceCode("093791963");
		s.seteCommerceName("上海琨铭文化传播有限公司");
		//s.setGrossWeight("1.1");
		s.setImportType("1");	/**进口类型（0一般进口，1保税进口）**/
		s.setMainGName(orderR.getOrderNo());
		s.setOrderNo(orderR.getOrderNo());
		s.setPackNo("1");
		s.setPaperNumber(orderR.getIdcard());/**zhengjian**/
		s.setPaperType("1");/**默认是1，身份证**/
		s.setSenderCountry("502");/**发件人国别（三字代码）**/
		s.setTradeCountry("601");/*贸易国别（起运地三字代码）*/
		s.setWorth(orderR.getTotal().subtract(orderR.getPsPrice()).subtract(orderR.getTaxPrice()).toString());/*价值*/
		s.setSenderName("震荣优品");
		SendPresonalItems si = new SendPresonalItems();
		List<HzCoolOrderItem> itemList = orderR.getHzItem();
		HzCoolOrderItem item = null;
		for(int i=0,len=itemList.size();i<len;i++) {
			item = itemList.get(i);
			si.setCodeTs(item.getCodeTs());
			si.setDeclareCount(item.getCount().toString());
			si.setFirstCount(item.getCount().toString());
			si.setDeclPrice(item.getPrice().toString());
			si.setDeclTotalPrice((item.getPrice().multiply(new BigDecimal(item.getCount()))).toString());
			si.setFirstUnit(item.getUnit());
			si.setGoodsItemNo(item.getgId().toString());
			si.setGoodsModel(item.getGg());//规格型号
			si.setGoodsName(item.getName());
			si.setGoodsOrder(String.valueOf(i));
			si.setGoodsUnit(item.getUnit());
			si.setOriginCountry("142");/**产销国代码(三字代码)**/
			si.setTradeCurr("142");
			si.setTradeTotal((item.getPrice().multiply(new BigDecimal(item.getCount()))).toString());/*成交总价（包裹实际成交的金额）*/
			si.setUseTo("11");
		}
		List<SendPresonalItems> sis = new ArrayList<SendPresonalItems>();
		sis.add(si);
		s.setItems(sis);
		String jsonObj = JsonUtil.writeValueAsString(s);
		System.out.println(jsonObj);
		String result = HttpUtils.httpPost(
				config.getServiceDomain()+"/logistics/dtw/sendPersonalInfo",jsonObj);
		SendPersonalResponse spr = JsonUtil.readValue(result, SendPersonalResponse.class);
		return spr.getErrCode();	
	}

	// 将订单发送给大田物流
    private String sendToDtw(CoolOrder orderR) {
        MultipleRequest qo = new MultipleRequest();
        // SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        qo.setLogisCompanyCode("WL15041401");
        qo.setLogisCompanyName("百世物流科技（中国）有限公司");
        qo.setConsignee(orderR.getLinkman());
        qo.setConsigneeAdd(orderR.getAddress());
        qo.setConsigneeCity(orderR.getLocationC());
        qo.setConsigneeCountry("中国");
        qo.setConsigneeDistrict(orderR.getLocationA());
        qo.setConsigneeMobile(orderR.getMobile());
        qo.setConsigneePro(orderR.getLocationP());
        qo.setConsigneeTel(orderR.getMobile());
        qo.setConsigneeZip(orderR.getZipcode());
        qo.seteCommerceCode("093791963");
        qo.seteCommerceName("上海琨铭文化传播有限公司");
        qo.setOrderNo(orderR.getOrderNo());
        qo.setMsgid(orderR.getOrderNo());
        qo.setPayCompanyCode("PTE51001409230000001");
        qo.setPayNumber(orderR.getTradeNo());
        qo.setPayType("03");
        qo.setPurchaserId(orderR.getmId().toString());
        qo.setShipper("震荣优品");
        qo.setShipperAddress("江干区下沙经济技术开发区杭州出口加工区，泰山路23号2-005");
        qo.setShipperCountry("中国");
        qo.setShipperAddress("浙江");
        qo.setShipperCity("杭州市");
        qo.setShipperZip("310000");
        qo.setOrderGoodsAmount(orderR.getTotal().subtract(orderR.getPsPrice()).subtract(orderR.getTaxPrice()).toString());
        qo.setOrderTotalAmount(orderR.getTotal().toString());
        qo.setTotalAmount(orderR.getTotal().subtract(orderR.getPsPrice()).subtract(orderR.getTaxPrice()).toString());
        qo.setTotalCount("1");
        List<MultipleItems> qs = new ArrayList<MultipleItems>();
        for (HzCoolOrderItem orderItem : orderR.getHzItem()) {
            MultipleItems qi = new MultipleItems();
            qi.setAmount(orderItem.getPrice().toString());
            qi.setCurrency("142");
            qi.setQty(orderItem.getCount().toString());
            qi.setUnit(orderItem.getGg());
            qi.setSupplier("C3823699");
            qi.setSpec(orderItem.getGg());
            qi.setPartName(orderItem.getName());
            qi.setPartno(orderItem.getgId().toString());
            qs.add(qi);
        }
        qo.setItems(qs);
        // String result =config.getServiceDomain()
        String jsonObj = JsonUtil.writeValueAsString(qo);
        System.out.println(jsonObj);
        String result = HttpUtils.httpPost(config.getServiceDomain()+"/logistics/dtw/multipleOrders",jsonObj );
        MultipleResponse mr = JsonUtil.readValue(result, MultipleResponse.class);
        return mr.getErrCode();
    }
    
    // 易慧金报关
    private boolean sendToYhg(CoolOrder orderR) {
        EhkingCustomsRequest request = new EhkingCustomsRequest();
        request.setPaySerialNumber(orderR.getTradeNo());
        request.setCustomsInfos(new ArrayList<EhkingCustomsInfo>());
        EhkingCustomsInfo info = new EhkingCustomsInfo();
        info.setAmount(orderR.getTotal().longValue());
        info.setFreight(orderR.getPsPrice().multiply(new BigDecimal(100)).longValue());
        info.setGoodsAmount(orderR.getTotal().multiply(new BigDecimal(100)).longValue());
        request.getCustomsInfos().add(info);
        request.setPayer(new EhkingCustomsPayer());
        request.getPayer().setPayerName(orderR.getLinkman());
        request.getPayer().setIdNum(orderR.getIdcard());
        request.getPayer().setPhoneNum(orderR.getMobile());
        String result = HttpUtils.httpPost(config.getServiceDomain()+"/pay/ehking/sendOrder", JsonUtil.writeValueAsString(request));
        JsonData jsonObj = JsonUtil.readValue(result, JsonData.class);
        return jsonObj.getSuccess();
    }
    
    // 杭州海关报关
    private void sendToHzhg(CoolOrder orderR) {
        CrossBorderServiceImpl impl = new CrossBorderServiceImpl();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        OrderFiling orderFiling = new OrderFiling();
        orderFiling.setBusinessNo(orderR.getOrderNo());
        orderFiling.setIeFlag("I");
        orderFiling.setPayType("03");
        orderFiling.setPayNumber(orderR.getTradeNo());
        orderFiling.setOrderTotalAmount(orderR.getTotal());
        orderFiling.setTotalAmount(orderR.getTotal().subtract(orderR.getTaxPrice()).subtract(orderR.getPsPrice()));
        orderFiling.setOrderGoodsAmount(orderR.getTotal().subtract(orderR.getTaxPrice()).subtract(orderR.getPsPrice()));
        orderFiling.setOrderNo(orderR.getOrderNo());
        orderFiling.setOrderTaxAmount(orderR.getTaxPrice());
        orderFiling.setFeeAmount(orderR.getPsPrice());
        orderFiling.setTradeTime(sdf.format(orderR.getCreatetime()));
        orderFiling.setCurrCode("142");
        orderFiling.setConsigneeTel(orderR.getMobile());
        orderFiling.setConsignee(orderR.getLinkman());
        orderFiling.setConsigneeAddress(orderR.getLocationP() + orderR.getLocationC() + orderR.getLocationA()
            + orderR.getAddress());
        // 发货人
        orderFiling.setSenderCountry("142");
        orderFiling.setSenderName("震荣优品");
        orderFiling.setPurchaserId(orderR.getmId().toString());
        orderFiling.setLogisCompanyCode("WL15041401");
        orderFiling.setLogisCompanyName("百世物流科技（中国）有限公司");
        orderFiling.setTotalCount(1);
        orderFiling.setUserProcotol("正式运营");
        
        // 商品详情
        List<OrderFilingDetail> details = new ArrayList<OrderFilingDetail>();
        for (HzCoolOrderItem orderItem : orderR.getHzItem()) {
            OrderFilingDetail detail = new OrderFilingDetail();
            detail.setGoodsName(orderItem.getName());
            detail.setCodeTs(orderItem.getCodeTs());
            detail.setGoodsModel(orderItem.getGg());
            detail.setOriginCountry(orderItem.getTradeCountry());
            detail.setUnitPrice(orderItem.getPrice());
            detail.setGoodsCount(new BigDecimal(orderItem.getCount()));
             detail.setGoodsUnit(orderItem.getUnit());
            details.add(detail);
        }
        orderFiling.setDetails(details);
        orderFiling.setPurchaserName(orderR.getLinkman());
        orderFiling.setPurchaserTelNumber(orderR.getMobile());
        orderFiling.setPurchaserPaperType("01");
        orderFiling.setPurchaserPaperNumber(orderR.getIdcard());
        orderFiling.setPurchaserAddress(orderR.getLocationP() + orderR.getLocationC() + orderR.getLocationA()
            + orderR.getAddress());
        impl.orderRecord(orderFiling);
    }
    
    public void sendMsg(CoolOrder orderR, Map<String, Object> param) {
        // 发送物流消息给客户
        CoolMessage coolMessage = new CoolMessage();
        coolMessage.setContent("【小酷儿】订单" + orderR.getOrderNo() + "已发货了，" + "物流消息请点击:"
            + "<a href='http://m.i-coolshop.com/common/searchWapExpress/" + orderR.getId() + "'>"
            + "http://m.i-coolshop.com/common/searchWapExpress/" + orderR.getId() + "</a>" + "。客服电话4009693356，感谢您的使用！");
        coolMessage.setClick(0);
        coolMessage.setType("2");
        coolMessage.setBelongId(orderR.getOrderNo());
        coolMessage.setUserId(orderR.getmId());
        coolMessage.setSendtime(new Date());
        coolMessage.setOperatorId(Integer.valueOf(param.get("operatorId").toString()));
        messageMapper.saveCoolMessage(coolMessage);
    }
    
    @Override
    public Record getCoolOrderFare(Map<String, Object> param) {
        // TODO Auto-generated method stub
        Record record = new Record();
        String pId = param.get("pId").toString();
        String targetAddress = param.get("targetAddress").toString();// 收货地
        CoolStockDepot coolStockDepot = coolStockDepotMapper.getCoolStockDepotProvinceById(Integer.valueOf(pId));
        Double sumPrice = 0.0;
        String startAddress = "";// 发货地所在省份
        StringBuffer msg = new StringBuffer();
        boolean result = true;
        if (coolStockDepot != null) {
            startAddress = coolStockDepot.getProvince();
            CoonFareTempProByGetFare coonFareTempProByGetFare =
                coonFareTempProByGetFareMapper.getCoonFareTempProByGetFare(startAddress, targetAddress);
            if (coonFareTempProByGetFare != null) {
                CoolProductGg productGg;
                Double totalWeight = 0.0; // 订单总重量，单位克
                productGg = coolProductGgMapper.getCoolProductGgByPId(Integer.valueOf(pId));
                totalWeight += (productGg.getWeight() == null ? 0 : productGg.getWeight());
                sumPrice = countPsPriceGetFare(coonFareTempProByGetFare, totalWeight / 1000);
            } else {
                result = false;
                msg.append("没有合适的物流");
            }
        } else {
            result = false;
            msg.append("商品未绑定");
        }
        record.set("msg", msg.toString());
        record.set("sumPrice", sumPrice);
        record.set("startAddress", startAddress);
        record.set("result", result);
        return record;
    }
    
    public Double countPsPriceGetFare(CoonFareTempProByGetFare coonFareTempProByGetFare, Double sw) {
        Double sumPrice = 0.0;
        // 获取物流信息
        if (coonFareTempProByGetFare != null) {
            
            if (sw <= coonFareTempProByGetFare.getFirstWei().doubleValue()) {
                sumPrice = coonFareTempProByGetFare.getFirstPri().doubleValue();
            } else {
                if (sw - coonFareTempProByGetFare.getFirstWei().doubleValue() <= coonFareTempProByGetFare.getExtentWei()
                    .doubleValue()) {
                    sumPrice =
                        coonFareTempProByGetFare.getFirstPri().doubleValue()
                            + coonFareTempProByGetFare.getExtentPri().doubleValue();
                } else {
                    sumPrice =
                        coonFareTempProByGetFare.getFirstPri().doubleValue()
                            + Math.ceil(sw - coonFareTempProByGetFare.getFirstWei().doubleValue())
                            / coonFareTempProByGetFare.getExtentWei().doubleValue()
                            * coonFareTempProByGetFare.getExtentPri().doubleValue();
                }
                
            }
        }
        
        return sumPrice;
    }

    /**
     * 获取最近购物人次列表
     * 
     * @param coonShop 购物车参数
     * @return 最近购物人次列表
     */
	@Override
	public Record getBuyCountPage(CoonShop coonShop) {
		Record record = new Record(); 
		boolean success = false;
		String shopCode = coonShop.getCode();
		if(StringUtils.isNotEmpty(shopCode)){
			CoonShop shop = coonShopMapper.getCoonShopByCode(shopCode);
			if(shop != null){
				CoolOrderParam coolOrderParam = new CoolOrderParam();
				coolOrderParam.setSupplier(shop.getId());
				List<StatisticsPo> po = coolOrderMapper.getBuyCountPage(coonShop.getRowBounds(), coolOrderParam);
				record.set("countList", po);
			}else{
				record.set("msg", "无法获取店铺信息");
			}
		}else{
			record.set("msg", "店铺编码不能为空");
		}		
		record.set("success", success);
		return record;
	}
}
