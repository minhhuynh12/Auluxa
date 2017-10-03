package net.dirox.auluxa.widget;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;

import java.util.ArrayList;

import static net.dirox.auluxa.widget.VideoRemoteButton.Button.CENTRE;
import static net.dirox.auluxa.widget.VideoRemoteButton.Button.CH_DOWN;
import static net.dirox.auluxa.widget.VideoRemoteButton.Button.CH_UP;
import static net.dirox.auluxa.widget.VideoRemoteButton.Button.VOL_DOWN;
import static net.dirox.auluxa.widget.VideoRemoteButton.Button.VOL_UP;

/**
 * Created by an on 6/23/2017.
 */

public class VideoRemoteNavigator extends RelativeLayout {
    private final String TAG = "VideoRemoteNavigator";

    private final int DESIGN_VIEW_WIDTH = 516;
    private final int DESIGN_VIEW_HEIGHT = 424;
    private final int DESIGN_CORNER_BUTTON_SIZE = 198;

    private int mViewWidth, mViewHeight;
    private int mCornerButtonSize, mCentreButtonSize;
    private Paint paintLine;

    private VideoRemoteCornerButton btnVolUp, btnVolDown;
    private VideoRemoteCornerButton btnCHUp, btnCHDown;
    private VideoRemoteCentreButton btnCentre;
    private boolean mEnabledTouch = true;
    private ArrayList<RectF> arrayUserAffectRect;       // array region rect when tab on have interaction

    public VideoRemoteNavigator(Context context) {
        super(context);
        init(context);
    }

    public VideoRemoteNavigator(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public VideoRemoteNavigator(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }


    private void init(Context context) {
        arrayUserAffectRect = new ArrayList<>();

        paintLine = new Paint();
        paintLine.setAntiAlias(true);
        paintLine.setColor(Color.BLACK);
        paintLine.setStyle(Paint.Style.STROKE);
        paintLine.setStrokeWidth(2);


        btnVolUp = new VideoRemoteCornerButton(context, VOL_UP);
        btnVolDown = new VideoRemoteCornerButton(context, VOL_DOWN);
        btnCHUp = new VideoRemoteCornerButton(context, CH_UP);
        btnCHDown = new VideoRemoteCornerButton(context, CH_DOWN);
        btnCentre = new VideoRemoteCentreButton(context, CENTRE);

        addView(btnVolUp);
        addView(btnVolDown);
        addView(btnCHUp);
        addView(btnCHDown);
        addView(btnCentre);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        for (int i = 0; i < this.getChildCount(); i++) {

            int posX = 0, posY = 0;

            View childView = getChildAt(i);
            if (childView instanceof VideoRemoteButton) {
                switch (((VideoRemoteButton) childView).getButton()) {
                    case VOL_UP:
                        posX = 0;
                        posY = 0;
                        break;
                    case VOL_DOWN:
                        posX = 0;
                        posY = mViewHeight - mCornerButtonSize;
                        break;

                    case CH_UP:
                        posX = mViewWidth - mCornerButtonSize;
                        posY = 0;
                        break;

                    case CH_DOWN:
                        posX = mViewWidth - mCornerButtonSize;
                        posY = mViewHeight - mCornerButtonSize;
                        break;

                    case CENTRE:
                        posX = (mViewWidth - mViewHeight) / 2;
                        posY = 0;
                }

                ((VideoRemoteButton) childView).setLocationOnParent(posX, posY);
            }

            childView.layout(posX, posY, posX + childView.getMeasuredWidth(), posY + childView.getMeasuredHeight());
        }
    }

    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int height = widthMeasureSpec * DESIGN_VIEW_HEIGHT / DESIGN_VIEW_WIDTH;
        super.onMeasure(widthMeasureSpec, height);
        for (int i = 0; i < getChildCount(); i++) {
            View childView = getChildAt(i);
            if (childView instanceof VideoRemoteCornerButton) {
                childView.measure(mCornerButtonSize, mCornerButtonSize);
            }
            if (childView instanceof VideoRemoteCentreButton) {
                childView.measure(mCentreButtonSize, mCentreButtonSize);
            }
        }
        setMeasuredDimension(widthMeasureSpec, height);
    }

    @Override
    protected void onSizeChanged(int width, int height, int oldw, int oldh) {
        super.onSizeChanged(width, height, oldw, oldh);

        mViewWidth = width;
        mViewHeight = height;
        mCornerButtonSize = mViewWidth * DESIGN_CORNER_BUTTON_SIZE / DESIGN_VIEW_WIDTH;
        mCentreButtonSize = height;

        btnVolUp.setSize(mCornerButtonSize, mCornerButtonSize);
        btnVolDown.setSize(mCornerButtonSize, mCornerButtonSize);
        btnCHUp.setSize(mCornerButtonSize, mCornerButtonSize);
        btnCHDown.setSize(mCornerButtonSize, mCornerButtonSize);
        btnCentre.setSize(mCentreButtonSize, mCentreButtonSize);
    }

    /*@Override
    public synchronized void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        btnVolUp.draw(canvas);
        btnVolDown.draw(canvas);
        btnCHUp.draw(canvas);
        btnCHDown.draw(canvas);
        btnCentre.draw(canvas);
    }*/

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (mEnabledTouch) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    handlerPress(event.getX(), event.getY());
                    break;

                case MotionEvent.ACTION_UP:
                    onTouchUp();
                    break;
            }

            return true;
        }
        return false;
    }

    private void handlerPress(float x, float y) {
        for (int i = 0; i < getChildCount(); i++) {
            View childView = getChildAt(i);
            if (childView instanceof VideoRemoteButton) {
                if (((VideoRemoteButton) childView).checkContainPoint(x, y) == true) {

                }
            }
        }
    }

    private void onTouchUp() {
        for (int i = 0; i < getChildCount(); i++) {
            View childView = getChildAt(i);
            if (childView instanceof VideoRemoteButton) {
                ((VideoRemoteButton) childView).onTouchUp();
            }
        }
    }
}
