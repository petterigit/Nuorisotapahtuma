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
import java.util.ArrayList;

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
        File xmlFile = new File(fileLoc);
        EventList event_list = EventList.getInstance();
        try {
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document doc = builder.parse(xmlFile);
            doc.getDocumentElement().normalize();

            NodeList nodelist = doc.getDocumentElement().getElementsByTagName("Event");
            System.out.println("Or hereasd");
            for (int i = 0; i < nodelist.getLength(); i++) {
                Node node = nodelist.item(i);
                System.out.println("Or here");
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    System.out.println("Here?");
                    Element element = (Element) node;
                    String e_name = element.getElementsByTagName("name").item(0).getTextContent();
                    String e_begins = element.getElementsByTagName("begins").item(0).getTextContent();
                    String e_ends = element.getElementsByTagName("ends").item(0).getTextContent();
                    String e_place = element.getElementsByTagName("place").item(0).getTextContent();
                    String e_date = element.getElementsByTagName("date").item(0).getTextContent();
                    String e_info = element.getElementsByTagName("info").item(0).getTextContent();
                    String e_ages = element.getElementsByTagName("ages").item(0).getTextContent();

                    event_list.createEvent(e_name, e_begins, e_ends, e_place, e_date, e_info, e_ages);
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

            Element root0 = document.createElement("Events");
            document.appendChild(root0);

            Element root = document.createElement("Event");
            root0.appendChild(root);

            Element name = document.createElement("name");
            name.appendChild(document.createTextNode("Junnukertsi Yö"));
            root.appendChild(name);

            Element begins = document.createElement("begins");
            begins.appendChild(document.createTextNode("02:00"));
            root.appendChild(begins);

            Element ends = document.createElement("ends");
            ends.appendChild(document.createTextNode("06:00"));
            root.appendChild(ends);

            Element place = document.createElement("place");
            place.appendChild(document.createTextNode("Ahjola"));
            root.appendChild(place);

            Element date = document.createElement("date");
            date.appendChild(document.createTextNode("01/01"));
            root.appendChild(date);

            Element info = document.createElement("info");
            info.appendChild(document.createTextNode("Jee"));
            root.appendChild(info);

            Element ages = document.createElement("ages");
            ages.appendChild(document.createTextNode("18-99"));
            root.appendChild(ages);

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

    public void writeXML(Context context) {
        contextWrapper = new ContextWrapper(context);
        String fileLoc = contextWrapper.getFilesDir().toString();
        fileLoc = fileLoc + "/EventsXML.xml";

        ArrayList<Event> eventList = EventList.getInstance().getEvent_list();

        try {
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.newDocument();

            for (int i = 0; i < eventList.size(); i++) {
                Event event = eventList.get(i);

                Element root0 = document.createElement("Events");
                document.appendChild(root0);

                Element root = document.createElement("Event");
                root0.appendChild(root);

                Element name = document.createElement("name");
                name.appendChild(document.createTextNode(event.getName()));
                root.appendChild(name);

                Element begins = document.createElement("begins");
                begins.appendChild(document.createTextNode(event.getBegins()));
                root.appendChild(begins);

                Element ends = document.createElement("ends");
                ends.appendChild(document.createTextNode(event.getEnds()));
                root.appendChild(ends);

                Element place = document.createElement("place");
                place.appendChild(document.createTextNode(event.getPlace()));
                root.appendChild(place);

                Element date = document.createElement("date");
                date.appendChild(document.createTextNode(event.getDate()));
                root.appendChild(date);

                Element info = document.createElement("info");
                info.appendChild(document.createTextNode(event.getInfo()));
                root.appendChild(info);

                Element ages = document.createElement("ages");
                ages.appendChild(document.createTextNode(event.getAges()));
                root.appendChild(ages);
            }

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