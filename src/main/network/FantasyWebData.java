package network;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.net.URL;
import java.util.ArrayList;

import model.Player;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class FantasyWebData {

    private NodeList nodelist;

    private ArrayList<Player> webpool;

    public FantasyWebData() {
        webpool = new ArrayList<>();
        getWebData();
        calOverall();
    }


    private void getWebData() {

        try {
            String url = "https://www.fantasybasketballnerd.com/service/draft-projections";
            System.setProperty("http.agent", "Chrome");
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(new URL(url).openStream());
            doc.getDocumentElement().normalize();
            nodelist = doc.getElementsByTagName("Player");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void printallplayers() {

        for (int i = 0; i < nodelist.getLength(); i++) {
            Node node = nodelist.item(i);


            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;

                System.out.println("ID : " + element.getElementsByTagName("playerId").item(0).getTextContent());
                System.out.println("Name : " + element.getElementsByTagName("name").item(0).getTextContent());
                System.out.println("PTS : " + element.getElementsByTagName("PTS").item(0).getTextContent());
                System.out.println("Minutes : " + element.getElementsByTagName("Minutes").item(0).getTextContent());
            }

        }
    }

    private void calOverall() {
        for (int i = 0; i < nodelist.getLength(); i++) {
            Node node = nodelist.item(i);

            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;
                int id = Integer.parseInt(element.getElementsByTagName("playerId").item(0).getTextContent());
                String name = element.getElementsByTagName("name").item(0).getTextContent();
                Double pts = Double.valueOf(element.getElementsByTagName("PTS").item(0).getTextContent());
                Double games = Double.valueOf(element.getElementsByTagName("Games").item(0).getTextContent());
                Double overall = pts / games;
                webpool.add(new Player(id, name, overall));
            }
        }
    }

    public ArrayList<Player> getWebpool() {
        return webpool;
    }


}