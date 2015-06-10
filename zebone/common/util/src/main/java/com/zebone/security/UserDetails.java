package com.zebone.security;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.management.relation.Role;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.codehaus.jackson.map.Module;

/**
 * 登录人员信息。
 * 
 * @author 宋俊杰
 * 
 */
public class UserDetails implements Serializable {
    /**
     * 注释内容
     */
    private static final long serialVersionUID = 4098911551149660614L;

    /** 账户唯一标识 */
    private String id;
    
    /** 账户登录名 */
    private String loginName;
    
    /** 账户关联的用户id */
    private String userId;
    
    /**
     * 密码
     */
    private String password;
    
    /**
     * 员工编号
     */
    private String empid;
    
    /**
     * 姓名
     */
    private String fullname;
    
    /**
     * 姓名拼音码
     */
    private String userPym;
    
    /**
     * 姓名五笔码
     */
    private String userWbm;
    
    /**
     * 性别
     */
    private String userSex;
    
    /**
     * 职务
     */
    private String userPosition;
    
    /**
     * 岗位
     */
    private String userPost;
    
    /**
     * 电话
     */
    private String userTel;
    
    /**
     * 手机
     */
    private String userMobile;
    
    /**
     * 备注
     */
    private Object userRemark;
    
    /**
     * email
     */
    private String userEmil;
    
    /**
     * 是否停用 0 停用 1启用
     */
    private String disabled;
    
    /**
     * 删除标志 1是 0否
     */
    private String deleted;
    
    private String ip;
    
    /**
     * 排序号
     */
    private Short orderNo;
    
    /**
     * 启用时间
     */
    private String userStartTime;
    
    /**
     * 停用时间
     */
    private String userStopTime;
    
    /**
     * 机构ID
     */
    private String deptId;
    
    /**
     * 
     */
    private Short version;
    
    /**
     * 员工编号
     */
    private String userYgbh;
    
    /**
     * 是否系统用户
     */
    private String userSfxtyh;
    
    /**
     * 地址
     */
    private String userAddress;
    
    /**
     * 特长
     */
    private String userTc;
    
    /**
     * 专业
     */
    private String userZy;
    
    /**
     * 职称
     */
    private String userZc;
    
    /**
     * 最后操作人
     */
    private String zhczr;
    
    /**
     * 最后操作时间
     */
    private Date zhczsj;
    
    /**
     * 机构
     */
    private String jg;
    
    private String remark;
    
    private String modifyTime;
    
    private List<Role> roles;
    
    private List<Module> modules;
    
    /**
     * 重写toString方法
     * 
     * @return String
     */
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
    
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public String getUserId() {
        return userId;
    }
    
    public void setUserId(String userId) {
        this.userId = userId;
    }
    
    public String getLoginName() {
        return loginName;
    }
    
    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }
    
    public String getEmpid() {
        return empid;
    }
    
    public void setEmpid(String empid) {
        this.empid = empid;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getFullname() {
        return fullname;
    }
    
    public void setFullname(String fullname) {
        this.fullname = fullname;
    }
    
    public String getUserPym() {
        return userPym;
    }
    
    public void setUserPym(String userPym) {
        this.userPym = userPym;
    }
    
    public String getUserWbm() {
        return userWbm;
    }
    
    public void setUserWbm(String userWbm) {
        this.userWbm = userWbm;
    }
    
    public String getUserSex() {
        return userSex;
    }
    
    public void setUserSex(String userSex) {
        this.userSex = userSex;
    }
    
    public String getUserPosition() {
        return userPosition;
    }
    
    public void setUserPosition(String userPosition) {
        this.userPosition = userPosition;
    }
    
    public String getUserPost() {
        return userPost;
    }
    
    public void setUserPost(String userPost) {
        this.userPost = userPost;
    }
    
    public String getUserTel() {
        return userTel;
    }
    
    public void setUserTel(String userTel) {
        this.userTel = userTel;
    }
    
    public String getUserMobile() {
        return userMobile;
    }
    
    public void setUserMobile(String userMobile) {
        this.userMobile = userMobile;
    }
    
    public Object getUserRemark() {
        return userRemark;
    }
    
    public void setUserRemark(Object userRemark) {
        this.userRemark = userRemark;
    }
    
    public String getUserEmil() {
        return userEmil;
    }
    
    public void setUserEmil(String userEmil) {
        this.userEmil = userEmil;
    }
    
    public String getDisabled() {
        return disabled;
    }
    
    public void setDisabled(String disabled) {
        this.disabled = disabled;
    }
    
    public String getDeleted() {
        return deleted;
    }
    
    public void setDeleted(String deleted) {
        this.deleted = deleted;
    }
    
    public String getIp() {
        return ip;
    }
    
    public void setIp(String ip) {
        this.ip = ip;
    }
    
    public Short getOrderNo() {
        return orderNo;
    }
    
    public void setOrderNo(Short orderNo) {
        this.orderNo = orderNo;
    }
    
    public String getUserStartTime() {
        return userStartTime;
    }
    
    public void setUserStartTime(String userStartTime) {
        this.userStartTime = userStartTime;
    }
    
    public String getUserStopTime() {
        return userStopTime;
    }
    
    public void setUserStopTime(String userStopTime) {
        this.userStopTime = userStopTime;
    }
    
    public String getDeptId() {
        return deptId;
    }
    
    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }
    
    public Short getVersion() {
        return version;
    }
    
    public void setVersion(Short version) {
        this.version = version;
    }
    
    public String getUserYgbh() {
        return userYgbh;
    }
    
    public void setUserYgbh(String userYgbh) {
        this.userYgbh = userYgbh;
    }
    
    public String getUserSfxtyh() {
        return userSfxtyh;
    }
    
    public void setUserSfxtyh(String userSfxtyh) {
        this.userSfxtyh = userSfxtyh;
    }
    
    public String getUserAddress() {
        return userAddress;
    }
    
    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }
    
    public String getUserTc() {
        return userTc;
    }
    
    public void setUserTc(String userTc) {
        this.userTc = userTc;
    }
    
    public String getUserZy() {
        return userZy;
    }
    
    public void setUserZy(String userZy) {
        this.userZy = userZy;
    }
    
    public String getUserZc() {
        return userZc;
    }
    
    public void setUserZc(String userZc) {
        this.userZc = userZc;
    }
    
    public String getZhczr() {
        return zhczr;
    }
    
    public void setZhczr(String zhczr) {
        this.zhczr = zhczr;
    }
    
    public Date getZhczsj() {
        return zhczsj;
    }
    
    public void setZhczsj(Date zhczsj) {
        this.zhczsj = zhczsj;
    }
    
    public String getJg() {
        return jg;
    }
    
    public void setJg(String jg) {
        this.jg = jg;
    }
    
    public String getRemark() {
        return remark;
    }
    
    public void setRemark(String remark) {
        this.remark = remark;
    }
    
    public String getModifyTime() {
        return modifyTime;
    }
    
    public void setModifyTime(String modifyTime) {
        this.modifyTime = modifyTime;
    }
    
    public List<Module> getModules() {
        return modules;
    }
    
    public void setModules(List<Module> modules) {
        this.modules = modules;
    }
    
    public List<Role> getRoles() {
        return roles;
    }
    
    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}
