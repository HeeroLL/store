package com.isell.service.custorms.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.isell.service.custorms.dao.CoolProductCustomsZzMapper;
import com.isell.service.custorms.po.CoolProductCustomsZz;
import com.isell.service.custorms.service.CoolProductCustomsZzService;

/**
 * 郑州商品海关备案业务层接口实现类
 * 
 * @author lilin
 * @version [版本号, 2015年12月10日]
 */
@Service("coolProductCustomsZzService")
public class CoolProductCustomsZzServiceImpl implements CoolProductCustomsZzService {
    /**
     * 郑州商品海关备案Mapper
     */
    @Resource
    private CoolProductCustomsZzMapper coolProductCustomsZzMapper;
    
    @Override
    public CoolProductCustomsZz getCustomsInfoByGid(Integer gid) {
        return coolProductCustomsZzMapper.getCustomsInfoByGid(gid);
    }
    
}
