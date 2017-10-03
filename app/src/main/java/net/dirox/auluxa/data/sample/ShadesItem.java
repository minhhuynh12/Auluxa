package net.dirox.auluxa.data.sample;

/**
 * Created by an on 6/13/2017.
 */

public class ShadesItem {
    private final int CHANGE_STEP = 2;
    public int id;
    public String name;
    public boolean isHorizontalShade;
    public int shadesPercent;

    private boolean isOn;

    public ShadesItem(int id, String name, int shadesPercent, boolean isHorizontalShade) {
        this.id = id;
        this.name = name;
        this.shadesPercent = shadesPercent;
        this.isHorizontalShade = isHorizontalShade;
    }

    public boolean isOpenable() {
        return shadesPercent > 0 ? true : false;

    }

    public boolean isCloseable() {
        return shadesPercent < 100 ? true : false;
    }

    public void open() {
        this.shadesPercent -= CHANGE_STEP;
        if (shadesPercent < 0) {
            shadesPercent = 0;
        }
    }

    public void close() {
        this.shadesPercent += CHANGE_STEP;
        if (shadesPercent > 100) {
            shadesPercent = 100;
        }
    }

    public boolean isOn() {
        return isOn;
    }

    public void setOn(boolean on) {
        isOn = on;
    }
}
