package greenfo.app;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.GridView;

import java.util.ArrayList;

import model.ElectricDevice;
import model.FullSelection;
import model.RefuseProduction;
import model.WaterActivity;


public class ShowUserActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_user);
        BackgroundHelper.setBackground(this);

        GridView electricDevicesGrid = (GridView) findViewById(R.id.electric_devices_grid);
        GridView waterActivityGrid = (GridView) findViewById(R.id.water_activity_grid);
        GridView refuseProductionGrid = (GridView) findViewById(R.id.refuse_production_grid);

        FullSelection fullSelection = FullSelection.getInstance();

        ArrayList<ElectricDevice> fetchedElectricDeviceData = fullSelection.getAllElectricDevices();
        ElectricDevice[] electricDevices =
                fetchedElectricDeviceData.toArray(new ElectricDevice[fetchedElectricDeviceData.size()]);
        ArrayList<WaterActivity> fetchedWaterActivityData = fullSelection.getAllWaterActivities();
        WaterActivity[] waterActivities =
                fetchedWaterActivityData.toArray(new WaterActivity[fetchedWaterActivityData.size()]);
        ArrayList<RefuseProduction> fetchedRefuseProductionData = fullSelection.getAllRefuseProductions();
        RefuseProduction[] refuseProductions =
                fetchedRefuseProductionData.toArray(new RefuseProduction[fetchedRefuseProductionData.size()]);

        electricDevicesGrid.setAdapter(new ImageButtonAdapter(this, electricDevices));
        waterActivityGrid.setAdapter(new ImageButtonAdapter(this, waterActivities));
        refuseProductionGrid.setAdapter(new ImageButtonAdapter(this, refuseProductions));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.show_user, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.reset_all_user_elements:
                ResetDialog resetDialog = new ResetDialog();
                resetDialog.show(getFragmentManager(), "reset");
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
