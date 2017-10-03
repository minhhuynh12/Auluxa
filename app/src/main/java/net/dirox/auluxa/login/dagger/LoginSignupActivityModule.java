package net.dirox.auluxa.login.dagger;

import net.dirox.auluxa.login.LoginSignupContract;
import net.dirox.auluxa.login.LoginSignupPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by minh on 14/06/2017.
 */
@Module
public class LoginSignupActivityModule {
    private final LoginSignupContract.View mView;

    public LoginSignupActivityModule(LoginSignupContract.View mView){this.mView = mView; }

    @Provides
    LoginSignupContract.View view(){return mView;}

    @Provides
    LoginSignupContract.Presenter presenter(LoginSignupContract.View view){
        return new LoginSignupPresenter(view);
    }
}
