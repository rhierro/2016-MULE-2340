package Mule;
import java.util.Random;
import java.util.PriorityQueue;


/**
 * Created by David on 10/19/2015.
 */
public class RandomEvent {
    private int event;
    private Player first;
    private Player last;
    private Random rand = new Random();
    private MainController mc;

    private void rollEvent() {
        int eventRoll = rand.nextInt(11);
    }

    private void calcPlace(PriorityQueue players) {
        Player[] playerArray = (Player[]) players.toArray();
        first = playerArray[0];
        last = playerArray[0];
        for(Player player : playerArray) {
            if (player.getScore() > first.getScore()) {
                first = player;
            }
            if (player.getScore() < last.getScore()) {
                last = player;
            }
        }
    }

    public void causeEvent(PriorityQueue players, Player ) {
        RandomEvent rand2PointO = new RandomEvent();
        rand2PointO.rollEvent();
        rand2PointO.calcPlace(players);
        if(rand2PointO.event == 1 ) {
            rand2PointO.decreaseProduction();
        } else if(rand2PointO.event == 2) {
            rand2PointO.decreaseTime();
        } else if(rand2PointO.event == 3) {
            rand2PointO.loseMoney();
        } else if(rand2PointO.event == 4) {
            rand2PointO.loseMule();
        } else if(rand2PointO.event == 5) {
            rand2PointO.loseResources();
        } else if(rand2PointO.event == 6) {
            rand2PointO.gainResources();
        } else if(rand2PointO.event == 7) {
            rand2PointO.increaseProduction();
        } else if(rand2PointO.event == 8) {
            rand2PointO.increaseTime();
        }
    }




    private void loseMule() {

    }

    private void loseMoney(Player player) {
        player.adjustMoney(-rand.nextInt(player.getMoney()));
    }

    private void increaseProduction() {

    }

    private void decreaseProduction() {

    }

    private void increaseTime() {

    }

    private void decreaseTime() {

    }

    private void gainResources(Player player) {
        int roll = rand.nextInt(3) + 1;
        if (roll == 1) {
            player.changeInventory(Store.Item.Smithore, rand.nextInt(3) + 1);
        } else if (roll == 2) {
            player.changeInventory(Store.Item.Energy, rand.nextInt(3) + 1);
        } else if (roll == 3) {
            player.changeInventory(Store.Item.Food, rand.nextInt(3) + 1);
        }
    }

    private void loseResources(Player player) {
        int roll = rand.nextInt(3) + 1;
        if (roll == 1) {
            player.changeInventory(Store.Item.Smithore, -rand.nextInt(player.getInventoryAmount(Store.Item.Smithore)));
        } else if (roll == 2) {
            player.changeInventory(Store.Item.Energy, -rand.nextInt(player.getInventoryAmount(Store.Item.Energy)));
        } else if (roll == 3) {
            player.changeInventory(Store.Item.Food, -rand.nextInt(player.getInventoryAmount(Store.Item.Food)));
        }
    }


    public void setMainController(MainController mc) {
        this.mc = mc;
    }


}
