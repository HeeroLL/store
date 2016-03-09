package com.isell.app.dao.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

public class Product implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1716021475167232660L;
	private int goodsid;
	private String name;
	private String img;
	private String price;
	private String remark;
	private String content;
	private String code;
	private String qrcode;
	private BigDecimal divide;
	private BigDecimal tax;
	private String fav_price;
	
	private int shopcode;
	private String shopid;
	private String mobile;
	private int isrec;// 0 已关注 不存在 1 已关注 已存在 3 未关注 
	private List<ProductImg>productImglist;
	private List<ProductGg>productGglist;
	private List<ProductImg>contentimgs;
	
	private int added;
	private int salenum;
	
	private String cateString;//分类
	private String country;//原产地
	private int stock;//库存
	private String createtime;//创建时间
	private String saleType;//销售类型
	private int ishas;
	private String onSaleTime;// 上架时间
	private String bindTime;// 绑定时间
	private String brand;// 品牌时间
	private int saleCount;// 销量
	
	
	public int getIshas() {
		return ishas;
	}
	public void setIshas(int ishas) {
		this.ishas = ishas;
	}
	public String getMobile() {
		if(mobile==null)
		{
			mobile="";
		}
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public int getShopcode() {
		return shopcode;
	}
	public void setShopcode(int shopcode) {
		this.shopcode = shopcode;
	}
	public String getShopid() {
		return shopid;
	}
	public void setShopid(String shopid) {
		this.shopid = shopid;
	}
	public String getCateString() {
		if(cateString==null)
		{
			cateString="";
		}
		return cateString;
	}
	public void setCateString(String cateString) {
		this.cateString = cateString;
	}
	public String getCountry() {
		if(country==null)
		{
			country="";
		}
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public String getCreatetime() {
		if(createtime==null)
		{
			createtime="";
		}
		return createtime;
	}
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
	public String getSaleType() {
		if(saleType==null)
		{
			saleType="";
		}
		return saleType;
	}
	public void setSaleType(String saleType) {
		this.saleType = saleType;
	}
	public int getSalenum() {
		return salenum;
	}
	public void setSalenum(int salenum) {
		this.salenum = salenum;
	}
	public int getAdded() {
		return added;
	}
	public void setAdded(int added) {
		this.added = added;
	}
	public String getFav_price() {
		return fav_price;
	}
	public void setFav_price(String fav_price) {
		this.fav_price = fav_price;
	}
	public List<ProductImg> getContentimgs() {
		return contentimgs;
	}
	public void setContentimgs(List<ProductImg> contentimgs) {
		this.contentimgs = contentimgs;
	}
	public List<ProductGg> getProductGglist() {
		return productGglist;
	}
	public void setProductGglist(List<ProductGg> productGglist) {
		this.productGglist = productGglist;
	}
	private String image_domain;
	
	public String getImage_domain() {
		return image_domain;
	}
	public void setImage_domain(String image_domain) {
		this.image_domain = image_domain;
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
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getQrcode() {
		return qrcode;
	}
	public void setQrcode(String qrcode) {
		this.qrcode = qrcode;
	}
	public BigDecimal getDivide() {
		return divide;
	}
	public void setDivide(BigDecimal divide) {
		this.divide = divide;
	}
	public BigDecimal getTax() {
		return tax;
	}
	public void setTax(BigDecimal tax) {
		this.tax = tax;
	}
	public List<ProductImg> getProductImglist() {
		return productImglist;
	}
	public void setProductImglist(List<ProductImg> productImglist) {
		this.productImglist = productImglist;
	}
	public int getIsrec() {
		return isrec;
	}
	public void setIsrec(int isrec) {
		this.isrec = isrec;
	}
	 
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public int getGoodsid() {
		return goodsid;
	}
	public void setGoodsid(int goodsid) {
		this.goodsid = goodsid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getImg() {
		if(img==null)
		{
			img="";
		}
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public String getOnSaleTime() {
		return onSaleTime;
	}
	public void setOnSaleTime(String onSaleTime) {
		this.onSaleTime = onSaleTime;
	}
	public String getBindTime() {
		return bindTime;
	}
	public void setBindTime(String bindTime) {
		this.bindTime = bindTime;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public int getSaleCount() {
		return saleCount;
	}
	public void setSaleCount(int saleCount) {
		this.saleCount = saleCount;
	}
	
}
