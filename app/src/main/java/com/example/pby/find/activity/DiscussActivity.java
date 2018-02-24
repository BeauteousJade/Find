package com.example.pby.find.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.net.inter.impl.CollectionOperation;
import com.example.net.inter.impl.FollowOperation;
import com.example.net.inter.impl.NoteOperation;
import com.example.net.inter.impl.ReadOperation;
import com.example.net.inter.impl.UserOperation;
import com.example.pby.find.R;
import com.example.pby.find.activity.base.BaseActivity;
import com.example.pby.find.adapter.list.DiscussAdapter;
import com.example.pby.find.bean.Operation;
import com.example.pby.find.bean.recyclerView.DiscussBean;
import com.example.pby.find.bean.recyclerView.NoteBean;
import com.example.pby.find.constant.ConstVariable;
import com.example.pby.find.customview.DiscussHeadView;
import com.example.pby.find.customview.PbyPopup;
import com.example.pby.find.decoration.LinearRecyclerViewDecoration;
import com.example.pby.find.mvp.presenter.LoadPresenter;
import com.example.pby.find.util.PopupUtil;
import com.scwang.smartrefresh.header.WaterDropHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.zyyoona7.lib.HorizontalGravity;
import com.zyyoona7.lib.VerticalGravity;

import java.util.ArrayList;
import java.util.List;

import es.dmoral.toasty.Toasty;

/**
 * Created by pby on 2018/2/9.
 */

public class DiscussActivity extends BaseActivity implements View.OnClickListener, BaseQuickAdapter.OnItemChildClickListener, PbyPopup.OnMenuItemClickClickListener {
    private ImageView mImageViewBack;
    private TextView mTextViewTitle = null;
    private ImageView mImageViewMore = null;
    private ImageView mImageViewAppreciate = null;
    private Button mButtonReply = null;


    private SmartRefreshLayout mRefreshLayout = null;

    private RecyclerView mRecyclerView = null;
    private List<DiscussBean> mDataList = null;
    private DiscussAdapter mAdapter = null;

    private DiscussHeadView mHeadView = null;


    private LoadPresenter mPresenter = null;

    private PbyPopup mMorePopup = null;

    private String mNoteId = null;
    private String mEmail = null;
    private NoteBean mNoteBean = null;

    public static final String KEY_NOTE_ID = "noteId";
    public static final String KEY_NOTE_EMAIL = "noteEmail";

