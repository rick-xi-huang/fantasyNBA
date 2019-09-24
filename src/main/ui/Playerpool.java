package ui;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Playerpool {

    ArrayList<PlayerLog> allplayers;

    public Playerpool() throws IOException {
        allplayers = new ArrayList<PlayerLog>();

        List<String> lines = Files.readAllLines(Paths.get("NBApool.csv"));

        for (String line : lines) {
            ArrayList<String> partsOfLine = splitOnSpace(line);
            System.out.print("ID: " + partsOfLine.get(0) + " ");
            System.out.print("Name: " + partsOfLine.get(1) + " ");
            System.out.println("Overall: " + partsOfLine.get(15));
            PlayerLog playerLog = new PlayerLog(Integer.parseInt(partsOfLine.get(0)),
                    partsOfLine.get(1), Double.parseDouble(partsOfLine.get(15)));
            allplayers.add(playerLog);

        }
    }

    private static ArrayList<String> splitOnSpace(String line) {
        String[] splits = line.split(",");
        return new ArrayList<>(Arrays.asList(splits));
    }


}
