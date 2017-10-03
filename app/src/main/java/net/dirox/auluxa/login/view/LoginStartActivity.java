package net.dirox.auluxa.login.view;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;

import net.dirox.auluxa.Configuration;
import net.dirox.auluxa.R;
import net.dirox.auluxa.common.activity.BaseActivity;
import net.dirox.auluxa.databinding.ActivityStartLoginBinding;
import net.dirox.auluxa.login.LoginStartActivityContract;
import net.dirox.auluxa.login.dagger.DaggerLoginStartActivityComponent;
import net.dirox.auluxa.login.dagger.LoginStartActivityComponent;
import net.dirox.auluxa.login.dagger.LoginStartActivityModule;
import net.dirox.auluxa.main.view.MainActivity;

import javax.inject.Inject;

//import net.dirox.auluxa.databinding.ActivityLoginSetupBinding;

/**
 * Created by MINH NGUYEN on 6/6/2017.
 */

public class LoginStartActivity extends BaseActivity implements LoginStartActivityContract.View {

    private ActivityStartLoginBinding mBinding;
    private LoginStartActivityComponent mComponent;

    @Inject
    LoginStartActivityContract.Presenter presenter;

    private LoginStartActivityComponent component() {
        if (mComponent == null) {
            mComponent = DaggerLoginStartActivityComponent.builder()
                    .activityComponent(abstractActivityComponent())
                    .loginStartActivityModule(new LoginStartActivityModule(this))
                    .build();
        }
        return mComponent;
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_start_login);
        component().inject(this);
        mBinding.setPresenter(presenter);


    }

    @Override
    public void showLoginActivitySetup() {
        if (Configuration.TEST_SKIP_SETUP_ACTIVITY == true) {
            startActivity(new Intent(this, MainActivity.class));
        } else {
            startActivity(new Intent(this, LoginSetupActivity.class));
        }

    }

    @Override
    public void showLoginActivity() {

    }

    @Override
    public void showDialogAlertSetup() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this)
                .setMessage("Please complete your setup before login ")
                .setNeutralButton("Ok", (dialog, i) -> dialog.dismiss())
                .setCancelable(false);
        builder.create().show();

    }


}
