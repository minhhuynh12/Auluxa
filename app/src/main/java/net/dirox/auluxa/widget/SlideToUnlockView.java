package net.dirox.auluxa.widget;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import net.dirox.auluxa.R;

/**
 * Created by an on 6/20/2017.
 */

public class SlideToUnlockView extends RelativeLayout {
    private Drawable track;
    private View background;

    public interface OnUnlockListener {

        /**
         * Called when unlock event occurred.
         */
        void onUnlock();
    }

    private OnUnlockListener listener;
    private SeekBar seekbar;
    private TextView label;
    private int thumbWidth;

    public SlideToUnlockView(Context context) {
        super(context);
        init(context, null);
    }

    public SlideToUnlockView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public SlideToUnlockView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context, attrs);
    }

    public void setOnUnlockListener(OnUnlockListener listener) {
        this.listener = listener;
    }

    /**
     * Resets slider to initial state.
     */
    public void reset() {
        seekbar.setProgress(0);
    }

    private void init(Context context, AttributeSet attrs) {
        if (isInEditMode()) {
            return;
        }
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.layout_slide_to_unlock_view, this, true);

        label = (TextView) findViewById(R.id.slider_label);
        seekbar = (SeekBar) findViewById(R.id.slider_seekbar);
        background = findViewById(R.id.slider_bg);

        TypedArray attributes = context.obtainStyledAttributes(attrs, R.styleable.SlideToUnlockView);
        String text = attributes.getString(R.styleable.SlideToUnlockView_slide_text);
        Drawable thumb = attributes.getDrawable(R.styleable.SlideToUnlockView_slide_thumb);
        if (thumb == null) {
            thumb = getResources().getDrawable(R.drawable.ic_security_unlock);
        }
        track = attributes.getDrawable(R.styleable.SlideToUnlockView_slide_track);
        attributes.recycle();

        thumbWidth = (int) context.getResources().getDimension(R.dimen.icon_security_slide_to_unlock_thumb_size);// thumb.getIntrinsicWidth();

        Bitmap bmpOrg = ((BitmapDrawable) thumb).getBitmap();
        Bitmap bmpScaled = Bitmap.createScaledBitmap(bmpOrg, thumbWidth, thumbWidth, true);
        Drawable newThumb = new BitmapDrawable(context.getResources(), bmpScaled);
        newThumb.setBounds(0, 0, newThumb.getIntrinsicWidth(), newThumb.getIntrinsicHeight());

        if (track != null) {
            background.setBackgroundDrawable(track);
        }

        if (text != null) {
            label.setText(text);
        }
        label.setPadding(thumbWidth / 2, 0, 0, 0);

        int defaultOffset = seekbar.getThumbOffset();
        seekbar.setThumb(newThumb);
        seekbar.setThumbOffset(defaultOffset);


        seekbar.setOnTouchListener(new OnTouchListener() {
            private boolean isInvalidMove;

            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        return isInvalidMove = motionEvent.getX() > thumbWidth;
                    case MotionEvent.ACTION_MOVE:
                        return isInvalidMove;
                    case MotionEvent.ACTION_UP:
                        return isInvalidMove;
                }
                return false;
            }
        });

        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromTouch) {
                label.setAlpha(1f - progress * 0.02f);
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                if (seekBar.getProgress() < 100) {
                    ObjectAnimator anim = ObjectAnimator.ofInt(seekBar, "progress", 0);
                    anim.setInterpolator(new AccelerateDecelerateInterpolator());
                    anim.setDuration(getResources().getInteger(android.R.integer.config_shortAnimTime));
                    anim.start();
                } else {
                    if (listener != null) {
                        listener.onUnlock();
                    }
                }
            }
        });
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (isInEditMode()) {
            return;
        }

        //prevents 9-patch background image from full size stretching
        if (getLayoutParams().height == ViewGroup.LayoutParams.WRAP_CONTENT) {
            background.getLayoutParams().height = seekbar.getHeight() + fromDpToPx(3);
        }

    }

    public int fromDpToPx(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                getResources().getDisplayMetrics());
    }
}