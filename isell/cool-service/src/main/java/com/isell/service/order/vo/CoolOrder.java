package com.isell.service.order.vo;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 订单表VO
 * 
 * @author lilin
 * @version [版本号, 2015年9月24日]
 */
public class CoolOrder {
    
    /**
     * 待支付
     */
    public static final byte ORDER_STATE_0 = 0;
    
    /**
     * 待发货
     */
    public static final byte ORDER_STATE_1 = 1;
    
    /**
     * 待收货
     */
    public static final byte ORDER_STATE_2 = 2;
    
    /**
     * 待评价
     */
    public static final byte ORDER_STATE_3 = 3;
    
    /**
     * 已完成
     */
    public static final byte ORDER_STATE_4 = 4;
    
    /**
     * 已退款
     */
    public static final byte ORDER_STATE_5 = 5;
    
    /**
     * 已通知海关发货
     */
    public static final byte ORDER_STATE_11 = 11;
    
    /**
     * 已取消
     */
    public static final byte ORDER_STATE_99 = 99;
    
    /**
     * 自提
     */
    public static final byte FHFS_0 = 0;
    
    /**
     * 内贸-圆通
     */
    public static final byte FHFS_1 = 1;
    /**
     * 汇通
     */
    public static final byte FHFS_2 = 2;
    /**
     * 顺丰
     */
    public static final byte FHFS_3 = 3;
    /**
     * 大田物流
     */
    public static final byte FHFS_4 = 4;
    /**
     * 宁波艾购保税仓
     */
    public static final byte FHFS_10 = 10;
    
    /**
     * 宁波优贝保税仓
     */
    public static final byte FHFS_11 = 11;
    
    /**
     * 郑州保税仓
     */
    public static final byte FHFS_20 = 20;
    /**
     * 杭州保税仓
     */
    public static final byte FHFS_30 = 30; 
    
    /**
     * 支付方式：2-支付宝支付
     */
    public static final int ZFFS_2 = 2;
    
    /**
     * 支付方式：3-微信支付
     */
    public static final int ZFFS_3 = 3;
    
    /**
     * 支付方式：4-易极付支付
     */
    public static final int ZFFS_4 = 4;
    
    /**
     * 支付方式：5-易汇金支付
     */
    public static final int ZFFS_5 = 5;
    
    /**
     * 支付方式：6-浙江银商支付
     */
    public static final int ZFFS_6 = 6;
    
    /**
     * 支付方式：7-富友支付
     */
    public static final int ZFFS_7 = 7;
    
    /**
     * 主键id
     */
    private Integer id;
    
    /**
     * 省
     */
    private String locationP;
    
    /**
     * 市
     */
    private String locationC;
    
    /**
     * 区
     */
    private String locationA;
    
    /**
     * 详细地址
     */
    private String address;
    
    /**
     * 联系人
     */
    private String linkman;
    
    /**
     * 手机号码
     */
    private String mobile;
    
    /**
     * 电话号码
     */
    private String tel;
    
    /**
     * 配送方式
     */
    private String psfs;
    
    /**
     * 邮费
     */
    private BigDecimal psPrice;
    
    /**
     * 支付方式
     */
    private Integer zffs;
    
    /**
     * 备注
     */
    private String comments;
    
    /**
     * 订单总金额
     */
    private BigDecimal total;
    
    /**
     * 订单状态：0：待支付/1：待发货/2：待收货/3：待评价/4：已完成/5：已退款/11：已通知海关发货/99：已取消
     */
    private Byte state;
    
    /**
     * 创建时间
     */
    private Date createtime;
    
    /**
     * 订单编号
     */
    private String orderNo;
    
    /**
     * 订单客户id
     */
    private Integer mId;
    
    /**
     * 发货时间
     */
    private Date updatetime;
    
    /**
     * 描述
     */
    private String remark;
    
    /**
     * 打分
     */
    private Integer score;
    
    /**
     * 订单类型：0：手机订单/1：PC订单
     */
    private Byte oType;
    
    /**
     * 无意义
     */
    private Integer bId;
    
    /**
     * 理由
     */
    private String reason;
    
    /**
     * 快递公司运单号
     */
    private String psCode;
    
    /**
     * 连连支付的支付单号
     */
    private String oidBillno;
    
    /**
     * 支付时间
     */
    private Date payTime;
    
    /**
     * 支付宝/微信支付单号
     */
    private String tradeNo;
    
    /**
     * 
     */
    private Integer distributors;
    
    /**
     * 酷店id
     */
    private String supplier;
    /** 酷店名称 */
    private String sName;
    
    /**
     * 
     */
    private Boolean supplierSettle;
    
    /**
     * 邮政编码
     */
    private String zipcode;
    
    /**
     * 酷店分成金额
     */
    private BigDecimal supplierProfit;
    
    /**
     * 签收时间
     */
    private Date finishTime;
    
    /**
     * 支付状态：0：未报关/1：已报关
     */
    private Byte payState;
    
