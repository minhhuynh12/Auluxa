package net.dirox.auluxa.setting.view.device_list.video;

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
import net.dirox.auluxa.databinding.LayoutSettingsVideoSelectDeviceBinding;
import net.dirox.auluxa.utils.Constant;
import net.dirox.auluxa.utils.Prefs;

import java.util.ArrayList;
import java.util.List;

public class FragmentVideoSelectDevice extends BaseFragment {

    private LayoutSettingsVideoSelectDeviceBinding mBinding;

    private FragmentInteraction mInteraction;

    List<SettingsLanguageItem> list;

    SettingsLanguageAdapter adapter;

    int pos;

    public static FragmentVideoSelectDevice newInstance() {

        Bundle args = new Bundle();

        FragmentVideoSelectDevice fragment = new FragmentVideoSelectDevice();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.layout_settings_video_select_device, container, false);

        mBinding.btnBackSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackAndHideKeyBoard();
            }
        });

        list = new ArrayList<SettingsLanguageItem>();
        list.add(new SettingsLanguageItem(0, "TV", false));
        list.add(new SettingsLanguageItem(1, "STB", false));
        list.add(new SettingsLanguageItem(2, "STRM", false));
        list.add(new SettingsLanguageItem(3, "AUDIO", false));

        adapter = new SettingsLanguageAdapter(getActivity(), R.layout.layout_item_settings_language, list) {
            @Override
            public void onItemSelected(int position) {
                pos = position;
                Log.d("VIDEO SELECT DEVICE: ", list.get(pos).getLanguage());
            }
        };
        mBinding.listVideoSelectDevice.setAdapter(adapter);

        mBinding.btnSaveDevice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Prefs.setStringPrefs(getContext(), Constant.VIDEO_SELECT_DEVICE, list.get(pos).getLanguage());
                getActivity().onBackPressed();
            }
        });

        mBinding.btnCancelDevice.setOnClickListener(new View.OnClickListener() {
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
