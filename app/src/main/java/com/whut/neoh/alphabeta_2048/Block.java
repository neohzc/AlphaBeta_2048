package com.whut.neoh.alphabeta_2048;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

public class Block extends FrameLayout {

    //colors
    int[] colorSet = {
            0xFFFFFF,                                    //0
            0xBBFFFF, 0xAEEEEE, 0x96CDCD, 0x668B8B,     //2,4,8,16
            0x98F5FF, 0x8EE5EE, 0x7AC5CD, 0x53868B,     //32,64,128,256
            0x00F5FF, 0x00E5EE, 0x00C5CD, 0x00868B,     //512,1024,2048,4096
            0x00FFFF, 0x00EEEE, 0x00CDCD, 0x008B8B      //8192,16384,32768,65536
    };                                                  //0-16

    private TextView tv_showNum;
    private View v_background;


    private int number = 0;


    public Block(Context context, int num) {
        super(context);
        number = num;

        LayoutParams lp_background = null;
        LayoutParams lp_Textview = null;

        //background
        v_background = new View(getContext());
        v_background.setBackgroundColor(0x006064);
        lp_background = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        lp_background.setMargins(5, 5, 5, 5);
        //v_background.setLayoutParams(lp);
        // TODO: 2019/1/15 修改layout参数加载方式
        addView(v_background, lp_background);

        //textview
        tv_showNum = new TextView(getContext());
        tv_showNum.setTextSize(25);
        tv_showNum.setGravity(Gravity.CENTER);
        lp_Textview = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        lp_Textview.setMargins(5, 5, 5, 5);
        addView(tv_showNum, lp_Textview);

    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
        if (number <= 0)
            tv_showNum.setText("");
        else
            tv_showNum.setText(String.valueOf(number));
        int i;              //num是2的i次方   0-0  2-1 4-2 8-3
        for (i = 0; number > 1; number >>= 1, i++) ;
        if (i >= 0 && i < colorSet.length)
            tv_showNum.setBackgroundColor(colorSet[i]);//colorSet[i % (colorSet.length - 1)]
        else
            tv_showNum.setBackgroundColor(0xCC0000);
    }

    public boolean isEqualTo(Block block) {
        return getNumber() == block.getNumber();
    }

    public TextView getTv_showNum() {
        return tv_showNum;
    }
}
