package com.isell.core.util;

import java.util.UUID;

public class CommonUtils {
	
//	private static CodeService instance;  
//    private static DefaultConfiguration cfg;
	
	/** 
     * 二维码参数 
     */  
//    private static final String FORMAT = MimeTypes.MIME_JPEG;  
//    private static final int ORIENTATION = 0;  
//    private static final int RESOLUTION = 300;  
//    private static final String BARCODE_TYPE = "datamatrix"; 

	/**
	 * 生成随机四位数字
	 * 
	 * @return int
	 */
	public static int randomFour() {
		int index = (int) (Math.random() * 9000 + 1000);
		return index;
	}
	
	/**
     * 加密后的用户名<br>
     * name 名字
     */
    public static String getEncodeName(String name){
        if (name != null) {
            String p = name.substring(0,1);
            String s = name.substring(name.length()-1);
            name = p+"***" + s;
        }
        return name;
    }
    
    /**
     * 获取加密后的手机号
     *
     * @param mobile 手机号
     * @return 加密后的手机号
     */
    public static String getEncodeMobile(String mobile) {
        if (mobile != null) {
            if (mobile.length() != 11) {
                return mobile;
            }
            String p = mobile.substring(0,3);
            String s = mobile.substring(7);
            mobile = p + "****" + s;
        } 
        return mobile;
    }

	/**
	 * 封装JDK自带的UUID, 通过Random数字生成, 中间无-分割.
	 */
	public static String uuid() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}
	
	 /**
     * 生成二维码(QRCode)图片
     * 
     * @param content 二维码图片的内容
     * @param imgPath 生成二维码图片完整的路径
     * @param ccbpath 二维码图片中间的logo路径
     * @param size 二维码尺寸 取值范围1-40，值越大尺寸越大，可存储的信息越大
     */
    public static int createQRCode(String content, String imgPath, String ccbPath, int size) {
//        try {
//            Qrcode qrcodeHandler = new Qrcode();
//            // 设置二维码排错率，可选L(7%)、M(15%)、Q(25%)、H(30%)，排错率越高可存储的信息越少，但对二维码清晰度的要求越小
//
//            qrcodeHandler.setQrcodeErrorCorrect('M');
//            qrcodeHandler.setQrcodeEncodeMode('B');
//            // 设置设置二维码尺寸，取值范围1-40，值越大尺寸越大，可存储的信息越大
//            qrcodeHandler.setQrcodeVersion(size);
//            
//            byte[] contentBytes = content.getBytes("utf-8");
//            // 图片尺寸
//            int imgSize = 67 + 12 * (size - 1);
//            BufferedImage bufImg = new BufferedImage(imgSize, imgSize, BufferedImage.TYPE_INT_RGB);
//            Graphics2D gs = bufImg.createGraphics();
//            
//            gs.setBackground(Color.WHITE);
//            gs.clearRect(0, 0, imgSize, imgSize);
//            
//            // 设定图像颜色 > BLACK
//            gs.setColor(Color.BLACK);
//            
//            // 设置偏移量 不设置可能导致解析出错
//            int pixoff = 2;
//            // 输出内容> 二维码
//            if (contentBytes.length > 0 && contentBytes.length < 800) {
//                boolean[][] codeOut = qrcodeHandler.calQrcode(contentBytes);
//                for (int i = 0; i < codeOut.length; i++) {
//                    for (int j = 0; j < codeOut.length; j++) {
//                        if (codeOut[j][i]) {
//                            gs.fillRect(j * 3 + pixoff, i * 3 + pixoff, 3, 3);
//                        }
//                    }
//                }
//            } else {
//                throw new Exception("QRCode content bytes length = " + contentBytes.length + " not in [0, 800].");
//            }
//            gs.dispose();
//            bufImg.flush();
//            // Image img = ImageIO.read(new File(ccbPath));// 实例化一个Image对象。
//            // gs.drawImage(img, width/2, width/2, null);
//            // gs.dispose();
//            // bufImg.flush();
//            
//            // 生成二维码QRCode图片
//            File imgFile = new File(imgPath);
//            if (imgFile.exists())
//                imgFile.delete();
//            ImageIO.write(bufImg, "png", imgFile);
//            
//        } catch (Exception e) {
//            e.printStackTrace();
//            return -100;
//        }
//        
        return 0;
    }
	
	
	
}
