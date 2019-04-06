package com.example.eggtimer;

import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    SeekBar counter;
    boolean counterIsActive=false;
    Button goButton;
    CountDownTimer countDownTimer;

    public void resetTimer(){
        textView.setText("1:00");
        counter.setProgress(60);
        counter.setEnabled(true);
        countDownTimer.cancel();
        goButton.setText("GO !");
        counterIsActive=false;
    }
    public void click(View view){
       if(counterIsActive){
           resetTimer();
       }else {

           counterIsActive = true;
           counter.setEnabled(false);
           goButton.setText("STOP !!");
           Log.i("button ", "nice");
           countDownTimer = new CountDownTimer(counter.getProgress() * 1000 + 100, 1000) {

               @Override
               public void onTick(long millisUntilFinished) {
                   int mil=(int) millisUntilFinished/1000;

                   Log.i("finished", Integer.toString(mil));
                   updateTimer((int) millisUntilFinished / 1000);

               }

               @Override
               public void onFinish() {
                   Log.i("finished", "timer doone");
                   MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.rooster);
                   mediaPlayer.start();
                   resetTimer();
               }
           }.start();
       }
    }

    public void updateTimer(int secondsLeft){
        int minutes = secondsLeft / 60;
        int seconds = secondsLeft - (minutes*60);

        String minutesS= Integer.toString(minutes);
        String secondsS= Integer.toString(seconds);

        if(seconds<=9){
            secondsS="0"+seconds;
        }


        textView.setText(minutesS+":"+secondsS);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        counter = findViewById(R.id.seekBarCounter);
        textView = findViewById(R.id.textView);
        goButton = findViewById(R.id.button);
        counter.setMax(900);
        counter.setProgress(60);

        counter.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                Log.i("seekbar", Integer.toString(progress));
                updateTimer(progress);


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
