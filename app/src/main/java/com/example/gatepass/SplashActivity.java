package com.example.gatepass;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;

import com.google.firebase.auth.FirebaseAuth;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_splash );

        getSupportActionBar().hide();
        getWindow().setFlags( WindowManager.LayoutParams.FLAG_FULLSCREEN,
                              WindowManager.LayoutParams.FLAG_FULLSCREEN);


        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                finally {
//                    if (mauth.getCurrentUser()==null){
//                        startActivity(new Intent( SplashActivity.this,Student_login.class));
//                        finish();
//                    }else {
//                        startActivity( new Intent( SplashActivity.this,MainActivity.class ) );
//                        finish();
//                    }
                    startActivity( new Intent(SplashActivity.this,MainActivity.class) );
                    finish();
                }
            }
        }).start();
    }
}