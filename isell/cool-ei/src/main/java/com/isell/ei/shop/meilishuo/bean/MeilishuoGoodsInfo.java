package com.isell.ei.shop.meilishuo.bean;

/**
 * 美丽说订单商品详情
 * 
 * @author wangpeng
 * @version [版本号, 2015-12-31]
 */
public class MeilishuoGoodsInfo {
	
	/** 品牌 */
	private String brand;
	
	/** 商品编码 */
	private long goods_id;
	
	/** 商品名称 */
	private String goods_name;
	
	/** 商品类目 */	
	private String category_name;
	
	/** 商品成交价 */
	private double goods_price;
	
	/** 商品原价 */
	private double goods_list_price;
	
	/** 价格单位 */
	private String price_unit;
	
	/** 商品数量 */
	private long goods_quantity;
	
	/** sku编码 */
	private long sku_id;
	
	/** 商品属性 */
	private MeilishuoGoodsProperties goods_properties;
	
	/** 征集订单特有的货号 */
	private String goods_no;

	/** 商品编码 */
	public long getGoods_id() {
		return goods_id;
	}

	/** 商品编码 */
	public void setGoods_id(long goods_id) {
		this.goods_id = goods_id;
	}

	/** 商品名称 */
	public String getGoods_name() {
		return goods_name;
	}

	/** 商品名称 */
	public void setGoods_name(String goods_name) {
		this.goods_name = goods_name;
	}

	/** 商品类目 */	
	public String getCategory_name() {
		return category_name;
	}

	/** 商品类目 */	
	public void setCategory_name(String category_name) {
		this.category_name = category_name;
	}

	/** 商品成交价 */
	public double getGoods_price() {
		return goods_price;
	}

	/** 商品成交价 */
	public void setGoods_price(double goods_price) {
		this.goods_price = goods_price;
	}

	/** 商品原价 */
	public double getGoods_list_price() {
		return goods_list_price;
	}

	/** 商品原价 */
	public void setGoods_list_price(double goods_list_price) {
		this.goods_list_price = goods_list_price;
	}

	/** 价格单位 */
	public String getPrice_unit() {
		return price_unit;
	}

	/** 价格单位 */
	public void setPrice_unit(String price_unit) {
		this.price_unit = price_unit;
	}

	/** 商品数量 */
	public long getGoods_quantity() {
		return goods_quantity;
	}

	/** 商品数量 */
	public void setGoods_quantity(long goods_quantity) {
		this.goods_quantity = goods_quantity;
	}

	/** sku编码 */
	public long getSku_id() {
		return sku_id;
	}

	/** sku编码 */
	public void setSku_id(long sku_id) {
		this.sku_id = sku_id;
	}

	/** 商品属性 */
	public MeilishuoGoodsProperties getGoods_properties() {
		return goods_properties;
	}

	/** 商品属性 */
	public void setGoods_properties(MeilishuoGoodsProperties goods_properties) {
		this.goods_properties = goods_properties;
	}

	/** 品牌 */
	public String getBrand() {
		return brand;
	}

	/** 品牌 */
	public void setBrand(String brand) {
		this.brand = brand;
	}

	/**征集订单特有的货号 */
	public String getGoods_no() {
		return goods_no;
	}

	/**征集订单特有的货号 */
	public void setGoods_no(String goods_no) {
		this.goods_no = goods_no;
	}

}
