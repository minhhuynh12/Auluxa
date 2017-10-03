package net.dirox.auluxa.setting.view;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.dirox.auluxa.R;
import net.dirox.auluxa.activity.FragmentInteraction;
import net.dirox.auluxa.adapter.RecentlyAddedAdapter;
import net.dirox.auluxa.common.fragment.BaseFragment;
import net.dirox.auluxa.data.sample.SettingsClimateDevicesItem;
import net.dirox.auluxa.databinding.LayoutSettingsRecentlyAddedBinding;
import net.dirox.auluxa.extras.Enumes;
import net.dirox.auluxa.utils.Constant;

import java.util.ArrayList;

public class FragmentSettingRecentlyAdded extends BaseFragment {

    private LayoutSettingsRecentlyAddedBinding mBinding;

    private FragmentInteraction mInteraction;


    public static FragmentSettingRecentlyAdded newInstance() {

        Bundle args = new Bundle();

        FragmentSettingRecentlyAdded fragment = new FragmentSettingRecentlyAdded();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.layout_settings_recently_added, container, false);

        mBinding.btnBackSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });

        RecentlyAddedAdapter adapter = new RecentlyAddedAdapter(getContext()) {
            @Override
            public void onItemClicked(int position) {

                Bundle bundle = new Bundle();
                ArrayList<SettingsClimateDevicesItem> list = new ArrayList<SettingsClimateDevicesItem>();
                list.add(new SettingsClimateDevicesItem(0, "IR NODE NAME", R.drawable.ir_node));
                list.add(new SettingsClimateDevicesItem(1, "THERMOSTAT", R.drawable.thrmostat_panel));
                list.add(new SettingsClimateDevicesItem(2, "THERMOSTAT PANEL", R.drawable.thrmostat_panel));
                list.add(new SettingsClimateDevicesItem(3, "HUMIDITY SENSOR", R.drawable.sensor));
                list.add(new SettingsClimateDevicesItem(4, "TEMPERATURE SENSOR", R.drawable.temp_sensor));
                list.add(new SettingsClimateDevicesItem(5, "TEMPERATURE & HUMIDITY SENSOR", R.drawable.sensor));
//                bundle.putInt(Constant.CLIMATE_DEVICE_ID, list.get(position).getId());
//                bundle.putString(Constant.CLIMATE_DEVICE_NAME, list.get(position).getClimateDeviceName());
//                bundle.putInt(Constant.CLIMATE_DEVICE_RESOURCE_ID, list.get(position).getResourdId());

                switch (position) {
                    // Light
                    case 0:
                        mInteraction.openFragment(Enumes.FragmentType.FRAGMENT_ONE_BUTTON_ON_OFF, null, Enumes.Direction.RIGHT_IN);
                        break;
                    case 1:
                        mInteraction.openFragment(Enumes.FragmentType.FRAGMENT_TWO_BUTTON_ON_OFF, null, Enumes.Direction.RIGHT_IN);
                        break;
                    case 2:
                        mInteraction.openFragment(Enumes.FragmentType.FRAGMENT_FOUR_BUTTON_ON_OFF, null, Enumes.Direction.RIGHT_IN);
                        break;
                    case 3:
                        mInteraction.openFragment(Enumes.FragmentType.FRAGMENT_ON_OFF_NODE, null, Enumes.Direction.RIGHT_IN);
                        break;
                    case 4:
                        mInteraction.openFragment(Enumes.FragmentType.FRAGMENT_ONE_BUTTON_DIMMING, null, Enumes.Direction.RIGHT_IN);
                        break;
                    case 5:
                        mInteraction.openFragment(Enumes.FragmentType.FRAGMENT_TWO_BUTTON_DIMMING, null, Enumes.Direction.RIGHT_IN);
                        break;
                    case 6:
                        mInteraction.openFragment(Enumes.FragmentType.FRAGMENT_DIMMING_NODE, null, Enumes.Direction.RIGHT_IN);
                        break;
                    case 7:
                        mInteraction.openFragment(Enumes.FragmentType.FRAGMENT_ONE_BUTTON_PANEL, null, Enumes.Direction.RIGHT_IN);
                        break;
                    case 8:
                        mInteraction.openFragment(Enumes.FragmentType.FRAGMENT_TWO_BUTTON_PANEL, null, Enumes.Direction.RIGHT_IN);
                        break;
                    case 9:
                        mInteraction.openFragment(Enumes.FragmentType.FRAGMENT_FOUR_BUTTON_PANEL, null, Enumes.Direction.RIGHT_IN);
                        break;
                    case 10:
                        mInteraction.openFragment(Enumes.FragmentType.FRAGMENT_ONE_BUTTON_DIMMING_PANEL, null, Enumes.Direction.RIGHT_IN);
                        break;
                    case 11:
                        mInteraction.openFragment(Enumes.FragmentType.FRAGMENT_TWO_BUTTON_DIMMING_PANEL, null, Enumes.Direction.RIGHT_IN);
                        break;

                    // Shades
                    case 12:
                        mInteraction.openFragment(Enumes.FragmentType.FRAGMENT_ONE_BUTTON_SHADES, null, Enumes.Direction.RIGHT_IN);
                        break;
                    case 13:
                        mInteraction.openFragment(Enumes.FragmentType.FRAGMENT_TWO_BUTTON_SHADES, null, Enumes.Direction.RIGHT_IN);
                        break;
                    case 14:
                        mInteraction.openFragment(Enumes.FragmentType.FRAGMENT_ONE_BUTTON_SHADES_PANEL, null, Enumes.Direction.RIGHT_IN);
                        break;
                    case 15:
                        mInteraction.openFragment(Enumes.FragmentType.FRAGMENT_TWO_BUTTON_SHADES_PANEL, null, Enumes.Direction.RIGHT_IN);
                        break;
                    case 16:
                        mInteraction.openFragment(Enumes.FragmentType.FRAGMENT_SHADES_NODE, null, Enumes.Direction.RIGHT_IN);
                        break;

                    // Settings Climates
                    case 17:
                        bundle.putInt(Constant.CLIMATE_DEVICE_ID, list.get(0).getId());
                        bundle.putString(Constant.CLIMATE_DEVICE_NAME, list.get(0).getClimateDeviceName());
                        bundle.putInt(Constant.CLIMATE_DEVICE_RESOURCE_ID, list.get(0).getResourdId());
                        mInteraction.openFragment(Enumes.FragmentType.FRAGMENT_SETTING_VIDEO_DEVICE_GENERAL, bundle, Enumes.Direction.RIGHT_IN);
                        break;
                    case 18:
                        bundle.putInt(Constant.CLIMATE_DEVICE_ID, list.get(1).getId());
                        bundle.putString(Constant.CLIMATE_DEVICE_NAME, list.get(1).getClimateDeviceName());
                        bundle.putInt(Constant.CLIMATE_DEVICE_RESOURCE_ID, list.get(1).getResourdId());
                        mInteraction.openFragment(Enumes.FragmentType.FRAGMENT_SETTING_CLIMATE_DEVICE_GENERAL, bundle, Enumes.Direction.RIGHT_IN);
                        break;
                    case 19:
                        bundle.putInt(Constant.CLIMATE_DEVICE_ID, list.get(2).getId());
                        bundle.putString(Constant.CLIMATE_DEVICE_NAME, list.get(2).getClimateDeviceName());
                        bundle.putInt(Constant.CLIMATE_DEVICE_RESOURCE_ID, list.get(2).getResourdId());
                        mInteraction.openFragment(Enumes.FragmentType.FRAGMENT_SETTING_CLIMATE_DEVICE_THERMOSTAT_PANEL, bundle, Enumes.Direction.RIGHT_IN);
                        break;
                    case 20:
                        bundle.putInt(Constant.CLIMATE_DEVICE_ID, list.get(3).getId());
                        bundle.putString(Constant.CLIMATE_DEVICE_NAME, list.get(3).getClimateDeviceName());
                        bundle.putInt(Constant.CLIMATE_DEVICE_RESOURCE_ID, list.get(3).getResourdId());
                        mInteraction.openFragment(Enumes.FragmentType.FRAGMENT_SETTING_CLIMATE_DEVICE_GENERAL, bundle, Enumes.Direction.RIGHT_IN);
                        break;
                    case 21:
                        bundle.putInt(Constant.CLIMATE_DEVICE_ID, list.get(4).getId());
                        bundle.putString(Constant.CLIMATE_DEVICE_NAME, list.get(4).getClimateDeviceName());
                        bundle.putInt(Constant.CLIMATE_DEVICE_RESOURCE_ID, list.get(4).getResourdId());
                        mInteraction.openFragment(Enumes.FragmentType.FRAGMENT_SETTING_CLIMATE_DEVICE_GENERAL, bundle, Enumes.Direction.RIGHT_IN);
                        break;
                    case 22:
                        bundle.putInt(Constant.CLIMATE_DEVICE_ID, list.get(5).getId());
                        bundle.putString(Constant.CLIMATE_DEVICE_NAME, list.get(5).getClimateDeviceName());
                        bundle.putInt(Constant.CLIMATE_DEVICE_RESOURCE_ID, list.get(5).getResourdId());
                        mInteraction.openFragment(Enumes.FragmentType.FRAGMENT_SETTING_CLIMATE_DEVICE_GENERAL, bundle, Enumes.Direction.RIGHT_IN);
                        break;

                    // Settings Video
                    case 23:
                        bundle.putString(Constant.CLIMATE_DEVICE_NAME, "CABLE TV");
                        mInteraction.openFragment(Enumes.FragmentType.FRAGMENT_SETTING_VIDEO_DEVICE_GENERAL, bundle, Enumes.Direction.RIGHT_IN);
                        break;
                    case 24:
                        bundle.putString(Constant.CLIMATE_DEVICE_NAME, "NOW TV");
                        mInteraction.openFragment(Enumes.FragmentType.FRAGMENT_SETTING_VIDEO_DEVICE_GENERAL, bundle, Enumes.Direction.RIGHT_IN);
                        break;

                    // Settings Security
                    case 25:
                        mInteraction.openFragment(Enumes.FragmentType.FRAGMENT_AULUXA_DOOR_LOCK, null, Enumes.Direction.RIGHT_IN);
                        break;
                    case 26:
                        mInteraction.openFragment(Enumes.FragmentType.FRAGMENT_DOOR_LOCK, null, Enumes.Direction.RIGHT_IN);
                        break;
                    case 27:
                        mInteraction.openFragment(Enumes.FragmentType.FRAGMENT_ALARM, null, Enumes.Direction.RIGHT_IN);
                        break;
                    case 28:
                        mInteraction.openFragment(Enumes.FragmentType.FRAGMENT_SENSOR, null, Enumes.Direction.RIGHT_IN);
                        break;
                    case 29:
                        mInteraction.openFragment(Enumes.FragmentType.FRAGMENT_DOOR_BELL, null, Enumes.Direction.RIGHT_IN);
                        break;

                }

            }
        };
        mBinding.listRecentlyAdded.setAdapter(adapter);

        return mBinding.getRoot();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mInteraction = (FragmentInteraction) context;
    }
}
