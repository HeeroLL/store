package com.zebone.dnode.etl.convert.pojo;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

import java.util.List;

/**
 * 表配置对象
 *
 * @author 杨英
 * @version 2014-02-18 上午09:05
 */
public class TableParameter {

    @XStreamAlias("name")
    @XStreamAsAttribute
    private String tableName;

    @XStreamImplicit(itemFieldName="column")
    private List<ColumnParameter> columnParameter;

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public List<ColumnParameter> getColumnParameter() {
        return columnParameter;
    }

    public void setColumnParameter(List<ColumnParameter> columnParameter) {
        this.columnParameter = columnParameter;
    }
}
