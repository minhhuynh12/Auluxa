package net.dirox.auluxa.setting.view.device_list.video;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
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

public class FragmentVideoDeviceRoomsGeneral extends BaseFragment {

    private LayoutSettingsClimateDeviceRoomsGeneralBinding mBinding;

    private FragmentInteraction mInteraction;

    List<SettingsNotificationsRoomsItem> list;
    public static int pos = -1;
    SettingsNotificationsRoomsAdapter adapter;

    public static FragmentVideoDeviceRoomsGeneral newInstance(int position) {

        Bundle args = new Bundle();
        pos = position;
        FragmentVideoDeviceRoomsGeneral fragment = new FragmentVideoDeviceRoomsGeneral();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.layout_settings_climate_device_rooms_general, container, false);
        list = new ArrayList<SettingsNotificationsRoomsItem>();
        switch (pos) {
            case 1:
                list.add(new SettingsNotificationsRoomsItem(0, "LIVING ROOM", false));
                list.add(new SettingsNotificationsRoomsItem(1, "DINING ROOM", false));
                list.add(new SettingsNotificationsRoomsItem(2, "GAME ROOM", false));
                list.add(new SettingsNotificationsRoomsItem(3, "MASTER BEDROOM", false));
                list.add(new SettingsNotificationsRoomsItem(4, "GUEST BEDROOM", false));
                break;

            case 2:
                mBinding.tvSubscribeAll.setVisibility(View.GONE);
                mBinding.cbSelectRooms.setVisibility(View.GONE);
                mBinding.separator2.setVisibility(View.GONE);
                list.add(new SettingsNotificationsRoomsItem(0, "TV", false));
                list.add(new SettingsNotificationsRoomsItem(1, "STB", false));
                list.add(new SettingsNotificationsRoomsItem(2, "STRM", false));
                list.add(new SettingsNotificationsRoomsItem(3, "AUDIO", false));
                break;
            case 3:
                mBinding.tvSubscribeAll.setVisibility(View.GONE);
                mBinding.cbSelectRooms.setVisibility(View.GONE);
                mBinding.separator2.setVisibility(View.GONE);
                list.add(new SettingsNotificationsRoomsItem(0, "TV", false));
                list.add(new SettingsNotificationsRoomsItem(1, "VCR", false));
                list.add(new SettingsNotificationsRoomsItem(2, "DVD", false));
                list.add(new SettingsNotificationsRoomsItem(3, "AUD", false));
                list.add(new SettingsNotificationsRoomsItem(4, "CD", false));
                list.add(new SettingsNotificationsRoomsItem(5, "OTHERS", false));
                break;

        }
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
