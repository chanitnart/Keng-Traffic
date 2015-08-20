package com.example.lab.trafficcontrol;

import android.graphics.Typeface;
import android.media.Image;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.security.PublicKey;

public class MainActivity extends AppCompatActivity {
    private Button btnStart;
    private TextView mTvTimer;
    private ImageView mIvRed,mIvYellow,mIvGreen;

    private boolean timerHasStarted = false;
    private boolean checkTrafficColor = false;
    private TrafficCounDownTimer countDownTimer;

    private final long startTime = 20000;
    private final long interval = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTvTimer = (TextView)findViewById(R.id.mTvTimer);
        mIvRed = (ImageView)findViewById(R.id.mlvRed);
        mIvYellow=(ImageView)findViewById(R.id.mlvYellow);
        mIvGreen = (ImageView)findViewById(R.id.mlvGreen);
        btnStart=(Button)findViewById(R.id.btnStart);

        countDownTimer = new TrafficCounDownTimer(startTime,interval);

        //setfont

        //Typeface font = Typeface.createFromAsset()
        // mTvTimer.setTypeface(font);
        //mTvTimer.setTextSize(120);

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if (!timerHasStarted){
                   mIvRed.setImageResource(R.drawable.red);
                   countDownTimer.start();
                   timerHasStarted= true;
                   btnStart.setText("R E S E T");
               }
                else {
                   mTvTimer.setTextColor(getResources().getColor(R.color.gray));
                   mTvTimer.setText(00);
                   mIvRed.setImageResource(R.drawable.red_noactive);
                   mIvGreen.setImageResource(R.drawable.green_noactive);
                   mIvYellow.setImageResource(R.drawable.yellow_noactive);
                   timerHasStarted=false;
                   countDownTimer.cancel();
                   btnStart.setText("Start");

               }
            }
        });


        }

    public class TrafficCounDownTimer extends CountDownTimer {
           public TrafficCounDownTimer(long millisInFuture, long countDownInterval) {
                super(millisInFuture, countDownInterval);
            }

            @Override
            public void onTick(long millisUntilFinished) {
                if (checkTrafficColor==false){
                    mTvTimer.setTextColor(getResources().getColor(R.color.red));
                    mIvRed.setImageResource(R.drawable.red);
                    mIvGreen.setImageResource(R.drawable.green_noactive);
                    mIvYellow.setImageResource(R.drawable.yellow_noactive);

                }else{
                    mTvTimer.setTextColor(getResources().getColor(R.color.green));
                    mIvRed.setImageResource(R.drawable.red_noactive);
                    mIvGreen.setImageResource(R.drawable.green);
                    mIvYellow.setImageResource(R.drawable.yellow_noactive);
                }
                mTvTimer.setText(" "+millisUntilFinished/1000);
                long intCheckYellow = millisUntilFinished/1000;
                if (intCheckYellow <= 10){      //นับถอยหลังมาครบ 10 วิ จะเปลี่ยนสีเหลืองให้สว่าง
                    mTvTimer.setTextColor(getResources().getColor(R.color.yellow));
                    mIvRed.setImageResource(R.drawable.red_noactive);
                    mIvGreen.setImageResource(R.drawable.green_noactive);
                    mIvYellow.setImageResource(R.drawable.yellow);

                }

            }

            @Override
            public void onFinish() {

                mIvYellow.setImageResource(R.drawable.yellow_noactive);
                if (checkTrafficColor==false) {
                    checkTrafficColor = true;
                    mIvGreen.setImageResource((R.drawable.green));
                    mIvRed.setImageResource(R.drawable.red_noactive);
                }
                    else{
                        checkTrafficColor=false;
                        mIvRed.setImageResource(R.drawable.red);
                        mIvGreen.setImageResource(R.drawable.green_noactive);
                    }
                start();
            }
        }
}
