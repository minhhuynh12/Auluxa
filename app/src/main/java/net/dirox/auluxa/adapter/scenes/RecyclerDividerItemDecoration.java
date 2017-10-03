package net.dirox.auluxa.adapter.scenes;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import net.dirox.auluxa.R;

/**
 * Created by DoanThiPhuongHuyen on 21/07/2017.
 */

public class RecyclerDividerItemDecoration extends RecyclerView.ItemDecoration {
    private Drawable mDivider;
    private int left = 60;
    private int right =60;

    public RecyclerDividerItemDecoration(Context context) {
        mDivider = context.getResources().getDrawable(R.drawable.line_divider);
    }
    public RecyclerDividerItemDecoration(Context context, int left, int right) {
        mDivider = context.getResources().getDrawable(R.drawable.line_divider);
        this.left = left;
        this.right = right;
    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        int left = parent.getPaddingLeft() + this.left;
        int right = parent.getWidth() - parent.getPaddingRight() - this.right;

        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = parent.getChildAt(i);

            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();

            int top = child.getBottom() + params.bottomMargin ;
            int bottom = top + mDivider.getIntrinsicHeight();

            mDivider.setBounds(left, top, right, bottom);
            mDivider.draw(c);
        }
    }
}