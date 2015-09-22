package com.zebone.dnode.etl.extract.pojo;

import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

public class JdbcParameter {
	
   	@XStreamAsAttribute
	private String name;
   	
	private String url;
    
    private String userName;
    
    private String password;
   
    
   

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("JdbcParameter [name=");
		builder.append(name);
		builder.append(", url=");
		builder.append(url);
		builder.append(", userName=");
		builder.append(userName);
		builder.append(", password=");
		builder.append(password);
		builder.append("]");
		return builder.toString();
	}

     
  
}
