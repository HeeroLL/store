package com.isell.demo.gztg.util;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;


public class FtpUtil {
	 private  FTPClient ftp;      
	    /**  
	     *   
	     * @param path 上传到ftp服务器哪个路径下     
	     * @param addr 地址  
	     * @param port 端口号  
	     * @param username 用户名  
	     * @param password 密码  
	     * @return  
	     * @throws Exception  
	     */    
	    public  boolean connect(String path,String addr,int port,String username,String password) throws Exception {      
	        boolean result = false;      
	        ftp = new FTPClient();      
	        int reply;      
	        ftp.connect(addr,port);      
	        ftp.login(username,password);      
	        ftp.setFileType(FTPClient.BINARY_FILE_TYPE);      
	        reply = ftp.getReplyCode();      
	        if (!FTPReply.isPositiveCompletion(reply)) {      
	            ftp.disconnect();      
	            return result;      
	        }      
	        ftp.changeWorkingDirectory(path);      
	        result = true;      
	        return result;      
	    }      
	    /**  
	     *   
	     * @param file 上传的文件或文件夹  
	     * @throws Exception  
	     */    
	    public void upload(File file) throws Exception{      
	        if(file.isDirectory()){           
	            ftp.makeDirectory(file.getName());                
	            ftp.changeWorkingDirectory(file.getName());      
	            String[] files = file.list();             
	            for (int i = 0; i < files.length; i++) {      
	                File file1 = new File(file.getPath()+"\\"+files[i] );      
	                if(file1.isDirectory()){      
	                    upload(file1);      
	                    ftp.changeToParentDirectory();      
	                }else{                    
	                    File file2 = new File(file.getPath()+"\\"+files[i]);      
	                    FileInputStream input = new FileInputStream(file2);      
	                    ftp.storeFile(file2.getName(), input);      
	                    input.close();                            
	                }                 
	            }      
	        }else{      
	            File file2 = new File(file.getPath());      
	            FileInputStream input = new FileInputStream(file2);      
	            ftp.storeFile(file2.getName(), input);      
	            input.close();        
	        }      
	    }   
	    public static boolean downFile( 
	            String url, //FTP服务器hostname 
	            int port,//FTP服务器端口 
	            String username, //FTP登录账号 
	            String password, //FTP登录密码 
	            String remotePath,//FTP服务器上的相对路径  
	            String fileName,//要下载的文件名 
	            String localPath//下载后保存到本地的路径 
	            ) {   
	        boolean success = false;   
	        FTPClient ftp = new FTPClient();   
	        try {   
	            int reply;   
	            ftp.connect(url, port);   
	            //如果采用默认端口，可以使用ftp.connect(url)的方式直接连接FTP服务器    
	            ftp.login(username, password);//登录    
	            reply = ftp.getReplyCode();   
	            if (!FTPReply.isPositiveCompletion(reply)) {   
	                ftp.disconnect();   
	                return success;   
	            }   
	            ftp.changeWorkingDirectory(remotePath);//转移到FTP服务器目录    
	            FTPFile[] fs = ftp.listFiles();   
	            for(FTPFile ff:fs){   
	                if(ff.getName().equals(fileName)){   
	                    File localFile = new File(localPath+"/"+ff.getName());   
	                    OutputStream is = new FileOutputStream(localFile);    
	                    ftp.retrieveFile(ff.getName(), is);   
	                    is.close();   
	                }   
	            }   
	                 
	            ftp.logout();   
	            success = true;   
	        } catch (IOException e) {   
	            e.printStackTrace();   
	        } finally {   
	            if (ftp.isConnected()) {   
	                try {   
	                    ftp.disconnect();   
	                } catch (IOException ioe) {   
	                }   
	            }   
	        }   
	        return success;   
	    }
	    
	    
	    public static void main(String args[]){
	    	FtpUtil ftp = new FtpUtil();
	    	try {
				ftp.connect("upload", "210.21.48.7", 2312, "test", "test");
				File file2 = new File("f:\\a\\ftp.txt");
				ftp.upload(file2);
			} catch (Exception e) {
				e.printStackTrace();
			}
	    	//downFile("210.21.48.7", 2312, "test", "test", "download", "ftp.txt", "f:");
	    }
}
