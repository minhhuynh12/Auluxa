package net.dirox.auluxa.security.view;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
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
import net.dirox.auluxa.databinding.FragmentSecurityDurationPinBinding;
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
 * Created by DoanThiPhuongHuyen on 02/08/2017.
 */

public class FragmentSecurityDurationPin extends BaseFragment {

   // private FragmentSecurityDurationPinDeatailBinding mBinding;
    private FragmentSecurityDurationPinBinding mBinding;
    private FragmentInteraction mInteraction;
    AssignedLockPinItem item;
    ArrayList<CountryItem> arrayListCountry = new ArrayList<>();
    private int position;
    ListCountryAdapter clad;
    ListView lv;
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("d MMM, yyyy");

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
       // mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_security_duration_pin_deatail, container, false);
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_security_duration_pin, container, false);
        setEnabled(false);
        initListCountry();
        mBinding.textViewStartDate.setFilters(new InputFilter[]{new InputFilter.AllCaps()});
        mBinding.textViewStartTime.setFilters(new InputFilter[]{new InputFilter.AllCaps()});
        mBinding.textViewEndDate.setFilters(new InputFilter[]{new InputFilter.AllCaps()});
        mBinding.textViewEndTime.setFilters(new InputFilter[]{new InputFilter.AllCaps()});
        // Received data from Assigned Lock Pin
        Bundle bundle = getArguments();
        if(bundle != null)
        {
            position = bundle.getInt("Position");
            item = (AssignedLockPinItem )bundle.getSerializable("Object");
          mBinding.textViewAPContentName.setText(item.getName());
            mBinding.editTextNamePin.setText(item.getName());
            mBinding.textViewContentTimeZone.setText(item.getTimeZone());
            mBinding.textViewStartDate.setText(item.getStartDate());
            mBinding.textViewStartTime.setText(item.getStartTime());
            mBinding.textViewEndDate.setText(item.getEndDate());
            mBinding.textViewEndTime.setText(item.getEndTime());
            mBinding.textViewContentStatus.setText(item.getStatus());
            mBinding.textViewContentPin.setText(item.getDescription());
           position = bundle.getInt("Position");
            if(item.getStatus().equals("IN USE"))
            {
                mBinding.textViewContentStatus.setTextColor(Color.parseColor("#6cbe74"));
                mBinding.textViewContentStatus.setBackgroundResource(R.drawable.custom_content_pin_green);
            }else if(item.getStatus().equals("USE & EXPIRED")){
                mBinding.textViewContentStatus.setTextColor(Color.parseColor("#ff96be"));
                mBinding.textViewContentStatus.setBackgroundResource(R.drawable.custom_content_pin_pink);
            }else if(item.getStatus().equals("TO BE ACTIVATED")||item.getStatus().equals("EXPIRES IN 8 HOURS")){
                mBinding.textViewContentStatus.setTextColor(Color.parseColor("#ffb43c"));
                mBinding.textViewContentStatus.setBackgroundResource(R.drawable.custom_content_status_pin);
            }
        }

        // Click button with text: EDIT OR SAVE
        mBinding.btnEditDurationPin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mBinding.btnEditDurationPin.getText().equals("EDIT"))
                {
                    setEnabled(true);
                    mBinding.btnEditDurationPin.setText("SAVE");
                    mBinding.btnShareDurationPin.setText("REMOVE FROM LIST");
                    mBinding.textViewAPContentName.setVisibility(View.GONE);
                    mBinding.textViewName.setVisibility(View.GONE);
                    mBinding.editTextNamePin.setVisibility(View.VISIBLE);
                }
                else if(mBinding.btnEditDurationPin.getText().equals("SAVE")) {
                    if (!"".equals(mBinding.editTextNamePin.getText().toString())) {
                        setEnabled(false);
                        arrayList.get(position).setName(mBinding.editTextNamePin.getText().toString());
                        arrayList.get(position).setStartDate(mBinding.textViewStartDate.getText().toString());
                        arrayList.get(position).setStartTime(mBinding.textViewStartTime.getText().toString());
                        arrayList.get(position).setEndTime(mBinding.textViewEndTime.getText().toString());
                        arrayList.get(position).setEndDate(mBinding.textViewEndDate.getText().toString());
                        arrayList.get(position).setTimeZone(mBinding.textViewContentTimeZone.getText().toString());
                        Prefs.applyObjectPrefs(getContext(), Constant.ARRAY_PIN, arrayList);
                        mInteraction.openFragment(Enumes.FragmentType.FRAGMENT_SECURITY_ASSIGNED_LOCK_PIN, null, Enumes.Direction.RIGHT_IN);
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

            }
        });
        // Click button with text: SHARE OR REMOVE FROM LIST
        mBinding.btnShareDurationPin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(mBinding.btnShareDurationPin.getText().equals("SHARE"))
                {
                    Intent myIntentShare = new Intent(Intent.ACTION_SEND);
                    myIntentShare.setType("text/plain");
                    String shareBody ="";
                    String shareSub ="";
                    myIntentShare.putExtra(Intent.EXTRA_SUBJECT,shareBody);
                    myIntentShare.putExtra(Intent.EXTRA_TEXT,shareBody);
                    startActivity(Intent.createChooser(myIntentShare,"Share"));

                }
                //  else if(mBinding.btnShareDetailPin.getText().equals("REMOVE FROM LIST"))
                else
                {
                    // Confirm deleted
                    AlertDialog.Builder alert  = new AlertDialog.Builder(getActivity());
                    alert.setMessage("Are you sure you want to remove assigned from list?");
                    alert.setPositiveButton("Remove", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            arrayList.remove(position);
                            Prefs.applyObjectPrefs(getContext(), Constant.ARRAY_PIN, arrayList);
                            mInteraction.openFragment(Enumes.FragmentType.FRAGMENT_SECURITY_ASSIGNED_LOCK_PIN,null, Enumes.Direction.RIGHT_IN);
                        }
                    });
                    alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    });
                    alert.show();

                }
            }
        });
        // Click "Back button" to return Assigned Lock Pin
        mBinding.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mInteraction.openFragment(Enumes.FragmentType.FRAGMENT_SECURITY_ASSIGNED_LOCK_PIN, null, Enumes.Direction.RIGHT_IN);
            }
        });
        mBinding.btnTimeZone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog();
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
                            mBinding.textViewStartDate.setText(dateFormat.format(dateSet.getTime()));
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
                            mBinding.textViewEndDate.setText(dateFormat.format(dateSet.getTime()));
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
                            mBinding.textViewStartTime.setText(hour + ":" + minute + " " + timeSelected);
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
                            mBinding.textViewEndTime.setText(hour + ":" + minute + " " + timeSelected);
                        })
                ).show();
            }
        });
        return mBinding.getRoot();
    }
    public void setEnabled(boolean bln)
    {
        mBinding.btnTimeZone.setEnabled(bln);
        mBinding.btnStartDate.setEnabled(bln);
        mBinding.btnStartTime.setEnabled(bln);
        mBinding.btnEndDate.setEnabled(bln);
        mBinding.btnEndTime.setEnabled(bln);
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mInteraction = (FragmentInteraction) context;
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
        changeColorText(arrayListCountry, mBinding.textViewContentTimeZone.getText().toString());

        lv.setAdapter(clad);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
               mBinding.textViewContentTimeZone.setText(arrayListCountry.get(i).getName().toString());
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
}

