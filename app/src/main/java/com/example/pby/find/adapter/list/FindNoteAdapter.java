package com.example.pby.find.adapter.list;

import android.graphics.Color;
import android.support.annotation.Nullable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.pby.find.R;
import com.example.pby.find.bean.recyclerView.NoteBean;
import com.example.pby.find.constant.ConstVariable;
import com.example.pby.find.customview.RichTextView;
import com.example.pby.find.util.ViewUtil;

import java.util.List;

/**
 * Created by pby on 2018/2/11.
 */

public class FindNoteAdapter extends BaseQuickAdapter<NoteBean, BaseViewHolder> {


    public FindNoteAdapter(int layoutResId, @Nullable List<NoteBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, NoteBean item) {
        helper.setText(R.id.item_recyclerView_note_textView_name, item.getName());
        helper.setText(R.id.item_recyclerView_note_textView_time, item.getTime());
        helper.setText(R.id.item_recyclerView_note_textView_discuss, item.getDiscussNumber() + "");
        helper.setText(R.id.item_recyclerView_note_textView_read, item.getReadNumber() + "");

        String head = item.getHead();
        if (head == null || head.equals("")) {
            head = ConstVariable.DEFAULT_HEAD_URL;
        }
        Glide.with(mContext).load(head).apply(RequestOptions.circleCropTransform()).into((ImageView) helper.getView(R.id.item_recyclerView_note_imageView_head));

        RichTextView textViewContent = helper.getView(R.id.item_recyclerView_note_textView_content);
        textViewContent.setContent(item.getContent());

        LinearLayout linearLayout = helper.getView(R.id.item_recyclerView_note_linearLayout_images);
        linearLayout.setVisibility(View.GONE);
        linearLayout.removeAllViews();
        if (item.getImages() != null && item.getImages().size() != 0) {
            linearLayout.setVisibility(View.VISIBLE);
            ViewUtil.addImageViewToLinearLayout(mContext, item.getImages(), linearLayout);
        }

        SpannableString spannableString = new SpannableString("来自" + item.getAreaName() + "专区");
        ForegroundColorSpan colorSpan = new ForegroundColorSpan(Color.parseColor("#FF69B4"));
        spannableString.setSpan(colorSpan, 2, 4, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        helper.setText(R.id.item_recyclerView_note_textView_areaName, spannableString);


        helper.addOnClickListener(R.id.item_recyclerView_note_textView_content)
                .addOnClickListener(R.id.item_recyclerView_note_linearLayout_images);
    }
}
