package com.example.pby.find.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SimpleItemAnimator;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.net.inter.impl.NoteOperation;
import com.example.net.inter.impl.UserOperation;
import com.example.pby.find.R;
import com.example.pby.find.activity.base.BaseActivity;
import com.example.pby.find.adapter.list.NoteAdapter;
import com.example.pby.find.bean.recyclerView.AreaBean;
import com.example.pby.find.bean.recyclerView.NoteBean;
import com.example.pby.find.constant.ConstVariable;
import com.example.pby.find.customview.AreaHeadView;
import com.example.pby.find.decoration.LinearRecyclerViewDecoration;
import com.example.pby.find.mvp.presenter.LoadPresenter;
import com.scwang.smartrefresh.header.WaterDropHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;

import java.util.ArrayList;
import java.util.List;

import es.dmoral.toasty.Toasty;

/**
 * Created by pby on 2018/2/2.
 */

public class AreaActivity extends BaseActivity implements View.OnClickListener, BaseQuickAdapter.OnItemChildClickListener, AreaHeadView.OnTextViewClickListener {
    private ImageView mImageViewBack = null;
    private TextView mTextViewTitle = null;

    private AreaHeadView mAreaHeadView = null;
    private ImageView mImageViewNew = null;


    private RecyclerView mRecyclerView = null;
    private List<NoteBean> mDataList = null;
    private NoteAdapter mAdapter = null;

    private AreaBean mAreaBean = null;


    private SmartRefreshLayout mRefreshLayout = null;




    private LoadPresenter mNormalPresenter = null;

    private int mDataCode = -1;
    public static final String KEY_AREA = "area";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_area);
        setImmerseMode();

    }

    @Override
    public void init() {
        mAreaBean = getIntent().getParcelableExtra(KEY_AREA);

        mImageViewBack = findView(R.id.toolbar_back_search_imageView_back);
        mTextViewTitle = findView(R.id.toolbar_back_search_textView_title);
        mRecyclerView = findView(R.id.activity_area_recyclerView);
        mImageViewNew = findView(R.id.activity_area_imageView_new);
        mRefreshLayout = findView(R.id.activity_area_refreshLayout);

        mNormalPresenter = new LoadPresenter();

        mDataList = new ArrayList<>();
        mAdapter = new NoteAdapter(R.layout.item_recyclerview_note_00, mDataList);
        mRecyclerView.setAdapter(mAdapter);
        ((SimpleItemAnimator) mRecyclerView.getItemAnimator()).setSupportsChangeAnimations(false);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mRecyclerView.addItemDecoration(new LinearRecyclerViewDecoration(this, R.drawable.decoration_gray_01, LinearRecyclerViewDecoration.VERTIACL));

        mAreaHeadView = new AreaHeadView(this, R.layout.head_recyclerview_area)
                .setIcon(mAreaBean.getIconId())
                .setName(mAreaBean.getName())
                .setRank(ConstVariable.RANK_LIST.get(UserOperation.getCurrentUser().getLevel() - 1))
                .setLevel(UserOperation.getCurrentUser().getLevel())
                .setTextViewClickListener(this)
                .setProcess((int) (UserOperation.getCurrentUser().getExperience() * 100.0 / ConstVariable.EXPERIENCE_LIST.get(UserOperation.getCurrentUser().getLevel() - 1)))
                .callOnClick();

        mTextViewTitle.setText(mAreaBean.getName());
        mAdapter.addHeaderView(mAreaHeadView.build());
        mRefreshLayout.setRefreshHeader(new WaterDropHeader(this));
        mRefreshLayout.setRefreshFooter(new ClassicsFooter(this));
        mRefreshLayout.setOnRefreshListener(refreshLayout -> {
            mNormalPresenter.refreshNote(UserOperation.getCurrentUser().getEmail(), mAreaBean.getName(), AreaActivity.this, ConstVariable.CODE_GET, mDataCode);
        });
        mRefreshLayout.setOnLoadMoreListener(refreshLayout -> {
            mNormalPresenter.lodMoreNote(UserOperation.getCurrentUser().getEmail(), mAreaBean.getName(), mDataList.size(), AreaActivity.this, ConstVariable.CODE_MORE, mDataCode);
        });
        mImageViewBack.setOnClickListener(this);
        mImageViewNew.setOnClickListener(this);
        mAdapter.setOnItemChildClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.toolbar_back_ok_imageView_back: {
                onBackPressed();
                break;
            }
            case R.id.activity_area_imageView_new: {
                Intent intent = new Intent(this, NewActivity.class);
                intent.putExtra(NewActivity.KEY_AREA_NAME, mAreaBean.getName());
                startActivity(intent);
                break;
            }
        }
    }

    @Override
    public void result(Throwable e, Object data, int requestCode) {
        if (e == null) {
            List<NoteBean> list = (List<NoteBean>) data;
            switch (requestCode) {
                case ConstVariable.CODE_GET: {
                    mDataList.clear();
                    mAdapter.addData(list);
                    mAdapter.setAnimation(true);
                    break;
                }
                case ConstVariable.CODE_MORE: {
                    mAdapter.addData(list);
                }
            }
            Toasty.success(this, "加载内容成功!").show();

        } else {
            e.printStackTrace();
            Toasty.normal(this, "加载内容失败!" + e.getMessage(), Toast.LENGTH_LONG).show();
        }
        mRefreshLayout.finishRefresh();
        mRefreshLayout.finishLoadMore();
    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        switch (view.getId()) {
            case R.id.item_recyclerView_note_imageView_appreciate: {
                NoteBean noteBean = mDataList.get(position);
                if (noteBean.getIsAppreciate() == 0) {
                    NoteOperation noteOperation = new NoteOperation();
                    noteOperation.appreciate(mDataList.get(position).getId(), UserOperation.getCurrentUser().getEmail());
                    noteBean.setAppreciateNumber(noteBean.getAppreciateNumber() + 1);
                    noteBean.setIsAppreciate(1);
                    mAdapter.setAnimationPosition(position + 1);

                    mAdapter.notifyItemChanged(position + 1);
                } else {
                    NoteOperation noteOperation = new NoteOperation();
                    noteOperation.cancelAppreciate(mDataList.get(position).getId(), UserOperation.getCurrentUser().getEmail());
                    noteBean.setAppreciateNumber(noteBean.getAppreciateNumber() - 1);
                    noteBean.setIsAppreciate(0);
                    mAdapter.setAnimationPosition(position + 1);
                    mAdapter.notifyItemChanged(position + 1);
                }
                break;
            }
            case R.id.item_recyclerView_note_textView_content:
            case R.id.item_recyclerView_note_linearLayout_images: {
                Intent intent = new Intent(this, DiscussActivity.class);
                intent.putExtra(DiscussActivity.KEY_NOTE_ID, mDataList.get(position).getId());
                intent.putExtra(DiscussActivity.KEY_NOTE_EMAIL, mDataList.get(position).getEmail());
                startActivity(intent);
                break;
            }
        }
    }

    @Override
    public void onTextViewClick(int id) {
        switch (id) {
            case R.id.head_recyclerView_area_textView_all: {
                mRefreshLayout.autoRefresh();
                mDataCode = ConstVariable.CODE_ALL;
                break;
            }
            case R.id.head_recyclerView_area_textView_hot: {
                mRefreshLayout.autoRefresh();
                mDataCode = ConstVariable.CODE_HOT;
                break;
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

}
