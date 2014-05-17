package model;

public class RefuseProduction {
    //TODO add all necessary fields and methods.

    private String name;
    private int pointValue;

    public RefuseProduction(String name, int pointValue) {
        this.name = name;
        this.pointValue = pointValue;
    }

    public RefuseProduction() {
        this("DefaultRefuseProduction", 0);
    }

    public int getPointValue() {
        return pointValue;
    }
}
