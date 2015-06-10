package com.zebone.util;

import java.security.SecureRandom;
import java.util.UUID;

/**
 * 生成UUID的工具类
 * 
 * @author songjunjie
 * @version 2013-8-16 上午08:24:03
 */
public class UUIDUtil {
    
    private static SecureRandom random = new SecureRandom();
    
    /**
     * 返回UUID(32位)
     * 
     * @return String
     */
    public synchronized static String getUuid() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString().replaceAll("-", "");
    }
    
    /**
     * 使用SecureRandom随机生成Long.
     */
    public static long randomLong() {
        return Math.abs(random.nextLong());
    }
    
    /**
     * 基于Base62编码的SecureRandom随机生成bytes.
     */
    public static String randomBase62(int length) {
        byte[] randomBytes = new byte[length];
        random.nextBytes(randomBytes);
        return Encodes.encodeBase62(randomBytes);
    }
    
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            System.out.println(randomLong());
        }
    }
    
}
