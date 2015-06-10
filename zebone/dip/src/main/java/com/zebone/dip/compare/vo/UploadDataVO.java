package com.zebone.dip.compare.vo;

/**
 * 用于传递方法中的文件地址， 成功数量，失败数量
 * @author YinCM
 * 
 */
public class UploadDataVO {
	/**
	 * 地址文件名
	 */
	private String fileName;
	/**
	 * 校验成功数量
	 */
	private int correctCount;
	/**
	 * 失败数量
	 */
	private int failCount;
	
	/**
	 * 提交命名错误
	 */
	private boolean isFileNameWrong;
	/**
	 * 成功入库数量
	 */
	private int successCount;
	
	public boolean isFileNameWrong() {
		return isFileNameWrong;
	}
	public void setFileNameWrong(boolean isFileNameWrong) {
		this.isFileNameWrong = isFileNameWrong;
	}
	public int getSuccessCount() {
		return successCount;
	}
	public void setSuccessCount(int successCount) {
		this.successCount = successCount;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public int getCorrectCount() {
		return correctCount;
	}
	public void setCorrectCount(int correctCount) {
		this.correctCount = correctCount;
	}
	public int getFailCount() {
		return failCount;
	}
	public void setFailCount(int failCount) {
		this.failCount = failCount;
	}
}
