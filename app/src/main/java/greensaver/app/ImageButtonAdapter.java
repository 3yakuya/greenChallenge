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
            String name = this.recognizeName(elements[position].getName());
            int drawableResourceId = context.getResources().getIdentifier(name, "drawable", context.getPackageName());
            imageButton.setImageResource(drawableResourceId);
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

    private String recognizeName(String name) {
        char first = name.charAt(0);
        switch (first) {
            case 'T':
                return "tv";
            case 'R':
                if (name.charAt(1) == 'a')
                    return "radio";
                else
                    return "router";
            case 'B':
                if (name.charAt(3) == 'h')
                    return "bath";
                else if (name.charAt(3) == 't')
                    return "batteries_and_bulbs";
                else if (name.charAt(1) == 'i')
                    return "big_size_waste";
                else
                    return "brushing_shaving";
            case 'C':
                if (name.charAt(1) == 'o')
                    return "computer";
                else
                    return "cleaning_hands";
            case 'H':
                return "household";
            case 'P':
                if (name.charAt(2) == 'i')
                    return "printer";
                else if (name.charAt(2) == 'e')
                    return "pressing_bottles";
                else
                    return "plastic_bags";
            case 'S':
                if (name.charAt(2) == 't')
                    return "set_top_box";
                else if (name.charAt(2) == 'g')
                    return "segregation";
                else
                    return "shower";
            case 'D':
                if (name.charAt(1) == 'v')
                    return "dvd_set";
                else
                    return "dishwasher";
            case 'M':
                if (name.charAt(1) == 'i')
                    return "microwave";
                else
                    return "medicine";
            case 'W':
                if (name.charAt(8) == 'm')
                    return "washing_machine";
                else if (name.charAt(8) == 'u')
                    return "washing_up";
                else
                    return "washing_car";
            default:
                return "ic_launcher";
        }
    }

}
