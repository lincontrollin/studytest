package com.lin.studytest.codec;

import java.security.MessageDigest;

/**
 * @author <a href="mailto:wanqi.lwq@alibaba-inc.com">wanqi.lwq</a>
 * @version 1.0.0
 * @description 消息摘要算法
 * @since 2016/8/12
 */
public class MessDigest {

    public String encodeMD5(byte [] bytes)throws Exception{
        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        byte [] codeBytes = messageDigest.digest(bytes);
        return "";
    }
}
