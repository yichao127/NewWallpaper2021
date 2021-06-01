package com.okappz.best.bull;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.nshmura.recyclertablayout.RecyclerTabLayout;
import com.okappz.best.bull.adapter.DemoCustomView02Adapter;
import com.okappz.best.bull.adapter.WallFragmentPagerAdapter;
import com.okappz.best.bull.fragment.EveryDayFragment;
import com.okappz.best.bull.fragment.SortFragment;
import com.okappz.best.bull.fragment.VideoFragment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HomePageActivity extends AppCompatActivity {

    private List<String> mTitle = Arrays.asList("每日", "video", "分类");

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);


        ViewPager viewPager = findViewById(R.id.view_pager);

        DisplayMetrics metric = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metric);
        int screenWidth = metric.widthPixels;


        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @SuppressLint("ResourceAsColor")
            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        Fragment everyDayFragment = new EveryDayFragment();
        Fragment sortFragment = new SortFragment();
        Fragment videoFragment = new VideoFragment();
        List<Fragment> fragments = new ArrayList();
        fragments.add(everyDayFragment);
        fragments.add(sortFragment);
        fragments.add(videoFragment);

        FragmentManager fragmentManager = getSupportFragmentManager();
        WallFragmentPagerAdapter wallFragmentPagerAdapter = new WallFragmentPagerAdapter(fragmentManager, fragments);
        viewPager.setAdapter(wallFragmentPagerAdapter);

        wallFragmentPagerAdapter.addAll(new ArrayList<>(Arrays.asList(
                R.drawable.ic_3d_rotation_white_36dp,
                R.drawable.ic_accessibility_white_36dp,
                R.drawable.ic_account_balance_wallet_white_36dp
        )));

        RecyclerTabLayout recyclerTabLayout = findViewById(R.id.recycler_tab_layout);
        recyclerTabLayout.setUpWithAdapter(new DemoCustomView02Adapter(viewPager));
        recyclerTabLayout.setPositionThreshold(0.5f);

    }
}
