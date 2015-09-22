package com.zebone.dip.log.service;

import com.zebone.btp.core.util.Pagination;
import com.zebone.dip.log.vo.DocViewLog;
import com.zebone.dip.log.vo.ParmInfo;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

/**
 * 文档调阅日志服务
 * @author 杨英
 * @version 2013-11-20 下午2:19
 */
public interface DocViewLogService {

    /**
     * 方法描述: 获取日志信息列表
     * @param docViewLog
     * @param page
     * @return
     * Pagination<DocViewLog>
     */
    Pagination<DocViewLog> getDocViewLogPage(DocViewLog docViewLog, Pagination<DocViewLog> page);

    /**
     * 方法描述: 获取标识信息列表
     * @return
     * List<ParmInfo>
     */
    List<ParmInfo> getCardInfo();
}
