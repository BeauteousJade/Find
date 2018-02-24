package com.example.pby.find.adapter.list;

import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.TransitionOptions;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.BaseTarget;
import com.bumptech.glide.request.target.ImageViewTarget;
import com.bumptech.glide.request.target.SizeReadyCallback;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.transition.Transition;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.net.inter.impl.UserOperation;
import com.example.pby.find.R;
import com.example.pby.find.bean.recyclerView.NoteBean;
import com.example.pby.find.constant.ConstVariable;
import com.example.pby.find.customview.RichEditText;
import com.example.pby.find.customview.RichTextView;
import com.example.pby.find.util.ViewUtil;

import java.util.List;

/**
 * Created by pby on 2018/2/2.
 */

public class NoteAdapter extends BaseQuickAdapter<NoteBean, BaseViewHolder> {
    private boolean isAnimation = false;
    private int animationPosition = -1;

    public NoteAdapter(int layoutResId, @Nullable List<NoteBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, NoteBean item) {
        Object head = item.getHead();
        if (head == null || head.toString().equals("")) {
            head = ConstVariable.DEFAULT_HEAD_URL;
        }
        Glide.with(mContext).load(head).apply(RequestOptions.circleCropTransform()).into((ImageView) helper.getView(R.id.item_recyclerView_note_imageView_head));
        String name = item.getName();
        if (name == null || name.equals("")) {
            name = item.getEmail();
        }
        helper.setText(R.id.item_recyclerView_note_textView_name, name);
        helper.setText(R.id.item_recyclerView_note_textView_time, item.getTime());
        ImageView imageViewAppreciate = helper.getView(R.id.item_recyclerView_note_imageView_appreciate);
        Glide.with(mContext).load(R.mipmap.icon_appreciate_00).into(imageViewAppreciate);
        if (item.getIsAppreciate() == 1) {
            Glide.with(mContext).load(R.mipmap.icon_appreciate_01).into(new ImageViewTarget<Drawable>(imageViewAppreciate) {
                @Override
                protected void setResource(@Nullable Drawable resource) {
                    imageViewAppreciate.setImageDrawable(resource);
                    if (isAnimation && helper.getLayoutPosition() == animationPosition) {
                        Animation animation = AnimationUtils.loadAnimation(mContext, R.anim.anim_scale);
                        imageViewAppreciate.startAnimation(animation);
                        animationPosition = -1;
                    }
                }
            });

        }
        ((RichTextView) (helper.getView(R.id.item_recyclerView_note_textView_content))).setContent(item.getContent());
        helper.setText(R.id.item_recyclerView_note_textView_appreciate, item.getAppreciateNumber() +"");
        helper.setText(R.id.item_recyclerView_note_textView_read, item.getReadNumber() + "");
        helper.setText(R.id.item_recyclerView_note_textView_discuss, item.getDiscussNumber() + "");
        LinearLayout linearLayout = helper.getView(R.id.item_recyclerView_note_linearLayout_images);
        linearLayout.setVisibility(View.GONE);
        linearLayout.removeAllViews();
        if (item.getImages() != null && item.getImages().size() != 0) {
            linearLayout.setVisibility(View.VISIBLE);
            ViewUtil.addImageViewToLinearLayout(mContext, item.getImages(), linearLayout);
        }

        helper.addOnClickListener(R.id.item_recyclerView_note_imageView_appreciate);
        helper.addOnClickListener(R.id.item_recyclerView_note_textView_content);
        helper.addOnClickListener(R.id.item_recyclerView_note_linearLayout_images);
    }

    public void setAnimation(boolean animation) {
        isAnimation = animation;
    }

    public void setAnimationPosition(int animationPosition) {
        this.animationPosition = animationPosition;
    }
}
