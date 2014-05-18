package model;

public class ElectricDevice {

    private String name;
    private int amount;
    private int powerConsumption;           /** Watts */
    private int hoursPerDay;
    private int standbyPowerConsumption;    /** Watts */
    private int standbyHoursPerDay;

    public ElectricDevice(String name, int amount,
                          int powerConsumption,
                          int hoursPerDay,
                          int standbyPowerConsumption,
                          int standbyHoursPerDay) {
        this.name = name;
        this.amount = amount;
        this.powerConsumption = powerConsumption;
        this.hoursPerDay = hoursPerDay;
        this.standbyPowerConsumption = standbyPowerConsumption;
        this.standbyHoursPerDay = standbyHoursPerDay;
    }

    public ElectricDevice() {
        this("DefaultElectricDevice", 0, 0, 0, 0, 0);
    }

    public String getName() {
        return this.name;
    }

    public int getAmount() {
        return this.amount;
    }

    public int getPowerConsumption() {
        return this.powerConsumption;
    }

    public int getHoursPerDay() {
        return this.hoursPerDay;
    }

    public int getStandbyPowerConsumption() {
        return this.standbyPowerConsumption;
    }

    public int getStandbyHoursPerDay() {
        return this.standbyHoursPerDay;
    }

    public int getDailyStandbyPowerUsage() {
        /** Result in Wh */
        return amount*standbyPowerConsumption*standbyHoursPerDay;
    }

    public int getDailyTotalPowerUsage() {
        /** Result in Wh */
        int standbyPowerUsage = getDailyStandbyPowerUsage();
        return standbyPowerUsage + amount*powerConsumption*hoursPerDay;
    }

    public void increaseAmount() {
        this.amount++;
    }
}
