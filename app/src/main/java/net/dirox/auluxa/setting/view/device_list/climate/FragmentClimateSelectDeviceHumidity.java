package net.dirox.auluxa.setting.view.device_list.climate;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.dirox.auluxa.R;
import net.dirox.auluxa.activity.FragmentInteraction;
import net.dirox.auluxa.common.fragment.BaseFragment;
import net.dirox.auluxa.databinding.LayoutSettingsClimateSelectDeviceHumidityBinding;

public class FragmentClimateSelectDeviceHumidity extends BaseFragment {

    private LayoutSettingsClimateSelectDeviceHumidityBinding mBinding;

    private FragmentInteraction mInteraction;


    public static FragmentClimateSelectDeviceHumidity newInstance() {

        Bundle args = new Bundle();

        FragmentClimateSelectDeviceHumidity fragment = new FragmentClimateSelectDeviceHumidity();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.layout_settings_climate_select_device_humidity, container, false);

        mBinding.btnBackSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackAndHideKeyBoard();
            }
        });

        mBinding.btnSaveSelectDevice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackAndHideKeyBoard();
            }
        });

        mBinding.btnCancelSelectDevice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackAndHideKeyBoard();
            }
        });

        mBinding.checkBoxIrNode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mBinding.checkBoxIrNode.isChecked() == true) {
                    mBinding.layoutSelectDeviceIrnode.setAlpha(1.0f);
                    mBinding.tvSelectDeviceIrNode.setTextColor(Color.parseColor("#18bd9b"));
                    // POWER
                    mBinding.tvIrNodePower.setTextColor(Color.parseColor("#18bd9b"));
                    mBinding.imgIrNodePower.setImageResource(R.drawable.switch_on);
                    // HUMIDITY
                    mBinding.tvIrNodeHumidity.setTextColor(Color.parseColor("#18bd9b"));
                    mBinding.etIrNodeHumidity.setBackgroundResource(R.drawable.green_percentage);
                    mBinding.etIrNodeHumidity.setEnabled(true);
                    mBinding.etIrNodeHumidity.setTextColor(Color.parseColor("#18bd9b"));
                } else {
                    mBinding.layoutSelectDeviceIrnode.setAlpha(0.5f);
                    mBinding.tvSelectDeviceIrNode.setTextColor(Color.parseColor("#B0000000"));
                    // POWER
                    mBinding.tvIrNodePower.setTextColor(Color.parseColor("#C7696969"));
                    mBinding.imgIrNodePower.setImageResource(R.drawable.switch_fade_on);
                    // HUMIDITY
                    mBinding.tvIrNodeHumidity.setTextColor(Color.parseColor("#C7696969"));
                    mBinding.etIrNodeHumidity.setBackgroundResource(R.drawable.grey_percentage);
                    mBinding.etIrNodeHumidity.setEnabled(false);
                    mBinding.etIrNodeHumidity.setTextColor(Color.parseColor("#C7696969"));
                }
            }
        });

        mBinding.checkBoxThermostat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mBinding.checkBoxThermostat.isChecked() == true) {
                    mBinding.layoutSelectDeviceThermostat.setAlpha(1.0f);
                    mBinding.tvSelectDeviceThermostat.setTextColor(Color.parseColor("#18bd9b"));
                    // POWER
                    mBinding.tvThermostatPower.setTextColor(Color.parseColor("#18bd9b"));
                    mBinding.imgThermostatPower.setImageResource(R.drawable.switch_on);
                    // HUMIDITY
                    mBinding.tvThermostatHumidity.setTextColor(Color.parseColor("#18bd9b"));
                    mBinding.etThermostatHumidity.setBackgroundResource(R.drawable.green_percentage);
                    mBinding.etThermostatHumidity.setEnabled(true);
                    mBinding.etThermostatHumidity.setTextColor(Color.parseColor("#18bd9b"));
                } else {
                    mBinding.layoutSelectDeviceThermostat.setAlpha(0.5f);
                    mBinding.tvSelectDeviceThermostat.setTextColor(Color.parseColor("#B0000000"));
                    // POWER
                    mBinding.tvThermostatPower.setTextColor(Color.parseColor("#C7696969"));
                    mBinding.imgThermostatPower.setImageResource(R.drawable.switch_fade_on);
                    // HUMIDITY
                    mBinding.tvThermostatHumidity.setTextColor(Color.parseColor("#C7696969"));
                    mBinding.etThermostatHumidity.setBackgroundResource(R.drawable.grey_percentage);
                    mBinding.etThermostatHumidity.setEnabled(false);
                    mBinding.etThermostatHumidity.setTextColor(Color.parseColor("#C7696969"));
                }
            }
        });


        return mBinding.getRoot();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mInteraction = (FragmentInteraction) context;
    }
}
