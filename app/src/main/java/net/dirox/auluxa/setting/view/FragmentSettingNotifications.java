package net.dirox.auluxa.setting.view;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.dirox.auluxa.R;
import net.dirox.auluxa.activity.FragmentInteraction;
import net.dirox.auluxa.adapter.SettingsNotificationsAdapter;
import net.dirox.auluxa.common.fragment.BaseFragment;
import net.dirox.auluxa.data.sample.NotificationsItem;
import net.dirox.auluxa.databinding.LayoutSettingsNotificationsBinding;
import net.dirox.auluxa.extras.Enumes;

import java.util.ArrayList;
import java.util.List;

public class FragmentSettingNotifications extends BaseFragment {

    private LayoutSettingsNotificationsBinding mBinding;

    private FragmentInteraction mInteraction;

    List<NotificationsItem> list;
    SettingsNotificationsAdapter adapter;

    public static FragmentSettingNotifications newInstance() {

        Bundle args = new Bundle();

        FragmentSettingNotifications fragment = new FragmentSettingNotifications();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.layout_settings_notifications, container, false);

        mBinding.btnBackSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });

        list = new ArrayList<NotificationsItem>();
        list.add(new NotificationsItem(0, "ENERGY MANAGEMENT"));
        list.add(new NotificationsItem(1, "ROOMS"));
        list.add(new NotificationsItem(2, "SECURITY"));


        adapter = new SettingsNotificationsAdapter(getActivity(), R.layout.layout_item_settings_notifications, list) {
            @Override
            public void onItemSelected(int position) {
                switch (position) {
                    case 0:
                        mInteraction.openFragment(Enumes.FragmentType.FRAGMENT_SETTING_NOTIFICATIONS_ENERGY, null, Enumes.Direction.RIGHT_IN);
                        break;
                    case 1:
                        mInteraction.openFragment(Enumes.FragmentType.FRAGMENT_SETTING_NOTIFICATIONS_ROOMS, null, Enumes.Direction.RIGHT_IN);
                        break;
                    case 2:
                        break;

                }

            }
        };

        mBinding.listNotification.setAdapter(adapter);

        return mBinding.getRoot();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mInteraction = (FragmentInteraction) context;
    }
}
