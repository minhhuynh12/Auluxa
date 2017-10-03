package net.dirox.auluxa.setting;

import javax.inject.Inject;

/**
 * Created by MINH NGUYEN on 6/13/2017.
 */

public class FragmentGatewaySettingPresenter implements FragmentGatewaySettingContract.Presenter {

    FragmentGatewaySettingContract.View mView;

    @Inject
    public FragmentGatewaySettingPresenter(FragmentGatewaySettingContract.View mView) {
        this.mView = mView;
    }

    @Override
    public void start() {

    }

    @Override
    public void stop() {

    }

    /*@Override
    public void localAccessClick() {
        mView.showLocalAccess();
    }

    @Override
    public void remoteAccessClick() {
        mView.showRemoteAccess();
    }*/

    /*@Override
    public void saveGatewaySettingClick() {
        mView.saveGatewaySetting();
    }

    @Override
    public void cancelGatewaySettingClick() {
        mView.cancelGatewaySetting();
    }*/
}
