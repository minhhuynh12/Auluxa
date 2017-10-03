package net.dirox.auluxa.setting.view.device_list.security;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import net.dirox.auluxa.R;
import net.dirox.auluxa.activity.FragmentInteraction;
import net.dirox.auluxa.adapter.SettingsMainAdapter;
import net.dirox.auluxa.common.fragment.BaseFragment;
import net.dirox.auluxa.data.sample.SettingsItem;
import net.dirox.auluxa.extras.Enumes;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class FragmentSensor extends BaseFragment {
    @InjectView(R.id.ivImage)
    ImageView ivImage;
    @InjectView(R.id.etButton)
    EditText etButton;
    @InjectView(R.id.tvTitle)
    TextView tvTitle;
    @InjectView(R.id.rlCheck)
    RelativeLayout rlCheck;
    @InjectView(R.id.rvButton)
    ListView rvButton;
    SettingsMainAdapter adapter;

    List<SettingsItem> list = new ArrayList<>();

    private FragmentInteraction mInteraction;

    public static FragmentSensor newInstance() {

        Bundle args = new Bundle();

        FragmentSensor fragment = new FragmentSensor();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_settings_audio_device_security_sensor, container, false);
        ButterKnife.inject(this, view);
        list = new ArrayList<>();
        list.add(new SettingsItem(1,"LIGHT"));
        list.add(new SettingsItem(2,"SHADES"));
        list.add(new SettingsItem(3,"ALARM"));
        list.add(new SettingsItem(4,"CAMERA"));
        list.add(new SettingsItem(5,"SCENES"));
        ivImage.setImageResource(R.drawable.sensor_2x);
        etButton.setText("SENSOR");
        tvTitle.setText("SENSOR");
        rlCheck.setVisibility(View.GONE);
        adapter = new SettingsMainAdapter(getActivity(), R.layout.item_settings, list) {
            @Override
            public void onItemSelected(int position) {
                mInteraction.openFragment(Enumes.FragmentType.FRAGMENT_SETTING_CLIMATE_DEVICE_ROOMS_GENERAL, null, Enumes.Direction.RIGHT_IN_FLIP_OUT);

            }
        };
        rvButton.setAdapter(adapter);
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mInteraction = (FragmentInteraction) context;
    }

    @OnClick(R.id.ivBack)
    public void onBack() {
        onBackAndHideKeyBoard();
    }
}
