package com.example.pby.find.fragment;

import android.support.v4.view.ViewPager;
import android.view.View;

import com.example.pby.find.R;
import com.example.pby.find.adapter.pager.FindAdapter;
import com.example.pby.find.customview.ViewPagerIndicator;
import com.example.pby.find.fragment.base.BaseFragment;

import java.util.Arrays;
import java.util.List;

/**
 * Created by pby on 2018/2/11.
 */

public class FindFragment extends BaseFragment {
    private ViewPager mFragmentViewPager = null;
    private FindAdapter mAdapter = null;
    private ViewPagerIndicator mViewPagerIndicator = null;
    private List<FindChildFragment> mFragmentList = null;

    public FindFragment() {
        super(R.layout.fragment_find);

    }

    @Override
    public void init(View view) {
        mFragmentViewPager = view.findViewById(R.id.fragment_find_viewPager);
        mViewPagerIndicator = view.findViewById(R.id.toolbar_fragment_find_viewPagerIndicator);

        mFragmentList = Arrays.asList(FindChildFragment.newInstance(FindChildFragment.TYPE_REC), FindChildFragment.newInstance(FindChildFragment.TYPE_NEWEST), FindChildFragment.newInstance(FindChildFragment.TYPE_FOLLOW));
        mAdapter = new FindAdapter(getFragmentManager(), mFragmentList);
        mFragmentViewPager.setAdapter(mAdapter);
        mFragmentViewPager.setOffscreenPageLimit(3);


        mFragmentViewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                mViewPagerIndicator.scroll(position, positionOffset);
            }

            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                mFragmentList.get(position).refresh();

            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
    }
}
