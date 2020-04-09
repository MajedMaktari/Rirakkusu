package com.example.rirakkusu.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.se.omapi.Session;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.rirakkusu.MainActivity;
import com.example.rirakkusu.MeditationActivity;
import com.example.rirakkusu.R;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private SeekBar timeSeek;
    private EditText timeEditText;
    private float sessionTime = 5 ;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        final TextView textView = root.findViewById(R.id.text_home);

        //event listener for seek bar
        timeEditText = root.findViewById(R.id.timeEditText);
        timeSeek = root.findViewById(R.id.seekBar);
        timeSeek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                timeEditText.setText(String.format("%d", progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                int progress = seekBar.getProgress();
                sessionTime = progress;


            }
        });

        //event listener for start button
        Button startSession = root.findViewById(R.id.startBtn);
        startSession.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StartMeditaion();
            }
        });


        homeViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);


            }
        });
        return root;
    }

    public void StartMeditaion() {
        Intent intent = new Intent(getActivity(), MainActivity.class);
        startActivity(intent);
    }
}