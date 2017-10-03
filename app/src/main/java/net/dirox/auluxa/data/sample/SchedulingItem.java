package net.dirox.auluxa.data.sample;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by trungnq on 12/06/2017.
 */

public class SchedulingItem implements Parcelable {
    public int id;
    public String titleSchedule;

    public SchedulingItem(int id, String titleSchedule) {
        this.id = id;
        this.titleSchedule = titleSchedule;
    }

    protected SchedulingItem(Parcel in) {
        id = in.readInt();
        titleSchedule = in.readString();
    }

    public static final Creator<SchedulingItem> CREATOR = new Creator<SchedulingItem>() {
        @Override
        public SchedulingItem createFromParcel(Parcel in) {
            return new SchedulingItem(in);
        }

        @Override
        public SchedulingItem[] newArray(int size) {
            return new SchedulingItem[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitleSchedule() {
        return titleSchedule;
    }

    public void setTitleSchedule(String titleSchedule) {
        this.titleSchedule = titleSchedule;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(titleSchedule);
    }
}
