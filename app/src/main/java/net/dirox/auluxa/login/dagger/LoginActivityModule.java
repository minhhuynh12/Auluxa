package net.dirox.auluxa.login.dagger;

import net.dirox.auluxa.login.LoginActivityContract;
import net.dirox.auluxa.login.LoginActivityPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by YEN_MINH on 6/8/2017 2:30 AM.
 * Code and Life ( ^ .,,. ^ )
 * ---------------------------------------------
 */
@Module
public class LoginActivityModule {
    private final LoginActivityContract.View mView;

    public LoginActivityModule(LoginActivityContract.View mView) {
        this.mView = mView;
    }

    @Provides
    LoginActivityContract.View view() {
        return mView;
    }

    @Provides
    LoginActivityContract.Presenter presenter(LoginActivityContract.View view) {
        return new LoginActivityPresenter(view);
    }
}
