package net.dirox.auluxa.fragments.scenes;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.dirox.auluxa.R;
import net.dirox.auluxa.activity.FragmentInteraction;
import net.dirox.auluxa.common.fragment.BaseFragment;
import net.dirox.auluxa.databinding.FragmentScenesCreateRoomDetailBinding;
import net.dirox.auluxa.extras.Enumes;

/**
 * Created by an on 6/29/2017.
 */

public class CreateScenesRoomDetailFragment extends BaseFragment {

    private FragmentInteraction mInteraction;
    private FragmentScenesCreateRoomDetailBinding mBinding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_scenes_create_room_detail, container, false);
        String roomName = getArguments().getString("RoomName");
        mBinding.setName(roomName);
        mBinding.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });

        mBinding.btnClimate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mInteraction.openFragment(Enumes.FragmentType.FRAGMENT_SCENE_CREATE_ROOM_CLIMATE, null, Enumes.Direction.RIGHT_IN);
            }
        });

        mBinding.btnLight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mInteraction.openFragment(Enumes.FragmentType.FRAGMENT_SCENE_CREATE_LIGHT, null, Enumes.Direction.RIGHT_IN);
            }
        });

        mBinding.btnVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mInteraction.openFragment(Enumes.FragmentType.FRAGMENT_SCENE_CREATE_ROOM_VIDEO_LIST, null, Enumes.Direction.RIGHT_IN);
            }
        });

        mBinding.btnSecurity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mInteraction.openFragment(Enumes.FragmentType.FRAGMENT_SCENE_CREATE_ROOM_SECURITY, null, Enumes.Direction.RIGHT_IN);
            }
        });

        // Shades
        mBinding.btnShades.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mInteraction.openFragment(Enumes.FragmentType.FRAGMENT_SCENE_CREATE_SHADE, null, Enumes.Direction.RIGHT_IN);
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
