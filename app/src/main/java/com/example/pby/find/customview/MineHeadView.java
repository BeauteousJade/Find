package com.example.pby.find.customview;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.pby.find.R;

/**
 * Created by pby on 2018/2/20.
 */

public class MineHeadView implements View.OnClickListener {
    private View mView = null;
    private Activity mActivity = null;

    private ImageView mImageViewHead = null;
    private TextView mTextViewName = null;
    private TextView mTextViewAutograph = null;
    private TextView mTextViewNoteNumber = null;
    private TextView mTextViewFollowNumber = null;
    private TextView mTextViewFansNumber = null;

    private LinearLayout mLinearLayoutNote = null;
    private LinearLayout mLinearLayoutFollow = null;
    private LinearLayout mLinearLayoutFans = null;

    private OnMineHeadViewClickListener mListener = null;

    @Override
    public void onClick(View v) {
        mListener.onMineHeadViewClick(v);
    }

    public interface OnMineHeadViewClickListener {
        void onMineHeadViewClick(View view);
    }

    public MineHeadView(int layoutId, Activity activity) {
        mActivity = activity;
        mView = LayoutInflater.from(activity).inflate(layoutId, null);

        mImageViewHead = mView.findViewById(R.id.head_recyclerView_mine_imageView_head);
        mTextViewName = mView.findViewById(R.id.head_recyclerView_mine_textView_name);
        mTextViewAutograph = mView.findViewById(R.id.head_recyclerView_mine_textView_autograph);
        mTextViewNoteNumber = mView.findViewById(R.id.head_recyclerView_mine_textView_note_number);
        mTextViewFollowNumber = mView.findViewById(R.id.head_recyclerView_mine_textView_follow_number);
        mTextViewFansNumber = mView.findViewById(R.id.head_recyclerView_mine_textView_fans_number);

        mLinearLayoutNote = mView.findViewById(R.id.head_recyclerView_mine_linearLayout_note);
        mLinearLayoutFollow = mView.findViewById(R.id.head_recyclerView_mine_linearLayout_follow);
        mLinearLayoutFans = mView.findViewById(R.id.head_recyclerView_mine_linearLayout_fans);

        mLinearLayoutNote.setOnClickListener(this);
        mLinearLayoutFollow.setOnClickListener(this);
        mLinearLayoutFans.setOnClickListener(this);

    }

    public MineHeadView setHead(String head) {
        Log.i("pby123", "head = " + head);
        Glide.with(mActivity).load(head).apply(RequestOptions.circleCropTransform()).into(mImageViewHead);
        return this;
    }

    public MineHeadView setName(String name) {
        mTextViewName.setText(name);
        return this;
    }

    public MineHeadView setAutograph(String autograph) {
        mTextViewAutograph.setText(autograph);
        return this;
    }

    public MineHeadView setNoteNumber(int number) {
        mTextViewNoteNumber.setText(number + "");
        return this;
    }

    public MineHeadView setFollowNumber(int number) {
        mTextViewFollowNumber.setText(number + "");
        return this;
    }

    public MineHeadView setFansNumber(int number) {
        mTextViewFansNumber.setText(number + "");
        return this;
    }

    public MineHeadView setOnMineHeadViewClickListener(OnMineHeadViewClickListener listener) {
        this.mListener = listener;
        return this;
    }

    public View build() {
        return mView;
    }
}
