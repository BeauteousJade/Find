package com.example.pby.find.activity.base;

import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;

import com.example.pby.find.R;
import com.example.pby.find.mvp.view.IResultView;
import com.example.pby.find.util.StatusBarUtils;


/**
 * Created by apple on 2017/12/7.
 */

public abstract class BaseActivity extends AppCompatActivity implements IResultView {


    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        init();
    }

    public abstract void init();

    public void setImmerseMode() {
//        StatusBarUtils.setWindowStatusBarColor(this, R.color.bg_toolbar);
    }


    public void setIsFullScreen(boolean isFullScreen) {
        if (isFullScreen) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
    }


    public <T extends View> T findView(int id) {
        return (T) findViewById(id);
    }
}
