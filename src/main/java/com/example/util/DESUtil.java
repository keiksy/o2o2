package com.example.util;

import com.mchange.util.Base64Encoder;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import java.util.Base64.Encoder;
import java.util.Base64.Decoder;
import java.security.Key;
import java.security.SecureRandom;
import java.util.Base64;

public class DESUtil {
    private static Key key;
    private static String KEY_STR = "myKey";
    private static String CHARSETNAME = "UTF-8";
    private static String ALGORITHM = "DES";

    static {
        try {
            KeyGenerator generator = KeyGenerator.getInstance(ALGORITHM);
            SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
            random.setSeed(KEY_STR.getBytes());
            generator.init(random);
            key = generator.generateKey();
            generator = null;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static String getEncryptString(String str) {
        // 基于BASE64编码，接收byte[]并转换成String
        Encoder base64encoder = Base64.getEncoder();
        try {
            // 按UTF8编码
            byte[] bytes = str.getBytes(CHARSETNAME);
            // 获取加密对象
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            // 初始化密码信息
            cipher.init(Cipher.ENCRYPT_MODE, key);
            // 加密
            byte[] doFinal = cipher.doFinal(bytes);
            // byte[]to encode好的String并返回
            return base64encoder.encodeToString(doFinal);
        } catch (Exception e) {
            // TODO: handle exception
            throw new RuntimeException(e);
        }
    }

    /**
     * 获取解密之后的信息
     *
     * @param str
     * @return
     */
    public static String getDecryptString(String str) {
        // 基于BASE64编码，接收byte[]并转换成String
        Decoder base64decoder = Base64.getDecoder();
        try {
            // 将字符串decode成byte[]
            byte[] bytes = base64decoder.decode(str);
            // 获取解密对象
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            // 初始化解密信息
            cipher.init(Cipher.DECRYPT_MODE, key);
            // 解密
            byte[] doFinal = cipher.doFinal(bytes);
            // 返回解密之后的信息
            return new String(doFinal, CHARSETNAME);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        System.out.println(getEncryptString("caixy"));
    }
}
