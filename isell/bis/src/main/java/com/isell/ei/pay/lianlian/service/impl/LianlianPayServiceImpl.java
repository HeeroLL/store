package com.isell.ei.pay.lianlian.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.isell.core.util.Coder;
import com.isell.core.util.HttpUtils;
import com.isell.core.util.JsonUtil;
import com.isell.ei.pay.lianlian.bean.LianlianPayInfo;
import com.isell.ei.pay.lianlian.bean.LianlianRefundInfo;
import com.isell.ei.pay.lianlian.bean.LianlianRefundResult;
import com.isell.ei.pay.lianlian.service.LianlianPayService;

/**
 * 连连支付业务层实现类
 * 
 * @author lilin
 * @version [版本号, 2015年8月20日]
 */
@Service("lianlianPayService")
public class LianlianPayServiceImpl implements LianlianPayService {
    
    @Override
    public String getPayParams(LianlianPayInfo param) {
        String signSrc =
            VERSION + MERCHANT_ID + param.getMerchant_userid() + param.getBiz_code() + param.getUrl_notify()
                + param.getReq_ip() + param.getGoods_type() + param.getMerchant_orderno()
                + param.getMerchant_trans_date() + param.getTrans_amt() + param.getTrans_cur() + SIGN;
        String signed = Coder.encodeMd5(signSrc);
        StringBuilder params = new StringBuilder();
        /** 版本号 */
        params.append("<input type='hidden' name='version' value='" + VERSION + "' />");
        /** 商户唯一标识 （测试商户号：201401271000001093） */
        params.append("<input type='hidden' name='merchant_id' value='" + MERCHANT_ID + "' />");
        /** 用户ID */
        if (!StringUtils.isEmpty(param.getMerchant_userid())) {
            params.append("<input type='hidden' name='merchant_userid' value='" + param.getMerchant_userid() + "' />");
        }
        /** 支付方式 */
        if (!StringUtils.isEmpty(param.getPay_method())) {
            params.append("<input type='hidden' name='pay_method' value='" + param.getPay_method() + "' />");
        }
        /** 支付方式明细 */
        if (!StringUtils.isEmpty(param.getPayment_detail())) {
            params.append("<input type='hidden' name='payment_detail' value='" + param.getPayment_detail() + "' />");
        }
        /** 业务编号 */
        params.append("<input type='hidden' name='biz_code' value='" + param.getBiz_code() + "' />");
        /** 支付结果通知地址 */
        params.append("<input type='hidden' name='url_notify' value='" + param.getUrl_notify() + "' />");
        /** 服务器IP */
        params.append("<input type='hidden' name='req_ip' value='" + param.getReq_ip() + "' />");
        /** 支付单有效期 */
        if (!StringUtils.isEmpty(param.getBill_validdate())) {
            params.append("<input type='hidden' name='bill_validdate' value='" + param.getBill_validdate() + "' />");
        }
        /** 支付结束回显URL */
        if (!StringUtils.isEmpty(param.getUrl_return())) {
            params.append("<input type='hidden' name='url_return' value='" + param.getUrl_return() + "' />");
        }
        /** 会员信息 */
        if (!StringUtils.isEmpty(param.getFirst_name())) {
            params.append("<input type='hidden' name='first_name' value='" + param.getFirst_name() + "' />");
        }
        if (!StringUtils.isEmpty(param.getLast_name())) {
            params.append("<input type='hidden' name='last_name' value='" + param.getLast_name() + "' />");
        }
        if (!StringUtils.isEmpty(param.getEmail())) {
            params.append("<input type='hidden' name='email' value='" + param.getEmail() + "' />");
        }
        if (!StringUtils.isEmpty(param.getPhone())) {
            params.append("<input type='hidden' name='phone' value='" + param.getPhone() + "' />");
        }
        if (!StringUtils.isEmpty(param.getShipping_address())) {
            params.append("<input type='hidden' name='shipping_address' value='" + param.getShipping_address() + "' />");
        }
        
        /** 订单信息 */
        /** 商品类别 */
        if (!StringUtils.isEmpty(param.getGoods_type())) {
            params.append("<input type='hidden' name='goods_type' value='" + param.getGoods_type() + "' />");
        }
        /** 商品订单号 */
        if (!StringUtils.isEmpty(param.getMerchant_orderno())) {
            params.append("<input type='hidden' name='merchant_orderno' value='" + param.getMerchant_orderno() + "' />");
        }
        /** 商品订单交易时间 */
        if (!StringUtils.isEmpty(param.getMerchant_trans_date())) {
            params.append("<input type='hidden' name='merchant_trans_date' value='" + param.getMerchant_trans_date()
                + "' />");
        }
        /** 订单交易金额 单位为厘 */
        if (!StringUtils.isEmpty(param.getTrans_amt())) {
            params.append("<input type='hidden' name='trans_amt' value='" + param.getTrans_amt() + "' />");
        }
        /** 订单币种 */
        if (!StringUtils.isEmpty(param.getTrans_cur())) {
            params.append("<input type='hidden' name='trans_cur' value='" + param.getTrans_cur() + "' />");
        }
        /** 订单地址 */
        if (!StringUtils.isEmpty(param.getUrl_order())) {
            params.append("<input type='hidden' name='url_order' value='" + param.getUrl_order() + "' />");
        }
        /** 订单信息 */
        if (!StringUtils.isEmpty(param.getInfo_order())) {
            params.append("<input type='hidden' name='info_order' value='" + param.getInfo_order() + "' />");
        }
        /** 商品编号 */
        if (!StringUtils.isEmpty(param.getGoods_no())) {
            params.append("<input type='hidden' name='goods_no' value='" + param.getGoods_no() + "' />");
        }
        /** 商品名称 */
        if (!StringUtils.isEmpty(param.getGoods_name())) {
            params.append("<input type='hidden' name='goods_name' value='" + param.getGoods_name() + "' />");
        }
        /** 网关信息 1.IOS 2.Android 3.WAP */
        if (!StringUtils.isEmpty(param.getApp_request())) {
            params.append("<input type='hidden' name='app_request' value='" + param.getApp_request() + "' />");
        }
        /** 备注信息 */
        if (!StringUtils.isEmpty(param.getRemark())) {
            params.append("<input type='hidden' name='remark' value='" + param.getRemark() + "' />");
        }
        /** 风控参数 */
        if (!StringUtils.isEmpty(param.getRisk_request_json())) {
            params.append("<input type='hidden' name='risk_request_json' value='" + param.getRisk_request_json()
                + "' />");
        }
        /** 分账信息 */
        if (!StringUtils.isEmpty(param.getShareing_data())) {
            params.append("<input type='hidden' name='shareing_data' value='" + param.getShareing_data() + "' />");
        }
        /** 报关口岸 */
        if (!StringUtils.isEmpty(param.getExtend_field1())) {
            params.append("<input type='hidden' name='extend_field1' value='" + param.getExtend_field1() + "' />");
        }
        /** 申报通知地址 */
        if (!StringUtils.isEmpty(param.getDeclare_notify())) {
            params.append("<input type='hidden' name='declare_notify' value='" + param.getDeclare_notify() + "' />");
        }
        
        /** 签名 */
        params.append("<input type='hidden' name='sign' value='" + signed + "' />");
        /** 签名方式 */
        params.append("<input type='hidden' name='sign_method' value='" + SIGN_METHOD + "' />");
        
        return params.toString();
    }

    @Override
    public LianlianRefundResult refund(LianlianRefundInfo param) {
        String signSrc =
            param.getVersion() + param.getMerchant_id() + param.getOid_billno() + param.getCol_custid()
                + param.getCol_amt_refund() + param.getCol_cur_code() + param.getSign();
        String signed = Coder.encodeMd5(signSrc);
        
        Map<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("version", param.getVersion());
        paramMap.put("merchant_id", param.getMerchant_id());
        paramMap.put("oid_billno", param.getOid_billno());
        paramMap.put("col_custid", param.getCol_custid());
        paramMap.put("col_amt_refund", param.getCol_amt_refund());
        paramMap.put("col_cur_code", param.getCol_cur_code());
        paramMap.put("url_notify", param.getUrl_notify());
        paramMap.put("sign", signed);
        paramMap.put("sign_method", SIGN_METHOD);
        
        String result = HttpUtils.httpsPost(REFUND_URL, paramMap);
        
        return JsonUtil.readValue(result, LianlianRefundResult.class);
    }
    
}
