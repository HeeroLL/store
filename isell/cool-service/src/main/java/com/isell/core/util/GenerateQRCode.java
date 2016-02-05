package com.isell.core.util;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import org.apache.log4j.Logger;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;

/**
 *生成二维码
 */
public class GenerateQRCode {
	
	private static final Logger log = Logger.getLogger(GenerateQRCode.class);
	
	private static final GenerateQRCode instance = new GenerateQRCode();
	
	private GenerateQRCode() {
		
    }
	
	public static GenerateQRCode getInstance() {  
        return instance;  
    }
	
	private static final int BLACK = 0xff000000;  
    private static final int WHITE = 0xFFFFFFFF; 
    
    /** 
     * @Description: 生成二维码 
     * 
     * @param assetsName  二维码图片名称            
     * @param params 二维码信息             
     * @param path 二维码图片存放目录  
     * @param width  生成的图片的宽             
     * @param height 生成的图片的高
     * @throws Exception 
     * 
     * @return String 二维码图片名称 
     */  
    public static String generate(String assetsName, String params, String path,  int width, int height) throws Exception {  
  
        log.info("GenerateQRCode-->start to generate qrcode.");  
        log.info("the qrcode's save path is:" + path);  
  
        File file = new File(path);  
        if (!file.exists()) {  
            file.mkdirs();  
        }
        
        // 二维码图片名称  
        //String fileName = assetsName.concat("_").concat(DateUtils.getStringDate1()).concat(".png"); 
        String fileName = assetsName;        
        // 二维码图片存放路径  
        path = path.concat(fileName);
        
        log.info("the qrcode's path is:" + path);  
        
        file = new File(path);
        
        if (!file.exists()) {  
            file.createNewFile();  
        }
        
        BitMatrix bitMatrix = new MultiFormatWriter().encode(params, BarcodeFormat.QR_CODE, width, height);         
        
        writeToFile(bitMatrix, "png", file);
        
        log.info("GenerateQRCode-->end to generate qrcode."); 
        
  
        return fileName;  
    }  
    
    public static void writeToFile(BitMatrix matrix, String format, File file)  throws Exception {  
        BufferedImage image = toBufferedImage(matrix);  
        ImageIO.write(image, format, file);  
    } 
    
    public static BufferedImage toBufferedImage(BitMatrix matrix) {      	  
        int width = matrix.getWidth();  
        int height = matrix.getHeight();
        BufferedImage image = new BufferedImage(width, height,BufferedImage.TYPE_INT_ARGB);  
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                image.setRGB(x, y, matrix.get(x, y) == true ? BLACK : WHITE);
            }  
        }  
        return image;
    }  

}
