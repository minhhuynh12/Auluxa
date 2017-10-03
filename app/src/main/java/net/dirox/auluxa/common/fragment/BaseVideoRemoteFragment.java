package net.dirox.auluxa.common.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import net.dirox.auluxa.R;
import net.dirox.auluxa.adapter.InputAdapter;
import net.dirox.auluxa.fragments.scenes.CreateScenesRoomVideoAddSequenceFragment;

/**
 * Created by an on 6/30/2017.
 */

public class BaseVideoRemoteFragment extends BaseFragment {

    public int linearLayoutCurrent = -1;
    public int imageCurrent = -1;
    public int imageDefault = -1;
    public int colorDefault = -1;
    public InputAdapter adapter1;
    public InputAdapter adapter2;
    LinearLayout linearLayoutCurrent1;

    public void changeActionButtonImage(View parentView, int childViewId, boolean resetAdapter, int ivOld, int ivNew, int color) {

        LinearLayout viewClick1 = (LinearLayout) parentView.findViewById(childViewId);
        viewClick1.setOnClickListener(new View.OnClickListener() {
            @Override
            public synchronized void onClick(View v) {
                if (resetAdapter) {
                    resetAdapter();
                }
                //   Chưa Click
                if (linearLayoutCurrent == -1) {
                    linearLayoutCurrent = childViewId;
                    linearLayoutCurrent1 = (LinearLayout) parentView.findViewById(linearLayoutCurrent);
                    linearLayoutCurrent1.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.drawable.shape_background_btn_video_select));
                    for (int j = 0; j < linearLayoutCurrent1.getChildCount(); j++) {
                        if (linearLayoutCurrent1.getChildAt(j) instanceof ImageView) {
                            ((ImageView) (linearLayoutCurrent1.getChildAt(j))).setImageResource(ivNew);
                            imageCurrent = ivNew;
                            imageDefault = ivOld;
                            colorDefault = -1;
                        } else if (linearLayoutCurrent1.getChildAt(j) instanceof TextView) {
                            ((TextView) (linearLayoutCurrent1.getChildAt(j))).setTextColor(ContextCompat.getColor(getContext(), R.color.white));
                            colorDefault = color;
                        }
                    }
                } else {
                    if (childViewId == linearLayoutCurrent) {
                        resetViewDefault(parentView);
                    } else {
                        linearLayoutCurrent1 = (LinearLayout) parentView.findViewById(linearLayoutCurrent);
                        if (linearLayoutCurrent1 != null) {
                            linearLayoutCurrent1.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.drawable.shape_background_btn_video));
                            for (int j = 0; j < linearLayoutCurrent1.getChildCount(); j++) {
                                if (linearLayoutCurrent1.getChildAt(j) instanceof ImageView) {
                                    ((ImageView) (linearLayoutCurrent1.getChildAt(j))).setImageResource(imageDefault);
                                } else if (linearLayoutCurrent1.getChildAt(j) instanceof TextView) {
                                    ((TextView) (linearLayoutCurrent1.getChildAt(j))).setTextColor(ContextCompat.getColor(getContext(), colorDefault));
                                }
                            }
                        } else
                            linearLayoutCurrent = -1;
                        viewClick1.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.drawable.shape_background_btn_video_select));
                        for (int j = 0; j < viewClick1.getChildCount(); j++) {
                            if (viewClick1.getChildAt(j) instanceof ImageView) {
                                ((ImageView) (viewClick1.getChildAt(j))).setImageResource(ivNew);
                                imageCurrent = ivNew;
                                imageDefault = ivOld;
                                colorDefault = -1;
                            } else if (viewClick1.getChildAt(j) instanceof TextView) {
                                imageDefault = -1;
                                imageCurrent = -1;
                                colorDefault = color;
                                ((TextView) (viewClick1.getChildAt(j))).setTextColor(ContextCompat.getColor(getContext(), R.color.white));
                            }
                        }
                        linearLayoutCurrent = childViewId;
                    }
                }

                Fragment mParentFragment = getParentFragment();
                if (mParentFragment instanceof CreateScenesRoomVideoAddSequenceFragment) {
                    ((CreateScenesRoomVideoAddSequenceFragment)mParentFragment).setActionButtonSelected(linearLayoutCurrent);
                }
            }
        });
    }

    private void resetAdapter() {
        adapter1.setSelect(-1);
        adapter2.setSelect(-1);
    }

    public void resetViewDefault(View view) {
        if (linearLayoutCurrent != -1) {
            linearLayoutCurrent1 = (LinearLayout) view.findViewById(linearLayoutCurrent);
            linearLayoutCurrent1.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.drawable.shape_background_btn_video));
            for (int j = 0; j < linearLayoutCurrent1.getChildCount(); j++) {
                if (linearLayoutCurrent1.getChildAt(j) instanceof ImageView) {
                    ((ImageView) (linearLayoutCurrent1.getChildAt(j))).setImageResource(imageDefault);
                } else if (linearLayoutCurrent1.getChildAt(j) instanceof TextView) {
                    ((TextView) (linearLayoutCurrent1.getChildAt(j))).setTextColor(ContextCompat.getColor(getContext(), colorDefault));
                }
            }
            imageCurrent = -1;
            imageDefault = -1;
            colorDefault = -1;
            linearLayoutCurrent = -1;
        }
    }
}
