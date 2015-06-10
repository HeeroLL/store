package com.zebone.dnode.engine.preprocess.dao;

import java.util.List;

import com.zebone.btp.core.mybatis.DcMapper;
import com.zebone.dnode.engine.preprocess.vo.PreprocessData;

/**
 * 数据加工预处理Dao
 *
 * @author 杨英
 * @version 2013-9-11 上午09:48
 */
@DcMapper
public interface PreprocessDataMapper {

    /**
     * 批量插入预处理的数据
     * @param list
     * @return
     */
    public void insertPreprocessData (List<PreprocessData> list);

    /**
     * 根据ID批量删除数据
     * @param list
     * @return
     */
    public void deletePreprocessData (List<PreprocessData> list);

    /**
     * 根据empiNo和DataCode获取ID信息
     * @param preprocessData
     * @return
     */
    public String getIdByEmpiAndDataCd(PreprocessData preprocessData);
}
