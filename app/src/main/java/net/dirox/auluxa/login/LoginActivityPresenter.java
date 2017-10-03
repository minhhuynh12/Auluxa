package net.dirox.auluxa.login;

import javax.inject.Inject;

/**
 * Created by YEN_MINH on 6/8/2017 2:45 AM.
 * Code and Life ( ^ .,,. ^ )
 * ---------------------------------------------
 */

public class LoginActivityPresenter implements LoginActivityContract.Presenter {

    private final LoginActivityContract.View mView;

    @Inject
    public LoginActivityPresenter(LoginActivityContract.View mView) {
        this.mView = mView;
    }

    @Override
    public void start() {

    }

    @Override
    public void stop() {

    }

    @Override
    public void loginButtonClick() {
        mView.showLoginSuccess();
    }

    @Override
    public void tabLoginClick() {
        mView.showTabLoginClick();
    }

    /*@Override
    public void setSignup() {
        mView.showSignupSuccess();
    }*/

    @Override
    public void tabSetupGatewayClick() {
        mView.showTabSetupGatewayClick();
    }

    @Override
    public void addGatewayClick() {
        mView.showAddGatewayList();
    }

    @Override
    public void signupButtonClick() {
        mView.goToSignupActivity();
    }

    @Override
    public void setForgetPassword() {
        mView.showSetForgetPassword();
    }
}