    /**
     * 订单类型：0：普通订单/1：一件代发/2:采购订单
     */
    private Byte orderType;
    
    /**
     * 逻辑删除标志（酷店是否删除）
     */
    private Byte isDel;
    
    /**
     * 分享人
     */
    private String shareUser;
    
    /**
     * 是否已二次分佣
     */
    private Boolean shareAdded;
    
    /**
     * 快递公司返回的大头笔
     */
    private String bigpen;
    
    /**
     * 运单号条形码
     */
    private String barcode;
    
    /**
     * 身份证号
     */
    private String idcard;
    
    /**
     * 逻辑删除标志（会员是否删除）
     */
    private Byte isDelM;
    
    /**
     * 订单来源
     */
    private String source;
    
    /**
     * 是否批量删除 0：否 /1：是
     */
    private Byte isBatch;
    
    /**
     * 0.正常订单 1.欠费订单 2.欠费后已缴费的订单
     */
    private Integer arrears;
    
    /**
     * 0.正常订单 1.退款中2.退款完成
     */
    private Integer refundState;
    
    /**
     * 退款金额
     */
    private BigDecimal refundAmount;
    
    /**
     * 行邮税
     */
    private BigDecimal taxPrice;
    
    /**
     * 外部订单号
     */
    private String orderOldno;
    
    /**
     * 发货方式
     */
    private Byte fhfs;
    
    /**
     * 优惠金额
     */
    private BigDecimal discountPrice;
    
    private Boolean isout;
    
    private String outdays;
    
    private Integer count;
    
    /**
     * 开始时间
     */
    private Date startTime;
    
    /**
     * 结束时间
     */
    private Date endTime;
    
    /**
     * 商铺名称
     */
    private String supName;
    /**
     * 回调路径
     */
    private String returnUrl;
    
    /**
     * 订单详情列表
     */
    private List<CoolOrderItem> itemList;
    
    private List<HzCoolOrderItem> hzItem;
    
    private List<CoolOrderItem> items;
    public List<CoolOrderItem> getItems() {
		return items;
	}

	public void setItems(List<CoolOrderItem> items) {
		this.items = items;
	}

	public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    public String getLocationP() {
        return locationP;
    }
    
    public void setLocationP(String locationP) {
        this.locationP = locationP;
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
    
    public String getLinkman() {
        return linkman;
    }
    
    public void setLinkman(String linkman) {
        this.linkman = linkman;
    }
    
    public String getMobile() {
        return mobile;
    }
    
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
    
    public String getTel() {
        return tel;
    }
    
    public void setTel(String tel) {
        this.tel = tel;
    }
    
    public String getPsfs() {
        return psfs;
    }
    
    public void setPsfs(String psfs) {
        this.psfs = psfs;
    }
    
    public BigDecimal getPsPrice() {
        return psPrice;
    }
    
    public void setPsPrice(BigDecimal psPrice) {
        this.psPrice = psPrice;
    }
    
    public Integer getZffs() {
        return zffs;
    }
    
    public void setZffs(Integer zffs) {
        this.zffs = zffs;
    }
    
    public String getComments() {
        return comments;
    }
    
    public void setComments(String comments) {
        this.comments = comments;
    }
    
    public BigDecimal getTotal() {
        return total;
    }
    
    public void setTotal(BigDecimal total) {
        this.total = total;
    }
    

	public Byte getState() {
        return state;
    }
    
    public void setState(Byte state) {
        this.state = state;
    }
    
    public Date getCreatetime() {
        return createtime;
    }
    
    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }
    
