package net.dirox.auluxa.login.view;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;

import net.dirox.auluxa.R;
import net.dirox.auluxa.activity.main.view.MainActivity;
import net.dirox.auluxa.common.activity.BaseActivity;
import net.dirox.auluxa.databinding.ActivityResultViewBinding;
import net.dirox.auluxa.login.LoginResultViewContract;
import net.dirox.auluxa.login.LoginResultViewPresenter;
import net.dirox.auluxa.login.dagger.DaggerLoginResultViewActivityComponent;
import net.dirox.auluxa.login.dagger.LoginResultViewActivityComponent;
import net.dirox.auluxa.login.dagger.LoginResultViewActivityModule;

import javax.inject.Inject;

/**
 * Created by YEN_MINH on 6/8/2017 1:55 AM.
 * Code and Life ( ^ .,,. ^ )
 * ---------------------------------------------
 */

public class LoginResultViewActivity extends BaseActivity implements LoginResultViewContract.View {

    private ActivityResultViewBinding mBinding;
    @Inject
    LoginResultViewContract.Presenter presenter;

    private LoginResultViewActivityComponent mComponent;
    private LoginResultViewActivityComponent component() {
        if (mComponent == null) {
            mComponent = DaggerLoginResultViewActivityComponent.builder()
                    .activityComponent(abstractActivityComponent())
                    .loginResultViewActivityModule(new LoginResultViewActivityModule(this))
                    .build();
        }
        return mComponent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_result_view);
        component().inject(this);
        mBinding.setPresenter(presenter);
    }

    @Override
    public void showSigupActviity() {

    }

    @Override
    public void showLoginActivity() {
        startActivity(new Intent(this, LoginActivity.class));
    }
}
