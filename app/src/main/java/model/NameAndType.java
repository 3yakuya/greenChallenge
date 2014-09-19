package model;

import java.util.Arrays;

public class NameAndType {

    private String name;
    private int type;
    private int index;

    private NameAndType(String name, int type, int index) {
        this.name = name;
        this.type = type;
        this.index = index;
    }

    public String getName() {
        return this.name;
    }

    public int getType() {
        return this.type;
    }

    public int getIndex() {
        return this.index;
    }

    public static NameAndType recognizeNameAndType(String name, FullSelection fullSelection) {
        String lowerCaseName = name.toLowerCase();
        String properName = lowerCaseName.replace(' ', '_');
        int type;
        int index = Arrays.asList(fullSelection.electricDeviceNames).indexOf(name);
        if (index > -1)
            type = 0;
        else {
            index = Arrays.asList(fullSelection.waterActivityNames).indexOf(name);
            if (index > -1)
                type = 1;
            else {
                index = Arrays.asList(fullSelection.refuseProductionNames).indexOf(name);
                type = 2;
            }
        }
        return new NameAndType(properName, type, index);
    }
}