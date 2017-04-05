package com.example.kalin.graduationwork.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.example.kalin.graduationwork.R;

public class ColorView extends View {

//    public ColorView(Context context, AttributeSet attrs, int defStyleAttr) {
//        super(context, attrs, defStyleAttr);
//    }
//
//    public void setColor(int color) {
//        this.color = color;
//        invalidate();
//    }

//    private static final int DEFAULT_CIRCLE_COLOR = Color.BLUE;

//    private int circleColor = DEFAULT_CIRCLE_COLOR;

//    private final int DEFAULT_CIRCLE_COLOR = R.string.red;
//    private int circleColor = DEFAULT_CIRCLE_COLOR;

    //private static final int color = R.string.pink;
    //private int circleColor = color;

    private static final int DEFAULT_CIRCLE_COLOR = R.color.colorGreenForEvents;
    private int circleColor = DEFAULT_CIRCLE_COLOR;

    private Paint paint;

    public ColorView(Context context) {
        super(context);
        init(context, null);
    }

    public ColorView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    protected void init(Context context, AttributeSet attrs) {
        paint = new Paint();
        paint.setAntiAlias(true);
    }

    public void setCircleColor(int circleColor) {
        this.circleColor = circleColor;
        invalidate();
    }

    public int getCircleColor() {
        return circleColor;
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int w = getWidth();
        int h = getHeight();

        int pl = getPaddingLeft();
        int pr = getPaddingRight();
        int pt = getPaddingTop();
        int pb = getPaddingBottom();

        int usableWidth = w - (pl + pr);
        int usableHeight = h - (pt + pb);

        int radius = Math.min(usableWidth, usableHeight) / 2;
        int cx = pl + (usableWidth / 2);
        int cy = pt + (usableHeight / 2);

        paint.setColor(circleColor);
        canvas.drawCircle(cx, cy, radius, paint);
    }
}

//    private int color = 0xffffff;
//    Paint paint;
//
//    public ColorView(Context context) {
//        super(context);
//        init(context, null);
//    }
//
//    public ColorView(Context context, AttributeSet attrs) {
//        super(context, attrs);
//        init(context, null);
//    }
//
//    protected void init(Context context, AttributeSet attrs) {
//        paint = new Paint();
//        paint.setAntiAlias(true);
//    }
//
//    public void setColor(int color) {
//        this.color = color;
//        invalidate();
//    }
//
//    @Override
//    protected void onDraw(Canvas canvas) {
//        super.onDraw(canvas);
//
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
//        paint.setColor(color);
//        canvas.drawCircle(cx, cy, radius, paint);
//    }
//}

