package com.circle.stu.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;

import com.circle.stu.R;

import butterknife.ButterKnife;

/**
 * Created by oyty on 9/22/16.
 */

public abstract class BaseActivity extends AppCompatActivity {

    protected Context mContext;
    Toolbar mToolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(initViewId());
        ButterKnife.bind(this);
        mContext = this;
        App.getInstance().addActivity(this);
        initTitle();
        initToolbar();
        process();
    }

    protected abstract void initToolbar();

    private void initTitle() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        if(mToolbar != null) {
            setSupportActionBar(mToolbar);
            if (getSupportActionBar() != null) {
                getSupportActionBar().setDisplayShowTitleEnabled(false);
            }
        }
    }

    protected void setTitle(String title) {
        if(!TextUtils.isEmpty(title) && mToolbar != null) {
            mToolbar.setTitle(title);
        }
    }

    public void setTitle(int resId) {
        if(mToolbar != null) {
            mToolbar.setTitle(resId);
        }
    }

    protected void showBackAction(boolean isShow) {
        if(getSupportActionBar() != null && mToolbar != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(isShow);
            mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
        }
    }

    protected void showBackAction(boolean isShow, View.OnClickListener listener) {
        if(getSupportActionBar() != null && mToolbar != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(isShow);
            mToolbar.setNavigationOnClickListener(listener);
        }
    }

    protected abstract int initViewId();

    protected abstract void process();


    @Override
    protected void onDestroy() {
        super.onDestroy();
        App.getInstance().removeActivity(this);
    }
}