    public static final int CODE_REFRESH = 1;
    public static final int CODE_MORE = 2;
    public static final int CODE_REFRESH_NOTE = 3;
    public static final int CODE_VALIDATE = 4;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discuss);
        setImmerseMode();
    }

    @Override
    public void init() {
        mNoteId = getIntent().getStringExtra(KEY_NOTE_ID);
        mEmail = getIntent().getStringExtra(KEY_NOTE_EMAIL);

        mImageViewBack = findView(R.id.toolbar_back_more_imageView_back);
        mTextViewTitle = findView(R.id.toolbar_back_more_textView_title);
        mImageViewMore = findView(R.id.toolbar_back_more_imageView_more);

        mImageViewAppreciate = findView(R.id.activity_discuss_imageView_appreciate);
        mButtonReply = findView(R.id.activity_discuss_button_reply);

        mRecyclerView = findView(R.id.activity_discuss_recyclerView);
        mRefreshLayout = findView(R.id.activity_discuss_refreshLayout);

        mHeadView = new DiscussHeadView(this, R.layout.head_recyclerview_discuss);

        mMorePopup = PopupUtil.buildMore(R.layout.popup_layout, this)
                .setOnMenuItemClickClickListener(this)
                .addOnMenuItemClickClick(R.id.popup_more_relativeLayout_share)
                .addOnMenuItemClickClick(R.id.popup_more_relativeLayout_follow)
                .addOnMenuItemClickClick(R.id.popup_more_relativeLayout_collection)
                .createPopup();

        mDataList = new ArrayList<>();
        mAdapter = new DiscussAdapter(R.layout.item_recyclerview_discuss, mDataList);
        mAdapter.addHeaderView(mHeadView.build());
        mAdapter.setHostEmail(mEmail);

        mPresenter = new LoadPresenter();


        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mRecyclerView.addItemDecoration(new LinearRecyclerViewDecoration(this, R.drawable.decoration_gray_00, LinearRecyclerViewDecoration.VERTIACL));
        mRecyclerView.setAdapter(mAdapter);

        mRefreshLayout.setRefreshHeader(new WaterDropHeader(this));
        mRefreshLayout.setRefreshFooter(new ClassicsFooter(this));

        mImageViewBack.setOnClickListener(this);
        mImageViewAppreciate.setOnClickListener(this);
        mButtonReply.setOnClickListener(this);
        mAdapter.setOnItemChildClickListener(this);
        mImageViewMore.setOnClickListener(this);

        mRefreshLayout.setOnRefreshListener(refreshLayout -> {
            mPresenter.getNote(UserOperation.getCurrentUser().getEmail(), mNoteId, DiscussActivity.this, CODE_REFRESH_NOTE);
            mPresenter.refreshDiscuss(mNoteId, DiscussActivity.this, CODE_REFRESH);

        });
        mRefreshLayout.setOnLoadMoreListener(refreshLayout -> mPresenter.loadMoreDiscuss(mNoteId, mDataList.size(), DiscussActivity.this, CODE_MORE));


        mTextViewTitle.setText("");


        mPresenter.validateFollow(UserOperation.getCurrentUser().getEmail(), mEmail, this, CODE_VALIDATE);
        mPresenter.validateCollection(UserOperation.getCurrentUser().getEmail(), mNoteId, this, ConstVariable.CODE_COLLECTION);
    }

    @Override
    public void onClick(View v) {
        if (mNoteBean == null) {
            return;
        }

        switch (v.getId()) {
            case R.id.toolbar_back_more_imageView_back: {
                onBackPressed();
                break;
            }
            case R.id.activity_discuss_imageView_appreciate: {

                final NoteOperation noteOperation = new NoteOperation();
                if (mNoteBean.getIsAppreciate() == 0) {
                    mNoteBean.setIsAppreciate(1);
                    noteOperation.appreciate(mNoteId, UserOperation.getCurrentUser().getEmail());
                    Glide.with(this).load(R.mipmap.icon_appreciate_01).into(mImageViewAppreciate);
                } else {
                    mNoteBean.setIsAppreciate(0);
                    noteOperation.cancelAppreciate(mNoteId, UserOperation.getCurrentUser().getEmail());
                    Glide.with(this).load(R.mipmap.icon_appreciate_00).into(mImageViewAppreciate);
                }
                Animation animation = AnimationUtils.loadAnimation(this, R.anim.anim_scale);
                mImageViewAppreciate.startAnimation(animation);
                break;
            }
            case R.id.activity_discuss_button_reply: {
                toReply();
                break;
            }
            case R.id.toolbar_back_more_imageView_more: {
                mMorePopup.showAtAnchorView(mImageViewMore, VerticalGravity.BELOW, HorizontalGravity.ALIGN_RIGHT, 20, 0);
                break;
            }
        }
    }

    private void toReply() {
        Intent intent = new Intent(this, NewActivity.class);
        Operation operation = new Operation();
        operation.setId(mNoteId);
        operation.setChild(false);
        operation.setName(mNoteBean.getName());
        intent.putExtra(NewActivity.KEY_OPERATION_REPLY, operation);
        startActivity(intent);
    }

    @Override
    public void result(Throwable e, Object data, int requestCode) {
        if (e == null) {
            switch (requestCode) {
                case CODE_REFRESH: {
                    List<DiscussBean> list = (List<DiscussBean>) data;
                    mDataList.clear();
                    mAdapter.addData(list);
                    Toasty.success(this, "加载评论成功!").show();
                    break;
                }
                case CODE_MORE: {
                    List<DiscussBean> list = (List<DiscussBean>) data;
                    mAdapter.addData(list);
                    break;
                }
                case CODE_VALIDATE: {
                    setFollow(((List<Integer>) data).get(0));
                    break;
                }
                case ConstVariable.CODE_COLLECTION: {
                    setCollection(((List<Integer>) data).get(0));
                    break;
                }
                case CODE_REFRESH_NOTE: {
                    List<NoteBean> list = (List<NoteBean>) data;
                    if (list != null && list.size() != 0) {
                        mNoteBean = list.get(0);
                        mHeadView.setHead(mNoteBean.getHead())
                                .setContent(mNoteBean.getContent())
                                .setLevel(UserOperation.getCurrentUser().getLevel())
                                .setTime(mNoteBean.getTime())
                                .setImages(mNoteBean.getImages())
                                .setName(mNoteBean.getName())
                                .setTime(mNoteBean.getTime());

                        if (mNoteBean.getIsAppreciate() == 1) {
                            Glide.with(this).load(R.mipmap.icon_appreciate_01).into(mImageViewAppreciate);
                        } else {
                            Glide.with(this).load(R.mipmap.icon_appreciate_00).into(mImageViewAppreciate);
                        }
                    }
                    break;
                }
            }
        } else {
            Toasty.normal(this, "加载评论失败!").show();
        }
        mRefreshLayout.finishRefresh();
        mRefreshLayout.finishLoadMore();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mRefreshLayout.autoRefresh();
    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        Intent intent = new Intent(this, NewActivity.class);
        Operation operation = prepareOperation(position);
        intent.putExtra(NewActivity.KEY_OPERATION_REPLY, operation);
        startActivity(intent);
    }

    private Operation prepareOperation(int position) {
        DiscussBean bean = mDataList.get(position);
        Operation operation = new Operation();
        operation.setId(mNoteId);
        operation.setName(bean.getName());
        operation.setChild(true);
        operation.setParentContent(prepareParentContent(bean));
        operation.setParentEmail(bean.getEmail());
        return operation;
    }

    public String prepareParentContent(DiscussBean bean) {
        StringBuilder stringBuilder = new StringBuilder(bean.getContent());
        if (bean.getImages() == null) {
            return stringBuilder.toString();
        }
        for (String string : bean.getImages()) {
            stringBuilder.append("[图片]");
        }
        return stringBuilder.toString();
    }

    @Override
    protected void onDestroy() {
        mMorePopup.dismiss();
        ReadOperation readOperation = new ReadOperation();
        readOperation.addRead(UserOperation.getCurrentUser().getEmail(), mNoteId);
        super.onDestroy();
    }


    public void setFollow(int code) {
        if (code == 0) {
            mMorePopup.setText(R.id.popup_more_textView_follow, "关注楼主")
                    .setImage(R.id.popup_more_imageView_follow, R.mipmap.icon_follow_01);
        } else {
            mMorePopup.setText(R.id.popup_more_textView_follow, "取消关注")
                    .setImage(R.id.popup_more_imageView_follow, R.mipmap.icon_follow_02);
        }
    }

    public void setCollection(int code) {
        if (code == 0) {
            mMorePopup.setText(R.id.popup_more_textView_collection, "收藏帖子")
                    .setImage(R.id.popup_more_imageView_collection, R.mipmap.icon_mine_item_collection);
        } else {
            mMorePopup.setText(R.id.popup_more_textView_collection, "取消收藏")
                    .setImage(R.id.popup_more_imageView_collection, R.mipmap.icon_collection_02);
        }
    }


    @Override
    public void onMenuItemClickClick(PbyPopup pbyPopup, View view) {
        switch (view.getId()) {
            case R.id.popup_more_relativeLayout_follow: {
                String text = mMorePopup.getText(R.id.popup_more_textView_follow);
                FollowOperation followOperation = new FollowOperation();
                if (text.equals("关注楼主")) {
                    if (mEmail.equals(UserOperation.getCurrentUser().getEmail())) {
                        Toasty.normal(this, "不能关注自己").show();
                    } else {
                        setFollow(1);
                        followOperation.follow(UserOperation.getCurrentUser().getEmail(), mEmail);
                    }
                } else {
                    setFollow(0);
                    followOperation.cancelFollow(UserOperation.getCurrentUser().getEmail(), mEmail);
                }
            }
            case R.id.popup_more_relativeLayout_collection: {
                final String text = mMorePopup.getText(R.id.popup_more_textView_collection);
                final CollectionOperation operation = new CollectionOperation();
                if (text.equals("收藏帖子")) {
                    setCollection(1);
                    operation.collection(UserOperation.getCurrentUser().getEmail(), mNoteId);
                } else {
                    setCollection(0);
                    operation.cancelCollection(UserOperation.getCurrentUser().getEmail(), mNoteId);
                }
                break;
            }
        }
        pbyPopup.dismiss();
    }
}
