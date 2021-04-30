package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private Chronometer chronometer;
    private boolean running, started = false, stop_presssed = false, pause_presssed = false;
    private ImageView start, pause, stop;
    private EditText type;
    private long time_record ;
    private Button test;
    private TextView time;
    private String recorded_time, Workout_record;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        chronometer = findViewById(R.id.chronometer);
        start = findViewById(R.id.start_bt);
        pause = findViewById(R.id.pause_bt);
        stop = findViewById(R.id.stop_bt);
        type = findViewById(R.id.Type);
        //test = findViewById(R.id.test_bt);
        ///workout = findViewById(R.id.workout);
        time = findViewById(R.id.Time);
        start.setImageResource(R.drawable.start);
        pause.setImageResource(R.drawable.pause);
        stop.setImageResource(R.drawable.stop);


        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                start();
            }
        });

        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pause();
            }
        });
        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stop();
            }
        });
//        test.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                test_time();
//
//            }
//        });

        if (savedInstanceState != null) {
            stop_presssed = savedInstanceState.getBoolean("stop_pressed");
            pause_presssed = savedInstanceState.getBoolean("puase");
            Long Time = savedInstanceState.getLong("time");
            String record = savedInstanceState.getString("record");
            Long Pause_time = savedInstanceState.getLong("pause_time");
            if (pause_presssed == true) {
                chronometer.setBase(SystemClock.elapsedRealtime() - Pause_time);
                chronometer.stop();
                running = false;
                started = false;
            }else if(stop_presssed == true) {
                chronometer.stop();
                running = false;
                started = false;
            } else {
                time.setText(record);
                chronometer.setBase(Time);
                chronometer.start();
                running = true;
            }


        }


    }

    public void start() {
        if (running == false) {
            if (started == false) {
                chronometer.setBase(0);
            }
            chronometer.setBase((SystemClock.elapsedRealtime()) - time_record);
            chronometer.start();
            running = true;
            started = true;
            stop_presssed = false;
            pause_presssed = false;
        }

    }

    public void pause() {
        if (running == true) {
            chronometer.stop();
            time_record = SystemClock.elapsedRealtime() - chronometer.getBase();
            running = false;
            pause_presssed = true;
            //time_record = chronometer.getBase();


        }
    }

    public void stop() {
        //time_record = SystemClock.elapsedRealtime() - chronometer.getBase();
        chronometer.stop();
        //SystemClock.sleep(time_record);
        //chronometer.setBase(time_record);
        recorded_time = chronometer.getText().toString();
        Workout_record = type.getText().toString();
        time.setText("You spent " + recorded_time + " on " + Workout_record + " last time.");


        chronometer.setBase(SystemClock.elapsedRealtime());
        time_record = 0;

        running = false;
        started = false;
        stop_presssed = true;


    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("record", time.getText().toString());
        outState.putLong("time", chronometer.getBase());
        outState.putBoolean("stop_pressed", stop_presssed);
        outState.putBoolean("puase", pause_presssed);
        outState.putLong("pause_time",time_record);

//        if(stop_presssed == true){
//            chronometer.stop();
//            chronometer.setBase(0);
//        }

    }
//    public void Stop(){
//        chronometer.stop();
//        chronometer.setBase(0);
//    }


    //    private void test_time(){
//        chronometer.setBase(SystemClock.elapsedRealtime() - time_record);
//
//
//    }
}