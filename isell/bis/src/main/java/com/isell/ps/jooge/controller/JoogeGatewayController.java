package com.isell.ps.jooge.controller;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

import javax.annotation.Resource;

import org.exolab.castor.types.DateTime;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.isell.core.mybatis.page.PageInfo;
import com.isell.core.util.Coder;
import com.isell.core.util.DateUtil;
import com.isell.core.util.JsonUtil;
import com.isell.ps.jooge.bean.JoogeParam;
import com.isell.ps.jooge.bean.Merch;
import com.isell.ps.jooge.bean.OrderInfo;
import com.isell.ps.jooge.bean.OrderList;
import com.isell.ps.jooge.bean.ProductList;
import com.isell.service.order.po.CoolOrderSelect;
import com.isell.service.order.service.OrderService;
import com.isell.service.order.vo.CoolOrder;
import com.isell.service.product.po.CoolProductSelect;
import com.isell.service.product.service.ProductService;
import com.isell.service.product.vo.CoolProduct;

/**
 * 对珊瑚云系统统一网关入口
 * 
 * @author lilin
 * @version [版本号, 2015年9月24日]
 */
@Controller
@RequestMapping("jooge")
public class JoogeGatewayController {
    /**
     * 艾售KEY
     */
    private static final String APPKEY = "shanhuyun2isell";
    
    /**
     * 加密密钥
     */
    private static final String APPSECRET = "3e38778c39bb4558a3f38515d07e80fa";
    
    /**
     * 订单服务层接口
     */
    @Resource
    private OrderService orderService;
    
    /**
     * 商品服务层接口
     */
    @Resource
    private ProductService productService;
    
    /**
     * 珊瑚云系统统一网关入口负责校验请求合法性，和转发合法请求到指定控制器
     * 
     * @param param 参数
     * @return 处理结果
     */
    @RequestMapping("gateway")
    public String gateway(@RequestBody JoogeParam param) {
        if (param.getMethod() == null) {
            throw new RuntimeException("exception.access.service.null");
        }
        if (!APPKEY.equals(param.getAppkey())) {
            throw new RuntimeException("exception.access.system-null");
        }
        // 校验请求合法性
        StringBuilder paramStr = new StringBuilder();
        paramStr.append(APPSECRET); // 开头拼上密钥
        paramStr.append("appkey").append(param.getAppkey());
        paramStr.append("method").append(param.getMethod());
        paramStr.append("param").append(param.getParam());
        paramStr.append("timestamp").append(param.getTimestamp());
        paramStr.append("v").append(param.getV());
        paramStr.append(APPSECRET); // 结尾拼上密钥
        String sign = Coder.encodeMd5(paramStr.toString()).toLowerCase();
        if (!sign.equals(param.getSign())) {
            throw new RuntimeException("exception.access.authcode-wrong");
        }
        
        return "forward:jooge/" + param.getMethod().replace(".", "/");// 默认为forward模式
    }
    
    /**
     * 请求订单列表
     * 
     * @param param 参数
     * @return 订单列表信息
     */
    @SuppressWarnings("unchecked")
    @RequestMapping("order/detail/get")
    @ResponseBody
    public OrderList getOrderList(@RequestBody JoogeParam param) {
        Map<String, Object> paramMap = JsonUtil.readValue(param.getParam(), Map.class);
        CoolOrderSelect paramOrder = new CoolOrderSelect();
        paramOrder.setRows((Integer)paramMap.get("PageSize")); // 当前页
        paramOrder.setPage((Integer)paramMap.get("PageNo")); // 当前页
        paramOrder.setStartUpdatetime((Date)paramMap.get("StartModified")); // 起始的修改时间
                                                                            // DateUtil.parseDate(paramMap.get("StartModified"),
                                                                            // DateUtil.yyyy_MM_dd_HH_mm_ss)
        paramOrder.setEndUpdatetime((Date)paramMap.get("EndModified")); // 起始的修改时间
                                                                        // DateUtil.parseDate(paramMap.get("EndModified"),
                                                                        // DateUtil.yyyy_MM_dd_HH_mm_ss)
        paramOrder.setState(CoolOrder.ORDER_STATE_2); // 已发货
        // paramOrder.setPsfs(""); // 配送方式
        
        PageInfo<CoolOrder> page = orderService.getCoolOrderListPage(paramOrder);
        OrderList orderList = new OrderList();
        orderList.setCode("0");
        orderList.setDesc("");
        orderList.setTotalResult(page.getTotal());
        
        orderList.setOrders(new ArrayList<OrderInfo>());
        if (page.getRows() != null) {
            for (CoolOrder order : page.getRows()) {
                OrderInfo orderInfo = new OrderInfo();
                orderInfo.setOrderId(order.getOrderNo());
                orderInfo.setModified(DateUtil.dateToStr(DateUtil.yyyy_MM_dd_HH_mm_ss, order.getUpdatetime()));
                
                orderList.getOrders().add(orderInfo);
            }
        }
        
        return orderList;
    }
    
    @SuppressWarnings("unchecked")
    @RequestMapping("merch/list/get")
    @ResponseBody
    public ProductList getProductList(@RequestBody JoogeParam param)
        throws ParseException {
        Map<String, Object> paramMap = JsonUtil.readValue(param.getParam(), Map.class);
        CoolProductSelect coolProductSelect = new CoolProductSelect();
        coolProductSelect.setRows((Integer)paramMap.get("PageSize"));
        coolProductSelect.setPage((Integer)paramMap.get("PageNo"));
        coolProductSelect.setStartUpdatetime((Date)paramMap.get("StartModified"));
        coolProductSelect.setEndUpdatetime((Date)paramMap.get("EndModified"));
        PageInfo<CoolProduct> page = productService.getCoolProductPageList(coolProductSelect);
        ProductList productList = new ProductList();
        productList.setCode("0");
        productList.setDesc("");
        productList.setTotalResult(page.getTotal());
        productList.setItems(new ArrayList<Merch>());
        if (page.getRows() != null) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss"); // Date类型转DateTime类型
            for (CoolProduct coolProduct : page.getRows()) {
                Merch merch = new Merch();
                merch.setId(String.valueOf(coolProduct.getId()));
                merch.setName(coolProduct.getNameEn());
                merch.setPrice(coolProduct.getPrice());
                // 商品计量单位，如个、件
                //merch.setUnit(unit);
                
                
                merch.setModified(DateTime.parse(simpleDateFormat.format(coolProduct.getUpdatetime()).toString()));
                productList.getItems().add(merch);
            }
        }
        return productList;
        
    }
}
