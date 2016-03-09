package com.isell.ps.jooge.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.isell.core.config.BisConfig;
import com.isell.core.mybatis.page.PageInfo;
import com.isell.core.util.Coder;
import com.isell.core.util.DateUtil;
import com.isell.core.util.JsonUtil;
import com.isell.ps.jooge.bean.JoogeParam;
import com.isell.ps.jooge.bean.Merch;
import com.isell.ps.jooge.bean.Order;
import com.isell.ps.jooge.bean.OrderDetail;
import com.isell.ps.jooge.bean.OrderInfo;
import com.isell.ps.jooge.bean.OrderList;
import com.isell.ps.jooge.bean.OrderRow;
import com.isell.ps.jooge.bean.OrderSend;
import com.isell.ps.jooge.bean.Payment;
import com.isell.ps.jooge.bean.ProductInfo;
import com.isell.ps.jooge.bean.ProductList;
import com.isell.ps.jooge.bean.Prop;
import com.isell.ps.jooge.bean.SKU;
import com.isell.ps.jooge.bean.Stock;
import com.isell.service.order.po.CoolOrderSelect;
import com.isell.service.order.service.OrderService;
import com.isell.service.order.vo.CoolOrder;
import com.isell.service.order.vo.CoolOrderItem;
import com.isell.service.product.po.CoolProductSelect;
import com.isell.service.product.service.ProductService;
import com.isell.service.product.vo.CoolProduct;
import com.isell.service.product.vo.CoolProductGg;

/**
 * 对珊瑚云系统统一网关入口
 * 
 * @author lilin
 * @version [版本号, 2015年9月24日]
 */
@Controller
@RequestMapping("jooge")
public class JoogeGatewayController {
    /**
     * 艾售KEY
     */
    private static final String APPKEY = "shanhuyun2isell";
    
    /**
     * 加密密钥
     */
    private static final String APPSECRET = "3e38778c37bb4558a3f38515d07e80fa";
    
    /**
     * 订单服务层接口
     */
    @Resource
    private OrderService orderService;
    
    /**
     * 商品服务层接口
     */
    @Resource
    private ProductService productService;
    
    /**
     * 系统配置类
     */
    @Resource
    private BisConfig config;
    
    /**
     * 珊瑚云系统统一网关入口负责校验请求合法性，和转发合法请求到指定控制器
     * 
     * @param param 参数
     * @return 处理结果
     */
    @RequestMapping("gateway")
    public String gateway(JoogeParam param) {
        if (param.getMethod() == null) {
            throw new RuntimeException("exception.access.service.null");
        }
        if (!APPKEY.equals(param.getAppkey())) {
            throw new RuntimeException("exception.access.system-null");
        }
        // 校验请求合法性
        StringBuilder paramStr = new StringBuilder();
        paramStr.append(APPSECRET); // 开头拼上密钥
        paramStr.append("appkey").append(param.getAppkey());
        paramStr.append("method").append(param.getMethod());
        paramStr.append("param").append(param.getParam());
        paramStr.append("timestamp").append(param.getTimestamp());
        paramStr.append("v").append(param.getV());
        paramStr.append(APPSECRET); // 结尾拼上密钥
        String sign = Coder.encodeMd5(paramStr.toString()).toLowerCase();
        if (!sign.equals(param.getSign())) {
            throw new RuntimeException("exception.access.authcode-wrong");
        }
        
        return "forward:" + param.getMethod().replace(".", "/");// 默认为forward模式
    }
    
