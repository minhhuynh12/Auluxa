package net.dirox.auluxa.data.sample;

/**
 * Created by trungnq on 15/06/2017.
 */

public class SettingsNotificationsEnergyItem {
    public int id;
    public String energy;
    public boolean checked;

    public SettingsNotificationsEnergyItem(int id, String energy, boolean checked) {
        this.id = id;
        this.energy = energy;
        this.checked = checked;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEnergy() {
        return energy;
    }

    public void setEnergy(String energy) {
        this.energy = energy;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }
}
