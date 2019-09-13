package UI;

import java.util.ArrayList;
import java.util.Scanner;

public class World {

    private ArrayList<AssembleTeam> worldlog;
    private Scanner scanner;

    public World(){

    worldlog = new ArrayList<>();
    scanner = new Scanner(System.in);
    UserMenu();
    }

    private void UserMenu(){

        int selection;

        while(true) {

            System.out.println("Please select an option: \n 1 Add a new team  \n 2 Delete a team \n 3 Review all teams \n 4 Quit");
            selection = scanner.nextInt();

            if (selection == 1) {
                AssembleTeam assembleTeam = new AssembleTeam();
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

    public static void main(String[] args) {

    new World();

    }

}
