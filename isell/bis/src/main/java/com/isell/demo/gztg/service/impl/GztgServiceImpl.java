package com.isell.demo.gztg.service.impl;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.isell.core.util.JaxbUtil;
import com.isell.demo.gztg.bean.Manifest;
import com.isell.demo.gztg.bean.ResponseMessage;
import com.isell.demo.gztg.service.GztgService;
import com.isell.demo.gztg.util.AESMessageSigner;
import com.isell.demo.gztg.util.FtpUtil;
import com.isell.service.gztg.dao.GztgMapper;
import com.isell.service.gztg.vo.Gztg;
@Service("gztgService")
public class GztgServiceImpl implements GztgService{
	@Resource
	private GztgMapper gztgMapper;
	@Override
	public void getGztg(Manifest manifest) {
		  String xml = JaxbUtil.convertToXml(manifest);
		  System.out.println(xml);
		  AESMessageSigner as = new AESMessageSigner();
		  as.setKeyWord("MYgGnQE2+DAS973vd1DFHg==");
		  String exml = as.encrypt(xml);
		  String path = "f:/a/";
		  SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
			String date = sdf.format(new Date());
		  String fileName = "880020"+date+"2346";
		  Gztg g = new Gztg();
		  g.setMessageid(manifest.getHead().getMessageID());
		  g.setName(fileName);
		  g.setOrderid(manifest.getDeclaration().geteOrder().getOrderId());
		  g.setUploadtime(new Date());
		  gztgMapper.saveGztg(g);
		  writeToFile(path, exml, fileName);
	}

	@Override
	public ResponseMessage getResponse(String messageId) {
		// Gztg gztg = new Gztg();
		 Gztg gztg = gztgMapper.getByMessageId(messageId);
		 /*FtpUtil ftp = new FtpUtil();
		 ftp.downFile("210.21.48.7", 2312, "test", "test", "/DOWNLOAD/", "1_"+gztg.getName()+".txt", "f:\\a\\");*/
		 String text = readTxtFile("f:\\a\\1_"+gztg.getName()+".txt");
		 AESMessageSigner as = new AESMessageSigner();
		 as.setKeyWord("MYgGnQE2+DAS973vd1DFHg==");
		 String result = as.decrypt(text);
		 ResponseMessage rm =  JaxbUtil.converyToJavaBean(result,ResponseMessage.class);
		 return rm;
	}
	
	public static void writeToFile(String path,String content,String fileName){
		BufferedWriter writer = null;
		File file = new File(path + fileName + ".txt");
		// 如果文件不存在，则新建一个
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		// 写入
		try {
			writer = new BufferedWriter(new FileWriter(file));
			writer.write(content);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (writer != null) {
					writer.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		FtpUtil ftp = new FtpUtil();
		try {
			ftp.connect("/UPLOAD/", "210.21.48.7", 2312, "test", "test");
			ftp.upload(file);
			//ftp.downFile("210.21.48.7", 2312, "test", "test", "download", "ftp.txt", "f:");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String readTxtFile(String filePath){
		  StringBuilder sb = new StringBuilder();
        try {
            String encoding="utf-8";
            File file=new File(filePath);
            if(file.isFile() && file.exists()){ //判断文件是否存在
                InputStreamReader read = new InputStreamReader(
                new FileInputStream(file),encoding);//考虑到编码格式
                BufferedReader bufferedReader = new BufferedReader(read);
                String lineTxt = null;
                while((lineTxt = bufferedReader.readLine()) != null){
                    sb.append(lineTxt);
                }
                read.close();
               
        }else{
            System.out.println("找不到指定的文件");
        }
        } catch (Exception e) {
            System.out.println("读取文件内容出错");
            e.printStackTrace();
        }
        return sb.toString();
	 }

	
	
}
