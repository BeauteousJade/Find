package com.example.pby.find.customview;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.pby.find.R;

import org.w3c.dom.Text;

import me.zhanghai.android.materialprogressbar.MaterialProgressBar;

/**
 * Created by pby on 2018/2/2.
 */

public class AreaHeadView implements View.OnClickListener {
    private View mView;
    private ImageView mImageViewIcon = null;
    private TextView mTextViewName = null;
    private ImageView mImageViewLevel = null;
    private TextView mTextViewRank = null;
    private MaterialProgressBar mProgressBar = null;
    private TextView mTextViewAll = null;
    private TextView mTextViewHot = null;

    private Activity mActivity = null;
    private OnTextViewClickListener mListener = null;

    public interface OnTextViewClickListener {
        void onTextViewClick(int fragmentId);
    }

    public AreaHeadView(Activity activity, int layoutId) {
        mView = LayoutInflater.from(activity).inflate(layoutId, null);
        mImageViewIcon = mView.findViewById(R.id.head_recyclerView_area_ImageViewView_icon);
        mImageViewLevel = mView.findViewById(R.id.head_recyclerView_area_imageView_level);
        mTextViewName = mView.findViewById(R.id.head_recyclerView_area_textView_name);
        mProgressBar = mView.findViewById(R.id.head_recyclerView_area_CustomHorizontalProgressBar_experience);
        mTextViewAll = mView.findViewById(R.id.head_recyclerView_area_textView_all);
        mTextViewHot = mView.findViewById(R.id.head_recyclerView_area_textView_hot);
        mTextViewRank = mView.findViewById(R.id.head_recyclerView_area_textView_rank);
        this.mActivity = activity;

        mTextViewAll.setOnClickListener(this);
        mTextViewHot.setOnClickListener(this);
    }

    public AreaHeadView setIcon(int iconId) {
        Glide.with(mActivity).load(iconId).into(mImageViewIcon);
        return this;
    }

    public AreaHeadView setLevel(int level) {
        Uri uri = Uri.parse("file:///android_asset/level/level_" + (level < 10 ? "0" + level : level) + ".png");
        Glide.with(mActivity).load(uri).into(mImageViewLevel);
        return this;
    }

    public AreaHeadView setName(String name) {
        mTextViewName.setText(name);
        return this;
    }

    public AreaHeadView setRank(String rank) {
        mTextViewRank.setText(rank);
        return this;
    }

    public AreaHeadView setTextViewClickListener(OnTextViewClickListener listener) {
        this.mListener = listener;
        return this;
    }

    public AreaHeadView callOnClick() {
        mTextViewAll.callOnClick();
        return this;
    }
    public AreaHeadView setProcess(int process){

        mProgressBar.setProgress(process);
        return this;
    }

    public View build() {
        return mView;
    }

    @Override
    public void onClick(View v) {
        resetTextColor();
        ((TextView) v).setTextColor(mActivity.getResources().getColor(R.color.color_textView_click));
        mListener.onTextViewClick(v.getId());
    }

    private void resetTextColor() {
        mTextViewAll.setTextColor(mActivity.getResources().getColor(R.color.color_textView_normal));
        mTextViewHot.setTextColor(mActivity.getResources().getColor(R.color.color_textView_normal));
    }
}
