package net.dirox.auluxa.utils;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


public class ScreenUtil {

    public static int getScreenHeight(Context context) {
        DisplayMetrics metrics = new DisplayMetrics();
        (((Activity) context).getWindowManager()).getDefaultDisplay().getMetrics(metrics);

        //int width = metrics.widthPixels;
        int height = metrics.heightPixels;

        return height;
    }

    public static int getScreenWidth(Context context) {
        DisplayMetrics metrics = new DisplayMetrics();
        (((Activity) context).getWindowManager()).getDefaultDisplay().getMetrics(metrics);

        int width = metrics.widthPixels;

        return width;
    }

    public static int getStatusBarHeight(Context context) {
        int result = 0;
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = context.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    public static int dpToPx(Context context, int dp) {
        return (int) (dp * context.getResources().getSystem().getDisplayMetrics().density);
    }

    public static int pxToDp(Context context, int px) {
        return (int) (px / context.getResources().getSystem().getDisplayMetrics().density);
    }


    public static ArrayList<View> getAllChildren(View v) {

        if (!(v instanceof ViewGroup)) {
            ArrayList<View> viewArrayList = new ArrayList<View>();
            viewArrayList.add(v);
            return viewArrayList;
        }

        ArrayList<View> result = new ArrayList<View>();

        ViewGroup vg = (ViewGroup) v;
        for (int i = 0; i < vg.getChildCount(); i++) {

            View child = vg.getChildAt(i);

            ArrayList<View> viewArrayList = new ArrayList<View>();
            viewArrayList.add(v);
            viewArrayList.addAll(getAllChildren(child));

            result.addAll(viewArrayList);
        }
        return result;
    }
}
