package UI;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class World {

    private ArrayList<AssembleTeam> worldlog;
    private Scanner scanner;
    private ArrayList<PlayerLog> playerpool;

    private World(ArrayList<PlayerLog> playerpool){

    worldlog = new ArrayList<>();
    scanner = new Scanner(System.in);
    this.playerpool=playerpool;
    UserMenu();

    }

    private void UserMenu(){

        int selection;

        while(true) {

            System.out.println("Please select an option: \n 1 Add a new team  \n 2 Delete a team \n 3 Review all teams \n 4 Quit");
            selection = scanner.nextInt();

            if (selection == 1) {
                AssembleTeam assembleTeam = new AssembleTeam(playerpool);
                worldlog.add(assembleTeam);
            }

            if (selection == 2) {
                System.out.println("All teams:"+ worldlog + "\n Please select the team to delete");
                int choice = scanner.nextInt();
                worldlog.remove(choice);
            }

            if (selection == 3) {
                System.out.println("All teams:"+ worldlog);
            }

            if (selection == 4) {
                break;
            }

        }

    }

    public static void main(String[] args) throws IOException {
        ArrayList<PlayerLog> playerpool = new ArrayList<>();

        List<String> lines = Files.readAllLines(Paths.get("NBApool.csv"));

        for (String line : lines){
            ArrayList<String> partsOfLine = splitOnSpace(line);
            System.out.print("ID: "+partsOfLine.get(0)+" ");
            System.out.print("Name: "+partsOfLine.get(1)+" ");
            System.out.println("Overall: "+partsOfLine.get(15));
            PlayerLog playerLog = new PlayerLog(Integer.parseInt(partsOfLine.get(0)),partsOfLine.get(1),Double.parseDouble(partsOfLine.get(15)));
            playerpool.add(playerLog);

        }

        new World(playerpool);


    }

    private static ArrayList<String> splitOnSpace(String line){
        String[] splits = line.split(",");
        return new ArrayList<>(Arrays.asList(splits));
    }
}
