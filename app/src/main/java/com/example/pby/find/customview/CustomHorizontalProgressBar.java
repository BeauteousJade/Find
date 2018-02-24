package com.example.pby.find.customview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.ProgressBar;

import com.example.pby.find.util.ScreenUtil;
import com.example.pby.find.R;

/**
 * Created by apple on 2017/12/9.
 */

public class CustomHorizontalProgressBar extends ProgressBar {
    private int mUnreachedColor = 0xFFF0FFFF;
    private int mReachedColor = 0xFF2fc0ed;

    private float mLineHeight = 0;
    private float mLineWidth = 0;

    private Paint mPaint = null;

    public CustomHorizontalProgressBar(Context context) {
        super(context);
        init(context, null);
    }

    public CustomHorizontalProgressBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public CustomHorizontalProgressBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.CustomHorizontalProgressBar);
        mUnreachedColor = array.getColor(R.styleable.CustomHorizontalProgressBar_unreachColor, mUnreachedColor);
        mReachedColor = array.getColor(R.styleable.CustomHorizontalProgressBar_reachColor, mReachedColor);
        mLineHeight = array.getDimension(R.styleable.CustomHorizontalProgressBar_lineHeight, (int) ScreenUtil.dpToPx(context, 4));
        array.recycle();

        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
        mPaint.setStrokeWidth(mLineHeight);
    }

    @Override
    protected synchronized void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        if (heightMode != MeasureSpec.EXACTLY) {
            int execHeight = (int) (getPaddingBottom() + getPaddingTop() + mLineHeight);
            heightMeasureSpec = MeasureSpec.makeMeasureSpec(execHeight, MeasureSpec.EXACTLY);
        }
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        mLineWidth = widthSize - getPaddingLeft() + getPaddingRight();
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected synchronized void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.save();
        canvas.translate(getPaddingLeft(), getHeight() / 2);
        float radio = getProgress() * 1.0f / getMax();
        float progressX = mLineWidth * radio;
        mPaint.setColor(mUnreachedColor);
        canvas.drawLine(0, 0, mLineWidth, 0, mPaint);
        mPaint.setColor(mReachedColor);
        canvas.drawLine(0, 0, progressX, 0, mPaint);
        canvas.restore();
    }
}
