package com.circle.stu.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;

/**
 * Created by oyty on 9/22/16.
 */

public abstract class BaseActivity extends AppCompatActivity {

    protected Context mContext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(initViewId());
        ButterKnife.bind(this);
        mContext = this;
        App.getInstance().addActivity(this);
        process();
    }

    protected abstract int initViewId();

    protected abstract void process();


    @Override
    protected void onDestroy() {
        super.onDestroy();
        App.getInstance().removeActivity(this);
    }
}
