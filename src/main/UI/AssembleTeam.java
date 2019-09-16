package UI;

import java.util.ArrayList;
import java.util.Scanner;


public class AssembleTeam{
   private ArrayList<PlayerLog> teamlog;
   private Scanner scanner;
   private String teamname;
   private ArrayList<PlayerLog> playerpool;

   AssembleTeam(ArrayList<PlayerLog> playerpool){

       teamlog = new ArrayList<>();
       scanner = new Scanner(System.in);
       this.playerpool=playerpool;
       InputOperations();
   }

        private void InputOperations() {
        String operation;
        int selection;
        int interval = 0;

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

        int x = interval + (int)(Math.random()*((10-1)+1))+ 1;
        int y = interval + (int)(Math.random()*((10-1)+1))+ 1;
        int z = interval + (int)(Math.random()*((10-1)+1))+ 1;

        PlayerLog candidate1 = playerpool.get(x);
        PlayerLog candidate2 = playerpool.get(y);
        PlayerLog candidate3 = playerpool.get(z);

        System.out.println("Please enter the player ID");
        System.out.println(candidate1+ "" + candidate2 + "" + candidate3);
        selection=scanner.nextInt();
        scanner.nextLine();
        teamlog.add(playerpool.get(selection-1));
        interval = interval + 10;

        }

        System.out.println("The team you have assembled:"+ teamname + "" + teamlog);
        }

    public String toString() {
        return teamname + "   " + teamlog;
    }

}

