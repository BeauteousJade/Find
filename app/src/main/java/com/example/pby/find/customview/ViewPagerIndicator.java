package com.example.pby.find.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.CornerPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.LinearLayout;

/**
 * Created by bg_introduce_activity_welcome_3 on 2017/7/31.
 */

public class ViewPagerIndicator extends LinearLayout {
    private float mTextViewWidth = 0;
    private Paint mPaintTriangle = null;
    private Path mPathTriangle = null;
    private float mInitTranslationX = 0;
    private float mMoveTranslationX = 0;
    private float mTriangleWidth = 0;
    private float mTriangleHeight = 0;

    private static final float RADIO_TRIANGLE = 1.0f / 3f;
    private static final int TAB_COUNT = 3;
    private static final float TAN_30 = 0.58f;

    public ViewPagerIndicator(Context context) {
        this(context, null);
    }

    public ViewPagerIndicator(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ViewPagerIndicator(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaintTriangle = new Paint();
        mPaintTriangle.setAntiAlias(true);
        mPaintTriangle.setDither(true);
        mPaintTriangle.setStyle(Paint.Style.FILL);
        mPaintTriangle.setColor(Color.WHITE);
        mPaintTriangle.setPathEffect(new CornerPathEffect(5));
    }


    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        if (changed) {

            mTextViewWidth = getChildAt(0).getWidth();
            initPath();
        }
    }

    private void initPath() {
        mTriangleWidth = mTextViewWidth * RADIO_TRIANGLE;
        mTriangleHeight = mTriangleWidth *TAN_30;
        mPathTriangle = new Path();
        mPathTriangle.moveTo(0, 0);
        mPathTriangle.lineTo(mTriangleWidth, 0);
        mPathTriangle.lineTo(mTriangleWidth / 2.0f, -mTriangleHeight);
        mPathTriangle.close();
        mInitTranslationX = getChildAt(0).getLeft() + mTextViewWidth/ 2.0f - mTriangleWidth / 2.0f;
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        canvas.save();
        // 画笔平移到正确的位置
        canvas.translate(mInitTranslationX + mMoveTranslationX, getHeight());
        canvas.drawPath(mPathTriangle, mPaintTriangle);
        canvas.restore();
        super.dispatchDraw(canvas);
    }

    public void scroll(int position, float offset) {
        mMoveTranslationX = mTextViewWidth * (position + offset);
        invalidate();
    }
}
