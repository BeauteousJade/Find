package com.example.pby.find.adapter.list;

import android.widget.CompoundButton;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.pby.find.R;
import com.example.pby.find.bean.recyclerView.MineItemBean;
import com.example.pby.find.constant.ConstVariable;
import com.example.pby.find.util.ApplicationUtil;
import com.kyleduo.switchbutton.SwitchButton;

import java.util.List;

import skin.support.SkinCompatManager;

/**
 * Created by pby on 2018/2/20.
 */

public class MineItemAdapter extends BaseMultiItemQuickAdapter<MineItemBean, BaseViewHolder> {

    public MineItemAdapter(List<MineItemBean> data) {
        super(data);
        addItemType(MineItemBean.TYPE_EMPTY, R.layout.item_recyclerview_empty);
        addItemType(MineItemBean.TYPE_NORMAL, R.layout.item_recyclerview_mine);
        addItemType(MineItemBean.TYPE_MODE, R.layout.item_recyclerview_mine_mode);
    }

    @Override
    protected void convert(BaseViewHolder helper, MineItemBean item) {
        switch (item.getItemType()) {
            case MineItemBean.TYPE_NORMAL: {
                Glide.with(mContext).load(item.getIconId()).into((ImageView) helper.getView(R.id.item_recyclerView_mine_imageView_icon));
                helper.setText(R.id.item_recyclerView_mine_textView_title, item.getTitle());
                helper.setText(R.id.item_recyclerView_mine_textView_count, item.getCount() + "");
                helper.addOnClickListener(R.id.item_recyclerView_mine_constraintLayout);
                break;
            }
            case MineItemBean.TYPE_MODE: {
                SwitchButton switchButton = helper.getView(R.id.item_recyclerView_mine_switchButton);
                switchButton.setChecked(ApplicationUtil.getBooleanFromSharedPreferences(mContext, ConstVariable.KEY_NIGHT));
                switchButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if(isChecked){
                            SkinCompatManager.getInstance().loadSkin("night", SkinCompatManager.SKIN_LOADER_STRATEGY_BUILD_IN); // 后缀加载
                        }else{
                            SkinCompatManager.getInstance().restoreDefaultTheme();
                        }
                        ApplicationUtil.addBooleanToSharedPreferences(mContext,ConstVariable.KEY_NIGHT, isChecked);
                    }
                });

                Glide.with(mContext).load(item.getIconId()).into((ImageView) helper.getView(R.id.item_recyclerView_mine_imageView_icon));
                helper.setText(R.id.item_recyclerView_mine_textView_title, item.getTitle());
            }

        }
    }
}
