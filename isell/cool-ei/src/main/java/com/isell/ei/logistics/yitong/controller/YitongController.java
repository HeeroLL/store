package com.isell.ei.logistics.yitong.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.isell.core.util.JsonData;
import com.isell.ei.logistics.yitong.bean.YitongRequestItem;
import com.isell.ei.logistics.yitong.bean.YitongRequestRow;
import com.isell.ei.logistics.yitong.bean.YitongRequestRows;
import com.isell.ei.logistics.yitong.bean.YitongResponse;
import com.isell.ei.logistics.yitong.service.YitongService;
import com.isell.service.custorms.po.CoolProductCustomsZz;
import com.isell.service.custorms.service.CoolProductCustomsZzService;
import com.isell.service.order.service.OrderService;
import com.isell.service.order.vo.CoolOrder;
import com.isell.service.order.vo.CoolOrderItem;

/**
 * 易通接口Controller
 * 
 * @author lilin
 * @version [版本号, 2015年12月3日]
 */
@Controller
@RequestMapping("logistics/yitong")
public class YitongController {
    /**
     * log
     */
    private static final Logger log = Logger.getLogger(YitongController.class);
    
    /**
     *  郑州商品海关备案业务层接口
     */
    @Resource
    private CoolProductCustomsZzService coolProductCustomsZzService;
    
    /**
     * 易通service层接口
     */
    @Resource
    private YitongService yitongService;
    
    /**
     * 订单service层接口
     */
    @Resource
    private OrderService orderService;
    
    /**
     * 发送订单信息
     * 
     * @param orderNos 订单编号集合
     * @return 封装后的响应数据
     */
    @RequestMapping("sendOrder")
    @ResponseBody
    public JsonData sendOrder(String... orderNos) {
        YitongResponse response = yitongService.sendOrder(getYitongRequestRows(false, orderNos));
        JsonData jsonData = new JsonData();
        jsonData.setData(response);
        return jsonData;
    }
    
    /**
     * 发送订单删除报文
     * 
     * @param orderNos 订单编号
     * @return 封装后的响应数据
     */
    @RequestMapping("deleteOrder")
    @ResponseBody
    public JsonData deleteOrder(String orderNos) {
        YitongResponse response = yitongService.sendOrder(getYitongRequestRows(true, orderNos.split(",")));
        JsonData jsonData = new JsonData();
        jsonData.setData(response);
        return jsonData;
    }
    
    private YitongRequestRows getYitongRequestRows(boolean isDel, String... orderNos) {
        if (orderNos == null) {
            throw new RuntimeException("exception.order.null");
        }
        
        YitongRequestRows yitongRequestRows = new YitongRequestRows();
        yitongRequestRows.setRows(new ArrayList<YitongRequestRow>());
        int rowCount = 0;
        for (String orderNo : orderNos) {
            CoolOrder order = orderService.getCoolOrderDetailByOrderNo(orderNo);
            if (order == null || CollectionUtils.isEmpty(order.getItemList())) {
                log.error("exception.order.null");
                continue;
            }
            YitongRequestRow row = new YitongRequestRow();
            row.setId(++rowCount + "");
            // 如果有运单号则消息重推
            if (order.getPsCode() != null) {
                row.setModifyMark("2");    
            }
            if (isDel) {
                row.setModifyMark("3"); // 删除订单
            }
            row.setOrderNo(orderNo); // 订单号
            row.setConsignee(order.getLinkman()); // 收件人
            row.setCustomerId(order.getIdcard().toUpperCase()); // 身份证号
            row.setConsigneeTelephone(order.getMobile()); // 收件人电话
            row.setProvince(order.getLocationP()); // 省
            row.setCity(order.getLocationC()); // 市
            row.setZone(order.getLocationA()); // 区
            row.setConsigneeAddress(order.getLocationP() + order.getLocationC() + order.getLocationA()
                + order.getAddress()); // 详细地址（包含省市区）
            
            String goodsName = null;
            
            List<YitongRequestItem> goodsItem = new ArrayList<YitongRequestItem>();
            int i = 1;
            int itemCount = 0; // 商品总数量
            for (CoolOrderItem item : order.getItemList()) {
                CoolProductCustomsZz customsInfo = coolProductCustomsZzService.getCustomsInfoByGid(item.getGid());
                goodsName = item.getName();
                YitongRequestItem yitongItem = new YitongRequestItem();
                yitongItem.setId(i + "");
                yitongItem.setItemNo(customsInfo.getGoodId()); // 商品货号  (有表格)
                yitongItem.setItemName(item.getName()); // 货名
                yitongItem.setItemModel(item.getGg()); // 规格
                yitongItem.setItemQuantity(item.getCount()); // 货物数量
                itemCount += item.getCount();
                i++;
                
                goodsItem.add(yitongItem);
            }
            
            row.setGoodsName(goodsName); // 商品名
            row.setQuantity(itemCount); // 商品数量
            
            row.setGoodsItem(goodsItem);
            yitongRequestRows.getRows().add(row);
        }
        if (rowCount == 0) {
            throw new RuntimeException("exception.order.null");
        }
        return yitongRequestRows;
    }
}
