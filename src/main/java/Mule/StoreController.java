package Mule;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;

/**
 * Created by Henry on 10/5/2015.
 * The Store Controller class
 */
public class StoreController {

    private MainController mc;
    private Player currentPlayer;
    private Store currentStore;

    @FXML
    private ListView<Store.Item> player_listView;
    @FXML
    private ListView<Store.Item> store_listView;
    @FXML
    private Button toStore_button;
    @FXML
    private Button toPlayer_button;
    @FXML
    private Label storeAmount_label;
    @FXML
    private Label playerAmount_label;
    @FXML
    private Label playerMoney_label;
    @FXML
    private Button back_button;

    private String generateSpaces(int totalLength, int wordLength) {
        int difference = totalLength - wordLength;
        String s = "";
        for (int i = 0; i < difference; i++) {
            s += " ";
        }
        return s;
    }

    private void initialize() {

        playerMoney_label.setText(String.format("%d",
                currentPlayer.getMoney()));

        store_listView.setItems(FXCollections.observableArrayList(currentStore.getInventory()));
        store_listView.setCellFactory(new Callback<ListView<Store.Item>,
                ListCell<Store.Item>>() {
            @Override
            public ListCell<Store.Item> call(ListView<Store.Item> param) {
                return new ListCell<Store.Item>() {
                    @Override
                    protected void updateItem(Store.Item item,
                                              boolean empty) {
                        super.updateItem(item, empty);
                        if (item != null) {
                            setText(item + generateSpaces(12,
                                            item.toString().length())
                                            + "Amount:"
                                            + currentStore.
                                            getInventoryAmount(item)
                                            + "     "
                                            + "Price:"
                                            + currentStore.getPrice(item)
                            );
                        }
                    }
                };
            }
        });



        player_listView.setItems(FXCollections.observableArrayList((currentPlayer.getInventory())));
        player_listView.
                setCellFactory(new Callback<ListView<Store.Item>,
                        ListCell<Store.Item>>() {
                    @Override
            public ListCell<Store.Item> call(ListView<Store.Item> param) {
                        return new ListCell<Store.Item>() {
                            @Override
                    protected void updateItem(Store.Item item,
                                boolean empty) {
                                super.updateItem(item, empty);
                                if (item != null) {
                                    setText(item
                                        + generateSpaces(12, item.toString().
                                        length())
                                        + "Amount:"
                                        + currentPlayer.
                                        getInventoryAmount(item)
                                        + "     "
                                        + "Price:"
                                        + currentStore.getPrice(item)
                                    );
                                }
                            }
                        };
                    }
                });

    }

    @FXML
    private void toStorePressed(ActionEvent event) {
        Store.Item item = player_listView.getSelectionModel().
                getSelectedItem();
        if (item != null) {
            if (currentPlayer.getInventoryAmount(item) > 0) {
                currentPlayer.adjustMoney(currentStore.getPrice(item));
                currentPlayer.changeInventory(item, -1);
                currentStore.changeInventory(item, 1);
                playerMoney_label.setText(String.format("%d",
                        currentPlayer.getMoney()));
                refreshLists();
            }
        }

    }

    @FXML
    private void toPlayerPressed(ActionEvent event) {
        Store.Item item = store_listView.getSelectionModel().
                getSelectedItem();
        if (item != null) {
            if (currentPlayer.getMoney() > currentStore.getPrice(item)) {
                if (currentStore.getInventoryAmount(item) > 0) {
                    currentPlayer.adjustMoney(-currentStore.getPrice(item));
                    currentPlayer.changeInventory(item, 1);
                    currentStore.changeInventory(item, -1);
                    playerMoney_label.setText(String.format("%d",
                            currentPlayer.getMoney()));
                    refreshLists();
                }
            }
        }
    }

    @FXML
    private void backPressed(ActionEvent event) {
        mc.switchtoTownMapScreen();
    }

    private void refreshLists() {
        ObservableList<Store.Item> playerTemp = player_listView.getItems();
        ObservableList<Store.Item> storeTemp = store_listView.getItems();
        player_listView.setItems(null);
        player_listView.setItems(playerTemp);
        store_listView.setItems(null);
        store_listView.setItems(storeTemp);
    }

    /**
     * Sets the reference back to the main controller
     * @param mc maincontroller object
     */
    public void setMainController(MainController mc) {
        this.mc = mc;
        this.currentStore = mc.getStore();
        this.currentPlayer = mc.getCurrentPlayer();
        initialize();
    }
}
