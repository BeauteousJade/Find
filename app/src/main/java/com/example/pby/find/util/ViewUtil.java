package com.example.pby.find.util;

import android.content.Context;
import android.graphics.Color;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

public class ViewUtil {

    public static void addImageViewToLinearLayout(Context context, List<String> imgUrlList, LinearLayout linearLayout) {
        final List<String> imgUrls = imgUrlList;
        final LinearLayout l = linearLayout;
        for (int i = 0; i < imgUrls.size(); i++) {
            ImageView imageView = new ImageView(context);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams((int) ScreenUtil.dpToPx(context, 100), (int) ScreenUtil.dpToPx(context, 150));
            if (i != 0) {
                lp.leftMargin = (int) ScreenUtil.dpToPx(context, 10);
            }
            imageView.setLayoutParams(lp);
            Glide.with(context).load(imgUrls.get(i)).into(imageView);
            l.addView(imageView);
        }
        l.requestLayout();
    }

    public static void addImageViewToLinearLayout(Context context, List<String> imgUrlList, LinearLayout linearLayout, int width, int height) {
        final List<String> imgUrls = imgUrlList;
        final LinearLayout l = linearLayout;
        for (int i = 0; i < imgUrls.size(); i++) {
            ImageView imageView = new ImageView(context);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(width, height);
            lp.topMargin = (int) ScreenUtil.dpToPx(context, 10);
            imageView.setLayoutParams(lp);
            Glide.with(context).load(imgUrls.get(i)).apply(RequestOptions.centerInsideTransform()).into(imageView);
            l.addView(imageView);
        }
        l.requestLayout();
    }
}