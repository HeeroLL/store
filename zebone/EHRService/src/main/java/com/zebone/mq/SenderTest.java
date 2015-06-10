package com.zebone.mq;

import java.util.Properties;

public class SenderTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Properties pro = System.getProperties();
		int count = 1;
		for (int i = 1; i <= count; i++) {
			String str = i + "_[��������ǻ۳�����Ϣϵͳ���޹�˾]����"
					+ System.currentTimeMillis();

//			Sender.send(str);
		}

	}

}
