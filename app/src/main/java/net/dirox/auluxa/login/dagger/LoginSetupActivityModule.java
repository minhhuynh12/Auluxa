package net.dirox.auluxa.login.dagger;

import net.dirox.auluxa.login.LoginSetupActivityContract;
import net.dirox.auluxa.login.LoginSetupActivityPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by MINH NGUYEN on 6/6/2017.
 */
@Module
public class LoginSetupActivityModule {
    private final LoginSetupActivityContract.View mView;

    public LoginSetupActivityModule(LoginSetupActivityContract.View mView) {
        this.mView = mView;
    }

    @Provides
    LoginSetupActivityContract.View view() {
        return mView;
    }

    @Provides
    LoginSetupActivityContract.Presenter presenter(LoginSetupActivityContract.View view) {
        return new LoginSetupActivityPresenter(view);
    }
}

