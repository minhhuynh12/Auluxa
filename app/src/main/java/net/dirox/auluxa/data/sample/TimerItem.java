package net.dirox.auluxa.data.sample;

/**
 * Created by trungnq on 15/06/2017.
 */

public class TimerItem {
    private int id;
    private String timer;
    private boolean isCheck;

    public TimerItem(int id, String timer, boolean isCheck) {
        this.id = id;
        this.timer = timer;
        this.isCheck = isCheck;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTimer() {
        return timer;
    }

    public void setTimer(String timer) {
        this.timer = timer;
    }

    public boolean isCheck() {
        return isCheck;
    }

    public void setCheck(boolean check) {
        isCheck = check;
    }


}
