package com.zebone.dnode.etl.check.pojo;

/**
 * 源数据对象
 *
 * @author 杨英
 * @version 2014-02-15 上午09:47
 */
public class SourceDataObj {

    //源数据表的主键信息
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
