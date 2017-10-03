package net.dirox.auluxa.setting.view.device_list.video;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.dirox.auluxa.R;
import net.dirox.auluxa.activity.FragmentInteraction;
import net.dirox.auluxa.adapter.SettingsLanguageAdapter;
import net.dirox.auluxa.common.fragment.BaseFragment;
import net.dirox.auluxa.data.sample.SettingsLanguageItem;
import net.dirox.auluxa.databinding.LayoutSettingsVideoSelectDeviceManufacturerBinding;
import net.dirox.auluxa.utils.Constant;
import net.dirox.auluxa.utils.Prefs;

import java.util.ArrayList;
import java.util.List;

public class FragmentVideoSelectDeviceManufacture extends BaseFragment {

    private LayoutSettingsVideoSelectDeviceManufacturerBinding mBinding;

    private FragmentInteraction mInteraction;

    List<SettingsLanguageItem> list;

    SettingsLanguageAdapter adapter;

    int pos;

    public static FragmentVideoSelectDeviceManufacture newInstance() {

        Bundle args = new Bundle();

        FragmentVideoSelectDeviceManufacture fragment = new FragmentVideoSelectDeviceManufacture();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.layout_settings_video_select_device_manufacturer, container, false);

        mBinding.btnBackSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackAndHideKeyBoard();
            }
        });

        list = new ArrayList<SettingsLanguageItem>();
        list.add(new SettingsLanguageItem(0, "ABEX", false));
        list.add(new SettingsLanguageItem(1, "ACCESSHD", false));
        list.add(new SettingsLanguageItem(2, "ACCOR", false));
        list.add(new SettingsLanguageItem(3, "ACER", false));

        adapter = new SettingsLanguageAdapter(getActivity(), R.layout.layout_item_settings_language, list) {
            @Override
            public void onItemSelected(int position) {
                pos = position;
                Log.d("MANUFACTURER: ", list.get(pos).getLanguage());
            }
        };
        mBinding.listManuFacturer.setAdapter(adapter);

        mBinding.etSearchManufacture.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                adapter.filter(mBinding.etSearchManufacture.getText().toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mBinding.btnSaveFacturer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Prefs.setStringPrefs(getContext(), Constant.VIDEO_SELECT_MANUFACTURE, list.get(pos).getLanguage());
                getActivity().onBackPressed();
            }
        });

        mBinding.btnCancelFacturer.setOnClickListener(new View.OnClickListener() {
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
