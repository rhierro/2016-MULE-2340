/**
 * Created by Henry on 9/8/2015.
 */

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.KeyCode;


public class MuleGame extends Application {

    private ObservableList<Player> players = FXCollections.observableArrayList();
    private MainController mc;

    public MuleGame() {
    //Constructor
    }

    public void start(Stage stage) throws Exception {
        mc = new MainController();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("ConfigurationScreen.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        ConfigurationScreenController controller = loader.getController();
        controller.setMainController(mc);


    }

}