    public String getOrderNo() {
        return orderNo;
    }
    
    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }
    
    public Integer getmId() {
        return mId;
    }
    
    public void setmId(Integer mId) {
        this.mId = mId;
    }
    
    public Date getUpdatetime() {
        return updatetime;
    }
    
    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }
    
    public String getRemark() {
        return remark;
    }
    
    public void setRemark(String remark) {
        this.remark = remark;
    }
    
    public Integer getScore() {
        return score;
    }
    
    public void setScore(Integer score) {
        this.score = score;
    }
    
    public Byte getoType() {
        return oType;
    }
    
    public void setoType(Byte oType) {
        this.oType = oType;
    }
    
    public Integer getbId() {
        return bId;
    }
    
    public void setbId(Integer bId) {
        this.bId = bId;
    }
    
    public String getReason() {
        return reason;
    }
    
    public void setReason(String reason) {
        this.reason = reason;
    }
    
    public String getPsCode() {
        return psCode;
    }
    
    public void setPsCode(String psCode) {
        this.psCode = psCode;
    }
    
    public String getOidBillno() {
        return oidBillno;
    }
    
    public void setOidBillno(String oidBillno) {
        this.oidBillno = oidBillno;
    }
    
    public Date getPayTime() {
        return payTime;
    }
    
    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }
    
    public String getTradeNo() {
        return tradeNo;
    }
    
    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo;
    }
    
    public Integer getDistributors() {
        return distributors;
    }
    
    public void setDistributors(Integer distributors) {
        this.distributors = distributors;
    }
    
    public String getSupplier() {
        return supplier;
    }
    
    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }
    
    public Boolean getSupplierSettle() {
        return supplierSettle;
    }
    
    public void setSupplierSettle(Boolean supplierSettle) {
        this.supplierSettle = supplierSettle;
    }
    
    public String getZipcode() {
        return zipcode;
    }
    
    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }
    
    public BigDecimal getSupplierProfit() {
        return supplierProfit;
    }
    
    public void setSupplierProfit(BigDecimal supplierProfit) {
        this.supplierProfit = supplierProfit;
    }
    
    public Date getFinishTime() {
        return finishTime;
    }
    
    public void setFinishTime(Date finishTime) {
        this.finishTime = finishTime;
    }
    
    public Byte getPayState() {
        return payState;
    }
    
    public void setPayState(Byte payState) {
        this.payState = payState;
    }
    
    public Byte getOrderType() {
        return orderType;
    }
    
    public void setOrderType(Byte orderType) {
        this.orderType = orderType;
    }
    
    public Byte getIsDel() {
        return isDel;
    }
    
    public void setIsDel(Byte isDel) {
        this.isDel = isDel;
    }
    
    public String getShareUser() {
        return shareUser;
    }
    
    public void setShareUser(String shareUser) {
        this.shareUser = shareUser;
    }
    
    public Boolean getShareAdded() {
        return shareAdded;
    }
    
    public void setShareAdded(Boolean shareAdded) {
        this.shareAdded = shareAdded;
    }
    
    public String getBigpen() {
        return bigpen;
    }
    
    public void setBigpen(String bigpen) {
        this.bigpen = bigpen;
    }
    
    public String getBarcode() {
        return barcode;
    }
    
    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }
    
    public String getIdcard() {
        return idcard;
    }
    
    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }
    
    public Byte getIsDelM() {
        return isDelM;
    }
    
    public void setIsDelM(Byte isDelM) {
        this.isDelM = isDelM;
    }
    
    public Byte getFhfs() {
        return fhfs;
    }
    
    public void setFhfs(Byte fhfs) {
        this.fhfs = fhfs;
    }
    
    public Boolean getIsout() {
        return isout;
    }
    
    public void setIsout(Boolean isout) {
        this.isout = isout;
    }
    
    public String getOutdays() {
        return outdays;
    }
    
    public void setOutdays(String outdays) {
        this.outdays = outdays;
    }
    
    public Integer getCount() {
        return count;
    }
    
    public void setCount(Integer count) {
        this.count = count;
    }
    
    public Date getStartTime() {
        return startTime;
    }
    
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }
    
    public Date getEndTime() {
        return endTime;
    }
    
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
    
    public String getSupName() {
        return supName;
    }
    
    public void setSupName(String supName) {
        this.supName = supName;
    }
    
    public List<CoolOrderItem> getItemList() {
        return itemList;
    }
    
    public void setItemList(List<CoolOrderItem> itemList) {
        this.itemList = itemList;
    }
    
    public String getSource() {
        return source;
    }
    
    public void setSource(String source) {
        this.source = source;
    }
    
    public Byte getIsBatch() {
        return isBatch;
    }
    
    public void setIsBatch(Byte isBatch) {
        this.isBatch = isBatch;
    }
    
    public BigDecimal getTaxPrice() {
        return taxPrice;
    }
    
    public void setTaxPrice(BigDecimal taxPrice) {
        this.taxPrice = taxPrice;
    }
    
    public Integer getArrears() {
        return arrears;
    }
    
    public void setArrears(Integer arrears) {
        this.arrears = arrears;
    }
    
    public Integer getRefundState() {
        return refundState;
    }
    
    public void setRefundState(Integer refundState) {
        this.refundState = refundState;
    }
    
    public BigDecimal getRefundAmount() {
        return refundAmount;
    }
    
    public void setRefundAmount(BigDecimal refundAmount) {
        this.refundAmount = refundAmount;
    }
    
    public String getOrderOldno() {
        return orderOldno;
    }
    
    public void setOrderOldno(String orderOldno) {
        this.orderOldno = orderOldno;
    }
    
    public BigDecimal getDiscountPrice() {
        return discountPrice;
    }
    
    public void setDiscountPrice(BigDecimal discountPrice) {
        this.discountPrice = discountPrice;
    }

	public String getReturnUrl() {
		return returnUrl;
	}

	public void setReturnUrl(String returnUrl) {
		this.returnUrl = returnUrl;
	}

	public String getsName() {
		return sName;
	}

	public void setsName(String sName) {
		this.sName = sName;
	}

	public List<HzCoolOrderItem> getHzItem() {
		return hzItem;
	}

	public void setHzItem(List<HzCoolOrderItem> hzItem) {
		this.hzItem = hzItem;
	}
    
}