    /**
     * 请求订单列表
     * 
     * @param param 参数
     * @return 订单列表信息
     */
    @SuppressWarnings("unchecked")
    @RequestMapping("order/list/get")
    @ResponseBody
    public OrderList getOrderList(JoogeParam param) {
        Map<String, Object> paramMap = JsonUtil.readValue(param.getParam(), Map.class);
        CoolOrderSelect paramOrder = new CoolOrderSelect();
        paramOrder.setRows(Integer.parseInt(paramMap.get("PageSize").toString())); // 当前页
        paramOrder.setPage(Integer.parseInt(paramMap.get("PageNo").toString())); // 当前页
        paramOrder.setStartUpdatetime(DateUtil.parseDate((String)paramMap.get("StartModified"),
            DateUtil.yyyy_MM_dd_HH_mm_ss)); // 起始的修改时间
        paramOrder.setEndUpdatetime(DateUtil.parseDate((String)paramMap.get("EndModified"),
            DateUtil.yyyy_MM_dd_HH_mm_ss)); // 结束的修改时间
        paramOrder.setState(CoolOrder.ORDER_STATE_11); // 已通知海关发货
        paramOrder.setFhfs(CoolOrder.FHFS_10); // 发货方式
        
        PageInfo<CoolOrder> page = orderService.getCoolOrderListPage(paramOrder);
        OrderList orderList = new OrderList();
        orderList.setCode("0");
        orderList.setDesc("");
        orderList.setTotalResult(page.getTotal());
        orderList.setOrders(new ArrayList<OrderInfo>());
        if (page.getRows() != null) {
            for (CoolOrder order : page.getRows()) {
                OrderInfo orderInfo = new OrderInfo();
                orderInfo.setOrderId(order.getOrderNo());
                orderInfo.setModified(DateUtil.dateToStr(DateUtil.yyyy_MM_dd_HH_mm_ss, order.getUpdatetime()));
                orderList.getOrders().add(orderInfo);
            }
        }
        return orderList;
    }
    
    /**
     * 请求订单详情
     * 
     * @param param 参数
     * @return 订单详情信息
     */
    @SuppressWarnings("unchecked")
    @RequestMapping("order/detail/get")
    @ResponseBody
    public OrderDetail getOrderDetail(JoogeParam param) {
        Map<String, Object> paramMap = JsonUtil.readValue(param.getParam(), Map.class);
        String orderNo = (String)paramMap.get("Id");
        CoolOrder coolOrder = orderService.getCoolOrderDetailByOrderNo(orderNo);
        if (coolOrder == null || coolOrder.getUpdatetime() == null || coolOrder.getFhfs() == null) {
            throw new RuntimeException("exception.order.null");
        }
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setCode("0");
        orderDetail.setDesc("");
        Order order = new Order();
        order.setOrderId(orderNo);// 订单号，可唯一标识订单的编号
        order.setCreated(DateUtil.dateToStr(DateUtil.yyyy_MM_dd_HH_mm_ss, coolOrder.getCreatetime())); // 订单创建时间
        order.setModified(DateUtil.dateToStr(DateUtil.yyyy_MM_dd_HH_mm_ss, coolOrder.getUpdatetime())); // 订单最后修改时间
        if (coolOrder.getPayTime() != null) {
            order.setPayTime(DateUtil.dateToStr(DateUtil.yyyy_MM_dd_HH_mm_ss, coolOrder.getPayTime())); // 订单付款时间，未付款订单可以不填
        }
        if (coolOrder.getFhfs() != CoolOrder.FHFS_10) {
            order.setStatus("10"); // 已取消
        } else {
            order.setStatus("40"); // 已付款未发货
        }
        order.setReceiverName(coolOrder.getLinkman()); // 收货人姓名
        order.setReceiverProvince(coolOrder.getLocationP()); // 收货人所在省
        order.setReceiverCity(coolOrder.getLocationC()); // 收货人所在市
        order.setReceiverDistrict(coolOrder.getLocationA()); // 收货人所在区县
        order.setReceiverAddress(coolOrder.getAddress()); // 收货人街道地址
        order.setReceiverPhone1(coolOrder.getMobile()); // 收货人手机，和座机必须要有一项不能为空
        order.setReceiverPhone2(coolOrder.getTel()); // 收货人座机，和手机必须要有一项不能为空
        order.setHasInvoice("0"); // 是否需要发票，0：否，1：是，默认值0
        order.setBuyerCode(coolOrder.getMobile()); // 顾客编码(不能重复，不然会卡海关)
        order.setBuyerTrueName(coolOrder.getLinkman()); // 买家真实姓名，跨境订单必填
        order.setBuyerIdCardNo(coolOrder.getIdcard()); // 买家身份证号码，跨境订单必填
        order.setBuyerEmail(coolOrder.getMobile() + "@163.com"); // 买家邮箱地址，跨境订单必填
        order.setBuyerPhone(coolOrder.getMobile()); // 买家手机号，跨境订单必填
        order.setFeeAmount(coolOrder.getPsPrice()); // 向买家收取的运费
        order.setDiscount(coolOrder.getDiscountPrice()); // 整单优惠金额
        order.setPayAmount(coolOrder.getTotal()); // 整单付款金额
        order.setDeliveryWay("物流"); // 发货方式（物流，自提）
        order.setPayments(new ArrayList<Payment>(1)); // 订单支付信息，跨境订单必填
        
        order.setRows(new ArrayList<OrderRow>());// 订单行信息
        if (coolOrder.getItemList() != null) {
            for (CoolOrderItem item : coolOrder.getItemList()) {
                OrderRow row = new OrderRow();
                row.setOrderRowId(item.getId() + ""); // 订单行Id
                row.setMerchId("isell" + item.getGid());// 商品Id 或 Sku Id
                row.setRowDesc(item.getName());// 行描述
                row.setQty(item.getCount()); // 行数量
                row.setPrice(item.getPrice());// 单价
                row.setAdjustFee(0); // 行调整金额
                
                // TODO: 这是订单造假部分
                /*
                 * if (coolOrder.getTotal().subtract(coolOrder.getPsPrice()).compareTo(new BigDecimal(100)) > 0 &&
                 * coolOrder.getItemList().size() == 1) { // 订单金额如果大于0，且只有一种产品时，改价格 BigDecimal payAmount = new
                 * BigDecimal(99).add(coolOrder.getPsPrice()); order.setPayAmount(payAmount); row.setPrice(new
                 * BigDecimal(99).divide(new BigDecimal(item.getCount()), 2, RoundingMode.HALF_UP));// 单价 }
                 */
                // 行金额，应等于Qty * Price – AdjustFee
                row.setAmount(new BigDecimal(item.getCount()).multiply((BigDecimal)row.getPrice()));
                order.getRows().add(row);
            }
        }
        
        Payment pay = new Payment();
        int zffs = coolOrder.getZffs() == null ? 0 : coolOrder.getZffs();
        switch (zffs) {
            case 2:
                pay.setPaymentMethod("10"); // 支付宝
                break;
            case 3:
                pay.setPaymentMethod("30"); // 微信支付
                break;
            case 4:
                pay.setPaymentMethod("23"); // 易极付支付
                break;
            case 5:
                pay.setPaymentMethod("24"); // 易宝支付
                break;
            case 6:
                pay.setPaymentMethod("90"); // 浙江银商
                break;    
            default:
                pay.setPaymentMethod("20"); // 网银支付
                break;
        }
        pay.setAmount(order.getPayAmount()); // 支付金额，精确到分
        pay.setPayNumber(coolOrder.getTradeNo()); // 支付流水号
        order.getPayments().add(pay);
        orderDetail.setOrder(order);
        
        return orderDetail;
    }
    
