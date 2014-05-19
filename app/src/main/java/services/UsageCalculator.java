package services;

import java.util.ArrayList;

import model.ElectricDevice;
import model.RefuseProduction;
import model.WaterActivity;

public class UsageCalculator {

    private static UsageCalculator instance = null;

    public static UsageCalculator getInstance() {
        if (instance == null)
            instance = new UsageCalculator();
        return instance;
    }

    public int calculateDailyPowerUsage(ArrayList<ElectricDevice> electricDevices) {
        int powerUsage = 0;
        for (ElectricDevice device: electricDevices)
            powerUsage += calculateElectricDeviceDailyTotalPowerUsage(device);
        return powerUsage;
    }

    public int calculateDailyStandbyPowerUsage(ArrayList<ElectricDevice> electricDevices) {
        int powerUsage = 0;
        for (ElectricDevice device: electricDevices)
            powerUsage += calculateElectricDeviceDailyStandbyPowerUsage(device);
        return powerUsage;
    }

    public int calculateDailyWaterUsage(ArrayList<WaterActivity> waterActivities) {
        int waterUsage = 0;
        for (WaterActivity waterActivity : waterActivities)
            waterUsage += calculateWaterActivityDailyWaterUsage(waterActivity);
        return waterUsage;
    }

    public int calculateRefuseProductionPoints(ArrayList<RefuseProduction> refuseProductions) {
        int refuseProductionPoints = 0;
        for (RefuseProduction refuseProduction : refuseProductions)
            refuseProductionPoints += refuseProduction.getPointValue();
        return refuseProductionPoints;
    }

    private int calculateElectricDeviceDailyStandbyPowerUsage(ElectricDevice electricDevice) {
        /** Result in Wh */
        int amount = electricDevice.getAmount();
        int standbyPowerConsumption = electricDevice.getStandbyPowerConsumption();
        int standbyHoursPerDay = electricDevice.getStandbyHoursPerDay();
        return amount*standbyPowerConsumption*standbyHoursPerDay;
    }

    private int calculateElectricDeviceDailyTotalPowerUsage(ElectricDevice electricDevice) {
        /** Result in Wh */
        int amount = electricDevice.getAmount();
        int powerConsumption = electricDevice.getPowerConsumption();
        int hoursPerDay = electricDevice.getHoursPerDay();
        int standbyPowerUsage = calculateElectricDeviceDailyStandbyPowerUsage(electricDevice);
        return standbyPowerUsage + amount*powerConsumption*hoursPerDay;
    }

    private int calculateWaterActivityDailyWaterUsage(WaterActivity waterActivity) {
        int litersUsed = waterActivity.getLitersUsed();
        int timesPerDay = waterActivity.getTimesPerDay();
        return litersUsed*timesPerDay;
    }
}
