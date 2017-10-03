package net.dirox.auluxa.activity.main.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import net.dirox.auluxa.R;
import net.dirox.auluxa.activity.main.MainActivityContract;
import net.dirox.auluxa.activity.main.dagger.DaggerMainActivityComponent;
import net.dirox.auluxa.activity.main.dagger.MainActivityComponent;
import net.dirox.auluxa.activity.main.dagger.MainActivityModule;
import net.dirox.auluxa.common.activity.BaseActivity;

import javax.inject.Inject;

public class MainActivity extends BaseActivity implements MainActivityContract.View {
    @Inject
    MainActivityContract.Presenter presenter;

    private MainActivityComponent activityComponent;

    private MainActivityComponent getDaggerComponent() {
        if (activityComponent == null) {
            activityComponent = DaggerMainActivityComponent.builder()
                    .activityComponent(abstractActivityComponent())
                    .mainActivityModule(new MainActivityModule(this))
                    .build();
        }
        return activityComponent;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        binding = DataBindingUtil.setContentView(this, R.layout.layout_settingslogin);
        setContentView(R.layout.layout_setuplogin);
//        getDaggerComponent().inject(this);
        //binding.setPresenter((MainActivityPresenter) presenter);
//        initializeViews();
//        init();
    }

    private void initializeViews() {

    }

    private void init() {
        presenter.validateAppInfo();
    }


    @Override
    public void showLoginInput(boolean show) {

    }

    @Override
    public void showLoginSetup() {
        startActivity(new Intent(this, LoginSetupActivity.class));
    }
}
