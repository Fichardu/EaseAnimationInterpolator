package com.fichardu.ease;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;

/**
 * Created by xf on 15/1/21.
 */
public class PanelView extends View {

    private Paint mLinePaint = new Paint();
    private Paint mBallPaint = new Paint();
    private Path mLinePath = new Path();
    private Rect mFrame = new Rect();
    private float mAnimY;
    private boolean mStartAnim;
    private int CONTENT_HEIHGT;

    public PanelView(Context context) {
        this(context, null);
    }

    public PanelView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PanelView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mLinePaint.setColor(Color.BLACK);
        mLinePaint.setAntiAlias(true);
        mLinePaint.setStyle(Paint.Style.STROKE);
        mLinePaint.setStrokeWidth(3.0f);

        mBallPaint.setColor(Color.RED);
        mBallPaint.setAntiAlias(true);
        mBallPaint.setStyle(Paint.Style.FILL);

        DisplayMetrics metrics = getContext().getResources().getDisplayMetrics();
        CONTENT_HEIHGT = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 160, metrics);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = getMeasuredWidth();
        int height = getMeasuredHeight();
        int left = width / 10;
        int right = width * 9 / 10;
        int top = height / 2 - CONTENT_HEIHGT / 2;
        int bottom = height / 2 + CONTENT_HEIHGT  / 2;
        mFrame.set(left, top, right, bottom);
        mAnimY = mFrame.top;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawRect(mFrame, mLinePaint);
        canvas.drawPath(mLinePath, mLinePaint);
        canvas.drawCircle(mFrame.left, mAnimY, 20, mBallPaint);
    }

    public void onAnimate(float timeFraction, float animFration) {
        if (timeFraction > 1.0f) {
            timeFraction = 1.0f;
        }
        float x = timeFraction * mFrame.width() + mFrame.left;
        mAnimY = animFration * mFrame.height() + mFrame.top;
        if (!mStartAnim) {
            mStartAnim = true;
            mLinePath.moveTo(x, mAnimY);
        }
        mLinePath.lineTo(x, mAnimY);
        invalidate();
    }

    public void reset() {
        mLinePath.reset();
        mAnimY = mFrame.top;
        mStartAnim = false;
        invalidate();
    }


}
