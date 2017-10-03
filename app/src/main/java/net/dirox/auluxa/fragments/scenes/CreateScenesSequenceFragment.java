package net.dirox.auluxa.fragments.scenes;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.dirox.auluxa.R;
import net.dirox.auluxa.activity.FragmentInteraction;
import net.dirox.auluxa.adapter.SequenceAdapter;
import net.dirox.auluxa.common.fragment.BaseFragment;
import net.dirox.auluxa.data.sample.SequenceItem;
import net.dirox.auluxa.databinding.FragmentScenesCreateSequenceBinding;
import net.dirox.auluxa.utils.drap_drop_sequence.OnStartDragListener;
import net.dirox.auluxa.utils.drap_drop_sequence.SequenceItemTouchHelperCallback;

import java.util.ArrayList;

/**
 * Created by an on 6/28/2017.
 */

public class CreateScenesSequenceFragment extends BaseFragment implements OnStartDragListener {

    private FragmentInteraction mInteraction;
    private FragmentScenesCreateSequenceBinding mBinding;
    SequenceAdapter sequenceAdapter;
    private ItemTouchHelper mItemTouchHelper;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_scenes_create_sequence, container, false);

        ArrayList<SequenceItem> sequenceItems = new ArrayList<>();
        sequenceItems.add(new SequenceItem(0, "AIR CONDITIONER(WINDOW)", "LIVING ROOM",
                "SET DELAY(IN SEC)"));
        sequenceItems.add(new SequenceItem(1, "AIR CONDITIONER(DOOR)", "LIVING ROOM",
                "SET DELAY(IN SEC)"));
        sequenceItems.add(new SequenceItem(2, "FRONT LIGHTS", "LIVING ROOM",
                "SET DELAY(IN SEC)"));
        sequenceItems.add(new SequenceItem(3, "BACK LIGHTS", "GUEST BEDROOM",
                "SET DELAY(IN SEC)"));
        sequenceItems.add(new SequenceItem(4, "MIRROR LIGHTS", "GUEST BEDROOM",
                "SET DELAY(IN SEC)"));

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mBinding.recyclerViewSequence.setLayoutManager(layoutManager);

        sequenceAdapter = new SequenceAdapter(R.layout.fragment_scenes_create_sequence_list_items, sequenceItems, this);
        mBinding.recyclerViewSequence.setAdapter(sequenceAdapter);

        ItemTouchHelper.Callback callback = new SequenceItemTouchHelperCallback(sequenceAdapter);
        mItemTouchHelper = new ItemTouchHelper(callback);
        mItemTouchHelper.attachToRecyclerView(mBinding.recyclerViewSequence);

        mBinding.btnBackSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });

        sequenceAdapter.editable = false;
        sequenceAdapter.notifyDataSetChanged();

        mBinding.btnSaveSequence.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (Integer.parseInt(mBinding.btnSaveSequence.getTag().toString())) {
                    case 1:
                        mBinding.btnSaveSequence.setText("SAVE");
                        sequenceAdapter.editable = true;
                        mBinding.btnSaveSequence.setTag(2);
                        sequenceAdapter.notifyDataSetChanged();
                        break;
                    case 2:
                        mBinding.btnSaveSequence.setText("EDIT");
                        sequenceAdapter.editable = false;
                        mBinding.btnSaveSequence.setTag(1);
                        sequenceAdapter.notifyDataSetChanged();
                        getActivity().onBackPressed();
                        break;
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

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onStartDrag(RecyclerView.ViewHolder viewHolder) {
        mItemTouchHelper.startDrag(viewHolder);
    }
}
