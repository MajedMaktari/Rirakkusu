package com.example.rirakkusu.ui.preferences;

import android.content.res.Resources;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.rirakkusu.R;

public class SettingsActivity extends AppCompatActivity {

    private static Resources resources;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_preferences);
        getSupportFragmentManager().beginTransaction().replace(R.id.settings, new PreferencesFragment())
        .commit();

        resources = getResources();
    }

    public static Resources getAppResources() {
        return resources;

    }


}
