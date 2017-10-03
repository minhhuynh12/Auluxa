package net.dirox.auluxa.setting.view;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.dirox.auluxa.R;
import net.dirox.auluxa.activity.FragmentInteraction;
import net.dirox.auluxa.adapter.SettingsClimateAdapter;
import net.dirox.auluxa.common.fragment.BaseFragment;
import net.dirox.auluxa.data.sample.SettingsClimateItem;
import net.dirox.auluxa.databinding.LayoutSettingsClimateBinding;
import net.dirox.auluxa.utils.Constant;
import net.dirox.auluxa.utils.Prefs;

import java.util.ArrayList;
import java.util.List;

public class FragmentSettingClimate extends BaseFragment {

    private LayoutSettingsClimateBinding mBinding;

    private FragmentInteraction mInteraction;

    List<SettingsClimateItem> list;

    SettingsClimateAdapter adapter;

    int pos = 0;

    public static FragmentSettingClimate newInstance() {

        Bundle args = new Bundle();

        FragmentSettingClimate fragment = new FragmentSettingClimate();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.layout_settings_climate, container, false);

        mBinding.btnBackSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });

        list = new ArrayList<>();
        list.add(new SettingsClimateItem(0, "CELSIUS", false));
        list.add(new SettingsClimateItem(1, "FAHRENHEIT", false));


        adapter = new SettingsClimateAdapter(getActivity(), R.layout.layout_item_settings_climate, list, Prefs.getStringPrefs(getContext(), Constant.CLIMATE_UNIT).equals("FAHRENHEIT") ? 1 : 0) {
            @Override
            public void onItemSelected(int position) {
                pos = position;
            }
        };

        mBinding.listSettingsClimate.setAdapter(adapter);

        mBinding.btnSaveClimate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (pos == 0)
                    Prefs.setStringPrefs(getContext(), Constant.CLIMATE_UNIT, list.get(0).getClimate());
                else
                    Prefs.setStringPrefs(getContext(), Constant.CLIMATE_UNIT, list.get(1).getClimate());

                getActivity().onBackPressed();
            }
        });

        mBinding.btnCancelClimate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
