package com.example.pby.find.adapter.list;

import android.net.Uri;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.pby.find.R;
import com.example.pby.find.bean.recyclerView.ImageBean;

import java.util.List;

/**
 * Created by pby on 2018/2/7.
 */

public class ImageAdapter extends BaseQuickAdapter<ImageBean, BaseViewHolder> {
    public ImageAdapter(int layoutResId, @Nullable List<ImageBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ImageBean item) {
        if (item.getImageType().equals(ImageBean.Type.expression)) {
            Glide.with(mContext).load(Uri.parse(item.getImagePath())).into((ImageView) helper.getView(R.id.item_recyclerView_image_expression_imageView));
            helper.addOnClickListener(R.id.item_recyclerView_image_expression_imageView);
        } else if (item.getImageType().equals(ImageBean.Type.image)) {
            Glide.with(mContext).load(item.getImagePath()).into((ImageView) helper.getView(R.id.item_recyclerView_image_image_imageView_image));
            helper.addOnLongClickListener(R.id.item_recyclerView_image_image_imageView_image);
        } else if (item.getImageType().equals(ImageBean.Type.head)) {
            Glide.with(mContext).load(item.getImagePath()).apply(RequestOptions.centerCropTransform()).
                    into((ImageView) helper.getView(R.id.item_recyclerView_image_head_imageView));
            helper.addOnClickListener(R.id.item_recyclerView_image_head_imageView);
        }
    }
}
