package com.isell.demo.gztg.util;

import java.security.Security;


import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

public class AESMessageSigner {

	private String keyWord;
	
	private static String DEFAULT_CHARSET = "UTF-8";

	static {
		Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
	}
	
	public String encrypt(String content) {
		try {
			SecretKeySpec key = new SecretKeySpec(Base64.decodeBase64(keyWord), "AES");
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS7Padding", "BC");// 创建密码器
			byte[] byteContent = content.getBytes(DEFAULT_CHARSET);
			cipher.init(Cipher.ENCRYPT_MODE, key);// 初始化
			byte[] result = cipher.doFinal(byteContent);
			byte[] encode = Base64.encodeBase64(result);
			return new String(encode, DEFAULT_CHARSET); // 加密
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("encrypt error.", e);
		}
	}

	public String decrypt(String content) {
		try {
			SecretKey key = new SecretKeySpec(Base64.decodeBase64(keyWord), "AES");
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS7Padding", "BC");// 创建密码器
			cipher.init(Cipher.DECRYPT_MODE, key);// 初始化
			byte[] result = cipher.doFinal(Base64.decodeBase64(content));
			return new String(result, DEFAULT_CHARSET); // 解密
		} catch (Exception e) {
			throw new RuntimeException("decrypt error.", e);
		}
	}

	public String encrypt(byte[] content) {
		try {
			SecretKey key = new SecretKeySpec(Base64.decodeBase64(keyWord), "AES");
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS7Padding", "BC");// 创建密码器
			cipher.init(Cipher.ENCRYPT_MODE, key);// 初始化
			byte[] result = cipher.doFinal(content);
			byte[] encode = Base64.encodeBase64(result);
			return new String(encode, DEFAULT_CHARSET); // 加密
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("encrypt error.", e);
		}
	}

	public String getKeyWord() {
		return keyWord;
	}

	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}
	
}

