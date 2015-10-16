package com.isell.service.product.vo;

import java.math.BigDecimal;
import java.util.Date;


/**
 * 
 * 商品VO
 * 
 * @author wangpeng
 * @version [版本号, 2015-10-04]
 */
public class CoolProduct{
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
     * 
     */
    public Integer getId(){
        return this.id;
    }

    /**
     * 
     */
    public void setId(Integer id){
        this.id = id;
    }    
    /**
     * 
     */
    public String getName(){
        return this.name;
    }

    /**
     * 
     */
    public void setName(String name){
        this.name = name;
    }    
    /**
     * 
     */
    public String getNameEn(){
        return this.nameEn;
    }

    /**
     * 
     */
    public void setNameEn(String nameEn){
        this.nameEn = nameEn;
    }    
    /**
     * 
     */
    public Integer getType(){
        return this.type;
    }

    /**
     * 
     */
    public void setType(Integer type){
        this.type = type;
    }    
    /**
     * 
     */
    public String getRemark(){
        return this.remark;
    }

    /**
     * 
     */
    public void setRemark(String remark){
        this.remark = remark;
    }    
    /**
     * 
     */
    public String getContent(){
        return this.content;
    }

    /**
     * 
     */
    public void setContent(String content){
        this.content = content;
    }    
    /**
     * 
     */
    public Boolean getTuijian(){
        return this.tuijian;
    }

    /**
     * 
     */
    public void setTuijian(Boolean tuijian){
        this.tuijian = tuijian;
    }    
    /**
     * 
     */
    public Boolean getShouye(){
        return this.shouye;
    }

    /**
     * 
     */
    public void setShouye(Boolean shouye){
        this.shouye = shouye;
    }    
    /**
     * 
     */
    public String getBp(){
        return this.bp;
    }

    /**
     * 
     */
    public void setBp(String bp){
        this.bp = bp;
    }    
    /**
     * 
     */
    public Date getCreatetime(){
        return this.createtime;
    }

    /**
     * 
     */
    public void setCreatetime(Date createtime){
        this.createtime = createtime;
    }    
    /**
     * 
     */
    public Integer getOrderby(){
        return this.orderby;
    }

    /**
     * 
     */
    public void setOrderby(Integer orderby){
        this.orderby = orderby;
    }    
    /**
     * 
     */
    public String getLogo(){
        return this.logo;
    }

    /**
     * 
     */
    public void setLogo(String logo){
        this.logo = logo;
    }    
    /**
     * 
     */
    public Integer getStock(){
        return this.stock;
    }

    /**
     * 
     */
    public void setStock(Integer stock){
        this.stock = stock;
    }    
    /**
     * 1.商品 2.配件
     */
    public String getGt(){
        return this.gt;
    }

    /**
     * 1.商品 2.配件
     */
    public void setGt(String gt){
        this.gt = gt;
    }    
    /**
     * 
     */
    public Integer getClick(){
        return this.click;
    }

    /**
     * 
     */
    public void setClick(Integer click){
        this.click = click;
    }    
    /**
     * 
     */
    public Integer getbId(){
        return this.bId;
    }

    /**
     * 
     */
    public void setbId(Integer bId){
        this.bId = bId;
    }    
    /**
     * 
     */
    public String getIntro(){
        return this.intro;
    }

    /**
     * 
     */
    public void setIntro(String intro){
        this.intro = intro;
    }    
    /**
     * 
     */
    public Boolean getShelves(){
        return this.shelves;
    }

    /**
     * 
     */
    public void setShelves(Boolean shelves){
        this.shelves = shelves;
    }    
    /**
     * 
     */
    public Boolean getKuajing(){
        return this.kuajing;
    }

    /**
     * 
     */
    public void setKuajing(Boolean kuajing){
        this.kuajing = kuajing;
    }    
    /**
     * 
     */
    public Integer getBrandId(){
        return this.brandId;
    }

    /**
     * 
     */
    public void setBrandId(Integer brandId){
        this.brandId = brandId;
    }    
    /**
     * 
     */
    public Integer getGgMaxCode(){
        return this.ggMaxCode;
    }

    /**
     * 
     */
    public void setGgMaxCode(Integer ggMaxCode){
        this.ggMaxCode = ggMaxCode;
    }    
    /**
     * 
     */
    public String getCountryCode(){
        return this.countryCode;
    }

    /**
     * 
     */
    public void setCountryCode(String countryCode){
        this.countryCode = countryCode;
    }    
    /**
     * 
     */
    public String getCode(){
        return this.code;
    }

    /**
     * 
     */
    public void setCode(String code){
        this.code = code;
    }    
    /**
     * 
     */
    public BigDecimal getPrice(){
        return this.price;
    }

    /**
     * 
     */
    public void setPrice(BigDecimal price){
        this.price = price;
    }    
    /**
     * 
     */
    public BigDecimal getFavPrice(){
        return this.favPrice;
    }

    /**
     * 
     */
    public void setFavPrice(BigDecimal favPrice){
        this.favPrice = favPrice;
    }    
    /**
     * 
     */
    public Boolean getAdded(){
        return this.added;
    }

    /**
     * 
     */
    public void setAdded(Boolean added){
        this.added = added;
    }    
    /**
     * 
     */
    public Integer getWapClick(){
        return this.wapClick;
    }

    /**
     * 
     */
    public void setWapClick(Integer wapClick){
        this.wapClick = wapClick;
    }    
    /**
     * 
     */
    public Integer getSales(){
        return this.sales;
    }

    /**
     * 
     */
    public void setSales(Integer sales){
        this.sales = sales;
    }    
    /**
     * 
     */
    public String getQrCode(){
        return this.qrCode;
    }

    /**
     * 
     */
    public void setQrCode(String qrCode){
        this.qrCode = qrCode;
    }    
    /**
     * 
     */
    public BigDecimal getDivide(){
        return this.divide;
    }

    /**
     * 
     */
    public void setDivide(BigDecimal divide){
        this.divide = divide;
    }    
    /**
     * 
     */
    public String getTag(){
        return this.tag;
    }

    /**
     * 
     */
    public void setTag(String tag){
        this.tag = tag;
    }

    public CoolProductGg getStandard() {
        return standard;
    }

    public void setStandard(CoolProductGg standard) {
        this.standard = standard;
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
}