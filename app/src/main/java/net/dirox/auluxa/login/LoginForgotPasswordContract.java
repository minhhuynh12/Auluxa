package net.dirox.auluxa.login;

import net.dirox.auluxa.common.mvp.BasePresenter;
import net.dirox.auluxa.common.mvp.BaseView;

/**
 * Created by MyPC on 16/06/2017.
 */

public interface LoginForgotPasswordContract {
    interface View extends BaseView {
        void showSetFinnishButtonCanel();
    }
    interface Presenter extends BasePresenter{
         void setFinishButtonCanel();
    }
}
