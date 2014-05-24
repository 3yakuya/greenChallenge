package greensaver.app;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;


public class AddElectricDeviceActivity extends Activity
        implements AdapterView.OnItemSelectedListener, SeekBar.OnSeekBarChangeListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_electric_device);
        /////////////////////TEST stuff//////////////////////
        setListenerForAllBars();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.add_electric_device, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return super.onOptionsItemSelected(item);
    }

    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
        // An item was selected. You can retrieve the selected item using
        // parent.getItemAtPosition(pos)
    }

    public void onNothingSelected(AdapterView<?> parent) {
    }

    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        TextView textProgress = findTextViewHavingSeekBar(seekBar);
        if (textProgress == null)
            return;

        textProgress.setText(Integer.toString(progress));
    }

    public void onStartTrackingTouch(SeekBar seekBar) {
    }

    public void onStopTrackingTouch(SeekBar seekBar) {
    }

    private void setElectricDeviceSpinner() {
        Spinner spinner = (Spinner)findViewById(R.id.electric_device_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.electric_device_spinner_list, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
    }

    private TextView findTextViewHavingSeekBar(SeekBar seekBar) {
        TextView textProgress;
        int id = seekBar.getId();
        switch (id) {
            case R.id.electric_device_amount_bar:
                textProgress = (TextView) findViewById(R.id.electric_device_amount_box);
                break;
            case R.id.electric_device_power_consumption_bar:
                textProgress = (TextView) findViewById(R.id.electric_device_power_consumption_box);
                break;
            case R.id.electric_device_hours_per_day_bar:
                textProgress = (TextView) findViewById(R.id.electric_device_hours_per_day_box);
                break;
            case R.id.electric_device_standby_power_bar:
                textProgress = (TextView) findViewById(R.id.electric_device_standby_power_box);
                break;
            case R.id.electric_device_standby_hours_bar:
                textProgress = (TextView) findViewById(R.id.electric_device_standby_hours_box);
                break;
            default:
                textProgress = null;
                break;
        }
        return textProgress;
    }

    private void setListenerForAllBars() {
        SeekBar seekBar;
        seekBar = (SeekBar) findViewById(R.id.electric_device_amount_bar);
        seekBar.setOnSeekBarChangeListener(this);
        seekBar = (SeekBar) findViewById(R.id.electric_device_power_consumption_bar);
        seekBar.setOnSeekBarChangeListener(this);
        seekBar = (SeekBar) findViewById(R.id.electric_device_hours_per_day_bar);
        seekBar.setOnSeekBarChangeListener(this);
        seekBar = (SeekBar) findViewById(R.id.electric_device_standby_power_bar);
        seekBar.setOnSeekBarChangeListener(this);
        seekBar = (SeekBar) findViewById(R.id.electric_device_standby_hours_bar);
        seekBar.setOnSeekBarChangeListener(this);
    }

}
