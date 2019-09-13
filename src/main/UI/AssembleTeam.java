package UI;

import java.util.ArrayList;
import java.util.Scanner;

public class AssembleTeam{
   private ArrayList<PlayerLog> teamlog;
   private Scanner scanner;
   private String teamname;


   public AssembleTeam(){

       teamlog = new ArrayList<>();
       scanner = new Scanner(System.in);
       InputOperations();
   }

        private void InputOperations() {
        String operation;
        int rating;

        System.out.println("Please enter your team name:");
        operation=scanner.nextLine();
        this.teamname = operation;

        while (true){
        System.out.println("Please select an option (add player or quit):");
        operation=scanner.nextLine();
        System.out.println("you selected: "+operation);

        if(operation.equals("quit")){
        break;
        }

        PlayerLog playerLog = new PlayerLog();
        System.out.println("Please enter the player name");
        operation=scanner.nextLine();
        playerLog.setName(operation);

        System.out.println("Please enter the player overall rating");
        rating=scanner.nextInt();
        scanner.nextLine();
        playerLog.setRating(rating);

        teamlog.add(playerLog);
        }

        System.out.println("The team you have assembled:"+ teamname + "" + teamlog);
        }

    public String toString() {
        return teamname + " " + teamlog;
    }

}

