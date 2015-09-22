package com.zebone.dip.md.vo;

import java.util.ArrayList;
import java.util.List;

/**
 * 主数据查询实体对象
 * @author Yang Ying
 * @version 2013-08-01  14:31
 */
public class MasterDataQuery {

    //表名
    private String dbName;

    //表字段
    private String dbFields;

    //逗号分隔id的字符串
    private String id;

    //主键ID集合
    private List<String> idList;

    //主键字段
    private String primaryKeyField;

    //主键值
    private String primaryKeyValue;

    //字段值以~分隔
    private String fieldValue;

    //字段值
    private List<String> valueList;

    //查询字段 暂时只支持主数据名
    private String mdName;

    //查询字段值
    private String mdNameValue;

    private String mdCodeValue;

    //字段是否显示
    private String dbVisible;

    //字段对应的属性值
    private String corres;

    //字段对应的类型值
    private String fieldType;

    /**
     * 赋值 ID
     * @param id
     */
    public void addId(String id) {
        if (idList == null) {
            this.idList = new ArrayList<String>();
        }
        idList.add(id);
    }

    /**
     * 赋值 Value
     *
     * @param value
     */
    public void addValue(String value) {
        if (valueList == null) {
            this.valueList = new ArrayList<String>();
        }
        valueList.add(value);
    }

    public String getDbName() {
        return dbName;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }

    public String getDbFields() {
        return dbFields;
    }

    public void setDbFields(String dbFields) {
        this.dbFields = dbFields;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<String> getIdList() {
        return idList;
    }

    public void setIdList(List<String> idList) {
        this.idList = idList;
    }

    public String getPrimaryKeyField() {
        return primaryKeyField;
    }

    public void setPrimaryKeyField(String primaryKeyField) {
        this.primaryKeyField = primaryKeyField;
    }

    public String getPrimaryKeyValue() {
        return primaryKeyValue;
    }

    public void setPrimaryKeyValue(String primaryKeyValue) {
        this.primaryKeyValue = primaryKeyValue;
    }

    public String getFieldValue() {
        return fieldValue;
    }

    public void setFieldValue(String fieldValue) {
        this.fieldValue = fieldValue;
    }

    public List<String> getValueList() {
        return valueList;
    }

    public void setValueList(List<String> valueList) {
        this.valueList = valueList;
    }

    public String getMdName() {
        return mdName;
    }

    public void setMdName(String mdName) {
        this.mdName = mdName;
    }

    public String getMdNameValue() {
        return mdNameValue;
    }

    public void setMdNameValue(String mdNameValue) {
        this.mdNameValue = mdNameValue;
    }

    public String getMdCodeValue() {
        return mdCodeValue;
    }

    public void setMdCodeValue(String mdCodeValue) {
        this.mdCodeValue = mdCodeValue;
    }

    public String getDbVisible() {
        return dbVisible;
    }

    public void setDbVisible(String dbVisible) {
        this.dbVisible = dbVisible;
    }

    public String getCorres() {
        return corres;
    }

    public void setCorres(String corres) {
        this.corres = corres;
    }

    public String getFieldType() {
        return fieldType;
    }

    public void setFieldType(String fieldType) {
        this.fieldType = fieldType;
    }

    /**
     * toString
     */
    public String toString() {
        final String tab = "";
        String retValue = "";
        retValue = "MasterDataQuery ( " + super.toString() + tab + "dbName = "
                + this.dbName + tab + "dbFields = " + this.dbFields + tab
                + "fieldType = "+this.fieldType + tab
                + "id = " + this.id + tab + "idList = " + this.idList + tab
                + "primaryKeyField = " + this.primaryKeyField + tab
                + "primaryKeyValue = " + this.primaryKeyValue + tab
                + "fieldValue = " + this.fieldValue + tab + "valueList = "
                + this.valueList + tab + "mdName = " + this.mdName + tab
                + "mdNameValue = " + this.mdNameValue + tab
                + "mdCodeValue = " + this.mdCodeValue + tab + "dbVisible = "
                + this.dbVisible + tab + "corres = " + this.corres + tab + " )";
        return retValue;
    }
}
