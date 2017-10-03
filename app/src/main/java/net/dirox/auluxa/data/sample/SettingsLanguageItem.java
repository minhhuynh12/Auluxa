package net.dirox.auluxa.data.sample;

/**
 * Created by trungnq on 15/06/2017.
 */

public class SettingsLanguageItem {
    private int id;
    private String language;
    private boolean isCheck;

    public SettingsLanguageItem(int id, String language, boolean isCheck) {
        this.id = id;
        this.language = language;
        this.isCheck = isCheck;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public boolean isCheck() {
        return isCheck;
    }

    public void setCheck(boolean check) {
        isCheck = check;
    }


}
