package net.dirox.auluxa.login.dagger;

import net.dirox.auluxa.common.dagger.ActivityComponent;
import net.dirox.auluxa.common.dagger.SessionActivity;
import net.dirox.auluxa.login.view.LoginStartActivity;

import dagger.Component;

/**
 * Created by MINH NGUYEN on 6/6/2017.
 */
@SessionActivity
@Component(dependencies = ActivityComponent.class, modules = LoginStartActivityModule.class)
public interface LoginStartActivityComponent {

    void inject(LoginStartActivity activity);
}
