package net.dirox.auluxa.data.sample;

import net.dirox.auluxa.R;

import java.util.ArrayList;
import java.util.LinkedHashMap;

/**
 * Created by an on 6/20/2017.
 */

public class SecurityItem {

    public LinkedHashMap<Alarm, Boolean> hashMapAlarm;
    public LinkedHashMap<Door, Boolean> hashMapDoor;
    public ArrayList<CameraItem> arrayListCamera;

    public SecurityItem() {

        hashMapAlarm = new LinkedHashMap<>();
        hashMapAlarm.put(Alarm.FRONT_DOOR, true);
        hashMapAlarm.put(Alarm.GARAGE_ALARM, true);
        hashMapAlarm.put(Alarm.KITCHEN_ALARM, false);
        hashMapAlarm.put(Alarm.DINING_ROOM, true);
        hashMapAlarm.put(Alarm.GUEST_ROOM, false);

        hashMapDoor = new LinkedHashMap<>();
        hashMapDoor.put(Door.FRONT_DOOR, true);
        hashMapDoor.put(Door.GARAGE, false);

        arrayListCamera = new ArrayList<>();
        arrayListCamera.add(new CameraItem("Back door cam", R.drawable.back_diningroom));
        arrayListCamera.add(new CameraItem("Balcony cam", R.drawable.back_bedroom));
        arrayListCamera.add(new CameraItem("Closet cam", R.drawable.back_livingroom));
    }

    /**
     * ------------------------------------------ALARM----------------------------------------------
     */
    public enum Alarm {
        FRONT_DOOR("FRONT DOOR"),
        GARAGE_ALARM("GARAGE ALARM"),
        KITCHEN_ALARM("KITCHEN ALARM"),
        DINING_ROOM("DINING ROOM"),
        GUEST_ROOM("GUEST ROOM"),;

        public String alarm;

        private Alarm(String h) {
            alarm = h;
        }
    }

    public Alarm getAlarmKey(int index) {
        return (Alarm) hashMapAlarm.keySet().toArray()[index];
    }

    public void toggleAlarmStatus(Alarm alarm) {
        hashMapAlarm.put(alarm, !hashMapAlarm.get(alarm));
    }

    /**
     * --------------------------------------------DOOR LOCK----------------------------------------
     */
    public enum Door {
        FRONT_DOOR("IGLOOHOME LATCH"),
        GARAGE("IGLOOHOME DEADBOLT"),;

        public String door;

        private Door(String h) {
            door = h;
        }
    }

    public Door getDoorKey(int index) {
        return (Door) hashMapDoor.keySet().toArray()[index];
    }

    public void toggleDoorStatus(Door door) {
        hashMapDoor.put(door, !hashMapDoor.get(door));
    }

    /**
     * ---------------------------------------Camera------------------------------------------------
     */
    public class CameraItem {
        public String name;
        public int resourceId;

        public CameraItem(String name, int resourceId) {
            this.name = name;
            this.resourceId = resourceId;
        }
    }

}
