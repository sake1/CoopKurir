package com.coopmart.kurir.coopkurir.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.coopmart.kurir.coopkurir.R;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        sleep();
    }

    private void sleep() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                goTo(LoginActivity.class);
            }
        }, 5000);
    }

    private void goTo(Class targetActivity) {
        Intent intent = new Intent(this, targetActivity);
        startActivity(intent);
        finish();
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
    }
}
