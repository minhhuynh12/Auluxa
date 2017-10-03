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

public class CreateScenesShadesFragment extends BaseFragment {

    @InjectView(R.id.sbWindowside1)
    SeekBar sbWindowside1;
    @InjectView(R.id.sbDoorside2)
    SeekBar sbDoorside2;
    @InjectView(R.id.tvSeekbar1)
    TextView tvSeekbar1;
    @InjectView(R.id.tvSeekbar2)
    TextView tvSeekbar2;
    @InjectView(R.id.cbWindowside1)
    CheckBox cbWindowside1;
    @InjectView(R.id.cbDoorside1)
    CheckBox cbDoorside1;
    @InjectView(R.id.cbDoorside2)
    CheckBox cbDoorside2;
    @InjectView(R.id.cbWindowside2)
    CheckBox cbWindowside2;
    @InjectView(R.id.llBlinds1)
    RelativeLayout llBlinds1;
    @InjectView(R.id.llBlinds2)
    RelativeLayout llBlinds2;
    @InjectView(R.id.tvBlindsContent1)
    TextView tvBlindsContent1;
    @InjectView(R.id.tvBlindsContent2)
    TextView tvBlindsContent2;
    @InjectView(R.id.tvSave)
    TextView tvSave;
    @InjectView(R.id.ivArrow1)
    ImageView ivArrow1;
    @InjectView(R.id.ivArrow2)
    ImageView ivArrow2;
    @InjectView(R.id.ivArrow3)
    ImageView ivArrow3;
    @InjectView(R.id.ivArrow4)
    ImageView ivArrow4;
    @InjectView(R.id.tvBlinds2)
    TextView tvBlinds2;
    @InjectView(R.id.tvBlinds1)
    TextView tvBlinds1;
    @InjectView(R.id.tvWindowside1)
    TextView tvWindowside1;
    @InjectView(R.id.tvWindowside2)
    TextView tvWindowside2;
    @InjectView(R.id.tvDoorside2)
    TextView tvDoorside2;
    @InjectView(R.id.tvDoorside1)
    TextView tvDoorside1;
    boolean isEdit = true;
    private FragmentInteraction mInteraction;

    public static CreateScenesShadesFragment newInstance() {

        Bundle args = new Bundle();

        CreateScenesShadesFragment fragment = new CreateScenesShadesFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_scenes_creating_shades, container, false);
        ButterKnife.inject(this, view);

        cbDoorside1.setChecked(true);
        cbDoorside2.setChecked(true);
        cbWindowside1.setChecked(true);
        cbWindowside2.setChecked(true);

        sbWindowside1.setMax(100);
        sbDoorside2.setMax(100);
        sbWindowside1.setProgress(30);
        sbDoorside2.setProgress(30);
        tvSeekbar1.setText(30 + "%");
        tvSeekbar2.setText(30 + "%");
        sbWindowside1.setEnabled(false);
        sbDoorside2.setEnabled(false);
        llBlinds1.setVisibility(View.VISIBLE);
        llBlinds2.setVisibility(View.VISIBLE);
        cbDoorside1.setChecked(true);
        cbWindowside2.setChecked(true);

