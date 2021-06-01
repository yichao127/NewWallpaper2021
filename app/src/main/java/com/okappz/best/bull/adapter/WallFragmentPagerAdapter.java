package com.okappz.best.bull.adapter;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class WallFragmentPagerAdapter extends FragmentPagerAdapter {

    private FragmentManager fragmetnmanager;
    private List<Fragment> fragments;
    private List<Integer> mItems = new ArrayList<>();

    public void addAll(List<Integer> items) {
        mItems = new ArrayList<>(items);
    }

    @DrawableRes
    public int getImageResourceId(int position) {
        return mItems.get(position);
    }

    public WallFragmentPagerAdapter(@NonNull FragmentManager fm, List<Fragment> fragments) {
        super(fm);
        this.fragmetnmanager=fm;
        this.fragments=fragments;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
}
