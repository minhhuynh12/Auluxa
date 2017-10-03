package net.dirox.auluxa.data.sample;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by trungnq on 23/06/2017.
 */

public class RoomImageItem implements Parcelable {
    @SerializedName("id")
    public int id;
    @SerializedName("roomName")
    public String roomName;
    @SerializedName("roomImagePath")
    public String roomImagePath;
    @SerializedName("isSelected")
    public boolean isSelected;
    @SerializedName("climateItems")
    public ArrayList<ClimateItem> climateItems;

    public RoomImageItem(int id, String roomName, String roomImagePath) {
        this.id = id;
        this.roomName = roomName;
        this.roomImagePath = roomImagePath;
        isSelected = false;

        climateItems = new ArrayList<>();
        climateItems.add(new ClimateItem(this.id, 1, "Airconditioner 1"));
        climateItems.add(new ClimateItem(this.id, 2, "Airconditioner 2"));
        climateItems.add(new ClimateItem(this.id, 3, "Airconditioner 3"));
        climateItems.add(new ClimateItem(this.id, 4, "Airconditioner 4"));
        climateItems.add(new ClimateItem(this.id, 5, "Airconditioner 5"));
    }

    /**
     * Save sleep time for air conditioner of climate
     */
    public void saveAirConditionerSleepTime(ClimateItem climateItem, int sleepAfterMinutes) {
        if (climateItem == null) {
            return;
        }
        for (int i = 0; i < climateItems.size(); i++) {
            if (climateItems.get(i).id == climateItem.id) {
                climateItems.get(i).saveAirConditionerSleepTime(sleepAfterMinutes);
            }
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.roomName);
        dest.writeString(this.roomImagePath);
        dest.writeTypedList(this.climateItems);
        dest.writeByte(this.isSelected ? (byte) 1 : (byte) 0);
    }

    protected RoomImageItem(Parcel in) {
        this.id = in.readInt();
        this.roomName = in.readString();
        this.roomImagePath = in.readString();
        this.climateItems = in.createTypedArrayList(ClimateItem.CREATOR);
        this.isSelected = in.readByte() != 0;
    }

    @Override
    public String toString() {
        return "id:" + id + ", " + "roomName:" + roomName + ", " + "roomImagePath:" + roomImagePath;
    }

    public static final Creator<RoomImageItem> CREATOR = new Creator<RoomImageItem>() {
        @Override
        public RoomImageItem createFromParcel(Parcel in) {
            return new RoomImageItem(in);
        }

        @Override
        public RoomImageItem[] newArray(int size) {
            return new RoomImageItem[size];
        }
    };

}
