package net.dirox.auluxa.data.sample;

/**
 * Created by trungnq on 15/06/2017.
 */

public class SettingsNotificationsRoomsItem {
    public int id;
    public String rooms;
    public boolean checked;

    public SettingsNotificationsRoomsItem(int id, String rooms, boolean checked) {
        this.id = id;
        this.rooms = rooms;
        this.checked = checked;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRooms() {
        return rooms;
    }

    public void setRooms(String rooms) {
        this.rooms = rooms;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }
}
