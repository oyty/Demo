package com.circle.stu.ui.activity;

import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.arlib.floatingsearchview.FloatingSearchView;
import com.avos.avoscloud.AVAnalytics;
import com.circle.stu.R;
import com.circle.stu.base.BaseActivity;
import com.circle.stu.widget.Fab;
import com.circle.stu.widget.imageslider.Indicators.CircleIndicator;
import com.circle.stu.widget.imageslider.SliderLayout;
import com.circle.stu.widget.imageslider.SliderTypes.BaseSliderView;
import com.circle.stu.widget.imageslider.SliderTypes.TextSliderView;
import com.gordonwong.materialsheetfab.MaterialSheetFab;
import com.gordonwong.materialsheetfab.MaterialSheetFabEventListener;

import java.util.HashMap;

import butterknife.BindView;

public class MainActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener, BaseSliderView.OnSliderClickListener {

    private MaterialSheetFab materialSheetFab;
    private int statusBarColor;

    @BindView(R.id.mSliderLayout)
    SliderLayout mSliderLayout;
    @BindView(R.id.mCircleIndicator)
    CircleIndicator mCircleIndicator;
    @BindView(R.id.mSearchView)
    FloatingSearchView mSearchView;
    @BindView(R.id.mDrawerLayout)
    DrawerLayout mDrawerLayout;

    @Override
    protected void initToolbar() {

    }

    @Override
    protected int initViewId() {
        return R.layout.activity_main;
    }

    @Override
    protected void process() {
        initFab();
        initSlider();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        mSearchView.attachNavigationDrawerToMenuButton(mDrawerLayout);

        AVAnalytics.trackAppOpened(getIntent());
    }

    private void initSlider() {
        HashMap<String, Integer> file_maps = new HashMap<String, Integer>();
        file_maps.put("House of Cards", R.mipmap.house);
        file_maps.put("Big Bang Theory", R.mipmap.bigbang);
        file_maps.put("Hannibal", R.mipmap.hannibal);
        file_maps.put("game of thrones", R.mipmap.game_of_thrones);

        for (String name : file_maps.keySet()) {
            TextSliderView textSliderView = new TextSliderView(this);
            // initialize a SliderLayout
            textSliderView
                    .description(name)
                    .image(file_maps.get(name))
                    .setScaleType(BaseSliderView.ScaleType.Fit)
                    .setOnSliderClickListener(this);

            //add your extra information
            textSliderView.bundle(new Bundle());
            textSliderView.getBundle()
                    .putString("extra", name);

            mSliderLayout.addSlider(textSliderView);
        }
        mSliderLayout.setPresetTransformer(SliderLayout.Transformer.Default);
//        mSliderLayout.setCustomAnimation(new DescriptionAnimation());
        mSliderLayout.setDuration(4000);
        mSliderLayout.setCurrentPosition();
        mSliderLayout.setIndicator(mCircleIndicator);
        mSliderLayout.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }

    private void initFab() {
        Fab fab = (Fab) findViewById(R.id.fab);
        View sheetView = findViewById(R.id.fab_sheet);
        View overlay = findViewById(R.id.overlay);
        int sheetColor = getResources().getColor(R.color.background_card);
        int fabColor = getResources().getColor(R.color.theme_accent);

        materialSheetFab = new MaterialSheetFab<>(fab, sheetView, overlay, sheetColor, fabColor);
        materialSheetFab.setEventListener(new MaterialSheetFabEventListener() {
            @Override
            public void onShowSheet() {
                // Save current status bar color
                statusBarColor = getStatusBarColor();
                // Set darker status bar color to match the dim overlay
                setStatusBarColor(getResources().getColor(R.color.theme_primary_dark2));
            }

            @Override
            public void onHideSheet() {
                setStatusBarColor(statusBarColor);
            }
        });

//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
////                startActivity(new Intent(mContext, PublishActivity.class));
////                overridePendingTransition(R.anim.publish_slide_in, 0);
//
//            }
//        });
    }

    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        } else {
            if (materialSheetFab.isSheetVisible()) {
                materialSheetFab.hideSheet();
            } else {
                super.onBackPressed();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();


//        if (id == R.id.nav_send) {
//
//        } else if(id == nv_setting) {
//            FeedbackAgent agent = new FeedbackAgent(context);
//            agent.startDefaultThreadActivity();
//        }

        mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    private int getStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            return getWindow().getStatusBarColor();
        }
        return 0;
    }

    private void setStatusBarColor(int color) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(color);
        }
    }

    @Override
    public void onSliderClick(BaseSliderView slider) {

    }
}
