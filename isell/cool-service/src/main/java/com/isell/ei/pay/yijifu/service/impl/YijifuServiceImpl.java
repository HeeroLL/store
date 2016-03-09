package com.isell.ei.pay.yijifu.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.isell.core.util.HttpUtils;
import com.isell.core.util.Identities;
import com.isell.core.util.JsonUtil;
import com.isell.ei.logistics.kuaidi100.service.KuaidiService;
import com.isell.ei.pay.yijifu.bean.CoolYjfRemit;
import com.isell.ei.pay.yijifu.bean.YijifuGoodsClause;
import com.isell.ei.pay.yijifu.bean.YijifuLogisticInfo;
import com.isell.ei.pay.yijifu.bean.YijifuOrderInfo;
import com.isell.ei.pay.yijifu.dao.CoolYjfRemitMapper;
import com.isell.ei.pay.yijifu.dao.CoolYjfRemitOrderMapper;
import com.isell.ei.pay.yijifu.service.YijifuService;
import com.isell.ei.pay.yijifu.util.YijifuUtil;
import com.isell.service.order.service.OrderService;
import com.isell.service.order.vo.CoolOrder;
import com.isell.service.order.vo.CoolOrderItem;

/**
 * 易极付支付service层接口实现类
 * 
 * @author lilin
 * @version [版本号, 2015年7月26日]
 */
@SuppressWarnings("unchecked")
@Service("yijifuService")
public class YijifuServiceImpl implements YijifuService {
    
    /**
     * log
     */
    private Logger log = Logger.getLogger(YijifuServiceImpl.class);
    
    /**
     * 服务的域名
     */
    @Value("${service_domain}")
    private String serviceDomain;
    
    /**
     * 订单服务接口
     */
    @Resource
    private OrderService orderService;
    
    /**
     * 快递查询接口
     */
    @Resource
    private KuaidiService kuaidiService;
    
    /**
     * 易极付批次信息mapper
     */
    @Resource
    private CoolYjfRemitMapper yjfRemitMapper;
    
    /**
     * 易极付订单信息mapper
     */
    @Resource
    private CoolYjfRemitOrderMapper yjfRemitOrderMapper;
    
    @Override
    public String getPayParams(Map<String, String> paramMap) {
        // 删除空参数
        paramMap = YijifuUtil.paraFilter(paramMap);
        paramMap.put("partnerId", PAY_PARTNERID);
        paramMap.put("sellerUserId", PAY_PARTNERID);
        paramMap.put("signType", SIGN_TYPE);
        paramMap.put("version", "2.0"); // 2.0接口可以返回outOrderNo 外部订单号
        // paramMap.put("returnUrl", "http://www.i-coolshop.com");
        paramMap.put("notifyUrl", serviceDomain + "/payNotify/yijifu");
        
        paramMap.put("sign", YijifuUtil.encryptString(YijifuUtil.getParameter(paramMap), PAY_KEY));
        
        StringBuilder sbHtml = new StringBuilder();
        sbHtml.append("<form id=\"yijifusubmit\" name=\"yijifusubmit\" action=\"" + YIJIFU_GATEWAY
            + "\" method=\"POST\">");
        
        for (Entry<String, String> entry : paramMap.entrySet()) {
            sbHtml.append("<input type=\"hidden\" name='" + entry.getKey() + "' value='" + entry.getValue() + "'/>");
        }
        
        // submit按钮控件请不要含有name属性
        sbHtml.append("<input type=\"submit\" value=\"确认\" style=\"display:none;\"></form>");
        sbHtml.append("<script>document.forms['yijifusubmit'].submit();</script>");
        
        log.info(sbHtml.toString());
        return sbHtml.toString();
    }
    
    @Override
    public Map<String, String> tradeRefund(Map<String, String> paramMap) {
        // 删除空参数
        paramMap = YijifuUtil.paraFilter(paramMap);
        paramMap.put("service", "tradeRefund");
        paramMap.put("orderNo", Identities.uuid());
        paramMap.put("partnerId", PAY_PARTNERID);
        paramMap.put("signType", SIGN_TYPE);
        paramMap.put("notifyUrl", serviceDomain + "/payNotify/yijifuRefund"); // 异步通知url
        paramMap.put("sign", YijifuUtil.encryptString(YijifuUtil.getParameter(paramMap), PAY_KEY));
        String result = HttpUtils.httpsPost(YIJIFU_GATEWAY, paramMap);
        
        return JsonUtil.readValue(result, Map.class);
    }
    
