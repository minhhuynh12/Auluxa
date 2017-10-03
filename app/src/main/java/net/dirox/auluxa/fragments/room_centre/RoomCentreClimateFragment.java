package net.dirox.auluxa.fragments.room_centre;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import net.dirox.auluxa.R;
import net.dirox.auluxa.activity.FragmentInteraction;
import net.dirox.auluxa.adapter.RoomCentreClimateAdapter;
import net.dirox.auluxa.common.fragment.BaseFragment;
import net.dirox.auluxa.data.sample.RoomImageItem;
import net.dirox.auluxa.extras.Enumes;
import net.dirox.auluxa.utils.Constant;

/**
 * Created by an on 6/12/2017.
 */

public class RoomCentreClimateFragment extends BaseFragment {

    private FragmentInteraction mInteraction;
    private RoomImageItem mRoomItem;
    ListView listViewClimate;
    RoomCentreClimateAdapter adapter;


    public RoomCentreClimateFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_room_centre_climate, container, false);

        listViewClimate = (ListView) rootView.findViewById(R.id.listViewClimate);

        mRoomItem = getArguments().getParcelable(Constant.ROOM_IMAGE_ITEM);

        adapter = new RoomCentreClimateAdapter(getActivity(), R.layout.layout_item_climate, mRoomItem.climateItems);
        listViewClimate.setAdapter(adapter);

        listViewClimate.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Bundle bundle = new Bundle();
                bundle.putParcelable(Constant.CLIMATE_ITEM, mRoomItem.climateItems.get(position));
                mInteraction.openFragment(Enumes.FragmentType.FRAGMENT_ROOM_CENTRE_CLIMATE_CONTROL, bundle, Enumes.Direction.RIGHT_IN);
            }
        });

        rootView.findViewById(R.id.fragment_schedule_btnBack).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });
        return rootView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mInteraction = (FragmentInteraction) context;
    }
}
