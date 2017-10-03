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
import net.dirox.auluxa.databinding.LayoutSettingsAuidoDeviceMappingBinding;
import net.dirox.auluxa.extras.Enumes;
import net.dirox.auluxa.utils.Constant;

import java.util.ArrayList;
import java.util.List;

public class FragmentAudioDeviceMapping extends BaseFragment {

    private LayoutSettingsAuidoDeviceMappingBinding mBinding;

    private FragmentInteraction mInteraction;

    List<SettingsItem> list;
    SettingsMainAdapter adapter;

    public static FragmentAudioDeviceMapping newInstance() {

        Bundle args = new Bundle();

        FragmentAudioDeviceMapping fragment = new FragmentAudioDeviceMapping();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.layout_settings_auido_device_mapping, container, false);

        mBinding.btnBackSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hideKeyboardIfNeed(getContext());
                getActivity().onBackPressed();
            }
        });

        list = new ArrayList<SettingsItem>();
        list.add(new SettingsItem(0, "HDMI 1"));
        list.add(new SettingsItem(1, "HDMI 2"));
        list.add(new SettingsItem(2, "HDMI 3"));

        adapter = new SettingsMainAdapter(getActivity(), R.layout.item_settings, list) {
            @Override
            public void onItemSelected(int position) {
                Bundle mBundle = new Bundle();
                mBundle.putString(Constant.CLIMATE_DEVICE_NAME, list.get(position).getTitleSettings());
                mInteraction.openFragment(Enumes.FragmentType.FRAGMENT_SETTING_AUDIO_DEVICE_HDMI, mBundle, Enumes.Direction.RIGHT_IN);
            }
        };
        mBinding.listAudioDeviceMapping.setAdapter(adapter);

        return mBinding.getRoot();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mInteraction = (FragmentInteraction) context;
    }
}
