package greensaver.app;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;

import model.ElectricDevice;
import model.FullSelection;
import model.INamable;
import model.NameAndType;
import model.RefuseProduction;
import model.WaterActivity;
import services.DataManager;

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
            String name = elements[position].getName();
            NameAndType selection = NameAndType.recognizeNameAndType(name, FullSelection.getInstance());
            int drawableResourceId = context.getResources().getIdentifier(selection.getName(), "drawable", context.getPackageName());
            imageButton.setImageResource(drawableResourceId);
            imageButton.setTag(name);
            this.assignOnClickListener(imageButton);
            this.setButtonSelectionBackground(imageButton, selection.getType(), name);
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

    private void assignOnClickListener(ImageButton button) {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), AddElementBasicActivity.class);
                intent.putExtra("selectionName", (String) view.getTag());
                view.getContext().startActivity(intent);
            }
        });
    }

    private void setButtonSelectionBackground(ImageButton imageButton, int type, String name){
        if (isSelected(type, name))
            imageButton.setBackground(this.context.getResources().getDrawable(R.drawable.image_button_selection_border));
    }

    private boolean isSelected(int type, String name) {
        switch (type) {
            case 0:
                if (DataManager.fetchElectricDeviceData().contains(new ElectricDevice(name)))
                    return true;
                else
                    return false;
            case 1:
                if (DataManager.fetchWaterActivityData().contains(new WaterActivity(name)))
                    return true;
                else
                    return false;
            case 2:
                if (DataManager.fetchRefuseProductionData().contains(new RefuseProduction(name)))
                    return true;
                else
                    return false;
            default:
                return false;
        }
    }

}