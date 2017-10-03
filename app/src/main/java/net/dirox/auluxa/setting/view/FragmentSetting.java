package net.dirox.auluxa.setting.view;

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
import net.dirox.auluxa.databinding.LayoutSettingsMainBinding;
import net.dirox.auluxa.extras.Enumes;

import java.util.ArrayList;
import java.util.List;

public class FragmentSetting extends BaseFragment {

    private LayoutSettingsMainBinding mBinding;

    private FragmentInteraction mInteraction;

    List<SettingsItem> list;
    SettingsMainAdapter adapter;

    public static FragmentSetting newInstance() {

        Bundle args = new Bundle();

        FragmentSetting fragment = new FragmentSetting();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.layout_settings_main, container, false);

        mBinding.btnBackSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mInteraction.openFragment(Enumes.FragmentType.FRAGMENT_MAIN_HOME, null, Enumes.Direction.RIGHT_IN);
            }
        });

        list = new ArrayList<SettingsItem>();
        list.add(new SettingsItem(0, "DATE & TIME"));
        list.add(new SettingsItem(1, "LANGUAGE"));
        list.add(new SettingsItem(2, "CLIMATE"));
        list.add(new SettingsItem(3, "NOTIFICATIONS"));
        list.add(new SettingsItem(4, "DEVICE LIST"));
        list.add(new SettingsItem(5, "ROOM IMAGE"));
        list.add(new SettingsItem(6, "USER MANAGEMENT"));


        adapter = new SettingsMainAdapter(getActivity(), R.layout.item_settings, list) {
            @Override
            public void onItemSelected(int position) {
                switch (position) {
                    case 0:
                        mInteraction.openFragment(Enumes.FragmentType.FRAGMENT_SETTING_DATE_TIME, null, Enumes.Direction.RIGHT_IN);
                        break;
                    case 1:
                        mInteraction.openFragment(Enumes.FragmentType.FRAGMENT_SETTING_LANGUAGE, null, Enumes.Direction.RIGHT_IN);
                        break;
                    case 2:
                        mInteraction.openFragment(Enumes.FragmentType.FRAGMENT_SETTING_CLIMATE, null, Enumes.Direction.RIGHT_IN);
                        break;
                    case 3:
                        mInteraction.openFragment(Enumes.FragmentType.FRAGMENT_SETTING_NOTIFICATIONS, null, Enumes.Direction.RIGHT_IN);
                        break;
                    case 4:
                        mInteraction.openFragment(Enumes.FragmentType.FRAGMENT_SETTING_DEVICE_LIST, null, Enumes.Direction.RIGHT_IN);
                        break;
                    case 5:
                        mInteraction.openFragment(Enumes.FragmentType.FRAGMENT_SETTING_ROOM_IMAGE, null, Enumes.Direction.RIGHT_IN);
                        break;
                    case 6:
                        mInteraction.openFragment(Enumes.FragmentType.FRAGMENT_SETTING_LIST_USER_MANAGEMENT, null, Enumes.Direction.RIGHT_IN);
                        break;
                }
            }
        };
        mBinding.listSettings.setAdapter(adapter);

        return mBinding.getRoot();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mInteraction = (FragmentInteraction) context;
    }

}
