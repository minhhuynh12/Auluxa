package net.dirox.auluxa.data.local;

import android.content.Context;

import javax.inject.Inject;

import static net.dirox.auluxa.data.local.AppInfo.SCHEMA.IS_SETUP;

public class AppInfo extends BasePreference {

    private boolean isSetup = false;

    @Inject
    public AppInfo(Context context) {
        this.context = context;
        this.PREF_NAME = "AppInfo";
    }

    static final class SCHEMA {
        static final String IS_SETUP = "is_setup";
    }

//region Getters and Setters

    public boolean isSetup() {
        if (!isSetup) {
            isSetup = getPreference(IS_SETUP, false);
        }
        return isSetup;
    }

    public void setSetup(boolean setup) {
        isSetup = setup;
        savePreference(IS_SETUP, isSetup);
    }
    
//endregion
}
