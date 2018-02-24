package com.example.pby.find.customview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.TextView;

import com.example.pby.find.constant.ConstVariable;
import com.example.pby.find.util.BitmapUtil;

import skin.support.widget.SkinCompatBackgroundHelper;
import skin.support.widget.SkinCompatSupportable;
import skin.support.widget.SkinCompatTextHelper;

/**
 * Created by pby on 2018/2/8.
 */

@SuppressLint("AppCompatCustomView")
public class RichTextView extends TextView implements SkinCompatSupportable {
    private static final int EXPRESSION_LENGTH = 18;
    private SkinCompatBackgroundHelper mBackgroundTintHelper;
    private SkinCompatTextHelper mTextHelper;

    public RichTextView(Context context) {
        this(context, null);
    }

    public RichTextView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RichTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mBackgroundTintHelper = new SkinCompatBackgroundHelper(this);
        mBackgroundTintHelper.loadFromAttributes(attrs, defStyleAttr);
        mTextHelper = SkinCompatTextHelper.create(this);
        mTextHelper.loadFromAttributes(attrs, defStyleAttr);
    }

    public void setContent(String content) {
        setText("");

        Editable editable = new Editable.Factory().newEditable("");
        char cs[] = content.toCharArray();
        for (int i = 0; i < cs.length; i++) {
            if (cs[i] == '[' && i + EXPRESSION_LENGTH < cs.length && cs[i + EXPRESSION_LENGTH] == ']') {
                String string = new String(cs, i + 1, EXPRESSION_LENGTH - 1);
                Log.i("pby123", string);
                if (string.matches(ConstVariable.EXPRESSION_REDULAR)) {
                    Bitmap bitmap = BitmapUtil.getBitmap(getContext(), string);
                    Log.i("pby123", "bitmap = " + (bitmap == null));
                    if (bitmap != null) {
                        ImageSpan imageSpan = new ImageSpan(getContext(), bitmap);
                        SpannableString spannableString = new SpannableString("[" + string + "]");
                        spannableString.setSpan(imageSpan, 0, string.length() + 2, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        editable.append(spannableString);
                    } else {
                        editable.append("[" + string + "]");
                    }
                } else {
                    editable.append("[" + string + "]");
                }
                i += EXPRESSION_LENGTH;
            } else {
                editable.append(cs[i]);
            }
        }

        setText(editable);
    }

    @Override
    public void setBackgroundResource(@DrawableRes int resId) {
        super.setBackgroundResource(resId);
        if (mBackgroundTintHelper != null) {
            mBackgroundTintHelper.onSetBackgroundResource(resId);
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
        if (mBackgroundTintHelper != null) {
            mBackgroundTintHelper.applySkin();
        }
        if (mTextHelper != null) {
            mTextHelper.applySkin();
        }
    }
}
