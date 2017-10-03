package net.dirox.auluxa.login;

import javax.inject.Inject;

/**
 * Created by MyPC on 16/06/2017.
 */

public class LoginForgotPasswordPresenter implements LoginForgotPasswordContract.Presenter {
    private LoginForgotPasswordContract.View mView;

    @Inject
    public LoginForgotPasswordPresenter(LoginForgotPasswordContract.View mView) {
        this.mView = mView;
    }

    @Override
    public void setFinishButtonCanel() {
        mView.showSetFinnishButtonCanel();
    }

    @Override
    public void start() {

    }

    @Override
    public void stop() {

    }
}
