package com.example.pby.find.customview;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by pby on 2018/2/10.
 */

public class ScrollConstraintLayout extends ConstraintLayout {
    private int headViewHeight = 0;
    private int preY = 0;

    public ScrollConstraintLayout(Context context) {
        super(context);
    }

    public ScrollConstraintLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ScrollConstraintLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        measureChildren(widthMeasureSpec, heightMeasureSpec);
        View fistView = getChildAt(0);
        headViewHeight = fistView.getMeasuredHeight();
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:{
                preY = (int) ev.getY();
                break;
            }
            case MotionEvent.ACTION_MOVE:{
                int moveY = (int) (preY - ev.getY());
                Log.i("pby123", "moveY = " + moveY);
                if(getScrollY() <= 0 && moveY < 0 || getScrollY() >= headViewHeight && moveY > 0){
                    return super.onInterceptTouchEvent(ev);
                }else{
                    return true;
                }
            }
        }
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN: {
                preY = (int) event.getY();
                Log.i("pby123", "scrollY = " + getScrollY());
                break;
            }
            case MotionEvent.ACTION_MOVE: {
                int moveY = (int) (preY - event.getY());


                scrollBy(0, moveY);
                preY = (int) event.getY();
                break;
            }
            case MotionEvent.ACTION_UP: {
                Log.i("pby123", "scrollY = " + getScrollY());

                return true;
            }
        }
        return true;
    }
}
