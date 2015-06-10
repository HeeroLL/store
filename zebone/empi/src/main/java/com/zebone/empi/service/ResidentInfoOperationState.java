package com.zebone.empi.service;

/**
 * ResidentInfoLog 的状态
 * @author YinCM
 * @since
 */
public enum ResidentInfoOperationState {
	Add("1"),	Update("2"),	Delete("3");
	ResidentInfoOperationState(String abbreviation){
		this.abbreviation = abbreviation;
	}
	public String getAbbreviation(){
		return this.abbreviation;
	}
	private String abbreviation;
}
