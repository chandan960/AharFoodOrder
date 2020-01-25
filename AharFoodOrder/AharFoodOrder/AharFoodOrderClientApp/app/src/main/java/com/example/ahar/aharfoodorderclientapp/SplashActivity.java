package com.example.ahar.aharfoodorderclientapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;

public class SplashActivity extends AppCompatActivity {


    //write code
    private ProgressBar progressBar;
    private int progress;

    //

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        //write code

        progressBar=(ProgressBar) findViewById(R.id.progressBarId);

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                doWork();
                startApp();
            }
        });
        thread.start();

        //
    }

    //write code

    public void doWork(){

        for(progress=25; progress<=100; progress=progress+25){

            try {
                Thread.sleep(1000);
                progressBar.setProgress(progress);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    public void startApp(){
        Intent intent =new Intent(SplashActivity.this,MenuActivity.class);
        startActivity(intent);
        finish();
    }

    //
}
