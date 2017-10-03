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
import net.dirox.auluxa.databinding.LayoutSettingsClimateSelectDeviceTempHumiditySensorBinding;

public class FragmentClimateSelectDeviceTempHumiditySensor extends BaseFragment {

    private LayoutSettingsClimateSelectDeviceTempHumiditySensorBinding mBinding;

    private FragmentInteraction mInteraction;

    private int temp;

    public static FragmentClimateSelectDeviceTempHumiditySensor newInstance() {

        Bundle args = new Bundle();

        FragmentClimateSelectDeviceTempHumiditySensor fragment = new FragmentClimateSelectDeviceTempHumiditySensor();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.layout_settings_climate_select_device_temp_humidity_sensor, container, false);

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
                    // TEMPERATURE
                    mBinding.tvTemperatureIrNode.setTextColor(Color.parseColor("#18bd9b"));

                    mBinding.maxTemperatureIrnode.setImageResource(R.drawable.plus);
                    mBinding.minusTemperatureIrnode.setImageResource(R.drawable.minus);
                    mBinding.maxTemperatureIrnode.setEnabled(true);
                    mBinding.minusTemperatureIrnode.setEnabled(true);

                    temp = Integer.parseInt(mBinding.tvTemperatureIrNode.getText().toString().substring(0, 2));

                    mBinding.maxTemperatureIrnode.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            temp += 1;
                            if (temp > 35) {
                                temp = 35;
                            }
                            mBinding.tvTemperatureIrNode.setText(String.valueOf(temp) + "°C");
                        }
                    });

                    mBinding.minusTemperatureIrnode.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            temp -= 1;
                            if (temp < 17) {
                                temp = 17;
                            }
                            mBinding.tvTemperatureIrNode.setText(String.valueOf(temp) + "°C");
                        }
                    });

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
                    // TEMPERATURE
                    mBinding.tvTemperatureIrNode.setTextColor(Color.parseColor("#C7696969"));
                    mBinding.maxTemperatureIrnode.setImageResource(R.drawable.grey_plus);
                    mBinding.minusTemperatureIrnode.setImageResource(R.drawable.grey_minus);
                    mBinding.maxTemperatureIrnode.setEnabled(false);
                    mBinding.minusTemperatureIrnode.setEnabled(false);
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
                    // TEMPERATURE
                    mBinding.tvTemperatureThermostat.setTextColor(Color.parseColor("#18bd9b"));

                    // Set Temperature
                    mBinding.maxTemperatureThermostat.setImageResource(R.drawable.plus);
                    mBinding.minusTemperatureThermostat.setImageResource(R.drawable.minus);
                    mBinding.maxTemperatureThermostat.setEnabled(true);
                    mBinding.minusTemperatureThermostat.setEnabled(true);

                    temp = Integer.parseInt(mBinding.tvTemperatureThermostat.getText().toString().substring(0, 2));

                    mBinding.maxTemperatureThermostat.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            temp += 1;
                            if (temp > 35) {
                                temp = 35;
                            }
                            mBinding.tvTemperatureThermostat.setText(String.valueOf(temp) + "°C");
                        }
                    });

                    mBinding.minusTemperatureThermostat.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            temp -= 1;
                            if (temp < 17) {
                                temp = 17;
                            }
                            mBinding.tvTemperatureThermostat.setText(String.valueOf(temp) + "°C");
                        }
                    });

                } else {
                    mBinding.layoutSelectDeviceThermostat.setAlpha(0.5f);
                    mBinding.tvSelectDeviceThermostat.setTextColor(Color.parseColor("#B0000000"));
                    // POWER
                    mBinding.tvThermostatPower.setTextColor(Color.parseColor("#C7696969"));
                    mBinding.imgThermostatPower.setImageResource(R.drawable.switch_fade_on);
                    // TEMPERATURE
                    mBinding.tvTemperatureThermostat.setTextColor(Color.parseColor("#C7696969"));
                    mBinding.maxTemperatureThermostat.setImageResource(R.drawable.grey_plus);
                    mBinding.minusTemperatureThermostat.setImageResource(R.drawable.grey_minus);
                    mBinding.maxTemperatureThermostat.setEnabled(false);
                    mBinding.minusTemperatureThermostat.setEnabled(false);
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
