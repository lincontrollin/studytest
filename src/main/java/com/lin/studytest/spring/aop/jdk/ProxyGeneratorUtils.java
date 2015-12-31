package com.lin.studytest.spring.aop.jdk;

import java.io.FileOutputStream;
import java.io.IOException;
import sun.misc.ProxyGenerator;

public class ProxyGeneratorUtils {
    
    public static void writeProxy(String proxyName ,String path)throws Exception{
        
        byte[] classFile = ProxyGenerator.generateProxyClass(proxyName, RealSubject.class.getInterfaces());  
        
        FileOutputStream out = null;  
          
        try {  
            out = new FileOutputStream(path);  
            out.write(classFile);  
            out.flush();  
        } catch (Exception e) {  
            e.printStackTrace();  
        } finally {  
            try {
                out.close();  
            } catch (IOException e) {
                e.printStackTrace();  
            }  
        }  
    }

}
