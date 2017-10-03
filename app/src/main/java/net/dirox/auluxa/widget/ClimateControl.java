package net.dirox.auluxa.widget;

import android.animation.Animator;
import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.os.Vibrator;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Scroller;

import net.dirox.auluxa.R;
import net.dirox.auluxa.utils.Layout;

/**
 * Created by an on 6/15/2017.
 */

public class ClimateControl extends View {
    private static final String TAG = "ClimateControl";

    private final float MAX_TXT_WIDTH = 0.30f;
    private final float MIN_TXT_WIDTH = 0.10f;

    private final float RADIUS_OUT = 0.44f;
    private final float RADIUS_IN = 0.40f;
    private final float Y_DISTANCE = 0.20f;
    private final float ALPHA_THRESHOLD = 0.15f;
    private final boolean ENABLE_VIBRATOR = false;

    private final int[] temperatures = new int[]{
            17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35
    };

    private Paint p_ringIn, p_ringOut, pTxt, p_backFlash, p_back;

    private int radiusIn, radiusOut, tIndex;
    private int setClimateAlpha, setOffAlpha;
    private int yScrollDiff;

    private float xCenter, yCenter, yDistance, alphaThreshold;
    private float mainTxtSize, subTxtSize, txtSizeDiff;
    private float yTopBounds, yBottomBounds;

    private IListener ccInterface;
    private ControlTouchListener controlTouchListener;

    public ClimateControl(Context context) {
        super(context);
        init();
    }

