package net.dirox.auluxa.main;

import android.view.View;

import net.dirox.auluxa.R;

import javax.inject.Inject;

/**
 * Created by MINH NGUYEN on 6/8/2017.
 */

public class MainActivityPresenter implements MainActivityContract.Presenter {

    private final MainActivityContract.View mView;

    @Inject
    public MainActivityPresenter(MainActivityContract.View mView) {
        this.mView = mView;
    }

    @Override
    public void start() {

    }

    @Override
    public void stop() {

    }

    @Override
    public void onToolbarMenuClick(View view) {
        switch (view.getId()) {
            case R.id.main_menu_button_toggle:
                mView.toggleNavigationViewClick();
                break;
            case R.id.main_menu_button_home:
                mView.showHomeToolbarMenuClick();
                break;
            case R.id.main_menu_button_setting:
                mView.showDropDownToolbarMenuClick();
                break;
            case R.id.main_menu_button_security:
                mView.showSecurityToolbarMenuClick();
                break;
            case R.id.main_menu_setting_dropdown_notify_item:
                mView.showNotifyFragment();
                break;
            case R.id.main_menu_setting_dropdown_logout_item:
                mView.showLogOutAlert();
                break;
            case R.id.main_menu_setting_dropdown_energy_item:
                mView.showEnergySystemFragment();
                break;
            case R.id.main_menu_setting_dropdown_gate_way_item:
                mView.showGateWayFragment();
                break;
            case R.id.main_menu_setting_dropdown_setting_item:
                mView.showSettingFragment();
                break;
        }
    }

    @Override
    public void userLogOut() {
        mView.showLogInActivity();
    }
}
