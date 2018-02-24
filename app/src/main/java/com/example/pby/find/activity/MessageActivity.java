package com.example.pby.find.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.net.inter.impl.MessageOperation;
import com.example.net.inter.impl.UserOperation;
import com.example.pby.find.R;
import com.example.pby.find.activity.base.BaseActivity;
import com.example.pby.find.adapter.list.MessageAdapter;
import com.example.pby.find.bean.Operation;
import com.example.pby.find.bean.recyclerView.MessageDiscussBean;
import com.example.pby.find.decoration.LinearRecyclerViewDecoration;
import com.example.pby.find.mvp.presenter.LoadPresenter;
import com.scwang.smartrefresh.header.WaterDropHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;

import java.util.ArrayList;
import java.util.List;

import es.dmoral.toasty.Toasty;

/**
 * Created by pby on 2018/2/12.
 */

public class MessageActivity extends BaseActivity implements View.OnClickListener, BaseQuickAdapter.OnItemChildClickListener {
    private ImageView mImageViewBack = null;
    private TextView mTextViewTitle = null;


    private RecyclerView mRecyclerView = null;
    private SmartRefreshLayout mRefreshLayout = null;

    private List<MessageDiscussBean> mDataList = null;
    private MessageAdapter mAdapter = null;
    private LoadPresenter mPresenter = null;

    public int operationKey = 0;

    public static final int CODE_GET = 1;
    public static final int CODE_MORE = 2;

    public static final int KEY_DISCUSS = 1;
    public static final int KEY_APPRECIATE = 2;
    public static final int KEY_REPLY = 3;

    public static final String NAME_OPERATION = "name";

    public static final String titles[] = {"评论", "喜欢", "回复"};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_discuss);
        setImmerseMode();
    }

    @Override
    public void init() {
        operationKey = getIntent().getIntExtra(NAME_OPERATION, 0);
        mPresenter = new LoadPresenter();

        mImageViewBack = findView(R.id.toolbar_back_ok_imageView_back);
        mTextViewTitle = findView(R.id.toolbar_back_ok_textView_title);
        mRefreshLayout = findView(R.id.activity_message_discuss_refreshLayout);
        mRecyclerView = findView(R.id.activity_message_discuss_recyclerView);
        mDataList = new ArrayList<>();
        mAdapter = new MessageAdapter(R.layout.item_recyclerview_message_discuss, mDataList, operationKey);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mRecyclerView.addItemDecoration(new LinearRecyclerViewDecoration(this, R.drawable.decoration_gray_00, LinearRecyclerViewDecoration.VERTIACL));


        mRefreshLayout.setOnRefreshListener(refreshLayout -> {
            switch (operationKey) {
                case KEY_DISCUSS: {
                    mPresenter.getMessageDiscuss(UserOperation.getCurrentUser().getEmail(), MessageActivity.this, CODE_GET);
                    break;
                }
                case KEY_APPRECIATE: {
                    mPresenter.getMessageAppreciate(UserOperation.getCurrentUser().getEmail(), MessageActivity.this, CODE_GET);
                    break;
                }
                case KEY_REPLY: {
                    mPresenter.getMessageReply(UserOperation.getCurrentUser().getEmail(), MessageActivity.this, CODE_GET);
                    break;
                }
            }
        });
        mRefreshLayout.setOnLoadMoreListener(refreshLayout -> {
            switch (operationKey) {
                case KEY_DISCUSS: {
                    mPresenter.loadMoreMessageDiscuss(UserOperation.getCurrentUser().getEmail(), mDataList.size(), MessageActivity.this, CODE_MORE);
                    break;
                }
                case KEY_APPRECIATE: {
                    mPresenter.loadMoreMessageAppreciate(UserOperation.getCurrentUser().getEmail(), mDataList.size(), MessageActivity.this, CODE_MORE);
                    break;
                }
                case KEY_REPLY: {
                    mPresenter.loadMoreMessageReply(UserOperation.getCurrentUser().getEmail(), mDataList.size(), MessageActivity.this, CODE_MORE);
                    break;
                }
            }
        });

        mImageViewBack.setOnClickListener(this);

        mRefreshLayout.setRefreshHeader(new WaterDropHeader(this));
        mRefreshLayout.setRefreshFooter(new ClassicsFooter(this));

        mTextViewTitle.setText(titles[operationKey - 1]);
        mAdapter.setOnItemChildClickListener(this);
    }

    @Override
    public void result(Throwable e, Object data, int requestCode) {
        if (e == null) {
            List<MessageDiscussBean> list = (List<MessageDiscussBean>) data;
            if (requestCode == CODE_GET) {
                mDataList.clear();
                mAdapter.addData(list);
            } else {
                mAdapter.addData(list);
            }
        } else {
            Toasty.success(this, "加载内容失败!");
        }
        mRefreshLayout.finishRefresh();
        mRefreshLayout.finishLoadMore();
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

    private Operation prepareOperation(int position) {
        MessageDiscussBean bean = mDataList.get(position);
        Operation operation = new Operation();
        operation.setId(bean.getNoteId());
        operation.setName(bean.getName());
        operation.setChild(true);
        operation.setParentContent(prepareParentContent(bean));
        operation.setParentEmail(bean.getEmail());
        return operation;
    }

    public String prepareParentContent(MessageDiscussBean bean) {
        StringBuilder stringBuilder = new StringBuilder(bean.getDiscussContent());
        if (bean.getDiscussImages() == null) {
            return stringBuilder.toString();
        }
        for (String string : bean.getDiscussImages()) {
            stringBuilder.append("[图片]");
        }
        return stringBuilder.toString();
    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        switch (view.getId()) {
            case R.id.item_recyclerView_message_discuss_textView_reply: {
                Operation operation = prepareOperation(position);
                Intent intent = new Intent(this, NewActivity.class);
                intent.putExtra(NewActivity.KEY_OPERATION_REPLY, operation);
                startActivity(intent);
                break;
            }
            case R.id.item_recyclerView_message_discuss_textView_content: {
                Intent intent = new Intent(this, DiscussActivity.class);
                intent.putExtra(DiscussActivity.KEY_NOTE_ID, mDataList.get(position).getNoteId());
                intent.putExtra(DiscussActivity.KEY_NOTE_EMAIL, UserOperation.getCurrentUser().getEmail());
                startActivity(intent);
                break;
            }
        }
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
        switch (operationKey) {
            case KEY_DISCUSS: {
                messageOperation.readDiscuss(email);
                break;
            }
            case KEY_APPRECIATE: {
                messageOperation.readAppreciate(email);
                break;
            }
            case KEY_REPLY: {
                messageOperation.readReply(email);
                break;
            }
        }
        super.onDestroy();

    }
}
