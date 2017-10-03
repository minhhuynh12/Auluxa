package net.dirox.auluxa.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.TypedValue;

/**
 * Created by an on 6/23/2017.
 */

public class VideoRemoteCornerButton extends VideoRemoteButton {

    private final float DESIGN_VIEW_SIZE = 198;
    private final float DESIGN_TEXT_LOCATION = 32;

    private int TEXT_COLOR_NORMAL = Color.parseColor("#18bd9b");
    private int TEXT_COLOR_SELECTED = Color.WHITE;

    private Paint paintText;
    private Point locationText;

    public VideoRemoteCornerButton(Context context, Button button) {
        super(context, button);
        init(context);
    }

    private void init(Context context) {
        paintText = new Paint();
        paintText.setAntiAlias(true);
        paintText.setColor(Color.BLACK);
        paintText.setTextSize(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, TEXT_SIZE_IN_SP, context.getResources().getDisplayMetrics()));
        if (mButtonType == Button.CH_DOWN || mButtonType == Button.CH_UP) {
            paintText.setTextAlign(Paint.Align.RIGHT);
        }
        locationText = new Point((int) LINE_WIDTH, (int) LINE_WIDTH);
    }

    public void setSize(int width, int height) {
        mViewSize = width;
        setMeasuredDimension(width, height);
    }

    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //super.onMeasure(mViewSize, mViewSize);
        mBorderPath = getPath(mButtonType, widthMeasureSpec, heightMeasureSpec, (int) LINE_WIDTH);
        locationText = getLocationText(mButtonType, widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(mViewSize, mViewSize);
    }

    @Override
    protected void onSizeChanged(int width, int height, int oldw, int oldh) {
        super.onSizeChanged(width, height, oldw, oldh);
        mBorderPath = getPath(mButtonType, width, height, (int) LINE_WIDTH);
        locationText = getLocationText(mButtonType, width, height);
    }

    @Override
    public synchronized void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (mIsFocus == true) {
            mBorderLinePaint.setStyle(Paint.Style.FILL_AND_STROKE);
            mBorderLinePaint.setColor(TEXT_COLOR_NORMAL);

            paintText.setColor(TEXT_COLOR_SELECTED);
        } else {
            mBorderLinePaint.setStyle(Paint.Style.STROKE);
            mBorderLinePaint.setColor(Color.BLACK);

            paintText.setColor(TEXT_COLOR_NORMAL);
        }

        canvas.drawPath(mBorderPath, mBorderLinePaint);
        canvas.drawText(getButtonText(mButtonType), locationText.x, locationText.y, paintText);
    }

    private Point getLocationText(Button button, int viewWidth, int viewHeight) {
        Point point = new Point();
        int marginH = (int) (viewWidth * DESIGN_TEXT_LOCATION / DESIGN_VIEW_SIZE);
        int marginV = (int) (viewHeight * DESIGN_TEXT_LOCATION / DESIGN_VIEW_SIZE);
        switch (button) {
            case VOL_UP:
                point.set(marginH, (int) (marginV + paintText.getTextSize()));
                break;
            case VOL_DOWN:
                point.set(marginH, viewHeight - marginV);
                break;
            case CH_UP:
                point.set(viewWidth - marginH, (int) (marginV + paintText.getTextSize()));
                break;
            case CH_DOWN:
                point.set(viewWidth - marginH, viewHeight - marginV);
                break;
        }
        return point;
    }
}