    public ClimateControl(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ClimateControl(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        Resources res = getResources();

        p_ringIn = new Paint(Paint.ANTI_ALIAS_FLAG);
        p_ringIn.setStyle(Paint.Style.STROKE);
        p_ringIn.setColor(res.getColor(R.color.DimGray));
        p_ringIn.setStrokeWidth(Layout.dpToPx(2));

        p_ringOut = new Paint(Paint.ANTI_ALIAS_FLAG);
        p_ringOut.setStyle(Paint.Style.STROKE);
        p_ringOut.setColor(res.getColor(R.color.MediumGrayFaded));
        p_ringOut.setStrokeWidth(Layout.dpToPx(5));

        p_backFlash = new Paint(Paint.ANTI_ALIAS_FLAG);
        p_backFlash.setStyle(Paint.Style.FILL);
        p_backFlash.setColor(res.getColor(R.color.MainTeal));

        p_back = new Paint(Paint.ANTI_ALIAS_FLAG);
        p_back.setStyle(Paint.Style.FILL);
        p_back.setColor(res.getColor(R.color.BgLight));

        pTxt = new Paint(Paint.ANTI_ALIAS_FLAG);
        pTxt.setStyle(Paint.Style.FILL);

        Typeface plain = Typeface.createFromAsset(getContext().getAssets(), "Fonts/Lato/Lato-Light.ttf");
        pTxt.setTypeface(plain);
        pTxt.setTextAlign(Paint.Align.CENTER);
        pTxt.setColor(Color.WHITE);

        tIndex = temperatures.length / 2;

        this.setOnTouchListener(controlTouchListener = new ControlTouchListener());
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        float xpad = (float) (getPaddingLeft() + getPaddingRight());
        float ypad = (float) (getPaddingTop() + getPaddingBottom());

        float ww = (float) w - xpad;
        float hh = (float) h - ypad;

        // Figure out how big we can make the pie.
        float diameter = Math.min(ww, hh);

        radiusOut = (int) (diameter * RADIUS_OUT);
        radiusIn = (int) (diameter * RADIUS_IN);

        xCenter = ww / 2;
        yCenter = hh / 2;

        yTopBounds = yCenter - radiusIn;
        yBottomBounds = yCenter + radiusIn;

        yDistance = hh * Y_DISTANCE;
        alphaThreshold = hh * ALPHA_THRESHOLD;

        setTxtSize(pTxt, ww * MAX_TXT_WIDTH, "" + temperatures[temperatures.length - 1]);
        mainTxtSize = pTxt.getTextSize();

        setTxtSize(pTxt, ww * MIN_TXT_WIDTH, "" + temperatures[temperatures.length - 1]);
        subTxtSize = pTxt.getTextSize();

        txtSizeDiff = mainTxtSize - subTxtSize;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        p_back.setAlpha(155);
        canvas.drawCircle(xCenter, yCenter, radiusOut + p_ringOut.getStrokeWidth() / 2, p_back);

        if (setClimateAlpha > 0) {
            p_backFlash.setColor(pTxt.getColor());
            p_backFlash.setAlpha(setClimateAlpha);
            canvas.drawCircle(xCenter, yCenter, radiusOut, p_backFlash);
        }

        canvas.drawCircle(xCenter, yCenter, radiusOut, p_ringOut);
        canvas.drawCircle(xCenter, yCenter, radiusIn, p_ringIn);

        float yAdjusted = yCenter + yScrollDiff;
        float y_txtMain_base = yAdjusted;

        pTxt.setColor(getTxtColor(tIndex));
        if (ccInterface != null)
            ccInterface.onTextChangeColor(getTxtColor(tIndex));
        pTxt.setTextSize(getAdjustedTxtSize(y_txtMain_base));
        pTxt.setAlpha(getTxtAlpha(y_txtMain_base));

        float y_txtMain = y_txtMain_base + getTxtHalfHeight();
        canvas.drawText(String.valueOf(temperatures[tIndex]), xCenter, y_txtMain, pTxt);

        for (int indexDiff = 1; indexDiff <= 2; indexDiff++) {

            int prevIndex = tIndex - indexDiff;
            if (prevIndex >= 0) {
                float y_txtSubAbove_base = yAdjusted - (indexDiff * yDistance);

                if (y_txtSubAbove_base > yTopBounds) {
//                    Log.d(TAG, "Drawing : " + temperatures[prevIndex] + " base=" + y_txtSubAbove_base);

                    if (y_txtSubAbove_base <= yCenter - yDistance) pTxt.setTextSize(subTxtSize);
                    else pTxt.setTextSize(getAdjustedTxtSize(y_txtSubAbove_base));
                    pTxt.setColor(getTxtColor(prevIndex));
                    if (ccInterface != null)
                        ccInterface.onTextChangeColor(getTxtColor(tIndex));
                    pTxt.setAlpha(getTxtAlpha(y_txtSubAbove_base));

                    float y_txtSubAbove = y_txtSubAbove_base + getTxtHalfHeight();
                    canvas.drawText(String.valueOf(temperatures[prevIndex]), xCenter, y_txtSubAbove, pTxt);
                } else {
//                    Log.d(TAG, "EOB : " + temperatures[prevIndex] + " base=" + y_txtSubAbove_base);
                }
            }

            int nextIndex = tIndex + indexDiff;
            if (nextIndex < temperatures.length) {
                float y_txtSubBelow_base = yAdjusted + (indexDiff * yDistance);

                if (y_txtSubBelow_base < yBottomBounds) {
//                    Log.d(TAG, "Drawing : " + temperatures[nextIndex] + " base=" + y_txtSubBelow_base);

                    if (y_txtSubBelow_base >= yCenter + yDistance) pTxt.setTextSize(subTxtSize);
                    else pTxt.setTextSize(getAdjustedTxtSize(y_txtSubBelow_base));
                    pTxt.setColor(getTxtColor(nextIndex));
                    if (ccInterface != null)
                        ccInterface.onTextChangeColor(getTxtColor(tIndex));
                    pTxt.setAlpha(getTxtAlpha(y_txtSubBelow_base));

                    float y_txtSubBelow = y_txtSubBelow_base + getTxtHalfHeight();
                    canvas.drawText(String.valueOf(temperatures[nextIndex]), xCenter, y_txtSubBelow, pTxt);
                } else {
//                    Log.d(TAG, "EOB : " + temperatures[nextIndex] + " base=" + y_txtSubBelow_base);
                }
            }
        }

        if (setOffAlpha > 0) {
            p_back.setAlpha(setOffAlpha);
            canvas.drawCircle(xCenter, yCenter, radiusOut + p_ringOut.getStrokeWidth() / 2, p_back);

            pTxt.setColor(Color.WHITE);
            pTxt.setAlpha(setOffAlpha);
            pTxt.setTextSize(subTxtSize);
            float y_txtOff = y_txtMain_base + getTxtHalfHeight();
            canvas.drawText("OFF", xCenter, y_txtOff, pTxt);
        }

        controlTouchListener.handleFling();
    }

    public void setListner(IListener i) {
        ccInterface = i;
    }

    public void setOn(final boolean on) {
        ValueAnimator offScreen = on ? ValueAnimator.ofInt(190, 0) : ValueAnimator.ofInt(0, 190);
        offScreen.setDuration(300);
        offScreen.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                setOffAlpha = (Integer) valueAnimator.getAnimatedValue();
                invalidate();
            }
        });
        offScreen.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationCancel(Animator animation) {
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
            }

            @Override
            public void onAnimationStart(Animator animation) {
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                setClimateAlpha = 0;
            }
        });
        offScreen.start();
    }

    public void setClimate(int temp) {
        for (int i = 0; i < temperatures.length; i++)
            if (temperatures[i] == temp) {
                tIndex = i;
                invalidate();
                return;
            }
    }

    public int getTxtColor(int index) {
        return (Integer) new ArgbEvaluator().evaluate((((float) index) / ((float) (temperatures.length - 1))), getResources().getColor(R.color.CoolBlue), getResources().getColor(R.color.HotRed));
    }

    private int getTxtAlpha(float yTxtBase) {
        double distance = Math.abs(yTxtBase - yCenter);

        if (distance < alphaThreshold) return 255;

        double distanceDiff = distance - alphaThreshold;
        double maxDiff = radiusIn - alphaThreshold;

        double alpha = (255 - (255 * (distanceDiff / maxDiff)));

        return (int) alpha;
    }

    private float getAdjustedTxtSize(float yTxtBase) {
        return mainTxtSize - (txtSizeDiff * (Math.abs((yTxtBase - yCenter) / yDistance)));
    }

    private float getTxtHalfHeight() {
        return Math.abs((pTxt.descent() + pTxt.ascent()) / 2);
    }

    private void setTxtSize(Paint paint, float desiredWidth, String text) {

        // Pick a reasonably large value for the test. Larger values produce
        // more accurate results, but may cause problems with hardware
        // acceleration. But there are workarounds for that, too; refer to
        // http://stackoverflow.com/questions/6253528/font-size-too-large-to-fit-in-cache
        final float testTextSize = 48f;

        // Get the bounds of the text, using our testTextSize.
        paint.setTextSize(testTextSize);
        Rect bounds = new Rect();
        paint.getTextBounds(text, 0, text.length(), bounds);

        // Calculate the desired size as a proportion of our testTextSize.
        float desiredTextSize = testTextSize * desiredWidth / bounds.width();

        // Set the paint for that size.
        paint.setTextSize(desiredTextSize);
    }

    private class ControlTouchListener implements OnTouchListener {
        private static final int MAX_CLICK_DUR = 200;

        private Scroller scroller;
        private GestureDetector flingListener;
        private ValueAnimator resetScroll, setBack;

        private float yStart = 0;
        private boolean isFlung = false;
        private long startTouchTime;

        public ControlTouchListener() {

            scroller = new Scroller(getContext(), null, true);
            flingListener = new GestureDetector(getContext(), new FlingListener());
            resetScroll = null;
        }

        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {

            int action = motionEvent.getAction();

            switch (action) {
                case MotionEvent.ACTION_DOWN:

                    if (setOffAlpha > 0) return true;

                    yStart = motionEvent.getY();
                    yScrollDiff = 0;
                    isFlung = false;

                    if (resetScroll != null) {
                        resetScroll.cancel();
                        resetScroll = null;
                    }

                    if (setBack != null) {
                        setBack.cancel();
                        setBack = null;
                        setClimateAlpha = 0;
                    }

                    scroller.forceFinished(true);
                    flingListener.onTouchEvent(motionEvent);
                    startTouchTime = System.currentTimeMillis();

                    Log.d(TAG, "ACTION_DOWN");
                    break;

                case MotionEvent.ACTION_MOVE:

                    if (setOffAlpha > 0) return true;
                    else if (isAtEdge()) return false;

                    yScrollDiff = (int) (motionEvent.getY() - yStart);
                    flingListener.onTouchEvent(motionEvent);

                    Log.d(TAG, "ACTION_MOVE :: yScrollDiff=" + yScrollDiff);
                    break;

                case MotionEvent.ACTION_UP:

                    boolean isClick = System.currentTimeMillis() - startTouchTime <= MAX_CLICK_DUR;

                    if (setOffAlpha > 0) return setOn(true);
                    else if (isAtEdge()) return false;

                    if (!flingListener.onTouchEvent(motionEvent)) {
                        if (isClick) setOn(false);
                        adjustScroll();
                    }

                    Log.d(TAG, "ACTION_UP");
                    break;

                case MotionEvent.ACTION_CANCEL:

                    flingListener.onTouchEvent(motionEvent);
                    break;
            }

            settleScrollDiff(motionEvent.getY());
            if (!isAtEdge()) invalidate();

            return true;
        }

        private boolean setOn(boolean on) {
            if (ccInterface != null) ccInterface.onUserSet(on);
            ClimateControl.this.setOn(on);
            return true;
        }

        public void handleFling() {
            if (!isFlung) return;

            if (scroller.isFinished() || isAtEdge()) {

                adjustScroll();
                isFlung = false;
                Log.d(TAG, "ACTION FLING DONE :: yScrollDiff=" + yScrollDiff + " isAtEdge=" + (isAtEdge() ? "true" : "false"));

            } else {

                scroller.computeScrollOffset();
                int scrollerY = scroller.getCurrY();

                yScrollDiff += (int) (scrollerY - yStart);

                settleScrollDiff(scrollerY);
                invalidate();

                Log.d(TAG, "ACTION FLING :: yScrollDiff=" + yScrollDiff + " scrollerY=" + scrollerY);

            }
        }

        public void adjustScroll() {
            Log.d(TAG, "ACTION :: Adjust Scroll : yScrollDiff=" + yScrollDiff);

            resetScroll = ValueAnimator.ofInt(yScrollDiff, 0);
            resetScroll.setDuration(350);
            resetScroll.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    yScrollDiff = (Integer) valueAnimator.getAnimatedValue();
                    invalidate();
                }
            });
            resetScroll.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationCancel(Animator animator) {
                }

                @Override
                public void onAnimationRepeat(Animator animator) {
                }

                @Override
                public void onAnimationStart(Animator animator) {
                }

                @Override
                public void onAnimationEnd(Animator animator) {
                    if (setOffAlpha > 0) return;

                    setBack = ValueAnimator.ofInt(0, 100);
                    setBack.setRepeatMode(ValueAnimator.REVERSE);
                    setBack.setRepeatCount(1);
                    setBack.setDuration(300);
                    setBack.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                        @Override
                        public void onAnimationUpdate(ValueAnimator valueAnimator) {
                            setClimateAlpha = (Integer) valueAnimator.getAnimatedValue();
                            invalidate();
                        }
                    });
                    setBack.addListener(new Animator.AnimatorListener() {
                        @Override
                        public void onAnimationCancel(Animator animation) {
                        }

                        @Override
                        public void onAnimationRepeat(Animator animation) {
                        }

                        @Override
                        public void onAnimationEnd(Animator animation) {
                            if (ccInterface != null) ccInterface.onUserSet(temperatures[tIndex]);
                            setClimateAlpha = 0;
                        }

                        @Override
                        public void onAnimationStart(Animator animation) {
                            if (ENABLE_VIBRATOR == true) {
                                ((Vibrator) getContext().getSystemService(Context.VIBRATOR_SERVICE)).vibrate(300);
                            }
                        }
                    });
                    setBack.start();
                }
            });
            resetScroll.start();
        }

        public void settleScrollDiff(float currentY) {

            if (Math.abs(yScrollDiff) >= yDistance) {

                if (yScrollDiff > 0) tIndex--;
                else if (yScrollDiff < 0) tIndex++;

                if (tIndex >= temperatures.length) tIndex = temperatures.length - 1;
                else if (tIndex < 0) tIndex = 0;

                boolean negScrollDiff = yScrollDiff < 0;
                float difference = Math.abs(yScrollDiff) - yDistance;

                yStart = currentY;
                yScrollDiff = (int) (negScrollDiff ? -difference : difference);

                if (ENABLE_VIBRATOR == true) {
                    ((Vibrator) getContext().getSystemService(Context.VIBRATOR_SERVICE)).vibrate(100);
                }
//                Log.d(TAG, "ACTION :: RESET SCROLL DIFF :: tIndex=" + tIndex + " yScrollDiff=" + yScrollDiff);
            }
        }

        public boolean isAtEdge() {
            return (tIndex == 0 && yScrollDiff > 0) || (tIndex == temperatures.length - 1 && yScrollDiff < 0);
        }

        private class FlingListener extends GestureDetector.SimpleOnGestureListener {

            private final int SCALE = 3;

            @Override
            public boolean onDown(MotionEvent e) {
                return true;
            }

            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                int startY = (int) (yStart = tIndex * yDistance);
                int maxY = (int) (yDistance * temperatures.length);

//                Log.d(TAG, "ACTION FLING :: Simulating Fling :: yScrollDiff=" + yScrollDiff + " velocityY=" + (int) (velocityY / SCALE));

                scroller.fling(0, startY, 0, (int) (velocityY / SCALE), 0, 0, 0, maxY);
                postInvalidate();
                return isFlung = true;
            }
        }
    }


    public interface IListener {
        public void onUserSet(int temp);

        public void onUserSet(boolean on);

        public void onTextChangeColor(int color);
    }
}

