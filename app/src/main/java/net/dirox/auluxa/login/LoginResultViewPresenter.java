package net.dirox.auluxa.login;

import javax.inject.Inject;

/**
 * Created by YEN_MINH on 6/8/2017 2:22 AM.
 * Code and Life ( ^ .,,. ^ )
 * ---------------------------------------------
 */

public class LoginResultViewPresenter implements LoginResultViewContract.Presenter {
    private final LoginResultViewContract.View mView;

    @Inject
    public LoginResultViewPresenter(LoginResultViewContract.View mView) {
        this.mView = mView;
    }

    @Override
    public void start() {

    }

    @Override
    public void stop() {

    }


    @Override
    public void loginButtonClicked() {
        mView.showLoginActivity();
    }

    @Override
    public void signUpButtonClicked() {
        mView.showSigupActviity();
    }
}
