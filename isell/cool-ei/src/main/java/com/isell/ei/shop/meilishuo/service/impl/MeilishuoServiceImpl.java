package com.isell.ei.shop.meilishuo.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.isell.cache.service.JVMCacheService;
import com.isell.core.util.Coder;
import com.isell.core.util.DateUtil;
import com.isell.core.util.HttpUtils;
import com.isell.core.util.JsonUtil;
import com.isell.core.util.Record;
import com.isell.ei.shop.meilishuo.bean.MeilishuoAddressInfo;
import com.isell.ei.shop.meilishuo.bean.MeilishuoBuyerInfo;
import com.isell.ei.shop.meilishuo.bean.MeilishuoErrorInfo;
import com.isell.ei.shop.meilishuo.bean.MeilishuoGoodsInfo;
import com.isell.ei.shop.meilishuo.bean.MeilishuoHopLogisticsCompanies;
import com.isell.ei.shop.meilishuo.bean.MeilishuoLogisticsCompany;
import com.isell.ei.shop.meilishuo.bean.MeilishuoOrderData;
import com.isell.ei.shop.meilishuo.bean.MeilishuoOrderInfo;
import com.isell.ei.shop.meilishuo.bean.MeilishuoOrderListResult;
import com.isell.ei.shop.meilishuo.bean.MeilishuoOrderResult;
import com.isell.ei.shop.meilishuo.bean.MeilishuoPayInfo;
import com.isell.ei.shop.meilishuo.bean.MeilishuoTokenResult;
import com.isell.ei.shop.meilishuo.dao.CoolLogisticsCompanyMlsMapper;
import com.isell.ei.shop.meilishuo.service.MeilishuoService;
import com.isell.ei.shop.meilishuo.vo.CoolLogisticsCompanyMls;
import com.isell.service.order.dao.CoolOrderItemMapper;
import com.isell.service.order.dao.CoolOrderMapper;
import com.isell.service.order.util.OrderUtil;
import com.isell.service.order.vo.CoolOrder;
import com.isell.service.order.vo.CoolOrderItem;
import com.isell.service.product.dao.CoolProductGgMapper;
import com.isell.service.product.dao.CoolProductMapper;
import com.isell.service.product.vo.CoolProduct;
import com.isell.service.product.vo.CoolProductGg;

/**
 * 美丽说业务层接口实现类
 * 
 * @author wangpeng
 * @version [版本号, 2015-12-31]
 */
@Service("meilishuoService")
public class MeilishuoServiceImpl implements MeilishuoService {
    
    /**
     * log
     */
    private static final Logger log = Logger.getLogger(MeilishuoServiceImpl.class);
    
    /**
     * JVM缓存服务接口
     */
    @Resource
    protected JVMCacheService jvmCacheService;
    
    /**
     * 订单表mapper
     */
    @Resource
    private CoolOrderMapper coolOrderMapper;
    
    /**
     * 
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
     * 美丽说物流公司信息表mapper
     */
    @Resource
    private CoolLogisticsCompanyMlsMapper coolLogisticsCompanyMlsMapper;
    
    /**
     * coolJdbcTemplate
     */
    @Resource
    private JdbcTemplate jdbcTemplate;
    
    /**
     * 调用HIGO登录授权界面
     * 
     * @param paramMap
     * @return 响应结果
     */
    @Override
    public void login(Map<String, String> paramMap) {
        paramMap.put("client_id", MLS_APPKEY);
        paramMap.put("state", STATE);
        paramMap.put("redirect_url", REDIRECT_URL);
        
        String result = HttpUtils.httpPost(HOST + "/oauth2/login", JsonUtil.writeValueAsString(paramMap));
        log.info(result);
        // System.out.println(result);
    }
    
