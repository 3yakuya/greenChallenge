package modelTests;

import android.test.InstrumentationTestCase;

import model.WaterActivity;

public class WaterActivityTest extends InstrumentationTestCase {

    public void testCloneWaterActivity() {
        WaterActivity waterActivityOne = new WaterActivity("ActivityOne", 10, 10);
        WaterActivity waterActivityTwo = new WaterActivity();
        waterActivityTwo.cloneWaterActivity(waterActivityOne);
        assertEquals(waterActivityOne.getLitersUsed(), waterActivityTwo.getLitersUsed());
        assertEquals(waterActivityOne.getTimesPerDay(), waterActivityTwo.getTimesPerDay());
    }
}
