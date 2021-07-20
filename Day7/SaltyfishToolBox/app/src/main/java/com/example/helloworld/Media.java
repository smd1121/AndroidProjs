package com.example.helloworld;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;

public class Media extends AppCompatActivity {
    SurfaceView surfaceView;
    MediaPlayer mediaPlayer;
    boolean isSeekBarChanging = false;
    int duration;
    Timer timer;
    SeekBar seekBar;
    TextView textView;
    String mockUrl = "https://lf1-cdn-tos.bytescm.com/obj/static/ies/bytedance_official_cn/_next/static/images/0-390b5def140dc370854c98b8e82ad394.png";

    private void updateText() {
        textView.setText(calculateTime(mediaPlayer.getCurrentPosition() / 1000) + " / " + calculateTime(duration / 1000));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media);

        timer = new Timer();
        textView = findViewById(R.id.tv_prog);

        findViewById(R.id.media_layout).getBackground().setAlpha(64);
        seekBar = findViewById(R.id.progressBar4);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                updateText();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                isSeekBarChanging = true;
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                isSeekBarChanging = false;
                mediaPlayer.seekTo(seekBar.getProgress());
                updateText();
            }
        });


        surfaceView = findViewById(R.id.surfaceView);
        mediaPlayer = new MediaPlayer();
        try {
            mediaPlayer.setDataSource("https://stream7.iqilu.com/10339/upload_transcode/202002/18/20200218114723HDu3hhxqIT.mp4");

            mediaPlayer.prepareAsync();
            surfaceView.getHolder().addCallback(new PlayerCallBack());
            surfaceView.getHolder().setFixedSize(0, 0);
            mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    duration = mediaPlayer.getDuration();
                    seekBar.setMax(duration);
                    updateText();
                    mediaPlayer.start();
                    surfaceView.getHolder().setFixedSize(
                            findViewById(R.id.media_layout).getWidth(),
                            findViewById(R.id.media_layout).getWidth() / mediaPlayer.getVideoWidth() * mediaPlayer.getVideoHeight()
                    );
                    timer.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            if (!isSeekBarChanging)
                                seekBar.setProgress(mediaPlayer.getCurrentPosition());
                        }
                    }, 0, 50);
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

        findViewById(R.id.fig_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Glide.with(v).load(mockUrl).error(R.drawable.bar_color_a).fallback(R.drawable.bar_color_a)
                        .transition(withCrossFade()).into((ImageView) findViewById(R.id.fig_iv));
            }
        });

        findViewById(R.id.fig_btn_rnd).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Glide.with(v).load(mockUrl).error(R.drawable.bar_color_a).fallback(R.drawable.bar_color_a)
                        .transition(withCrossFade()).apply(RequestOptions.bitmapTransform(new RoundedCorners(50)))
                        .into((ImageView) findViewById(R.id.fig_iv));
            }
        });
    }

    private class PlayerCallBack implements SurfaceHolder.Callback {

        @Override
        public void surfaceCreated(@NonNull SurfaceHolder holder) {
            mediaPlayer.setDisplay(holder);
        }

        @Override
        public void surfaceChanged(@NonNull SurfaceHolder holder, int format, int width, int height) {

        }

        @Override
        public void surfaceDestroyed(@NonNull SurfaceHolder holder) {

        }
    }

    public String calculateTime(int time){
        int minute;
        int second;
        if(time > 60){
            minute = time / 60;
            second = time % 60;
            //分钟再0~9
            if(minute >= 0 && minute < 10){
                //判断秒
                if(second >= 0 && second < 10){
                    return "0"+minute+":"+"0"+second;
                }else {
                    return "0"+minute+":"+second;
                }
            }else {
                //分钟大于10再判断秒
                if(second >= 0 && second < 10){
                    return minute+":"+"0"+second;
                }else {
                    return minute+":"+second;
                }
            }
        }else if(time < 60){
            second = time;
            if(second >= 0 && second < 10){
                return "00:"+"0"+second;
            }else {
                return "00:"+ second;
            }
        }
        return null;
    }
}