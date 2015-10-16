package com.isell.service.order.po;

import java.math.BigDecimal;
import java.util.List;

import com.isell.service.product.vo.CoolProduct;

/**
 * 
 * 订单返回类
 * 
 * @author wangpegn
 * @version [版本号, 2015-10-05]
 */
public class CoolOrderReturn {
	
	/**
	 * 商品总数
	 */
	private int count;
	
	/**
	 * 总金额
	 */
	private BigDecimal total;
	
	/**
	 * 邮费
	 */
	private Double psPrice;
	
	/**
	 * 商品信息列表
	 */
	private List<CoolProduct> products;

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public Double getPsPrice() {
		return psPrice;
	}

	public void setPsPrice(Double psPrice) {
		this.psPrice = psPrice;
	}

	public List<CoolProduct> getProducts() {
		return products;
	}

	public void setProducts(List<CoolProduct> products) {
		this.products = products;
	}

}
