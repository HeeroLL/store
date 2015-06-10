package com.zebone.dip.compare.vo;

import org.springframework.web.multipart.MultipartFile;  

public class FileUpload {  
  
 private MultipartFile file;  
 
 private String fileType;
  
 private String tableName;
 
 
public String getTableName() {
	return tableName;
}

public void setTableName(String tableName) {
	this.tableName = tableName;
}

public String getFileType() {
	return fileType;
}

public void setFileType(String fileType) {
	this.fileType = fileType;
}

public MultipartFile getFile() {  
  return file;  
 }  
  
 public void setFile(MultipartFile file) {  
  this.file = file;  
 }  
} 