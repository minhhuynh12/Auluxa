package net.dirox.auluxa.login;

import net.dirox.auluxa.common.mvp.BasePresenter;
import net.dirox.auluxa.common.mvp.BaseView;

/**
 * Created by MINH NGUYEN on 6/6/2017.
 */

public interface LoginStartActivityContract {

    interface View extends BaseView {

        void showLoginActivitySetup();

        void showLoginActivity();

        void showDialogAlertSetup();


    }

    interface Presenter extends BasePresenter {

        void loginButtonClicked();

        void setupButtonClicked();


    }
}
