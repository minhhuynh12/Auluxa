package net.dirox.auluxa.energy;

import android.view.View;
import android.widget.TextView;

import net.dirox.auluxa.R;

import javax.inject.Inject;

/**
 * Created by YEN_MINH on 6/16/2017 3:49 AM.
 * Code and Life ( ^ .,,. ^ )
 * ---------------------------------------------
 */

public class FragmentEnergyElectricPresenter implements FragmentEnergyElectricContract.Presenter {

    private final FragmentEnergyElectricContract.View mView;

    @Inject
    public FragmentEnergyElectricPresenter(FragmentEnergyElectricContract.View mView) {
        this.mView = mView;
    }

    @Override
    public void start() {

    }

    @Override
    public void stop() {

    }


    @Override
    public void openDropDownMenu(View view) {
        mView.showDropDownMenu(view.getId());
        /*if (view instanceof TextView) {
            switch (((TextView) view).getText().toString()) {
                case "HOME":
                    mView.showDropDownMenu(R.id.fragment_energy_electric_home_sub);
                    break;
                case "GAME ROOM":
                    mView.showDropDownMenu(R.id.fragment_energy_electric_game_sub);
                    break;
                case "LIVING ROOM":
                    mView.showDropDownMenu(R.id.fragment_energy_electric_living_sub);
                    break;
                case "DINNING ROOM":
                    mView.showDropDownMenu(R.id.fragment_energy_electric_dinning_sub);
                    break;
            }
        }*/

    }

    @Override
    public void collapseDropDownMenu(View view) {
        mView.collapseDropDownMenu( view);
    }

    @Override
    public void timeButtonClick(View view) {
        mView.showTimeButtonClick(view.getId());
    }
}
