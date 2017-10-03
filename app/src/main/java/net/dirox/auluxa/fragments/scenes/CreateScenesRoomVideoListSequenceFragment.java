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
import net.dirox.auluxa.adapter.scenes.ScenesCreateRoomVideoAddSequenceAdapter;
import net.dirox.auluxa.common.fragment.BaseFragment;
import net.dirox.auluxa.data.sample.VideoSequenceItem;
import net.dirox.auluxa.databinding.FragmentScenesCreateRoomVideoListSequenceBinding;
import net.dirox.auluxa.extras.Enumes;
import net.dirox.auluxa.widget.SimpleDividerItemDecoration;

import java.util.ArrayList;

/**
 * Created by an on 6/29/2017.
 */

public class CreateScenesRoomVideoListSequenceFragment extends BaseFragment implements ScenesCreateRoomVideoAddSequenceAdapter.ItemClickListener {
    private FragmentInteraction mInteraction;
    private FragmentScenesCreateRoomVideoListSequenceBinding mBinding;

    private static ScenesCreateRoomVideoAddSequenceAdapter adapter;
    private static ArrayList<VideoSequenceItem> arraySequence;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_scenes_create_room_video_list_sequence, container, false);

        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        mBinding.recyclerView.setLayoutManager(mLayoutManager);
        mBinding.recyclerView.addItemDecoration(new SimpleDividerItemDecoration(getActivity(), R.drawable.line_dark_gray_divider));


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

        arraySequence = new ArrayList<>();
        adapter = new ScenesCreateRoomVideoAddSequenceAdapter(context, arraySequence, this);
    }

    @Override
    public void onItemClick(int position) {
        getArguments().putInt("position", position);
        getArguments().putInt("arraySequence", adapter.getItemCount());
        mInteraction.openFragment(Enumes.FragmentType.FRAGMENT_SCENE_CREATE_ROOM_VIDEO_ADD_SEQUENCE, getArguments(), Enumes.Direction.RIGHT_IN);
    }

    @Override
    public void onAddSequenceClicked() {
        getArguments().putInt("position", adapter.getItemCount());
        getArguments().putInt("arraySequence", adapter.getItemCount());
        mInteraction.openFragment(Enumes.FragmentType.FRAGMENT_SCENE_CREATE_ROOM_VIDEO_ADD_SEQUENCE, getArguments(), Enumes.Direction.RIGHT_IN);
    }

    public static void addNewSequence(int buttonId) {
        if (arraySequence != null) {
            VideoSequenceItem item = new VideoSequenceItem(arraySequence.size(), buttonId);
            arraySequence.add(item);

            if (adapter != null) {
                adapter.notifyItemChanged(arraySequence.size());
            }
        }
    }

    public static void updateSequence(int buttonId, int pos) {
        if (arraySequence != null) {
            VideoSequenceItem item = new VideoSequenceItem(pos, buttonId);
            arraySequence.set(pos, item);

            if (adapter != null) {
                adapter.notifyItemChanged(pos);
            }
        }
    }
}
