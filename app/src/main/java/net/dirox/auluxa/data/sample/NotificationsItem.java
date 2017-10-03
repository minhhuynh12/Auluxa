package net.dirox.auluxa.data.sample;

/**
 * Created by trungnq on 12/06/2017.
 */

public class NotificationsItem {
    private int id;
    private String titleNotifications;

    public NotificationsItem(int id, String titleNotifications) {
        this.id = id;
        this.titleNotifications = titleNotifications;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitleNotifications() {
        return titleNotifications;
    }

    public void setTitleNotifications(String titleNotifications) {
        this.titleNotifications = titleNotifications;
    }

}
