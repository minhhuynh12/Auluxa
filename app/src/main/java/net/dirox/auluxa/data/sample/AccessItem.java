package net.dirox.auluxa.data.sample;

/**
 * Created by trungnq on 14/06/2017.
 */

public class AccessItem {
    private String localAccessName;
    private String localAccessContent;

    public AccessItem(String localAccessName, String localAccessContent) {
        this.localAccessName = localAccessName;
        this.localAccessContent = localAccessContent;
    }

    public String getLocalAccessName() {
        return localAccessName;
    }

    public void setLocalAccessName(String localAccessName) {
        this.localAccessName = localAccessName;
    }

    public String getLocalAccessContent() {
        return localAccessContent;
    }

    public void setLocalAccessContent(String localAccessContent) {
        this.localAccessContent = localAccessContent;
    }
}
