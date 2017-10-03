package net.dirox.auluxa.fragments.room_centre;

import android.app.TimePickerDialog;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import net.dirox.auluxa.R;
import net.dirox.auluxa.activity.FragmentInteraction;
import net.dirox.auluxa.common.fragment.BaseFragment;
import net.dirox.auluxa.data.sample.SchedulingItem;
import net.dirox.auluxa.databinding.FragmentClimateSchedulingDetailBinding;
import net.dirox.auluxa.utils.DateTimeUtil;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by trungnq on 15/06/2017.
 */

public class ClimateSchedulingDetailFragment extends BaseFragment {

    private static final String TAG = ClimateSchedulingDetailFragment.class.getSimpleName();
    FragmentClimateSchedulingDetailBinding mBinding;

    private FragmentInteraction mInteraction;

    private View rootView;

    private SchedulingItem item;

    private boolean isTempDead;

    private final SimpleDateFormat dateFormat = new SimpleDateFormat("MMM d, yyy");

    private int temp;

    // Array mode of fan speed
    String fanSpeed[] = {"HIGH", "MED", "LOW", "OFF"};
    int countFanSpeed = fanSpeed.length;
    // to keep current Index of textID array
    int fanSpeedCurrentIndex = -1;

    // Array mode of mode
    String modes[] = {"HEAT", "AUTO", "COOL"};
    int countMode = modes.length;
    int modeCurrentIndex = -1;

    public ClimateSchedulingDetailFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_climate_scheduling_detail, container, false);

        // Get Schedule Title
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            mBinding.tvSchedulingDetail.setText(bundle.getString("SCHEDULE_NAME"));
        }

        // Set Timer
        DateTimeUtil.getCurrentTime(mBinding.tvTimerSchedule);

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
                            mBinding.tvTimerSchedule.setText(hour + ":" + minute + " " + timeSelected);
                        })
                ).show();
            }
        });

        // Set Temperature
        temp = Integer.parseInt(mBinding.tvTemperature.getText().toString().substring(0, 2));

        mBinding.maxTemperature.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                temp += 1;
                if (temp > 50) {
                    temp = 50;
                }
                mBinding.tvTemperature.setText(String.valueOf(temp) + "°C");
            }
        });

        mBinding.minusTemperature.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                temp -= 1;
                if (temp < 0) {
                    temp = 0;
                }
                mBinding.tvTemperature.setText(String.valueOf(temp) + "°C");
            }
        });

        // Set Mode Fan Speed
        mBinding.tvFanSpeedSetting.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                TextView t = new TextView(getContext());
                t.setTextSize(16);
                t.setTextColor(getResources().getColor(R.color.auluxaGreen));
                t.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL);
                return t;
            }
        });

        mBinding.tvFanSpeedSetting.setCurrentText("HIGH");
        mBinding.rightFanSpeed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    fanSpeedCurrentIndex++;
                    if (fanSpeedCurrentIndex == countFanSpeed)
                        fanSpeedCurrentIndex = 0;
                    mBinding.tvFanSpeedSetting.setText(fanSpeed[fanSpeedCurrentIndex]);
                } catch (Exception e) {
                    Log.d(TAG, e.toString());
                }
            }
        });

        mBinding.leftFanSpeed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    fanSpeedCurrentIndex++;
                    if (fanSpeedCurrentIndex == countFanSpeed)
                        fanSpeedCurrentIndex = 0;
                    mBinding.tvFanSpeedSetting.setText(fanSpeed[fanSpeedCurrentIndex]);
                } catch (Exception e) {
                    Log.d(TAG, e.toString());
                }
            }
        });

        // Set Mode
        mBinding.tvModeSetting.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                TextView t = new TextView(getContext());
                t.setTextSize(16);
                t.setTextColor(getResources().getColor(R.color.auluxaGreen));
                t.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL);
                return t;
            }
        });

        mBinding.tvModeSetting.setCurrentText("HEAT");

        mBinding.rightMode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    modeCurrentIndex++;
                    if (modeCurrentIndex == countMode)
                        modeCurrentIndex = 0;
                    mBinding.tvModeSetting.setText(modes[modeCurrentIndex]);
                } catch (Exception e) {
                    Log.d(TAG, e.toString());
                }
            }
        });

        mBinding.leftMode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    modeCurrentIndex++;
                    if (modeCurrentIndex == countMode)
                        modeCurrentIndex = 0;
                    mBinding.tvModeSetting.setText(modes[modeCurrentIndex]);
                } catch (Exception e) {
                    Log.d(TAG, e.toString());
                }
            }
        });

        mBinding.btnScheduleDetailBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });

        // Save schedulue
        mBinding.btnSaveScheduleDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
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

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mInteraction = (FragmentInteraction) context;
    }

}
