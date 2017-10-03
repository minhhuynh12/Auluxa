package net.dirox.auluxa.login.view;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;

import net.dirox.auluxa.R;
import net.dirox.auluxa.common.activity.BaseActivity;
import net.dirox.auluxa.databinding.ActivityLoginConnectDeviceBinding;
import net.dirox.auluxa.login.LoginConnectActivityContract;
import net.dirox.auluxa.login.dagger.DaggerLoginConnectActivityComponent;
import net.dirox.auluxa.login.dagger.LoginConnectActivityComponent;
import net.dirox.auluxa.login.dagger.LoginConnectActivityModule;

import javax.inject.Inject;

/**
 * Created by MINH NGUYEN on 6/7/2017.
 */

public class LoginConnectActivity extends BaseActivity implements LoginConnectActivityContract.View {

    private ActivityLoginConnectDeviceBinding mBinding;
    @Inject
    LoginConnectActivityContract.Presenter presenter;

    private LoginConnectActivityComponent mComponent;

    private LoginConnectActivityComponent component() {
        if (mComponent == null) {
            mComponent = DaggerLoginConnectActivityComponent.builder()
                    .activityComponent(abstractActivityComponent())
                    .loginConnectActivityModule(new LoginConnectActivityModule(this))
                    .build();
        }
        return mComponent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_login_connect_device);
        component().inject(this);
        mBinding.setPresenter(presenter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.start();
    }

    @Override
    protected void onStop() {
        super.onStop();
        presenter.stop();
    }

    @Override
    public void showNextCall() {
        startActivity(new Intent(this, LoginInputUserActivity.class));
    }

    @Override
    public void showBackCall() {
        finish();
    }

    @Override
    public void showSignupSuccess() {

    }

    @Override
    public void showConnectionSuccess() {
        mBinding.activityLoginConnectStatusTv.setText("You are now connected");
        mBinding.retrybtnButtonSetup5Setup5.setBackgroundColor(ContextCompat.getColor(this, R.color.auluxaGreen));
        mBinding.retrybtnButtonSetup5Setup5.setEnabled(true);
    }

    @Override
    public void showConnecting() {

    }
}
