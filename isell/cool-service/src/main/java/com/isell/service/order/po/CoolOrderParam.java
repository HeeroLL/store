package com.isell.service.order.po;

import java.math.BigDecimal;
import java.util.List;

import com.isell.service.order.vo.CoolOrderItem;

/**
 * 
 * 订单参数类
 * 
 * @author wangpegn
 * @version [版本号, 2015-10-05]
 */
public class CoolOrderParam {
	
	/**
	 * 收货地省
	 */
	private String locationP;
	
	/**
	 * 登录会员id
	 */
	private Integer userId;
	
	/**
	 * 会员主键
	 */
	private String mId;
	
	/**
	 * 收货地址信息主键
	 */
	private Integer recId; 
	
	/**
	 * 分享人
	 */
	private String shareUser;
	
	/**
     * 配送方式
     */
    private String psfs;
    
    /**
     * 支付方式
     */
    private Integer zffs;
    
    /**
     * 酷店主键
     */
    private Integer storeId;
    
    /**
     * 订单总金额
     */
    private BigDecimal total;
    
    /**
     * 邮费
     */
    private BigDecimal psPrice;
    
    /**
     * 是否包邮
     */
    private Boolean freePost;
    
    /**
     * 是否免税
     */
    private Boolean freeTax;
    
    /**
     * 订单类型：0：普通订单/1：一件代发
     */
    private Byte orderType;
    
    /**
     * 0：手机订单，1：PC订单
     */
    private Byte oType;
	
	/**
	 * 订单详情
	 */
	private List<CoolOrderItem> orderItems;

	public String getLocationP() {
		return locationP;
	}

	public void setLocationP(String locationP) {
		this.locationP = locationP;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getmId() {
		return mId;
	}

	public void setmId(String mId) {
		this.mId = mId;
	}

	public Integer getRecId() {
		return recId;
	}

	public void setRecId(Integer recId) {
		this.recId = recId;
	}

	public String getShareUser() {
		return shareUser;
	}

	public void setShareUser(String shareUser) {
		this.shareUser = shareUser;
	}

	public String getPsfs() {
		return psfs;
	}

	public void setPsfs(String psfs) {
		this.psfs = psfs;
	}

	public Integer getZffs() {
		return zffs;
	}

	public void setZffs(Integer zffs) {
		this.zffs = zffs;
	}

	public Integer getStoreId() {
		return storeId;
	}

	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public BigDecimal getPsPrice() {
		return psPrice;
	}

	public void setPsPrice(BigDecimal psPrice) {
		this.psPrice = psPrice;
	}

	public List<CoolOrderItem> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<CoolOrderItem> orderItems) {
		this.orderItems = orderItems;
	}

    public Boolean getFreePost() {
        return freePost;
    }

    public void setFreePost(Boolean freePost) {
        this.freePost = freePost;
    }

    public Boolean getFreeTax() {
        return freeTax;
    }

    public void setFreeTax(Boolean freeTax) {
        this.freeTax = freeTax;
    }

	public Byte getOrderType() {
		return orderType;
	}

	public void setOrderType(Byte orderType) {
		this.orderType = orderType;
	}

    public Byte getoType() {
        return oType;
    }

    public void setoType(Byte oType) {
        this.oType = oType;
    }

}
