package com.zebone.register.validation.domain;
/**
 *
 * @author 
 */
public class PSysregister{
    /**
     * ID
     */
    private String id;
    /**
     * 系统编码（即唯一授权码）
     */
    private String systemcode;
    /**
     * 系统名称
     */
    private String systemname;
    /**
     * 所属机构代码
     */
    private String organcode;
    /**
     * 是否停用：1是，0否
     */
    private String disabled;

    /**
     * ID
     */
    public String getId(){
        return this.id;
    }

    /**
     * ID
     */
    public void setId(String id){
        this.id = id;
    }    
    /**
     * 系统编码（即唯一授权码）
     */
    public String getSystemcode(){
        return this.systemcode;
    }

    /**
     * 系统编码（即唯一授权码）
     */
    public void setSystemcode(String systemcode){
        this.systemcode = systemcode;
    }    
    /**
     * 系统名称
     */
    public String getSystemname(){
        return this.systemname;
    }

    /**
     * 系统名称
     */
    public void setSystemname(String systemname){
        this.systemname = systemname;
    }    
    /**
     * 所属机构代码
     */
    public String getOrgancode(){
        return this.organcode;
    }

    /**
     * 所属机构代码
     */
    public void setOrgancode(String organcode){
        this.organcode = organcode;
    }    
    /**
     * 是否停用：1是，0否
     */
    public String getDisabled(){
        return this.disabled;
    }

    /**
     * 是否停用：1是，0否
     */
    public void setDisabled(String disabled){
        this.disabled = disabled;
    }    
}