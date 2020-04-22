package com.example.rirakkusu.ui.help;

import android.content.Context;
import android.content.Intent;

import androidx.fragment.app.Fragment;

import com.example.rirakkusu.SingleFragmentActivity;
import com.example.rirakkusu.ui.home.HomeActivty;

import java.util.UUID;

public class HelpActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new HelpFragment();
    }

    public static Intent newIntent(Context packageContext, int position) {
        Intent intent = new Intent(packageContext, HelpActivity.class);
        return intent;
    }
}
