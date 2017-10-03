package net.dirox.auluxa.common.dagger;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

import dagger.Component;

@PerActivity
@Component(dependencies = {AppComponent.class}, modules = ActivityModule.class)
public interface ActivityComponent {
    Application application();

    Activity activity();

    Context context();
}
