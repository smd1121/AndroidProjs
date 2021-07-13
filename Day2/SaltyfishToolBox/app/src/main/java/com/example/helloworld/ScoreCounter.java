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
import android.widget.Toast;

public class ScoreCounter extends AppCompatActivity {
    private int scoreA = 0, scoreB = 0;
    private boolean hasAWin = false, hasBWin = false;

    private void updateDisplay() {
        final TextView scrA = findViewById(R.id.scoreA);
        final TextView scrB = findViewById(R.id.scoreB);
        scrA.setText(String.valueOf(scoreA));
        scrB.setText(String.valueOf(scoreB));
        final ProgressBar pgsbA = findViewById(R.id.progressBar);
        final ProgressBar pgsbB = findViewById(R.id.progressBar2);
        pgsbA.setProgress(scoreA);
        pgsbB.setProgress(scoreB);
        if (!hasAWin && scoreA >= 15) {
            hasAWin = true;
            Toast.makeText(this, "A has reached 15 pts.", Toast.LENGTH_SHORT).show();
        }
        if (!hasBWin && scoreB >= 15) {
            hasBWin = true;
            Toast.makeText(this, "B has reached 15 pts.", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_counter);
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
        
        Log.i("life cycle", "ScoreCounterActivity onCreate");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("life cycle", "ScoreCounterActivity onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("life cycle", "ScoreCounterActivity onResume");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i("life cycle", "ScoreCounterActivity onRestart");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("life cycle", "ScoreCounterActivity onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("life cycle", "ScoreCounterActivity onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("life cycle", "ScoreCounterActivity onDestroy");
    }
}