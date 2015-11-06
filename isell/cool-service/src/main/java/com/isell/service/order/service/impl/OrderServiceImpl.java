package com.isell.service.order.service.impl;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.isell.core.config.BisConfig;
import com.isell.core.mybatis.page.PageInfo;
import com.isell.core.util.CommonUtils;
import com.isell.core.util.DateUtil;
import com.isell.core.util.GetBarCode;
import com.isell.core.util.HttpUtils;
import com.isell.core.util.JaxbUtil;
import com.isell.core.util.JsonData;
import com.isell.core.util.JsonUtil;
import com.isell.core.util.Record;
import com.isell.core.util.SMSUtil;
import com.isell.pay.alipay.util.AlipayNotify;
import com.isell.service.account.dao.CoonRunAccountMapper;
import com.isell.service.account.vo.CoonRunAccount;
import com.isell.service.alipay.dao.CoolAlipayMapper;
import com.isell.service.alipay.vo.CoolAlipay;
import com.isell.service.common.GeneralDef;
import com.isell.service.common.po.AddressInfo;
import com.isell.service.common.po.ItemInfo;
import com.isell.service.common.po.OrderRequest;
import com.isell.service.fare.dao.CoonFareTempProMapper;
import com.isell.service.fare.vo.CoonFareTempPro;
import com.isell.service.member.dao.CoolMemberReceiverMapper;
import com.isell.service.member.vo.CoolMemberReceiver;
import com.isell.service.message.dao.CoolMessageMapper;
import com.isell.service.message.vo.CoolMessage;
import com.isell.service.order.dao.CoolOrderItemMapper;
import com.isell.service.order.dao.CoolOrderMapper;
import com.isell.service.order.po.CoolOrderParam;
import com.isell.service.order.po.CoolOrderSelect;
import com.isell.service.order.po.CoolProductSales;
import com.isell.service.order.service.OrderService;
import com.isell.service.order.vo.CoolOrder;
import com.isell.service.order.vo.CoolOrderItem;
import com.isell.service.product.dao.CoolProductGgMapper;
import com.isell.service.product.dao.CoolProductMapper;
import com.isell.service.product.vo.CoolProduct;
import com.isell.service.product.vo.CoolProductGg;
import com.isell.service.shop.dao.CoonShopLevelMapper;
import com.isell.service.shop.dao.CoonShopMapper;
import com.isell.service.shop.dao.CoonShopPartnerMapper;
import com.isell.service.shop.dao.CoonShopProductMapper;
import com.isell.service.shop.vo.CoonShop;
import com.isell.service.shop.vo.CoonShopLevel;
import com.isell.service.shop.vo.CoonShopPartner;
import com.isell.service.shop.vo.CoonShopProduct;
import com.isell.ws.ningbo.bean.Detail;
import com.isell.ws.ningbo.bean.Order;
import com.isell.ws.ningbo.bean.OrderMsg;
import com.isell.ws.ningbo.bean.UserReg;
import com.isell.ws.ningbo.service.YoubeiService;
import com.isell.ws.ningbo.ws.order.KJB2CWebService;
import com.isell.ws.zhengzhou.bean.Bodydetail;
import com.isell.ws.zhengzhou.bean.Bodymaster;
import com.isell.ws.zhengzhou.bean.Cbecmessage;
import com.isell.ws.zhengzhou.bean.Messagebody;
import com.isell.ws.zhengzhou.bean.Messagehead;
import com.isell.ws.zhengzhou.bean.OrderInfo;
import com.isell.ws.zhengzhou.ws.WSRecvService;

/**
 * 订单服务接口实现类
 * 
 * @author lilin
 * @version [版本号, 2015年9月24日]
 */
