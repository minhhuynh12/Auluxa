package net.dirox.auluxa.activity.main.dagger;

import net.dirox.auluxa.activity.main.view.MainActivity;
import net.dirox.auluxa.common.dagger.ActivityComponent;
import net.dirox.auluxa.common.dagger.SessionActivity;
import dagger.Component;

@SessionActivity
@Component(dependencies = {ActivityComponent.class}, modules = MainActivityModule.class)
public interface MainActivityComponent {
    void inject(MainActivity activity);
}
