package net.dirox.auluxa.security.view;

import android.app.Dialog;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.InputFilter;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import net.dirox.auluxa.R;
import net.dirox.auluxa.activity.FragmentInteraction;
import net.dirox.auluxa.adapter.BluetoothAdapter;
import net.dirox.auluxa.adapter.ListCountryAdapter;
import net.dirox.auluxa.common.fragment.BaseFragment;
import net.dirox.auluxa.data.sample.BluetoothItems;
import net.dirox.auluxa.data.sample.CountryItem;
import net.dirox.auluxa.databinding.FragmentSecurityAddBluetoothKeyBinding;
import net.dirox.auluxa.dialog.DatePickerDialog;
import net.dirox.auluxa.dialog.TimePickerDialog;
import net.dirox.auluxa.extras.Enumes;
import net.dirox.auluxa.utils.Constant;
import net.dirox.auluxa.utils.Prefs;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import static net.dirox.auluxa.security.view.FragmentSecurityLock.bluetoothItemslist;

/**
 * Created by MyPC on 01/08/2017.
 */

public class FragmentSecurityAddBluetooth extends BaseFragment {
    private FragmentSecurityAddBluetoothKeyBinding mBinding;
    private FragmentInteraction mInteraction;
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("d MMM, yyyy");
    private BluetoothAdapter bluetoothAdapter;
    ArrayList<CountryItem> countryItems = new ArrayList<>();
    ListCountryAdapter clad;
    ListView lv;
    TextView textView;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_security_add_bluetooth_key, container, false);
        listTimeZone();
        mBinding.textStartDate.setFilters(new InputFilter[]{new InputFilter.AllCaps()});
        mBinding.textStartTime.setFilters(new InputFilter[]{new InputFilter.AllCaps()});
        mBinding.textEndDate.setFilters(new InputFilter[]{new InputFilter.AllCaps()});
        mBinding.textEndTime.setFilters(new InputFilter[]{new InputFilter.AllCaps()});


        mBinding.btnStartDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                java.util.Calendar calendar = java.util.Calendar.getInstance();
                int yearStart = calendar.get(java.util.Calendar.YEAR);
                int monthStart = calendar.get(java.util.Calendar.MONTH);
                int dateStart = calendar.get(java.util.Calendar.DATE);
                DatePickerDialog.datePicker(getActivity(), yearStart, monthStart, dateStart, ((yearSelected, monthSelected, dateSelected) -> {
                            java.util.Calendar dateSet = java.util.Calendar.getInstance();
                            dateSet.set(yearSelected, monthSelected, dateSelected);
                            mBinding.textStartDate.setText(dateFormat.format(dateSet.getTime()));
                        })
                ).show();
            }
        });
        mBinding.btnEndDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                java.util.Calendar calendar = java.util.Calendar.getInstance();
                int yearStart = calendar.get(java.util.Calendar.YEAR);
                int monthStart = calendar.get(java.util.Calendar.MONTH);
                int dateStart = calendar.get(java.util.Calendar.DATE);
                DatePickerDialog.datePicker(getActivity(), yearStart, monthStart, dateStart,
                        ((yearSelected, monthSelected, dateSelected) -> {
                            java.util.Calendar dateSet = java.util.Calendar.getInstance();
                            dateSet.set(yearSelected, monthSelected, dateSelected);
                            mBinding.textEndDate.setText(dateFormat.format(dateSet.getTime()));
                        })
                ).show();
            }
        });
        mBinding.btnStartTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                java.util.Calendar calendar = java.util.Calendar.getInstance();
                int hourStart = calendar.get(java.util.Calendar.HOUR);
                int minStart = calendar.get(java.util.Calendar.MINUTE);
                int am_pm = calendar.get(java.util.Calendar.AM_PM);
                String time = (am_pm == java.util.Calendar.AM) ? "AM" : "PM";
                TimePickerDialog.timePickerDialog(getActivity(), hourStart, minStart, time,
                        (
                                (hourSelected, minSelected, timeSelected) -> {
                                    String hour = (hourSelected < 10 ? "0" : "") + hourSelected;
                                    String minute = (minSelected < 10 ? "0" : "") + minSelected;
                                    mBinding.textStartTime.setText(hour + ":" + minute + " " + timeSelected);
                                })
                ).show();
            }
        });
        mBinding.btnEndTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                java.util.Calendar calendar = java.util.Calendar.getInstance();
                int hourStart = calendar.get(java.util.Calendar.HOUR);
                int minStart = calendar.get(java.util.Calendar.MINUTE);
                int am_pm = calendar.get(java.util.Calendar.AM_PM);
                String time = (am_pm == java.util.Calendar.AM) ? "AM" : "PM";
                TimePickerDialog.timePickerDialog(getActivity(), hourStart, minStart, time,
                        ((hourSelected, minSelected, timeSelected) -> {
                            String hour = (hourSelected < 10 ? "0" : "") + hourSelected;
                            String minute = (minSelected < 10 ? "0" : "") + minSelected;
                            mBinding.textEndTime.setText(hour + ":" + minute + " " + timeSelected);
                        })
                ).show();
            }
        });


        mBinding.btnCreateAdddBluetooth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = mBinding.editTextAddBluetooth.getText().toString();
                String timeZone = mBinding.TextViewTimeZone.getText().toString();
                String startDate = mBinding.textStartDate.getText().toString();
                String endDate = mBinding.textEndDate.getText().toString();
                String startTime = mBinding.textStartTime.getText().toString();
                String endTime = mBinding.textEndTime.getText().toString();
                BluetoothItems bluetoothItems;

                for (int i = 0; i < bluetoothItemslist.size(); i++) {
                    if (name.equals(bluetoothItemslist.get(i).getName())) {
                        Dialog dialog = new Dialog(getContext());
                        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                        dialog.setContentView(R.layout.dialog_security_add_bluetooth);
                        textView = (TextView) dialog.findViewById(R.id.txtDialogSecurityPendingBluetooth);
                        textView.setText("BLUETOOTH ALREADY EXISTS");
                        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                        dialog.findViewById(R.id.btnCancelDialogAddBluetooth).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                dialog.dismiss();
                            }
                        });
                        dialog.show();
                        return;

                    }
                }

                if (!name.trim().isEmpty() && !timeZone.trim().isEmpty() && !startDate.trim().isEmpty() && !endDate.trim().isEmpty() && !startDate.trim().isEmpty() && !endTime.trim().isEmpty()) {
                    bluetoothItems = new BluetoothItems(name,
                            "PENDING", R.drawable.arrow, "DURATION", "IN USE", 3,
                            timeZone, startDate, startTime, endDate, endTime);
                    bluetoothItemslist.add(bluetoothItemslist.size() - 1, bluetoothItems);
                    Prefs.saveObjectPrefs(getContext(), Constant.ARRAY_BLUETOOTH, bluetoothItems);
                    Bundle bundle = new Bundle();
                    bundle.putInt("backRemove", 3);

                    mInteraction.openFragment(Enumes.FragmentType.FRAGMENT_SECURITY_LOCK, bundle, Enumes.Direction.RIGHT_IN);
                } else {

                    Dialog dialog = new Dialog(getContext());
                    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                    dialog.setContentView(R.layout.dialog_security_add_bluetooth);
                    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    dialog.findViewById(R.id.btnCancelDialogAddBluetooth).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            dialog.dismiss();
                        }
                    });
                    dialog.show();
                }
            }


        });

        mBinding.imgDrowdownlistSchedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog();
            }
        });

        mBinding.btnBackAddBluetooth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putInt("backRemove", 3);
                mInteraction.openFragment(Enumes.FragmentType.FRAGMENT_SECURITY_LOCK, bundle, Enumes.Direction.RIGHT_IN);
            }
        });
        return mBinding.getRoot();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mInteraction = (FragmentInteraction) context;
    }


    private void listTimeZone() {
        countryItems.add(new CountryItem("HONG KONG", 0));
        countryItems.add(new CountryItem("Viet Nam", 0));
        countryItems.add(new CountryItem("UK", 0));
        countryItems.add(new CountryItem("USA", 0));
        clad = new ListCountryAdapter(countryItems, getActivity());
    }

    private void changeColorText(ArrayList<CountryItem> list, String text) {
        int position = 0;
        for (int i = 0; i < list.size(); i++)
            if (list.get(i).getName().equals(text)) {
                list.get(i).setColorText(1);
                position = i;
                for (int j = 0; j < list.size(); j++) {
                    if (j != position) {
                        list.get(j).setColorText(0);
                    }
                }
                clad.notifyDataSetChanged();
                lv.setAdapter(clad);
                return;
            }
    }

    private void showDialog() {
        final Dialog dialog = new Dialog(getActivity());
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_security_create_duration_pin_list_country, null);
        lv = (ListView) view.findViewById(R.id.listCountry);
        changeColorText(countryItems, mBinding.TextViewTimeZone.getText().toString());
        lv.setAdapter(clad);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                mBinding.TextViewTimeZone.setText(countryItems.get(i).getName().toString());
                dialog.dismiss();
            }
        });
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setContentView(view);
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = (WindowManager.LayoutParams.MATCH_PARENT);
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.gravity = Gravity.CENTER;
        dialog.getWindow().setAttributes(lp);
        dialog.show();


    }


}
