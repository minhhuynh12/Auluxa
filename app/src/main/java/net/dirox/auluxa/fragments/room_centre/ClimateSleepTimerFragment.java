package net.dirox.auluxa.fragments.room_centre;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import net.dirox.auluxa.R;
import net.dirox.auluxa.activity.FragmentInteraction;
import net.dirox.auluxa.adapter.ClimateSleepTimerAdapter;
import net.dirox.auluxa.common.fragment.BaseFragment;
import net.dirox.auluxa.data.GeneratorSampleData;
import net.dirox.auluxa.data.sample.ClimateItem;
import net.dirox.auluxa.data.sample.TimerItem;
import net.dirox.auluxa.utils.Constant;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by trungnq on 15/06/2017.
 */

public class ClimateSleepTimerFragment extends BaseFragment {

    private View rootView;

    private ListView lvTimer;
    List<TimerItem> list;
    ClimateSleepTimerAdapter adapter;

    private ClimateItem mClimateItem;

    private int timer = 0;

    private FragmentInteraction mInteraction;

    public ClimateSleepTimerFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mClimateItem = getArguments().getParcelable(Constant.CLIMATE_ITEM);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_climate_sleep_timer, container, false);

        rootView.findViewById(R.id.btnSleepTimerBack).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });

        lvTimer = (ListView) rootView.findViewById(R.id.lvClimateSleepTimer);

        list = new ArrayList<TimerItem>();
        list.add(new TimerItem(1, "15 MINS", false));
        list.add(new TimerItem(2, "30 MINS", false));
        list.add(new TimerItem(3, "1 HOUR", false));
        list.add(new TimerItem(4, "2 HOURS", false));
        list.add(new TimerItem(5, "3 HOURS", false));
        list.add(new TimerItem(6, "4 HOURS", false));

        adapter = new ClimateSleepTimerAdapter(getActivity(), R.layout.layout_item_sleep_timer, list) {
            @Override
            public void onItemSelected(int position) {
                switch (list.get(position).getTimer()) {
                    case "15 MINS":
                        timer = 15;
                        break;
                    case "30 MINS":
                        timer = 30;
                        break;
                    case "1 HOUR":
                        timer = 60;
                        break;
                    case "2 HOURS":
                        timer = 120;
                        break;
                    case "3 HOURS":
                        timer = 180;
                        break;
                    case "4 HOURS":
                        timer = 240;
                        break;
                }
            }
        };

        lvTimer.setAdapter(adapter);

        rootView.findViewById(R.id.btnSaveSleepTimer).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GeneratorSampleData.saveAirConditionerSleepTime(mClimateItem, timer);
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
