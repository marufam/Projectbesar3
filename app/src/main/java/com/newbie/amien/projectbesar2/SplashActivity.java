package com.newbie.amien.projectbesar2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import java.io.File;


/**
 * Created by amien on 19/12/16.
 */

public class SplashActivity extends AppCompatActivity {
    private static int SPLASH_TIME_OUT = 3000; //milisecond

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_layout);

        File f = new File(
                "/data/data/com.newbie.amien.projectbesar2/shared_prefs/MyPref.xml");
        if (!f.exists()) {
            SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0); // 0 - for private mode
            SharedPreferences.Editor editor = pref.edit();
            editor.putString("Id", "0");
            editor.commit();

        }
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {

                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
                finish(); // keluar dari activity ini
            }
        }, SPLASH_TIME_OUT);
    }
}
