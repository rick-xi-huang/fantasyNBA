package network;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;

import model.Player;
import model.Team;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

//XML Parsing
//http://www.javased.com/index.php?api=javax.xml.parsers.DocumentBuilderFactory
public class FantasyWebData {

    private static DecimalFormat df = new DecimalFormat("#.##");

    private NodeList nodelist;

    private ArrayList<Player> webpool;

    public FantasyWebData() {
        webpool = new ArrayList<>();
        getWebData();
        calOverall();
    }

    //Obtain latest player rating information from web
    //MODIFIES: this
    //EFFECTS: store player informaion from web in the webpool arraylist
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

//    public void printallplayers() {
//
//        for (int i = 0; i < nodelist.getLength(); i++) {
//            Node node = nodelist.item(i);
//
//
//            if (node.getNodeType() == Node.ELEMENT_NODE) {
//                Element element = (Element) node;
//
//                System.out.println("ID : " + element.getElementsByTagName("playerId").item(0).getTextContent());
//                System.out.println("Name : " + element.getElementsByTagName("name").item(0).getTextContent());
//                System.out.println("PTS : " + element.getElementsByTagName("PTS").item(0).getTextContent());
//                System.out.println("Minutes : " + element.getElementsByTagName("Minutes").item(0).getTextContent());
//            }
//
//        }
//    }

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


    //Update all players in the current teams
    //MODIFIES: teams
    //EFFECTS: Compare every current player with the latest webpool, if there is a name match, update the player

    public void updateFromWeb(ArrayList<Team> teams) {
        ArrayList<Player> temp = getWebpool();
        for (Team team: teams) {
            for (Player player: team.getTeamplayers()) {
                for (int i = 0; i < temp.size(); i++) {
                    Player next = temp.get(i);
                    if (player.getName().equals(next.getName())) {
                        player.setOverall(next.getOverall());
                        System.out.println(player.getName() + " rating updated to " + df.format(player.getOverall()));
                    }
                }
            }
        }
    }


}