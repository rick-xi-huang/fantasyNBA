package ui;

import java.util.ArrayList;
import java.util.Scanner;


public class AssembleTeam {
    private ArrayList<PlayerLog> teamlog;
    private Scanner scanner;
    private String teamname;
    private ArrayList<PlayerLog> allplayers;

    AssembleTeam(ArrayList<PlayerLog> allplayers) {

        teamlog = new ArrayList<>();
        scanner = new Scanner(System.in);
        this.allplayers = allplayers;
        inputOperations();
    }

    private void inputOperations() {
        String operation;
        int selection;
        int interval = 0;

        System.out.println("Please enter your team name:");
        operation = scanner.nextLine();
        this.teamname = operation;

        while (true) {
            System.out.println("Please select an option (add player or quit):");
            operation = scanner.nextLine();
            System.out.println("you selected: " + operation);

            if (operation.equals("quit")) {
                break;
            }

            int x = interval + (int) (Math.random() * ((10 - 1) + 1)) + 1;
            int y = interval + (int) (Math.random() * ((10 - 1) + 1)) + 1;
            int z = interval + (int) (Math.random() * ((10 - 1) + 1)) + 1;

            PlayerLog candidate1 = allplayers.get(x);
            PlayerLog candidate2 = allplayers.get(y);
            PlayerLog candidate3 = allplayers.get(z);

            System.out.println("Please enter the player ID");
            System.out.println(candidate1 + "" + candidate2 + "" + candidate3);
            selection = scanner.nextInt();
            scanner.nextLine();
            teamlog.add(allplayers.get(selection - 1));
            interval = interval + 10;

        }

        System.out.println("The team you have assembled:" + teamname + "" + teamlog);
    }

    public String toString() {
        return teamname + "   " + teamlog;
    }

}

