package com.isell.ei.pay.fuiou.service.impl;

import java.util.Map;
import java.util.Map.Entry;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.isell.core.util.Coder;
import com.isell.ei.pay.fuiou.service.FuiouService;
import com.isell.ei.pay.fuiou.util.FuiouUtil;

/**
 *富友支付业务层
 * 
 * @author 毛伟杰
 * @version [版本号, 2016年3月8日]
 */
@Service("fuiouService")
public class FuiouServiceImpl implements FuiouService{

	 /**
     * log
     */
    private Logger log = Logger.getLogger(FuiouServiceImpl.class);
	
	@Override
	public String webPay(Map<String, String> paramMap) {
        paramMap.put("mchnt_cd", MCHNTCD);
        paramMap.put("order_pay_type", PAYTYPE);
        paramMap.put("ver", VER);
        paramMap.put("pay_merchant_code",PAYMERCHANTCODE);
        paramMap.put("pay_merchant_name",PAYMERCHANTNAME);
        paramMap.put("md5", Coder.encodeMd5(FuiouUtil.getParameter(paramMap)));
		StringBuilder sbHtml = new StringBuilder();
        sbHtml.append("<form id='fuyousubmit' name='fuyousubmit' action='" + FUIOU_CESHI + "' method='"
            + METHOD + "'>");
        
        for (Entry<String, String> entry : paramMap.entrySet()) {
            sbHtml.append("<input type='hidden' name='" + entry.getKey() + "' value='" + entry.getValue() + "'/>");
        }
        
        // submit按钮控件请不要含有name属性
        sbHtml.append("<input type='submit' value='确认' style='display:none;'></form>");
        sbHtml.append("<script>document.forms['fuyousubmit'].submit();</script>");
        
        log.info(sbHtml.toString());
        return sbHtml.toString(); 
	}

}
