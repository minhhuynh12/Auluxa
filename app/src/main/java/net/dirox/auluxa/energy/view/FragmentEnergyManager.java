package net.dirox.auluxa.energy.view;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.dirox.auluxa.R;
import net.dirox.auluxa.activity.FragmentInteraction;
import net.dirox.auluxa.common.fragment.BaseFragment;
import net.dirox.auluxa.databinding.FragmentEnergyBinding;
import net.dirox.auluxa.energy.FragmentEnergyManagerContract;
import net.dirox.auluxa.energy.dagger.DaggerFragmentEnergyManagerComponent;
import net.dirox.auluxa.energy.dagger.FragmentEnergyManagerComponent;
import net.dirox.auluxa.energy.dagger.FragmentEnergyManagerModule;
import net.dirox.auluxa.extras.Enumes;

import javax.inject.Inject;

/**
 * Created by MINH NGUYEN on 6/13/2017.
 */

public class FragmentEnergyManager extends BaseFragment implements FragmentEnergyManagerContract.View {

    private FragmentEnergyBinding mBinding;
    private FragmentEnergyManagerComponent mComponent;
    private FragmentInteraction mInteraction;

    @Inject
    FragmentEnergyManagerContract.Presenter presenter;

    private FragmentEnergyManagerComponent component() {
        if (mComponent == null) {
            mComponent = DaggerFragmentEnergyManagerComponent.builder()
                    .activityComponent(abstractActivityComponent())
                    .fragmentEnergyManagerModule(new FragmentEnergyManagerModule(this))
                    .build();
        }
        return mComponent;
    }


    public static FragmentEnergyManager newInstance() {
        Bundle args = new Bundle();
        FragmentEnergyManager fragment = new FragmentEnergyManager();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_energy, container, false);
        component().inject(this);
        inflateLayout();
        mBinding.setPresenter(presenter);
        /*mBinding.btnBackNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mInteraction.openFragment(Enumes.FragmentType.FRAGMENT_MAIN_HOME, null);
            }
        });*/

        mBinding.fragmentEnergyEditBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mInteraction.openFragment(Enumes.FragmentType.FRAGMENT_EDIT_TARGET, null, Enumes.Direction.FLIP_IN);
            }
        });
        return mBinding.getRoot();
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mInteraction = (FragmentInteraction) context;
    }


    public void showBackPressed() {
        getActivity().onBackPressed();
        //mInteraction.openFragment(Enumes.FragmentType.FRAGMENT_MAIN_HOME, null, Enumes.Direction.RIGHT_IN);
    }

    private void inflateLayout() {

        FragmentEnergyElectric fragmentEnergyElectric = FragmentEnergyElectric.newInstance();
        getChildFragmentManager().beginTransaction().replace(R.id.fragment_energy_electric, fragmentEnergyElectric).commit();
    }
}
