package net.dirox.auluxa.notification;

import net.dirox.auluxa.common.mvp.BasePresenter;
import net.dirox.auluxa.common.mvp.BaseView;

/**
 * Created by minh on 12/06/2017.
 */

public interface FragmentNotificationContract {
    interface View extends BaseView{
        void  showNotification();


    }
    interface Presenter extends BasePresenter{

    }
}
