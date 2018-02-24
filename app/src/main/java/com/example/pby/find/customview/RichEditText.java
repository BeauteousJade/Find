package com.example.pby.find.customview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.text.Editable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.EditText;

import com.example.pby.find.constant.ConstVariable;
import com.example.pby.find.util.BitmapUtil;

import skin.support.widget.SkinCompatSupportable;
import skin.support.widget.SkinCompatTextHelper;

/**
 * Created by pby on 2018/2/7.
 */

@SuppressLint("AppCompatCustomView")
public class RichEditText extends EditText implements SkinCompatSupportable {
    private static final int EXPRESSION_LENGTH = 17;
    private SkinCompatTextHelper mTextHelper;

    public RichEditText(Context context) {
        super(context);
    }

    public RichEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public RichEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mTextHelper = SkinCompatTextHelper.create(this);
        mTextHelper.loadFromAttributes(attrs, defStyleAttr);
    }
    public void setContent(String content){
            setText("");

            Editable editable = new Editable.Factory().newEditable("");
            char cs[] = content.toCharArray();
            for(int i = 0; i < cs.length; i++){
                if(cs[i] == '[' && i + EXPRESSION_LENGTH < cs.length && cs[i + EXPRESSION_LENGTH] ==']'){
                    String string = new String(cs, i + 1, EXPRESSION_LENGTH);
                    if(string.matches(ConstVariable.EXPRESSION_REDULAR)){
                        Bitmap bitmap = BitmapUtil.getBitmap(getContext(), string);
                        if(bitmap != null){
                            ImageSpan imageSpan = new ImageSpan(getContext(), bitmap);
                            SpannableString spannableString = new SpannableString("[" +string+ "]");
                            spannableString.setSpan(imageSpan, 0, string.length() + 2, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                            editable.append(spannableString);
                        }else{
                            editable.append("[" + string + "]");
                        }
                    }else{
                        editable.append("[" + string + "]");
                    }
                    i += EXPRESSION_LENGTH;
                }else{
                    editable.append(cs[i]);
                }
            }

            setText(editable);
    }
    public void appendExpression(String path){
        String fileName = path.substring(path.lastIndexOf("/") + 1);
        Editable editable = getEditableText();
        if(editable == null){
            editable = new Editable.Factory().newEditable("");
        }
        if(fileName.matches(ConstVariable.EXPRESSION_REDULAR)){
            Bitmap bitmap = BitmapUtil.getBitmap(getContext(), fileName);
            if(bitmap != null){
                ImageSpan imageSpan = new ImageSpan(getContext(), bitmap);
                SpannableString spannableString = new SpannableString("[" +fileName+ "]");
                spannableString.setSpan(imageSpan, 0, fileName.length() + 2, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                editable.append(spannableString);
            }
        }
    }

    @Override
    public void setTextAppearance(int resId) {
        setTextAppearance(getContext(), resId);
    }

    @Override
    public void setTextAppearance(Context context, int resId) {
        super.setTextAppearance(context, resId);
        if (mTextHelper != null) {
            mTextHelper.onSetTextAppearance(context, resId);
        }
    }

    @Override
    public void applySkin() {
        if (mTextHelper != null) {
            mTextHelper.applySkin();
        }
    }
}
