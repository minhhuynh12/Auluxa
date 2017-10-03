package net.dirox.auluxa.login.dagger;

import net.dirox.auluxa.common.dagger.ActivityComponent;
import net.dirox.auluxa.common.dagger.SessionActivity;
import net.dirox.auluxa.login.view.LoginInputUserActivity;

import dagger.Component;

/**
 * Created by YEN_MINH on 6/8/2017 2:27 AM.
 * Code and Life ( ^ .,,. ^ )
 * ---------------------------------------------
 */
@SessionActivity
@Component(dependencies = ActivityComponent.class, modules = LoginInputUserActivityModule.class)
public interface LoginInputUserActivityComponent {
    void inject(LoginInputUserActivity activity);
}
