package com.example.pby.find.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.example.pby.find.R;
import com.example.pby.find.activity.base.BaseActivity;


/**
 * Created by pby on 2018/2/1.
 */

public class TestActivity extends BaseActivity {
    private TextView mTextView = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        mTextView = findView(R.id.textview);
    }

    @Override
    public void init() {

    }

    @Override
    public void result(Throwable e, Object data, int requestCode) {

    }

}
