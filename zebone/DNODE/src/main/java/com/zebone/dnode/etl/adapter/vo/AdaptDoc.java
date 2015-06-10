package com.zebone.dnode.etl.adapter.vo;

public class AdaptDoc {
    /**标识*/
    private String id;
    /**前置节点名称*/
    private String nodeName;
    /**文档状态（0未上传 1 已上传 2 重新上传）*/
    private String docFlag;
    /**操作状态（ 删除 0  新增 1 更新 2 ）*/
    private String operFlag;
    /**文档上传成功标志（0 失败 1 成功）*/
    private String uploadFlag;
    /**文档上传失败描述*/
    private String uploadDesc;
    /**创建时间*/
    private String createTime;
    /**文档存储*/
    private String docXml;

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }
    public String getNodeName() {
        return nodeName;
    }
    public void setNodeName(String nodeName) {
        this.nodeName = nodeName == null ? null : nodeName.trim();
    }
    public String getDocFlag() {
        return docFlag;
    }
    public void setDocFlag(String docFlag) {
        this.docFlag = docFlag == null ? null : docFlag.trim();
    }
    public String getOperFlag() {
        return operFlag;
    }
    public void setOperFlag(String operFlag) {
        this.operFlag = operFlag == null ? null : operFlag.trim();
    }
    public String getUploadFlag() {
        return uploadFlag;
    }
    public void setUploadFlag(String uploadFlag) {
        this.uploadFlag = uploadFlag == null ? null : uploadFlag.trim();
    }
    public String getUploadDesc() {
        return uploadDesc;
    }
    public void setUploadDesc(String uploadDesc) {
        this.uploadDesc = uploadDesc == null ? null : uploadDesc.trim();
    }
    public String getCreateTime() {
        return createTime;
    }
    public void setCreateTime(String createTime) {
        this.createTime = createTime == null ? null : createTime.trim();
    }
    public String getDocXml() {
        return docXml;
    }
    public void setDocXml(String docXml) {
        this.docXml = docXml == null ? null : docXml.trim();
    }
}