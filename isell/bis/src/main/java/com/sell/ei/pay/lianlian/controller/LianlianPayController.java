package com.sell.ei.pay.lianlian.controller;

import java.io.IOException;
import java.io.PrintWriter;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sell.ei.pay.lianlian.util.LianlianPayInfo;

@Controller
@RequestMapping("pay/lianlian")
public class LianlianPayController {
    
    /**
     * 手机端连连支付
     *
     * @param lianlianPayInfo 连连支付信息
     * @param ops 输出流
     * @throws IOException IOException
     */
    @RequestMapping("wapPay")
    public void wapPay(@RequestBody LianlianPayInfo lianlianPayInfo, PrintWriter writer) {
        writer.write("<form id='form' action='https://yintong.com.cn/globalpay/wap/gateway/securityCashier.htm' method='post'>");
        writer.write(lianlianPayInfo.getParamInputs());
        writer.write("</form>");
        writer.write("<script>document.getElementById('form').submit();</script>");
        writer.flush();
    }
}
