package com.isell.service.order.po;

import java.math.BigDecimal;
import java.util.Date;

import com.isell.core.mybatis.page.PageConfig;

/**
 * 列表查询参数类
 * 
 * @author wangpeng
 * @version [版本号, 2015-10-08]
 */
public class CoolOrderSelect extends PageConfig {
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
     * 订单状态：0：待支付/1：待发货/2：待收货/3：待评价/4：已完成/5：已退款/99：已取消
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
     * 订单客户id(会员id)
     */
    private Integer mId;
    
    /**
     * 订单客户id(用户id)
     */
    private Integer userId;
    
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
    
    /** 购买商品名称 */
    private String pName; 
    
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
     * 订单类型：0：普通订单/1：一件代发
     */
    private Byte orderType;
    
    /**
     * 逻辑删除标志（酷店是否删除）
     */
    private int isDel;
    
    /**
     * 分享人
     */
    private String shareUser;
    
    /**
     * 是否已二次分佣
     */
    private int shareAdded;
    
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
    private int isDelM;
    
    /**
     * 开始时间
     */
    private Date startTime;
    
    /**
     * 结束时间
     */
    private Date endTime;
    
    /**
     * 店铺名称
     */
    private String supName;
    
    /**
     * 发货方式
     */
    private Byte fhfs;
    
    /**
     * 是否查询详情信息
     */
    private boolean searchDetail;
    
    /**
     * 开始发货时间
     */
    private Date startUpdatetime;
    
    /**
     * 结束发货时间
     */
    private Date endUpdatetime;
    
    /**
     * app查询参数
     */
    private String appSelect;
    
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
    
    public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
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
    
    public int getIsDel() {
		return isDel;
	}

	public void setIsDel(int isDel) {
		this.isDel = isDel;
	}

	public String getShareUser() {
        return shareUser;
    }
    
    public void setShareUser(String shareUser) {
        this.shareUser = shareUser;
    }
    
    public int getShareAdded() {
        return shareAdded;
    }
    
    public void setShareAdded(int shareAdded) {
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

	public int getIsDelM() {
		return isDelM;
	}

	public void setIsDelM(int isDelM) {
		this.isDelM = isDelM;
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

    public boolean isSearchDetail() {
        return searchDetail;
    }

    public void setSearchDetail(boolean searchDetail) {
        this.searchDetail = searchDetail;
    }

    public Date getStartUpdatetime() {
        return startUpdatetime;
    }

    public void setStartUpdatetime(Date startUpdatetime) {
        this.startUpdatetime = startUpdatetime;
    }

    public Date getEndUpdatetime() {
        return endUpdatetime;
    }

    public void setEndUpdatetime(Date endUpdatetime) {
        this.endUpdatetime = endUpdatetime;
    }

    public Byte getFhfs() {
        return fhfs;
    }

    public void setFhfs(Byte fhfs) {
        this.fhfs = fhfs;
    }

	public String getsName() {
		return sName;
	}

	public void setsName(String sName) {
		this.sName = sName;
	}

	public String getpName() {
		return pName;
	}

	public void setpName(String pName) {
		this.pName = pName;
	}

	public String getAppSelect() {
		return appSelect;
	}

	public void setAppSelect(String appSelect) {
		this.appSelect = appSelect;
	}
}
