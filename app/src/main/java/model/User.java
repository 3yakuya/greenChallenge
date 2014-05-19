package model;

import java.util.ArrayList;

import services.DataManager;
import services.UsageCalculator;

public class User {
    //TODO decide on refuseProductionPoints units.

    private static User instance = null;

    private int powerUsage;         /** Wh/week */
    private int standbyPowerUsage;  /** Wh/week */
    private int waterUsage;         /** Liters/week */
    private int refuseProductionPoints;   /** own units */

    private ArrayList<ElectricDevice> electricDevices;
    private ArrayList<WaterActivity> waterActivities;
    private ArrayList<RefuseProduction> refuseProductions;

    private User() {
        this.electricDevices = new ArrayList<ElectricDevice>();
        this.waterActivities = new ArrayList<WaterActivity>();
        this.refuseProductions = new ArrayList<RefuseProduction>();
    }

    public static User getInstance() {
        if (instance == null)
            instance = new User();
        return instance;
    }

    public ArrayList<ElectricDevice> getElectricDevices() {
        return this.electricDevices;
    }

    public ArrayList<WaterActivity> getWaterActivities() {
        return this.waterActivities;
    }

    public ArrayList<RefuseProduction> getRefuseProductions() {
        return this.refuseProductions;
    }

    public void setUserStats() {
        UsageCalculator usageCalculator = UsageCalculator.getInstance();
        this.powerUsage = usageCalculator.calculateDailyPowerUsage(this.electricDevices);
        this.standbyPowerUsage = usageCalculator.calculateDailyStandbyPowerUsage(this.electricDevices);
        this.waterUsage = usageCalculator.calculateDailyWaterUsage(this.waterActivities);
        this.refuseProductionPoints = usageCalculator.calculateRefuseProductionPoints(this.refuseProductions);
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

    public int getRefuseProductionPoints() {
        return this.refuseProductionPoints;
    }

    public void insertElectricDevice(ElectricDevice electricDevice) {
        int index = this.electricDevices.indexOf(electricDevice);
        if (index >= 0) {
            DataManager dataManager = DataManager.getInstance();
            ElectricDevice existingElectricDevice = this.electricDevices.get(index);
            dataManager.cloneElectricDevice(electricDevice, existingElectricDevice);
        } else {
            this.electricDevices.add(electricDevice);
        }
    }

    public void insertWaterActivity(WaterActivity waterActivity) {
        int index = this.waterActivities.indexOf(waterActivity);
        if (index >= 0) {
            DataManager dataManager = DataManager.getInstance();
            WaterActivity existingWaterActivity = this.waterActivities.get(index);
            dataManager.cloneWaterActivity(waterActivity, existingWaterActivity);
        } else {
            this.waterActivities.add(waterActivity);
        }
    }

    public void insertRefuseProduction(RefuseProduction refuseProduction) {
        int index = this.refuseProductions.indexOf(refuseProduction);
        if (index >= 0) {
            DataManager dataManager = DataManager.getInstance();
            RefuseProduction existingRefuseProduction = this.refuseProductions.get(index);
            dataManager.cloneRefuseProduction(refuseProduction, existingRefuseProduction);
        } else {
            this.refuseProductions.add(refuseProduction);
        }
    }

}