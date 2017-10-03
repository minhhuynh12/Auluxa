package net.dirox.auluxa.security.view;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.databinding.DataBindingUtil;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.text.InputFilter;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;

import net.dirox.auluxa.R;
import net.dirox.auluxa.activity.FragmentInteraction;
import net.dirox.auluxa.adapter.ListCountryAdapter;
import net.dirox.auluxa.common.fragment.BaseFragment;
import net.dirox.auluxa.data.sample.AssignedLockPinItem;
import net.dirox.auluxa.data.sample.CountryItem;
import net.dirox.auluxa.databinding.FragmentSecurityCreateDurationPinBinding;
import net.dirox.auluxa.dialog.DatePickerDialog;
import net.dirox.auluxa.dialog.TimePickerDialog;
import net.dirox.auluxa.extras.Enumes;
import net.dirox.auluxa.utils.Constant;
import net.dirox.auluxa.utils.Prefs;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import static net.dirox.auluxa.security.view.FragmentSecurityAssignedLockPin.arrayList;

/**
 * Created by DoanThiPhuongHuyen on 31/07/2017.
 */

public class FragmentSecurityCreateDurationPin extends BaseFragment {
    private FragmentSecurityCreateDurationPinBinding mBinding;

    private FragmentInteraction mInteraction;
    AssignedLockPinItem assignedLockPinItem;
    ArrayList<CountryItem> arrayListCountry = new ArrayList<>();
    private int position;
    ListCountryAdapter clad;
    ListView lv;
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("d MMM, yyyy");
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_security_create_duration_pin, container, false);
        initListCountry();
        mBinding.textStartDate.setFilters(new InputFilter[]{new InputFilter.AllCaps()});
        mBinding.textStartTime.setFilters(new InputFilter[]{new InputFilter.AllCaps()});
        mBinding.textEndDate.setFilters(new InputFilter[]{new InputFilter.AllCaps()});
        mBinding.textEndTime.setFilters(new InputFilter[]{new InputFilter.AllCaps()});
        mBinding.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mInteraction.openFragment(Enumes.FragmentType.FRAGMENT_SECURITY_CREATE_LOCK_PIN, null, Enumes.Direction.RIGHT_IN);
            }
        });
        mBinding.btnStartDate.setOnClickListener(new View.OnClickListener() {
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
        mBinding.btnStartTime.setOnClickListener(new View.OnClickListener() {
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
                            mBinding.textStartTime.setText(hour + ":" + minute + " " + timeSelected);
                        })
                ).show();
            }
        });
        mBinding.btnEndTime.setOnClickListener(new View.OnClickListener() {
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
                            mBinding.textEndTime.setText(hour + ":" + minute + " " + timeSelected);
                        })
                ).show();
            }
        });
        mBinding.imgDrowdownlistSchedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog();
            }
        });
       mBinding.btnCreateDurationPin.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               String name = mBinding.textViewNamePin.getText().toString();
               String timeZone =  mBinding.TextViewTimeZone.getText().toString();
               String startDate = mBinding.textStartDate.getText().toString();
               String endDate =  mBinding.textEndDate.getText().toString();
               String startTime = mBinding.textStartTime.getText().toString();
               String endTime = mBinding.textEndTime.getText().toString();
               if(!"".equals(name) && !"".equals(timeZone) && !"".equals(startDate) && !"".equals(endDate) && !"".equals(startTime) && !"".equals(endTime) )
               {
                   assignedLockPinItem = new AssignedLockPinItem(name,
                           "19871987", R.drawable.arrow, "DURATION", "IN USE", 3,
                           timeZone, startDate, startTime, endDate, endTime);
                   arrayList.add(arrayList.size() - 1, assignedLockPinItem);
                   Prefs.applyObjectPrefs(getContext(), Constant.ARRAY_PIN, arrayList);
                   mInteraction.openFragment(Enumes.FragmentType.FRAGMENT_SECURITY_ASSIGNED_LOCK_PIN, null, Enumes.Direction.RIGHT_IN);
               }
               else if(checkData(arrayList,name)){
                   AlertDialog.Builder alert  = new AlertDialog.Builder(getActivity());
                   alert.setMessage("Information already exists, please enter information again");
                   alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                       @Override
                       public void onClick(DialogInterface dialogInterface, int i) {

                       }
                   });
                   alert.show();
               }
               else{
                   AlertDialog.Builder alert  = new AlertDialog.Builder(getActivity());
                   alert.setMessage("You have to fill full information");
                   alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                       @Override
                       public void onClick(DialogInterface dialogInterface, int i) {
                           //Send data which want to be deleted from the list to Assigned Lock Pin

                       }
                   });

                   alert.show();
               }
           }
       });
        return mBinding.getRoot();
    }
    private void initListCountry(){

        arrayListCountry.add(new CountryItem("HONG KONG",0));
        arrayListCountry.add(new CountryItem("VIET NAM",0));
        arrayListCountry.add(new CountryItem("KOREA",0));
        arrayListCountry.add(new CountryItem("JAPAN",0));
        arrayListCountry.add(new CountryItem("FRANCE",0));
        clad = new ListCountryAdapter(arrayListCountry, getActivity());
    }
    private void changeColorText(ArrayList<CountryItem> list, String text)
    {
        int vitri=0;
        for(int i=0;i< list.size();i++)
            if(list.get(i).getName().equals(text))
            {
                list.get(i).setColorText(1);
                vitri = i;
                for(int j=0;j< list.size();j++)
                {
                    if(j!=vitri)
                    {
                        list.get(j).setColorText(0);
                    }
                }
                clad.notifyDataSetChanged();
                lv.setAdapter(clad);
                return;
            }
    }
    private void showDialog(){
        final Dialog dialog = new Dialog(getActivity());
        View view  = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_security_create_duration_pin_list_country, null);
        lv = (ListView) view.findViewById(R.id.listCountry);
        changeColorText(arrayListCountry, mBinding.TextViewTimeZone.getText().toString());

        lv.setAdapter(clad);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                mBinding.TextViewTimeZone.setText(arrayListCountry.get(i).getName().toString());
                dialog.dismiss();
            }
        });
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setContentView(view);
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.WRAP_CONTENT ;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.gravity = Gravity.CENTER;
        dialog.getWindow().setAttributes(lp);
        dialog.show();
    }
    public boolean checkData(ArrayList<AssignedLockPinItem> arrayList, String name)
    {
        for(int i = 0;i< arrayList.size()-1;i++)
            if(arrayList.get(i).getName().equals(name))
            {
                return true;
            }
        return false;
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mInteraction = (FragmentInteraction) context;
    }
    @Override
    public void onDetach() {
        super.onDetach();

    }

}
