package greenfo.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;

import services.DataManager;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RelativeLayout layout =(RelativeLayout) findViewById(R.id.background);
        DataManager.loadUserDataFromFile(this);
        layout.setBackgroundResource(BackgroundHelper.selectBackgroundImage());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent;
        switch (item.getItemId()) {
            case R.id.action_show_user:
                intent = new Intent(getApplicationContext(), ShowUserActivity.class);
                startActivity(intent);
                return true;

            case R.id.action_show_authors:
                intent = new Intent(getApplicationContext(), AboutActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void showElectricInfo(View v) {
        Intent intent = new Intent(getApplicationContext(), ElectricInfoActivity.class);
        startActivity(intent);
    }

    public void showWaterInfo(View v) {
        Intent intent = new Intent(getApplicationContext(), WaterInfoActivity.class);
        startActivity(intent);
    }

    public void showRefuseInfo(View v) {
        Intent intent = new Intent(getApplicationContext(), RefuseInfoActivity.class);
        startActivity(intent);
    }
}
