package model;

import java.util.ArrayList;
import services.UsageCalculator;

public class User {
    //TODO decide on refuseProduction units.

    private static User instance = null;

    private int powerUsage;         /** kWh/week */
    private int standbyPowerUsage;  /** kWh/week */
    private int waterUsage;         /** Liters/week */
    private int refuseProduction;   /** own units */
    private UsageCalculator usageCalculator;

    private ArrayList<ElectricDevice> electricDevices;
    private ArrayList<WaterActivity> waterActivities;
    private ArrayList<RefuseProduction> refuseProductions;

    private User() {
        this.electricDevices = new ArrayList<ElectricDevice>();
        this.waterActivities = new ArrayList<WaterActivity>();
        this.refuseProductions = new ArrayList<RefuseProduction>();
    }

    public User getInstance() {
        if (instance == null)
            instance = new User();
        return instance;
    }

    public void setUserStats() {
        usageCalculator.getInstance();
        this.powerUsage = usageCalculator.calculateDailyPowerUsage(this.electricDevices);
        this.standbyPowerUsage = usageCalculator.calculateDailyStandbyPowerUsage(this.electricDevices);
        this.waterUsage = usageCalculator.calculateDailyWaterUsage(this.waterActivities);
        this.refuseProduction = usageCalculator.calculateRefuseProductionPoints(this.refuseProductions);
    }

    public int getPowerUsage() {
        return this.powerUsage;
    }

    public int getStandbyPowerUsage() {
        return this.standbyPowerUsage;
    }

    public int getWaterUsage() {
        return this.waterUsage;
    }

    public int getRefuseProduction() {
        return this.refuseProduction;
    }

    public void addElectricDevice(ElectricDevice electricDevice) {
        int index = this.electricDevices.indexOf(electricDevice);
        if (index >= 0)
            this.electricDevices.get(index).increaseAmount();
        else
            this.electricDevices.add(electricDevice);
    }

    public void addWaterActivity(WaterActivity waterActivity) {
        int index = this.waterActivities.indexOf(waterActivity);
        if (index >= 0)
            this.waterActivities.get(index).increaseTimesPerDay();
        else
            this.waterActivities.add(waterActivity);
    }

    public void addRefuseProduction(RefuseProduction refuseProduction) {
        int index = this.refuseProductions.indexOf(refuseProduction);
        if (index >= 0)
            return;
        this.refuseProductions.add(refuseProduction);
    }

}