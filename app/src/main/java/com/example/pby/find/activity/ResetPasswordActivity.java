package com.example.pby.find.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.net.inter.impl.UserOperation;
import com.example.pby.find.R;
import com.example.pby.find.activity.base.BaseActivity;
import com.example.pby.find.mvp.presenter.PromptDialogPresenter;
import com.example.pby.find.mvp.view.IPromptDialogView;

import es.dmoral.toasty.Toasty;
import me.leefeng.promptlibrary.PromptDialog;

/**
 * Created by pby on 2018/2/23.
 */

public class ResetPasswordActivity extends BaseActivity implements View.OnClickListener, IPromptDialogView {
    private ImageView mImageViewBack = null;
    private TextView mTextViewTitle = null;

    private EditText mEditTextOld = null;
    private EditText mEditTextNew = null;
    private Button mButtonOk = null;

    private PromptDialogPresenter mPresenter = null;
    private PromptDialog mDialog = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);
    }

    @Override
    public void init() {
        mPresenter = new PromptDialogPresenter();

        mImageViewBack = findView(R.id.toolbar_back_ok_imageView_back);
        mTextViewTitle = findView(R.id.toolbar_back_ok_textView_title);

        mEditTextOld = findView(R.id.activity_reset_editText_old);
        mEditTextNew = findView(R.id.activity_reset_editText_new);
        mButtonOk = findView(R.id.activity_reset_button_ok);

        mDialog = new PromptDialog(this);

        mTextViewTitle.setText("重置密码");

        mImageViewBack.setOnClickListener(this);
        mButtonOk.setOnClickListener(this);
    }

    @Override
    public void result(Throwable e, Object data, int requestCode) {
        if (e != null) {
            Toasty.normal(this, "重置密码失败，请检查原密码是否输入正确").show();
        }
        onBackPressed();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.activity_reset_button_ok: {
                resetPwd();
                break;
            }
            case R.id.toolbar_back_ok_imageView_back: {
                onBackPressed();
                break;
            }
        }
    }

    @Override
    public void changeStatus(boolean flag) {
        if (flag) {
            mDialog.showLoading("");
        } else {
            mDialog.dismiss();
        }
    }

    @Override
    protected void onDestroy() {
        mDialog.dismiss();
        super.onDestroy();
    }

    private void resetPwd() {
        final String email = UserOperation.getCurrentUser().getEmail();
        final String oldPwd = mEditTextOld.getText().toString();
        final String newPwd = mEditTextNew.getText().toString();
        if (oldPwd.equals("")) {
            Toasty.normal(this, "未输入原密码!").show();
            return;
        }
        if (newPwd.equals("")) {
            Toasty.normal(this, "未输入新密码!").show();
            return;
        }
        mPresenter.resetPassword(email, oldPwd, newPwd, this, this);

    }
}
