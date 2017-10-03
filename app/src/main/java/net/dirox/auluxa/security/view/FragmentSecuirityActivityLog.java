package net.dirox.auluxa.security.view;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import net.dirox.auluxa.R;
import net.dirox.auluxa.activity.FragmentInteraction;
import net.dirox.auluxa.adapter.ActivityLogAdapter;
import net.dirox.auluxa.adapter.ActivityLogFooterAdapter;
import net.dirox.auluxa.adapter.PinAdapter;
import net.dirox.auluxa.adapter.scenes.RecyclerDividerItemDecoration;
import net.dirox.auluxa.common.fragment.BaseFragment;
import net.dirox.auluxa.data.sample.ActivityLogItems;
import net.dirox.auluxa.data.sample.PinItem;
import net.dirox.auluxa.databinding.FragmentSecurityActivitylogBinding;
import net.dirox.auluxa.extras.Enumes;

import java.util.ArrayList;

/**
 * Created by MyPC on 14/07/2017.
 */

public class FragmentSecuirityActivityLog extends BaseFragment {
    private FragmentSecurityActivitylogBinding mBinding;
    private FragmentInteraction mInteraction;
    private ActivityLogAdapter activityLogAdapter;
    private ActivityLogFooterAdapter activityLogFooterAdapter;
    private PinAdapter pinAdapter;