    @Deprecated
    @Override
    public Map<String, String> paymentBillV2Order(Map<String, String> paramMap) {
        // 删除空参数
        paramMap = YijifuUtil.paraFilter(paramMap);
        paramMap.put("partnerId", PARTNERID);
        paramMap.put("signType", SIGN_TYPE);
        // paramMap.put("version", "1.0"); // 只支持1.0
        paramMap.put("eshopType", "A"); // A: 特殊业务（易极付实现支付单生成，无支付信息的流程可设置）
        paramMap.put("customsCode", "4600"); // 申请海关代码
        paramMap.put("eshopEntCode", "aa"); // 电商平台的海关备案编号或代码
        paramMap.put("eshopEntName", "bb"); // 电商平台的海关备案名称
        paramMap.put("payerDocType", "01"); // 支付人证件类型 01:居民身份证。
        paramMap.put("goodsCurrency", "142"); // 货款币种编号 142：人民币
        paramMap.put("taxCurrency", "142"); // 税款币种编号 142：人民币
        paramMap.put("freightCurrency", "142"); // 物流币种编号 142：人民币
        paramMap.put("ieType", "1"); // 郑州海关必填 1：进口 2：出口 默认值为1
        
        // paramMap.put("returnUrl", "http://www.i-coolshop.com");
        paramMap.put("notifyUrl", serviceDomain + "/payNotify/yijifuBg");
        paramMap.put("sign", YijifuUtil.encryptString(YijifuUtil.getParameter(paramMap), KEY));
        String result = HttpUtils.httpsPost(YIJIFU_GATEWAY, paramMap);
        
        return JsonUtil.readValue(result, Map.class);
    }
    
    @Override
    public Map<String, String> realNameQuery(Map<String, String> paramMap) {
        paramMap = YijifuUtil.paraFilter(paramMap);
        paramMap.put("partnerId", PARTNERID);
        paramMap.put("orderNo", Identities.uuid());
        paramMap.put("signType", SIGN_TYPE);
        paramMap.put("sign", YijifuUtil.encryptString(YijifuUtil.getParameter(paramMap), KEY));
        
        String result = HttpUtils.httpsPost(YIJIFU_GATEWAY, paramMap);
        
        return JsonUtil.readValue(result, Map.class);
    }
    
    @Override
    public String unionCashierWebPay(Map<String, String> paramMap) {
        // 删除空参数
        paramMap = YijifuUtil.paraFilter(paramMap);
        paramMap.put("service", "unionCashierWebPay");
        paramMap.put("orderNo", Identities.uuid());
        paramMap.put("partnerId", PAY_PARTNERID);
        paramMap.put("signType", SIGN_TYPE);
        paramMap.put("notifyUrl", serviceDomain + "/payNotify/yijifu");
        paramMap.put("sign", YijifuUtil.encryptString(YijifuUtil.getParameter(paramMap), PAY_KEY));
        
        StringBuilder sbHtml = new StringBuilder();
        sbHtml.append("<form id=\"yijifusubmit\" name=\"yijifusubmit\" action=\"" + YIJIFU_GATEWAY
            + "\" method=\"POST\">");
        
        for (Entry<String, String> entry : paramMap.entrySet()) {
            sbHtml.append("<input type=\"hidden\" name='" + entry.getKey() + "' value='" + entry.getValue() + "'/>");
        }
        
        // submit按钮控件请不要含有name属性
        sbHtml.append("<input type=\"submit\" value=\"确认\" style=\"display:none;\"></form>");
        sbHtml.append("<script>document.forms['yijifusubmit'].submit();</script>");
        
        log.info(sbHtml.toString());
        return sbHtml.toString();
    }
    
