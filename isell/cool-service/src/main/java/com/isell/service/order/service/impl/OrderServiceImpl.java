package com.isell.service.order.service.impl;

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
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.isell.core.common.StatisticsPo;
import com.isell.core.mybatis.page.PageInfo;
import com.isell.core.util.CommonUtils;
import com.isell.core.util.DateUtil;
import com.isell.core.util.GetBarCode;
import com.isell.core.util.HttpUtils;
import com.isell.core.util.JsonData;
import com.isell.core.util.JsonUtil;
import com.isell.core.util.Record;
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
import com.isell.service.order.dao.CoolOrderItemMapper;
import com.isell.service.order.dao.CoolOrderMapper;
import com.isell.service.order.po.CoolOrderExport;
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
import com.isell.service.shop.dao.CoonShopProductMapper;
import com.isell.service.shop.vo.CoonShop;
import com.isell.service.shop.vo.CoonShopLevel;
import com.isell.service.shop.vo.CoonShopProduct;

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
            coolOrderMapper.updateCoolOrder(param);
            // 减销量加库存
            List<CoolOrderItem> itemList = coolOrderItemMapper.findCoolOrderItemByOrderId(id);
            if (itemList != null) {
                for (CoolOrderItem item : itemList) {
                    jdbcTemplate.update("update cool_product_gg set stock=stock+?,sales=sales-? where id=?",
                        item.getCount(),
                        item.getCount(),
                        item.getGid());
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
                
                products.add(product);
                isProducts = "1";
            }
            
            // 不包邮的情况下才计算邮费
            if (param.getFreePost() != null && !param.getFreePost() && StringUtils.isNotEmpty(param.getLocationP())) {
                psPrice = countPsPrice(param.getLocationP(), totalWeight / 1000);
            }
        }
        
        Record record = new Record();
        record.set("count", count);
        record.set("products", products);
        record.set("total", total.add(new BigDecimal(psPrice)));
        record.set("psPrice", psPrice);
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
        
        // 收货地址
        Integer recId = param.getRecId();
        CoolMemberReceiver recRec = coolMemberReceiverMapper.getCoolMemberReceiverById(recId);
        order.setLocationP(recRec.getLocationP());
        order.setLocationC(recRec.getLocationC());
        order.setLocationA(recRec.getLocationA());
        order.setAddress(recRec.getAddress());
        order.setLinkman(recRec.getName());
        order.setMobile(recRec.getMobile());
        order.setTel(recRec.getTel());
        order.setZipcode(recRec.getZipcode());
        
        // 旗舰店分佣
        boolean canSplitBrokerage = (param.getShareUser() != null && !param.getShareUser().equals(userId));
        if (canSplitBrokerage) {
            order.setShareUser(param.getShareUser());
            order.setShareAdded(0);
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
     * 修改订单（订单发货）0 自提， 1 圆通 2 费舍尔
     */
    @SuppressWarnings("unchecked")
    @Override
    public Record updateCoolOrderDelivery(Map<String, Object> param) {
        Record record = new Record();
        CoolOrder orderR = coolOrderMapper.getCoolOrderById((Integer)param.get("id"));
        
        String cmpany = param.get("company").toString();
        int resultUpdate = 0;
        if ("0".equals(cmpany)) {
            CoolOrder order = new CoolOrder();
            order.setState(CoolOrder.ORDER_STATE_2);
            order.setPsfs("自提");
            order.setUpdatetime(new Timestamp(System.currentTimeMillis()));
            
            order.setId(orderR.getId());
            resultUpdate = coolOrderMapper.updateCoolOrder(order);
            
            record.set("success", resultUpdate > 0 ? true : false);
            return record;
        } else if ("1".equals(cmpany)) {
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
            List<CoolOrderItem> orderItems = coolOrderItemMapper.findCoolOrderItemByOrderNo(orderR.getOrderNo());
            for (CoolOrderItem orderItem : orderItems) {
                ItemInfo item = new ItemInfo();
                item.setItemName(orderItem.getName());
                item.setNumber(orderItem.getCount());
                item.setItemValue(orderItem.getPrice().multiply(new BigDecimal(orderItem.getCount())));
                list.add(item);
            }
            request.setItems(list);
            request.setGoodsValue(orderR.getTotal());
            String result = HttpUtils.httpPost(GeneralDef.YUANTONG_SURFACE_URL, JsonUtil.writeValueAsString(request));
            JsonData jsonData = JsonUtil.readValue(result, JsonData.class);
            Object jsonObject = jsonData.getDataValue("orderMessage");
            Map<String, Object> orderMessage = (Map<String, Object>)jsonObject;
            String datePath = new SimpleDateFormat("yy-MM-dd").format(new Date());
            String filePath = "/order/" + datePath + "/" + record.getStr("order_no") + ".png";
            GetBarCode.getBarCode("/order/" + datePath + "/",
                record.getStr("order_no") + ".png",
                (String)orderMessage.get("mailNo"));
            
            order.setUpdatetime(new Timestamp(System.currentTimeMillis()));
            order.setState(CoolOrder.ORDER_STATE_2);
            order.setPsfs("yuantong");
            order.setBigpen(orderMessage.get("bigPen").toString());
            order.setPsCode(orderMessage.get("mailNo").toString());
            order.setBarcode(filePath);
            order.setId(orderR.getId());
            resultUpdate = coolOrderMapper.updateCoolOrder(order);
            
            record.set("success", resultUpdate > 0 ? true : false);
            return record;
        } else if ("2".equals(cmpany)) {
            // List<CoolOrderItem> orderItems = coolOrderItemMapper.findCoolOrderItemByOrderNo(orderR.getOrderNo());
            /**
             * String sql = "select i.g_id,i.name,f.bar_code,i.count,i.price,f.post_tax_no,g.code,g.weight from " +
             * "cool_order_item i inner join cool_product_gg g on i.gid=g.id " +
             * "inner join cool_product_filling f on f.category_code=g.code where i.order_no=?";
             * 
             * String countSql = "select count(*) from cool_order_item where order_no=?";
             * 
             * List<Record> itemList = Db.find(sql, orderR.getStr("order_no")); long count = Db.queryLong(countSql,
             * getPara(0));
             * 
             * if (CollectionUtils.isEmpty(itemList) || count != itemList.size()) { setAttr("success", false);
             * setAttr("msg", "请先备案及填写商品规格信息"); renderJson(); return; } EcmOrders orders = new EcmOrders(); EcmOrder
             * order = new EcmOrder(); order.setOrderCode(orderR.getStr("order_no")); // 订单编号
             * order.setOrderDate(orderR.getTimestamp("createtime")); // 下单时间
             * order.setReceiverName(orderR.getStr("linkman")); // 收件人 order.setMobile(orderR.getStr("mobile") == null ?
             * orderR.getStr("tel") : orderR.getStr("mobile")); // 联系电话 order.setProvince(orderR.getStr("location_p"));
             * // 收货地址-省 order.setCity(orderR.getStr("location_c"));// 收货地址-市
             * order.setDistrict(orderR.getStr("location_a"));// 收货地址-区
             * order.setReceiverAddress(orderR.getStr("address"));// 收货地址-详细地址
             * order.setReceiverZip(orderR.getStr("zipcode"));// 邮政编码（不必填） order.setPayType("03"); // 01:银行卡支付 02:余额支付
             * 03:其他 // order.setPayCompanyCode("ZF14050401"); // 支付平台在杭州口岸备案编号
             * order.setPayNumber(orderR.getStr("oid_billno")); // 支付单号 // order.setPaperNumber("320402199005273738");
             * 购买人身份证号 order.setOrderTotalAmount(orderR.getBigDecimal("total").doubleValue()); // 订单总价
             * order.setOrderGoodsAmount(order.getOrderTotalAmount()); order.setOrderTaxAmount(0.00); //
             * 交易过程中商家向用户征收的税款，免税模式填写0 order.setFeeAmount(0.00); // 交易过程中商家向用户征收的运费，免邮模式填写0 order.setTradeTime(new
             * Date()); // 成交时间 order.setTotalAmount(order.getOrderTotalAmount()); // “订单总金额”扣除“折扣”之后的金额
             * order.setPurchaserId(orderR.get("m_id").toString()); // 消费者下单时在电商平台的注册ID
             * order.setId(order.getPurchaserId()); // 消费者下单时在电商平台的注册ID order.setName(order.getReceiverName()); // 购买人姓名
             * order.setTelNumber(order.getMobile()); // 购买人电话 order.setAddress(order.getReceiverAddress()); // 购买人联系地址
             * 
             * order.setOrderDtls(new ArrayList<EcmCommodity>());
             * 
             * for (Record item : itemList) { EcmCommodity commodity = new EcmCommodity();
             * commodity.setCommodityCode(item.getStr("code")); // commodity.setCommodityCode("150402010001");
             * commodity.setCommodityName(item.getStr("name")); commodity.setCommodityBarcode(item.getStr("bar_code"));
             * // 商品条形码
             * 
             * commodity.setQty(item.getInt("count")); commodity.setWeight(item.getDouble("weight") *
             * item.getInt("count") / 1000d); // 单位KG
             * commodity.setTradePrice(item.getBigDecimal("price").doubleValue());
             * commodity.setTradeTotal(item.getBigDecimal("price") .multiply(new BigDecimal(item.getInt("count")))
             * .doubleValue()); commodity.setDeclPrice(commodity.getTradePrice());
             * commodity.setDeclTotalPrice(commodity.getTradeTotal()); commodity.setCodeTs(item.getStr("post_tax_no"));
             * // 货主商品在海关备案的行邮税号
             * 
             * order.getOrderDtls().add(commodity); }
             * 
             * orders.setOrders(new ArrayList<EcmOrder>()); orders.getOrders().add(order);
             * 
             * String jsonObj = JsonUtil.writeValueAsString(orders); Map<String, String> paramMap = new HashMap<String,
             * String>(); paramMap.put("accessCode", "xiaocoon"); paramMap.put("jsonObj", jsonObj);
             * paramMap.put("authCode", Coder.encryptBASE64(Coder.encryptMD5(jsonObj + PRIVATE_KEY)));
             * 
             * String result = HttpUtils.httpPost(AppConfig.ECM_PUSHSALEORDER_URL, paramMap); JsonData jsonData =
             * JsonUtil.readValue(result, JsonData.class); if (jsonData.getSuccess()) { // 更新订单状态为已发货
             * Db.update("update cool_order set state=2,psfs = 'ecm',updatetime = ? where id=?", new
             * Timestamp(System.currentTimeMillis()), getPara(0)); } setAttr("success", jsonData.getSuccess());
             * setAttr("msg", jsonData.getMsg()); renderJson();
             */
        }
        
        return record;
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
                    order.setIsDelM(1);
                }
                if (order != null && param.get("sId") != null) { // 酷店删除
                    order.setIsDel(1);
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
     * 导出订单
     */
    @Override
    public void exportCoolOrderList(CoolOrderSelect orderSelect, JsonData jsonData) {
        List<CoolOrderExport> orderList = new ArrayList<CoolOrderExport>();
        orderList = coolOrderMapper.getCoolOrderListExport(orderSelect);
        for (CoolOrderExport export : orderList) {
            if (export.getState() != null) {
                Byte state = export.getState();
                switch (state) {
                    case CoolOrder.ORDER_STATE_0:
                        export.setStateShow("待支付");
                    case CoolOrder.ORDER_STATE_1:
                        export.setStateShow("代发货");
                    case CoolOrder.ORDER_STATE_2:
                        export.setStateShow("待收货");
                    case CoolOrder.ORDER_STATE_3:
                        export.setStateShow("待评价");
                    case CoolOrder.ORDER_STATE_4:
                        export.setStateShow("已完成");
                    case CoolOrder.ORDER_STATE_5:
                        export.setStateShow("已退款");
                    case CoolOrder.ORDER_STATE_99:
                        export.setStateShow("已取消");
                }
            }
        }
        
        jsonData.setRows(orderList);
    }
    
    /**
     * 查询订单流水
     */
    @Override
    public void getCoolOrderListSerial(CoolOrder order, JsonData jsonData) {
        List<CoolOrder> orderList = new ArrayList<CoolOrder>();
        orderList = coolOrderMapper.getCoolOrderList(order);
        
        jsonData.setRows(orderList);
    }
    
    // 通用更新
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
     * 统计订单数
     */
    @Override
    public void getSumCoolOrderNumber(Map<String, Object> param, JsonData jsonData) {
        String selectType = param.get("selectType").toString(); // 1 按年 2 按月 3 按日
        String time = null;
        if (param.get("time") != null) {
            time = param.get("time").toString();
        }
        List<StatisticsPo> poList = coolOrderMapper.getSumCoolOrderNumber(selectType, time);
        List<StatisticsPo> list = new ArrayList<StatisticsPo>();
        for (StatisticsPo po : poList) {
            if ("1".equals(selectType)) {
                po.setStatistics_k(po.getStatistics_k().substring(5, 7));
            }
            if ("2".equals(selectType)) {
                po.setStatistics_k(po.getStatistics_k().substring(8, 10));
            }
            if ("3".equals(selectType)) {
                po.setStatistics_k(po.getStatistics_k().substring(11, 13));
            }
            list.add(po);
        }
        
        jsonData.setRows(list);
    }
    
    /**
     * 统计销售额
     */
    @Override
    public void getSumCoolOrderSales(Map<String, Object> param, JsonData jsonData) {
        String selectType = param.get("selectType").toString(); // 1 按年 2 按月 3 按日
        String time = null;
        if (param.get("time") != null) {
            time = param.get("time").toString();
        }
        List<StatisticsPo> poList = coolOrderMapper.getSumCoolOrderSales(selectType, time);
        List<StatisticsPo> list = new ArrayList<StatisticsPo>();
        for (StatisticsPo po : poList) {
            if ("1".equals(selectType)) {
                po.setStatistics_k(po.getStatistics_k().substring(5, 7));
            }
            if ("2".equals(selectType)) {
                po.setStatistics_k(po.getStatistics_k().substring(8, 10));
            }
            if ("3".equals(selectType)) {
                po.setStatistics_k(po.getStatistics_k().substring(11, 13));
            }
            list.add(po);
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
    
}
