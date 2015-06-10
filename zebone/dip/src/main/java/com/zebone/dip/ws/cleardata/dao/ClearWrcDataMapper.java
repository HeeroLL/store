package com.zebone.dip.ws.cleardata.dao;

import com.zebone.btp.core.mybatis.WrcMapper;

/**
 * @author Yang Ying
 * @version 2014-07-10
 */
@WrcMapper
public interface ClearWrcDataMapper {

    void deleteRegEmpiInfo(String cardNo);

    void deleteRegLogInfo(String empiNo);

    void deleteRegMainInfo(String empiNo);
}
