package com.example.pby.find.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.net.inter.impl.UserOperation;
import com.example.pby.find.R;
import com.example.pby.find.activity.base.BaseActivity;

import es.dmoral.toasty.Toasty;

/**
 * Created by pby on 2018/2/23.
 */

public class SetAutographActivity extends BaseActivity implements View.OnClickListener {

    private TextView mTextViewTitle = null;
    private TextView mTextViewOk = null;
    private ImageView mImageViewBack = null;
    private EditText mEditText = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_autograph);
    }

    @Override
    public void init() {
        mTextViewOk = findView(R.id.toolbar_back_ok_textView_ok);
        mTextViewTitle = findView(R.id.toolbar_back_ok_textView_title);
        mImageViewBack = findView(R.id.toolbar_back_ok_imageView_back);
        mEditText = findView(R.id.activity_set_autograph_editText);

        mTextViewTitle.setText("个性签名");

        mImageViewBack.setOnClickListener(this);
        mTextViewOk.setOnClickListener(this);
    }

    @Override
    public void result(Throwable e, Object data, int requestCode) {
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.toolbar_back_ok_textView_ok: {
                setAutograph(mEditText.getText().toString());
                break;
            }
            case R.id.toolbar_back_ok_imageView_back: {
                onBackPressed();
                break;
            }
        }
    }

    public void setAutograph(String autograph) {
        if (!autograph.equals("")) {
            final UserOperation operation = new UserOperation();
            operation.setAutograph(UserOperation.getCurrentUser().getEmail(), autograph);
        } else {
            Toasty.normal(this, "内容为空").show();
        }
        onBackPressed();
    }
}
