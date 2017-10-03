package net.dirox.auluxa.data.sample;

import java.io.Serializable;

/**
 * Created by trungnq on 12/06/2017.
 */

public class ScenesItem implements Serializable {
    public int id;
    public String titleScenes;

    public ScenesItem() {
        this.id = -1;
        this.titleScenes = "";
    }

    public ScenesItem(int id, String titleScenes) {
        this.id = id;
        this.titleScenes = titleScenes;
    }

}
