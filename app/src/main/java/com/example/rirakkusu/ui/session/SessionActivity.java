package com.example.rirakkusu.ui.session;

import android.content.Context;
import android.content.Intent;

import androidx.fragment.app.Fragment;

import com.example.rirakkusu.SingleFragmentActivity;
import com.example.rirakkusu.ui.help.HelpActivity;

public class SessionActivity extends SingleFragmentActivity {

    public static final String EXTRA_SESSION_TIME= "edu.ivytech.newsreader.rss_item";

    @Override
    protected Fragment createFragment() {
        return new SessionFragment();
    }

    public static Intent newIntent(Context packageContext, int position) {
        Intent intent = new Intent(packageContext, SessionActivity.class);
        intent.putExtra(EXTRA_SESSION_TIME, position);
        return intent;
    }
}
