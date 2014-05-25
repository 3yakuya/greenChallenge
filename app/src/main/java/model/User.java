package model;

import java.util.ArrayList;

import services.DataManager;
import services.UsageCalculator;

public class User {
    //TODO decide on refuseProductionPoints units.

    private static User instance = null;

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

    public void insertElectricDevice(ElectricDevice electricDevice) {
        int index = this.electricDevices.indexOf(electricDevice);
        if (index >= 0) {
            this.electricDevices.get(index).cloneElectricDevice(electricDevice);
        } else {
            this.electricDevices.add(electricDevice);
        }
    }

    public void insertWaterActivity(WaterActivity waterActivity) {
        int index = this.waterActivities.indexOf(waterActivity);
        if (index >= 0) {
            this.waterActivities.get(index).cloneWaterActivity(waterActivity);
        } else {
            this.waterActivities.add(waterActivity);
        }
    }

    public void insertRefuseProduction(RefuseProduction refuseProduction) {
        int index = this.refuseProductions.indexOf(refuseProduction);
        if (index >= 0) {
            this.refuseProductions.get(index).cloneRefuseProduction(refuseProduction);
        } else {
            this.refuseProductions.add(refuseProduction);
        }
    }

    public void reset() {
        this.electricDevices.clear();
        this.waterActivities.clear();
        this.refuseProductions.clear();
    }

}