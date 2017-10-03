package net.dirox.auluxa.login.dagger;

import net.dirox.auluxa.common.dagger.ActivityComponent;
import net.dirox.auluxa.common.dagger.SessionActivity;
import net.dirox.auluxa.login.view.LoginConnectActivity;

import dagger.Component;

/**
 * Created by YEN_MINH on 6/8/2017 3:48 AM.
 * Code and Life ( ^ .,,. ^ )
 * ---------------------------------------------
 */
@SessionActivity
@Component(dependencies = ActivityComponent.class, modules = LoginConnectActivityModule.class)
public interface LoginConnectActivityComponent {
    void inject(LoginConnectActivity activity);
}
