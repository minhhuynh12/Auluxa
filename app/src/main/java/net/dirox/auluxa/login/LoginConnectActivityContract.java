package net.dirox.auluxa.login;

/**
 * Created by YEN_MINH on 6/8/2017 3:46 AM.
 * Code and Life ( ^ .,,. ^ )
 * ---------------------------------------------
 */

public interface LoginConnectActivityContract {

    interface View extends LoginSetupActivityContract.View {
        void showConnectionSuccess();

        void showConnecting();
    }

    interface Presenter extends LoginSetupActivityContract.Presenter {

    }
}
