package com.isell.ei.pay.zjys.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.stereotype.Service;

import com.isell.core.util.Coder;
import com.isell.core.util.HttpUtils;
import com.isell.core.util.JsonUtil;
import com.isell.ei.pay.zjys.service.ZjysService;
import com.isell.ei.pay.zjys.vo.ZjysBuyTradeNoReq;
import com.isell.ei.pay.zjys.vo.ZjysBuyTradeNoRes;
import com.isell.ei.pay.zjys.vo.ZjysOrderInfo;
import com.isell.service.order.vo.CoolOrder;

/**
 * 浙江银商支付服务层接口
 * 
 * @author lilin
 * @version [版本号, 2016年1月28日]
 */
@Service
public class ZjysServiceImpl implements ZjysService {
    
    @Override
    public ZjysBuyTradeNoRes buyTradeNo(CoolOrder... coolOrders) {
        if (ArrayUtils.isEmpty(coolOrders)) {
            throw new RuntimeException("exception.order.null");
        }
        String timestamp = System.currentTimeMillis() + ""; // 时间戳
        
        ZjysBuyTradeNoReq req = new ZjysBuyTradeNoReq();
        req.setAccount(ACCOUNT);
        req.setMethod("addorder"); // API方法  addorder:添加订单
        req.setTimestamp(timestamp);
        req.setSign(Coder.encodeMd5(ACCOUNT + Coder.encodeMd5(PWD) + ACCOUNT + timestamp)); // 签名
        
        List<ZjysOrderInfo> orderList = new ArrayList<ZjysOrderInfo>();
        for (CoolOrder coolOrder : coolOrders) {
            ZjysOrderInfo order = new ZjysOrderInfo();
            order.setOutTrandNo(coolOrder.getOrderNo());
            order.setRealName(coolOrder.getLinkman());
            order.setAmount(coolOrder.getTotal());
            order.setIdNum(coolOrder.getIdcard());
            order.setTelephone(coolOrder.getMobile());
            
            orderList.add(order);
        }
        req.setOrders(orderList);
        
        String result = HttpUtils.httpPost(BUYTRADENO_URL, JsonUtil.writeValueAsString(req));
        
        return JsonUtil.readValue(result, ZjysBuyTradeNoRes.class);
    }
}
