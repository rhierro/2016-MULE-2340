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
    Button returnToMap_button;

    @FXML
    private void returntoMapPressed(ActionEvent event) throws Exception{
        mc.loadMainMapScreen();
    }

    public void setMainController(MainController mc) {
        this.mc = mc;
    }
}
