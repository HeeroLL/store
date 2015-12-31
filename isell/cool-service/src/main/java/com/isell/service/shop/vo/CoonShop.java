package com.isell.service.shop.vo;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.isell.core.mybatis.page.PageConfig;
import com.isell.service.shop.po.CoonShopProductInfo;

/**
 * 酷店表vo
 * 
 * @author lilin
 * @version [版本号, 2015年9月25日]
 */
public class CoonShop  extends PageConfig{
	/**
	 * 展示方式 
	 */
	public static final Byte SHOW_WAY_1 = 1;
	
    /**
     * 主键id
     */
    private String id;
    
    /**
     * 用户id
     */
    private String userId;
    
    /**
     * 用户编码
     */
    private String code;
    
    /**
     * 酷店名称
     */
    private String name;
    
    /**
     * 酷店logo
     */
    private String logo;
    
    /**
     * 酷店公告
     */
    private String annInfo;
    
    /**
     * 酷店招牌
     */
    private String img;
    
    /**
     * 酷店二维码
     */
    private String qrCode;
    
    /**
     * 创建时间
     */
    private Date createtime;
    
    /**
     * 昨日收入
     */
    private BigDecimal yIncome;
    
    /**
     * 未提现金额
     */
    private BigDecimal nwdAmount;
    
    /**
     * 已提现金额
     */
    private BigDecimal wdAmount;
    
    /**
     * 累计金额
     */
    private BigDecimal allAmount;
    
    /**
     * 1.上下展示 2.左右展示
     */
    private Byte showWay;
    
    /**
     * 是否显示海报
     */
    private Boolean showBanner;
    
    /**
     * 酷店等级（外键）
     */
    private String level;
    
    /**
     * 成交订单数
     */
    private Integer turnoverOrders;
    
    /**
     * 展示模板 1:甜蜜粉 2:炫酷黑 3:热情红 4:热情红 5:简洁蓝 6:青春绿 7:梦幻紫
     */
    private Byte showModel;
    
    /**
     * 待确认佣金
     */
    private BigDecimal tbdAmount;
    
    /**
     * 推荐店铺id
     */
    private String recommendId;
    
    /**
     * 推荐店铺获得的佣金
     */
    private BigDecimal recommendAmount;
    
    /**
     * 费欠总金额
     */
    private BigDecimal arrearsTotal;
   
    /**
     * 即将可用金额
     */
    private BigDecimal jjAmount;
    
    /**
     * 是否体验店 0：否  1： 是
     */
    private String isExperience;
    
    /**
     * 体验店地址
     */
    private String address;
    
    /**
     * 是否渠道选中平台 0：否  1： 是
     */
    private String isChannelSelected;
    
    /**
     * 一级分销比例
     */
    private BigDecimal fPercentage;
    /**
     * 二机分销比例
     */
    private BigDecimal sPercentage;
    
    /**
     * 用户名（手机号）
     */
    private String username;
    
    /**
     * 密码
     */
    private String password;
    
    /**
     * 用户真实姓名
     */
    private String realname;
    
    /**
     * 酷店商品
     */
    private List<CoonShopProductInfo> productList;
   
    /**
     * 酷店vip等级
     */
    private Integer vip;
    
    
    public Integer getVip() {
        return vip;
    }

    public void setVip(Integer vip) {
        this.vip = vip;
    }

    public static Byte getShowWay1() {
        return SHOW_WAY_1;
    }

    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public String getUserId() {
        return userId;
    }
    
    public void setUserId(String userId) {
        this.userId = userId;
    }
    
    public String getCode() {
        return code;
    }
    
    public void setCode(String code) {
        this.code = code;
    }
    
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
    
    public String getAnnInfo() {
        return annInfo;
    }
    
    public void setAnnInfo(String annInfo) {
        this.annInfo = annInfo;
    }
    
    public String getImg() {
        return img;
    }
    
    public void setImg(String img) {
        this.img = img;
    }
    
    public String getQrCode() {
        return qrCode;
    }
    
    public void setQrCode(String qrCode) {
        this.qrCode = qrCode;
    }
    
    public Date getCreatetime() {
        return createtime;
    }
    
    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }
    
    public BigDecimal getyIncome() {
        return yIncome;
    }
    
    public void setyIncome(BigDecimal yIncome) {
        this.yIncome = yIncome;
    }
    
    public BigDecimal getNwdAmount() {
        return nwdAmount;
    }
    
    public void setNwdAmount(BigDecimal nwdAmount) {
        this.nwdAmount = nwdAmount;
    }
    
    public BigDecimal getWdAmount() {
        return wdAmount;
    }
    
    public void setWdAmount(BigDecimal wdAmount) {
        this.wdAmount = wdAmount;
    }
    
    public BigDecimal getAllAmount() {
        return allAmount;
    }
    
    public void setAllAmount(BigDecimal allAmount) {
        this.allAmount = allAmount;
    }
    
    public Byte getShowWay() {
        return showWay;
    }
    
    public void setShowWay(Byte showWay) {
        this.showWay = showWay;
    }
    
    public Boolean getShowBanner() {
        return showBanner;
    }
    
    public void setShowBanner(Boolean showBanner) {
        this.showBanner = showBanner;
    }
    
    public String getLevel() {
        return level;
    }
    
    public void setLevel(String level) {
        this.level = level;
    }
    
    public Integer getTurnoverOrders() {
        return turnoverOrders;
    }
    
    public void setTurnoverOrders(Integer turnoverOrders) {
        this.turnoverOrders = turnoverOrders;
    }
    
    public Byte getShowModel() {
        return showModel;
    }
    
    public void setShowModel(Byte showModel) {
        this.showModel = showModel;
    }
    
    public BigDecimal getTbdAmount() {
        return tbdAmount;
    }
    
    public void setTbdAmount(BigDecimal tbdAmount) {
        this.tbdAmount = tbdAmount;
    }

	public String getRecommendId() {
		return recommendId;
	}

	public void setRecommendId(String recommendId) {
		this.recommendId = recommendId;
	}

	public BigDecimal getRecommendAmount() {
		return recommendAmount;
	}

	public void setRecommendAmount(BigDecimal recommendAmount) {
		this.recommendAmount = recommendAmount;
	}

	public BigDecimal getArrearsTotal() {
		return arrearsTotal;
	}

	public void setArrearsTotal(BigDecimal arrearsTotal) {
		this.arrearsTotal = arrearsTotal;
	}

	public BigDecimal getJjAmount() {
		return jjAmount;
	}

	public void setJjAmount(BigDecimal jjAmount) {
		this.jjAmount = jjAmount;
	}

	public String getIsExperience() {
		return isExperience;
	}

	public void setIsExperience(String isExperience) {
		this.isExperience = isExperience;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public BigDecimal getfPercentage() {
		return fPercentage;
	}

	public void setfPercentage(BigDecimal fPercentage) {
		this.fPercentage = fPercentage;
	}

	public BigDecimal getsPercentage() {
		return sPercentage;
	}

	public void setsPercentage(BigDecimal sPercentage) {
		this.sPercentage = sPercentage;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	public List<CoonShopProductInfo> getProductList() {
		return productList;
	}

	public void setProductList(List<CoonShopProductInfo> productList) {
		this.productList = productList;
	}

	public String getIsChannelSelected() {
		return isChannelSelected;
	}

	public void setIsChannelSelected(String isChannelSelected) {
		this.isChannelSelected = isChannelSelected;
	}	 
}