package modelTests;

import android.test.InstrumentationTestCase;

import model.RefuseProduction;

public class RefuseProductionTest extends InstrumentationTestCase {

    public void testCloneRefuseProduction() {
        RefuseProduction refuseProductionOne = new RefuseProduction("RefuseOne", 10);
        RefuseProduction refuseProductionTwo = new RefuseProduction();
        refuseProductionTwo.cloneRefuseProduction(refuseProductionOne);
        assertEquals(refuseProductionOne.getPointValue(), refuseProductionTwo.getPointValue());
    }
}
