package com.example.pby.find.fragment;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.example.net.inter.impl.UserOperation;
import com.example.net.net.bean.User;
import com.example.pby.find.R;
import com.example.pby.find.activity.MineNoteActivity;
import com.example.pby.find.activity.SettingActivity;
import com.example.pby.find.adapter.list.MineItemAdapter;
import com.example.pby.find.bean.recyclerView.MineItemBean;
import com.example.pby.find.constant.ConstVariable;
import com.example.pby.find.customview.MineHeadView;
import com.example.pby.find.decoration.LinearRecyclerViewDecoration;
import com.example.pby.find.fragment.base.BaseFragment;
import com.example.pby.find.mvp.presenter.LoadPresenter;
import com.example.pby.find.mvp.view.IResultView;
import com.scwang.smartrefresh.header.WaterDropHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by pby on 2018/2/19.
 */

public class MineFragment extends BaseFragment implements IResultView, MineHeadView.OnMineHeadViewClickListener, BaseQuickAdapter.OnItemChildClickListener, View.OnClickListener {
    private TextView mTextViewTitle = null;
    private ImageView mImageViewSetting = null;

    private RecyclerView mRecyclerView = null;
    private List<MineItemBean> mDataList = null;
    private MineItemAdapter mAdapter = null;
    private MineHeadView mHeadView = null;

    private SmartRefreshLayout mRefreshLayout = null;

    private LoadPresenter mPresenter = null;

    private boolean isCreate = false;

    public static final int CODE_USER = 1;
    public static final int CODE_NUMBER = 2;


    public MineFragment() {
        super(R.layout.fragment_mine);
    }

    @Override
    public void init(View view) {
        isCreate = true;
        mPresenter = new LoadPresenter();

        mTextViewTitle = view.findViewById(R.id.toolbar_setting_textView_title);
        mImageViewSetting = view.findViewById(R.id.toolbar_setting_imageView_setting);
        mRecyclerView = view.findViewById(R.id.fragment_mine_recyclerView);
        mRefreshLayout = view.findViewById(R.id.fragment_mine_refreshLayout);

        mDataList = Arrays.asList(new MineItemBean(MineItemBean.TYPE_EMPTY), new MineItemBean(R.mipmap.icon_mine_item_collection, "收藏的帖子", 0),
                new MineItemBean(R.mipmap.icon_mine_item_appreciate, "喜欢的帖子", 0), new MineItemBean(MineItemBean.TYPE_EMPTY),
                new MineItemBean(R.mipmap.icon_mine_item_record, "我的足迹", 0), new MineItemBean(R.mipmap.icon_mine_item_discuss, "我的评论", 0),
                new MineItemBean(R.mipmap.icon_mine_item_album, "我的相册", 0), new MineItemBean(MineItemBean.TYPE_EMPTY),
                new MineItemBean(R.mipmap.icon_night, "夜间模式"));
        mAdapter = new MineItemAdapter(mDataList);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        mRecyclerView.addItemDecoration(new LinearRecyclerViewDecoration(getActivity(), R.drawable.decoration_gray_01, LinearRecyclerViewDecoration.VERTIACL));

        mHeadView = new MineHeadView(R.layout.head_recyclerview_mine, getActivity());
        mAdapter.addHeaderView(mHeadView.build());


        mRefreshLayout.setOnRefreshListener(refreshLayout -> {
            mPresenter.getUserInfo(UserOperation.getCurrentUser().getEmail(), MineFragment.this, CODE_USER);
            mPresenter.getNumberCount(UserOperation.getCurrentUser().getEmail(), MineFragment.this, CODE_NUMBER);
        });
        mRefreshLayout.setEnableLoadMore(false);
        mRefreshLayout.setEnableOverScrollBounce(true);
        mRefreshLayout.setRefreshHeader(new WaterDropHeader(getActivity()));
        mTextViewTitle.setText("我的");

        mHeadView.setOnMineHeadViewClickListener(this);

        mAdapter.setOnItemChildClickListener(this);
        mImageViewSetting.setOnClickListener(this);
    }

    @Override
    public void result(Throwable e, Object data, int requestCode) {
        if (e == null) {
            switch (requestCode) {
                case CODE_NUMBER: {
                    List<Integer> list = (List<Integer>) data;
                    if (list != null && list.size() == 7) {
                        mHeadView.setNoteNumber(list.get(0))
                                .setFollowNumber(list.get(1))
                                .setFansNumber(list.get(2));
                        int k = 3;
                        for (int i = 0; i < mDataList.size() && k < list.size(); i++) {
                            if (mDataList.get(i).getItemType() != MineItemBean.TYPE_EMPTY) {
                                mDataList.get(i).setCount(list.get(k++));
                            }
                        }
                        mAdapter.notifyDataSetChanged();
                    }
                    break;
                }
                case CODE_USER: {
                    List<User> list = (List<User>) data;
                    if (list != null && list.size() != 0) {
                        User user = list.get(0);
                        mHeadView.setAutograph(user.getAutograph() == null ? "暂无签名" : user.getAutograph())
                                .setHead(user.getHead() == null ? ConstVariable.DEFAULT_HEAD_URL : user.getHead())
                                .setName(user.getName() == null ? user.getEmail() : user.getName());
                    }
                    break;
                }
            }
        } else {

        }
        mRefreshLayout.finishRefresh();
    }

    @Override
    public void onMineHeadViewClick(View view) {
        Intent intent = new Intent(getActivity(), MineNoteActivity.class);
        switch (view.getId()) {
            case R.id.head_recyclerView_mine_linearLayout_note: {
                intent.putExtra(MineNoteActivity.KEY_TYPE, MineNoteActivity.TYPE_NOTE);
                break;
            }
            case R.id.head_recyclerView_mine_linearLayout_follow: {
                intent.putExtra(MineNoteActivity.KEY_TYPE, MineNoteActivity.TYPE_FOLLOW);
                break;
            }
            case R.id.head_recyclerView_mine_linearLayout_fans: {
                intent.putExtra(MineNoteActivity.KEY_TYPE, MineNoteActivity.TYPE_FANS);
                break;
            }

        }
        startActivity(intent);


    }

    @Override
    public void onResume() {
        super.onResume();
        if (isCreate) {
            isCreate = false;
            mRefreshLayout.autoRefresh();
        }
    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        switch (position) {
            case 1: {
                Intent intent = new Intent(getActivity(), MineNoteActivity.class);
                intent.putExtra(MineNoteActivity.KEY_TYPE, MineNoteActivity.TYPE_COLLECTION);
                startActivity(intent);
                break;
            }
            case 2: {
                Intent intent = new Intent(getActivity(), MineNoteActivity.class);
                intent.putExtra(MineNoteActivity.KEY_TYPE, MineNoteActivity.TYPE_APPRECIATE);
                startActivity(intent);
                break;
            }
            case 4: {
                Intent intent = new Intent(getActivity(), MineNoteActivity.class);
                intent.putExtra(MineNoteActivity.KEY_TYPE, MineNoteActivity.TYPE_RECORD);
                startActivity(intent);
                break;
            }
            case 5: {
                Intent intent = new Intent(getActivity(), MineNoteActivity.class);
                intent.putExtra(MineNoteActivity.KEY_TYPE, MineNoteActivity.TYPE_DISCUSS);
                startActivity(intent);
                break;
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.toolbar_setting_imageView_setting: {
                Intent intent = new Intent(getActivity(), SettingActivity.class);
                startActivity(intent);
                break;
            }
        }
    }
}
