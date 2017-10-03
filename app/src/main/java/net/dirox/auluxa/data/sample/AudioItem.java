package net.dirox.auluxa.data.sample;

/**
 * Created by MyPC on 25/06/2017.
 */

public class AudioItem {
    public String id;
    public String name;
    public int photo;
    public boolean isSelected;

    public AudioItem(String id, String name, int photo) {
        this.id = id;
        this.name = name;
        this.photo = photo;
        isSelected = false;
    }


}
