package com.zebone.dip.log.dao;

import com.zebone.btp.core.mybatis.WrcMapper;
import com.zebone.dip.log.vo.DocViewLog;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

/**
 * 文档调阅日志DAO
 * @author 杨英
 * @version 2013-11-21 上午9:33
 */

@WrcMapper
public interface DocViewLogMapper {

    /**
     * 获取文档调阅日志列表
     * @param docViewLog
     * @param rowBounds
     * @return
     * List<DocViewLog>
     */
    List<DocViewLog> getDocViewLogList(DocViewLog docViewLog, RowBounds rowBounds);

    /**
     * 获取文档调阅日志总数
     * @param docViewLog
     * @return
     * int
     */
    int getDocViewLogCount(DocViewLog docViewLog);
}
