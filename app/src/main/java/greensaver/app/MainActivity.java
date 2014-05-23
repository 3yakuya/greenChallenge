package greensaver.app;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
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
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_show_user:
                //TODO call method to show user's elements.
                return true;
            case R.id.action_show_authors:
                //TODO call method to show authors.
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
