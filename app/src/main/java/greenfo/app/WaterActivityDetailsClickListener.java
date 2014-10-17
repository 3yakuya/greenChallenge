package greenfo.app;

import android.content.Intent;
import android.view.View;

public class WaterActivityDetailsClickListener implements View.OnClickListener {

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(view.getContext(), AddWaterActivityActivity.class);
        intent.putExtra("selectionName", (String) view.getTag());
        view.getContext().startActivity(intent);
    }
}
