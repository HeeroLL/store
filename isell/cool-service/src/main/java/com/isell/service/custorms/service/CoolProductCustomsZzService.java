package com.isell.service.custorms.service;

import com.isell.service.custorms.po.CoolProductCustomsZz;

/**
 * 郑州商品海关备案业务层接口
 * 
 * @author lilin
 * @version [版本号, 2015年12月10日]
 */
public interface CoolProductCustomsZzService {
    /**
     * 通过规格id获取商品备案信息
     *
     * @param gid 规格id
     * @return 商品备案信息
     */
    CoolProductCustomsZz getCustomsInfoByGid(Integer gid);
}
