package com.zebone.dip.ws.resource.pojo;

public enum Opt {
	 
	ADD("1"),DEL("0"),UPDATE("2"),QUERY("3");
	
	private String code;
	
	Opt(String _code){
		this.code = _code;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	
	public static boolean contains(String value){
		Opt[] opts = Opt.values();
	    for(Opt opt : opts){
	    	if(opt.getCode().equalsIgnoreCase(value)){
	    		return true;
	    	}
	    }
		return false;
	}
	
	public static Opt getOpt(String code){
		Opt[] opts = Opt.values();
	    for(Opt opt : opts){
	    	if(opt.getCode().equalsIgnoreCase(code)){
	    		return opt;
	    	}
	    }
	    return null;
	}
	
	
}
