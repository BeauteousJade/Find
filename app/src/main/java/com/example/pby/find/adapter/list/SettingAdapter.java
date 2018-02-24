package com.example.pby.find.adapter.list;

import android.util.Log;
import android.widget.CompoundButton;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.pby.find.R;
import com.example.pby.find.bean.SettingBean;
import com.example.pby.find.constant.ConstVariable;
import com.example.pby.find.util.ApplicationUtil;
import com.kyleduo.switchbutton.SwitchButton;

import java.util.List;

/**
 * Created by pby on 2018/2/22.
 */

public class SettingAdapter extends BaseMultiItemQuickAdapter<SettingBean, BaseViewHolder> {

    public SettingAdapter(List<SettingBean> data) {
        super(data);
        addItemType(SettingBean.TYPE_TEXT, R.layout.item_recyclerview_setting_text);
        addItemType(SettingBean.TYPE_IMG, R.layout.item_recyclerview_setting_image);
        addItemType(SettingBean.TYPE_TITLE, R.layout.item_recyclerview_title);
        addItemType(SettingBean.TYPE_SWITCH, R.layout.item_recyclerview_setting_switch);
    }

    @Override
    protected void convert(BaseViewHolder helper, SettingBean item) {
        switch (item.getItemType()) {
            case SettingBean.TYPE_TEXT: {
                helper.setText(R.id.item_recyclerView_setting_textView_title, item.getTitle());
                helper.setText(R.id.item_recyclerView_setting_textView_text, item.getContent());
                helper.addOnClickListener(R.id.item_recyclerView_setting_constraintLayout);
                break;
            }
            case SettingBean.TYPE_IMG: {
                helper.setText(R.id.item_recyclerView_setting_textView_title, item.getTitle());
                Glide.with(mContext).load(item.getContent()).into((ImageView) helper.getView(R.id.item_recyclerView_setting_imageView_image));
                helper.addOnClickListener(R.id.item_recyclerView_setting_constraintLayout);
                break;
            }
            case SettingBean.TYPE_TITLE: {
                helper.setText(R.id.item_recyclerView_title_textView_title, item.getTitle());
                break;
            }
            case SettingBean.TYPE_SWITCH: {
                helper.setText(R.id.item_recyclerView_setting_textView_title, item.getTitle());
                SwitchButton switchButton = helper.getView(R.id.item_recyclerView_setting_switchButton);
                switchButton.setChecked(ApplicationUtil.getBooleanFromSharedPreferences(mContext, ConstVariable.KEY_AUTO_LOGIN));
                switchButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        ApplicationUtil.addBooleanToSharedPreferences(mContext, ConstVariable.KEY_AUTO_LOGIN, isChecked);
                    }
                });
                break;
            }
        }
    }
}
