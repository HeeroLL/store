package com.isell.service.order.vo;

import java.math.BigDecimal;

/**
 * 订单详情表
 * 
 * @author lilin
 * @version [版本号, 2015年9月24日]
 */
public class HzCoolOrderItem {
    /**
     * 主键id
     */
    private Integer id;
    
    /**
     * 商品id
     */
    private Integer gId;
    
    /**
     * 商品名
     */
    private String name;
    
    /**
     * 商品图片
     */
    private String logo;
    
    /**
     * 商品规格描述
     */
    private String gg;
    
    /**
     * 订购数量
     */
    private Integer count;
    
    /**
     * 订购单价
     */
    private BigDecimal price;
    
    /**
     * 订单编号
     */
    private String orderNo;
    
    /**
     * 
     */
    private Integer bId;
    
    /**
     * 是否已评价
     */
    private Boolean review;
    
    /**
     * 
     */
    private Integer lId;
    
    /**
     * 
     */
    private BigDecimal lPrice;
    
    /**
     * 
     */
    private String lName;
    
    /**
     * 
     */
    private String lCode;
    
    /**
     * 备注
     */
    private String comments;
    
    /**
     * 商品规格id
     */
    private Integer gid;
    
    /**
     * 商品编码
     */
    private String code;
    
    /**
     * 分佣比例(%)
     */
    private BigDecimal brokerage;
    
    /**
     * 佣金
     */
    private BigDecimal profit;
    
    /**
     * 批发价格
     */
    private BigDecimal pfPrice;
    
    /**
     * 币别
     */
    private String currency;
    /**
     * 供应商编码（电商平台下的商家备案编号）
     */
    private String supplier;
    /**
     * 行邮税号
     */
    private String codeTs;
    /**
     * 起运国三字代码
     */
    private String tradeCountry;
    /**
     * 规格型号
     */
    private String goodsModal;
    /**
     * 第一单位和申报计量单位(三字代码)统一
     */
    private String unit;
    /*货物规格*/
    private String spec;
    /*货物单位*/
    private double weight;
    /*行邮税号*/
    private String hsCode;
    public String getHsCode() {
		return hsCode;
	}

	public void setHsCode(String hsCode) {
		this.hsCode = hsCode;
	}

	public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    public Integer getgId() {
        return gId;
    }
    
    public void setgId(Integer gId) {
        this.gId = gId;
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
    
    public String getGg() {
        return gg;
    }
    
    public void setGg(String gg) {
        this.gg = gg;
    }
    
    public Integer getCount() {
        return count;
    }
    
    public void setCount(Integer count) {
        this.count = count;
    }
    
    public BigDecimal getPrice() {
        return price;
    }
    
    public void setPrice(BigDecimal price) {
        this.price = price;
    }
    
    public String getOrderNo() {
        return orderNo;
    }
    
    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }
    
    public Integer getbId() {
        return bId;
    }
    
    public void setbId(Integer bId) {
        this.bId = bId;
    }
    
    public Boolean getReview() {
        return review;
    }
    
    public void setReview(Boolean review) {
        this.review = review;
    }
    
    public Integer getlId() {
        return lId;
    }
    
    public void setlId(Integer lId) {
        this.lId = lId;
    }
    
    public BigDecimal getlPrice() {
        return lPrice;
    }
    
    public void setlPrice(BigDecimal lPrice) {
        this.lPrice = lPrice;
    }
    
    public String getlName() {
        return lName;
    }
    
    public void setlName(String lName) {
        this.lName = lName;
    }
    
    public String getlCode() {
        return lCode;
    }
    
    public void setlCode(String lCode) {
        this.lCode = lCode;
    }
    
    public String getComments() {
        return comments;
    }
    
    public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public String getSpec() {
		return spec;
	}

	public void setSpec(String spec) {
		this.spec = spec;
	}

	public void setComments(String comments) {
        this.comments = comments;
    }
    
    public Integer getGid() {
        return gid;
    }
    
    public void setGid(Integer gid) {
        this.gid = gid;
    }
    
    public BigDecimal getBrokerage() {
        return brokerage;
    }
    
    public void setBrokerage(BigDecimal brokerage) {
        this.brokerage = brokerage;
    }

	public BigDecimal getProfit() {
		return profit;
	}

	public void setProfit(BigDecimal profit) {
		this.profit = profit;
	}

	public BigDecimal getPfPrice() {
		return pfPrice;
	}

	public void setPfPrice(BigDecimal pfPrice) {
		this.pfPrice = pfPrice;
	}

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getSupplier() {
		return supplier;
	}

	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}


	public String getCodeTs() {
		return codeTs;
	}

	public void setCodeTs(String codeTs) {
		this.codeTs = codeTs;
	}

	public String getTradeCountry() {
		return tradeCountry;
	}

	public void setTradeCountry(String tradeCountry) {
		this.tradeCountry = tradeCountry;
	}

	public String getGoodsModal() {
		return goodsModal;
	}

	public void setGoodsModal(String goodsModal) {
		this.goodsModal = goodsModal;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}


    
}