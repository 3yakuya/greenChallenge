package model;


public class ElectricDevice implements INamable {

    private String name;
    private int powerConsumption;           /** Watts */
    private int hoursPerDay;
    private int standbyPowerConsumption;    /** Watts */
    private int standbyHoursPerDay;


    public ElectricDevice(String name,
                          int powerConsumption,
                          int hoursPerDay,
                          int standbyPowerConsumption,
                          int standbyHoursPerDay) {
        this.name = name;
        this.powerConsumption = powerConsumption;
        this.hoursPerDay = hoursPerDay;
        this.standbyPowerConsumption = standbyPowerConsumption;
        this.standbyHoursPerDay = standbyHoursPerDay;
    }

    public ElectricDevice(String name) {
        this.name = name;
        this.powerConsumption = 0;
        this.hoursPerDay = 0;
        this.standbyPowerConsumption = 0;
        this.standbyHoursPerDay = 0;
    }


    public ElectricDevice() {
        this("DefaultElectricDevice", 0, 0, 0, 0);
    }


    public String getName() {
        return this.name;
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
        return (otherElectricDevice.getName().equals(this.getName()));
    }

    public void cloneElectricDevice(ElectricDevice source) {
        this.setPowerConsumption(source.getPowerConsumption());
        this.setHoursPerDay(source.getHoursPerDay());
        this.setStandbyPowerConsumption(source.getStandbyPowerConsumption());
        this.setStandbyHoursPerDay(source.getStandbyHoursPerDay());
    }
}
