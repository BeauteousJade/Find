package com.example.pby.find.customview;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.pby.find.R;
import com.example.pby.find.constant.ConstVariable;
import com.example.pby.find.util.ViewUtil;

import java.util.List;

/**
 * Created by pby on 2018/2/10.
 */

public class RandomHeadView implements View.OnClickListener {
    private View mView = null;
    private ImageView mImageViewHead = null;
    private TextView mTextViewName = null;
    private TextView mTextViewTime = null;
    private TextView mTextViewAreaName = null;
    private RichTextView mRichTextViewContent = null;
    private TextView mTextViewRead = null;
    private TextView mTextViewDiscuss = null;
    private LinearLayout mLinearLayoutImages = null;

    private Fragment mFragment = null;

    private OnChildClickListener mListener = null;

    public interface OnChildClickListener {
        void OnChildListener(View view, int id);
    }

    public RandomHeadView(Fragment fragment, int layoutId) {
        this.mFragment = fragment;

        mView = LayoutInflater.from(mFragment.getActivity()).inflate(layoutId, null);
        mImageViewHead = mView.findViewById(R.id.item_recyclerView_note_imageView_head);
        mTextViewName = mView.findViewById(R.id.item_recyclerView_note_textView_name);
        mTextViewTime = mView.findViewById(R.id.item_recyclerView_note_textView_time);
        mTextViewAreaName = mView.findViewById(R.id.item_recyclerView_note_textView_areaName);
        mRichTextViewContent = mView.findViewById(R.id.item_recyclerView_note_textView_content);
        mTextViewRead = mView.findViewById(R.id.item_recyclerView_note_textView_read);
        mTextViewDiscuss = mView.findViewById(R.id.item_recyclerView_note_textView_discuss);
        mLinearLayoutImages = mView.findViewById(R.id.item_recyclerView_note_linearLayout_images);

        mRichTextViewContent.setOnClickListener(this);
        mLinearLayoutImages.setOnClickListener(this);
    }

    public RandomHeadView setHeadIcon(String path) {
        if (path == null || path.equals("")) {
            path = ConstVariable.DEFAULT_HEAD_URL;
        }
        Glide.with(mFragment).load(path).apply(RequestOptions.circleCropTransform()).into(mImageViewHead);
        return this;
    }

    public RandomHeadView setName(String name) {
        mTextViewName.setText(name);
        return this;
    }

    public RandomHeadView setTime(String time) {
        mTextViewTime.setText(time);
        return this;
    }

    public RandomHeadView setAreaName(String areaName) {
        SpannableString spannableString = new SpannableString("来自" + areaName + "专区");
        ForegroundColorSpan colorSpan = new ForegroundColorSpan(Color.parseColor("#FF69B4"));
        spannableString.setSpan(colorSpan, 2, 4, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        mTextViewAreaName.setText(spannableString);
        return this;
    }

    public RandomHeadView setContent(String content) {
        mRichTextViewContent.setContent(content);
        return this;
    }


    public RandomHeadView setReadNumber(int number) {
        mTextViewRead.setText(number + "");
        return this;
    }

    public RandomHeadView setDiscussNumber(int number) {
        mTextViewDiscuss.setText(number + "");
        return this;
    }

    public RandomHeadView setImages(List<String> images) {
        mLinearLayoutImages.removeAllViews();
        mLinearLayoutImages.setVisibility(View.GONE);
        if (images == null || images.size() == 0) {
            return this;
        }
        mLinearLayoutImages.setVisibility(View.VISIBLE);
        ViewUtil.addImageViewToLinearLayout(mFragment.getActivity(), images, mLinearLayoutImages);
        return this;
    }

    public View build() {
        return mView;
    }

    @Override
    public void onClick(View v) {
        mListener.OnChildListener(v, v.getId());
    }

    public void setOnChildClickListener(OnChildClickListener listener) {
        mListener = listener;
    }
}
