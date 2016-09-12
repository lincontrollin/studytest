package com.lin.studytest.codec.cert;

import java.io.InputStream;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;

/**
 * @author <a href="mailto:wanqi.lwq@alibaba-inc.com">wanqi.lwq</a>
 * @version 1.0.0
 * @description 证书相关操作
 * @since 2016/9/2
 */
public class CertificateCoder {
    //类型证书x509
    public static final String CERT_TYPE = "X.509";

    public static final String ALIAS = "lynch";
    //密码
    private static final  String PASSWORD="201655lin";

    /**
     * 获取keystore
     * @param keyStorePath keystore的路径
     * @param password  密码
     * @return
     * @throws Exception
     */
    private KeyStore getKeyStore(String keyStorePath,String password)throws Exception{
        KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
        InputStream inputStream = CertificateCoder.class.getResourceAsStream(keyStorePath);
        keyStore.load(inputStream,password.toCharArray());
        inputStream.close();
        return keyStore;
    }

    /**
     * 获取证书
     * @param certPath 证书路径
     * @return
     * @throws Exception
     */
    private Certificate getCertificate(String certPath)throws Exception{
        CertificateFactory certificateFactory = CertificateFactory.getInstance(CERT_TYPE);
        InputStream inputStream = CertificateCoder.class.getResourceAsStream(certPath);
        Certificate certificate = certificateFactory.generateCertificate(inputStream);
        inputStream.close();
        return certificate;
    }

    /**
     * 从keystore中获取私钥
     * @param keyStorePath keystore路径
     * @param alias 别名
     * @param password  密码
     * @return
     * @throws Exception
     */
    private PrivateKey getPrivateKeyByKeyStore(String keyStorePath,String alias,String password)throws Exception{
        KeyStore keyStore = getKeyStore(keyStorePath,password);
        return (PrivateKey)keyStore.getKey(alias,password.toCharArray());
    }

    /**
     * 从证书中获取公钥
     * @param certPath 证书路径
     * @return
     * @throws Exception
     */
    private PublicKey getPublicKeyByCert(String certPath)throws Exception{
        Certificate certificate = getCertificate(certPath);

        return certificate.getPublicKey();
    }

    /**
     * 使用keystore来签名
     * @param data
     * @param keyStorePath
     * @param alias
     * @param password
     * @return
     * @throws Exception
     */
    public byte[] signByKeyStore(byte[]data,String keyStorePath,String alias,String password)throws Exception{
        KeyStore keyStore = getKeyStore(keyStorePath,password);
        PrivateKey privateKey = (PrivateKey)keyStore.getKey(alias,password.toCharArray());
        Signature signature = Signature.getInstance(privateKey.getAlgorithm());
        signature.initSign(privateKey);
        signature.update(data);
        return signature.sign();
    }

    /**
     * 使用证书cert来验证签名
     * @param data
     * @param certPath
     * @return
     * @throws Exception
     */
    public boolean verifyByCert(byte[] data,byte [] sign,String certPath)throws Exception{
        PublicKey publicKey = getPublicKeyByCert(certPath);
        Signature signature = Signature.getInstance(publicKey.getAlgorithm());
        signature.initVerify(publicKey);
//        signature.update(data);
        return signature.verify(data);
    }


    public static void main(String[] args) throws Exception{
        CertificateCoder certificateCoder = new CertificateCoder();
        byte [] data = "我爱你".getBytes();
        byte [] sign = certificateCoder.signByKeyStore(
                data,"../lynch.keystore",ALIAS,PASSWORD);
        System.out.println(certificateCoder.verifyByCert(data,sign, "../lynch.cer"));
    }



}
