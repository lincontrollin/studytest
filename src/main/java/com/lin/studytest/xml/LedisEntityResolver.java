package com.lin.studytest.xml;

import java.io.IOException;
import java.io.InputStream;

import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class LedisEntityResolver implements EntityResolver {
	public InputSource resolveEntity(String publicId, String systemId)
			throws SAXException, IOException {
		if (systemId != null) {
			try {
				InputStream is = this.getClass().getResourceAsStream("/com/lin/studytest/xml/spring-beans.xsd");
//				readis(is);
				InputSource source = new InputSource(is);
				source.setPublicId(publicId);
				source.setSystemId(systemId);
				return source;
			}catch (Exception ex) {
			}
		}
		return null;
	}
	
	public void readis(InputStream is)throws Exception{
		byte[] bytes = new byte[1024];               
		StringBuilder sb = new StringBuilder();      
		while(is.read(bytes)!=-1){                   
			sb.append(new String(bytes));            
			bytes = new byte[1024];                  
		}                                            
		System.out.println(sb.toString());           
	}
}
