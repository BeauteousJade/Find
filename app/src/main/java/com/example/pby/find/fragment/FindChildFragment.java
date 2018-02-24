package com.example.pby.find.fragment;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.net.inter.impl.UserOperation;
import com.example.pby.find.R;
import com.example.pby.find.activity.DiscussActivity;
import com.example.pby.find.adapter.list.FindNoteAdapter;
import com.example.pby.find.bean.recyclerView.NoteBean;
import com.example.pby.find.decoration.LinearRecyclerViewDecoration;
import com.example.pby.find.fragment.base.BaseFragment;
import com.example.pby.find.mvp.presenter.LoadPresenter;
import com.example.pby.find.mvp.view.IResultView;
import com.scwang.smartrefresh.header.WaterDropHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;

import java.util.ArrayList;
import java.util.List;

import es.dmoral.toasty.Toasty;

/**
 * Created by pby on 2018/2/11.
 */

public class FindChildFragment extends BaseFragment implements IResultView, BaseQuickAdapter.OnItemChildClickListener {

    private int type = -1;
    private RecyclerView mRecyclerView = null;
    private List<NoteBean> mDataList = null;
    private FindNoteAdapter mAdapter = null;

    private SmartRefreshLayout mRefreshLayout = null;

    private LoadPresenter mPresenter = null;

    public static final int CODE_REFRESH = 1;
    public static final int CODE_MORE = 2;

    public static final int TYPE_REC = 1;
    public static final int TYPE_NEWEST = 2;
    public static final int TYPE_FOLLOW = 3;


    public FindChildFragment() {
        super(R.layout.fragment_find_child);
    }

    private FindChildFragment setType(int type) {
        this.type = type;
        return this;
    }

    @Override

    public void init(View view) {
        mRefreshLayout = view.findViewById(R.id.fragment_find_child_refreshLayout);

        mRecyclerView = view.findViewById(R.id.fragment_find_child_recyclerView);
        mDataList = new ArrayList<>();
        mAdapter = new FindNoteAdapter(R.layout.item_recyclerview_note_01, mDataList);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        mRecyclerView.addItemDecoration(new LinearRecyclerViewDecoration(getActivity(), R.drawable.decoration_gray_00, LinearRecyclerViewDecoration.VERTIACL));

        mPresenter = new LoadPresenter();
        mRefreshLayout.setRefreshHeader(new WaterDropHeader(getActivity()));
        mRefreshLayout.setRefreshFooter(new ClassicsFooter(getActivity()));

        mRefreshLayout.setOnRefreshListener(refreshLayout -> {
            switch (type) {
                case TYPE_REC: {
                    mPresenter.getRecNote(UserOperation.getCurrentUser().getEmail(), FindChildFragment.this, CODE_REFRESH);
                    break;
                }
                case TYPE_NEWEST: {
                    mPresenter.getNewestNote(UserOperation.getCurrentUser().getEmail(), FindChildFragment.this, CODE_REFRESH);

                    break;
                }
                case TYPE_FOLLOW: {
                    mPresenter.getFollowNote(UserOperation.getCurrentUser().getEmail(), FindChildFragment.this, CODE_REFRESH);
                    break;
                }
            }
        });
        mRefreshLayout.setOnLoadMoreListener(refreshLayout -> {
            switch (type) {
                case TYPE_REC: {
                    mPresenter.loadMoreRecNote(UserOperation.getCurrentUser().getEmail(), mDataList.size(), FindChildFragment.this, CODE_MORE);
                    break;
                }
                case TYPE_NEWEST: {
                    mPresenter.loadMoreNewestNote(UserOperation.getCurrentUser().getEmail(), mDataList.size(), FindChildFragment.this, CODE_MORE);

                    break;
                }
                case TYPE_FOLLOW: {
                    mPresenter.loadMoreFollowNote(UserOperation.getCurrentUser().getEmail(), mDataList.size(), FindChildFragment.this, CODE_MORE);
                    break;
                }
            }
        });

        mAdapter.setOnItemChildClickListener(this);
    }

    public static FindChildFragment newInstance(int type) {
        return new FindChildFragment().setType(type);
    }

    @Override
    public void result(Throwable e, Object data, int requestCode) {
        if (e == null) {
            List<NoteBean> list = (List<NoteBean>) data;
            switch (requestCode) {
                case CODE_MORE: {
                    mAdapter.addData(list);
                    break;
                }
                case CODE_REFRESH: {
                    mDataList.clear();
                    mAdapter.addData(list);
                    break;
                }
            }
            Toasty.success(getActivity(), "加载内容成功!").show();
        } else {
            Toasty.normal(getActivity(), "加载内容失败!").show();
        }
        mRefreshLayout.finishRefresh();
        mRefreshLayout.finishLoadMore();
    }

    public void refresh() {
        if (mRefreshLayout != null && mDataList.size() == 0) {
            mRefreshLayout.autoRefresh();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (type == TYPE_REC) {
            refresh();
        }
    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        Intent intent = new Intent(getActivity(), DiscussActivity.class);
        intent.putExtra(DiscussActivity.KEY_NOTE_ID, mDataList.get(position).getId());
        intent.putExtra(DiscussActivity.KEY_NOTE_EMAIL, mDataList.get(position).getEmail());
        startActivity(intent);
    }
}
