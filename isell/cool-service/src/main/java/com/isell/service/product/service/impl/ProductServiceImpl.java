package com.isell.service.product.service.impl;


import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.isell.core.mybatis.page.PageInfo;
import com.isell.service.product.dao.CoolProductGgMapper;
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
    /**
     * 商品mapper
     */
    @Resource
    private CoolProductMapper coolProductMapper;
    
    /**
     * 商品规格mapper
     */
    @Resource
    private CoolProductGgMapper coolProductGgMapper;
    
    /**
     * 所有商品
     */
    @Override
    public PageInfo<CoolProduct> getCoolProductPageList(CoolProductSelect coolProductSelect) {
        PageInfo<CoolProduct> pageInfo = new PageInfo<CoolProduct>();
        coolProductSelect.setShelves(false); // 未下架的产品
        pageInfo.setRows(coolProductMapper.getCoolProductPageList(coolProductSelect.getRowBounds(), coolProductSelect));
        pageInfo.setTotal(coolProductMapper.getCoolProductPageListCount(coolProductSelect));
        if (coolProductSelect.isSearchDetail() && pageInfo.getRows() != null) {
            for (CoolProduct product : pageInfo.getRows()) {
                product.setGgList(coolProductGgMapper.findCoolProductGgList(product.getId()));
            }
        }
        return pageInfo;
    }

    @Override
    public CoolProduct getCoolProductById(CoolProductSelect param) {
        CoolProduct product = coolProductMapper.getCoolProductById(param.getId());
        if (param.isSearchDetail() && product != null) {
            product.setGgList(coolProductGgMapper.findCoolProductGgList(product.getId()));
        }
        return product;
    }


}
