package net.dirox.auluxa.login.view;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.widget.Toast;

import net.dirox.auluxa.R;
import net.dirox.auluxa.activity.FragmentInteraction;
import net.dirox.auluxa.common.activity.BaseActivity;
import net.dirox.auluxa.databinding.ActivitySignupLoginBinding;
import net.dirox.auluxa.extras.Enumes;
import net.dirox.auluxa.login.LoginSignupContract;
//import net.dirox.auluxa.login.dagger.DaggerLoginSignupActivityComponent;
import net.dirox.auluxa.login.dagger.DaggerLoginSignupActivityComponent;
import net.dirox.auluxa.login.dagger.LoginSignupActivityComponent;
import net.dirox.auluxa.login.dagger.LoginSignupActivityModule;

import javax.inject.Inject;

import dagger.internal.DaggerCollections;

/**
 * Created by minh on 14/06/2017.
 */

public class LoginSignupActivity extends BaseActivity implements LoginSignupContract.View {

    private ActivitySignupLoginBinding mBinding;
    @Inject
    LoginSignupContract.Presenter presenter;

    private LoginSignupActivityComponent mComponent;

    private LoginSignupActivityComponent component() {
        if (mComponent == null) {
            mComponent = DaggerLoginSignupActivityComponent.builder()
                    .activityComponent(abstractActivityComponent())
                    .loginSignupActivityModule(new LoginSignupActivityModule(this))
                    .build();
        }
        return  mComponent;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_signup_login);
        component().inject(this);
        mBinding.setPresenter(presenter);
    }


    @Override
    public void showSignupSuccess() {
        finish();
    }

    @Override
    public void showSignupBack() {
        finish();
    }
}


