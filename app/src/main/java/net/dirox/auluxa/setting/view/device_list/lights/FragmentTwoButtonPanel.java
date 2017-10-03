package net.dirox.auluxa.setting.view.device_list.lights;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import net.dirox.auluxa.R;
import net.dirox.auluxa.activity.FragmentInteraction;
import net.dirox.auluxa.adapter.ButtonAdapter;
import net.dirox.auluxa.common.fragment.BaseFragment;
import net.dirox.auluxa.databinding.LayoutSettingsAudioDeviceGeneralBinding;
import net.dirox.auluxa.extras.Enumes;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class FragmentTwoButtonPanel extends BaseFragment {
    @InjectView(R.id.ivImage)
    ImageView ivImage;
    @InjectView(R.id.etButton)
    EditText etButton;
    @InjectView(R.id.tvTitle)
    TextView tvTitle;
    @InjectView(R.id.rvButton)
    RecyclerView rvButton;

    private FragmentInteraction mInteraction;

    public static FragmentTwoButtonPanel newInstance() {

        Bundle args = new Bundle();

        FragmentTwoButtonPanel fragment = new FragmentTwoButtonPanel();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_settings_audio_device_one_button_panel, container, false);
        ButterKnife.inject(this, view);
        etButton.setText("2 BUTTON PANEL");
        tvTitle.setText("2 BUTTON PANEL");
        ivImage.setImageResource(R.drawable.two_button_2x);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvButton.setLayoutManager(layoutManager);
        ButtonAdapter buttonAdapter = new ButtonAdapter(2, new ButtonAdapter.OnClickItemButtonListener() {
            @Override
            public void inClickButton() {
                mInteraction.openFragment(Enumes.FragmentType.FRAGMENT_SETTING_CLIMATE_DEVICE_ROOMS_GENERAL, null, Enumes.Direction.RIGHT_IN_FLIP_OUT);
            }
        });
        rvButton.setAdapter(buttonAdapter);
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
