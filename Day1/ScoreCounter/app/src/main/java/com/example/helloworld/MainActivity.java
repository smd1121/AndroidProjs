package com.example.helloworld;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private int scoreA = 0, scoreB = 0;

    private void updateDisplay() {
        final TextView scrA = findViewById(R.id.scoreA);
        final TextView scrB = findViewById(R.id.scoreB);
        scrA.setText(String.valueOf(scoreA));
        scrB.setText(String.valueOf(scoreB));
        final ProgressBar pgsbA = findViewById(R.id.progressBar);
        final ProgressBar pgsbB = findViewById(R.id.progressBar2);
        pgsbA.setProgress(scoreA);
        pgsbB.setProgress(scoreB);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        updateDisplay();

        Button btnA = findViewById(R.id.buttonA);
        btnA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scoreA++;
                Log.d("button", "A add by 1.");
                updateDisplay();
            }
        });

        Button btnB = findViewById(R.id.buttonB);
        btnB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scoreB++;
                Log.d("button", "B add by 1.");
                updateDisplay();
            }
        });

        CheckBox ckbx1 = findViewById(R.id.checkBox1);
        ckbx1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                scoreA += isChecked ? 3 : -3;
                if (isChecked)
                    Log.d("checkbox", "A checked.");
                else
                    Log.d("checkbox", "A unchecked.");
                updateDisplay();
            }
        });

        CheckBox ckbx2 = findViewById(R.id.checkBox2);
        ckbx2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                scoreB += isChecked ? 3 : -3;
                if (isChecked)
                    Log.d("checkbox", "B checked.");
                else
                    Log.d("checkbox", "B unchecked.");
                updateDisplay();
            }
        });
    }
}