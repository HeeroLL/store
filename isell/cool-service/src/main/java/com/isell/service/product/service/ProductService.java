package com.isell.service.product.service;

import java.util.List;

import com.isell.core.mybatis.page.PageInfo;
import com.isell.service.product.po.CoolProductSelect;
import com.isell.service.product.vo.CoolProduct;


/**
 * 商品接口层
 * 
 * @author wangpeng
 * @version [版本号, 2015-10-09]
 */
public interface ProductService {
	
	/**
	 * 分页批量获取商品数据
	 */
    PageInfo<CoolProduct> getCoolProductPageList(CoolProductSelect coolProductSelect);
}
