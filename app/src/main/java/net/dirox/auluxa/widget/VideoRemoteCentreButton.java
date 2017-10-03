package net.dirox.auluxa.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;

import java.util.ArrayList;

/**
 * Created by an on 6/23/2017.
 */

public class VideoRemoteCentreButton extends VideoRemoteButton {

    private final float DESIGN_VIEW_SIZE = 425;
    private final float DESIGN_CHILD_SIZE = 55;
    private final float DESIGN_CHILD_CENTER_SMALL = 20;
    private final float DESIGN_CHILD_CENTER_LINE = 55;
    private final float DESIGN_CHILD_MARGIN_BORDER = 27;
    private final float DESIGN_CHILD_LINE_MARGIN = 10;

    private final int CHILD_COLOR_NORMAL = Color.parseColor("#636363");
    private final int CHILD_LINE_COLOR = Color.parseColor("#18bd9b");

    private ArrayList<ChildButton> arrayChildButton;
    protected Paint mChildPaint, mSelectedLinePaint;
    private float mChildButtonSize, mMarginBorder, mChildLineMargin;
    private float mChildCenterSmall, mChildCenterLine;

    private Button mCurrentSelect;

    public VideoRemoteCentreButton(Context context, Button button) {
        super(context, button);
        init(context);
    }

    private void init(Context context) {
        mCurrentSelect = Button.NONE;

        mChildPaint = new Paint();
        mChildPaint.setAntiAlias(true);
        mChildPaint.setColor(CHILD_COLOR_NORMAL);
        mChildPaint.setStyle(Paint.Style.STROKE);
        mChildPaint.setStrokeWidth(LINE_WIDTH);

        mSelectedLinePaint = new Paint();
        mSelectedLinePaint.setAntiAlias(true);
        mSelectedLinePaint.setColor(CHILD_LINE_COLOR);
        mSelectedLinePaint.setStyle(Paint.Style.STROKE);
        mSelectedLinePaint.setStrokeWidth(LINE_WIDTH);


        arrayChildButton = new ArrayList<>();
        arrayChildButton.add(new ChildButton(Button.CENTRE_ARROW_LEFT));
        arrayChildButton.add(new ChildButton(Button.CENTRE_ARROW_UP));
        arrayChildButton.add(new ChildButton(Button.CENTRE_ARROW_RIGHT));
        arrayChildButton.add(new ChildButton(Button.CENTRE_ARROW_DOWN));
        arrayChildButton.add(new ChildButton(Button.CENTRE_CENTRE));
    }

    public void setSize(int width, int height) {
        mViewSize = width;
        mChildButtonSize = mViewSize * DESIGN_CHILD_SIZE / DESIGN_VIEW_SIZE;
        mChildCenterSmall = mViewSize * DESIGN_CHILD_CENTER_SMALL / DESIGN_VIEW_SIZE;
        mChildCenterLine = mViewSize * DESIGN_CHILD_CENTER_LINE / DESIGN_VIEW_SIZE;

        mMarginBorder = DESIGN_CHILD_MARGIN_BORDER * mViewSize / DESIGN_VIEW_SIZE;
        mChildLineMargin = DESIGN_CHILD_LINE_MARGIN * mViewSize / DESIGN_VIEW_SIZE;

        for (int i = 0; i < arrayChildButton.size(); i++) {
            updateChildButton(arrayChildButton.get(i), mViewSize, (int) mChildButtonSize, (int) mMarginBorder);
        }
        setMeasuredDimension(width, height);
    }

