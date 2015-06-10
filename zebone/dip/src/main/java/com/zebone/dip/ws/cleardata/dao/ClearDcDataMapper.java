package com.zebone.dip.ws.cleardata.dao;

import com.zebone.btp.core.mybatis.DcMapper;

import java.util.Map;

/**
 * @author Yang Ying
 * @version 2014-07-10
 */
@DcMapper
public interface ClearDcDataMapper {

    void deleteDocLogInfo(String docNo);

    void deleteDocInfo(String docNo);

    void deleteParseInfo(Map oMap);


}
