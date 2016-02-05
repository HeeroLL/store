package com.isell.service.product.vo;

/**
 * 商品映射表Vo
 * 
 * @author lilin
 * @version [版本号, 2016年1月14日]
 */
public class CoolProductMap {
    /**
     * 主键id
     */
    private Integer id;
    
    /**
     * 艾售商品id
     */
    private Integer pid;
    
    /**
     * 艾售规格id
     */
    private Integer gid;
    
    /**
     * 外部商品编码
     */
    private String wid;
    
    /**
     * 酷店id
     */
    private String wsid;
    
    /**
     * 艾售商品组id
     */
    private Integer groupId;
    
    /**
     * 
     */
    public Integer getId() {
        return this.id;
    }
    
    /**
     * 
     */
    public void setId(Integer id) {
        this.id = id;
    }
    
    /**
     * 
     */
    public Integer getPid() {
        return this.pid;
    }
    
    /**
     * 
     */
    public void setPid(Integer pid) {
        this.pid = pid;
    }
    
    /**
     * 
     */
    public Integer getGid() {
        return this.gid;
    }
    
    /**
     * 
     */
    public void setGid(Integer gid) {
        this.gid = gid;
    }
    
    /**
     * 
     */
    public String getWid() {
        return this.wid;
    }
    
    /**
     * 
     */
    public void setWid(String wid) {
        this.wid = wid;
    }
    
    /**
     * 
     */
    public String getWsid() {
        return this.wsid;
    }
    
    /**
     * 
     */
    public void setWsid(String wsid) {
        this.wsid = wsid;
    }
    
    public Integer getGroupId() {
        return groupId;
    }
    
    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }
}