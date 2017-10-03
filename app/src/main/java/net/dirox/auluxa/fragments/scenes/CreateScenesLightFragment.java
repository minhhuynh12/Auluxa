package net.dirox.auluxa.fragments.scenes;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import net.dirox.auluxa.R;
import net.dirox.auluxa.activity.FragmentInteraction;
import net.dirox.auluxa.common.fragment.BaseFragment;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class CreateScenesLightFragment extends BaseFragment {

    @InjectView(R.id.sbWindowside1)
    SeekBar sbWindowside1;
    @InjectView(R.id.sbDoorside2)
    SeekBar sbDoorside2;
    @InjectView(R.id.sbDoorLights)
    SeekBar sbDoorLights;
    @InjectView(R.id.tvSeekbar1)
    TextView tvSeekbar1;
    @InjectView(R.id.tvSeekbar2)
    TextView tvSeekbar2;
    @InjectView(R.id.tvSeekbar3)
    TextView tvSeekbar3;
    @InjectView(R.id.cbWindowside1)
    CheckBox cbWindowside1;
    @InjectView(R.id.cbDoorside1)
    CheckBox cbDoorside1;
    @InjectView(R.id.cbDoorside2)
    CheckBox cbDoorside2;
    @InjectView(R.id.cbWindowside2)
    CheckBox cbWindowside2;
    @InjectView(R.id.llBlinds2)
    RelativeLayout llBlinds2;
    @InjectView(R.id.tvBlindsContent2)
    TextView tvBlindsContent2;
    @InjectView(R.id.tvSave)
    TextView tvSave;
    @InjectView(R.id.ivArrow3)
    ImageView ivArrow3;
    @InjectView(R.id.ivArrow4)
    ImageView ivArrow4;
    @InjectView(R.id.tvBlinds2)
    TextView tvBlinds2;
    @InjectView(R.id.tvCove)
    TextView tvCove;
    @InjectView(R.id.tvWindow)
    TextView tvWindow;
    @InjectView(R.id.tvSpot)
    TextView tvSpot;
    @InjectView(R.id.tvDoor)
    TextView tvDoor;

    boolean isEdit = true;

    private FragmentInteraction mInteraction;

    public static CreateScenesLightFragment newInstance() {

        Bundle args = new Bundle();

        CreateScenesLightFragment fragment = new CreateScenesLightFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_scenes_creating_lights, container, false);
        ButterKnife.inject(this, view);
        cbDoorside1.setChecked(true);
        cbDoorside2.setChecked(true);
        cbWindowside1.setChecked(true);
        cbWindowside2.setChecked(true);

        sbWindowside1.setMax(100);
        sbDoorside2.setMax(100);
        sbDoorLights.setMax(100);
        sbWindowside1.setProgress(30);
        sbDoorside2.setProgress(30);
        sbDoorLights.setProgress(30);
        tvSeekbar1.setText(30 + "%");
        tvSeekbar2.setText(30 + "%");
        tvSeekbar3.setText(30 + "%");
        initView();

        sbDoorside2.setPadding(20, 0, 20, 0);
        sbWindowside1.setPadding(20, 0, 20, 0);
        sbDoorLights.setPadding(20, 0, 20, 0);

        sbWindowside1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                tvSeekbar1.setText(progress + "%");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        sbDoorside2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                tvSeekbar2.setText(progress + "%");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        sbDoorLights.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                tvSeekbar3.setText(progress + "%");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mInteraction = (FragmentInteraction) context;
    }

    @OnClick(R.id.ivBack)
    public void onBack() {
        getActivity().onBackPressed();
    }

    @OnClick(R.id.cbWindowside1)
    public void onCheckBox() {
        if (cbWindowside1.isChecked()) {
            tvSeekbar1.setTextColor(ContextCompat.getColor(getContext(), R.color.BgDarker));
            tvCove.setTextColor(ContextCompat.getColor(getContext(), R.color.BgDarker));
            sbWindowside1.setEnabled(true);
        } else {
            sbWindowside1.setEnabled(false);
            tvSeekbar1.setTextColor(ContextCompat.getColor(getContext(), R.color.BgDarkGray));
            tvCove.setTextColor(ContextCompat.getColor(getContext(), R.color.BgDarkGray));
        }
    }

    @OnClick(R.id.cbDoorside2)
    public void onCheckBox4() {
        if (cbDoorside2.isChecked()) {
            sbDoorside2.setEnabled(true);
            tvSeekbar2.setTextColor(ContextCompat.getColor(getContext(), R.color.BgDarker));
            tvSpot.setTextColor(ContextCompat.getColor(getContext(), R.color.BgDarker));
        } else {
            sbDoorside2.setEnabled(false);
            tvSeekbar2.setTextColor(ContextCompat.getColor(getContext(), R.color.BgDarkGray));
            tvSpot.setTextColor(ContextCompat.getColor(getContext(), R.color.BgDarkGray));
        }

    }

    @OnClick(R.id.cbWindowside2)
    public void onCheckBox2() {
        if (cbWindowside2.isChecked()) {
            sbDoorLights.setEnabled(true);
            tvSeekbar3.setTextColor(ContextCompat.getColor(getContext(), R.color.BgDarker));
            tvDoor.setTextColor(ContextCompat.getColor(getContext(), R.color.BgDarker));
        } else {
            sbDoorLights.setEnabled(false);
            tvSeekbar3.setTextColor(ContextCompat.getColor(getContext(), R.color.BgDarkGray));
            tvDoor.setTextColor(ContextCompat.getColor(getContext(), R.color.BgDarkGray));
        }

    }

    @OnClick(R.id.cbDoorside1)
    public void onCheckBox3() {
        if (cbDoorside1.isChecked()) {
            ivArrow3.setVisibility(View.VISIBLE);
            ivArrow4.setVisibility(View.VISIBLE);
            tvBlinds2.setTextColor(ContextCompat.getColor(getContext(), R.color.auluxaGreen));
            tvWindow.setTextColor(ContextCompat.getColor(getContext(), R.color.BgDarker));
            tvBlindsContent2.setTextColor(ContextCompat.getColor(getContext(), R.color.auluxaGreen));
        } else {
            ivArrow3.setVisibility(View.GONE);
            ivArrow4.setVisibility(View.GONE);
            tvBlindsContent2.setTextColor(ContextCompat.getColor(getContext(), R.color.BgDarkGray));
            tvWindow.setTextColor(ContextCompat.getColor(getContext(), R.color.BgDarkGray));
            tvBlinds2.setTextColor(ContextCompat.getColor(getContext(), R.color.BgDarkGray));
        }
    }

    @OnClick({R.id.ivArrow3, R.id.ivArrow4})
    public void onArrow2() {
        if (tvBlindsContent2.getText().toString().equals("ON"))
            tvBlindsContent2.setText("OFF");
        else tvBlindsContent2.setText("ON");
    }

    @OnClick(R.id.llSave)
    public void onSave() {
        if (!isEdit) {
            /*initView();*/
            getActivity().onBackPressed();
        } else
            initEdit();
    }


    public void initView() {
        isEdit = true;
        sbWindowside1.setEnabled(false);
        sbDoorside2.setEnabled(false);
        sbDoorLights.setEnabled(false);
        cbDoorside1.setVisibility(View.INVISIBLE);
        cbDoorside2.setVisibility(View.INVISIBLE);
        cbWindowside1.setVisibility(View.INVISIBLE);
        cbWindowside2.setVisibility(View.INVISIBLE);
        ivArrow3.setVisibility(View.GONE);
        tvBlinds2.setTextColor(ContextCompat.getColor(getContext(), R.color.BgDarkGray));
        ivArrow4.setVisibility(View.GONE);
        tvBlindsContent2.setTextColor(ContextCompat.getColor(getContext(), R.color.BgDarkGray));
        tvSave.setText("EDIT");
    }

    public void initEdit() {
        isEdit = false;
        sbWindowside1.setEnabled(true);
        sbDoorside2.setEnabled(true);
        sbDoorLights.setEnabled(true);
        cbDoorside1.setVisibility(View.VISIBLE);
        cbDoorside2.setVisibility(View.VISIBLE);
        cbWindowside1.setVisibility(View.VISIBLE);
        cbWindowside2.setVisibility(View.VISIBLE);
        tvSave.setText("SAVE");

        if (cbDoorside1.isChecked()) {
            ivArrow3.setVisibility(View.VISIBLE);
            ivArrow4.setVisibility(View.VISIBLE);
            tvBlinds2.setTextColor(ContextCompat.getColor(getContext(), R.color.auluxaGreen));
            tvWindow.setTextColor(ContextCompat.getColor(getContext(), R.color.BgDarker));
            tvBlindsContent2.setTextColor(ContextCompat.getColor(getContext(), R.color.auluxaGreen));
        } else {
            ivArrow3.setVisibility(View.GONE);
            ivArrow4.setVisibility(View.GONE);
            tvBlindsContent2.setTextColor(ContextCompat.getColor(getContext(), R.color.BgDarkGray));
            tvWindow.setTextColor(ContextCompat.getColor(getContext(), R.color.BgDarkGray));
            tvBlinds2.setTextColor(ContextCompat.getColor(getContext(), R.color.BgDarkGray));
        }

        if (cbWindowside2.isChecked()) {
            sbDoorLights.setEnabled(true);
            tvSeekbar3.setTextColor(ContextCompat.getColor(getContext(), R.color.BgDarker));
            tvDoor.setTextColor(ContextCompat.getColor(getContext(), R.color.BgDarker));
        } else {
            sbDoorLights.setEnabled(false);
            tvSeekbar3.setTextColor(ContextCompat.getColor(getContext(), R.color.BgDarkGray));
            tvDoor.setTextColor(ContextCompat.getColor(getContext(), R.color.BgDarkGray));
        }

        if (cbDoorside2.isChecked()) {
            sbDoorside2.setEnabled(true);
            tvSeekbar2.setTextColor(ContextCompat.getColor(getContext(), R.color.BgDarker));
            tvSpot.setTextColor(ContextCompat.getColor(getContext(), R.color.BgDarker));
        } else {
            sbDoorside2.setEnabled(false);
            tvSeekbar2.setTextColor(ContextCompat.getColor(getContext(), R.color.BgDarkGray));
            tvSpot.setTextColor(ContextCompat.getColor(getContext(), R.color.BgDarkGray));
        }

        if (cbWindowside1.isChecked()) {
            tvSeekbar1.setTextColor(ContextCompat.getColor(getContext(), R.color.BgDarker));
            tvCove.setTextColor(ContextCompat.getColor(getContext(), R.color.BgDarker));
            sbWindowside1.setEnabled(true);
        } else {
            sbWindowside1.setEnabled(false);
            tvSeekbar1.setTextColor(ContextCompat.getColor(getContext(), R.color.BgDarkGray));
            tvCove.setTextColor(ContextCompat.getColor(getContext(), R.color.BgDarkGray));
        }
    }
}
