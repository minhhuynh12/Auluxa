package net.dirox.auluxa.login.view;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;

import com.bumptech.glide.Glide;

import net.dirox.auluxa.R;
import net.dirox.auluxa.common.activity.BaseActivity;
import net.dirox.auluxa.databinding.ActivitySetupLoginBinding;
import net.dirox.auluxa.login.LoginSetupActivityContract;
import net.dirox.auluxa.login.dagger.DaggerLoginSetupActivityComponent;
import net.dirox.auluxa.login.dagger.LoginSetupActivityComponent;
import net.dirox.auluxa.login.dagger.LoginSetupActivityModule;

import javax.inject.Inject;

/**
 * Created by MINH NGUYEN on 6/6/2017.
 */

public class LoginSetupActivity extends BaseActivity implements LoginSetupActivityContract.View {

    @Inject
    LoginSetupActivityContract.Presenter presenter;

    private LoginSetupActivityComponent mComponent;
    private ActivitySetupLoginBinding mBinding;
    private int[] mImageResource = {R.drawable.setup2_image_setup2, R.drawable.setup3_image_setup3};
    private int mCurrentImageResourceIndex = 0;

    private LoginSetupActivityComponent component() {
        if (mComponent == null) {
            mComponent = DaggerLoginSetupActivityComponent.builder()
                    .activityComponent(abstractActivityComponent())
                    .loginSetupActivityModule(new LoginSetupActivityModule(this))
                    .build();
        }
        return mComponent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_setup_login);
        component().inject(this);
        mBinding.setPresenter(presenter);
    }

    @Override
    public void showNextCall() {
        if (mCurrentImageResourceIndex == mImageResource.length - 1) {
            //TODO jump to connect activity
            startActivity(new Intent(this, LoginConnectActivity.class));
        } else {
            mCurrentImageResourceIndex++;
            //Glide.with(this).load(mImageResource[mCurrentImageResourceIndex]).into(mBinding.setupLoginImageDevice);
            mBinding.setupLoginImageDevice.setImageDrawable(ContextCompat.getDrawable(this, mImageResource[mCurrentImageResourceIndex]));
            mBinding.textViewTutorial.setText("A SINGLE LED WILL INDICATE THE\nKRANIUM IS READY TO CONNECT.\nTHIS MAY TAKE UP TO 2 MINUTES TO\nCONNECT.");
        }

    }

    @Override
    public void showBackCall() {
        if (mCurrentImageResourceIndex == 0) {
            finish();
        } else {
            mCurrentImageResourceIndex--;
            mBinding.setupLoginImageDevice.setImageDrawable(ContextCompat.getDrawable(this, mImageResource[mCurrentImageResourceIndex]));
            //Glide.with(this).load(mImageResource[mCurrentImageResourceIndex]).into(mBinding.setupLoginImageDevice);
            mBinding.textViewTutorial.setText("CONNECT THE KRANIUM\nTO A POWER OUTLET AND\nTO YOUR ROUTER");
        }
    }

    @Override
    public void showSignupSuccess() {

    }
}
