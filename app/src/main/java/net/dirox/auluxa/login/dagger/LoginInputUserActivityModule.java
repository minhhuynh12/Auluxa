package net.dirox.auluxa.login.dagger;

import net.dirox.auluxa.login.LoginInputUserActivityContract;
import net.dirox.auluxa.login.LoginInputUserPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by YEN_MINH on 6/8/2017 2:29 AM.
 * Code and Life ( ^ .,,. ^ )
 * ---------------------------------------------
 */
@Module
public class LoginInputUserActivityModule {
    private final LoginInputUserActivityContract.View mView;

    public LoginInputUserActivityModule(LoginInputUserActivityContract.View mView) {
        this.mView = mView;
    }

    @Provides
    LoginInputUserActivityContract.View view() {
        return mView;
    }

    @Provides
    LoginInputUserActivityContract.Presenter presenter(LoginInputUserActivityContract.View view) {
        return new LoginInputUserPresenter(view);
    }
}
