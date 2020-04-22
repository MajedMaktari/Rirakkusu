package com.example.rirakkusu.ui.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.rirakkusu.R;
import com.example.rirakkusu.SingleFragmentActivity;
import com.example.rirakkusu.ui.help.HelpActivity;
import com.example.rirakkusu.ui.session.SessionActivity;

import java.util.UUID;

public class HomeActivty extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new HomeFragment();
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }
}
