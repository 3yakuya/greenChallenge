package services;

import model.ElectricDevice;
import model.RefuseProduction;
import model.User;
import model.WaterActivity;

public class DataManager {

    private static DataManager instance = null;

    public static DataManager getInstance() {
        if (instance == null)
            instance = new DataManager();
        return instance;
    }

    public void storeElectricDeviceData(String name, int amount,
                                        int powerConsumption,
                                        int hoursPerDay,
                                        int standbyPowerConsumption,
                                        int standbyHoursPerDay) {
        ElectricDevice electricDevice = new ElectricDevice(name, amount, powerConsumption,
                hoursPerDay, standbyPowerConsumption, standbyHoursPerDay);
        User user = User.getInstance();
        user.insertElectricDevice(electricDevice);
    }

    public void storeWaterActivityData(String name, int litersUsed, int timesPerDay) {
        WaterActivity waterActivity = new WaterActivity(name, litersUsed, timesPerDay);
        User user = User.getInstance();
        user.insertWaterActivity(waterActivity);
    }

    public void storeRefuseProductionData(String name, int pointValue) {
        RefuseProduction refuseProduction = new RefuseProduction(name, pointValue);
        User user = User.getInstance();
        user.insertRefuseProduction(refuseProduction);
    }

    public void cloneElectricDevice(ElectricDevice source, ElectricDevice target) {
        target.setAmount(source.getAmount());
        target.setPowerConsumption(source.getPowerConsumption());
        target.setHoursPerDay(source.getHoursPerDay());
        target.setStandbyPowerConsumption(source.getStandbyPowerConsumption());
        target.setStandbyHoursPerDay(source.getStandbyHoursPerDay());
    }

    public void cloneWaterActivity(WaterActivity source, WaterActivity target) {
        target.setLitersUsed(source.getLitersUsed());
        target.setTimesPerDay(source.getTimesPerDay());
    }

    public void cloneRefuseProduction(RefuseProduction source, RefuseProduction target) {
        target.setPointValue(source.getPointValue());
    }
}
