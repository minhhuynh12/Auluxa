package net.dirox.auluxa.setting.view.device_list.shades;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import net.dirox.auluxa.R;
import net.dirox.auluxa.activity.FragmentInteraction;
import net.dirox.auluxa.adapter.SettingsMainAdapter;
import net.dirox.auluxa.common.fragment.BaseFragment;
import net.dirox.auluxa.data.sample.SettingsItem;
import net.dirox.auluxa.extras.Enumes;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class FragmentTwoButtonShades extends BaseFragment {

    @InjectView(R.id.ivImage)
    ImageView ivImage;
    @InjectView(R.id.etButton)
    EditText etButton;
    @InjectView(R.id.tvTitle)
    TextView tvTitle;
    private FragmentInteraction mInteraction;

    List<SettingsItem> list;
    SettingsMainAdapter adapter;

    public static FragmentTwoButtonShades newInstance() {

        Bundle args = new Bundle();

        FragmentTwoButtonShades fragment = new FragmentTwoButtonShades();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_settings_audio_device_two_button, container, false);
        ButterKnife.inject(this, view);
        etButton.setText("2 BUTTON SHADES");
        tvTitle.setText("2 BUTTON SHADES");
        ivImage.setImageResource(R.drawable.four_button_shades_2x);
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mInteraction = (FragmentInteraction) context;
    }

    @OnClick(R.id.ivBack)
    public void onBack(){
        onBackAndHideKeyBoard();
    }

    @OnClick({R.id.llBack, R.id.llBack2})
    public void onBackButton() {
        onBack();
    }

    @OnClick({R.id.llSetting, R.id.llSetting2})
    public void setting() {
        mInteraction.openFragment(Enumes.FragmentType.FRAGMENT_SETTING_CLIMATE_DEVICE_ROOMS_GENERAL, null, Enumes.Direction.RIGHT_IN_FLIP_OUT);
    }

}