    /**
     * 推送订单发货信息
     * 
     * @param param 参数
     * @return 订单发货信息
     */
    @SuppressWarnings("unchecked")
    @RequestMapping("order/send")
    @ResponseBody
    public OrderSend sendOrder(JoogeParam param) {
        Map<String, Object> paramMap = JsonUtil.readValue(param.getParam(), Map.class);
        CoolOrder coolOrder = new CoolOrder();
        coolOrder.setOrderNo((String)paramMap.get("Id")); // 订单ID
        // 物流公司名称
        // if ("中通速递".equals(paramMap.get("LogisticCompany"))) {
        // coolOrder.setPsfs("zhongtong");
        // } else if ("邮政速递".equals(paramMap.get("LogisticCompany"))) {
        // coolOrder.setPsfs("ems");
        // } else if ("韵达快运".equals(paramMap.get("LogisticCompany"))) {
        // coolOrder.setPsfs("yunda");
        // } else if ("汇通快运".equals(paramMap.get("LogisticCompany"))) {
        // coolOrder.setPsfs("huitongkuaidi");
        // } else if ("圆通速递".equals(paramMap.get("LogisticCompany"))) {
        // coolOrder.setPsfs("yuantong");
        // } else {
        coolOrder.setPsfs((String)paramMap.get("LogisticCompany"));
        // }
        coolOrder.setState(CoolOrder.ORDER_STATE_2); // 已发货
        coolOrder.setFhfs(CoolOrder.FHFS_10); // 发货方式：宁波保税仓
        coolOrder.setPsCode((String)paramMap.get("LogisiticNumber")); // 运单号，多个运单号的话用逗号隔开
        coolOrder.setUpdatetime(new Date());
        orderService.updateOrder(coolOrder); // 更新订单信息
        
        OrderSend orderSend = new OrderSend();
        orderSend.setCode("0");
        orderSend.setDesc("");
        return orderSend;
    }
    
