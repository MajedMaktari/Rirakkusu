package com.example.rirakkusu.ui.home;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.preference.PreferenceManager;

import com.example.rirakkusu.Feelings;
import com.example.rirakkusu.R;
import com.example.rirakkusu.FeelingsLab;
import com.example.rirakkusu.ui.session.SessionActivity;
import com.example.rirakkusu.ui.session.SessionFragment;

public class HomeFragment extends Fragment {

    private Feelings mFeeling;
    private SeekBar timeSeek;
    private EditText homeTitleEditText;
    private EditText timeEditText;
    private CheckBox feeling1, feeling2, feeling3, feeling4, feeling5, feeling6, feeling7, feeling8, feeling9;
    private String userFeelings = "";
    private RadioGroup feelingGroup;
    private int sessionTime = 5;
    private SharedPreferences savedValues;
    private String userName;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mFeeling = new Feelings();


        //Prefrence manger
        PreferenceManager.setDefaultValues(getActivity(), R.xml.root_preferemces, false);
        savedValues = PreferenceManager.getDefaultSharedPreferences(getActivity());
        userName = savedValues.getString(getResources().getString(R.string.user_name_key), " ");




    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_home, container, false);
        final TextView homeTitle = root.findViewById(R.id.homeTitle);

        // title changer to user name
        homeTitle.setText("Welcome " + userName + "!");
        mFeeling.setName(userName);
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
                Log.e ("user feelings", userFeelings);


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
        feeling1 = root.findViewById(R.id.feeling1);
        feeling2 = root.findViewById(R.id.feeling2);
        feeling3 = root.findViewById(R.id.feeling3);
        feeling4 = root.findViewById(R.id.feeling4);
        feeling5 = root.findViewById(R.id.feeling5);
        feeling6 = root.findViewById(R.id.feeling6);
        feeling7 = root.findViewById(R.id.feeling7);
        feeling8 = root.findViewById(R.id.feeling8);
        feeling9 = root.findViewById(R.id.feeling9);

        feeling1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                userFeelings = userFeelings + feeling1.getText() +" | " ;
                Log.e ("user feelings", userFeelings);
                mFeeling.setFeeling(userFeelings);
            }
        });

        feeling2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                userFeelings = userFeelings + feeling2.getText() +" | " ;
                Log.e ("user feelings", userFeelings);
                mFeeling.setFeeling(userFeelings);
            }
        });

        feeling3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                userFeelings = userFeelings + feeling3.getText() +" | " ;
                Log.e ("user feelings", userFeelings);
                mFeeling.setFeeling(userFeelings);
            }
        });

        feeling4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                userFeelings = userFeelings + feeling4.getText() +" | " ;
                Log.e ("user feelings", userFeelings);
                mFeeling.setFeeling(userFeelings);
            }
        });

        feeling5.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                userFeelings = userFeelings + feeling5.getText() +" | " ;
                Log.e ("user feelings", userFeelings);
                mFeeling.setFeeling(userFeelings);
            }
        });

        feeling6.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                userFeelings = userFeelings + feeling6.getText() +" | " ;
                Log.e ("user feelings", userFeelings);
                mFeeling.setFeeling(userFeelings);
            }
        });

        feeling7.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                userFeelings = userFeelings + feeling7.getText() +" | " ;
                Log.e ("user feelings", userFeelings);
                mFeeling.setFeeling(userFeelings);
            }
        });

        feeling8.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                userFeelings = userFeelings + feeling8.getText() +" | " ;
                Log.e ("user feelings", userFeelings);
                mFeeling.setFeeling(userFeelings);
            }
        });

        feeling9.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                userFeelings = userFeelings + feeling9.getText() + " | " ;
                Log.e ("user feelings", userFeelings);
                mFeeling.setFeeling(userFeelings);
            }
        });

        //Reminder

        Button startSession = root.findViewById(R.id.startBtn);
        startSession.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (savedValues.getBoolean(getResources().getString(R.string.save_data), true)) {
                    FeelingsLab feelingsLab = FeelingsLab.get(getActivity());
                    feelingsLab.addFeelings(mFeeling);

                }
                Intent intent =SessionActivity.newIntent(getActivity(), sessionTime);
                startActivity(intent);
            }
        });

        //mFeeling.setName(userName);
        //mFeeling.setFeeling(userFeelings);
        return root;
    }

    @Override
    public void onPause() {
        super.onPause();
        FeelingsLab.get(getActivity());
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