    @Override
    public boolean checkContainPoint(float x, float y) {
        if (mBorderPolygon == null) {
            return false;
        }

        mIsFocus = mBorderPolygon.contains(new net.dirox.auluxa.utils.Polygon.Point(x - locationOnParent.x, y - locationOnParent.y));
        if (mIsFocus == true) {
            for (int i = 0; i < arrayChildButton.size(); i++) {
                if (arrayChildButton.get(i).checkContainPoint(x - locationOnParent.x, y - locationOnParent.y) == true) {
                    mCurrentSelect = arrayChildButton.get(i).buttonType;
                }
            }
        }
        invalidate();
        return mIsFocus;
    }

    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //super.onMeasure(widthMeasureSpec, widthMeasureSpec);
        mBorderPath = getPath(mButtonType, widthMeasureSpec, heightMeasureSpec, (int) LINE_WIDTH);
        setMeasuredDimension(mViewSize, mViewSize);
    }

    @Override
    protected void onSizeChanged(int width, int height, int oldw, int oldh) {
        super.onSizeChanged(width, height, oldw, oldh);
        mBorderPath = getPath(mButtonType, width, height, (int) LINE_WIDTH);
    }

    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawPath(mBorderPath, mBorderLinePaint);

        for (int i = 0; i < arrayChildButton.size(); i++) {
            ChildButton childButton = arrayChildButton.get(i);

            switch (childButton.buttonType) {
                case CENTRE_ARROW_LEFT:
                case CENTRE_ARROW_UP:
                case CENTRE_ARROW_RIGHT:
                case CENTRE_ARROW_DOWN:
                    mChildPaint.setStyle(Paint.Style.STROKE);
                    canvas.drawPath(childButton.mPath, mChildPaint);
                    break;

                case CENTRE_CENTRE:
                    mChildPaint.setStyle(Paint.Style.FILL_AND_STROKE);
                    canvas.drawPath(childButton.mPath, mChildPaint);
                    drawButtonSelected(canvas, childButton);
                    break;
            }
        }


    }

    private void drawButtonSelected(Canvas canvas, ChildButton childButton) {
        if (mCurrentSelect != Button.NONE) {
            float left = childButton.rectLocation.left;
            float top = childButton.rectLocation.top;
            float right = childButton.rectLocation.right;
            float bottom = childButton.rectLocation.bottom;

            float startX = 0, startY = 0, endX = 0, endY = 0;
            switch (mCurrentSelect) {
                case CENTRE_ARROW_LEFT:
                    startX = left - mChildLineMargin;
                    startY = (top + bottom) / 2;
                    endX = startX - mChildCenterLine;
                    endY = startY;
                    break;
                case CENTRE_ARROW_UP:
                    startX = (left + right) / 2;
                    startY = top - mChildLineMargin;
                    endX = startX;
                    endY = startY - mChildCenterLine;
                    break;
                case CENTRE_ARROW_RIGHT:
                    startX = right + mChildLineMargin;
                    startY = (top + bottom) / 2;
                    endX = startX + mChildCenterLine;
                    endY = startY;
                    break;
                case CENTRE_ARROW_DOWN:
                    startX = (left + right) / 2;
                    startY = bottom + mChildLineMargin;
                    endX = startX;
                    endY = startY + mChildCenterLine;
                    break;
            }

            canvas.drawLine(startX, startY, endX, endY, mSelectedLinePaint);
        }
    }

    private void updateChildButton(ChildButton childButton, int parentSize, float viewSize, int marginBorder) {

        RectF rectF = new RectF();
        Path _path = new Path();

        int posX, posY;
        switch (childButton.buttonType) {
            case CENTRE_ARROW_LEFT:
                posX = marginBorder;
                posY = (int) ((parentSize - viewSize) / 2);

                rectF.set(posX, posY, posX + viewSize, posY + viewSize);

                _path.moveTo(posX + (0.67f * viewSize), posY + (0.145f * viewSize));
                _path.lineTo(posX + (0.31f * viewSize), posY + (viewSize / 2));
                _path.lineTo(posX + (0.67f * viewSize), posY + (viewSize - (0.145f * viewSize)));

                break;

            case CENTRE_ARROW_UP:
                posX = (int) ((parentSize - viewSize) / 2);
                posY = marginBorder;

                rectF.set(posX, posY, posX + viewSize, posY + viewSize);

                _path.moveTo(posX + (0.145f * viewSize), posY + (0.67f * viewSize));
                _path.lineTo(posX + (viewSize / 2), posY + (0.31f * viewSize));
                _path.lineTo(posX + (viewSize - (0.145f * viewSize)), posY + (0.67f * viewSize));
                break;

            case CENTRE_ARROW_RIGHT:
                posX = (int) (parentSize - (viewSize + marginBorder));
                posY = (int) ((parentSize - viewSize) / 2);

                rectF.set(posX, posY, posX + viewSize, posY + viewSize);

                _path.moveTo(posX + (0.31f * viewSize), posY + (0.145f * viewSize));
                _path.lineTo(posX + (0.67f * viewSize), posY + (viewSize / 2));
                _path.lineTo(posX + (0.31f * viewSize), posY + (viewSize - (0.145f * viewSize)));
                break;

            case CENTRE_ARROW_DOWN:
                posX = (int) ((parentSize - viewSize) / 2);
                posY = (int) (parentSize - (viewSize + marginBorder));

                rectF.set(posX, posY, posX + viewSize, posY + viewSize);

                _path.moveTo(posX + (0.145f * viewSize), posY + (0.31f * viewSize));
                _path.lineTo(posX + (viewSize / 2), posY + (0.67f * viewSize));
                _path.lineTo(posX + (viewSize - (0.145f * viewSize)), posY + (0.31f * viewSize));
                break;

            case CENTRE_CENTRE:
                posX = (int) ((parentSize - mChildCenterSmall) / 2);
                posY = (int) ((parentSize - mChildCenterSmall) / 2);

                rectF.set(posX, posY, posX + mChildCenterSmall, posY + mChildCenterSmall);

                _path.moveTo(posX + (mChildCenterSmall / 2), posY);
                _path.lineTo(posX + (mChildCenterSmall), posY + (mChildCenterSmall / 2));
                _path.lineTo(posX + (mChildCenterSmall / 2), posY + (mChildCenterSmall));
                _path.lineTo(posX, posY + (mChildCenterSmall / 2));
                _path.close();
                break;
        }

        childButton.rectLocation = rectF;
        childButton.mPath = _path;
    }

    private class ChildButton {
        public Button buttonType;
        public RectF rectLocation;
        public Path mPath;

        public ChildButton(Button button) {
            this.buttonType = button;
            this.rectLocation = null;
        }

        public boolean checkContainPoint(float x, float y) {
            if (rectLocation == null) {
                return false;
            }
            return rectLocation.contains(x, y);
        }
    }
}
