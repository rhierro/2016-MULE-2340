// Annotations
import org.junit.Before;
import org.junit.Test;
import org.junit.After;
// Assertions
import static org.junit.Assert.fail;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertArrayEquals;

public class GetInventoryAmountTest {

    @Test
    public void testEmptyStore() {
        assertEquals(0, getInventoryAmount(Item.Food));
    }

    @Before
    public void setUp() {
        generatePrices();
    }

    @Test
    public void testGeneratedStore() {
        assertEquals(69, getInventoryAmount(Item.Food));
    }
}
