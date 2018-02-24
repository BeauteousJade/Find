package com.example.pby.find.adapter.list;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.pby.find.R;
import com.example.pby.find.bean.recyclerView.AreaBean;

import java.util.List;

/**
 * Created by pby on 2018/2/1.
 */

public class AreaAdapter extends BaseQuickAdapter<AreaBean, BaseViewHolder> {
    public AreaAdapter(int layoutResId, @Nullable List<AreaBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, AreaBean item) {
        Glide.with(mContext).load(item.getIconId()).into((ImageView) helper.getView(R.id.item_recyclerView_area_imageView_icon));
        helper.setText(R.id.item_recyclerView_area_textView_name, item.getName());
        helper.addOnClickListener(R.id.item_recyclerView_area_imageView_icon);
    }
}
