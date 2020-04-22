package com.example.rirakkusu.ui.session;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
//import android.os.Handler;
import android.os.IBinder;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import com.example.rirakkusu.R;
import com.example.rirakkusu.ui.SoundService;

import java.util.concurrent.TimeUnit;

import static android.content.Context.BIND_AUTO_CREATE;

public class SessionFragment extends Fragment {

    SoundService soundService;
    boolean isBound = false;

    public int counter;
    public ProgressBar progressBar;
    public int Time = 60000;
    public int totalTime;
    TextView counttime;
    TextView screenContent;
    FrameLayout layout;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);


    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_meditation, container, false);
        layout = root.findViewById(R.id.meditation_layout);
        screenContent= root.findViewById(R.id.fullscreen_content);
        counttime = root.findViewById(R.id.counttime);
        progressBar = root.findViewById(R.id.progressBar);
        int position = getActivity().getIntent().getIntExtra(SessionActivity.EXTRA_SESSION_TIME, 5);
        totalTime = position;

        countDown(); // start countdown


        Button endSession = root.findViewById(R.id.end_session);
        endSession.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /////
            }
        });
        return root;
    }


    public void countDown() {
        Log.e("session time minuts ", String.valueOf(totalTime));
        final int timeDown = totalTime * 60000;
        Log.e("session time sec ", String.valueOf(timeDown));


        new CountDownTimer(timeDown, 1000) {

            public void onTick(long millisUntilFinished) {
                progressBar.setMax(timeDown);
                progressBar.setProgress(counter*1000);
                counttime.setText("" + String.format("%d : %d ",
                        TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished),
                        TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) -
                                TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished))));
                counter++;
            }

            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            public void onFinish() {
                onStop();
                layout.setBackgroundResource(R.drawable.end_gradient);
                screenContent.setText("");
                counttime.setText("FINISH!!");
            }
        }.start();
    }

    //sound player
    @Override
    public void onStart() {
        super.onStart();
        Intent intent = new Intent(getActivity(), SoundService.class);
        getActivity().startService(intent);
        getActivity().bindService(intent, soundServiceConnection, BIND_AUTO_CREATE);
    }

    @Override
    public void onResume() {
        super.onResume();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {


                Toast.makeText(getActivity(), "Breath and Relax", Toast.LENGTH_SHORT).show();

            }
        };
    }

    @Override
    public void onStop() {
        super.onStop();
        if (isBound) {
            getActivity().unbindService(soundServiceConnection);
            isBound = false;


        }
    }


    private ServiceConnection soundServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {

            SoundService.MyBinder binderBridge = (SoundService.MyBinder) service;
            soundService = binderBridge.getService();
            isBound = true;

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

            isBound = false;
            soundService = null;


        }
    };

}
