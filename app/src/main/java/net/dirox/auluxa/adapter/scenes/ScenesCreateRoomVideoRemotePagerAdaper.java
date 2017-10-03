package net.dirox.auluxa.adapter.scenes;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import net.dirox.auluxa.fragments.scenes.CreateScenesRoomVideoAddSequenceFragment;
import net.dirox.auluxa.video.VideoAplifierPageOneFragment;
import net.dirox.auluxa.video.VideoAplifierPageSecondFragment;
import net.dirox.auluxa.video.VideoAppleTVPageOneFragment;
import net.dirox.auluxa.video.VideoCableTVPageOneFragment;
import net.dirox.auluxa.video.VideoCableTVPageSecondFragment;
import net.dirox.auluxa.video.VideoDVDRemotePageOneFragment;
import net.dirox.auluxa.video.VideoDVDRemotePageSecondFragment;
import net.dirox.auluxa.video.VideoMatrixPageOneFragment;
import net.dirox.auluxa.video.VideoMediaServerPageOneFragment;

/**
 * Created by an on 6/29/2017.
 */

public class ScenesCreateRoomVideoRemotePagerAdaper extends FragmentStatePagerAdapter {

    private CreateScenesRoomVideoAddSequenceFragment.Device mDeviceType;

    public ScenesCreateRoomVideoRemotePagerAdaper(FragmentManager fm, CreateScenesRoomVideoAddSequenceFragment.Device deviceType) {
        super(fm);
        mDeviceType = deviceType;
    }

    @Override
    public Fragment getItem(int position) {

        switch (mDeviceType) {
            case CABLE_TV:
                if (position == 0) {
                    return VideoCableTVPageOneFragment.newInstance(1, "CABLE TV", false);
                } else {
                    return VideoCableTVPageSecondFragment.newInstance(2, "CABLE TV");
                }
            case APPLE_TV:
                return VideoAppleTVPageOneFragment.newInstance(1, "APPLE TV", false);
            case BLUERAY:
                if (position == 0) {
                    return VideoDVDRemotePageOneFragment.newInstance(1, "DVD REMOTE", false);
                } else {
                    return VideoDVDRemotePageSecondFragment.newInstance(2, "DVD REMOTE");
                }
            case AMPLIFIER:
                if (position == 0) {
                    return VideoAplifierPageOneFragment.newInstance(1, "AMPLIFIER", false);
                } else {
                    return VideoAplifierPageSecondFragment.newInstance(2, "AMPLIFIER");
                }
            case MATRIX:
                return VideoMatrixPageOneFragment.newInstance(1, "MATRIX", false);
            case MEDIA_SERVER:
                return VideoMediaServerPageOneFragment.newInstance(1, "MEDIA SERVER", false);
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        switch (mDeviceType) {
            case CABLE_TV:
                return 2;
            case APPLE_TV:
                return 1;
            case BLUERAY:
                return 2;
            case AMPLIFIER:
                return 2;
            case MATRIX:
                return 1;
            case MEDIA_SERVER:
                return 1;
            default:
                return 1;
        }
    }
}
