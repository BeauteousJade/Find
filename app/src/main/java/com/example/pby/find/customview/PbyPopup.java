package com.example.pby.find.customview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.pby.find.R;
import com.zyyoona7.lib.EasyPopup;

/**
 * Created by pby on 2018/2/22.
 */

public class PbyPopup extends EasyPopup implements View.OnClickListener {
    private View mContentView = null;

    public PbyPopup(Context context) {
        super(context);
    }

    public interface OnMenuItemClickClickListener {
        void onMenuItemClickClick(PbyPopup pbyPopup, View view);
    }

    private OnMenuItemClickClickListener mListener = null;

    public PbyPopup setContentView(int layoutId) {
        mContentView = LayoutInflater.from(getContext()).inflate(layoutId, null);
        return setContentView(mContentView);
    }

    public PbyPopup setText(int id, String text) {
        ((TextView) getView(id)).setText(text);
        return this;
    }

    public PbyPopup setHintText(int id, String text) {
        ((EditText) getView(id)).setHint(text);
        return this;
    }


    public PbyPopup setImage(int id, String path) {
        Glide.with(getContext()).load(path).into((ImageView) getView(id));

        return this;
    }

    public PbyPopup setImage(int id, int imageId) {
        Glide.with(getContext()).load(imageId).into((ImageView) getView(id));
        return this;
    }

    public PbyPopup createPopup() {
        PopupWindow popupWindow = super.createPopup().getPopupWindow();
        popupWindow.setFocusable(false);
        return super.createPopup();
    }

    public String getText(int id) {
        return ((TextView) getView(id)).getText().toString();
    }

    @Override
    public PbyPopup setFocusable(boolean focusable) {
        super.setFocusable(focusable);
        return this;
    }

    @Override
    public PbyPopup setBackgroundDimEnable(boolean isDim) {
        super.setBackgroundDimEnable(isDim);
        return this;
    }

    @Override
    public PbyPopup setAnimationStyle(int animationStyle) {
        super.setAnimationStyle(animationStyle);
        return this;
    }

    public PbyPopup addOnMenuItemClickClick(int id) {
        getView(id).setOnClickListener(this);
        return this;
    }

    public PbyPopup setOnMenuItemClickClickListener(OnMenuItemClickClickListener listener) {
        this.mListener = listener;
        return this;
    }


    @Override
    public PbyPopup setDimValue(float dimValue) {
        return super.setDimValue(dimValue);
    }

    public <T extends View> T getView(int id) {
        return mContentView.findViewById(id);
    }

    @Override
    public void onClick(View v) {
        mListener.onMenuItemClickClick(this, v);
    }
}
