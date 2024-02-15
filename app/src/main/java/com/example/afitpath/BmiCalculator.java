package com.example.afitpath;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class BmiCalculator extends AppCompatActivity {

    private EditText etHeight, etWeight;
    private Button calculate_btn, reCalculate;
    private TextView bmi_tv, bmi, status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi_calculator);

        ImageView imageView = findViewById(R.id.imageView4);
        etHeight = findViewById(R.id.height);
        etWeight = findViewById(R.id.weight);
        calculate_btn = findViewById(R.id.calculate);
        reCalculate = findViewById(R.id.recalculate);
        bmi_tv = findViewById(R.id.bmiTextView);
        bmi = findViewById(R.id.bmi);
        status = findViewById(R.id.status);

        calculate_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!etHeight.getText().toString().isEmpty() && !etWeight.getText().toString().isEmpty()) {
                    int height = Integer.parseInt(etHeight.getText().toString());
                    int weight = Integer.parseInt(etWeight.getText().toString());

                    float BMI = calculateBMI(height, weight);

                    bmi.setText(String.valueOf(BMI));
                    bmi.setVisibility(View.VISIBLE);

                    if (BMI < 18.5) {
                        status.setText("Under Weight");
                    } else if (BMI >= 18.5 && BMI < 24.9) {
                        status.setText("Healthy");
                    } else if (BMI >= 24.9 && BMI < 30) {
                        status.setText("Overweight");
                    } else if (BMI >= 30) {
                        status.setText("Suffering from Obesity");
                    }

                    bmi_tv.setVisibility(View.VISIBLE);
                    status.setVisibility(View.VISIBLE);

                    reCalculate.setVisibility(View.VISIBLE);
                    calculate_btn.setVisibility(View.GONE);

                } else {
                    Toast.makeText(BmiCalculator.this, "Please enter valid height and weight", Toast.LENGTH_SHORT).show();
                }
            }
        });

        reCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetEverything();
            }
        });
    }

    private void resetEverything() {
        calculate_btn.setVisibility(View.VISIBLE);
        reCalculate.setVisibility(View.GONE);

        etHeight.getText().clear();
        etWeight.getText().clear();
        status.setText("");
        bmi.setText("");
        bmi_tv.setVisibility(View.GONE);
    }

    private float calculateBMI(int height, int weight) {
        float height_in_metre = (float) height / 100;
        return weight / (height_in_metre * height_in_metre);
    }
}