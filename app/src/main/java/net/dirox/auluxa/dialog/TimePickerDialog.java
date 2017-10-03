package net.dirox.auluxa.dialog;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;

import com.weigan.loopview.LoopView;

import net.dirox.auluxa.R;
import net.dirox.auluxa.databinding.DialogTimePickerBinding;

import java.util.ArrayList;

/**
 * Created by MINH NGUYEN on 6/26/2017.
 */

public class TimePickerDialog {

    public static AlertDialog timePickerDialog(Activity activity, int hourStart, int minStart, String timeStart, TimePickerDialog
            .OnSaveButtonClicked onSaveButtonClicked) {
        ArrayList<String> listTime = new ArrayList<>();
        for (int i = 0; i < 60; i++) {
            listTime.add(i + "");
        }
        View view = LayoutInflater.from(activity).inflate(R.layout.dialog_time_picker, null, false);
        DialogTimePickerBinding binding = DataBindingUtil.bind(view);
        AlertDialog.Builder builder = new AlertDialog.Builder(activity).setView(binding.getRoot());
        AlertDialog alertDialog = builder.create();
        alertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //mock data
        ArrayList<String> timeHoursList = makeHourList();
        ArrayList<String> timeMinsList = makeMinList();
        ArrayList<String> timeTimeList = new ArrayList<>();
        timeTimeList.add("AM");
        timeTimeList.add("PM");
        //adapt data default
        binding.timeLoopPickerColumn1.setItems(timeHoursList);
        binding.timeLoopPickerColumn2.setItems(timeMinsList);
        binding.timeLoopPickerColumn3.setItems(timeTimeList);
        //
        binding.timeLoopPickerColumn1.setCurrentPosition(hourStart);
        binding.timeLoopPickerColumn2.setCurrentPosition(minStart);
        binding.timeLoopPickerColumn3.setCurrentPosition(timeTimeList.indexOf(String.valueOf(timeStart)));
        //
        setupTimePicker(binding.timeLoopPickerColumn1, binding.timeLoopPickerColumn2, binding.timeLoopPickerColumn3);
        binding.button.setOnClickListener(v -> {
            if (onSaveButtonClicked != null) {
                int hour = Integer.valueOf(timeHoursList.get(binding.timeLoopPickerColumn1.getSelectedItem()));
                int min = Integer.valueOf(timeMinsList.get(binding.timeLoopPickerColumn2.getSelectedItem()));
                String time = timeTimeList.get(binding.timeLoopPickerColumn3.getSelectedItem());
                onSaveButtonClicked.onSaveButtonClicked(hour, min, time);
            }
            alertDialog.dismiss();
        });
        return alertDialog;
    }

    private static void setupTimePicker(LoopView timeHours, LoopView timeMins, LoopView timeTime) {
        //mock data
      /*  ArrayList<String> timeHoursList = new ArrayList<>();
        ArrayList<String> timeMinsList = new ArrayList<>();
        ArrayList<String> timeTimeList = new ArrayList<>();


        timeTimeList.add("AM");
        timeTimeList.add("PM");*/
        //
        //timeHours.setItems(timeHoursList);
        timeHours.setItemsVisibleCount(5);
        timeHours.setNotLoop();
        //timeMins.setItems(timeMinsList);
        timeMins.setItemsVisibleCount(5);
        timeMins.setNotLoop();
        //timeTime.setItems(timeTimeList);
        timeTime.setItemsVisibleCount(5);
        timeTime.setNotLoop();
    }

    private static ArrayList<String> makeHourList() {
        ArrayList<String> hourList = new ArrayList<>();
        for (int i = 1; i <= 12; i++) {
            hourList.add(i + "");
        }
        return hourList;
    }

    private static ArrayList<String> makeMinList() {
        ArrayList<String> minList = new ArrayList<>();
        for (int i = 0; i < 60; i++) {
            minList.add(i + "");
        }
        return minList;
    }

    public interface OnSaveButtonClicked {
        /**
         * return values of time after save button clicked
         *
         * @param hour int
         * @param min  int
         * @param time "AM" or "PM"
         */
        void onSaveButtonClicked(int hour, int min, String time);
    }
}
