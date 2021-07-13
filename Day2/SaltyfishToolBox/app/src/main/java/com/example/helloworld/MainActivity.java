package com.example.helloworld;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        View view = findViewById(R.id.mainLayout);
        view.getBackground().setAlpha(64);

        findViewById(R.id.toScrCntr).setOnClickListener(this);
        findViewById(R.id.toHonor).setOnClickListener(this);
        findViewById(R.id.toWebList).setOnClickListener(this);

        Log.i("life cycle", "MainActivity onCreate");
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.toScrCntr:
                Intent intent1 = new Intent(MainActivity.this, ScoreCounter.class);
                startActivity(intent1);
                break;
            case R.id.toHonor:
                Intent intent2 = new Intent(MainActivity.this, HonorList.class);
                startActivity(intent2);
                break;
            case R.id.toWebList:
                Intent intent3 = new Intent(MainActivity.this, WebsiteList.class);
                startActivity(intent3);
                break;
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("life cycle", "MainActivity onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("life cycle", "MainActivity onResume");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i("life cycle", "MainActivity onRestart");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("life cycle", "MainActivity onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("life cycle", "MainActivity onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("life cycle", "MainActivity onDestroy");
    }
}