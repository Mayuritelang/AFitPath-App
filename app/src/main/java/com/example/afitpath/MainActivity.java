package com.example.afitpath;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    public void timerPage(View view){
        Intent intent = new Intent(this, WorkOutActivity.class);
        startActivity(intent);
    }

    public  void bmiCalc(View view){
        Intent intent = new Intent(this, BmiCalculator.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}