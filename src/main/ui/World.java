package ui;

import data.Worldlog;
import model.Player;
import model.Playerpool;
import model.Team;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class World implements Serializable {

    private ArrayList<Team> currentTeams;
    private Scanner scanner;
    private ArrayList<Player> allplayers;
    private Playerpool playerpool;

    public World() throws IOException {

        currentTeams = new ArrayList<>();
        scanner = new Scanner(System.in);
        playerpool = new Playerpool();
        this.allplayers = playerpool.getallplayers();

    }

    //generate a user input menu with different choices
    //REQUIRES: correct user input
    //MODIFIES: this team player
    //EFFECTS: add a new team object / delete a team object / review all team objects / quit
    private void userMenu() throws IOException, ClassNotFoundException {

        int selection;

        while (true) {
            System.out.println("Please select an option: \n 1 Team Management  "
                    + "\n 2 Load\n 3 Save \n 4 Quit");
            selection = scanner.nextInt();
            if (selection == 1) {
                teamMenu();
            }

            if (selection == 2) {
                load();
            }
            if (selection == 3) {
                save();
            }
            if (selection == 4) {
                break;
            }
        }

    }

    private void teamMenu() {
        int selection;
        while (true) {
            System.out.println("Please select an option: \n 1 Add a new team"
                               + "\n 2 Delete a team \n 3 Review all teams \n 4 Back");
            selection = scanner.nextInt();
            if (selection == 1) {
                newTeam();
            }
            if (selection == 2) {
                deleteTeam();
            }
            if (selection == 3) {
                System.out.println("All teams:" + currentTeams);
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

        Team team = new Team();
        scanner.nextLine();
        System.out.println("Please enter your team name:");
        String input = scanner.nextLine();
        team.setTeamname(input);

        for (int i = 0; i < 190; i = i + 10) {
            System.out.println("Please select an option (add player or quit):");
            input = scanner.nextLine();

            if (input.equals("quit")) {
                break;
            }

            randomDraft(i);
            int selection = scanner.nextInt();
            scanner.nextLine();
            team.addplayer(allplayers.get(selection - 1));

        }

        System.out.println("The team you have assembled:" + team.getTeamname() + "" + team.getTeamplayers());
        currentTeams.add(team);
    }

    private void randomDraft(int i) {
        int x = i + (int) (Math.random() * ((10 - 1) + 1)) + 1;
        int y = i + (int) (Math.random() * ((10 - 1) + 1)) + 1;
        int z = i + (int) (Math.random() * ((10 - 1) + 1)) + 1;

        Player candidate1 = allplayers.get(x);
        Player candidate2 = allplayers.get(y);
        Player candidate3 = allplayers.get(z);

        System.out.println("Please enter the player ID");
        System.out.println(candidate1 + "" + candidate2 + "" + candidate3);
    }

    //delete a team based on user input
    //REQUIRES: correct user input
    //MODIFIES: this
    //EFFECTS: remove a team object from currentTeams
    private void deleteTeam() {
        System.out.println("All teams:" + currentTeams + "\n Please select the team to delete");
        int choice = scanner.nextInt();
        currentTeams.remove(choice);
    }

    public void load() throws IOException, ClassNotFoundException {

        Worldlog worldlog = new Worldlog();
        worldlog.load();
        currentTeams = worldlog.getCurrentTeams();
    }


    public void save() throws IOException {

        Worldlog worldlog = new Worldlog(currentTeams);
        worldlog.save();
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        World world = new World();
        world.userMenu();
    }
}
