package net.dirox.auluxa.login.view;

import android.content.Context;
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
import net.dirox.auluxa.databinding.ActivityInputPassBinding;
import net.dirox.auluxa.login.LoginInputPassActivityContract;
import net.dirox.auluxa.login.LoginInputPassPresenter;
import net.dirox.auluxa.login.dagger.DaggerLoginInputPassActivityComponent;
import net.dirox.auluxa.login.dagger.LoginInputPassActivityComponent;
import net.dirox.auluxa.login.dagger.LoginInputPassActivityModule;

import javax.inject.Inject;

/**
 * Created by YEN_MINH on 6/8/2017 1:54 AM.
 * Code and Life ( ^ .,,. ^ )
 * ---------------------------------------------
 */

public class LoginInputPassActivity extends BaseActivity implements LoginInputPassActivityContract.View {

    private ActivityInputPassBinding mBinding;
    @Inject
    LoginInputPassPresenter presenter;

    private LoginInputPassActivityComponent mComponent;

    private LoginInputPassActivityComponent component() {
        if (mComponent == null) {
            mComponent = DaggerLoginInputPassActivityComponent.builder()
                    .activityComponent(abstractActivityComponent())
                    .loginInputPassActivityModule(new LoginInputPassActivityModule(this))
                    .build();
        }
        return mComponent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_input_pass);
        component().inject(this);
        mBinding.setPresenter(presenter);

    }

    @Override
    public void showNextCall() {
        startActivity(new Intent(this, LoginResultViewActivity.class));
    }

    @Override
    public void showBackCall() {
        finish();
    }

    @Override
    public void showSignupSuccess() {

    }

    @Override
    public void showPassTextEmpty() {
        mBinding.nextbtnButtonSetup7Setup7.setBackgroundColor(Color.GRAY);
        mBinding.nextbtnButtonSetup7Setup7.setEnabled(false);
    }

    @Override
    public void showPassTextInputed() {
        mBinding.nextbtnButtonSetup7Setup7.setBackgroundColor(ContextCompat.getColor(this, R.color.auluxaGreen));
        mBinding.nextbtnButtonSetup7Setup7.setEnabled(true);
    }
}
