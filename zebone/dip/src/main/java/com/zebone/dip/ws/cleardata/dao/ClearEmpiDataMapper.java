package com.zebone.dip.ws.cleardata.dao;

import com.zebone.btp.core.mybatis.EmpiMapper;

import java.util.Map;

/**
 * @author Yang Ying
 * @version 2014-07-10
 */
@EmpiMapper
public interface ClearEmpiDataMapper {

    String getEmpiNo(Map oMap);

    void deleteLogData(String empiNo);

    void deleteCardData(String empiNo);

    void deleteEmpiInfo(String empiNo);
}
