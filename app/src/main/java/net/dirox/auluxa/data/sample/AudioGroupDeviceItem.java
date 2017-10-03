package net.dirox.auluxa.data.sample;

import android.net.rtp.AudioGroup;

import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;
import com.thoughtbot.expandablerecyclerview.models.ExpandableListPosition;

import java.util.List;

/**
 * Created by an on 6/27/2017.
 */

public class AudioGroupDeviceItem extends ExpandableGroup {
    public final static int CHILD = ExpandableListPosition.CHILD;
    public final static int GROUP_TITLE = ExpandableListPosition.GROUP;
    public final static int GROUP_ITEM_DETAIL = 101;

    public AudioGroupDeviceItem(String title, List items) {
        super(title, items);
    }
}
