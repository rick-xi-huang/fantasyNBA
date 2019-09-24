package ui;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.IOException;

public class World {

    private ArrayList<AssembleTeam> worldlog;
    private Scanner scanner;
    private ArrayList<PlayerLog> allplayers;

    private World(ArrayList<PlayerLog> allplayers) {

        worldlog = new ArrayList<>();
        scanner = new Scanner(System.in);
        this.allplayers = allplayers;

    }

    private void userMenu() {

        int selection;

        while (true) {
            System.out.println("Please select an option: \n 1 Add a new team  "
                    + "\n 2 Delete a team \n 3 Review all teams \n 4 Quit");
            selection = scanner.nextInt();
            if (selection == 1) {
                newTeam();
            }
            if (selection == 2) {
                deleteTeam();
            }
            if (selection == 3) {
                System.out.println("All teams:" + worldlog);
            }
            if (selection == 4) {
                break;
            }
        }

    }

    private void newTeam() {
        AssembleTeam assembleTeam = new AssembleTeam(allplayers);
        worldlog.add(assembleTeam);
    }

    private void deleteTeam() {
        System.out.println("All teams:" + worldlog + "\n Please select the team to delete");
        int choice = scanner.nextInt();
        worldlog.remove(choice);
    }

    public static void main(String[] args) throws IOException {

        Playerpool playerpool = new Playerpool();
        World world = new World(playerpool.allplayers);
        world.userMenu();
    }

}
