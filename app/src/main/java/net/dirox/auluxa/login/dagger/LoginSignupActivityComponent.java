package net.dirox.auluxa.login.dagger;

import net.dirox.auluxa.common.dagger.ActivityComponent;
import net.dirox.auluxa.common.dagger.SessionActivity;
import net.dirox.auluxa.login.LoginSignupContract;
import net.dirox.auluxa.login.view.LoginSignupActivity;

import dagger.Component;

/**
 * Created by minh on 14/06/2017.
 */
@SessionActivity
@Component(dependencies = ActivityComponent.class, modules = LoginSignupActivityModule.class)

public interface LoginSignupActivityComponent {
    void inject(LoginSignupActivity activity);
}
