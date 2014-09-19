package modelTests;

import android.test.InstrumentationTestCase;

import model.FullSelection;
import model.NameAndType;

public class NameAndTypeTest extends InstrumentationTestCase {

    public void testRecognizeNameAndTypeElectricDevice() {
        FullSelection fullSelection = FullSelection.getInstance();
        String name = fullSelection.electricDeviceNames[5];
        NameAndType one = NameAndType.recognizeNameAndType(name, fullSelection);
        assertEquals(one.getName(), "set_top_box");
        assertEquals(one.getType(), 0);
        assertEquals(fullSelection.electricDeviceNames[one.getIndex()], name);
    }

    public void testRecognizeNameAndTypeWaterActivity() {
        FullSelection fullSelection = FullSelection.getInstance();
        String name = fullSelection.waterActivityNames[6];
        NameAndType one = NameAndType.recognizeNameAndType(name, fullSelection);
        assertEquals(one.getName(), "brushing_teeth_and_shaving");
        assertEquals(one.getType(), 1);
        assertEquals(fullSelection.waterActivityNames[one.getIndex()], name);
    }

    public void testRecognizeNameAndTypeRefuseProduction() {
        FullSelection fullSelection = FullSelection.getInstance();
        String name = fullSelection.refuseProductionNames[5];
        NameAndType one = NameAndType.recognizeNameAndType(name, fullSelection);
        assertEquals(one.getName(), "household_facilities");
        assertEquals(one.getType(), 2);
        assertEquals(fullSelection.refuseProductionNames[one.getIndex()], name);
    }
}