    @Override
    public Map<String, String> singlePaymentUpload(Map<String, String> paramMap) {
        // 删除空参数
        paramMap = YijifuUtil.paraFilter(paramMap);
        paramMap.put("service", "singlePaymentUpload");
        paramMap.put("orderNo", Identities.uuid());
        paramMap.put("partnerId", PARTNERID);
        paramMap.put("signType", SIGN_TYPE);
        paramMap.put("orderFlowType", "NORMAL"); // 支付单业务类型编码
        paramMap.put("payerDocType", "Identity_Card"); // 支付人证件类型
        
        paramMap.put("goodsCurrency", "CNY"); // 商品币种
        paramMap.put("taxCurrency", "CNY"); // 税率币种
        paramMap.put("freightCurrency", "CNY"); // 物流币种
        paramMap.put("taxAmount", "0"); // 税款金额
        paramMap.put("freightAmount", "0"); // 物流金额
        
        // paramMap.put("returnUrl", serviceDomain); // 同步返回url
        paramMap.put("notifyUrl", serviceDomain + "/payNotify/yijifuBg"); // 异步通知url
        paramMap.put("sign", YijifuUtil.encryptString(YijifuUtil.getParameter(paramMap), KEY));
        String result = HttpUtils.httpsPost(YIJIFUBG_GATEWAY, paramMap);
        
        return JsonUtil.readValue(result, Map.class);
    }
    
    @Override
    @Transactional
    public Map<String, String> corderRemittanceSynOrder(String remittranceBatchNo, String... orderNos) {
        if (StringUtils.isBlank(remittranceBatchNo)) {
            // 生成批次号
            remittranceBatchNo = "isell" + System.currentTimeMillis();
        }
        if (ArrayUtils.isEmpty(orderNos)) {
            throw new RuntimeException("exception.order.null");
        }
        if (orderNos.length > 20) {
            throw new RuntimeException("一次最多操作20笔订单");
        }
        Map<String, String> paramMap = new TreeMap<String, String>();
        
        List<YijifuOrderInfo> orderList = new ArrayList<YijifuOrderInfo>();
        for (String orderNo : orderNos) {
            CoolOrder coolOrder = orderService.getCoolOrderDetailByOrderNo(orderNo);
            if (coolOrder == null || coolOrder.getZffs() == null || coolOrder.getZffs() != CoolOrder.ZFFS_4
                || coolOrder.getTradeNo() == null || CollectionUtils.isEmpty(coolOrder.getItemList())
                || coolOrder.getPsCode() == null || coolOrder.getPsfs() == null) {
                throw new RuntimeException("exception.order.null");
            }
            CoolOrderItem item = coolOrder.getItemList().get(0);
            
            YijifuOrderInfo orderInfo = new YijifuOrderInfo();
            orderInfo.setDetailOrderSerialNo(orderNo);
            orderInfo.setDetailOrderAmount(coolOrder.getTotal());
            orderInfo.setBuyerRealName(coolOrder.getLinkman());
            orderInfo.setBuyerIDNumber(coolOrder.getIdcard());
            
            YijifuLogisticInfo logisticInfo = new YijifuLogisticInfo();
            logisticInfo.setOutOrderNo(orderNo);
            logisticInfo.setLogisticsCompany(kuaidiService.queryPostTypeByName(coolOrder.getPsfs()));
            logisticInfo.setTransportNumber(coolOrder.getPsCode());
            logisticInfo.setConsigneeName(coolOrder.getLinkman());
            logisticInfo.setConsigneeAddress(coolOrder.getLocationP() + coolOrder.getLocationC()
                + coolOrder.getLocationA() + coolOrder.getAddress());
            logisticInfo.setConsigneeContact(coolOrder.getMobile());
            
            YijifuGoodsClause goodsClause = new YijifuGoodsClause();
            goodsClause.setOutId(item.getGid() + "");
            goodsClause.setName(item.getName());
            goodsClause.setQuantity(1);
            goodsClause.setPrice(coolOrder.getTotal());
            
            orderInfo.setLogisticInfo(logisticInfo);
            orderInfo.setGoodsClause(goodsClause);
            orderList.add(orderInfo);
            
        }
        
        // 保存批次信息（如果没保存过的话）
        CoolYjfRemit remit = yjfRemitMapper.getCoolYjfRemitByBatchno(remittranceBatchNo);
        if (remit == null) {
            remit = new CoolYjfRemit();
            remit.setRemittranceBatchno(remittranceBatchNo);
            remit.setStatus("0");
            yjfRemitMapper.saveCoolYjfRemit(remit);
        }
        
        paramMap.put("details", JsonUtil.writeValueAsString(orderList));
        paramMap.put("remittranceBatchNo", remittranceBatchNo);
        paramMap.put("service", "corderRemittanceSynOrder");
        paramMap.put("orderNo", Identities.uuid());
        paramMap.put("partnerId", PARTNERID);
        paramMap.put("signType", SIGN_TYPE);
        paramMap.put("notifyUrl", serviceDomain + "/payNotify/yijifuSynOrder");
        paramMap.put("sign", YijifuUtil.encryptString(YijifuUtil.getParameter(paramMap), KEY));
        
        String result = HttpUtils.httpsPost(YIJIFU_GATEWAY, paramMap);
        Map<String, String> resultMap = JsonUtil.readValue(result, Map.class);
        return resultMap;
    }
    
