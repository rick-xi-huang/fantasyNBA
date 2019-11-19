package data;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Player;
import model.PlayerDisplay;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Playerpool {

    private HashMap<Integer,Player> allplayers;
    final ObservableList<PlayerDisplay> data = FXCollections.observableArrayList();

    //load player information from the player pool
    //REQUIRES: NBApool.csv in correct format in the data folder
    //MODIFIES: this
    //EFFECTS: store all player information in the playerpool object field

    public Playerpool() throws IOException {
        allplayers = new HashMap<>();

        List<String> lines = Files.readAllLines(Paths.get("data/NBApool.csv"));

        int i = 0;
        for (String line : lines) {
            ArrayList<String> partsOfLine = splitOnSpace(line);
            //System.out.print("ID: " + partsOfLine.get(0) + " ");
            //System.out.print("Name: " + partsOfLine.get(1) + " ");
            //System.out.println("Overall: " + partsOfLine.get(15));
            Player player = new Player(Integer.parseInt(partsOfLine.get(0)),
                    partsOfLine.get(1), Double.parseDouble(partsOfLine.get(15)));
            allplayers.put(i,player);
            data.add(new PlayerDisplay(player));
            i++;
        }
    }

    private static ArrayList<String> splitOnSpace(String line) {
        String[] splits = line.split(",");
        return new ArrayList<>(Arrays.asList(splits));
    }

    //obtain all player information the playerpool object
    //EFFECTS: return all player information as an ArrayList of player objects

    public HashMap<Integer, Player> getallplayers() {
        return allplayers;
    }

    public Player getPlayer(int key) {
        return allplayers.get(key);
    }

    public ObservableList<PlayerDisplay> getData() {
        return data;
    }

}
