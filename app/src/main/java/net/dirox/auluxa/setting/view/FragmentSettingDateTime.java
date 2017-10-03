package net.dirox.auluxa.setting.view;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.dirox.auluxa.R;
import net.dirox.auluxa.activity.FragmentInteraction;
import net.dirox.auluxa.common.fragment.BaseFragment;
import net.dirox.auluxa.databinding.LayoutSettingsDateTimeBinding;
import net.dirox.auluxa.extras.Enumes;
import net.dirox.auluxa.utils.Constant;
import net.dirox.auluxa.utils.DateTimeUtil;
import net.dirox.auluxa.utils.Prefs;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class FragmentSettingDateTime extends BaseFragment {

    private static final String TAG = FragmentSettingDateTime.class.getSimpleName();

    private LayoutSettingsDateTimeBinding mBinding;

    private FragmentInteraction mInteraction;

    private final SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMM, yyyy");

    private boolean isTempDead;

    public static FragmentSettingDateTime newInstance() {

        Bundle args = new Bundle();

        FragmentSettingDateTime fragment = new FragmentSettingDateTime();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        mBinding = DataBindingUtil.inflate(inflater, R.layout.layout_settings_date_time, container, false);

        mBinding.btnBackSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });

        mBinding.btnSaveDateTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });

        mBinding.tsw24Hour.setText("OFF");

        mBinding.imgLeftArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    switch (mBinding.tsw24Hour.getText().toString()) {
                        case "OFF":
                            mBinding.tsw24Hour.setText("ON");
                            break;
                        case "ON":
                            mBinding.tsw24Hour.setText("OFF");
                            break;
                    }
                } catch (Exception e) {
                    Log.d(TAG, e.toString());
                }

            }
        });

        mBinding.imgRightArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    switch (mBinding.tsw24Hour.getText().toString()) {
                        case "OFF":
                            mBinding.tsw24Hour.setText("ON");
                            break;
                        case "ON":
                            mBinding.tsw24Hour.setText("OFF");
                            break;
                    }
                } catch (Exception e) {
                    Log.d(TAG, e.toString());
                }
            }
        });

        /*Set Time*/
        DateTimeUtil.getCurrentTime(mBinding.tvTime);
        mBinding.rlSetTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                int hourStart = calendar.get(Calendar.HOUR);
                int minStart = calendar.get(Calendar.MINUTE);
                int am_pm = calendar.get(Calendar.AM_PM);
                String time = (am_pm == Calendar.AM) ? "AM" : "PM";
                net.dirox.auluxa.dialog.TimePickerDialog.timePickerDialog(getActivity(), hourStart, minStart, time,
                        ((hourSelected, minSelected, timeSelected) -> {
                            String hour = (hourSelected < 10 ? "0" : "") + hourSelected;
                            String minute = (minSelected < 10 ? "0" : "") + minSelected;
                            mBinding.tvTime.setText(hour + ":" + minute + " " + timeSelected);
                        })
                ).show();
            }
        });

        /*Set Date*/
        DateTimeUtil.getCurrentDate(mBinding.tvDate);
        mBinding.rlSetDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Calendar calendar = Calendar.getInstance();
                int yearStart = calendar.get(Calendar.YEAR);
                int monthStart = calendar.get(Calendar.MONTH);
                int dateStart = calendar.get(Calendar.DATE);
                net.dirox.auluxa.dialog.DatePickerDialog.datePicker(getActivity(), yearStart, monthStart, dateStart,
                        ((yearSelected, monthSelected, dateSelected) -> {
                            Calendar dateSet = Calendar.getInstance();
                            dateSet.set(yearSelected, monthSelected, dateSelected);
                            mBinding.tvDate.setText(dateFormat.format(dateSet.getTime()));
                        })
                ).show();
            }
        });

        /*Set TimeZone*/

        if (mBinding.tvTimeZone.getText().equals("")) {
            mBinding.tvTimeZone.setText("Hanoi, Vietnam");
        } else {
            mBinding.tvTimeZone.setText(Prefs.getStringPrefs(getContext(), Constant.PREF_KEY));
        }

        mBinding.rlSetTimeZone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mInteraction.openFragment(Enumes.FragmentType.FRAGMENT_SETTING_TIMEZONE, null, Enumes.Direction.RIGHT_IN);
            }
        });

        return mBinding.getRoot();
    }

    private TimePickerDialog newTimePicker(String title, TimePickerDialog.OnTimeSetListener onTimeSet) {

        Calendar currentTime = Calendar.getInstance();
        int hour = currentTime.get(Calendar.HOUR_OF_DAY);
        int minute = currentTime.get(Calendar.MINUTE);

        TimePickerDialog timePicker;
        timePicker = new TimePickerDialog(getActivity(), TimePickerDialog.THEME_HOLO_DARK, onTimeSet, hour, minute, true);
        timePicker.setTitle(title);

        return timePicker;
    }

    private DatePickerDialog newDatePicker(String title, DatePickerDialog.OnDateSetListener onDateSet) {

        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePicker;
        //noinspection ResourceType
        datePicker = new DatePickerDialog(getActivity(), DatePickerDialog.THEME_HOLO_DARK, onDateSet, year, month, day);
        datePicker.setTitle(title);

        return datePicker;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mInteraction = (FragmentInteraction) context;
    }

}
