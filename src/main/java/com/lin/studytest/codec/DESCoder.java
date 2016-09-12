package com.lin.studytest.codec;


import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;

/**
 * @author <a href="mailto:wanqi.lwq@alibaba-inc.com">wanqi.lwq</a>
 * @version 1.0.0
 * @description 对称加密算法 DESCoder
 * @since 2016/8/28
 */
public class DESCoder {

    public String ALGORITHM = "DES";//生成key方式

    /**
     * 生成秘钥
     * java6 仅仅支持56位秘钥；Bouncy castle支持64位
     * @return 二进制秘钥
     * @throws Exception
     */
    public byte[] initKey()throws Exception{
        KeyGenerator keyGenerator = KeyGenerator.getInstance(ALGORITHM);
        keyGenerator.init(56);
        //生成秘钥
        SecretKey key = keyGenerator.generateKey();
        byte [] passwords = key.getEncoded();
        return passwords;
    }

    /**
     * 还原秘钥
     * @param passwords 二进制秘钥
     * @return key 秘钥
     * @throws Exception
     */
    public Key toKey(byte[] passwords)throws Exception{
        //实例化DES秘钥材料
        DESKeySpec desKeySpec = new DESKeySpec(passwords);
        SecretKeyFactory factory = SecretKeyFactory.getInstance(ALGORITHM);
        SecretKey key = factory.generateSecret(desKeySpec);
        return key;
    }


    /**
     * 加密
     * @param content
     * @param key
     * @return
     * @throws Exception
     */
    public byte[] decrypt(byte[] content,byte[] key)throws Exception{

        Key secretKey = toKey(key);
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE,secretKey);
        return cipher.doFinal(content);
    }

    /**
     * 解密
     * @param content
     * @param key
     * @return
     * @throws Exception
     */
    public byte[] encrypt(byte[] content,byte[] key)throws Exception{

        Key secretKey = toKey(key);
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE,secretKey);
        return cipher.doFinal(content);
    }

    public Key toSecretKey(byte [] key)throws  Exception{
        SecretKey secretKey = new SecretKeySpec(key,"AES");
        return secretKey;
    }
    public static void main(String[] args) throws Exception{
        DESCoder coder = new DESCoder();
        System.out.println("初始化秘钥");
        byte [] key = coder.initKey();
        System.out.println("加密开始，明文是‘我爱你’....");
        byte [] data = coder.encrypt("我爱你".getBytes(),key);
        System.out.println("解密开始..");
        byte [] deData = coder.decrypt(data,key);
        System.out.println("解密得到的是："+new String(deData));
    }
}
