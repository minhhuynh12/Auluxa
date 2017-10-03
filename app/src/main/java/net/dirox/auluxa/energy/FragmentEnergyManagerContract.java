package net.dirox.auluxa.energy;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import net.dirox.auluxa.common.mvp.BasePresenter;
import net.dirox.auluxa.common.mvp.BaseView;

/**
 * Created by MINH NGUYEN on 6/13/2017.
 */

public interface FragmentEnergyManagerContract {

    interface View extends BaseView {

//        void showButtonBackFromEditTarger(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState);

        void showBackPressed();


    }

    interface Presenter extends BasePresenter {
        void backButtonPressed();
    }
}
