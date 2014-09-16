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
        DataManager.storeWaterActivityData("Cleaning Hands", 1, 5);/////////////////////////
        DataManager.storeWaterActivityData("Dishwasher", 30, 1);/////////////////////////
        DataManager.storeRefuseProductionData("Segregation", 8);//////////////////////////
        DataManager.storeRefuseProductionData("Medicine", 5);


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_user);

        GridView electricDevicesGrid = (GridView) findViewById(R.id.electric_devices_grid);
        GridView waterActivityGrid = (GridView) findViewById(R.id.water_activity_grid);
        GridView refuseProductionGrid = (GridView) findViewById(R.id.refuse_production_grid);

        ArrayList<ElectricDevice> fetchedElectricDeviceData = DataManager.fetchElectricDeviceData();
        ElectricDevice[] electricDevices =
                fetchedElectricDeviceData.toArray(new ElectricDevice[fetchedElectricDeviceData.size()]);
        ArrayList<WaterActivity> fetchedWaterActivityData = DataManager.fetchWaterActivityData();
        WaterActivity[] waterActivities =
                fetchedWaterActivityData.toArray(new WaterActivity[fetchedWaterActivityData.size()]);
        ArrayList<RefuseProduction> fetchedRefuseProductionData = DataManager.fetchRefuseProductionData();
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
        Intent intent;
        switch (item.getItemId()) {

            case R.id.reset_all_user_elements:
                DataManager.resetAllUserElements();
                DataSaver.saveDataToFile(this);
                intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
