package com.example.pby.find.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pby.find.R;
import com.example.pby.find.activity.base.BaseActivity;
import com.example.pby.find.constant.ConstVariable;
import com.example.pby.find.mvp.presenter.PromptDialogPresenter;
import com.example.pby.find.mvp.view.IPromptDialogView;
import com.example.pby.find.mvp.view.IResultView;
import com.example.pby.find.util.ApplicationUtil;

import java.io.IOException;
import java.util.Arrays;

import es.dmoral.toasty.Toasty;
import me.leefeng.promptlibrary.PromptDialog;

/**
 * Created by pby on 2018/2/1.
 */

public class LoginActivity extends BaseActivity implements View.OnClickListener, IPromptDialogView {
    private TextView mTextViewRegister = null;
    private TextView mTextViewForgetPassword = null;

    private EditText mEditTextEmail = null;
    private EditText mEditTextPwd = null;

    private Button mButtonOk = null;

    private PromptDialogPresenter mPromptDialogPresenter = null;
    private PromptDialog mPromptDialog = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setIsFullScreen(true);



    }

    @Override
    public void init() {
        mTextViewForgetPassword = findView(R.id.activity_login_textView_forgetPwd);
        mTextViewRegister = findView(R.id.activity_login_textView_register);

        mEditTextEmail = findView(R.id.activity_login_editText_email);
        mEditTextPwd = findView(R.id.activity_login_editText_password);

        mButtonOk = findView(R.id.activity_login_button_ok);

        mPromptDialog = new PromptDialog(this);
        mPromptDialogPresenter = new PromptDialogPresenter();

        mButtonOk.setOnClickListener(this);
        mTextViewForgetPassword.setOnClickListener(this);
        mTextViewRegister.setOnClickListener(this);

        String email = ApplicationUtil.getTextFromSharedPreferences(this, ConstVariable.KEY_EMAIL);
        String pwd = ApplicationUtil.getTextFromSharedPreferences(this, ConstVariable.KEY_PWD);
        boolean isAuto = ApplicationUtil.getBooleanFromSharedPreferences(this, ConstVariable.KEY_AUTO_LOGIN);
        if (email != null && pwd != null) {
            mEditTextEmail.setText(email);
            mEditTextPwd.setText(pwd);
            if (isAuto) {
                login(email, pwd);
            }
        }


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.activity_login_button_ok: {
                final String email = mEditTextEmail.getText().toString();
                final String pwd = mEditTextPwd.getText().toString();
                login(email, pwd);
                ApplicationUtil.addStringToSharedPreferences(this, ConstVariable.KEY_EMAIL, email);
                ApplicationUtil.addStringToSharedPreferences(this, ConstVariable.KEY_PWD, pwd);
                ApplicationUtil.addBooleanToSharedPreferences(this, ConstVariable.KEY_AUTO_LOGIN, true);

                break;
            }
            case R.id.activity_login_textView_forgetPwd: {
                Intent intent = new Intent(this, SendEmailActivity.class);
                intent.putExtra(SendEmailActivity.KEY_TITLE, SendEmailActivity.ITitle.modify);
                startActivity(intent);
                break;
            }
            case R.id.activity_login_textView_register: {
                Intent intent = new Intent(this, SendEmailActivity.class);
                intent.putExtra(SendEmailActivity.KEY_TITLE, SendEmailActivity.ITitle.register);
                startActivity(intent);
                break;
            }
        }
    }

    @Override
    public void changeStatus(boolean flag) {
        if (flag) {
            mPromptDialog.showLoading("登录中...");
        } else {
            mPromptDialog.dismiss();
        }
    }

    @Override
    public void result(Throwable e, Object data, int requestCode) {
        if (e == null) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            this.finish();
        } else {
            Toasty.normal(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    private void login(String email, String pwd) {
        mPromptDialogPresenter.login(email, pwd, this, this);
    }
}
