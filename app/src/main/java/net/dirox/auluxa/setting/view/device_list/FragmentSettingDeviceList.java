package net.dirox.auluxa.setting.view.device_list;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.dirox.auluxa.R;
import net.dirox.auluxa.activity.FragmentInteraction;
import net.dirox.auluxa.common.fragment.BaseFragment;
import net.dirox.auluxa.databinding.LayoutSettingsDeviceListBinding;
import net.dirox.auluxa.extras.Enumes;

public class FragmentSettingDeviceList extends BaseFragment {

    private LayoutSettingsDeviceListBinding mBinding;

    private FragmentInteraction mInteraction;


    public static FragmentSettingDeviceList newInstance() {

        Bundle args = new Bundle();

        FragmentSettingDeviceList fragment = new FragmentSettingDeviceList();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.layout_settings_device_list, container, false);

        mBinding.btnBackSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mInteraction.openFragment(Enumes.FragmentType.FRAGMENT_SETTING_MAIN, null, Enumes.Direction.RIGHT_IN);
            }
        });

        // BUTTON RECENTLY ADDED
        mBinding.rlRecentlyAdded.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mInteraction.openFragment(Enumes.FragmentType.FRAGMENT_SETTING_RECENTLY_ADDED, null, Enumes.Direction.RIGHT_IN);
            }
        });

        // BUTTON 3RD PARTY
        mBinding.rlThirdParty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mInteraction.openFragment(Enumes.FragmentType.FRAGMENT_SETTING_THIRD_PARTY, null, Enumes.Direction.RIGHT_IN);
            }
        });

        // BUTTON DEVICE LIST
        mBinding.rlDeviceList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBinding.layoutAddDevice.setVisibility(View.GONE);
                mBinding.layoutDeviceList.setVisibility(View.VISIBLE);
                mBinding.settingsTitleSettings.setText("DEVICE LIST");
                mBinding.tvClimateDeviceQty.setText("6");
                mBinding.tvLightsDeviceQty.setText("9");
                mBinding.tvAudioDeviceQty.setText("2");
                mBinding.tvVideoDeviceQty.setText("2");
                mBinding.tvSecurityDeviceQty.setText("5");
                mBinding.tvShadesDeviceQty.setText("5");
            }
        });

        // CLIMATE DEVICES
        mBinding.rlClimate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!mBinding.tvClimateDeviceQty.getText().equals("_")) {
                    mInteraction.openFragment(Enumes.FragmentType.FRAGMENT_SETTING_DEVICE_CLIMATE, null, Enumes.Direction.RIGHT_IN);
                } else {

                }
            }
        });

        // LIGHTS DEVICES
        mBinding.rlLights.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!mBinding.tvLightsDeviceQty.getText().equals("_")) {
                    mInteraction.openFragment(Enumes.FragmentType.FRAGMENT_SETTING_DEVICE_LIGHTS, null, Enumes.Direction.RIGHT_IN);
                } else {

                }
            }
        });

        // AUDIO DEVICES
        mBinding.rlAudio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!mBinding.tvAudioDeviceQty.getText().equals("_")) {
                    mInteraction.openFragment(Enumes.FragmentType.FRAGMENT_SETTING_DEVICE_AUDIO, null, Enumes.Direction.RIGHT_IN);
                } else {

                }
            }
        });

        // VIDEO DEVICES
        mBinding.rlVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!mBinding.tvVideoDeviceQty.getText().equals("_")) {
                    mInteraction.openFragment(Enumes.FragmentType.FRAGMENT_SETTING_DEVICE_VIDEO, null, Enumes.Direction.RIGHT_IN);
                } else {

                }
            }
        });

        // SECURITY DEVICES
        mBinding.rlSecurity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!mBinding.tvSecurityDeviceQty.getText().equals("_")) {
                    mInteraction.openFragment(Enumes.FragmentType.FRAGMENT_SETTING_DEVICE_SECURITY, null, Enumes.Direction.RIGHT_IN);
                } else {

                }
            }
        });

        // SHADES DEVICES
        mBinding.rlShades.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!mBinding.tvShadesDeviceQty.getText().equals("_")) {
                    mInteraction.openFragment(Enumes.FragmentType.FRAGMENT_SETTING_DEVICE_SHADES, null, Enumes.Direction.RIGHT_IN);
                } else {

                }
            }
        });

        // BUTTON ADD DEVICE
        mBinding.btnAddDevice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBinding.layoutDeviceList.setVisibility(View.GONE);
                mBinding.layoutAddDevice.setVisibility(View.VISIBLE);
                mBinding.settingsTitleSettings.setText(getString(R.string.settings_title_add_device));
                mBinding.layoutSearchingDevice.setVisibility(View.VISIBLE);

                if (mBinding.layoutSearchingDevice.getVisibility() == View.VISIBLE) {
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            // Hide view after 1 seconds
                            mBinding.layoutSearchingDevice.setVisibility(View.GONE);
                            mBinding.layoutConnectionAgain.setVisibility(View.VISIBLE);
                        }
                    }, 1000);
                }
            }
        });

        // BUTTON CONNECT AGAIN
        mBinding.btnConnectAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mInteraction.openFragment(Enumes.FragmentType.FRAGMENT_SETTING_ADD_DEVICES, null, Enumes.Direction.RIGHT_IN);
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
