package net.dirox.auluxa.data.sample;

/**
 * Created by DoanThiPhuongHuyen on 07/08/2017.
 */

public class CountryItem {
    private String Name;
    private int ColorText;

    public CountryItem(String name, int colorText) {
        Name = name;
        this.ColorText = colorText;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getColorText() {
        return ColorText;
    }

    public void setColorText(int colorText) {
        ColorText = colorText;
    }
}
