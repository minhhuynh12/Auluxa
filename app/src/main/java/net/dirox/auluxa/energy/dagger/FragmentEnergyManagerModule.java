package net.dirox.auluxa.energy.dagger;

import net.dirox.auluxa.energy.FragmentEnergyManagerContract;
import net.dirox.auluxa.energy.FragmentEnergyManagerPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by MINH NGUYEN on 6/16/2017.
 */

@Module
public class FragmentEnergyManagerModule {
    private final FragmentEnergyManagerContract.View mView;

    public FragmentEnergyManagerModule(FragmentEnergyManagerContract.View mView) {
        this.mView = mView;
    }

    @Provides
    public FragmentEnergyManagerContract.View view() {
        return mView;
    }

    @Provides
    public FragmentEnergyManagerContract.Presenter presenter(FragmentEnergyManagerContract.View view) {
        return new FragmentEnergyManagerPresenter(view);
    }
}
