package com.zebone.dnode.engine.clean;

/**
 * 对char等进行截断的转换器
 * @author cz
 *
 */
public class SConvert implements DataConvert {
	
	public int length;

	public SConvert(int length) {
		this.length = length;
	}

	@Override
	public Object convert(Object value){
		// TODO Auto-generated method stub
		if(value == null){
			return null;
		}else{
			String val = value.toString();
			if(val.length() > length){
				return val.substring(0, length);
			}
		}
		return "";
	}

	public void setLength(int length) {
		this.length = length;
	}

}
