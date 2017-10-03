package net.dirox.auluxa.setting.view.device_list.climate;

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
import net.dirox.auluxa.databinding.LayoutSettingsClimateSelectDeviceIrNodeManufacturerBinding;

import java.util.ArrayList;
import java.util.List;

public class FragmentClimateSelectDeviceIrNodeManufacture extends BaseFragment {

    private LayoutSettingsClimateSelectDeviceIrNodeManufacturerBinding mBinding;

    private FragmentInteraction mInteraction;

    List<SettingsLanguageItem> list;

    SettingsLanguageAdapter adapter;

    public static FragmentClimateSelectDeviceIrNodeManufacture newInstance() {

        Bundle args = new Bundle();

        FragmentClimateSelectDeviceIrNodeManufacture fragment = new FragmentClimateSelectDeviceIrNodeManufacture();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.layout_settings_climate_select_device_ir_node_manufacturer, container, false);

        mBinding.btnBackSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackAndHideKeyBoard();
            }
        });

        mBinding.btnSaveFacturer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackAndHideKeyBoard();
            }
        });

        mBinding.btnCancelFacturer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
                Log.d("MANUFACTURER: ", list.get(position).getLanguage());
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
        return mBinding.getRoot();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mInteraction = (FragmentInteraction) context;
    }
}
