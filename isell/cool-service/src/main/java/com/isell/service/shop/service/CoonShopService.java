package com.isell.service.shop.service;

import com.isell.service.shop.vo.CoonShop;

/**
 * 酷店业务层接口
 * 
 * @author lilin
 * @version [版本号, 2015年9月25日]
 */
public interface CoonShopService {
    /**
     * 根据主键查询酷店信息
     * 
     * @param id 主键
     * @return 酷店信息
     */
    CoonShop getCoonShopById(String id);
}
