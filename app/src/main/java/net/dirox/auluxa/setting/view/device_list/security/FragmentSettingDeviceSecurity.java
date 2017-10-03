package net.dirox.auluxa.setting.view.device_list.security;

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
import net.dirox.auluxa.extras.Enumes;
import net.dirox.auluxa.utils.Constant;

import java.util.ArrayList;
import java.util.List;

public class FragmentSettingDeviceSecurity extends BaseFragment {

    private LayoutSettingsDeviceSecurityBinding mBinding;

    private FragmentInteraction mInteraction;

    List<SettingsClimateDevicesItem> list;
    SettingsClimateDevicesAdapter adapter;


    public static FragmentSettingDeviceSecurity newInstance() {

        Bundle args = new Bundle();

        FragmentSettingDeviceSecurity fragment = new FragmentSettingDeviceSecurity();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.layout_settings_device_security, container, false);

        mBinding.btnBackSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackAndHideKeyBoard();
            }
        });

        list = new ArrayList<SettingsClimateDevicesItem>();
        list.add(new SettingsClimateDevicesItem(0, "AULUXA DOOR LOCK", R.drawable.auluxa_door_lock_2x));
        list.add(new SettingsClimateDevicesItem(1, "DOOR LOCK", R.drawable.doorlock_2x));
        list.add(new SettingsClimateDevicesItem(2, "ALARM", R.drawable.shades_node_2x));
        list.add(new SettingsClimateDevicesItem(3, "SENSOR", R.drawable.sensor));
        list.add(new SettingsClimateDevicesItem(4, "DOORBELL", R.drawable.doorbell_2x));
        list.add(new SettingsClimateDevicesItem(5, "VIDEO DOOR PHONE", R.drawable.video_door_fone_2x));

        adapter = new SettingsClimateDevicesAdapter(getActivity(), R.layout.layout_item_settings_climate_device, list) {
            @Override
            public void onItemSelected(int position) {
                Bundle bundle = new Bundle();
                bundle.putInt(Constant.CLIMATE_DEVICE_ID, list.get(position).getId());
                bundle.putString(Constant.CLIMATE_DEVICE_NAME, list.get(position).getClimateDeviceName());
                bundle.putInt(Constant.CLIMATE_DEVICE_RESOURCE_ID, list.get(position).getResourdId());

                switch (position) {
                    case 0:
                        mInteraction.openFragment(Enumes.FragmentType.FRAGMENT_AULUXA_DOOR_LOCK, null, Enumes.Direction.RIGHT_IN);
                        break;
                    case 1:
                        mInteraction.openFragment(Enumes.FragmentType.FRAGMENT_DOOR_LOCK, null, Enumes.Direction.RIGHT_IN);
                        break;
                    case 2:
                        mInteraction.openFragment(Enumes.FragmentType.FRAGMENT_ALARM, null, Enumes.Direction.RIGHT_IN);
                        break;
                    case 3:
                        mInteraction.openFragment(Enumes.FragmentType.FRAGMENT_SENSOR, null, Enumes.Direction.RIGHT_IN);
                        break;
                    case 4:
                        mInteraction.openFragment(Enumes.FragmentType.FRAGMENT_DOOR_BELL, null, Enumes.Direction.RIGHT_IN);
                        break;
                    case 5:
                        mInteraction.openFragment(Enumes.FragmentType.FRAGMENT_VIDEO_DOOR_PHONE, null, Enumes.Direction.RIGHT_IN);
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
