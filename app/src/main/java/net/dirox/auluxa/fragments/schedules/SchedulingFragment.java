package net.dirox.auluxa.fragments.schedules;


import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.ViewSwitcher;

import net.dirox.auluxa.dialog.TimePickerDialog;
import net.dirox.auluxa.dialog.DatePickerDialog;

import net.dirox.auluxa.R;
import net.dirox.auluxa.activity.FragmentInteraction;
import net.dirox.auluxa.common.fragment.BaseFragment;
import net.dirox.auluxa.data.sample.ScheduleItem;
import net.dirox.auluxa.extras.Enumes;
import net.dirox.auluxa.widget.IconButton;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by an on 6/14/2017.
 */

public class SchedulingFragment extends BaseFragment {

    private static final String TAG = "ScheduleFragment";
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("MMM d, yyy");

    private View rootView;
    private ScheduleItem mScheduleItem;

    private FragmentInteraction mInteraction;

    private boolean isTempDead;
    private int colorMainTealFaded, colorTransparent, textColor, textColorWhite;

    public static SchedulingFragment newInstance(ScheduleItem s) {
        SchedulingFragment fragment = new SchedulingFragment();
        fragment.setSchedule(s);

        return fragment;
    }

    public SchedulingFragment() {

    }

    private void setSchedule(ScheduleItem item) {
        //this.isTempDead = s.id().equals(Schedule.ID_NEW);
        this.mScheduleItem = item;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_scheduling, container, false);

        colorMainTealFaded = getResources().getColor(R.color.MainTealFaded);// ContextCompat.getColor(getContext(), R.color.MainTealFaded);
        colorTransparent = getResources().getColor(R.color.colorTransparent);// ContextCompat.getColor(getContext(), R.color
        // .colorTransparent);
        textColor = getResources().getColor(R.color.colorText);//ContextCompat.getColor(getContext(), R.color.colorText);
        textColorWhite = getResources().getColor(R.color.colorTextWhite);//ContextCompat.getColor(getContext(), R.color.colorTextWhite);


        final TextView tv_scheduling = (TextView) rootView.findViewById(R.id.tv_scheduling);
        final TextView tv_autoAway = (TextView) rootView.findViewById(R.id.tv_autoAway);

        final ViewSwitcher viewSwitcher = (ViewSwitcher) rootView.findViewById(R.id.vs_scheduling);

        final View rl_date = rootView.findViewById(R.id.rl_schedulingDate);
        final View rl_time = rootView.findViewById(R.id.rl_schedulingTime);
        final TextView tv_setDate = (TextView) rootView.findViewById(R.id.tv_setDate);
        final TextView tv_setTime = (TextView) rootView.findViewById(R.id.tv_setTime);
        final LinearLayout ll_repeatSel = (LinearLayout) rootView.findViewById(R.id.ll_repeatSelection);

        final IconButton ib_checkHome = (IconButton) rootView.findViewById(R.id.ib_checkHome);
        final IconButton ib_checkAway = (IconButton) rootView.findViewById(R.id.ib_checkAway);

        rootView.findViewById(R.id.fragment_schedule_btnBack).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mInteraction.openFragment(Enumes.FragmentType.FRAGMENT_MAIN_HOME, null, Enumes.Direction.RIGHT_IN);
            }
        });

        tv_setTime.setText(mScheduleItem.select_hour);

        // Time picker
        rl_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                int hourStart = calendar.get(Calendar.HOUR);
                int minStart = calendar.get(Calendar.MINUTE);
                int am_pm = calendar.get(Calendar.AM_PM);
                String time = (am_pm == Calendar.AM) ? "AM" : "PM";
                TimePickerDialog.timePickerDialog(getActivity(), hourStart, minStart, time,
                        ((hourSelected, minSelected, timeSelected) -> {
                            String hour = (hourSelected < 10 ? "0" : "") + hourSelected;
                            String minute = (minSelected < 10 ? "0" : "") + minSelected;
                            tv_setTime.setText(hour + ":" + minute + " " + timeSelected);
                        })
                ).show();
            }
        });

        // Date picker
        rl_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                int yearStart = calendar.get(Calendar.YEAR);
                int monthStart = calendar.get(Calendar.MONTH);
                int dateStart = calendar.get(Calendar.DATE);
                DatePickerDialog.datePicker(getActivity(), yearStart, monthStart, dateStart,
                        ((yearSelected, monthSelected, dateSelected) -> {
                            Calendar dateSet = Calendar.getInstance();
                            dateSet.set(yearSelected, monthSelected, dateSelected);
                            tv_setDate.setText(dateFormat.format(dateSet.getTime()));
                        })
                ).show();
            }
        });

        for (int i = 0; i < ll_repeatSel.getChildCount(); i++) {
            TextView dayView = (TextView) ll_repeatSel.getChildAt(i);
            ScheduleItem.Day day = ScheduleItem.Day.valueOf(dayView.getText().toString().toUpperCase());

            dayView.setTag(day);
            dayView.setSelected(mScheduleItem.repeatDay(day));
            //dayView.setBackgroundColor(mScheduleItem.repeatDay(day) ? colorMainTealFaded : colorTransparent);
            dayView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    boolean dayIsOn = mScheduleItem.toggleRepeatDay(day);
                    dayView.setSelected(mScheduleItem.repeatDay(day));
                    //view.setBackgroundColor(dayIsOn ? colorMainTealFaded : colorTransparent);
                }
            });
        }

        tv_scheduling.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (viewSwitcher.getDisplayedChild() == 1) {
                    tv_autoAway.setTextColor(textColor);
                    tv_scheduling.setTextColor(colorMainTealFaded);
                    viewSwitcher.showPrevious();
                }
            }
        });

        tv_autoAway.setTextColor(textColor);
        tv_autoAway.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (viewSwitcher.getDisplayedChild() == 0) {
                    tv_scheduling.setTextColor(textColor);
                    tv_autoAway.setTextColor(colorMainTealFaded);
                    viewSwitcher.showNext();
                }
            }
        });

        ib_checkHome.setAsCheckbox(new IconButton.ICallback() {
            @Override
            public void onClick(boolean isOn) {

            }
        });
        ib_checkAway.setAsCheckbox(new IconButton.ICallback() {
            @Override
            public void onClick(boolean isOn) {

            }
        });

        rootView.findViewById(R.id.btnOpenAll).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });

        rootView.findViewById(R.id.btnCloseAll).setOnClickListener(new View.OnClickListener() {
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
