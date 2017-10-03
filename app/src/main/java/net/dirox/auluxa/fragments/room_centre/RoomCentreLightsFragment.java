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
import net.dirox.auluxa.adapter.RoomCentreLightsAdapter;
import net.dirox.auluxa.common.fragment.BaseFragment;
import net.dirox.auluxa.data.sample.LightItem;
import net.dirox.auluxa.databinding.FragmentRoomCentreLightsBinding;
import net.dirox.auluxa.widget.SimpleDividerItemDecoration;

import java.util.ArrayList;

/**
 * Created by an on 6/12/2017.
 */

public class RoomCentreLightsFragment extends BaseFragment {

    FragmentRoomCentreLightsBinding mBinding;
    ArrayList<LightItem> lightItems;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        lightItems = new ArrayList<>();
        lightItems.add(new LightItem(1, "Cove Lights", 50, true));
        lightItems.add(new LightItem(2, "Down Lights", 50, true));
        lightItems.add(new LightItem(3, "Door Lights", 50, true));
        lightItems.add(new LightItem(4, "Spot Lights", 50, true));
        lightItems.add(new LightItem(5, "Balconny Lights", 50, true));
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_room_centre_lights, container, false);
        RecyclerView recyclerView = (RecyclerView) mBinding.getRoot().findViewById(R.id.fragment_centre_light_recyclerView);


        RoomCentreLightsAdapter adapter = new RoomCentreLightsAdapter(getContext(), BR.item, lightItems);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        RecyclerView.ItemDecoration dividerItemDecoration = new SimpleDividerItemDecoration(getContext(), R.drawable.line_divider);
        recyclerView.addItemDecoration(dividerItemDecoration);
        recyclerView.setAdapter(adapter);

        mBinding.fragmentLightBtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });

        mBinding.btnCloseAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.turnAllLight(false);
            }
        });

        mBinding.btnOpenAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.turnAllLight(true);
            }
        });

        return mBinding.getRoot();
    }


}
