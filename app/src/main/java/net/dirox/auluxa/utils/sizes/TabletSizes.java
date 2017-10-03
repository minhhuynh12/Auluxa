package net.dirox.auluxa.utils.sizes;

/**
 * Created by an on 6/13/2017.
 */

public class TabletSizes extends Sizes {

    public TabletSizes() {

        PhoneSizes pSizes = new PhoneSizes();

        mainLogo = new Size(0.5, 0.3);

        mainDrawer = new Size(0.7, 1);

        menuRoomItem = new Size(0, 0.2);
        menuRoomShade = new Size(0, 0.08);

        deviceIcon = new Size(0.09, 0.07);

        itemStandard = new Size(1, 0.07);

        headerFooterStandard = pSizes.headerFooterStandard;
        sceneTabsFooter = pSizes.sceneTabsFooter;

        itemShade = pSizes.itemShade;
    }
}

