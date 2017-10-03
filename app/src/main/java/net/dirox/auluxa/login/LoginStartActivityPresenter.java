package net.dirox.auluxa.login;

import javax.inject.Inject;

/**
 * Created by MINH NGUYEN on 6/6/2017.
 */

public class LoginStartActivityPresenter implements LoginStartActivityContract.Presenter {
    private final LoginStartActivityContract.View mView;
    private boolean isSetup = false;

    @Inject
    public LoginStartActivityPresenter(LoginStartActivityContract.View mView) {
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
        if (!isSetup) {
            mView.showDialogAlertSetup();
        }
    }

    @Override
    public void setupButtonClicked() {
        mView.showLoginActivitySetup();
    }


}
