package net.dirox.auluxa.login;

import net.dirox.auluxa.common.mvp.BasePresenter;
import net.dirox.auluxa.common.mvp.BaseView;

/**
 * Created by minh on 14/06/2017.
 */

public interface LoginSignupContract {
    interface View extends BaseView{
        void showSignupSuccess();

        void showSignupBack();

    }
    interface Presenter extends BasePresenter{
        void signupButtonClick();

        void backButtonClick();
    }
}
