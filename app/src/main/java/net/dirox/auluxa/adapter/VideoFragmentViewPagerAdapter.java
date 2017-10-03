package net.dirox.auluxa.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import net.dirox.auluxa.fragments.room_centre.RoomCentreVideoFragment;
import net.dirox.auluxa.video.VideoAplifierPageOneFragment;
import net.dirox.auluxa.video.VideoAplifierPageSecondFragment;
import net.dirox.auluxa.video.VideoAppleTVPageOneFragment;
import net.dirox.auluxa.video.VideoCableTVPageOneFragment;
import net.dirox.auluxa.video.VideoCableTVPageSecondFragment;
import net.dirox.auluxa.video.VideoDVDRemotePageOneFragment;
import net.dirox.auluxa.video.VideoDVDRemotePageSecondFragment;
import net.dirox.auluxa.video.VideoMatrixPageOneFragment;
import net.dirox.auluxa.video.VideoMediaServerPageOneFragment;
import net.dirox.auluxa.video.VideoTVRemotePageOneFragment;
import net.dirox.auluxa.video.VideoTVRemotePageSecondFragment;

public class VideoFragmentViewPagerAdapter extends FragmentStatePagerAdapter {
    public static RoomCentreVideoFragment.CurrentFragment currentFragment = RoomCentreVideoFragment.CurrentFragment.APLIFIER;

    public VideoFragmentViewPagerAdapter(FragmentManager fragmentManager,  OnCurrentFragment onCurrentFragment) {
        super(fragmentManager);
        this.onCurrentFragment = onCurrentFragment;
    }

    public interface OnCurrentFragment{
        public void checkCountViewPager();
    }

    OnCurrentFragment onCurrentFragment;

    @Override
    public int getCount() {
        if (currentFragment == RoomCentreVideoFragment.CurrentFragment.APLIFIER || currentFragment == RoomCentreVideoFragment.CurrentFragment.CABLE_TV || currentFragment == RoomCentreVideoFragment.CurrentFragment.TV_REMOTE || currentFragment == RoomCentreVideoFragment.CurrentFragment.DVD_REMOTE) {
            return 2;
        } else return 1;
    }

    @Override
    public Fragment getItem(int position) {
        switch (currentFragment) {
            case APLIFIER: {
                switch (position) {
                    case 0:
                        return VideoAplifierPageOneFragment.newInstance(1, "AMPLIFIER", true);
                    case 1:
                        return VideoAplifierPageSecondFragment.newInstance(2, "AMPLIFIER");
                    default:
                        return null;
                }
            }
            case APPLE_TV:
                return VideoAppleTVPageOneFragment.newInstance(1, "APPLE TV", true);
            case MATRIX:
                return VideoMatrixPageOneFragment.newInstance(1, "MATRIX", true);
            case TV_REMOTE:
                switch (position) {
                    case 0:
                        return VideoTVRemotePageOneFragment.newInstance(1, "TV REMOTE");
                    case 1:
                        return VideoTVRemotePageSecondFragment.newInstance(2, "TV REMOTE");
                    default:
                        return null;
                }
            case CABLE_TV:
                switch (position) {
                    case 0:
                        return VideoCableTVPageOneFragment.newInstance(1, "CABLE TV", true);
                    case 1:
                        return VideoCableTVPageSecondFragment.newInstance(2, "CABLE TV");
                    default:
                        return null;
                }
            case DVD_REMOTE:
                switch (position) {
                    case 0:
                        return VideoDVDRemotePageOneFragment.newInstance(1, "DVD REMOTE", true);
                    case 1:
                        return VideoDVDRemotePageSecondFragment.newInstance(2, "DVD REMOTE");
                    default:
                        return null;
                }
            case MEDIA_SERVER:
                return VideoMediaServerPageOneFragment.newInstance(1, "MEDIA SERVER", true);
            default:
                return null;
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return "Page " + position;
    }

    public void setCurentFragmentLayout(RoomCentreVideoFragment.CurrentFragment currentFragment) {
        if (this.currentFragment != currentFragment) {
            this.currentFragment = currentFragment;
            onCurrentFragment.checkCountViewPager();
            notifyDataSetChanged();
        }
    }

    public RoomCentreVideoFragment.CurrentFragment getCurrentFragment() {
        return this.currentFragment;
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

}