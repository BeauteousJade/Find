package com.example.pby.find.decoration;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by dishfo on 17-12-8.
 */

public class LinearRecyclerViewDecoration extends RecyclerView.ItemDecoration {


    public static final int VERTIACL= LinearLayoutManager.VERTICAL;
    public static final int HORIZONTAL= LinearLayoutManager.HORIZONTAL;

    private Drawable mDivider;
    private int orientation;
    private Context context;

    public LinearRecyclerViewDecoration(Context context,int resid,int orientation){
        this.context=context;
        this.orientation=orientation;
        this.mDivider=context.getDrawable(resid);
    }

    private void drawVertical(Canvas c, RecyclerView parent, RecyclerView.State state){
        int top=0;
        int left=0;
        int right=0;
        int bottom=0;
        RecyclerView.LayoutParams params=null;

        int count=parent.getChildCount();
        View view=null;

        for(int i=0;i<count;i++){
            view=parent.getChildAt(i);
            params= (RecyclerView.LayoutParams) view.getLayoutParams();
            left=view.getLeft()+params.leftMargin;
            right=view.getRight()+params.leftMargin;
            top=view.getBottom()+params.bottomMargin;
            bottom=top+mDivider.getIntrinsicHeight();
            if(count-1==i)
                break;
            mDivider.setBounds(left,top,right,bottom);
            mDivider.draw(c);
        }

    }

    private void drawHorizontal(Canvas c, RecyclerView parent, RecyclerView.State state){

    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        if(orientation==VERTIACL){
            drawVertical(c, parent, state);
        }else if(orientation==HORIZONTAL){
            drawHorizontal(c, parent, state);
        }
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        int top=0;
        int left=0;
        int right=0;
        int bottom=mDivider.getIntrinsicHeight();
        outRect.set(left,top,right,bottom);

    }
}
