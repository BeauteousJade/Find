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
import com.example.pby.find.activity.MineNoteActivity;
import com.example.pby.find.bean.recyclerView.MessageDiscussBean;
import com.example.pby.find.bean.recyclerView.MessageFollowBean;
import com.example.pby.find.bean.recyclerView.NoteBean;
import com.example.pby.find.constant.ConstVariable;
import com.example.pby.find.customview.RichTextView;
import com.example.pby.find.util.GlideUtil;
import com.example.pby.find.util.StringUtil;

import java.util.List;

import static com.example.pby.find.R.id.item_recyclerView_message_follow_imageView_head;

/**
 * Created by pby on 2018/2/21.
 */

public class MineNoteAdapter extends BaseQuickAdapter<Object, BaseViewHolder> {
    private int mType = 0;

    public MineNoteAdapter(int layoutResId, @Nullable List data, int type) {
        super(layoutResId, data);
        mType = type;
    }

    @Override
    protected void convert(BaseViewHolder helper, Object item) {
        switch (mType) {
            case MineNoteActivity.TYPE_NOTE: {
                NoteBean noteBean = (NoteBean) item;
                RichTextView richTextView = helper.getView(R.id.item_recyclerView_note_textView_content);
                richTextView.setContent(StringUtil.getTitleFromContent(noteBean.getContent(), noteBean.getImages()));
                helper.setText(R.id.item_recyclerView_note_textView_time, noteBean.getTime());
                helper.setText(R.id.item_recyclerView_note_textView_appreciate, noteBean.getAppreciateNumber() + "");
                helper.setText(R.id.item_recyclerView_note_textView_discuss, noteBean.getDiscussNumber() + "");
                helper.setText(R.id.item_recyclerView_note_textView_read, noteBean.getReadNumber() + "");

                helper.addOnClickListener(R.id.item_recyclerView_note_constraintLayout);
                break;
            }
            case MineNoteActivity.TYPE_FOLLOW: {
                MessageFollowBean bean = (MessageFollowBean) item;
                helper.setText(R.id.item_recyclerView_message_follow_textView_name, bean.getName());
                helper.setText(R.id.item_recyclerView_message_follow_textView_time, bean.getTime());
                String head = bean.getHead();
                if (head == null || head.equals("")) {
                    head = ConstVariable.DEFAULT_HEAD_URL;
                }
                helper.setText(R.id.item_recyclerView_message_follow_textView_follow, ConstVariable.STRING_NO_CANCEL);

                Glide.with(mContext).load(head).apply(RequestOptions.circleCropTransform()).into((ImageView) helper.getView(item_recyclerView_message_follow_imageView_head));

                helper.addOnClickListener(R.id.item_recyclerView_message_follow_textView_follow);
                break;
            }
            case MineNoteActivity.TYPE_FANS: {
                MessageFollowBean bean = (MessageFollowBean) item;
                helper.setText(R.id.item_recyclerView_message_follow_textView_name, bean.getName());
                helper.setText(R.id.item_recyclerView_message_follow_textView_time, bean.getTime());
                String head = bean.getHead();
                if (head == null || head.equals("")) {
                    head = ConstVariable.DEFAULT_HEAD_URL;
                }
                if (bean.getIsFollow() == 0) {
                    helper.setText(R.id.item_recyclerView_message_follow_textView_follow, ConstVariable.STRING_FOLLOW);
                } else {
                    helper.setText(R.id.item_recyclerView_message_follow_textView_follow, ConstVariable.STRING_NO_CANCEL);

                }
                Glide.with(mContext).load(head).apply(RequestOptions.circleCropTransform()).into((ImageView) helper.getView(item_recyclerView_message_follow_imageView_head));

                helper.addOnClickListener(R.id.item_recyclerView_message_follow_textView_follow);
                break;
            }
            case MineNoteActivity.TYPE_COLLECTION:
            case MineNoteActivity.TYPE_RECORD:
            case MineNoteActivity.TYPE_APPRECIATE: {
                NoteBean noteBean = (NoteBean) item;
                RichTextView richTextView = helper.getView(R.id.item_recyclerView_note_textView_content);
                richTextView.setContent(StringUtil.getTitleFromContent(noteBean.getContent(), noteBean.getImages()));
                helper.setText(R.id.item_recyclerView_note_textView_time, noteBean.getTime());
                helper.setText(R.id.item_recyclerView_note_textView_appreciate, noteBean.getAppreciateNumber() + "");
                helper.setText(R.id.item_recyclerView_note_textView_discuss, noteBean.getDiscussNumber() + "");
                helper.setText(R.id.item_recyclerView_note_textView_read, noteBean.getReadNumber() + "");

                TextView textViewName = helper.getView(R.id.item_recyclerView_note_textView_name);
                textViewName.setText(noteBean.getName());
                textViewName.setVisibility(View.VISIBLE);

                ImageView imageViewHead = helper.getView(R.id.item_recyclerView_note_imageView_head);
                imageViewHead.setVisibility(View.VISIBLE);
                Glide.with(mContext).load(noteBean.getHead()).apply(GlideUtil.buildHeadOptions()).into(imageViewHead);
                helper.addOnClickListener(R.id.item_recyclerView_note_constraintLayout);
                break;
            }
            case MineNoteActivity.TYPE_DISCUSS: {
                MessageDiscussBean bean = (MessageDiscussBean) item;
                helper.setText(R.id.item_recyclerView_message_discuss_textView_name, bean.getName());
                helper.setText(R.id.item_recyclerView_message_discuss_textView_time, bean.getTime());
                Glide.with(mContext).load(bean.getHead()).apply(GlideUtil.buildHeadOptions()).into((ImageView) helper.getView(R.id.item_recyclerView_message_discuss_imageView_head));
                ((RichTextView) (helper.getView(R.id.item_recyclerView_message_discuss_textView_content))).setContent(StringUtil.getTitleFromContent(bean.getNoteContent(), bean.getNoteImages()));
                RichTextView discussTextView = helper.getView(R.id.item_recyclerView_message_discuss_textView_discuss);
                discussTextView.setVisibility(View.VISIBLE);
                discussTextView.setContent(StringUtil.getTitleFromContent(bean.getDiscussContent(), bean.getDiscussImages()));
                TextView textViewReply = helper.getView(R.id.item_recyclerView_message_discuss_textView_reply);
                textViewReply.setVisibility(View.VISIBLE);
                helper.getView(R.id.item_recyclerView_message_discuss_textView_reply).setVisibility(View.VISIBLE);

                break;
            }
        }
    }
}
