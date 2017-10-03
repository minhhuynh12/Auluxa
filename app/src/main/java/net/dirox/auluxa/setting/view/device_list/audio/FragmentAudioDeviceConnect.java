package net.dirox.auluxa.setting.view.device_list.audio;

import android.content.Context;
import android.content.Intent;
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
import net.dirox.auluxa.databinding.LayoutSettingsAudioDeviceConnectBinding;
import net.dirox.auluxa.login.view.LoginSignupActivity;
import net.dirox.auluxa.utils.Constant;

import java.util.List;

public class FragmentAudioDeviceConnect extends BaseFragment {

    private LayoutSettingsAudioDeviceConnectBinding mBinding;

    private FragmentInteraction mInteraction;

    List<SettingsItem> list;
    SettingsMainAdapter adapter;

    public static FragmentAudioDeviceConnect newInstance() {

        Bundle args = new Bundle();

        FragmentAudioDeviceConnect fragment = new FragmentAudioDeviceConnect();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.layout_settings_audio_device_connect, container, false);

        Bundle bundle = this.getArguments();
        if (bundle != null) {
            mBinding.settingsTitleSettings.setText(bundle.getString(Constant.CLIMATE_DEVICE_NAME));
            mBinding.tvSelectDeviceIrNode.setText(bundle.getString(Constant.CLIMATE_DEVICE_NAME) + " LOGIN");
        }

        mBinding.btnBackSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hideKeyboardIfNeed(getContext());
                getActivity().onBackPressed();
            }
        });

        mBinding.btnConnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });

        mBinding.btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });

        mBinding.forgetandsSignupSetuploginSetuplogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), LoginSignupActivity.class));
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
