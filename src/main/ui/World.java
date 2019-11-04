package ui;

import data.Playerpool;
import data.Worldlog;
import event.Match;
import exception.InvalidInput;
import exception.InvalidMenuSelection;
import exception.InvalidPlayerSelection;
import model.Player;
import model.Team;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class World implements Serializable {

    private Scanner scanner;
    private Playerpool playerpool;
    private Worldlog worldlog;

    public World() throws IOException {

        scanner = new Scanner(System.in);
        playerpool = new Playerpool();
        worldlog = new Worldlog();

    }


    private void mainMenu() throws IOException, ClassNotFoundException {


        while (true) {

            System.out.println("Please select: \n 1 Team Management \n 2 Event\n 3 Data \n 4 Quit");
            int selection = selectionFour();

            if (selection == 1) {
                teamMenu();
            }
            if (selection == 2) {
                eventMenu();
            }
            if (selection == 3) {
                dataMenu();
            }
            if (selection == 4) {
                break;
            }
        }


    }

    private void teamMenu() {

        while (true) {
            System.out.println(" 1 Add a new team \n 2 Delete a team \n 3 Review all teams \n 4 Back");
            int selection = selectionFour();
            if (selection == 1) {
                newTeam();
            }
            if (selection == 2) {
                deleteTeam();
            }
            if (selection == 3) {
                System.out.println("All teams:" + worldlog.getCurrentTeams());
            }
            if (selection == 4) {
                break;
            }

        }
    }

    private void eventMenu() {

        while (true) {
            System.out.println(" 1 New Match Day\n 2  \n 3  \n 4 Back");
            int selection = selectionFour();
            if (selection == 1) {
                Match match = new Match(worldlog.getCurrentTeams());
                match.newMatch();
            }
            if (selection == 2) {
                break;
            }
            if (selection == 3) {
                break;
            }
            if (selection == 4) {
                break;
            }

        }
    }

    private void dataMenu() throws IOException, ClassNotFoundException {

        while (true) {
            System.out.println(" 1 Load \n 2 Save \n 3 Back");
            int selection = selectionFour();
            if (selection == 1) {
                load();
            }
            if (selection == 2) {
                save();
            }
            if (selection == 3) {
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
        System.out.println("Please enter your team name:");
        scanner.nextLine();
        String input = scanner.nextLine();
        team.setTeamname(input);

        for (int i = 0; i < 50; i = i + 10) {

            int selection = selectionplayer(randomDraft(i));
            scanner.nextLine();
            team.addplayer(playerpool.getPlayer(selection - 1));

        }

        System.out.println("The team you have assembled:" + team.getTeamname() + "" + team.getTeamplayers());
        worldlog.addTeam(team);
    }

    private ArrayList<Integer> randomDraft(int i) {

        ArrayList<Integer> smallpool = new ArrayList<>();

        int x = i + (int) (Math.random() * ((10 - 1) + 1)) + 1;
        smallpool.add(x);
        int y = i + (int) (Math.random() * ((10 - 1) + 1)) + 1;
        smallpool.add(y);
        int z = i + (int) (Math.random() * ((10 - 1) + 1)) + 1;
        smallpool.add(z);

        Player candidate1 = playerpool.getPlayer(x - 1);
        Player candidate2 = playerpool.getPlayer(y - 1);
        Player candidate3 = playerpool.getPlayer(z - 1);

        System.out.println("Please enter the player ID");
        System.out.println(candidate1 + "" + candidate2 + "" + candidate3);

        return smallpool;
    }

    //delete a team based on user input
    //REQUIRES: correct user input
    //MODIFIES: this
    //EFFECTS: remove a team object from currentTeams
    private void deleteTeam() {
        System.out.println("All teams:" + worldlog.getCurrentTeams() + "\n Please select the team to delete");
        int choice = scanner.nextInt();
        worldlog.removeTeam(worldlog.getTeam(choice));
    }

    public void load() throws IOException {
        worldlog.load();
    }


    public void save() throws IOException {
        worldlog.save();
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        World world = new World();
        world.mainMenu();
    }


    private int selectionFour() {

        int selection = 0;

        try {
            selection = scanner.nextInt();
            if (!(selection == 1 || selection == 2 || selection == 3 || selection == 4)) {
                throw new InvalidMenuSelection();
            }
        } catch (InvalidInput e) {
            System.out.println("Invalid Input");
        } finally {
            System.out.println("Thanks for playing");
        }

        return selection;

    }

    private int selectionplayer(ArrayList<Integer> list) {

        int selection = 0;

        try {
            selection = scanner.nextInt();

            if (!list.contains(selection)) {
                throw new InvalidPlayerSelection();
            }

        } catch (InvalidInput e) {
            System.out.println("Invalid Input");
        }

        return selection;
    }


}
