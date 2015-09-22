package com.zebone.dip.ws.cleardata.dao;

import com.zebone.btp.core.mybatis.Mapper;

import java.util.List;

/**
 * @author Yang Ying
 * @version 2014-07-10
 */
@Mapper
public interface ClearDipDataMapper {

    List<String> getTableNameLic(String empiNo);

    List<String> getDocNoLic(String empiNo);

    void deleteParseTableInfo(String empiNo);
}
