package net.dirox.auluxa.setting.view.device_list.audio;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.dirox.auluxa.R;
import net.dirox.auluxa.activity.FragmentInteraction;
import net.dirox.auluxa.adapter.SettingsLanguageAdapter;
import net.dirox.auluxa.common.fragment.BaseFragment;
import net.dirox.auluxa.data.sample.SettingsLanguageItem;
import net.dirox.auluxa.databinding.LayoutSettingsAudioDeviceHdmiBinding;
import net.dirox.auluxa.utils.Constant;

import java.util.ArrayList;
import java.util.List;

public class FragmentAudioDeviceHdmi extends BaseFragment {

    private LayoutSettingsAudioDeviceHdmiBinding mBinding;

    private FragmentInteraction mInteraction;

    List<SettingsLanguageItem> list;

    SettingsLanguageAdapter adapter;

    public static FragmentAudioDeviceHdmi newInstance() {

        Bundle args = new Bundle();

        FragmentAudioDeviceHdmi fragment = new FragmentAudioDeviceHdmi();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.layout_settings_audio_device_hdmi, container, false);

        mBinding.btnBackSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hideKeyboardIfNeed(getContext());
                getActivity().onBackPressed();
            }
        });

        Bundle bundle = this.getArguments();
        if (bundle != null) {
            mBinding.settingsTitleSettings.setText(bundle.getString(Constant.CLIMATE_DEVICE_NAME));
        }

        list = new ArrayList<SettingsLanguageItem>();
        list.add(new SettingsLanguageItem(0, "CABLE TV_IR", false));
        list.add(new SettingsLanguageItem(1, "BLUERAY_IR", false));
        list.add(new SettingsLanguageItem(2, "APPLE TV_IR", false));
        list.add(new SettingsLanguageItem(3, "AMPLIFIER_IR", false));

        adapter = new SettingsLanguageAdapter(getActivity(), R.layout.layout_item_settings_language, list) {
            @Override
            public void onItemSelected(int position) {
                Log.d("HDMI: ", list.get(position).getLanguage());
            }
        };
        mBinding.listAudioDeviceHdmi.setAdapter(adapter);

        mBinding.btnSaveHdmi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });

        mBinding.btnCancelHdmi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
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
