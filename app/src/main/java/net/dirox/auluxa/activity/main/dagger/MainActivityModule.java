package net.dirox.auluxa.activity.main.dagger;

import net.dirox.auluxa.activity.main.MainActivityContract;
import net.dirox.auluxa.activity.main.MainActivityPresenter;
import dagger.Module;
import dagger.Provides;

@Module
public class MainActivityModule {
    private final MainActivityContract.View view;

    public MainActivityModule(MainActivityContract.View view) {
        this.view = view;
    }

    @Provides
    public MainActivityContract.View view() {
        return view;
    }

    @Provides
    public MainActivityContract.Presenter presenter(MainActivityPresenter presenter) {
        return presenter;
    }
}
