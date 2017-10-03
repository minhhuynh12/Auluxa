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
import android.widget.Toast;

import com.google.gson.reflect.TypeToken;

import net.dirox.auluxa.R;
import net.dirox.auluxa.activity.FragmentInteraction;
import net.dirox.auluxa.adapter.ActivityLogAdapter;
import net.dirox.auluxa.adapter.BluetoothAdapter;
import net.dirox.auluxa.adapter.PinAdapter;
import net.dirox.auluxa.adapter.scenes.RecyclerDividerItemDecoration;
import net.dirox.auluxa.common.fragment.BaseFragment;
import net.dirox.auluxa.data.sample.ActivityLogItems;
import net.dirox.auluxa.data.sample.BluetoothItems;
import net.dirox.auluxa.data.sample.PinItem;
import net.dirox.auluxa.databinding.FragmentSecurityLockBinding;
import net.dirox.auluxa.extras.Enumes;
import net.dirox.auluxa.utils.Constant;
import net.dirox.auluxa.utils.Prefs;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by MyPC on 28/07/2017.
 */

public class FragmentSecurityLock extends BaseFragment {
    private FragmentSecurityLockBinding mBinding;
    private FragmentInteraction mInteraction;
    static String name = "";
    private ActivityLogAdapter activityLogAdapter;
    private BluetoothAdapter bluetoothAdapter;
    private PinAdapter pinAdapter;
    public static ArrayList<BluetoothItems> bluetoothItemslist;
    ImageView imgLockActivityLog;
    ImageView imgPin;
    ImageView imgBluetoothActivityLog;
    ImageView imgActivityLog;
    ImageView imgAIRBNBativityLog;
    boolean clicked = true;
    int flag = 0;

    int flagBluetooth = 0;

    public static FragmentSecurityLock newInstance() {
        FragmentSecurityLock fragmentSecurityLock = new FragmentSecurityLock();
        Bundle args = new Bundle();
        fragmentSecurityLock.setArguments(args);
        return fragmentSecurityLock;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_security_lock, container, false);

        Bundle bundleFlag = getArguments();
        if (bundleFlag != null) {
            flag = bundleFlag.getInt("Pin");
            flagBluetooth = bundleFlag.getInt("backRemove");

        }

        imgLockActivityLog = (ImageView) mBinding.getRoot().findViewById(R.id.imgLockActivityLog);
        imgPin = (ImageView) mBinding.getRoot().findViewById(R.id.imgPin);
        imgActivityLog = (ImageView) mBinding.getRoot().findViewById(R.id.imgActivityLog);
        imgBluetoothActivityLog = (ImageView) mBinding.getRoot().findViewById(R.id.imgBluetoothActivityLog);
        imgAIRBNBativityLog = (ImageView) mBinding.getRoot().findViewById(R.id.imgAIRBNBativityLog);


        //activityLog
        ArrayList<ActivityLogItems> activityLogItemses = new ArrayList<>();
        activityLogItemses.add(new ActivityLogItems(R.drawable.icon_time, "2 HOURS AGO", "YOU HAVE CREATED A PIN CODE VALID " +
                "FROM JUN 23,2017 13:00 HKT TO JUN 25,2017 03:00 HKT", R.color.white));
        activityLogItemses.add(new ActivityLogItems(R.drawable.icon_time, "2 HOURS AGO", "YOU HAVE CREATED A PERMANENT PIN", R.color.white));
        activityLogItemses.add(new ActivityLogItems(R.drawable.icon_time, "1 DAY AGO", "YOU HAVE UNLOCKED THE LOCK VIA BASIC UNLOCK", R.color.white));
        activityLogItemses.add(new ActivityLogItems(R.drawable.icon_time, "2 DAYS AGO", "A BATTERY CHANGE WAS MADE ON THE LOCK. NEW BATTERY LEVEL: 100%", R.color.white));
        activityLogItemses.add(new ActivityLogItems(R.drawable.icon_time, "3 DAYS AGO", "DP DHILLION (DP ENTRY) UNLOCKED THE LOCK VIA BLUETOOTH KEY", R.color.white));
        activityLogItemses.add(new ActivityLogItems(R.drawable.icon_time, "1 DAY AGO", "DP DHILLION (DP ENTRY) UNLOCKED THE LOCK VIA BLUETOOTH KEY", R.color.white));
        activityLogItemses.add(new ActivityLogItems(R.drawable.icon_time, "3 DAYS AGO", "DP DHILLION (DP ENTRY) UNLOCKED THE LOCK VIA BLUETOOTH KEY", R.color.white));


        ArrayList<PinItem> pinItems = new ArrayList<>();
        pinItems.add(new PinItem("MASTER LOOK PIN", "NEVER EXPIRES", R.drawable.arrow, R.color.white));
        pinItems.add(new PinItem("ASSIGNED LOOK PIN", "", R.drawable.arrow, R.color.white));


