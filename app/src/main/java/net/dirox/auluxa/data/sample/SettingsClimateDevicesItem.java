package net.dirox.auluxa.data.sample;

/**
 * Created by trungnq on 27/06/2017.
 */

public class SettingsClimateDevicesItem {
    private int id;
    private String climateDeviceName;
    private int resourdId;

    public SettingsClimateDevicesItem(int id, String climateDeviceName, int resourdId) {
        this.id = id;
        this.climateDeviceName = climateDeviceName;
        this.resourdId = resourdId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getClimateDeviceName() {
        return climateDeviceName;
    }

    public void setClimateDeviceName(String climateDeviceName) {
        this.climateDeviceName = climateDeviceName;
    }

    public int getResourdId() {
        return resourdId;
    }

    public void setResourdId(int resourdId) {
        this.resourdId = resourdId;
    }
}
