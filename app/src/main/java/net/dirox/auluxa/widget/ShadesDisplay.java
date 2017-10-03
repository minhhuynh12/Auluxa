package net.dirox.auluxa.widget;

import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import net.dirox.auluxa.R;
import net.dirox.auluxa.utils.Layout;

/**
 * Created by an on 6/13/2017.
 */

public class ShadesDisplay extends View {
    private static final String TAG = "ShadesDisplay";

    private final int blockRoundRad = 4;
    private final int spacingDP = 7;
    private final int shadeBlocks = 11;
    private final int curtainBlocks = 13;

    private Paint p_close, p_open, p_semi;

    private int level, animLevel, spacingPixels;
    private float shadeBlkSz;
    private boolean isShade;

    private ValueAnimator lvlAnim;
    private RectF blockRect;

    public static int[] shadeSemi;

    public ShadesDisplay(Context context) {
        super(context);
        init();
    }

    public ShadesDisplay(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ShadesDisplay(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {

        Resources res = getResources();

        p_close = new Paint(Paint.ANTI_ALIAS_FLAG);
        p_close.setStyle(Paint.Style.FILL);
        p_close.setColor(res.getColor(R.color.MainTeal));

        p_open = new Paint(Paint.ANTI_ALIAS_FLAG);
        p_open.setStyle(Paint.Style.FILL);
        p_open.setColor(res.getColor(R.color.BgDarker));

        p_semi = new Paint(Paint.ANTI_ALIAS_FLAG);
        p_semi.setStyle(Paint.Style.FILL);

        spacingPixels = Layout.dpToPx(spacingDP);
        blockRect = new RectF();

        animLevel = -1;
        isShade = false;

        shadeSemi = new int[5];

        ArgbEvaluator evaluator = new ArgbEvaluator();
        int mainTeal = res.getColor(R.color.MainTeal);
        int bgDarker = res.getColor(R.color.BgDarker);

        float partition = 1f / shadeSemi.length;
        for (int i = 0; i < shadeSemi.length; i++) {
            shadeSemi[i] = (Integer) evaluator.evaluate(partition * (i + 1), bgDarker, mainTeal);
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        // Account for padding
        float xpad = (float) (getPaddingLeft() + getPaddingRight());
        float ypad = (float) (getPaddingTop() + getPaddingBottom());

        float ww = (float) w - xpad;
        float hh = (float) h - ypad;

        shadeBlkSz = 100 / shadeBlocks;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int displayLvl = animLevel == -1 ? level : animLevel;

        if (isShade) {

            int closedShades = displayLvl / (int) shadeBlkSz;
            int semiShade = displayLvl % (int) shadeBlkSz;
            int totalPadding = getPaddingBottom() + getPaddingTop();
            int totalSpacing = ((shadeBlocks - 1) * spacingPixels);
            int blockHeight = (getHeight() - totalSpacing - totalPadding) / shadeBlocks;

            int left = getPaddingLeft(), right = getWidth() - getPaddingRight();

            for (int i = 0; i < shadeBlocks; i++) {

                boolean closed = i < closedShades;
                int top = getPaddingTop() + i * (spacingPixels + blockHeight);
                int bottom = top + blockHeight;

                Paint p_block = closed ? p_close : p_open;
                int semiIndex = (int) (semiShade / shadeBlkSz) * shadeSemi.length; //Math.min(Layout.Colors.shadeSemi.length - 1, );
                if (!closed && i == closedShades && displayLvl > 0) {
                    p_semi.setColor(shadeSemi[semiIndex]);
                    p_block = p_semi;
                }

                blockRect.set(left, top, right, bottom);
                canvas.drawRoundRect(blockRect, blockRoundRad, blockRoundRad, p_block);
            }

        } else {

            double exactClosedCurtains = ((double) displayLvl / 100) * curtainBlocks;
            int totalPadding = getPaddingLeft() + getPaddingRight();
            int totalSpacing = ((curtainBlocks - 2) * spacingPixels);
            int blockWidth = (getWidth() - totalSpacing - totalPadding) / curtainBlocks;

            double exactClosedHalf = exactClosedCurtains / 2;
            int closedHalf = (int) exactClosedHalf;
            int top = getPaddingTop(), bottom = getHeight() - getPaddingBottom();

            for (int i = 0; i < curtainBlocks; i++) {

                boolean closed = (displayLvl == 100) || (i < closedHalf) || (i >= curtainBlocks - closedHalf);
                int left = getPaddingLeft() + i * (spacingPixels + blockWidth);
                int right = left + blockWidth;
                Paint p_block = closed ? p_close : p_open;
                float fraction = (float) (exactClosedHalf - closedHalf);
                if (i == curtainBlocks / 2) fraction = Math.min(fraction * 2f, 1);
                int semiIndex = (int) (fraction * shadeSemi.length - 1);
                if (!closed && i == closedHalf || i == curtainBlocks - closedHalf - 1) {
                    if (semiIndex >= 0) {
                        p_semi.setColor(shadeSemi[semiIndex]);
                        p_block = p_semi;
                    }
                }

                if (animLevel != -1) Log.d(TAG, "fraction=" + fraction + " semiIndex=" + semiIndex);

                blockRect.set(left, top, right, bottom);
                canvas.drawRoundRect(blockRect, blockRoundRad, blockRoundRad, p_block);
            }
        }
    }

    public void startAnim(boolean close) {
        lvlAnim = ValueAnimator.ofInt(level, close ? 100 : 0);
        lvlAnim.setRepeatMode(ValueAnimator.RESTART);
        lvlAnim.setRepeatCount(ValueAnimator.INFINITE);
        lvlAnim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            private boolean invalid = false;

            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                animLevel = (Integer) valueAnimator.getAnimatedValue();
                invalidate();
            }
        });
        lvlAnim.setDuration(2500);//(2000 * ((close ? 100 - level : level)/100)));
        lvlAnim.start();
    }

    public void setLvl(int lvl) {
        level = lvl;

        if (animLevel != -1) {
            Log.d(TAG, "LvlAnim Stopping @ " + level);
            lvlAnim.cancel();
            animLevel = -1;
        }

        invalidate();
    }

    public void setOrientation(boolean isHorizontal) {
        isShade = !isHorizontal;
        invalidate();
    }
}

