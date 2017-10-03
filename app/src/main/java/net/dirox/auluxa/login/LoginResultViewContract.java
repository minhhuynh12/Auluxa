package net.dirox.auluxa.login;

import net.dirox.auluxa.common.mvp.BasePresenter;
import net.dirox.auluxa.common.mvp.BaseView;

/**
 * Created by YEN_MINH on 6/8/2017 2:22 AM.
 * Code and Life ( ^ .,,. ^ )
 * ---------------------------------------------
 */

public interface LoginResultViewContract {

    interface View extends BaseView {
        void showSigupActviity();

        void showLoginActivity();
    }

    interface Presenter extends BasePresenter {
        void loginButtonClicked();

        void signUpButtonClicked();
    }
}
