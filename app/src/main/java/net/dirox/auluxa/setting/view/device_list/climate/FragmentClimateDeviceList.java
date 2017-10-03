package net.dirox.auluxa.setting.view.device_list.climate;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.dirox.auluxa.R;
import net.dirox.auluxa.activity.FragmentInteraction;
import net.dirox.auluxa.adapter.SettingsClimateDevicesAdapter;
import net.dirox.auluxa.common.fragment.BaseFragment;
import net.dirox.auluxa.data.sample.SettingsClimateDevicesItem;
import net.dirox.auluxa.databinding.LayoutSettingsDeviceClimateBinding;
import net.dirox.auluxa.extras.Enumes;
import net.dirox.auluxa.utils.Constant;

import java.util.ArrayList;
import java.util.List;

public class FragmentClimateDeviceList extends BaseFragment {

    private LayoutSettingsDeviceClimateBinding mBinding;

    private FragmentInteraction mInteraction;

    List<SettingsClimateDevicesItem> list;
    SettingsClimateDevicesAdapter adapter;


    public static FragmentClimateDeviceList newInstance() {

        Bundle args = new Bundle();

        FragmentClimateDeviceList fragment = new FragmentClimateDeviceList();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.layout_settings_device_climate, container, false);

        mBinding.btnBackSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackAndHideKeyBoard();
            }
        });

        list = new ArrayList<SettingsClimateDevicesItem>();
        list.add(new SettingsClimateDevicesItem(0, "IR NODE NAME", R.drawable.ir_node));
        list.add(new SettingsClimateDevicesItem(1, "THERMOSTAT", R.drawable.thrmostat_panel));
        list.add(new SettingsClimateDevicesItem(2, "THERMOSTAT PANEL", R.drawable.thrmostat_panel));
        list.add(new SettingsClimateDevicesItem(3, "HUMIDITY SENSOR", R.drawable.sensor));
        list.add(new SettingsClimateDevicesItem(4, "TEMPERATURE SENSOR", R.drawable.temp_sensor));
        list.add(new SettingsClimateDevicesItem(5, "TEMPERATURE & HUMIDITY SENSOR", R.drawable.sensor));

        adapter = new SettingsClimateDevicesAdapter(getActivity(), R.layout.layout_item_settings_climate_device, list) {
            @Override
            public void onItemSelected(int position) {
                Bundle bundle = new Bundle();
                bundle.putInt(Constant.CLIMATE_DEVICE_ID, list.get(position).getId());
                bundle.putString(Constant.CLIMATE_DEVICE_NAME, list.get(position).getClimateDeviceName());
                bundle.putInt(Constant.CLIMATE_DEVICE_RESOURCE_ID, list.get(position).getResourdId());
                if (position == 2) {
                    mInteraction.openFragment(Enumes.FragmentType.FRAGMENT_SETTING_CLIMATE_DEVICE_THERMOSTAT_PANEL, bundle, Enumes.Direction.RIGHT_IN_FLIP_OUT);
                } else if (position == 0) {
                    mInteraction.openFragment(Enumes.FragmentType.FRAGMENT_SETTING_VIDEO_DEVICE_GENERAL, bundle, Enumes.Direction.RIGHT_IN);
                } else
                    mInteraction.openFragment(Enumes.FragmentType.FRAGMENT_SETTING_CLIMATE_DEVICE_GENERAL, bundle, Enumes.Direction.RIGHT_IN);

            }
        };

        mBinding.listSettingsClimateDevice.setAdapter(adapter);

        return mBinding.getRoot();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mInteraction = (FragmentInteraction) context;
    }
}
