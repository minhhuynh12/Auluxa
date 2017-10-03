package net.dirox.auluxa.data.sample;

/**
 * Created by trungnq on 12/06/2017.
 */

public class SettingsItem {
    private int id;
    private String titleSettings;

    public SettingsItem(int id, String titleSettings) {
        this.id = id;
        this.titleSettings = titleSettings;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitleSettings() {
        return titleSettings;
    }

    public void setTitleSettings(String titleSettings) {
        this.titleSettings = titleSettings;
    }

}
