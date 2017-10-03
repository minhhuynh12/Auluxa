package net.dirox.auluxa.video;

import android.view.View;
import android.widget.TextView;

import net.dirox.auluxa.common.mvp.BasePresenter;
import net.dirox.auluxa.common.mvp.BaseView;


public interface FragmentRoomCenterVideoContract {

    interface View extends BaseView {

        void showDropDownMenu(int idFocus);

        void collapseDropDownMenu(String text);

    }

    interface Presenter extends BasePresenter {

        void openDropDownMenu(android.view.View view);

        void collapseDropDownMenu(android.view.View textView);
    }
}
