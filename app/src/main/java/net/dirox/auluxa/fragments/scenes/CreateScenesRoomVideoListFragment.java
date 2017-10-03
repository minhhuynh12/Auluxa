package net.dirox.auluxa.fragments.scenes;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.dirox.auluxa.R;
import net.dirox.auluxa.activity.FragmentInteraction;
import net.dirox.auluxa.adapter.scenes.ScenesCreateRoomVideoListAdapter;
import net.dirox.auluxa.common.fragment.BaseFragment;
import net.dirox.auluxa.databinding.FragmentScenesCreateRoomVideoListBinding;
import net.dirox.auluxa.extras.Enumes;
import net.dirox.auluxa.widget.SimpleDividerItemDecoration;

/**
 * Created by an on 6/29/2017.
 */

public class CreateScenesRoomVideoListFragment extends BaseFragment {
    private FragmentInteraction mInteraction;
    private FragmentScenesCreateRoomVideoListBinding mBinding;
    private ScenesCreateRoomVideoListAdapter adapter;

    private String[] arrayDevice = new String[]{"Cable TV", "Apple TV", "Blueray", "Amplifier", "Matrix"};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_scenes_create_room_video_list, container, false);

        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        mBinding.recyclerView.setLayoutManager(mLayoutManager);
        mBinding.recyclerView.addItemDecoration(new SimpleDividerItemDecoration(getActivity(), R.drawable.line_dark_gray_divider));
        adapter = new ScenesCreateRoomVideoListAdapter(getActivity(), arrayDevice, new ScenesCreateRoomVideoListAdapter.ItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Bundle bundle = new Bundle();
                bundle.putString("DeviceType", arrayDevice[position]);
                mInteraction.openFragment(Enumes.FragmentType.FRAGMENT_SCENE_CREATE_ROOM_VIDEO_LIST_SEQUENCE, bundle, Enumes.Direction.RIGHT_IN);
            }
        });
        mBinding.recyclerView.setAdapter(adapter);

        mBinding.btnBack.setOnClickListener(new View.OnClickListener() {
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
