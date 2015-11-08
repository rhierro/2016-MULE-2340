import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Ruiz on 11/8/2015.
 */
public class StoreTest {

    @Before
    public void setUp() throws Exception {
        generatePrices();
    }

    @Test
    public void testGetInventoryAmount() throws Exception {
        assertEquals(0, getInventoryAmount(Mule.Store.Item.Food));
    }
}
