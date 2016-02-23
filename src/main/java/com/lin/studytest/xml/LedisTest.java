package com.lin.studytest.xml;

import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.validation.SchemaFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class LedisTest {

	public static void testXSD2()throws Exception{
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setValidating(true);
		factory.setNamespaceAware(true);
//		factory.setAttribute("http://java.sun.com/xml/jaxp/properties/schemaLanguage", "http://www.w3.org/2001/XMLSchema");
		SchemaFactory schemaFactory  = SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema");
		factory.setSchema(schemaFactory.newSchema(MainTest.class.getResource("/com/lin/studytest/xml/ledis2.xsd")));
		DocumentBuilder docBuilder = factory.newDocumentBuilder();
	    InputStream inputStream = MainTest.class.getResourceAsStream("/com/lin/studytest/xml/ledis.xml");
//	    readis(inputStream);
	    InputSource inputSource = new InputSource(inputStream);
	    Document doc = docBuilder.parse(inputSource);
	    Element root = doc.getDocumentElement();
        NodeList students = root.getChildNodes();
        if(students!=null){
        	for (int i = 0, size = students.getLength(); i < size; i++) {
            	Node student = students.item(i);
                System.out.println(student.getNodeName());
            }
        }
	}
	
	public static void main(String[] args)throws Exception {
		testXSD2();
	}
}
