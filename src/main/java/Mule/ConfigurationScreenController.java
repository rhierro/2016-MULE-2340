package Mule;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.scene.Parent;
import sun.applet.Main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ConfigurationScreenController {

    @FXML
    private Label difficulty_label;
    @FXML
    private Label map_label;
    @FXML
    private Label players_label;
    @FXML
    private Button increase_difficulty;
    @FXML
    private Button decrease_difficulty;
    @FXML
    private Button increase_map;
    @FXML
    private Button decrease_map;
    @FXML
    private Button increase_players;
    @FXML
    private Button decrease_players;
    @FXML
    private Button next_button;

    private int difficulty = 1;
    private int map = 0;
    private int players = 1;
    private MainController mc;

    @FXML
    private void increaseDifficulty(ActionEvent event) {
        if (difficulty < 3) {
            difficulty++;
        }
        if (difficulty == 1) {
            difficulty_label.setText("Beginner");
        } else if (difficulty == 2) {
            difficulty_label.setText("Medium");
        } else if (difficulty == 3) {
            difficulty_label.setText("Expert");
        }
    }

    @FXML
    private void decreaseDifficulty(ActionEvent event) {
        if (difficulty > 0) {
            difficulty--;
        }
        if (difficulty == 1) {
            difficulty_label.setText("Beginner");
        } else if (difficulty == 2) {
            difficulty_label.setText("Medium");
        } else if (difficulty == 3) {
            difficulty_label.setText("Expert");
        }
    }

    @FXML
     private void increaseMap(ActionEvent event) {
        if (map < 3) {
            map++;
        }
        if (map == 0) {
            map_label.setText("Map1");
        } else if (map == 1) {
            map_label.setText("Map2");
        } else if (map == 2) {
            map_label.setText("Map3");
        }
    }

    @FXML
    private void decreaseMap(ActionEvent event) {
        if (map > 0) {
            map--;
        }
        if (map == 0) {
            map_label.setText("Map1");
        } else if (map == 1) {
            map_label.setText("Map2");
        } else if (map == 2) {
            map_label.setText("Map3");
        }
    }

    @FXML
    private void increasePlayers(ActionEvent event) {
        if (players < 4) {
            players++;
        }
        players_label.setText(String.valueOf(players));
    }

    @FXML
    private void decreasePlayers(ActionEvent event) {
        if (players > 1) {
            players--;
        }
        players_label.setText(String.valueOf(players));
    }

    @FXML
    private void next(ActionEvent event) throws Exception{
        mc.setDifficulty(difficulty);
        mc.setMap(setupMap(map));
        mc.setNumberOfPlayers(players);
        mc.setMap(setupMap(map));
        mc.setMapNum(map);
        Stage stageN = (Stage) ((Node) event.getSource()).getScene()
                .getWindow();
        mc.loadPlayerConfigurationScreen();




    }

    private ObservableList<Land[]> setupMap (int m ) {
        Land[][] landArray = new Land[5][5];
        List<int[]> mountainArray = new ArrayList<>();
        List<int[]> waterArray = new ArrayList<>();
        for (int i = 0; i < landArray.length; i++) {
            for (int j = 0; j < landArray[0].length; j++) {
                landArray[i][j] = new Land();
            }
        }
        if (m == 0) {
            int[][] mountains = {{0, 1}, {1, 1}, {1, 2}, {3, 3}, {4, 3}, {3, 4}, {4, 4}};
            int[][] waters = {{2, 0}, {3, 0}, {4, 0}, {2, 1}, {2, 2}, {2, 3}, {1, 3}, {1, 4}};

            for (int[] mtn : mountains) {
                mountainArray.add(mtn);
            }
            for (int[] wtr : waters) {
                waterArray.add(wtr);
            }
        } else if (m == 1) {
            int[][] mountains = {{3, 0}, {0, 1}, {1, 1}, {4, 1}, {1, 3}, {2, 3}, {0, 4}, {1, 4}};
            int[][] waters = {{2, 0}, {3, 1}, {0, 2}, {1, 2}, {3, 2}, {3, 3}, {4, 4}};

            for (int[] mtn : mountains) {
                mountainArray.add(mtn);
            }
            for (int[] wtr : waters) {
                waterArray.add(wtr);
            }
        }
        if ((waterArray.size() != 0) && (mountainArray.size() != 0)) {
            for (int[] mountain : mountainArray) {

                landArray[mountain[0]][mountain[1]].setType("mountain");
            }

            for (int[] water : waterArray) {
                landArray[water[0]][water[1]].setType("water");
            }
        }

        ObservableList<Land[]> map = FXCollections.observableArrayList(landArray);
        return map;




    }

    public void setMainController(MainController mc) {
        this.mc = mc;
    }


}

