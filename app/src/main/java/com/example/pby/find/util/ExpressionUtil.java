package com.example.pby.find.util;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

import com.example.pby.find.bean.recyclerView.ImageBean;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by pby on 2018/2/7.
 */

public class ExpressionUtil {
    public static List<ImageBean> getAllExpression(Context context) {
        List<ImageBean> list = new ArrayList<>();
        AssetManager manager = context.getAssets();
        try {
            String[] strings = manager.list("expression");
            for(String string:strings){
                ImageBean bean = new ImageBean();
                bean.setImagePath("file:///android_asset/expression/" + string);
                bean.setImageType(ImageBean.Type.expression);
                list.add(bean);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.i("pby123", list.size()+"");
        return list;
    }
}
