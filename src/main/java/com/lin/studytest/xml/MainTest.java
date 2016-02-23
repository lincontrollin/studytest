package com.lin.studytest.xml;

import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.validation.SchemaFactory;

import org.apache.log4j.xml.SAXErrorHandler;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class MainTest {
	
	/**
	 * 测试解析xml文件
	 * @throws Exception
	 */
	public static void testParseXml()throws Exception{
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
	      DocumentBuilder docBuilder = dbf.newDocumentBuilder();
	      docBuilder.setErrorHandler(new SAXErrorHandler());
	      docBuilder.setEntityResolver(new LedisEntityResolver());
	      InputStream inputStream = MainTest.class.getResourceAsStream("/com/lin/studytest/xml/log4j.xml");
	      InputSource inputSource = new InputSource(inputStream);
//          inputSource.setSystemId("log4j.dtd");
	      Document doc = docBuilder.parse(inputSource);
	      
	      System.out.println(doc);
	}
	
	public static void testXSD()throws Exception{
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setValidating(true);
		factory.setNamespaceAware(true);
		factory.setAttribute("http://java.sun.com/xml/jaxp/properties/schemaLanguage", "http://www.w3.org/2001/XMLSchema");
		DocumentBuilder docBuilder = factory.newDocumentBuilder();
		docBuilder.setErrorHandler(new SAXErrorHandler());
	    docBuilder.setEntityResolver(new LedisEntityResolver());
	    InputStream inputStream = MainTest.class.getResourceAsStream("/com/lin/studytest/xml/applications.xml");
//	    readis(inputStream);
	    InputSource inputSource = new InputSource(inputStream);
//	    inputSource.setSystemId("/com/lin/studytest/xml/ledis.xml");
	    Document doc = docBuilder.parse(inputSource);
	    System.out.println(doc.getNodeValue());
		
	}
	
	public static void testXSD2()throws Exception{
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setValidating(true);
		factory.setNamespaceAware(true);
//		factory.setAttribute("http://java.sun.com/xml/jaxp/properties/schemaLanguage", "http://www.w3.org/2001/XMLSchema");
		SchemaFactory schemaFactory  = SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema");
		factory.setSchema(schemaFactory.newSchema(MainTest.class.getResource("/com/lin/studytest/xml/spring-beans.xsd")));
		DocumentBuilder docBuilder = factory.newDocumentBuilder();
	    InputStream inputStream = MainTest.class.getResourceAsStream("/com/lin/studytest/xml/applications.xml");
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
	
	public static void testXSD3()throws Exception{
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setValidating(true);
		DocumentBuilder docBuilder = factory.newDocumentBuilder();
	    InputStream inputStream = MainTest.class.getResourceAsStream("/com/lin/studytest/xml/applications.xml");
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
	
	public static void readis(InputStream is)throws Exception{
		byte[] bytes = new byte[1024];               
		StringBuilder sb = new StringBuilder();      
		while(is.read(bytes)!=-1){
			sb.append(new String(bytes));            
			bytes = new byte[1024];                  
		}                                            
		System.out.println(sb.toString());           
	}
	
	public static void main(String[] args) throws Exception{
		testParseXml();
	}

}
