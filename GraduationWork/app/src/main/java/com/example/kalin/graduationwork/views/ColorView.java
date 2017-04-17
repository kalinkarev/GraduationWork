package com.example.kalin.graduationwork.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class ColorView extends View {

//    private static final int DEFAULT_CIRCLE_COLOR = R.color.colorOrangeForEvents;
//    private int circleColor = DEFAULT_CIRCLE_COLOR;

//    private Paint paint;

    public ColorView(Context context) {
        super(context);
//        init(context, null);
    }
//
    public ColorView(Context context, AttributeSet attrs) {
        super(context, attrs);
//        init(context, attrs);
    }
//
//    protected void init(Context context, AttributeSet attrs) {
//        paint = new Paint();
//        paint.setAntiAlias(true);
//    }
//
//    public void setCircleColor(int circleColor) {
////        this.circleColor = circleColor;
//        invalidate();
//    }

//    public int getCircleColor() {
//        return circleColor;
//    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int x = getWidth();
        int y = getHeight();
        int radius;
        radius = 10;
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.BLACK);
        canvas.drawPaint(paint);
//        paint.setColor(Color.parseColor();
        canvas.drawCircle(x/2, y/2, radius, paint);

//        int w = getWidth();
//        int h = getHeight();
//
//        int pl = getPaddingLeft();
//        int pr = getPaddingRight();
//        int pt = getPaddingTop();
//        int pb = getPaddingBottom();
//
//        int usableWidth = w - (pl + pr);
//        int usableHeight = h - (pt + pb);
//
//        int radius = Math.min(usableWidth, usableHeight) / 2;
//        int cx = pl + (usableWidth / 2);
//        int cy = pt + (usableHeight / 2);
//
//        paint.setColor(circleColor);
//        paint.setStyle(Paint.Style.FILL);
//        canvas.drawCircle(cx, cy, radius, paint);
    }
}