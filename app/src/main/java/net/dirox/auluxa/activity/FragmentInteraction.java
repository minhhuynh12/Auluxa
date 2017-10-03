package net.dirox.auluxa.activity;

import android.os.Bundle;

import net.dirox.auluxa.extras.Enumes;

/**
 * Created by an on 6/9/2017.
 */

public interface FragmentInteraction {
    void openFragment(Enumes.FragmentType fragmentType, Bundle bundle, Enumes.Direction directionIn);

    void onFragmentVisible(boolean isVisible);
}
