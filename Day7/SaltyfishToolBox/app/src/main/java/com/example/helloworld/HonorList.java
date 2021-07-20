package com.example.helloworld;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class HonorList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_honor_list2);

        View view = findViewById(R.id.honorWallLayout);
        view.getBackground().setAlpha(64);

        String dateString = "2021-04-26";
        Date date1 = null;
        try {
            date1 = new SimpleDateFormat("yyyy-MM-dd").parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Date date2 = new Date();
        int dayNum = (int) ((date2.getTime() - date1.getTime()) / (1000*3600*24));
        TextView textView = findViewById(R.id.days);
        textView.setText("连续热爱串串 " + dayNum + " 天");

        findViewById(R.id.backFromHonor).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        findViewById(R.id.conLove).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                Uri data = Uri.parse("tel:");
                intent.setData(data);
                startActivity(intent);
            }
        });

        Log.i("life cycle", "HonorListActivity onCreate");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("life cycle", "HonorListActivity onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("life cycle", "HonorListActivity onResume");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i("life cycle", "HonorListActivity onRestart");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("life cycle", "HonorListActivity onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("life cycle", "HonorListActivity onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("life cycle", "HonorListActivity onDestroy");
    }
}