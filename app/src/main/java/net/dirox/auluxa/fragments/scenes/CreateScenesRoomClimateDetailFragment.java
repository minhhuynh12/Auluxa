package net.dirox.auluxa.fragments.scenes;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import net.dirox.auluxa.R;
import net.dirox.auluxa.activity.FragmentInteraction;
import net.dirox.auluxa.common.fragment.BaseFragment;
import net.dirox.auluxa.databinding.LayoutRoomClimateDetailBinding;

/**
 * Created by an on 6/29/2017.
 */

public class CreateScenesRoomClimateDetailFragment extends BaseFragment {

    private FragmentInteraction mInteraction;
    private LayoutRoomClimateDetailBinding mBinding;

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

    // Array mode of swing
    String swings[] = {"HIGH", "MED", "LOW", "OFF"};
    int countSwing = swings.length;
    int swingCurrentIndex = -1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.layout_room_climate_detail, container, false);

        mBinding.btnBackSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });

        // WINDOW
        mBinding.tvModeWindow.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                TextView t = new TextView(getContext());
                t.setTextColor(getResources().getColor(R.color.BgDarker));
                t.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL);
                return t;
            }
        });
        mBinding.tvModeWindow.setCurrentText("HEAT");

        mBinding.tvSpeedWindowSetting.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                TextView t = new TextView(getContext());
                t.setTextColor(getResources().getColor(R.color.BgDarker));
                t.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL);
                return t;
            }
        });
        mBinding.tvSpeedWindowSetting.setCurrentText("HIGH");

        mBinding.tvSwingSettingWindow.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                TextView t = new TextView(getContext());
                t.setTextColor(getResources().getColor(R.color.BgDarker));
                t.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL);
                return t;
            }
        });
        mBinding.tvSwingSettingWindow.setCurrentText("HIGH");

        mBinding.checkboxWindow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkBoxWindowChecked();
            }
        });

        // Set Temperature
        temp = Integer.parseInt(mBinding.tvTemperatureWindow.getText().toString().substring(0, 2));

        mBinding.minusTemperatureWindow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                temp -= 1;
                if (temp < 0) {
                    temp = 0;
                }
                mBinding.tvTemperatureWindow.setText(String.valueOf(temp) + "°C");
            }
        });

        mBinding.maxTemperatureWindow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                temp += 1;
                mBinding.tvTemperatureWindow.setText(String.valueOf(temp) + "°C");
            }
        });

        // Set Mode
        mBinding.rightModeWindow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    modeCurrentIndex++;
                    if (modeCurrentIndex == countMode)
                        modeCurrentIndex = 0;
                    mBinding.tvModeWindow.setText(modes[modeCurrentIndex]);
                } catch (Exception e) {
                }
            }
        });

        mBinding.leftModeWindow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    modeCurrentIndex++;
                    if (modeCurrentIndex == countMode)
                        modeCurrentIndex = 0;
                    mBinding.tvModeWindow.setText(modes[modeCurrentIndex]);
                } catch (Exception e) {
                }
            }
        });

        // Set Speed
        mBinding.rightSpeedWindow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    fanSpeedCurrentIndex++;
                    if (fanSpeedCurrentIndex == countFanSpeed)
                        fanSpeedCurrentIndex = 0;
                    mBinding.tvSpeedWindowSetting.setText(fanSpeed[fanSpeedCurrentIndex]);
                } catch (Exception e) {
                }
            }
        });

        mBinding.leftSpeedWindow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    fanSpeedCurrentIndex++;
                    if (fanSpeedCurrentIndex == countFanSpeed)
                        fanSpeedCurrentIndex = 0;
                    mBinding.tvSpeedWindowSetting.setText(fanSpeed[fanSpeedCurrentIndex]);
                } catch (Exception e) {
                }
            }
        });

        // Set Swing
        mBinding.rightSwingWindow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    swingCurrentIndex++;
                    if (swingCurrentIndex == countSwing)
                        swingCurrentIndex = 0;
                    mBinding.tvSwingSettingWindow.setText(swings[swingCurrentIndex]);
                } catch (Exception e) {
                }
            }
        });

        mBinding.leftSwingWindow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    swingCurrentIndex++;
                    if (swingCurrentIndex == countSwing)
                        swingCurrentIndex = 0;
                    mBinding.tvSwingSettingWindow.setText(swings[swingCurrentIndex]);
                } catch (Exception e) {
                }
            }
        });

        // DOOR
        mBinding.tvModeDoor.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                TextView t = new TextView(getContext());
                t.setTextColor(getResources().getColor(R.color.BgDarker));
                t.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL);
                return t;
            }
        });
        mBinding.tvModeDoor.setCurrentText("HEAT");

        mBinding.tvSpeedDoorSetting.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                TextView t = new TextView(getContext());
                t.setTextColor(getResources().getColor(R.color.BgDarker));
                t.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL);
                return t;
            }
        });
        mBinding.tvSpeedDoorSetting.setCurrentText("HIGH");

        mBinding.tvSwingSettingDoor.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                TextView t = new TextView(getContext());
                t.setTextColor(getResources().getColor(R.color.BgDarker));
                t.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL);
                return t;
            }
        });
        mBinding.tvSwingSettingDoor.setCurrentText("HIGH");

        mBinding.checkboxDoor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkBoxDoorChecked();
            }
        });

        // Set Temperature
        temp = Integer.parseInt(mBinding.tvTemperatureDoor.getText().toString().substring(0, 2));

        mBinding.minusTemperatureDoor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                temp -= 1;
                if (temp < 0) {
                    temp = 0;
                }
                mBinding.tvTemperatureDoor.setText(String.valueOf(temp) + "°C");
            }
        });

        mBinding.maxTemperatureDoor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                temp += 1;
                mBinding.tvTemperatureDoor.setText(String.valueOf(temp) + "°C");
            }
        });

        // Set Mode
        mBinding.rightModeDoor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    modeCurrentIndex++;
                    if (modeCurrentIndex == countMode)
                        modeCurrentIndex = 0;
                    mBinding.tvModeDoor.setText(modes[modeCurrentIndex]);
                } catch (Exception e) {
                }
            }
        });

        mBinding.leftModeDoor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    modeCurrentIndex++;
                    if (modeCurrentIndex == countMode)
                        modeCurrentIndex = 0;
                    mBinding.tvModeDoor.setText(modes[modeCurrentIndex]);
                } catch (Exception e) {
                }
            }
        });

        // Set Speed
        mBinding.rightSpeedDoor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    fanSpeedCurrentIndex++;
                    if (fanSpeedCurrentIndex == countFanSpeed)
                        fanSpeedCurrentIndex = 0;
                    mBinding.tvSpeedDoorSetting.setText(fanSpeed[fanSpeedCurrentIndex]);
                } catch (Exception e) {
                }
            }
        });

        mBinding.leftSpeedDoor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    fanSpeedCurrentIndex++;
                    if (fanSpeedCurrentIndex == countFanSpeed)
                        fanSpeedCurrentIndex = 0;
                    mBinding.tvSpeedDoorSetting.setText(fanSpeed[fanSpeedCurrentIndex]);
                } catch (Exception e) {
                }
            }
        });

        // Set Swing
        mBinding.rightSwingDoor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    swingCurrentIndex++;
                    if (swingCurrentIndex == countSwing)
                        swingCurrentIndex = 0;
                    mBinding.tvSwingSettingDoor.setText(swings[swingCurrentIndex]);
                } catch (Exception e) {
                }
            }
        });

        mBinding.leftSwingDoor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    swingCurrentIndex++;
                    if (swingCurrentIndex == countSwing)
                        swingCurrentIndex = 0;
                    mBinding.tvSwingSettingDoor.setText(swings[swingCurrentIndex]);
                } catch (Exception e) {
                }
            }
        });

        // BUTTON SAVE/EDIT
        mBinding.btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (Integer.parseInt(mBinding.btnSave.getTag().toString())) {
                    case 1:
                        mBinding.btnSave.setText("SAVE");
                        mBinding.checkboxWindow.setVisibility(View.VISIBLE);
                        mBinding.checkboxDoor.setVisibility(View.VISIBLE);
                        mBinding.checkboxWindow.setChecked(true);
                        mBinding.checkboxDoor.setChecked(true);
                        checkBoxWindowChecked();
                        checkBoxDoorChecked();

                        mBinding.btnSave.setTag(2);
                        break;
                    case 2:
                        mBinding.btnSave.setText("EDIT");
                        mBinding.checkboxWindow.setVisibility(View.GONE);
                        mBinding.checkboxDoor.setVisibility(View.GONE);
                        mBinding.checkboxWindow.setChecked(false);
                        mBinding.checkboxDoor.setChecked(false);
                        checkBoxWindowChecked();
                        checkBoxDoorChecked();

                        mBinding.btnSave.setTag(1);

                        getActivity().onBackPressed();
                        break;
                }


            }
        });
        return mBinding.getRoot();
    }

    public void checkBoxWindowChecked() {
        if (mBinding.checkboxWindow.isChecked() == true) {
            mBinding.minusTemperatureWindow.setVisibility(View.VISIBLE);
            mBinding.maxTemperatureWindow.setVisibility(View.VISIBLE);
            mBinding.leftModeWindow.setVisibility(View.VISIBLE);
            mBinding.rightModeWindow.setVisibility(View.VISIBLE);
            mBinding.leftSpeedWindow.setVisibility(View.VISIBLE);
            mBinding.rightSpeedWindow.setVisibility(View.VISIBLE);
            mBinding.leftSwingWindow.setVisibility(View.VISIBLE);
            mBinding.rightSwingWindow.setVisibility(View.VISIBLE);

            mBinding.tvTemperatureWindow.setTextColor(getResources().getColor(R.color.auluxaGreen));
            ((TextView) mBinding.tvModeWindow.getCurrentView()).setTextColor(getResources().getColor(R.color.auluxaGreen));
            ((TextView) mBinding.tvModeWindow.getNextView()).setTextColor(getResources().getColor(R.color.auluxaGreen));
            ((TextView) mBinding.tvSpeedWindowSetting.getCurrentView()).setTextColor(getResources().getColor(R.color.auluxaGreen));
            ((TextView) mBinding.tvSpeedWindowSetting.getNextView()).setTextColor(getResources().getColor(R.color.auluxaGreen));
            ((TextView) mBinding.tvSwingSettingWindow.getCurrentView()).setTextColor(getResources().getColor(R.color.auluxaGreen));
            ((TextView) mBinding.tvSwingSettingWindow.getNextView()).setTextColor(getResources().getColor(R.color.auluxaGreen));

        } else {
            mBinding.minusTemperatureWindow.setVisibility(View.GONE);
            mBinding.maxTemperatureWindow.setVisibility(View.GONE);
            mBinding.leftModeWindow.setVisibility(View.GONE);
            mBinding.rightModeWindow.setVisibility(View.GONE);
            mBinding.leftSpeedWindow.setVisibility(View.GONE);
            mBinding.rightSpeedWindow.setVisibility(View.GONE);
            mBinding.leftSwingWindow.setVisibility(View.GONE);
            mBinding.rightSwingWindow.setVisibility(View.GONE);

            mBinding.tvTemperatureWindow.setTextColor(getResources().getColor(R.color.BgDarker));
            ((TextView) mBinding.tvModeWindow.getCurrentView()).setTextColor(getResources().getColor(R.color.BgDarker));
            ((TextView) mBinding.tvSpeedWindowSetting.getCurrentView()).setTextColor(getResources().getColor(R.color.BgDarker));
            ((TextView) mBinding.tvSwingSettingWindow.getCurrentView()).setTextColor(getResources().getColor(R.color.BgDarker));
        }
    }

    public void checkBoxDoorChecked() {
        if (mBinding.checkboxDoor.isChecked() == true) {
            mBinding.minusTemperatureDoor.setVisibility(View.VISIBLE);
            mBinding.maxTemperatureDoor.setVisibility(View.VISIBLE);
            mBinding.leftModeDoor.setVisibility(View.VISIBLE);
            mBinding.rightModeDoor.setVisibility(View.VISIBLE);
            mBinding.leftSpeedDoor.setVisibility(View.VISIBLE);
            mBinding.rightSpeedDoor.setVisibility(View.VISIBLE);
            mBinding.leftSwingDoor.setVisibility(View.VISIBLE);
            mBinding.rightSwingDoor.setVisibility(View.VISIBLE);

            mBinding.tvTemperatureDoor.setTextColor(getResources().getColor(R.color.auluxaGreen));
            ((TextView) mBinding.tvModeDoor.getCurrentView()).setTextColor(getResources().getColor(R.color.auluxaGreen));
            ((TextView) mBinding.tvModeDoor.getNextView()).setTextColor(getResources().getColor(R.color.auluxaGreen));
            ((TextView) mBinding.tvSpeedDoorSetting.getCurrentView()).setTextColor(getResources().getColor(R.color.auluxaGreen));
            ((TextView) mBinding.tvSpeedDoorSetting.getNextView()).setTextColor(getResources().getColor(R.color.auluxaGreen));
            ((TextView) mBinding.tvSwingSettingDoor.getCurrentView()).setTextColor(getResources().getColor(R.color.auluxaGreen));
            ((TextView) mBinding.tvSwingSettingDoor.getNextView()).setTextColor(getResources().getColor(R.color.auluxaGreen));

        } else {
            mBinding.minusTemperatureDoor.setVisibility(View.GONE);
            mBinding.maxTemperatureDoor.setVisibility(View.GONE);
            mBinding.leftModeDoor.setVisibility(View.GONE);
            mBinding.rightModeDoor.setVisibility(View.GONE);
            mBinding.leftSpeedDoor.setVisibility(View.GONE);
            mBinding.rightSpeedDoor.setVisibility(View.GONE);
            mBinding.leftSwingDoor.setVisibility(View.GONE);
            mBinding.rightSwingDoor.setVisibility(View.GONE);

            mBinding.tvTemperatureDoor.setTextColor(getResources().getColor(R.color.BgDarker));
            ((TextView) mBinding.tvModeDoor.getCurrentView()).setTextColor(getResources().getColor(R.color.BgDarker));
            ((TextView) mBinding.tvSpeedDoorSetting.getCurrentView()).setTextColor(getResources().getColor(R.color.BgDarker));
            ((TextView) mBinding.tvSwingSettingDoor.getCurrentView()).setTextColor(getResources().getColor(R.color.BgDarker));
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mInteraction = (FragmentInteraction) context;
    }
}