@Service("orderService")
public class OrderServiceImpl implements OrderService {
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
     * 确认订单
     */
    @Override
    public Record getCoolOrderReturn(CoolOrderParam param) {
        Double psPrice = 0.0; // 邮费
        BigDecimal total = new BigDecimal(0); // 订单总金额
        Double totalWeight = 0.0; // 订单总重量，单位克
        int count = 0; // 订单商品数量
        double tax = 0.0; // 税
        
        List<CoolProduct> products = new ArrayList<CoolProduct>();
        String isProducts = "0";
        List<CoolOrderItem> orderItems = param.getOrderItems();
        if (orderItems != null && !orderItems.isEmpty()) {
            CoolProduct product;
            CoolProductGg productGg;
            for (CoolOrderItem item : orderItems) {
                productGg = coolProductGgMapper.getCoolProductGgById(item.getGid());
                product = coolProductMapper.getCoolProductById(productGg.getGoodsId());
                
                Byte orderType = param.getOrderType();
                if (0 == orderType) { // 普通订单
                    if (productGg.getCxjg().compareTo(new BigDecimal(0)) == 0) {
                        product.setPrice(productGg.getJg());
                    } else {
                        product.setPrice(productGg.getCxjg());
                    }
                } else if (1 == orderType) { // 一件代发
                    Integer userId = param.getUserId();
                    BigDecimal rate = coonShopLevelMapper.getLevelPrate(userId.toString());
                    product.setPrice(productGg.getDrpPrice().multiply(rate));
                }
                
                count += item.getCount();
                totalWeight += item.getCount() * (productGg.getWeight() == null ? 0 : productGg.getWeight());
                total = total.add(product.getPrice().multiply(new BigDecimal(item.getCount())));
                product.setStandard(productGg);
                product.setQuantity(item.getCount());
                
                tax += product.getPrice().multiply(product.getTax()).doubleValue() * count / 100;
                
                products.add(product);
                isProducts = "1";
            }
            
            // 不包邮的情况下才计算邮费
            if (param.getFreePost() != null && !param.getFreePost() && StringUtils.isNotEmpty(param.getLocationP())) {
                psPrice = countPsPrice(param.getLocationP(), totalWeight / 1000);
            }
            
            // 是否免税
            if ((param.getFreeTax() != null && !param.getFreeTax()) || param.getFreeTax() == null) {
                if (tax > 50) {
                    total = total.add(new BigDecimal(tax));
                }
            }
        }
        
        Record record = new Record();
        record.set("count", count);
        record.set("products", products);
        record.set("total", total.add(new BigDecimal(psPrice)));
        record.set("psPrice", psPrice);
        record.set("taxPrice", tax);
        record.set("isProducts", isProducts);
        
        return record;
    }
    
