package com.example.pby.find.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.net.inter.impl.FollowOperation;
import com.example.net.inter.impl.MessageOperation;
import com.example.net.inter.impl.UserOperation;
import com.example.pby.find.R;
import com.example.pby.find.activity.base.BaseActivity;
import com.example.pby.find.adapter.list.MessageFollowAdapter;
import com.example.pby.find.bean.recyclerView.MessageFollowBean;
import com.example.pby.find.constant.ConstVariable;
import com.example.pby.find.decoration.LinearRecyclerViewDecoration;
import com.example.pby.find.mvp.presenter.LoadPresenter;
import com.scwang.smartrefresh.header.WaterDropHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;

import java.util.ArrayList;
import java.util.List;

import es.dmoral.toasty.Toasty;

/**
 * Created by pby on 2018/2/18.
 */

public class MessageFollowActivity extends BaseActivity implements View.OnClickListener, BaseQuickAdapter.OnItemChildClickListener {
    private ImageView mImageViewBack = null;
    private TextView mTextViewTitle = null;
    private SmartRefreshLayout mRefreshLayout = null;
    private RecyclerView mRecyclerView = null;
    private List<MessageFollowBean> mDataList = null;
    private MessageFollowAdapter mAdapter = null;
    private LoadPresenter mPresenter = null;

    public static final int CODE_GET = 1;
    public static final int CODE_MORE = 2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_follow_message);
        setImmerseMode();
    }


    @Override
    public void init() {
        mPresenter = new LoadPresenter();
        mImageViewBack = findView(R.id.toolbar_back_ok_imageView_back);
        mTextViewTitle = findView(R.id.toolbar_back_ok_textView_title);
        mRecyclerView = findView(R.id.activity_follow_message_recyclerView);
        mRefreshLayout = findView(R.id.activity_follow_message_refreshLayout);

        mDataList = new ArrayList<>();
        mAdapter = new MessageFollowAdapter(R.layout.item_recyclerview_message_follow, mDataList);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mRecyclerView.addItemDecoration(new LinearRecyclerViewDecoration(this, R.drawable.decoration_gray_00, LinearRecyclerViewDecoration.VERTIACL));

        mRefreshLayout.setRefreshHeader(new WaterDropHeader(this));
        mRefreshLayout.setRefreshFooter(new ClassicsFooter(this));
        mRefreshLayout.setOnRefreshListener(refreshLayout -> mPresenter.getFans(UserOperation.getCurrentUser().getEmail(), MessageFollowActivity.this, CODE_GET));
        mRefreshLayout.setOnLoadMoreListener(refreshLayout -> mPresenter.loadMoreFans(UserOperation.getCurrentUser().getEmail(), mDataList.size(), MessageFollowActivity.this, CODE_MORE));


        mTextViewTitle.setText("关注");
        mImageViewBack.setOnClickListener(this);
        mAdapter.setOnItemChildClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.toolbar_back_ok_imageView_back: {
                onBackPressed();
                break;
            }
        }
    }

    @Override
    public void result(Throwable e, Object data, int requestCode) {
        if (e == null) {
            List<MessageFollowBean> list = (List<MessageFollowBean>) data;
            if (requestCode == CODE_GET) {
                mDataList.clear();
                mAdapter.addData(list);
            } else if (requestCode == CODE_MORE) {
                mAdapter.addData(list);
            }
        } else {
            Toasty.normal(this, "加载失败!").show();
        }
        mRefreshLayout.finishLoadMore();
        mRefreshLayout.finishRefresh();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mDataList.size() == 0) {
            mRefreshLayout.autoRefresh();
        }
    }

    @Override
    protected void onDestroy() {
        final MessageOperation messageOperation = new MessageOperation();
        final String email = UserOperation.getCurrentUser().getEmail();
        messageOperation.readFollow(email);
        super.onDestroy();
    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        final TextView textView = (TextView) view;
        final String email = UserOperation.getCurrentUser().getEmail();
        final String followEmail = mDataList.get(position).getEmail();
        final FollowOperation followOperation = new FollowOperation();
        final String string = textView.getText().toString();
        if (ConstVariable.STRING_FOLLOW.equals(string)) {
            textView.setText("取消关注");
            followOperation.follow(email, followEmail);
        } else if (ConstVariable.STRING_NO_CANCEL.equals(string)) {
            textView.setText("关注");
            followOperation.cancelFollow(email, followEmail);
        }
    }
}
