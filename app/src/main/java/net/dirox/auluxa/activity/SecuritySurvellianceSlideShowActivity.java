package net.dirox.auluxa.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import net.dirox.auluxa.R;
import net.dirox.auluxa.common.activity.BaseActivity;
import net.dirox.auluxa.data.GeneratorSampleData;
import net.dirox.auluxa.data.sample.SecurityItem;
import net.dirox.auluxa.databinding.ActivitySecuritySurvellianceSlideShowBinding;
import net.dirox.auluxa.security.view.FragmentSecuritySurvellianceSlideItem;

import java.util.ArrayList;

/**
 * Created by an on 6/22/2017.
 */

public class SecuritySurvellianceSlideShowActivity extends BaseActivity {

    private ActivitySecuritySurvellianceSlideShowBinding mBinding;
    private ArrayList<SecurityItem.CameraItem> arrayListCamera;
    private int currentPosition;
    private FragmentSecuritySurvellianceSlideItem mCurrentDetailsFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_security_survelliance_slide_show);

        arrayListCamera = GeneratorSampleData.securityItem.arrayListCamera;
        currentPosition = getIntent().getIntExtra("Position", 0);
        mBinding.textView.setText(arrayListCamera.get(currentPosition).name);

        mBinding.viewPager.setAdapter(new SecuritySurvellianceSlideAdapter(getSupportFragmentManager()));
        mBinding.viewPager.setCurrentItem(currentPosition);
        mBinding.viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mBinding.textView.setText(arrayListCamera.get(position).name);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        mBinding.securitySurvellianceBtnExtend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    private class SecuritySurvellianceSlideAdapter extends FragmentStatePagerAdapter {

        public SecuritySurvellianceSlideAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return FragmentSecuritySurvellianceSlideItem.newInstance(arrayListCamera.get(position));
        }

        @Override
        public void setPrimaryItem(ViewGroup container, int position, Object object) {
            super.setPrimaryItem(container, position, object);
            mCurrentDetailsFragment = (FragmentSecuritySurvellianceSlideItem) object;
        }


        @Override
        public int getCount() {
            return arrayListCamera.size();
        }
    }

}
