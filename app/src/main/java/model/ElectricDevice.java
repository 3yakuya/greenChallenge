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


    public void setAmount(int amount) {
        this.amount = amount;
    }


    public void setPowerConsumption(int powerConsumption) {
        this.powerConsumption = powerConsumption;
    }


    public void setHoursPerDay(int hoursPerDay) {
        this.hoursPerDay = hoursPerDay;
    }


    public void setStandbyPowerConsumption(int standbyPowerConsumption) {
        this.standbyPowerConsumption = standbyPowerConsumption;
    }


    public void setStandbyHoursPerDay(int standbyHoursPerDay) {
        this.standbyHoursPerDay = standbyHoursPerDay;
    }

    @Override
    public boolean equals(Object other) {
        if (other == null) return false;
        if (!(other instanceof ElectricDevice)) return false;
        ElectricDevice otherElectricDevice = (ElectricDevice)other;
        return (otherElectricDevice.getName() == this.getName());
    }
}
