package net.dirox.auluxa.data.sample;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import java.util.Date;
import java.util.Random;

/**
 * Created by trungnq on 12/06/2017.
 */

public class ClimateItem implements Parcelable {

    public int roomId;
    public int id;
    public String titleClimate;
    public boolean isOn;
    public int temp, humidity;
    public SysMode sysMode;
    public FanMode fanMode;
    public StartStop startStop;
    public Date sleepTime;

    public ClimateItem(int roomId, int id, String titleClimate) {
        this.roomId = roomId;
        this.id = id;
        this.titleClimate = titleClimate;

        Random rd = new Random();
        this.isOn = rd.nextBoolean();
        temp = rd.nextInt(39) + 1;
        humidity = rd.nextInt(45) + 50;
        sysMode = SysMode.HEAT;
        fanMode = FanMode.LOW;
        startStop = StartStop.Start;
    }

    public String toggleStartStop() {
        if (startStop == StartStop.Stop) {
            startStop = StartStop.Start;
        } else {
            startStop = StartStop.Stop;
        }

        return startStop.toString();
    }

    public enum SysMode {
        HEAT("HEAT"), AUTO("AUTO"), COOL("COOL");

        public String hexCode;

        SysMode(String h) {
            hexCode = h;
        }
    }

    public enum FanMode {
        HIGH("HIGH"), MED("MED"), LOW("LOW");

        public String hexCode;

        FanMode(String h) {
            hexCode = h;
        }
    }

    public enum StartStop {
        Start("START"), Stop("STOP");

        public String hexCode;

        StartStop(String h) {
            hexCode = h;
        }
    }

    /**
     * Save sleep time for air conditioner of climate
     */
    public void saveAirConditionerSleepTime(int sleepAfterMinutes) {
        if (sleepAfterMinutes <= 0) {
            return;
        }
        Log.i("saveAirConditionerSleepTime", sleepAfterMinutes + "");
        sleepTime = new Date(System.currentTimeMillis() + (sleepAfterMinutes * 60 * 1000));
        Log.i("sleepTime", sleepTime + "");
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        Log.i("writeToParcel sleepTime", this.sleepTime + "");
        dest.writeInt(this.roomId);
        dest.writeInt(this.id);
        dest.writeString(this.titleClimate);
        dest.writeByte(this.isOn ? (byte) 1 : (byte) 0);
        dest.writeInt(this.temp);
        dest.writeInt(this.humidity);
        dest.writeInt(this.sysMode == null ? -1 : this.sysMode.ordinal());
        dest.writeInt(this.fanMode == null ? -1 : this.fanMode.ordinal());
        dest.writeInt(this.startStop == null ? -1 : this.startStop.ordinal());
        dest.writeLong(this.sleepTime != null ? this.sleepTime.getTime() : -1);
    }

    protected ClimateItem(Parcel in) {
        this.roomId = in.readInt();
        this.id = in.readInt();
        this.titleClimate = in.readString();
        this.isOn = in.readByte() != 0;
        this.temp = in.readInt();
        this.humidity = in.readInt();
        int tmpSysMode = in.readInt();
        this.sysMode = tmpSysMode == -1 ? null : SysMode.values()[tmpSysMode];
        int tmpFanMode = in.readInt();
        this.fanMode = tmpFanMode == -1 ? null : FanMode.values()[tmpFanMode];
        int tmpStartStop = in.readInt();
        this.startStop = tmpStartStop == -1 ? null : StartStop.values()[tmpStartStop];
        long tmpSleepTime = in.readLong();
        this.sleepTime = tmpSleepTime == -1 ? null : new Date(tmpSleepTime);
    }

    public static final Creator<ClimateItem> CREATOR = new Creator<ClimateItem>() {
        @Override
        public ClimateItem createFromParcel(Parcel source) {
            return new ClimateItem(source);
        }

        @Override
        public ClimateItem[] newArray(int size) {
            return new ClimateItem[size];
        }
    };
}
