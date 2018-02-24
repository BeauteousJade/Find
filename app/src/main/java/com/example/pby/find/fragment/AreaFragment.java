package com.example.pby.find.fragment;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.net.inter.impl.UserOperation;
import com.example.pby.find.R;
import com.example.pby.find.activity.AreaActivity;
import com.example.pby.find.activity.DiscussActivity;
import com.example.pby.find.adapter.list.AreaAdapter;
import com.example.pby.find.bean.recyclerView.AreaBean;
import com.example.pby.find.bean.recyclerView.NoteBean;
import com.example.pby.find.customview.RandomHeadView;
import com.example.pby.find.decoration.GridRecyclerViewDecoration;
import com.example.pby.find.fragment.base.BaseFragment;
import com.example.pby.find.manager.AreaGridLayoutManager;
import com.example.pby.find.mvp.presenter.LoadPresenter;
import com.example.pby.find.mvp.view.IResultView;
import com.scwang.smartrefresh.header.WaterDropHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import es.dmoral.toasty.Toasty;

/**
 * Created by pby on 2018/2/1.
 */

public class AreaFragment extends BaseFragment implements BaseQuickAdapter.OnItemChildClickListener, IResultView, RandomHeadView.OnChildClickListener {
    private ImageView mImageViewSearch = null;
    private TextView mTextViewTitle = null;

    private SmartRefreshLayout mRefreshLayout = null;
    private LinearLayout mLinearLayout = null;
    private RecyclerView mRecyclerView = null;
    private List<AreaBean> mDataList = null;
    private AreaAdapter mAdapter = null;


    private NoteBean mNoteBean = null;
    private RandomHeadView mRandomHeadView = null;


    private LoadPresenter mPresenter = null;

    private String[] names = {"我的关注", "学习", "运动", "旅游", "游戏", "娱乐", "明星", "美食", "动漫"};
    private int[] iconIds = {R.mipmap.icon_area_follow, R.mipmap.icon_area_study, R.mipmap.icon_area_sport,
            R.mipmap.icon_area_tour, R.mipmap.icon_area_game, R.mipmap.icon_area_entertainment, R.mipmap.icon_area_star,
            R.mipmap.icon_area_cate, R.mipmap.icon_area_comic
    };

    public AreaFragment() {
        super(R.layout.fragment_area);
    }

    @Override
    public void init(View view) {
        mRandomHeadView = new RandomHeadView(this, R.layout.head_recyclerview_random);
        mPresenter = new LoadPresenter();

        mImageViewSearch = view.findViewById(R.id.toolbar_setting_imageView_setting);
        mTextViewTitle = view.findViewById(R.id.toolbar_noBack_search_textView_title);
        mRefreshLayout = view.findViewById(R.id.fragment_area_refreshLayout);
        mLinearLayout = view.findViewById(R.id.fragment_area_linearLayout);

        mRecyclerView = view.findViewById(R.id.fragment_area_recyclerView);
        mDataList = new ArrayList<>();
        mAdapter = new AreaAdapter(R.layout.item_recyclerview_area, mDataList);
        for (int i = 0; i < names.length; i++) {
            mAdapter.addData(new AreaBean(names[i], iconIds[i]));
        }
        mRecyclerView.setAdapter(mAdapter);
        AreaGridLayoutManager layoutManager = new AreaGridLayoutManager(getActivity(), 2);
        layoutManager.setScrollEnabled(false);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.addItemDecoration(new GridRecyclerViewDecoration(R.drawable.decoration_gray_01, 2, getActivity()));

        mRefreshLayout.setRefreshHeader(new WaterDropHeader(getActivity()));
        mRefreshLayout.setEnableLoadMore(false);
        mRefreshLayout.setEnableOverScrollBounce(true);
        mRefreshLayout.setOnRefreshListener(refreshLayout -> mPresenter.getRandomNote(UserOperation.getCurrentUser().getEmail(),AreaFragment.this));

        mLinearLayout.addView(mRandomHeadView.build(), 0);
        mTextViewTitle.setText("专区");

        mAdapter.setOnItemChildClickListener(this);
        mPresenter.getRandomNote(UserOperation.getCurrentUser().getEmail(),AreaFragment.this);
    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        if (position != 0) {
            Intent intent = new Intent(getActivity(), AreaActivity.class);
            intent.putExtra(AreaActivity.KEY_AREA, mDataList.get(position));
            startActivity(intent);
        } else {

        }
    }

    @Override
    public void result(Throwable e, Object data, int requestCode) {
        if (e == null) {
            List<NoteBean> list = (List<NoteBean>) data;
            if (list != null && list.size() != 0) {
                mNoteBean = list.get(0);
                mRandomHeadView.setAreaName(mNoteBean.getAreaName())
                        .setContent(mNoteBean.getContent())
                        .setDiscussNumber(mNoteBean.getDiscussNumber())
                        .setHeadIcon(mNoteBean.getHead())
                        .setImages(mNoteBean.getImages())
                        .setName(mNoteBean.getName())
                        .setTime(mNoteBean.getTime())
                        .setReadNumber(mNoteBean.getReadNumber())
                        .setOnChildClickListener(this);
                Toasty.success(this.getActivity(), "获取内容成功!").show();
            }
        } else {
            Toasty.normal(this.getActivity(), "获取内容失败!").show();
            mNoteBean = null;
        }
        mRefreshLayout.finishRefresh();
    }

    @Override
    public void OnChildListener(View view, int id) {
        if (mNoteBean == null) {
            return;
        }
        switch (id) {

            case R.id.item_recyclerView_note_textView_content:
            case R.id.item_recyclerView_note_linearLayout_images: {
                Intent intent = new Intent(getActivity(), DiscussActivity.class);
                intent.putExtra(DiscussActivity.KEY_NOTE_ID,mNoteBean.getId());
                intent.putExtra(DiscussActivity.KEY_NOTE_EMAIL, mNoteBean.getEmail());
                startActivity(intent);
                break;
            }
        }

    }
}
