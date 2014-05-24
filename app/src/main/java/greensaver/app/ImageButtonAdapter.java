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
            //String name = elements[position].getName();
            //int drawableResourceId = context.getResources().getIdentifier(name, "drawable", context.getPackageName());
            //Test stuff below:
            int drawableResourceId = context.getResources().getIdentifier("ic_launcher", "drawable", context.getPackageName());
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

}
