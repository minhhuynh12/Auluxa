package net.dirox.auluxa.fragments.room_centre;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import net.dirox.auluxa.R;
import net.dirox.auluxa.activity.FragmentInteraction;
import net.dirox.auluxa.adapter.SchedulingAdapter;
import net.dirox.auluxa.common.fragment.BaseFragment;
import net.dirox.auluxa.data.sample.SchedulingItem;
import net.dirox.auluxa.extras.Enumes;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by trungnq on 15/06/2017.
 */

public class ClimateSchedulingFragment extends BaseFragment {

    private FragmentInteraction mInteraction;

    private View rootView;

    List<SchedulingItem> list;
    SchedulingAdapter adapter;

    private int selectedIndex = -1;

    public ClimateSchedulingFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_climate_scheduling, container, false);
        final ListView lvClimateSleepTimer = (ListView) rootView.findViewById(R.id.lvClimateSleepTimer);

        list = new ArrayList<SchedulingItem>();
        list.add(new SchedulingItem(0, "SCHEDULE 1"));
        list.add(new SchedulingItem(1, "SCHEDULE 2"));
        list.add(new SchedulingItem(2, "SCHEDULE 3"));
        list.add(new SchedulingItem(3, "SCHEDULE 4"));

        adapter = new SchedulingAdapter(getActivity(), R.layout.layout_item_scheduling, list) {
            @Override
            public void onItemSelected(int position, SchedulingItem item) {
                Bundle bundle = new Bundle();
                bundle.putString("SCHEDULE_NAME", list.get(position).getTitleSchedule());
                mInteraction.openFragment(Enumes.FragmentType.FRAGMENT_CLIMATE_SCHEDULING_DETAIL, bundle, Enumes.Direction.RIGHT_IN);
            }
        };
        lvClimateSleepTimer.setAdapter(adapter);

        rootView.findViewById(R.id.btnSleepTimerBack).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });

        rootView.findViewById(R.id.btnAddSchedule).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mInteraction.openFragment(Enumes.FragmentType.FRAGMENT_CLIMATE_SCHEDULING_DETAIL, null, Enumes.Direction.RIGHT_IN);
            }
        });

        rootView.findViewById(R.id.btnClearSchedule).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (list.size() > 0) {
                    AlertDialog.Builder dialog = new AlertDialog.Builder(getContext());

                    dialog.setTitle("Alert!")
                            .setMessage("Are you sure you want to clear all schedules?")
                            .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialoginterface, int i) {
                                    dialoginterface.cancel();
                                }
                            })
                            .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialoginterface, int i) {
                                    list.clear();
                                    adapter.notifyDataSetChanged();
                                }
                            }).show();
                } else {
                }
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
