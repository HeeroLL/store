package com.sell.ei.logistics.sf.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sell.bis.sys.NoNeedForExceptionHandler;
import com.sell.ei.logistics.sf.service.SFService;
import com.sell.ei.logistics.sf.vo.SOrder;
import com.sell.ei.logistics.sf.vo.SResponse;

/**
 * 
 * 顺丰物流接口Controller
 * 
 * @author lilin
 * @version [版本号, 2015年7月4日]
 */
@Controller
@RequestMapping("logistics/sf")
public class SFLogisticsController implements NoNeedForExceptionHandler {
    
    /**
     * 顺丰接口
     */
    @Resource
    private SFService sfService;
    
    /**
     * 顺丰下订单接口
     * 
     * @param order 订单明细
     * @return 顺丰返回的处理结果
     */
    @RequestMapping("orderService")
    @ResponseBody
    public SResponse orderService(@RequestBody
    SOrder order, HttpServletRequest request) {
        return sfService.orderService(order);
    }
}
