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
import com.example.net.inter.impl.FollowOperation;
import com.example.net.inter.impl.UserOperation;
import com.example.pby.find.R;
import com.example.pby.find.activity.base.BaseActivity;
import com.example.pby.find.adapter.list.MineNoteAdapter;
import com.example.pby.find.bean.recyclerView.MessageDiscussBean;
import com.example.pby.find.bean.recyclerView.MessageFollowBean;
import com.example.pby.find.bean.recyclerView.NoteBean;
import com.example.pby.find.constant.ConstVariable;
import com.example.pby.find.decoration.LinearRecyclerViewDecoration;
import com.example.pby.find.mvp.presenter.LoadPresenter;
import com.scwang.smartrefresh.header.WaterDropHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pby on 2018/2/21.
 */

public class MineNoteActivity extends BaseActivity implements View.OnClickListener, BaseQuickAdapter.OnItemChildClickListener {
    private ImageView mImageViewBack = null;
    private TextView mTextViewTitle = null;

    private RecyclerView mRecyclerView = null;
    private List mDataList = null;
    private MineNoteAdapter mAdapter = null;

    private SmartRefreshLayout mRefreshLayout = null;

    private LoadPresenter mPresenter = null;


    private int mType = -1;

    public static final int TYPE_NOTE = 0;
    public static final int TYPE_FOLLOW = 1;
    public static final int TYPE_FANS = 2;
    public static final int TYPE_COLLECTION = 3;
    public static final int TYPE_APPRECIATE = 4;
    public static final int TYPE_RECORD = 5;
    public static final int TYPE_DISCUSS = 6;


    public static final String KEY_TYPE = "type";

    public static final String[] TITLES = {"我的帖子", "我的关注", "我的粉丝", "我的收藏", "我的喜欢", "我的足迹", "我的评论"};
    public static final int[] LAYOUTS = {R.layout.item_recyclerview_note_02, R.layout.item_recyclerview_message_follow,
            R.layout.item_recyclerview_message_follow, R.layout.item_recyclerview_note_02,
            R.layout.item_recyclerview_note_02, R.layout.item_recyclerview_note_02,
            R.layout.item_recyclerview_message_discuss};

