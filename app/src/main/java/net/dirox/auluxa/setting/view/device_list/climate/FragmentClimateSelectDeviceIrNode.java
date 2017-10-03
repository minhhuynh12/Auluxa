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
import net.dirox.auluxa.databinding.LayoutSettingsClimateSelectDeviceIrnodeBinding;
import net.dirox.auluxa.extras.Enumes;

import java.util.ArrayList;
import java.util.List;

public class FragmentClimateSelectDeviceIrNode extends BaseFragment {

    private LayoutSettingsClimateSelectDeviceIrnodeBinding mBinding;

    private FragmentInteraction mInteraction;

    List<SettingsItem> list;
    SettingsMainAdapter adapter;

    public static FragmentClimateSelectDeviceIrNode newInstance() {

        Bundle args = new Bundle();

        FragmentClimateSelectDeviceIrNode fragment = new FragmentClimateSelectDeviceIrNode();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.layout_settings_climate_select_device_irnode, container, false);

        mBinding.btnBackSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackAndHideKeyBoard();
            }
        });

        list = new ArrayList<SettingsItem>();
        list.add(new SettingsItem(0, "CATEGORY"));
        list.add(new SettingsItem(1, "MANUFACTURER"));

        adapter = new SettingsMainAdapter(getActivity(), R.layout.item_settings, list) {
            @Override
            public void onItemSelected(int position) {
                switch (position) {
                    case 0:
                        hideKeyboardIfNeed(getContext());
                        mInteraction.openFragment(Enumes.FragmentType.FRAGMENT_SETTING_CLIMATE_SELECT_DEVICE_IR_NODE_CATEGORY, null, Enumes.Direction.RIGHT_IN_FLIP_OUT);
                        break;
                    case 1:
                        hideKeyboardIfNeed(getContext());
                        mInteraction.openFragment(Enumes.FragmentType.FRAGMENT_SETTING_CLIMATE_SELECT_DEVICE_IR_NODE_MANUFACTURER, null, Enumes.Direction.RIGHT_IN_FLIP_OUT);
                        break;
                }
            }
        };

        mBinding.listSettingsDeviceIrnode.setAdapter(adapter);

        return mBinding.getRoot();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mInteraction = (FragmentInteraction) context;
    }
}
