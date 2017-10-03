package net.dirox.auluxa.data.sample;

/**
 * Created by trungnq on 27/06/2017.
 */

public class SettingsClimateThermostatPanelItem {
    private int id;
    private String thermostatPanelName;
    private boolean isCheck;

    public SettingsClimateThermostatPanelItem(int id, String thermostatPanelName, boolean isCheck) {
        this.id = id;
        this.thermostatPanelName = thermostatPanelName;
        this.isCheck = isCheck;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getThermostatPanelName() {
        return thermostatPanelName;
    }

    public void setThermostatPanelName(String thermostatPanelName) {
        this.thermostatPanelName = thermostatPanelName;
    }

    public boolean isCheck() {
        return isCheck;
    }

    public void setCheck(boolean check) {
        isCheck = check;
    }
}
