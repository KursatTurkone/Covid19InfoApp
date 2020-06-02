package sample.controller;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import sample.model.RecordModel;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Transactions {
    public List<RecordModel> okuma(String url) {
        List<RecordModel> records = new ArrayList<>();
        try {

            //Get Document Builder
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(new URL(url).openStream());
            document.getDocumentElement().normalize();
            Element root = document.getDocumentElement();
            System.out.println(root.getNodeName());
            NodeList nList = document.getElementsByTagName("record");
            System.out.println("============================");



            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node node = nList.item(temp);
                System.out.println("");    //Just a separator
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    RecordModel recordModel = new RecordModel();

                    Element eElement = (Element) node;
                    recordModel.setGeoId(eElement.getElementsByTagName("geoId").item(0).getTextContent());
                    recordModel.setCountriesAndTerritories(eElement.getElementsByTagName("countriesAndTerritories").item(0).getTextContent());
                    recordModel.setDateRep(eElement.getElementsByTagName("dateRep").item(0).getTextContent());
                    recordModel.setDay(Integer.parseInt(eElement.getElementsByTagName("day").item(0).getTextContent()));
                    recordModel.setMonth(Integer.parseInt(eElement.getElementsByTagName("month").item(0).getTextContent()));
                    recordModel.setYear(Integer.parseInt(eElement.getElementsByTagName("year").item(0).getTextContent()));
                    recordModel.setCases(Integer.parseInt(eElement.getElementsByTagName("cases").item(0).getTextContent()));
                    recordModel.setDeaths(Integer.parseInt(eElement.getElementsByTagName("deaths").item(0).getTextContent()));
                    if ("" == (eElement.getElementsByTagName("popData2018").item(0).getTextContent())) {
                        recordModel.setPopData2018(0);
                    } else
                        recordModel.setPopData2018(Integer.parseInt(eElement.getElementsByTagName("popData2018").item(0).getTextContent()));
                    recordModel.setContinentExp(eElement.getElementsByTagName("continentExp").item(0).getTextContent());
                    records.add(recordModel);
                }
            }




        } catch (MalformedURLException e){
            System.out.println(e);


        } catch (Exception e) {

            System.out.println(e);
        }

        return records;
    }
}



