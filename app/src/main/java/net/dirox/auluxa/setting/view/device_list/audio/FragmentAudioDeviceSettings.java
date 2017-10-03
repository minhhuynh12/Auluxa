package net.dirox.auluxa.setting.view.device_list.audio;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.dirox.auluxa.R;
import net.dirox.auluxa.activity.FragmentInteraction;
import net.dirox.auluxa.adapter.SettingsMainAdapter;
import net.dirox.auluxa.common.fragment.BaseFragment;
import net.dirox.auluxa.data.sample.SettingsItem;
import net.dirox.auluxa.databinding.LayoutSettingsAudioDeviceSettingBinding;
import net.dirox.auluxa.extras.Enumes;
import net.dirox.auluxa.utils.Constant;

import java.util.ArrayList;
import java.util.List;

public class FragmentAudioDeviceSettings extends BaseFragment {

    private LayoutSettingsAudioDeviceSettingBinding mBinding;

    private FragmentInteraction mInteraction;

    List<SettingsItem> list = new ArrayList<>();
    SettingsMainAdapter adapter;

    public static FragmentAudioDeviceSettings newInstance() {

        Bundle args = new Bundle();

        FragmentAudioDeviceSettings fragment = new FragmentAudioDeviceSettings();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.layout_settings_audio_device_setting, container, false);

        mBinding.btnBackSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackAndHideKeyBoard();
            }
        });

        Bundle bundle = this.getArguments();
        if (bundle != null) {
            switch (bundle.getString(Constant.CLIMATE_DEVICE_NAME).toString()) {
                case "SONOS":
                    list = new ArrayList<SettingsItem>();
                    list.add(new SettingsItem(0, "PLAY 1"));
                    list.add(new SettingsItem(1, "PLAY 3"));
                    list.add(new SettingsItem(2, "CONNECT"));
                    break;
                case "DENON":
                    list = new ArrayList<SettingsItem>();
                    list.add(new SettingsItem(0, "SPEAKER 1"));
                    list.add(new SettingsItem(1, "SPEAKER 2"));
                    list.add(new SettingsItem(2, "SPEAKER 3"));
                    break;
            }
        }

        adapter = new SettingsMainAdapter(getActivity(), R.layout.item_settings, list) {
            @Override
            public void onItemSelected(int position) {
                Bundle mBundle = new Bundle();
                mBundle.putString(Constant.CLIMATE_DEVICE_NAME, list.get(position).getTitleSettings());
                switch (position) {
                    case 0:
                        mInteraction.openFragment(Enumes.FragmentType.FRAGMENT_SETTING_CLIMATE_DEVICE_ROOMS_GENERAL, mBundle, Enumes.Direction.RIGHT_IN);
                        break;
                    case 1:
                        mInteraction.openFragment(Enumes.FragmentType.FRAGMENT_SETTING_CLIMATE_DEVICE_ROOMS_GENERAL, mBundle, Enumes.Direction.RIGHT_IN);
                        break;
                    case 2:
                        break;
                }

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
