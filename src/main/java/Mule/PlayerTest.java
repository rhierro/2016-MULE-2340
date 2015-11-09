package Mule;

import javafx.scene.paint.Color;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by Dana on 11/8/2015.
 */
public class PlayerTest {

    private Player pEnergyFull;
    private Player pSmithoreFull;
    private Player pFoodFull;
    private Player pEnergyEmpty;
    private Player pSmithoreEmpty;
    private Player pFoodEmpty;
    private Player pESF;
    private Player pES;
    private Player pEF;
    private Player pSF;

    public static final int TIMEOUT = 200;

    @Before
    public void setUp() {
        pEnergyFull = new Player("Name", "Race", Color.RED);
        pSmithoreFull = new Player("Name", "Race", Color.RED);
        pFoodFull = new Player("Name", "Race", Color.RED);

        pEnergyEmpty = new Player("Name", "Race", Color.RED);
        pSmithoreEmpty = new Player("Name", "Race", Color.RED);
        pFoodEmpty = new Player("Name", "Race", Color.RED);

        pESF = new Player("Name", "Race", Color.RED);
        pES = new Player("Name", "Race", Color.RED);
        pEF = new Player("Name", "Race", Color.RED);
        pSF = new Player("Name", "Race", Color.RED);

        pSmithoreFull.changeInventory(Store.Item.Smithore, 5);
        pSmithoreFull.changeInventory(Store.Item.Energy, -6);

        pFoodFull.changeInventory(Store.Item.Food, 9);
        pFoodFull.changeInventory(Store.Item.Energy, -6);

        pEnergyEmpty.changeInventory(Store.Item.Energy, -6);
        pSmithoreEmpty.changeInventory(Store.Item.Energy, -10);
        pFoodEmpty.changeInventory(Store.Item.Energy, -6);

        pSF.changeInventory(Store.Item.Food, 4);
        pSF.changeInventory(Store.Item.Smithore, 6);
        pSF.changeInventory(Store.Item.Energy, -6);

        pEF.changeInventory(Store.Item.Food, 12);
        pES.changeInventory(Store.Item.Smithore, 10);

        pESF.changeInventory(Store.Item.Smithore, 7);
        pESF.changeInventory(Store.Item.Food, 8);
    }

    @Test(timeout = TIMEOUT)
    public void testGetScore() {
        assertEquals(6696969, pEnergyFull.getScore());
        assertEquals(5696969, pSmithoreFull.getScore());
        assertEquals(9696969, pFoodFull.getScore());

        assertEquals(696969, pEnergyEmpty.getScore());
        assertEquals(696969, pSmithoreEmpty.getScore());
        assertEquals(696969, pFoodEmpty.getScore());

        assertEquals(21696969, pESF.getScore());
        assertEquals(16696969, pES.getScore());
        assertEquals(18696969, pEF.getScore());
        assertEquals(10696969, pSF.getScore());
    }
}
