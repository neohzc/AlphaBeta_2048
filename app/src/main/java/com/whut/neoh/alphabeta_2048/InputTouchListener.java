package com.whut.neoh.alphabeta_2048;

import android.view.MotionEvent;
import android.view.View;

public class InputTouchListener implements View.OnTouchListener {
    // private static final int


    private float y, x;                 //当前触碰输位置
    private float originX, originY;     //触碰开始坐标


    @Override
    public boolean onTouch(View v, MotionEvent event) {
        //x = event.
        return false;
    }




    //此次触摸事件是点击
    private boolean isTap(){
        return false;// pathMoved()<=;
    }


    //当前坐标与初始坐标的距离
    private float pathMoved() {
        return (float) Math.sqrt(Math.pow(y-originY,2)+Math.pow(x-originX,2));
        //(y - originY) * (y - originY) + (x - originX) * (x - originX);
    }
}
