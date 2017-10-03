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

public class VideoCableTVPageOneFragment extends BaseVideoRemoteFragment {

    @InjectView(R.id.tvTiteMenu)
    TextView tvTiteMenu;
    public static String titleMenu;

    public static VideoCableTVPageOneFragment newInstance(int page, String title, boolean visibleTopMenu) {
        VideoCableTVPageOneFragment fragmentFirst = new VideoCableTVPageOneFragment();
        Bundle args = new Bundle();
        args.putBoolean("VisibleTopMenu", visibleTopMenu);
        fragmentFirst.setArguments(args);
        titleMenu = title;
        return fragmentFirst;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_room_centre_video_cable_page_one, container, false);
        boolean visibleTopMenu = getArguments().getBoolean("VisibleTopMenu", true);
        ButterKnife.inject(this, view);
        tvTiteMenu.setText(titleMenu);
        if (visibleTopMenu == false) {
            view.findViewById(R.id.layoutTopMenu).setVisibility(View.GONE);
        }

        changeActionButtonImage(view, R.id.llMute, false, -1, -1, R.color.BgDarker);
        changeActionButtonImage(view, R.id.llHome, false, R.drawable.home_video, R.drawable.home_video, R.color.BgDarker);
        changeActionButtonImage(view, R.id.llPower, false, R.drawable.power, R.drawable.power_white, R.color.BgDarker);
        changeActionButtonImage(view, R.id.llBack, false, -1, -1, R.color.BgDarker);
        changeActionButtonImage(view, R.id.llMenu, false, -1, -1, R.color.BgDarker);
        changeActionButtonImage(view, R.id.llApps, false, -1, -1, R.color.BgDarker);
        changeActionButtonImage(view, R.id.llExit, false, -1, -1, R.color.BgDarker);
        changeActionButtonImage(view, R.id.llRed, false, R.drawable.red_dot, R.drawable.red_dot, -1);
        changeActionButtonImage(view, R.id.llGreen, false, R.drawable.green, R.drawable.green, -1);
        changeActionButtonImage(view, R.id.llYellow, false, R.drawable.yello, R.drawable.yello, -1);
        changeActionButtonImage(view, R.id.llBlue, false, R.drawable.blue, R.drawable.blue, -1);
        return view;
    }
}