    @Override

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine_note);
        setImmerseMode();

    }

    @Override
    public void init() {
        mType = getIntent().getIntExtra(KEY_TYPE, -1);

        mPresenter = new LoadPresenter();
        mImageViewBack = findView(R.id.toolbar_back_ok_imageView_back);
        mTextViewTitle = findView(R.id.toolbar_back_ok_textView_title);

        mRecyclerView = findView(R.id.activity_mine_note_recyclerView);
        mRefreshLayout = findView(R.id.activity_mine_note_refreshLayout);
        mDataList = new ArrayList<>();
        mAdapter = new MineNoteAdapter(LAYOUTS[mType], mDataList, mType);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mRecyclerView.addItemDecoration(new LinearRecyclerViewDecoration(this, R.drawable.decoration_gray_00, LinearRecyclerViewDecoration.VERTIACL));

        mRefreshLayout.setRefreshHeader(new WaterDropHeader(this));
        mRefreshLayout.setRefreshFooter(new ClassicsFooter(this));
        mRefreshLayout.setOnRefreshListener(refreshLayout -> {
            switch (mType) {
                case TYPE_NOTE: {
                    mPresenter.getAllNote(UserOperation.getCurrentUser().getEmail(), MineNoteActivity.this, ConstVariable.CODE_GET);
                    break;
                }
                case TYPE_FOLLOW: {
                    mPresenter.getFollow(UserOperation.getCurrentUser().getEmail(), MineNoteActivity.this, ConstVariable.CODE_GET);

                    break;
                }
                case TYPE_FANS: {
                    mPresenter.getFans(UserOperation.getCurrentUser().getEmail(), MineNoteActivity.this, ConstVariable.CODE_GET);
                    break;
                }
                case TYPE_COLLECTION: {
                    mPresenter.getCollectionNote(UserOperation.getCurrentUser().getEmail(), 0, MineNoteActivity.this, ConstVariable.CODE_GET);
                    break;
                }
                case TYPE_APPRECIATE: {
                    mPresenter.getAppreciateNote(UserOperation.getCurrentUser().getEmail(), MineNoteActivity.this, ConstVariable.CODE_GET);
                    break;
                }
                case TYPE_RECORD: {
                    mPresenter.getRecordNote(UserOperation.getCurrentUser().getEmail(), MineNoteActivity.this, ConstVariable.CODE_GET);
                    break;
                }
                case TYPE_DISCUSS: {
                    mPresenter.getDiscuss(UserOperation.getCurrentUser().getEmail(), 0, MineNoteActivity.this, ConstVariable.CODE_GET);
                    break;
                }
            }
        });
        mRefreshLayout.setOnLoadMoreListener(refreshLayout -> {
            switch (mType) {
                case TYPE_NOTE: {
                    mPresenter.loadMoreAllNote(UserOperation.getCurrentUser().getEmail(), mDataList.size(), MineNoteActivity.this, ConstVariable.CODE_MORE);
                    break;
                }
                case TYPE_FOLLOW: {
                    mPresenter.loadMoreFollow(UserOperation.getCurrentUser().getEmail(), mDataList.size(), MineNoteActivity.this, ConstVariable.CODE_GET);
                    break;
                }
                case TYPE_FANS: {
                    mPresenter.loadMoreFans(UserOperation.getCurrentUser().getEmail(), mDataList.size(), MineNoteActivity.this, ConstVariable.CODE_MORE);
                    break;

                }
                case TYPE_COLLECTION: {
                    mPresenter.getCollectionNote(UserOperation.getCurrentUser().getEmail(), mDataList.size(), MineNoteActivity.this, ConstVariable.CODE_MORE);
                    break;
                }
                case TYPE_APPRECIATE: {
                    mPresenter.loadMoreAppreciateNote(UserOperation.getCurrentUser().getEmail(), mDataList.size(), MineNoteActivity.this, ConstVariable.CODE_MORE);
                    break;
                }
                case TYPE_RECORD: {
                    mPresenter.loadMoreRecordNote(UserOperation.getCurrentUser().getEmail(), mDataList.size(), MineNoteActivity.this, ConstVariable.CODE_MORE);
                    break;
                }
                case TYPE_DISCUSS: {
                    mPresenter.getDiscuss(UserOperation.getCurrentUser().getEmail(), mDataList.size(), MineNoteActivity.this, ConstVariable.CODE_MORE);
                    break;
                }
            }
        });

        mTextViewTitle.setText(TITLES[mType]);

        mImageViewBack.setOnClickListener(this);
        mAdapter.setOnItemChildClickListener(this);
    }

    @Override
    public void result(Throwable e, Object data, int requestCode) {
        if (e == null) {
            switch (mType) {
                case TYPE_NOTE: {
                    List<NoteBean> list = (List<NoteBean>) data;
                    if (requestCode == ConstVariable.CODE_GET) {
                        mDataList.clear();
                    }
                    mAdapter.addData(list);
                    break;
                }
                case TYPE_FOLLOW:
                case TYPE_FANS: {
                    List<MessageFollowBean> list = (List<MessageFollowBean>) data;
                    if (requestCode == ConstVariable.CODE_GET) {
                        mDataList.clear();
                    }
                    mAdapter.addData(list);
                    break;
                }
                case TYPE_COLLECTION:
                case TYPE_RECORD:
                case TYPE_APPRECIATE: {
                    List<NoteBean> list = (List<NoteBean>) data;
                    if (requestCode == ConstVariable.CODE_GET) {
                        mDataList.clear();
                    }
                    mAdapter.addData(list);
                    break;
                }
                case TYPE_DISCUSS: {
                    List<MessageDiscussBean> list = (List<MessageDiscussBean>) data;
                    if (requestCode == ConstVariable.CODE_GET) {
                        mDataList.clear();
                    }
                    mAdapter.addData(list);
                    break;
                }
            }
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

    @Override
    protected void onResume() {
        super.onResume();
        if (mDataList.size() == 0) {
            mRefreshLayout.autoRefresh();
        }
    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        switch (view.getId()) {
            case R.id.item_recyclerView_note_constraintLayout: {
                NoteBean noteBean = (NoteBean) mDataList.get(position);
                Intent intent = new Intent(this, DiscussActivity.class);
                intent.putExtra(DiscussActivity.KEY_NOTE_ID, noteBean.getId());
                intent.putExtra(DiscussActivity.KEY_NOTE_EMAIL, UserOperation.getCurrentUser().getEmail());
                startActivity(intent);
                break;
            }
            case R.id.item_recyclerView_message_follow_textView_follow: {
                final TextView textView = (TextView) view;
                final String email = UserOperation.getCurrentUser().getEmail();
                final String followEmail = ((MessageFollowBean) (mDataList.get(position))).getEmail();
                final FollowOperation followOperation = new FollowOperation();
                final String string = textView.getText().toString();
                if (mType == TYPE_FANS) {
                    if (ConstVariable.STRING_FOLLOW.equals(string)) {
                        textView.setText("取消关注");
                        followOperation.follow(email, followEmail);
                    } else if (ConstVariable.STRING_NO_CANCEL.equals(string)) {
                        textView.setText("关注");
                        followOperation.cancelFollow(email, followEmail);
                    }
                } else {
                    followOperation.cancelFollow(email, followEmail);
                    mAdapter.remove(position);
                }
            }
        }
    }
}
