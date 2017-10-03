package net.dirox.auluxa.login.dagger;

import net.dirox.auluxa.login.LoginStartActivityContract;
import net.dirox.auluxa.login.LoginStartActivityPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by MINH NGUYEN on 6/6/2017.
 */
@Module
public class LoginStartActivityModule {
    private final LoginStartActivityContract.View mView;

    public LoginStartActivityModule(LoginStartActivityContract.View mView) {
        this.mView = mView;
    }

    @Provides
    LoginStartActivityContract.View view() {
        return mView;
    }

    @Provides
    LoginStartActivityContract.Presenter presenter(LoginStartActivityContract.View view) {
        return new LoginStartActivityPresenter(view);
    }
}
