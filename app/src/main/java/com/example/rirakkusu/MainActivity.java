package com.example.rirakkusu;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.rirakkusu.ui.help.HelpActivity;
import com.example.rirakkusu.ui.home.HomeFragment;
import com.example.rirakkusu.ui.preferences.SettingsActivity;
import com.example.rirakkusu.ui.session.SessionActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.preference.PreferenceManager;

import java.util.Calendar;

import static android.app.AlarmManager.*;

public class MainActivity extends SingleFragmentActivity {


    private SharedPreferences savedValues;

    @Override
    protected Fragment createFragment() {
        return new HomeFragment();
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        PreferenceManager.setDefaultValues(this, R.xml.root_preferemces, false);
        savedValues = PreferenceManager.getDefaultSharedPreferences(this);

        //Reminder
        if (savedValues.getBoolean(getResources().getString(R.string.reminder_preference), true)){
            //alarmService
            AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.HOUR, 24);
            //calendar.add(Calendar.SECOND, 10); // use this for testing


            Intent intent = new Intent("rirakkusu.action.DISPLAY_NOTIFICATION");
            PendingIntent broadcast = PendingIntent.getBroadcast(this, 72, intent, PendingIntent.FLAG_UPDATE_CURRENT);

            alarmManager.setExact(RTC_WAKEUP, calendar.getTimeInMillis(), broadcast);
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.bottom_nav_menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.navigation_home) {
            return true;
        }

        switch(item.getItemId()) {
            case R.id.navigation_home:
                startActivity(new Intent(getApplicationContext(),HomeFragment.class));
                return true;
            case R.id.navigation_preferences:
                startActivity(new Intent(getApplicationContext(), SettingsActivity.class));
                return true;
            case R.id.navigation_help:
                startActivity(new Intent(getApplicationContext(), HelpActivity.class));
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


}
