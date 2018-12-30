package com.example.petteri.nuorisotapahtuma;


import android.content.Context;
import android.content.ContextWrapper;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

public class XmlHandler {

    ContextWrapper contextWrapper;

    private static XmlHandler instance = new XmlHandler();

    public static XmlHandler getInstance() {
        return instance;
    }
    // ContextWrapper.getFilesDir()

    public void readXML(Context context) {
        contextWrapper = new ContextWrapper(context);
        String fileLoc = contextWrapper.getFilesDir().toString();
        fileLoc = fileLoc + "/EventsXML.xml";
        EventList event_list = EventList.getInstance();
        try {
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document doc = builder.parse(new File(fileLoc));
            doc.getDocumentElement().normalize();
            NodeList nodelist = doc.getDocumentElement().getElementsByTagName("Event");
            for (int i = 0; i < nodelist.getLength(); i++) {
                Node node = nodelist.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    System.out.println("Here?");
                    Element element = (Element) node;
                    //String name, String begins, String ends, String place, String date, String info, int agesFrom, int agesTo
                    String e_name = element.getElementsByTagName("name").item(0).getTextContent();
                    String e_begins = element.getElementsByTagName("begins").item(0).getTextContent();
                    String e_ends = element.getElementsByTagName("ends").item(0).getTextContent();
                    String e_place = element.getElementsByTagName("place").item(0).getTextContent();
                    String e_date = element.getElementsByTagName("date").item(0).getTextContent();
                    String e_info = element.getElementsByTagName("info").item(0).getTextContent();
                    int e_agesFrom = Integer.parseInt(element.getElementsByTagName("agesFrom").item(0).getTextContent());
                    int e_agesTo = Integer.parseInt(element.getElementsByTagName("agesTo").item(0).getTextContent());

                    event_list.createEvent(e_name, e_begins, e_ends, e_place, e_date, e_info, e_agesFrom, e_agesTo);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } finally {
            System.out.println("Done reading from: " + fileLoc);
        }
    }


    /* Created with example from:
        https://examples.javacodegeeks.com/core-java/xml/parsers/documentbuilderfactory/create-xml-file-in-java-using-dom-parser-example/
     */
    public void initXML(Context context) {

        contextWrapper = new ContextWrapper(context);
        String fileLoc = contextWrapper.getFilesDir().toString();
        fileLoc = fileLoc + "/EventsXML.xml";
        try {
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.newDocument();

            Element root = document.createElement("Event");
            document.appendChild(root);

            //String name, String begins, String ends, String place, String date, String info, int agesFrom, int agesTo
            Element name = document.createElement("name");
            name.appendChild(document.createTextNode("Junnukertsi Yö"));
            root.appendChild(name);

            Element begins = document.createElement("begins");
            begins.appendChild(document.createTextNode("02:00"));
            name.appendChild(begins);

            Element ends = document.createElement("ends");
            ends.appendChild(document.createTextNode("06:00"));
            name.appendChild(ends);

            Element place = document.createElement("place");
            place.appendChild(document.createTextNode("Ahjola"));
            name.appendChild(place);

            Element date = document.createElement("date");
            date.appendChild(document.createTextNode("01/01"));
            name.appendChild(date);

            Element info = document.createElement("info");
            info.appendChild(document.createTextNode("Jee"));
            name.appendChild(info);

            Element agesFrom = document.createElement("agesFrom");
            agesFrom.appendChild(document.createTextNode("18"));
            name.appendChild(agesFrom);

            Element agesTo = document.createElement("agesTo");
            agesTo.appendChild(document.createTextNode("99"));
            name.appendChild(agesTo);

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource domSource = new DOMSource(document);
            StreamResult streamResult = new StreamResult(new File(fileLoc));

            transformer.transform(domSource, streamResult);

        } catch (ParserConfigurationException pce) {
            pce.printStackTrace();
        } catch (TransformerException te) {
            te.printStackTrace();
        } finally {
            System.out.println("Initialized xml file to: " + fileLoc);
        }

    }
}