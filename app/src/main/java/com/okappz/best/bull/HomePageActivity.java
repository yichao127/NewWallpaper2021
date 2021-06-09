package com.okappz.best.bull;

import android.os.Bundle;
import android.util.DisplayMetrics;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import com.nshmura.recyclertablayout.RecyclerTabLayout;
import com.okappz.best.bull.adapter.DemoCustomView02Adapter;
import com.okappz.best.bull.adapter.WallFragmentPagerAdapter;
import com.okappz.best.bull.fragment.ChoiceDayFragment;
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


        Fragment everyDayFragment = new ChoiceDayFragment(screenWidth);
        Fragment sortFragment = new SortFragment(screenWidth);
        Fragment videoFragment = new VideoFragment(screenWidth);
        List<Fragment> fragments = new ArrayList<Fragment>();
        fragments.add(everyDayFragment);
        fragments.add(videoFragment);
        fragments.add(sortFragment);

        FragmentManager fragmentManager = getSupportFragmentManager();
        WallFragmentPagerAdapter wallFragmentPagerAdapter = new WallFragmentPagerAdapter(fragmentManager, fragments);
        viewPager.setAdapter(wallFragmentPagerAdapter);
        viewPager.setOffscreenPageLimit(3);
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