        //bluetooth list
        Type typeArray = new TypeToken<ArrayList<BluetoothItems>>() {
        }.getType();
        //String data = Prefs.loadString(getContext(), Constant.ARRAY_BLUETOOTH);
        /*Log.e(FragmentSecurityLock.class.getSimpleName(), "" + data);
        if(!TextUtils.isEmpty(data)){
            bluetoothItemslist = (new Gson()).fromJson(data, typeArray);
        }*/
//        bluetoothItemslist = (ArrayList<BluetoothItems>) Prefs.loadObjectPrefs(getContext(), Constant.BLUETOOTH_KEY, typeArray);


        if (bluetoothItemslist == null || bluetoothItemslist.size() == 0) {
            bluetoothItemslist = new ArrayList<>();
            bluetoothItemslist.add(new BluetoothItems("NICHOLE BT KEY", "PENDING", R.drawable.arrow, "mmm", "good", 0, "HONG KONG", "5 JUL, 2017", "07:26 PM", "5 JUL, 2017", "07:26 PM"));
            bluetoothItemslist.add(new BluetoothItems("JORDAN KEY", "PENDING", R.drawable.arrow, "mmm", "good", 0, "HONG KONG", "5 JUL, 2017", "07:26 PM", "5 JUL, 2017", "07:26 PM"));
            bluetoothItemslist.add(new BluetoothItems("JANNET KEY", "PENDING", R.drawable.arrow, "mmm", "good", 0, "HONG KONG", "5 JUL, 2017", "07:26 PM", "5 JUL, 2017", "07:26 PM"));
            bluetoothItemslist.add(new BluetoothItems("TEST KEY", "PENDING", R.drawable.arrow, "mmm", "good", 0, "HONG KONG", "5 JUL, 2017", "07:26 PM", "5 JUL, 2017", "07:26 PM"));
            bluetoothItemslist.add(new BluetoothItems("JASON BLUETOOTH", "ACCEPTED", R.drawable.arrow, "mmm", "good", 0, "HONG KONG", "5 JUL, 2017", "07:26 PM", "5 JUL, 2017", "07:26 PM"));

            bluetoothItemslist.add(new BluetoothItems(R.drawable.plus_assgined_lock_pin, "ADD BLUETOOTH KEY", "", 0));
            Prefs.saveList(getContext(), Constant.ARRAY_BLUETOOTH, bluetoothItemslist);

        }
        if (flag == 0) {
            createlist(activityLogItemses);
        }

        if (flagBluetooth == 3) {
            createlistBluetooth(bluetoothItemslist);
            mBinding.recycActivityLog.setVisibility(View.VISIBLE);
            mBinding.constraintLayoutLockInfo.setVisibility(View.GONE);
            mBinding.includeLayoutAirbnb.contraintAirbnb.setVisibility(View.GONE);
            mBinding.headerSecurityDoorLock.setText("BLUETOOTH KEY");
            mBinding.contraintRecycActivityLog.setVisibility(View.VISIBLE);
            enableTabAtIndex(2);
        }

        imgLockActivityLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mBinding.constraintLayoutLockInfo.setVisibility(View.VISIBLE);
                mBinding.includeLayoutAirbnb.contraintAirbnb.setVisibility(View.GONE);
                mBinding.recycActivityLog.setVisibility(View.GONE);
                mBinding.headerSecurityDoorLock.setText("LOCK INFORMATION");
                mBinding.contraintRecycActivityLog.setVisibility(View.GONE);
                enableTabAtIndex(0);
            }
        });

        imgPin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
                layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                mBinding.headerSecurityDoorLock.setText("PIN");
                mBinding.recycActivityLog.setLayoutManager(layoutManager);
//                mBinding.recycActivityLog.addItemDecoration(new RecyclerDividerItemDecoration(getActivity(), 39, 38));
                pinAdapter = new PinAdapter(pinItems) {
                    @Override
                    public void onItemSelected(int position) {
                        mInteraction.openFragment(Enumes.FragmentType.FRAGMENT_SECURITY_ASSIGNED_LOCK_PIN, null, Enumes.Direction.RIGHT_IN);

                    }
                };
                mBinding.recycActivityLog.setAdapter(pinAdapter);
                mBinding.headerSecurityDoorLock.setText("PIN");
                mBinding.constraintLayoutLockInfo.setVisibility(View.GONE);
                mBinding.includeLayoutAirbnb.contraintAirbnb.setVisibility(View.GONE);
                mBinding.recycActivityLog.setVisibility(View.VISIBLE);
                mBinding.contraintRecycActivityLog.setVisibility(View.VISIBLE);
                enableTabAtIndex(1);
            }
        });
        imgBluetoothActivityLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                createlistBluetooth(bluetoothItemslist);
                mBinding.headerSecurityDoorLock.setText("BLUETOOTH KEY");
                mBinding.recycActivityLog.setVisibility(View.VISIBLE);
                mBinding.constraintLayoutLockInfo.setVisibility(View.GONE);
                mBinding.includeLayoutAirbnb.contraintAirbnb.setVisibility(View.GONE);
                mBinding.contraintRecycActivityLog.setVisibility(View.VISIBLE);
                enableTabAtIndex(2);
            }
        });
        imgActivityLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createlist(activityLogItemses);
                mBinding.headerSecurityDoorLock.setText("ACTIVITY LOG");
                mBinding.recycActivityLog.setVisibility(View.VISIBLE);
                mBinding.constraintLayoutLockInfo.setVisibility(View.GONE);
                mBinding.includeLayoutAirbnb.contraintAirbnb.setVisibility(View.GONE);
                mBinding.contraintRecycActivityLog.setVisibility(View.VISIBLE);

                enableTabAtIndex(3);
            }
        });
        imgAIRBNBativityLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mBinding.headerSecurityDoorLock.setText("AIRBNBA");
                mBinding.constraintLayoutLockInfo.setVisibility(View.GONE);
                mBinding.includeLayoutAirbnb.contraintAirbnb.setVisibility(View.VISIBLE);
                mBinding.recycActivityLog.setVisibility(View.GONE);
                mBinding.contraintRecycActivityLog.setVisibility(View.GONE);
                enableTabAtIndex(4);
            }
        });


        //set text from fragmentEditName
        String name = Prefs.getStringPrefs(getContext(), "name");
        if (name != null && !name.trim().isEmpty())
            mBinding.txtSecurityLockChanged.setText(name);
        mBinding.txtEditnameSecurityLock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String txt;
                Bundle bundle = new Bundle();
                txt = mBinding.txtSecurityLockChanged.getText().toString();
                bundle.putString("txt", txt);
