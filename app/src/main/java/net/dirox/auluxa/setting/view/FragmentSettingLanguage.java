package net.dirox.auluxa.setting.view;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.dirox.auluxa.R;
import net.dirox.auluxa.activity.FragmentInteraction;
import net.dirox.auluxa.adapter.SettingsLanguageAdapter;
import net.dirox.auluxa.common.fragment.BaseFragment;
import net.dirox.auluxa.data.sample.SettingsLanguageItem;
import net.dirox.auluxa.databinding.LayoutSettingsLanguageBinding;

import java.util.ArrayList;
import java.util.List;

public class FragmentSettingLanguage extends BaseFragment {

    private LayoutSettingsLanguageBinding mBinding;

    private FragmentInteraction mInteraction;

    List<SettingsLanguageItem> list;

    SettingsLanguageAdapter adapter;

    public static FragmentSettingLanguage newInstance() {

        Bundle args = new Bundle();

        FragmentSettingLanguage fragment = new FragmentSettingLanguage();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.layout_settings_language, container, false);

        mBinding.btnBackSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });

        mBinding.btnSaveLanguage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });

        mBinding.btnCancelLanguage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });

        list = new ArrayList<SettingsLanguageItem>();
        list.add(new SettingsLanguageItem(0, "ENGLISH", false));
        list.add(new SettingsLanguageItem(1, "CHINESE (SIMPLIFY)", false));
        list.add(new SettingsLanguageItem(2, "CHINESE (TRADITIONAL)", false));
        list.add(new SettingsLanguageItem(3, "ARABIC", false));
        list.add(new SettingsLanguageItem(4, "GERMAN", false));
        list.add(new SettingsLanguageItem(5, "SPANISH", false));

        adapter = new SettingsLanguageAdapter(getActivity(), R.layout.layout_item_settings_language, list) {
            @Override
            public void onItemSelected(int position) {
            }
        };
        mBinding.listSettingsLanguage.setAdapter(adapter);

        return mBinding.getRoot();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mInteraction = (FragmentInteraction) context;
    }
}
