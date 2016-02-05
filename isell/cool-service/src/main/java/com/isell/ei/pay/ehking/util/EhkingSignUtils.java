package com.isell.ei.pay.ehking.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import org.apache.commons.codec.binary.Hex;

/**
 * 易汇金算hmac工具类
 * 
 * @author lilin
 * @version [版本号, 2015年12月14日]
 */
public class EhkingSignUtils {
    /**
     * 计算hmac
     *
     * @param source 源
     * @param key key
     * @return 加密后字符串
     */
    public static String signMd5(String source, String key) {
        byte k_ipad[] = new byte[64];
        byte k_opad[] = new byte[64];
        byte keyb[];
        byte value[];
        try {
            keyb = key.getBytes("UTF-8");
            value = source.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            keyb = key.getBytes();
            value = source.getBytes();
        }
        Arrays.fill(k_ipad, keyb.length, 64, (byte)54);
        Arrays.fill(k_opad, keyb.length, 64, (byte)92);
        for (int i = 0; i < keyb.length; i++) {
            k_ipad[i] = (byte)(keyb[i] ^ 54);
            k_opad[i] = (byte)(keyb[i] ^ 92);
        }
        
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
        md.update(k_ipad);
        md.update(value);
        byte dg[] = md.digest();
        md.reset();
        md.update(k_opad);
        md.update(dg, 0, 16);
        dg = md.digest();
        return Hex.encodeHexString(dg);
    }
}