        sbDoorside2.setPadding(20, 0, 20, 0);
        sbWindowside1.setPadding(20, 0, 20, 0);
        initView();
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
            tvWindowside1.setTextColor(ContextCompat.getColor(getContext(), R.color.BgDarker));
            sbWindowside1.setEnabled(true);
        } else {
            sbWindowside1.setEnabled(false);
            tvSeekbar1.setTextColor(ContextCompat.getColor(getContext(), R.color.BgDarkGray));
            tvWindowside1.setTextColor(ContextCompat.getColor(getContext(), R.color.BgDarkGray));
        }
    }

    @OnClick(R.id.cbDoorside2)
    public void onCheckBox4() {
        if (cbDoorside2.isChecked()) {
            sbDoorside2.setEnabled(true);
            tvSeekbar2.setTextColor(ContextCompat.getColor(getContext(), R.color.BgDarker));
            tvDoorside2.setTextColor(ContextCompat.getColor(getContext(), R.color.BgDarker));
        } else {
            sbDoorside2.setEnabled(false);
            tvSeekbar2.setTextColor(ContextCompat.getColor(getContext(), R.color.BgDarkGray));
            tvDoorside2.setTextColor(ContextCompat.getColor(getContext(), R.color.BgDarkGray));
        }

    }

    @OnClick(R.id.cbWindowside2)
    public void onCheckBox2() {
        if (cbWindowside2.isChecked()) {
            ivArrow1.setVisibility(View.VISIBLE);
            ivArrow2.setVisibility(View.VISIBLE);
            tvBlinds1.setTextColor(ContextCompat.getColor(getContext(), R.color.auluxaGreen));
            tvBlindsContent1.setTextColor(ContextCompat.getColor(getContext(), R.color.auluxaGreen));
            tvWindowside2.setTextColor(ContextCompat.getColor(getContext(), R.color.BgDarker));
        } else {
            ivArrow1.setVisibility(View.GONE);
            ivArrow2.setVisibility(View.GONE);
            tvBlinds1.setTextColor(ContextCompat.getColor(getContext(), R.color.BgDarkGray));
            tvBlindsContent1.setTextColor(ContextCompat.getColor(getContext(), R.color.BgDarkGray));
            tvWindowside2.setTextColor(ContextCompat.getColor(getContext(), R.color.BgDarkGray));
        }

    }

    @OnClick(R.id.cbDoorside1)
    public void onCheckBox3() {
        if (cbDoorside1.isChecked()) {
            ivArrow3.setVisibility(View.VISIBLE);
            ivArrow4.setVisibility(View.VISIBLE);
            tvBlinds2.setTextColor(ContextCompat.getColor(getContext(), R.color.auluxaGreen));
            tvBlindsContent2.setTextColor(ContextCompat.getColor(getContext(), R.color.auluxaGreen));
            tvDoorside1.setTextColor(ContextCompat.getColor(getContext(), R.color.BgDarker));
        } else {
            ivArrow3.setVisibility(View.GONE);
            ivArrow4.setVisibility(View.GONE);
            tvBlinds2.setTextColor(ContextCompat.getColor(getContext(), R.color.BgDarkGray));
            tvBlindsContent2.setTextColor(ContextCompat.getColor(getContext(), R.color.BgDarkGray));
            tvDoorside1.setTextColor(ContextCompat.getColor(getContext(), R.color.BgDarkGray));
        }
    }

    @OnClick({R.id.ivArrow1, R.id.ivArrow2})
    public void onArrow() {
        if (tvBlindsContent1.getText().toString().equals("OPEN"))
            tvBlindsContent1.setText("CLOSE");
        else tvBlindsContent1.setText("OPEN");
    }

    @OnClick({R.id.ivArrow3, R.id.ivArrow4})
    public void onArrow2() {
        if (tvBlindsContent2.getText().toString().equals("OPEN"))
            tvBlindsContent2.setText("CLOSE");
        else tvBlindsContent2.setText("OPEN");
    }

    @OnClick(R.id.llSave)
    public void onSave() {
        if (!isEdit) {
            // initView();
            getActivity().onBackPressed();
        } else
            initEdit();

    }

    public void initView() {
        isEdit = true;
        sbWindowside1.setEnabled(false);
        sbDoorside2.setEnabled(false);

        cbDoorside1.setVisibility(View.INVISIBLE);
        cbDoorside2.setVisibility(View.INVISIBLE);
        cbWindowside1.setVisibility(View.INVISIBLE);
        cbWindowside2.setVisibility(View.INVISIBLE);

        ivArrow1.setVisibility(View.GONE);
        ivArrow2.setVisibility(View.GONE);
        tvBlinds1.setTextColor(ContextCompat.getColor(getContext(), R.color.BgDarkGray));
        tvBlindsContent1.setTextColor(ContextCompat.getColor(getContext(), R.color.BgDarkGray));

        ivArrow3.setVisibility(View.GONE);
        ivArrow4.setVisibility(View.GONE);
        tvBlinds2.setTextColor(ContextCompat.getColor(getContext(), R.color.BgDarkGray));
        tvBlindsContent2.setTextColor(ContextCompat.getColor(getContext(), R.color.BgDarkGray));

        tvSave.setText("EDIT");
    }

    public void initEdit() {
        isEdit = false;

        cbDoorside1.setVisibility(View.VISIBLE);
        cbDoorside2.setVisibility(View.VISIBLE);
        cbWindowside1.setVisibility(View.VISIBLE);
        cbWindowside2.setVisibility(View.VISIBLE);

        if (cbDoorside1.isChecked()) {
            ivArrow3.setVisibility(View.VISIBLE);
            ivArrow4.setVisibility(View.VISIBLE);
            tvBlinds2.setTextColor(ContextCompat.getColor(getContext(), R.color.auluxaGreen));
            tvBlindsContent2.setTextColor(ContextCompat.getColor(getContext(), R.color.auluxaGreen));
            tvDoorside1.setTextColor(ContextCompat.getColor(getContext(), R.color.BgDarker));
        } else {
            ivArrow3.setVisibility(View.GONE);
            ivArrow4.setVisibility(View.GONE);
            tvBlinds2.setTextColor(ContextCompat.getColor(getContext(), R.color.BgDarkGray));
            tvBlindsContent2.setTextColor(ContextCompat.getColor(getContext(), R.color.BgDarkGray));
            tvDoorside1.setTextColor(ContextCompat.getColor(getContext(), R.color.BgDarkGray));
        }

        if (cbWindowside2.isChecked()) {
            ivArrow1.setVisibility(View.VISIBLE);
            ivArrow2.setVisibility(View.VISIBLE);
            tvBlinds1.setTextColor(ContextCompat.getColor(getContext(), R.color.auluxaGreen));
            tvBlindsContent1.setTextColor(ContextCompat.getColor(getContext(), R.color.auluxaGreen));
            tvWindowside2.setTextColor(ContextCompat.getColor(getContext(), R.color.BgDarker));
        } else {
            ivArrow1.setVisibility(View.GONE);
            ivArrow2.setVisibility(View.GONE);
            tvBlinds1.setTextColor(ContextCompat.getColor(getContext(), R.color.BgDarkGray));
            tvBlindsContent1.setTextColor(ContextCompat.getColor(getContext(), R.color.BgDarkGray));
            tvWindowside2.setTextColor(ContextCompat.getColor(getContext(), R.color.BgDarkGray));
        }

        if (cbDoorside2.isChecked()) {
            sbDoorside2.setEnabled(true);
            tvSeekbar2.setTextColor(ContextCompat.getColor(getContext(), R.color.BgDarker));
            tvDoorside2.setTextColor(ContextCompat.getColor(getContext(), R.color.BgDarker));
        } else {
            sbDoorside2.setEnabled(false);
            tvSeekbar2.setTextColor(ContextCompat.getColor(getContext(), R.color.BgDarkGray));
            tvDoorside2.setTextColor(ContextCompat.getColor(getContext(), R.color.BgDarkGray));
        }

        if (cbWindowside1.isChecked()) {
            tvSeekbar1.setTextColor(ContextCompat.getColor(getContext(), R.color.BgDarker));
            tvWindowside1.setTextColor(ContextCompat.getColor(getContext(), R.color.BgDarker));
            sbWindowside1.setEnabled(true);
        } else {
            sbWindowside1.setEnabled(false);
            tvSeekbar1.setTextColor(ContextCompat.getColor(getContext(), R.color.BgDarkGray));
            tvWindowside1.setTextColor(ContextCompat.getColor(getContext(), R.color.BgDarkGray));
        }
        tvSave.setText("SAVE");

    }

}
