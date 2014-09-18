package greensaver.app;

public class NameAndType {

    private String name;
    private int type;

    public String getName() {
        return this.name;
    }

    public int getType() {
        return this.type;
    }

    private NameAndType(String name, int type) {
        this.name = name;
        this.type = type;
    }

    public static NameAndType recognizeNameAndType(String name) {
        char first = name.charAt(0);
        switch (first) {
            case 'T':
                return new NameAndType("tv", 0);
            case 'R':
                if (name.charAt(1) == 'a')
                    return new NameAndType("radio", 0);
                else
                    return new NameAndType("router", 0);
            case 'B':
                if (name.charAt(3) == 'h')
                    return new NameAndType("bath", 1);
                else if (name.charAt(3) == 't')
                    return new NameAndType("batteries_and_bulbs", 2);
                else if (name.charAt(1) == 'i')
                    return new NameAndType("big_size_waste", 2);
                else
                    return new NameAndType("brushing_shaving", 1);
            case 'C':
                if (name.charAt(1) == 'o')
                    return new NameAndType("computer", 0);
                else
                    return new NameAndType("cleaning_hands", 1);
            case 'H':
                return new NameAndType("household", 2);
            case 'P':
                if (name.charAt(2) == 'i')
                    return new NameAndType("printer", 0);
                else if (name.charAt(2) == 'e')
                    return new NameAndType("pressing_bottles", 2);
                else
                    return new NameAndType("plastic_bags", 2);
            case 'S':
                if (name.charAt(2) == 't')
                    return new NameAndType("set_top_box", 0);
                else if (name.charAt(2) == 'g')
                    return new NameAndType("segregation", 2);
                else
                    return new NameAndType("shower", 1);
            case 'D':
                if (name.charAt(1) == 'V')
                    return new NameAndType("dvd_set", 0);
                else
                    return new NameAndType("dishwasher", 1);
            case 'M':
                if (name.charAt(1) == 'i')
                    return new NameAndType("microwave", 0);
                else
                    return new NameAndType("medicine", 2);
            case 'W':
                if (name.charAt(8) == 'm')
                    return new NameAndType("washing_machine", 1);
                else if (name.charAt(8) == 'u')
                    return new NameAndType("washing_up", 1);
                else
                    return new NameAndType("washing_car", 1);
            default:
                return new NameAndType("ic_launcher", 0);
        }
    }

    public static int getElectricDeviceNumberFromName(String name) {
        if (name.equals("tv"))
            return 0;
        else if (name.equals("radio"))
            return 1;
        else if (name.equals("computer"))
            return 2;
        else if (name.equals("printer"))
            return 3;
        else if (name.equals("router"))
            return 4;
        else if (name.equals("set_top_box"))
            return 5;
        else if (name.equals("dvd_set"))
            return 6;
        else if (name.equals("microwave"))
            return 7;
        else
            return 0;
    }

    public static int getWaterActivityNumberFromName(String name) {
        if (name.equals("washing_machine"))
            return 0;
        else if (name.equals("dishwasher"))
            return 1;
        else if (name.equals("washing_up"))
            return 2;
        else if (name.equals("bath"))
            return 3;
        else if (name.equals("shower"))
            return 4;
        else if (name.equals("cleaning_hands"))
            return 5;
        else if (name.equals("brushing_shaving"))
            return 6;
        else if (name.equals("washing_car"))
            return 7;
        else
            return 0;
    }

    public static int getRefuseProductionNumberFromName(String name) {
        if (name.equals("segregation"))
            return 0;
        else if (name.equals("plastic_bags"))
            return 1;
        else if (name.equals("pressing_bottles"))
            return 2;
        else if (name.equals("medicine"))
            return 3;
        else if (name.equals("batteries_and_bulbs"))
            return 4;
        else if (name.equals("household"))
            return 5;
        else if (name.equals("big_size_waste"))
            return 6;
        else
            return 0;
    }
}
