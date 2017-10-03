package net.dirox.auluxa.video;

import android.view.View;
import android.widget.TextView;

import net.dirox.auluxa.R;

import javax.inject.Inject;

public class FragmentRoomCenterVideoPresenter implements FragmentRoomCenterVideoContract.Presenter {

    private final FragmentRoomCenterVideoContract.View mView;

    @Inject
    public FragmentRoomCenterVideoPresenter(FragmentRoomCenterVideoContract.View mView) {
        this.mView = mView;
    }

    @Override
    public void start() {

    }

    @Override
    public void stop() {

    }


    @Override
    public void openDropDownMenu(View view) {
        if (view instanceof TextView) {
            switch (((TextView) view).getText().toString()) {
                case "AMPLIFIER":
                    mView.showDropDownMenu(R.id.tvCustom1);
                    break;
                case "CABLE TV":
                    mView.showDropDownMenu(R.id.tvCustom1);
                    break;
                case "APPLE TV":
                    mView.showDropDownMenu(R.id.tvCustom1);
                    break;
                case "MATRIX":
                    mView.showDropDownMenu(R.id.tvCustom1);
                    break;
            }
        }

    }

    @Override
    public void collapseDropDownMenu(View view) {
        if (view instanceof TextView) {
            TextView text = (TextView) view;
            mView.collapseDropDownMenu(text.getText().toString());
        }
    }
}
