package com.circle.stu.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.circle.stu.R;
import com.circle.stu.base.Constants;
import com.circle.stu.util.PreferenceHelper;
import com.circle.stu.widget.imageslider.Indicators.CircleIndicator;
import com.circle.stu.widget.imageslider.Tricks.SlidePagerAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * Created by oyty on 10/16/16.
 */

public class GuideActivity extends AppCompatActivity {

    @BindView(R.id.mViewPager)
    ViewPager mViewPager;
    @BindView(R.id.mCircleIndicator)
    CircleIndicator mCircleIndicator;
    @BindView(R.id.mSkipAction)
    Button mSkipAction;
    @BindView(R.id.mNextPageAction)
    Button mNextPageAction;

    private int[] layouts;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        ButterKnife.bind(this);

        layouts = new int[] {
                R.layout.welcome_slide1,
                R.layout.welcome_slide2,
                R.layout.welcome_slide3,
                R.layout.welcome_slide4
        };

        // making notification bar transparent
        changeStatusBarColor();

        GuideViewPagerAdapter viewPagerAdapter = new GuideViewPagerAdapter();
        mViewPager.setAdapter(viewPagerAdapter);
        mViewPager.addOnPageChangeListener(viewPagerPageChangeListener);
        mCircleIndicator.setViewPager(mViewPager);
    }

    ViewPager.OnPageChangeListener viewPagerPageChangeListener = new ViewPager.OnPageChangeListener() {

        @Override
        public void onPageSelected(int position) {
            // changing the next button text 'NEXT' / 'GOT IT'
            if (position == layouts.length - 1) {
                // last page. make button text to GOT IT
                mNextPageAction.setText(getString(R.string.start));
                mSkipAction.setVisibility(View.GONE);
            } else {
                // still pages are left
                mNextPageAction.setText(getString(R.string.next));
                mSkipAction.setVisibility(View.VISIBLE);
            }
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {
        }

        @Override
        public void onPageScrollStateChanged(int arg0) {
        }
    };


    private void changeStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }
    }

    @OnClick(R.id.mNextPageAction)
    void nextPage(View view) {
        int current =  mViewPager.getCurrentItem() + 1;
        if(current < layouts.length) {
            mViewPager.setCurrentItem(current);
        } else {
            gotoMainActivity();
        }
    }

    @OnClick(R.id.mSkipAction)
    void skip(View view) {
        gotoMainActivity();
    }

    private void gotoMainActivity() {
        PreferenceHelper.putBoolean(Constants.IS_FIRST_IN, false);
        startActivity(new Intent(GuideActivity.this, MainActivity.class));
        finish();
    }

    /**
     * View pager adapter
     */
    public class GuideViewPagerAdapter extends SlidePagerAdapter {
        private LayoutInflater layoutInflater;

        public GuideViewPagerAdapter() {
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            View view = layoutInflater.inflate(layouts[position], container, false);
            container.addView(view);

            return view;
        }

        @Override
        public int getCount() {
            return layouts.length;
        }

        @Override
        public int getRealCount() {
            return layouts.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object obj) {
            return view == obj;
        }


        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            View view = (View) object;
            container.removeView(view);
        }
    }
}
