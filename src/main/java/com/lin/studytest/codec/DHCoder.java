package com.lin.studytest.codec;

import org.apache.commons.codec.binary.Hex;

import javax.crypto.Cipher;
import javax.crypto.KeyAgreement;
import javax.crypto.SecretKey;
import javax.crypto.interfaces.DHPrivateKey;
import javax.crypto.interfaces.DHPublicKey;
import javax.crypto.spec.DHParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

/**
 * @author <a href="mailto:wanqi.lwq@alibaba-inc.com">wanqi.lwq</a>
 * @version 1.0.0
 * @description DH 算法
 * @since 2016/9/1
 */
public class DHCoder {

    public static final String ALGOTITHM = "DH";

    public static final String CODE_ALGORITHM = "AES";

    /**
     * 秘钥长度
     * DH算法默认的秘钥长度是1024
     * 秘钥长度必须是64的倍数，其范围在512到1024之间
     */
    public static final int KEY_SIZE = 512;

    public static Map<String,Key> keyMap = new HashMap<String, Key>();

    /**
     * 初始化甲方秘钥
     * @return map 甲方秘钥map
     * @throws Exception
     */
    public Map<String,Key> initKey()throws Exception{
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(ALGOTITHM);
        keyPairGenerator.initialize(KEY_SIZE);
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        DHPublicKey publicKey = (DHPublicKey) keyPair.getPublic();
        DHPrivateKey privateKey = (DHPrivateKey) keyPair.getPrivate();
        Map<String,Key> keyMap = new HashMap<String, Key>();
        keyMap.put("publicKey",publicKey);
        keyMap.put("privateKey",privateKey);
        return keyMap;
    }

    /**
     * 初始化乙方秘钥
     * @param keys 甲方公钥
     * @return map 乙方秘钥map
     * @throws Exception
     */
    public Map<String,Key> initKey(byte[] keys)throws Exception{
        /*
         解析甲方公钥，转换为公钥材料
         */
        X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(keys);
        KeyFactory keyFactory = KeyFactory.getInstance(ALGOTITHM);
        //产生公钥
        PublicKey key = keyFactory.generatePublic(x509EncodedKeySpec);
        //由甲方公钥 构建 乙方公钥
        DHParameterSpec dhParameterSpec = ((DHPublicKey)key).getParams();
        //实例和初始化密钥对生成器
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(keyFactory.getAlgorithm());
        keyPairGenerator.initialize(dhParameterSpec);
        /*
        产生乙方的秘钥对
         */
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        PrivateKey privateKey = (PrivateKey)keyPair.getPrivate();
        PublicKey publicKey = keyPair.getPublic();
        /*
        返回密钥对
         */
        Map<String,Key> keyMap = new HashMap<String, Key>();
        keyMap.put("publicKey",publicKey);
        keyMap.put("privateKey",privateKey);
        return keyMap;
    }

    /**
     * 利用本地秘钥加密
     * @param data 被加密的数据
     * @param key   本地秘钥
     * @return
     * @throws Exception
     */
    public byte[] encrypt(byte[]data,byte[] key)throws Exception{
        SecretKey secretKey = new SecretKeySpec(key,CODE_ALGORITHM);
        Cipher cipher = Cipher.getInstance(CODE_ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE,secretKey);
        return cipher.doFinal(data);
    }

    /**
     * 利用本地秘钥解密
     * @param data 密文
     * @param key 本地秘钥
     * @return
     * @throws Exception
     */
    public byte[] decrypt(byte[] data,byte[]key)throws Exception{
        SecretKey secretKey = new SecretKeySpec(key,CODE_ALGORITHM);
        Cipher cipher = Cipher.getInstance(CODE_ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE,secretKey);
        return cipher.doFinal(data);
    }

    /**
     * 生成本地秘钥
     * @param publicKeys
     * @param privateKeys
     * @return
     * @throws Exception
     */
    public byte[] generateKey(byte[] publicKeys,byte[] privateKeys)throws Exception{
        KeyFactory keyFactory = KeyFactory.getInstance(ALGOTITHM);
        //初始化公钥，秘钥材料转换
        X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(publicKeys);
        PublicKey publicKey = keyFactory.generatePublic(x509EncodedKeySpec);
        //初始化私钥，秘钥材料转换
        PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(privateKeys);
        PrivateKey privateKey = keyFactory.generatePrivate(pkcs8EncodedKeySpec);
        //实例化
        KeyAgreement keyAgreement = KeyAgreement.getInstance(keyFactory.getAlgorithm());
        keyAgreement.init(privateKey);
        keyAgreement.doPhase(publicKey,true);
        //生成本地秘钥
        SecretKey secretKey = keyAgreement.generateSecret(CODE_ALGORITHM);
        return secretKey.getEncoded();

    }

    public static void main(String[] args) throws Exception{
        System.out.println("以下是DH算法展示：");
        String data = "我爱你";
        DHCoder coder = new DHCoder();
        Map<String,Key> meKeyMap = coder.initKey();//生成甲方密钥对
        Map<String,Key> youKeyMap = coder.initKey(meKeyMap.get("publicKey").getEncoded());//生成乙方密钥对
//        byte[] mePublicKey = meKeyMap.get("publicKey").getEncoded();//甲方公钥
        System.out.println("甲乙双方生成密钥对完毕！！");
        byte [] youKey = coder.generateKey(meKeyMap.get("publicKey").getEncoded(),
                youKeyMap.get("privateKey").getEncoded());
        byte [] meKey = coder.generateKey(meKeyMap.get("publicKey").getEncoded(),
                youKeyMap.get("privateKey").getEncoded());

        System.out.println("甲方的本地秘钥："+ Hex.encodeHexString(meKey)+"\n"+
                "乙方的本地秘钥："+ Hex.encodeHexString(meKey)+"\n");


    }
}
