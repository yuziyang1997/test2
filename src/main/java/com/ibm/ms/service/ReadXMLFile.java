package com.ibm.ms.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.commons.lang.StringUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class ReadXMLFile {
	 public static void main(String[] args) {
	        String filePath = "src/main/resources/applicationContext.xml";
	        File xmlFile = new File(filePath);
	        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	        DocumentBuilder dBuilder;
	        try {
	            dBuilder = dbFactory.newDocumentBuilder();

	            // parse xml file and load into document
	            Document doc = dBuilder.parse(xmlFile);

	            doc.getDocumentElement().normalize();
	            

	            // update Element value
	            updateElementValue(doc);

	            // delete element
	            deleteElement(doc);

	            // add new element
//	            addElement(doc);

	            // write the updated document to file or console
	            writeXMLFile(doc);

	        } catch (SAXException | ParserConfigurationException | IOException | TransformerException e1) {
	            e1.printStackTrace();
	        }
	    }

	    private static void writeXMLFile(Document doc)
	    throws TransformerFactoryConfigurationError, TransformerConfigurationException, TransformerException {
	        doc.getDocumentElement().normalize();
	        TransformerFactory transformerFactory = TransformerFactory.newInstance();
	        Transformer transformer = transformerFactory.newTransformer();
	        DOMSource source = new DOMSource(doc);
	        StreamResult result = new StreamResult(new File("src/main/resources/updated.xml"));
	        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
	        transformer.transform(source, result);
	        System.out.println("XML file updated successfully");
	    }

	    /**
	     * Add a new element salary to user element.
	     * @param doc
	     */
//	    private static void addElement(Document doc) {
//	        NodeList users = doc.getElementsByTagName("User");
//	        Element emp = null;
//
//	        // loop for each user
//	        for (int i = 0; i < users.getLength(); i++) {
//	            emp = (Element) users.item(i);
//	            Element salaryElement = doc.createElement("salary");
//	            salaryElement.appendChild(doc.createTextNode("10000"));
//	            emp.appendChild(salaryElement);
//	        }
//	    }

	    /**
	     * Delete gender element from User element
	     * @param doc
	     */
	    private static void deleteElement(Document doc) {
	        NodeList list = doc.getElementsByTagName("bean");
	        Element elem = null;
	        // loop for each user
	        for (int i = 0; i < list.getLength(); i++) {
	        	elem = (Element) list.item(i);
	            String name = elem.getAttribute("class");
	            if (name.equals("org.springframework.jndi.JndiObjectFactoryBean") || name.equals("org.springframework.jndi.JndiObjectFactoryBean") || name.equals("org.springframework.jndi.JndiObjectFactoryBean")) {
	            	elem.getParentNode().removeChild(elem);
	            	i--;
				}
	        }

	    }

	    /**
	     * Update firstName element value to Upper case.
	     * @param doc
	     */
	    private static void updateElementValue(Document doc) {
	        NodeList list = doc.getChildNodes();
	        Element elem = null;
	        // loop for each user

	        elem = (Element) list.item(0);
	        String a = elem.getAttribute("xsi:schemaLocation");
	        System.out.println(a);
	        String[] list1 = {};
	        list1 = a.split(" ");
	        for (int i = 0; i < list1.length; i++) {
	        	if (list1[i].equals("http://www.springframework.org/schema/integration/spring-integration-4.2.xsd")) {
	        		list1[i]="http://www.springframework.org/schema/integration/spring-integration-4.3.xsd";
				}
	        	if (list1[i].equals("http://www.springframework.org/schema/integration/http/spring-integration-http-4.2.xsd")) {
	        		list1[i]="http://www.springframework.org/schema/integration/http/spring-integration-http-4.3.xsd";					
				}
	        	if (list1[i].equals("http://www.springframework.org/schema/integration/mail/spring-integration-mail-4.2.xsd")) {
	        		list1[i]="http://www.springframework.org/schema/integration/mail/spring-integration-mail-4.3.xsd";
				}
	        	if (list1[i].equals("http://www.springframework.org/schema/integration/jdbc/spring-integration-jdbc-4.2.xsd")) {
	        		list1[i]="http://www.springframework.org/schema/integration/jdbc/spring-integration-jdbc-4.3.xsd";
				}
	        }
	        String b = StringUtils.join(list1, " ");
	        System.out.println(b);
	        elem.setAttribute("xsi:schemaLocation", b);
//	            Node name = elem.getElementsByTagName("firstName").item(0).getFirstChild();
//	            name.setNodeValue(name.getNodeValue().toUpperCase());
	            
	        
	    }
}
