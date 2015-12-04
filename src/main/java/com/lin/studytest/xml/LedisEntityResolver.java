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
				InputSource source = new InputSource(is);
				source.setPublicId(publicId);
				source.setSystemId(systemId);
				return source;
			}catch (Exception ex) {
			}
		}
		return null;
	}
}
