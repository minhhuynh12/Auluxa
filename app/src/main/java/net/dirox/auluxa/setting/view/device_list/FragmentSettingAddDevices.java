package net.dirox.auluxa.setting.view.device_list;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.dirox.auluxa.R;
import net.dirox.auluxa.activity.FragmentInteraction;
import net.dirox.auluxa.adapter.AddDevicesAdapter;
import net.dirox.auluxa.common.fragment.BaseFragment;
import net.dirox.auluxa.databinding.LayoutSettingsAddDevicesBinding;
import net.dirox.auluxa.extras.Enumes;

public class FragmentSettingAddDevices extends BaseFragment {

    private LayoutSettingsAddDevicesBinding mBinding;

    private FragmentInteraction mInteraction;


    public static FragmentSettingAddDevices newInstance() {

        Bundle args = new Bundle();

        FragmentSettingAddDevices fragment = new FragmentSettingAddDevices();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.layout_settings_add_devices, container, false);

        mBinding.btnBackSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });

        AddDevicesAdapter adapter = new AddDevicesAdapter(getContext()){
            @Override
            public void onItemClicked(int position) {
                switch (position)
                {
                    // Audio
                    case 0:
                        mInteraction.openFragment(Enumes.FragmentType.FRAGMENT_ONE_BUTTON_ON_OFF, null, Enumes.Direction.RIGHT_IN);
                        break;
                    case 1:
                        mInteraction.openFragment(Enumes.FragmentType.FRAGMENT_TWO_BUTTON_ON_OFF, null, Enumes.Direction.RIGHT_IN);
                        break;

                    // Climate
                    case 2:
                        mInteraction.openFragment(Enumes.FragmentType.FRAGMENT_SETTING_CLIMATE_SELECT_DEVICE_HUMIDITY, null, Enumes.Direction.RIGHT_IN);
                        break;
                    case 3:
                        mInteraction.openFragment(Enumes.FragmentType.FRAGMENT_SETTING_CLIMATE_SELECT_DEVICE_TEMP_SENSOR, null, Enumes.Direction.RIGHT_IN);
                        break;

                    // Security
                    case 4:
                        mInteraction.openFragment(Enumes.FragmentType.FRAGMENT_DOOR_LOCK, null, Enumes.Direction.RIGHT_IN);
                        break;
                    case 5:
                        mInteraction.openFragment(Enumes.FragmentType.FRAGMENT_ALARM, null, Enumes.Direction.RIGHT_IN);
                        break;
                    case 6:
                        mInteraction.openFragment(Enumes.FragmentType.FRAGMENT_SENSOR, null, Enumes.Direction.RIGHT_IN);
                        break;
                    case 7:
                        mInteraction.openFragment(Enumes.FragmentType.FRAGMENT_AULUXA_DOOR_LOCK, null, Enumes.Direction.RIGHT_IN);
                        break;
                    case 8:
                        mInteraction.openFragment(Enumes.FragmentType.FRAGMENT_DOOR_BELL, null, Enumes.Direction.RIGHT_IN);
                        break;

                }
            }
        };

        mBinding.list3rdParty.setAdapter(adapter);

        mBinding.button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBinding.button2.setVisibility(View.INVISIBLE);
                mBinding.buttonSearching.setVisibility(View.VISIBLE);

                if (mBinding.buttonSearching.getVisibility() == View.VISIBLE) {
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            // Hide view after 1 seconds
                            mBinding.buttonSearching.setVisibility(View.GONE);
                            mBinding.button2.setVisibility(View.VISIBLE);
                        }
                    }, 1000);
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