    /**
     * 请求商品列表
     * 
     * @param param 参数
     * @return 商品列表信息
     */
    @SuppressWarnings("unchecked")
    @RequestMapping("merch/list/get")
    @ResponseBody
    public ProductList getProductList(JoogeParam param) {
        Map<String, Object> paramMap = JsonUtil.readValue(param.getParam(), Map.class);
        CoolProductSelect coolProductSelect = new CoolProductSelect();
        coolProductSelect.setRows(Integer.parseInt(paramMap.get("PageSize").toString()));
        coolProductSelect.setPage(Integer.parseInt(paramMap.get("PageNo").toString()));
        coolProductSelect.setStartUpdatetime(DateUtil.parseDate((String)paramMap.get("StartModified"),
            DateUtil.yyyy_MM_dd_HH_mm_ss));
        coolProductSelect.setEndUpdatetime(DateUtil.parseDate((String)paramMap.get("EndModified"),
            DateUtil.yyyy_MM_dd_HH_mm_ss));
        PageInfo<CoolProduct> page = productService.getCoolProductPageList(coolProductSelect);
        ProductList productList = new ProductList();
        productList.setCode("0");
        productList.setDesc("");
        productList.setTotalResult(page.getTotal());
        productList.setItems(new ArrayList<Merch>());
        if (page.getRows() != null) {
            for (CoolProduct coolProduct : page.getRows()) {
                Merch merch = new Merch();
                merch.setId("isell" + coolProduct.getId());
                merch.setCode(merch.getId());
                merch.setName(coolProduct.getNameEn());
                merch.setPrice(coolProduct.getPrice());
                // 商品计量单位，如个、件
                merch.setUnit("个");
                
                merch.setModified(coolProduct.getUpdatetime());
                productList.getItems().add(merch);
            }
        }
        return productList;
    }
    
    /**
     * 请求商品详情
     * 
     * @param param 参数
     * @return 商品详情信息
     */
    @SuppressWarnings("unchecked")
    @RequestMapping("merch/detail/get")
    @ResponseBody
    public ProductInfo getProductDetail(JoogeParam param) {
        Map<String, Object> paramMap = JsonUtil.readValue(param.getParam(), Map.class);
        CoolProductSelect coolProductSelect = new CoolProductSelect();
        String id = (String)paramMap.get("Id");
        coolProductSelect.setId(Integer.parseInt(id.substring("isell".length())));
        coolProductSelect.setSearchDetail(true);
        
        ProductInfo productInfo = new ProductInfo();
        productInfo.setCode("0");
        productInfo.setDesc("");
        CoolProduct coolProduct = productService.getCoolProductById(coolProductSelect);
        if (coolProduct == null) {
            coolProduct = productService.getCoolProductByGid(coolProductSelect.getId());
            if (coolProduct == null) {
                throw new RuntimeException("exception.product.null");
            }
        }
        Merch merch = new Merch();
        merch.setId("isell" + coolProduct.getId());
        merch.setCode(merch.getId());
        merch.setName(coolProduct.getNameEn());
        merch.setPrice(coolProduct.getPrice());
        merch.setPicUrl(config.getImgDomain() + coolProduct.getLogo()); // 主图路径
        // 商品计量单位，如个、件
        merch.setUnit("个");
        merch.setModified(coolProduct.getUpdatetime());
        if (coolProduct.getGgList() != null) {
            merch.setSkus(new ArrayList<SKU>());
            for (CoolProductGg gg : coolProduct.getGgList()) {
                SKU sku = new SKU();
                sku.setSkuId("isell" + gg.getId());
                sku.setCode(sku.getSkuId());
                sku.setPrice(gg.getCxjg());
                sku.setProps(new ArrayList<Prop>(1));
                sku.getProps().add(new Prop("规格", gg.getGg()));
                
                merch.getSkus().add(sku);
            }
        }
        
        productInfo.setItem(merch);
        return productInfo;
    }
    
    /**
     * 推送商品库存
     * 
     * @param param
     */
    @SuppressWarnings("unchecked")
    @RequestMapping("inventory/update")
    @ResponseBody
    public Stock invenToryUpdate(JoogeParam param) {
        Map<String, Object> paramMap = JsonUtil.readValue(param.getParam(), Map.class);
        Stock s = new Stock();
        s.setId((String)paramMap.get("Id"));
        s.setQty((Integer)paramMap.get("Qty"));
        s.setWarehouse((String)paramMap.get("Warehouse"));
        return s;
    }
}
