package net.dirox.auluxa.login;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;

import javax.inject.Inject;

/**
 * Created by YEN_MINH on 6/8/2017 2:21 AM.
 * Code and Life ( ^ .,,. ^ )
 * ---------------------------------------------
 */

public class LoginInputPassPresenter implements LoginInputPassActivityContract.Presenter {

    private final LoginInputPassActivityContract.View mView;
    private final TextWatcher mTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            onTextChange(charSequence, i, i1, i2);
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };


    @Inject
    public LoginInputPassPresenter(LoginInputPassActivityContract.View mView) {
        this.mView = mView;
    }

    @Override
    public void start() {

    }

    @Override
    public void stop() {

    }

    @Override
    public void nextCall() {
        mView.showNextCall();

    }

    @Override
    public void backCall() {
        mView.showBackCall();
    }

    @Override
    public void onTextChange(CharSequence s, int start, int before, int count) {
        if (TextUtils.isEmpty(s)) {
            mView.showPassTextEmpty();
        } else {
            mView.showPassTextInputed();
        }
    }

    @Override
    public TextWatcher textWatcher() {
        return mTextWatcher;
    }
}
