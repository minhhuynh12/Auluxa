package net.dirox.auluxa.energy;

import android.view.View;
import android.widget.TextView;

import net.dirox.auluxa.common.mvp.BasePresenter;
import net.dirox.auluxa.common.mvp.BaseView;

/**
 * Created by YEN_MINH on 6/16/2017 3:48 AM.
 * Code and Life ( ^ .,,. ^ )
 * ---------------------------------------------
 */

public interface FragmentEnergyElectricContract {

    interface View extends BaseView {

        void showDropDownMenu(int idFocus);

        void collapseDropDownMenu(android.view.View view);

        void showTimeButtonClick(int id);

    }

    interface Presenter extends BasePresenter {

        void openDropDownMenu(android.view.View view);

        void collapseDropDownMenu(android.view.View textView);

        void timeButtonClick(android.view.View view);
    }
}
