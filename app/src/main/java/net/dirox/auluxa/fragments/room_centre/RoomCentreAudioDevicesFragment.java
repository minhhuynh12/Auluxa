package net.dirox.auluxa.fragments.room_centre;

import android.content.Context;
import android.content.DialogInterface;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;

import net.dirox.auluxa.R;
import net.dirox.auluxa.activity.FragmentInteraction;
import net.dirox.auluxa.adapter.audio.RoomCentreAudioDevicesAdapter;
import net.dirox.auluxa.common.activity.BaseActivity;
import net.dirox.auluxa.common.fragment.BaseFragment;
import net.dirox.auluxa.data.GeneratorSampleData;
import net.dirox.auluxa.data.sample.AudioDeviceItem;
import net.dirox.auluxa.data.sample.AudioGroupDeviceItem;
import net.dirox.auluxa.databinding.FragmentRoomCentreAudioDevicesBinding;
import net.dirox.auluxa.extras.Enumes;
import net.dirox.auluxa.widget.SimpleDividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by an on 6/26/2017.
 */

public class RoomCentreAudioDevicesFragment extends BaseFragment {

    private FragmentInteraction mInteraction;
    FragmentRoomCentreAudioDevicesBinding mBinding;
    RoomCentreAudioDevicesAdapter audioDevicesAdapter;

    private static List<AudioGroupDeviceItem> listGroupAudioDevice;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_room_centre_audio_devices, container, false);
        mBinding.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        mBinding.recyclerView.setLayoutManager(linearLayoutManager);
        RecyclerView.ItemDecoration dividerItemDecoration = new SimpleDividerItemDecoration(getContext(), R.drawable.audio_device_line_divider);
        mBinding.recyclerView.addItemDecoration(dividerItemDecoration);

        audioDevicesAdapter = new RoomCentreAudioDevicesAdapter(getActivity(), listGroupAudioDevice) {

            @Override
            public void onMoreItemClick(int viewType, int groupPosition, int childPosition, int selectedIndex) {

                switch (selectedIndex) {
                    case 0:
                        Bundle bundle = new Bundle();
                        bundle.putString("Object", new Gson().toJson(listGroupAudioDevice.get(groupPosition)));
                        mInteraction.openFragment(Enumes.FragmentType.FRAGMENT_ROOM_CENTRE_AUDIO_SETTINGS, bundle, Enumes.Direction.RIGHT_IN);
                        break;

                    case 1:
                        mInteraction.openFragment(Enumes.FragmentType.FRAGMENT_ROOM_CENTRE_AUDIO_CREATE_GROUP, null, Enumes.Direction.RIGHT_IN);
                        break;
                }
            }

            @Override
            public void onMoreGroupItemClick(int groupPosition, int selectedIndex) {
                switch (selectedIndex) {
                    case 0:
                        Bundle bundle = new Bundle();
                        bundle.putString("Object", new Gson().toJson(listGroupAudioDevice.get(groupPosition)));
                        bundle.putInt("position", groupPosition);
                        mInteraction.openFragment(Enumes.FragmentType.FRAGMENT_ROOM_CENTRE_AUDIO_CREATE_GROUP, bundle, Enumes.Direction.RIGHT_IN);
                        break;

                    case 1:
                        ((BaseActivity)getActivity()).showAlertConfirmTwoButton("AULUXA!", "Are you sure delete this group?", getActivity(), "CANCEL", "DELETE", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        }, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                deleteGroupAudioDevice(groupPosition);
                                audioDevicesAdapter.notifyDataSetChanged();
                            }
                        });
                        break;
                }
            }

            @Override
            public void onGroupExpanded(int groupIndex) {
                linearLayoutManager.scrollToPositionWithOffset(groupIndex, 0);
            }
        };

        mBinding.recyclerView.setAdapter(audioDevicesAdapter);
        return mBinding.getRoot();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mInteraction = (FragmentInteraction) context;
        listGroupAudioDevice = generateSampleData();
    }

    public void onResumeFromBackStack() {
        if (audioDevicesAdapter != null && listGroupAudioDevice != null) {
            audioDevicesAdapter.setData(listGroupAudioDevice);
        }
    }

    private List<AudioGroupDeviceItem> generateSampleData() {
        List<AudioGroupDeviceItem> listGroupAudioDevice = new ArrayList<>();

        AudioGroupDeviceItem groupAudioDevice;
        // generate group single device
        List<AudioDeviceItem> singleListDevice;
        for (int i = 0; i < GeneratorSampleData.audioDeviceItems.size(); i++) {
            singleListDevice = new ArrayList<>();
            singleListDevice.add(GeneratorSampleData.audioDeviceItems.get(i));
            groupAudioDevice = new AudioGroupDeviceItem("", singleListDevice);
            listGroupAudioDevice.add(groupAudioDevice);
        }

        return listGroupAudioDevice;
    }

    public static void addNewGroupAudioDevice(AudioGroupDeviceItem groupDeviceItem) {
        if (listGroupAudioDevice != null) {
            listGroupAudioDevice.add(groupDeviceItem);
        }
    }

    public static void deleteGroupAudioDevice(int groupDeviceItemPosition) {
        if (listGroupAudioDevice != null) {
            listGroupAudioDevice.remove(groupDeviceItemPosition);
        }
    }

    public static void updateGroupAudioDevice(int position, AudioGroupDeviceItem groupDeviceItemPosition) {
        if (listGroupAudioDevice != null) {
            listGroupAudioDevice.set(position, groupDeviceItemPosition);
        }
    }

}
