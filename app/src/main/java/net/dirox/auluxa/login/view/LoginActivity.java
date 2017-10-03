package net.dirox.auluxa.login.view;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;

import net.dirox.auluxa.R;
import net.dirox.auluxa.activity.FragmentInteraction;
import net.dirox.auluxa.adapter.GatewayListAdapter;
import net.dirox.auluxa.common.activity.BaseActivity;
import net.dirox.auluxa.data.sample.GatewayItem;
import net.dirox.auluxa.databinding.ActivityLoginBinding;
import net.dirox.auluxa.extras.Enumes;
import net.dirox.auluxa.login.LoginActivityContract;
import net.dirox.auluxa.login.dagger.DaggerLoginActivityComponent;
import net.dirox.auluxa.login.dagger.LoginActivityComponent;
import net.dirox.auluxa.login.dagger.LoginActivityModule;
import net.dirox.auluxa.main.view.MainActivity;
import net.dirox.auluxa.setting.view.SettingGateWayActivity;
import net.dirox.auluxa.utils.Constant;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by YEN_MINH on 6/8/2017 1:58 AM.
 * Code and Life ( ^ .,,. ^ )
 * ---------------------------------------------
 */

public class LoginActivity extends BaseActivity implements LoginActivityContract.View, FragmentInteraction {
    private ActivityLoginBinding mBinding;

    /*Gateway list*/
    private List<GatewayItem> list;
    private GatewayListAdapter adapter;

    @Inject
    LoginActivityContract.Presenter presenter;

    private LoginActivityComponent mComponent;

    private LoginActivityComponent component() {
        if (mComponent == null) {
            mComponent = DaggerLoginActivityComponent.builder()
                    .activityComponent(abstractActivityComponent())
                    .loginActivityModule(new LoginActivityModule(this))
                    .build();
        }
        return mComponent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        component().inject(this);
        mBinding.setPresenter(presenter);

        /**
         * Set focus email first
         */
        mBinding.emailaddreFieldSetuploginSetuplogin.requestFocus();

        /**
         * Gateway Setting List
         */
        Intent intent = getIntent();
        String value = intent.getStringExtra(Constant.SETTING_GATEWAY_KEY);

        try {
            if(value.equals(Constant.SETTING_GATEWAY_VALUE)) {
                showTabSetupGatewayClick();
            } else {

            }
        } catch (Exception e) {}

        /*Gateway list*/
        list = new ArrayList<GatewayItem>();
        list.add(new GatewayItem(1, "GATEWAY ONE", false));
        list.add(new GatewayItem(2, "GATEWAY TWO", false));
        list.add(new GatewayItem(3, "GATEWAY THREE", false));

        adapter = new GatewayListAdapter(this, R.layout.layout_item_gateway, list);
        mBinding.lvGatewayList.setAdapter(adapter);

        mBinding.lvGatewayList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                startActivity(new Intent(LoginActivity.this, SettingGateWayActivity.class));
            }
        });
    }

    @Override
    public void openFragment(Enumes.FragmentType fragmentType, Bundle bundle, Enumes.Direction directionIn) {

    }

    @Override
    public void onFragmentVisible(boolean isVisible) {

    }

    @Override
    public void showLoginSuccess() {

        startActivity(new Intent(this, MainActivity.class));

        overridePendingTransition(R.anim.from_middle, R.anim.to_middle);
    }

    @Override
    public void showLoginFailed() {

    }

    @Override
    public void showTabLoginClick() {
        mBinding.layoutGatewaySettingList.setVisibility(View.GONE);
        mBinding.layoutLoginMain.setVisibility(View.VISIBLE);
        mBinding.textLoginBelow.setAlpha(0.5f);
        mBinding.activeoffSetupSetuploginNavigationSetup.setAlpha(1f);
    }

    @Override
    public void showTabSetupGatewayClick() {
        mBinding.layoutLoginMain.setVisibility(View.GONE);
        mBinding.layoutGatewaySettingList.setVisibility(View.VISIBLE);
        mBinding.activeoffSetupSetuploginNavigationSetup.setAlpha(0.5f);
        mBinding.textLoginBelow.setAlpha(1f);

    }

    @Override
    public void showAddGatewayList() {
        startActivity(new Intent(this, LoginSetupActivity.class));
    }

    @Override
    public void goToSignupActivity() {
        startActivity(new Intent(this, LoginSignupActivity.class));
    }

    @Override
    public void showSetForgetPassword() {
        startActivity(new Intent(this, LoginForgotPasswordActivity.class));
    }
}
