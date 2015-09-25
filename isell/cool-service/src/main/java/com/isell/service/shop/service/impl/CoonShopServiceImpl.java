package com.isell.service.shop.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.isell.service.shop.dao.CoonShopMapper;
import com.isell.service.shop.service.CoonShopService;
import com.isell.service.shop.vo.CoonShop;

/**
 * 酷店业务层接口实现类
 * 
 * @author lilin
 * @version [版本号, 2015年9月25日]
 */
@Service("coonShopService")
public class CoonShopServiceImpl implements CoonShopService {
    
    /**
     * 酷店表Mapper
     */
    @Resource
    private CoonShopMapper coonShopMapper;
    
    @Override
    public CoonShop getCoonShopById(String id) {
        return coonShopMapper.getCoonShopById(id);
    }
    
}
