package com.zebone.dnode.etl.convert.pojo;

/**
 * 数据清洗转换日志对象
 *
 * @author 杨英
 * @version 2014-02-18 下午02:05
 */
public class ConvertLogObj {

    private String id;
    //前置节点名称
    private String nodeName;
    //表名
    private String tableName;
    //列名
    private String columnName;
    //清洗目标表名
    private String cleanTableName;
    //清洗目标列名
    private String cleanColumnName;
    //清洗前值
    private String columnValue;
    //清洗后值
    private String cleanColumnValue;
    //转换规则编号
    private String cleanRuleNo;
    //转换类型
    private String cleanType;
    //转换成功标志
    private String cleanFlag;
    //转换错误描述
    private String cleanErrorDesc;
    //清洗表主键
    private String mainId;
    //创建时间
    private String createTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNodeName() {
        return nodeName;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getCleanTableName() {
        return cleanTableName;
    }

    public void setCleanTableName(String cleanTableName) {
        this.cleanTableName = cleanTableName;
    }

    public String getCleanColumnName() {
        return cleanColumnName;
    }

    public void setCleanColumnName(String cleanColumnName) {
        this.cleanColumnName = cleanColumnName;
    }

    public String getColumnValue() {
        return columnValue;
    }

    public void setColumnValue(String columnValue) {
        this.columnValue = columnValue;
    }

    public String getCleanColumnValue() {
        return cleanColumnValue;
    }

    public void setCleanColumnValue(String cleanColumnValue) {
        this.cleanColumnValue = cleanColumnValue;
    }

    public String getCleanRuleNo() {
        return cleanRuleNo;
    }

    public void setCleanRuleNo(String cleanRuleNo) {
        this.cleanRuleNo = cleanRuleNo;
    }

    public String getCleanType() {
        return cleanType;
    }

    public void setCleanType(String cleanType) {
        this.cleanType = cleanType;
    }

    public String getCleanFlag() {
        return cleanFlag;
    }

    public void setCleanFlag(String cleanFlag) {
        this.cleanFlag = cleanFlag;
    }

    public String getCleanErrorDesc() {
        return cleanErrorDesc;
    }

    public void setCleanErrorDesc(String cleanErrorDesc) {
        this.cleanErrorDesc = cleanErrorDesc;
    }

    public String getMainId() {
        return mainId;
    }

    public void setMainId(String mainId) {
        this.mainId = mainId;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
