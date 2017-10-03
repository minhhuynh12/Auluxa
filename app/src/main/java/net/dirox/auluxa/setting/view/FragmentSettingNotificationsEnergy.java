package net.dirox.auluxa.setting.view;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.dirox.auluxa.R;
import net.dirox.auluxa.activity.FragmentInteraction;
import net.dirox.auluxa.adapter.SettingsNotificationsEnergyAdapter;
import net.dirox.auluxa.common.fragment.BaseFragment;
import net.dirox.auluxa.data.sample.SettingsNotificationsEnergyItem;
import net.dirox.auluxa.databinding.LayoutSettingsNotificationsEnergyBinding;

import java.util.ArrayList;
import java.util.List;

public class FragmentSettingNotificationsEnergy extends BaseFragment {

    private LayoutSettingsNotificationsEnergyBinding mBinding;

    private FragmentInteraction mInteraction;

    List<SettingsNotificationsEnergyItem> list;

    SettingsNotificationsEnergyAdapter adapter;

    public static FragmentSettingNotificationsEnergy newInstance() {

        Bundle args = new Bundle();

        FragmentSettingNotificationsEnergy fragment = new FragmentSettingNotificationsEnergy();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.layout_settings_notifications_energy, container, false);

        mBinding.btnBackSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });

        list = new ArrayList<SettingsNotificationsEnergyItem>();
        list.add(new SettingsNotificationsEnergyItem(0, "Electricity monthly bill target exceeded 1", false));
        list.add(new SettingsNotificationsEnergyItem(1, "Electricity monthly bill target exceeded 2", false));
        list.add(new SettingsNotificationsEnergyItem(2, "Electricity monthly bill target exceeded 3", false));

        adapter = new SettingsNotificationsEnergyAdapter(getActivity(), R.layout.layout_item_settings_notification_energy, list) {
            @Override
            public void onItemSelected(int position) {

            }
        };
        mBinding.listNotificationsEnergy.setAdapter(adapter);

        mBinding.cbSubscribeAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mBinding.cbSubscribeAll.isChecked()) {
                    adapter.checkAll(true);
                    mBinding.tvSubscribeAll.setTextColor(Color.parseColor("#18bd9b"));
                } else {
                    adapter.checkAll(false);
                    mBinding.tvSubscribeAll.setTextColor(Color.parseColor("#B0000000"));
                }
            }
        });

        return mBinding.getRoot();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mInteraction = (FragmentInteraction) context;
    }
}
