package com.ui.liaoshengrui.rprocess;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ScrollView;

import com.bumptech.glide.Glide;

/**
 * Created by liaoshengrui on 2018/1/28.
 */

public class RProcessView extends View {

    private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private volatile String text;
    private float textSize;
    private float radius;
    private volatile int process = 0;
    private int mainColor;
    private int textColor;

    public RProcessView(Context context) {
        this(context, null);
    }

    public RProcessView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RProcessView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttrs(attrs);
    }

    private void initAttrs(AttributeSet attrs) {
        TypedArray ta = getContext().obtainStyledAttributes(attrs, R.styleable.RProcessView);
        text = ta.getString(R.styleable.RProcessView_text);
        if (TextUtils.isEmpty(text)) {
            text = "暂停";
        }
        textSize = ta.getDimension(R.styleable.RProcessView_textSize, 30);
        radius = ta.getFloat(R.styleable.RProcessView_radius, 15);
        mainColor = ta.getColor(R.styleable.RProcessView_mainColor, Color.BLUE);
        textColor = ta.getColor(R.styleable.RProcessView_textColor, Color.WHITE);
        ta.recycle();
        mPaint.setTextSize(textSize);
//        ScrollView

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int width = getLayoutParams().width;
        int height = getLayoutParams().height;

        float textLenght = mPaint.measureText(text);
        float startX = (width - textLenght) / 2;
        float startY = getTextHight(mPaint);

        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(mainColor);
        RectF rectF = new RectF(0, 0, width, height);
        canvas.drawRoundRect(rectF, radius, radius, mPaint);
        canvas.drawText(text, startX, startY, mPaint);

        canvas.save();
        mPaint.setStyle(Paint.Style.FILL);
        float clipW = (float) (process * 0.01 * width);
        RectF clipRectF = new RectF(0, 0, clipW, height);
        canvas.clipRect(clipRectF);
        mPaint.setColor(mainColor);
        canvas.drawRoundRect(rectF, radius, radius, mPaint);
        mPaint.setColor(textColor);
        canvas.drawText(text, startX, startY, mPaint);
        canvas.restore();
    }


    public void setProcess(int process) {
        if (process < 0 || process > 100) {
            return;
        }
        this.process = process;
        postInvalidate();

    }

    public int getProcess() {
        return process;
    }

    public void setText(String text) {
        this.text = text;
        postInvalidate();
    }

    public float getTextHight(Paint paint) {
        Paint.FontMetrics fm = paint.getFontMetrics();
        return getHeight() / 2 - fm.descent + (fm.descent - fm.ascent) / 2;
    }


}
