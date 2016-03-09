package com.isell.ei.shop.pinduoduo.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.isell.core.util.Coder;
import com.isell.core.util.HttpUtils;
import com.isell.core.util.JaxbUtil;
import com.isell.core.util.JsonData;
import com.isell.ei.shop.pinduoduo.bean.PinduoduoOrderItem;
import com.isell.ei.shop.pinduoduo.bean.PinduoduoOrderResult;
import com.isell.ei.shop.pinduoduo.service.PinduoduoService;
import com.isell.service.order.dao.CoolOrderItemMapper;
import com.isell.service.order.dao.CoolOrderMapper;
import com.isell.service.order.dao.CoolOrderPushMapper;
import com.isell.service.order.util.OrderUtil;
import com.isell.service.order.vo.CoolOrder;
import com.isell.service.order.vo.CoolOrderItem;
import com.isell.service.order.vo.CoolOrderPush;
import com.isell.service.product.dao.CoolProductGgMapper;
import com.isell.service.product.dao.CoolProductGroupMapper;
import com.isell.service.product.dao.CoolProductMapper;
import com.isell.service.product.vo.CoolProduct;
import com.isell.service.product.vo.CoolProductGg;
import com.isell.service.product.vo.CoolProductGroup;

/**
 * 拼多多业务层接口
 * 
 * @author lilin
 * @version [版本号, 2015年12月21日]
 */
@Service("pinduoduoService")
public class PinduoduoServiceImpl implements PinduoduoService {
    /**
     * 订单查询mapper
     */
    @Resource
    private CoolOrderMapper coolOrderMapper;
    
    /**
     * 订单详情Mapper
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
     * 商品组Mapper
     */
    @Resource
    private CoolProductGroupMapper coolProductGroupMapper;
    
    /**
     * 订单推送表mapper
     */
    @Resource
    private CoolOrderPushMapper coolOrderPushMapper;
    
    /**
     * coolJdbcTemplate
     */
    @Resource
    private JdbcTemplate jdbcTemplate;
    
    @Override
    public String orderGoodsInfo(Map<String, String> paramMap) {
        paramMap.put("uCode", UCODE);
        paramMap.put("TimeStamp", new Date().getTime() + "");
        paramMap.put("Sign", getSign(paramMap));
        
        return HttpUtils.httpPost(SEND_URL, paramMap);
    }
    
    private String getSign(Map<String, String> paramMap) {
        StringBuilder builder = new StringBuilder();
        builder.append(SECRET);
        builder.append("mType").append(paramMap.get("mType"));
        builder.append("TimeStamp").append(paramMap.get("TimeStamp"));
        builder.append("uCode").append(paramMap.get("uCode"));
        builder.append(SECRET);
        
        return Coder.encodeMd5(builder.toString()).toUpperCase();
    }
    
