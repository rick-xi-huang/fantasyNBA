package ui;

import data.Eventlog;
import data.Playerpool;
import data.Teamlog;
import event.Season;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import model.Player;
import model.PlayerDisplay;
import model.Team;
import model.TeamDisplay;
import network.FantasyWebData;

import java.io.*;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


public class World extends Application {

    private Playerpool playerpool;
    private Teamlog teamlog;
    private Eventlog eventlog;
    private FantasyWebData fantasyWebData;
    private Season season;

    public World() throws IOException {

        playerpool = new Playerpool();
        teamlog = new Teamlog();
        eventlog = new Eventlog();
        fantasyWebData = new FantasyWebData();
        season = new Season(teamlog.getCurrentTeams());

    }

    private void load() throws IOException {
        teamlog.load();
        eventlog.load();
    }


    private void save() throws IOException {
        teamlog.save();
        eventlog.save();
    }

    //All UI codes reference
    //https://docs.oracle.com/javase/8/javafx/user-interface-tutorial/ui_controls.htm#JFXUI336


    public static void main(String[] args) {
        launch(args);
    }

    //start the program, create user interface
    //MODIFIES: this
    //EFFECTS: display all UI elements for user input and program output

    @Override
    public void start(Stage stage) {
        stage.setTitle("Fantasy NBA");
        Scene scene = new Scene(new VBox(), 600, 500);
        scene.setFill(Color.OLDLACE);
        MenuBar menuBar = new MenuBar();
        TextArea ta = new TextArea();

        final VBox vbox = new VBox();
        vbox.setAlignment(Pos.CENTER);
        vbox.setSpacing(10);
        vbox.setPadding(new Insets(0, 10, 0, 10));
        vbox.setVisible(true);

        Menu teamMenu = getMenuTeam(stage, scene);

        Menu datamenu = getMenuPlayer(stage, scene);

        Menu eventmenu = getMenuEvent(stage, scene);

        menuBar.getMenus().addAll(getFileMenu(), teamMenu, eventmenu, datamenu);

        printConsole(ta);

        ((VBox) scene.getRoot()).getChildren().addAll(menuBar, vbox, ta);

        stage.setScene(scene);
        stage.show();

    }

    private void printConsole(TextArea ta) {
        Console console = new Console(ta);
        PrintStream ps = new PrintStream(console, true);
        System.setOut(ps);
        System.setErr(ps);
    }

