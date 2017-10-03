package net.dirox.auluxa.data.sample;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by an on 6/8/2017.
 */

public class RoomItem {}

//public class RoomItem implements Parcelable {
//    public int id;
//    public String roomName;
//    public int resourceId;
//    public ArrayList<ClimateItem> climateItems;
//    public boolean isSelected;
//
//    public RoomItem(int id, String name, int resourceId) {
//        this.id = id;
//        roomName = name;
//        this.resourceId = resourceId;
//        isSelected = false;
//
//        climateItems = new ArrayList<>();
//        climateItems.add(new ClimateItem(this.id, 1, "Airconditioner 1"));
//        climateItems.add(new ClimateItem(this.id, 2, "Airconditioner 2"));
//        climateItems.add(new ClimateItem(this.id, 3, "Airconditioner 3"));
//        climateItems.add(new ClimateItem(this.id, 4, "Airconditioner 4"));
//        climateItems.add(new ClimateItem(this.id, 5, "Airconditioner 5"));
//    }
//
//    /**
//     * Save sleep time for air conditioner of climate
//     */
//    public void saveAirConditionerSleepTime(ClimateItem climateItem, int sleepAfterMinutes) {
//        if (climateItem == null) {
//            return;
//        }
//        for (int i = 0; i < climateItems.size(); i++) {
//            if (climateItems.get(i).id == climateItem.id) {
//                climateItems.get(i).saveAirConditionerSleepTime(sleepAfterMinutes);
//            }
//        }
//    }
//
//
//    @Override
//    public int describeContents() {
//        return 0;
//    }
//
//    @Override
//    public void writeToParcel(Parcel dest, int flags) {
//        dest.writeInt(this.id);
//        dest.writeString(this.roomName);
//        dest.writeInt(this.resourceId);
//        dest.writeTypedList(this.climateItems);
//        dest.writeByte(this.isSelected ? (byte) 1 : (byte) 0);
//    }
//
//    protected RoomItem(Parcel in) {
//        this.id = in.readInt();
//        this.roomName = in.readString();
//        this.resourceId = in.readInt();
//        this.climateItems = in.createTypedArrayList(ClimateItem.CREATOR);
//        this.isSelected = in.readByte() != 0;
//    }
//
//    public static final Creator<RoomItem> CREATOR = new Creator<RoomItem>() {
//        @Override
//        public RoomItem createFromParcel(Parcel source) {
//            return new RoomItem(source);
//        }
//
//        @Override
//        public RoomItem[] newArray(int size) {
//            return new RoomItem[size];
//        }
//    };
//}
