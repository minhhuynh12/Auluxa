package net.dirox.auluxa.energy.view;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.TextView;
import android.widget.ToggleButton;

import net.dirox.auluxa.R;
import net.dirox.auluxa.common.fragment.BaseFragment;
import net.dirox.auluxa.databinding.FragmentEnergyElectricBinding;
import net.dirox.auluxa.energy.FragmentEnergyElectricContract;
import net.dirox.auluxa.energy.dagger.DaggerFragmentEnergyElectricComponent;
import net.dirox.auluxa.energy.dagger.FragmentEnergyElectricComponent;
import net.dirox.auluxa.energy.dagger.FragmentEnergyElectricModule;

import javax.inject.Inject;

/**
 * Created by YEN_MINH on 6/16/2017 1:47 AM.
 * Code and Life ( ^ .,,. ^ )
 * ---------------------------------------------
 */

public class FragmentEnergyElectric extends BaseFragment implements FragmentEnergyElectricContract.View {

    private FragmentEnergyElectricBinding mBinding;

    private FragmentEnergyElectricComponent mComponent;

    @Inject
    FragmentEnergyElectricContract.Presenter presenter;

    private int[] mButtonsTime = {R.id.fragment_energy_electric_day_btn, R.id.fragment_energy_electric_week_btn,
            R.id.fragment_energy_electric_month_btn, R.id.fragment_energy_electric_year_btn};

    private FragmentEnergyElectricComponent component() {
        if (mComponent == null) {
            mComponent = DaggerFragmentEnergyElectricComponent.builder()
                    .activityComponent(abstractActivityComponent())
                    .fragmentEnergyElectricModule(new FragmentEnergyElectricModule(this))
                    .build();
        }
        return mComponent;
    }


    public static FragmentEnergyElectric newInstance() {
        Bundle args = new Bundle();
        FragmentEnergyElectric fragment = new FragmentEnergyElectric();
        fragment.setArguments(args);
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle
            savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_energy_electric, container, false);
        component().inject(this);
        mBinding.setPresenter(presenter);
        mBinding.fragmentEnergyElectricDayBtn.setChecked(true);
        return mBinding.getRoot();
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
    }


    @Override
    public void showDropDownMenu(int idFocus) {
        Animation slideDown = AnimationUtils.loadAnimation(getActivity().getApplicationContext(), R.anim.slide_down);
        slideDown.setInterpolator(new LinearInterpolator());
        mBinding.fragmentEnergyElectricDropdownMenu.startAnimation(slideDown);
        mBinding.fragmentEnergyElectricDropdownMenu.setVisibility(View.VISIBLE);
    }

    @Override
    public void collapseDropDownMenu(View view) {
        mBinding.fragmentEnergyElectricDinningSub.setTextColor(ContextCompat.getColor(getActivity(), R.color.BgLight));
        mBinding.fragmentEnergyElectricHomeSub.setTextColor(ContextCompat.getColor(getActivity(), R.color.BgLight));
        mBinding.fragmentEnergyElectricLivingSub.setTextColor(ContextCompat.getColor(getActivity(), R.color.BgLight));
        mBinding.fragmentEnergyElectricGameSub.setTextColor(ContextCompat.getColor(getActivity(), R.color.BgLight));
        ((TextView) view).setTextColor(ContextCompat.getColor(getActivity(), R.color.auluxaGreen));
        Animation slideUp = AnimationUtils.loadAnimation(getActivity().getApplicationContext(), R.anim.slide_up);
        slideUp.setInterpolator(new LinearInterpolator());
        mBinding.fragmentEnergyElectricDropdownMenu.startAnimation(slideUp);
        mBinding.fragmentEnergyElectricDropdownMenu.setVisibility(View.GONE);
        mBinding.fragmentEnergyElectricHome.setText(((TextView) view).getText());
    }

    @Override
    public void showTimeButtonClick(int id) {
        for (int i : mButtonsTime) {
            if (id != i) {
                ((ToggleButton) mBinding.getRoot().findViewById(i)).setChecked(false);
            } else {
                ((ToggleButton) mBinding.getRoot().findViewById(i)).setChecked(true);
            }

        }
    }
}
