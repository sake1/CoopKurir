package com.coopmart.kurir.coopkurir.Activity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;
import android.widget.RatingBar;

import com.coopmart.kurir.coopkurir.Method.Navigator;
import com.coopmart.kurir.coopkurir.R;
import com.vansuita.materialabout.builder.AboutBuilder;
import com.vansuita.materialabout.views.AboutView;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class ProfileShowActivity extends AppCompatActivity {

    public static final String TAG = "Profil Saya";

    @OnClick(R.id.ps_fab)
    public void onFabClick() {
        final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.ps_fab);
        Thread blink = new Thread(new Runnable() {
            @Override
            public void run() {
                fab.setBackgroundColor(getResources().getColor(R.color.primary_lighter));
                fab.setEnabled(false);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                fab.setBackgroundColor(getResources().getColor(R.color.primary));
                fab.setEnabled(true);
            }
        });
        blink.run();
        Intent intent = new Intent(this, ProfileEditActivity.class);
        startActivityForResult(intent, Navigator.REQUEST_PROFILE_EDIT);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_show);
        ButterKnife.bind(this);
        getSupportActionBar().setTitle(TAG);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        AboutView profile = AboutBuilder.with(this)
                .setPhoto(R.mipmap.profile_picture)
                .setCover(R.mipmap.profile_cover)
                .setName("Timothy Dicky")
                .setSubTitle("timothydickys@gmail.com")
                .addFiveStarsAction()
                .setVersionNameAsAppSubTitle()
                .setShowDivider(true)
                .addShareAction(R.string.app_name)
                .setWrapScrollView(true)
                .setLinksAnimated(true)
                .setShowAsCard(true)
                .build();

        ((FrameLayout) findViewById(R.id.ps_profile_frame)).addView(profile);

        RatingBar ratingBar = (RatingBar) findViewById(R.id.ps_output_rating);
        ratingBar.setEnabled(false);
        LayerDrawable stars = (LayerDrawable) ratingBar.getProgressDrawable();
        stars.getDrawable(2).setColorFilter(getResources().getColor(R.color.yellowStar), PorterDuff.Mode.SRC_ATOP);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == Navigator.REQUEST_PROFILE_EDIT) {
            if(resultCode == RESULT_OK) {
                //TODO: do stuff(s)
            }
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
