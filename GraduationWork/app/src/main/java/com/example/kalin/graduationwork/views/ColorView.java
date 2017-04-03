package com.example.kalin.graduationwork.views;

//public class ColorView extends View {
//
//
////    private static final int DEFAULT_CIRCLE_COLOR = Color.BLUE;
//
////    private int circleColor = DEFAULT_CIRCLE_COLOR;
//
//    private final int DEFAULT_CIRCLE_COLOR = R.string.dark_pink;
//    private int circleColor = DEFAULT_CIRCLE_COLOR;
//
//    //private static final int color = R.string.pink;
//    //private int circleColor = color;
//
//    private Paint paint;
//
//    public ColorView(Context context) {
//        super(context);
//        init(context, null);
//    }
//
//    public ColorView(Context context, AttributeSet attrs) {
//        super(context, attrs);
//        init(context, attrs);
//    }
//
//    protected void init(Context context, AttributeSet attrs) {
//        paint = new Paint();
//        paint.setAntiAlias(true);
//    }
//
//    public void setCircleColor(int circleColor) {
//        this.circleColor = circleColor;
//        invalidate();
//    }
//
//    public int getCircleColor() {
//        return circleColor;
//    }
//
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
//        paint.setColor(circleColor);
//        canvas.drawCircle(cx, cy, radius, paint);
//    }
//}


    //    private int color = 0x000000;
//
//    public ColorView(Context context) {
//        super(context);
//    }
//
//    public ColorView(Context context, AttributeSet attrs) {
//        super(context, attrs);
//    }
//
//    public ColorView(Context context, AttributeSet attrs, int defStyleAttr) {
//        super(context, attrs, defStyleAttr);
//    }
//
//    @Override
//    protected void onDraw(Canvas canvas) {
//        super.onDraw(canvas);
//
//        Paint paint = new Paint();
//        paint.setColor(color);
//        canvas.drawCircle(getWidth() / 2, getHeight() / 2, getWidth(), paint);
//    }
//
//    public void setColor(int color) {
//        this.color = color;
//        invalidate();
//    }
//}
