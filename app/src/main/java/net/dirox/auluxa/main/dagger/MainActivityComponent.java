package net.dirox.auluxa.main.dagger;

import net.dirox.auluxa.common.dagger.ActivityComponent;
import net.dirox.auluxa.common.dagger.SessionActivity;
import net.dirox.auluxa.login.dagger.LoginActivityModule;
import net.dirox.auluxa.main.view.MainActivity;

import dagger.Component;

/**
 * Created by MINH NGUYEN on 6/8/2017.
 */
@SessionActivity
@Component(dependencies = ActivityComponent.class, modules = MainActivityModule.class)
public interface MainActivityComponent {

    void inject(MainActivity activity);
}
