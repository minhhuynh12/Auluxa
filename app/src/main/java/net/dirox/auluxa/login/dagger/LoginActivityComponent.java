package net.dirox.auluxa.login.dagger;

import net.dirox.auluxa.common.dagger.ActivityComponent;
import net.dirox.auluxa.common.dagger.SessionActivity;
import net.dirox.auluxa.login.view.LoginActivity;

import dagger.Component;

/**
 * Created by YEN_MINH on 6/8/2017 2:28 AM.
 * Code and Life ( ^ .,,. ^ )
 * ---------------------------------------------
 */
@SessionActivity
@Component(dependencies = ActivityComponent.class, modules = LoginActivityModule.class)
public interface LoginActivityComponent {
    void inject(LoginActivity activity);
}
