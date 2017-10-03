package net.dirox.auluxa.energy;

import javax.inject.Inject;

/**
 * Created by MINH NGUYEN on 6/13/2017.
 */

public class FragmentEnergyManagerPresenter implements FragmentEnergyManagerContract.Presenter {
    private final FragmentEnergyManagerContract.View mView;

    @Inject
    public FragmentEnergyManagerPresenter(FragmentEnergyManagerContract.View mView) {
        this.mView = mView;
    }

    @Override
    public void start() {

    }

    @Override
    public void stop() {

    }

    @Override
    public void backButtonPressed() {
        mView.showBackPressed();
    }
}
