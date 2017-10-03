package net.dirox.auluxa.widget;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;


import net.dirox.auluxa.R;
import net.dirox.auluxa.utils.Layout;

/**
 * Created by an on 6/13/2017.
 */

public class IconButton extends android.support.v7.widget.AppCompatImageView {

    private Drawable imgOn, imgOff;

    private String title;
    private boolean activated;

    public IconButton(Context context) {
        super(context);
        init(null);
    }

    public IconButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public IconButton(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs);
    }

    private void init(AttributeSet attrs) {

        Resources res = getResources();

        if (attrs != null) {
            TypedArray attrsArray = getContext().getTheme().obtainStyledAttributes(attrs, R.styleable.IconButton, 0, 0);
            int id_on, id_off;
            try {
                title = attrsArray.getString(R.styleable.IconButton_ab_title);
                id_on = attrsArray.getResourceId(R.styleable.IconButton_ab_img_on, -1);
                id_off = attrsArray.getResourceId(R.styleable.IconButton_ab_img_off, -1);
            } finally {
                attrsArray.recycle();
            }
            imgOn = id_on == -1 ? null : res.getDrawable(id_on);
            imgOff = id_off == -1 ? null : res.getDrawable(id_off);

        } else imgOn = imgOff = null;

        setImageDrawable(imgOff);
    }

    public void setOn(final boolean on) {
        activated = on;

        AlphaAnimation blink = Layout.getBlinkAnim();
        blink.setAnimationListener(new Animation.AnimationListener() {
            @Override public void onAnimationStart(Animation animation) {
                IconButton.this.setImageDrawable(activated ? imgOn : imgOff);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });

        startAnimation(blink);
    }

    public void setAsPowerButton(final ICallback callback) {
        Resources res = getResources();

        imgOn = res.getDrawable(R.drawable.powerbtn_button_audioplayer4_popup_roomsplaying_livingroom2);
        imgOff = res.getDrawable(R.drawable.powerbtn_rollover_audioplayer4_popup_roomsplaying_livingroom2);

        setPadding(0, 0, 0, 0);
        setImageDrawable(imgOff);
        setOnClickListener(new OnClickListener() {
            @Override public void onClick(View view) {
                activated = !activated;

                setOn(activated);
                callback.onClick(activated);
            }
        });
    }

    public void setAsCheckbox(final ICallback callback) {
        Resources res = getResources();

        imgOn = res.getDrawable(R.drawable.check);
        imgOff = null;

        int padPix = Layout.dpToPx(5);

        setPadding(padPix, padPix, padPix, padPix);
        setBackgroundResource(R.drawable.checkbox);
        setOnClickListener(new OnClickListener() {
            @Override public void onClick(View v) {
                activated = !activated;

                setOn(activated);
                callback.onClick(activated);
            }
        });
    }

    public boolean isOn() { return activated; }
    public String getTitle() { return title; }

    public interface ICallback {
        public void onClick(boolean isOn);
    }

}

