package com.isell.service.product.po;

import java.math.BigDecimal;
import java.util.List;
/**
 * 对外商品查询
 * 
 * @author maoweijie
 * @version [版本号, 2016-1-15]
 */
public class CoolProductExternal {
    private Integer id;
    private Integer gId;
    private String name;
    private String nameEn;
    private Integer type;
    private String remark;
    private String content;
    private String code;
    private String brand;
    private String country;
    private BigDecimal price;
    private BigDecimal favprice;
    private String logo;
    private BigDecimal tax;
    private List<CoolProductExternalGg> items;
    private List<CoolProductExternalImg> imgs;
    private List<CoolProductAliUnit>units;
    
    
	public List<CoolProductAliUnit> getUnits() {
		return units;
	}
	public void setUnits(List<CoolProductAliUnit> units) {
		this.units = units;
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
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public BigDecimal getFavprice() {
		return favprice;
	}
	public void setFavprice(BigDecimal favprice) {
		this.favprice = favprice;
	}
	public String getLogo() {
		return logo;
	}
	public void setLogo(String logo) {
		this.logo = logo;
	}
	public BigDecimal getTax() {
		return tax;
	}
	public void setTax(BigDecimal tax) {
		this.tax = tax;
	}
	public List<CoolProductExternalGg> getItems() {
		return items;
	}
	public void setItems(List<CoolProductExternalGg> items) {
		this.items = items;
	}
	public List<CoolProductExternalImg> getImgs() {
		return imgs;
	}
	public void setImgs(List<CoolProductExternalImg> imgs) {
		this.imgs = imgs;
	}
    	
}
