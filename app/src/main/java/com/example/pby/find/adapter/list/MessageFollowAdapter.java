package com.example.pby.find.adapter.list;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.pby.find.R;
import com.example.pby.find.bean.recyclerView.MessageFollowBean;
import com.example.pby.find.constant.ConstVariable;

import java.util.List;

import static com.example.pby.find.R.id.item_recyclerView_message_follow_imageView_head;

/**
 * Created by pby on 2018/2/18.
 */

public class MessageFollowAdapter extends BaseQuickAdapter<MessageFollowBean, BaseViewHolder> {



    public MessageFollowAdapter(int layoutResId, @Nullable List<MessageFollowBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, MessageFollowBean item) {
        helper.setText(R.id.item_recyclerView_message_follow_textView_name, item.getName());
        helper.setText(R.id.item_recyclerView_message_follow_textView_time, item.getTime());
        String head = item.getHead();
        if (head == null || head.equals("")) {
            head = ConstVariable.DEFAULT_HEAD_URL;
        }
        if (item.getIsFollow() == 0) {
            helper.setText(R.id.item_recyclerView_message_follow_textView_follow, ConstVariable.STRING_FOLLOW);
        } else {
            helper.setText(R.id.item_recyclerView_message_follow_textView_follow, ConstVariable.STRING_NO_CANCEL);

        }
        Glide.with(mContext).load(head).apply(RequestOptions.circleCropTransform()).into((ImageView) helper.getView(item_recyclerView_message_follow_imageView_head));

        helper.addOnClickListener(R.id.item_recyclerView_message_follow_textView_follow);
    }
}
