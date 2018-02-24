package com.example.pby.find.adapter.list;

import android.net.Uri;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.pby.find.R;
import com.example.pby.find.bean.recyclerView.DiscussBean;
import com.example.pby.find.constant.ConstVariable;
import com.example.pby.find.customview.RichTextView;
import com.example.pby.find.util.IntUtil;
import com.example.pby.find.util.ViewUtil;

import java.util.List;

/**
 * Created by pby on 2018/2/9.
 */

public class DiscussAdapter extends BaseQuickAdapter<DiscussBean, BaseViewHolder> {
    private String hostEmail = null;

    public DiscussAdapter(int layoutResId, @Nullable List<DiscussBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, DiscussBean item) {
        Log.i("pby123", item.getName() + " " + (helper.getView(R.id.item_recyclerView_discuss_textView_name) == null));

        helper.setText(R.id.item_recyclerView_discuss_textView_name, item.getName());
        helper.setText(R.id.item_recyclerView_discuss_textView_time, item.getTime());
        helper.setText(R.id.item_recyclerView_discuss_textView_floor, "第" + helper.getLayoutPosition() + "楼");

        RichTextView richTextView = helper.getView(R.id.item_recyclerView_discuss_RichTextView_content);
        richTextView.setContent(item.getContent());

        LinearLayout linearLayout = helper.getView(R.id.item_recyclerView_discuss_linearLayout_images);
        linearLayout.removeAllViews();
        linearLayout.setVisibility(View.GONE);
        if (item.getImages() != null && item.getImages().size() != 0) {
            linearLayout.setVisibility(View.VISIBLE);
            ViewUtil.addImageViewToLinearLayout(mContext, item.getImages(), linearLayout);
        }

        RichTextView parentRichTextView = helper.getView(R.id.item_recyclerView_discuss_RichTextView_parent);
        parentRichTextView.setVisibility(View.GONE);
        if (item.getIsChild() == 1) {
            parentRichTextView.setVisibility(View.VISIBLE);
            parentRichTextView.setContent(item.getParentName()+ ": " + item.getParentContent());
        }

        int level = IntUtil.buildLevel(item.getExperience());
        Uri uri = Uri.parse("file:///android_asset/level/level_" + (level < 10 ? "0" + level: level) + ".png");
        Glide.with(mContext).load(uri).apply(RequestOptions.centerInsideTransform()).into((ImageView) helper.getView(R.id.item_recyclerView_discuss_imageView_level));
        String head = item.getHead();
        if (head == null || head.equals("")) {
            head = ConstVariable.DEFAULT_HEAD_URL;
        }
        Glide.with(mContext).load(head).apply(RequestOptions.circleCropTransform()).into((ImageView) helper.getView(R.id.item_recyclerView_discuss_imageView_head));

        ImageView imageViewHost = helper.getView(R.id.item_recyclerView_discuss_imageView_host);
        imageViewHost.setVisibility(View.GONE);
        if (item.getEmail().equals(hostEmail)) {
            imageViewHost.setVisibility(View.VISIBLE);
        }
        helper.addOnClickListener(R.id.item_recyclerView_discuss_textView_reply);
    }

    public void setHostEmail(String email) {
        this.hostEmail = email;
    }

}
