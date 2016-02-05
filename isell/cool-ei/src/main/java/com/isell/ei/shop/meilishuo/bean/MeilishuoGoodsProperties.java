package com.isell.ei.shop.meilishuo.bean;

/**
 * 美丽说商品属性
 * 
 * @author wangpeng
 * @version [版本号, 2015-12-31]
 */
public class MeilishuoGoodsProperties {	
	
	/** 颜色 */
	private String color_name;
	
	/** 尺码 */
	private String size_name;
	
	/** 材质 */
	private String material;
	
	/** 重量 */	
	private double quality;
	
	/** 重量单位 */
	private String quality_unit;

	/** 颜色 */
	public String getColor_name() {
		return color_name;
	}

	/** 颜色 */
	public void setColor_name(String color_name) {
		this.color_name = color_name;
	}

	/** 尺码 */
	public String getSize_name() {
		return size_name;
	}

	/** 尺码 */
	public void setSize_name(String size_name) {
		this.size_name = size_name;
	}

	/** 材质 */
	public String getMaterial() {
		return material;
	}

	/** 材质 */
	public void setMaterial(String material) {
		this.material = material;
	}

	/** 重量 */	
	public double getQuality() {
		return quality;
	}

	/** 重量 */	
	public void setQuality(double quality) {
		this.quality = quality;
	}

	/** 重量单位 */
	public String getQuality_unit() {
		return quality_unit;
	}

	/** 重量单位 */
	public void setQuality_unit(String quality_unit) {
		this.quality_unit = quality_unit;
	}

}
