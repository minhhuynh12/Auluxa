package net.dirox.auluxa.data.local;

import android.content.Context;
import android.content.SharedPreferences;

abstract class BasePreference {
    String PREF_NAME = "DEFAULT";
    Context context;

    void savePreference(String preferenceName, boolean value) {
        SharedPreferences.Editor editor = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE).edit();
        editor.putBoolean(preferenceName, value);
        editor.apply();
    }

    boolean getPreference(String preferenceName, boolean defaultValue) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean(preferenceName, defaultValue);
    }
}
