package com.zebone.dnode.engine.preprocess.vo;

/**
 * 数据加工预处理对象
 *
 * @author 杨英
 * @version 2013-9-11 上午09:42
 */
public class PreprocessData {
    private String id;

    //主索引编号
    private String empiNo;

    //加工时间
    private String createTime;

    //临时加工处理xml片段
    private String tempXml;

    //数据编码 (eg. 001,002)
    private String dataCode;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmpiNo() {
        return empiNo;
    }

    public void setEmpiNo(String empiNo) {
        this.empiNo = empiNo;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getTempXml() {
        return tempXml;
    }

    public void setTempXml(String tempXml) {
        this.tempXml = tempXml;
    }

    public String getDataCode() {
        return dataCode;
    }

    public void setDataCode(String dataCode) {
        this.dataCode = dataCode;
    }
}
