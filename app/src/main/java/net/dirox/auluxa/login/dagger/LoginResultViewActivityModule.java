package net.dirox.auluxa.login.dagger;

import net.dirox.auluxa.login.LoginResultViewContract;
import net.dirox.auluxa.login.LoginResultViewPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by YEN_MINH on 6/8/2017 2:30 AM.
 * Code and Life ( ^ .,,. ^ )
 * ---------------------------------------------
 */
@Module
public class LoginResultViewActivityModule {

    private final LoginResultViewContract.View mView;

    public LoginResultViewActivityModule(LoginResultViewContract.View mView) {
        this.mView = mView;
    }

    @Provides
    LoginResultViewContract.View view() {
        return mView;
    }

    @Provides
    LoginResultViewContract.Presenter presenter(LoginResultViewContract.View view) {
        return new LoginResultViewPresenter(view);
    }
}
