package net.dirox.auluxa.fragments.room_centre;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.dirox.auluxa.BR;
import net.dirox.auluxa.R;
import net.dirox.auluxa.adapter.RoomCentreShadesAdapter;
import net.dirox.auluxa.common.fragment.BaseFragment;
import net.dirox.auluxa.data.sample.ShadesItem;
import net.dirox.auluxa.databinding.FragmentRoomCentreShadesBinding;
import net.dirox.auluxa.widget.SimpleDividerItemDecoration;

import java.util.ArrayList;

/**
 * Created by an on 6/12/2017.
 */

public class RoomCentreShadesFragment extends BaseFragment implements View.OnClickListener {

    private FragmentRoomCentreShadesBinding mBinding;
    private RoomCentreShadesAdapter mAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_room_centre_shades, container, false);
        RecyclerView recyclerView = (RecyclerView) mBinding.getRoot().findViewById(R.id.fragment_centre_shades_recyclerView);

        ArrayList<ShadesItem> shadesItems = new ArrayList<>();
        shadesItems.add(new ShadesItem(1, "Windowside", 74, false));
        shadesItems.add(new ShadesItem(2, "Bedside", 100, false));
        shadesItems.add(new ShadesItem(3, "Doorside", 49, true));

        mAdapter = new RoomCentreShadesAdapter(getContext(), BR.item, shadesItems);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        RecyclerView.ItemDecoration dividerItemDecoration = new SimpleDividerItemDecoration(getContext(), R.drawable.line_divider);
        recyclerView.addItemDecoration(dividerItemDecoration);
        recyclerView.setAdapter(mAdapter);

        mBinding.getRoot().findViewById(R.id.fragment_centre_shades_btnOpenAll).setOnClickListener(this);
        mBinding.getRoot().findViewById(R.id.fragment_centre_shades_btnCloseAll).setOnClickListener(this);

        mBinding.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });
        return mBinding.getRoot();
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.fragment_centre_shades_btnOpenAll:
                mAdapter.setAllShadesState(true);
                break;
            case R.id.fragment_centre_shades_btnCloseAll:
                mAdapter.setAllShadesState(false);
                break;
        }
    }

}
