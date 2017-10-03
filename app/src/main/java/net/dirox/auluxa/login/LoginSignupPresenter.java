package net.dirox.auluxa.login;

import javax.inject.Inject;

/**
 * Created by minh on 14/06/2017.
 */

public class LoginSignupPresenter implements LoginSignupContract.Presenter {
    private final LoginSignupContract.View mView;
    private boolean isSetup = false;

    @Inject
    public LoginSignupPresenter(LoginSignupContract.View mView){
        this.mView = mView;
    }

    @Override
    public void signupButtonClick() {
        mView.showSignupSuccess();
    }

    @Override
    public void backButtonClick() {
        mView.showSignupBack();
    }


    @Override
    public void start() {

    }

    @Override
    public void stop() {

    }
}
