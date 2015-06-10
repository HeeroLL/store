package com.zebone.dnode.etl.convert.pojo;

/**
 * 清洗转换数据对象
 *
 * @author 杨英
 * @version 2014-02-18 下午01:58
 */
public class ConvertDataObj {

    private String id;

    //字段数据
    private String columnData;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getColumnData() {
        return columnData;
    }

    public void setColumnData(String columnData) {
        this.columnData = columnData;
    }
}
