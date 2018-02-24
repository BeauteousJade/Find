package com.example.pby.find.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pby.find.R;
import com.example.pby.find.activity.base.BaseActivity;
import com.example.pby.find.customview.CodeView;
import com.example.pby.find.mvp.presenter.PromptDialogPresenter;
import com.example.pby.find.mvp.view.IPromptDialogView;

import me.leefeng.promptlibrary.PromptDialog;

/**
 * Created by pby on 2018/2/1.
 */

public class SendEmailActivity extends BaseActivity implements View.OnClickListener, IPromptDialogView {
    private ImageView mImageViewBack = null;

    private TextView mTextViewTitle = null;

    private EditText mEditTextEmail = null;
    private EditText mEditTextCode = null;

    private CodeView mCodeView = null;

    private Button mButtonNext = null;

    private String title = null;



    private PromptDialogPresenter mPromptDialogPresenter = null;
    private PromptDialog mPromptDialog = null;

    public static final String KEY_TITLE = "title";

    public interface ITitle{
        String register = "新用户注册";
        String modify = "忘记密码";
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        setImmerseMode();
    }

    @Override
    public void init() {
        mImageViewBack = findView(R.id.toolbar_back_ok_imageView_back);
        mTextViewTitle = findView(R.id.toolbar_back_ok_textView_title);

        mEditTextCode = findView(R.id.activity_register_editText_code);
        mEditTextEmail = findView(R.id.activity_register_editText_email);

        mCodeView = findView(R.id.activity_register_codeView_code);

        mButtonNext = findView(R.id.activity_register_button_next);



        mPromptDialog = new PromptDialog(this);
        mPromptDialogPresenter = new PromptDialogPresenter();

        mImageViewBack.setOnClickListener(this);
        mCodeView.setOnClickListener(this);
        mButtonNext.setOnClickListener(this);

        title = getIntent().getStringExtra(KEY_TITLE);
        mTextViewTitle.setText(title);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.toolbar_back_ok_imageView_back: {
                onBackPressed();
                break;
            }
            case R.id.activity_register_codeView_code: {
                mCodeView.reDraw();
                break;
            }
            case R.id.activity_register_button_next: {
                next();
                break;
            }
        }
    }

    private void next(){
        if(mCodeView.checkContent(mEditTextCode.getText().toString())){
            mPromptDialogPresenter.sendEmail(mEditTextEmail.getText().toString(), this, this);
        }else{
            mCodeView.reDraw();
            Toast.makeText(this, "验证码错误!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void changeStatus(boolean flag) {
        if(flag){
            mPromptDialog.showLoading("发送邮件中...");
        }else{
            mPromptDialog.dismiss();
        }
    }

    @Override
    public void result(Throwable e, Object data, int requestCode) {
        if(e == null){
            Intent intent = new Intent(this, SetPasswordActivity.class);
            addDataToIntent(intent);
            startActivity(intent);
            finish();
        }else{
            mCodeView.reDraw();
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    private void addDataToIntent(Intent intent) {
        intent.putExtra(SetPasswordActivity.KEY_EMAIL, mEditTextEmail.getText().toString());
        if(ITitle.register.equals(title)){
            intent.putExtra(SetPasswordActivity.KEY_OPERATION, SetPasswordActivity.Operation.Register);
        }else if(ITitle.modify.equals(title)){
            intent.putExtra(SetPasswordActivity.KEY_OPERATION, SetPasswordActivity.Operation.modify);
        }
    }
}
