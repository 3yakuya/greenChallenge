package greensaver.app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ElectricDeviceDetailsClickListener implements View.OnClickListener {

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(view.getContext(), AddElectricDeviceActivity.class);
        intent.putExtra("deviceName", (String) view.getTag());
        view.getContext().startActivity(intent);
    }
}
