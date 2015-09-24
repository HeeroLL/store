package com.isell.ps.wpwl.vo;

import java.math.BigDecimal;
import java.util.List;

/**
 * 返回给沃朴物联科技有限公司的订单详情信息
 * 
 * @author lilin
 * @version [版本号, 2015年9月24日]
 */
public class WpwlOrderItem {
    /**
     * 商品名称
     */
    private String name;
    
    /**
     * 商品图片url
     */
    private String logo;
    
    /**
     * 购买数量
     */
    private Integer count;
    
    /**
     * 购买价格
     */
    private BigDecimal price;
    
    /**
     * 商品详情页url
     */
    private String url;
    
    /**
     * 自定义数据
     */
    private List<String> attribute;
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getLogo() {
        return logo;
    }
    
    public void setLogo(String logo) {
        this.logo = logo;
    }
    
    
    public BigDecimal getPrice() {
        return price;
    }
    
    public void setPrice(BigDecimal price) {
        this.price = price;
    }
    
    public String getUrl() {
        return url;
    }
    
    public void setUrl(String url) {
        this.url = url;
    }

    public List<String> getAttribute() {
        return attribute;
    }

    public void setAttribute(List<String> attribute) {
        this.attribute = attribute;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
