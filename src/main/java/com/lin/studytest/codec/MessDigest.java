package com.lin.studytest.codec;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.MessageDigest;

/**
 * @author <a href="mailto:wanqi.lwq@alibaba-inc.com">wanqi.lwq</a>
 * @version 1.0.0
 * @description 消息摘要算法
 * @since 2016/8/12
 */
public class MessDigest {

    public static String encodeMD5(byte [] bytes)throws Exception{
        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        byte [] codeBytes = messageDigest.digest(bytes);
        return Hex.encodeHexString(codeBytes);
    }

    public static String encodeSHA1(byte[] content)throws Exception{
        MessageDigest messageDigest = MessageDigest.getInstance("SHA");
        byte [] codeBytes = messageDigest.digest(content);
        return Hex.encodeHexString(codeBytes);
    }

    public static String encodeHmac(byte []content)throws Exception{
        javax.crypto.KeyGenerator keyGenerator = javax.crypto.KeyGenerator.getInstance("HmacMD5");
        SecretKey key = keyGenerator.generateKey();
        //获得秘钥，这里获得秘钥主要是甲方行为，并将秘钥给到乙方
        byte []secretKeys = key.getEncoded();

        //还原秘钥，这里是一个假设，因为我们这边存在甲乙双方，这里还原主要是乙方还原
        key = new SecretKeySpec(secretKeys,"HmacMD5");
        Mac mac = Mac.getInstance(key.getAlgorithm());
        mac.init(key);
        return Hex.encodeHexString(mac.doFinal(content));
    }


    public static String endcodeMD5Common(byte [] bytes){
        return DigestUtils.md5Hex(bytes);
    }


    public static void main(String[] args)throws Exception {
        System.out.println(encodeMD5("我爱算法导论！！".getBytes()));
        System.out.println(encodeSHA1("我爱算法导论！！".getBytes()));
        System.out.println(encodeHmac("我爱算法导论！！".getBytes()));
    }
}
