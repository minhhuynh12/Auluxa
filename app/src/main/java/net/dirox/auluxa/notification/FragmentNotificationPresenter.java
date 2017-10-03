package net.dirox.auluxa.notification;

import javax.inject.Inject;

/**
 * Created by minh on 12/06/2017.
 */

public class FragmentNotificationPresenter implements FragmentNotificationContract.Presenter {

    private FragmentNotificationContract.View mView;

    @Inject
    public FragmentNotificationPresenter(FragmentNotificationContract.View mView) {
        this.mView = mView;
    }


    @Override
    public void start() {

    }

    @Override
    public void stop() {

    }


}
