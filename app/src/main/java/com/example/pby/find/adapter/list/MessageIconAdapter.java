package com.example.pby.find.adapter.list;

import android.graphics.Color;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;


import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.pby.find.R;
import com.example.pby.find.bean.recyclerView.MessageBean;
import com.javonlee.dragpointview.view.DragPointView;

import java.util.List;


/**
 * Created by pby on 2018/2/12.
 */

public class MessageIconAdapter extends BaseQuickAdapter<MessageBean, BaseViewHolder> {
    public MessageIconAdapter(int layoutResId, @Nullable List<MessageBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, MessageBean item) {
        Glide.with(mContext).load(item.getIconId()).into((ImageView) helper.getView(R.id.item_recyclerView_message_imageView_icon));
        helper.setText(R.id.item_recyclerView_message_textView_text, item.getText());
        DragPointView dragPointView = helper.getView(R.id.item_recyclerView_message_DragPointView);
        dragPointView.setVisibility(View.GONE);
        if (item.getCount() != 0) {
            dragPointView.setVisibility(View.VISIBLE);
            dragPointView.setText(item.getCount() + "");
        }
        helper.addOnClickListener(R.id.item_recyclerView_message_imageView_icon);
    }

}
