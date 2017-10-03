package net.dirox.auluxa.data.sample;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by an on 6/26/2017.
 */

public class AudioDeviceItem {

    @SerializedName("id")
    public int id;
    @SerializedName("name")
    public String name;
    @SerializedName("pin")
    public String pin;
    @SerializedName("group")
    public String group;

    public AudioDeviceItem(int id, String name, String pin) {
        this.id = id;
        this.name = name;
        this.pin = pin;
        this.group = null;
    }

    public AudioDeviceItem() {
    }
}
