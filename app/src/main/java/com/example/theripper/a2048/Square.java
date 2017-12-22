package com.example.theripper.a2048;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by The Ripper on 11/16/2017.
 */

@SuppressLint("AppCompatCustomView")
public class Square extends TextView{


    public Square(Context context) {
        super(context);
    }

    public Square(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Square(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int height=getMeasuredWidth();
        setMeasuredDimension(height,height);
    }


    public void settext(int number) {
        if (number < 100)
            setTextSize(40);
        else if (number < 1000)
            setTextSize(35);
        else setTextSize(30);


        if (number < 8)
            setTextColor(Color.BLACK);
        else setTextColor(Color.WHITE);

        GradientDrawable drawablee = (GradientDrawable) this.getBackground();
        drawablee.setColor(Datagame.colorr(number));
        setBackground(drawablee);

        if(number==0)
            setText("");
        else setText(String.valueOf(number));

    }

}
