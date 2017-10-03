package net.dirox.auluxa.main;


import net.dirox.auluxa.common.mvp.BasePresenter;
import net.dirox.auluxa.common.mvp.BaseView;

/**
 * Created by YEN_MINH on 6/8/2017 3:03 AM.
 * Code and Life ( ^ .,,. ^ )
 * ---------------------------------------------
 */

public interface MainActivityContract {

    interface View extends BaseView {

        void toggleNavigationViewClick();

        void showHomeToolbarMenuClick();

        void showDropDownToolbarMenuClick();

        void showSecurityToolbarMenuClick();

        void showNotifyFragment();

        void showLogOutAlert();

        void showGateWayFragment();

        void showEnergySystemFragment();

        void showLogInActivity();

        void showSettingFragment();

    }

    interface Presenter extends BasePresenter {
        void onToolbarMenuClick(android.view.View view);

        void userLogOut();
    }

}
