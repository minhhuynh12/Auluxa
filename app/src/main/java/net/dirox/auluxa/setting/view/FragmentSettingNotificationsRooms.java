package net.dirox.auluxa.setting.view;

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
import net.dirox.auluxa.databinding.LayoutSettingsNotificationsRoomsBinding;

import java.util.ArrayList;
import java.util.List;

public class FragmentSettingNotificationsRooms extends BaseFragment {

    private LayoutSettingsNotificationsRoomsBinding mBinding;

    private FragmentInteraction mInteraction;

    List<SettingsNotificationsRoomsItem> list;

    SettingsNotificationsRoomsAdapter adapter;

    public static FragmentSettingNotificationsRooms newInstance() {

        Bundle args = new Bundle();

        FragmentSettingNotificationsRooms fragment = new FragmentSettingNotificationsRooms();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.layout_settings_notifications_rooms, container, false);

        mBinding.btnBackSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });

        list = new ArrayList<SettingsNotificationsRoomsItem>();
        list.add(new SettingsNotificationsRoomsItem(0, "MY LIVING ROOM", false));
        list.add(new SettingsNotificationsRoomsItem(1, "GUEST ROOM", false));
        list.add(new SettingsNotificationsRoomsItem(2, "GAMING ROOM", false));
        list.add(new SettingsNotificationsRoomsItem(3, "MASTER BEDROOM", false));
        list.add(new SettingsNotificationsRoomsItem(4, "BEDROOM", false));

        adapter = new SettingsNotificationsRoomsAdapter(getActivity(), R.layout.layout_item_settings_notification_rooms, list) {
            @Override
            public void onItemSelected(int position) {
            }
        };
        mBinding.listNotificationsRooms.setAdapter(adapter);

        mBinding.cbSubscribeAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mBinding.cbSubscribeAll.isChecked()) {
                    adapter.checkAll(true);
                    mBinding.tvSubscribeAll.setTextColor(Color.parseColor("#18bd9b"));
                } else {
                    adapter.checkAll(false);
                    mBinding.tvSubscribeAll.setTextColor(Color.parseColor("#B0000000"));
                }
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
