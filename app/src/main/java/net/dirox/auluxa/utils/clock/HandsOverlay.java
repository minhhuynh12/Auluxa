package net.dirox.auluxa.utils.clock;


import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;

import java.util.Calendar;


public class HandsOverlay implements DialOverlay {

    private final Drawable mHour;
    private final Drawable mMinute;
    private final Drawable mSecond;
    private final boolean mUseLargeFace;
    private float mHourRot;
    private float mMinRot;
    private float mSecRot;
    private boolean mShowSeconds;
    private float scale;

    public HandsOverlay(Context context, boolean useLargeFace) {
        final Resources r = context.getResources();

        mUseLargeFace = useLargeFace;

        mHour = null;
        mMinute = null;
        mSecond = null;
    }

    public HandsOverlay(Drawable hourHand, Drawable minuteHand, Drawable secondHand) {
        mUseLargeFace = false;

        mHour = hourHand;
        mMinute = minuteHand;
        mSecond = secondHand;

    }

    public HandsOverlay withScale(float scale) {
        this.scale = scale;
        return this;
    }

    public HandsOverlay(Context context, int hourHandRes, int minuteHandRes, int secondHandRes) {
        final Resources r = context.getResources();

        mUseLargeFace = false;

        mHour = r.getDrawable(hourHandRes);
        mMinute = r.getDrawable(minuteHandRes);
        mSecond = r.getDrawable(secondHandRes);
    }

    public static float getHourHandAngle(int h, int m, int s) {
        return CustomAnalogClock.is24 ? (
                (12 + h) / 24.0f * 360) % 360 + (m / 60.0f) * 360 / 24.0f + (s / 60.0f) * 360 / 60.0f
                :
                ((12 + h) / 12.0f * 360) % 360 + (m / 60.0f) * 360 / 12.0f + (s / 60.0f) * 360 / 60.0f;
    }

    @Override
    public void onDraw(Canvas canvas, int cX, int cY, int w, int h, Calendar calendar,
                       boolean sizeChanged) {

        setShowSeconds(true);

        updateHands(calendar);

        canvas.save();
        if (!CustomAnalogClock.hourOnTop) {
            drawHours(canvas, cX, cY, w, h, calendar, sizeChanged);
        } else {
            drawMinutes(canvas, cX, cY, w, h, calendar, sizeChanged);
            drawSeconds(canvas, cX, cY, w, h, calendar, sizeChanged);
        }
        canvas.restore();

        canvas.save();
        if (!CustomAnalogClock.hourOnTop) {
            drawMinutes(canvas, cX, cY, w, h, calendar, sizeChanged);
            drawSeconds(canvas, cX, cY, w, h, calendar, sizeChanged);
        } else {
            drawHours(canvas, cX, cY, w, h, calendar, sizeChanged);
        }
        canvas.restore();
    }

    private void drawSeconds(Canvas canvas, int cX, int cY, int w, int h, Calendar calendar,
                             boolean sizeChanged) {
        canvas.rotate(mSecRot, cX, cY);

        if (sizeChanged) {
            w = (int) (mSecond.getIntrinsicWidth() * scale);
            h = (int) (mSecond.getIntrinsicHeight() * scale);
            mSecond.setBounds(cX - (w / 2), cY - (h / 2), cX + (w / 2), cY + (h / 2));
        }
        mSecond.draw(canvas);
    }

    private void drawMinutes(Canvas canvas, int cX, int cY, int w, int h, Calendar calendar,
                             boolean sizeChanged) {
        canvas.rotate(mMinRot, cX, cY);

        if (sizeChanged) {
            w = (int) (mMinute.getIntrinsicWidth() * scale);
            h = (int) (mMinute.getIntrinsicHeight() * scale);
            mMinute.setBounds(cX - (w / 2), cY - (h / 2), cX + (w / 2), cY + (h / 2));
        }
        mMinute.draw(canvas);
    }

    private void drawHours(Canvas canvas, int cX, int cY, int w, int h, Calendar calendar,
                           boolean sizeChanged) {
        canvas.rotate(mHourRot, cX, cY);

        if (sizeChanged) {
            w = (int) (mHour.getIntrinsicWidth() * scale);
            h = (int) (mHour.getIntrinsicHeight() * scale);
            mHour.setBounds(cX - (w / 2), cY - (h / 2), cX + (w / 2), cY + (h / 2));
        }
        mHour.draw(canvas);
    }

    public void setShowSeconds(boolean showSeconds) {
        mShowSeconds = showSeconds;
    }

    private void updateHands(Calendar calendar) {

        final int h = calendar.get(Calendar.HOUR_OF_DAY);
        final int m = calendar.get(Calendar.MINUTE);
        final int s = calendar.get(Calendar.SECOND);

        mHourRot = getHourHandAngle(h, m, s);
        mMinRot = (m / 60.0f) * 360 + (mShowSeconds ? ((s / 60.0f) * 360 / 60.0f) : 0);
        mSecRot = (s / 60.0f) * 360;
    }

}
