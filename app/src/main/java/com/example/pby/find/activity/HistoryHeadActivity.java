package com.example.pby.find.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.net.inter.impl.UserOperation;
import com.example.net.net.bean.User;
import com.example.pby.find.R;
import com.example.pby.find.activity.base.BaseActivity;
import com.example.pby.find.adapter.list.ImageAdapter;
import com.example.pby.find.bean.recyclerView.ImageBean;
import com.example.pby.find.constant.ConstVariable;
import com.example.pby.find.customview.PbyPopup;
import com.example.pby.find.manager.AreaGridLayoutManager;
import com.example.pby.find.mvp.presenter.LoadPresenter;
import com.example.pby.find.util.PopupUtil;
import com.example.pby.find.util.ScreenUtil;
import com.zyyoona7.lib.HorizontalGravity;
import com.zyyoona7.lib.VerticalGravity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pby on 2018/2/22.
 */

public class HistoryHeadActivity extends BaseActivity implements View.OnClickListener, BaseQuickAdapter.OnItemChildClickListener, PbyPopup.OnMenuItemClickClickListener {
    private ImageView mImageView = null;
    private TextView mTextViewTitle = null;


    private ImageView mImageViewCurrentHead = null;

    private RecyclerView mRecyclerView = null;
    private List<ImageBean> mDataList = null;
    private ImageAdapter mAdapter = null;


    private LoadPresenter mPresenter = null;


    private PbyPopup mClickMenu = null;
    private PbyPopup mDialogMenu = null;


    private View mContentView = null;


    private int mSelectPosition = -1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_head);
    }

    @Override
    public void init() {
        mPresenter = new LoadPresenter();

        mImageView = findView(R.id.toolbar_back_ok_imageView_back);
        mTextViewTitle = findView(R.id.toolbar_back_ok_textView_title);
        mImageViewCurrentHead = findView(R.id.head_recyclerView_history_head_imageView);
        mContentView = findView(R.id.activity_history_head_contentView);

        mRecyclerView = findView(R.id.activity_history_head_recyclerView);
        mDataList = new ArrayList<>();
        mAdapter = new ImageAdapter(R.layout.item_recyclerview_image_head, mDataList);
        mRecyclerView.setAdapter(mAdapter);
        AreaGridLayoutManager layoutManager = new AreaGridLayoutManager(this, 4);
        layoutManager.setScrollEnabled(false);
        mRecyclerView.setLayoutManager(layoutManager);

        mClickMenu = PopupUtil.buildMenu(R.layout.pop_menu_head_setting, this)
                .setOnMenuItemClickClickListener(this)
                .addOnMenuItemClickClick(R.id.popup_menu_head_textView_setting)
                .addOnMenuItemClickClick(R.id.popup_menu_head_textView_delete)
                .addOnMenuItemClickClick(R.id.popup_menu_head_textView_cancel)
                .setAnimationStyle(R.style.popupAnim)
                .setWidth((int) ScreenUtil.getScreentWidth(this))
                .createPopup();

        mDialogMenu = PopupUtil.buildDialog(R.layout.popup_dialog, this)
                .setOnMenuItemClickClickListener(this)
                .addOnMenuItemClickClick(R.id.popup_dialog_textView_cancel)
                .addOnMenuItemClickClick(R.id.popup_dialog_textView_ok)
                .setText(R.id.popup_dialog_textView_title, "删除历史头像")
                .setText(R.id.popup_dialog_textView_cancel, "取消")
                .setText(R.id.popup_dialog_textView_ok, "确定")
                .setText(R.id.popup_dialog_textView_content, "删除头像，该头像不会你的历史头像中")
                .createPopup();


        mTextViewTitle.setText("历史头像");
        mImageView.setOnClickListener(this);
        mPresenter.getUserInfo(UserOperation.getCurrentUser().getEmail(), this, ConstVariable.CODE_USER);
        mPresenter.getHistoryHead(UserOperation.getCurrentUser().getEmail(), this, ConstVariable.CODE_HISTORY_HEAD);

        mAdapter.setOnItemChildClickListener(this);
        mClickMenu.setOnMenuItemClickClickListener(this);
    }

    @Override
    public void result(Throwable e, Object data, int requestCode) {
        if (e == null) {
            switch (requestCode) {
                case ConstVariable.CODE_HISTORY_HEAD: {
                    List<ImageBean> list = (List<ImageBean>) data;
                    mAdapter.addData(list);
                    break;
                }
                case ConstVariable.CODE_USER: {
                    List<User> list = (List<User>) data;
                    if (list != null && list.size() != 0) {
                        Glide.with(this).load(list.get(0).getHead()).into(mImageViewCurrentHead);
                    }
                    break;
                }
            }
        } else {

        }
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
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        mSelectPosition = position;
        mClickMenu.showAtAnchorView(mContentView, VerticalGravity.ALIGN_BOTTOM, HorizontalGravity.CENTER);
    }

    @Override
    public void onMenuItemClickClick(PbyPopup pbyPopup, View view) {
        pbyPopup.dismiss();
        switch (view.getId()) {
            case R.id.popup_menu_head_textView_setting: {
                if (mSelectPosition != -1) {
                    final String head = mDataList.get(mSelectPosition).getImagePath();
                    UserOperation operation = new UserOperation();
                    operation.setHead(UserOperation.getCurrentUser().getEmail(), head);
                    Glide.with(this).load(head).into(mImageViewCurrentHead);
                    mSelectPosition = -1;

                }
                break;
            }
            case R.id.popup_menu_head_textView_delete: {
                mDialogMenu.showAtAnchorView(mContentView, VerticalGravity.CENTER, HorizontalGravity.CENTER);
                break;
            }
            case R.id.popup_menu_head_textView_cancel: {
                mSelectPosition = -1;

                break;
            }
            case R.id.popup_dialog_textView_cancel: {
                mSelectPosition = -1;
                break;
            }
            case R.id.popup_dialog_textView_ok: {
                deleteHistoryHead();
                mSelectPosition = -1;
                break;
            }
        }
    }

    @Override
    protected void onDestroy() {
        mClickMenu.dismiss();
        super.onDestroy();
    }

    private void deleteHistoryHead() {
        Log.i("pby123", "mSelectPosition = " + mSelectPosition);
        if (mSelectPosition != -1) {
            final String head = mDataList.get(mSelectPosition).getImagePath();
            UserOperation operation = new UserOperation();
            operation.deleteHistoryHead(UserOperation.getCurrentUser().getEmail(), head);
            mAdapter.remove(mSelectPosition);
        }
    }
}
