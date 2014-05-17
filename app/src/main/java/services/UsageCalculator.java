package services;

import java.util.ArrayList;

import model.ElectricDevice;
import model.RefuseProduction;
import model.WaterActivity;

public class UsageCalculator {

    private static UsageCalculator instance = null;

    public UsageCalculator getInstance() {
        if (instance == null)
            instance = new UsageCalculator();
        return instance;
    }

    public int calculateDailyPowerUsage(ArrayList<ElectricDevice> electricDevices) {
        int powerUsage = 0;
        for (ElectricDevice device: electricDevices)
            powerUsage += device.getDailyTotalPowerUsage();
        return powerUsage;
    }

    public int calculateDailyStandbyPowerUsage(ArrayList<ElectricDevice> electricDevices) {
        int powerUsage = 0;
        for (ElectricDevice device: electricDevices)
            powerUsage += device.getDailyStandbyPowerUsage();
        return powerUsage;
    }

    public int calculateDailyWaterUsage(ArrayList<WaterActivity> waterActivities) {
        int waterUsage = 0;
        for (WaterActivity waterActivity : waterActivities)
            waterUsage += waterActivity.getDailyWaterUsage();
        return waterUsage;
    }

    public int calculateRefuseProductionPoints(ArrayList<RefuseProduction> refuseProductions) {
        int refuseProductionPoints = 0;
        for (RefuseProduction refuseProduction : refuseProductions)
            refuseProductionPoints += refuseProduction.getPointValue();
        return refuseProductionPoints;
    }
}
