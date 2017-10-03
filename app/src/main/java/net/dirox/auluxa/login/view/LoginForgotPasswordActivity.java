package net.dirox.auluxa.login.view;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import net.dirox.auluxa.R;
import net.dirox.auluxa.databinding.ActivityForgetLoginBinding;
import net.dirox.auluxa.login.LoginForgotPasswordContract;

import javax.inject.Inject;

/**
 * Created by minh on 15/06/2017.
 */

public class LoginForgotPasswordActivity extends AppCompatActivity implements LoginForgotPasswordContract.View {

    private ActivityForgetLoginBinding mBinding;
    Button btnFor_back_warning;


    @Inject
    LoginForgotPasswordContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().getAttributes().windowAnimations = R.style.Fade;

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_forget_login);
//        mBinding.setPresenter(presenter);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;
        getWindow().setLayout((int)(width*.9), (int) (height*.3));

        WindowManager.LayoutParams windowManager = getWindow().getAttributes();
        windowManager.dimAmount = 0.7f;
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);

        mBinding.btnForBackWarning.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("MyTagGoesHere", "jhjhjhjhjhjhjhhbjnnjhjnhjkknnjhjhjuyuhjhj");
                finish();
            }
        });


    }

    @Override
    public void showSetFinnishButtonCanel() {
//        onBackPressed();
        Log.d("MyTagGoesHere", "jhjhjhjhjhjhjhhbjnnjhjnhjkknnjhjhjuyuhjhj");
        finish();
    }
}
