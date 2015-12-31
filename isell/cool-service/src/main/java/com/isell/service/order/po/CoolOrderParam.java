package com.isell.service.order.po;

import java.math.BigDecimal;
import java.util.List;

import com.isell.core.mybatis.page.PageConfig;
import com.isell.service.order.vo.CoolOrderItem;
import com.isell.service.product.vo.CoolProductReview;

/**
 * 
 * 订单参数类
 * 
 * @author wangpeng
 * @version [版本号, 2015-10-05]
 */
public class CoolOrderParam  extends PageConfig{
	
	private String id;
	
	/**
	 * 收货地省
	 */
	private String locationP;
	
	/**
	 * 收货地市
	 */
	private String locationC;
	
	/**
	 * 收货地区
	 */
	private String locationA;
	
	/**
	 * 收货地详细
	 */
	private String address;
	
	/**
	 * 联系人
	 */
	private String linkman;
	
	/**
	 * 电话号码
	 */
	private String tel;
	
	private String mobile;
	
	private String zipcode;
	
	private String idcard;
	
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
	 * 店铺id
	 */
	private String supplier;
	
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
     * 是否批量（0：否  1：是）
     */
    private String isBatch;
    
    /**
     * 分销等级 2：二级 3：三级
     */
    private Integer level;
    
    /**
     * 合伙人店铺id
     */
    private String partnerId;
	
	/**
	 * 订单详情
	 */
	private List<CoolOrderItem> orderItems;
	
	/**
	 * 评价列表
	 */
	private List<CoolProductReview> reviewList;

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

	public String getLocationC() {
		return locationC;
	}

	public void setLocationC(String locationC) {
		this.locationC = locationC;
	}

	public String getLocationA() {
		return locationA;
	}

	public void setLocationA(String locationA) {
		this.locationA = locationA;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getSupplier() {
		return supplier;
	}

	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}

	public String getIsBatch() {
		return isBatch;
	}

	public void setIsBatch(String isBatch) {
		this.isBatch = isBatch;
	}

	public String getLinkman() {
		return linkman;
	}

	public void setLinkman(String linkman) {
		this.linkman = linkman;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public String getIdcard() {
		return idcard;
	}

	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public String getPartnerId() {
		return partnerId;
	}

	public void setPartnerId(String partnerId) {
		this.partnerId = partnerId;
	}

	public List<CoolProductReview> getReviewList() {
		return reviewList;
	}

	public void setReviewList(List<CoolProductReview> reviewList) {
		this.reviewList = reviewList;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
