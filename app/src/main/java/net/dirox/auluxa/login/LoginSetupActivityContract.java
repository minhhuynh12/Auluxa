package net.dirox.auluxa.login;

import net.dirox.auluxa.common.mvp.BasePresenter;
import net.dirox.auluxa.common.mvp.BaseView;

/**
 * Created by MINH NGUYEN on 6/6/2017.
 */

public interface LoginSetupActivityContract {

    interface View extends BaseView {

        void showNextCall();

        void showBackCall();

        void showSignupSuccess();


    }

    interface Presenter extends BasePresenter {

        void nextCall();

        void backCall();
    }
}
