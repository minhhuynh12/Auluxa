package net.dirox.auluxa.energy.dagger;

import net.dirox.auluxa.energy.FragmentEnergyElectricContract;
import net.dirox.auluxa.energy.FragmentEnergyElectricPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by MINH NGUYEN on 6/16/2017.
 */
@Module
public class FragmentEnergyElectricModule {

    private final FragmentEnergyElectricContract.View mView;

    public FragmentEnergyElectricModule(FragmentEnergyElectricContract.View mView) {
        this.mView = mView;
    }

    @Provides
    public FragmentEnergyElectricContract.View view() {
        return mView;
    }

    @Provides
    public FragmentEnergyElectricContract.Presenter presenter(FragmentEnergyElectricContract.View view) {
        return new FragmentEnergyElectricPresenter(view);
    }

}
