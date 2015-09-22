package com.zebone.dnode.engine.preprocess.dao;

import java.util.List;

import com.zebone.btp.core.mybatis.DipMapper;
import com.zebone.dnode.engine.preprocess.vo.ParseLogInfo;

/**
 * 业务文档变化日志Dao
 *
 * @author 杨英
 * @version 2013-9-11 上午08:30
 */
@DipMapper
public interface ParseLogMapper {

    /**
     * 查询出未加工的数据信息
     * @return
     */
    public List<ParseLogInfo> getUndressedInfo (List<String> list);

    /**
     * 更新数据的加工状态
     * @param id
     */
    public void updateDataFlag(String id);

    /**
     * 获取与高血压或糖尿病等慢病曲线相关的解析日志信息
     * @return
     */
    public List<ParseLogInfo> getParseLogInfoForCurve (List<String> list);
}
