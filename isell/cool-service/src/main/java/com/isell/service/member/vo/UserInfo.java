package com.isell.service.member.vo;

/**
 * 用户信息
 * 
 * @author lilin
 * @version [版本号, 2015年11月12日]
 */
public class UserInfo {
    /**
     * user_id
     */
    private Integer id;
    
    /**
     * 用户编号
     */
    private String no;
    
    /**
     * 用户名
     */
    private String username;
    
    /**
     * 昵称
     */
    private String petname;
    
    /**
     * 真实姓名
     */
    private String realname;
    
    /**
     * 电子邮件
     */
    private String email;
    
    /**
     * 手机号
     */
    private String mobile;
    
    /**
     * logo
     */
    private String logo;
    
    /**
     * qq
     */
    private String qq;
    
    /**
     * 会员类型 1.普通会员
     */
    private Integer level;
    
    /**
     * 微信
     */
    private String weixin;    
    
    private Integer mId;
    
    private String shopId;
    
    private String password;
    
    private int sex;
    
    private String idcard;
    private String location_p;
    private int recid;
    private String favshopcode;
    
    /**
     * 是否显示微信号 0：不显示；1：显示
     */
    private String weixinFlag;
    
    /**
     * 是否显示qq号 0：不显示；1：显示
     */
    private String qqFlag;
    
    /**
     * 是否显示手机号 0：不显示；1：显示
     */
    private String mobileFlag;
    
    
    
    public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	 

	public String getIdcard() {
		if(idcard==null)
		{
			idcard="";
		}
		return idcard;
	}

	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}

	public String getLocation_p() {
		if(location_p==null)
		{
			location_p="";
		}
		return location_p;
	}

	public void setLocation_p(String location_p) {
		this.location_p = location_p;
	}

	public int getRecid() {
		return recid;
	}

	public void setRecid(int recid) {
		this.recid = recid;
	}

	public String getFavshopcode() {
		if(favshopcode==null)
		{
			favshopcode="";
		}
		return favshopcode;
	}

	public void setFavshopcode(String favshopcode) {
		this.favshopcode = favshopcode;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    public String getNo() {
    	if(no==null)
    	{
    		no="";
    	}
        return no;
    }
    
    public void setNo(String no) {
        this.no = no;
    }
    
    public String getUsername() {
    	if(username==null)
    	{
    		username="";
    	}
        return username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    
    public String getPetname() {
    	if(petname==null)
    	{
    		petname="";
    	}
        return petname;
    }
    
    public void setPetname(String petname) {
        this.petname = petname;
    }
    
    public String getEmail() {
    	if(email==null)
    	{
    		email="";
    	}
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getMobile() {
    	if(mobile==null)
    	{
    		mobile="";
    	}
        return mobile;
    }
    
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
    
    public String getLogo() {
    	if(logo==null)
    	{
    		logo="";
    	}
        return logo;
    }
    
    public void setLogo(String logo) {
        this.logo = logo;
    }
    
    public String getQq() {
    	if(qq==null)
    	{
    		qq="";
    	}
        return qq;
    }
    
    public void setQq(String qq) {
        this.qq = qq;
    }
    
    public Integer getLevel() {
        return level;
    }
    
    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getRealname() {
    	if(realname==null)
    	{
    		realname="";
    	}
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

	public String getShopId() {
		return shopId;
	}

	public void setShopId(String shopId) {
		this.shopId = shopId;
	}

	public String getWeixin() {
		return weixin;
	}

	public void setWeixin(String weixin) {
		this.weixin = weixin;
	}

	public Integer getmId() {
		return mId;
	}

	public void setmId(Integer mId) {
		this.mId = mId;
	}

	public String getWeixinFlag() {
		return weixinFlag;
	}

	public void setWeixinFlag(String weixinFlag) {
		this.weixinFlag = weixinFlag;
	}

	public String getQqFlag() {
		return qqFlag;
	}

	public void setQqFlag(String qqFlag) {
		this.qqFlag = qqFlag;
	}

	public String getMobileFlag() {
		return mobileFlag;
	}

	public void setMobileFlag(String mobileFlag) {
		this.mobileFlag = mobileFlag;
	}
    
}
