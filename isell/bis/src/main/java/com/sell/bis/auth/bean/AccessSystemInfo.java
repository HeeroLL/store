package com.sell.bis.auth.bean;

/**
 * 接入系统信息
 * 
 * @author lilin
 * @version [版本号, 2015年7月22日]
 */
public class AccessSystemInfo {
    /**
     * 主键id
     */
    private int id;
    
    /**
     * 接入系统名称
     */
    private String name;
    
    /**
     * 接入编码（由BIS统一分配给客户）
     */
    private String accessCode;
    
    /**
     * 系统私钥（由BIS统一分配给客户）
     */
    private String privateKey;
    
    /**
     * 接入系统外网ip
     */
    private String ip;
    
    /**
     * 接入系统域名
     */
    private String host;
    
    /**
     * 是否冻结
     */
    private boolean frozen;
    
    public AccessSystemInfo() {
        
    }
    
    public AccessSystemInfo(String accessCode, String privateKey, boolean frozen) {
        this.accessCode = accessCode;
        this.privateKey = privateKey;
        this.frozen = frozen;
    }
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getAccessCode() {
        return accessCode;
    }
    
    public void setAccessCode(String accessCode) {
        this.accessCode = accessCode;
    }
    
    public String getIp() {
        return ip;
    }
    
    public void setIp(String ip) {
        this.ip = ip;
    }
    
    public String getHost() {
        return host;
    }
    
    public void setHost(String host) {
        this.host = host;
    }
    
    public boolean isFrozen() {
        return frozen;
    }
    
    public void setFrozen(boolean frozen) {
        this.frozen = frozen;
    }
    
    public String getPrivateKey() {
        return privateKey;
    }
    
    public void setPrivateKey(String privateKey) {
        this.privateKey = privateKey;
    }
}
