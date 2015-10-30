package com.isell.core.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

import org.krysalis.barcode4j.impl.code39.Code39Bean;
import org.krysalis.barcode4j.output.bitmap.BitmapCanvasProvider;
import org.krysalis.barcode4j.tools.UnitConv;

/**
 * 生成条形码工具类
 * 
 * @author wangweihua
 * @version [版本号, 2015年9月21日]
 */
public class GetBarCode {
    /**
     * 
     * 
     * @param path 生成路径
     * @param content 条形码内容
     * @return
     */
    public static void getBarCode(String path, String fileName, String content) {
        try {
            // Create the barcode bean
            Code39Bean bean = new Code39Bean();
            // 设置条形码的分辨率，越高越好
            final int dpi = 300;
            // Configure the barcode generator
            bean.setModuleWidth(UnitConv.in2mm(1.0f / dpi)); // makes the narrow bar
            // 设置一维码的宽高
            /* bean.setWideFactor(5); */
            bean.setHeight(6);
            bean.setFontSize(1.0);
            // 条形码两端是否留白
            bean.doQuietZone(true);
            File dirFile = new File(path);
            dirFile.mkdirs();
            // File outputFile = new File("C:\\Users\\Administrator\\Desktop\\out.png");
            File outputFile = new File(path + fileName);
            OutputStream out = new FileOutputStream(outputFile);
            try {
                // Set up the canvas provider for monochrome JPEG output
                BitmapCanvasProvider canvas =
                    new BitmapCanvasProvider(out, "image/png", dpi, BufferedImage.TYPE_BYTE_BINARY, false, 0);
                // Generate the barcode
                bean.generateBarcode(canvas, content);
                // Signal end of generation
                canvas.finish();
            } finally {
                out.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
}