    /**
     * 获取access_token
     * 
     * @param param
     * @return
     */
    @Override
    public String getAccessToken(Map<String, String> param) {
        Map<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("grant_type", "refresh_token"); // 固定值
        paramMap.put("client_id", MLS_APPKEY);
        paramMap.put("client_secret", MLS_APPSECRET);
        paramMap.put("code", MLS_CODE);
        paramMap.put("redirect_uri", REDIRECT_URL);
        
        String result = HttpUtils.httpPost(TOKEN_URL, JsonUtil.writeValueAsString(paramMap));
        System.out.println(result);
        // JsonData jsonData = JsonUtil.readValue(result, JsonData.class);
        MeilishuoTokenResult tokenResult = JsonUtil.readValue(result, MeilishuoTokenResult.class);
        jvmCacheService.set("meilishuo_access_token", tokenResult.getAccess_token());
        jvmCacheService.set("meilishuo_refresh_token", tokenResult.getRefresh_token());
        return result;
    }
    
    /**
     * 下载美丽说订单
     * 
     * @param param
     * @return
     */
    @Override
    public Record getMeilishuoOrder(Map<String, String> param) {
        Record record = new Record();
        boolean success = false;
        String msg = "";
        Map<String, String> paramMap = new HashMap<String, String>();
        String timestamp = DateUtil.dateToStr(DateUtil.yyyy_MM_dd_HH_mm_ss, new Date());
        String ctimeStart = param.get("ctimeStart").toString();
        String ctimeEnd = param.get("ctimeEnd").toString();
        
        paramMap.put("method", "higo.order.search");
        paramMap.put("timestamp", timestamp);
        paramMap.put("format", "json");
        paramMap.put("app_key", MLS_APPKEY);
        paramMap.put("v", "1.0");
        paramMap.put("sign_method", "md5");
        paramMap.put("session", ACCESS_TOKEN);
        paramMap.put("ctime_start", ctimeStart);
        paramMap.put("ctime_end", ctimeEnd);
        
        // 可选
        paramMap.put("order_status", "1001");
        paramMap.put("order_type", "1001");
        
        boolean flag = true;
        Integer i = 1;
        List<MeilishuoOrderData> orderDataList = new ArrayList<MeilishuoOrderData>();
        while (flag) {
            paramMap.put("page_curr", i.toString());
            StringBuffer signBuf = new StringBuffer(MLS_APPSECRET);
            signBuf.append("app_key").append(MLS_APPKEY);
            signBuf.append("ctime_end").append(ctimeEnd);
            signBuf.append("ctime_start").append(ctimeStart);
            signBuf.append("format").append("json");
            signBuf.append("method").append("higo.order.search");
            signBuf.append("order_status").append("1001");
            signBuf.append("order_type").append("1001");
            signBuf.append("page_curr").append("1");
            signBuf.append("session").append(ACCESS_TOKEN);
            signBuf.append("sign_method").append("md5");
            signBuf.append("timestamp").append(timestamp);
            signBuf.append("v").append("1.0");
            signBuf.append(MLS_APPSECRET);
            // System.out.println(signBuf.toString());
            // System.out.println(Coder.encodeMd5(signBuf.toString()).toUpperCase());
            paramMap.put("sign", Coder.encodeMd5(signBuf.toString()).toUpperCase());
            String result = HttpUtils.httpPost(HOST, paramMap);
            System.out.println(result);
            MeilishuoOrderResult orderResult = new MeilishuoOrderResult();
            if (StringUtils.isNotEmpty(result)) {
                orderResult = JsonUtil.readValue(result, MeilishuoOrderResult.class);
                MeilishuoErrorInfo errorInfo = orderResult.getError_response();
                if (errorInfo != null) {
                    String error = errorInfo.getError();
                    msg = "美丽说订单获取失败，原因：" + error;
                    log.info("美丽说订单获取失败，原因：" + error);
                    flag = false;
                } else {
                    orderResult = JsonUtil.readValue(result, MeilishuoOrderResult.class);
                    MeilishuoOrderListResult listResult = orderResult.getHigo_order_search();
                    List<MeilishuoOrderData> list = listResult.getOrder_data();
                    orderDataList.addAll(list);
                    i++;
                }
            } else {
                flag = false;
            }
        }
        
        // 转成订单保存
        for (MeilishuoOrderData mOrder : orderDataList) {
            CoolOrder order = new CoolOrder();
            
            String orderNo = "";
            String shopId = "9d2346b10b5e49289c6c8ac7dba93d5e";
            order.setoType(new Byte("1")); // pc 订单
            order.setOrderType(new Byte("1")); // 一件代发
            order.setSupplier(shopId);
            order.setmId(5274);
            order.setSupName("美丽说");
            
            // 订单信息
            MeilishuoOrderInfo orderInfo = mOrder.getOrder_info();
            if (orderInfo != null) {
                String orderOldNo = orderInfo.getOrder_id() + "";
                List<CoolOrder> oList = coolOrderMapper.getCoolOrderByOrderOldNoList(orderOldNo, shopId);
                if (CollectionUtils.isNotEmpty(oList)) { // 重复不下载
                    log.info("时间：" + DateUtil.dateToStr(DateUtil.yyyy_MM_dd_HH_mm_ss, new Date()) + "美丽说订单号为"
                        + orderOldNo + "的订单因为存在相同的订单号而终止导入");
                    continue;
                } else {
                    order.setOrderOldno(orderOldNo);
                }
                int orderStatus = orderInfo.getOrder_status();
                if (orderStatus == 1000) { // 未支付
                    order.setState(CoolOrder.ORDER_STATE_0);
                } else if (orderStatus == 1001) { // 已支付
                    order.setState(CoolOrder.ORDER_STATE_1);
                } else if (orderStatus == 1003) { // 已发货
                    order.setState(CoolOrder.ORDER_STATE_2);
                } else if (orderStatus == 1004) { // 确认收货
                    order.setState(CoolOrder.ORDER_STATE_3);
                }
                order.setComments(orderInfo.getBuyer_comment());
                order.setTotal(new BigDecimal(orderInfo.getOrder_price()));
                order.setPsPrice(new BigDecimal(orderInfo.getShipping_fee()));
                order.setDiscountPrice(new BigDecimal(orderInfo.getShop_coupon_amount()).add(new BigDecimal(
                    orderInfo.getPlatform_coupon_amount())));
                order.setCreatetime(orderInfo.getOrder_ctime());
                
                // 商品信息
                List<MeilishuoGoodsInfo> goodsInfo = mOrder.getGoods_info();
                
                orderNo = OrderUtil.generateOrderNo((int)goodsInfo.get(0).getGoods_id());
                order.setOrderNo(orderNo);
                
                for (MeilishuoGoodsInfo goods : goodsInfo) {
                    CoolOrderItem item = new CoolOrderItem();
                    long goodsId = goods.getGoods_id();
                    
                    item.setOrderNo(orderNo);
                    // TODO 这里需要转换成自己系统的商品规格id
                    Integer gId = (int)goodsId;
                    CoolProductGg gg = coolProductGgMapper.getCoolProductGgById(gId);
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
                    item.setGid(gg.getId());
                    item.setlPrice(new BigDecimal(goods.getGoods_price()));
                    item.setCount((int)goods.getGoods_quantity());
                    
                    coolOrderItemMapper.saveCoolOrderItem(item);
                    
                    // 处理商品库存和销量
                    jdbcTemplate.update("update cool_product_gg set stock=stock-" + item.getCount() + ",sales=sales+"
                        + item.getCount() + " where id=?", item.getGid());
                    jdbcTemplate.update("update cool_product set sales=sales+" + item.getCount() + " where id=?",
                        item.getgId());
                }
                
                // 支付信息
                MeilishuoPayInfo payInfo = mOrder.getPay_info();
                String payEnterprice = payInfo.getPay_enterprise();
                if ("微信".equals(payEnterprice)) {
                    order.setZffs(3); // 微信
                } else if ("支付宝".equals(payEnterprice)) {
                    order.setZffs(2); // 支付宝
                }
                String gateway_pay_no = payInfo.getGateway_pay_no(); // 支付流水号
                order.setTradeNo(gateway_pay_no);
                String customs_declare_no = payInfo.getCustoms_declare_no();// 海关报关流水号
                if (StringUtils.isNotEmpty(customs_declare_no)) {
                    order.setTradeNo(customs_declare_no);
                }
                order.setPayTime(payInfo.getPay_time());
                
                // 收件人地址信息
                MeilishuoAddressInfo addressInfo = mOrder.getAddress_info();
                order.setMobile(addressInfo.getMobile());
                order.setLocationP(addressInfo.getProvince());
                order.setLocationC(addressInfo.getCity());
                order.setLocationA(addressInfo.getDistrict());
                order.setAddress(addressInfo.getStreet());
                
                // 买家信息
                MeilishuoBuyerInfo buyerInfo = mOrder.getBuyer_info();
                order.setLinkman(buyerInfo.getBuyer_nick_name());
                order.setIdcard(buyerInfo.getBuyer_id());
                
                coolOrderMapper.saveCoolOrder(order);
            }
        }
        
        record.set("msg", msg);
        record.set("success", success);
        return record;
    }
    
