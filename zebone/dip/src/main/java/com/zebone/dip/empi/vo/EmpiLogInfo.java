package com.zebone.dip.empi.vo;

/**
 * 主索引日志对象
 *
 * @author 杨英
 * @version 2014-7-17 上午10:26
 */
public class EmpiLogInfo extends EmpiInfo{

    private String id;

    //操作情况  1.新增 2.更新
    private String operState;

    //上传机构编码
    private String deptCode;

    //日志记录时间
    private String createTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOperState() {
        return operState;
    }

    public void setOperState(String operState) {
        this.operState = operState;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getDeptCode() {
        return deptCode;
    }

    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode;
    }
}
