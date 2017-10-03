package net.dirox.auluxa.fragments.room_centre;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.dirox.auluxa.R;
import net.dirox.auluxa.activity.FragmentInteraction;
import net.dirox.auluxa.common.activity.BaseActivity;
import net.dirox.auluxa.common.fragment.BaseFragment;
import net.dirox.auluxa.data.sample.ClimateItem;
import net.dirox.auluxa.databinding.FragmentRoomCentreClimateControlBinding;
import net.dirox.auluxa.dialog.DialogSimpleList;
import net.dirox.auluxa.extras.Enumes;
import net.dirox.auluxa.utils.Constant;
import net.dirox.auluxa.utils.Prefs;
import net.dirox.auluxa.widget.ClimateControl;
import net.dirox.auluxa.widget.IconButton;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by an on 6/15/2017.
 */

public class RoomCentreClimateControlFragment extends BaseFragment implements View.OnClickListener, ClimateControl.IListener, Runnable {

    private BaseActivity baseActivity;
    private FragmentInteraction mInteraction;
    private FragmentRoomCentreClimateControlBinding mBinding;

    private ClimateItem mClimateItem;
    Handler mHandler = new Handler();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mClimateItem = getArguments().getParcelable(Constant.CLIMATE_ITEM);

        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_room_centre_climate_control, container, false);
        mBinding.setItem(mClimateItem);

        mBinding.btnPower.setOn(mClimateItem.isOn);
        mBinding.btnPower.setAsPowerButton(new IconButton.ICallback() {
            @Override
            public void onClick(boolean isOn) {
                onUserSet(isOn);
            }
        });

        onUserSet(mClimateItem.temp);
        onUserSet(mClimateItem.isOn);

        initHeadText();
        initFanText();
        displayCountDownTimer();

        mBinding.btnStop.setText(mClimateItem.startStop.toString());
        if (Prefs.getStringPrefs(getContext(), Constant.CLIMATE_UNIT).equals("FAHRENHEIT")) {
            mBinding.textView18.setText("°F");
            mBinding.txtTemp.setText(11 + "°F");
        } else {
            mBinding.textView18.setText("°C");
            mBinding.txtTemp.setText(11 + "°C");
        }

        /*mBinding.txtTemp.setText("" + mClimateItem.temp);
        mBinding.txtHumidity.setText("" + mClimateItem.humidity);*/

        mBinding.climateControl.setListner(this);

        mBinding.btnBack.setOnClickListener(this);
        mBinding.btnHeat.setOnClickListener(this);
        mBinding.btnLow.setOnClickListener(this);
        mBinding.btnStop.setOnClickListener(this);
        mBinding.btnSchedule.setOnClickListener(this);

        return mBinding.getRoot();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        baseActivity = (BaseActivity) getActivity();
        mInteraction = (FragmentInteraction) context;
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        List<String> stringList = new ArrayList<>();

        switch (id) {

            case R.id.btnBack:
                getActivity().onBackPressed();
                break;

            case R.id.climateControl:
                break;

            case R.id.btnHeat:
                stringList.add(ClimateItem.SysMode.HEAT.toString());
                stringList.add(ClimateItem.SysMode.AUTO.toString());
                stringList.add(ClimateItem.SysMode.COOL.toString());

                baseActivity.showDialogBasicList("Select mode", stringList, new DialogSimpleList.ListenerBasicListDialog() {
                    @Override
                    public void onSelectItem(int selectedPosition, String stringSelected) {
                        mClimateItem.sysMode = ClimateItem.SysMode.valueOf(stringSelected);
                        initHeadText();
                    }
                });
                break;

            case R.id.btnLow:
                stringList.add(ClimateItem.FanMode.HIGH.toString());
                stringList.add(ClimateItem.FanMode.MED.toString());
                stringList.add(ClimateItem.FanMode.LOW.toString());
                baseActivity.showDialogBasicList("Fan speed", stringList, new DialogSimpleList.ListenerBasicListDialog() {
                    @Override
                    public void onSelectItem(int selectedPosition, String stringSelected) {
                        mClimateItem.fanMode = ClimateItem.FanMode.valueOf(stringSelected);
                        initFanText();
                    }
                });
                break;

            case R.id.btnStop:
                mBinding.btnStop.setText(mClimateItem.toggleStartStop());
                break;

            case R.id.btnSchedule:
                mInteraction.openFragment(Enumes.FragmentType.FRAGMENT_ROOM_CENTRE_CLIMATE_SCHEDULE, getArguments(), Enumes.Direction.RIGHT_IN);
                break;
        }
    }

    @Override
    public void onUserSet(int temp) {
        mBinding.climateControl.setClimate(temp);

        //mBinding.climateDevice.CONTROL(temp);
    }

    @Override
    public void onUserSet(boolean on) {
        mBinding.climateControl.setOn(on);
        mBinding.btnPower.setOn(on);
        //mBinding.climateDevice.CONTROL(on ? Device.SysMode.Auto : Device.SysMode.Off);
    }

    @Override
    public void onTextChangeColor(int color) {
        mBinding.textView18.setTextColor(color);
    }

    @Override
    public void run() {
        displayCountDownTimer();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mHandler.removeCallbacks(this);
    }

    public void onResumeFromBackStack() {
        Log.d("ClimateControlFragment", "onResumeFromBackStack");
        mHandler.postDelayed(this, 1000);

    }

    private void initHeadText() {
        mBinding.btnHeat.setText(mClimateItem.sysMode.toString());
    }

    private void initFanText() {
        mBinding.btnLow.setText(mClimateItem.fanMode.toString());
    }

    private void displayCountDownTimer() {

        if (mClimateItem != null && mClimateItem.sleepTime != null) {
            String stringSleep = getStringDataTime(mClimateItem.sleepTime);
            if (stringSleep != null && stringSleep.length() > 0) {
                mBinding.txtSleepTimer.setText(stringSleep);
                mBinding.txtSleep.setVisibility(View.VISIBLE);
                mBinding.txtSleepTimer.setVisibility(View.VISIBLE);
            } else {
                mBinding.txtSleep.setVisibility(View.GONE);
                mBinding.txtSleepTimer.setVisibility(View.GONE);
            }

            /*long second = (mClimateItem.sleepTime.getTime() - System.currentTimeMillis()) / 1000;
            if (second > 1) {
                mBinding.txtSleepTimer.setText(getStringTimer(second));
                mBinding.txtSleep.setVisibility(View.VISIBLE);
                mBinding.txtSleepTimer.setVisibility(View.VISIBLE);
            } else {
                mBinding.txtSleep.setVisibility(View.GONE);
                mBinding.txtSleepTimer.setVisibility(View.GONE);
            }*/
        }

        mHandler.postDelayed(this, 1000);
    }

    private String getStringTimer(long second) {
        int hour = (int) (second / 3600);
        int remainder = (int) (second - (hour * 3600));
        int min = remainder / 60;
        remainder = remainder - (min * 60);
        int secs = remainder;

        if (hour > 0) {
            return String.format(Locale.getDefault(), "%02d:%02d:%02d", hour, min, secs);
        } else {
            return String.format(Locale.getDefault(), "%02d:%02d", min, secs);
        }
    }

    private String getStringDataTime(Date date) {
        try {
            DateFormat dateFormat = new SimpleDateFormat("hh:mm a");
            return dateFormat.format(date);
        } catch (Exception ex) {
            return null;
        }
    }


}