    private Menu getMenuEvent(Stage stage, Scene scene) {
        Menu eventmenu = new Menu("Event");

        MenuItem newseason = getMenuItemSeason();

        MenuItem match = getMenuItemMatch();

        MenuItem teamboard = new MenuItem("View Leaderboard");
        teamboard.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {
                viewTeamBoard(stage, scene);
            }
        });

        MenuItem view = new MenuItem("View History");
        view.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {
                eventlog.printAll();
            }
        });

        eventmenu.getItems().addAll(newseason, match, teamboard, view);
        return eventmenu;
    }

    private MenuItem getMenuItemMatch() {
        MenuItem match = new MenuItem("New Match Day");
        match.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {
                season.newMatchDay();
                eventlog.update(season.getHistory());
            }
        });
        return match;
    }

    private MenuItem getMenuItemSeason() {
        MenuItem newseason = new MenuItem("New Season");
        newseason.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {
                season = new Season(teamlog.getCurrentTeams());
            }
        });
        return newseason;
    }

    private Menu getMenuPlayer(Stage stage, Scene scene) {
        Menu datamenu = new Menu("Player");


        MenuItem web = new MenuItem("Update Player Rating");
        web.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {
                fantasyWebData.updateFromWeb(teamlog.getCurrentTeams());
            }
        });

        MenuItem pool = new MenuItem("View Player Pool");
        pool.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {
                viewPlayerPool(stage, scene);
            }
        });


        datamenu.getItems().addAll(pool, web);
        return datamenu;
    }

    private Menu getMenuTeam(Stage stage, Scene scene) {
        Menu teamMenu = new Menu("Team");
        MenuItem add = new MenuItem("Add a New Team");
        add.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {
                addTeam(stage, scene);
            }
        });

        MenuItem delete = new MenuItem("Delete a Team");
        delete.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {
                deleteTeam(stage,scene);
            }
        });

        MenuItem review = getMenuItemReview();

        teamMenu.getItems().addAll(add, delete, review);
        return teamMenu;
    }

    private MenuItem getMenuItemReview() {
        MenuItem review = new MenuItem("Review all teams");
        review.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {
                System.out.println("All teams:" + teamlog.getCurrentTeams());
                //ta.setText("All teams:" + teamlog.getCurrentTeams());
            }
        });
        return review;
    }

    private Menu getFileMenu() {
        Menu fileMenu = new Menu("File");

        MenuItem load = getMenuItemLoad();

        MenuItem save = getMenuItemSave();

        MenuItem exit = new MenuItem("Exit");
        exit.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {
                System.exit(0);
            }
        });

        fileMenu.getItems().addAll(load,save,exit);
        return fileMenu;
    }

    private MenuItem getMenuItemSave() {
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
        return save;
    }

    private MenuItem getMenuItemLoad() {
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
        return load;
    }

    private void addTeam(Stage stage, Scene scene) {

        ArrayList<Player> assembleTeam = new ArrayList<>();

        Button button1 = getHomeButton(stage, scene);

        TextField teamname = new TextField();
        teamname.setPromptText("Please enter your team name");

        Button button2 = getButtonConfirmNewTeam(assembleTeam, teamname);

        VBox layout1 = new VBox();
        layout1.getChildren().addAll(button1, button2, teamname);

        final CheckBox[] cbs = getCheckBoxesPlayer(assembleTeam);

        VBox layout2 = new VBox();
        layout2.getChildren().addAll(cbs);

        ScrollPane sp = new ScrollPane();
        sp.setContent(layout2);

        layout1.getChildren().add(sp);

        Scene scene2 = new Scene(layout1, 450, 450);
        stage.setScene(scene2);

    }

    private Button getButtonConfirmNewTeam(ArrayList<Player> assembleTeam, TextField teamname) {
        Button button = new Button("Confirm");
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Team newTeam = new Team();
                newTeam.setTeamplayers(assembleTeam);
                newTeam.setTeamname(teamname.getText());
                teamlog.addTeam(newTeam);
            }
        });
        return button;
    }

    private CheckBox[] getCheckBoxesPlayer(ArrayList<Player> assembleTeam) {
        final CheckBox[] cbs = new CheckBox[50];

        for (int i = 0; i < 50; i++) {
            final CheckBox cb = cbs[i] = new CheckBox(playerpool.getPlayer(i).getName());
            int finalI = i;
            cb.selectedProperty().addListener(new ChangeListener<Boolean>() {
                public void changed(ObservableValue<? extends Boolean> ov,
                                    Boolean oldval, Boolean newval) {
                    if (newval) {
                        assembleTeam.add(playerpool.getPlayer(finalI));
                    }
                    if (!newval) {
                        assembleTeam.remove(playerpool.getPlayer(finalI));
                    }

                }
            });
        }
        return cbs;
    }

    private void deleteTeam(Stage stage, Scene scene) {

        ArrayList<Team> teamstodelete = new ArrayList<>();

        Button button1 = getHomeButton(stage, scene);

        Button button2 = getButtonConfirmDeleteTeam(teamstodelete);

        VBox layout1 = new VBox();

        final CheckBox[] cbs = getCheckBoxesTeam(teamstodelete);

        layout1.getChildren().addAll(button1, button2);
        layout1.getChildren().addAll(cbs);

        Scene scene2 = new Scene(layout1, 450, 450);
        stage.setScene(scene2);

    }

    private Button getButtonConfirmDeleteTeam(ArrayList<Team> teamstodelete) {
        Button button = new Button("Delete");
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                for (Team team: teamstodelete) {
                    teamlog.removeTeam(team);
                }
            }
        });
        return button;
    }

    private CheckBox[] getCheckBoxesTeam(ArrayList<Team> teamstodelete) {

        int teamcount = teamlog.getCurrentTeams().size();

        final CheckBox[] cbs = new CheckBox[teamcount];

        for (int i = 0; i < teamcount; i++) {
            final CheckBox cb = cbs[i] = new CheckBox(teamlog.getCurrentTeams().get(i).getTeamname());
            int finalI = i;
            cb.selectedProperty().addListener(new ChangeListener<Boolean>() {
                public void changed(ObservableValue<? extends Boolean> ov,
                                    Boolean oldval, Boolean newval) {
                    if (newval) {
                        teamstodelete.add(teamlog.getCurrentTeams().get(finalI));
                    }
                    if (!newval) {
                        teamstodelete.remove(teamlog.getCurrentTeams().get(finalI));
                    }

                }
            });
        }
        return cbs;
    }

    private void viewPlayerPool(Stage stage, Scene scene) {
        VBox layout = new VBox();
        layout.getChildren().addAll(getHomeButton(stage, scene), getPlayerTableView());
        stage.setScene(new Scene(layout, 300, 450));
    }

    private void viewTeamBoard(Stage stage, Scene scene) {
        VBox layout = new VBox();
        layout.getChildren().addAll(getHomeButton(stage, scene), getTeamTableView());
        stage.setScene(new Scene(layout, 300, 450));
    }


    private Button getHomeButton(Stage stage, Scene scene) {
        Button button1 = new Button("Go to Home");
        button1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                stage.setScene(scene);
            }
        });
        return button1;
    }


    private TableView<PlayerDisplay> getPlayerTableView() {
        TableView<PlayerDisplay> table = new TableView<>();

        TableColumn idCol = new TableColumn("id");
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn nameCol = new TableColumn("name");
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn overallCol = new TableColumn("overall");
        overallCol.setCellValueFactory(new PropertyValueFactory<>("overall"));

        table.setItems(playerpool.getData());

        table.getColumns().addAll(idCol, nameCol, overallCol);
        return table;
    }

    private TableView<TeamDisplay> getTeamTableView() {
        TableView<TeamDisplay> table = new TableView<>();

        TableColumn nameCol = new TableColumn("teamname");
        nameCol.setCellValueFactory(new PropertyValueFactory<>("teamname"));

        TableColumn winCol = new TableColumn("win");
        winCol.setCellValueFactory(new PropertyValueFactory<>("win"));

        TableColumn lossCol = new TableColumn("loss");
        lossCol.setCellValueFactory(new PropertyValueFactory<>("loss"));

        table.setItems(season.getData());

        table.getColumns().addAll(nameCol, winCol, lossCol);
        return table;
    }


}
