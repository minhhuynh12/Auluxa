package net.dirox.auluxa.setting.view;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.dirox.auluxa.R;
import net.dirox.auluxa.activity.FragmentInteraction;
import net.dirox.auluxa.adapter.SettingsTimeZoneAdapter;
import net.dirox.auluxa.common.activity.BaseActivity;
import net.dirox.auluxa.common.fragment.BaseFragment;
import net.dirox.auluxa.data.sample.SettingsTimezoneItem;
import net.dirox.auluxa.databinding.LayoutSettingsTimezoneBinding;
import net.dirox.auluxa.utils.Constant;
import net.dirox.auluxa.utils.Prefs;

import java.util.ArrayList;
import java.util.List;

public class FragmentSettingTimeZone extends BaseFragment {

    private LayoutSettingsTimezoneBinding mBinding;

    private FragmentInteraction mInteraction;

    List<SettingsTimezoneItem> list;
    SettingsTimeZoneAdapter adapter;

    public static FragmentSettingTimeZone newInstance() {

        Bundle args = new Bundle();

        FragmentSettingTimeZone fragment = new FragmentSettingTimeZone();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.layout_settings_timezone, container, false);

        mBinding.btnBackSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackAndHideKeyBoard();
            }
        });

        list = new ArrayList<SettingsTimezoneItem>();
        list.add(new SettingsTimezoneItem(0, "Hanoi, Vietnam", false));
        list.add(new SettingsTimezoneItem(1, "Ho Chi Minh, Vietnam", false));
        list.add(new SettingsTimezoneItem(2, "Hong Kong, Hong Kong", false));
        list.add(new SettingsTimezoneItem(3, "Los Angeles, America", false));
        list.add(new SettingsTimezoneItem(4, "Hanoi, Vietnam", false));
        list.add(new SettingsTimezoneItem(5, "Ho Chi Minh", false));

        adapter = new SettingsTimeZoneAdapter(getActivity(), R.layout.layout_item_settings_timezone, list) {
            @Override
            public void onItemSelected(int position) {
                ((BaseActivity) getActivity()).hideKeyboard();
                Prefs.setStringPrefs(getContext(), Constant.PREF_KEY, list.get(position).getTimeZone());
                getActivity().onBackPressed();
            }
        };

        mBinding.listTimeZone.setAdapter(adapter);

        mBinding.etSearchTimeZone.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                adapter.filter(mBinding.etSearchTimeZone.getText().toString());

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mBinding.settingsmainSettingsBg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((BaseActivity) getActivity()).hideKeyboard();
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
