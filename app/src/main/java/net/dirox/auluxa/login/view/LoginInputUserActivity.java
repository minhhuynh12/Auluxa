package net.dirox.auluxa.login.view;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.text.TextWatcher;

import net.dirox.auluxa.R;
import net.dirox.auluxa.common.activity.BaseActivity;
import net.dirox.auluxa.databinding.ActivityInputUserBinding;
import net.dirox.auluxa.login.LoginInputUserActivityContract;
import net.dirox.auluxa.login.LoginInputUserPresenter;
import net.dirox.auluxa.login.dagger.DaggerLoginInputUserActivityComponent;
import net.dirox.auluxa.login.dagger.LoginInputUserActivityComponent;
import net.dirox.auluxa.login.dagger.LoginInputUserActivityModule;

import javax.inject.Inject;

/**
 * Created by YEN_MINH on 6/8/2017 1:54 AM.
 * Code and Life ( ^ .,,. ^ )
 * ---------------------------------------------
 */

public class LoginInputUserActivity extends BaseActivity implements LoginInputUserActivityContract.View {

    private ActivityInputUserBinding mBinding;
    @Inject
    LoginInputUserPresenter presenter;

    private LoginInputUserActivityComponent mComponent;

    private LoginInputUserActivityComponent component() {
        if (mComponent == null) {
            mComponent = DaggerLoginInputUserActivityComponent.builder()
                    .activityComponent(abstractActivityComponent())
                    .loginInputUserActivityModule(new LoginInputUserActivityModule(this))
                    .build();
        }
        return mComponent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_input_user);
        component().inject(this);
        mBinding.setPresenter(presenter);

    }

    @Override
    public void showNextCall() {
        startActivity(new Intent(this, LoginInputPassActivity.class));
    }

    @Override
    public void showBackCall() {
        finish();
    }

    @Override
    public void showSignupSuccess() {

    }

    @Override
    public void showUserTextEmpty() {
        mBinding.nextbtnButtonSetup7Setup7.setBackgroundColor(Color.GRAY);
        mBinding.nextbtnButtonSetup7Setup7.setEnabled(false);
    }

    @Override
    public void showUserTextInputed() {
        mBinding.nextbtnButtonSetup7Setup7.setBackgroundColor(ContextCompat.getColor(this, R.color.auluxaGreen));
        mBinding.nextbtnButtonSetup7Setup7.setEnabled(true);
    }
}
