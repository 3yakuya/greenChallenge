package greensaver.app;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;

import model.INamable;

public class ImageButtonAdapter extends BaseAdapter{
    private Context context;
    private INamable[] elements;

    public ImageButtonAdapter(Context context, INamable[] elements) {
        this.context = context;
        this.elements = elements;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ImageButton imageButton;

        if (convertView == null) {
            imageButton = new ImageButton(this.context);
            NameAndType selection = this.recognizeName(elements[position].getName());
            int drawableResourceId = context.getResources().getIdentifier(selection.name, "drawable", context.getPackageName());
            imageButton.setImageResource(drawableResourceId);
            imageButton.setTag(selection.name);
            this.assignOnClickListener(imageButton, selection.type);
        } else {
            imageButton = (ImageButton) convertView;
        }
        imageButton.setId(position);

        return imageButton;
    }

    @Override
    public int getCount() {
        return elements.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    private class NameAndType {
        String name;
        int type;

        private NameAndType(String name, int type) {
            this.name = name;
            this.type = type;
        }
    }

    private NameAndType recognizeName(String name) {
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

    private void assignOnClickListener(ImageButton button, int type) {
        if (type == 0)
            button.setOnClickListener(new ElectricDeviceDetailsClickListener());
        else if (type == 1)
            button.setOnClickListener(new WaterActivityDetailsClickListener());
        else
            button.setOnClickListener(new RefuseProductionDetailsClickListener());

    }

}
