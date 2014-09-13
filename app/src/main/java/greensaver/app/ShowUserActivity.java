package greensaver.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import java.util.ArrayList;

import model.ElectricDevice;
import model.RefuseProduction;
import model.WaterActivity;
import services.DataManager;
import services.DataSaver;


public class ShowUserActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_user);
        DataManager dataManager = DataManager.getInstance();

        GridView electricDevicesGrid = (GridView) findViewById(R.id.electric_devices_grid);
        GridView waterActivityGrid = (GridView) findViewById(R.id.water_activity_grid);
        GridView refuseProductionGrid = (GridView) findViewById(R.id.refuse_production_grid);

        ArrayList<ElectricDevice> fetchedElectricDeviceData = dataManager.fetchElectricDeviceData();
        ElectricDevice[] electricDevices =
                fetchedElectricDeviceData.toArray(new ElectricDevice[fetchedElectricDeviceData.size()]);
        ArrayList<WaterActivity> fetchedWaterActivityData = dataManager.fetchWaterActivityData();
        WaterActivity[] waterActivities =
                fetchedWaterActivityData.toArray(new WaterActivity[fetchedWaterActivityData.size()]);
        ArrayList<RefuseProduction> fetchedRefuseProductionData = dataManager.fetchRefuseProductionData();
        RefuseProduction[] refuseProductions =
                fetchedRefuseProductionData.toArray(new RefuseProduction[fetchedRefuseProductionData.size()]);

        electricDevicesGrid.setAdapter(new ImageButtonAdapter(this, electricDevices));
        waterActivityGrid.setAdapter(new ImageButtonAdapter(this, waterActivities));
        refuseProductionGrid.setAdapter(new ImageButtonAdapter(this, refuseProductions));

        electricDevicesGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                //TODO call method showing add electric device activity.
            }
        });
        waterActivityGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                //TODO call method showing add water activity activity.
            }
        });
        refuseProductionGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                //TODO call method showing add refuse production activity.
            }
        });
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
        Intent intent;
        switch (item.getItemId()) {
            case R.id.action_add_electric_device:
                intent = new Intent(getApplicationContext(), AddElectricDeviceActivity.class);
                startActivity(intent);
                return true;

            case R.id.action_add_water_activity:
                intent = new Intent(getApplicationContext(), AddWaterActivityActivity.class);
                startActivity(intent);
                return true;

            case R.id.action_add_refuse_production:
                intent = new Intent(getApplicationContext(), AddRefuseProductionActivity.class);
                startActivity(intent);
                return true;

            case R.id.reset_all_user_elements:
                DataManager dataManager = DataManager.getInstance();
                dataManager.resetAllUserElements();
                DataSaver.saveDataToFile(this);
                intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
