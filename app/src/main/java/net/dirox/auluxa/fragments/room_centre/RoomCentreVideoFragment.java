package net.dirox.auluxa.fragments.room_centre;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import net.dirox.auluxa.R;
import net.dirox.auluxa.activity.FragmentInteraction;
import net.dirox.auluxa.adapter.VideoFragmentViewPagerAdapter;
import net.dirox.auluxa.common.fragment.BaseFragment;
import net.dirox.auluxa.databinding.FragmentRoomCentreVideoBinding;
import net.dirox.auluxa.widget.CirclePageIndicator;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by Hoang Giang an on 6/12/2017.
 */

public class RoomCentreVideoFragment extends BaseFragment implements ViewPager.OnPageChangeListener {

    private FragmentInteraction mInteraction;

    public enum CurrentFragment {
        APLIFIER,
        CABLE_TV,
        APPLE_TV,
        MATRIX,
        TV_REMOTE,
        DVD_REMOTE,
        MEDIA_SERVER
    }

    FragmentRoomCentreVideoBinding mBinding;
    ViewPager vpVideo;
    RelativeLayout rlSelect;
    TextView tvTitle;
    LinearLayout lnMenu;
    static String title1 = "";
    CirclePageIndicator pgiVideo;
    VideoFragmentViewPagerAdapter pagerAdapter;
    static CurrentFragment defaultFragment = CurrentFragment.APLIFIER;

