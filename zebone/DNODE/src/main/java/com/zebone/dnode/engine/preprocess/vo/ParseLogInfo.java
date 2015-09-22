package com.zebone.dnode.engine.preprocess.vo;

/**
 * 业务文档变化日志对象
 *
 * @author 杨英
 * @version 2013-9-11 上午08:30
 */
public class ParseLogInfo {
    private String id;

    //创建时间
    private String createTime;

    //解析的表名
    private String tableName;

    //主索引编号
    private String empiNo;

    //文档编号
    private String docNo;

    //数据加工标志位， 1已加工，0未加工
    private String dataFlag;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getEmpiNo() {
        return empiNo;
    }

    public void setEmpiNo(String empiNo) {
        this.empiNo = empiNo;
    }

    public String getDocNo() {
        return docNo;
    }

    public void setDocNo(String docNo) {
        this.docNo = docNo;
    }

    public String getDataFlag() {
        return dataFlag;
    }

    public void setDataFlag(String dataFlag) {
        this.dataFlag = dataFlag;
    }
}
