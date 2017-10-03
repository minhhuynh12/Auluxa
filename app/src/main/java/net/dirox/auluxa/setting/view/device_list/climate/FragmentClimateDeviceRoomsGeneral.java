package net.dirox.auluxa.setting.view.device_list.climate;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.dirox.auluxa.R;
import net.dirox.auluxa.activity.FragmentInteraction;
import net.dirox.auluxa.adapter.SettingsNotificationsRoomsAdapter;
import net.dirox.auluxa.common.fragment.BaseFragment;
import net.dirox.auluxa.data.sample.SettingsNotificationsRoomsItem;
import net.dirox.auluxa.databinding.LayoutSettingsClimateDeviceRoomsGeneralBinding;
import net.dirox.auluxa.utils.Constant;

import java.util.ArrayList;
import java.util.List;

public class FragmentClimateDeviceRoomsGeneral extends BaseFragment {

    private LayoutSettingsClimateDeviceRoomsGeneralBinding mBinding;

    private FragmentInteraction mInteraction;

    List<SettingsNotificationsRoomsItem> list;

    SettingsNotificationsRoomsAdapter adapter;

    public static FragmentClimateDeviceRoomsGeneral newInstance() {

        Bundle args = new Bundle();

        FragmentClimateDeviceRoomsGeneral fragment = new FragmentClimateDeviceRoomsGeneral();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.layout_settings_climate_device_rooms_general, container, false);

        mBinding.btnBackSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackAndHideKeyBoard();
            }
        });

        Bundle bundle = this.getArguments();
        if (bundle != null) {
            mBinding.settingsTitleSettings.setText(bundle.getString(Constant.CLIMATE_DEVICE_NAME));
        }

        list = new ArrayList<SettingsNotificationsRoomsItem>();
        list.add(new SettingsNotificationsRoomsItem(0, "LIVING ROOM", false));
        list.add(new SettingsNotificationsRoomsItem(1, "DINING ROOM", false));
        list.add(new SettingsNotificationsRoomsItem(2, "GAME ROOM", false));
        list.add(new SettingsNotificationsRoomsItem(3, "MASTER BEDROOM", false));
        list.add(new SettingsNotificationsRoomsItem(4, "GUEST BEDROOM", false));

        adapter = new SettingsNotificationsRoomsAdapter(getActivity(), R.layout.layout_item_settings_notification_rooms, list) {
            @Override
            public void onItemSelected(int position) {

            }
        };
        mBinding.listSettingsClimateDeviceRooms.setAdapter(adapter);

        mBinding.cbSelectRooms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mBinding.cbSelectRooms.isChecked()) {
                    adapter.checkAll(true);
                    mBinding.tvSubscribeAll.setTextColor(Color.parseColor("#18bd9b"));
                } else {
                    adapter.checkAll(false);
                    mBinding.tvSubscribeAll.setTextColor(Color.parseColor("#B0000000"));
                }
            }
        });

        mBinding.btnSaveRooms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackAndHideKeyBoard();
            }
        });

        mBinding.btnCancelRooms.setOnClickListener(new View.OnClickListener() {
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