    /**
     * 刷新access_token
     * 
     * @param code
     * @return
     */
    @Override
    public MeilishuoTokenResult getRefreshToken(String token) {
        Map<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("grant_type", "refresh_token"); // 固定值
        paramMap.put("code", token);
        String result = HttpUtils.httpPost(HOST + "/oauth2/token", JsonUtil.writeValueAsString(paramMap));
        // JsonData jsonData = JsonUtil.readValue(result, JsonData.class);
        MeilishuoTokenResult tokenResult = JsonUtil.readValue(result, MeilishuoTokenResult.class);
        jvmCacheService.set("meilishuo_access_token", tokenResult.getAccess_token());
        jvmCacheService.set("meilishuo_refresh_token", tokenResult.getRefresh_token());
        return tokenResult;
    }
    
    /**
     * 获取美丽说物流公司信息
     */
    @Override
    public void getMeilishuoLogisticsCompanies() {
        Map<String, String> paramMap = new HashMap<String, String>();
        String timestamp = DateUtil.dateToStr(DateUtil.yyyy_MM_dd_HH_mm_ss, new Date());
        paramMap.put("method", "higo.logistics.companies.get");
        paramMap.put("timestamp", timestamp);
        paramMap.put("format", "json");
        paramMap.put("app_key", MLS_APPKEY);
        paramMap.put("v", "1.0");
        paramMap.put("sign_method", "md5");
        paramMap.put("session", ACCESS_TOKEN);
        
        StringBuffer signBuf = new StringBuffer(MLS_APPSECRET);
        signBuf.append("app_key").append(MLS_APPKEY);
        signBuf.append("format").append("json");
        signBuf.append("method").append("higo.logistics.companies.get");
        signBuf.append("session").append(ACCESS_TOKEN);
        signBuf.append("sign_method").append("md5");
        signBuf.append("timestamp").append(timestamp);
        signBuf.append("v").append("1.0");
        signBuf.append(MLS_APPSECRET);
        // System.out.println(signBuf.toString());
        // System.out.println(Coder.encodeMd5(signBuf.toString()).toUpperCase());
        paramMap.put("sign", Coder.encodeMd5(signBuf.toString()).toUpperCase());
        String result = HttpUtils.httpPost(HOST, paramMap);
        
        System.out.println(result);
        
        MeilishuoHopLogisticsCompanies hop_logistics_companies_get =
            JsonUtil.readValue(result, MeilishuoHopLogisticsCompanies.class);
        List<MeilishuoLogisticsCompany> company_data =
            hop_logistics_companies_get.getHop_logistics_companies_get().getCompany_data();
        for (MeilishuoLogisticsCompany company : company_data) {
            CoolLogisticsCompanyMls mls = new CoolLogisticsCompanyMls();
            mls.setCompanyId(company.getCompany_id());
            mls.setCompanyName(company.getCompany_name());
            coolLogisticsCompanyMlsMapper.saveCoolLogisticsCompanyMls(mls);
        }
        
    }
    
}
