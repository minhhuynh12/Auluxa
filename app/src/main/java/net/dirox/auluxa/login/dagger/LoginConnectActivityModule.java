package net.dirox.auluxa.login.dagger;

import net.dirox.auluxa.login.LoginConnectActivityContract;
import net.dirox.auluxa.login.LoginConnectActivityPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by YEN_MINH on 6/8/2017 3:48 AM.
 * Code and Life ( ^ .,,. ^ )
 * ---------------------------------------------
 */
@Module
public class LoginConnectActivityModule {
    private final LoginConnectActivityContract.View mView;

    public LoginConnectActivityModule(LoginConnectActivityContract.View mView) {
        this.mView = mView;
    }

    @Provides
    LoginConnectActivityContract.View view() {
        return mView;
    }

    @Provides
    LoginConnectActivityContract.Presenter presenter(LoginConnectActivityContract.View view) {
        return new LoginConnectActivityPresenter(view);
    }
}
