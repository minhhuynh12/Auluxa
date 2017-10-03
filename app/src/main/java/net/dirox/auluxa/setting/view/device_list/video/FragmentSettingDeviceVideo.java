package net.dirox.auluxa.setting.view.device_list.video;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.dirox.auluxa.R;
import net.dirox.auluxa.activity.FragmentInteraction;
import net.dirox.auluxa.adapter.SettingsClimateDevicesAdapter;
import net.dirox.auluxa.common.fragment.BaseFragment;
import net.dirox.auluxa.data.sample.SettingsClimateDevicesItem;
import net.dirox.auluxa.databinding.LayoutSettingsDeviceVideoBinding;
import net.dirox.auluxa.extras.Enumes;
import net.dirox.auluxa.utils.Constant;

import java.util.ArrayList;
import java.util.List;

public class FragmentSettingDeviceVideo extends BaseFragment {

    private LayoutSettingsDeviceVideoBinding mBinding;

    private FragmentInteraction mInteraction;

    List<SettingsClimateDevicesItem> list;
    static SettingsClimateDevicesAdapter adapter;


    public static FragmentSettingDeviceVideo newInstance() {

        Bundle args = new Bundle();

        FragmentSettingDeviceVideo fragment = new FragmentSettingDeviceVideo();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        list = new ArrayList<SettingsClimateDevicesItem>();
        list.add(new SettingsClimateDevicesItem(0, "CABLE TV", R.drawable.ir_node));
        list.add(new SettingsClimateDevicesItem(1, "NOW TV", R.drawable.ir_node));
        adapter = new SettingsClimateDevicesAdapter(getActivity(), R.layout.layout_item_settings_climate_device, list) {
            @Override
            public void onItemSelected(int position) {
                Bundle bundle = new Bundle();
                bundle.putInt(Constant.CLIMATE_DEVICE_ID, list.get(position).getId());
                bundle.putString(Constant.CLIMATE_DEVICE_NAME, list.get(position).getClimateDeviceName());
                bundle.putInt(Constant.CLIMATE_DEVICE_RESOURCE_ID, list.get(position).getResourdId());
                mInteraction.openFragment(Enumes.FragmentType.FRAGMENT_SETTING_VIDEO_DEVICE_GENERAL, bundle, Enumes.Direction.RIGHT_IN);
            }
        };
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.layout_settings_device_video, container, false);

        mBinding.btnBackSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackAndHideKeyBoard();
            }
        });
        mBinding.listSettingsVideoDevice.setAdapter(adapter);

        return mBinding.getRoot();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mInteraction = (FragmentInteraction) context;
    }
}
