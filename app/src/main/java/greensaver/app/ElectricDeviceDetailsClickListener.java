package greensaver.app;

import android.content.Intent;
import android.view.View;

public class ElectricDeviceDetailsClickListener implements View.OnClickListener {

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(view.getContext(), AddElectricDeviceActivity.class);
        view.getContext().startActivity(intent);
    }
}
