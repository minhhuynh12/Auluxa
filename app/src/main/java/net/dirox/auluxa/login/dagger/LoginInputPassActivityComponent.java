package net.dirox.auluxa.login.dagger;

import net.dirox.auluxa.common.dagger.ActivityComponent;
import net.dirox.auluxa.common.dagger.SessionActivity;
import net.dirox.auluxa.login.view.LoginInputPassActivity;

import dagger.Component;

/**
 * Created by YEN_MINH on 6/8/2017 2:26 AM.
 * Code and Life ( ^ .,,. ^ )
 * ---------------------------------------------
 */
@SessionActivity
@Component(dependencies = ActivityComponent.class, modules = LoginInputPassActivityModule.class)
public interface LoginInputPassActivityComponent {
    void inject(LoginInputPassActivity activity);
}