    /**
     * 计算邮费
     */
    public Double countPsPrice(String province, Double sw) {
        Double sumPrice = 0.0;
        // 获取物流信息
        List<CoonFareTempPro> proList = coonFareTempProMapper.getCoonFareTempProByPro(province);
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
        Date date = new Date();
        Integer userId = param.getUserId();
        BigDecimal total = new BigDecimal(0);
        
        CoolOrder order = new CoolOrder();
        order.setmId(userId);
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
        // 生成订单号 20位 规则：CO+14位时间字符串+4位随机码
        String orderNo = "CO" + DateUtil.dateToStr(DateUtil.yyyyMMddHHmmss, new Date()) + CommonUtils.randomFour();
        order.setOrderNo(orderNo);
        
        // 处理商品信息
        List<CoolOrderItem> items = param.getOrderItems();
        if (CollectionUtils.isNotEmpty(items)) {
            CoolProduct product;
            CoolProductGg productGg;
            for (CoolOrderItem item : items) {
                product = coolProductMapper.getCoolProductById(item.getgId());
                productGg = coolProductGgMapper.getCoolProductGgById(item.getGid());
                
                int count = item.getCount();
                // 保存商品清单
                item.setName(product.getName());
                item.setLogo(product.getLogo());
                item.setGg(productGg.getGg());
                item.setCount(count);
                item.setOrderNo(orderNo);
                item.setbId(product.getbId());
                item.setGid(productGg.getId());
                
                Byte orderType = param.getOrderType();
                if (0 == orderType) { // 普通订单
                    if (productGg.getCxjg().compareTo(new BigDecimal(0)) == 0) {
                        total = total.add(productGg.getJg().multiply(new BigDecimal(count)));
                        item.setPrice(productGg.getJg());
                    } else {
                        total = total.add(productGg.getCxjg().multiply(new BigDecimal(count)));
                        item.setPrice(productGg.getCxjg());
                    }
                } else if (1 == orderType) { // 一件代发
                    BigDecimal rate = coonShopLevelMapper.getLevelPrate(userId.toString());
                    total = total.add(productGg.getDrpPrice().multiply(rate).multiply(new BigDecimal(count)));
                    item.setPrice(productGg.getDrpPrice().multiply(rate));
                }
                
                // 旗舰店分佣
                if (canSplitBrokerage && param.getStoreId() != null) {
                    CoonShopProduct shopProductP = new CoonShopProduct();
                    shopProductP.setpId(item.getgId().toString());
                    shopProductP.setsId(param.getStoreId().toString());
                    CoonShopProduct shopProduct = coonShopProductMapper.getCoonShopProduct(shopProductP);
                    BigDecimal brokerage = shopProduct.getBrokerage();
                    if (brokerage != null) {
                        // 二次分佣 = 分佣比例 * 商品价格 * 佣金比例 * 酷店等级佣金比例(旗舰店为100%)
                        item.setBrokerage(brokerage.multiply(item.getPrice()).multiply(product.getDivide()));
                    }
                }
                coolOrderItemMapper.saveCoolOrderItem(item);
                
                // 处理商品库存和销量
                jdbcTemplate.update("update cool_product_gg set stock=stock-" + count + ",sales=sales+" + count
                    + " where id=?", item.getGid());
                jdbcTemplate.update("update cool_product set sales=sales+" + count + " where id=?", item.getgId());
                // 删除购物车中对应商品，如果有的话
                jdbcTemplate.update("delete from coon_shopcart where user_id=? and p_id=? and g_id=?",
                    userId,
                    item.getgId(),
                    item.getGid());
                order.setbId(product.getbId());
            }
        }
        if (param.getPsPrice() != null) {
            order.setPsPrice(param.getPsPrice());
            total.add(param.getPsPrice());
        }
        order.setTotal(total);
        order.setSupplier(param.getStoreId().toString());
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
        
        record.set("id", order.getId());
        record.set("orderNo", order.getOrderNo());
        record.set("success", result > 0 ? true : false);
        // record.set("zffs",param.getZffs());
        // record.set("storeId",param.getStoreId());
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
     * 支付宝付款更新订单
     */
    @SuppressWarnings("rawtypes")
    @Override
    public Record updateCoolOrderZfb(Map<String, Object> param) {
        Record record = new Record();
        CoolOrder order = coolOrderMapper.getCoolOrderById((Integer)param.get("id"));
        
        String msg = "";
        int b_id = order.getbId();
        
        CoolAlipay alipay = new CoolAlipay();
        alipay.setbId(b_id);
        List<CoolAlipay> alipayList = new ArrayList<CoolAlipay>();
        alipayList = coolAlipayMapper.findCoolAlipay(alipay);
        if (CollectionUtils.isNotEmpty(alipayList)) {
            // 如果商家的支付宝账号不存在，那么默认使用平台的支付宝
            alipay = alipayList.get(0);
            if (StringUtils.isEmpty(alipay.getPartner())) {
                alipay.setbId(0);
                alipayList = coolAlipayMapper.findCoolAlipay(alipay);
                alipay = alipayList.get(0);
            }
        }
        
        // 获取支付宝GET过来反馈信息
        Map<String, String> params = new HashMap<String, String>();
        Map requestParams = (Map)param.get("requestParams");
        
        // Map requestParams = getRequest().getParameterMap();
        for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
            String name = (String)iter.next();
            String[] values = (String[])requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
            }
            // 乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
            try {
                valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            params.put(name, valueStr);
        }
        
        // 获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以下仅供参考)//
        // 商户订单号
        // String out_trade_no = getPara("out_trade_no");
        // 支付宝交易号
        
        String trade_no = param.get("trade_no").toString();
        
        // 交易状态
        String trade_status = param.get("trade_status").toString();
        
        // 获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以上仅供参考)//
        
        // 计算得出通知验证结果
        boolean verify_result = AlipayNotify.verify(params, alipay.getKey(), alipay.getPartner());
        if (verify_result) {// 验证成功
            // ////////////////////////////////////////////////////////////////////////////////////////
            // 请在这里加上商户的业务逻辑程序代码
            
            // ——请根据您的业务逻辑来编写程序（以下代码仅作参考）——
            if (trade_status.equals("TRADE_FINISHED") || trade_status.equals("TRADE_SUCCESS")) {
                // 判断该笔订单是否在商户网站中已经做过处理
                // 如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
                // 如果有做过处理，不执行商户的业务程序
            }
            
            // 该页面可做页面美工编辑
            order.setTradeNo(trade_no);
            order.setState(CoolOrder.ORDER_STATE_1);
            order.setPayTime(new Timestamp(new Date().getTime()));
            int result = coolOrderMapper.updateCoolOrder(order);
            
            // ——请根据您的业务逻辑来编写程序（以上代码仅作参考）——
            msg = "恭喜您，您的订单已支付成功";
            record.set("success", result > 0 ? true : false);
            // ////////////////////////////////////////////////////////////////////////////////////////
        } else {
            // 该页面可做页面美工编辑
            msg = "订单支付失败，请到<a href='/user/order'>我的订单</a>尝试重新支付";
        }
        
        record.set("msg", msg);
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
        // 发送物流消息给客户
        CoolMessage coolMessage = new CoolMessage();
        coolMessage.setContent("【小酷儿】订单" + orderR.getOrderNo() + "已发货了，" + "物流消息请点击:"
            + "<a href='http://m.i-coolshop.com/common/searchWapExpress/" + orderR.getId() + "'>"
            + "http://m.i-coolshop.com/common/searchWapExpress/" + orderR.getId() + "</a>" + "。客服电话4009693356，感谢您的使用！");
        
        coolMessage.setClick(0);
        coolMessage.setType("1");
        coolMessage.setBelongId(orderR.getOrderNo());
        coolMessage.setUserId(orderR.getmId());
        coolMessage.setSendtime(new Date());
        coolMessage.setOperatorId(Integer.valueOf(param.get("operatorId").toString()));
        messageMapper.saveCoolMessage(coolMessage);
        String send_sms_url = param.get("send_sms_url").toString();
        
        String fhfs = param.get("fhfs").toString();
        int resultUpdate = 0;
        if ("0".equals(fhfs)) {
            CoolOrder order = new CoolOrder();
            order.setState(CoolOrder.ORDER_STATE_2);
            order.setPsfs("自提");
            order.setFhfs((byte)0);
            order.setUpdatetime(new Timestamp(System.currentTimeMillis()));
            
            order.setId(orderR.getId());
            resultUpdate = coolOrderMapper.updateCoolOrder(order);
            
            sendMessage(orderR, send_sms_url);
            record.set("success", resultUpdate > 0 ? true : false);
            return record;
        } else if ("1".equals(fhfs)) {
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
            order.setPsfs("yuantong");
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
            CoolOrder order = new CoolOrder();
            order.setState(CoolOrder.ORDER_STATE_11); // 已通知海关发货
            order.setFhfs((byte)10);
            order.setUpdatetime(new Timestamp(System.currentTimeMillis()));
            
            order.setId(orderR.getId());
            resultUpdate = coolOrderMapper.updateCoolOrder(order);
            
            sendMessage(orderR, send_sms_url);
            record.set("success", resultUpdate > 0 ? true : false);
            return record;
        } else if ("11".equals(fhfs)) { // 宁波优贝保税仓
            OrderMsg orderMsg = getOrderMsg(orderR);
            String xml = JaxbUtil.convertToXml(orderMsg);
            xml = xml.replace("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>", "");
            
            KJB2CWebService service = new KJB2CWebService();
            String result =
                service.getKJB2CWebServiceSoap12().addOrderKJB2C(YoubeiService.ERPKEY, YoubeiService.ERPSECRET, xml);
            System.out.println(result);
            if ("success".equals(result)) {
                CoolOrder order = new CoolOrder();
                order.setState(CoolOrder.ORDER_STATE_11); // 已通知海关发货
                order.setFhfs((byte)11);
                order.setUpdatetime(new Timestamp(System.currentTimeMillis()));
                
                order.setId(orderR.getId());
                resultUpdate = coolOrderMapper.updateCoolOrder(order);
                
                sendMessage(orderR, send_sms_url);
            }
            
            record.set("success", resultUpdate > 0 ? true : false);
            return record;
        } else if ("20".equals(fhfs)) { // 郑州海关
            String xml = JaxbUtil.convertToXml(getCbecmessage(orderR));
            System.out.println(xml);
            // 发消息给海关
            WSRecvService service = new WSRecvService();
            String result = service.getWSRecvServicePort().receive(xml);
            System.out.println(result);
            record.set("success", "SUCCESS".equals(result));
            
            if ("SUCCESS".equals(result)) {
                CoolOrder order = new CoolOrder();
                order.setState(CoolOrder.ORDER_STATE_11); // 已通知海关发货
                order.setFhfs((byte)20);
                order.setUpdatetime(new Timestamp(System.currentTimeMillis()));
                
                order.setId(orderR.getId());
                resultUpdate = coolOrderMapper.updateCoolOrder(order);
                sendMessage(orderR, send_sms_url);
            }
            
            // 发送消息给物流
            record.set("success", resultUpdate > 0 ? true : false);
            return record;
        }
        
        return record;
    }
    
