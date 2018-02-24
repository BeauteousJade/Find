package com.example.pby.find.customview;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import retrofit2.http.HEAD;

/**
 * Created by pby on 2018/2/22.
 */

public class HeadView implements View.OnClickListener {
    private View mView = null;
    private Activity mActivity = null;


    public interface OnHeadViewItemClickListener {
        void OnHeadViewItemClick(HeadView headView, View view);
    }

    private OnHeadViewItemClickListener mListener = null;

    public HeadView(Activity activity, int layoutId) {
        this.mActivity = activity;
        mView = LayoutInflater.from(activity).inflate(layoutId, null);
    }

    public HeadView setText(int id, String text) {
        getTextView(id).setText(text);
        return this;
    }

    public HeadView setImage(int id, String path) {
        Glide.with(mActivity).load(path).into(getImageView(id));
        return this;
    }

    public HeadView setImageView(int id, String path, RequestOptions requestOptions) {
        Glide.with(mActivity).load(path).apply(requestOptions).into(getImageView(id));
        return this;
    }

    public HeadView addOnHeadViewItemClick(int id) {
        getView(id).setOnClickListener(this);
        return this;
    }

    public HeadView setOnHeadViewItemClickListener(OnHeadViewItemClickListener listener) {
        this.mListener = listener;
        return this;
    }

    public <T extends View> T getView(int id) {
        return mView.findViewById(id);
    }

    public ImageView getImageView(int id) {
        return mView.findViewById(id);
    }

    public TextView getTextView(int id) {
        return mView.findViewById(id);
    }

    @Override
    public void onClick(View v) {
        mListener.OnHeadViewItemClick(this, v);
    }

    public View build() {
        return mView;
    }
}
