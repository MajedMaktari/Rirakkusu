package com.example.rirakkusu.ui.preferences;

import android.content.res.Resources;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.rirakkusu.R;
import com.example.rirakkusu.SingleFragmentActivity;

public class SettingsActivity extends SingleFragmentActivity {

    private static Resources resources;

    @Override
    protected Fragment createFragment() {
        return new PreferencesFragment();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        resources = getResources();
    }

    public static Resources getAppResources() {
        return resources;

    }


}
