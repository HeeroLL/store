package com.zebone.docSub.dao;

import com.zebone.btp.core.mybatis.DcMapper;

/**
 * 文档存储数据 DAO
 *
 * @author 杨英
 * @version 2014-08-12 下午02:47
 */
@DcMapper
public interface DocStorageMapper {
    /**
     * 根据文档编号获取文档具体内容
     * @param docNo
     * @return
     */
    String getDocContentByDocNo(String docNo);
}
