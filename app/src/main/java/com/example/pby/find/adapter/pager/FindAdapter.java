package com.example.pby.find.adapter.pager;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import com.example.pby.find.fragment.FindChildFragment;
import com.example.pby.find.fragment.base.BaseFragment;

import java.util.List;

/**
 * Created by pby on 2018/2/11.
 */

public class FindAdapter extends FragmentPagerAdapter {
    private List<FindChildFragment> mFragmentList;

    public FindAdapter(FragmentManager fm, List<FindChildFragment> fragments) {
        super(fm);
        mFragmentList = fragments;

    }

    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }

}
