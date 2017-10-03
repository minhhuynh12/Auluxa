package net.dirox.auluxa.activity.main;

import net.dirox.auluxa.data.local.AppInfo;

import javax.inject.Inject;

public class MainActivityPresenter implements MainActivityContract.Presenter {
    private final MainActivityContract.View view;

    @Inject
    AppInfo appInfo;

    @Inject
    MainActivityPresenter(MainActivityContract.View view) {
        this.view = view;
    }


    @Override
    public void validateAppInfo() {
        view.showLoginInput(appInfo.isSetup());
    }

    @Override
    public void setupButtonClicked() {
        view.showLoginSetup();
    }

    @Override
    public void start() {

    }

    @Override
    public void stop() {

    }
}
