package com.example.pby.find.activity;


import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.example.net.inter.impl.UserOperation;
import com.example.pby.find.R;
import com.example.pby.find.activity.base.BaseActivity;
import com.example.pby.find.fragment.AreaFragment;
import com.example.pby.find.fragment.FindFragment;
import com.example.pby.find.fragment.MessageFragment;
import com.example.pby.find.fragment.MineFragment;
import com.example.pby.find.fragment.base.BaseFragment;
import com.example.pby.find.mvp.presenter.LoadPresenter;
import com.jpeng.jptabbar.JPTabBar;
import com.jpeng.jptabbar.OnTabSelectListener;
import com.jpeng.jptabbar.animate.AnimationType;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity implements OnTabSelectListener {
    private JPTabBar mJPTabBar = null;

    private FragmentManager mFragmentManager = null;
    private BaseFragment mFragments[] = null;
    private BaseFragment mCurrentFragment = null;
    private int[] normalIcons = {R.mipmap.icon_tab_area_normal, R.mipmap.icon_tab_find_normal,
            R.mipmap.icon_tab_message_normal, R.mipmap.icon_tab_mine_normal};
    private int[] selectIcons = {R.mipmap.icon_tab_area_select, R.mipmap.icon_tab_find_select,
            R.mipmap.icon_tab_message_select, R.mipmap.icon_tab_mine_select};
    private LoadPresenter mPresenter = null;

    private ArrayList<Integer> mNumberList = null;
    private int mCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setImmerseMode();
    }

    @Override
    public void init() {
        mJPTabBar = findView(R.id.activity_main_jpTabBar);

        mFragments = new BaseFragment[4];

        mFragmentManager = getSupportFragmentManager();
        mJPTabBar.setTitles("专区", "发现", "消息", "我的")
                .setNormalIcons(normalIcons)
                .setSelectedIcons(selectIcons)
                .generate();
        mJPTabBar.setAnimation(AnimationType.FLIP);
        mJPTabBar.setTabListener(this);
        mJPTabBar.setSelectTab(0);

        mPresenter = new LoadPresenter();
        mPresenter.getMessageNumber(UserOperation.getCurrentUser().getEmail(), this);
    }

    @Override
    public void onTabSelect(int index) {
        FragmentTransaction transaction = mFragmentManager.beginTransaction();
        if (mCurrentFragment != null) {
            transaction.hide(mCurrentFragment);
        }
        switch (index) {
            case 0: {
                if (mFragments[index] == null) {
                    mFragments[index] = new AreaFragment();
                    transaction.add(R.id.activity_main_frameLayout, mFragments[index]);
                } else {
                    transaction.show(mFragments[index]);
                }
                break;
            }
            case 1: {
                if (mFragments[index] == null) {
                    mFragments[index] = new FindFragment();
                    transaction.add(R.id.activity_main_frameLayout, mFragments[index]);
                } else {
                    transaction.show(mFragments[index]);
                }
                break;
            }
            case 2: {
                if (mFragments[index] == null) {
                    mFragments[index] = new MessageFragment();
                    transaction.add(R.id.activity_main_frameLayout, mFragments[index]);
                } else {
                    transaction.show(mFragments[index]);
                }
                break;

            }
            case 3: {
                if (mFragments[index] == null) {
                    mFragments[index] = new MineFragment();
                    transaction.add(R.id.activity_main_frameLayout, mFragments[index]);
                } else {
                    transaction.show(mFragments[index]);
                }
                break;
            }
        }

        mCurrentFragment = mFragments[index];
        transaction.commit();
    }

    @Override
    public void result(Throwable e, Object data, int requestCode) {
        if (e == null) {
            mNumberList = (ArrayList<Integer>) data;
            mCount = 0;
            for (int i : mNumberList) {
                mCount += i;
            }
            showBadge(0);
        } else {

        }
    }

    public void showBadge(int d) {
        mCount = mCount + d;
        if (mCount > 0) {
            mJPTabBar.showBadge(2, mCount, true);
        } else {
            mJPTabBar.hideBadge(2);
        }
    }

    public void resetBadge(int count) {
        mCount = count;
        if (mCount != 0) {
            mJPTabBar.showBadge(2, mCount, true);
        }
    }
}
