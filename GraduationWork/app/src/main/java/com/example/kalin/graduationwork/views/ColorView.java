package com.example.kalin.graduationwork.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Kalin on 30.3.2017 г..
 */

public class ColorView extends View {
    private int color = 0xffff00;

    public ColorView(Context context) {
        super(context);
    }

    public ColorView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ColorView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Paint paint = new Paint();
        paint.setColor(color);
        canvas.drawCircle(getWidth() / 2, getHeight() / 2, getWidth(), paint);
    }

    public void setColor(int color) {
        this.color = color;
        invalidate();
    }
}
