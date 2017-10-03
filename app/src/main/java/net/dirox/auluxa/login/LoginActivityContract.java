package net.dirox.auluxa.login;

import net.dirox.auluxa.common.mvp.BasePresenter;
import net.dirox.auluxa.common.mvp.BaseView;

/**
 * Created by YEN_MINH on 6/8/2017 2:35 AM.
 * Code and Life ( ^ .,,. ^ )
 * ---------------------------------------------
 */

public interface LoginActivityContract {
    interface View extends BaseView {
        void showLoginSuccess();

        void showLoginFailed();

        void showTabLoginClick();

        void showTabSetupGatewayClick();

        void showAddGatewayList();

        void goToSignupActivity();

        void showSetForgetPassword();

    }

    interface Presenter extends BasePresenter {

        void loginButtonClick();

        void tabSetupGatewayClick();

        void tabLoginClick();

        void addGatewayClick();

        void signupButtonClick();

        void setForgetPassword();

    }
}
