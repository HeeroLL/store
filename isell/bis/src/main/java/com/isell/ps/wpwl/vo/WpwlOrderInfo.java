package com.isell.ps.wpwl.vo;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 返回给沃朴物联科技有限公司的订单信息
 * 
 * @author lilin
 * @version [版本号, 2015年9月24日]
 */
public class WpwlOrderInfo {
    /**
     * 订单id
     */
    private String id;
    
    /**
     * 订单编号
     */
    private String orderNo;
    
    /**
     * 收货地址
     */
    private String address;
    
    /**
     * 下单时间
     */
    private Date createtime;
    
    /**
     * 联系人
     */
    private String customerName;
    
    /**
     * 订单总价
     */
    private BigDecimal total;
    
    /**
     * 订单详情
     */
    private List<WpwlOrderItem> items;
    
    private Boolean receiptStatus;//签收状态
    
    private String receiptTime;//未签收
    
    
    public Boolean getReceiptStatus() {
		return receiptStatus;
	}

	public void setReceiptStatus(Boolean receiptStatus) {
		this.receiptStatus = receiptStatus;
	}

	 

	public String getReceiptTime() {
		if(receiptTime==null)
		{
			receiptTime="";
		}
		return receiptTime;
	}

	public void setReceiptTime(String receiptTime) {
		this.receiptTime = receiptTime;
	}

	public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public String getOrderNo() {
        return orderNo;
    }
    
    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }
    
    public String getAddress() {
        return address;
    }
    
    public void setAddress(String address) {
        this.address = address;
    }
    
    public Date getCreatetime() {
        return createtime;
    }
    
    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }
    
    public String getCustomerName() {
        return customerName;
    }
    
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
    
    public BigDecimal getTotal() {
        return total;
    }
    
    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public List<WpwlOrderItem> getItems() {
        return items;
    }

    public void setItems(List<WpwlOrderItem> items) {
        this.items = items;
    }
    
}
