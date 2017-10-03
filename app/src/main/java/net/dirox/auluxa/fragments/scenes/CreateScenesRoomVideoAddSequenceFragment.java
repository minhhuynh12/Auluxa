package net.dirox.auluxa.fragments.scenes;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import net.dirox.auluxa.R;
import net.dirox.auluxa.adapter.scenes.ScenesCreateRoomVideoRemotePagerAdaper;
import net.dirox.auluxa.common.fragment.BaseFragment;
import net.dirox.auluxa.databinding.FragmentScenesCreateRoomVideoAddSequenceBinding;

/**
 * Created by an on 6/29/2017.
 */

public class CreateScenesRoomVideoAddSequenceFragment extends BaseFragment implements ViewPager.OnPageChangeListener {

    private FragmentScenesCreateRoomVideoAddSequenceBinding mBinding;
    private Device mDeviceType;
    private ScenesCreateRoomVideoRemotePagerAdaper adapter;
    int pos = -1, countList = 1;

    private int currentSelectButtonId = -1;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_scenes_create_room_video_add_sequence, container, false);
        String deviceType = getArguments().getString("DeviceType");
        pos = getArguments().getInt("position");
        countList = getArguments().getInt("arraySequence");
        mDeviceType = Device.fromString(deviceType);

        adapter = new ScenesCreateRoomVideoRemotePagerAdaper(getChildFragmentManager(), mDeviceType);
        mBinding.viewPager.setAdapter(adapter);
        mBinding.viewPager.addOnPageChangeListener(this);

        mBinding.pageIndicator.setViewPager(mBinding.viewPager);
        mBinding.pageIndicator.setCurrentItem(0);
        if (adapter.getCount() > 1)
            mBinding.pageIndicator.setVisibility(View.VISIBLE);
        else
            mBinding.pageIndicator.setVisibility(View.INVISIBLE);

        mBinding.pageIndicator.setFillColor(ContextCompat.getColor(getContext(), R.color.auluxaGreen));
        mBinding.pageIndicator.setPageColor(ContextCompat.getColor(getContext(), R.color.BgDarkGray));

        mBinding.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });

        mBinding.btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (pos < countList && pos != -1) {
                    CreateScenesRoomVideoListSequenceFragment.updateSequence( currentSelectButtonId, pos);
                    getActivity().onBackPressed();
                } else {
                    CreateScenesRoomVideoListSequenceFragment.addNewSequence(currentSelectButtonId);
                    getActivity().onBackPressed();
                }
            }
        });
        return mBinding.getRoot();
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        Animation animation;
        switch (position) {
            case 0:
                animation = AnimationUtils.loadAnimation(getActivity(), R.anim.grow_from_bottom);
                mBinding.btnSave.setVisibility(View.VISIBLE);
                mBinding.btnSave.startAnimation(animation);
                break;
            default:
                animation = AnimationUtils.loadAnimation(getActivity(), R.anim.shrink_from_top);
                mBinding.btnSave.setVisibility(View.GONE);
                mBinding.btnSave.startAnimation(animation);
                break;
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    public void setActionButtonSelected(int buttonId) {
        currentSelectButtonId = buttonId;

        if (currentSelectButtonId == -1) {
            mBinding.btnSave.setEnabled(false);
        } else {
            mBinding.btnSave.setEnabled(true);
        }
    }


    public enum Device {
        CABLE_TV("Cable TV"),
        APPLE_TV("Apple TV"),
        BLUERAY("Blueray"),
        AMPLIFIER("Amplifier"),
        MATRIX("Matrix"),
        MEDIA_SERVER("Media server");

        final String mDevice;

        Device(String value) {
            mDevice = value;
        }

        @Override
        public String toString() {
            return mDevice;
        }

        public static Device fromString(String text) {
            for (Device b : Device.values()) {
                if (b.mDevice.equalsIgnoreCase(text)) {
                    return b;
                }
            }
            return null;
        }
    }
}
