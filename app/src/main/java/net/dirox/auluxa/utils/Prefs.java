package net.dirox.auluxa.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.Gson;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by kiemduongvan on 3/15/16.
 */
public class Prefs {

    public static void setStringPrefs(Context context, String key, String value) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public static String getStringPrefs(Context context, String key) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        return prefs.getString(key, "");
    }

    public static void setIntPrefs(Context context, String key, int value) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(key, value);
        editor.apply();
    }

    public static int getIntPrefs(Context context, String key) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        return prefs.getInt(key, 0);
    }

    public static void setBooleanPrefs(Context context, String key, boolean value) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

    public static Boolean getBooleanPrefs(Context context, String key) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        return prefs.getBoolean(key, false);
    }

    public static boolean saveArrayPrefs(Context mContext, String arrayName, String[] array) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(mContext);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(arrayName + "_size", array.length);

        for (int i = 0; i < array.length; i++)
            editor.putString(arrayName + "_" + i, array[i]);

        return editor.commit();
    }

    public static String[] loadArrayPrefs(Context mContext, String arrayName) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(mContext);
        int size = prefs.getInt(arrayName + "_size", 0);
        String array[] = new String[size];

        for (int i = 0; i < size; i++)
            array[i] = prefs.getString(arrayName + "_" + i, null);

        return array;
    }

    public static boolean saveObjectPrefs(Context mContext, String objectName, Object object) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(mContext);
        SharedPreferences.Editor editor = prefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(object);
        editor.putString(objectName, json);
        //Log.e(object + " saveObjectPrefs", json);

        return editor.commit();
    }

    public static void saveList(Context mContext, String objectName, List list){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(mContext);
        SharedPreferences.Editor editor = prefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(list);
        editor.putString(objectName, json);
        editor.commit();
    }

    public static void applyObjectPrefs(Context mContext, String objectName, Object object) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(mContext);
        SharedPreferences.Editor editor = prefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(object);
        //clear
        editor.remove(objectName);
        editor.apply();
        //apply new
        editor.putString(objectName, json);
        //Log.e(object + " saveObjectPrefs", json);
        editor.apply();
    }

    public static Object loadObjectPrefs(Context mContext, String objectName, Type type) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(mContext);
        Gson gson = new Gson();
        String objectString = prefs.getString(objectName, "");

        //Log.e(objectName + " loadObjectPrefs", objectString);
        Object object = gson.fromJson(objectString, type);

        return object;
    }


    public static String loadString(Context mContext, String objectName){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(mContext);
        return prefs.getString(objectName, "");
    }

    public static void clearPrefs(Context mContext) {
        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(mContext);
        SharedPreferences.Editor editor = sharedPrefs.edit();
        editor.clear();
        editor.commit();
        editor.apply();
    }

    public static void removePrefs(Context mContext, String key) {
        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(mContext);
        SharedPreferences.Editor editor = sharedPrefs.edit();
        editor.remove(key);
        editor.commit();
        editor.apply();
    }

}
