package model;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Playerpool {

    ArrayList<Player> allplayers;

    public Playerpool() throws IOException {
        allplayers = new ArrayList<Player>();

        List<String> lines = Files.readAllLines(Paths.get("NBApool.csv"));

        for (String line : lines) {
            ArrayList<String> partsOfLine = splitOnSpace(line);
            System.out.print("ID: " + partsOfLine.get(0) + " ");
            System.out.print("Name: " + partsOfLine.get(1) + " ");
            System.out.println("Overall: " + partsOfLine.get(15));
            Player player = new Player(Integer.parseInt(partsOfLine.get(0)),
                    partsOfLine.get(1), Double.parseDouble(partsOfLine.get(15)));
            allplayers.add(player);

        }
    }

    private static ArrayList<String> splitOnSpace(String line) {
        String[] splits = line.split(",");
        return new ArrayList<>(Arrays.asList(splits));
    }

    public ArrayList<Player> getallplayers() {
        return allplayers;
    }

}
