package net.dirox.auluxa.setting.view.device_list.audio;

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
import net.dirox.auluxa.databinding.LayoutSettingsDeviceAudioBinding;
import net.dirox.auluxa.extras.Enumes;
import net.dirox.auluxa.utils.Constant;

import java.util.ArrayList;
import java.util.List;

public class FragmentAudioDeviceList extends BaseFragment {

    private LayoutSettingsDeviceAudioBinding mBinding;

    private FragmentInteraction mInteraction;

    List<SettingsClimateDevicesItem> list;
    SettingsClimateDevicesAdapter adapter;

    public static FragmentAudioDeviceList newInstance() {

        Bundle args = new Bundle();

        FragmentAudioDeviceList fragment = new FragmentAudioDeviceList();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.layout_settings_device_audio, container, false);

        mBinding.btnBackSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hideKeyboardIfNeed(getContext());
                getActivity().onBackPressed();
            }
        });

        list = new ArrayList<SettingsClimateDevicesItem>();
        list.add(new SettingsClimateDevicesItem(0, "SONOS", R.drawable.sonos));
        list.add(new SettingsClimateDevicesItem(1, "DENON", R.drawable.denon));

        adapter = new SettingsClimateDevicesAdapter(getActivity(), R.layout.layout_item_settings_climate_device, list) {
            @Override
            public void onItemSelected(int position) {
                Bundle bundle = new Bundle();
                bundle.putInt(Constant.CLIMATE_DEVICE_ID, list.get(position).getId());
                bundle.putString(Constant.CLIMATE_DEVICE_NAME, list.get(position).getClimateDeviceName());
                bundle.putInt(Constant.CLIMATE_DEVICE_RESOURCE_ID, list.get(position).getResourdId());
                mInteraction.openFragment(Enumes.FragmentType.FRAGMENT_SETTING_AUDIO_DEVICE_GENERAL, bundle, Enumes.Direction.RIGHT_IN);
            }
        };

        mBinding.listSettingsAudioDevice.setAdapter(adapter);

        return mBinding.getRoot();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mInteraction = (FragmentInteraction) context;
    }
}
