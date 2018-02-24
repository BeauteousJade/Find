package com.example.pby.find.util;

import android.content.Context;

import com.example.pby.find.R;
import com.example.pby.find.customview.PbyPopup;

/**
 * Created by pby on 2018/2/23.
 */

public class PopupUtil {

    public static PbyPopup buildMenu(int layoutId, Context context) {
        return new PbyPopup(context)
                .setContentView(layoutId)
                .setFocusAndOutsideEnable(true)
                //允许背景变暗
                .setBackgroundDimEnable(true)
                .setDimValue(0.1F)
                .setAnimationStyle(R.style.popupAnim);
    }

    public static final PbyPopup buildDialog(int layoutId, Context context) {
        return new PbyPopup(context)
                .setContentView(layoutId)
                //是否允许点击PopupWindow之外的地方消失
                .setFocusAndOutsideEnable(true)
                //允许背景变暗
                .setBackgroundDimEnable(true)
                .setDimValue(0.4F);
    }
    public static PbyPopup buildMore(int layoutId, Context context){
        return new PbyPopup(context)
                .setContentView(layoutId)
                .setFocusAndOutsideEnable(true)
                //允许背景变暗
                .setBackgroundDimEnable(true)
                .setDimValue(0.1F);
    }
}
