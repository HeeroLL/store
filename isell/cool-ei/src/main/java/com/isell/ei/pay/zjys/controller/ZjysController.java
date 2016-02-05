package com.isell.ei.pay.zjys.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.isell.core.util.JsonData;
import com.isell.ei.pay.zjys.service.ZjysService;
import com.isell.ei.pay.zjys.vo.ZjysBuyTradeNoRes;
import com.isell.service.order.vo.CoolOrder;

/**
 * 浙江银商支付控制层
 * 
 * @author lilin
 * @version [版本号, 2016年1月28日]
 */
@Controller
@RequestMapping("pay/zjys")
public class ZjysController {
    /**
     * 浙江银商服务层
     */
    @Resource
    private ZjysService zjysService;
    
    /**
     * 购买支付流水号
     * 
     * @param paramMap 参数信息
     * @return 返回封装后的信息
     */
    @RequestMapping("buyTradeNo")
    @ResponseBody
    public JsonData buyTradeNo(@RequestBody CoolOrder coolOrder) {
        JsonData jsonData = new JsonData();
        ZjysBuyTradeNoRes zjysBuyTradeNoRes = zjysService.buyTradeNo(coolOrder);
        if (zjysBuyTradeNoRes.getResult() != null) {
            jsonData.setSuccess(zjysBuyTradeNoRes.getResult());
        }
        jsonData.setMsg(zjysBuyTradeNoRes.getErrorMsg());
        jsonData.setData(zjysBuyTradeNoRes);
        return jsonData;
    }
}
