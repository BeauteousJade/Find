package com.example.pby.find.activity;


import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.net.net.bean.User;
import com.example.pby.find.R;
import com.example.pby.find.activity.base.BaseActivity;
import com.example.pby.find.mvp.presenter.PromptDialogPresenter;
import com.example.pby.find.mvp.view.IPromptDialogView;

import me.leefeng.promptlibrary.PromptDialog;

/**
 * Created by pby on 2018/2/1.
 */

public class SetPasswordActivity extends BaseActivity implements View.OnClickListener, IPromptDialogView {

    private ImageView mImageViewBack = null;
    private TextView mTextViewTitle = null;
    private TextView mTextViewTip = null;


    private EditText mEditTextCode = null;
    private EditText mEditTextPwd = null;

    private Button mButtonOk = null;

    private PromptDialog mPromptDialog = null;
    private PromptDialogPresenter mPresenter = null;

    private String mEmail = null;
    private String mOperationMessage = null;

    public static final String KEY_EMAIL = "email";
    public static final String KEY_OPERATION = "operation";


    public interface Operation {
        String Register = "新用户注册";
        String modify = "密码修改";
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_password);
        setImmerseMode();
    }

    @Override
    public void init() {
        mImageViewBack = findView(R.id.toolbar_back_ok_imageView_back);
        mTextViewTitle = findView(R.id.toolbar_back_ok_textView_title);

        mEditTextCode = findView(R.id.activity_setPassword_editText_code);
        mEditTextPwd = findView(R.id.activity_setPassword_editText_password);

        mTextViewTip = findView(R.id.activity_set_password_textView_tip);

        mButtonOk = findView(R.id.activity_setPassword_button_ok);

        mPromptDialog = new PromptDialog(this);
        mPresenter = new PromptDialogPresenter();

        mButtonOk.setOnClickListener(this);
        mImageViewBack.setOnClickListener(this);

        mTextViewTitle.setText("设置密码");
        mEmail = getIntent().getStringExtra(KEY_EMAIL);
        mOperationMessage = getIntent().getStringExtra(KEY_OPERATION);


        setEmail(mEmail);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.activity_setPassword_button_ok: {
                register();
                break;
            }
            case R.id.toolbar_back_ok_imageView_back: {
                onBackPressed();
                break;
            }
        }
    }

    private void register() {
        if (mEmail != null && !mEmail.equals("")) {
            User user = new User();
            user.setEmail(mEmail);
            user.setPassword(mEditTextPwd.getText().toString());
            if (Operation.Register.equals(mOperationMessage)) {
                mPresenter.register(user, mEditTextCode.getText().toString(), this, this);
            } else if (Operation.modify.equals(mOperationMessage)) {
                mPresenter.modify(user, mEditTextCode.getText().toString(), this, this);
            }
        }
    }

    @Override
    public void result(Throwable e, Object data, int requestCode) {
        if (e != null) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        } else {
            onBackPressed();
            Toast.makeText(this, mOperationMessage + "成功!", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void changeStatus(boolean flag) {
        if (flag) {
            mPromptDialog.showLoading(mOperationMessage + "中...");
        } else {
            mPromptDialog.dismiss();
        }
    }

    private void setEmail(String email) {
        if (email != null && !email.equals("")) {
            Editable editable = new Editable.Factory().newEditable("");
            editable.append("   成功发送验证码到");
            SpannableString spannableString = new SpannableString(email);
            spannableString.setSpan(new ForegroundColorSpan(Color.RED), 0, spannableString.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            editable.append(spannableString);
            editable.append("，请输入你的验证码!");
            mTextViewTip.setText(editable);

        } else {
            String string = "未知错误!";
            mTextViewTip.setText(string);
        }
    }
}
