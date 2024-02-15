package com.example.afitpath;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

public class WorkOutActivity extends AppCompatActivity {


    TextView countDownTextView;
    SeekBar seekBar;
    boolean counterIsActive = false;
    Button button;
    CountDownTimer countDownTimer;

    public void resetTimer() {

        countDownTextView.setText("0:00");
        seekBar.setProgress(00);
        seekBar.setEnabled(true);
        countDownTimer.cancel();
        button.setText("Start");
        counterIsActive = false;
    }

    public void startButton(View view){

        if(counterIsActive){
            resetTimer();
        }
        else {
            seekBar.setEnabled(true);
            button.setText("Stop");
            counterIsActive = false;

            countDownTimer = new CountDownTimer(seekBar.getProgress() * 1000 + 100, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    updateTimer((int) millisUntilFinished / 1000);
                }

                @Override
                public void onFinish() {
                    MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.alarm);
                    mediaPlayer.start();
                    resetTimer();
                }
            }.start();
        }
    }

    public void updateTimer(int secondLeft) {

        int minutes = secondLeft / 60;
        int seconds = secondLeft - (minutes * 60);

        String setSeconds = Integer.toString(seconds);

        if (seconds <= 9) {
            setSeconds = "0" + setSeconds;
        }

        countDownTextView.setText(Integer.toString(minutes)+ ":" + setSeconds);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work_out);


        seekBar = findViewById(R.id.seekbar);
        countDownTextView = findViewById(R.id.textview3);
        ImageView imageView = findViewById(R.id.imageView3);
        button = findViewById(R.id.button);

        seekBar.setMax(600);
        seekBar.setProgress(30);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

                updateTimer(i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}