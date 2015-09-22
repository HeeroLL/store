/**
 * ==================   =============   ============   =================================
 * Author               OperateType     Date           Description
 * ==================   =============   ============   =================================
 * lilin                New             2013-1-7       AES算法加密解密工具类
 */
package com.zebone.btp.sso.util;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 * <pre>
 * AES算法加密解密工具类
 * 如果运用于客户端服务器需要同时导入该类进行加密解密操作（非java系统可告知密钥让其自行实现解密）
 * CRYPT_KEY为加密解密密钥，没有它破解变得异常困难（可以自行设定，长度必须是16位）
 * </pre>
 *
 * @author lilin
 * @version [版本号, 2013-1-7]
 */
public final class AesUtil
{
    /**
     * 加密算法
     */
    private static final String AES = "AES";

    /**
     * 密钥
     */
    private static final String CRYPT_KEY = "JiangSuZeboneKey";

    /**
     * 私有化构造方法避免实例化
     */
    private AesUtil()
    {

    }

    /**
     * 加密
     *
     * @param src src
     * @param key key
     * @return byte[]
     * @throws Exception Exception
     */
    private static byte[] encrypt(byte[] src, String key)
        throws Exception
    {
        Cipher cipher = Cipher.getInstance(AES);
        SecretKeySpec securekey = new SecretKeySpec(key.getBytes(), AES);
        cipher.init(Cipher.ENCRYPT_MODE, securekey);
        return cipher.doFinal(src);
    }

    /**
     * 解密
     *
     * @param src src
     * @param key key
     * @return byte[]
     * @throws Exception Exception
     */
    private static byte[] decrypt(byte[] src, String key)
        throws Exception
    {
        Cipher cipher = Cipher.getInstance(AES);
        SecretKeySpec securekey = new SecretKeySpec(key.getBytes(), AES);
        cipher.init(Cipher.DECRYPT_MODE, securekey);
        return cipher.doFinal(src);
    }

    /**
     * 二行制转十六进制字符串
     *
     * @param b b
     * @return String
     */
    private static String byte2hex(byte[] b)
    {
        String hs = "";
        String stmp = "";
        for (int n = 0; n < b.length; n++)
        {
            stmp = (java.lang.Integer.toHexString(b[n] & 0XFF));
            if (stmp.length() == 1)
            {
                hs = hs + "0" + stmp;
            }
            else
            {
                hs = hs + stmp;
            }
        }
        return hs.toUpperCase();
    }

    /**
     * 十六进制转二行制
     *
     * @param b b
     * @return String
     */
    private static byte[] hex2byte(byte[] b)
    {
        if ((b.length % 2) != 0)
        {
            throw new IllegalArgumentException("长度不是偶数");
        }
        byte[] b2 = new byte[b.length / 2];
        for (int n = 0; n < b.length; n += 2)
        {
            String item = new String(b, n, 2);
            b2[n / 2] = (byte)Integer.parseInt(item, 16);
        }
        return b2;
    }

    /**
     * 解密(供外部调用)
     *
     * @param data 解密前字符串
     * @return 解密后字符串
     */
    public final static String decrypt(String data)
    {
        try
        {
            return new String(decrypt(hex2byte(data.getBytes()), CRYPT_KEY));
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }

    /**
     * 加密(供外部调用)
     *
     * @param data 加密前字符串
     * @return 加密后字符串
     */
    public final static String encrypt(String data)
    {
        try
        {
            return byte2hex(encrypt(data.getBytes(), CRYPT_KEY));
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }

}