    @Override
    public String applyRemittranceWithSynOrder(Map<String, String> paramMap) {
        // 删除空参数
        paramMap = YijifuUtil.paraFilter(paramMap);
        CoolYjfRemit remit = yjfRemitMapper.getCoolYjfRemitByBatchno(paramMap.get("remittranceBatchNo"));
        if (remit == null) {
            throw new RuntimeException("批次号不存在");
        }
        paramMap.put("partnerId", PARTNERID);
        paramMap.put("signType", SIGN_TYPE);
        paramMap.put("orderNo", Identities.uuid());
        paramMap.put("service", "applyRemittranceWithSynOrder");
        paramMap.put("notifyUrl", serviceDomain + "/payNotify/yijifuRemit");
        
        paramMap.put("outOrderNo", paramMap.get("remittranceBatchNo")); // 外部订单号与跨境付款批次号相同
        paramMap.put("payAmount", remit.getPayAmount() + ""); // 汇款金额
        paramMap.put("payCurrency", "CNY"); // 汇款币种
        paramMap.put("withdrawCurrency", "CNY"); // 提现币种
        paramMap.put("payMemo", "汇款申请"); // 备注
        paramMap.put("toCountryCode", "HKG"); // 汇往国家代码
        paramMap.put("tradeUseCode", "326"); // 交易用途编码
        paramMap.put("payeeName", "艾售国际贸易(集团)有限公司"); // 收款人姓名
        paramMap.put("payeeAddress", "九龙72长沙湾路重明大厦B栋公寓8楼"); // 收款人地址
        paramMap.put("payeeBankName", "HSBC Hong Kong"); // 收款人银行名称
        paramMap.put("payeeBankAddress", "香港中环皇后大道中1号"); // 收款银行地址
        paramMap.put("payeeBankSwiftCode", "HSBCHKHHHKH"); // 银行国际编码
        paramMap.put("payeeBankNo", "801492315838"); // 收款账号
        
        paramMap.put("sign", YijifuUtil.encryptString(YijifuUtil.getParameter(paramMap), KEY));
        
        StringBuilder sbHtml = new StringBuilder();
        sbHtml.append("<form id=\"yijifusubmit\" name=\"yijifusubmit\" action=\"" + YIJIFU_GATEWAY
            + "\" method=\"POST\">");
        
        for (Entry<String, String> entry : paramMap.entrySet()) {
            sbHtml.append("<input type=\"hidden\" name='" + entry.getKey() + "' value='" + entry.getValue() + "'/>");
        }
        
        // submit按钮控件请不要含有name属性
        sbHtml.append("<input type=\"submit\" value=\"确认\" style=\"display:none;\"></form>");
        sbHtml.append("<script>document.forms['yijifusubmit'].submit();</script>");
        
        log.info(sbHtml.toString());
        return sbHtml.toString();
    }
    
}