    ImageView imgLockActivityLog;
    ImageView imgPin;
    ImageView imgBluetoothActivityLog;
    ImageView imgActivityLog;
    ImageView imgAIRBNBativityLog;
    int flag =0;
    boolean check = false;


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_security_activitylog, container, false);
        Bundle bundleFlag = getArguments();
        flag = bundleFlag.getInt("Pin");
        //activityLog
        ArrayList<ActivityLogItems> activityLogItemses = new ArrayList<>();
        activityLogItemses.add(new ActivityLogItems(R.drawable.icon_time, "2 HOURS AGO", "YOU HAVE CREATED A PIN CODE VALID " +
                "FROM JUN 23,2017 13:00 HKT TO JUN 25,2017 03:00 HKT", R.color.white));
        activityLogItemses.add(new ActivityLogItems(R.drawable.icon_time, "2 HOURS AGO", "YOU HAVE CREATED A PERMANENT PIN", R.color.white));
        activityLogItemses.add(new ActivityLogItems(R.drawable.icon_time, "1 DAY AGO", "YOU HAVE UNLOCKED THE LOCK VIA BASIC UNLOCK", R.color.white));
        activityLogItemses.add(new ActivityLogItems(R.drawable.icon_time, "2 DAY AGO", "A BATTERY CHANGE WAS MADE ON THE LOCK. NEW BATTERY LEVEL: 100%", R.color.white));
        activityLogItemses.add(new ActivityLogItems(R.drawable.icon_time, "3 DAY AGO", "DP DHILLION (DP ENTRY) UNLOCKED THE LOCK VIA BLUETOOTH KEY", R.color.white));
        activityLogItemses.add(new ActivityLogItems(R.drawable.icon_time, "1 DAY AGO", "DP DHILLION (DP ENTRY) UNLOCKED THE LOCK VIA BLUETOOTH KEY", R.color.white));
        activityLogItemses.add(new ActivityLogItems(R.drawable.icon_time, "3 DAY AGO", "DP DHILLION (DP ENTRY) UNLOCKED THE LOCK VIA BLUETOOTH KEY", R.color.white));

        createlist(activityLogItemses);

        //PIN
        ArrayList<PinItem> pinItems = new ArrayList<>();
        pinItems.add(new PinItem("MASTER FPIN", "NEVER EXPIRES", R.drawable.arrow, R.color.white));
        pinItems.add(new PinItem("ASSIGNED LOCK PIN", "", R.drawable.arrow, R.color.white));

        mBinding.imgBackActivityLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mInteraction.openFragment(Enumes.FragmentType.FRAGMENT_SECURITY_DOOR_LOCK, null, Enumes.Direction.RIGHT_IN);
            }
        });

        imgPin = (ImageView) mBinding.getRoot().findViewById(R.id.imgPin);
        imgActivityLog = (ImageView) mBinding.getRoot().findViewById(R.id.imgActivityLog);
        imgLockActivityLog = (ImageView) mBinding.getRoot().findViewById(R.id.imgLockActivityLog);
        imgBluetoothActivityLog = (ImageView) mBinding.getRoot().findViewById(R.id.imgBluetoothActivityLog);
        imgAIRBNBativityLog = (ImageView) mBinding.getRoot().findViewById(R.id.imgAIRBNBativityLog);

        imgLockActivityLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
                layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                mBinding.recycActivityLog.setLayoutManager(layoutManager);
                mBinding.recycActivityLog.addItemDecoration(new RecyclerDividerItemDecoration(getActivity(),60,60));
                pinAdapter = new PinAdapter(pinItems) {
                    @Override
                    public void onItemSelected(int position) {

                    }
                };
                mBinding.recycActivityLog.setAdapter(pinAdapter);
                mBinding.headerActivityLog.setText("LOCK");
                enableTabAtIndex(0);
            }
        });

        imgPin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
                layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                mBinding.recycActivityLog.setLayoutManager(layoutManager);
                mBinding.recycActivityLog.addItemDecoration(new RecyclerDividerItemDecoration(getActivity(),39, 38));
                pinAdapter = new PinAdapter(pinItems) {
                    @Override
                    public void onItemSelected(int position) {

                        mInteraction.openFragment(Enumes.FragmentType.FRAGMENT_SECURITY_ASSIGNED_LOCK_PIN, null, Enumes.Direction.RIGHT_IN);

                    }
                };
                mBinding.recycActivityLog.setAdapter(pinAdapter);
                mBinding.headerActivityLog.setText("PIN");
                enableTabAtIndex(1);
            }
        });


        imgBluetoothActivityLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
                layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                mBinding.recycActivityLog.setLayoutManager(layoutManager);
                mBinding.recycActivityLog.addItemDecoration(new RecyclerDividerItemDecoration(getActivity(), 39,38));
                pinAdapter = new PinAdapter(pinItems) {
                    @Override
                    public void onItemSelected(int position) {

                    }
                };
                mBinding.recycActivityLog.setAdapter(pinAdapter);
                mBinding.headerActivityLog.setText("BLUETOOTH");
                enableTabAtIndex(2);
            }
        });

        imgActivityLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createlist(activityLogItemses);
                mBinding.headerActivityLog.setText("ACTIVITY LOG");
                mBinding.recycActivityLog.setVisibility(View.VISIBLE);
                mBinding.includeLayoutAirbnb.contraintAirbnb.setVisibility(View.GONE);
                enableTabAtIndex(3);

            }
        });

        imgAIRBNBativityLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mBinding.headerActivityLog.setText("AIRBNBA");
                mBinding.recycActivityLog.setVisibility(View.GONE);
                mBinding.includeLayoutAirbnb.contraintAirbnb.setVisibility(View.VISIBLE);

                enableTabAtIndex(4);
            }
        });
        if(flag == 1)
        {
            LinearLayoutManager layoutManagerPin = new LinearLayoutManager(getActivity());
            layoutManagerPin.setOrientation(LinearLayoutManager.VERTICAL);
            mBinding.recycActivityLog.setLayoutManager(layoutManagerPin);
            mBinding.recycActivityLog.addItemDecoration(new RecyclerDividerItemDecoration(getActivity()));
            pinAdapter = new PinAdapter(pinItems) {
                @Override
                public void onItemSelected(int position) {
                    mInteraction.openFragment(Enumes.FragmentType.FRAGMENT_SECURITY_ASSIGNED_LOCK_PIN, null, Enumes.Direction.RIGHT_IN);
                }
            };
            mBinding.recycActivityLog.setAdapter(pinAdapter);
            mBinding.headerActivityLog.setText("PIN");
            enableTabAtIndex(1);
        }
        return mBinding.getRoot();
    }

    public void createlist(ArrayList<ActivityLogItems> activityLogItemses) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mBinding.recycActivityLog.setLayoutManager(layoutManager);
        mBinding.recycActivityLog.addItemDecoration(new RecyclerDividerItemDecoration(getActivity()));
        activityLogAdapter = new ActivityLogAdapter(activityLogItemses);
        mBinding.recycActivityLog.setAdapter(activityLogAdapter);
    }

    private void enableTabAtIndex(int index) {
        switch (index) {
            case 0:
                imgLockActivityLog.setImageResource(R.drawable.icon_lock_activitylog_green);
                imgPin.setImageResource(R.drawable.icon_pin_activitylog);
                imgBluetoothActivityLog.setImageResource(R.drawable.icon_bluetooth_activitylog_);
                imgActivityLog.setImageResource(R.drawable.icon_activitylog_white);
                imgAIRBNBativityLog.setImageResource(R.drawable.icon_airbnb_activitylog);
                break;

            case 1:
                imgLockActivityLog.setImageResource(R.drawable.icon_lock_activitylog);
                imgPin.setImageResource(R.drawable.icon_pin_green);
                imgBluetoothActivityLog.setImageResource(R.drawable.icon_bluetooth_activitylog_);
                imgActivityLog.setImageResource(R.drawable.icon_activitylog_white);
                imgAIRBNBativityLog.setImageResource(R.drawable.icon_airbnb_activitylog);
                break;

            case 2:
                imgLockActivityLog.setImageResource(R.drawable.icon_lock_activitylog);
                imgPin.setImageResource(R.drawable.icon_pin_activitylog);
                imgBluetoothActivityLog.setImageResource(R.drawable.icon_bluetooth_activitylog_green);
                imgActivityLog.setImageResource(R.drawable.icon_activitylog_white);
                imgAIRBNBativityLog.setImageResource(R.drawable.icon_airbnb_activitylog);
                break;

            case 3:
                imgLockActivityLog.setImageResource(R.drawable.icon_lock_activitylog);
                imgPin.setImageResource(R.drawable.icon_pin_activitylog);
                imgBluetoothActivityLog.setImageResource(R.drawable.icon_bluetooth_activitylog_);
                imgActivityLog.setImageResource(R.drawable.icon_activitylog);
                imgAIRBNBativityLog.setImageResource(R.drawable.icon_airbnb_activitylog);
                break;

            case 4:
                imgLockActivityLog.setImageResource(R.drawable.icon_lock_activitylog);
                imgPin.setImageResource(R.drawable.icon_pin_activitylog);
                imgBluetoothActivityLog.setImageResource(R.drawable.icon_bluetooth_activitylog_);
                imgActivityLog.setImageResource(R.drawable.icon_activitylog_white);
                imgAIRBNBativityLog.setImageResource(R.drawable.icon_airbnb_activitylog_green);
                break;
        }

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mInteraction = (FragmentInteraction) context;
    }

}
