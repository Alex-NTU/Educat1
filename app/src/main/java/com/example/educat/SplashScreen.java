package com.example.educat;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

public class SplashScreen extends AppCompatActivity
{
    public static int time = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);

        new Handler().postDelayed(new Runnable()
        {
            @Override
            public void run ()
            {
                Intent loginintent = new Intent (SplashScreen.this,MainActivity.class);
                startActivity(loginintent);
                finish();
            }
        }, time);
    }

}
