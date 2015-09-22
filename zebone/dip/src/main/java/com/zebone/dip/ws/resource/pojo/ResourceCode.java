package com.zebone.dip.ws.resource.pojo;

public enum ResourceCode {
    /** 科室资源 **/
	KSZY,
	/** 卫生人员  **/
	WSRY,
	/** 家庭档案  **/
	JTDA;
	
	
	public static boolean contains(String value){
		ResourceCode[] resourceCodes = ResourceCode.values();
	    for(ResourceCode rc : resourceCodes){
	    	if(rc.name().equalsIgnoreCase(value)){
	    		return true;
	    	}
	    }
		return false;
	}
	
}
