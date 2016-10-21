package com.circle.stu.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.circle.stu.R;
import com.circle.stu.base.BaseActivity;

/**
 * Created by oyty on 10/10/16.
 */

public class SettingActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
    }

    @Override
    protected int initViewId() {
        return 0;
    }

    @Override
    protected void process() {

    }
}
