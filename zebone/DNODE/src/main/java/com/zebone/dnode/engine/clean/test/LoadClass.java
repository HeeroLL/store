package com.zebone.dnode.engine.clean.test;

import org.apache.commons.lang3.ClassUtils;

public class LoadClass {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
       try {
		ClassUtils.getClass("com.zebone.dnode.engine.clean.EmailCheck");
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}

}
