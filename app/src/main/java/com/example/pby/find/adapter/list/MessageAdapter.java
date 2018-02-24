package com.example.pby.find.adapter.list;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.pby.find.R;
import com.example.pby.find.activity.MessageActivity;
import com.example.pby.find.bean.recyclerView.MessageDiscussBean;
import com.example.pby.find.constant.ConstVariable;
import com.example.pby.find.customview.RichTextView;

import java.util.List;

/**
 * Created by pby on 2018/2/13.
 */

public class MessageAdapter extends BaseQuickAdapter<MessageDiscussBean, BaseViewHolder> {
    private int key = 0;

    public MessageAdapter(int layoutResId, @Nullable List<MessageDiscussBean> data, int key) {
        super(layoutResId, data);
        this.key = key;
    }

    @Override
    protected void convert(BaseViewHolder helper, MessageDiscussBean item) {
        helper.setText(R.id.item_recyclerView_message_discuss_textView_name, item.getName());
        helper.setText(R.id.item_recyclerView_message_discuss_textView_time, item.getTime());
        String noteContent = item.getNoteContent();
        String head = item.getHead();
        if (head == null || head.equals("")) {
            head = ConstVariable.DEFAULT_HEAD_URL;
        }
        Glide.with(mContext).load(head).apply(RequestOptions.circleCropTransform()).into((ImageView) helper.getView(R.id.item_recyclerView_message_discuss_imageView_head));

        if (item.getNoteImages() != null) {
            for (int i = 0; i < item.getNoteImages().size(); i++) {
                noteContent += "[图片]";
            }
        }
        ((RichTextView) (helper.getView(R.id.item_recyclerView_message_discuss_textView_content))).setContent(noteContent);
        RichTextView discussTextView = helper.getView(R.id.item_recyclerView_message_discuss_textView_discuss);
        discussTextView.setVisibility(View.GONE);
        TextView textViewReply = helper.getView(R.id.item_recyclerView_message_discuss_textView_reply);
        textViewReply.setVisibility(View.VISIBLE);
        switch (key) {
            case MessageActivity.KEY_APPRECIATE: {
                textViewReply.setVisibility(View.GONE);
                break;
            }
            case MessageActivity.KEY_DISCUSS: {
                String discussContent = item.getDiscussContent() == null ? "" : item.getDiscussContent();

                if (item.getDiscussImages() != null) {
                    for (int i = 0; i < item.getDiscussImages().size(); i++) {
                        noteContent += "[图片]";
                    }
                }
                discussTextView.setVisibility(View.VISIBLE);
                discussTextView.setContent(discussContent);
                break;
            }
            case MessageActivity.KEY_REPLY: {
                String discussContent = item.getDiscussContent() == null ? "" : item.getDiscussContent();
                if (item.getDiscussImages() != null) {
                    for (int i = 0; i < item.getDiscussImages().size(); i++) {
                        noteContent += "[图片]";
                    }
                }
                discussTextView.setContent(discussContent);
                discussTextView.setVisibility(View.VISIBLE);
                break;
            }
        }


        helper.addOnClickListener(R.id.item_recyclerView_message_discuss_textView_reply);
        helper.addOnClickListener(R.id.item_recyclerView_message_discuss_textView_content);

    }
}
