package com.heero.redis.user.po;

/**
 * 用户信息
 * 
 * @author lilin
 */
public class UserInfo {
    /**
     * session超时时间
     */
    public static final long SESSION_TIME = 60 * 30;
    
    /**
     * 用户id
     */
    private long userId;
    
    /**
     * 用户名
     */
    private String userName;
    
    /**
     * 密码
     */
    private String password;
    
    /**
     * 昵称
     */
    private String nickname;
    
    /**
     * 性别
     */
    private String sex;
    
    /**
     * email
     */
    private String email;
    
    /**
     * sessionid
     */
    private String sessionid;
    
    @Override
    public int hashCode() {
        return (int)userId;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj){
            return true;
        }
        if (obj == null || !(obj instanceof UserInfo)){
            return false;
        }
        
        return ((UserInfo)obj).getUserId() == this.userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getSessionid() {
        return sessionid;
    }

    public void setSessionid(String sessionid) {
        this.sessionid = sessionid;
    }
    
}
