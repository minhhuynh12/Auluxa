package net.dirox.auluxa.data;

import android.content.Context;
import android.util.Log;

import com.google.gson.reflect.TypeToken;

import net.dirox.auluxa.data.sample.AudioDeviceItem;
import net.dirox.auluxa.data.sample.ClimateItem;
import net.dirox.auluxa.data.sample.RoomImageItem;
import net.dirox.auluxa.data.sample.SecurityItem;
import net.dirox.auluxa.utils.Constant;
import net.dirox.auluxa.utils.Prefs;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by an on 6/19/2017.
 */

public class GeneratorSampleData {

    public static List<AudioDeviceItem> audioDeviceItems;

    public static SecurityItem securityItem;

    public static List<RoomImageItem> roomItemArrayList;


    public static void initData(Context context) {

        Type type = new TypeToken<ArrayList<RoomImageItem>>() {
        }.getType();

        roomItemArrayList = (ArrayList<RoomImageItem>) Prefs.loadObjectPrefs(context, Constant.ROOM_IMAGE, type);

        if (roomItemArrayList == null) {
            roomItemArrayList = new ArrayList<>();
            roomItemArrayList.add(new RoomImageItem(1, "LIVING ROOM", "/data/data/"
                    + context.getApplicationContext().getPackageName()
                    + "/Images/back_livingroom.png"));
            roomItemArrayList.add(new RoomImageItem(2, "GAMING ROOM", "/data/data/"
                    + context.getApplicationContext().getPackageName()
                    + "/Images/back_livingroom.png"));
            roomItemArrayList.add(new RoomImageItem(3, "BED ROOM", "/data/data/"
                    + context.getApplicationContext().getPackageName()
                    + "/Images/back_bedroom.png"));
            roomItemArrayList.add(new RoomImageItem(4, "DINING ROOM", "/data/data/"
                    + context.getApplicationContext().getPackageName()
                    + "/Images/back_diningroom.png"));
            roomItemArrayList.add(new RoomImageItem(5, "MASTER ROOM", "/data/data/"
                    + context.getApplicationContext().getPackageName()
                    + "/Images/back_diningroom.png"));

            Prefs.saveObjectPrefs(context, Constant.ROOM_IMAGE, roomItemArrayList);

        }

        audioDeviceItems = new ArrayList<>();
        audioDeviceItems.add(new AudioDeviceItem(1, "CHROMECASTAUDIO SB", "3322"));
        audioDeviceItems.add(new AudioDeviceItem(2, "CHROMECAST 321", "3322"));
        audioDeviceItems.add(new AudioDeviceItem(3, "CHROMECASTAUDIO7621", "3322"));
        audioDeviceItems.add(new AudioDeviceItem(4, "CHROMECASTAUDIO F71", "3322"));
        audioDeviceItems.add(new AudioDeviceItem(5, "CHROMECASTAUDIO 1841PP", "3322"));
        audioDeviceItems.add(new AudioDeviceItem(6, "CHROMECASTAUDIO 89 AB", "3322"));
        audioDeviceItems.add(new AudioDeviceItem(7, "CHROMECASTAUDIO 3KKL", "3322"));

        securityItem = new SecurityItem();
    }
    public static void upLoadRoomItems(Context context){
        if(roomItemArrayList != null){
            roomItemArrayList.clear();
            Type type = new TypeToken<ArrayList<RoomImageItem>>() {
            }.getType();
            roomItemArrayList = (ArrayList<RoomImageItem>) Prefs.loadObjectPrefs(context, Constant.ROOM_IMAGE, type);
        }
    }

    /**
     * Save sleep time for air conditioner of climate
     */
    public static void saveAirConditionerSleepTime(ClimateItem climateItem, int sleepAfterMinutes) {
        if (roomItemArrayList == null) {
            return;
        } else {
            for (int i = 0; i < roomItemArrayList.size(); i++) {
                if (roomItemArrayList.get(i).id == climateItem.roomId) {
                    roomItemArrayList.get(i).saveAirConditionerSleepTime(climateItem, sleepAfterMinutes);
                }
            }
        }

    }

}
