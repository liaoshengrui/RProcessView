package com.ui.liaoshengrui.rprocess;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by liaoshengrui on 2018/1/28.
 */

public class RProcessView extends View {

    private Paint backPint;
    private Paint proPaint;
    private volatile String text;
    private float textSize = 40;
    private volatile int process = 0;
    private int mainColor = Color.BLUE;
    private int textColor = Color.WHITE;

    public RProcessView(Context context) {
        this(context, null);
    }

    public RProcessView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RProcessView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        backPint = new Paint(Paint.ANTI_ALIAS_FLAG);
        backPint.setColor(mainColor);
        backPint.setTextSize(textSize);
        proPaint = new Paint(backPint);
        text = "暂停";
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int width = getLayoutParams().width;
        int height = getLayoutParams().height;

        float textLenght = backPint.measureText(text);
        float startX = (width - textLenght) / 2;
        float startY = getTextHight(backPint);

        backPint.setStyle(Paint.Style.STROKE);
        RectF rectF = new RectF(0, 0, width, height);
        canvas.drawRoundRect(rectF, 10, 10, backPint);
        canvas.drawText(text, startX, startY, backPint);

        canvas.save();
        float clipW = (float) (process * 0.01 * width);
        RectF clipRectF = new RectF(0, 0, clipW, height);
        canvas.clipRect(clipRectF);
        proPaint.setColor(mainColor);
        canvas.drawRoundRect(rectF, 10, 10, proPaint);
        proPaint.setColor(textColor);
        canvas.drawText(text, startX, startY, proPaint);
        canvas.restore();
    }


    public void setProcess(int process) throws Exception {
        if (process < 0 || process > 100) {
            return;
        }
        this.process = process;
        postInvalidate();

    }

    public int getProcess() {
        return process;
    }

    public void setText(String text){
        this.text=text;
        postInvalidate();
    }

    public float getTextHight(Paint paint) {
        Paint.FontMetrics fm = paint.getFontMetrics();
        return getHeight() / 2 - fm.descent + (fm.descent - fm.ascent) / 2;
    }


}
