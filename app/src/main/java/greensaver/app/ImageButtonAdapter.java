package greensaver.app;

import android.app.ActionBar;
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
            case 'C':
                return "computer";
            case 'P':
                return "printer";
            case 'S':
                return "set_top_box";
            case 'D':
                return "dvd_set";
            case 'M':
                return "microwave";
            default:
                return "ic_launcher";
        }
    }

}
