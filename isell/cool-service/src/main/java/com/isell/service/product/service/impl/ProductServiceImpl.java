package com.isell.service.product.service.impl;


import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.isell.core.mybatis.page.PageInfo;
import com.isell.service.product.dao.CoolProductMapper;
import com.isell.service.product.po.CoolProductSelect;
import com.isell.service.product.service.ProductService;
import com.isell.service.product.vo.CoolProduct;

/**
 * 商品服务接口实现类
 * 
 * @author lilin
 * @version [版本号, 2015年9月24日]
 */
@Service("productService")
public class ProductServiceImpl implements ProductService {
    
    @Resource
    private CoolProductMapper coolProductMapper;
    
    /**
     * 所有商品
     */
    @Override
    public PageInfo<CoolProduct> getCoolProductPageList(CoolProductSelect coolProductSelect) {
        // TODO Auto-generated method stub
        PageInfo<CoolProduct> pageInfo = new PageInfo<CoolProduct>();
        pageInfo.setRows(coolProductMapper.getCoolProductPageList(coolProductSelect.getRowBounds(), coolProductSelect));
        pageInfo.setTotal(coolProductMapper.getCoolProductPageListCount(coolProductSelect));
        return pageInfo;
    }


}
