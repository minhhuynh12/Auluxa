package net.dirox.auluxa.login.dagger;

import net.dirox.auluxa.login.LoginInputPassActivityContract;
import net.dirox.auluxa.login.LoginInputPassPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by YEN_MINH on 6/8/2017 2:29 AM.
 * Code and Life ( ^ .,,. ^ )
 * ---------------------------------------------
 */
@Module
public class LoginInputPassActivityModule {
    private final LoginInputPassActivityContract.View mView;

    public LoginInputPassActivityModule(LoginInputPassActivityContract.View mView) {
        this.mView = mView;
    }

    @Provides
    LoginInputPassActivityContract.View view() {
        return mView;
    }

    @Provides
    LoginInputPassActivityContract.Presenter presenter(LoginInputPassActivityContract.View view) {
        return new LoginInputPassPresenter(view);
    }
}
