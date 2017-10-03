package net.dirox.auluxa.setting.view.device_list.security;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import net.dirox.auluxa.R;
import net.dirox.auluxa.activity.FragmentInteraction;
import net.dirox.auluxa.adapter.SettingsMainAdapter;
import net.dirox.auluxa.common.fragment.BaseFragment;
import net.dirox.auluxa.data.sample.SettingsItem;
import net.dirox.auluxa.databinding.LayoutSettingsAudioDeviceGeneralBinding;
import net.dirox.auluxa.extras.Enumes;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class FragmentAuluxaDoorLock extends BaseFragment {

    @InjectView(R.id.ivImage)
    ImageView ivImage;
    @InjectView(R.id.etButton)
    EditText etButton;
    @InjectView(R.id.tvTitle)
    TextView tvTitle;
    @InjectView(R.id.rlCheck)
    RelativeLayout rlCheck;
    @InjectView(R.id.llButton1)
    LinearLayout llButton1;

    private FragmentInteraction mInteraction;

    public static FragmentAuluxaDoorLock newInstance() {

        Bundle args = new Bundle();
        FragmentAuluxaDoorLock fragment = new FragmentAuluxaDoorLock();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_settings_audio_device_one_button, container, false);
        ButterKnife.inject(this, view);
        etButton.setText("AULUXA DOOR LOCK");
        tvTitle.setText("AULUXA DOOR LOCK");
        ivImage.setImageResource(R.drawable.auluxa_door_lock_2x);
        rlCheck.setVisibility(View.GONE);
        llButton1.setVisibility(View.GONE);
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
