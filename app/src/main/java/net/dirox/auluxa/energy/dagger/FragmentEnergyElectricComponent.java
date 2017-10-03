package net.dirox.auluxa.energy.dagger;

import net.dirox.auluxa.common.dagger.ActivityComponent;
import net.dirox.auluxa.common.dagger.PerFragment;
import net.dirox.auluxa.energy.view.FragmentEnergyElectric;

import dagger.Component;

/**
 * Created by MINH NGUYEN on 6/16/2017.
 */
@PerFragment
@Component(dependencies = ActivityComponent.class, modules = FragmentEnergyElectricModule.class)
public interface FragmentEnergyElectricComponent {
    void inject(FragmentEnergyElectric fragment);
}
