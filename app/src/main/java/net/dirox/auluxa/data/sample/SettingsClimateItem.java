package net.dirox.auluxa.data.sample;

/**
 * Created by trungnq on 15/06/2017.
 */

public class SettingsClimateItem {
    private int id;
    private String climate;
    private boolean isCheck;

    public SettingsClimateItem(int id, String climate, boolean isCheck) {
        this.id = id;
        this.climate = climate;
        this.isCheck = isCheck;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getClimate() {
        return climate;
    }

    public void setClimate(String climate) {
        this.climate = climate;
    }

    public boolean isCheck() {
        return isCheck;
    }

    public void setCheck(boolean check) {
        isCheck = check;
    }


}
