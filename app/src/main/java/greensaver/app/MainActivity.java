package greensaver.app;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageButton;
import android.widget.RelativeLayout;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //////////////////////////////////////////TEST stuff /////////////////////////////
        RelativeLayout layout =(RelativeLayout) findViewById(R.id.background);
        layout.setBackgroundResource(R.drawable.background_main);
        ImageButton electricityImageButton = (ImageButton) findViewById(R.id.electricityImageButton);
        ImageButton waterImageButton = (ImageButton) findViewById(R.id.waterImageButton);
        ImageButton refuseImageButton = (ImageButton) findViewById(R.id.refuseImageButton);
        electricityImageButton.setImageResource(R.drawable.ic_launcher);
        waterImageButton.setImageResource(R.drawable.ic_launcher);
        refuseImageButton.setImageResource(R.drawable.ic_launcher);
        ActionBar actionBar = getActionBar();
        actionBar.show();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
