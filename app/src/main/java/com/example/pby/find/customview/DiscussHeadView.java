package com.example.pby.find.customview;

import android.app.Activity;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
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
 * Created by pby on 2018/2/9.
 */

public class DiscussHeadView {
    private Activity mActivity = null;
    private View mView = null;
    private ImageView mImageViewHead = null;
    private ImageView mImageViewLevel = null;
    private TextView mTextViewName = null;
    private TextView mTextViewTime = null;
    private RichTextView mRichTextViewContent = null;
    private LinearLayout mLinearLayoutImages = null;

    public DiscussHeadView(Activity activity, int layoutId) {
        this.mActivity = activity;

        mView = LayoutInflater.from(activity).inflate(layoutId, null);
        mImageViewHead = mView.findViewById(R.id.head_recyclerView_discuss_imageView_head);
        mImageViewLevel = mView.findViewById(R.id.head_recyclerView_discuss_imageView_level);
        mTextViewName = mView.findViewById(R.id.head_recyclerView_discuss_textView_name);
        mTextViewTime = mView.findViewById(R.id.head_recyclerView_discuss_textView_time);
        mRichTextViewContent = mView.findViewById(R.id.head_recyclerView_discuss_richTextView_content);
        mLinearLayoutImages = mView.findViewById(R.id.head_recyclerView_discuss_linearLayout_images);
    }

    public DiscussHeadView setHead(String headUrl) {
        if (headUrl == null || headUrl.equals("")) {
            headUrl = ConstVariable.DEFAULT_HEAD_URL;
        }
        Glide.with(mActivity).load(headUrl).apply(RequestOptions.circleCropTransform()).into(mImageViewHead);
        return this;
    }

    public DiscussHeadView setName(String name) {
        mTextViewName.setText(name);
        return this;
    }

    public DiscussHeadView setLevel(int level) {
        String uriString = "file:///android_asset/level/level_" + (level < 10 ? "0" + level : level) + ".png";
        Glide.with(mActivity).load(Uri.parse(uriString)).into(mImageViewLevel);
        return this;
    }

    public DiscussHeadView setTime(String time) {
        mTextViewTime.setText(time);
        return this;
    }

    public DiscussHeadView setContent(String content) {
        mRichTextViewContent.setContent(content);
        return this;
    }

    public DiscussHeadView setImages(List<String> imageList) {
        if (imageList != null && imageList.size() != 0) {
            mLinearLayoutImages.setVisibility(View.VISIBLE);
            mLinearLayoutImages.removeAllViews();
            ViewUtil.addImageViewToLinearLayout(mActivity, imageList, mLinearLayoutImages, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        }
        return this;
    }

    public View build() {
        return mView;
    }
}
