package greensaver.app;

import android.content.Intent;
import android.view.View;

public class WaterActivityDetailsClickListener implements View.OnClickListener {

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(view.getContext(), AddWaterActivityActivity.class);
        view.getContext().startActivity(intent);
    }
}
