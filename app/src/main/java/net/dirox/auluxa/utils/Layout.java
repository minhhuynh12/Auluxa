package net.dirox.auluxa.utils;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;

import net.dirox.auluxa.utils.sizes.Size;

/**
 * Created by an on 6/13/2017.
 */

public class Layout {
    public static final String TAG = "LAYOUT";

    public enum SizeType {WIDTH, HEIGHT, BOTH}

    public static DisplayMetrics ScreenMetrics;

    public static Size Calc(Size size) {
        return new Size(ScreenMetrics.widthPixels * size.width, ScreenMetrics.heightPixels * size.height);
    }

    public static void setViewSize(View view, Size size) {
        setViewSize(view, size, SizeType.BOTH);
    }

    public static void setViewSize(View view, Size size, SizeType type) {
        if (view == null) return;

        ViewGroup.LayoutParams params = view.getLayoutParams();
        if (params == null)
            params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        Size sizeInPx = Calc(size);

        if (type == SizeType.BOTH || type == SizeType.WIDTH) params.width = (int) sizeInPx.width;
        if (type == SizeType.BOTH || type == SizeType.HEIGHT) params.height = (int) sizeInPx.height;

        view.setLayoutParams(params);
    }


    public static int dpToPx(int dp, DisplayMetrics displayMetrics) {
        return Math.round(dp * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
    }

    public static int dpToPx(int dp) {
        return (int) (dp * Resources.getSystem().getDisplayMetrics().density);
    }

    public static Bitmap getBitmap(Size imgSize, String fPath) {
        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        imgSize = Calc(imgSize);

        bmOptions.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(fPath, bmOptions);

        bmOptions.inJustDecodeBounds = false;
        bmOptions.inSampleSize = CalcSampleSize(bmOptions, (int) imgSize.width, (int) imgSize.height);
        return BitmapFactory.decodeFile(fPath, bmOptions);
    }

    public static int CalcSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            // Calculate the largest inSampleSize value that is a power of 2 and keeps both
            // height and width larger than the requested height and width.
            while ((halfHeight / inSampleSize) > reqHeight
                    && (halfWidth / inSampleSize) > reqWidth) {
                inSampleSize *= 2;
            }
        }

        return inSampleSize;
    }

    public static AlphaAnimation getBlinkAnim() {
        AlphaAnimation blinkOff = new AlphaAnimation(0.3f, 1.0f);
        blinkOff.setDuration(300);
        return blinkOff;
    }


    public static void fade(View view, boolean in) {
        if (view == null) return;

        view.clearAnimation();
        view.animate().alpha(in ? 1 : 0).setDuration(500).start();
    }

    public static void deactivate(View... views) {
        for (View view : views) {
            view.setClickable(false);
            view.setEnabled(false);
            view.setAlpha(0.4f);
        }
    }

    public static void activate(View... views) {
        for (View view : views) {
            view.setClickable(true);
            view.setEnabled(true);
            view.setAlpha(1f);
        }
    }


}
