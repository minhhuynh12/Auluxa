package net.dirox.auluxa.data.sample;

/**
 * Created by trungnq on 15/06/2017.
 */

public class SettingsTimezoneItem {
    private int id;
    private String timeZone;
    private boolean isSelected;

    public SettingsTimezoneItem(int id, String timeZone, boolean isSelected) {
        this.id = id;
        this.timeZone = timeZone;
        this.isSelected = isSelected;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
