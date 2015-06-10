/**
 * ==================   =============   ============   =================================
 * Author               OperateType     Date           Description
 * ==================   =============   ============   =================================
 * lilin                New             2012-11-27     审计日志常量定义接口
 */
package com.zebone.btp.log;

/**
 * 审计日志常量定义接口
 *
 * @author lilin
 * @version [版本号, 2012-11-27]
 */
public interface LogConstant
{
    /**
     * opflag标识：新增
     */
    public static final String LOG_OPFLAG_ADD = "1";

    /**
     * opflag标识：修改
     */
    public static final String LOG_OPFLAG_UPDATE = "2";

    /**
     * opflag标识：删除
     */
    public static final String LOG_OPFLAG_DELETE = "3";

    /**
     * opflag标识：查询
     */
    public static final String LOG_OPFLAG_SEARCH = "4";
}