    public static RoomCentreVideoFragment newInstance(CurrentFragment currentFragment1, String title) {
        Bundle args = new Bundle();
        defaultFragment = currentFragment1;
        title1 = title;
        RoomCentreVideoFragment fragment = new RoomCentreVideoFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public static RoomCentreVideoFragment newInstance(CurrentFragment currentFragment1) {
        Bundle args = new Bundle();
        defaultFragment = currentFragment1;
        title1 = "VIDEO";
        RoomCentreVideoFragment fragment = new RoomCentreVideoFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public RoomCentreVideoFragment() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {

        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_room_centre_video, container, false);
        ButterKnife.inject(this, mBinding.getRoot());

        vpVideo = (ViewPager) mBinding.getRoot().findViewById(R.id.vpVideo);
        tvTitle = (TextView) mBinding.getRoot().findViewById(R.id.tvTitle);
        tvTitle.setText(title1);
        pgiVideo = (CirclePageIndicator) mBinding.getRoot().findViewById(R.id.pgiVideo);
        rlSelect = (RelativeLayout) mBinding.getRoot().findViewById(R.id.rlSelect);
        lnMenu = (LinearLayout) mBinding.getRoot().findViewById(R.id.lnMenu);
        pagerAdapter = new VideoFragmentViewPagerAdapter(getChildFragmentManager(), new VideoFragmentViewPagerAdapter.OnCurrentFragment() {
            @Override
            public void checkCountViewPager() {
                countViewPager();
                changeColorTitleWithCurrentFragment();
            }
        });
        vpVideo.setAdapter(pagerAdapter);
        vpVideo.addOnPageChangeListener(this);
        vpVideo.setOffscreenPageLimit(pagerAdapter.getCount());
        pgiVideo.setViewPager(vpVideo);
        pgiVideo.setCurrentItem(0);
        pgiVideo.setFillColor(ContextCompat.getColor(getContext(), R.color.auluxaGreen));
        pgiVideo.setPageColor(ContextCompat.getColor(getContext(), R.color.BgDarkGray));
        pagerAdapter.setCurentFragmentLayout(defaultFragment);
        countViewPager();
        return mBinding.getRoot();
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }

    @Override
    public void onPageSelected(int position) {
        if (position > 0) {
            rlSelect.setVisibility(View.GONE);
            lnMenu.setVisibility(View.GONE);
        } else {
            rlSelect.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }


    @OnClick(R.id.tvAplifier)
    public void onClickAplifier() {
        if (pagerAdapter.getCurrentFragment() != CurrentFragment.APLIFIER) {

            defaultFragment = CurrentFragment.APLIFIER;
            changeColorTitleWithCurrentFragment();
            pagerAdapter.setCurentFragmentLayout(CurrentFragment.APLIFIER);
            pagerAdapter.notifyDataSetChanged();
            vpVideo.invalidate();
            actionMenu();
        } else actionMenu();
    }

    @OnClick(R.id.tvCabletv)
    public void onClickCableTV() {
        if (pagerAdapter.getCurrentFragment() != CurrentFragment.CABLE_TV) {
            defaultFragment = CurrentFragment.CABLE_TV;
            changeColorTitleWithCurrentFragment();
            pagerAdapter.setCurentFragmentLayout(CurrentFragment.CABLE_TV);
            pagerAdapter.notifyDataSetChanged();
            vpVideo.invalidate();
            actionMenu();
        } else actionMenu();
    }

    @OnClick(R.id.tvAppletv)
    public void onClickAppleTV() {
        if (pagerAdapter.getCurrentFragment() != CurrentFragment.APPLE_TV) {
            defaultFragment = CurrentFragment.APPLE_TV;
            changeColorTitleWithCurrentFragment();
            pagerAdapter.setCurentFragmentLayout(CurrentFragment.APPLE_TV);
            pagerAdapter.notifyDataSetChanged();
            vpVideo.invalidate();
            actionMenu();
        } else actionMenu();
    }

    @OnClick(R.id.tvMatrix)
    public void onClickMatrix() {
        if (pagerAdapter.getCurrentFragment() != CurrentFragment.MATRIX) {
            defaultFragment = CurrentFragment.MATRIX;
            changeColorTitleWithCurrentFragment();
            pagerAdapter.setCurentFragmentLayout(CurrentFragment.MATRIX);
            pagerAdapter.notifyDataSetChanged();
            vpVideo.invalidate();
            actionMenu();
        } else actionMenu();
    }

    @OnClick(R.id.tvTvremote)
    public void onClickTVRemote() {
        if (pagerAdapter.getCurrentFragment() != CurrentFragment.TV_REMOTE) {
            defaultFragment = CurrentFragment.TV_REMOTE;
            changeColorTitleWithCurrentFragment();
            pagerAdapter.setCurentFragmentLayout(CurrentFragment.TV_REMOTE);
            pagerAdapter.notifyDataSetChanged();
            vpVideo.invalidate();
            actionMenu();
        } else actionMenu();
    }

    @OnClick(R.id.tvDvdRemote)
    public void onClickDVDRemote() {
        if (pagerAdapter.getCurrentFragment() != CurrentFragment.DVD_REMOTE) {
            defaultFragment = CurrentFragment.DVD_REMOTE;
            changeColorTitleWithCurrentFragment();
            pagerAdapter.setCurentFragmentLayout(CurrentFragment.DVD_REMOTE);
            pagerAdapter.notifyDataSetChanged();
            vpVideo.invalidate();
            actionMenu();
        } else actionMenu();
    }

    @OnClick(R.id.tvMediaServer)
    public void onClickMediaServer() {
        if (pagerAdapter.getCurrentFragment() != CurrentFragment.MEDIA_SERVER) {
            defaultFragment = CurrentFragment.MEDIA_SERVER;
            changeColorTitleWithCurrentFragment();
            pagerAdapter.setCurentFragmentLayout(CurrentFragment.MEDIA_SERVER);
            pagerAdapter.notifyDataSetChanged();
            vpVideo.invalidate();
            actionMenu();
        } else actionMenu();
    }

    @OnClick(R.id.ivBack)
    public void onBack() {
        getActivity().onBackPressed();
    }

    @OnClick(R.id.rlSelect)
    public void onMenu() {
        actionMenu();
    }

    public void actionMenu() {
        hideKeyboardIfNeed(getContext());
        if (lnMenu.getVisibility() == View.VISIBLE)
            lnMenu.setVisibility(View.GONE);
        else lnMenu.setVisibility(View.VISIBLE);
    }

    public void setChangeColorItemMenuSelect(TextView tvMenuSelect, View view) {
        mBinding.tvAplifier.setTextColor(ContextCompat.getColor(getContext(), R.color.white));
        mBinding.lineAp.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.colorOverlayGray));
        mBinding.tvAppletv.setTextColor(ContextCompat.getColor(getContext(), R.color.white));
        mBinding.lineApp.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.colorOverlayGray));
        mBinding.tvCabletv.setTextColor(ContextCompat.getColor(getContext(), R.color.white));
        mBinding.lineCa.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.colorOverlayGray));
        mBinding.tvMatrix.setTextColor(ContextCompat.getColor(getContext(), R.color.white));
        mBinding.lineMa.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.colorOverlayGray));
        mBinding.tvTvremote.setTextColor(ContextCompat.getColor(getContext(), R.color.white));
        mBinding.lineTvRe.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.colorOverlayGray));
        mBinding.tvDvdRemote.setTextColor(ContextCompat.getColor(getContext(), R.color.white));
        mBinding.lineDvd.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.colorOverlayGray));
        mBinding.tvMediaServer.setTextColor(ContextCompat.getColor(getContext(), R.color.white));
        mBinding.lineMe.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.colorOverlayGray));

        tvMenuSelect.setTextColor(ContextCompat.getColor(getContext(), R.color.auluxaGreen));

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mInteraction = (FragmentInteraction) context;
    }

    @Override
    public void onStart() {
        super.onStart();
        if (!(pagerAdapter == null)) {
            pagerAdapter.setCurentFragmentLayout(VideoFragmentViewPagerAdapter.currentFragment);
            pagerAdapter.notifyDataSetChanged();
        }
    }

    public void countViewPager() {
        if (pagerAdapter.getCount() > 1)
            pgiVideo.setVisibility(View.VISIBLE);
        else
            pgiVideo.setVisibility(View.INVISIBLE);
    }

    public void changeColorTitleWithCurrentFragment(){
        switch(defaultFragment) {
            case APLIFIER:
                setChangeColorItemMenuSelect(mBinding.tvAplifier, mBinding.lineAp);
                break;
            case APPLE_TV:
                setChangeColorItemMenuSelect(mBinding.tvAppletv, mBinding.lineApp);
                break;
            case CABLE_TV:
                setChangeColorItemMenuSelect(mBinding.tvCabletv, mBinding.lineCa);
                break;
            case TV_REMOTE:
                setChangeColorItemMenuSelect(mBinding.tvTvremote, mBinding.lineTvRe);
                break;
            case DVD_REMOTE:
                setChangeColorItemMenuSelect(mBinding.tvDvdRemote, mBinding.lineDvd);
                break;
            case MATRIX:
                setChangeColorItemMenuSelect(mBinding.tvMatrix, mBinding.lineMa);
                break;
            case MEDIA_SERVER:
                setChangeColorItemMenuSelect(mBinding.tvMediaServer, mBinding.lineMe);
                break;
            default:
                break;
        }
    }
}
