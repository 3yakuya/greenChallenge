package services;

import model.ElectricDevice;
import model.RefuseProduction;
import model.WaterActivity;

public class DataManager {

    private static DataManager instance = null;

    public static DataManager getInstance() {
        if (instance == null)
            instance = new DataManager();
        return instance;
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
