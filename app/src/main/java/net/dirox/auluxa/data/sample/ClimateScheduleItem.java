package net.dirox.auluxa.data.sample;

/**
 * Created by trungnq on 12/06/2017.
 */

public class ClimateScheduleItem {
    private int id;
    private String titleClimateSchedule;

    public ClimateScheduleItem(int id, String titleClimateSchedule) {
        this.id = id;
        this.titleClimateSchedule = titleClimateSchedule;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitleClimateSchedule() {
        return titleClimateSchedule;
    }

    public void setTitleClimateSchedule(String titleClimateSchedule) {
        this.titleClimateSchedule = titleClimateSchedule;
    }

}
