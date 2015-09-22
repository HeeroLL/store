package com.zebone.dip.ws.resource.pojo;

public enum ResourceResult {
	
	 SUCCEED("0"),FAIL("1");
	 
	 private String code;
	 
	 ResourceResult(String _code){
		 this.code = _code;
	 }

	public String getCode() {
		return code;
	}
	 
}
