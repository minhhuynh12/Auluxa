package net.dirox.auluxa.setting.view.device_list.shades;

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
import net.dirox.auluxa.databinding.LayoutSettingsDeviceSecurityBinding;
import net.dirox.auluxa.databinding.LayoutSettingsDeviceShadesBinding;
import net.dirox.auluxa.extras.Enumes;
import net.dirox.auluxa.utils.Constant;

import java.util.ArrayList;
import java.util.List;

public class FragmentSettingDeviceShades extends BaseFragment {

    private LayoutSettingsDeviceShadesBinding mBinding;

    private FragmentInteraction mInteraction;

    List<SettingsClimateDevicesItem> list;
    SettingsClimateDevicesAdapter adapter;


    public static FragmentSettingDeviceShades newInstance() {

        Bundle args = new Bundle();

        FragmentSettingDeviceShades fragment = new FragmentSettingDeviceShades();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.layout_settings_device_shades, container, false);

        mBinding.btnBackSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               onBackAndHideKeyBoard();
            }
        });

        list = new ArrayList<SettingsClimateDevicesItem>();
        list.add(new SettingsClimateDevicesItem(0, "1 BUTTON SHADES", R.drawable.two_button_shades_2x));
        list.add(new SettingsClimateDevicesItem(1, "2 BUTTON SHADES", R.drawable.four_button_shades_2x));
        list.add(new SettingsClimateDevicesItem(2, "1 BUTTON SHADES PANEL", R.drawable.two_button_shades_2x));
        list.add(new SettingsClimateDevicesItem(3, "2 BUTTON SHADES PANEL", R.drawable.four_button_shades_2x));
        list.add(new SettingsClimateDevicesItem(4, "SHADES NODE", R.drawable.shades_node_2x));

        adapter = new SettingsClimateDevicesAdapter(getActivity(), R.layout.layout_item_settings_climate_device, list) {
            @Override
            public void onItemSelected(int position) {
                Bundle bundle = new Bundle();
                bundle.putInt(Constant.CLIMATE_DEVICE_ID, list.get(position).getId());
                bundle.putString(Constant.CLIMATE_DEVICE_NAME, list.get(position).getClimateDeviceName());
                bundle.putInt(Constant.CLIMATE_DEVICE_RESOURCE_ID, list.get(position).getResourdId());

                switch (position) {
                    case 0:
                        mInteraction.openFragment(Enumes.FragmentType.FRAGMENT_ONE_BUTTON_SHADES, null, Enumes.Direction.RIGHT_IN_FLIP_OUT);
                        break;
                    case 1:
                        mInteraction.openFragment(Enumes.FragmentType.FRAGMENT_TWO_BUTTON_SHADES, null, Enumes.Direction.RIGHT_IN_FLIP_OUT);
                        break;
                    case 2:
                        mInteraction.openFragment(Enumes.FragmentType.FRAGMENT_ONE_BUTTON_SHADES_PANEL, null, Enumes.Direction.RIGHT_IN_FLIP_OUT);
                        break;
                    case 3:
                        mInteraction.openFragment(Enumes.FragmentType.FRAGMENT_TWO_BUTTON_SHADES_PANEL, null, Enumes.Direction.RIGHT_IN_FLIP_OUT);
                        break;
                    case 4:
                        mInteraction.openFragment(Enumes.FragmentType.FRAGMENT_SHADES_NODE, null, Enumes.Direction.RIGHT_IN_FLIP_OUT);
                        break;
                }
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
