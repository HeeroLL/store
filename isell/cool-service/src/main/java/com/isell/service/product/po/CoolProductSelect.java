package com.isell.service.product.po;

import java.math.BigDecimal;
import java.util.Date;

import com.isell.core.mybatis.page.PageConfig;
import com.isell.service.product.vo.CoolProductGg;

public class CoolProductSelect extends PageConfig{
    /**
     * 
     */
    private Integer id;
    /**
     * 
     */
    private String name;
    /**
     * 
     */
    private String nameEn;
    /**
     * 
     */
    private Integer type;
    /**
     * 
     */
    private String remark;
    /**
     * 
     */
    private String content;
    /**
     * 
     */
    private Boolean tuijian;
    /**
     * 
     */
    private Boolean shouye;
    /**
     * 
     */
    private String bp;
    /**
     * 
     */
    private Date createtime;
    /**
     * 
     */
    private Integer orderby;
    /**
     * 
     */
    private String logo;
    /**
     * 
     */
    private Integer stock;
    /**
     * 1.商品 2.配件
     */
    private String gt;
    /**
     * 
     */
    private Integer click;
    /**
     * 
     */
    private Integer bId;
    /**
     * 
     */
    private String intro;
    /**
     * 
     */
    private Boolean shelves;
    /**
     * 
     */
    private Boolean kuajing;
    /**
     * 
     */
    private Integer brandId;
    /**
     * 
     */
    private Integer ggMaxCode;
    /**
     * 
     */
    private String countryCode;
    /**
     * 
     */
    private String code;
    /**
     * 
     */
    private BigDecimal price;
    /**
     * 
     */
    private BigDecimal favPrice;
    /**
     * 
     */
    private Boolean added;
    /**
     * 
     */
    private Integer wapClick;
    /**
     * 
     */
    private Integer sales;
    /**
     * 
     */
    private String qrCode;
    /**
     * 
     */
    private BigDecimal divide;
    /**
     * 
     */
    private String tag;
    
    /**
     * 数量
     */
    private Integer quantity;
    
    /**
     * 
     */
    private Date updatetime;
    
    /**
     * 规格
     */
    private CoolProductGg standard;
    /**
     * 起始的修改时间
     */
    private Date startUpdatetime;
    
    /**
     * 结束的修改时间
     */
    private Date endUpdatetime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameEn() {
        return nameEn;
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Boolean getTuijian() {
        return tuijian;
    }

    public void setTuijian(Boolean tuijian) {
        this.tuijian = tuijian;
    }

    public Boolean getShouye() {
        return shouye;
    }

    public void setShouye(Boolean shouye) {
        this.shouye = shouye;
    }

    public String getBp() {
        return bp;
    }

    public void setBp(String bp) {
        this.bp = bp;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Integer getOrderby() {
        return orderby;
    }

    public void setOrderby(Integer orderby) {
        this.orderby = orderby;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public String getGt() {
        return gt;
    }

    public void setGt(String gt) {
        this.gt = gt;
    }

    public Integer getClick() {
        return click;
    }

    public void setClick(Integer click) {
        this.click = click;
    }

    public Integer getbId() {
        return bId;
    }

    public void setbId(Integer bId) {
        this.bId = bId;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public Boolean getShelves() {
        return shelves;
    }

    public void setShelves(Boolean shelves) {
        this.shelves = shelves;
    }

    public Boolean getKuajing() {
        return kuajing;
    }

    public void setKuajing(Boolean kuajing) {
        this.kuajing = kuajing;
    }

    public Integer getBrandId() {
        return brandId;
    }

    public void setBrandId(Integer brandId) {
        this.brandId = brandId;
    }

    public Integer getGgMaxCode() {
        return ggMaxCode;
    }

    public void setGgMaxCode(Integer ggMaxCode) {
        this.ggMaxCode = ggMaxCode;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getFavPrice() {
        return favPrice;
    }

    public void setFavPrice(BigDecimal favPrice) {
        this.favPrice = favPrice;
    }

    public Boolean getAdded() {
        return added;
    }

    public void setAdded(Boolean added) {
        this.added = added;
    }

    public Integer getWapClick() {
        return wapClick;
    }

    public void setWapClick(Integer wapClick) {
        this.wapClick = wapClick;
    }

    public Integer getSales() {
        return sales;
    }

    public void setSales(Integer sales) {
        this.sales = sales;
    }

    public String getQrCode() {
        return qrCode;
    }

    public void setQrCode(String qrCode) {
        this.qrCode = qrCode;
    }

    public BigDecimal getDivide() {
        return divide;
    }

    public void setDivide(BigDecimal divide) {
        this.divide = divide;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    public CoolProductGg getStandard() {
        return standard;
    }

    public void setStandard(CoolProductGg standard) {
        this.standard = standard;
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
    
    
}
