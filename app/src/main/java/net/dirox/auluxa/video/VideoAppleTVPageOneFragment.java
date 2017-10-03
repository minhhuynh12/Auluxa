package net.dirox.auluxa.video;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import net.dirox.auluxa.R;
import net.dirox.auluxa.common.fragment.BaseVideoRemoteFragment;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class VideoAppleTVPageOneFragment extends BaseVideoRemoteFragment {

    @InjectView(R.id.tvTiteMenu)
    TextView tvTiteMenu;

    public static String titleMenu;

    public static VideoAppleTVPageOneFragment newInstance(int page, String title, boolean visibleTopMenu) {
        VideoAppleTVPageOneFragment fragmentFirst = new VideoAppleTVPageOneFragment();
        Bundle args = new Bundle();
        args.putBoolean("VisibleTopMenu", visibleTopMenu);
        fragmentFirst.setArguments(args);
        titleMenu = title;
        return fragmentFirst;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_room_centre_video_apple_tv_page_one, container, false);
        ButterKnife.inject(this, view);
        tvTiteMenu.setText(titleMenu);
        boolean visibleTopMenu = getArguments().getBoolean("VisibleTopMenu", true);
        if (visibleTopMenu == false) {
            view.findViewById(R.id.layoutTopMenu).setVisibility(View.GONE);
        }

        changeActionButtonImage(view, R.id.llCustom1, false, -1, -1, R.color.BgDarker);
        changeActionButtonImage(view, R.id.llCustom2, false, -1, -1, R.color.BgDarker);
        changeActionButtonImage(view, R.id.llCustom3, false, -1, -1, R.color.BgDarker);
        changeActionButtonImage(view, R.id.llMute, false, -1, -1, R.color.BgDarker);
        changeActionButtonImage(view, R.id.llBack, false, -1, -1, R.color.BgDarker);
        changeActionButtonImage(view, R.id.llPower, false, R.drawable.power, R.drawable.power_white, -1);
        changeActionButtonImage(view, R.id.llHome, false, R.drawable.home_video, R.drawable.home_video, -1);
        changeActionButtonImage(view, R.id.llPlay, false, R.drawable.play_pause, R.drawable.play_pause, -1);

        return view;
    }
}