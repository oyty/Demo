package com.circle.stu.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.circle.stu.R;
import com.circle.stu.util.PreferenceHelper;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by oyty on 10/13/16.
 */

public class SplashActivity extends AppCompatActivity {

    @BindView(R.id.mSplashImg)
    ImageView mSplashImg;

    private Animation mSplashAnim;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);
        mSplashAnim = AnimationUtils.loadAnimation(this, R.anim.splash);
        mSplashImg.setImageResource(R.mipmap.splash);
        mSplashImg.startAnimation(mSplashAnim);

        mSplashAnim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if(PreferenceHelper.isFirstIn()) {
                    gotoGuideActivity();
                } else {
                    gotoMainActivity();
                }
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
    }

    private void gotoMainActivity() {
        startActivity(new Intent(SplashActivity.this, MainActivity.class));
        finish();
    }

    private void gotoGuideActivity() {
        startActivity(new Intent(SplashActivity.this, GuideActivity.class));
        finish();
    }

}
