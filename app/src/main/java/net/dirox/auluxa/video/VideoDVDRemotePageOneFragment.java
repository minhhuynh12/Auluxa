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

public class VideoDVDRemotePageOneFragment extends BaseVideoRemoteFragment {
    @InjectView(R.id.tvTiteMenu)
    TextView tvTiteMenu;

    public static String titleMenu;

    public static VideoDVDRemotePageOneFragment newInstance(int page, String title, boolean visibleTopMenu) {
        VideoDVDRemotePageOneFragment fragmentFirst = new VideoDVDRemotePageOneFragment();
        Bundle args = new Bundle();
        args.putBoolean("VisibleTopMenu", visibleTopMenu);
        fragmentFirst.setArguments(args);
        titleMenu = title;
        return fragmentFirst;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_room_centre_video_dvd_remote_page_one, container, false);
        ButterKnife.inject(this, view);
        tvTiteMenu.setText(titleMenu);

        boolean visibleTopMenu = getArguments().getBoolean("VisibleTopMenu", true);
        if (visibleTopMenu == false) {
            view.findViewById(R.id.layoutTopMenu).setVisibility(View.GONE);
        }

        changeActionButtonImage(view, R.id.llMenu, false, -1, -1, R.color.BgDarker);
        changeActionButtonImage(view, R.id.llSource, false, -1, -1, R.color.BgDarker);
        changeActionButtonImage(view, R.id.llBack, false, -1, -1, R.color.BgDarker);
        changeActionButtonImage(view, R.id.llExit, false, -1, -1, R.color.BgDarker);

        changeActionButtonImage(view, R.id.llUp, false, R.drawable.up_video, R.drawable.up_video, -1);
        changeActionButtonImage(view, R.id.llPower, false, R.drawable.power, R.drawable.power_white, -1);
        changeActionButtonImage(view, R.id.llHome, false, R.drawable.home_video, R.drawable.home_video, -1);
        changeActionButtonImage(view, R.id.llPrevious, false, R.drawable.rewind, R.drawable.rewind, -1);
        changeActionButtonImage(view, R.id.llPlay, false, R.drawable.play, R.drawable.play, -1);
        changeActionButtonImage(view, R.id.llPause, false, R.drawable.pause, R.drawable.pause, -1);
        changeActionButtonImage(view, R.id.llStop, false, R.drawable.stop, R.drawable.stop, -1);
        changeActionButtonImage(view, R.id.llNext, false, R.drawable.fast_forward, R.drawable.fast_forward, -1);
        return view;
    }
}