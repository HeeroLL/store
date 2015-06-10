package com.zebone.docSub.vo;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 文档订阅参数对象
 *
 * @author 杨英
 * @version 2014-8-12 下午1:25
 */
@XStreamAlias("request")
public class DocSubRequest {
    //文档订阅机构
    private String subOrg;
    //文档上传机构  可有多个机构,机构间以逗号隔开
    private String docOrg;
    //文档类型  可有多种文档类型,中间以逗号隔开
    private String docType;
    //卫生服务活动开始时间
    private String startDt;
    //活动截止时间
    private String endDt;

    public String getSubOrg() {
        return subOrg;
    }

    public void setSubOrg(String subOrg) {
        this.subOrg = subOrg;
    }

    public String getDocOrg() {
        return docOrg;
    }

    public void setDocOrg(String docOrg) {
        this.docOrg = docOrg;
    }

    public String getDocType() {
        return docType;
    }

    public void setDocType(String docType) {
        this.docType = docType;
    }

    public String getStartDt() {
        return startDt;
    }

    public void setStartDt(String startDt) {
        this.startDt = startDt;
    }

    public String getEndDt() {
        return endDt;
    }

    public void setEndDt(String endDt) {
        this.endDt = endDt;
    }
}
