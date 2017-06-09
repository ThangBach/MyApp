package com.example.bqt.myapp.View.Splash;

import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.bqt.myapp.R;
import com.example.bqt.myapp.View.Home.HomeActivity;
import com.github.paolorotolo.appintro.AppIntro;
import com.github.paolorotolo.appintro.AppIntro2;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class SplashActivity extends AppIntro2 {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_splash);

        addSlide(new FirstFragment());
        addSlide(new SecondFragment());
        addSlide(new LastFragment());

        setBarColor(R.color.it_bglogo);

//        setSkipText("Bỏ Qua");
//        setDoneText("Kết Thúc");
        setBarColor(Color.parseColor("#3498DB"));

        showSkipButton(true);
        setProgressButtonEnabled(true);

        setVibrate(true);
        setFadeAnimation();
        setZoomAnimation();
        setFlowAnimation();
        setSlideOverAnimation();
        setDepthAnimation();

    }

    @Override
    public void onSkipPressed(Fragment currentFragment) {
        super.onSkipPressed(currentFragment);
        Thread thread=new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    Thread.sleep(1000);
                }catch (Exception e){

                }finally {
                    startActivity(new Intent(SplashActivity.this, HomeActivity.class));
                }
            }
        });
        thread.start();
    }

    @Override
    public void onDonePressed(Fragment currentFragment)
    {
        super.onDonePressed(currentFragment);
//        SweetAlertDialog pDialog = new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE);
//        pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
//        pDialog.setTitleText("Loading");
//        pDialog.setCancelable(false);
//        pDialog.show();
    }

    @Override
    public void onSlideChanged(@Nullable Fragment oldFragment, @Nullable Fragment newFragment) {
        super.onSlideChanged(oldFragment, newFragment);
    }
}
