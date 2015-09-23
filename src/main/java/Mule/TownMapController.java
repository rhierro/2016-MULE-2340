package Mule;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

/**
 * Created by Henry on 9/22/2015.
 */
public class TownMapController {

    private MainController mc;

    @FXML
    private Button returnToMap_button;
    @FXML
    private Button pub_button;

    @FXML
    private void returntoMapPressed(ActionEvent event) throws Exception {
        mc.loadMainMapScreen();
    }

    @FXML
    private void pubPressed(ActionEvent event) throws Exception {
        //todo: end turn
        mc.endTurn();
    }

    public void setMainController(MainController mc) {
        this.mc = mc;
    }
}
