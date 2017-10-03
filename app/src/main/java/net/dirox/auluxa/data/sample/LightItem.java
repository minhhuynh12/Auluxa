package net.dirox.auluxa.data.sample;

/**
 * Created by an on 6/12/2017.
 */

public class LightItem {
    public int id;
    public String lightName;
    public int lightPercent;
    public boolean isTurnedOn;

    public LightItem(int id, String lightName, int lightPercent, boolean isTurnedOn) {
        this.id = id;
        this.lightName = lightName;
        this.lightPercent = lightPercent;
        this.isTurnedOn = isTurnedOn;
    }
}
