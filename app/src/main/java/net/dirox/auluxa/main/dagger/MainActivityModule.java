package net.dirox.auluxa.main.dagger;

import net.dirox.auluxa.main.MainActivityContract;
import net.dirox.auluxa.main.MainActivityPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by MINH NGUYEN on 6/8/2017.
 */
@Module
public class MainActivityModule {
    private final MainActivityContract.View mView;

    public MainActivityModule(MainActivityContract.View mView) {
        this.mView = mView;
    }

    @Provides
    public MainActivityContract.View view() {
        return mView;
    }

    @Provides
    public MainActivityContract.Presenter presenter(MainActivityContract.View view) {
        return new MainActivityPresenter(view);
    }

}
