package com.hjg.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * @author hjg
 * @title 魅力值进度（最大为1000）
 */

public class CharmView extends View {
    private Paint bgPaint, forgroundPaint, circlePaint, whitePaint;//背景笔
    private int height, width;
    private int centY;
    Context context;
    private float targetValue;
    private float radius, inRadius;//圆直径,内圆直径
    private float stokeWidth;//线的宽度
    private int maxTargetValue = 1000;

    public CharmView(Context context) {
        super(context);
        initPaint(context);
    }

    public CharmView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initPaint(context);
    }

    public CharmView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint(context);
    }

    private void initPaint(Context context) {
        this.context = context;
        radius = dip2px(context, 4);
        inRadius = dip2px(context, 2.5f);
        stokeWidth = dip2px(context, 2);

        bgPaint = new Paint();
        bgPaint.setColor(Color.parseColor("#E9EBED"));
        bgPaint.setAntiAlias(true);
        bgPaint.setStyle(Paint.Style.FILL);

        forgroundPaint = new Paint();
        forgroundPaint.setColor(Color.parseColor("#E21483"));
        forgroundPaint.setAntiAlias(true);
        forgroundPaint.setStyle(Paint.Style.FILL);

        circlePaint = new Paint();
        circlePaint.setColor(Color.parseColor("#E21483"));
        circlePaint.setAntiAlias(true);
        circlePaint.setStyle(Paint.Style.FILL);

        whitePaint = new Paint();
        whitePaint.setColor(Color.parseColor("#ffffff"));
        whitePaint.setAntiAlias(true);
        whitePaint.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        width = getWidth();
        height = getHeight();
        targetValue = targetValue / maxTargetValue * width;
        centY = height / 2;
        bgPaint.setStrokeWidth(stokeWidth);
        canvas.drawLine(0, centY, width, centY, bgPaint);//画背景线

        forgroundPaint.setStrokeWidth(stokeWidth);
        canvas.drawLine(0, centY, targetValue, centY, forgroundPaint);//画前景线,到达圆的中心

        canvas.drawCircle(targetValue + radius, centY, radius, circlePaint);//画空心圆
        canvas.drawCircle(targetValue + radius, centY, inRadius, whitePaint);//画白心
    }


    /**
     * 設置相应的值(最大值是1000)
     *
     * @param target
     */

    public void setTargetValue(int target) {
        targetValue = target;
        Log.d("CharmView", "target:" + target);
        invalidate();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        Log.d("CharmView", "w:" + w);
        Log.d("CharmView", "h:" + h);
    }

    // 根据手机的分辨率从 dp 的单位 转成为 px(像素)
    public static float dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (dpValue * scale + 0.5f);
    }
}
