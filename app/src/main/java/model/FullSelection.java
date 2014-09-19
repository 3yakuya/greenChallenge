package model;

import java.util.ArrayList;

public class FullSelection {

    private static FullSelection instance;

    private ArrayList<ElectricDevice> electricDevices;
    private ArrayList<WaterActivity> waterActivities;
    private ArrayList<RefuseProduction> refuseProductions;

    private FullSelection() {
        this.electricDevices = new ArrayList<ElectricDevice>();
        this.waterActivities = new ArrayList<WaterActivity>();
        this.refuseProductions = new ArrayList<RefuseProduction>();
        this.prepareElectricDevices();
        this.prepareWaterActivities();
        this.prepareRefuseProductions();
    }

    public static FullSelection getInstance() {
        if (instance == null)
            instance = new FullSelection();
        return instance;
    }

    public final String[] electricDeviceNames = {
            "TV", "Radio", "Computer", "Printer", "Router", "Set Top Box", "DVD set", "Microwave"
    };
    public final String[] waterActivityNames = {
            "Washing machine", "Dishwasher", "Washing up", "Bath", "Shower", "Cleaning hands",
            "Brushing teeth and shaving", "Washing the car"
    };
    public final String[] refuseProductionNames = {
            "Segregation", "Plastic bags", "Pressing bottles", "Medicine", "Batteries and bulbs",
            "Household facilities", "Big size waste"
    };

    public final int[] minPowerConsumptions = {50, 5, 30, 5, 3, 5, 10, 650};
    public final int[] maxPowerConsumptions = {100, 50, 300, 170, 10, 45, 40, 1750};
    public final int[] minStandbyPowerConsumptions = {1, 1, 1, 0, 3, 3, 5, 1};
    public final int[] maxStandbyPowerConsumptions = {6, 25, 98, 5, 8, 40, 15, 40};
    public final int[] maxDeviceTime = {16, 16, 16, 2, 24, 16, 16, 2};
    public final int[] minDeviceTime = {1, 1, 1, 0, 1, 1, 1, 0};
    public final int[] minWaterUsage = {45, 9, 30, 120, 30, 1, 10, 50};
    public final int[] maxWaterUsage = {60, 25, 80, 150, 40, 2, 15, 150};
    public final int[] maxWaterTimesPerDay = {2, 2, 5, 3, 3, 10, 5, 2};
    public final int[] minWaterTimesPerDay = {1, 1, 1, 1, 1, 3, 2, 0};
    public final int[] refusePointValues = {10, 8, 4, 5, 5, 5, 5};


    public ArrayList<ElectricDevice> getAllElectricDevices() {
        return this.electricDevices;
    }

    public ArrayList<WaterActivity> getAllWaterActivities() {
        return this.waterActivities;
    }

    public ArrayList<RefuseProduction> getAllRefuseProductions() {
        return this.refuseProductions;
    }

    private void prepareElectricDevices() {
        this.electricDevices.add(new ElectricDevice(electricDeviceNames[0]));
        this.electricDevices.add(new ElectricDevice(electricDeviceNames[1]));
        this.electricDevices.add(new ElectricDevice(electricDeviceNames[2]));
        this.electricDevices.add(new ElectricDevice(electricDeviceNames[3]));
        this.electricDevices.add(new ElectricDevice(electricDeviceNames[4]));
        this.electricDevices.add(new ElectricDevice(electricDeviceNames[5]));
        this.electricDevices.add(new ElectricDevice(electricDeviceNames[6]));
        this.electricDevices.add(new ElectricDevice(electricDeviceNames[7]));
    }

    private void prepareWaterActivities() {
        this.waterActivities.add(new WaterActivity(waterActivityNames[0]));
        this.waterActivities.add(new WaterActivity(waterActivityNames[1]));
        this.waterActivities.add(new WaterActivity(waterActivityNames[2]));
        this.waterActivities.add(new WaterActivity(waterActivityNames[3]));
        this.waterActivities.add(new WaterActivity(waterActivityNames[4]));
        this.waterActivities.add(new WaterActivity(waterActivityNames[5]));
        this.waterActivities.add(new WaterActivity(waterActivityNames[6]));
        this.waterActivities.add(new WaterActivity(waterActivityNames[7]));
    }

    private void prepareRefuseProductions() {
        this.refuseProductions.add(new RefuseProduction(refuseProductionNames[0]));
        this.refuseProductions.add(new RefuseProduction(refuseProductionNames[1]));
        this.refuseProductions.add(new RefuseProduction(refuseProductionNames[2]));
        this.refuseProductions.add(new RefuseProduction(refuseProductionNames[3]));
        this.refuseProductions.add(new RefuseProduction(refuseProductionNames[4]));
        this.refuseProductions.add(new RefuseProduction(refuseProductionNames[5]));
        this.refuseProductions.add(new RefuseProduction(refuseProductionNames[6]));
    }
}
