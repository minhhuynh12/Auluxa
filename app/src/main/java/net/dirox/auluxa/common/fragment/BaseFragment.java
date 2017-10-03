package net.dirox.auluxa.common.fragment;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import net.dirox.auluxa.R;
import net.dirox.auluxa.adapter.InputAdapter;
import net.dirox.auluxa.common.activity.BaseActivity;
import net.dirox.auluxa.common.dagger.ActivityComponent;

/**
 * Created by MINH NGUYEN on 6/6/2017.
 */

public class BaseFragment extends Fragment {
    /**
     * call this method if fragment is attached on child activity from base actvity
     *
     * @return ActivityComponent
     */
    public ActivityComponent abstractActivityComponent() {
        return ((BaseActivity) getActivity()).abstractActivityComponent();
    }

    protected void hideKeyboardIfNeed(Context ctx) {
        InputMethodManager inputManager = (InputMethodManager) ctx.getSystemService(Context.INPUT_METHOD_SERVICE);
        // check if no view has focus:
        View v = ((Activity) ctx).getCurrentFocus();
        if (v == null)
            return;
        inputManager.hideSoftInputFromWindow(v.getWindowToken(), 0);
    }

    public void onBackAndHideKeyBoard() {
        hideKeyboardIfNeed(getContext());
        getActivity().onBackPressed();
    }

    public View.OnKeyListener onKeyListener = new View.OnKeyListener() {
        public boolean onKey(View v, int keyCode, KeyEvent event) {
            if (event.getAction() == KeyEvent.ACTION_DOWN) {
                switch (keyCode) {
                    case KeyEvent.KEYCODE_ENTER:
                        hideKeyboardIfNeed(getContext());
                        return true;
                    case KeyEvent.KEYCODE_DPAD_CENTER:

                    default:
                        break;
                }
            }
            return false;
        }
    };
}

