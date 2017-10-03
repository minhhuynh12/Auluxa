package net.dirox.auluxa.login;

import android.text.Editable;
import android.text.TextWatcher;

import org.w3c.dom.Text;

/**
 * Created by YEN_MINH on 6/8/2017 1:59 AM.
 * Code and Life ( ^ .,,. ^ )
 * ---------------------------------------------
 */

public interface LoginInputUserActivityContract {
    interface View extends LoginSetupActivityContract.View {
        void showUserTextEmpty();

        void showUserTextInputed();
    }

    interface Presenter extends LoginSetupActivityContract.Presenter {


        void onTextChange(CharSequence s, int start, int before, int count);

        /**
         * this method using for data-binding
         * @return TextWatcher
         */
        TextWatcher textWatcher();
    }
}
