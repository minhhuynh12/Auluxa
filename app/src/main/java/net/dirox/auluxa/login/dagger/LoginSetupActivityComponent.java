package net.dirox.auluxa.login.dagger;

import net.dirox.auluxa.common.dagger.ActivityComponent;
import net.dirox.auluxa.common.dagger.SessionActivity;
import net.dirox.auluxa.login.LoginSetupActivityPresenter;
import net.dirox.auluxa.login.view.LoginSetupActivity;

import dagger.Component;

/**
 * Created by MINH NGUYEN on 6/6/2017.
 */
@SessionActivity
@Component(dependencies = ActivityComponent.class, modules = LoginSetupActivityModule.class)
public interface LoginSetupActivityComponent {
    void inject(LoginSetupActivity activity);
}
