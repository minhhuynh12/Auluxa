package net.dirox.auluxa.setting.view.device_list.climate;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.dirox.auluxa.R;
import net.dirox.auluxa.activity.FragmentInteraction;
import net.dirox.auluxa.adapter.SettingsNotificationsRoomsAdapter;
import net.dirox.auluxa.common.fragment.BaseFragment;
import net.dirox.auluxa.data.sample.SettingsNotificationsRoomsItem;
import net.dirox.auluxa.databinding.LayoutSettingsClimateSelectDeviceIrNodeCategoryBinding;

import java.util.ArrayList;
import java.util.List;

public class FragmentClimateSelectDeviceIrNodeCategory extends BaseFragment {

    private LayoutSettingsClimateSelectDeviceIrNodeCategoryBinding mBinding;

    private FragmentInteraction mInteraction;

    List<SettingsNotificationsRoomsItem> list;

    SettingsNotificationsRoomsAdapter adapter;

    public static FragmentClimateSelectDeviceIrNodeCategory newInstance() {

        Bundle args = new Bundle();

        FragmentClimateSelectDeviceIrNodeCategory fragment = new FragmentClimateSelectDeviceIrNodeCategory();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.layout_settings_climate_select_device_ir_node_category, container, false);

        mBinding.btnBackSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });

        list = new ArrayList<SettingsNotificationsRoomsItem>();
        list.add(new SettingsNotificationsRoomsItem(0, "AC1", false));
        list.add(new SettingsNotificationsRoomsItem(1, "AC2", false));

        adapter = new SettingsNotificationsRoomsAdapter(getActivity(), R.layout.layout_item_settings_notification_rooms, list) {
            @Override
            public void onItemSelected(int position) {

            }
        };
        mBinding.listIrnodeCategory.setAdapter(adapter);

        mBinding.btnSaveCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackAndHideKeyBoard();
            }
        });

        mBinding.btnCancelCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackAndHideKeyBoard();
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
