package net.dirox.auluxa.video;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.dirox.auluxa.R;
import net.dirox.auluxa.common.fragment.BaseVideoRemoteFragment;

import butterknife.ButterKnife;

public class VideoTVRemotePageSecondFragment extends BaseVideoRemoteFragment {


    public static String titleMenu;

    public static VideoTVRemotePageSecondFragment newInstance(int page, String title) {
        VideoTVRemotePageSecondFragment fragmentFirst = new VideoTVRemotePageSecondFragment();
        Bundle args = new Bundle();
        fragmentFirst.setArguments(args);
        titleMenu = title;
        return fragmentFirst;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_room_centre_video_tv_remote_page_two, container, false);
        ButterKnife.inject(this, view);
        changeActionButtonImage(view, R.id.llCustom1, false, -1, -1, R.color.BgDarker);
        changeActionButtonImage(view, R.id.llCustom2, false, -1, -1, R.color.BgDarker);
        changeActionButtonImage(view, R.id.llCustom3, false, -1, -1, R.color.BgDarker);
        changeActionButtonImage(view, R.id.llCustom4, false, -1, -1, R.color.BgDarker);
        changeActionButtonImage(view, R.id.llCustom5, false, -1, -1, R.color.BgDarker);
        changeActionButtonImage(view, R.id.llCustom6, false, -1, -1, R.color.BgDarker);
        changeActionButtonImage(view, R.id.llOne, false, -1, -1, R.color.auluxaGreen);
        changeActionButtonImage(view, R.id.llTwo, false, -1, -1, R.color.auluxaGreen);
        changeActionButtonImage(view, R.id.llThree, false, -1, -1, R.color.auluxaGreen);
        changeActionButtonImage(view, R.id.llFour, false, -1, -1, R.color.auluxaGreen);
        changeActionButtonImage(view, R.id.llFive, false, -1, -1, R.color.auluxaGreen);
        changeActionButtonImage(view, R.id.llSix, false, -1, -1, R.color.auluxaGreen);
        changeActionButtonImage(view, R.id.llSeven, false, -1, -1, R.color.auluxaGreen);
        changeActionButtonImage(view, R.id.llEight, false, -1, -1, R.color.auluxaGreen);
        changeActionButtonImage(view, R.id.llNine, false, -1, -1, R.color.auluxaGreen);
        changeActionButtonImage(view, R.id.llCharacter, false, -1, -1, R.color.BgDarker);
        changeActionButtonImage(view, R.id.llClear, false, -1, -1, R.color.BgDarker);
        changeActionButtonImage(view, R.id.llZero, false, -1, -1, R.color.auluxaGreen);
        return view;
    }

}