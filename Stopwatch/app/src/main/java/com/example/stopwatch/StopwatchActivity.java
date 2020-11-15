package com.example.stopwatch;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

public class StopwatchActivity extends AppCompatActivity {

    //Seconds displayed on the stopwatch
    private int seconds=0;
    //is the stopwatch running
    private boolean running;
    private boolean wasRunning;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stopwatch);
        if (savedInstanceState != null){
            seconds =savedInstanceState.getInt("seconds");
            running =savedInstanceState.getBoolean("running");
            wasRunning = savedInstanceState.getBoolean("wasRunning");
        }
        runTimer();
    }

    @Override
    protected void onPause(){
        super.onPause();
        wasRunning =running;
        running = false;
    }

    @Override
    protected void onResume(){
        super.onResume();
       if (wasRunning){
           running = true;
       }
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putInt("seconds", seconds);
        savedInstanceState.getBoolean("running", running);
        savedInstanceState.putBoolean("wasRunning", wasRunning);
    }


    //Start the stopwatch
    public void onClickStart(View view){
        running= true;
    }

    //Stop the stopwatch
    public void onClickStop(View view){
        running= false;
    }

    //Reset the stopwatch
    public void onClickReset(View view){
        running = false;
        seconds = 0;
    }

    //Set the number of seconds
    private void runTimer(){
        final TextView timeView =(TextView)findViewById(R.id.time_view);
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                int hours =seconds/3600;
                int minutes= (seconds%3600)/60;
                int secs = seconds%60;
                String time = String.format("%d:%02d:%02d", hours, minutes, secs);
                timeView.setText(time);
                if (running){
                    seconds++;
                }
                handler.postDelayed(this, 1000);
            }
        });
    }
}