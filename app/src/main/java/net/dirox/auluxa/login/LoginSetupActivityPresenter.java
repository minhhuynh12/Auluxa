package net.dirox.auluxa.login;

import android.util.Log;

import javax.inject.Inject;

/**
 * Created by MINH NGUYEN on 6/6/2017.
 */

public class LoginSetupActivityPresenter implements LoginSetupActivityContract.Presenter {

    private final LoginSetupActivityContract.View mView;

    @Inject
    public LoginSetupActivityPresenter(LoginSetupActivityContract.View mView) {
        this.mView = mView;
    }

    @Override
    public void start() {

    }

    @Override
    public void stop() {

    }

    @Override
    public void nextCall() {
        mView.showNextCall();

    }

    @Override
    public void backCall() {
        mView.showBackCall();
    }


}
