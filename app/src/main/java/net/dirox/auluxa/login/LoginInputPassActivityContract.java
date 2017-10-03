package net.dirox.auluxa.login;

import android.text.TextWatcher;

/**
 * Created by YEN_MINH on 6/8/2017 1:59 AM.
 * Code and Life ( ^ .,,. ^ )
 * ---------------------------------------------
 */

public interface LoginInputPassActivityContract {
    interface View extends LoginSetupActivityContract.View {
        void showPassTextEmpty();

        void showPassTextInputed();
    }

    interface Presenter extends LoginSetupActivityContract.Presenter {

        void onTextChange(CharSequence s, int start, int before, int count);

        /**
         * this method using for data-binding
         *
         * @return TextWatcher
         */
        TextWatcher textWatcher();
    }
}
