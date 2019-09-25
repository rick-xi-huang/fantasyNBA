package ui;

import model.Player;
import model.Playerpool;
import model.Team;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.IOException;

public class World {

    private ArrayList<Team> worldlog;
    private Scanner scanner;
    private ArrayList<Player> allplayers;
    private Playerpool playerpool;

    private World() throws IOException {

        worldlog = new ArrayList<>();
        scanner = new Scanner(System.in);
        playerpool = new Playerpool();
        this.allplayers = playerpool.getallplayers();

    }

    //generate a user input menu with different choices
    //REQUIRES: correct user input
    //MODIFIES: this team player
    //EFFECTS: add a new team object / delete a team object / review all team objects / quit
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

    //generate a new team based on user input
    //REQUIRES: correct user input
    //MODIFIES: this team player
    //EFFECTS: create a new team object
    private void newTeam() {
        String input;
        int selection;
        int interval = 0;

        Team team = new Team();
        scanner.nextLine();
        System.out.println("Please enter your team name:");
        input = scanner.nextLine();
        team.setTeamname(input);

        while (true) {
            System.out.println("Please select an option (add player or quit):");
            input = scanner.nextLine();
            System.out.println("you selected: " + input);

            if (input.equals("quit")) {
                break;
            }

            int x = interval + (int) (Math.random() * ((10 - 1) + 1)) + 1;
            int y = interval + (int) (Math.random() * ((10 - 1) + 1)) + 1;
            int z = interval + (int) (Math.random() * ((10 - 1) + 1)) + 1;

            Player candidate1 = allplayers.get(x);
            Player candidate2 = allplayers.get(y);
            Player candidate3 = allplayers.get(z);

            System.out.println("Please enter the player ID");
            System.out.println(candidate1 + "" + candidate2 + "" + candidate3);
            selection = scanner.nextInt();
            scanner.nextLine();
            team.addplayer(allplayers.get(selection - 1));
            interval = interval + 10;

        }

        System.out.println("The team you have assembled:" + team.getTeamname() + "" + team.getTeamplayers());
        worldlog.add(team);
    }

    //delete a team based on user input
    //REQUIRES: correct user input
    //MODIFIES: this
    //EFFECTS: remove a team object from worldlog
    private void deleteTeam() {
        System.out.println("All teams:" + worldlog + "\n Please select the team to delete");
        int choice = scanner.nextInt();
        worldlog.remove(choice);
    }

    public static void main(String[] args) throws IOException {

        World world = new World();
        world.userMenu();
    }

}
