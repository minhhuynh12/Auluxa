package net.dirox.auluxa.setting.view.device_list.climate;

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
import net.dirox.auluxa.databinding.LayoutSettingsClimateDeviceGeneralBinding;
import net.dirox.auluxa.extras.Enumes;
import net.dirox.auluxa.utils.Constant;

import java.util.ArrayList;
import java.util.List;

public class FragmentClimateDeviceGeneral extends BaseFragment {

    private LayoutSettingsClimateDeviceGeneralBinding mBinding;

    private FragmentInteraction mInteraction;

    List<SettingsItem> list;
    SettingsMainAdapter adapter;

    public static FragmentClimateDeviceGeneral newInstance() {

        Bundle args = new Bundle();

        FragmentClimateDeviceGeneral fragment = new FragmentClimateDeviceGeneral();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.layout_settings_climate_device_general, container, false);

        Bundle bundle = this.getArguments();
        if (bundle != null) {
            mBinding.settingsTitleSettings.setText(bundle.getString(Constant.CLIMATE_DEVICE_NAME));
            mBinding.etClimateDeviceNameGeneral.setText(bundle.getString(Constant.CLIMATE_DEVICE_NAME));
            mBinding.imgClimateDeviceGeneral.setImageResource(bundle.getInt(Constant.CLIMATE_DEVICE_RESOURCE_ID));
        }

        mBinding.btnBackSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackAndHideKeyBoard();
            }
        });

        list = new ArrayList<SettingsItem>();
        list.add(new SettingsItem(0, "ROOMS"));
        list.add(new SettingsItem(1, "SELECT DEVICE"));

        switch (bundle.getInt(Constant.CLIMATE_DEVICE_ID)) {
            case 1:
                list.remove(1);
                break;
            default:
                break;
        }

        adapter = new SettingsMainAdapter(getActivity(), R.layout.item_settings, list) {
            @Override
            public void onItemSelected(int position) {
                switch (position) {
                    case 0:
                        hideKeyboardIfNeed(getContext());
                        mInteraction.openFragment(Enumes.FragmentType.FRAGMENT_SETTING_CLIMATE_DEVICE_ROOMS_GENERAL, null, Enumes.Direction.RIGHT_IN_FLIP_OUT);
                        break;
                    case 1:
                        hideKeyboardIfNeed(getContext());
                        switch (bundle.getInt(Constant.CLIMATE_DEVICE_ID)) {
                            case 0:
                                mInteraction.openFragment(Enumes.FragmentType.FRAGMENT_SETTING_CLIMATE_SELECT_DEVICE_IR_NODE, null, Enumes.Direction.RIGHT_IN_FLIP_OUT);
                                break;
                            case 3:
                                mInteraction.openFragment(Enumes.FragmentType.FRAGMENT_SETTING_CLIMATE_SELECT_DEVICE_HUMIDITY, null, Enumes.Direction.RIGHT_IN_FLIP_OUT);
                                break;
                            case 4:
                                mInteraction.openFragment(Enumes.FragmentType.FRAGMENT_SETTING_CLIMATE_SELECT_DEVICE_TEMP_SENSOR, null, Enumes.Direction.RIGHT_IN_FLIP_OUT);
                                break;
                            case 5:
                                mInteraction.openFragment(Enumes.FragmentType.FRAGMENT_SETTING_CLIMATE_SELECT_DEVICE_TEMP_HUMIDITY_SENSOR, null, Enumes.Direction.RIGHT_IN_FLIP_OUT);
                                break;
                        }

                        break;
                }
            }
        };

        mBinding.listSettingsClimateDeviceGeneral.setAdapter(adapter);

        return mBinding.getRoot();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mInteraction = (FragmentInteraction) context;
    }
}
