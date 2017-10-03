package net.dirox.auluxa.activity.main;

import net.dirox.auluxa.common.mvp.BasePresenter;
import net.dirox.auluxa.common.mvp.BaseView;
//import net.dirox.auluxa.databinding.LayoutSetuploginBinding;

public interface MainActivityContract {
    interface View extends BaseView {
        void showLoginInput(boolean show);
        void showLoginSetup();
    }

    interface Presenter extends BasePresenter {
        void validateAppInfo();

        void setupButtonClicked();

//        void setBinding(LayoutSetuploginBinding layoutSetuploginBinding);
    }
}
