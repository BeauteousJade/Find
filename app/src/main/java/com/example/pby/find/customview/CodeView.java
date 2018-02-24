package com.example.pby.find.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.example.pby.find.util.ScreenUtil;


/**
 * Created by pby on 2018/1/24.
 */

public class CodeView extends View {
    private int mWidth = 0;
    private int mHeight = 0;
    private int mLineWidth = 0;
    private int mLineColor = 0;
    private RectF mRectF = null;
    private Paint mPaint = null;
    private int[][] mPointCoordinates = null;
    private float[][] mTextCoordinates = null;
    private int[] mTexts = null;

    private boolean once = true;

    private String content = null;

    public CodeView(Context context) {
        super(context);
        init();
    }

    public CodeView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CodeView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mLineWidth = (int) ScreenUtil.dpToPx(getContext(), 1);
        mLineColor = Color.parseColor("#000000");
        mPaint = new Paint();
        mPaint.setTextSize(ScreenUtil.spTopx(getContext(), 18));
        mPaint.setColor(mLineColor);
        mPaint.setStrokeWidth(mLineWidth);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setStrokeJoin(Paint.Join.ROUND);
        mPaint.setTextAlign(Paint.Align.CENTER);
        mPointCoordinates = new int[100][2];
        mTexts = new int[4];
        mTextCoordinates = new float[4][2];

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mWidth = getMeasuredWidth();
        mHeight = getMeasuredHeight();
        mRectF = new RectF(mLineWidth / 2.0f, mLineWidth / 2.0f, mWidth - mLineWidth / 2.0f, mHeight - mLineWidth / 2.0f);

        float textHeight = Math.abs(mPaint.ascent() + mPaint.descent());
        float tempWidth = mWidth / 4.0f;

        mTextCoordinates[0][0] = tempWidth / 2;
        mTextCoordinates[0][1] = textHeight / 2 + mHeight / 2;
        for (int i = 1; i < mTextCoordinates.length; i++) {
            mTextCoordinates[i][0] = tempWidth + mTextCoordinates[i - 1][0];
            mTextCoordinates[i][1] = mTextCoordinates[0][1];
        }
        if (mWidth != 0 && once) {
            generateCoordinates();
            generateText();
            once = false;
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawRect(mRectF, mPaint);
        for (int i = 0; i < mPointCoordinates.length; i++) {
            canvas.drawPoint(mPointCoordinates[i][0], mPointCoordinates[i][1], mPaint);
        }
        for (int i = 0; i < mTexts.length; i++) {
            canvas.drawText(String.valueOf(mTexts[i]), mTextCoordinates[i][0], mTextCoordinates[i][1], mPaint);
        }
    }

    private void generateCoordinates() {
        for (int i = 0; i < mPointCoordinates.length; i++) {
            mPointCoordinates[i][0] = (int) (1 + Math.random() * (mWidth - 2));
            mPointCoordinates[i][1] = (int) (1 + Math.random() * (mHeight - 2));
        }
    }

    private void generateText() {
        content = "";
        for (int i = 0; i < mTexts.length; i++) {
            mTexts[i] = (int) (Math.random() * 10);
            content += mTexts[i];
        }
    }

    public void reDraw() {
        generateCoordinates();
        generateText();
        invalidate();
    }

    public boolean checkContent(String checkContent) {
        Log.i("pby123", content);
        return content.equals(checkContent);
    }

}
