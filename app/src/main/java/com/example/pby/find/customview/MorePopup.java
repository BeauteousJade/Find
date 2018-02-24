package com.example.pby.find.customview;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.pby.find.R;
import com.zyyoona7.lib.EasyPopup;

/**
 * Created by pby on 2018/2/12.
 */

public class MorePopup extends EasyPopup implements View.OnClickListener {

    public interface OnMenuItemClickListener {
        void onMenuItemClick(View v, int id);
    }

    private OnMenuItemClickListener mListener;

    public MorePopup(Context context) {
        super(context);
    }

    public MorePopup setContentView(int layoutId) {
        mContentView = LayoutInflater.from(getContext()).inflate(layoutId, null);
        return this;
    }

    public MorePopup addItemClickListener(int id) {
        mContentView.findViewById(id).setOnClickListener(this);
        return this;
    }

    public MorePopup setOnMenuItemClickListener(OnMenuItemClickListener listener) {
        this.mListener = listener;
        return this;
    }

    public MorePopup setFollowIcon(Activity activity, int iconId) {
        Glide.with(activity).load(iconId).into((ImageView) mContentView.findViewById(R.id.popup_more_imageView_follow));
        return this;
    }

    public MorePopup setFollowText(String text) {
        ((TextView) (mContentView.findViewById(R.id.popup_more_textView_follow))).setText(text);
        return this;
    }

    public String getFollowText() {
        return ((TextView) (mContentView.findViewById(R.id.popup_more_textView_follow))).getText().toString();
    }

    public MorePopup createPopup() {
        super.createPopup();
        return this;
    }

    @Override
    public void onClick(View v) {
        mListener.onMenuItemClick(v, v.getId());
    }
}
