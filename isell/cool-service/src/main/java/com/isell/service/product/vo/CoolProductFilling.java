package com.isell.service.product.vo;

import java.util.Date;

/**
 * 
 * 海关备案表VO
 * 
 * @author wangpeng
 * @version [版本号, 2015-10-04]
 */
public class CoolProductFilling{
    /**
     * 
     */
    private Integer id;
    /**
     * 
     */
    private String postTaxNo;
    /**
     * 
     */
    private String goodsType;
    /**
     * 
     */
    private String goodsName;
    /**
     * 
     */
    private String barCode;
    /**
     * 
     */
    private String brand;
    /**
     * 
     */
    private String goodsModel;
    /**
     * 
     */
    private String mainElement;
    /**
     * 
     */
    private String purpose;
    /**
     * 
     */
    private String standards;
    /**
     * 
     */
    private String productionEnterprise;
    /**
     * 
     */
    private String productionCountry;
    /**
     * 
     */
    private String licenceKey;
    /**
     * 
     */
    private String categoryCode;
    /**
     * 
     */
    private String materialAddress;
    /**
     * 
     */
    private String declareTime;
    /**
     * 0.未备案 1.备案初审 2.备案中 3.备案成功 4.备案初审失败 5.备案失败
     */
    private Integer state;
    /**
     * 
     */
    private Integer proId;
    /**
     * 
     */
    private Integer categoryId;
    /**
     * 
     */
    private String businessNo;
    /**
     * 
     */
    private String noticeDate;
    /**
     * 
     */
    private String productRecordNo;
    /**
     * 
     */
    private String approveResult;
    /**
     * 
     */
    private String approveComment;
    /**
     * 
     */
    private String processTime;
    /**
     * 
     */
    private Date createtime;

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
    public String getPostTaxNo(){
        return this.postTaxNo;
    }

    /**
     * 
     */
    public void setPostTaxNo(String postTaxNo){
        this.postTaxNo = postTaxNo;
    }    
    /**
     * 
     */
    public String getGoodsType(){
        return this.goodsType;
    }

    /**
     * 
     */
    public void setGoodsType(String goodsType){
        this.goodsType = goodsType;
    }    
    /**
     * 
     */
    public String getGoodsName(){
        return this.goodsName;
    }

    /**
     * 
     */
    public void setGoodsName(String goodsName){
        this.goodsName = goodsName;
    }    
    /**
     * 
     */
    public String getBarCode(){
        return this.barCode;
    }

    /**
     * 
     */
    public void setBarCode(String barCode){
        this.barCode = barCode;
    }    
    /**
     * 
     */
    public String getBrand(){
        return this.brand;
    }

    /**
     * 
     */
    public void setBrand(String brand){
        this.brand = brand;
    }    
    /**
     * 
     */
    public String getGoodsModel(){
        return this.goodsModel;
    }

    /**
     * 
     */
    public void setGoodsModel(String goodsModel){
        this.goodsModel = goodsModel;
    }    
    /**
     * 
     */
    public String getMainElement(){
        return this.mainElement;
    }

    /**
     * 
     */
    public void setMainElement(String mainElement){
        this.mainElement = mainElement;
    }    
    /**
     * 
     */
    public String getPurpose(){
        return this.purpose;
    }

    /**
     * 
     */
    public void setPurpose(String purpose){
        this.purpose = purpose;
    }    
    /**
     * 
     */
    public String getStandards(){
        return this.standards;
    }

    /**
     * 
     */
    public void setStandards(String standards){
        this.standards = standards;
    }    
    /**
     * 
     */
    public String getProductionEnterprise(){
        return this.productionEnterprise;
    }

    /**
     * 
     */
    public void setProductionEnterprise(String productionEnterprise){
        this.productionEnterprise = productionEnterprise;
    }    
    /**
     * 
     */
    public String getProductionCountry(){
        return this.productionCountry;
    }

    /**
     * 
     */
    public void setProductionCountry(String productionCountry){
        this.productionCountry = productionCountry;
    }    
    /**
     * 
     */
    public String getLicenceKey(){
        return this.licenceKey;
    }

    /**
     * 
     */
    public void setLicenceKey(String licenceKey){
        this.licenceKey = licenceKey;
    }    
    /**
     * 
     */
    public String getCategoryCode(){
        return this.categoryCode;
    }

    /**
     * 
     */
    public void setCategoryCode(String categoryCode){
        this.categoryCode = categoryCode;
    }    
    /**
     * 
     */
    public String getMaterialAddress(){
        return this.materialAddress;
    }

    /**
     * 
     */
    public void setMaterialAddress(String materialAddress){
        this.materialAddress = materialAddress;
    }    
    /**
     * 
     */
    public String getDeclareTime(){
        return this.declareTime;
    }

    /**
     * 
     */
    public void setDeclareTime(String declareTime){
        this.declareTime = declareTime;
    }    
    /**
     * 0.未备案 1.备案初审 2.备案中 3.备案成功 4.备案初审失败 5.备案失败
     */
    public Integer getState(){
        return this.state;
    }

    /**
     * 0.未备案 1.备案初审 2.备案中 3.备案成功 4.备案初审失败 5.备案失败
     */
    public void setState(Integer state){
        this.state = state;
    }    
    /**
     * 
     */
    public Integer getProId(){
        return this.proId;
    }

    /**
     * 
     */
    public void setProId(Integer proId){
        this.proId = proId;
    }    
    /**
     * 
     */
    public Integer getCategoryId(){
        return this.categoryId;
    }

    /**
     * 
     */
    public void setCategoryId(Integer categoryId){
        this.categoryId = categoryId;
    }    
    /**
     * 
     */
    public String getBusinessNo(){
        return this.businessNo;
    }

    /**
     * 
     */
    public void setBusinessNo(String businessNo){
        this.businessNo = businessNo;
    }    
    /**
     * 
     */
    public String getNoticeDate(){
        return this.noticeDate;
    }

    /**
     * 
     */
    public void setNoticeDate(String noticeDate){
        this.noticeDate = noticeDate;
    }    
    /**
     * 
     */
    public String getProductRecordNo(){
        return this.productRecordNo;
    }

    /**
     * 
     */
    public void setProductRecordNo(String productRecordNo){
        this.productRecordNo = productRecordNo;
    }    
    /**
     * 
     */
    public String getApproveResult(){
        return this.approveResult;
    }

    /**
     * 
     */
    public void setApproveResult(String approveResult){
        this.approveResult = approveResult;
    }    
    /**
     * 
     */
    public String getApproveComment(){
        return this.approveComment;
    }

    /**
     * 
     */
    public void setApproveComment(String approveComment){
        this.approveComment = approveComment;
    }    
    /**
     * 
     */
    public String getProcessTime(){
        return this.processTime;
    }

    /**
     * 
     */
    public void setProcessTime(String processTime){
        this.processTime = processTime;
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
}