    private void sendMessage(CoolOrder orderR, String url) {
        if (!"56510b6f384c4f8e881ee1614913a3ef".equals(orderR.getSupplier())) {// 可爱淘不需要发货短信
            SMSUtil.sendMsg("15129", orderR.getMobile(), orderR.getOrderNo(), url);
        }
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
        orderMsg.setOrder(order);
        orderMsg.setOrderItems(new ArrayList<Detail>(orderDb.getItemList().size()));
        for (CoolOrderItem item : orderDb.getItemList()) {
            Detail detail = new Detail();
            detail.setProductCode("310520156167801272"); // TODO： 这个要填货品在保税仓的批号
            detail.setProductName(item.getName());
            detail.setUnit("个");
            detail.setQuantity(item.getCount());
            detail.setPrice(item.getPrice());
            detail.setTotalPrice(detail.getPrice().multiply(new BigDecimal(detail.getQuantity())));
            
            orderMsg.getOrderItems().add(detail);
        }
        UserReg userReg = new UserReg();
        userReg.setName(orderDb.getLinkman());
        userReg.setPhone(orderDb.getMobile());
        userReg.setIdnum(orderDb.getIdcard());
        
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
        messagehead.setMessageid(orderDb.getOrderNo());
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
        bodymaster.setCustomerid(orderDb.getIdcard());
        bodymaster.setSubmittime(DateUtil.dateToStr(DateUtil.yyyy_MM_dd_HH_mm_ss, orderDb.getCreatetime()));
        bodymaster.setPaynumber(orderDb.getTradeNo());
        if (orderDb.getZffs() == 2) { // 支付宝支付
            bodymaster.setPayenterprisecode("P460400001");
            bodymaster.setPayenterprisename("支付宝（杭州）信息技术有限公司");
        } else if (orderDb.getZffs() == 3) { // 微信支付
            bodymaster.setPayenterprisecode("P460400004");
            bodymaster.setPayenterprisename("财付通支付科技有限公司");
        }
        // TODO 测试平台支付企业备案号
        bodymaster.setPayenterprisecode("P461200031");
        
        Bodydetail bodydetail = new Bodydetail();
        bodydetail.setOrderList(new ArrayList<OrderInfo>(orderDb.getItemList().size()));
        
        for (CoolOrderItem item : orderDb.getItemList()) {
            OrderInfo order = new OrderInfo();
            order.setItemno("4100605453AW1239"); // 海关备案商品编号
            order.setShelfgoodsname(item.getlName()); // 商品上架品名
            order.setGoodid(item.getGid() + ""); // 商品货号
            order.setSpecifications(item.getGg()); // 规格型号
            order.setAmount(item.getCount() + ""); // 申报数量
            order.setGoodprice(item.getPrice() + ""); // 成交单价
            order.setOrdersum(item.getPrice().multiply(new BigDecimal(item.getCount())) + ""); // 成交总价
            order.setOrderid(orderDb.getOrderNo()); // 订单编号
            order.setGoodidinsp("4100605453AW1239"); // 检验检疫商品备案编号
            
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
        CoolOrder order = coolOrderMapper.getCoolOrderById((Integer)param.get("id"));
        order.setState(CoolOrder.ORDER_STATE_3);
        order.setFinishTime(new Timestamp(System.currentTimeMillis()));
        
        int result = 0;
        String shopId = order.getSupplier();
        if (StringUtils.isNotEmpty(shopId)) {
            CoonShop shop = coonShopMapper.getCoonShopById(shopId);
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
                bill.setSerialNumber(DateUtil.dateToStr(DateUtil.yyyyMMddHHmmss, new Date()) + CommonUtils.randomFour());
                
                coonRunAccountMapper.saveCoonRunAccount(bill);
                
                // 更新酷店成交订单数
                result =
                    jdbcTemplate.update("update coon_shop set turnover_orders=turnover_orders+1,all_amount=all_amount+?,nwd_amount=nwd_amount+? where id=?",
                        order.getSupplierProfit(),
                        order.getSupplierProfit(),
                        shopId);
                
                // 推荐店铺相关处理
                String recommendId = shop.getRecommendId();
                if (StringUtils.isNotEmpty(recommendId)) {
                    CoonShopPartner partner = new CoonShopPartner();
                    partner.setId(CommonUtils.uuid());
                    partner.setoId(order.getId());
                    partner.setSupplier(recommendId);
                    partner.setPartnerId(shop.getId());
                    partner.setPartnerName(shop.getName());
                    partner.setPartnerAmount(order.getSupplierProfit().multiply(GeneralDef.RECOMMEND_RATE));
                    partner.setCreatetime(new Date());
                    partner.setOrderAmount(order.getTotal());
                    coonShopPartnerMapper.saveCoonShopPartner(partner);
                    
                    // 记录佣金
                    CoonRunAccount bill2 = new CoonRunAccount();
                    CoonShop shop2 = coonShopMapper.getCoonShopById(recommendId);
                    bill2.setId(CommonUtils.uuid());
                    bill2.setUserId(shop2.getUserId());
                    bill2.setAmount(order.getSupplierProfit().multiply(GeneralDef.RECOMMEND_RATE));
                    bill2.setType(CoonRunAccount.TYPE_0);
                    bill2.setCreateTime(new Date());
                    bill2.setFinishTime(new Date());
                    bill2.setWithdrawState(CoonRunAccount.WITHDRAW_STATE_3);
                    bill2.setSerialNumber(DateUtil.dateToStr(DateUtil.yyyyMMddHHmmss, new Date())
                        + CommonUtils.randomFour());
                    
                    coonRunAccountMapper.saveCoonRunAccount(bill2);
                    
                    jdbcTemplate.update("update coon_shop set all_amount=all_amount+?,nwd_amount=nwd_amount+?,recommend_amount=recommend_amount +? where id=?",
                        order.getSupplierProfit().multiply(GeneralDef.RECOMMEND_RATE),
                        order.getSupplierProfit().multiply(GeneralDef.RECOMMEND_RATE),
                        order.getSupplierProfit().multiply(GeneralDef.RECOMMEND_RATE),
                        recommendId);
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
        
        record.set("success", result > 0 ? true : false);
        return record;
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
        List<CoolProductSales> salesList = coolOrderItemMapper.getSumCoolProductSales(new CoolOrderSelect());
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
     * 导入订单
     */
	@Override
	public Record saveCoolOrderForImport(Map<String, Object> param) {
		Record record = new Record();
		String filePath = param.get("filePath").toString();
		String importType = param.get("importType").toString();
		String arrears = param.get("arrears").toString();
		if("1".equals(importType)){   //拼多多
			record = savePdd(filePath,arrears);
			record.set("success", true);
		}		
		return record;
	}
    
    /**
     * 拼多多订单导入
     * 
     * @param filePath
     * @return
     */
    private Record savePdd(String filePath,String arrears) {
        Record record = new Record();
        File file = new File(filePath);
        InputStream is = null;
        boolean success = false;
        try {
            is = new FileInputStream(file);
            BufferedInputStream binput = new BufferedInputStream(is);
            Workbook hwb;
            hwb = WorkbookFactory.create(binput);
            
            Sheet sheet = hwb.getSheetAt(0);
            Row row = null;
            Cell cell = null;
            
            // List<CoolOrder> orderList = new ArrayList<CoolOrder>();
            // List<CoolOrderItem> orderItemList = new ArrayList<CoolOrderItem>();
            int rowNum = sheet.getLastRowNum();
            for(int i = 1; i <= rowNum; i++){
            	success = false;
            	//获取到的行数可能会存在问题，所以添加判断标志，都为空时结束循环
            	int flag = 0;
            	CoolOrder order = new CoolOrder();
            	if("0".equals(arrears)){  //正常订单
            		order.setArrears(0); 
            	}else{
            		order.setArrears(1); // 欠费订单
            	}            	
            	order.setoType(new Byte("1")); //pc 订单
            	order.setOrderType(new Byte("1")); //一件代发
            	order.setState(CoolOrder.ORDER_STATE_1); //待发货
            	order.setSupplier("7b54557199554ee8bc392c514b8377ec");
            	order.setmId(883);
            	order.setSupName("拼多多商城");
            	CoolOrderItem item = new CoolOrderItem();
            	
            	row = sheet.getRow(i);            	
            	
            	cell = row.getCell(0); //商品id
            	if(cell != null){
            		//将单元格格式设为string，方便判断是否为空             
                    cell.setCellType(Cell.CELL_TYPE_STRING);
                    if (StringUtils.isNotEmpty(cell.getStringCellValue().trim())) {
                        CoolProduct product =
                            coolProductMapper.getCoolProductById(Integer.valueOf(cell.getStringCellValue()
                                .trim()
                                .toString()));
                        int gId = Integer.valueOf(cell.getStringCellValue().trim().toString());
                        CoolProductGg gg = coolProductGgMapper.findCoolProductGgList(gId).get(0);
                        item.setgId(gId);
                        item.setName(product.getNameEn());
                        if (StringUtils.isNotEmpty(gg.getLogo())) {
                            item.setLogo(gg.getLogo());
                        } else {
                            item.setLogo(product.getLogo());
                        }
                        item.setGg(gg.getGg());
                        item.setbId(product.getbId());
                        item.setGid(gg.getId());
                        
                    } else {
                        flag = flag + 1;
                    }
                } else {
                    flag = flag + 1;
                }
                cell = row.getCell(1); // 数量
                if (cell != null) {
                    // 将单元格格式设为string，方便判断是否为空
                    cell.setCellType(Cell.CELL_TYPE_STRING);
                    if (StringUtils.isNotEmpty(cell.getStringCellValue().trim())) {
                        item.setCount(Integer.valueOf(cell.getStringCellValue().trim().toString()));
                    } else {
                        flag = flag + 1;
                    }
                } else {
                    flag = flag + 1;
                }
                cell = row.getCell(3); // 订单号
                if (cell != null) {
                    // 将单元格格式设为string，方便判断是否为空
                    cell.setCellType(Cell.CELL_TYPE_STRING);
                    if (StringUtils.isNotEmpty(cell.getStringCellValue().trim())) {
                        order.setOrderNo(cell.getStringCellValue().trim().toString());
                        item.setOrderNo(cell.getStringCellValue().trim().toString());
                    } else {
                        flag = flag + 1;
                    }
                } else {
                    flag = flag + 1;
                }
                cell = row.getCell(6); // 订单金额
                if (cell != null) {
                    // 将单元格格式设为string，方便判断是否为空
                    cell.setCellType(Cell.CELL_TYPE_STRING);
                    if (StringUtils.isNotEmpty(cell.getStringCellValue().trim())) {
                        order.setTotal(new BigDecimal(cell.getStringCellValue().trim().toString()));
                        item.setPrice(new BigDecimal(cell.getStringCellValue().trim().toString()).divide(new BigDecimal(
                            item.getCount())));
                    } else {
                        flag = flag + 1;
                    }
                } else {
                    flag = flag + 1;
                }
                cell = row.getCell(7); // 身份证姓名
                if (cell != null) {
                    // 将单元格格式设为string，方便判断是否为空
                    cell.setCellType(Cell.CELL_TYPE_STRING);
                    if (StringUtils.isNotEmpty(cell.getStringCellValue().trim())) {
                        // order.setLinkman(cell.getStringCellValue().trim().toString());
                    } else {
                        flag = flag + 1;
                    }
                } else {
                    flag = flag + 1;
                }
                cell = row.getCell(8); // 身份证号码
                if (cell != null) {
                    // 将单元格格式设为string，方便判断是否为空
                    cell.setCellType(Cell.CELL_TYPE_STRING);
                    if (StringUtils.isNotEmpty(cell.getStringCellValue().trim())) {
                        order.setIdcard(cell.getStringCellValue().trim().toString());
                    } else {
                        flag = flag + 1;
                    }
                } else {
                    flag = flag + 1;
                }
                cell = row.getCell(9); // 收货人
                if (cell != null) {
                    // 将单元格格式设为string，方便判断是否为空
                    cell.setCellType(Cell.CELL_TYPE_STRING);
                    if (StringUtils.isNotEmpty(cell.getStringCellValue().trim())) {
                        order.setLinkman(cell.getStringCellValue().trim().toString());
                    } else {
                        flag = flag + 1;
                    }
                } else {
                    flag = flag + 1;
                }
                cell = row.getCell(10); // 手机
                if (cell != null) {
                    // 将单元格格式设为string，方便判断是否为空
                    cell.setCellType(Cell.CELL_TYPE_STRING);
                    if (StringUtils.isNotEmpty(cell.getStringCellValue().trim())) {
                        order.setMobile(cell.getStringCellValue().trim());
                    } else {
                        flag = flag + 1;
                    }
                } else {
                    flag = flag + 1;
                }
                cell = row.getCell(11); // 省
                if (cell != null) {
                    // 将单元格格式设为string，方便判断是否为空
                    cell.setCellType(Cell.CELL_TYPE_STRING);
                    if (StringUtils.isNotEmpty(cell.getStringCellValue().trim())) {
                        order.setLocationP(cell.getStringCellValue().trim());
                    } else {
                        flag = flag + 1;
                    }
                } else {
                    flag = flag + 1;
                }
                cell = row.getCell(12); // 市
                if (cell != null) {
                    // 将单元格格式设为string，方便判断是否为空
                    cell.setCellType(Cell.CELL_TYPE_STRING);
                    if (StringUtils.isNotEmpty(cell.getStringCellValue().trim())) {
                        order.setLocationC(cell.getStringCellValue().trim());
                    } else {
                        flag = flag + 1;
                    }
                } else {
                    flag = flag + 1;
                }
                cell = row.getCell(13); // 区
                if (cell != null) {
                    // 将单元格格式设为string，方便判断是否为空
                    cell.setCellType(Cell.CELL_TYPE_STRING);
                    if (StringUtils.isNotEmpty(cell.getStringCellValue().trim())) {
                        order.setLocationA(cell.getStringCellValue().trim());
                    } else {
                        flag = flag + 1;
                    }
                } else {
                    flag = flag + 1;
                }
                cell = row.getCell(14); // 确认时间
                if (cell != null) {
                    // 将单元格格式设为string，方便判断是否为空
                    cell.setCellType(Cell.CELL_TYPE_STRING);
                    if (StringUtils.isNotEmpty(cell.getStringCellValue().trim())) {
                        order.setCreatetime(DateUtil.parseDate(cell.getStringCellValue().toString(),
                            DateUtil.yyyy_MM_dd_HH_mm));
                    } else {
                        flag = flag + 1;
                    }
                } else {
                    flag = flag + 1;
                }
                cell = row.getCell(15); // 街道
                if (cell != null) {
                    // 将单元格格式设为string，方便判断是否为空
                    cell.setCellType(Cell.CELL_TYPE_STRING);
                    if (StringUtils.isNotEmpty(cell.getStringCellValue().trim())) {
                        order.setAddress(cell.getStringCellValue());
                    } else {
                        flag = flag + 1;
                    }
                } else {
                    flag = flag + 1;
                }
                if (flag == 13) {
                    break;
                } else {
                    // orderList.add(order);
                    // orderItemList.add(item);
                    coolOrderMapper.saveCoolOrder(order);
                    coolOrderItemMapper.saveCoolOrderItem(item);
                    
                	// 处理商品库存和销量
                    jdbcTemplate.update("update cool_product_gg set stock=stock-" + item.getCount() + ",sales=sales+" +  item.getCount()
                        + " where id=?", item.getGid());
                    jdbcTemplate.update("update cool_product set sales=sales+" +  item.getCount() + " where id=?", item.getgId());
                	success = true;
                }
                // coolOrderMapper.insertBatch(orderList);

            }
            
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
		record.set("success", success);
		return record;
	}
    
    //
    // /**
    // * 统计订单数
    // */
    // @Override
    // public void getSumCoolOrderNumber(Map<String, Object> param, JsonData jsonData) {
    // String selectType = param.get("selectType").toString(); // 1 按年 2 按月 3 按日
    // String time = null;
    // if (param.get("time") != null) {
    // time = param.get("time").toString();
    // }
    // List<StatisticsPo> poList = coolOrderMapper.getSumCoolOrderNumber(selectType, time);
    // List<StatisticsPo> list = new ArrayList<StatisticsPo>();
    // for (StatisticsPo po : poList) {
    // if ("1".equals(selectType)) {
    // po.setStatistics_k(po.getStatistics_k().substring(5, 7));
    // }
    // if ("2".equals(selectType)) {
    // po.setStatistics_k(po.getStatistics_k().substring(8, 10));
    // }
    // if ("3".equals(selectType)) {
    // po.setStatistics_k(po.getStatistics_k().substring(11, 13));
    // }
    // list.add(po);
    // }
    //
    // jsonData.setRows(list);
    // }
    //
    // /**
    // * 统计销售额
    // */
    // @Override
    // public void getSumCoolOrderSales(Map<String, Object> param, JsonData jsonData) {
    // String selectType = param.get("selectType").toString(); // 1 按年 2 按月 3 按日
    // String time = null;
    // if (param.get("time") != null) {
    // time = param.get("time").toString();
    // }
    // List<StatisticsPo> poList = coolOrderMapper.getSumCoolOrderSales(selectType, time);
    // List<StatisticsPo> list = new ArrayList<StatisticsPo>();
    // for (StatisticsPo po : poList) {
    // if ("1".equals(selectType)) {
    // po.setStatistics_k(po.getStatistics_k().substring(5, 7));
    // }
    // if ("2".equals(selectType)) {
    // po.setStatistics_k(po.getStatistics_k().substring(8, 10));
    // }
    // if ("3".equals(selectType)) {
    // po.setStatistics_k(po.getStatistics_k().substring(11, 13));
    // }
    // list.add(po);
    // }
    //
    // jsonData.setRows(list);
    // }
    //
    //
    // /**
    // * 导入订单（银科金典）
    // */
    // @Override
    // public Record saveCoolOrderYkjd(Map<String, Object> param) {
    // Record record = new Record();
    // //String filePath = param.get("filePath").toString();
    //
    // return record;
    // }o
    
}
