package net.dirox.auluxa.data.sample;

import com.google.gson.annotations.SerializedName;
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;
import com.thoughtbot.expandablerecyclerview.models.ExpandableListPosition;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hoang Giang on 7/6/2017.
 */

public class AudioGroupDeviceItemData {
    @SerializedName("items")
    public ArrayList<AudioDeviceItem> items;
    @SerializedName("title")
    public String title;
}
