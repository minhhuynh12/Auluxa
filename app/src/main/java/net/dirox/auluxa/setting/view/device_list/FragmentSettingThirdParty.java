package net.dirox.auluxa.setting.view.device_list;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.dirox.auluxa.R;
import net.dirox.auluxa.activity.FragmentInteraction;
import net.dirox.auluxa.adapter.ThirdPartyAdapter;
import net.dirox.auluxa.common.fragment.BaseFragment;
import net.dirox.auluxa.data.sample.SettingsClimateDevicesItem;
import net.dirox.auluxa.databinding.LayoutSettingsThirdPartyBinding;
import net.dirox.auluxa.extras.Enumes;
import net.dirox.auluxa.utils.Constant;

import java.util.ArrayList;

public class FragmentSettingThirdParty extends BaseFragment {

    private LayoutSettingsThirdPartyBinding mBinding;

    private FragmentInteraction mInteraction;


    public static FragmentSettingThirdParty newInstance() {

        Bundle args = new Bundle();

        FragmentSettingThirdParty fragment = new FragmentSettingThirdParty();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.layout_settings_third_party, container, false);

        mBinding.btnBackSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });
        ArrayList<SettingsClimateDevicesItem> list = new ArrayList<SettingsClimateDevicesItem>();
        list.add(new SettingsClimateDevicesItem(0, "SONOS", R.drawable.sonos));
        list.add(new SettingsClimateDevicesItem(1, "DENON", R.drawable.denon));
        ArrayList<SettingsClimateDevicesItem> list2 = new ArrayList<SettingsClimateDevicesItem>();
        list2.add(new SettingsClimateDevicesItem(3, "HUMIDITY SENSOR", R.drawable.sensor));
        list2.add(new SettingsClimateDevicesItem(4, "TEMPERATURE SENSOR", R.drawable.temp_sensor));

        Bundle bundle = new Bundle();
        ThirdPartyAdapter adapter = new ThirdPartyAdapter(getContext()){
            @Override
            public void onItemClicked(int position) {
                switch (position)
                {
                    // Audio
                    case 0:
                        bundle.putInt(Constant.CLIMATE_DEVICE_ID, list.get(0).getId());
                        bundle.putString(Constant.CLIMATE_DEVICE_NAME, list.get(0).getClimateDeviceName());
                        bundle.putInt(Constant.CLIMATE_DEVICE_RESOURCE_ID, list.get(0).getResourdId());
                        mInteraction.openFragment(Enumes.FragmentType.FRAGMENT_SETTING_AUDIO_DEVICE_GENERAL, bundle, Enumes.Direction.RIGHT_IN);
                        break;
                    case 1:
                        bundle.putInt(Constant.CLIMATE_DEVICE_ID, list.get(1).getId());
                        bundle.putString(Constant.CLIMATE_DEVICE_NAME, list.get(1).getClimateDeviceName());
                        bundle.putInt(Constant.CLIMATE_DEVICE_RESOURCE_ID, list.get(1).getResourdId());
                        mInteraction.openFragment(Enumes.FragmentType.FRAGMENT_SETTING_AUDIO_DEVICE_GENERAL, bundle, Enumes.Direction.RIGHT_IN);
                        break;

                    // Climate
                    case 2:
                        bundle.putInt(Constant.CLIMATE_DEVICE_ID, list2.get(0).getId());
                        bundle.putString(Constant.CLIMATE_DEVICE_NAME, list2.get(0).getClimateDeviceName());
                        bundle.putInt(Constant.CLIMATE_DEVICE_RESOURCE_ID, list2.get(0).getResourdId());
                        mInteraction.openFragment(Enumes.FragmentType.FRAGMENT_SETTING_CLIMATE_DEVICE_GENERAL, bundle, Enumes.Direction.RIGHT_IN);
                        break;
                    case 3:
                        bundle.putInt(Constant.CLIMATE_DEVICE_ID, list2.get(1).getId());
                        bundle.putString(Constant.CLIMATE_DEVICE_NAME, list2.get(1).getClimateDeviceName());
                        bundle.putInt(Constant.CLIMATE_DEVICE_RESOURCE_ID, list2.get(1).getResourdId());
                        mInteraction.openFragment(Enumes.FragmentType.FRAGMENT_SETTING_CLIMATE_DEVICE_GENERAL, bundle, Enumes.Direction.RIGHT_IN);
                        break;

                    // Security
                    case 4:
                        mInteraction.openFragment(Enumes.FragmentType.FRAGMENT_DOOR_LOCK, null, Enumes.Direction.RIGHT_IN);
                        break;
                    case 5:
                        mInteraction.openFragment(Enumes.FragmentType.FRAGMENT_ALARM, null, Enumes.Direction.RIGHT_IN);
                        break;
                    case 6:
                        mInteraction.openFragment(Enumes.FragmentType.FRAGMENT_SENSOR, null, Enumes.Direction.RIGHT_IN);
                        break;
                    case 7:
                        mInteraction.openFragment(Enumes.FragmentType.FRAGMENT_AULUXA_DOOR_LOCK, null, Enumes.Direction.RIGHT_IN);
                        break;
                    case 8:
                        mInteraction.openFragment(Enumes.FragmentType.FRAGMENT_DOOR_BELL, null, Enumes.Direction.RIGHT_IN);
                        break;

                }
            }
        };

        mBinding.list3rdParty.setAdapter(adapter);

        return mBinding.getRoot();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mInteraction = (FragmentInteraction) context;
    }
}
