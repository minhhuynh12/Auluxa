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
import net.dirox.auluxa.databinding.FragmentScenesCreateRoomsBinding;
import net.dirox.auluxa.extras.Enumes;

/**
 * Created by an on 6/28/2017.
 */

public class CreateScenesRoomFragment extends BaseFragment {

    private FragmentInteraction mInteraction;
    private FragmentScenesCreateRoomsBinding mBinding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_scenes_create_rooms, container, false);
        mBinding.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });

        mBinding.btnLivingRoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("RoomName", "LIVING ROOM");
                mInteraction.openFragment(Enumes.FragmentType.FRAGMENT_SCENE_CREATE_ROOM_DETAIL, bundle, Enumes.Direction.RIGHT_IN);
            }
        });

        mBinding.btnDiningRoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("RoomName", "DINING ROOM");
                mInteraction.openFragment(Enumes.FragmentType.FRAGMENT_SCENE_CREATE_ROOM_DETAIL, bundle, Enumes.Direction.RIGHT_IN);
            }
        });

        mBinding.btnMasterBedRoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("RoomName", "MASTER BEDROOM");
                mInteraction.openFragment(Enumes.FragmentType.FRAGMENT_SCENE_CREATE_ROOM_DETAIL, bundle, Enumes.Direction.RIGHT_IN);
            }
        });

        mBinding.btnGuestRoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("RoomName", "GUEST BEDROOM");
                mInteraction.openFragment(Enumes.FragmentType.FRAGMENT_SCENE_CREATE_ROOM_DETAIL, bundle, Enumes.Direction.RIGHT_IN);
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
