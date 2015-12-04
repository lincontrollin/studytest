package com.lin.studytest.xml;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.StringWriter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.sun.org.apache.xml.internal.serialize.OutputFormat;
import com.sun.org.apache.xml.internal.serialize.XMLSerializer;

public class ExampleTest {
	private void doc2XmlFile(Document document, String filePath) {
		try {
			OutputFormat format = new OutputFormat(document);//Serialize DOM
			format.setEncoding("UTF-8");
			StringWriter stringOut = new StringWriter();//Writer will be a String
			XMLSerializer serial = new XMLSerializer(stringOut, format);
			serial.asDOMSerializer();// As a DOM Serializer
			serial.serialize(document.getDocumentElement());
			String STRXML = stringOut.toString(); //Spit out DOM as a String
			File f = new File(filePath);
			PrintWriter out = new PrintWriter(new FileWriter(f));
			out.print(STRXML + "\n");
			out.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	private Document load(String filename) {
		Document document = null;
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			factory.setValidating(false);
			DocumentBuilder builder = factory.newDocumentBuilder();
			builder.setEntityResolver(this.new IgnoreDTDEntityResolver());
			document = builder.parse(new File(filename));
			document.normalize();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return document;
	}

	/**
	 * 演示修改文件的具体某个节点的值
	 */
	public void xmlUpdateDemo(String filePath, String customerName, String DMSID) {
		Document document = load(filePath);
		Element root = document.getDocumentElement();
		/** 如果root有子元素 */
		if (root.hasChildNodes()) {
			/** ftpnodes */
			NodeList ftpnodes = root.getChildNodes();

			/** 循环取得ftp所有节点 */
			for (int i = 0; i < ftpnodes.getLength(); i++) {
				Node subnode = ftpnodes.item(i);

				if (subnode.getNodeType() == Node.ELEMENT_NODE && subnode.getNodeName() == "Details") {
					subnode.getAttributes().getNamedItem("DMSID").setNodeValue(DMSID);
				}
				if (subnode.getNodeType() == Node.ELEMENT_NODE && subnode.getNodeName() == "CustomerDetails") {
					subnode.getAttributes().getNamedItem("CustomerName").setNodeValue(customerName);
				}
			}
		}
		doc2XmlFile(document, filePath);
	}
	/**
	 * 忽略DTD验证文件子类
	 */
	class IgnoreDTDEntityResolver implements EntityResolver{
		public InputSource resolveEntity(java.lang.String publicId, java.lang.String systemId) throws SAXException,
		java.io.IOException{
			if (systemId.contains("log4j.dtd")){
				InputSource is = new InputSource(new ByteArrayInputStream("<?xml version=\"1.0\" encoding=\"UTF-8\"?>".getBytes()));
				return is;
			}
			else
				return null;
		}
	}

	public static void main(String args[]) throws Exception {
		new ExampleTest().xmlUpdateDemo("D:\\myGitProject\\studytest\\target\\classes\\com\\lin\\studytest\\xml\\log4j.xml","3333","3333");

	}

}
