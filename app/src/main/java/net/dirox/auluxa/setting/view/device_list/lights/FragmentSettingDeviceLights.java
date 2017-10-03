package net.dirox.auluxa.setting.view.device_list.lights;

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
import net.dirox.auluxa.databinding.LayoutSettingsDeviceLightsBinding;
import net.dirox.auluxa.extras.Enumes;
import net.dirox.auluxa.utils.Constant;

import java.util.ArrayList;
import java.util.List;

public class FragmentSettingDeviceLights extends BaseFragment {

    private LayoutSettingsDeviceLightsBinding mBinding;

    private FragmentInteraction mInteraction;

    List<SettingsClimateDevicesItem> list;
    SettingsClimateDevicesAdapter adapter;


    public static FragmentSettingDeviceLights newInstance() {

        Bundle args = new Bundle();

        FragmentSettingDeviceLights fragment = new FragmentSettingDeviceLights();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.layout_settings_device_lights, container, false);

        mBinding.btnBackSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackAndHideKeyBoard();
            }
        });

        list = new ArrayList<SettingsClimateDevicesItem>();
        list.add(new SettingsClimateDevicesItem(0, "1 BUTTON ON/OFF", R.drawable.one_button_2x));
        list.add(new SettingsClimateDevicesItem(1, "2 BUTTON ON/OFF", R.drawable.two_button_2x));
        list.add(new SettingsClimateDevicesItem(2, "4 BUTTON ON/OFF", R.drawable.four_button_2x));
        list.add(new SettingsClimateDevicesItem(3, "ON/OFF NODE", R.drawable.shades_node_2x));
        list.add(new SettingsClimateDevicesItem(4, "1 BUTTON DIMMING", R.drawable.one_dimming_button_big_2x));
        list.add(new SettingsClimateDevicesItem(5, "2 BUTTON DIMMING", R.drawable.four_button_2x));
        list.add(new SettingsClimateDevicesItem(6, "DIMMING NODE", R.drawable.shades_node_2x));
        list.add(new SettingsClimateDevicesItem(7, "1 BUTTON PANEL", R.drawable.one_button_2x));
        list.add(new SettingsClimateDevicesItem(8, "2 BUTTON PANEL", R.drawable.two_button_2x));
        list.add(new SettingsClimateDevicesItem(9, "4 BUTTON PANEL", R.drawable.four_button_2x));
        list.add(new SettingsClimateDevicesItem(10, "1 BUTTON DIMMING PANEL", R.drawable.one_dimming_button_big_2x));
        list.add(new SettingsClimateDevicesItem(11, "2 BUTTON DIMMING PANEL", R.drawable.two_dimming_button_2x));

        adapter = new SettingsClimateDevicesAdapter(getActivity(), R.layout.layout_item_settings_climate_device, list) {
            @Override
            public void onItemSelected(int position) {
                Bundle bundle = new Bundle();
                bundle.putInt(Constant.CLIMATE_DEVICE_ID, list.get(position).getId());
                bundle.putString(Constant.CLIMATE_DEVICE_NAME, list.get(position).getClimateDeviceName());
                bundle.putInt(Constant.CLIMATE_DEVICE_RESOURCE_ID, list.get(position).getResourdId());

                switch (position) {
                    // Light
                    case 0:
                        mInteraction.openFragment(Enumes.FragmentType.FRAGMENT_ONE_BUTTON_ON_OFF, null, Enumes.Direction.RIGHT_IN_FLIP_OUT);
                        break;
                    case 1:
                        mInteraction.openFragment(Enumes.FragmentType.FRAGMENT_TWO_BUTTON_ON_OFF, null, Enumes.Direction.RIGHT_IN_FLIP_OUT);
                        break;
                    case 2:
                        mInteraction.openFragment(Enumes.FragmentType.FRAGMENT_FOUR_BUTTON_ON_OFF, null, Enumes.Direction.RIGHT_IN_FLIP_OUT);
                        break;
                    case 3:
                        mInteraction.openFragment(Enumes.FragmentType.FRAGMENT_ON_OFF_NODE, null, Enumes.Direction.RIGHT_IN_FLIP_OUT);
                        break;
                    case 4:
                        mInteraction.openFragment(Enumes.FragmentType.FRAGMENT_ONE_BUTTON_DIMMING, null, Enumes.Direction.RIGHT_IN_FLIP_OUT);
                        break;
                    case 5:
                        mInteraction.openFragment(Enumes.FragmentType.FRAGMENT_TWO_BUTTON_DIMMING, null, Enumes.Direction.RIGHT_IN_FLIP_OUT);
                        break;
                    case 6:
                        mInteraction.openFragment(Enumes.FragmentType.FRAGMENT_DIMMING_NODE, null, Enumes.Direction.RIGHT_IN_FLIP_OUT);
                        break;
                    case 7:
                        mInteraction.openFragment(Enumes.FragmentType.FRAGMENT_ONE_BUTTON_PANEL, null, Enumes.Direction.RIGHT_IN_FLIP_OUT);
                        break;
                    case 8:
                        mInteraction.openFragment(Enumes.FragmentType.FRAGMENT_TWO_BUTTON_PANEL, null, Enumes.Direction.RIGHT_IN_FLIP_OUT);
                        break;
                    case 9:
                        mInteraction.openFragment(Enumes.FragmentType.FRAGMENT_FOUR_BUTTON_PANEL, null, Enumes.Direction.RIGHT_IN_FLIP_OUT);
                        break;
                    case 10:
                        mInteraction.openFragment(Enumes.FragmentType.FRAGMENT_ONE_BUTTON_DIMMING_PANEL, null, Enumes.Direction.RIGHT_IN_FLIP_OUT);
                        break;
                    case 11:
                        mInteraction.openFragment(Enumes.FragmentType.FRAGMENT_TWO_BUTTON_DIMMING_PANEL, null, Enumes.Direction.RIGHT_IN_FLIP_OUT);
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
