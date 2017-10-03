package net.dirox.auluxa.utils;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.widget.Button;

import net.dirox.auluxa.R;

/**
 * Created by trungnq on 29/06/2017.
 */

public class VideoSelectDeviceUtil {

    public static void buttonSelectDeviceInActive(Context context, Button button, Resources resources, String key) {
        button.setText(Prefs.getStringPrefs(context, key));
        button.setBackgroundResource(R.drawable.custom_border_edit_text_off);
        button.setTextColor(Color.parseColor("#B0000000"));
        button.setCompoundDrawablesWithIntrinsicBounds(null, null, resources.getDrawable(R.drawable.login_button_plus), null);
    }

    public static void buttonSelectDeviceActive(Context context, Button button, Resources resources) {
        button.setBackgroundResource(R.drawable.custom_border_edit_text);
        button.setTextColor(Color.parseColor("#18bd9b"));
        button.setCompoundDrawablesWithIntrinsicBounds(null, null, resources.getDrawable(R.drawable.add_icon), null);
    }
}
