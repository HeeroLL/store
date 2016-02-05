package com.isell.core.util;

import java.io.File;

import org.apache.commons.lang.StringUtils;

/**
 * 文件上传工具类
 * 
 * @author lilin
 * @version [版本号, 2015年8月3日]
 */
public final class FileUploadUtil {
    
    /**
     * 上传图片
     *
     * @param uploadFile 文件
     * @param dirPath 上传的相对路径
     * @param fileName 文件名
     * @param imgMaxsize 文件最大限制
     * @param imgLocal 文件保存路径
     * @return 上传结果
     */
    public static String uploadImage(File uploadFile, String dirPath, String fileName, long  imgMaxsize, String imgLocal) {
        if (uploadFile == null || uploadFile.isDirectory()) {
            return "文件不存在";
        }
        
        // 检查文件大小
        if (uploadFile.length() > imgMaxsize) {
            return "上传文件超出限制";
        }
        
        int index = uploadFile.getName().lastIndexOf('.');
        if (index == -1) {
            return "文件格式不正确";
        }
        // 校验文件格式
        String suffix = uploadFile.getName().substring(index);
        if (!suffix.toLowerCase().matches("\\.(gif|jpe?g|png)")) {
            return "文件格式不正确";
        }
        
        if (StringUtils.isEmpty(dirPath)) {
            dirPath = "/temp/";
        } else {
            if (!dirPath.startsWith("/")) {
                dirPath = '/' + dirPath;
            }
            if (!dirPath.endsWith("/")) {
                dirPath += '/';
            }
        }
        
        File dirFile = new File(imgLocal + dirPath);
        
        // 新建目录
        dirFile.mkdirs();
        // 文件名
        if (StringUtils.isEmpty(fileName)) {
            fileName = System.currentTimeMillis() + suffix;
        }
        
        // 上传文件
        File newFile = new File(imgLocal + dirPath + fileName);
        if (newFile.exists()) {
            newFile.delete();
        }
        uploadFile.renameTo(newFile);
        
        // 删除临时文件
        if (uploadFile.exists()) {
            uploadFile.delete();
        }
        
        return "true";
    }
    
    /**
     * 上传excel
     *
     * @param uploadFile 文件
     * @param dirPath 上传的相对路径
     * @param fileName 文件名
     * @param imgMaxsize 文件最大限制
     * @param imgLocal 文件保存路
     * @return 上传结果
     */
    public static String uploadExcel(File uploadFile, String dirPath, String fileName, long  imgMaxsize, String imgLocal) {
        if (uploadFile == null || uploadFile.isDirectory()) {
            return "文件不存在";
        }
        
        // 检查文件大小
        if (uploadFile.length() > imgMaxsize) {
            return "上传文件超出限制";
        }
        
        int index = uploadFile.getName().lastIndexOf('.');
        if (index == -1) {
            return "文件格式不正确";
        }
        // 校验文件格式
        String suffix = uploadFile.getName().substring(index);
        if (!suffix.toLowerCase().matches("\\.(xls|xlsx)")) {
            return "文件格式不正确";
        }
        
        if (StringUtils.isEmpty(dirPath)) {
            dirPath = "/temp/";
        } else {
            if (!dirPath.startsWith("/")) {
                dirPath = '/' + dirPath;
            }
            if (!dirPath.endsWith("/")) {
                dirPath += '/';
            }
        }
        
        File dirFile = new File(imgLocal + dirPath);
        
        // 新建目录
        dirFile.mkdirs();
        // 文件名
        if (StringUtils.isEmpty(fileName)) {
            fileName = System.currentTimeMillis() + suffix;
        }
        
        // 上传文件
        File newFile = new File(imgLocal + dirPath + fileName);
        if (newFile.exists()) {
            newFile.delete();
        }
        uploadFile.renameTo(newFile);
        
        // 删除临时文件
        if (uploadFile.exists()) {
            uploadFile.delete();
        }
        
        return "true";
    }
}
