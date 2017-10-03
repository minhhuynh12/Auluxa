package net.dirox.auluxa.fragments.room_centre;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.dirox.auluxa.R;
import net.dirox.auluxa.activity.FragmentInteraction;
import net.dirox.auluxa.adapter.RoomCentreAudioAVDevicesAdapter;
import net.dirox.auluxa.adapter.RoomCentreAudioMusicAppAdapter;
import net.dirox.auluxa.common.fragment.BaseFragment;
import net.dirox.auluxa.data.sample.AudioItem;
import net.dirox.auluxa.databinding.FragmentRoomCentreAudioBinding;
import net.dirox.auluxa.extras.Enumes;

import java.util.ArrayList;

import butterknife.OnClick;

/**
 * Created by an on 6/12/2017.
 */

public class RoomCentreAudioFragment extends BaseFragment {
    private FragmentInteraction mInteraction;
    FragmentRoomCentreAudioBinding mBinding;
    private ArrayList<AudioItem> audioItems;
    static RoomCentreAudioAVDevicesAdapter devicesAdapter;
    RecyclerView.LayoutManager deviceLayoutManager;

    public static RoomCentreAudioFragment newInstance() {
        Bundle args = new Bundle();
        RoomCentreAudioFragment fragment = new RoomCentreAudioFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        audioItems = new ArrayList<>();
        audioItems.add(new AudioItem("p00", "AMPLIFIER", R.drawable.spotify_2x));
        audioItems.add(new AudioItem("p01", "CABLE TV", R.drawable.sq_2x));
        audioItems.add(new AudioItem("p02", "APPLE TV", R.drawable.os_2x));
        audioItems.add(new AudioItem("p03", "MATRIX", R.drawable.ml_2x));
        audioItems.add(new AudioItem("p04", "TV REMOTE", R.drawable.spotify_2x));
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_room_centre_audio, container, false);


        RoomCentreAudioMusicAppAdapter musicAppAdapter = new RoomCentreAudioMusicAppAdapter(getActivity());
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        mBinding.recyclerViewMusicApps.setLayoutManager(mLayoutManager);
        mBinding.recyclerViewMusicApps.setAdapter(musicAppAdapter);


        devicesAdapter = new RoomCentreAudioAVDevicesAdapter(getActivity(), audioItems, new RoomCentreAudioAVDevicesAdapter.OnItemClick() {
            @Override
            public void onItemSelected(int position) {
                switch (position) {
                    case 0:
                        mInteraction.openFragment(Enumes.FragmentType.FRAGMENT_ROOM_CENTRE_VIDEO_APLIFIER, null, Enumes.Direction.RIGHT_IN);
                        break;
                    case 1:
                        mInteraction.openFragment(Enumes.FragmentType.FRAGMENT_ROOM_CENTRE_VIDEO_CABLE_TV, null, Enumes.Direction.RIGHT_IN);
                        break;
                    case 2:
                        mInteraction.openFragment(Enumes.FragmentType.FRAGMENT_ROOM_CENTRE_VIDEO_APPLE_TV, null, Enumes.Direction.RIGHT_IN);
                        break;
                    case 3:
                        mInteraction.openFragment(Enumes.FragmentType.FRAGMENT_ROOM_CENTRE_VIDEO_MATRIX, null, Enumes.Direction.RIGHT_IN);
                        break;
                    case 4:
                        mInteraction.openFragment(Enumes.FragmentType.FRAGMENT_ROOM_CENTRE_VIDEO_TV_REMOTE, null, Enumes.Direction.RIGHT_IN);
                        break;
                    default:
                        break;
                }
            }
        });

        deviceLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        mBinding.recyclerViewDevices.setLayoutManager(deviceLayoutManager);
        mBinding.recyclerViewDevices.setAdapter(devicesAdapter);


        mBinding.btnDevice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mInteraction.openFragment(Enumes.FragmentType.FRAGMENT_ROOM_CENTRE_AUDIO_DEVICES, null, Enumes.Direction.RIGHT_IN);
            }
        });

        mBinding.ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
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
