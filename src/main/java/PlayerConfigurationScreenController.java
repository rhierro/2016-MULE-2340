/**
 * Created by Henry on 9/9/2015.
 */

import com.sun.org.apache.xml.internal.security.Init;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import java.net.URL;
import java.util.ResourceBundle;

public class PlayerConfigurationScreenController{

    @FXML
    private TextField name_textfield;
    @FXML
    private ComboBox<String> race_combobox;
    @FXML
    private ColorPicker color_colorpicker;
    @FXML
    private Label testLabel;
    @FXML
    private Label error_label;

    private String name;
    private Color color = Color.WHITE;
    private String race;


    @FXML
    private void continuePressed(ActionEvent event) {
        if (name_textfield.getText().trim().equals("")) {
            error_label.setText("Please enter a name");
        } else{
            name = name_textfield.getText();
            race = race_combobox.getValue();
            color = color_colorpicker.getValue();
        }

    }
    @FXML
    private void inputboxEntered(KeyEvent event) {
        error_label.setText("");
    }

}
