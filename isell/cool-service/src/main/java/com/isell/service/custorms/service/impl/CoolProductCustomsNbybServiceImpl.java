package com.isell.service.custorms.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.isell.service.custorms.dao.CoolProductCustomsNbybMapper;
import com.isell.service.custorms.po.CoolProductCustomsNbyb;
import com.isell.service.custorms.service.CoolProductCustomsNbybService;

/**
 * 宁波优贝商品海关备案业务层接口实现类
 * 
 * @author lilin
 * @version [版本号, 2015年12月10日]
 */
@Service("coolProductCustomsNbybService")
public class CoolProductCustomsNbybServiceImpl implements CoolProductCustomsNbybService {
    /**
     * 郑州商品海关备案Mapper
     */
    @Resource
    private CoolProductCustomsNbybMapper coolProductCustomsNbybMapper;
    
    @Override
    public CoolProductCustomsNbyb getCustomsInfoByGid(Integer gid) {
        return coolProductCustomsNbybMapper.getCustomsInfoByGid(gid);
    }
    
}
