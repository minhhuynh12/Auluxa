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
import net.dirox.auluxa.databinding.LayoutSettingsAudioDeviceGeneralBinding;
import net.dirox.auluxa.extras.Enumes;
import net.dirox.auluxa.utils.Constant;

import java.util.List;

public class FragmentAudioDeviceGeneral extends BaseFragment {

    private LayoutSettingsAudioDeviceGeneralBinding mBinding;

    private FragmentInteraction mInteraction;

    List<SettingsItem> list;
    SettingsMainAdapter adapter;

    public static FragmentAudioDeviceGeneral newInstance() {

        Bundle args = new Bundle();

        FragmentAudioDeviceGeneral fragment = new FragmentAudioDeviceGeneral();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.layout_settings_audio_device_general, container, false);

        Bundle bundle = this.getArguments();
        if (bundle != null) {
            mBinding.settingsTitleSettings.setText(bundle.getString(Constant.CLIMATE_DEVICE_NAME));
            mBinding.etAudioDeviceNameGeneral.setText(bundle.getString(Constant.CLIMATE_DEVICE_NAME));
            mBinding.imgAudioDeviceGeneral.setImageResource(bundle.getInt(Constant.CLIMATE_DEVICE_RESOURCE_ID));
        }

        if (bundle.getString(Constant.CLIMATE_DEVICE_NAME).equals("DENON")) {
            mBinding.btnAudioMapping.setVisibility(View.VISIBLE);
            mBinding.btnAudioMapping.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mInteraction.openFragment(Enumes.FragmentType.FRAGMENT_SETTING_AUDIO_DEVICE_MAPPING, null, Enumes.Direction.RIGHT_IN);
                }
            });
        } else {
            mBinding.btnAudioMapping.setVisibility(View.GONE);
        }

        mBinding.btnBackSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hideKeyboardIfNeed(getContext());
                getActivity().onBackPressed();
            }
        });

        mBinding.btnAudioConnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mInteraction.openFragment(Enumes.FragmentType.FRAGMENT_SETTING_AUDIO_DEVICE_CONNECT, bundle, Enumes.Direction.RIGHT_IN);
            }
        });

        mBinding.btnAudioSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bundle mBundle = new Bundle();
                mBundle.putString(Constant.CLIMATE_DEVICE_NAME, mBinding.settingsTitleSettings.getText().toString());

                mInteraction.openFragment(Enumes.FragmentType.FRAGMENT_SETTING_AUDIO_DEVICE_SETTINGS, mBundle, Enumes.Direction.RIGHT_IN);
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
