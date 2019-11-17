package ui;

import data.Eventlog;
import data.Playerpool;
import data.Teamlog;
import event.Match;
import exception.InvalidInput;
import exception.InvalidMenuSelection;
import exception.InvalidPlayerSelection;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import model.Player;
import model.Team;
import network.FantasyWebData;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


public class World extends Application {

    private Scanner scanner;
    private Playerpool playerpool;
    private Teamlog teamlog;
    private Eventlog eventlog;
    private FantasyWebData fantasyWebData;
    private Stage stage;

    public World() throws IOException {

        scanner = new Scanner(System.in);
        playerpool = new Playerpool();
        teamlog = new Teamlog();
        eventlog = new Eventlog();
        fantasyWebData = new FantasyWebData();

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
                System.out.println("All teams:" + teamlog.getCurrentTeams());
            }
            if (selection == 4) {
                break;
            }

        }
    }

    private void eventMenu() {

        while (true) {
            System.out.println(" 1 New Match Day\n 2 View History \n 3  \n 4 Back");
            int selection = selectionFour();
            if (selection == 1) {
                Match match = new Match(teamlog.getCurrentTeams());
                match.newMatch();
                eventlog.addEvent(match.getEvent());
            }
            if (selection == 2) {
                eventlog.printAll();
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
            System.out.println(" 1 Load \n 2 Save \n 3 Web Data \n 4 Back");
            int selection = selectionFour();
            if (selection == 1) {
                load();
            }
            if (selection == 2) {
                save();
            }
            if (selection == 3) {
                fantasyWebData.updateFromWeb(teamlog.getCurrentTeams());
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
        System.out.println("Please enter your team name:");
        String input = scanner.nextLine();
        team.setTeamname(input);

        for (int i = 0; i < 50; i = i + 10) {

            int selection = selectionplayer(randomDraft(i));
            scanner.nextLine();
            team.addplayer(playerpool.getPlayer(selection - 1));

        }

        System.out.println("The team you have assembled:" + team.getTeamname() + "" + team.getTeamplayers());
        teamlog.addTeam(team);
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
        System.out.println("All teams:" + teamlog.getCurrentTeams() + "\n Please select the team to delete");
        int choice = scanner.nextInt();
        teamlog.removeTeam(teamlog.getTeam(choice));
    }

    private void load() throws IOException {
        teamlog.load();
        eventlog.load();
    }


    private void save() throws IOException {
        teamlog.save();
        eventlog.save();
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


    public static void main(String[] args) throws IOException, ClassNotFoundException {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException {
        stage.setTitle("Fantasy NBA");
        Scene scene = new Scene(new VBox(), 400, 350);
        scene.setFill(Color.OLDLACE);
        MenuBar menuBar = new MenuBar();
        TextArea ta = new TextArea();


        final VBox vbox = new VBox();
        vbox.setAlignment(Pos.CENTER);
        vbox.setSpacing(10);
        vbox.setPadding(new Insets(0, 10, 0, 10));
        vbox.setVisible(true);

        Menu teamMenu = new Menu("Team");
        MenuItem add = new MenuItem("Add a New Team");
        add.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {
                addTeam(stage,scene);
            }
        });

        MenuItem delete = new MenuItem("Delete a Team");
        delete.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {
                deleteTeam();
            }
        });

        MenuItem review = new MenuItem("Review all teams");
        review.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {
                System.out.println("All teams:" + teamlog.getCurrentTeams());
                //ta.setText("All teams:" + teamlog.getCurrentTeams());
            }
        });

        teamMenu.getItems().addAll(add, delete, review);

        Menu eventmenu = new Menu("Event");

        MenuItem match = new MenuItem("New Match Day");
        match.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {
                Match match = new Match(teamlog.getCurrentTeams());
                match.newMatch();
                eventlog.addEvent(match.getEvent());
            }
        });

        MenuItem view = new MenuItem("View History");
        view.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {
                eventlog.printAll();
            }
        });

        eventmenu.getItems().addAll(match, view);

        Menu datamenu = new Menu("Data");

        MenuItem load = new MenuItem("Load");
        load.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {
                try {
                    load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        MenuItem save = new MenuItem("Save");
        save.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {
                try {
                    save();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        MenuItem web = new MenuItem("Web");
        web.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {
                fantasyWebData.updateFromWeb(teamlog.getCurrentTeams());
            }
        });

        datamenu.getItems().addAll(load,save,web);

        menuBar.getMenus().addAll(teamMenu,eventmenu,datamenu);

        Console console = new Console(ta);
        PrintStream ps = new PrintStream(console, true);
        System.setOut(ps);
        System.setErr(ps);

        ((VBox) scene.getRoot()).getChildren().addAll(menuBar,vbox,ta);

        stage.setScene(scene);
        stage.show();

    }

    public void addTeam(Stage stage, Scene scene) {

        ArrayList<Player> assembleTeam = new ArrayList<>();
        Button button1 = new Button("Go to Home");
        button1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                stage.setScene(scene);
            }
        });

        TextArea teamname = new TextArea("Team Name");

        Button button2 = new Button("Confirm");
        button2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Team newTeam = new Team();
                newTeam.setTeamplayers(assembleTeam);
                newTeam.setTeamname(teamname.getText());
                teamlog.addTeam(newTeam);
            }
        });

        VBox layout2 = new VBox();
        layout2.getChildren().addAll(button1,button2,teamname);

        final CheckBox[] cbs = new CheckBox[50];

        for (int i = 0; i < 50; i++) {
            final CheckBox cb = cbs[i] = new CheckBox(playerpool.getPlayer(i).getName());
            int finalI = i;
            cb.selectedProperty().addListener(new ChangeListener<Boolean>() {
                public void changed(ObservableValue<? extends Boolean> ov,
                                    Boolean old_val, Boolean new_val) {
//                  assembleTeam.add(new_val ? playerpool.getPlayer(finalI) : null);
                    if (new_val) {
                        assembleTeam.add(playerpool.getPlayer(finalI));
                    }
                }
            });
        }

        layout2.getChildren().addAll(cbs);

        Scene scene2 = new Scene(layout2,300,250);
        stage.setScene(scene2);



    }


}
