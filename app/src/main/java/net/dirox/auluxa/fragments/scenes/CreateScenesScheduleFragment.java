package net.dirox.auluxa.fragments.scenes;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.text.InputFilter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ToggleButton;

import net.dirox.auluxa.R;
import net.dirox.auluxa.activity.FragmentInteraction;
import net.dirox.auluxa.common.fragment.BaseFragment;
import net.dirox.auluxa.data.sample.ScenesItem;
import net.dirox.auluxa.databinding.FragmentScenesCreateScheduleBinding;
import net.dirox.auluxa.dialog.DatePickerDialog;
import net.dirox.auluxa.dialog.TimePickerDialog;
import net.dirox.auluxa.utils.ScreenUtil;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by an on 6/28/2017.
 */

public class CreateScenesScheduleFragment extends BaseFragment {

    private FragmentInteraction mInteraction;

    private FragmentScenesCreateScheduleBinding mBinding;

    public static ScenesItem scenesItem;

    private final SimpleDateFormat dateFormat = new SimpleDateFormat("MMM d, yyy");

    ArrayList<String> listDays = new ArrayList<>();

    //String[] days = new String[]{"SUN", "MON", "TUE", "WED", "THU", "FRI", "SAT"};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_scenes_create_schedule, container, false);
        scenesItem = new ScenesItem();
        mBinding.setItem(scenesItem);

        mBinding.textStartDate.setFilters(new InputFilter[]{new InputFilter.AllCaps()});
        mBinding.textEndDate.setFilters(new InputFilter[]{new InputFilter.AllCaps()});
        mBinding.textSelectTime.setFilters(new InputFilter[]{new InputFilter.AllCaps()});
        mBinding.textSetRepeat.setFilters(new InputFilter[]{new InputFilter.AllCaps()});

        setEnableViews(false);

        mBinding.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });

        mBinding.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                setEnableViews(isChecked);
            }
        });

        mBinding.btnStartDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                int yearStart = calendar.get(Calendar.YEAR);
                int monthStart = calendar.get(Calendar.MONTH);
                int dateStart = calendar.get(Calendar.DATE);
                DatePickerDialog.datePicker(getActivity(), yearStart, monthStart, dateStart,
                        ((yearSelected, monthSelected, dateSelected) -> {
                            Calendar dateSet = Calendar.getInstance();
                            dateSet.set(yearSelected, monthSelected, dateSelected);
                            mBinding.textStartDate.setText(dateFormat.format(dateSet.getTime()));
                        })
                ).show();
            }
        });

        mBinding.btnEndDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                int yearStart = calendar.get(Calendar.YEAR);
                int monthStart = calendar.get(Calendar.MONTH);
                int dateStart = calendar.get(Calendar.DATE);
                DatePickerDialog.datePicker(getActivity(), yearStart, monthStart, dateStart,
                        ((yearSelected, monthSelected, dateSelected) -> {
                            Calendar dateSet = Calendar.getInstance();
                            dateSet.set(yearSelected, monthSelected, dateSelected);
                            mBinding.textEndDate.setText(dateFormat.format(dateSet.getTime()));
                        })
                ).show();
            }
        });

        // Time picker
        mBinding.btnSelectTime.setOnClickListener(new View.OnClickListener() {
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
                            mBinding.textSelectTime.setText(hour + ":" + minute + " " + timeSelected);
                        })
                ).show();
            }
        });

        mBinding.btnSetRepeat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mBinding.textSetRepeat.setText("SET");
                mBinding.layoutWeek.setVisibility(View.VISIBLE);
            }
        });

        mBinding.textSetRepeat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = mBinding.textSetRepeat.getText().toString();
                if (text.equals("SET")) {
                    mBinding.layoutWeek.setVisibility(View.GONE);
                    //mBinding.textSetRepeat.setText("TUE, THU");
                    LinearLayout parent = mBinding.layoutWeek;
                    listDays = new ArrayList<String>();

                    ArrayList<View> allViewsWithinMyTopView = ScreenUtil.getAllChildren(parent);
                    for (View child : allViewsWithinMyTopView) {
                        if (child instanceof ToggleButton) {
                            if(((ToggleButton) child).isChecked()){
                               listDays.add(child.getTag().toString());
                            }
                        }
                    }

                    mBinding.textSetRepeat.setText(listToStringDays(listDays));
                    
                } else {
                    mBinding.layoutWeek.setVisibility(View.VISIBLE);
                    mBinding.textSetRepeat.setText("SET");
                }
            }
        });

        mBinding.btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });

        mBinding.btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });

        return mBinding.getRoot();
    }

    private String listToStringDays(ArrayList<String> list){

        if(list == null || list.size() == 0)
            return "";

        String daysString = "";
        for (String str: list) {
               daysString += str + ",";
        }

        daysString = daysString.substring(0, daysString.length() - 1);

        return daysString;
    }

    private void setEnableViews(boolean isEnabled)
    {
        if(isEnabled){
            mBinding.btnStartDate.setEnabled(true);
            mBinding.btnEndDate.setEnabled(true);
            mBinding.btnSelectTime.setEnabled(true);
            mBinding.btnSetRepeat.setEnabled(true);
            mBinding.textSetRepeat.setEnabled(true);

            ((TextView)mBinding.btnStartDate.getChildAt(0)).setTextColor(getResources().getColor(R.color.black));
            ((TextView)mBinding.btnEndDate.getChildAt(0)).setTextColor(getResources().getColor(R.color.black));
            ((TextView)mBinding.btnSelectTime.getChildAt(0)).setTextColor(getResources().getColor(R.color.black));
            ((TextView)mBinding.btnSetRepeat.getChildAt(0)).setTextColor(getResources().getColor(R.color.black));

            mBinding.textStartDate.setTextColor(getResources().getColor(R.color.auluxaGreen));
            mBinding.textEndDate.setTextColor(getResources().getColor(R.color.auluxaGreen));
            mBinding.textSelectTime.setTextColor(getResources().getColor(R.color.auluxaGreen));
            mBinding.textSetRepeat.setTextColor(getResources().getColor(R.color.auluxaGreen));
        }
        else {
            mBinding.btnStartDate.setEnabled(false);
            mBinding.btnEndDate.setEnabled(false);
            mBinding.btnSelectTime.setEnabled(false);
            mBinding.btnSetRepeat.setEnabled(false);
            mBinding.textSetRepeat.setEnabled(false);

            ((TextView)mBinding.btnStartDate.getChildAt(0)).setTextColor(getResources().getColor(R.color.colorOverlayGray));
            ((TextView)mBinding.btnEndDate.getChildAt(0)).setTextColor(getResources().getColor(R.color.colorOverlayGray));
            ((TextView)mBinding.btnSelectTime.getChildAt(0)).setTextColor(getResources().getColor(R.color.colorOverlayGray));
            ((TextView)mBinding.btnSetRepeat.getChildAt(0)).setTextColor(getResources().getColor(R.color.colorOverlayGray));

            mBinding.textStartDate.setTextColor(getResources().getColor(R.color.colorOverlayGray));
            mBinding.textEndDate.setTextColor(getResources().getColor(R.color.colorOverlayGray));
            mBinding.textSelectTime.setTextColor(getResources().getColor(R.color.colorOverlayGray));
            mBinding.textSetRepeat.setTextColor(getResources().getColor(R.color.colorOverlayGray));
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mInteraction = (FragmentInteraction) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        scenesItem = null;
    }

}
