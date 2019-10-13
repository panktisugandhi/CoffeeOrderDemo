package com.example.ganesh.coffeeorderdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

/**
 * Created by ganesh on 7/30/2017.
 */

public class Splashscreen extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splashscreen);

//        final Thread th = new Thread(){
//
//            @Override
//            public void run() {
//                try{
//                    sleep(5*1000);
//                    Intent i=new Intent(Splashscreen.this,MainActivity.class);
//                    startActivity(i);
//                    finish();
//                }catch (Exception e){}
//            }
//        };
//        th.start();
        final ImageView imageView = (ImageView) findViewById(R.id.splash);
        final Animation anim1 = AnimationUtils.loadAnimation(this,R.anim.rotate);
        final Animation anim2 = AnimationUtils.loadAnimation(this,R.anim.antirotate);

        imageView.startAnimation(anim2);
        anim2.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                imageView.startAnimation(anim1);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        anim1.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                imageView.startAnimation(anim2);
                finish();
                Intent i = new Intent(Splashscreen.this,MainActivity.class);
                startActivity(i);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }
}
