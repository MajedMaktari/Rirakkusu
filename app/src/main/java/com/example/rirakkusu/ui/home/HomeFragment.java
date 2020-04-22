package com.example.rirakkusu.ui.home;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.preference.PreferenceManager;

import com.example.rirakkusu.R;
import com.example.rirakkusu.ui.help.HelpActivity;
import com.example.rirakkusu.ui.session.SessionActivity;
import com.example.rirakkusu.ui.session.SessionFragment;

public class HomeFragment extends Fragment {

    private SeekBar timeSeek;
    private EditText homeTitleEditText;
    private EditText timeEditText;

    private int sessionTime = 5;
    private SharedPreferences savedValues;
    private String userName;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Prefrence manger
        PreferenceManager.setDefaultValues(getActivity(), R.xml.root_preferemces, false);
        savedValues = PreferenceManager.getDefaultSharedPreferences(getActivity());
        userName = savedValues.getString(getResources().getString(R.string.user_name_key), "!");



    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_home, container, false);
        final TextView homeTitle = root.findViewById(R.id.homeTitle);

        // title changer to user name
        homeTitle.setText("Welcome " + userName + "!");
        //event listener for seek bar
        timeEditText = root.findViewById(R.id.timeEditText);
        timeSeek = root.findViewById(R.id.seekBar);
        timeSeek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                timeEditText.setText(String.format("%d", progress));
                setSessionTime(progress);
                Log.e ("session Time", String.valueOf(getSessionTime()));
                sessionTime = progress;
                Log.e ("session Time", String.valueOf(sessionTime));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                int progress = seekBar.getProgress();
                timeEditText.setText(String.format("%d", progress));
                //setSessionTime(progress);


            }
        });

        Button startSession = root.findViewById(R.id.startBtn);
        startSession.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =SessionActivity.newIntent(getActivity(), sessionTime);
                startActivity(intent);
            }
        });



        return root;
    }

    public void StartMeditation() {
        Intent intent = new Intent(getActivity(), SessionFragment.class);
        startActivity(intent);
    }

    public void setSessionTime(int time) {
        Log.e ("session Time setter", String.valueOf(sessionTime));
        this.sessionTime = time;

    }

    public int getSessionTime() {
        Log.e ("session Time Getter", String.valueOf(sessionTime));
        return sessionTime;
    }


}