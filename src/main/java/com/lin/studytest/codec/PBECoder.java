package com.lin.studytest.codec;

import org.apache.commons.codec.binary.Hex;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;
import java.security.Key;
import java.security.SecureRandom;

/**
 * @author <a href="mailto:wanqi.lwq@alibaba-inc.com">wanqi.lwq</a>
 * @version 1.0.0
 * @description PBE 对称加密算法
 *
 * @since 2016/8/29
 */
public class PBECoder {

    //算法
    public static final String ALGORITHM = "PBEWITHMD5andDES";
    /**
     * 迭代次数
     */
    public static final int ITERATION_COUNT = 100;


    /**
     * 初始化盐
     * @return
     * @throws Exception
     */
    public static byte[] initSalt()throws Exception{
        SecureRandom random = new SecureRandom();
        return random.generateSeed(8);
    }

    /**
     * 转换秘钥
     * @param password 口令
     * @return 秘钥
     * @throws Exception
     */
    private static Key toKey(String password)throws Exception{
        //秘钥转换材料
        PBEKeySpec pbeKeySpec = new PBEKeySpec(password.toCharArray());
        //实例化
        SecretKeyFactory factory = SecretKeyFactory.getInstance(ALGORITHM);
        //生成秘钥
        return factory.generateSecret(pbeKeySpec);
    }

    /**
     * 加密
     * @param data
     * @param password
     * @param salt
     * @return
     * @throws Exception
     */
    public static byte[] encrypt(byte[] data,String password,byte[]salt)throws Exception{
        Key key = toKey(password);
        //实例化PBE参数材料
        PBEParameterSpec pbeParameterSpec = new PBEParameterSpec(salt,ITERATION_COUNT);
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE,key,pbeParameterSpec);
        return cipher.doFinal(data);
    }

    /**
     * 解密
     * @param data
     * @param password
     * @param salt
     * @return
     * @throws Exception
     */
    public static byte[] decrypt(byte[] data, String password, byte[]salt)throws Exception{
        Key key = toKey(password);
        //实例化PBE参数材料
        PBEParameterSpec pbeParameterSpec = new PBEParameterSpec(salt,ITERATION_COUNT);
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE,key,pbeParameterSpec);
        return cipher.doFinal(data);
    }


    public static void main(String[] args) throws Exception{
        PBECoder coder = new PBECoder();
        System.out.println("初始化秘钥");
        String password = "fdsewerwr";
        byte [] salt = initSalt();
        System.out.println("加密开始，明文是‘我爱你’....");
        byte [] data = coder.encrypt("我爱你".getBytes(),password,salt);
        System.out.println("加密得到的数据："+ Hex.encodeHexString(data));
        System.out.println("解密开始..");
        byte [] deData = coder.decrypt(data,password,salt);
        System.out.println("解密得到的是："+new String(deData));
    }
}
