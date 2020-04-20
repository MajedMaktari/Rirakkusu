package com.example.rirakkusu.ui.home;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rirakkusu.R;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class SessionActivity extends Activity {


    public int counter;
    ProgressBar progressBar;
    public int Time = 60000;
    public int totalTime;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meditation);



        countDown(); // start countdown

        Toast.makeText(getApplicationContext(), "Breath and Relax",    Toast.LENGTH_SHORT).show();
    }
    public void countDown(){
        totalTime = new HomeFragment().getSessionTime();
        Log.e("session time minuts ", String.valueOf(totalTime));
        final int timeDown = totalTime*60000;
        Log.e("session time sec ", String.valueOf(timeDown));

        //new BackgroundSound().execute();
        //toastHandler();


        final TextView counttime = (TextView) findViewById(R.id.counttime);
        progressBar = (ProgressBar)findViewById(R.id.progressBar);
        new  CountDownTimer(10000, 1000){
            public void onTick(long millisUntilFinished){
                progressBar.setMax(timeDown);
                progressBar.setProgress(counter/1000);
                counttime.setText(""+String.format("%d : %d ",
                        TimeUnit.MILLISECONDS            .toMinutes( millisUntilFinished),
                        TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) -
                                TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished))));
                //counttime.setText(String.valueOf(counter));
                counter++;
            }
            public  void onFinish(){

                //new BackgroundSound().end(); // end this ?
                //toastHandler(); // end this/
                counttime.setText("FINISH!!");
            }
        }.start();

    }

    public class BackgroundSound extends AsyncTask<Void, Void, Void> {
        MediaPlayer player = MediaPlayer.create(SessionActivity.this, R.raw.chris_zabriskie_04_the_oceans_continue_to_rise);;

        @Override
        protected Void doInBackground(Void... params) {

            player.setLooping(true); // Set looping
            player.setVolume(1.0f, 1.0f);
            player.start();


            return null;


        }


        public void end() {
            player.stop();
            player.release();
        }
    }


    public void toastHandler() {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {

            @Override
            public void run() {
                runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        final Toast toast = Toast.makeText(getApplicationContext(), "Breath", Toast.LENGTH_SHORT);
                        toast.show();
                        Handler handler = new Handler();

                        handler.postDelayed(new Runnable() {

                            @Override
                            public void run() {
                                toast.cancel();
                            }
                        }, 5000);

                    }
                });
            }
        }, 0, 5000);

    }
}
