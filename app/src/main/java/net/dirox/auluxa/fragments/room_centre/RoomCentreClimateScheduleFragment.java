package net.dirox.auluxa.fragments.room_centre;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import net.dirox.auluxa.R;
import net.dirox.auluxa.activity.FragmentInteraction;
import net.dirox.auluxa.adapter.RoomCentreClimateScheduleAdapter;
import net.dirox.auluxa.common.fragment.BaseFragment;
import net.dirox.auluxa.data.sample.ClimateItem;
import net.dirox.auluxa.data.sample.ClimateScheduleItem;
import net.dirox.auluxa.extras.Enumes;
import net.dirox.auluxa.utils.Constant;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by an on 6/15/2017.
 */

public class RoomCentreClimateScheduleFragment extends BaseFragment {

    private FragmentInteraction mInteraction;
    private ListView lvScheduleClimate;
    List<ClimateScheduleItem> list;
    RoomCentreClimateScheduleAdapter adapter;
    private ClimateItem mClimateItem;

    public RoomCentreClimateScheduleFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mClimateItem = getArguments().getParcelable(Constant.CLIMATE_ITEM);

        // Inflate the layout for this fragment
        final View rootView = inflater.inflate(R.layout.fragment_centre_climate_schedule, container, false);

        rootView.findViewById(R.id.btnScheduleBack).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });

        lvScheduleClimate = (ListView) rootView.findViewById(R.id.lvScheduleClimate);

        list = new ArrayList<ClimateScheduleItem>();
        list.add(new ClimateScheduleItem(1, "SLEEP TIMER"));
        list.add(new ClimateScheduleItem(2, "SCHEDULING"));

        adapter = new RoomCentreClimateScheduleAdapter(getActivity(), R.layout.layout_item_climate_schedule, list) {
            @Override
            public void onItemSelected(int position) {
                switch (position) {
                    case 0:
                        Bundle bundle = new Bundle();
                        bundle.putParcelable(Constant.CLIMATE_ITEM, mClimateItem);
                        mInteraction.openFragment(Enumes.FragmentType.FRAGMENT_CLIMATE_SLEEP_TIMER, bundle, Enumes.Direction.RIGHT_IN);
                        break;

                    case 1:

                        mInteraction.openFragment(Enumes.FragmentType.FRAGMENT_CLIMATE_SCHEDULING, null, Enumes.Direction.RIGHT_IN);
                        break;
                }
            }
        };

        lvScheduleClimate.setAdapter(adapter);

        return rootView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mInteraction = (FragmentInteraction) context;
    }
}