    @Transactional
    @Override
    public JsonData saveOrder(Map<String, String> paramMap) {
        JsonData jsonData = new JsonData();
        List<CoolOrder> oList = coolOrderMapper.getCoolOrderByOrderOldNoList(paramMap.get("OrderNO"), "");
        if (CollectionUtils.isNotEmpty(oList)) { // 重复不导入
            jsonData.setSuccess(false);
            jsonData.setMsg("外部订单已存在");
            return jsonData;
        }
        
        paramMap.put("mType", "mGetOrder");
        String result = orderGoodsInfo(paramMap);
        PinduoduoOrderResult orderResult = JaxbUtil.converyToJavaBean((String)result, PinduoduoOrderResult.class);
        if ("0".equals(orderResult.getResult())) {
            jsonData.setSuccess(false);
            jsonData.setMsg(orderResult.getCause());
            return jsonData;
        }
        // 保存订单
        CoolOrder order = new CoolOrder();
        order.setArrears(1); // 欠费订单
        order.setoType(new Byte("1")); // pc 订单
        order.setOrderType(new Byte("1")); // 一件代发
        order.setState(CoolOrder.ORDER_STATE_1); // 待发货
        
        order.setSupplier("7b54557199554ee8bc392c514b8377ec");
        order.setmId(883);
        
        order.setOrderOldno(paramMap.get("OrderNO")); // 外部订单号
        
        if (CollectionUtils.isEmpty(orderResult.getItemList())
            || orderResult.getItemList().get(0).getGoodsID() == null) {
            jsonData.setSuccess(false);
            jsonData.setMsg("订单数据不正确");
            return jsonData;
        }
        
        String gid = orderResult.getItemList().get(0).getGoodsID().split(";")[0];
        if (gid.startsWith("group_")) {
            gid = gid.substring("group_".length());
        }
        if (!NumberUtils.isDigits(gid)) {
            jsonData.setSuccess(false);
            jsonData.setMsg("商品编码不正确");
            return jsonData;
        }
        // 生成订单号
        String orderNo = OrderUtil.generateOrderNo(Integer.parseInt(gid));
        order.setOrderNo(orderNo);
        
        // order.setLinkman(orderResult.getBuyerName()); // 买家/收货人姓名
        order.setLocationP(orderResult.getProvince()); // 省
        order.setLocationC(orderResult.getCity()); // 市
        order.setLocationA(orderResult.getTown()); // 区
        order.setAddress(orderResult.getAdr()); // 详细地址
        order.setZipcode(orderResult.getZip()); // 邮政编码
        order.setMobile(orderResult.getPhone()); // 联系电话
        order.setTotal(orderResult.getTotal()); // 货款总额
        order.setPsPrice(orderResult.getPostage()); // 运费
        order.setComments(orderResult.getCustomerRemark()); // 客户备注
        order.setIdcard(orderResult.getRemark()); // 身份证号
        order.setLinkman(orderResult.getInvoiceTitle()); // 身份证姓名
        order.setCreatetime(new Date()); // 订单创建时间
        order.setPayTime(new Date(order.getCreatetime().getTime() + 60000)); // 订单支付时间
        order.setTradeNo(orderResult.getPayID()); // 支付编号
        if (StringUtils.isNotEmpty(orderResult.getPayAccount())) { // 支付方式
            if ("ALIPAY".equalsIgnoreCase(orderResult.getPayAccount())) {
                order.setZffs(2);
            } else if ("WEIXIN".equalsIgnoreCase(orderResult.getPayAccount())) {
                order.setZffs(3);
            }
        }
        
        // 保存订单
        coolOrderMapper.saveCoolOrder(order);
        // 保存推送订单表
        CoolOrderPush coolOrderPush = new CoolOrderPush();
        coolOrderPush.setOrderNo(orderNo);
        coolOrderPushMapper.saveCoolOrderPush(coolOrderPush);
        
        // 遍历商品详情信息
        for (PinduoduoOrderItem pinItem : orderResult.getItemList()) {
            String goodsId = pinItem.getGoodsID().split(";")[0];
            // 组合商品
            if (goodsId.startsWith("group_")) {
                goodsId = goodsId.substring("group_".length());
                List<CoolProductGroup> groupList = coolProductGroupMapper.findCoolProductGroupByGroupId(goodsId);
                if (CollectionUtils.isEmpty(groupList)) {
                    throw new RuntimeException("exception.product.null");
                }
                int index = 0;
                BigDecimal sumPrice = BigDecimal.ZERO;
                for (CoolProductGroup group : groupList) {
                    index++;
                    BigDecimal price;
                    if (index == groupList.size()) {
                        price = orderResult.getTotal().subtract(sumPrice);
                    } else {
                        price = orderResult.getTotal().divide(new BigDecimal(groupList.size()), 0, RoundingMode.DOWN);
                        sumPrice = sumPrice.add(price);
                    }
                    saveOrderItem(orderNo, group.getgId(), group.getCount(), price);
                }
            } else { // 非组合商品
                saveOrderItem(orderNo, Integer.valueOf(goodsId), pinItem.getCount(), orderResult.getTotal());
            }
        }
        
        return jsonData;
    }
    
    private void saveOrderItem(String orderNo, Integer gid, Integer count, BigDecimal price) {
        CoolOrderItem item = new CoolOrderItem();
        item.setOrderNo(orderNo);
        
        CoolProductGg gg = coolProductGgMapper.getCoolProductGgById(gid);
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
        
        item.setCount(count);
        item.setPrice(price.divide(new BigDecimal(count), 2, RoundingMode.HALF_UP));
        
        // 保存订单明细
        coolOrderItemMapper.saveCoolOrderItem(item);
        // 处理商品库存和销量
        jdbcTemplate.update("update cool_product_gg set stock=stock-" + item.getCount() + ",sales=sales+"
            + item.getCount() + " where id=?",
            item.getGid());
        jdbcTemplate.update("update cool_product set sales=sales+" + item.getCount() + " where id=?", item.getgId());
    }
}
