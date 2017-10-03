package net.dirox.auluxa.setting.view.device_list.video;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import net.dirox.auluxa.R;
import net.dirox.auluxa.activity.FragmentInteraction;
import net.dirox.auluxa.adapter.ButtonAdapter;
import net.dirox.auluxa.adapter.CableAdapter;
import net.dirox.auluxa.common.fragment.BaseFragment;
import net.dirox.auluxa.databinding.LayoutSettingsAudioDeviceGeneralBinding;
import net.dirox.auluxa.extras.Enumes;
import net.dirox.auluxa.utils.Constant;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class FragmentVideoDeviceGeneral extends BaseFragment {
    @InjectView(R.id.ivImage)
    ImageView ivImage;
    @InjectView(R.id.etButton)
    EditText etButton;
    @InjectView(R.id.tvTitle)
    TextView tvTitle;
    @InjectView(R.id.rvButton)
    RecyclerView rvButton;

    ArrayList<String> list;
    static String title1 = "";
    private FragmentInteraction mInteraction;

    public static FragmentVideoDeviceGeneral newInstance(String title) {

        Bundle args = new Bundle();
        title1 = title;
        FragmentVideoDeviceGeneral fragment = new FragmentVideoDeviceGeneral();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_settings_video_device_general, container, false);
        ButterKnife.inject(this, view);
        etButton.setText(title1);
        etButton.setOnKeyListener(onKeyListener);
        tvTitle.setText(title1);
        ivImage.setImageResource(R.drawable.four_button_big_2x);
        list = new ArrayList<>();
        list.add("ROOMS");
        if (!title1.equals("IR NODE NAME")) {
            list.add("SELECT DEVICE");
        }
        list.add("SELECT CATEGORY");
        list.add("SELECT MANUFACTURER");
        list.add("CODE");
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvButton.setLayoutManager(layoutManager);
        CableAdapter cableAdapter = new CableAdapter(list, new CableAdapter.OnClickItemButtonListener() {
            @Override
            public void inClickButton(int pos) {
                Bundle bundle = new Bundle();
                if (title1.equals("IR NODE NAME")) {
                    switch (pos) {
                        case 0:
                            bundle.putInt("position", 1);
                            bundle.putString(Constant.CLIMATE_DEVICE_NAME, "ROOMS");
                            mInteraction.openFragment(Enumes.FragmentType.FRAGMENT_SETTING_VIDEO_ROOMS, bundle, Enumes.Direction.RIGHT_IN_FLIP_OUT);
                            break;
                        case 1:
                            bundle.putInt("position", 3);
                            bundle.putString(Constant.CLIMATE_DEVICE_NAME, "SELECT CATEGORY");
                            mInteraction.openFragment(Enumes.FragmentType.FRAGMENT_SETTING_CLIMATE_SELECT_DEVICE_IR_NODE_CATEGORY, null, Enumes.Direction.RIGHT_IN_FLIP_OUT);
                            break;
                        case 2:
                            bundle.putBoolean("visible", false);
                            bundle.putString("title", "SELECT MANUFACTURER");
                            mInteraction.openFragment(Enumes.FragmentType.FRAGMENT_SETTING_VIDEO_CODE, bundle, Enumes.Direction.RIGHT_IN_FLIP_OUT);
                            break;
                        case 3:
                            bundle.putBoolean("visible", true);
                            bundle.putString("title", "CODE");
                            mInteraction.openFragment(Enumes.FragmentType.FRAGMENT_SETTING_VIDEO_CODE, bundle, Enumes.Direction.RIGHT_IN_FLIP_OUT);
                            break;
                    }
                } else {
                    switch (pos) {
                        case 0:
                            bundle.putInt("position", 1);
                            bundle.putString(Constant.CLIMATE_DEVICE_NAME, "ROOMS");
                            mInteraction.openFragment(Enumes.FragmentType.FRAGMENT_SETTING_VIDEO_ROOMS, bundle, Enumes.Direction.RIGHT_IN_FLIP_OUT);
                            break;
                        case 1:
                            bundle.putInt("position", 2);
                            bundle.putString(Constant.CLIMATE_DEVICE_NAME, "SELECT DEVICE");
                            mInteraction.openFragment(Enumes.FragmentType.FRAGMENT_SETTING_VIDEO_ROOMS, bundle, Enumes.Direction.RIGHT_IN_FLIP_OUT);
                            break;
                        case 2:
                            bundle.putInt("position", 3);
                            bundle.putString(Constant.CLIMATE_DEVICE_NAME, "SELECT CATEGORY");
                            mInteraction.openFragment(Enumes.FragmentType.FRAGMENT_SETTING_VIDEO_ROOMS, bundle, Enumes.Direction.RIGHT_IN_FLIP_OUT);
                            break;
                        case 3:
                            bundle.putBoolean("visible", false);
                            bundle.putString("title", "SELECT MANUFACTURER");
                            mInteraction.openFragment(Enumes.FragmentType.FRAGMENT_SETTING_VIDEO_CODE, bundle, Enumes.Direction.RIGHT_IN_FLIP_OUT);
                            break;
                        case 4:
                            bundle.putBoolean("visible", true);
                            bundle.putString("title", "CODE");
                            mInteraction.openFragment(Enumes.FragmentType.FRAGMENT_SETTING_VIDEO_CODE, bundle, Enumes.Direction.RIGHT_IN_FLIP_OUT);
                            break;
                    }
                }
            }
        });
        rvButton.setAdapter(cableAdapter);
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
