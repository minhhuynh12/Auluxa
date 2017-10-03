package net.dirox.auluxa.video;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.dirox.auluxa.R;
import net.dirox.auluxa.common.fragment.BaseVideoRemoteFragment;

public class VideoAplifierPageSecondFragment extends BaseVideoRemoteFragment {
    // Store instance variables

    // newInstance constructor for creating fragment with arguments
    public static VideoAplifierPageSecondFragment newInstance(int page, String title) {
        VideoAplifierPageSecondFragment fragmentSecond = new VideoAplifierPageSecondFragment();
        Bundle args = new Bundle();
        fragmentSecond.setArguments(args);
        return fragmentSecond;
    }

    // Store instance variables based on arguments passed
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    // Inflate the view for the fragment based on layout XML
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_room_centre_video_aplifier_page_two, container, false);
        changeActionButtonImage(view, R.id.lnMenu, false, -1, -1, R.color.BgDarker);
        changeActionButtonImage(view, R.id.lnSleep, false, -1, -1, R.color.BgDarker);
        changeActionButtonImage(view, R.id.lnBack, false, -1, -1, R.color.BgDarker);
        changeActionButtonImage(view, R.id.lnExit, false, -1, -1, R.color.BgDarker);
        changeActionButtonImage(view, R.id.lnCustom1, false, -1, -1, R.color.BgDarker);
        changeActionButtonImage(view, R.id.lnCustom2, false, -1, -1, R.color.BgDarker);
        changeActionButtonImage(view, R.id.lnCustom3, false, -1, -1, R.color.BgDarker);
        changeActionButtonImage(view, R.id.lnCustom4, false, -1, -1, R.color.BgDarker);
        changeActionButtonImage(view, R.id.lnCustom5, false, -1, -1, R.color.BgDarker);
        changeActionButtonImage(view, R.id.lnCustom6, false, -1, -1, R.color.BgDarker);
        changeActionButtonImage(view, R.id.lnCustom7, false, -1, -1, R.color.BgDarker);
        changeActionButtonImage(view, R.id.lnCustom8, false, -1, -1, R.color.BgDarker);
        changeActionButtonImage(view, R.id.lnCustom9, false, -1, -1, R.color.BgDarker);
        return view;
    }
}