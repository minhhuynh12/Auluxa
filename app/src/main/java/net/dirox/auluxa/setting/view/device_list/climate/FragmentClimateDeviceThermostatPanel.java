package net.dirox.auluxa.setting.view.device_list.climate;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.dirox.auluxa.R;
import net.dirox.auluxa.activity.FragmentInteraction;
import net.dirox.auluxa.adapter.SettingsClimateDeviceThermostatPanelAdapter;
import net.dirox.auluxa.common.fragment.BaseFragment;
import net.dirox.auluxa.data.sample.SettingsClimateThermostatPanelItem;
import net.dirox.auluxa.databinding.LayoutSettingsClimateDeviceThermostatPanelBinding;
import net.dirox.auluxa.utils.Constant;

import java.util.ArrayList;
import java.util.List;

public class FragmentClimateDeviceThermostatPanel extends BaseFragment {

    private LayoutSettingsClimateDeviceThermostatPanelBinding mBinding;

    private FragmentInteraction mInteraction;

    List<SettingsClimateThermostatPanelItem> list;

    SettingsClimateDeviceThermostatPanelAdapter adapter;

    public static FragmentClimateDeviceThermostatPanel newInstance() {

        Bundle args = new Bundle();

        FragmentClimateDeviceThermostatPanel fragment = new FragmentClimateDeviceThermostatPanel();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.layout_settings_climate_device_thermostat_panel, container, false);

        Bundle bundle = this.getArguments();
        if (bundle != null) {
            mBinding.settingsTitleSettings.setText(bundle.getString(Constant.CLIMATE_DEVICE_NAME));
        }

        mBinding.btnBackSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackAndHideKeyBoard();
            }
        });

        mBinding.btnSaveThermostatPanel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackAndHideKeyBoard();
            }
        });

        mBinding.btnCancelThermostatPanel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackAndHideKeyBoard();
            }
        });

        list = new ArrayList<SettingsClimateThermostatPanelItem>();
        list.add(new SettingsClimateThermostatPanelItem(0, "THERMOSTAT A", false));
        list.add(new SettingsClimateThermostatPanelItem(1, "THERMOSTAT B", false));
        list.add(new SettingsClimateThermostatPanelItem(2, "THERMOSTAT C", false));
        list.add(new SettingsClimateThermostatPanelItem(3, "THERMOSTAT D", false));
        list.add(new SettingsClimateThermostatPanelItem(4, "THERMOSTAT E", false));
        list.add(new SettingsClimateThermostatPanelItem(5, "THERMOSTAT F", false));

        adapter = new SettingsClimateDeviceThermostatPanelAdapter(getActivity(), R.layout.layout_item_settings_climate_thermostat_panel, list) {
            @Override
            public void onItemSelected(int position) {

            }
        };
        mBinding.listSettingsClimateThermostatPanel.setAdapter(adapter);

        return mBinding.getRoot();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mInteraction = (FragmentInteraction) context;
    }
}
