package net.dirox.auluxa.login;

import android.os.Handler;

import javax.inject.Inject;

/**
 * Created by YEN_MINH on 6/8/2017 3:47 AM.
 * Code and Life ( ^ .,,. ^ )
 * ---------------------------------------------
 */

public class LoginConnectActivityPresenter implements LoginConnectActivityContract.Presenter {

    private final LoginConnectActivityContract.View mView;
    private Runnable mockRunable;
    private Handler mockHanderler;
    private final long timeMockDuration = 3000;

    @Inject
    public LoginConnectActivityPresenter(LoginConnectActivityContract.View mView) {
        this.mView = mView;
    }

    @Override
    public void start() {
        if(mockRunable == null){
            mockRunable = mView::showConnectionSuccess;
        }
        if(mockHanderler == null){
            mockHanderler = new Handler();
        }
        mockHanderler.postDelayed(mockRunable, timeMockDuration);
    }

    @Override
    public void stop() {
        if(mockHanderler != null && mockRunable != null){
            mockHanderler.removeCallbacks(mockRunable);
        }

    }

    @Override
    public void nextCall() {
        mView.showNextCall();

    }

    @Override
    public void backCall() {
        mView.showBackCall();
    }
}
