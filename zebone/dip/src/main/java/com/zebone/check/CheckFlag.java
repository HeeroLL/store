package com.zebone.check;

public enum CheckFlag {
	
	 SUCCEED("0"),FAIL("1");
	 
	 private String code;
	 
	 CheckFlag(String _code){
		 this.code = _code;
	 }

	public String getCode() {
		return code;
	}
	 
}
