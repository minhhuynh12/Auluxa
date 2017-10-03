package net.dirox.auluxa.main.view;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.dirox.auluxa.R;
import net.dirox.auluxa.activity.FragmentInteraction;
import net.dirox.auluxa.adapter.RecyclerBindingAdapter;
import net.dirox.auluxa.data.GeneratorSampleData;
import net.dirox.auluxa.data.sample.RoomImageItem;
import net.dirox.auluxa.data.sample.RoomViewModal;
import net.dirox.auluxa.databinding.FragmentNavigationDrawerBinding;
import net.dirox.auluxa.extras.Enumes;
import net.dirox.auluxa.utils.Constant;
import net.dirox.auluxa.utils.drap_drop_sequence.OnStartDragListener;
import net.dirox.auluxa.utils.drap_drop_sequence.SequenceItemTouchHelperCallback;

/**
 * Created by YEN_MINH on 6/8/2017 3:10 AM.
 * Code and Life ( ^ .,,. ^ )
 * ---------------------------------------------
 */

public class NavigationDrawerFragment extends Fragment
        implements RecyclerBindingAdapter.OnItemClickListener<RoomImageItem>, OnStartDragListener {

    private FragmentInteraction mInteraction;
    private FragmentNavigationDrawerBinding mBinding;
    private RoomViewModal model;
    private ItemTouchHelper mItemTouchHelper;
    private int selectedIndex = -1;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_navigation_drawer, container, false);
        model = new RoomViewModal(getContext(), this);
        SequenceItemTouchHelperCallback sequenceItemTouchHelperCallback = new SequenceItemTouchHelperCallback(model.adapter);
        sequenceItemTouchHelperCallback.setLongPressEnable(true);
        ItemTouchHelper.Callback callback = sequenceItemTouchHelperCallback;
        mItemTouchHelper = new ItemTouchHelper(callback);
        mItemTouchHelper.attachToRecyclerView(mBinding.fragmentNavigationDrawerRecyclerView);
        model.setItemTouchHelper(mItemTouchHelper);
        mBinding.setModel(model);
        return mBinding.getRoot();
    }

    @Override
    public void onStart() {
        super.onStart();
        initializeViews();
    }

    @Override
    public void onResume() {
        super.onResume();
        initializeViews();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mInteraction = (FragmentInteraction) context;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mInteraction = null;
    }

    public void initializeViews() {
        model.setData(GeneratorSampleData.roomItemArrayList);
    }

    @Override
    public void onItemClick(int position, RoomImageItem item) {
        Enumes.FragmentType fragmentType = Enumes.FragmentType.FRAGMENT_DEFAULT_NULL;
        Bundle bundle = new Bundle();
        bundle.putParcelable(Constant.ROOM_IMAGE_ITEM, item);

        if (selectedIndex != position) {
            model.setItemSelected(selectedIndex, position);
            selectedIndex = position;
            fragmentType = Enumes.FragmentType.FRAGMENT_ROOM_CENTRE;
        }
        mInteraction.openFragment(fragmentType, bundle, Enumes.Direction.NONE);
    }

    public void updateDataListChange() {
        GeneratorSampleData.upLoadRoomItems(getActivity().getApplicationContext());
    }

    @Override
    public void onStartDrag(RecyclerView.ViewHolder viewHolder) {
        mItemTouchHelper.startDrag(viewHolder);
    }
}
