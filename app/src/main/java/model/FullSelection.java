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
            "General segregation", "Multi use bags", "Pressing bottles", "Disposing medicine", "Disposing batteries and bulbs",
            "Disposing household facilities", "Disposing big size waste"
    };

    public final int[] minPowerConsumptions = {50, 5, 30, 5, 3, 5, 10, 650};
    public final int[] maxPowerConsumptions = {100, 50, 300, 170, 10, 45, 40, 1750};
    public final int[] minStandbyPowerConsumptions = {1, 1, 1, 0, 3, 3, 5, 1};
    public final int[] maxStandbyPowerConsumptions = {6, 25, 98, 5, 8, 40, 15, 40};
    public final int[] maxDeviceTime = {16, 16, 16, 2, 24, 16, 16, 2};
    public final int[] minDeviceTime = {1, 1, 1, 0, 1, 1, 1, 0};
    public final int[] minWaterUsage = {35, 9, 10, 40, 10, 1, 1, 50};
    public final int[] maxWaterUsage = {60, 30, 80, 120, 40, 2, 15, 150};
    public final int[] maxWaterTimesPerDay = {2, 2, 5, 3, 3, 10, 5, 2};
    public final int[] minWaterTimesPerDay = {1, 1, 1, 1, 1, 3, 2, 0};
    public final int[] refusePointValues = {10, 8, 4, 5, 5, 5, 5};
    public final int[] powerLevelLimits = {6000, 3000};
    public final int[] waterLevelLimits = {160, 100};
    public final int[] refuseLevelLimits = {14, 28};

    public final String[] electricTips = {
            "When selecting electric devices check their energy class.",
            "If you have a device you rarely use, pull its plug out of an outlet.",
            "Fridge and freezer should stand as far away from heaters as possible.",
            "A single bulb gives less light than two bulbs with half as much power each.",
            "Buy energy saver bulbs for all the rooms you spend a lot of time in.",
            "If you are going to walk away from your computer for a longer while, put it to sleep.",
            "More powerful kettles boil water faster and therefore loose less energy.",
            "Traditional or plasma-screen TVs are much more energy consuming than LED ones.",
            "Ink printers usually consume much less power than laser ones.",
            "Turn off the TV if you are not watching it. Do the same with the radio.",
            "Remember - saving power is not only good for the planet. It is also great for your wallet."
    };
    public final String[] waterTips = {
            "Turn on the washing machine or dishwasher only after it is full, or use energy-saving mode.",
            "Gather rain water and use it to water the plants.",
            "Always turn off the tap while brushing your teeth or shaving.",
            "When washing your car use a sponge and a bucket of water. Use a garden hose only to rinse the car.",
            "Do not use running water to defrost food (place it in a fridge the night before instead.)",
            "Don't pour out water you can use to wash something or to water the plants.",
            "Avoid unnecessary flushing the toilet (for example, after you throw a single tissue in.)",
            "Water the plants late in the evening to avoid excessive transpiration (this is good for plants too!)",
            "Fix a leaking tap, check all the valves and gaskets.",
            "Use a single-handle tap. Using two spigots to regulate temperature wastes both water and time.",
            "Don't make a mistake to think that water is an infinite resource - it is not."
    };
    public final String[] refuseTips = {
            "Food packagings are usually made of many materials. Segregate them as plastic.",
            "There is no need to wash empty yoghurt cups - it's just a waste of water.",
            "Crush plastic bottles, cartons and cans before throwing them away. This will save a lot " +
                    "of space in the bin.",
            "When you throw a bottle away, throw its cap and label seperately - it is good for recycling.",
            "Do not throw diapers, wallpapers or sanitary towels to bins for paper.",
            "Old batteries, electronic devices or paints should be brought to specialistic points.",
            "In many countries you pay less if you segregate your waste. Save the environment and money " +
                    "at once!",
            "Old windows, windscreens, mirrors, ceramic elements or light bulbs should not be segregated " +
                    "as glass.",
            "Do not throw disposing_medicine away after its date expired - bring it to a local pharmacy instead.",
            "If you are unsure what to do with some kind of rubbish or waste, " +
            "check it online. Intuition is not a good advisor here."
    };


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