//                Prefs.setStringPrefs(getContext(), "txt" , mBinding.txtSecurityLockChanged.getText().toString());

                mInteraction.openFragment(Enumes.FragmentType.FRAGMENT_SECURITY_LOCK_EDITNAME, bundle, Enumes.Direction.RIGHT_IN);
            }
        });

        mBinding.imgEasyUnlock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mBinding.imgTurnOffEasyUnlock.setVisibility(View.VISIBLE);
                mBinding.imgEasyUnlock.setVisibility(View.GONE);
            }
        });
        mBinding.imgTurnOffEasyUnlock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mBinding.imgTurnOffEasyUnlock.setVisibility(View.GONE);
                mBinding.imgEasyUnlock.setVisibility(View.VISIBLE);
            }
        });

        mBinding.btnBackLockInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mInteraction.openFragment(Enumes.FragmentType.FRAGMENT_SECURITY_DOOR_LOCK, null, Enumes.Direction.RIGHT_IN);
            }
        });

        if (flag == 1) {
            LinearLayoutManager layoutManagerPin = new LinearLayoutManager(getActivity());
            layoutManagerPin.setOrientation(LinearLayoutManager.VERTICAL);
            mBinding.recycActivityLog.setLayoutManager(layoutManagerPin);
//            mBinding.recycActivityLog.addItemDecoration(new RecyclerDividerItemDecoration(getActivity()));
            pinAdapter = new PinAdapter(pinItems) {
                @Override
                public void onItemSelected(int position) {
                    mInteraction.openFragment(Enumes.FragmentType.FRAGMENT_SECURITY_ASSIGNED_LOCK_PIN, null, Enumes.Direction.RIGHT_IN);
                }
            };
            mBinding.recycActivityLog.setAdapter(pinAdapter);

            mBinding.headerSecurityDoorLock.setText("PIN");
            mBinding.constraintLayoutLockInfo.setVisibility(View.GONE);
            mBinding.includeLayoutAirbnb.contraintAirbnb.setVisibility(View.GONE);
            mBinding.recycActivityLog.setVisibility(View.VISIBLE);
            mBinding.contraintRecycActivityLog.setVisibility(View.VISIBLE);
            enableTabAtIndex(1);
        }
        return mBinding.getRoot();
    }

    public void createlist(ArrayList<ActivityLogItems> activityLogItemses) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mBinding.recycActivityLog.setLayoutManager(layoutManager);
//        mBinding.recycActivityLog.addItemDecoration(new RecyclerDividerItemDecoration(getActivity()));
        activityLogAdapter = new ActivityLogAdapter(activityLogItemses);
        mBinding.recycActivityLog.setAdapter(activityLogAdapter);
    }

    public void createlistBluetooth(ArrayList<BluetoothItems> bluetoothItemses) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mBinding.recycActivityLog.setLayoutManager(layoutManager);
//        mBinding.recycActivityLog.addItemDecoration(new RecyclerDividerItemDecoration(getActivity()));

        bluetoothAdapter = new BluetoothAdapter(bluetoothItemses) {
            @Override
            public void onItemSelected(int position) {
                if (position == (bluetoothItemses.size() - 1)) {
                    mInteraction.openFragment(Enumes.FragmentType.FRAGMNET_ADD_BLUETOOTH_KEY, null, Enumes.Direction.RIGHT_IN);
                } else {
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("Object", bluetoothItemses.get(position));
                    bundle.putSerializable("positionBluetooth", position);
                    mInteraction.openFragment(Enumes.FragmentType.FRAGMENT_SECURITY_PENDING_BLUETOOTH_KEY, bundle, Enumes.Direction.RIGHT_IN);
                }
            }
        };
        mBinding.recycActivityLog.setAdapter(bluetoothAdapter);
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
