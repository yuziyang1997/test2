package com.ibm.ms.service;
import java.io.File;
import java.io.IOException;

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

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.fasterxml.jackson.databind.node.TextNode;
public class pomchange {
    public static void main(String[] args) {
        String filePath = "src/main/resources/pom.xml";
        File xmlFile = new File(filePath);
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder;
        try {
            dBuilder = dbFactory.newDocumentBuilder();

            // parse xml file and load into document
            Document doc = dBuilder.parse(xmlFile);

            doc.getDocumentElement().normalize();

            // update Element value
 //           updateElementValue(doc);

            // delete element
//            deleteElement(doc);

            // add new element
            addElement(doc);

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
        StreamResult result = new StreamResult(new File("src/main/resources/pom_updated.xml"));
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.transform(source, result);
        System.out.println("XML file updated successfully");
    }

    /**
     * Add a new element salary to user element.
     * @param doc
     */
    private static void addElement(Document doc) {
        Node node = doc.getElementsByTagName("dependencies").item(0);
        Element newEle0 = doc.createElement("dependency");
        Element newEle1 = doc.createElement("groupId");
        Node newText1 = doc.createTextNode("org.springframework.integration");
        Element newEle2 = doc.createElement("artifactId");
        Node newText2 = doc.createTextNode("spring-integration-core");
        Element newEle3 = doc.createElement("version");
        Node newText3 = doc.createTextNode("4.3.21.RELEASE");
        newEle1.appendChild(newText1);
        newEle2.appendChild(newText2);
        newEle3.appendChild(newText3);
        newEle0.appendChild(newEle1);
        newEle0.appendChild(newEle2);
        newEle0.appendChild(newEle3);
        node.appendChild(newEle0);
        // loop for each user
//        for (int i = 0; i < users.getLength(); i++) {
//            emp = (Element) users.item(i);
//            Element salaryElement = doc.createElement("salary");
//            salaryElement.appendChild(doc.createTextNode("10000"));
//            emp.appendChild(salaryElement);
//        }
    }

    /**
     * Delete gender element from User element
     * @param doc
     */
    private static void deleteElement(Document doc) {
        NodeList users = doc.getElementsByTagName("User");
        Element user = null;
        // loop for each user
        for (int i = 0; i < users.getLength(); i++) {
            user = (Element) users.item(i);
            Node genderNode = user.getElementsByTagName("gender").item(0);
            user.removeChild(genderNode);
        }

    }

    /**
     * Update firstName element value to Upper case.
     * @param doc
     */
    private static void updateElementValue(Document doc) {
        NodeList users = doc.getElementsByTagName("User");
        Element user = null;
        // loop for each user
        for (int i = 0; i < users.getLength(); i++) {
            user = (Element) users.item(i);
            Node name = user.getElementsByTagName("firstName").item(0).getFirstChild();
            name.setNodeValue(name.getNodeValue().toUpperCase());
        }
    }
}
