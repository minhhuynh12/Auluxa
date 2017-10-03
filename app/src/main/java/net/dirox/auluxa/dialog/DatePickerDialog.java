package net.dirox.auluxa.dialog;

import android.app.Activity;
import android.content.DialogInterface;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;

import com.weigan.loopview.LoopView;

import net.dirox.auluxa.R;
import net.dirox.auluxa.databinding.DialogTimePickerBinding;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by MINH NGUYEN on 6/28/2017.
 */

public class DatePickerDialog {

    private static final String[] monthArrays = {"JAN", "FEB", "MAR", "APR", "MAY", "JUNE", "JULY", "AUG", "SEPT", "OCT", "NOV", "DEC"};

    public static AlertDialog datePicker(Activity activity, int startYear, int startMonth, int startDates, OnSaveButtonClicked
            onSaveButtonClicked) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        View view = LayoutInflater.from(activity).inflate(R.layout.dialog_time_picker, null, false);
        DialogTimePickerBinding binding = DataBindingUtil.bind(view);
        builder.setView(binding.getRoot());
        AlertDialog datePicker = builder.create();
        datePicker.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //mock data
        final ArrayList<String> dateList;
        ArrayList<String> monthList = new ArrayList<>();
        ArrayList<String> yearList = makeYearList(startYear);
        monthList.addAll(Arrays.asList(monthArrays));
        dateList = makeListDates(startMonth, startYear);
        //adapt default data
        binding.timeLoopPickerColumn1.setItems(dateList);
        binding.timeLoopPickerColumn2.setItems(monthList);
        binding.timeLoopPickerColumn3.setItems(yearList);
        binding.timeLoopPickerColumn1.setCurrentPosition(startDates);
        binding.timeLoopPickerColumn2.setCurrentPosition(startMonth - 1);
        binding.timeLoopPickerColumn3.setCurrentPosition(yearList.indexOf(String.valueOf(startYear)));
        //
        binding.timeLoopPickerColumn2.setListener(index -> {
            int year = Integer.valueOf(yearList.get(binding.timeLoopPickerColumn3.getSelectedItem()));
            dateList.clear();
            dateList.addAll(makeListDates(binding.timeLoopPickerColumn2.getSelectedItem() + 1, year));
            binding.timeLoopPickerColumn1.setItems(dateList);
        });
        binding.timeLoopPickerColumn3.setListener(index -> {
            if (isLeapYear(Integer.valueOf(yearList.get(index)))) {
                if (binding.timeLoopPickerColumn2.getSelectedItem() == 1) {
                    int year = Integer.valueOf(yearList.get(binding.timeLoopPickerColumn3.getSelectedItem()));
                    dateList.clear();
                    dateList.addAll(makeListDates(binding.timeLoopPickerColumn2.getSelectedItem() + 1, year));
                    binding.timeLoopPickerColumn1.setItems(dateList);
                }
            }
        });
        //
        setupDatePicker(binding.timeLoopPickerColumn3, binding.timeLoopPickerColumn2, binding.timeLoopPickerColumn1);
        //save button clicked
        binding.button.setOnClickListener(v -> {
            if (onSaveButtonClicked != null) {
                int date = Integer.valueOf(dateList.get(binding.timeLoopPickerColumn1.getSelectedItem()));
                int month = binding.timeLoopPickerColumn2.getSelectedItem();
                int year = Integer.valueOf(yearList.get(binding.timeLoopPickerColumn3.getSelectedItem()));
                onSaveButtonClicked.onSaveButtonClicked(year, month, date);
                datePicker.dismiss();
            }
        });
        return datePicker;
    }

    private static void setupDatePicker(LoopView yearView, LoopView monthView, LoopView dayView) {

        dayView.setNotLoop();
        dayView.setItemsVisibleCount(5);
        //dayView.setCurrentPosition(startDate);
        //
        monthView.setNotLoop();
        monthView.setItemsVisibleCount(5);
        //monthView.setCurrentPosition(startMonth - 1);
        //
        yearView.setNotLoop();
        yearView.setItemsVisibleCount(5);
        //yearView.setCurrentPosition(startYear);
        //
    }

    private static ArrayList<String> makeYearList(int startYear) {

        ArrayList<String> tempYearList = new ArrayList<>();
        for (int i = startYear > 100 ? startYear : startYear - 100; i < startYear + 200; i++) {
            tempYearList.add(i + "");
        }
        return tempYearList;
    }

    private static ArrayList<String> makeListDates(int month, int year) {
        month = month < 0 ? 0 : month;
        int countDates = getDateCountInMonth(month, year);
        ArrayList<String> dates = new ArrayList<>();
        for (int i = 1; i <= countDates; i++) {
            dates.add(i + "");
        }
        return dates;
    }

    private static int getDateCountInMonth(int month, int year) {
        if ((month) % 2 == 0) {
            if ((month) == 2) {
                return getFebDates(year);
            } else {
                return 30;
            }
        } else {
            return 31;
        }
    }

    private static int getFebDates(int year) {
        if (isLeapYear(year)) {
            return 29;
        }
        return 28;
    }

    private static boolean isLeapYear(int year) {
        if(year % 100 == 0 && year > 400){
            return year % 400 == 0;
        }else {
            return year % 4 == 0;
        }
        /*if (year < 400) {
            return year % 4 == 0;
        } else if (year >= 400) {
            return year % 100 == 0 && year % 400 == 0;
        }*/
    }

    public interface OnSaveButtonClicked {
        void onSaveButtonClicked(int yearSelected, int monthSelected, int dateSelected);
    }

}
